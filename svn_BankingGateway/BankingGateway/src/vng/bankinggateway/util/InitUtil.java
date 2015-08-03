/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.util;

import org.apache.log4j.PropertyConfigurator;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.ConfigUtil;

/**
 *
 * @author root
 */
public class InitUtil {

    public static byte[] sharedkey = {};

    public static byte[] sharedvector = {};
    
    public static String PROXY_HOST = "";
    public static int PROXY_PORT = 0;
    public static String HDBANK_URL = "";
    public static int DURATION_TIME_PENDING = 0;
    public static int TIME_SLEEP = 0;
    public static String PRIVATE_KEY_RECONCILE = "";
    public static String FOLDER_RECONCILE = "";
    public static String REPORTER = "";
    
    
    // VIETTIN
    public static String TERMINAL_CODE_1 = "";
    public static String TERMINAL_CODE_2 = "";
    public static String SECRETKEY = "";
    public static String VIETTINBANK_URL = "";
    public static String VIETTINBANK_PAYMENTMETHOD = "VNPAY";
    
    // 123pay
    public static String MERCHANTCODE = "";
    public static String SECRETKEY_123PAY = "";
    public static String PASSCODE_123PAY = "";
    public static String URL_123PAY = "";
    public static String URL_123PAY_QUERY = "";
    
    public static void initEncryptionInfo(String key, String vector) throws Exception {
        String[] temp = key.split(",");
        if (temp.length < 24) {
            throw new Exception("[ERROR]Invalid key format, must be 24 hex chars");
        }
        byte[] keyTemp = new byte[24];
        for (int i = 0; i < 24; i++) {
            int intTemp;
            try {
                intTemp = Integer.parseInt(temp[i]);
            } catch (Exception e) {
                throw new Exception("[ERROR]Invalid key format, key member must be a number");
            }
            keyTemp[i] = (byte) intTemp;
        }
        InitUtil.sharedkey = keyTemp.clone();
        String[] temp2 = vector.split(",");
        if (temp2.length < 8) {
            throw new Exception("[ERROR]Invalid vector format, must be 8 hex chars");
        }
        byte[] vectorTemp = new byte[8];
        for (int i = 0; i < 8; i++) {
            int intTemp2;
            try {
                intTemp2 = Integer.parseInt(temp2[i]);
            } catch (Exception e) {
                throw new Exception("[ERROR]Invalid vector format, vector member must be a number");
            }
            vectorTemp[i] = (byte) intTemp2;
        }
        InitUtil.sharedvector = vectorTemp.clone();
    }
    
     
    public static void initConfiguration() {
        try {
            String config = System.getProperty("config", "");
            String appEnv = System.getProperty("appenv", "staging");
            PropertyConfigurator.configure(config + "/log4j.properties");
            ConfigUtil.loadConfigFile(config + "/banking_" + appEnv + ".config");
            
            CommonDef.SHA1_SHARED_KEY = System.getProperty("sha1_shared_key", "");
            CommonDef.STORAGE_HOST = System.getProperty("storage_host", "");
            CommonDef.STORAGE_PORT = Integer.valueOf(System.getProperty("storage_port", "0"));
            CommonDef.SCRIBE_HOST = System.getProperty("scribe_host", "");
            CommonDef.SCRIBE_PORT = Integer.valueOf(System.getProperty("scribe_port", ""));
            CommonDef.ESALE_WEBSERVICE_URL = System.getProperty("ESALE_URL", "");
            CommonDef.ESALE_REDIRECT_URL = System.getProperty("esale_redirect_url", "");
            
            InitUtil.PROXY_HOST = System.getProperty("proxy_host", "");
            InitUtil.PROXY_PORT = Integer.valueOf(System.getProperty("proxy_port", "80"));
            InitUtil.HDBANK_URL = System.getProperty("HDBank_url");
            InitUtil.DURATION_TIME_PENDING = Integer.valueOf(System.getProperty("duration_time_pending", "900000"));
            InitUtil.TIME_SLEEP = Integer.valueOf(System.getProperty("time_sleep_worker", "300000"));
            InitUtil.PRIVATE_KEY_RECONCILE = System.getProperty("private_key_reconcile"); 
            InitUtil.FOLDER_RECONCILE = System.getProperty("folder_reconcile");
            InitUtil.REPORTER = System.getProperty("reporter");
            initEncryptionInfo(System.getProperty("sharedkey"), System.getProperty("sharedvector"));
            
            InitUtil.TERMINAL_CODE_1 = System.getProperty("TerminalCode1", "");
            InitUtil.TERMINAL_CODE_2 = System.getProperty("TerminalCode2", "");
            InitUtil.SECRETKEY = System.getProperty("SecretKey", "");
            InitUtil.VIETTINBANK_URL = System.getProperty("VietTinBank_url");
            InitUtil.VIETTINBANK_PAYMENTMETHOD = System.getProperty("PaymentMethod", "VNPAY");
            
            InitUtil.SECRETKEY_123PAY = System.getProperty("SecretKey123Pay", "").trim();
            InitUtil.PASSCODE_123PAY = System.getProperty("PassCode123Pay", "").trim();
            InitUtil.URL_123PAY = System.getProperty("url_123pay", "");
            InitUtil.URL_123PAY_QUERY = System.getProperty("url_123pay_query", "");
            InitUtil.MERCHANTCODE = System.getProperty("MerchantCode", "").trim();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
