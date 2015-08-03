package com.services.soap.mo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageCounter {
	
	private BlockingQueue<MOMessage>  queueMessageCounter = null;

	public MessageCounter(int spamMaxSend) {
		queueMessageCounter = new ArrayBlockingQueue<MOMessage>(spamMaxSend);
	}
	
	public final int getCount() {
		return queueMessageCounter.size();
	}
	
	public final boolean enqueueMOMessage(MOMessage mOMessage) {
		return queueMessageCounter.add(mOMessage);
	}
	
	public final MOMessage dequeueMOMessage() {
		return queueMessageCounter.poll();
	}
	
	public final MOMessage getFirstMOMessage() {
		return queueMessageCounter.peek();
	}	
}