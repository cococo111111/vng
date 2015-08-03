package banking.gatewayweb.config;

import com.vng.jcore.common.Config;
import com.vng.jcore.common.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

/**
 * Load configuration file
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public class Configuration {

    private static final Logger logger_ = Logger.getLogger(Configuration.class);
    public static String SYSTEM_TITLE;
    public static String SYSTEM_URL;
    public static String BALANCE_HOST;
    public static int BALANCE_PORT;
    public static String STORAGE_HOST;
    public static int STORAGE_PORT;
    public static String ADMIN_HOST;
    public static int ADMIN_PORT;
    public static int APPINFO_SLEEPTHREAD;
    public static String HUGELIST_READ_HOST;
    public static int HUGELIST_READ_PORT;
    public static int HUGELIST_READ_APPID;
    public static String SSO_HOST;
    public static int SSO_PORT;
    public static String LASTEDCACHE_HOST;
    public static int LASTEDCACHE_PORT;
    public static String SYSTEM_MAINTAIN_TEXT;
    public static boolean IS_MAINTAIN_FLG;
    public static String MAINTAIN_SECRET_KEY;
    public static boolean IS_CHECK_WHITELIST_FLG;
    public static String STDPROFILE_HOST;
    public static int STDPROFILE_PORT;
    public static String STDPROFILE_SOURCE;
    public static String STDPROFILE_AUTH;
    public static String SYSTEM_PUBLIC_PATH;
    public static String SYSTEM_ME_URL;
    public static String SYSTEM_APPNAME;
    public static List<String> SYSTEM_MAINTAIN_ALOWS_USER;
	public static List<String> FEATURE_WHITE_LIST;
    public static String SYSTEM_SECRET_KEY;
    public static int SYSTEM_TIMEOUT;
    public static String MOBILE_TOUCH_CREDITS_URL;
    public static String MOBILE_WAP_CREDITS_URL;
    public static String NAPTHE_URL;
    public static String NAPATM_URL;
    public static String NAPATM_THANKYOU_URL;
    public static boolean NAPTHE_ENABLE;
    public static String MEMCACHE_APP_SORT_HOST;
    public static int MEMCACHE_APP_SORT_PORT;
    public static String CSS_VERSION;
    
    public static String SCHEDULER;
    
    static {
        try {
            //begin set all "key=value" from ini file to configuration object

            SYSTEM_URL = Config.getParam("system", "credits-url");
            SYSTEM_TITLE = Config.getParam("system", "credits-title");
            SYSTEM_ME_URL = Config.getParam("system", "me-url");
            SYSTEM_APPNAME = Config.getParam("system", "credits-appname");
            SCHEDULER = Config.getParam("system", "scheduler");
            
            
            BALANCE_HOST = Config.getParam("balance", "host");
            BALANCE_PORT = Integer.parseInt(Config.getParam("balance", "port"));

            ADMIN_HOST = Config.getParam("admin", "host");
            ADMIN_PORT = Integer.parseInt(Config.getParam("admin", "port"));
            STORAGE_HOST = Config.getParam("storage", "host");
            STORAGE_PORT = Integer.parseInt(Config.getParam("storage", "port"));


            HUGELIST_READ_HOST = Config.getParam("hugelist", "read-host");
            HUGELIST_READ_PORT = Integer.parseInt(Config.getParam("hugelist", "read-port"));
            HUGELIST_READ_APPID = Integer.parseInt(Config.getParam("hugelist", "appid"));

            SSO_HOST = Config.getParam("SSO", "host");
            SSO_PORT = Integer.parseInt(Config.getParam("SSO", "port"));

            LASTEDCACHE_HOST = Config.getParam("lastedCached", "host");
            LASTEDCACHE_PORT = Integer.parseInt(Config.getParam("lastedCached", "port"));

            SYSTEM_MAINTAIN_TEXT = Config.getParam("system", "maintain-text");
            IS_MAINTAIN_FLG = Integer.parseInt(Config.getParam("system", "maintain")) == 1;
            MAINTAIN_SECRET_KEY = Config.getParam("system", "maintain-secret-key");
            IS_CHECK_WHITELIST_FLG = Integer.parseInt(Config.getParam("system", "whitelist")) == 1;
            APPINFO_SLEEPTHREAD = Integer.parseInt(Config.getParam("system", "appinfo-sleepthread"));
            SYSTEM_PUBLIC_PATH = Config.getParam("system", "public-path");

            STDPROFILE_HOST = Config.getParam("stdprofile2", "host");
            STDPROFILE_PORT = Integer.parseInt(Config.getParam("stdprofile2", "port"));
            STDPROFILE_SOURCE = Config.getParam("stdprofile2", "source");
            STDPROFILE_AUTH = Config.getParam("stdprofile2", "auth");

            System.setProperty("aicacheperiod", Configuration.APPINFO_SLEEPTHREAD + "");
            System.setProperty("appHost", Configuration.ADMIN_HOST + "");
            System.setProperty("appPort", Configuration.ADMIN_PORT + "");

            SYSTEM_MAINTAIN_ALOWS_USER = new ArrayList<String>();
            String allows = Config.getParam("system", "user-allows");
            StringTokenizer token = new StringTokenizer(allows, ",");
            while (token.hasMoreTokens()) {
                SYSTEM_MAINTAIN_ALOWS_USER.add(token.nextToken());
            }
			
			FEATURE_WHITE_LIST = new ArrayList<String>();
            String atmWhiteList = Config.getParam("system", "feature-white-list");
			token	= new StringTokenizer(atmWhiteList, ",");
            while (token.hasMoreTokens()) {
                FEATURE_WHITE_LIST.add(token.nextToken());
            }
			
            SYSTEM_SECRET_KEY = Config.getParam("system", "secret-key");
            SYSTEM_TIMEOUT = Integer.parseInt(Config.getParam("system", "timeout-key"));

            MOBILE_TOUCH_CREDITS_URL = Config.getParam("system", "mobile-touch-credits-url");
            MOBILE_WAP_CREDITS_URL = Config.getParam("system", "mobile-wap-credits-url");
            NAPTHE_URL = Config.getParam("system", "napthe-url");
			NAPATM_URL = Config.getParam("system", "napatm-url");
			NAPATM_THANKYOU_URL = Config.getParam("system", "napatm-thankyou-url");
            NAPTHE_ENABLE = Integer.parseInt(Config.getParam("system", "napthe-enable")) == 1;

            MEMCACHE_APP_SORT_HOST=Config.getParam("memcache-app-sort", "host");
            MEMCACHE_APP_SORT_PORT=Integer.parseInt(Config.getParam("memcache-app-sort", "port"));
            CSS_VERSION=Config.getParam("css-version", "credits");
        } catch (Exception e) {
            logger_.error("Fatal error: " + LogUtil.stackTrace(e));
            System.err.println("Bad configuration; unable to start server");
            System.exit(1);
        }
    }

    public static void init() {
    }
}
