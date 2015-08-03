package com.vng.cpnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.vng.cpnew.db.dao.EmsSendQueueDAO;
import com.vng.cpnew.db.dao.beans.EmsSendQueue;
import com.vng.cpnew.util.CPRMTConstants;

public class EmsSendQueueDAOImpl extends BaseDaoImpl implements EmsSendQueueDAO {
	private static Logger logger = Logger.getLogger(EmsSendQueueDAOImpl.class);

	public EmsSendQueueDAOImpl() {
	}

	public int insert(Connection connection, EmsSendQueue emsSendQueue)
			throws Exception {
		String strQuery = "INSERT INTO "
				+ CPRMTConstants.tblSendQueue
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, "
				+ " INFO, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID,  TOTAL_SEGMENTS, CPId, SUBMIT_DATE, DONE_DATE)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		int errorCode = 0;
		try {
			Date today = new Date();
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, emsSendQueue.getUserID());
			ps.setString(2, emsSendQueue.getServiceID());
			ps.setString(3, emsSendQueue.getOperator());
			ps.setString(4, emsSendQueue.getCommandCode());
			ps.setDouble(5, emsSendQueue.getContentType());
			ps.setString(6, emsSendQueue.getMessage());
			ps.setDouble(7, emsSendQueue.getMsgType());
			ps.setString(8, emsSendQueue.getRequestID());
			ps.setString(9, emsSendQueue.getRequestID());
			ps.setDouble(10, emsSendQueue.getSumMT());
			ps.setInt(11, 26);
			ps.setTimestamp(12, new Timestamp(today.getTime()));
			ps.setTimestamp(13, new Timestamp(today.getTime()));

			errorCode = executeUpdatePreparedStatement(ps);

		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger.error("INSERT Ems Send Queue FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}

		return errorCode;
	}

	public int insert(EmsSendQueue emsSendQueue) throws Exception {
		Connection conn = getConnection();
		int errorCode = 0;
		try {
			errorCode = insert(conn, emsSendQueue);
		} catch (Exception e) {
			logger.error("INSERT Ems Send Queue FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return errorCode;
	}

}
