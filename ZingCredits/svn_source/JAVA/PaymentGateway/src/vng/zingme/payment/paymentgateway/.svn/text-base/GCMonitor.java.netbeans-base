/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentgateway;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 *
 * @author loidc@vng.com.vn
 */
public class GCMonitor {

    private static Logger logger_ = Logger.getLogger("appActions");
    private static GCMonitor _instance = null;
    protected static ReentrantLock locker = new ReentrantLock();

    public static GCMonitor getInstance() {
        if (_instance == null) {
            locker.lock();
            try {
                if (_instance == null) {
                    _instance = new GCMonitor();
                }
            } finally {
                locker.unlock();
            }
        }
        return _instance;
    }

    private GCMonitor() {
        this.setUpThread();
    }

    private void setUpThread() {
        gcMonitorThread = new Thread(new GCMonitorRunable());
        gcMonitorThread.start();
    }

    protected static class GCMonitorRunable implements Runnable {

        private static long sleep = 0;
        private static long firstwait = 0;

        public GCMonitorRunable() {
            sleep = Integer.parseInt(System.getProperty("gcmonitorperiod", "86400000"));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            cal.set(Calendar.HOUR_OF_DAY, 2);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            firstwait = cal.getTime().getTime() - (new Date().getTime());
        }

        public void run() {

            try {
                Thread.sleep(firstwait);
            } catch (InterruptedException ex) {
                logger_.error("thread gcMonitor Exception: " + vng.zingme.util.LogUtil.stackTrace(ex));
            }

            while (true) {

                System.gc();
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException ex) {
                    logger_.error("thread gcMonitor Exception: " + vng.zingme.util.LogUtil.stackTrace(ex));
                }
            }
        }
    }
    private static Thread gcMonitorThread = null;
}
