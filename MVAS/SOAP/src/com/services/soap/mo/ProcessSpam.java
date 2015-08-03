package com.services.soap.mo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.MsgObject;

public class ProcessSpam {
	
	private static int SPAM_DURATION_CHECK = 5 * 60 * 1000;
	private static int SPAM_MAX_SEND = 3;
	private static String SPAM_MESSAGE_TEXT = "Tin nhan SPAM. Ban khong duoc nhan qua 3 tin trong vong 5 phut.";
	private static int SPAM_MESSAGE_TYPE = 2;

	private static final String CACHED_THREE_MESSAGES_IN_FIVE_MINUTES = "cachedThreeMessagesInFiveMinutes";
	private static final String CACHED_FIVE_MESSAGES_IN_TEN_MINUTES = "cachedFiveMessagesInTenMinutes";
	private static final String CACHED_THIRTY_MESSAGES_IN_ONE_HOUR = "cachedThirtyMessagesInOneHour";
	private static final String CACHED_TEN_MESSAGES_IN_ONE_HOUR = "cachedTenMessagesInOneHour";
	private static final String CACHED_THREE_MESSAGES_IN_THIRTY_MINUTES = "cachedThreeMessagesInThirtyMinutes";
	
	private static ProcessSpam instance = null;
	
	private Map<String, MessageCounter> cachedThreeMessagesInFiveMinutes =  Collections.synchronizedMap(new HashMap<String, MessageCounter>());
	private Map<String, MessageCounter> cachedFiveMessagesInTenMinutes =  Collections.synchronizedMap(new HashMap<String, MessageCounter>());
	private Map<String, MessageCounter> cachedThirtyMessagesInOneHour =  Collections.synchronizedMap(new HashMap<String, MessageCounter>());
	private Map<String, MessageCounter> cachedTenMessagesInOneHour =  Collections.synchronizedMap(new HashMap<String, MessageCounter>());
	private Map<String, MessageCounter> cachedThreeMessagesInThirtyMinutes =  Collections.synchronizedMap(new HashMap<String, MessageCounter>());
	
	
	private ProcessSpam(){
		
	}
	
	public static ProcessSpam getInstance(){
		if (instance == null){
			SPAM_DURATION_CHECK = new Integer(Constants._prop.getProperty("SPAM_DURATION_CHECK")).intValue();
			SPAM_MAX_SEND = new Integer(Constants._prop.getProperty("SPAM_MAX_SEND")).intValue();
			SPAM_MESSAGE_TEXT = Constants._prop.getProperty("SPAM_MESSAGE_TEXT");
			SPAM_MESSAGE_TYPE = new Integer(Constants._prop.getProperty("SPAM_MESSAGE_TYPE")).intValue();
			instance = new ProcessSpam();
		}
		return instance;
	}
	
	private boolean isSpamTime(String userId, long now, int spamMaxSend, int spamDurationCheck, String cachedName){
		MessageCounter messageCounter = ProcessSpam.getInstance().getMessageCounter(userId, cachedName);
		MOMessage mOMessage = null;
		boolean isSpam = false;
		if (messageCounter != null){
			if (messageCounter.getCount() >= spamMaxSend){
				if ( (now - messageCounter.getFirstMOMessage().getTime())  <= spamDurationCheck ){
					isSpam = true;
				}else{
					messageCounter.dequeueMOMessage();
					mOMessage = new MOMessage();
					mOMessage.setTime(now);
					messageCounter.enqueueMOMessage(mOMessage);
				}
			}else{
				mOMessage = new MOMessage();
				mOMessage.setTime(now);
				messageCounter.enqueueMOMessage(mOMessage);
			}
			
		}else{
			messageCounter = new MessageCounter(spamMaxSend);
			mOMessage = new MOMessage();
			mOMessage.setTime(now);
			messageCounter.enqueueMOMessage(mOMessage);
		}
		ProcessSpam.getInstance().putMessageCounter(userId, messageCounter, cachedName);
		return isSpam;
	}
	
	private boolean isSpamThreeMessagesInFiveMinutes(String userId, long now, int spamMaxSend, int spamDurationCheck){
		return isSpamTime(userId, now, spamMaxSend, spamDurationCheck, CACHED_THREE_MESSAGES_IN_FIVE_MINUTES);		
	}

	private boolean isSpamFiveMessagesInTenMinutes(String userId, long now, int spamMaxSend, int spamDurationCheck){
		return isSpamTime(userId, now, spamMaxSend, spamDurationCheck, CACHED_FIVE_MESSAGES_IN_TEN_MINUTES);		
	}
	
	private boolean isSpamThirtyMessagesInOneHour(String userId, long now, int spamMaxSend, int spamDurationCheck){
		return isSpamTime(userId, now, spamMaxSend, spamDurationCheck, CACHED_THIRTY_MESSAGES_IN_ONE_HOUR);		
	}
	
	private boolean isSpamTenMessagesInOneHour(String userId, long now, int spamMaxSend, int spamDurationCheck){
		return isSpamTime(userId, now, spamMaxSend, spamDurationCheck, CACHED_TEN_MESSAGES_IN_ONE_HOUR);		
	}
	
	private boolean isSpamThreeMessagesInThirtyMinutes(String userId, long now, int spamMaxSend, int spamDurationCheck){
		return isSpamTime(userId, now, spamMaxSend, spamDurationCheck, CACHED_THREE_MESSAGES_IN_THIRTY_MINUTES);		
	}
	
	private boolean overMaxMoneyAllow(MsgObject msgObject){
		return ServicePrice.getInstance().overMaxMoneyAllow(msgObject);	
	}
	
	public boolean processing(Collection messages, MsgObject msgObject){
		Util.logger.info("Calling " + getClass().getName() + ".processing() check spam.");
		
		String mobileOperator = msgObject.getMobileoperator();
		long now = System.currentTimeMillis();
		
		if ("VMS".equalsIgnoreCase(mobileOperator)){
			if (isSpamThreeMessagesInFiveMinutes(msgObject.getUserid(), now, SPAM_MAX_SEND, SPAM_DURATION_CHECK)){
				addMessageSpam(messages, msgObject, SPAM_MESSAGE_TEXT, SPAM_MESSAGE_TYPE);
				return true;
			}else if (isSpamFiveMessagesInTenMinutes(msgObject.getUserid(), now, SPAM_MAX_SEND, SPAM_DURATION_CHECK)){
				addMessageSpam(messages, msgObject, SPAM_MESSAGE_TEXT, SPAM_MESSAGE_TYPE);
				return true;
			}else if (isSpamThirtyMessagesInOneHour(msgObject.getUserid(), now, SPAM_MAX_SEND, SPAM_DURATION_CHECK)){
				addMessageSpam(messages, msgObject, SPAM_MESSAGE_TEXT, SPAM_MESSAGE_TYPE);
				return true;
			}else if (overMaxMoneyAllow(msgObject)){
				addMessageSpam(messages, msgObject, SPAM_MESSAGE_TEXT, SPAM_MESSAGE_TYPE);
				return true;
			}
		}
		
		return false;
	}
	
	private void addMessageSpam(Collection messages, MsgObject msgObject, String messageText, int messageType){
		msgObject.setUsertext(messageText);
		msgObject.setMsgtype(messageType);
		messages.add(new MsgObject(msgObject));		
	}
	
	private void putMessageCounter(String userID, MessageCounter messageCounter, String cachedName) {
		
		if (CACHED_THREE_MESSAGES_IN_FIVE_MINUTES.equalsIgnoreCase(cachedName)){
			cachedThreeMessagesInFiveMinutes.put(userID, messageCounter);			
		}else if (CACHED_FIVE_MESSAGES_IN_TEN_MINUTES.equalsIgnoreCase(cachedName)){
			cachedFiveMessagesInTenMinutes.put(userID, messageCounter);						
		}else if (CACHED_THIRTY_MESSAGES_IN_ONE_HOUR.equalsIgnoreCase(cachedName)){
			cachedThirtyMessagesInOneHour.put(userID, messageCounter);						
		}else if (CACHED_TEN_MESSAGES_IN_ONE_HOUR.equalsIgnoreCase(cachedName)){
			cachedTenMessagesInOneHour.put(userID, messageCounter);						
		}else if (CACHED_THREE_MESSAGES_IN_THIRTY_MINUTES.equalsIgnoreCase(cachedName)){
			cachedThreeMessagesInThirtyMinutes.put(userID, messageCounter);						
		}		
	}
	
	private MessageCounter getMessageCounter(String userID, String cachedName) {
		
		if (CACHED_THREE_MESSAGES_IN_FIVE_MINUTES.equalsIgnoreCase(cachedName)){
			return cachedThreeMessagesInFiveMinutes.get(userID);			
		}else if (CACHED_FIVE_MESSAGES_IN_TEN_MINUTES.equalsIgnoreCase(cachedName)){
			return cachedFiveMessagesInTenMinutes.get(userID);						
		}else if (CACHED_THIRTY_MESSAGES_IN_ONE_HOUR.equalsIgnoreCase(cachedName)){
			return cachedThirtyMessagesInOneHour.get(userID);						
		}else if (CACHED_TEN_MESSAGES_IN_ONE_HOUR.equalsIgnoreCase(cachedName)){
			return cachedTenMessagesInOneHour.get(userID);						
		}else if (CACHED_THREE_MESSAGES_IN_THIRTY_MINUTES.equalsIgnoreCase(cachedName)){
			return cachedThreeMessagesInThirtyMinutes.get(userID);						
		}
		
		return null;		
	}
	
}
