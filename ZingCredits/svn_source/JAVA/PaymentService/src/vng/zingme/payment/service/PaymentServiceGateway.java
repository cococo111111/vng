package vng.zingme.payment.service;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import com.vng.zing.stdprofile2.thrift.StdProfile2Service_Rd;
import com.vng.zing.stdprofile2.thrift.TValueResult;
import com.vng.zing.wtcommon.thriftutil.TClientPool;
import com.vng.zing.wtcommon.thriftutil.TClientPoolConfig;
import com.vng.zing.wtcommon.thriftutil.TClientPoolMan;
import com.vng.zing.zcommon.thrift.OpHandle;
import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.bo.thrift.TokenHandler;
import vng.zingme.payment.common.*;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.common.zk.ZKUtil;
import vng.zingme.payment.model.Money;
import vng.zingme.payment.model.MoneyResponse;
import vng.zingme.payment.model.User;
import vng.zingme.payment.model.UserResponse;
import vng.zingme.payment.thrift.*;
import vng.zingme.util.LogUtil;
import vng.zingme.util.StringUtil;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class PaymentServiceGateway {

    public PaymentServiceGateway(String rc) {

        try {
            System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
            System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        } catch (Exception e) {
            // e.printStackTrace();
            log.warn(LogUtil.stackTrace(e));
            // System.exit(1);
        }

        balHandler = BalanceCacheHandler.getMainInstance();
        tkHandler = TokenHandler.getMainInstance();

        lastCache = LatestTranxCacheHandler.getMainInstance();

        gen_catalog = System.getProperty("zgen-catalog", "zingcredits");

//        _genmaster = getGenClient();
//        try {
//            _genmaster.createGenerator(gen_catalog);
//        } catch (Exception ex) {
//            log.warn(LogUtil.stackTrace(ex));
//        }

        aiCache = AppInfoCache.getInstance();

        // gcMonitor = GCMonitor.getInstance();

        billingExpireTime = Integer.parseInt(System.getProperty("billingExpireTime", "300"));

        initalizeStdProfileClientPool();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                log.warn("Fatal exception in thread " + t.toString());
                log.warn(LogUtil.stackTrace(e));
                if (e instanceof OutOfMemoryError) {
                    System.exit(100);
                }
            }
        });
    }

    public T_AccResponse getBalance(int userID) throws TException {
        T_AccResponse res = new T_AccResponse();

        String userName = getZingmeName(userID);
        if (userName == null || userName.equals("")) {
            res.code = PaymentCode.G_ERROR_ACC_NOT_EXIST;
            return res;
        }

        ++totalGetbalanceOperator;
        long tmpget = System.nanoTime();
        res = balHandler.getBalance(userID);
        totalGetbalanceMicroSec += ((System.nanoTime() - tmpget) / 1000);
        return res;
    }

    public void warmupCache(int userID) throws TException {
        String userName = getZingmeName(userID);
        if (userName == null || userName.equals("")) {
            return;
        }
        balHandler.warmupCache(userID);
    }

    public T_Response bill(T_Transaction tx, T_Token tk, String sercuCode) throws TException {
        ++totalBillingOperator;
        long tmpbill = System.nanoTime();

        T_Response res = new T_Response();

        res.code = tkHandler.checkToken(tk);
        if (res.code != TTokenResCode.ZC_TK_RESCODE_SUCCESS.getValue()) {
            if (tk != null) {
                log.info("Token validate fail with code " + String.valueOf(res.code) + "; token: " + tk.toString());
            } else {
                log.info("Token validate fail with code " + String.valueOf(res.code) + "; token is null!");
            }
            lastbilling = ((System.nanoTime() - tmpbill) / 1000);
            totalBillingMicroSec += lastbilling;

            res.code = PaymentReturnCode.BillingCode.B_ERROR_TOKEN_INVALID;
            return res;
        }

        //check money is a passive number
        if (tx.amount <= 0) {
            res.code = PaymentReturnCode.BillingCode.B_ERROR_MONEY_ILLEGAL;
            return res;
        }

        tx.userName = getZingmeName(tx.userID);
        if (tx.userName == null || tx.userName.length() <= 0) {
            res.code = PaymentCode.G_ERROR_ACC_NOT_EXIST;
            return res;
        }


        //make - transactionID
        tx.txID = getTranxID();
        if (tx.txID < 0) {
            log.warn("(billing) zgen return: " + tx.txID);
            res.code = PaymentReturnCode.BillingCode.B_ERROR_FAIL;
            return res;
        }

        //create - transactionTime
        tx.txLocalTime = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);

        //fix txType
        tx.txType = CommonDef.TRANX_TYPE.TT_DEDUCT_MONEY;

        //deduct
        T_AccResponse accRes = balHandler.deduct(tx);

        if (accRes == null) {
            log.info("accRes is null!. Deduct fail with code " + String.valueOf(res.code));
            res.code = PaymentReturnCode.BillingCode.B_ERROR_FAIL;
            lastbilling = ((System.nanoTime() - tmpbill) / 1000);
            totalBillingMicroSec += lastbilling;
            return res;
        }

        res = new T_Response(accRes.code, String.valueOf(tx.txID), tx.mac, accRes.currentBalance);

        lastbilling = ((System.nanoTime() - tmpbill) / 1000);
        totalBillingMicroSec += lastbilling;

        if (res.code == 0) {
            tx.responseCode = CommonDef.TRANX_STAT.TS_INPROCESS;

            int hope_count = 2, pcode = PaymentReturnCode.ERROR_OPERATOR_FAIL;
            while (hope_count > 0 && pcode != PaymentReturnCode.SUCCESS) {
                try {
                    pcode = lastCache.put(tx);
                } catch (TException ex) {
                    log.warn(LogUtil.stackTrace(ex));
                }
                --hope_count;
            }

            String s_data_log = ScriberUtil.logme(tx, CommonDef.TRANX_RES_CODE.TC_INPROCESS);

            try {
                ScriberUtil.getScribe().log(s_data_log);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

            try {
                datalog.info(s_data_log);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

        } else {
            requestfaillog.info("Deducted for " + tx.toString() + "; result " + res.toString());
        }

        return res;
    }

    public T_Response pushMoney(T_Transaction tx) throws TException {
        T_Response response = new T_Response();
        ++totalOperator;
        long tmp = System.nanoTime();
        ZKFrontEndService frontInstance = ZKFrontEndService.getInstance();
        if (frontInstance != null) {
            response.code = frontInstance.storepushTransaction(tx, "");
        }
        lasttransfer = ((System.nanoTime() - tmp) / 1000);
        totalMicroSec += lasttransfer;
        return response;
    }
    private static PaymentServiceGateway _instance = null;

    public static PaymentServiceGateway getMainInstance() {
        if (_instance == null) {
            synchronized (lockObj) {
                if (_instance == null) {
                    _instance = new PaymentServiceGateway();
                }
            }
        }
        return _instance;
    }

    private PaymentServiceGateway() {

        String confpath = System.getProperty("config", "/zserver/japps/zingcredits/PaymentGateway/conf");

        PropertyConfigurator.configure(confpath + "/log4j.properties");

        String filename = confpath + "/paymentgateway.config";
        ConfigUtil.loadConfigFile(filename, PaymentServiceGateway.class.getName());

        createZKWorkingPath();
        
        log.info("CONFIG PATH=" + confpath);

        balHandler = BalanceCacheHandler.getMainInstance();
        lastCache = LatestTranxCacheHandler.getMainInstance();

        gen_catalog = System.getProperty("zgen-catalog", "zingcredits");

//        _genmaster = getGenClient();
//        try {
//            _genmaster.createGenerator(gen_catalog);
//        } catch (Exception ex) {
//            log.warn(LogUtil.stackTrace(ex));
//        }

        aiCache = AppInfoCache.getInstance();

        // gcMonitor = GCMonitor.getInstance();

        _memClient = getMemClient();
        initalizeStdProfileClientPool();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                log.warn("Fatal exception in thread " + t.toString());
                log.warn(LogUtil.stackTrace(e));
                if (e instanceof OutOfMemoryError) {
                    System.exit(100);
                }
            }
        });
    }

    public long getTotalOperator() {
        return totalOperator;
    }

    public double getTransferRate() {
        return ((totalOperator * (double) 1000000.0) / totalMicroSec);
    }
    private volatile long totalOperator = 0;
    private volatile long totalMicroSec = 0;
    // private volatile long tmp = 0;

    public MoneyResponse addMoney(Money addMoney, String clientIP) {
        MoneyResponse res = new MoneyResponse();

        res.setTransactionId(addMoney.getTransactionId());
        res.setBonusMoney("0");
        res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_DB));
        res.setMoney(addMoney.getMoney());
        res.setUserId(addMoney.getUserId());

        if (checkExist(CommonDef.ZING_PAY_ID + res.getTransactionId())) {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_DUPLICATE_TRANSACTION));
            log_info.info("(addMoney) duplicate, Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() + "," + addMoney.getUserId());
            return res;
        }

        //check exist user in zingme
        int userID = getZingmeID(addMoney.getUserId());
        if (userID <= 0) {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_ACC_NOT_EXIST));
            log_info.info("(addMoney) Account not exist, Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() + "," + addMoney.getUserId());
            return res;
        }

        //check money is a passive number or not number
        try {
            double amount = Double.parseDouble(addMoney.getMoney());
            if (amount <= 0) {
                res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_MONEY_NEGATIVE));
                log_info.info("(addMoney) Money invalid (<=0), Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() + "," + addMoney.getUserId());
                return res;
            }
        } catch (Exception ex) {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_MONEY_ILLEGAL));
            log_info.info("(addMoney) Money invalid (Exception), Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() + "," + addMoney.getUserId());
            return res;
        }

        T_Transaction tranx = conversion(addMoney);
        tranx.userID = userID;
        tranx.clientIP = clientIP;

        //check valid-sender & transaction
        if (!checkPL(CommonDef.ZING_PAY_ID, addMoney.getSig(), addMoney.getUserId(), addMoney.getMoney(), addMoney.getTransactionId(), 0)) {
            log_info.info("PayLetter-signature not valid, transaction " + tranx.toString());
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_SIG_INVALID));
            return res;
        }

        //make - transactionID
        tranx.txID = getTranxID();
        if (tranx.txID < 0) {
            log.warn("(push) zgen return: " + tranx.txID);
            log_info.warn("(push) zgen return: " + tranx.txID);
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_DB));
            return res;
        }

        //create - transactionTime
        tranx.txLocalTime = tranx.txTime = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);

        short txType = Short.valueOf(addMoney.getTransferType());
        //fix txType
        if (txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY
                || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ATM
                || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_CARD
                || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_SMS
                || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_IBANKING
                || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ESALE) {
            tranx.txType = txType;
        } else {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_PARAMETER_INVALID));
            log_info.info("(addMoney) Transfer Type is invalid), Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() 
                    + "," + addMoney.getUserId() + "," + addMoney.getTransferType());
            return res;
        }
        
        T_Response zkRes = null;
        try {
            zkRes = pushMoney(tranx);
        } catch (TException ex) {
            log.warn("zkRes = pushMoney(tranx) fail " + ex.getMessage());
        }

        if (zkRes != null && zkRes.code == PaymentReturnCode.SUCCESS) {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_SUCCESS));

            tranx.responseCode = CommonDef.TRANX_STAT.TS_INPROCESS;

            int hope_count = 2, pcode = PaymentReturnCode.ERROR_OPERATOR_FAIL;
            while (hope_count > 0 && pcode != PaymentReturnCode.SUCCESS) {
                try {
                    pcode = lastCache.put(tranx);
                } catch (TException ex) {
                    log.warn(LogUtil.stackTrace(ex));
                }
                --hope_count;
            }

            String s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_INPROCESS);

            try {
                ScriberUtil.getScribe().log(s_data_log);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

            try {
                datalog.info(s_data_log);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

            addMemCache(CommonDef.ZING_PAY_ID + tranx.refID, zkRes.code);

        } else {
            res.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_DB));
            log_info.info("(addMoney) fail in push, Transaction: " + addMoney.getTransactionId() + "," + addMoney.getMoney() + "," + addMoney.getUserId() + "; resultCode " + zkRes.code);
        }

        return res;
    }

    private T_Transaction conversion(Money xtranx) {
        T_Transaction tranx = new T_Transaction();

        tranx.userName = xtranx.getUserId();
        tranx.agentID = CommonDef.ZING_PAY_ID;
        tranx.refID = xtranx.getTransactionId();
        tranx.amount = Double.parseDouble(xtranx.getMoney());
        tranx.mac = xtranx.getSig();

        return tranx;
    }
    private static final Object lockObj = new Object();

    private boolean checkPLGetSum(String agentID, String pSig, String from, String to) {
        String key = getKey(agentID, 2);
        if (key == null) {
            log.warn("Payletter not support key! (key is null)");
            return false;
        }
        MessageDigest digest = null;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            log.warn("java.security.MessageDigest.getInstance('MD5') fail, code " + ex.getMessage());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }

        if (digest == null) {
            log.info("digest Object is null!");

            return false;
        }
        String mac = from + to + key;
        digest.update(mac.getBytes());
        String _sig = StringUtil.getHexString(digest.digest());

        if (_sig == null ? pSig == null : _sig.compareToIgnoreCase(pSig) == 0) {
            return true;
        }
        return false;

    }

    private boolean checkPL(String agentID, String pSig, String userName, String money, String transactionID, int pushertime) {
        String key = getKey(agentID, 2);
        if (key == null) {
            log.warn("Payletter not support key! (key is null)");
            return false;
        }

        MessageDigest digest = null;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            log.warn("java.security.MessageDigest.getInstance('MD5') fail, code " + ex.getMessage());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }

        if (digest == null) {
            log.info("digest Object is null!");

            return false;
        }

        if (transactionID == null) {
            return checkGetMoneyPL(digest, pSig, userName, key);
        }
        return checkAddMoneyPL(digest, pSig, userName, money, transactionID, key, pushertime);
    }

    private boolean checkAddMoneyPL(MessageDigest digest, String pSig, String userName, String money, String transactionID, String key, int pushertime) {
        String mac = money + userName + transactionID;
        if (pushertime > 0) {
            mac += pushertime;
        }
        mac += key;
        digest.update(mac.getBytes());
        String _sig = StringUtil.getHexString(digest.digest());
        if (_sig == null ? pSig == null : _sig.compareToIgnoreCase(pSig) == 0) {
            return true;
        }
        return false;
    }

    private boolean checkGetMoneyPL(MessageDigest digest, String pSig, String userName, String key) {

        String mac = userName + key;
        digest.update(mac.getBytes());
        String _sig = StringUtil.getHexString(digest.digest());

        if (_sig == null ? pSig == null : _sig.compareToIgnoreCase(pSig) == 0) {
            return true;
        }
        return false;
    }

    public UserResponse getUser(User getUser) {
        UserResponse result = new UserResponse();
        result.setUserId(getUser.getUserId());
        if (!checkPL(CommonDef.ZING_PAY_ID, getUser.getSig(), getUser.getUserId(), null, null, 0)) {
            log.info("User PayLetter not valid.");
            result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_SIG_INVALID));
            return result;
        }

        int userID = getZingmeID(getUser.getUserId());
        if (userID <= 0) {
            result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_ACC_NOT_EXIST));
        } else {
            result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_SUCCESS));
        }
        return result;
    }

    private String GetSum_GetDate(String date, String ext) {
        if (date.length() != 8) {
            return null;
        }
        String result = "";
        result = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + ext;
        return result;
    }

    public MoneyResponse getMoney(User user) {
        // log.info("getMoney web-method has called for user " + getMoney.getUserId());
        MoneyResponse result = new MoneyResponse();
        result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_PARAMETER_INVALID));
        result.setMoney("0");

        //check exist user in zingme
        int userID = getZingmeID(user.getUserId());
        if (userID <= 0) {
            result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_ACC_NOT_EXIST));
            return result;
        }

        //check valid-sender & transaction
        if (!checkPL(CommonDef.ZING_PAY_ID, user.getSig(), user.getUserId(), null, null, 0)) {
            log.info("PayLetter not valid.");
            result.setCode(String.valueOf(PaymentReturnCode.PayletterReturnCode.P_ERROR_SIG_INVALID));
            return result;
        }

        ++totalGetMoneyOperator;
        long tmpgetm = System.nanoTime();

        T_AccResponse res = null;
        try {
            res = balHandler.getBalance(userID);
        } catch (TException ex) {
            log.warn(ex.getMessage());
        }
        if (res == null) {
            lastgetmoney = ((System.nanoTime() - tmpgetm) / 1000);
            totalGetMoneyMicroSec += lastgetmoney;
            return result;
        }
        result.setCode(String.valueOf(res.code));
        result.setMoney(String.valueOf(res.currentBalance));
        lastgetmoney = ((System.nanoTime() - tmpgetm) / 1000);
        totalGetMoneyMicroSec += lastgetmoney;
        return result;
    }

    public double getBillingRate() {
        return ((totalBillingOperator * (double) 1000000.0) / totalBillingMicroSec);
    }

    public long getTotalBillOperator() {
        return totalBillingOperator;
    }
    private volatile long totalBillingOperator = 0;
    private volatile long totalBillingMicroSec = 0;
    // private volatile long tmpbill = 0;

    public double getGetBalanceRate() {
        return ((totalGetbalanceOperator * (double) 1000000.0) / totalGetbalanceMicroSec);
    }

    public long getTotalRequestGetBalance() {
        return totalGetbalanceOperator;
    }
    private volatile long totalGetbalanceOperator = 0;
    private volatile long totalGetbalanceMicroSec = 0;

    public long getGetMoney() {
        return totalGetMoneyOperator;
    }

    public double getGetMoneyRate() {
        return ((totalGetMoneyOperator * (double) 1000000.0) / totalGetMoneyMicroSec);
    }
    private volatile long totalGetMoneyOperator = 0;
    private volatile long totalGetMoneyMicroSec = 0;

    public double getLastBillingTime() {
        return lastbilling / (double) 1000000.0;
    }

    public double getLastGetMoneyTime() {
        return lastgetmoney / (double) 1000000.0;
    }

    public double getLastTransferTime() {
        return lasttransfer / (double) 1000000.0;
    }
    private volatile long lasttransfer = 0;
    private volatile long lastbilling = 0;
    private volatile long lastgetmoney = 0;
    private static final Logger log = Logger.getLogger("appActions");
    private static BalanceCacheHandler balHandler = null;
    private static TokenHandler tkHandler = null;
    private static LatestTranxCacheHandler lastCache = null;

    private int getZingmeID(String name) {
        return getZingId(name);
    }

    public T_Response processBill(String agentID, String strEncoded, T_Token tk, String clientIP) throws TException {
        if (agentID == null) {
            return new T_Response(PaymentCode.G_ERROR_SIG_INVALID, "", "", -1.0);
        }
        T_AppInfo ai = getAppInfo(agentID);
        if (ai == null || ai.appID == null || ai.appID == "" || !ai.appID.equals(agentID)) {
            return new T_Response(PaymentCode.G_ERROR_SIG_INVALID, "", "", -1.0);
        }
        T_Transaction tranx = null;
        if (ai.isEnabled != 1) {
            tranx = parseTransaction(agentID, strEncoded);
            // allow only users in while-list
            if (ai.lswhitelist == null || !ai.lswhitelist.contains(tranx.userID)) {
                return new T_Response(PaymentReturnCode.BillingCode.B_ERROR_GAME_APP_NOT_ALLOW, "", "", -1.0);
            }
        }

        if (tranx == null) {
            tranx = parseTransaction(agentID, strEncoded);
        }
        if (agentID == null ? tranx.agentID != null : !agentID.equals(tranx.agentID)) {
            return new T_Response(PaymentCode.G_ERROR_SIG_INVALID, "", "", -1.0);
        }

        tranx.clientIP = clientIP;

        return bill(tranx, tk, "");
    }

    private T_AppInfo getAppInfo(String agentID) {
        return aiCache.get(agentID);
    }

    private String getKey(String agentID, int keyIdx) {
        T_AppInfo ai = getAppInfo(agentID);

        if (keyIdx == 1) {
            return ai.key1;
        }
        return ai.key2;
    }
//    private static Generator.Client _genmaster = null;

    private long getTranxID() {
        return GeneratorServiceClient.getInstance(System.getProperty("zgenHost", "localhost"), 
                Integer.parseInt(System.getProperty("zgenPort", "9090"))).getValue(gen_catalog);
//        long res = -1;
//        int hope_count = 2;
//        while (hope_count > 0 && res < 0) {
//            try {
//                res = _genmaster.getValue(gen_catalog);
//            } catch (Exception ex) {
//                log.warn(LogUtil.stackTrace(ex));
//                _genmaster = getGenClient();
//            }
//            --hope_count;
//        }
//        return res;
    }

    public T_Transaction parseTransaction(String agentID, String strEncoded) {
        T_Transaction tranx = new T_Transaction();

        std__vectorT_std__string_t params2 = zma_decode(agentID, strEncoded, 0);

        if (params2 == null) {
            return tranx;
        }

        try {
            tranx.agentID = agentID;
            // tranx.userName = params2.get(0);
            tranx.userID = Integer.parseInt(params2.get(0));
            tranx.refID = params2.get(1);
            tranx.itemIDs = params2.get(2);
            tranx.itemNames = params2.get(3);
            tranx.itemQuantities = params2.get(4);
            tranx.itemPrices = params2.get(5);
            tranx.amount = Double.parseDouble(params2.get(6));
            tranx.txTime = Integer.parseInt(params2.get(7));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            return new T_Transaction();
        }

        return tranx;
    }

//    private Generator.Client getGenClient() {
//        Generator.Client client = null;
//        try {
//            TTransport socket = new TSocket(System.getProperty("zgenHost", "localhost"), Integer.parseInt(System.getProperty("zgenPort", "9090")));
//            TProtocol protocol = new TBinaryProtocol(socket);
//            socket.open();
//            client = new Generator.Client(protocol);
//        } catch (Exception ex) {
//            log.warn(LogUtil.stackTrace(ex));
//        }
//        return client;
//    }

    public List<String> zingUnSignature(String agentID, String strEncoded, final int kindofkey) {
        List<String> res = new LinkedList<String>();

        std__vectorT_std__string_t params2 = zma_decode(agentID, strEncoded, kindofkey);

        if (params2 == null) {
            return res;
        }

        try {
            int size = (int) params2.size();
            for (int i = 0; i < size; i++) {
                res.add(params2.get(i));
            }
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }

        return res;
    }

    private std__vectorT_std__string_t zma_decode(String agentID, String strEncoded, final int kindofkey) {

        String k = getKey(agentID, 1);
        if (k == null) {
            return null;
        }

        k = getPreKey(kindofkey) + k;

        return zma_decode(agentID, strEncoded, k);
    }
    private static String gen_catalog = "";

    private String getZingmeName(int zid) {
        return getZingName(zid);
    }
    private static int billingExpireTime = 300;
    private static MemcachedClient _memClient = null;

    private MemcachedClient getMemCacheClient() {
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(new InetSocketAddress(System.getProperty("memHost", "localhost"), Integer.parseInt(System.getProperty("memPort", "11321"))));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        return client;
    }

    private boolean addMemCache(String key, Object val) {
        int hope_count = 3;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                getMemClient().set(key, 0, val);
                res = true;
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
                renewMemClient();
            }
            --hope_count;
        }
        return res;
    }

    private boolean checkExist(String key) {

        int hope_count = 3;
        boolean res = false, flag = false;

        while (hope_count > 0 && !flag) {
            try {
                Object obj = getMemClient().get(key);
                if (obj != null) {
                    int resCode = (Integer) (obj);
                    if (resCode == PaymentReturnCode.PayletterReturnCode.P_SUCCESS
                            || resCode == PaymentReturnCode.PayletterReturnCode.P_ERROR_SIG_INVALID) {
                        res = true;
                    }
                }
                flag = true;
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
                renewMemClient();
            }
            --hope_count;
        }
        return res;
    }

    private String getPreKey(int kindofkey) {
        String res = "";
        switch (kindofkey) {
            case 0:
                res = "pay";
                break;
            case 1:
                res = "bal";
                break;
            case 2:
                res = "kindtwo";
                break;
            case 3:
                res = "kindthree";
                break;
            case 4:
                res = "kindfour";
                break;
            case 5:
                res = "kindfive";
                break;
            case 6:
                res = "kindsix";
                break;
            default:
                res = "";

        }
        return res;
    }

    public PaymentServiceGateway(int val) {
        try {
            System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
            System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
        }
        billingExpireTime = Integer.parseInt(System.getProperty("billingExpireTime", "300"));
    }

    public std__vectorT_std__string_t zma_decode(String agentID, String strEncoded, String k) {
        std__vectorT_std__string_t params2 = new std__vectorT_std__string_t();

        zcommon_StringHolder key = new zcommon_StringHolder();

        key.setValue(k);
        zcommon_StringHolder data = new zcommon_StringHolder();
        data.setValue(strEncoded);

        int e = ZCypher.zma_decode(params2, data, key, 0, billingExpireTime);
        if (e < 0) {
            return null;
        }

        return params2;
    }
    
    private static final Logger datalog = Logger.getLogger("dataActions");
    private static final Logger requestfaillog = Logger.getLogger("requestfailActions");
    private static final Logger log_info = Logger.getLogger("infoActions");
    private static AppInfoCache aiCache = null;
    // private static GCMonitor gcMonitor = null;
    private static OpHandle stdprofileOpt = null;

    private static int getZingId(String zingName) {
        TClientPool<StdProfile2Service_Rd.Client> firstPool = (TClientPool<StdProfile2Service_Rd.Client>) TClientPoolMan.Instance.getFirstPool(StdProfile2Service_Rd.Client.class);
        int resId = 0;
        StdProfile2Service_Rd.Client cli = null;
        try {
            cli = (StdProfile2Service_Rd.Client) firstPool.borrowClient();
            TValueResult val = cli.getByName(stdprofileOpt, zingName);
            if (val.error >= 0) {
                resId = val.value.uID;
            } else {
                log.warn("StdProfile2Service_Rd.getByName fail with code " + val.error + " for username " + zingName);
            }
            firstPool.returnObject(cli);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            firstPool.invalidObjExless(cli);
        }
        return resId;
    }

    private static String getZingName(int id) {
        TClientPool<StdProfile2Service_Rd.Client> firstPool = (TClientPool<StdProfile2Service_Rd.Client>) TClientPoolMan.Instance.getFirstPool(StdProfile2Service_Rd.Client.class);
        String resName = "";
        StdProfile2Service_Rd.Client cli = null;
        try {
            cli = (StdProfile2Service_Rd.Client) firstPool.borrowClient();
            TValueResult val = cli.get(stdprofileOpt, id);
            if (val.error >= 0) {
                resName = val.value.userName;
            } else {
                log.warn("StdProfile2Service_Rd.get fail with code " + val.error + " for userid " + id);
            }

            firstPool.returnObject(cli);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            firstPool.invalidObjExless(cli);
        }
        return resName;
    }

    private static void initalizeStdProfileClientPool() {
        String STDPROFILE_ADDR = System.getProperty("stdprofileHost", "10.30.22.173");
        int STDPROFILE_PORT = Integer.parseInt(System.getProperty(
                "stdprofilePort", "9281"));
        TClientPoolConfig.ConnConfig STDPROFILE_CONFIG_CONN = new TClientPoolConfig.ConnConfig(
                STDPROFILE_ADDR, STDPROFILE_PORT, true, false, 0);



        TClientPoolMan.Instance.addPool(
                new TClientPool<StdProfile2Service_Rd.Client>(
                new StdProfile2Service_Rd.Client.Factory(), STDPROFILE_CONFIG_CONN, TClientPoolConfig.DefaultPoolConfig));

        stdprofileOpt = new OpHandle();
        stdprofileOpt.auth = System.getProperty("stdprofileAuth", "9EpNVY");
        stdprofileOpt.source = System.getProperty("stdprofileSource", "credits");
    }
    protected static final Object _memClientLock = new Object();

    protected final MemcachedClient getMemClient() {
        synchronized (_memClientLock) {
            if (_memClient == null) {
                _memClient = getMemCacheClient();
            }
        }
        return _memClient;
    }

    protected MemcachedClient renewMemClient() {
        synchronized (_memClientLock) {
            if (_memClient != null) {
                _memClient.shutdown();
            }
            _memClient = getMemCacheClient();
        }
        return _memClient;
    }

    
    public static void createZKWorkingPath() {
        String pulltranxpath = System.getProperty("vng.zingme.payment.pull.tranx.path");
        String pushtranxpath = System.getProperty("vng.zingme.payment.push.tranx.path");

        String auth = System.getProperty("paymentauthencode");

        ZKFrontEndService adater = ZKFrontEndService.getInstance();
        if (adater != null) {
            ZKUtil.createZKPath(ZKFrontEndService.getZk(), pulltranxpath, auth);
            ZKUtil.createZKPath(ZKFrontEndService.getZk(), pushtranxpath, auth);
        }
    }
}
