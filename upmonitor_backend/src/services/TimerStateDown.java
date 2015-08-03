package services;

import java.util.Timer;
import java.util.TimerTask;
import clients.StorageMySQLCli;
import com.vng.jcore.common.Config;
import java.text.SimpleDateFormat;

/**
 * Schedule a task that executes once every second.
 */
public class TimerStateDown {

    String znode;
    Timer timer;
    int level = Integer.parseInt(Config.getParam("alert", "max"));
    int interval = Integer.parseInt(Config.getParam("alert", "interval"));
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TimerStateDown.class);
    private static final SimpleDateFormat dateFormatFull = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    private static final NotificationCenter notify = new NotificationCenter();
    private static final StorageMySQLCli sqlCli = StorageMySQLCli.getInstance();

    public TimerStateDown(String znode) {
        this.znode = znode;
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, //initial delay
                interval * 1000); //subsequent rate
    }

    class RemindTask extends TimerTask {
        public void checkState(String znode) {
            int deletetime = sqlCli.getState(znode);
            if (deletetime > 0) {
                if (level == 1) {
                    notify.doNotify("Service " + znode + "'s DOWN. " + dateFormatFull.format(deletetime * 1000L) + "!", znode);
                    ZooKeeperZMonitor.lruCachedInt_NotifyNode.put(znode, 1, -1l);
                    timer.cancel();
                }
                logger.info("Warning level node " + znode + ": " + level);
                level--;
            } else {
                timer.cancel();
            }
        }

        @Override
        public void run() {
            checkState(znode);
        }
    }
}
