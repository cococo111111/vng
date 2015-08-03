package com.vng.cpnew.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

public class DBPool {
	private static Logger logger = Logger.getLogger(DBPool.class);

	public DBPool() {

	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.default");
		} catch (SQLException e) {
			logger.error("getConnection Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnection Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionSMSGW() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.smsgw");
		} catch (SQLException e) {
			logger.error("getConnectionSMSGW Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionSMSGW Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionAlert() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.alert");
		} catch (SQLException e) {
			logger.error("getConnectionAlert Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionAlert Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionDebug() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.debug");
		} catch (SQLException e) {
			logger.error("getConnectionDebug Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionDebug Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionKeyword() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.keyword");
		} catch (SQLException e) {
			logger.error("getConnectionKeyword Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionKeyword Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionReport() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.report");
		} catch (SQLException e) {
			logger.error("getConnectionReport Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionReport Failed! " + e);
		}
		return conn;
	}

	public static Connection getConnectionSimulator() {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.simulator");
		} catch (SQLException e) {
			logger.error("getConnectionSimulator Failed! " + e);
		}

		catch (Exception e) {
			logger.error("getConnectionSimulator Failed! " + e);
		}
		return conn;
	}

	//
	public static Vector ExecuteQuery(String strSQL) throws Exception {
		Connection connection = (Connection) DBPool.getConnection();
		Vector vt = ExecuteQuery(connection, strSQL);
		connection.close();
		return vt;
	}

	public static Vector ExecuteQuery(Connection cn, String strSQL)
			throws Exception {
		Statement stmt = cn.createStatement();
		ResultSet rs = stmt.executeQuery(strSQL);
		Vector vt = ConvertToVector(rs);
		rs.close();
		stmt.close();
		return vt;
	}

	public static Vector ConvertToVector(ResultSet rsData) throws Exception {
		Vector vctReturn = new Vector();
		int iColumnCount = rsData.getMetaData().getColumnCount();
		Vector vctRow;
		for (; rsData.next(); vctReturn.addElement(vctRow)) {
			vctRow = new Vector();
			for (int i = 1; i <= iColumnCount; i++) {
				String strValue = rsData.getString(i);
				if (strValue == null) {
					strValue = "";
				}
				vctRow.addElement(strValue);
			}
		}
		vctReturn.trimToSize();
		return vctReturn;
	}
}
