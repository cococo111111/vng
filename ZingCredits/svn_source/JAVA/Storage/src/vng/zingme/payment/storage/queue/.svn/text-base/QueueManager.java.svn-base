package vng.zingme.payment.storage.queue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vng.zingme.payment.storage.queue.cmd.Command;
import vng.zingme.payment.model.util.Config;

public class QueueManager {

    private static final Lock createLock_ = new ReentrantLock();
    private static QueueManager instances_ = null;
    private final Queue queue = new QueueImpl();

    ;

    protected QueueManager() {
        process();
    }

    public static QueueManager getInstance() {
        if (instances_ == null) {
            createLock_.lock();
            try {
                if (instances_ == null) {
                    instances_ = new QueueManager();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return instances_;
    }

    private void process() {
        for (int i = 0; i < Config.NumOfQueueWorker; i++) {
            QueueWorker qw = new QueueWorker(queue);
            new Thread(qw).start();
        }
    }

    public void put(Command cmd) {
        queue.put(cmd);
    }

    public Command take() {
        return queue.take();
    }
}
