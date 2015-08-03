package vng.bankinggateway.storage.queue;

public interface QueueImplMBean {

    public int getTotalItem();

    public long getTotalTake();

    public long getQueuePutInMinute();

    public long getQueueTakeInMinute();
}
