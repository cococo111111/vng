package vng.zingme.queue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import vng.zingme.mep_bo.thrift.T_Account;
//import vng.zingme.mep_bo.thrift.T_Invoice;
//import vng.zingme.mep_bo.thrift.T_Order;
//import vng.zingme.mep_bo.thrift.T_Response;
//import vng.zingme.mep_bo.thrift.T_Transaction;
//Toan Aug 5, 2010 No need to implement queue
//public class QueueManager implements Queue {
public class QueueManager{
	private static final Lock createLock_ = new ReentrantLock();
	private static QueueManager instances_ = null;
	private int numWorker = 10;
	private  Queue queue;// = new QueueImpl();
	
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
	private QueueManager(){
		queue = new QueueImpl();
	}
	public void process() {
		for (int i = 0; i < numWorker; i++) {
			QueueWorker qw = new QueueWorker(queue);
			new Thread(qw).start();
		}
	}
	
	public void enqueue(Command cmd){
		queue.put(cmd);
	}
	public void setNumWorker(int num){
		numWorker = num;
	}
	
}
