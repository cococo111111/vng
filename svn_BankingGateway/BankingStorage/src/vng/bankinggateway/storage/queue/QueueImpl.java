package vng.bankinggateway.storage.queue;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ArrayBlockingQueue;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import vng.bankinggateway.model.util.Config;

import vng.bankinggateway.storage.queue.cmd.Command;
import vng.bankinggateway.util.TimedStatsDeque;

public class QueueImpl implements Queue, QueueImplMBean {

    private Logger logger_ = Logger.getLogger("appActions");
    ArrayBlockingQueue<Command> clQueue;
    private volatile TimedStatsDeque queuePutStats = new TimedStatsDeque(60000);// 1
    private volatile TimedStatsDeque queueTakeStats = new TimedStatsDeque(60000);// 1
    private volatile long totalTake;

    public QueueImpl() {
        clQueue = new ArrayBlockingQueue<Command>(Config.maxQueueSize);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String mbeanName = "vng.zingme.payment.storage" + " :type=StorageQueue";
        try {
            mbs.registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            logger_.error("Register storage queue bean error: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean put(Command cmd) {
        long startTime = System.currentTimeMillis();
        boolean ret = false;
        try {
            clQueue.put(cmd);
            ret = true;
        } catch (InterruptedException e) {
            logger_.error("Uncaught exception: " + e.getMessage());
        }
        queuePutStats.add(System.currentTimeMillis() - startTime);
        return ret;
    }

    @Override
    public Command take() {
        long startTime = System.currentTimeMillis();
        Command cmd = null;
        try {
            cmd = clQueue.take();
            totalTake++;
        } catch (InterruptedException e) {
            logger_.error("Uncaught exception: " + e.getMessage());
        }
        queueTakeStats.add(System.currentTimeMillis() - startTime);
        return cmd;

    }

    @Override
    public int getTotalItem() {
        return clQueue.size();
    }

    @Override
    public long getQueuePutInMinute() {
        return queuePutStats.size();
    }

    @Override
    public long getQueueTakeInMinute() {
        return queueTakeStats.size();
    }

    @Override
    public long getTotalTake() {
        return totalTake;
    }
}
