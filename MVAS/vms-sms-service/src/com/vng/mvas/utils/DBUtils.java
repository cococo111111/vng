package com.vng.mvas.utils;

import com.vng.mvas.models.Incoming;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DBUtils {

	public static String MESSAGE_OVER_MONEY_VMS = System
			.getProperty("MESSAGE_OVER_MONEY_VMS");
	public static String MESSAGE_SYNTAX_ERROR = System
			.getProperty("MESSAGE_SYNTAX_ERROR");
	public static int CPId = Integer.parseInt(System.getProperty("CPId"));
	public static int PRIORITY = Integer.parseInt(System
			.getProperty("PRIORITY"));
	public static int RETRIES_NUM = Integer.parseInt(System
			.getProperty("RETRIES_NUM"));
	private static Logger logger = Logger.getLogger(DBUtils.class);
	private static String INVALID_COMMANDCODE = "INV";

	public static int addIncoming(Incoming msg) throws SQLException {
		PreparedStatement pstmt = null;
		Connection connection = null;
		int errorCode = 0;
		try {

			connection = DBConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO sms_receive_queue(USER_ID,SERVICE_ID,MOBILE_OPERATOR,COMMAND_CODE,"
					+ "INFO,receive_date,REQUEST_ID) "
					+ "VALUES(?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, DataUtils.getUSER_ID(msg.getSenderAddress()));
			pstmt.setString(2, DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber()));
			pstmt.setString(3,
					DataUtils.getMOBILE_OPERATOR(msg.getSenderAddress()));
			pstmt.setString(4, DataUtils.getCOMMAND_CODE(msg.getMessage()));
			pstmt.setString(5, msg.getMessage());
			pstmt.setTimestamp(6, new Timestamp(msg.getDateTime().getTime()));
			pstmt.setString(7, msg.getRequestID());
			// pstmt.setString(8, msg.getTraceUniqueID());
			// pstmt.setString(9, msg.getSpId());

			pstmt.execute();
			errorCode = pstmt.getUpdateCount();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		} catch (PropertyVetoException ex) {
			logger.error(ex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			pstmt.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}
		return errorCode;
	}

	public static int logIncoming(Incoming msg, boolean isInvalid)
			throws SQLException {

		PreparedStatement pstmt = null;
		Connection connection = null;
		int errorCode = 0;
		try {
			connection = DBConnectionManager.getInstance().getConnection();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			String tableSuffix = df.format(msg.getDateTime());
			String sql = "INSERT INTO sms_receive_log" + tableSuffix
					+ "(USER_ID,SERVICE_ID,MOBILE_OPERATOR,COMMAND_CODE,"
					+ "INFO,receive_date,REQUEST_ID,NOTES,CPId) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, DataUtils.getUSER_ID(msg.getSenderAddress()));
			pstmt.setString(2, DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber()));
			pstmt.setString(3,
					DataUtils.getMOBILE_OPERATOR(msg.getSenderAddress()));
			pstmt.setString(4,
					(isInvalid) ? INVALID_COMMANDCODE : DataUtils
							.getCOMMAND_CODE(msg.getMessage()));
			pstmt.setString(5, msg.getMessage());
			pstmt.setTimestamp(6, new Timestamp(msg.getDateTime().getTime()));
			pstmt.setString(7, msg.getRequestID());
			pstmt.setString(8, msg.getTraceUniqueID());
			pstmt.setString(9, msg.getSpId());

			pstmt.execute();
			errorCode = pstmt.getUpdateCount();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		} catch (PropertyVetoException ex) {
			logger.error(ex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			pstmt.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}
		return errorCode;
	}

	public static int addOverLimitation(Incoming msg, int contentType,
			int msgType, int sumMT) throws SQLException {
		String strQuery = "INSERT INTO "
				+ "ems_send_queue"
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, "
				+ " INFO, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID,  TOTAL_SEGMENTS,RETRIES_NUM, insert_date, CPId, SUBMIT_DATE, DONE_DATE,PRIORITY)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		Connection connection = null;
		int errorCode = 0;
		try {
			connection = DBConnectionManager.getInstance().getConnection();
			Date today = new Date();
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, DataUtils.getUSER_ID(msg.getSenderAddress()));
			ps.setString(2, DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber()));
			ps.setString(3,
					DataUtils.getMOBILE_OPERATOR(msg.getSenderAddress()));
			ps.setString(4, DataUtils.getCOMMAND_CODE(msg.getMessage()));
			ps.setInt(5, contentType);
			ps.setString(6, MESSAGE_OVER_MONEY_VMS);
			ps.setInt(7, msgType);
			ps.setString(8, msg.getRequestID());
			ps.setString(9, msg.getRequestID());
			ps.setInt(10, sumMT);
			ps.setInt(11, RETRIES_NUM);
			ps.setTimestamp(12, new Timestamp(today.getTime()));
			ps.setInt(13, CPId);
			ps.setTimestamp(14, new Timestamp(today.getTime()));
			ps.setTimestamp(15, new Timestamp(today.getTime()));
			ps.setInt(16, PRIORITY);
			ps.execute();
			errorCode = ps.getUpdateCount();

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		} catch (PropertyVetoException ex) {
			logger.error(ex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}

		return errorCode;
	}

	public static int addInvalidMessage(Incoming msg, int contentType,
			int msgType, int sumMT) throws SQLException {
		String strQuery = "INSERT INTO "
				+ "ems_send_queue"
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, "
				+ " INFO, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID,  TOTAL_SEGMENTS,RETRIES_NUM, insert_date, CPId, SUBMIT_DATE, DONE_DATE,PRIORITY)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		Connection connection = null;
		int errorCode = 0;
		try {
			connection = DBConnectionManager.getInstance().getConnection();
			Date today = new Date();
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, DataUtils.getUSER_ID(msg.getSenderAddress()));
			ps.setString(2, DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber()));
			ps.setString(3,
					DataUtils.getMOBILE_OPERATOR(msg.getSenderAddress()));
			ps.setString(4, INVALID_COMMANDCODE);
			ps.setInt(5, contentType);
			ps.setString(6, MESSAGE_SYNTAX_ERROR);
			ps.setInt(7, msgType);
			ps.setString(8, msg.getRequestID());
			ps.setString(9, msg.getRequestID());
			ps.setInt(10, sumMT);
			ps.setInt(11, RETRIES_NUM);
			ps.setTimestamp(12, new Timestamp(today.getTime()));
			ps.setInt(13, CPId);
			ps.setTimestamp(14, new Timestamp(today.getTime()));
			ps.setTimestamp(15, new Timestamp(today.getTime()));
			ps.setInt(16, PRIORITY);
			ps.execute();
			errorCode = ps.getUpdateCount();

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		} catch (PropertyVetoException ex) {
			logger.error(ex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}

		return errorCode;
	}

	public int insert111(Incoming msg, String getcontenType, int getMsgType,
			int getSumMT) throws SQLException {
		String strQuery = "INSERT INTO "
				+ "ems_send_queue"
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, "
				+ " INFO, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID,  TOTAL_SEGMENTS, CPId, SUBMIT_DATE, DONE_DATE)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		Connection connection = null;
		int errorCode = 0;
		try {
			connection = DBConnectionManager.getInstance().getConnection();
			Date today = new Date();
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, DataUtils.getUSER_ID(msg.getSenderAddress()));
			ps.setString(2, DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber()));
			ps.setString(3,
					DataUtils.getMOBILE_OPERATOR(msg.getSenderAddress()));
			ps.setString(4, DataUtils.getCOMMAND_CODE(msg.getMessage()));
			ps.setString(5, getcontenType);
			ps.setString(6, msg.getMessage());
			ps.setInt(7, getMsgType);
			ps.setString(8, msg.getRequestID());
			ps.setString(9, msg.getRequestID());
			ps.setInt(10, getSumMT);
			ps.setInt(11, CPId);
			ps.setTimestamp(12, new Timestamp(today.getTime()));
			ps.setTimestamp(13, new Timestamp(today.getTime()));
			ps.execute();
			errorCode = ps.getUpdateCount();

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		} catch (PropertyVetoException ex) {
			logger.error(ex.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}

		return errorCode;
	}

}