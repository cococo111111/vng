package com.services.soap.mo;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.DBPool;

public class Spam {

	private static Spam instance = null;

	/*
	 * private final String SPAM_DIRECTION_CHECK = "SPAM_DIRECTION_CHECK";
	 * private final String SPAM_MAX_SEND = "SPAM_DIRECTION_CHECK"; private
	 * final String SPAM_MESSAGE_TEXT = "SPAM_MESSAGE_TEXT"; private final
	 * String SPAM_MESSAGE_TYPE = "SPAM_MESSAGE_TYPE";
	 */
	public static int SPAM_DURATION_CHECK = 5 * 60 * 1000;
	public static int SPAM_MAX_SEND = 3;
	public static String SPAM_MESSAGE_TEXT = "Tin nhan SPAM. Ban khong duoc nhan qua 3 tin trong vong 5 phut.";
	public static int SPAM_MESSAGE_TYPE = 1;

	public static String OVER_MONEY = "Tin nhan SPAM. Ban khong duoc nap so tien vuot qua 150.000d/ngay. Vui long goi 1900561558 de duoc huong dan them.";
	public static String THREE_MESSAGES_IN_FIVE_MINUTES = "Tin nhan SPAM. Ban khong duoc nhan qua 3 tin trong vong 5 phut.";
	public static String FIVE_MESSAGES_IN_TEN_MINUTES = "Tin nhan SPAM. Ban khong duoc nhan qua 5 tin trong vong 10 phut.";
	public static String THIRTY_MESSAGES_IN_ONE_HOUR = "Tin nhan SPAM. Ban khong duoc nhan qua 30 tin trong vong 1 gio.";
	public static String THREE00_MESSAGES_IN_24_HOUR = "Tin nhan SPAM. Ban khong duoc nhan qua 300 tin trong vong 24 gio.";

	public static String MESSAGE_OVER_MONEY = "Tin nhan SPAM. Ban khong duoc nap so tien vuot qua 150.000d/ngay. Vui long goi 1900561558 de duoc huong dan them.";
	public static String MESSAGE_OVER_MONEY_VMS = "Tin nhan SPAM. Ban khong duoc nap so tien vuot qua 300.000d/ngay. Vui long goi 1900561558 de duoc huong dan them.";
	public static int MESSAGE_OVER_MONEY_TYPE = 2;

	private Map<String, MoneyCounter> cachedMoneys = Collections
			.synchronizedMap(new HashMap<String, MoneyCounter>());

	public Spam() {
	}

	public static Spam getInstance() {
		if (instance == null) {
			SPAM_DURATION_CHECK = new Integer(Constants._prop
					.getProperty("SPAM_DURATION_CHECK")).intValue();
			SPAM_MAX_SEND = new Integer(Constants._prop
					.getProperty("SPAM_MAX_SEND")).intValue();
			SPAM_MESSAGE_TEXT = Constants._prop
					.getProperty("SPAM_MESSAGE_TEXT");
			SPAM_MESSAGE_TYPE = new Integer(Constants._prop
					.getProperty("SPAM_MESSAGE_TYPE")).intValue();

			MESSAGE_OVER_MONEY = Constants._prop
					.getProperty("MESSAGE_OVER_MONEY");
			MESSAGE_OVER_MONEY_TYPE = new Integer(Constants._prop
					.getProperty("MESSAGE_OVER_MONEY_TYPE")).intValue();

			THREE_MESSAGES_IN_FIVE_MINUTES = Constants._prop
					.getProperty("THREE_MESSAGES_IN_FIVE_MINUTES");
			FIVE_MESSAGES_IN_TEN_MINUTES = Constants._prop
					.getProperty("FIVE_MESSAGES_IN_TEN_MINUTES");
			THIRTY_MESSAGES_IN_ONE_HOUR = Constants._prop
					.getProperty("THIRTY_MESSAGES_IN_ONE_HOUR");
			THREE00_MESSAGES_IN_24_HOUR = Constants._prop
					.getProperty("THREE00_MESSAGES_IN_24_HOUR");

			instance = new Spam();
		}
		return instance;
	}

	public void putMoneyCounter(String userID, MoneyCounter moneyCounter) {
		cachedMoneys.put(userID, moneyCounter);
	}

	public MoneyCounter getMoneyCounter(String userID) {
		return cachedMoneys.get(userID);
	}

	public boolean isSpam(String userId) {
		Util.logger.info("Calling " + getClass().getName()
				+ ".isSpam() check spam.");
		MoneyCounter moneyCounter = Spam.getInstance().getMoneyCounter(userId);
		MOMessage mOMessage = null;
		long now = System.currentTimeMillis();
		boolean isSpam = false;
		if (moneyCounter != null) {
			if (moneyCounter.getCount() >= Spam.SPAM_MAX_SEND) {
				if ((now - moneyCounter.getFirstMOMessage().getTime()) <= Spam.SPAM_DURATION_CHECK) {
					isSpam = true;
				} else {
					moneyCounter.dequeueMOMessage();
					mOMessage = new MOMessage();
					mOMessage.setTime(now);
					moneyCounter.enqueueMOMessage(mOMessage);
				}
			} else {
				mOMessage = new MOMessage();
				mOMessage.setTime(now);
				moneyCounter.enqueueMOMessage(mOMessage);
			}

		} else {
			moneyCounter = new MoneyCounter();
			mOMessage = new MOMessage();
			mOMessage.setTime(now);
			moneyCounter.enqueueMOMessage(mOMessage);
		}
		Spam.getInstance().putMoneyCounter(userId, moneyCounter);
		return isSpam;
	}

	public SpamResult CheckSpamAll(String operator, String serviceId,
			String userId, Timestamp receive_date, String keyword, int mo_id) {
		Util.logger.info("Calling " + getClass().getName()
				+ ".isSpamAll() check spam.");
		boolean isSpam = false;
		SpamResult spamResult = new SpamResult();
		Connection connection = null;
		CallableStatement cs = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			cs = connection
					.prepareCall("{ call CheckSpam(?,?,?,?,?,?,?,?,?) }");
			cs.setString(1, operator);
			cs.setString(2, serviceId);
			cs.setString(3, userId);
			cs.setTimestamp(4, receive_date);
			cs.setString(5, keyword);
			cs.setInt(6, mo_id);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.registerOutParameter(8, Types.INTEGER);
			cs.registerOutParameter(9, Types.INTEGER);
			cs.execute();

			spamResult.setIs_spam(cs.getInt(7));
			spamResult.setMo_count(cs.getInt(8));
			spamResult.setMo_duration(cs.getInt(9));
			cs.close();
		} catch (SQLException e) {
			Util.logger.error(getClass().getName() + ".isSpamAll() ERROR:"
					+ e.getMessage());
		} catch (Exception e) {
			Util.logger.error(getClass().getName() + ".isSpamAll() ERROR:"
					+ e.getMessage());
		}

		finally {
			dbpool.cleanup(connection);
		}

		return spamResult;
	}

	public boolean isOverMoney(String operator, String serviceId, String userId) {
		Util.logger.info("Calling " + getClass().getName()
				+ ".isOverMoney() check spam.");
		boolean isSpam = false;
		SpamResult spamResult = new SpamResult();
		Connection connection = null;
		CallableStatement cs = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			cs = connection.prepareCall("{ call CheckMaxMoney(?,?,?,?) }");
			cs.setString(1, operator);
			cs.setString(2, serviceId);
			cs.setString(3, userId);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();

			isSpam = (cs.getInt(4) == 1);
			cs.close();
		} catch (SQLException e) {
			Util.logger.error(getClass().getName() + ".isOverMoney() ERROR:"
					+ e.getMessage());
		} catch (Exception e) {
			Util.logger.error(getClass().getName() + ".isOverMoney() ERROR:"
					+ e.getMessage());
		}

		finally {
			dbpool.cleanup(connection);
		}

		return isSpam;
	}

	public static Integer GetMoneyMsg(String serviceID) {
		Integer money = 0;
		if ("6069".equals(serviceID)) {
			money = 500;
		} else if ("6169".equals(serviceID)) {
			money = 1000;
		} else if ("6269".equals(serviceID)) {
			money = 2000;
		} else if ("6369".equals(serviceID)) {
			money = 3000;
		} else if ("6469".equals(serviceID)) {
			money = 4000;
		} else if ("6569".equals(serviceID)) {
			money = 5000;
		} else if ("6669".equals(serviceID)) {
			money = 10000;
		} else if ("6769".equals(serviceID)) {
			money = 15000;
		}
		return money;
	}

	public static Connection getConnection() {
		String url = "jdbc:mysql://10.30.29.101:3306/";
		String dbName = "smsgw?useUnicode=true&characterEncoding=utf8";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "smsgw";
		String password = "smsadmin1122334455";
		
		/*String url = "jdbc:mysql://10.199.38.102:3306/";
		String dbName = "smsgw?useUnicode=true&characterEncoding=utf8";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "123456";*/
		
		Connection conn = null;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}

		return conn;
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
			Date today = new Date();
			String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(today);
			if (conn == null)
				conn = getConnection();

			strsql = "select service_id, user_id from sms_receive_log"+ yyyyMM +"  where user_id = '"
					+ userID
					+ "' and date(current_timestamp) = date(receive_date)";
			preStmt = conn.prepareStatement(strsql, 1004, 1008);
			if (preStmt.execute()) {
				for (rs = preStmt.getResultSet(); rs.next();) {
					moneycheck += GetMoneyMsg(rs.getString(1));
					telco = rs.getString(2);
				}
				if ("VMS".equals(telco)) {
					if (moneycheck + moneyadd > 300000) {
						result_check = false;
					}
				} else {
					if (moneycheck + moneyadd > 150000) {
						result_check = false;
					}
				}
			}
		} catch (Exception ex) {
			Util.logger.error("Check over money fail: "+ex.getMessage());
		} finally {
			if (conn != null )
			{
				if(preStmt != null)
				{
					preStmt.close();
					conn.close();
				}
				else
				{
					conn.close();
				}
			}
		}
		return result_check;
	}
	public static Boolean UpdateTimeSendMO(String userID, BigDecimal requestID, String operator)
	throws Exception {
		Boolean result_check = true;
		Connection conn = null;
		PreparedStatement preStmt = null;
		String strsql = null;
		try {
			Date today = new Date();
			String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(today);
			if (conn == null)
				conn = getConnection();
		
			strsql = "update sms_receive_day_"+ operator +" set timesendmotoCP = current_timestamp where request_id = '"
					+ requestID
					+ "' and user_id = '"+userID+"' ";
			preStmt = conn.prepareStatement(strsql, 1004, 1008);
			if (preStmt.execute()) {
				result_check = true;
			}
		} catch (Exception ex) {
			Util.logger.error("Log time send mo to CP: "+ex.getMessage());
		} finally {
			if (conn != null )
			{
				if(preStmt != null)
				{
					preStmt.close();
					conn.close();
				}
				else
				{
					conn.close();
				}
			}
		}
		return result_check;
	}
	public static Boolean UpdateTimeGetResponse(String userID, BigDecimal requestID, String operator)
	throws Exception {
		Boolean result_check = true;
		Connection conn = null;
		PreparedStatement preStmt = null;
		String strsql = null;
		try {
			Date today = new Date();
			String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(today);
			if (conn == null)
				conn = getConnection();
		
			strsql = "update sms_receive_day_"+ operator +" set timegetresponse = current_timestamp where request_id = '"
					+ requestID
					+ "' and user_id = '"+userID+"' ";
			preStmt = conn.prepareStatement(strsql, 1004, 1008);
			if (preStmt.execute()) {
				result_check = true;
			}
		} catch (Exception ex) {
			Util.logger.error("Log time send mo to CP: "+ex.getMessage());
		} finally {
			if (conn != null )
			{
				if(preStmt != null)
				{
					preStmt.close();
					conn.close();
				}
				else
				{
					conn.close();
				}
			}
		}
		return result_check;
	}
}
