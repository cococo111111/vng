package com.services.soap.mo;

import java.text.SimpleDateFormat;

public class SOAPConstants {
	public static  int MAX_RETRIES = 10;
	public static  int RETRIES_TIME = 1;
	public static  String DATE_TIME = "dd/MM/yyyy HH:mm:ss";
	
	public static String convertTimestampToString(java.sql.Timestamp timestamp, String pattern) {
		return new SimpleDateFormat(pattern).format(timestamp);	
	}
    public static java.util.Calendar convertTimestampToCalendar(java.sql.Timestamp timestamp) {
        java.util.Calendar calendar = null;
        if (timestamp != null) {
            calendar = java.util.Calendar.getInstance();            
            calendar.setTimeInMillis(timestamp.getTime());
        }
        return calendar;
    }
    
	
}
