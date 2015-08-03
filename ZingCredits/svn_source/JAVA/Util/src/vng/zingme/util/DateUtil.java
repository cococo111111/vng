package vng.zingme.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String formatDate(Date date, String pattern){
		String str = "";
		DateFormat formater = new SimpleDateFormat(pattern);
		str = formater.format(date);
		return str;
	}
	public static Date parse(String str, String pattern){
		try {
			DateFormat formater = new SimpleDateFormat(pattern);
			return formater.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
