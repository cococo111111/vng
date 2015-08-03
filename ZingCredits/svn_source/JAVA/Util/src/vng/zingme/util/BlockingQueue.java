package vng.zingme.util;

import java.util.LinkedList;

/**
 * implements a blocking queue 
 * @see ThreadPool
 * @since 1.1
 */
public class BlockingQueue {
    private LinkedList queue = new LinkedList();
    private boolean closed = false;
    private int consumers = 0;

    public static class Closed extends RuntimeException {

        private static final long serialVersionUID = 3404885702116373450L;

        public Closed() {
            super ("queue closed");
        }
    }

    public synchronized void enqueue (Object o) throws Closed {
        if (closed)
            throw new Closed();
        queue.addLast (o);
        notify();
    }
    public synchronized void requeue (Object o) throws Closed {
        if (closed)
            throw new Closed();
        queue.addFirst (o);
        notify();
    }

    public synchronized Object dequeue()
        throws InterruptedException, Closed
    {
        consumers++;
        try {
            while (queue.size() == 0) {
                wait();
                if (closed)
                    throw new Closed();
            }
        } finally {
            consumers--;
        }
        return queue.removeFirst();
    }

    public synchronized Object dequeue (long timeout)
        throws InterruptedException, Closed
    {
        if (timeout == 0)
            return dequeue ();

        consumers++;
        long maxTime = System.currentTimeMillis() + timeout;
        try {
            while (queue.size() == 0 && System.currentTimeMillis() < maxTime) {
                wait (timeout);
                if (closed)
                    throw new Closed();
            }
        } finally {
            consumers--;
        }
        return queue.size() > 0 ? queue.removeFirst() : null;
    }
    public synchronized void close() {
        closed = true;
        notifyAll();
    }
    public synchronized int consumerCount() {
        return consumers;
    }
    public synchronized boolean ready() {
        return !closed;
    }
    public synchronized int pending() {
        return queue.size();
    }
    public LinkedList getQueue () {
        return queue;
    }
    public void setQueue (LinkedList queue) {
        this.queue = queue;
    }
}

