/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.util.InitUtil;
import vng.bankinggateway.worker.BIDVBankingGatewayWorker;


/**
 *
 * @author root
 */
public class ConfirmOrderService {

    private static final Logger log_system = Logger.getLogger("systemActions");
    private static final Logger log_tranx = Logger.getLogger("gatewayActions");
    private final ConcurrentLinkedHashMap<String, Byte> _filter;
    private final ReentrantLock _locker = new ReentrantLock();
    private BIDVBankingGatewayWorker _bankingGatewayWorker = null;
    
    private static class RETURN_CODE {
        public static final int AUTHEN_FAIL = -5000;
        public static final int UNKNOWN_ERROR = -99999;
        public static final int INVALID_DATA = -1000;
        public static final int CONFIRM_BEFORE = -1001;
        public static final int ERROR = 0;
        public static final int SUCCESS = 1;
    }
    
    public ConfirmOrderService() {
        InitUtil.initConfiguration();

        _filter = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, 10000);
        DateFormat df = new SimpleDateFormat("MMddyy");
        try {
            String day = df.format(new Date());
            List<T_TransactionReport> listTranxs = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).getTranxsWithBankCode(day, (short) 0, true, CommonDef.BANKCODE.BIDVBANK);
            if (listTranxs != null) {
                for (int i = 0; i < listTranxs.size(); i++) {
                    _filter.put(listTranxs.get(i).getTxID()+ "|" + day, Byte.MIN_VALUE);
                }
            }
        } catch (TException ex) {
            log_system.error(ex.getMessage());
        }

        log_system.info("Init BankingGatewayWorker ...");
        _bankingGatewayWorker = new BIDVBankingGatewayWorker();
        try {
            Thread thread = new Thread(_bankingGatewayWorker);
            thread.start();
        } catch (Exception e) {
        }

        log_system.info("Init BankingGatewayWorker success!");
    }
    
    
    public int confirmOrderBy123Pay (String responseCode, String agencyCode, 
            String transactionId, String refTransId123Pay, String time, String sig) {
        ZionDataObj zionData = new ZionDataObj(responseCode, agencyCode, transactionId, refTransId123Pay, time, sig);
        log_system.info("Request from 123pay := " + zionData.toStringForSubscribe());
        if(!sig.equalsIgnoreCase(Encryption.SHA1(zionData.toString() + InitUtil.PASSCODE))) {
            log_system.error("Invalid Data");
            return RETURN_CODE.AUTHEN_FAIL;
        }
        
        if(isExistedAndPutTransaction(zionData.getTransactionID())) {
            log_system.info("Giao dịch đã được confirm " + zionData.getTransactionID());
            return RETURN_CODE.CONFIRM_BEFORE;
        }
        
        
        ScriberBankingGateway.sendLogWithCurrentTime(zionData.toStringForSubscribe());
        log_tranx.info("Request from 123pay := " + zionData.toStringForSubscribe());
        _bankingGatewayWorker.pushJob(zionData.toQueueMessage());
        log_system.info("Response CONFIRM ORDER for 123 Pay " + RETURN_CODE.SUCCESS);        
        return RETURN_CODE.SUCCESS;
    }
    
    // check confirm transaction ID
    private boolean isExistedAndPutTransaction(String trnsactionID){
        boolean res = true;
        try {
            _locker.lock();
            if (!_filter.containsKey(trnsactionID)) {
                _filter.put(trnsactionID, Byte.MIN_VALUE);
                res = false;
            }
        } finally {
            _locker.unlock();
        }
        return res;
    }
}
