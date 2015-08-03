package vng.zingme.payment.frontend.credits.config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.wte.common.Config;

/**
 * Load configuration file
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public class Configuration {

    private static final Logger logger_ = Logger.getLogger(Configuration.class);
    public static String PAYMENT_GATEWAY_HOST;
    public static int PAYMENT_GATEWAY_PORT;
    public static boolean IS_MAINTAIN_FLG;
    public static boolean IS_CHECK_WHITELIST_FLG;
    public static int APPINFO_CACHESIZE;
    public static String ADMIN_HOST;
    public static int ADMIN_PORT;
    public static int APPINFO_SLEEPTHREAD;
    public static String HUGELIST_READ_HOST;
    public static int HUGELIST_READ_PORT;
    public static int HUGELIST_READ_APPID;
    public static String TOKENCACHE_HOST;
    public static int TOKENCACHE_PORT;
    public static String USERAPPCACHE_HOST;
    public static int USERAPPCACHE_PORT;
    public static int USERAPPCACHE_EXPIRE;
    public static String TOKENBACKEND_HOST;
    public static int TOKENBACKEND_PORT;
    public static String SYSTEM_REQUESTFORM_TITLE;
    public static String SYSTEM_URL;
    public static String STATIC_URL;
    public static String SYSTEM_CREDITS_URL;
    public static String SYSTEM_BILLSTATUS_URL;
    public static String SSO_HOST;
    public static int SSO_PORT;
    public static String STDPROFILE_HOST;
    public static int STDPROFILE_PORT;
    public static String STDPROFILE_SOURCE;
    public static String STDPROFILE_AUTH;
    public static String LASTEDCACHE_HOST;
    public static int LASTEDCACHE_PORT;
    public static String SYSTEM_MAINTAIN_TEXT;
    public static String MAINTAIN_SECRET_KEY;
    public static String SYSTEM_BILLINGFORM_TITLE;
    public static String SYSTEM_PUBLIC_PATH;
    public static List<String> SYSTEM_MAINTAIN_ALOWS_USER;
    public static String SCRIBE_HOST;
    public static int SCRIBE_PORT;
    public static String MOBILE_TOUCH_CREDITS_URL;
    public static String MOBILE_WAP_CREDITS_URL;
    
    public static String BALANCE_HOST;
    public static int BALANCE_PORT;
//    public static String MOBILE_TOUCH_PAY_URL;
//    public static String MOBILE_WAP_PAY_URL;

    static {
        try {

            //begin set all "key=value" from ini file to configuration object
            PAYMENT_GATEWAY_HOST = Config.getParam("payment_gateway", "host");
            PAYMENT_GATEWAY_PORT = Integer.parseInt(Config.getParam("payment_gateway", "port"));
            
            BALANCE_HOST = Config.getParam("balance", "host");
            BALANCE_PORT = Integer.parseInt(Config.getParam("balance", "port"));

            IS_MAINTAIN_FLG = Integer.parseInt(Config.getParam("system", "maintain")) == 1;
            IS_CHECK_WHITELIST_FLG = Integer.parseInt(Config.getParam("system", "whitelist")) == 1;
            APPINFO_CACHESIZE = Integer.parseInt(Config.getParam("system", "appinfo-cachesize"));
            SYSTEM_REQUESTFORM_TITLE = Config.getParam("system", "requestForm-title");
            SYSTEM_URL = Config.getParam("system", "url");
            STATIC_URL = Config.getParam("system", "static-credits-url");
            SYSTEM_PUBLIC_PATH = Config.getParam("system", "public-path");
            SYSTEM_CREDITS_URL = Config.getParam("system", "credits-url");
            SYSTEM_BILLSTATUS_URL = Config.getParam("system", "billstatus-url");
            ADMIN_HOST = Config.getParam("admin", "host");
            ADMIN_PORT = Integer.parseInt(Config.getParam("admin", "port"));
            APPINFO_SLEEPTHREAD = Integer.parseInt(Config.getParam("system", "appinfo-sleepthread"));
            HUGELIST_READ_HOST = Config.getParam("hugelist", "read-host");
            HUGELIST_READ_PORT = Integer.parseInt(Config.getParam("hugelist", "read-port"));
            HUGELIST_READ_APPID = Integer.parseInt(Config.getParam("hugelist", "appid"));
            TOKENCACHE_HOST = Config.getParam("token_cache", "host");
            TOKENCACHE_PORT = Integer.parseInt(Config.getParam("token_cache", "port"));
            USERAPPCACHE_HOST = Config.getParam("userapp_cache", "host");
            USERAPPCACHE_PORT = Integer.parseInt(Config.getParam("userapp_cache", "port"));
            USERAPPCACHE_EXPIRE = Integer.parseInt(Config.getParam("userapp_cache", "expire"));

            SSO_HOST = Config.getParam("SSO", "host");
            SSO_PORT = Integer.parseInt(Config.getParam("SSO", "port"));
            STDPROFILE_HOST = Config.getParam("stdprofile2", "host");
            STDPROFILE_PORT = Integer.parseInt(Config.getParam("stdprofile2", "port"));
            STDPROFILE_SOURCE = Config.getParam("stdprofile2", "source");
            STDPROFILE_AUTH = Config.getParam("stdprofile2", "auth");

            TOKENBACKEND_HOST = Config.getParam("token_backend", "host");
            TOKENBACKEND_PORT = Integer.parseInt(Config.getParam("token_backend", "port"));
            LASTEDCACHE_HOST = Config.getParam("lastedCached", "host");
            LASTEDCACHE_PORT = Integer.parseInt(Config.getParam("lastedCached", "port"));
            SYSTEM_MAINTAIN_TEXT = Config.getParam("system", "maintain-text");
            MAINTAIN_SECRET_KEY = Config.getParam("system", "maintain-secret-key");

            SYSTEM_BILLINGFORM_TITLE = Config.getParam("system", "billingForm-title");

            SCRIBE_HOST = Config.getParam("scribe", "host");
            SCRIBE_PORT = Integer.parseInt(Config.getParam("scribe", "port"));

            System.setProperty("aicacheperiod", Configuration.APPINFO_SLEEPTHREAD + "");
            System.setProperty("appHost", Configuration.ADMIN_HOST + "");
            System.setProperty("appPort", Configuration.ADMIN_PORT + "");


            SYSTEM_MAINTAIN_ALOWS_USER = new ArrayList<String>();
            String allows = Config.getParam("system", "user-allows");
            StringTokenizer token = new StringTokenizer(allows, ",");
            while (token.hasMoreTokens()) {
                SYSTEM_MAINTAIN_ALOWS_USER.add(token.nextToken());
            }
            MOBILE_TOUCH_CREDITS_URL = Config.getParam("system", "mobile-touch-credits-url");
            MOBILE_WAP_CREDITS_URL = Config.getParam("system", "mobile-wap-credits-url");
          //  MOBILE_TOUCH_PAY_URL = Config.getParam("system", "mobile-touch-pay-url");
           // MOBILE_WAP_PAY_URL = Config.getParam("system", "mobile-wap-pay-url");

        } catch (Exception e) {
            logger_.error("Fatal error: " + LogUtil.stackTrace(e));
            System.err.println("Bad configuration; unable to start server");
            System.exit(1);
        }
    }
   
    public static void init() {
    }
}
