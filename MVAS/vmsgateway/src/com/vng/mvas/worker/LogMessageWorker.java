package com.vng.mvas.worker;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.vng.mvas.models.Sending;
import com.vng.mvas.utils.DBUtils;

/**
 * 
 * @author root
 */
public class LogMessageWorker implements IWorkerRunable {

	private static Logger logger = Logger.getLogger(LogMessageWorker.class);
	private boolean stoped = false;
	private ArrayBlockingQueue<Sending> workerQueue = null;

	public LogMessageWorker(ArrayBlockingQueue<Sending> queueIn) {
		int queuesize = Integer.parseInt(System.getProperty("queuesize",
				"102400"));
		workerQueue = new ArrayBlockingQueue<Sending>(queuesize, true);
		workerQueue.addAll(queueIn);
	}

	public ArrayBlockingQueue<Sending> getQueue() {
		return workerQueue;

	}

	@Override
	public void stop() {
		stoped = true;
	}

	@Override
	public void run() {
		while (!stoped) {
			try {
				// Thread.sleep(100000L);
				Sending sending = workerQueue.take();

				int logRs = DBUtils.logSending(sending);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
	}

	@Override
	public void pushJob(Sending obj) {
		try {
			if (workerQueue != null) {
				workerQueue.put(obj);
			}
		} catch (InterruptedException ex) {
			logger.warn(ex.getMessage());
		}
	}

}
