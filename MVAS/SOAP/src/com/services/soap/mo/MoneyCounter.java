package com.services.soap.mo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MoneyCounter {
	private BlockingQueue<MOMessage>  queueMoneyCounter = new ArrayBlockingQueue<MOMessage>(Spam.SPAM_MAX_SEND);

	public MoneyCounter() {
	}
	
	public final int getCount() {
		return queueMoneyCounter.size();
	}
	
	public final boolean enqueueMOMessage(MOMessage mOMessage) {
		return queueMoneyCounter.add(mOMessage);
	}
	
	public final MOMessage dequeueMOMessage() {
		return queueMoneyCounter.poll();
	}
	
	public final MOMessage getFirstMOMessage() {
		return queueMoneyCounter.peek();
	}	
}
