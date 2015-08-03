package com.vng.adsnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.MsgMODAO;
import com.vng.adsnew.db.dao.beans.MsgMO;
import com.vng.adsnew.util.AdsRMTConstants;

public class MsgMODAOImpl extends BaseDaoImpl implements MsgMODAO {
	private static Logger logger = Logger.getLogger(MsgMODAOImpl.class);

	public int insert(Connection connection, MsgMO msgMO) throws Exception {
		int errorCode = 0;

		String strQuery = "INSERT INTO "
				+ AdsRMTConstants.tblMO
				+ " (mo_id, userid, serviceid, commandcode, message, username, password, requesttime, ip, operator)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setInt(1, msgMO.getMO_ID());
			ps.setString(2, msgMO.getUserID());
			ps.setString(3, msgMO.getServiceID());
			ps.setString(4, msgMO.getCommandCode());
			ps.setString(5, msgMO.getMessage());
			ps.setString(6, msgMO.getPartnerUsername());
			ps.setString(7, msgMO.getPartnerPassword());
			ps.setString(8, msgMO.getRequestTime());
			ps.setString(9, msgMO.getIP());
			ps.setString(10, msgMO.getOperator());

			errorCode = (executeUpdatePreparedStatement(ps) == 1 ? 0 : 1);

		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger.error("INSERT MsgMO FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}

		return errorCode;
	}

	public int insert(MsgMO msgMO) throws Exception {
		Connection conn = getConnection();
		int errorCode = 0;
		try {
			errorCode = insert(conn, msgMO);
		} catch (Exception e) {
			logger.error("INSERT MsgMO FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return errorCode;

	}

}
