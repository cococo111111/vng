/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.*;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingGatewayHandler implements TBankingService.Iface {

    private static final Logger log = Logger.getLogger("systemActions");
    private IBanker hdBanker = null;
    private IBanker vietTinBanker = null;
    private IBanker pay123Banker = null;

    public BankingGatewayHandler() {
        hdBanker = new HDBankBanker();
        vietTinBanker = new VietTinBanker();
        pay123Banker = new Pay123Banker();
    }

    private IBanker getBanker(String bankCode) {
        if (bankCode.trim().equals(CommonDef.BANKCODE.HDBANK)) {
            return hdBanker;
//            bankCode = CommonDef.BANKCODE.BIDVBANK;
//            return pay123Banker;
        } else if (bankCode.trim().equals(CommonDef.BANKCODE.VIETINBANK)) {
            return vietTinBanker;
        } else if (bankCode.trim().equals(CommonDef.BANKCODE.BIDVBANK)) {
            return pay123Banker;
        } else {
            return null;
        }
    }

    @Override
    public T_Response requestTransfer(String refID, String time, String username,
            String agencyCode, String region, int amount, String clientIP, String description,
            String bankCode, String commision, String urlCallBack, String transferType, String sig) throws TException {
        IBanker iBanker = getBanker(bankCode);
        if (iBanker != null) {
            return iBanker.requestTransfer(refID, time, username, agencyCode,
                    region, amount, clientIP, description, bankCode, commision,
                    urlCallBack, transferType, sig);
        } else {
            log.error("Wrong BankCode!!! bankCode = " + bankCode);
            T_Response response = new T_Response();
            response.code = 80;
            return response;
        }
    }

    @Override
    public T_TranStat getTranxStatus(String refID, String day) throws TException {
        log.info("getTranxStatus with refID = " + refID + " and day = " + day);
        return BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).getTranxStatus(refID, day);
    }

    @Override
    public List<T_TransactionReport> getTranxs(String day, short txStatus, boolean allStatus) throws TException {
        log.info("getTranxs with txStatus = " + txStatus + " and day = " + day);
        return BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).getTranxs(day, txStatus, allStatus);
    }

    public void reconfirmStatusForEsale(String day, String bankCode) throws TException {
        IBanker iBanker = getBanker(bankCode);
        if (iBanker != null) {
            iBanker.reconfirmStatusForEsale(day, bankCode);
        } else {
            log.error("Wrong BankCode in reconfirmStatusForEsale with bankCode = " + bankCode);
        }
    }

    /**
     * is called by worker Duration Time > 10 minutes Call to Bank to query
     * status for pending transaction
     *
     * @param day : MMddyy
     */
    public void processPendingTransaction(String day, String bankCode) throws TException {
        IBanker iBanker = getBanker(bankCode);
        if (iBanker != null) {
            iBanker.processPendingTransaction(day, bankCode);
        } else {
            log.error("Wrong BankCode in processPendingTransaction with bankCode = " + bankCode);
        }
    }
}
