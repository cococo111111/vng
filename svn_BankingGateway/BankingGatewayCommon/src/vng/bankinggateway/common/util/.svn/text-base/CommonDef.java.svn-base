/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.common.util;

/**
 *
 * @author root
 */
public class CommonDef {
    public static String ESALE_WEBSERVICE_URL = "";
    public static String ESALE_REDIRECT_URL = "";
    public static String SHA1_SHARED_KEY = "";
    public static String STORAGE_HOST = "";
    public static int STORAGE_PORT = 0;
    public static int SCRIBE_PORT = 0;
    public static String SCRIBE_HOST = "";
    public static String INVALID_CONNECTION = "ERROR";
    
    public static class CONFIRM_TRANSACTION_STATUS {
        public static final short PENDING = 0;
        public static final short SUCCESS = 1;
        public static final short FAILED_TRANSANPORT = 2;
        public static final short CONNECTION_TIMEOUT = 3;
        public static final short WEBSERVICE_RESPONSE_ERROR = 4;
    }
    
    
    public static class TRANSACTION_STATUS {
        public static final short TXS_FAIL = -1;
        public static final short TXS_SUCCESS = 100;
        public static final short TXS_ERROR = -2;
    }
    
    public static class REQUEST_TRANSAFER_RESPONSE {
        public static final int INVALID_SIG = -1;
        public static final int TRANSACTION_EXISTED = -2;
    }
    
    public static class CONNECTION_ERROR_MESSAGE {
        public static final String INVALID_TRASPORT = "Transport error";
        public static final String CONNECTION_TIMEOUT = "Connection timed out";
    }
    
    public static class ZION_REGION {
        public static final String ZIONBAC = "ZIONBAC";
        public static final String ZIONNAM = "ZIONNAM";
    }
    
    public static class BANK_METHOD {
        public static final short ESALE_INTERNET_BANKING = 1;
        public static final short INTERNET_BANKING = 2;
    }
    
    public static void initConfigurationForCommon() {
        CommonDef.SHA1_SHARED_KEY = System.getProperty("sha1_shared_key", "");
        CommonDef.ESALE_WEBSERVICE_URL = System.getProperty("ESALE_URL", "");
        CommonDef.STORAGE_HOST = System.getProperty("storage_host", "");
        CommonDef.STORAGE_PORT = Integer.valueOf(System.getProperty("storage_port", "0"));
        CommonDef.ESALE_REDIRECT_URL = System.getProperty("esale_redirect_url", "");
        CommonDef.SCRIBE_HOST = System.getProperty("scribe_host", "");
        CommonDef.SCRIBE_PORT = Integer.valueOf(System.getProperty("scribe_port", ""));
    }
    
    public static class BANKCODE {
        public static final String HDBANK = "HDBANK";
        public static final String VIETINBANK = "VIETINBANK";
        public static final String BIDVBANK = "BIDVBANK";
        public static final String BIDV123PBANK = "123PBIDV";
        public static final String VIETINBANKVISA = "VISAMASTER";
    }
}
