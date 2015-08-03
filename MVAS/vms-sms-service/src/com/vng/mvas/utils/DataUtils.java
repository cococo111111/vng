package com.vng.mvas.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	private static String requestID_replace = System.getProperty("requestID_replace");
	private static String MOBILE_OPERATOR = System.getProperty("MOBILE_OPERATOR");
	
	public static String getUSER_ID(String senderAddress) {
		return senderAddress.replace("tel:", "");
	}
	
	public static String getSERVICE_ID(String smsServiceActivationNumber) {
		return smsServiceActivationNumber.replace("tel:", "");
	}
	
	public static String getMOBILE_OPERATOR(String senderAddress) {
		return MOBILE_OPERATOR;
	}
	
	public static String getCOMMAND_CODE(String message) {
		return message.split(" ")[0];
	}

	public static String getREQUEST_ID() {
		Calendar cal = Calendar.getInstance();
		Date date =cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timestamp=df.format(date);
		
		return timestamp.replaceFirst("20", requestID_replace.trim());
	}
}
