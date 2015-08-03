package com.vng.cpnew.server;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * <p>
 * Description: DBConfigLoader.
 * 
 * @author <A HREF="mailto:duyn77@yahoo.com">Nguyen Duc Duy</A>
 * @version 1.0
 */

public class CPRMTServer {
	private static Logger logger = Logger.getLogger(CPRMTServer.class);

	private static CPRMTServer instance = null;

	// private DBConfig dbConfig = DBConfigLoader.getInstance().getDbConfig();

	private CPRMTServer() {
	}

	public static CPRMTServer getInstance() {
		if (instance == null) {
			synchronized (CPRMTServer.class) {
				if (instance == null) {
					instance = new CPRMTServer();
				}
			}

		}
		return instance;
	}

	/*
	 * private Connection createConnection() throws SQLException {
	 * 
	 * Connection connection = null; try {
	 * Class.forName(dbConfig.getJdbcDriver()); connection =
	 * DriverManager.getConnection(dbConfig.getDbURL() + "user=" +
	 * dbConfig.getDbUser() + "&password=" + dbConfig.getDbPassword()); } catch
	 * (Exception ecx) { logger.error("FAILED TO CREATE CONNECTION TO :" +
	 * ecx.getMessage()); throw new
	 * SQLException("FAILED TO CREATE CONNECTION TO" + ": " + ecx.getMessage());
	 * } return connection; }
	 */

	// dbpool for production
	private Connection createConnection() throws SQLException {
		logger.info("Get SMSGW connection from connection pool");
		new DBPool();
		return DBPool.getConnectionSMSGW();
	}

	public Connection getConnection() throws SQLException {
		return createConnection();
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("CAN'T CLOSE CONNECTION EX: " + e.getMessage());
			}
		}
	}

	public void rollBackTransaction(Connection connection) throws SQLException {
		if (connection != null) {
			connection.rollback();
		}
	}

	public void commitTransaction(Connection connection) throws SQLException {
		if (connection != null) {
			connection.commit();
		}
	}
}
