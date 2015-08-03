/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.bank123payservice.client.Bank123PayServiceHandler;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.esaleservice.client.ConfirmOrderBankServiceHandler;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Response;
import vng.bankinggateway.thrift.T_Task;
import vng.bankinggateway.thrift.T_TranStat;
import vng.bankinggateway.thrift.T_Transaction;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author root
 */
public class Pay123Banker implements IBanker {

    private static final Logger log = Logger.getLogger("systemActions");
    private static final Logger log_tranx = Logger.getLogger("gatewayActions");

    private static final String LOG_FORMAT_CREATED_TRANSACTION = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";

    private static final String MMddyyHHmmss = "MMddyyHHmmss";
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static final String INITTRANS = "inittrans";
    private static final String CHECKTRANS = "checktrans";

    private static final String DESCRIPTION = "%s. Ma Dai Ly: %s. Phi dich vu nap tien: %s VND/giao dich. Nha cung cap: CT TNHH Thuong mai dich vu truyen thong Thanh Son (%s)";

    @Override
    public T_Response requestTransfer(String refID, String time, String username, String agencyCode, String region, int amount, String clientIP, String description, String bankCode, String transferFee, String urlCallBack, String transferType, String sig) throws TException {
        /*
         * 00 Giao dịch thành công
         * 01 Giao dịch cần truy vấn chưa thực hiện thanh toán
         * 02 Không tìm thấy giao dịch cần truy vấn
         * hoặc giao dịch đã quá thời hạn thanh toán
         * 06 Giao dich bi loi
         * 10 Giao dịch bị lỗi do quá thời hạn thanh toán
         * 11 Mã giao dịch không hợp lệ
         * 12 Số tiền/mã tiền tệ thanh toán không hợp lệ
         * 13 MerchantID/ProviderID không hợp lệ
         * 20 Khách hàng thực hiện hủy giao dịch thanh toán tại ngân hàng.
         * 90 Không thể giải mã chuỗi mã hóa 3DES (chỉ trả về mã 90,
         * không mã hóa)
         * 91 Sai mã MAC giao dịch. (chỉ trả về mã
         * 91, không mã hóa)
         * 92 Chuỗi yêu cầu sai định dạng (chỉ
         * trả về mã 92, không mã hóa)
         * 99 Hệ thống tạm ngừng phục vụ
         *
         * 51 DB ERROR
         * 80 Something Wrong
         * 87 orderNo da xu ly
         * 88 sai SHA1
         */

        String s = String.format(LOG_FORMAT_CREATED_TRANSACTION, refID, time, username, agencyCode, region,
                String.valueOf(amount), clientIP, description, sig, bankCode, transferFee, urlCallBack, transferType);
        // write log4j
        log_tranx.info("Request from Esale for Pay123Bank : " + s);
        log.info("Request from Esale Pay123Bank : " + s);

        // write scribe
        ScriberBankingGateway.sendLogCreateTransactionFromEsale(refID, time, username,
                agencyCode, region, amount, clientIP, description, sig);

        T_Response response = new T_Response();
        response.code = 0;

        // Check duplicate transaction
        if (!BankingGatewayMain.checkTransaction(refID)) {
            response.code = 87;
            log.info("Duplicate transaction with refID = " + refID);
            return response;
        }

        String checkSum = refID + time + username + agencyCode + region
                + amount + clientIP + description + bankCode + transferFee
                + urlCallBack + transferType + CommonDef.SHA1_SHARED_KEY;
        checkSum = Encryption.SHA1(checkSum);
        if (!checkSum.equalsIgnoreCase(sig)) {
            log.info("SHA1 is wrong for refID = " + refID);
            response.code = 88; // 88 sai SHA1
            return response;
        }

        if (amount <= 0) {
            response.code = 12;
            log.info("Số tiền/mã tiền tệ thanh toán không hợp lệ for refID = " + refID);
            return response;
        }

        // generate tranxID
        int tranxID = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).generateTransIdByBankCode(bankCode);

        log.info("tranID = " + tranxID + " for refID " + refID);

        if (transferFee.trim().length() == 0) {
            transferFee = "3.300";
        }
        // set terminalCode
        if (region.equals(CommonDef.ZION_REGION.ZIONNAM)) {
            description = String.format(DESCRIPTION, description, agencyCode, transferFee, "Mien Nam");
        } else if (region.equals(CommonDef.ZION_REGION.ZIONBAC)) {
            description = String.format(DESCRIPTION, description, agencyCode, transferFee, "Mien Bac");
        } else {
            response.code = 80;
            log.info("Region is wrong for refID = " + refID);
            return response;
        }

        // Create tranaction IN DB
        T_Transaction tx = new T_Transaction(CommonDef.BANKCODE.BIDVBANK, tranxID,
                StorageCommonDef.TRANX_TYPE.ESALE_EBANKING, time,
                username, agencyCode, InitUtil.MERCHANTCODE, refID,
                amount, clientIP, description, "", "", (short) 0);
        int ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).storeTranx(tx);
        if (ret == -1) {
            response.code = 51;
            log.info("DB Error for refID = " + refID);
            return response;
        }

        // Send to bank
        Bank123PayServiceHandler handler = Bank123PayServiceHandler.getInstance(InitUtil.URL_123PAY, InitUtil.URL_123PAY_QUERY);
        String redirectUrl = makeRedirectURL(tranxID, time, "0", String.valueOf(amount));
        String errorUrl = makeRedirectURL(tranxID, time, "6", String.valueOf(amount));
        String[] responseStrArray = handler.call123PayService(refID, InitUtil.MERCHANTCODE,
                agencyCode, CommonDef.BANKCODE.BIDVBANK, String.valueOf(tranxID), username, agencyCode, "", "", clientIP, "", "", "", "", "", "", description, String.valueOf(amount), region, redirectUrl, redirectUrl, errorUrl);
        if (responseStrArray.length == 1 && responseStrArray[0].equals(CommonDef.INVALID_CONNECTION)) {
            log.info("URL of BANK is wrong! with url =" + InitUtil.URL_123PAY);
            T_TranStat txStatus = new T_TranStat(tranxID,
                    time, CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid PAY123 BANK URL");
            BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            response.code = 99;
            return response;
        }

        if (!responseStrArray[0].equals("1")) {
            response.code = 6;
            log.info("loi goi ve tu 123pay " + responseStrArray[0]);
        } else if (responseStrArray.length != 4) {
            log.info("Chuỗi yêu cầu sai định dạng from 123pay for refId = " + tranxID);
            response.code = 88; // 88 sai SHA1
        } else {
            String checksum = Encryption.SHA1(responseStrArray[0] + responseStrArray[1] + responseStrArray[2] + InitUtil.SECRETKEY_123PAY);
            if (!checksum.equalsIgnoreCase(responseStrArray[3])) {
                log.info("Sai SHA1 from 123pay for refId = " + tranxID);
                response.code = 92;
            } else {
                response.code = 0;
                response.refID = refID;
                response.redirectURL = responseStrArray[2];
                response.transactionID = String.valueOf(tranxID);
                log.info("Send redirect URL for ESALE " + response.redirectURL);
            }
        }
        
        // Update status for DB if error
        if(response.code != 0) {
            // Storage DB
            T_TranStat txStatus = new T_TranStat(tranxID,
                    time, (short) -1, responseStrArray[0], "");
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            if (ret == -1) {
                log.info("DB Error for update status with transacID = " + tranxID);
                response.code = 51;
                return response;
            }
        }
        
        return response;
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

    @Override
    public void reconfirmStatusForEsale(String day, String bankCode) throws TException {
        SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");

        T_Task task = new T_Task();
        task.setTaskId(StorageCommonDef.TASK_ID_RUN_RECOMFIRM);
        task.setStatus((short) StorageCommonDef.Task_Status.RUNNING.ordinal());
        task.setStartTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).updateTaskStart(task);

        List<T_TransactionReport> list = BankingStorageClient.getMainInstance(
                CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).getTranxsWithConfirmStatusAndBankCode(day,
                CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT, bankCode);
        if (list != null) {
            reconfirmStatusForEsale(list);
        }
        list = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).getTranxsWithConfirmStatusAndBankCode(day,
                CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT, bankCode);
        if (list != null) {
            reconfirmStatusForEsale(list);
        }
        list = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).getTranxsWithConfirmStatusAndBankCode(day,
                CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR, bankCode);
        if (list != null) {
            reconfirmStatusForEsale(list);
        }

        T_Task endTask = new T_Task();
        endTask.setTaskId(StorageCommonDef.TASK_ID_RUN_RECOMFIRM);
        endTask.setStatus((short) StorageCommonDef.Task_Status.SUCCESS.ordinal());
        endTask.setEndTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).updateTaskStop(endTask);
    }

    private void reconfirmStatusForEsale(List<T_TransactionReport> list) {
        for (T_TransactionReport tx_report : list) {
            switch (tx_report.txType) {
                case CommonDef.BANK_METHOD.ESALE_INTERNET_BANKING:
                    reconfirmEsaleTransaction(tx_report);
                    break;

                case CommonDef.BANK_METHOD.INTERNET_BANKING:
                    try {
                    reconfirmInternetBankingTransaction(tx_report);
                } catch (AxisFault ex) {
                }
                    break;

                default:
                    log.info("not yet implement these transaction");
                    break;
            }
        }
    }

    private void reconfirmInternetBankingTransaction(T_TransactionReport tx_report) throws AxisFault {
        // NA
    }

    private void reconfirmEsaleTransaction(T_TransactionReport tx_report) {
        String errorMessage = "";
        // call webservice Esale
        log.info("Recall webservice Esale to update CONFIRM STATUS");
        int esaleStatus = 0;
        try {
            esaleStatus = ConfirmOrderBankServiceHandler.getInstance(
                    CommonDef.ESALE_WEBSERVICE_URL).confirmOrderBankService(
                    tx_report.getAgencyCode(), tx_report.getRefID(),
                    tx_report.getResponseCode(), String.valueOf(tx_report.getTxID()), tx_report.getBankCode());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            errorMessage = ex.getMessage();
        }

        log.info("Esale Confirm STATUS = " + esaleStatus);

        // Check Confirm Status
        if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT)
                || errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)
                || esaleStatus != 1) {
            return;
        }

        // update Confirm Status for transaction in DB
        log.info("update Confirm status for transaction");
        T_Transaction tx = new T_Transaction();
        tx.setTxID(tx_report.getTxID());
        tx.setTime(tx_report.getTime());
        tx.setBankAccountCode(tx_report.getBankAccountCode());
        tx.setBankAccountNumber(tx_report.getBankAccountNumber());
        tx.setRefID(tx_report.getRefID());
        tx.setConfirmStatus(CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS);

        int ret = -1;
        try {
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTransaction(tx);
        } catch (TException ex) {
            log.error(ex.getMessage());
        }
        if (ret == -1) {
            log.error("Can not UPDATE TRANSACTION for transactionID = " + tx.getTxID());
        }
    }

    @Override
    public void processPendingTransaction(String day, String bankCode) throws TException {
        log.info("Process pending for day = " + day + " for bankCode = " + bankCode);

        SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");
        T_Task task = new T_Task();
        task.setTaskId(StorageCommonDef.TASK_ID_PROCESS_PENDING);
        task.setStatus((short) StorageCommonDef.Task_Status.RUNNING.ordinal());
        task.setStartTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).updateTaskStart(task);

        List<T_TransactionReport> list = BankingStorageClient.getMainInstance(
                CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT)
                .getTranxsWithBankCode(day, (short) 1, false, bankCode);
        log.info("Total pending = " + list.size() + " for bankCode = " + bankCode);

        processPendingTransaction(list);

        T_Task endTask = new T_Task();
        endTask.setTaskId(StorageCommonDef.TASK_ID_PROCESS_PENDING);
        endTask.setStatus((short) StorageCommonDef.Task_Status.SUCCESS.ordinal());
        endTask.setEndTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).updateTaskStop(endTask);
    }

    private void processPendingTransaction(List<T_TransactionReport> list) throws TException {
        SimpleDateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        for (T_TransactionReport tx_report : list) {
            long lTime = 0;
            try {
                lTime = df.parse(tx_report.getTime()).getTime();
            } catch (ParseException ex) {
                ex.getMessage();
            }
            // CHECK DURATION TIME
            if (System.currentTimeMillis() - lTime < InitUtil.DURATION_TIME_PENDING) {
                continue;
            }
            log.info("Process Pending for transaction " + tx_report.toString());

            T_Transaction tx = new T_Transaction(tx_report.getBankCode(),
                    tx_report.getTxID(), tx_report.getTxType(),
                    tx_report.getTime(), tx_report.getUserName(),
                    tx_report.getAgencyCode(), tx_report.getProviderCode(),
                    tx_report.getRefID(), tx_report.getAmount(), tx_report.getClientIP(),
                    tx_report.getDescription(), tx_report.getBankAccountCode(),
                    tx_report.getBankAccountNumber(), tx_report.getConfirmStatus());

            log.info("call  queryTransaction");
            T_TranStat txStatus = queryEsalseTransaction(tx);

            log.info("status of response code " + txStatus.getResponseCode());

            switch (tx_report.txType) {
                case CommonDef.BANK_METHOD.ESALE_INTERNET_BANKING:
//                    if ("00".equals(txStatus.getResponseCode())
//                            || "01".equals(txStatus.getResponseCode())
//                            || "02".equals(txStatus.getResponseCode())) {
                    if (!"99".equals(txStatus.getResponseCode())) {
                    processPendingForEsaleTransaction(tx, txStatus.getResponseCode(),
                            txStatus.getTxStatus());
                }
                    break;

                case CommonDef.BANK_METHOD.INTERNET_BANKING:
                    log.info("call  queryInternetTransaction");
                    processPendingForInternetTransaction(tx, tx_report.responseCode);
                    break;

                default:
                    break;
            }
        }
    }

    private void processPendingForEsaleTransaction(T_Transaction tx, String responseCode, short status) {
        String errorMessage = "";
        // call webservice Esale
        log.info("Call webservice Esale to update STATUS");
        int esaleStatus = 0;
        try {
            esaleStatus = ConfirmOrderBankServiceHandler.getInstance(CommonDef.ESALE_WEBSERVICE_URL)
                    .confirmOrderBankService(
                    tx.getAgencyCode(),
                    tx.getRefID(), responseCode, tx.getBankAccountCode(), tx.getBankCode());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            errorMessage = ex.getMessage();
        }

        log.info("Esale Confirm STATUS = " + esaleStatus);

        // Check Confirm Status
        short confirmStatus;
        if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT;
        } else if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT;
        } else {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
        }

        /*
         * -1002: adready added/confirmed
         *  WEBSERVICE_RESPONSE_ERROR : call webservice is ok, but response code is failed
         */
        if (esaleStatus != 1 && esaleStatus != -1002
                && confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR;
        }

        // update status for transaction in DB
        log.info("update status for transaction");
        // Storage DB
        tx.setConfirmStatus(confirmStatus);

        int ret = -1;
        try {
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxAndStatus(tx, status, responseCode);
        } catch (TException ex) {
            log.error(ex.getMessage());
        }
        if (ret == -1) {
            log.error("Can not UPDATE STATUS for transactionID = " + tx.getTxID());
        }
    }

    private void processPendingForInternetTransaction(T_Transaction tx, String responseCode) {
        // N/A
    }

    private T_TranStat queryEsalseTransaction(T_Transaction tx)
            throws TException {
        T_TranStat txStatus = new T_TranStat();
        Bank123PayServiceHandler handler = Bank123PayServiceHandler.getInstance(InitUtil.URL_123PAY, InitUtil.URL_123PAY_QUERY);
        String[] responseStrArray = handler.call123PayQueryService(tx.getProviderCode(), String.valueOf(tx.getTxID()), tx.getClientIP());

        if (responseStrArray.length == 1 && responseStrArray[0].equals(CommonDef.INVALID_CONNECTION)) {
            log.info("URL of BANK is wrong! with url =" + InitUtil.URL_123PAY_QUERY);
            txStatus = new T_TranStat(tx.getTxID(),
                    tx.getTime(), CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid PAY123 BANK URL");
            return txStatus;
        }

        if (!responseStrArray[0].equals("1")) {
            log.info("Query khong thanh cong from 123pay for refId = " + tx.getTxID());
            txStatus.setResponseCode("99");
        } else if (responseStrArray.length != 8) {
            log.info("Chuỗi yêu cầu sai định dạng from 123pay for refId = " + tx.getTxID());
            txStatus.setResponseCode("88");
        } else {
            // check check sum
            String checksum = Encryption.SHA1(responseStrArray[0] + responseStrArray[1] 
                    + responseStrArray[2] + responseStrArray[3] + responseStrArray[4] + responseStrArray[5] + InitUtil.SECRETKEY_123PAY);
            if (!checksum.equalsIgnoreCase(responseStrArray[7])) {
                log.info("Sai SHA1 from 123pay for refId = " + tx.getTxID());
                txStatus.setResponseCode("92");
            } else {
                if (!responseStrArray[2].equals("1")) {
                    short status = CommonDef.TRANSACTION_STATUS.TXS_FAIL;
                    txStatus = new T_TranStat(tx.getTxID(),
                            tx.getTime(), status, responseStrArray[2], "");
                    return txStatus;
                } else {
                    short status = CommonDef.TRANSACTION_STATUS.TXS_SUCCESS;
                    txStatus = new T_TranStat(tx.getTxID(),
                            tx.getTime(), status, "00", "");
                    return txStatus;
                }
            }
        }
        return txStatus;
    }

    private String makeRedirectURL(int transactionId, String time, String responseCode, String amount) {
        try {
            T_Transaction tx = BankingStorageClient.getMainInstance(
                    CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).getTransaction(transactionId, time);
            String checkSum = CommonDef.SHA1_SHARED_KEY + responseCode + tx.getRefID() + amount;
            checkSum = Encryption.SHA1(checkSum);
            StringBuilder rediectUrl = new StringBuilder(CommonDef.ESALE_REDIRECT_URL);
            rediectUrl.append("zb_SecureHash=");
            rediectUrl.append(checkSum);
            rediectUrl.append("&zb_MerchTxnRef=");
            rediectUrl.append(tx.getRefID());
            rediectUrl.append("&zb_Amount=");
            rediectUrl.append(amount);
            rediectUrl.append("&zb_ResponseCode=");
            rediectUrl.append(responseCode);
            log.info("redirect_url = " + rediectUrl.toString());
            return rediectUrl.toString();
        } catch (TException ex) {
            log.error(ex.getMessage());
        }
        return CommonDef.ESALE_REDIRECT_URL;
    }
}
