/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.esaleservice.client.ConfirmOrderBankServiceHandler;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.CheckAgencyResponse;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.TopupResponse;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Transaction;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.util.InitUtil;
import vng.bankinggateway.worker.BankingGatewayWorker;

/**
 *
 * @author root
 */
public class BankingGatewayService {

    private static final Logger log_tranx = Logger.getLogger("serviceActions");
    private static final Logger log_system = Logger.getLogger("systemActions");
    private static final String BANK_CONFIRM_TRANSACTION_MESSAGE_CODE = "0600";
    private static final String CONFIRM_TRANSACTION_MESSAGE_CODE = "0610";
    private static final String CONFIRM_TRANSACTION_ACTION_CODE = "000002";
    private static final String CONFIRM_TRANSACTION_PROVIDER_CODE_PREFIX = "ZION";
    //CUSTOMER INFORM
    private static final String CUSTOMER_MESSAGE_CODE = "0500";
    private static final String CUSTOMER_CONFIRM_MESSAGE_CODE = "0510";
    private static final String CUSTOMER_ACTION_CODE = "100000";
    //TOPUP INFORM
    private static final String TOPUP_MESSAGE_CODE = "7000";
    private static final String TOPUP_CONFIRM_MESSAGE_CODE = "7010";
    private static final String TOPUP_ACTION_CODE = "200000";
    // TRANSACTION CHANNEL
    private static final String BANK_CODE = "HDBANK";
    private static final String TRANSACTION_EBANK = "EBANKING";
    private static final String TRANSACTION_MOBILE = "APPMOBILE";
    //MONEY CODE
    private static final String MONEY_CODE = "704";
    //RESPONSECODE
    private static final String SUCCESS_00 = "00";
    private static final String USERNAME_NOT_EXIST_03 = "03";
    // AGENCY_RESPONSE_CODE
    private static final int SUCCESS_CODE_1 = 1;
    private static final int OBJ_NOT_EXIST_0 = 0;
    private static final int DUPLICATE_TRANSACTION = -1;
    // CONNECTION PARAMETER
    private BankingGatewayWorker _bankingGatewayWorker = null;
    private final ConcurrentLinkedHashMap<String, Byte> _filter;
    private final ReentrantLock _locker = new ReentrantLock();

    public BankingGatewayService() {
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
                    CommonDef.STORAGE_PORT).getTranxsWithBankCode(day, (short) 0, true, CommonDef.BANKCODE.HDBANK);
            if (listTranxs != null) {
                for (int i = 0; i < listTranxs.size(); i++) {
                    _filter.put(listTranxs.get(i).getBankAccountCode() + "|" + day, Byte.MIN_VALUE);
                }
            }
        } catch (TException ex) {
            ex.printStackTrace();
        }

        log_system.info("Init BankingGatewayWorker success!");
    }

    public String reqExecute(String req) {
        log_system.info(req);
        String decrypInput = "";
        try {
            decrypInput = Encryption.decrypt(req, InitUtil.sharedkey,
                    InitUtil.sharedvector);
        } catch (Exception ex) {
            log_system.error(ex.getMessage());
            log_system.info("Không thể giải mã chuỗi mã hóa 3DES");
            return "90";
        }

        log_system.info(decrypInput);
        String testMessageCode = "";
        if (decrypInput.length() < 4 || decrypInput.indexOf("|") < 0) {
            log_system.info("Message code is wrong!");
            return "92";
        } else {
            testMessageCode = decrypInput.substring(0, decrypInput.indexOf("|"));
        }

        if (CUSTOMER_MESSAGE_CODE.equals(testMessageCode)) {
            return reqInfoOfCustomer(decrypInput);
        } else if (TOPUP_MESSAGE_CODE.equals(testMessageCode)) {
            return topupAgent(decrypInput);
        } else if (BANK_CONFIRM_TRANSACTION_MESSAGE_CODE.equals(testMessageCode)) {
            return confirmOrderForEsaleEbanking(decrypInput);
        }
        log_system.info("Message code is wrong!");
        return "92";
    }

    private String reqInfoOfCustomer(String decrypInput) {
        /*
         * 00 Giao dịch thành công
         * 01 Giao dịch cần truy vấn chưa thực hiện thanh toán
         * 02 Không tìm thấy giao dịch cần truy vấn hoặc giao dịch đã quá thời hạn thanh toán
         * 06 Giao dich bi loi
         * 10 Giao dịch bị lỗi do quá thời hạn thanh toán
         * 11 Mã giao dịch không hợp lệ
         * 12 Số tiền/mã tiền tệ thanh toán không hợp lệ
         * 13 MerchantID/ProviderID không hợp lệ
         * 20 Khách hàng thực hiện hủy giao dịch thanh toán tại ngân hàng.
         * 90 Không thể giải mã chuỗi mã hóa 3DES (chỉ trả về mã 90, không mã hóa)
         * 91 Sai mã MAC giao dịch. (chỉ trả về mã 91, không mã hóa)
         * 92 Chuỗi yêu cầu sai định dạng (chỉ trả về mã 92, không mã hóa)
         * 99 Hệ thống tạm ngừng phục vụ
         */

        CostumerInformObj retrieveInfo = new CostumerInformObj();
        if (!retrieveInfo.fromString(decrypInput)) {
            log_system.info("Chuỗi yêu cầu sai định dạng");
            return "92"; // 92 Chuỗi yêu cầu sai định dạng (chỉ trả về mã 92, không mã hóa)
        }

        String beforMAC = decrypInput.substring(0, (decrypInput.lastIndexOf("|") + 1));
        String MAC = decrypInput.substring((decrypInput.lastIndexOf("|") + 1),
                decrypInput.length());
        String MAC2 = Encryption.MD5(beforMAC);

        // verify MAC
        if (!MAC2.equals(MAC)) {
            log_system.info("Wrong MAC");
            return "91";
        }

        log_system.info("Correct MAC");
        log_system.info(retrieveInfo.toStringFromRequestWithMac());

        // validate topup  message code
        if (!CUSTOMER_MESSAGE_CODE.equals(retrieveInfo.getMessageCode())) {
            log_system.info("wrong topup Message code" + retrieveInfo.getMessageCode());
            return "92";
        }

        //validate action code
        if (!CUSTOMER_ACTION_CODE.equals(retrieveInfo.getActionCode())) {
            log_system.info("wrong topup action code" + retrieveInfo.getActionCode());
            return "92";
        }

        //validate transactionid code
        if (!((retrieveInfo.getTransactionId() != null) && (retrieveInfo.getTransactionId().trim().length() == 6))) {
            log_system.info(
                    "wrong trasnsactionid  code" + retrieveInfo.getTransactionId());
            return "92";
        }

        //validate TransactionChannel
        if (!((TRANSACTION_EBANK.equals(retrieveInfo.getTransactionChannel().trim()))
                || (TRANSACTION_MOBILE.equals(retrieveInfo.getTransactionChannel())))) {
            log_system.info(
                    "wrong transactionChannel " + retrieveInfo.getTransactionChannel());
            return "92";
        }

        //validate date
        boolean dateFormatCheck = checkFormatTime(retrieveInfo.getTime());
        if (dateFormatCheck == false) {
            log_system.info("wrong date format" + retrieveInfo.getTime());
            return "92";
        }

        //validate accountName
        if ((retrieveInfo.getAccountName() == null) || (retrieveInfo.getAccountName().isEmpty())
                || (retrieveInfo.getAccountName().trim().length() > 15)) {
            log_system.info("wrong AccountName" + retrieveInfo.getAccountName()); //check null?
            return "92";
        }

        // log scriber and log tranx
        log_tranx.info("Receive Request Customer Info from HDBank " + retrieveInfo.toStringFromRequestWithMac());
        //ScriberBankingGateway.sendLogMessage(retrieveInfo.toStringFromRequestWithMac());

        CheckAgencyResponse agencyResponse = null;
        try {
            ConfirmOrderBankServiceHandler confirmOrderBankServiceHandler
                    = new ConfirmOrderBankServiceHandler(CommonDef.ESALE_WEBSERVICE_URL);
            agencyResponse = confirmOrderBankServiceHandler.checkAgency(
                    retrieveInfo.getAccountName());
        } catch (Exception ex) {
            log_system.error(ex.getMessage());
            return "99";
        }

        if (agencyResponse.getCheckAgencyResult() == null) {
            log_system.error("agencyResponse.getCheckAgencyResult() IS null");
            return "99";
        }

        // Set Response Code
        int responseCode = agencyResponse.getCheckAgencyResult().getReturnCode();
        switch (responseCode) {
            case SUCCESS_CODE_1:
                retrieveInfo.setResponseCode(SUCCESS_00);
                break;
            case OBJ_NOT_EXIST_0:
                retrieveInfo.setResponseCode(USERNAME_NOT_EXIST_03);
                break;
            default: // other
                log_system.info("ResponseCode is wrong!!! ResponseCode = " + agencyResponse.getCheckAgencyResult().getReturnCode());
                return "99";
        }

        retrieveInfo.setMessageCode(CUSTOMER_CONFIRM_MESSAGE_CODE);
        retrieveInfo.setAgencyCode(agencyResponse.getCheckAgencyResult().getAgencyCode());
        retrieveInfo.setIdNumber(agencyResponse.getCheckAgencyResult().getIdCard());
        retrieveInfo.setFullName(agencyResponse.getCheckAgencyResult().getFullName());
        retrieveInfo.setAddress(agencyResponse.getCheckAgencyResult().getAddress());
        if (agencyResponse.getCheckAgencyResult().getManageUnitId() == 1) {
            retrieveInfo.setRegion(CommonDef.ZION_REGION.ZIONBAC);
        } else if (agencyResponse.getCheckAgencyResult().getManageUnitId() == 2) {
            retrieveInfo.setRegion(CommonDef.ZION_REGION.ZIONNAM);
        } else {
            log_system.info("ManageUnitId is wrong!!! ManageUnitId = " + agencyResponse.getCheckAgencyResult().getManageUnitId());
            return "99";
        }

        log_tranx.info(retrieveInfo.toStringFromReponseWithMac());
        //ScriberBankingGateway.sendLogMessage(retrieveInfo.toStringFromRequestWithMac());

        String beforeMACresponse = retrieveInfo.toStringFromReponse();
        String MACResponse = Encryption.MD5(beforeMACresponse);

        return Encryption.encrypt(beforeMACresponse + MACResponse, InitUtil.sharedkey,
                InitUtil.sharedvector);
    }

    private String topupAgent(String decrypInput) {
        TopupIdentifyCheckObj topupObj = new TopupIdentifyCheckObj();
        if (!topupObj.fromString(decrypInput)) {
            log_system.info("Chuỗi yêu cầu sai định dạng");
            return "92"; // 92 Chuỗi yêu cầu sai định dạng (chỉ trả về mã 92, không mã hóa)
        }

        String beforMAC = decrypInput.substring(0, (decrypInput.lastIndexOf("|") + 1));
        String MAC = decrypInput.substring((decrypInput.lastIndexOf("|") + 1), decrypInput.length());
        String MAC2 = Encryption.MD5(beforMAC);

        // verify MAC
        if (!MAC2.equals(MAC)) {
            log_system.info("Wrong MAC");
            return "91";
        }
        log_system.info("Correct MAC");
        log_system.info("Receive Request topup from HDBank " + topupObj.toStringFromRequestWithMac());
        // validate message code
        if (!TOPUP_MESSAGE_CODE.equals(topupObj.getMessageCode())) {
            log_system.info("wrong Message Code " + topupObj.getMessageCode());
            return "92";
        }

        // validate action code
        if (!TOPUP_ACTION_CODE.equals(topupObj.getActionCode())) {
            log_system.info("wrong action Code " + topupObj.getActionCode());
            return "92";
        }
        // validate transaction code
        if (!(topupObj.getTransactionCode() != null
                && topupObj.getTransactionCode().trim().length() == 6)) {
            log_system.info("wrong TransactionID " + topupObj.getTransactionCode());
            return "92";
        }

        //validate TransactionChannel channel
        if (!((TRANSACTION_EBANK.equals(topupObj.getTransactionChannel().trim()))
                || (TRANSACTION_MOBILE.equals(topupObj.getTransactionChannel())))) {
            log_system.info(
                    "wrong transactionChannel " + topupObj.getTransactionChannel());
            return "92";
        }

        //validate accountName
        if ((topupObj.getAccountName() == null) || (topupObj.getAccountName().trim().length() > 15)) {
            log_system.info("wrong AccountName" + topupObj.getAccountName());
            return "92";
        }

        //validate paymentAccount
        if ((topupObj.getPaymentAccount() == null) || (topupObj.getPaymentAccount().length() > 20)) {
            log_system.info("wrong PaymentAccount" + topupObj.getPaymentAccount());
            return "92";
        }

        //validate date
        boolean dateFormatCheck = checkFormatTime(topupObj.getTime());
        if (dateFormatCheck == false) {
            log_system.info("wrong date format" + topupObj.getTime());
            return "92";
        }

        //validate moneyTotal
        boolean checkMoney = moneyCheck(topupObj.getAmount());
        if (checkMoney == false) {
            log_system.info("wrong money format" + topupObj.getAmount());
            return "92";
        }

        if (topupObj.getMoneyCode() == null || !MONEY_CODE.equals(topupObj.getMoneyCode())) {
            log_system.info("wrong money code" + topupObj.getMoneyCode());
            return "92";
        }
        if (topupObj.getDescription().length() > 100) {
            log_system.info("wrong note" + topupObj.getDescription());
            return "92";
        }

        // check transaction
        if (!checkAndPutTransaction(topupObj.getTransactionCode() + "|" + formatTime(topupObj.getTime()))) {
            log_system.info("Transaction is Existed with TransactionId = " + topupObj.getTransactionCode()
                    + " time = " + topupObj.getTime());
            topupObj.setMessageCode(TOPUP_CONFIRM_MESSAGE_CODE);
            topupObj.setResponseCode(SUCCESS_00);
            log_tranx.info("Transaction is Existed " + topupObj.toStringForResponseWithMac());
            ScriberBankingGateway.sendLogWithCurrentTime("Transaction is Existed " + topupObj.toStringForResponseWithMac());

            String beforeMACresponse = topupObj.toStringForResponse();
            String MACResponse = Encryption.MD5(beforeMACresponse);

            return Encryption.encrypt(beforeMACresponse + MACResponse, InitUtil.sharedkey,
                    InitUtil.sharedvector);
        }

        // confirm information
        log_tranx.info("Receive Request topup from HDBank " + topupObj.toStringFromRequestWithMac());
        ScriberBankingGateway.sendLogWithCurrentTime(topupObj.toStringFromRequestWithMac());

        int tranxID = 0;
        try {
            // generate tranxID
            tranxID = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).generateTransID(topupObj.getTime().substring(0, 6));
        } catch (TException ex) {
            log_system.error(ex.getMessage());
        }
        // Create tranaction IN DB
        String refID = "";
        String clientIP = "";
        String providerCode = "ZIONSRV";
        T_Transaction tx = new T_Transaction(BANK_CODE, tranxID, StorageCommonDef.TRANX_TYPE.INTERNET_BANKING,
                topupObj.getTime(), topupObj.getAccountName(),
                topupObj.getAgencyCode(), providerCode, refID, Integer.parseInt(
                topupObj.getAmount()), clientIP, topupObj.getDescription(),
                topupObj.getTransactionCode(), topupObj.getPaymentAccount(), (short) 0);
        try {
            int ret = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).storeTranx(tx);
            if (ret == -1) {
                log_system.info("DB Error for tranxID = " + tranxID);
                return "51";
            }
        } catch (TException ex) {
            log_system.error(ex.getMessage());
        }

        ConfirmOrderBankServiceHandler confirmOrderBankServiceHandler
                = new ConfirmOrderBankServiceHandler(CommonDef.ESALE_WEBSERVICE_URL);

        TopupResponse topupResponse = new TopupResponse();
        String errorMessage = "";
        short transactionStatus;
        String descriptionTxStatus;

        try {
            BigDecimal moneyTotal = new BigDecimal(topupObj.getAmount());
            topupResponse
                    = confirmOrderBankServiceHandler.topup(topupObj.getAccountName(),
                    moneyTotal, topupObj.getDescription(), String.valueOf(tranxID),
                    BANK_CODE, topupObj.getTransactionChannel());

        } catch (Exception ex) {
            log_system.error(ex.getMessage());
            errorMessage = ex.getMessage();
        }

        // Check Confirm Status
        short confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
        if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.INVALID_TRASPORT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT;
            descriptionTxStatus = "FAILED_TRANSANPORT";
        } else if (errorMessage.contains(CommonDef.CONNECTION_ERROR_MESSAGE.CONNECTION_TIMEOUT)) {
            confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT;
            descriptionTxStatus = "CONNECTION_TIMEOUT";
        }

        if (confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT
                || confirmStatus == CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT) {
            transactionStatus = (short) 100;
            descriptionTxStatus = "SUCCESS";
            // topup response code
            topupObj.setResponseCode(SUCCESS_00);
        } else {
            // Store DB
            refID = (topupResponse.getTopupResult().getOrderNo() != null) ? topupResponse.getTopupResult().getOrderNo() : "";
            switch (topupResponse.getTopupResult().getReturnCode()) {
                case SUCCESS_CODE_1:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                    transactionStatus = (short) 100;
                    descriptionTxStatus = "SUCCESS";
                    // topup response code
                    topupObj.setResponseCode(SUCCESS_00);
                    break;
                case OBJ_NOT_EXIST_0:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;
                    transactionStatus = (short) -1;
                    descriptionTxStatus = "NOT_EXIST";
                    // topup response code
                    topupObj.setResponseCode(USERNAME_NOT_EXIST_03);
                    break;
                case DUPLICATE_TRANSACTION:
                    confirmStatus = CommonDef.CONFIRM_TRANSACTION_STATUS.SUCCESS;

                    transactionStatus = (short) 100;
                    descriptionTxStatus = "DUPLICATE_TRANSACTION";
                    // topup response code
                    topupObj.setResponseCode(SUCCESS_00);
                    break;
                default:
                    confirmStatus
                            = CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR;
                    descriptionTxStatus
                            = "Response code from Esale " + topupResponse.getTopupResult().getReturnCode();
                    transactionStatus = (short) -1;
                    // topup response code
                    topupObj.setResponseCode(SUCCESS_00);
                    break;
            }
        }

        // updateTranSactionAndStatus: refID, transactionStatus, ConfirmStatus
        tx.setRefID(refID);
        tx.setConfirmStatus(confirmStatus);

        try {
            BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).updateTranxAndStatus(tx, transactionStatus, topupObj.getResponseCode());
        } catch (TException ex) {
            log_system.error(ex.getMessage());
            // check luc doi soat
        }

        topupObj.setMessageCode(TOPUP_CONFIRM_MESSAGE_CODE);

        log_tranx.info(topupObj.toStringForResponseWithMac());
        ScriberBankingGateway.sendLogWithCurrentTime(topupObj.toStringForResponseWithMac());

        String beforeMACresponse = topupObj.toStringForResponse();
        String MACResponse = Encryption.MD5(beforeMACresponse);

        return Encryption.encrypt(beforeMACresponse + MACResponse, InitUtil.sharedkey,
                InitUtil.sharedvector);
    }

    private String confirmOrderForEsaleEbanking(String decrypInput) {
        ZionDataObj zionDataObj = new ZionDataObj();
        if (!zionDataObj.fromString(decrypInput)) {
            log_system.info("Chuỗi yêu cầu sai định dạng");
            return "92"; // 92 Chuỗi yêu cầu sai định dạng (chỉ trả về mã 92, không mã hóa)
        }
        String beforMAC = decrypInput.substring(0, (decrypInput.lastIndexOf("|") + 1));
        String MAC = decrypInput.substring((decrypInput.lastIndexOf("|") + 1),
                decrypInput.length());
        String MAC2 = Encryption.MD5(beforMAC);

        // verify MAC
        if (!MAC2.equals(MAC)) {
            log_system.info("Wrong MAC");
            return "91";
        }
        log_system.info("Correct MAC");
        log_system.info(zionDataObj.toString());
        // validate message code
        if (!zionDataObj.getMessageCode().equals(BANK_CONFIRM_TRANSACTION_MESSAGE_CODE)) {
            log_system.info("wrong Message Code " + zionDataObj.getMessageCode());
            return "92";
        }

        // validate action code
        if (!zionDataObj.getActionCode().equals(CONFIRM_TRANSACTION_ACTION_CODE)) {
            log_system.info("wrong Action Code " + zionDataObj.getActionCode());
            return "92";
        }

        // validate length of transactionID
        if (!(zionDataObj.getTransactionID() != null
                && zionDataObj.getTransactionID().trim().length() == 6)) {
            log_system.info("wrong TransactionID " + zionDataObj.getTransactionID());
            return "92";
        }

        // validate Provider Code
        if (!(zionDataObj.getProviderCode() != null
                && zionDataObj.getProviderCode().indexOf(
                CONFIRM_TRANSACTION_PROVIDER_CODE_PREFIX) >= 0)) {
            log_system.info("wrong ProviderCode " + zionDataObj.getProviderCode());
            return "92";
        }

        // validate money code
        if (!(zionDataObj.getMoneyCode() != null
                && zionDataObj.getMoneyCode().equals(MONEY_CODE))) {
            log_system.info("wrong MoneyCode " + zionDataObj.getMoneyCode());
            return "92";
        }

        // log scriber and log tranx
        log_tranx.info("Receive CONFIRM ORDER From BANK " + zionDataObj.toString());
        ScriberBankingGateway.sendLogWithCurrentTime(zionDataObj.getScribeMessage());

        _bankingGatewayWorker.pushJob(zionDataObj.toQueueMessage());

        zionDataObj.setMessageCode(CONFIRM_TRANSACTION_MESSAGE_CODE);
        zionDataObj.setActionCode(CONFIRM_TRANSACTION_ACTION_CODE);
        zionDataObj.setRedirectURL(makeRedirectURL(zionDataObj));
        zionDataObj.setResponseCode("00");

        log_system.info(zionDataObj.toString());

        String beforeMACresponse = zionDataObj.toString();
        String MACResponse = Encryption.MD5(beforeMACresponse);
        return Encryption.encrypt(beforeMACresponse + MACResponse, InitUtil.sharedkey,
                InitUtil.sharedvector);
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

    private String makeRedirectURL(ZionDataObj zionDataObj) {
        try {
            T_Transaction tx = BankingStorageClient.getMainInstance(
                    CommonDef.STORAGE_HOST,
                    CommonDef.STORAGE_PORT).getTransaction(Integer.valueOf(
                    zionDataObj.getTransactionID()), zionDataObj.getTime());
            String checkSum
                    = CommonDef.SHA1_SHARED_KEY + zionDataObj.getResponseCode() + tx.getRefID() + zionDataObj.getAmount();
            checkSum = Encryption.SHA1(checkSum);
            StringBuilder rediectUrl = new StringBuilder(CommonDef.ESALE_REDIRECT_URL);
            rediectUrl.append("zb_SecureHash=");
            rediectUrl.append(checkSum);
            rediectUrl.append("&zb_MerchTxnRef=");
            rediectUrl.append(tx.getRefID());
            rediectUrl.append("&zb_Amount=");
            rediectUrl.append(zionDataObj.getAmount());
            rediectUrl.append("&zb_ResponseCode=");
            rediectUrl.append(zionDataObj.getResponseCode());
            log_system.info("redirect_url = " + rediectUrl.toString());
            return rediectUrl.toString();
        } catch (TException ex) {
            log_system.error(ex.getMessage());
        }
        return CommonDef.ESALE_REDIRECT_URL;
    }

    private boolean checkAndPutTransaction(String refID) {
        boolean res = false;
        try {
            _locker.lock();
            if (!_filter.containsKey(refID)) {
                _filter.put(refID, Byte.MIN_VALUE);
                res = true;
            }
        } finally {
            _locker.unlock();
        }
        return res;
    }

    private String formatTime(String inputTime) {
        try {
            DateFormat df = new SimpleDateFormat("MMddyyHHmmss");
            Date date = df.parse(inputTime);
            DateFormat dfTransxTime = new SimpleDateFormat("MMddyy");
            return dfTransxTime.format(date);
        } catch (ParseException ex) {
        }
        return "";
    }
}
