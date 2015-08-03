package upzkclient;

import com.vng.jcore.common.Config;
import com.vng.jcore.common.LogUtil;
import java.io.File;
import org.apache.log4j.Logger;

public class ServiceDaemon {

    private static final Logger logger_ = Logger.getLogger(ServiceDaemon.class);

    public static void main(String[] args) throws Exception {
        LogUtil.init();
        String zkaddress = Config.getParam("upmonitor", "address");
        String zname  = System.getProperty("zname");
        String pidFile = System.getProperty("pidfile");
        if (!zname.matches("^/(.+)/(.+)")){
            logger_.error("Error: Incorrect zname format. (/$product/$service). Ex: /passport/zingid");
            System.exit(-1);
        }
        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }
            new ZooKeeperZClient(zname,zkaddress).run();
        } catch (Throwable e) {
            logger_.error("Uncaught exception: " + e.getMessage());
            System.exit(3);
        }
    }
}
