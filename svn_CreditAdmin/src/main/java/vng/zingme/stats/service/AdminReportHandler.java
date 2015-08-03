package vng.zingme.stats.service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

import com.twmacinta.util.MD5;

/**
 * 
 * @author vinhcq@vng.com.vn
 */
public class AdminReportHandler {

	private static Logger logger_ = Logger.getLogger(AdminReportHandler.class);
	private static final String SET_APP_POSITION = "insert into apps_sort values('%s','%d') ON DUPLICATE KEY UPDATE position='%d'";
	private static final String SELECT_APP_POSITION = "select position from apps_sort where appID='%s';";
	private static final String SELECT_ALL_POSITION = "select appID,position from apps_sort;";
	private static final String SELECT_ADMIN_CREDIT = "select * from credits_admin where adminID='%s';";
	private static final String SELECT_ALL_ADMIN_CREDIT = "select * from credits_admin;";
	private static final String RESET_CREDIT_PASS = "update credits_admin set adminPassword='%s' where adminID='%s';";
	private static final String UPDATE_CREDIT_ADMIN = "update credits_admin set adminName='%s' where adminID='%s';";
	private static final String DELETE_CREDIT_ADMIN = "delete from credits_admin where adminID='%s';";
	private static final String INSERT_CREDIT_ADMIN = "insert into credits_admin(adminID,adminName,adminPassword)values('%s','%s','%s') ;";

	private static final String INSERT_ADMIN = "insert into report_admin(adminID,adminName,adminPassword,adminflg)values('%s','%s','%s','%d');";
	private static final String DELETE_ADMIN = "delete from report_admin where adminID='%s'";
	private static final String UPDATE_ADMIN = "update report_admin set adminName='%s',adminflg='%d' where adminID='%s';";
	private static final String RESET_PASS = "update reuport_admin set adminPassword='%s' where adminID='%s';";
	private static final String SELECT_ADMIN = "select * from report_admin where adminID='%s';";
	private static final String SELECT_ALL_ADMIN = "select * from report_admin;";
	private static final String SELECT_ALL_ADMIN_APP = "select * from report_admin_app where adminID='%s';";
	private static final String DELETE_ADMIN_APP = "delete from report_admin_app where adminID='%s';";

	public static void init() {
	}

	public static boolean setPosition(String appID, int pos) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(SET_APP_POSITION, appID, pos, pos));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static int getPosition(String appID) throws Exception {
		Connection conn = null;
		int pos = -1;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(SELECT_APP_POSITION,
					appID));

			while (rs.next()) {
				pos = rs.getInt("position");
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return pos;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return pos;
	}

	public static Map<String, Integer> getAllPosition() throws Exception {
		Connection conn = null;
		Map<String, Integer> _allPosition = new ConcurrentHashMap<String, Integer>();
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(SELECT_ALL_POSITION));
			while (rs.next()) {
				_allPosition.put(rs.getString("appID"), rs.getInt("position"));
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return _allPosition;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return _allPosition;
	}

	public static Map<String, String> getCreditsAdmin(String adminID)
			throws Exception {
		Connection conn = null;
		Map<String, String> allAdminCredits = new ConcurrentHashMap<String, String>();
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(SELECT_ADMIN_CREDIT,
					adminID));
			while (rs.next()) {
				allAdminCredits.put(rs.getString("adminID"),
						rs.getString("adminName"));
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return allAdminCredits;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return allAdminCredits;
	}

	public static Map<String, String> getAllCreditsAdmin() throws Exception {
		Connection conn = null;
		Map<String, String> allAdminCredits = new ConcurrentHashMap<String, String>();
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String
					.format(SELECT_ALL_ADMIN_CREDIT));
			while (rs.next()) {
				allAdminCredits.put(rs.getString("adminID"),
						rs.getString("adminName"));
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return allAdminCredits;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return allAdminCredits;
	}

	public static boolean insertCreditsAdmin(String username, String name,
			String pass) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(INSERT_CREDIT_ADMIN, username, name,
					new MD5(pass)));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean deleteCreditsAdmin(String userID) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(DELETE_CREDIT_ADMIN, userID));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean resetCreditspass(String resetPass, String adminID)
			throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(RESET_CREDIT_PASS, new MD5(resetPass),
					adminID));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean updateCreditsAdmin(String adminID, String adminName)
			throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(UPDATE_CREDIT_ADMIN, adminName, adminID));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static ArrayList<ArrayList<String>> getAllAdmin() throws Exception {
		Connection conn = null;
		ArrayList<ArrayList<String>> table = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(String
					.format(SELECT_ALL_ADMIN));

			int columnCount = resultSet.getMetaData().getColumnCount();

			if (resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
				table = new ArrayList<ArrayList<String>>();
			} else {
				resultSet.last();
				table = new ArrayList<ArrayList<String>>(resultSet.getRow());
				resultSet.beforeFirst();
			}

			for (ArrayList<String> row; resultSet.next(); table.add(row)) {
				row = new ArrayList<String>(columnCount);

				for (int c = 1; c <= columnCount; ++c) {
					row.add(resultSet.getString(c).intern());
				}
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return table;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return table;
	}

	public static ArrayList<String> getAllAdminApp(String adminID)
			throws Exception {
		Connection conn = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(String.format(
					SELECT_ALL_ADMIN_APP, adminID));
			while (resultSet.next()) {
				result.add(resultSet.getString("adminAppID"));
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return result;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return result;
	}

	public static ArrayList<String> getAdmin(String adminID) throws Exception {
		Connection conn = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(String.format(SELECT_ADMIN,
					adminID));
			while (resultSet.next()) {
				result.add(resultSet.getString("adminID"));
				result.add(resultSet.getString("adminName"));
				result.add(String.valueOf(resultSet.getInt("adminflg")));
			}
			DBConnectionManager.getInstance().returnConnection(conn);
			return result;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return result;
	}

	private static String buildQuery(ArrayList<String> apps, String adminID) {
		String query = "insert into report_admin_app(adminID,adminAppID) values";
		for (String appID : apps) {
			query += " ('" + adminID + "','" + appID + "')";
			if (!apps.get(apps.size() - 1).equals(appID)) {
				query += " , ";
			}
		}
		return query;
	}

	public static boolean insertAdmin(String adminID, String adminName,
			String adminPass, int adminflg, ArrayList<String> apps)
			throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(5000);
			Statement st = conn.createStatement();
			st.execute(String.format(INSERT_ADMIN, adminID, adminName, new MD5(
					adminPass), adminflg));
			if (apps.isEmpty()) {
				DBConnectionManager.getInstance().returnConnection(conn);
				return true;
			}
			boolean executeQuery = st.execute(buildQuery(apps, adminID));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean updateAdmin(String adminID, String adminName,
			int adminflg, ArrayList<String> apps) throws Exception {
		Connection conn = null;
		try {

			conn = DBConnectionManager.getInstance().getConnection(5000);
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();

			st.execute(String
					.format(UPDATE_ADMIN, adminName, adminflg, adminID));
			st.execute(String.format(DELETE_ADMIN_APP, adminID));
			if (apps.isEmpty()) {
				DBConnectionManager.getInstance().returnConnection(conn);
				return true;
			}
			boolean executeQuery = st.execute(buildQuery(apps, adminID));

			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean deleteAdmin(String adminID) throws Exception {
		Connection conn = null;
		try {

			conn = DBConnectionManager.getInstance().getConnection(5000);
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();

			st.execute(String.format(DELETE_ADMIN, adminID));
			st.execute(String.format(DELETE_ADMIN_APP, adminID));

			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

	public static boolean resetPass(String resetPass, String adminID)
			throws Exception {
		Connection conn = null;
		try {
			conn = DBConnectionManager.getInstance().getConnection(2000);
			Statement st = conn.createStatement();
			st.execute(String.format(RESET_PASS, new MD5(resetPass), adminID));
			int rs = st.getUpdateCount();
			DBConnectionManager.getInstance().returnConnection(conn);
			if (rs > 0)
				return true;
		} catch (SQLException ex) {
			logger_.error(ex.getMessage(), ex);
			if (conn != null) {
				DBConnectionManager.getInstance().returnConnection(conn);
			}
		}
		return false;
	}

}
