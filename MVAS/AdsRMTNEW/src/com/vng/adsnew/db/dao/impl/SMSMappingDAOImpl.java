package com.vng.adsnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.SMSMappingDAO;
import com.vng.adsnew.util.AdsRMTConstants;
import com.vng.adsnew.util.Utils;

public class SMSMappingDAOImpl extends BaseDaoImpl implements SMSMappingDAO {
	private static Logger logger = Logger.getLogger(SMSMappingDAOImpl.class);

	public SMSMappingDAOImpl() {
	}

	public String convertMOIDMappingToRequestID(Connection connection,
			int requestIDMapping) throws Exception {
		String strQuery = "SELECT request_id FROM "
				+ AdsRMTConstants.tableNameMapping + " where unique_id = ?";
		String requestID = "";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setInt(1, requestIDMapping);

			ResultSet rs = executeQueryPreparedStatement(ps);

			if (rs != null && rs.next()) {
				requestID = Utils.getStr(rs.getString("request_id"), "");
			}
		} catch (SQLException e) {
			logger.error("Convert MOIDMapping To RequestID  FAILED ex:"
					+ e.getMessage());
			throw new Exception("Convert MOIDMapping To RequestID  FAILED ex:"
					+ e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}

		return requestID;
	}

}
