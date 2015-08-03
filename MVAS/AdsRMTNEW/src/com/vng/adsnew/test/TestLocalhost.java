package com.vng.adsnew.test;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.axis.encoding.Base64;

import com.sun.tools.javac.util.List;
import com.vng.adsnew.util.Hasher;
import com.vng.adsnew.ws.AdsRMTNEW;
import com.vng.adsnew.ws.AdsRMTNEWService;
import com.vng.adsnew.ws.AdsRMTNEWServiceLocator;

public class TestLocalhost {
	public static void main(String[] args) {
		AdsRMTNEWService zingReceiverMTService = new AdsRMTNEWServiceLocator();
		Long timestart = Calendar.getInstance().getTimeInMillis();
		
		try {
			
			
			
			AdsRMTNEW receiverMO = zingReceiverMTService.getAdsRMT(new URL(
					"http://10.199.38.101/axis/services/AdsRMTNEW"));

			String message = "test tin nhan ssttool";
			String RequestID = "201464546582";
			String UserID = "84902623086";
			// String UserID = "841292135137";
			String serviceID = "6769";
			String key = "SSTOOLMT";
			String secretKey = "@123$456";

			String tempmd5 = RequestID + UserID + serviceID + key + message
					+ secretKey;

			String sig = MD5(tempmd5);

			/*
			 * String sig = authenticate(RequestID, UserID, serviceID, key,
			 * message, secretKey);/
			 */

			message = Base64.encode(message.getBytes());

			System.out.println(receiverMO.mtReceiver(RequestID, UserID,
					serviceID, key, message, sig));

			
			

		} catch (Exception e) {
			e.printStackTrace();
			Long timeend2 = Calendar.getInstance().getTimeInMillis();
			System.out.println(timeend2);
			Long timesend = timeend2 - timestart;
			System.out.println(timesend);
		}
	}

	
	
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString().toLowerCase();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	private static String authenticate(String requestID, String userID,
			String serviceID, String commandCode, String message,
			String secretKey) {
		String sig = null;
		try {
			sig = Hasher.getInstance("MD5").hash(
					requestID + userID + serviceID + commandCode + message
							+ secretKey);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return sig.toLowerCase();
	}

	public static Boolean CheckOverMoney(String userID, String serviceID)
			throws Exception {
		Boolean result_check = true;
		Connection conn = null;
		PreparedStatement preStmt = null;
		String strsql = null;
		ResultSet rs = null;
		Integer moneycheck = 0;
		Integer moneyadd = GetMoneyMsg(serviceID);
		String telco = null;
		try {
			if (conn == null)
				conn = getConnection();
			
			strsql = "select service_id, user_id from sms_receive_log201309  where user_id = '"
					+ userID
					+ "' and date(current_timestamp) = date(receive_date)";
			preStmt = conn.prepareStatement(strsql, 1004, 1008);
			if (preStmt.execute()) {
				for (rs = preStmt.getResultSet(); rs.next();) 
				{
					moneycheck += GetMoneyMsg(rs.getString(1));
					telco	= rs.getString(2);
				}
				if("VMS".equals(telco))
				{
					if(moneycheck+moneyadd > 300000)
					{
						result_check = false;
					}
				}
				else
				{
					if(moneycheck+moneyadd > 150000)
					{
						result_check = false;
					}
				}
				System.out.println(moneycheck+moneyadd);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		}
		finally {
			try {
				
				preStmt.close();
				conn.close();
			} catch (SQLException ex) {
				//Logger.error("DBTools", "Error:" + ex.toString());
			}
		}
		return result_check;
	}
	public static Connection getConnection() {
		String url = "jdbc:mysql://10.199.38.102:3306/";
		String dbName = "smsgw?useUnicode=true&characterEncoding=utf8";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "123456";
	    Connection conn = null;

	    try
	    {
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url + dbName, userName, password);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        conn = null;
	    }

	    return conn;
	}
	public static Integer GetMoneyMsg(String serviceID) {
		Integer money = 0;
		if("6069".equals(serviceID))
		{
			money = 500;
		}
		else if ("6169".equals(serviceID)) {
			money = 1000;
		}
		else if ("6269".equals(serviceID)) {
			money = 2000;
		}
		else if ("6369".equals(serviceID)) {
			money = 3000;
		}
		else if ("6469".equals(serviceID)) {
			money = 4000;
		}
		else if ("6569".equals(serviceID)) {
			money = 5000;
		}
		else if ("6669".equals(serviceID)) {
			money = 10000;
		}
		else if ("6769".equals(serviceID)) {
			money = 15000;
		}
		return money;
	}
	public static void cleanup(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			//Logger.error("cleanup Connection" + e.toString());
		} catch (Exception e) {
			//Logger.error("cleanup Connection,PreparedStatement" + e.toString());
		}
	}
}
