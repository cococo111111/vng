package vng.zingme.stats.mySqlConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.pool.PoolableObjectFactory;

public class DBConnectionFactory implements PoolableObjectFactory {

	public void activateObject(Object arg0) throws Exception {
		// TODO Auto-generated method stub
	}

	public void destroyObject(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = (Connection) arg0;
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	public Object makeObject() throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName(Config2.driver).newInstance();
			conn = DriverManager.getConnection(Config2.url + "/"
					+ Config2.dbName
					+ "?useUnicode=true&characterEncoding=UTF-8",
					Config2.userName, Config2.passWord);
		} catch (Exception e) {
			throw e;
		}
		return conn;
	}

	public void passivateObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
	}

	public boolean validateObject(Object obj) {
		// TODO Auto-generated method stub
		Connection conn = (Connection) obj;
		boolean valid = true;
		try {
			if (conn.isClosed() || !conn.isValid(10)) {
				valid = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			valid = false;
		}
		return valid;
	}
}
