package com.vng.cpnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vng.cpnew.db.dao.SmsReceiveQueueDAO;
import com.vng.cpnew.db.dao.beans.SmsReceiveQueue;
import com.vng.cpnew.util.CPRMTConstants;

public class SmsReceiveQueueDAOImpl extends BaseDaoImpl implements
		SmsReceiveQueueDAO {
	private static Logger logger = Logger
			.getLogger(SmsReceiveQueueDAOImpl.class);

	public SmsReceiveQueueDAOImpl() {
	}

	public int insert(Connection connection, SmsReceiveQueue smsReceiveQueue)
			throws Exception {

		String strQuery = "INSERT INTO "
				+ CPRMTConstants.tblReceiveQueue
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, receive_date, RESPONDED, REQUEST_ID)"
				+ " VALUE(?,?,?,?,?,CURRENT_TIMESTAMP,?,?)";

		PreparedStatement ps = null;
		int errorCode = 0;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, smsReceiveQueue.getUserID());
			ps.setString(2, smsReceiveQueue.getServiceID());
			ps.setString(3, smsReceiveQueue.getOperator());
			ps.setString(4, smsReceiveQueue.getCommandCode());
			ps.setString(5, smsReceiveQueue.getMessage());
			ps.setDouble(6, 0);
			ps.setString(7, String.valueOf(smsReceiveQueue.getMO_ID()));

			errorCode = (executeUpdatePreparedStatement(ps) == 1 ? 0 : 1);

		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger
					.error("INSERT Sms Receive Queue FAILED ex:"
							+ e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}

		return errorCode;
	}

	public int insert(SmsReceiveQueue smsReceiveQueue) throws Exception {
		Connection conn = getConnection();
		int errorCode = 0;
		try {
			errorCode = insert(conn, smsReceiveQueue);
		} catch (Exception e) {
			logger
					.error("INSERT Sms Receive Queue FAILED ex:"
							+ e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return errorCode;
	}

}
