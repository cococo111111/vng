package vng.zingme.queue;

import org.apache.log4j.Logger;


public class QueueWorker implements Runnable {
//	private static Logger logger = Logger.getLogger(QueueWorker.class);

	// private BlockingQueue<Command> queue;
	private Queue queue;

	public QueueWorker(Queue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
//		logger.debug("QueueWorker: in step 1");
		while (true) {
			Command command = null;
			command = queue.take();
			if (command != null) {
//				logger.debug("QueueWorker: in step 2");
				command.execute();
			}
		}
	}

}
