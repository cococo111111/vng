package vng.zingme.payment.util;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IDGen {
	private static final Lock createLock_ = new ReentrantLock();
	private static IDGen instances_;

	private static AtomicLong keyTrans;
	private static AtomicLong keyInv;
	private static AtomicLong keyAcc;

	public static IDGen getInstance() {
		if (instances_ == null) {
			IDGen.createLock_.lock();
			try {
				if (instances_ == null) {
					instances_ = new IDGen();
				}
			} finally {
				createLock_.unlock();
			}
		}
		return instances_;
	}

	private IDGen() {
	}

	public void initCommentId() {
		long initialValue = 0;

		// LOAD FROM FILE

		keyTrans = new AtomicLong(initialValue);
		keyInv = new AtomicLong(initialValue);
		keyAcc = new AtomicLong(initialValue);
	}

	public long getKeyTrans() {
		long id = keyTrans.incrementAndGet();

		// SAVE this to file

		return id;
	}

	public long getKeyInv() {
		long id = keyInv.incrementAndGet();

		// SAVE this to file

		return id;
	}
	
	public long getKeyAcc() {
		long id = keyAcc.incrementAndGet();

		// SAVE this to file

		return id;
	}
}
