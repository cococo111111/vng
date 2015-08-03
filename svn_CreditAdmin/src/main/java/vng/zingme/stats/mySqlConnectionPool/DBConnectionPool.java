package vng.zingme.stats.mySqlConnectionPool;

import java.sql.Connection;

import org.apache.commons.pool.impl.GenericObjectPool;

public class DBConnectionPool implements
		vng.zingme.stats.mySqlConnectionPool.DBConnectionPoolMBean2 {

	private static GenericObjectPool _connPool = null;

	public DBConnectionPool() {
		GenericObjectPool.Config conf = new GenericObjectPool.Config();
		conf.maxIdle = 100;
		conf.minIdle = 10;
		conf.maxActive = 499;
		conf.maxWait = -1; // unlimit
		conf.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
		conf.testOnBorrow = true;
		conf.testOnReturn = false;
		conf.testWhileIdle = true;
		conf.timeBetweenEvictionRunsMillis = 10000; // 10 secs
		conf.numTestsPerEvictionRun = -1; // take size of pools
		conf.minEvictableIdleTimeMillis = 600000; // 10 min
		conf.softMinEvictableIdleTimeMillis = -1; // unlimit
		conf.lifo = false; // queue
		_connPool = new GenericObjectPool(new DBConnectionFactory(), conf);
	}

	public Connection getConnection(long timeOut) throws Exception {
		Connection conn = null;
		try {
			conn = (Connection) _connPool.borrowObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void returnConnection(Connection conn) {
		try {
			_connPool.returnObject(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public void invalidateConnection(Connection conn) {
		try {
			_connPool.invalidateObject(conn);
		} catch (Exception ex) {
		}
	}

	public int getMaxPoolSize() {
		return _connPool.getMaxActive();
	}

	public long getMaxQueueSize() {
		return _connPool.getMaxWait();
	}

	public long getMaxIdle() {
		// TODO Auto-generated method stub
		return _connPool.getMaxIdle();
	}

	public long getNumerActive() {
		// TODO Auto-generated method stub
		return _connPool.getNumActive();
	}

	public long getNumerIdle() {
		// TODO Auto-generated method stub
		return _connPool.getNumIdle();
	}
}
