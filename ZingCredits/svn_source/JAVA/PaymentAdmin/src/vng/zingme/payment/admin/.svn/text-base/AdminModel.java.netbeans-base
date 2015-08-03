/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.admin;

import ZGenerator.Generator;
import com.vng.zing.stdprofile2.thrift.StdProfile2Service_Rd;
import com.vng.zing.stdprofile2.thrift.TValueResult;
import com.vng.zing.zcommon.thrift.OpHandle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.zingme.util.StringUtil;

import java.util.Date;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.T_Transaction;

import org.apache.commons.codec.binary.Base64;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.bo.thrift.StorageHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.ScriberUtil;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Account;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class AdminModel {

    private static final Logger log = Logger.getLogger("appActions");

    public static String genSig(String gSig) {
        return genCode(gSig, gSig);
    }

    public static int checkAdmin(String adminSig) {

        String val = genAdminCode();

        if (val == null ? adminSig == null : val.equals(adminSig)) {
            return PaymentReturnCode.AdminCode.A_SUCCESS;
        }

        return PaymentReturnCode.AdminCode.A_ERROR;
    }

    private static MessageDigest getDigist(String key) {
        MessageDigest digist = null;
        try {
            String _sig = StringUtil.getHexString(key.getBytes());
            _sig = _sig.substring(0, 4);
            long val = Long.parseLong(_sig, 16);
            int v = (int) (val % 7);
            switch (v) {
                case 1:
                case 3:
                case 5:
                case 0:
                    digist = java.security.MessageDigest.getInstance("SHA-512");
                    break;
                case 2:
                case 4:
                case 6:
                default:
                    digist = java.security.MessageDigest.getInstance("SHA-384");
                    break;
            }
        } catch (NoSuchAlgorithmException ex) {
            log.warn(ex.getMessage());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }

        if (digist == null) {
            try {
                digist = java.security.MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                log.warn(ex.getMessage());
            }
        }
        return digist;
    }

    private static String mergs(String a, String b) {

        String res = "";
        if (b == null || b.length() <= 0) {
            res = a;
        } else {
            res += b.substring(b.length() - 1);
            res += a;
            if (b.length() > 1) {
                res += b.substring(0, b.length() - 2);
            }
        }
        return res;
    }

    public static String genAdminCode() {
        String _privatekey = System.getProperty("adminkey", "zingvngpaymentkey");
        String _akey = System.getProperty("admink", "adminsig");

        return genCode(_akey, _privatekey);
    }

    private static String genCode(String a, String b) {
        MessageDigest digist = getDigist(a);
        String _k = mergs(b, a);
        digist.update(_k.getBytes());

        // return StringUtil.getHexString(digist.digest());
        return new String(Base64.encodeBase64(digist.digest()));
    }

    public static int adjustUser(final int userID, final double adjustMoney, final String adminSig, final String reason, final String clientIP, final int time) {
        if (checkAdmin(adminSig) != PaymentReturnCode.AdminCode.A_SUCCESS) {
            return ADMIN_ADJUST_CODE_INVALID_ADMIN_SIG;
        }

        if (userID <= 0) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        BalanceCacheHandler blCache = BalanceCacheHandler.getMainInstance();

        double currentBalance = -1.0;

        int hope_count = 2;
        T_AccResponse accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
        while (hope_count > 0 && accRes.code != PaymentReturnCode.SUCCESS) {
            try {
                accRes = blCache.getBalance(userID);
            } catch (TException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            if (accRes == null) {
                accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
            }
            --hope_count;
        }
        currentBalance = accRes.currentBalance;
        long txID = -1;
        try {
            TTransport socket = new TSocket(System.getProperty("zgenHost", "localhost"), Integer.parseInt(System.getProperty("zgenPort", "9090")));
            TProtocol protocol = new TBinaryProtocol(socket);
            socket.open();
            Generator.Client client = new Generator.Client(protocol);
            txID = client.getValue(System.getProperty("zgen-catalog", "zingcredits"));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        if (txID <= 0 || (currentBalance + adjustMoney) < 0) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        T_Transaction tranx = new T_Transaction();
        tranx.txID = txID;
        tranx.userID = userID;
        tranx.agentID = "admin";
        tranx.amount = adjustMoney;
        tranx.currentBalance = currentBalance;
        tranx.refID = String.valueOf(txID);
        tranx.userName = getZingName(userID);
        tranx.description = reason;
        tranx.clientIP = clientIP;
        tranx.txType = CommonDef.TRANX_TYPE.TT_ADMIN;
        tranx.txTime = time;
        //create - transactionTime
        tranx.txLocalTime = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);
        tranx.responseCode = CommonDef.TRANX_STAT.TS_INPROCESS;

        //log-transaction
        hope_count = 5;
        int res = PaymentReturnCode.DatabaseCode.DB_ERROR;
        while (hope_count != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                res = StorageHandler.getMainInstance().storeTx(tranx);
            } catch (Exception ex) {
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

        if (res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        //adjust money
        T_Account acc = new T_Account(userID, currentBalance, adjustMoney, (long) txID, (short) CommonDef.TRANX_TYPE.TT_ADMIN, reason);

        accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
        hope_count = 3;
        while (hope_count > 0 && accRes.code != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                accRes = blCache.add(acc);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            if (accRes == null) {
                accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
            }
            --hope_count;
        }

        if (accRes.code != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_UPDATED_BALANCE, CommonDef.TRANX_RES_CODE.TC_SUCCESS, reason));

        //put to latestcache & scriber-log

        tranx.responseCode = CommonDef.TRANX_STAT.TS_UPDATED_BALANCE;
        hope_count = 2;
        int pcode = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        while (hope_count > 0 && pcode != PaymentReturnCode.SUCCESS) {
            try {
                pcode = LatestTranxCacheHandler.getMainInstance().put(tranx);
            } catch (TException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }

        s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_SUCCESS);

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

        return ADMIN_ADJUST_CODE_SUCCESS;
    }
    
    public static int compensate(final int userID, final double adjustMoney, final String adminSig, final String reason, final String clientIP, final int time, final String appID, final int txType) {
        if (checkAdmin(adminSig) != PaymentReturnCode.AdminCode.A_SUCCESS) {
            return ADMIN_ADJUST_CODE_INVALID_ADMIN_SIG;
        }

        if (userID <= 0) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        BalanceCacheHandler blCache = BalanceCacheHandler.getMainInstance();

        double currentBalance = -1.0;

        int hope_count = 2;
        T_AccResponse accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
        while (hope_count > 0 && accRes.code != PaymentReturnCode.SUCCESS) {
            try {
                accRes = blCache.getBalance(userID);
            } catch (TException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            if (accRes == null) {
                accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
            }
            --hope_count;
        }
        currentBalance = accRes.currentBalance;
        long txID = -1;
        try {
            TTransport socket = new TSocket(System.getProperty("zgenHost", "localhost"), Integer.parseInt(System.getProperty("zgenPort", "9090")));
            TProtocol protocol = new TBinaryProtocol(socket);
            socket.open();
            Generator.Client client = new Generator.Client(protocol);
            txID = client.getValue(System.getProperty("zgen-catalog", "zingcredits"));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        if (txID <= 0 || (currentBalance + adjustMoney) < 0) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        T_Transaction tranx = new T_Transaction();
        tranx.txID = txID;
        tranx.userID = userID;
        tranx.agentID = appID; // vinh
        tranx.amount = adjustMoney;
        tranx.currentBalance = currentBalance;
        tranx.refID = String.valueOf(txID);
        tranx.userName = getZingName(userID);
        tranx.description = reason;
        tranx.clientIP = clientIP;
        tranx.txType = (short) txType; // vinh
        tranx.txTime = time;
        //create - transactionTime
        tranx.txLocalTime = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);
        tranx.responseCode = CommonDef.TRANX_STAT.TS_INPROCESS;

        //log-transaction
        hope_count = 5;
        int res = PaymentReturnCode.DatabaseCode.DB_ERROR;
        while (hope_count != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                res = StorageHandler.getMainInstance().storeTx(tranx);
            } catch (Exception ex) {
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

        if (res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        //adjust money
        T_Account acc = new T_Account(userID, currentBalance, adjustMoney, (long) txID, (short) CommonDef.TRANX_TYPE.TT_ADMIN, reason);

        accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
        hope_count = 3;
        while (hope_count > 0 && accRes.code != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                accRes = blCache.add(acc);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            if (accRes == null) {
                accRes = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, 0.0);
            }
            --hope_count;
        }

        if (accRes.code != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            return ADMIN_ADJUST_CODE_FAIL;
        }

        tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_UPDATED_BALANCE, CommonDef.TRANX_RES_CODE.TC_SUCCESS, reason));

        //put to latestcache & scriber-log

        tranx.responseCode = CommonDef.TRANX_STAT.TS_UPDATED_BALANCE;
        hope_count = 2;
        int pcode = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        while (hope_count > 0 && pcode != PaymentReturnCode.SUCCESS) {
            try {
                pcode = LatestTranxCacheHandler.getMainInstance().put(tranx);
            } catch (TException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }

        s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_SUCCESS);

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

        return ADMIN_ADJUST_CODE_SUCCESS;
    }
    private static final int ADMIN_ADJUST_CODE_SUCCESS = 0;
    private static final int ADMIN_ADJUST_CODE_FAIL = -1;
    private static final int ADMIN_ADJUST_CODE_INVALID_ADMIN_SIG = -2;

    private static int tryUpdateTranxStat(T_TranStat ts) {
        int hope_count = 5, res = PaymentReturnCode.DatabaseCode.DB_ERROR;
        while (hope_count > 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                res = StorageHandler.getMainInstance().updateTransactionStatus(ts);
            } catch (TException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }
        return res;
    }
    private static final Logger datalog = Logger.getLogger("dataActions");

    private static String getZingName(int zid) {
        String retName = "";
        int hope_count = 2;
        while (hope_count > 0 && (retName == null || retName.equals(""))) {
            try {
                TTransport socket = new TSocket(System.getProperty("stdprofileHost", "10.30.22.173"),
                        Integer.parseInt(System.getProperty(
                        "stdprofilePort", "9281")));
                TTransport framedtrans = new TFramedTransport(socket);
                TProtocol protocol = new TBinaryProtocol(framedtrans);
                framedtrans.open();
                StdProfile2Service_Rd.Client client = new StdProfile2Service_Rd.Client(protocol);

                TValueResult val = client.get(stdprofileOpt, zid);
                if (val.error >= 0) {
                    retName = val.value.userName;
                } else {
                    log.warn("StdProfile2Service_Rd.get fail with code " + val.error + " for userid " + zid);
                }

            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }
        return retName;
    }
    private static OpHandle stdprofileOpt = null;

    static {
        stdprofileOpt = new OpHandle();
        stdprofileOpt.auth = System.getProperty("stdprofileAuth", "9EpNVY");
        stdprofileOpt.source = System.getProperty("stdprofileSource", "credits");
    }
}
