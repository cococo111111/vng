package vng.bankinggateway.storage.queue;

import org.apache.log4j.Logger;

import vng.bankinggateway.storage.queue.cmd.Command;

public class QueueWorker implements Runnable {

    private Queue queue;

    public QueueWorker(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Command command = null;
            command = queue.take();
            if (command != null) {
                command.execute();
            }
        }
    }
}
