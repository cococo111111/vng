package com.vng.cpnew.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.vng.cpnew.server.DBConfigLoader;
import com.vng.cpnew.server.DBPool;

public class Utils {
	static FileOutputStream fout = null;
	private static Logger logger = Logger.getLogger(Utils.class);

	public Utils() {
	}

	public static String displayDateTime(java.util.Date d, String f) {
		if (d == null) {
			return "";
		}
		return new SimpleDateFormat(f).format(d);
	}

	public static String displayDate(java.util.Date d) {
		return displayDateTime(d, "dd/MM/yyyy");
	}

	public static String displayDateTime(java.util.Date d) {
		return displayDateTime(d, "dd/MM/yyyy HH:mm:ss");
	}

	public static String getDateNow() {
		Calendar now = Calendar.getInstance();
		return displayDate(now.getTime());
	}

	public static String getDateTimeNow() {
		Calendar now = Calendar.getInstance();
		return displayDateTime(now.getTime());
	}

	public static String getDate4MySQL() {
		Calendar now = Calendar.getInstance();
		return displayDateTime(now.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDate4Log() {
		Calendar now = Calendar.getInstance();
		return displayDateTime(now.getTime(), "yyyy-MM-dd");
	}

	public static java.util.Date ParserDate(String s, String p) {
		try {
			java.util.Date d = new SimpleDateFormat(p).parse(s);
			return d;
		} catch (ParseException ex) {
			return new java.util.Date(0);
		}
	}

	// Parse
	public static String getStr(Object o, String strNullValue) {
		try {
			return o.toString();
		} catch (Exception ex) {
			return strNullValue;
		}
	}

	public static int ParserInt(Object o) {
		try {
			return Integer.parseInt(o.toString());
		} catch (Exception ex) {
			return 0;
		}
	}

	public static double ParserDouble(Object o) {
		try {
			return Double.parseDouble(o.toString());
		} catch (Exception ex) {
			return 0;
		}
	}

	// Logging
	private static void openLogFile() {
		try {
			if (fout == null) {
				fout = new FileOutputStream("logs-" + getDate4Log() + ".txt",
						true); // append=true
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Utilities.openLogFile: " + ex.getMessage());
		}
	}

	public static void log(String s) {
		System.out.println("[" + getDateTimeNow() + "] " + s);
		// try {
		// openLogFile();
		// fout.write( ("[" + getDateTimeNow() + "] " + s + "\r\n").getBytes());
		// fout.flush();
		// closeLogFile();
		// }
		// catch (Exception e) {
		// System.out.println("Utils.log: " + e.getMessage());
		// }
	}

	private static void closeLogFile() {
		if (fout != null) {
			try {
				fout.close();
				fout = null;
			} catch (IOException ex1) {
			}
		}
	}

	// Check
	public static String getOperator(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() < 10) {
			return null;
		}
		if (!phoneNumber.substring(0, 2).equalsIgnoreCase("84")) {
			return null;
		}
		String[] strPrefix1 = { "8491", "8494", "84123", "8490", "8493",
				"84122", "8497", "8498", "84168", "84169", "8496", "8495",
				"8492" };
		String[] strPrefix2 = { "+8491", "+8494", "+84123", "+8490", "+8493",
				"+84122", "+8497", "+8498", "+84168", "+84169", "+8496",
				"+8495", "+8492" };
		String[] strOperator = { "GPC", "GPC", "GPC", "VMS", "VMS", "VMS",
				"VIETTEL", "VIETTEL", "VIETTEL", "VIETTEL", "EVN", "SFONE",
				"HTC" };
		int i = 0;
		for (i = 0; i < strPrefix1.length; i++) {
			if (phoneNumber.substring(0, strPrefix1[i].length())
					.equalsIgnoreCase(strPrefix1[i])) {
				return strOperator[i];
			}
		}
		for (i = 0; i < strPrefix2.length; i++) {
			if (phoneNumber.substring(0, strPrefix2[i].length())
					.equalsIgnoreCase(strPrefix2[i])) {
				return strOperator[i];
			}
		}
		return "";
	}

	public static boolean checkMO(int MO_ID, String UserID, String ServiceID,
			String CommandCode, String Message, String Operator,
			String PartnerUsername, String PartnerPassword, String RequestTime) {
		if (MO_ID < 0) {
			logger.debug("MO_ID: " + MO_ID);
			return false;
		}
		if (UserID.length() > 20) {
			logger.debug("UserID: " + UserID);
			return false;
		}
		if (ServiceID.length() > 15) {
			logger.debug("ServiceID: " + ServiceID);
			return false;
		}
		if (CommandCode.length() > 20) {
			logger.debug("CommandCode: " + CommandCode);
			return false;
		}
		if (Message.length() > 160) {
			logger.debug("Message: " + Message);
			return false;
		}
		if (Operator.length() > 15) {
			logger.debug("Operator: " + Operator);
			return false;
		}
		if (PartnerUsername.length() > 30) {
			logger.debug("PartnerUsername: " + PartnerUsername);
			return false;
		}
		if (PartnerPassword.length() > 40) {
			logger.debug("PartnerPassword: " + PartnerPassword);
			return false;
		}
		if (RequestTime.length() > 25) {
			logger.debug("RequestTime: " + RequestTime);
			return false;
		}

		return true;
	}

	public static int checkMT(String requestID, String Operator, String UserID,
			String ServiceID, String CommandCode, String Message,
			Connection connection) throws Exception, SQLException {

		if (UserID == null || UserID.length() > 20 || !UserID.startsWith("84")
				|| !Utils.isDigits(UserID)) {
			logger.error("Invalid Data: UserID = [" + UserID + "]");
			// implement them rule here
			return CPRMTConstants.ERRORCODE_INVALID_USERID;
		}

		if (Message == null || Message.length() > 160 /*
													 * ||
													 * !Utils.isAllowMessageString
													 * (Message)
													 */) {
			logger.error("Invalid Data: Message = [" + Message + "]");
			return CPRMTConstants.ERRORCODE_INVALID_MESSAGE;
		}

		if (Operator == null
				|| Operator.equals("")
				|| Operator.length() > 15
				|| !ServicePrice.getInstance().isExistOperator(
						Operator.toUpperCase())
		/* || !MobileOperator.getInstance().isAllowOperator(Operator) */) {
			logger.error("Invalid Data: Operator = [" + Operator + "]");
			return CPRMTConstants.ERRORCODE_INVALID_OPERATOR;
		}
		if (ServiceID == null || ServiceID.length() > 15
				|| !ServicePrice.getInstance().isExistServiceNumber(ServiceID)
				|| CommandCode == null || CommandCode.length() > 15) {
			logger.error("Invalid Data: ServiceID = [" + ServiceID
					+ "] and CommandCode = [" + CommandCode + "]");
			return CPRMTConstants.ERRORCODE_INVALID_SERVICEID_KEYWORD;
		}

		if (requestID == null || requestID.length() > 20
				|| !Utils.isDigits(requestID)) {
			logger.error("Invalid Data: RequestID = [" + requestID + "]");
			return CPRMTConstants.ERRORCODE_INVALID_REQUESTID;
		}

		return CPRMTConstants.ERRORCODE_TRANSACTION_OK;
	}

	public static int getPolicy(int GId, int MId) {
		return getPolicy(null, GId, MId);
	}

	public static boolean isDigits(String number) {
		boolean Ok = true;

		String digits = "0123456789";
		if (number != null && number.length() > 0) {
			for (int i = 0; i < number.length(); i++) {
				if (digits.indexOf(number.charAt(i)) == -1) {
					Ok = false;
					break;
				}
			}
		} else {
			Ok = false;
		}

		return Ok;
	}

	public static boolean isValidCommandCode(String commandCode) {
		return DBConfigLoader.getInstance().getDbConfig().getCommandCode(
				commandCode) != null;
	}

	public static boolean isValidServiceID(String serviceID) {
		return DBConfigLoader.getInstance().getDbConfig().getServiceID()
				.equals(serviceID);
	}

	public static int getPolicy(Connection conn, int GId, int MId) {
		if (GId == 0 || MId == 0) {
			return 0;
		}

		String strQuery = "SELECT * FROM " + CPRMTConstants.tblPolicy
				+ " WHERE GroupId=?" + " AND ModuleId=?"
				+ " AND (flag is null OR flag<>'T')";
		boolean flag = false;
		int r = 0;
		if (conn == null) {
			conn = (Connection) DBPool.getConnection();
			flag = true;
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(strQuery);
			stmt.setInt(1, GId);
			stmt.setInt(2, MId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				r = rs.getInt("Policy");
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			r = -1;
		} finally {
			if (flag) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}

		return r;
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

	public static int updateMoneyByUserID(Connection connection, String userID,
			String serviceID) {
		int errorCode = 0;

		// get dd value of MT receive date, in this case system date
		String dd = String.valueOf(Calendar.getInstance().getTime().getDate());
		if (dd.length() < 2)
			dd = "0".concat(dd);

		logger.info("Increase Money used by UserID: [" + userID + "]");

		String tablename = "quota_" + dd;
		String sUpdateSQL = "INSERT INTO "
				+ tablename
				+ "(user_id, money) VALUES (?, ?) ON DUPLICATE KEY UPDATE money = money + ?";

		PreparedStatement psUpdate = null;
		try {

			// Update new money
			psUpdate = connection.prepareStatement(sUpdateSQL);
			psUpdate.setString(1, userID);
			psUpdate.setInt(2, ServicePrice.getInstance()
					.getPriceByServiceNumber(serviceID));
			psUpdate.setInt(3, ServicePrice.getInstance()
					.getPriceByServiceNumber(serviceID));
			errorCode = psUpdate.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				psUpdate.close();
			} catch (Exception e) {
			}
			;
		}

		return errorCode;

	}

	public static boolean checkDuplicateMTbyRequestID(Connection connection,
			String requestID, String userID, int summt) throws Exception {
		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(new Date());

		int totalMT = 0;
		String sql = "SELECT count(1) as totalMT FROM " + CPRMTConstants.tblMT
				+ yyyyMM + " WHERE requestid = ? and userid = ? and summt = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, requestID);
			ps.setString(2, userID);
			ps.setInt(3, summt);

			ResultSet result = ps.executeQuery();
			result.next();

			totalMT = result.getInt("totalMT");

			result.close();
			ps.close();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception("CHECK DUPLICATE REQUESTID FAILED ex:"
					+ e.getMessage());
		}

		return (totalMT != 0);
	}

	public static int getCurrentMoney(Connection connection, String userID) {
		int money = 0;

		String dayOfMonth = String.valueOf(Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
		if (dayOfMonth.length() < 2) {
			dayOfMonth = "0".concat(dayOfMonth);
		}

		String tablename = "quota_" + dayOfMonth;
		String sSQL = "SELECT money FROM " + tablename + " WHERE user_id = ? ";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sSQL);
			ps.setString(1, userID);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				money = result.getInt("money");
			}
			result.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}
			;
		}
		return money;
	}

	public static boolean isCaneledAdsUserID(Connection connection,
			String userID) throws Exception {
		String strQuery = "SELECT userid FROM vng_cancel_ads WHERE userid = ? LIMIT 1";
		boolean OK = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, userID);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				OK = true;
			}
			result.close();
		} catch (SQLException e) {
			OK = false;
			logger.error(e.getMessage());
			throw new Exception("Caneled Ads User FAILED ex:" + e.getMessage());
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}
			;
		}
		return OK;
	}

	public static boolean isOKConfig() {
		if (DBConfigLoader.getInstance().getDbConfig().getAllowOperators() == null
				|| DBConfigLoader.getInstance().getDbConfig()
						.getAllowOperators().equals("")

				|| DBConfigLoader.getInstance().getDbConfig().getServiceID() == null
				|| DBConfigLoader.getInstance().getDbConfig().getServiceID()
						.equals("")

				|| DBConfigLoader.getInstance().getDbConfig().getCommandCodes() == null
				|| DBConfigLoader.getInstance().getDbConfig().getCommandCodes()
						.equals("")) {
			return false;
		}
		return true;
	}

	public static boolean isAllowMessageString(String messsage) {
		if (messsage == null || messsage.trim().equals("")) {
			return false;
		}

		Pattern p = Pattern
				.compile("([a-zA-Z_.\\s,?!@#$%^\\-&*()_+:<>;/~\"0-9])*");
		Matcher m = p.matcher(messsage);
		return m.matches();
	}

	public static boolean authenticate(String currentSig, String requestID,
			String userID, String serviceID, String commandCode,
			String message, String secretKey) throws Exception {
		String sig = Hasher.getInstance("MD5").hash(
				requestID + userID + serviceID + commandCode + message
						+ secretKey);
		return sig.toLowerCase().equals(currentSig.toLowerCase());
	}

}
