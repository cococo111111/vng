/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.service;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.log4j.Logger;

/**
 *
 * @author nhutnh@vng.com.vn
 */
public class ServiceMonitor implements ServiceMonitorMBean {

    private static final Lock createLock_ = new ReentrantLock();
    private static ServiceMonitor instances_ = null;
    private static final Logger logger_ = Logger.getLogger(ServiceMonitor.class);
    private final LatencyTracker takeStats = new LatencyTracker();
    private volatile long totalGetUser = 0;
    private volatile long totalGetSum = 0;
    private volatile long totalAddMoney = 0;
    private volatile long totalGetMoney = 0;
    private final DecimalFormat df = new DecimalFormat("#.###");

    public static ServiceMonitor getInstance() {
        if (instances_ == null) {
            createLock_.lock();
            try {
                if (instances_ == null) {
                    instances_ = new ServiceMonitor();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return instances_;
    }

    public ServiceMonitor() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String mbeanName = "vng.zingme.credits" + ":type=paymentservice";
        try {
            mbs.registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addNano(long nano) {
        this.takeStats.addNano(nano);
    }

    public long getTotalReadLatencyMicros() {
        return takeStats.getTotalLatencyMicros();
    }

    public long getReadOperationPerSecond() {
        return takeStats.getTotalOpsPerSec();
    }

    @Override
    public long getReadOperations() {
        return takeStats.getOpCount();
    }

    public String getTotalReadLatencyAvg() {
        return df.format(takeStats.getAvgLatency());
    }

    public String getRecentLatencyMicros() {
        return df.format(takeStats.getRecentLatencyMicros());
    }

    @Override
    public long getTotalGetUser() {
        return this.totalGetUser;
    }

    @Override
    public long getTotalGetSum() {
        return this.totalGetSum;
    }

    @Override
    public long getTotalAddMoney() {
        return this.totalAddMoney;
    }

    @Override
    public long getTotalGetMoney() {
        return totalGetMoney;
    }

    public void incTotalGetUser() {
        totalGetUser++;
    }

    public void incTotalGetSum() {
        totalGetSum++;
    }

    public void incTotalAddMoney() {
        totalAddMoney++;
    }

    public void incTotalGetMoney() {
        totalGetMoney++;
    }
}
