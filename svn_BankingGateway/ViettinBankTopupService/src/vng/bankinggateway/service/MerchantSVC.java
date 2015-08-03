/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.tempuri.ExecuteResponse;
import org.tempuri.HelloWorldResponse;
import org.tempuri.MerchantSVCSkeletonInterface;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.util.InitUtil;
import vng.bankinggateway.worker.BankingGatewayWorker;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class MerchantSVC implements MerchantSVCSkeletonInterface {
    private static final Logger log_tranx = Logger.getLogger("serviceActions");
    private static final Logger log_system = Logger.getLogger("systemActions");
    
    private static final String CONFIRMTRANS = "CONFIRMTRANS";
    private static final String MMddyyHHmmss = "MMddyyHHmmss";
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    //MONEY CODE
    private static final String MONEY_CODE = "VND";
    private BankingGatewayWorker _bankingGatewayWorker = null;
    
    private final ConcurrentLinkedHashMap<String, Byte> _filter;
    private final ReentrantLock _locker = new ReentrantLock();

    public MerchantSVC() {
        InitUtil.initConfiguration();

        log_system.info("Init BankingGatewayWorker ...");
        _bankingGatewayWorker = new BankingGatewayWorker();
        try {
            Thread thread = new Thread(_bankingGatewayWorker);
            thread.start();
        } catch (Exception e) {
        }

        _filter = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, 10000);
        DateFormat df = new SimpleDateFormat("MMddyy");
        try {
            String day = df.format(new Date());
            List<T_TransactionReport> listTranxs = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).getTranxsWithBankCode(day, (short) 0, true, CommonDef.BANKCODE.VIETINBANK);
            if (listTranxs != null) {
                for (int i = 0; i < listTranxs.size(); i++) {
                    _filter.put(listTranxs.get(i).getTxID()+ "|" + day, Byte.MIN_VALUE);
                }
            }
        } catch (TException ex) {
            log_system.error(ex.getMessage());
        }

        log_system.info("Init BankingGatewayWorker success!");
    }

    @Override
    public HelloWorldResponse helloWorld(org.tempuri.HelloWorld helloWorld0) {
        //TODO implement this method
        log_system.info("Not Implement yet");
        HelloWorldResponse helloWorldResponse = new HelloWorldResponse();
        helloWorldResponse.setHelloWorldResult("Not implemented yet!");
        return helloWorldResponse;
    }

    @Override
    public ExecuteResponse execute(org.tempuri.Execute execute) {
        log_system.info("cmdCode = " + execute.getCmdCode() + " strInput = " + execute.getStrInput());
        String errorCode = "00";
        String messageOfResponseCode = "thành công";
        
        ZionDataObj zionDataObj = new ZionDataObj();
        if (!zionDataObj.fromString(execute.getStrInput())) {
            log_system.info("Chuỗi yêu cầu sai định dạng");
            zionDataObj.setResponseCode("97"); // Chữ ký không hợp lệ
        }
        
        String beforMAC = zionDataObj.toStringForRequestWithoutSignature();
        String MAC2 = Encryption.MD5(beforMAC + InitUtil.SECRETKEY);
        
        // verify MAC
        if (!MAC2.equals(zionDataObj.getSignature())) {
            log_system.info("Wrong MAC");
            errorCode = "97"; // Chữ ký không hợp lệ
            messageOfResponseCode = "Chữ ký không hợp lệ";
        }
        
        log_system.info("Correct MAC");
        log_system.info(zionDataObj.getScribeMessageForRequest());
        
        // validate message code
        if (!execute.getCmdCode().equals(CONFIRMTRANS)) {
            log_system.info("wrong Command Code " + zionDataObj.getMessageCode());
            errorCode = "97";
            messageOfResponseCode = "CommandCode không hợp lệ";
        }

        // validate length of transactionID
        if (!(zionDataObj.getTransactionID() != null
                && zionDataObj.getTransactionID().trim().length() > 0)) {
            log_system.info("wrong TransactionID " + zionDataObj.getTransactionID());
            errorCode = "97";
            messageOfResponseCode = "TransactionID không hợp lệ";
        }
        
        if(isExistedAndPutTransaction(zionDataObj.getTransactionID())) {
            log_system.info("Giao dịch đã được confirm " + zionDataObj.getTransactionID());
            errorCode = "02";
            messageOfResponseCode = "Giao dịch đã được confirm";
        }

        // validate Provider Code
        if (!(zionDataObj.getProviderCode() != null
                && (zionDataObj.getProviderCode().equals(InitUtil.TERMINAL_CODE_1)) 
                || zionDataObj.getProviderCode().equals(InitUtil.TERMINAL_CODE_2)) ) {
            log_system.info("wrong Terminalcode " + zionDataObj.getProviderCode());
            errorCode = "99";
            messageOfResponseCode = "Lỗi hệ thống khác";
        }

        // validate money code
        if (!(zionDataObj.getMoneyCode() != null
                && zionDataObj.getMoneyCode().equals(MONEY_CODE))) {
            log_system.info("wrong MoneyCode " + zionDataObj.getMoneyCode());
            errorCode = "99";
            messageOfResponseCode = "Lỗi hệ thống khác";
        }

        // log scriber and log tranx
        log_tranx.info("Receive CONFIRM ORDER From VietTin BANK " + zionDataObj.getScribeMessageForRequest());
        ScriberBankingGateway.sendLogWithCurrentTime(zionDataObj.getScribeMessageForRequest());
        
        // formatTime
        zionDataObj.setTime(formatTime(zionDataObj.getTime(), yyyyMMddHHmmss, MMddyyHHmmss));
        
        if(errorCode.equals("00")) {
            _bankingGatewayWorker.pushJob(zionDataObj.toQueueMessage());
        }

        zionDataObj.setTime(parseTime(new Date(), yyyyMMddHHmmss));
        zionDataObj.setDescription(messageOfResponseCode);
        zionDataObj.setResponseCode(errorCode);

        log_system.info("Response CONFIRM ORDER for VietTin BANK " + zionDataObj.toStringForResponse());

        String beforeMACresponse = zionDataObj.toStringForResponse();
        String MACResponse = Encryption.MD5(beforeMACresponse + InitUtil.SECRETKEY);
        
        ExecuteResponse response = new ExecuteResponse();
        response.setExecuteResult(beforeMACresponse + MACResponse + "|");      
        
        return response;
    }

    private boolean checkFormatTime(String time) {
        if (time == null) {
            return false;
        }
        if (time.isEmpty()) {
            return false;
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MMddyyHHmmss");
        try {
            date = format.parse(time);
        } catch (Exception e) {
        }
        if (date == null) {
            return false;
        }
        return true;
    }

    private boolean moneyCheck(String money) {
        if (money == null) {
            return false;
        }
        if (money.length() < 0 || money.length() > 10) {
            return false;
        }
        for (int i = 0; i < money.length(); i++) {
            char c = money.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
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

    private String formatTime(String inputTime, String fromDateFormat, String toDateFormat) {
        try {
            DateFormat df = new SimpleDateFormat(fromDateFormat);
            Date date = df.parse(inputTime);
            DateFormat dfTransxTime = new SimpleDateFormat(toDateFormat);
            return dfTransxTime.format(date);
        } catch (ParseException ex) {
        }
        return "";
    }
    
    private String parseTime(Date date, String dateFormat) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            return df.format(date);
        } catch (Exception ex) {
        }
        return "";
    }
    
}
