/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.accountbalance;

import java.util.concurrent.ArrayBlockingQueue;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.worker.IWorkerRunable;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class ZKWriterWorker implements IWorkerRunable {

    private static ArrayBlockingQueue<T_Transaction> workerQueue = null;

    public ZKWriterWorker() {
        int _updateCacheQueueSize = Integer.parseInt(System.getProperty("zkwriterQueue", "4096"));
        workerQueue = new ArrayBlockingQueue<T_Transaction>(_updateCacheQueueSize, true);
    }

    public void run() {
        while (!stoped) {
            try {
                T_Transaction tx = workerQueue.take();
                if (tx != null) {
                    boolean res = false;
                    for (int i = 0; i < HOPE_COUNT && !res; ++i) {
                        try {
                            res = AccBalCachingManager.getInstance().writeToZookeeper(tx);
                        } catch (Exception ex) {
                            log.info(ex.getMessage());
                        }
                    }
                    if (!res) {
                        pushJob(tx);
                    }
                }
            } catch (InterruptedException ex) {
                log.warn(ex.getMessage());
            }

        }
    }

    public void stop() {
        stoped = true;
    }
    static boolean stoped = false;

    public void pushJob(Object obj) {
        try {
            workerQueue.put((T_Transaction) obj);
        } catch (InterruptedException ex) {
            log.info(ex.getMessage());
        }
    }
    private static final int HOPE_COUNT = 3;
    private static final Logger log = Logger.getLogger("appActions");
}
