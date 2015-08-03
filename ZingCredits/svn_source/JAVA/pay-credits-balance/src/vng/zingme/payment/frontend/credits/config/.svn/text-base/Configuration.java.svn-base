package vng.zingme.payment.frontend.credits.config;

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

    private static Logger logger_ = Logger.getLogger(Configuration.class);
    public static String BALANCE_HOST;
    public static int BALANCE_PORT;
    public static boolean IS_MAINTAIN_FLG;
    public static boolean IS_CHECK_WHITELIST_FLG;
    public static int APPINFO_CACHESIZE;
    public static String ADMIN_HOST;
    public static int ADMIN_PORT;
    public static int APPINFO_SLEEPTHREAD;

    static {
        try {

            //begin set all "key=value" from ini file to configuration object
            BALANCE_HOST = Config.getParam("balance-cache", "host");
            BALANCE_PORT = Integer.parseInt(Config.getParam("balance-cache", "port"));

            IS_MAINTAIN_FLG = Integer.parseInt(Config.getParam("system", "port")) == 1;
            IS_CHECK_WHITELIST_FLG = Integer.parseInt(Config.getParam("system", "whitelist")) == 1;
            APPINFO_CACHESIZE = Integer.parseInt(Config.getParam("system", "appinfo-cachesize"));
            ADMIN_HOST = Config.getParam("admin", "host");
            ADMIN_PORT = Integer.parseInt(Config.getParam("admin", "port"));
            APPINFO_SLEEPTHREAD = Integer.parseInt(Config.getParam("system", "appinfo-sleepthread"));

            System.setProperty("aicacheperiod", Configuration.APPINFO_SLEEPTHREAD + "");
            System.setProperty("appHost", Configuration.ADMIN_HOST + "");
            System.setProperty("appPort", Configuration.ADMIN_PORT + "");
        } catch (Exception e) {
            logger_.error("Fatal error: " + LogUtil.stackTrace(e));
            System.err.println("Bad configuration; unable to start server");
            System.exit(1);
        }
    }

    public static void init() {
    }
}
