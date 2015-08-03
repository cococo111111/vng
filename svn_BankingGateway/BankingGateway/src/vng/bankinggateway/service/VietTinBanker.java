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
import vng.bankinggateway.viettinbankservice.client.VietTinBankServiceHandler;

/**
 *
 * @author root
 */
public class VietTinBanker implements IBanker {

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
        log_tranx.info("Request from Esale for VietTinBank : " + s);
        log.info("Request from Esale VietTinBank : " + s);

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

        // conver time
        String formatTime = formatTime(time, MMddyyHHmmss, yyyyMMddHHmmss);

        VietTinDataObj requestObj = new VietTinDataObj(refID, formatTime, username, agencyCode, region, amount,
                clientIP, description, bankCode, transferFee, urlCallBack, transferType, InitUtil.VIETTINBANK_PAYMENTMETHOD);

        requestObj.setTransactionID(String.valueOf(tranxID));

        if(transferFee.trim().length() == 0) {
            transferFee = "3.300";
        }
        // set terminalCode
        if (requestObj.getRegion().equals(CommonDef.ZION_REGION.ZIONNAM)) {
            requestObj.setProviderCode(InitUtil.TERMINAL_CODE_1);
            requestObj.setDescription(String.format(DESCRIPTION, requestObj.getDescription(), agencyCode, transferFee, "Mien Nam"));
        } else if (requestObj.getRegion().equals(CommonDef.ZION_REGION.ZIONBAC)) {
            requestObj.setDescription(String.format(DESCRIPTION, requestObj.getDescription(), agencyCode, transferFee, "Mien Bac"));
            requestObj.setProviderCode(InitUtil.TERMINAL_CODE_2);
        } else {
            response.code = 80;
            log.info("Region is wrong for refID = " + refID);
            return response;
        }
        
        String beforeMAC = requestObj.toStringForRequestWithoutSignature();
        String mac = Encryption.MD5(beforeMAC + InitUtil.SECRETKEY);
        String afterMAC = beforeMAC + mac + "|";

        // Create tranaction IN DB
        T_Transaction tx = new T_Transaction(requestObj.getBankCode(), Integer.valueOf(requestObj.getTransactionID()),
                StorageCommonDef.TRANX_TYPE.ESALE_EBANKING, time,
                requestObj.getUsername(), requestObj.getAgencyCode(), requestObj.getProviderCode(), refID,
                requestObj.getAmount(), requestObj.getClientIP(), requestObj.getDescription(), "", "", (short) 0);
        int ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).storeTranx(tx);
        if (ret == -1) {
            response.code = 51;
            log.info("DB Error for refID = " + refID);
            return response;
        }

        // Send to bank
        log_tranx.info("SEND TO VietTinBANK " + afterMAC);
        ScriberBankingGateway.sendLogWithCurrentTime(requestObj.getScribeMessageForRequest());

        VietTinBankServiceHandler handler = VietTinBankServiceHandler.getInstance(InitUtil.VIETTINBANK_URL);

        String responseStr = handler.callMerchantSvcService(INITTRANS, afterMAC);
        if (responseStr.equals(CommonDef.INVALID_CONNECTION)) {
            log.info("URL of BANK is wrong! with url =" + InitUtil.VIETTINBANK_URL);
            T_TranStat txStatus = new T_TranStat(Integer.valueOf(requestObj.getTransactionID()),
                    time, CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid VIETTIN BANK URL");
            BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            response.code = 99;
            return response;
        }

        log.info("Response from VIETTIN BANK " + responseStr);

        VietTinDataObj responseObj = new VietTinDataObj();
        if (!responseObj.fromStringForResponse(responseStr)) {
            response.code = 92;
            log.info("Chuỗi yêu cầu sai định dạng");
            return response;
        }

        String MAC2 = Encryption.MD5(responseObj.toStringForResponseWithoutSignature() + InitUtil.SECRETKEY);

        // verify MAC
        if (!MAC2.equals(responseObj.getSignature())) {
            log.info("Wrong MAC");
            response.code = 97;
            log.info("Chữ ký không hợp lệ");
            return response;
        }

        log.info("Correct MAC");
        log.info(responseObj.getScribeMessageForResponse());

        boolean isSuccess = (responseObj.getResponseCode() != null
                && responseObj.getResponseCode().trim().equals("00"));
        log_tranx.info("Receive From VIETTIN BANK: " + responseObj.getScribeMessageForResponse());
        ScriberBankingGateway.sendLogWithCurrentTime(responseObj.getScribeMessageForResponse());
        // Update status for DB if error
        // Storage DB
        if (!isSuccess) {
            T_TranStat txStatus = new T_TranStat(Integer.valueOf(responseObj.getTransactionID()),
                    time, (short) -1, responseObj.getResponseCode(), "");
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            if (ret == -1) {
                log.info("DB Error for update status with transacID = " + tranxID);
                response.code = 51;
                return response;
            }
        }

        T_Response res = new T_Response(Integer.parseInt(responseObj.getResponseCode()),
                responseObj.getUrlRedirect(), refID, String.valueOf(responseObj.getTransactionID()));

        log.info("Send redirect URL for ESALE " + res.redirectURL);
        res.refID = refID;
        res.transactionID = String.valueOf(tranxID);
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
                    tx.getRefID(), responseCode, String.valueOf(tx.getTxID()), tx.getBankCode());
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
        SimpleDateFormat df = new SimpleDateFormat(yyyyMMddHHmmss);
        String time = df.format(new Date());

        VietTinQueryObj queryObj = new VietTinQueryObj(tx.getProviderCode(), String.valueOf(tx.getTxID()), tx.getBankAccountCode(), time, "");

        String beforeMAC = queryObj.toStringForRequestWithoutSignature();
        String mac = Encryption.MD5(beforeMAC + InitUtil.SECRETKEY);
        String afterMAC = beforeMAC + mac + "|";

        // Send to bank
        log_tranx.info("SEND TO VIETTIN BANK For QUERY " + afterMAC);
        log_tranx.info(queryObj.getScribeMessageForRequest());
        ScriberBankingGateway.sendLogWithCurrentTime(queryObj.getScribeMessageForRequest());

        VietTinBankServiceHandler handler = VietTinBankServiceHandler.getInstance(InitUtil.VIETTINBANK_URL);
        String responseStr = handler.callMerchantSvcService(CHECKTRANS, afterMAC);

        if (CommonDef.INVALID_CONNECTION.equals(responseStr)) {
            log.info("URL of BANK is wrong! with url =" + InitUtil.VIETTINBANK_URL);
            txStatus = new T_TranStat(Integer.valueOf(queryObj.getTransactionID()),
                    queryObj.getTime(), CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid VIETTIN BANK URL");
            return txStatus;
        }

        log.info("Response from VIETTINBANK For QUERY " + responseStr);

        // Check before decode
        if (responseStr == null || responseStr.trim().length() == 0) {
            txStatus.setResponseCode("80");
            log.info("response is NULL ");
            return txStatus;
        }

        responseStr = responseStr.trim();
        if (responseStr.length() == 2) {
            try {
                int code = Integer.parseInt(responseStr);
                if (code < 0 || code > 99) {
                    txStatus.setResponseCode("80");
                }
                return txStatus;
            } catch (Exception ex) {
                log.error(ex.getMessage());
                txStatus.setResponseCode("80");
                return txStatus;
            }
        }

        VietTinQueryObj responseObj = new VietTinQueryObj();
        if (!responseObj.fromStringForResponse(responseStr)) {
            txStatus.setResponseCode("92");
            log.info("Chuỗi yêu cầu sai định dạng");
            return txStatus;
        }

        String MAC2 = Encryption.MD5(responseObj.toStringForResponseWithoutSignature() + InitUtil.SECRETKEY);

        // verify MAC
        if (!MAC2.equals(responseObj.getSignature())) {
            log.info("Wrong MAC");
            txStatus.setResponseCode("91");
            return txStatus;
        }

        // conver time
        String formatTime = formatTime(time, yyyyMMddHHmmss, MMddyyHHmmss);

        boolean isSuccess = (responseObj.getResponseCode() != null
                && responseObj.getResponseCode().trim().equals("00"));
        log_tranx.info("Receive From VIETTIN BANK: " + responseObj.getScribeMessageForResponse());
        ScriberBankingGateway.sendLogWithCurrentTime(responseObj.getScribeMessageForResponse());
        short status = (isSuccess) ? CommonDef.TRANSACTION_STATUS.TXS_SUCCESS : CommonDef.TRANSACTION_STATUS.TXS_FAIL;
        txStatus = new T_TranStat(Integer.valueOf(queryObj.getTransactionID()),
                formatTime, status, responseObj.getResponseCode(), "");
        return txStatus;
    }
}
