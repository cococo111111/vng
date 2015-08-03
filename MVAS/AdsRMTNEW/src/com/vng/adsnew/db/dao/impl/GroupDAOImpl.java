package com.vng.adsnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.GroupDAO;
import com.vng.adsnew.db.dao.beans.Group;
import com.vng.adsnew.util.AdsRMTConstants;
import com.vng.adsnew.util.Utils;

public class GroupDAOImpl extends BaseDaoImpl implements GroupDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	public GroupDAOImpl() {
	}

	public Group getUserInfoByGroupID(Connection connection, int groupID)
			throws Exception {

		String strQuery = "SELECT GroupName, Detail  FROM "
				+ AdsRMTConstants.tblGroup
				+ " WHERE GroupId=? AND (flag is null OR flag<>'T')";

		PreparedStatement ps = connection.prepareStatement(strQuery);
		ps.setInt(1, groupID);
		ResultSet rs = executeQueryPreparedStatement(ps);

		Group group = null;
		if (rs != null && rs.next()) {
			group = new Group();
			group.setExist(true);
			group.setGroupName(Utils.getStr(rs.getString("GroupName"), ""));
			group.setDetail(Utils.getStr(rs.getString("Detail"), ""));
		}

		return group;
	}

	public Group getUserInfoByGroupID(int groupID) throws Exception {
		Connection conn = getConnection();
		Group group = null;
		try {
			group = getUserInfoByGroupID(conn, groupID);
		} catch (Exception e) {
			logger.error("GET GROUP INFOR FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return group;

	}

	public int insert(Connection connection, Group group) throws Exception {

		String strQuery = "INSERT INTO " + AdsRMTConstants.tblGroup + " "
				+ " (GroupName, Detail) " + " VALUE(?,?)";

		PreparedStatement ps = connection.prepareStatement(strQuery);
		ps.setString(1, group.getGroupName());
		ps.setString(2, group.getDetail());

		return executeUpdatePreparedStatement(ps);
	}

	public int insert(Group group) throws Exception {
		Connection conn = getConnection();
		int numEffectedRows = 0;
		try {
			numEffectedRows = insert(conn, group);
		} catch (Exception e) {
			logger.error("INSERT GROUP FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return numEffectedRows;

	}
}
