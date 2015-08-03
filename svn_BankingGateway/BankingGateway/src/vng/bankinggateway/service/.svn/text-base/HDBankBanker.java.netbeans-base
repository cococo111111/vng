/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.esaleservice.client.ConfirmOrderBankServiceHandler;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub;
import vng.bankinggateway.hdbankservice.client.HDBankServiceHandler;
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
public class HDBankBanker implements IBanker {

    private static final Logger log = Logger.getLogger("systemActions");
    private static final Logger log_tranx = Logger.getLogger("gatewayActions");
    private static final String PROCESS_TRANSACTION_MESSAGE_CODE = "0200";
    private static final String PROCESS_TRANSACTION_ACTION_CODE = "000001";
    private static final String RESPONSE_TRANSACTION_MESSAGE_CODE = "0210";
    private static final String QUERY_TRANSACTION_MESSAGE_CODE = "9000";
    private static final String QUERY_TRANSACTION_ACTION_CODE = "000006";
    private static final String RESPONSE_QUERY_TRANSACTION_MESSAGE_CODE = "9010";
    private static final String TRANSACTION_MONEY_CODE = "704";
    private static final String LOG_FORMAT_CREATED_TRANSACTION = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";
    private static final String INTERNET_BANKING = "EBANKING";
    private static final String MOBILE_BANKING = "APPMOBILE";
    private static final int TOPUP_RESPONSE_SUCCESS = 1;
    private static final int OBJ_NOT_EXIST_0 = 0;
    private static final int DUPLICATE_TRANSACTION = -1;
    private static final short INTERNET_TYPE = 2;
    private static final short MOBILE_TYPE = 3;

    @Override
    public T_Response requestTransfer(String refID, String time, String username,
            String agencyCode, String region, int amount, String clientIP,
            String description, String bankCode, String transferFee, String urlCallBack,
            String transferType, String sig) throws TException {
        /*
         * 00 Giao dịch thành công
         * 01 Giao dịch cần truy vấn chưa thực hiện thanh toán
         * 02 Không tìm thấy giao dịch cần truy vấn
         * hoặc giao dịch đã quá thời hạn thanh toán
         * 03 Giao dịch không thành công do đại lý không tồn tại
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
        log_tranx.info("Request from Esale: " + s);
        log.info("Request from Esale: " + s);

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
                CommonDef.STORAGE_PORT).generateTransID(time.substring(0, 6));

        log.info("tranID = " + tranxID + " for refID " + refID);

        BankingRequestObj requestObj = new BankingRequestObj(PROCESS_TRANSACTION_MESSAGE_CODE, PROCESS_TRANSACTION_ACTION_CODE,
                tranxID, time, username, agencyCode,
                region, amount, TRANSACTION_MONEY_CODE, clientIP, description, "");

        String beforeMAC = requestObj.toString();
        String mac = Encryption.MD5(beforeMAC);
        String afterMAC = beforeMAC + mac;
        String encodedInput = Encryption.encrypt(afterMAC, InitUtil.sharedkey, InitUtil.sharedvector);

        // Create tranaction IN DB
        T_Transaction tx = new T_Transaction(CommonDef.BANKCODE.HDBANK, Integer.valueOf(requestObj.getTransactionID()),
                StorageCommonDef.TRANX_TYPE.ESALE_EBANKING, requestObj.getTime(),
                requestObj.getUserName(), requestObj.getAgencyCode(), requestObj.getProviderCode(), refID,
                requestObj.getAmount(), requestObj.getClientIP(), requestObj.getDescription(), "", "", (short) 0);
        int ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).storeTranx(tx);
        if (ret == -1) {
            response.code = 51;
            log.info("DB Error for refID = " + refID);
            return response;
        }

        // Send to bank
        log_tranx.info("SEND TO HDBANK " + afterMAC);
        requestObj.setMAC(mac);
        ScriberBankingGateway.sendLogWithCurrentTime(requestObj.getScribeMessage());

        HDBankServiceHandler handler = HDBankServiceHandler.getInstance(InitUtil.HDBANK_URL);
        String responseStr = handler.callOnlinePaymentService(encodedInput);
        if (responseStr.equals(CommonDef.INVALID_CONNECTION)) {
            log.info("URL of HDBANK is wrong!");
            T_TranStat txStatus = new T_TranStat(Integer.valueOf(requestObj.getTransactionID()),
                    requestObj.getTime(), CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid BANKING URL");
            BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            response.code = 99;
            return response;
        }

        log.info("Response from HDBANK " + responseStr);

        // Check before decode
        if (responseStr == null || responseStr.trim().length() == 0) {
            response.code = 80;
            log.info("response is NULL ");
            return response;
        }

        responseStr = responseStr.trim();
        if (responseStr.length() == 2) {
            try {
                response.code = Integer.parseInt(responseStr);
                if (response.code < 0 || response.code > 99) {
                    response.code = 80;
                }
                return response;
            } catch (Exception ex) {
                log.error(ex.getMessage());
                response.code = 80;
                return response;
            }
        }

        //8. Decode response
        String decodedResponse = "";
        try {
            decodedResponse = Encryption.decrypt(responseStr, InitUtil.sharedkey, InitUtil.sharedvector);
            log.info(decodedResponse);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.info("Không thể giải mã chuỗi mã hóa 3DES");
            response.code = 90; // Không thể giải mã chuỗi mã hóa 3DES
            return response;
        }

        BankingResponseObj responseObj = new BankingResponseObj();
        if (!responseObj.fromString(decodedResponse)) {
            response.code = 92;
            log.info("Chuỗi yêu cầu sai định dạng");
            return response;
        }

        String beforMAC = decodedResponse.substring(0, (decodedResponse.lastIndexOf("|") + 1));
        String MAC = decodedResponse.substring((decodedResponse.lastIndexOf("|") + 1), decodedResponse.length());
        String MAC2 = Encryption.MD5(beforMAC);

        // verify MAC
        if (!MAC2.equals(MAC)) {
            log.info("Wrong MAC");
            response.code = 91;
            return response;
        }

        log.info(responseObj.toString());
        // validate message code
        if (!responseObj.getMessageCode().equals(RESPONSE_TRANSACTION_MESSAGE_CODE)) {
            log.info("wrong Message Code " + responseObj.getMessageCode());
            response.code = 92;
            return response;
        }

        // validate action code
        if (!responseObj.getActionCode().equals(PROCESS_TRANSACTION_ACTION_CODE)) {
            log.info("wrong Action Code " + responseObj.getActionCode());
            response.code = 92;
            return response;
        }

        // validate length of transactionID
        if (!(responseObj.getTransactionID() != null
                && responseObj.getTransactionID().trim().length() == 6)) {
            log.info("wrong TransactionID " + responseObj.getTransactionID());
            response.code = 92;
            return response;
        }

        // validate Provider Code
        if (!(responseObj.getProviderCode() != null
                && responseObj.getProviderCode().equals(requestObj.getProviderCode()))) {
            log.info("wrong ProviderCode " + responseObj.getProviderCode());
            response.code = 92;
            return response;
        }

        // validate money code
        if (!(responseObj.getMoneyCode() != null
                && responseObj.getMoneyCode().equals(TRANSACTION_MONEY_CODE))) {
            log.info("wrong MoneyCode " + responseObj.getMoneyCode());
            response.code = 92;
            return response;
        }

        boolean isSuccess = (responseObj.getResponseCode() != null
                && responseObj.getResponseCode().trim().equals("00"));
        log_tranx.info("Receive From HDBANK: " + responseObj.getScribeMessage());
        ScriberBankingGateway.sendLogWithCurrentTime(responseObj.getScribeMessage());
        // Update status for DB if error
        // Storage DB
        if (!isSuccess) {
            T_TranStat txStatus = new T_TranStat(Integer.valueOf(responseObj.getTransactionID()),
                    responseObj.getTime(), (short) -1, responseObj.getResponseCode(), "");
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxStatus(txStatus);
            if (ret == -1) {
                log.info("DB Error for update status with transacID = " + tranxID);
                response.code = 51;
                return response;
            }
        }

        T_Response res = new T_Response(Integer.parseInt(responseObj.getResponseCode()),
                responseObj.getRedirectUrl(), refID, String.valueOf(responseObj.getTransactionID()));

        log.info("Send redirect URL for ESALE " + res.redirectURL);
        res.refID = refID;
        res.transactionID = String.valueOf(tranxID);
        return res;
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
                    log.error(ex.getMessage());
                }
                    break;

                default:
                    log.info("not yet implement these transaction");
                    break;
            }
        }
    }

    private void reconfirmInternetBankingTransaction(T_TransactionReport tx_report) throws AxisFault {
        log.info("Recall webservice Esale to update CONFIRM STATUS (topup) ");

        String tx_Type = "";
        switch (tx_report.txType) {
            case INTERNET_TYPE:
                tx_Type = INTERNET_BANKING;
                break;

            case MOBILE_TYPE:
                tx_Type = MOBILE_BANKING;
                break;

            default:
                return;
        }
        String errorMessage = "";
        ConfirmOrderBankServiceStub.TopupResponse topupResponse = new ConfirmOrderBankServiceStub.TopupResponse();
        try {
            BigDecimal totalAmount = new BigDecimal(tx_report.amount);
            ConfirmOrderBankServiceStub.Topup topup = new ConfirmOrderBankServiceStub.Topup();
            topup.setAccountName(tx_report.userName);
            topup.setTotalAmount(totalAmount);
            topup.setNote(tx_report.getDescription());
            topup.setTrnsID(String.valueOf(tx_report.txID));
            topup.setBankCode(tx_report.bankCode);
            topup.setPaymentChannel(tx_Type);

            ConfirmOrderBankServiceStub stub = new ConfirmOrderBankServiceStub(CommonDef.ESALE_WEBSERVICE_URL);
            topupResponse = stub.topup(topup);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            errorMessage = ex.getMessage();
        }
        if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT) || errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)
                || (topupResponse != null && topupResponse.getTopupResult().getReturnCode() != 1
                && topupResponse.getTopupResult().getReturnCode() != -1)) {
            log.info("Ignore this transaction ");
        }

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
            log.error("Can not UPDATE STATUS for transactionID = " + tx.getTxID());
        }
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
        log.info("Process pending for day = " + day);

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
        log.info("Total pending = " + list.size());

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
//                            || "02".equals(txStatus.getResponseCode())
//                            || "03".equals(txStatus.getResponseCode())) {
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

    private void processPendingForInternetTransaction(T_Transaction tx, String responseCode) {
        ConfirmOrderBankServiceStub stub = null;
        try {
            stub = new ConfirmOrderBankServiceStub(CommonDef.ESALE_WEBSERVICE_URL);

        } catch (AxisFault ex) {
            java.util.logging.Logger.getLogger(BankingGatewayHandler.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
        }

        log.info("Call webservice Esale to update STATUS (topup)");

        String tx_Type = "";
        switch (tx.txType) {
            case 2:
                tx_Type = INTERNET_BANKING;
                break;
            case 3:
                tx_Type = MOBILE_BANKING;
                break;
            default:
                return;
        }

        ConfirmOrderBankServiceStub.TopupResponse topupResponse = new ConfirmOrderBankServiceStub.TopupResponse();
        String errorMessage = "";
        try {
            BigDecimal totalAmount = new BigDecimal(tx.amount);

            ConfirmOrderBankServiceStub.Topup topup = new ConfirmOrderBankServiceStub.Topup();
            topup.setAccountName(tx.userName);
            topup.setTotalAmount(totalAmount);
            topup.setNote(tx.getDescription());
            topup.setTrnsID(String.valueOf(tx.txID));
            topup.setBankCode(tx.bankCode);
            topup.setPaymentChannel(tx_Type);

            topupResponse = stub.topup(topup);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            errorMessage = ex.getMessage();
        }
        // Check Confirm Status
        short confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
        if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT;
        } else if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT;
        }

        short transactionStatus;
        if (confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT
                || confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT) {
            transactionStatus = CommonDef.TRANSACTION_STATUS.TXS_SUCCESS;
        } else {
            switch (topupResponse.getTopupResult().getReturnCode()) {
                case TOPUP_RESPONSE_SUCCESS:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                    transactionStatus = CommonDef.TRANSACTION_STATUS.TXS_SUCCESS;
                    break;
                case OBJ_NOT_EXIST_0:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                    transactionStatus = CommonDef.TRANSACTION_STATUS.TXS_FAIL;
                    break;
                case DUPLICATE_TRANSACTION:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                    transactionStatus = CommonDef.TRANSACTION_STATUS.TXS_SUCCESS;
                    break;
                default:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR;
                    transactionStatus = CommonDef.TRANSACTION_STATUS.TXS_FAIL;
                    break;
            }
        }
        tx.setConfirmStatus(confirmStatus);
        int ret = -1;
        try {
            ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxAndStatus(tx, transactionStatus, responseCode);
        } catch (TException ex) {
            log.error(ex.getMessage());
        }
        if (ret == -1) {
            log.error("Can not UPDATE STATUS for transactionID = " + tx.getTxID());
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

    private T_TranStat queryEsalseTransaction(T_Transaction tx)
            throws TException {
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

        T_TranStat txStatus = new T_TranStat();
        SimpleDateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        String time = df.format(new Date());
        // generate tranxID
        int queryID = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                CommonDef.STORAGE_PORT).generateQueryID(time.substring(0, 6));

        QueryRequestObj queryObj = new QueryRequestObj(QUERY_TRANSACTION_MESSAGE_CODE, QUERY_TRANSACTION_ACTION_CODE,
                queryID, time, tx.getUserName(), tx.getAgencyCode(),
                tx.getProviderCode(), tx.getAmount(), TRANSACTION_MONEY_CODE, tx.getClientIP(), tx.getTxID(), tx.getTime(), "");

        String beforeMAC = queryObj.toString();
        String mac = Encryption.MD5(beforeMAC);
        String afterMAC = beforeMAC + mac;
        String encodedInput = Encryption.encrypt(afterMAC, InitUtil.sharedkey, InitUtil.sharedvector);

        // Send to bank
        queryObj.setMAC(mac);
        log_tranx.info("SEND TO HDBANK For QUERY " + afterMAC);
        log_tranx.info(queryObj.toString());
        ScriberBankingGateway.sendLogWithCurrentTime(queryObj.getScribeMessage());

        HDBankServiceHandler handler = HDBankServiceHandler.getInstance(InitUtil.HDBANK_URL);
        String responseStr = handler.callOnlinePaymentService(encodedInput);
        if (CommonDef.INVALID_CONNECTION.equals(responseStr)) {
            log.info("URL of HDBANK is wrong!");
            txStatus = new T_TranStat(Integer.valueOf(queryObj.getTransactionID()),
                    queryObj.getTime(), CommonDef.TRANSACTION_STATUS.TXS_ERROR, "99", "Invalid BANKING URL");
            return txStatus;
        }

        log.info("Response from HDBANK For QUERY " + responseStr);

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

        //8. Decode response
        String decodedResponse = "";
        try {
            decodedResponse = Encryption.decrypt(responseStr, InitUtil.sharedkey, InitUtil.sharedvector);
            log.info(decodedResponse);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.info("Không thể giải mã chuỗi mã hóa 3DES");
            txStatus.setResponseCode("90"); // Không thể giải mã chuỗi mã hóa 3DES
            return txStatus;
        }

        QueryResponseObj responseObj = new QueryResponseObj();
        if (!responseObj.fromString(decodedResponse)) {
            txStatus.setResponseCode("92");
            log.info("Chuỗi yêu cầu sai định dạng");
            return txStatus;
        }

        String beforMAC = decodedResponse.substring(0, (decodedResponse.lastIndexOf("|") + 1));
        String MAC = decodedResponse.substring((decodedResponse.lastIndexOf("|") + 1), decodedResponse.length());
        String MAC2 = Encryption.MD5(beforMAC);

        // verify MAC
        if (!MAC2.equals(MAC)) {
            log.info("Wrong MAC");
            txStatus.setResponseCode("91");
            return txStatus;
        }

        log.info(responseObj.toString());
        // validate message code
        if (!responseObj.getMessageCode().equals(RESPONSE_QUERY_TRANSACTION_MESSAGE_CODE)) {
            log.info("wrong Message Code " + responseObj.getMessageCode());
            txStatus.setResponseCode("92");
            return txStatus;
        }

        // validate action code
        if (!responseObj.getActionCode().equals(QUERY_TRANSACTION_ACTION_CODE)) {
            log.info("wrong Action Code " + responseObj.getActionCode());
            txStatus.setResponseCode("92");
            return txStatus;
        }

        // validate length of transactionID
        if (String.valueOf(responseObj.getTransactionID()).length() < 6) {
            log.info("wrong TransactionID " + responseObj.getTransactionID());
            txStatus.setResponseCode("92");
            return txStatus;
        }

        // validate Provider Code
        if (!(responseObj.getProviderCode() != null
                && responseObj.getProviderCode().equals(tx.getProviderCode()))) {
            log.info("wrong ProviderCode " + responseObj.getProviderCode());
            txStatus.setResponseCode("92");
            return txStatus;
        }

        // validate money code
        if (!(responseObj.getMoneyCode() != null
                && responseObj.getMoneyCode().equals(TRANSACTION_MONEY_CODE))) {
            log.info("wrong MoneyCode " + responseObj.getMoneyCode());
            txStatus.setResponseCode("92");
            return txStatus;
        }

        boolean isSuccess = (responseObj.getResponseCode() != null
                && responseObj.getResponseCode().trim().equals("00"));
        log_tranx.info("Receive From HDBANK: " + responseObj.getScribeMessage());
        ScriberBankingGateway.sendLogWithCurrentTime(responseObj.getScribeMessage());
        short status = (isSuccess) ? CommonDef.TRANSACTION_STATUS.TXS_SUCCESS : CommonDef.TRANSACTION_STATUS.TXS_FAIL;
        txStatus = new T_TranStat(responseObj.getTransactionID(),
                responseObj.getTime(), status, responseObj.getResponseCode(), "");
        return txStatus;
    }
}
