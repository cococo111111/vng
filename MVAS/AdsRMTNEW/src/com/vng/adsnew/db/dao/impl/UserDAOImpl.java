package com.vng.adsnew.db.dao.impl;

/**
 * <p>Description: UserDAOImpl.
 * @author <A HREF="mailto:duyn77@yahoo.com">Nguyen Duc Duy</A>
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.UserDAO;
import com.vng.adsnew.db.dao.beans.User;
import com.vng.adsnew.util.AdsRMTConstants;
import com.vng.adsnew.util.Utils;

public class UserDAOImpl extends BaseDaoImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
	}

	public int changePassword(Connection connection, int userId, String password)
			throws Exception {
		int numEffectedRows = 0;
		String strQuery = "UPDATE " + AdsRMTConstants.tblUser
				+ " Set password=? " + " WHERE id=? "
				+ " AND (flag is NULL OR flag='')";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, password);
			ps.setInt(2, userId);
			numEffectedRows = executeUpdatePreparedStatement(ps);
		} catch (SQLException e) {
			logger.error("CHANGE PASSWORD FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}

		return numEffectedRows;
	}

	public int changePassword(int userId, String password) throws Exception {
		Connection conn = getConnection();
		int numEffectedRows = 0;
		try {
			numEffectedRows = changePassword(conn, userId, password);
		} catch (Exception e) {
			logger.error("CHANGE PASSWORD FAILED ex:" + e.getMessage());
			throw new Exception("CHANGE PASSWORD FAILED ex:" + e.getMessage());
		} finally {
			closeConnection(conn);
		}
		return numEffectedRows;

	}

	public User getUser(Connection connection, String loginName, String password)
			throws Exception {

		String strQuery = "SELECT * FROM "
				+ AdsRMTConstants.tblUser
				+ " WHERE username=? AND password=? AND (flag is null OR flag<>'T')";

		PreparedStatement ps = null;
		User user = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, loginName);
			ps.setString(2, password);
			ResultSet rs = executeQueryPreparedStatement(ps);

			if (rs != null && rs.next()) {
				user = new User();
				user.setExist(true);
				user.setUserId(rs.getInt("id"));
				user.setLoginName(Utils.getStr(rs.getString("username"), ""));
				user.setPassword(Utils.getStr(rs.getString("password"), ""));
				user.setIP(Utils.getStr(rs.getString("ip"), ""));
				user.setGroup(rs.getInt("group"));
				user.setFullName(Utils.getStr(rs.getString("fullname"), ""));
				user.setEmail(Utils.getStr(rs.getString("email"), ""));
				user.setPhone(Utils.getStr(rs.getString("phone"), ""));
			}
		} catch (SQLException e) {
			logger.error("GET USER FAILED ex:" + e.getMessage());
			throw e;
		}
		return user;
	}

	public User getUser(String loginName, String password) throws Exception {
		Connection conn = getConnection();
		User user = null;
		try {
			user = getUser(conn, loginName, password);
		} catch (Exception e) {
			logger.error("GET USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return user;

	}

	public User getUserInfoByUserID(Connection connection, int userID)
			throws Exception {

		String strQuery = "SELECT * FROM " + AdsRMTConstants.tblUser
				+ " WHERE id=? AND (flag is null OR flag='')";
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(strQuery);
			ps.setInt(1, userID);
			rs = executeQueryPreparedStatement(ps);

			if (rs != null && rs.next()) {
				user = new User();
				user.setExist(true);
				user.setUserId(rs.getInt("id"));
				user.setLoginName(Utils.getStr(rs.getString("username"), ""));
				user.setPassword(Utils.getStr(rs.getString("password"), ""));
				user.setIP(Utils.getStr(rs.getString("ip"), ""));
				user.setGroup(rs.getInt("group"));
				user.setFullName(Utils.getStr(rs.getString("fullname"), ""));
				user.setEmail(Utils.getStr(rs.getString("email"), ""));
				user.setPhone(Utils.getStr(rs.getString("phone"), ""));
			}
		} catch (SQLException e) {
			logger.error("GET USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}
		return user;
	}

	public User getUserInfoByUserID(int userID) throws Exception {
		Connection conn = getConnection();
		User user = null;
		try {
			user = getUserInfoByUserID(conn, userID);
		} catch (Exception e) {
			logger.error("GET USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return user;

	}

	public int insert(Connection connection, User user) throws Exception {

		String strQuery = "INSERT INTO " + AdsRMTConstants.tblUser + " "
				+ " (username, password, ip, `group`, fullname, email, phone) "
				+ " VALUE(?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		int numEffectedRows = 0;

		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, user.getLoginName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getIP());
			ps.setInt(4, user.getGroup());
			ps.setString(5, user.getFullName());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPhone());
			numEffectedRows = executeUpdatePreparedStatement(ps);
		} catch (SQLException e) {
			logger.error("INSERT USER FAILED ex:" + e.getMessage());
			throw new Exception("INSERT USER FAILED ex:" + e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}
		return numEffectedRows;
	}

	public int insert(User user) throws Exception {
		Connection conn = getConnection();
		int numEffectedRows = 0;
		try {
			numEffectedRows = insert(conn, user);
		} catch (Exception e) {
			logger.error("INSERT USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return numEffectedRows;

	}

	public int update(Connection connection, User user) throws Exception {

		String strQuery = "UPDATE "
				+ AdsRMTConstants.tblUser
				+ " "
				+ " SET username=?, password=?, ip=?, `group`=?, fullname=?, email=?, phone=? "
				+ " WHERE id=? AND (flag is null OR flag='')";

		PreparedStatement ps = null;
		int numEffectedRows = 0;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, user.getLoginName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getIP());
			ps.setInt(4, user.getGroup());
			ps.setString(5, user.getFullName());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getUserId());

			numEffectedRows = executeUpdatePreparedStatement(ps);
		} catch (SQLException e) {
			logger.error("INSERT USER FAILED ex:" + e.getMessage());
			throw new Exception("INSERT USER FAILED ex:" + e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}

		return numEffectedRows;
	}

	public int update(User user) throws Exception {
		Connection conn = getConnection();
		int numEffectedRows = 0;
		try {
			numEffectedRows = insert(conn, user);
		} catch (Exception e) {
			logger.error("INSERT USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return numEffectedRows;

	}

	public int deleteByUserID(Connection connection, int userID)
			throws Exception {

		String strQuery = "UPDATE " + AdsRMTConstants.tblUser + " "
				+ " SET flag='T' " + " WHERE id=?";

		PreparedStatement ps = null;
		int numOfEffectedRows = 0;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setInt(1, userID);
			numOfEffectedRows = executeUpdatePreparedStatement(ps);
		} catch (SQLException e) {
			logger.error("DELETE USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closePrepareStatement(ps);
		}

		return numOfEffectedRows;
	}

	public int deleteByUserID(int userID) throws Exception {
		Connection conn = getConnection();
		int numEffectedRows = 0;
		try {
			numEffectedRows = deleteByUserID(conn, userID);
		} catch (Exception e) {
			logger.error("DELETE USER FAILED ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return numEffectedRows;

	}

	public boolean checkUserName(Connection connection, String usr, String pwd,
			String ip) throws Exception {
		boolean isExist = false;
		String strQuery = "SELECT * FROM " + AdsRMTConstants.tblUser
				+ " WHERE username=?" + " AND password=?" + " AND ip=?"
				+ " AND (flag is NULL OR flag='')";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, usr);
			ps.setString(2, pwd);
			ps.setString(3, ip);
			ResultSet rs = executeQueryPreparedStatement(ps);
			isExist = rs.next();
		} catch (SQLException e) {
			logger.error("CHECK USER NAME FAILED ex:" + e.getMessage());
			throw new Exception("CHECK USER NAME FAILED ex:" + e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}

		return isExist;
	}

	public String getOperator(Connection connection, String phoneNumber,
			String tableName, String YYYYDD) throws Exception {
		String operator = "";
		String strQuery = "SELECT MOBILE_OPERATOR FROM " + tableName + YYYYDD
				+ " where USER_ID = ? limit 1";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, phoneNumber);

			ResultSet rs = executeQueryPreparedStatement(ps);
			if (rs != null && rs.next()) {
				operator = Utils.getStr(rs.getString("MOBILE_OPERATOR"), "");
			}
			;
		} catch (SQLException e) {
			logger.error("Get Operator  FAILED ex:" + e.getMessage());
			throw new Exception("Get Operator FAILED ex:" + e.getMessage());
		} finally {
			closePrepareStatement(ps);
		}

		return operator;
	}

	public String getOperator(String phoneNumber, String tableName,
			String YYYYDD) throws Exception {
		Connection conn = getConnection();
		String operator = "";
		try {
			operator = getOperator(conn, phoneNumber, tableName, YYYYDD);
		} catch (Exception e) {
			logger.error("Get Operator ex:" + e.getMessage());
			throw e;
		} finally {
			closeConnection(conn);
		}
		return operator;

	}
}
