package configcenter;

import com.vng.jcore.common.LogUtil;
import org.apache.log4j.Logger;

/**
 *
 * @author taitt
 */
public class DemoMain implements Runnable {

    private static final Logger _logger = Logger.getLogger(DemoMain.class);
    private static final String connectString = "127.0.0.1:2181";
    private static ZKClient zkCli;

    public static void main(String[] args) throws Exception {
        LogUtil.init();
        DemoMain demoMain = new DemoMain();
        demoMain.monitorConfig();
    }

    public void monitorConfig() {
        try {
            zkCli = new ZKClient(connectString);
            if (zkCli != null) {
                zkCli.addMonitor(new ServiceConfigMonitor());
                zkCli.run();
            } else {
                _logger.error("Error when create ZKClient object.");
            }
        } catch (Exception e) {
            _logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        DemoMain demoMain = new DemoMain();
        demoMain.monitorConfig();
    }
}
