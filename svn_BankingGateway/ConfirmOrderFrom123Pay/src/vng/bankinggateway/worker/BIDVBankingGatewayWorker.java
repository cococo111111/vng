/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.worker;

import java.util.concurrent.ArrayBlockingQueue;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.esaleservice.client.ConfirmOrderBankServiceHandler;
import vng.bankinggateway.service.ZionDataObj;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class BIDVBankingGatewayWorker implements IWorkerRunable {

    private static final Logger log_tranx = Logger.getLogger("serviceActions");
    private static final Logger log_system = Logger.getLogger("systemActions");
    private boolean stoped = false;
    private ArrayBlockingQueue<String> workerQueue = null;
    private static final String SUCCESS = "1";

    public BIDVBankingGatewayWorker() {
        int _recyclezkQueueSize = Integer.parseInt(System.getProperty("recyclezkqueuesize", "512"));
        workerQueue = new ArrayBlockingQueue<String>(_recyclezkQueueSize, true);
    }

    @Override
    public void stop() {
        stoped = true;
    }

    @Override
    public void pushJob(String obj) {
        try {
            if (workerQueue != null) {
                workerQueue.put(obj);
            }
        } catch (InterruptedException ex) {
            log_system.warn(ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (!stoped) {
            try {
                ZionDataObj zionDataObj = new ZionDataObj();
                if (!zionDataObj.fromQueueMessage(workerQueue.take())) {
                    break;
                }

                boolean isSuccess = (zionDataObj.getResponseCode() != null
                        && zionDataObj.getResponseCode().trim().equals(SUCCESS));
                
                String convertResponseCode = "00";
                if (!isSuccess) {
                    convertResponseCode = zionDataObj.getResponseCode();
                }
                
                String errorMessage = "";
                // call webservice Esale
                log_system.info("call webservice Esale");
                T_Transaction tx = null;
                int esaleStatus = 0;
                try {
                    tx = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                            CommonDef.STORAGE_PORT).getTransactionWithBankCode(Integer.valueOf(zionDataObj.getTransactionID()),
                            zionDataObj.getTime(), CommonDef.BANKCODE.BIDVBANK);
                    // assign agencyCode before confirm for Esale because VietTin doesnot care about agencyCode
                    zionDataObj.setAgencyCode(tx.getAgencyCode());
                    esaleStatus = ConfirmOrderBankServiceHandler.getInstance(CommonDef.ESALE_WEBSERVICE_URL).
                            confirmOrderBankService(zionDataObj.getAgencyCode(),
                            tx.getRefID(), convertResponseCode, zionDataObj.getRefId123pay(), tx.getBankCode());
                } catch (Exception ex) {
                    log_system.error(ex.getMessage());
                    errorMessage = ex.getMessage();
                }

                if (tx == null) {
                    log_system.error("CAN NOT GET TRANSACTION WITH transactionID = "
                            + zionDataObj.getTransactionID() + " and time = " + zionDataObj.getTime());
                    return;
                }

                short confirmStatus;
                if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT)) {
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT;
                } else if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)) {
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT;
                } else {
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                }

                if (esaleStatus != 1 && esaleStatus != -1002 && confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS) {
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR;
                }

                // update status for transaction in DB
                log_system.info("update status for transaction");
                // Storage DB
                short status = (isSuccess) ? (short) 100 : -1;
                tx.setBankAccountCode(zionDataObj.getRefId123pay());
                tx.setBankAccountNumber("");
                tx.setConfirmStatus(confirmStatus);

                int ret = -1;
                try {
                    ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                            CommonDef.STORAGE_PORT).updateTranxAndStatus(tx, status, zionDataObj.getResponseCode());
                } catch (TException ex) {
                    log_system.error(ex.getMessage());
                }
                if (ret == -1) {
                    log_system.error("Can not UPDATE STATUS for transactionID = " + zionDataObj.getTransactionID());
                }
            } catch (InterruptedException ex) {
                log_system.info(ex.getMessage());
            }
        }
    }
}
