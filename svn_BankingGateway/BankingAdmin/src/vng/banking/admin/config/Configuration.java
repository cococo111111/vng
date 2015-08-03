package vng.banking.admin.config;

import com.vng.jcore.common.Config;
import com.vng.jcore.common.LogUtil;
import org.apache.log4j.Logger;

/**
 * Load configuration file
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public class Configuration {

    private static final Logger logger_ = Logger.getLogger(Configuration.class);
    public static String STORAGE_HOST;
    public static int STORAGE_PORT;
    public static String SYSTEM_PUBLIC_PATH;
    public static String SCHEDULER;
    
    public static String EMAIL_LIST;
    public static String EMAIL_HOST;
    public static int EMAIL_PORT;
    public static String EMAIL_SCHEDULER;
    public static int EMAIL_DURATION;
    
    static {
        try {
            //begin set all "key=value" from ini file to configuration object

            SYSTEM_PUBLIC_PATH = Config.getParam("system", "public-path");
            SCHEDULER = Config.getParam("system", "scheduler");
            
            EMAIL_LIST = Config.getParam("email", "notification_email_list");
            EMAIL_HOST = Config.getParam("email", "email_host");
            EMAIL_PORT = Integer.valueOf(Config.getParam("email", "email_port"));
            EMAIL_SCHEDULER = Config.getParam("email", "email_scheduler");
            EMAIL_DURATION = Integer.valueOf(Config.getParam("email", "duration"));
            
            STORAGE_HOST = Config.getParam("storage", "host");
            STORAGE_PORT = Integer.parseInt(Config.getParam("storage", "port"));
        } catch (Exception e) {
            logger_.error("Fatal error: " + LogUtil.stackTrace(e));
            System.err.println("Bad configuration; unable to start server");
            System.exit(1);
        }
    }

    public static void init() {
    }
}
