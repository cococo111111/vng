package system;

import services.ZooKeeperZMonitor;
import com.vng.jcore.common.Config;
import com.vng.jcore.common.LogUtil;
import java.io.File;
import org.apache.log4j.Logger;

public class ServiceDaemon {

    private static final Logger logger_ = Logger.getLogger(ServiceDaemon.class);


    public static void main(String[] args) throws Exception {
        LogUtil.init();
        String pidFile = System.getProperty("pidfile");

        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }
            new ZooKeeperZMonitor(Config.getParam("zookeeper", "address")).run();
        } catch (Throwable e) {
            String msg = "Exception encountered during startup.";
            logger_.error(msg, e);
            logger_.error("Uncaught exception: " + e.getMessage());
            System.exit(3);
        }
    }

}
