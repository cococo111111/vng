package com.vng.cpnew.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vng.cpnew.server.CPRMTServer;

/**
 * <p>
 * Description: BaseDaoImpl.
 * 
 * @author <A HREF="mailto:duyn77@yahoo.com">Duy Nguyen Duc</A>
 * @version 1.0
 */

public abstract class BaseDaoImpl {

	public BaseDaoImpl() {
	}

	protected Connection getConnection() throws SQLException {
		return CPRMTServer.getInstance().getConnection();
	}

	protected void closeConnection(Connection connection) throws SQLException {
		CPRMTServer.getInstance().closeConnection(connection);
	}

	protected void rollBackTransation(Connection connection)
			throws SQLException {
		CPRMTServer.getInstance().rollBackTransaction(connection);
	}

	protected void closePrepareStatement(PreparedStatement ps)
			throws SQLException {
		if (ps != null) {
			ps.close();
			ps = null;
		}
	}

	protected void commitTransation(Connection connection) throws SQLException {
		CPRMTServer.getInstance().commitTransaction(connection);
	}

	protected int executeUpdatePreparedStatement(PreparedStatement ps)
			throws SQLException {
		/*
		 * int noOfRowsAffected = 0; try { noOfRowsAffected =
		 * ps.executeUpdate(); }catch(SQLException ex) { throw ex; } finally {
		 * ps.close(); ps = null; } return noOfRowsAffected;
		 */
		return ps.executeUpdate();
	}

	protected ResultSet executeQueryPreparedStatement(PreparedStatement ps)
			throws SQLException {
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException ex) {
			throw ex;
		}
		return rs;
	}

}
