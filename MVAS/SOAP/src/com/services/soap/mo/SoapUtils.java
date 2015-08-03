package com.services.soap.mo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.MsgObject;

public class SoapUtils {
	private static final String className = "com.services.soap.mo.SoapUtils";
	public static boolean isDigits(String number){
		boolean Ok = true;
		
		String digits = "0123456789";
		if (number != null && number.length() > 0){
			for (int i = 0; i < number.length(); i++) {
				if (digits.indexOf(number.charAt(i)) == -1){
					Ok = false;
					break;
				}
			}
		}else{
			Ok = false;
		}
		
		return Ok;
	}
	
	public static String searchReplace(String data, String find, String replace) {
		StringBuffer sb = new StringBuffer();
		int a = 0, b;
		int findLength = find.length();
		if (data == null || find == null || replace == null) {
			return data;
		}
		while ((b = data.indexOf(find, a)) != -1) {
			sb.append(data.substring(a, b));
			sb.append(replace);
			a = b + findLength;
		}
		if (a < data.length()) {
			sb.append(data.substring(a));
		}
		return sb.toString();
	}	
	
	public static int parseInt(String valueNumber) {
			try {
				return Integer.parseInt(valueNumber);
			} catch (NumberFormatException e) {
				return 0;
			}
	}	
	
	public static String convertToStringIfNull(Object valueObject, String defaultValue) {
		if (valueObject != null ){
			return valueObject.toString();
		}else{
			return defaultValue;
		}
	}	
	public synchronized static boolean add2MOLog(MsgObject msgObject, String returnCode, int cp_ID, int isSentMT) {
		boolean OK = true;
		Util.logger.info(className + ".add2MOLog()");
		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(new Date());		
		String tablename = "vng_mo_log" + yyyyMM;		
		String sSQLInsert = "insert into " + tablename
							+ "(MO_ID,RETURN_CODE,USER_ID,MOBILE_OPERATOR,SERVICE_ID,MESSAGE,CP_ID,REQUEST_ID, IS_SENT_MT, receive_date)"
							+ " values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			ps = connection.prepareStatement(sSQLInsert);
			ps.setInt(1, msgObject.getMo_id());
			ps.setString(2, returnCode);
			ps.setString(3, msgObject.getUserid());
			ps.setString(4, msgObject.getMobileoperator());
			ps.setString(5, msgObject.getServiceid());
			ps.setString(6, msgObject.getUsertext());
			ps.setInt(7, cp_ID);
			ps.setBigDecimal(8, msgObject.getRequestid());
			ps.setInt(9,isSentMT);
			ps.setTimestamp(10, msgObject.getTTimes());
			
			if (ps.executeUpdate() < 1) {
				Util.logger.error(className + ".add2MOLog():" + msgObject.getUserid() 
									+ ":" + msgObject.getUsertext() + ":ps.executeUpdate failed");
				OK = false;
			}
			ps.close();
		} catch (SQLException e) {
			Util.logger.error(className + ".add2MOLog():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error add row into tablename: "+ tablename +" error:" + e.toString());
			OK = false;
		} catch (Exception e) {
			Util.logger.error(className + ".add2MOLog():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error add row into tablename: "+ tablename +" error:" + e.toString());
			OK = false;
		}

		finally {
			dbpool.cleanup(connection);
		}
		
		return OK;
	}	
	
	
	
}
