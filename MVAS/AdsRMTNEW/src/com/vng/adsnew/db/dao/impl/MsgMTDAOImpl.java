package com.vng.adsnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.MsgMTDAO;
import com.vng.adsnew.db.dao.beans.MsgMT;
import com.vng.adsnew.util.AdsRMTConstants;

public class MsgMTDAOImpl extends BaseDaoImpl implements MsgMTDAO {
	private static Logger logger = Logger.getLogger(MsgMTDAOImpl.class);

	public MsgMTDAOImpl() {
	}

	public int insert(Connection connection, MsgMT msgMT) throws Exception {
		int errorCode = 0;

		Date today = new Date();
		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(today);

		String strQuery = "INSERT INTO "
				+ AdsRMTConstants.tblMT
				+ yyyyMM
				+ " (requestid, userid, message, serviceid, commandcode, msgtype, contenttype, summt, username, password, ip, operator, receive_date)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, msgMT.getRequestID());
			ps.setString(2, msgMT.getUserID());
			ps.setString(3, msgMT.getMessage());
			ps.setString(4, msgMT.getServiceID());
			ps.setString(5, msgMT.getCommandCode());
			ps.setInt(6, msgMT.getMsgType());
			ps.setInt(7, msgMT.getContentType());
			ps.setInt(8, msgMT.getSumMT());
			ps.setString(9, msgMT.getPartnerUsername());
			ps.setString(10, msgMT.getPartnerPassword());
			ps.setString(11, msgMT.getIP());
			ps.setString(12, msgMT.getOperator());
			ps.setTimestamp(13, new Timestamp(today.getTime()));

			errorCode = executeUpdatePreparedStatement(ps);

		} catch (SQLException e) {
			logger.error("INSERT MsgMT into table:[" + AdsRMTConstants.tblMT
					+ yyyyMM + "] FAILED. ex:" + e.getMessage());
			throw new Exception("INSERT MsgMT FAILED ex:" + e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}

		return errorCode;
	}

	public int insert(MsgMT msgMT) throws Exception {
		Connection conn = getConnection();
		int errorCode = 0;
		try {
			errorCode = insert(conn, msgMT);
		} catch (Exception e) {
			logger.error("INSERT MsgMO FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return errorCode;
	}

}
