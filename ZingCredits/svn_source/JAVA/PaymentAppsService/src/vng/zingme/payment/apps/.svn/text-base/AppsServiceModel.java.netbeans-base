/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.apps;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.model.util.SQLUtil;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_Transaction;

import org.apache.commons.codec.binary.Base64;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class AppsServiceModel {

    private static final int REGISTER_CODE_SUCCESS = 0;
    private static final int REGISTER_CODE_FAIL = -1;
    private static final int REGISTER_CODE_CANTWRITEDB = -2;
    private static final int REGISTER_CODE_INVALID_ADMIN_SIG = -3;
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

    public static void main(String[] args) {

        String stTest = "My Test: Base64 en-decode";

        byte[] test = Base64.encodeBase64(stTest.getBytes());

        System.out.println(new String(test));

        byte[] testdecoded = Base64.decodeBase64(test);

        System.out.println(new String(testdecoded));

        String hello = "SGVsbG8gV29ybGQ=";

        byte[] decoded = null;
        decoded = Base64.decodeBase64(hello.getBytes());

        System.out.println(Arrays.toString(decoded));

        String decodedString = new String(decoded);
        System.out.println(hello + " = " + decodedString);

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

    public static String pEncrypt(String sig) {
        return encrypt(sig, System.getProperty("plpsPharse", "zingme-passphrase.pm.me.zing.vn"));
    }

    public static String pDescrypt(String enSig) {
        return descrypt(enSig, System.getProperty("plpsPharse", "zingme-passphrase.pm.me.zing.vn"));
    }

    public static String gEncrypt(String sig) {
        return encrypt(sig, System.getProperty("gamepPharse", "zingme-game.me.zing.vn"));
    }

    public static String gDescrypt(String enSig) {
        return descrypt(enSig, System.getProperty("gamepPharse", "zingme-game.me.zing.vn"));
    }

    public static String encrypt(String sig, String passPhrase) {

        Cipher ecipher = initCipher(passPhrase, Cipher.ENCRYPT_MODE);
        if (ecipher == null) {
            log.warn("En-cipher is null!");
            return null;
        }
        return _encrypt(sig, ecipher);
    }

    public static String descrypt(String enSig, String passPhrase) {
        Cipher dcipher = initCipher(passPhrase, Cipher.DECRYPT_MODE);
        if (dcipher == null) {
            log.warn("De-cipher is null!");
            return null;
        }
        return _decrypt(enSig, dcipher);
    }

    private static Cipher initCipher(String passPhrase, int mode) {

        Cipher cipher = null;
        // 8-bytes Salt
        byte[] salt = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
        };

        // Iteration count
        int iterationCount = 19;

        try {

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            cipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            cipher.init(mode, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            String msg = "EXCEPTION: InvalidAlgorithmParameterException";
            // System.out.println(msg);
            log.warn(msg);
        } catch (InvalidKeySpecException e) {
            String msg = "EXCEPTION: InvalidKeySpecException";
            // System.out.println(msg);
            log.warn(msg);
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPTION: NoSuchPaddingException");
            String msg = "EXCEPTION: InvalidAlgorithmParameterException";
            // System.out.println(msg);
            log.warn(msg);
        } catch (NoSuchAlgorithmException e) {
            String msg = "EXCEPTION: NoSuchAlgorithmException";
            // System.out.println(msg);
            log.warn(msg);
        } catch (InvalidKeyException e) {
            String msg = "EXCEPTION: InvalidKeyException";
            // System.out.println(msg);
            log.warn(msg);
        }
        return cipher;
    }

    private static String _encrypt(String str, Cipher ecipher) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // utf8 string
            return StringUtil.getHexString(enc);


        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    private static String _decrypt(String str, Cipher dcipher) {

        try {

            // utf8 bytes: get bytes
            byte[] dec = StringUtil.HexStringToByteArray(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e) {
            System.out.println(e);
        } catch (IllegalBlockSizeException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getKey(String path, String agentID, boolean isGame) {
        ConfigUtil.loadConfigFile(path, AppsServiceModel.class.getName());

        String key = System.getProperty(agentID);

        if (key != null) {
            if (!isGame) {
                key = AppsServiceModel.pDescrypt(key);
            } else {
                key = AppsServiceModel.gDescrypt(key);
            }

        }
        return key;
    }

    public static String genMac(String key, T_Transaction tranx) {
        String mac = null;
        long lVal = (long) tranx.amount;
        if (lVal == tranx.amount) {
            mac = tranx.userName + tranx.refID + tranx.agentID + tranx.itemIDs + tranx.itemNames + tranx.itemQuantities + tranx.itemPrices + String.valueOf(lVal) + tranx.txTime + key;
        } else {
            mac = tranx.userName + tranx.refID + tranx.agentID + tranx.itemIDs + tranx.itemNames + tranx.itemQuantities + tranx.itemPrices + String.valueOf(tranx.amount) + tranx.txTime + key;
        }
        return mac;
    }

    private static int setAppInfo(T_AppInfo appInfo, boolean isInsert) {

        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {

                preparedStatement = makeStatement(conn, appInfo, isInsert);

                result = preparedStatement.executeUpdate();

                preparedStatement.close();

                DBConnectionManager.getInstance().returnConnection(conn);
            }
        } catch (SQLException e) {

            DBConnectionManager.getInstance().invalidateConnection(conn);

            if (SQLUtil.isDuplicate(e)) {
                result = setAppInfo(appInfo, false);
            }

            log.warn(LogUtil.stackTrace(e));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            DBConnectionManager.getInstance().invalidateConnection(conn);
        }

        return result;
    }

    private static PreparedStatement makeStatement(Connection conn, T_AppInfo appInfo, boolean isInsert) throws SQLException {
        PreparedStatement preparedStatement = null;
        if (isInsert) {
            preparedStatement = conn.prepareStatement(SQL_INSERT_APPINFO);
        } else {
            preparedStatement = conn.prepareStatement(SQL_UPDATE_APPINFO);
        }
        preparedStatement.setString(1, appInfo.appName);
        preparedStatement.setString(2, appInfo.appDes);
        preparedStatement.setString(3, appInfo.appURL);
        preparedStatement.setString(4, appInfo.iconPath);
        preparedStatement.setString(5, appInfo.restURL);
        preparedStatement.setString(6, appInfo.key1);
        preparedStatement.setString(7, appInfo.key2);
        preparedStatement.setByte(8, appInfo.isEnabled);
        preparedStatement.setString(9, appInfo.appID);
        return preparedStatement;
    }
    private static final String SQL_INSERT_APPINFO = "insert into apps_info(appName, appDesc, appURL, iconPath, restURL, key1, key2, isEnabled, appID) values(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_APPINFO = "update apps_info set appName=?, appDesc=?, appURL=?, iconPath=?, restURL=?, key1=?, key2=?, isEnabled=? where appID=?";

    public static T_AppInfo getAppInfo(String appID) {
        T_AppInfo appInfoinCache = _cache.get(appID);
        if (appInfoinCache != null) {
            return appInfoinCache;
        }

        T_AppInfo result = new T_AppInfo();

        int hope_count = 10;

        while ((appID == null ? result.appID != null : !appID.equals(result.appID)) && hope_count > 0) {
            List<T_AppInfo> listApp = getAppsInfoFromdb(appID);
            if (listApp.size() > 0) {
                result = listApp.get(0);
            }
            --hope_count;
        }

        if (appID == null ? result.appID == null : appID.equals(result.appID)) {
            /*if (appID == null ? "zing" == null : appID.equals("zing")) {
            result.key2 = pDescrypt(result.key2);
            } else {
            result.key1 = gDescrypt(result.key1);
            result.key2 = gDescrypt(result.key2);
            }*/
            _cache.put(appID, result);
        }

        return result;
    }

    private static List<T_AppInfo> getAppsInfoFromdb(String appID) {
        List<T_AppInfo> result = new LinkedList<T_AppInfo>();

        Connection conn = null;
        int hop_cout = 5;
        while (hop_cout > 0 && conn == null) {
            try {
                conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            } catch (Exception ex) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
                log.warn("AdminModel:getAppInfoFromdb getConnection: " + ex.getMessage());
            }
            --hop_cout;
        }

        if (conn == null) {
            DBConnectionManager.getInstance().invalidateConnection(conn);

            String msg = "AdminModel:getAppInfoFromdb connection-db null!";
            // System.out.print(msg);
            log.warn(msg);

            return result;
        }

        String sql;
        if (appID != null) {
            sql = "select * from apps_info where appID='" + appID + "'";
        } else {
            sql = "select * from apps_info";
        }

        log.info("Select statment: " + sql);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    T_AppInfo ai = new T_AppInfo();

                    ai.appID = resultSet.getString("appID");
                    ai.appName = resultSet.getString("appName");
                    ai.appDes = resultSet.getString("appDesc");
                    ai.appURL = resultSet.getString("appURL");
                    ai.iconPath = resultSet.getString("iconPath");
                    ai.restURL = resultSet.getString("restURL");
                    ai.key1 = resultSet.getString("key1");
                    ai.key2 = resultSet.getString("key2");
                    ai.isEnabled = resultSet.getByte("isEnabled");

                    result.add(ai);
                }
            }
            preparedStatement.close();

            DBConnectionManager.getInstance().returnConnection(conn);

        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));

            DBConnectionManager.getInstance().invalidateConnection(conn);
        }

        return result;
    }
    private static ConcurrentLinkedHashMap<String, T_AppInfo> _cache = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, Integer.parseInt(System.getProperty("appinfo-cachesize", "1024")));

    public static String registerGameServer(T_AppInfo appInfo, String adminSig, final boolean isNewKey) {
        if (checkAdmin(adminSig) == PaymentReturnCode.AdminCode.A_SUCCESS) {

            T_AppInfo resAppInfo = new T_AppInfo(appInfo);

            if (appInfo.appID.length() < 2) {
                return "X";
            }

            String res = "";

            if (isNewKey) {

                String mk = appInfo.appID + new Random(System.currentTimeMillis()).nextInt();

                String gHash = genCode(appInfo.appID, mk);

                gHash = gHash.substring(0, Integer.parseInt(System.getProperty("game-key-lent", "32")));

                resAppInfo.key1 = gHash;

                res = gHash;

                mk = appInfo.appID + new Random(System.currentTimeMillis()).nextInt() + "@";

                gHash = genCode(appInfo.appID, mk);

                gHash = gHash.substring(0, Integer.parseInt(System.getProperty("game-key-lent", "32")));

                resAppInfo.key2 = gHash;

                res += gHash;
            } else {
                T_AppInfo oldInfo = getAppInfo(resAppInfo.appID);
                resAppInfo.key1 = oldInfo.key1;
                resAppInfo.key2 = oldInfo.key2;
                res = resAppInfo.key1;
            }

            int hop_count = 5, resCode = PaymentReturnCode.DatabaseCode.DB_ERROR;
            while (hop_count > 0 && resCode != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                resCode = setAppInfo(resAppInfo, true);
                --hop_count;
            }

            if (resCode == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                _cache.remove(appInfo.appID);
                _cache.put(appInfo.appID, resAppInfo);
                return res;
            }

            return "Y";

        }
        return "";
    }

    public static int registerPayLetterServer(T_AppInfo appInfo, String adminSig) {
        if (checkAdmin(adminSig) == PaymentReturnCode.AdminCode.A_SUCCESS) {

            T_AppInfo resAppInfo = new T_AppInfo(appInfo);

            if (appInfo.appID.length() < 2) {
                return REGISTER_CODE_FAIL;
            }

            // String pHash = pEncrypt(appInfo.key1);

            resAppInfo.key2 = resAppInfo.key1;
            // resAppInfo.key1 = pHash;

            int hop_count = 5, resCode = PaymentReturnCode.DatabaseCode.DB_ERROR;
            while (hop_count > 0 && resCode != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                resCode = setAppInfo(resAppInfo, true);
                --hop_count;
            }

            if (resCode == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                _cache.remove(appInfo.appID);
                _cache.put(appInfo.appID, resAppInfo);
                return REGISTER_CODE_SUCCESS;
            }

            return REGISTER_CODE_CANTWRITEDB;

        }
        return REGISTER_CODE_INVALID_ADMIN_SIG;
    }

    public static String getKey(String agentID, boolean isGame) {

        T_AppInfo appInfo = getAppInfo(agentID);

        if (agentID == null ? appInfo.appID != null : !agentID.equals(appInfo.appID)) {
            return null;
        }
        String key = appInfo.key2;

        if (key != null) {
            if (!isGame) {
                key = AppsServiceModel.pDescrypt(key);
            } else {
                key = AppsServiceModel.gDescrypt(key);
            }

        }
        return key;
    }

    public static List<T_AppInfo> getAllAppInfo() {
        List<T_AppInfo> allApps = new LinkedList<T_AppInfo>();

        if (isLoaded) {
            for (T_AppInfo t_AppInfo : _cache.values()) {
                allApps.add(t_AppInfo);
            }
        } else {
            int hope_count = 5;

            while (allApps.size() <= 0 && hope_count > 0) {
                allApps = getAppsInfoFromdb(null);
                --hope_count;
            }

            if (allApps != null && allApps.size() > 0) {
                for (T_AppInfo t_AppInfo : allApps) {
                    int hc = 3;
                    int res = PaymentReturnCode.ERROR_OPERATOR_FAIL;
                    while (hc > 0 && res != PaymentReturnCode.SUCCESS) {
                        res = loadWhitelist(t_AppInfo.appID, t_AppInfo);
                        --hc;
                    }
                    _cache.put(t_AppInfo.appID, t_AppInfo);
                }
                isLoaded = true;
            }
        }

        return allApps;
    }
    private static boolean isLoaded = false;

    public static void addIdToWhitelist(final String appID, final int userID) {
        int hope_count = 5;
        int res = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        while (hope_count > 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS
                && res != PaymentReturnCode.DatabaseCode.DB_ERROR_DUPLICATE_KEY) {
            res = setAppWhitelist(appID, userID);
            --hope_count;
        }
        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            T_AppInfo ai = _cache.get(appID);
            if (ai.lswhitelist == null || ai.lswhitelist.size() <= 0) {
                ai.lswhitelist = new LinkedList<Integer>();
            }
            ai.lswhitelist.add(userID);
            _cache.put(appID, ai);
        }
    }

    public static void addIdsToWhitelist(final String appID, final List<Integer> lsUserID) {
        for (Integer userID : lsUserID) {
            addIdToWhitelist(appID, userID);
        }
    }

    private static int setAppWhitelist(String appID, int userID) {

        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {

                preparedStatement = conn.prepareStatement("insert into apps_whitelist(appID, userID) values(?,?)");

                preparedStatement.setString(1, appID);
                preparedStatement.setInt(2, userID);

                result = preparedStatement.executeUpdate();

                preparedStatement.close();

                DBConnectionManager.getInstance().returnConnection(conn);
            }
        } catch (SQLException e) {

            if (SQLUtil.isDuplicate(e)) {
                result = PaymentReturnCode.DatabaseCode.DB_ERROR_DUPLICATE_KEY;
            }

            DBConnectionManager.getInstance().invalidateConnection(conn);

            log.warn(LogUtil.stackTrace(e));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            DBConnectionManager.getInstance().invalidateConnection(conn);
        }

        return result;
    }

    public static void removeIdFromWhitelist(final String appID, final int userID) {
        int hope_count = 5;
        int res = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        while (hope_count > 0 && res < 0) {
            res = removeWhitelist(appID, userID);
            --hope_count;
        }
        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            T_AppInfo ai = _cache.get(appID);
            Integer iObj = new Integer(userID);
            ai.lswhitelist.remove(iObj);
            _cache.put(appID, ai);
        }
    }

    private static int removeWhitelist(String appID, int userID) {

        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {

                preparedStatement = conn.prepareStatement("delete from apps_whitelist where appID=? and userID=?");

                preparedStatement.setString(1, appID);
                preparedStatement.setInt(2, userID);

                // boolean info = preparedStatement.execute();

                // result = preparedStatement.execute() ? PaymentReturnCode.DatabaseCode.DB_SUCCESS : 0;
                result = preparedStatement.executeUpdate();

                preparedStatement.close();

                DBConnectionManager.getInstance().returnConnection(conn);
            }
        } catch (SQLException e) {

            if (SQLUtil.isDuplicate(e)) {
                result = PaymentReturnCode.DatabaseCode.DB_ERROR_DUPLICATE_KEY;
            }

            DBConnectionManager.getInstance().invalidateConnection(conn);

            log.warn(LogUtil.stackTrace(e));
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            DBConnectionManager.getInstance().invalidateConnection(conn);
        }

        return result;
    }

    private static int loadWhitelist(String appID, T_AppInfo ai) {
        int result = PaymentReturnCode.ERROR_OPERATOR_FAIL;

        Connection conn = null;
        int hop_cout = 5;
        while (hop_cout > 0 && conn == null) {
            try {
                conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            } catch (Exception ex) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
                log.warn("AdminModel:getAppInfoFromdb getConnection: " + ex.getMessage());
            }
            --hop_cout;
        }

        if (conn == null) {
            DBConnectionManager.getInstance().invalidateConnection(conn);

            String msg = "AdminModel:getAppInfoFromdb connection-db null!";
            // System.out.print(msg);
            log.warn(msg);

            return result;
        }

        String sql = "select * from apps_whitelist where appID=?";

        log.info("Select statment: " + sql);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, appID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                while (resultSet.next()) {
                    int userID = resultSet.getInt("userID");

                    if (ai.lswhitelist == null || ai.lswhitelist.size() <= 0) {
                        ai.lswhitelist = new LinkedList<Integer>();
                    }
                    ai.lswhitelist.add(userID);
                }
            }

            preparedStatement.close();

            result = PaymentReturnCode.SUCCESS;
            DBConnectionManager.getInstance().returnConnection(conn);

        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));

            DBConnectionManager.getInstance().invalidateConnection(conn);
        }

        return result;
    }
}
