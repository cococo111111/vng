package vng.zingme.stats.mySqlConnectionPool;

import java.sql.Connection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConnectionManager {

	private static final Lock createLock_ = new ReentrantLock();
	private static DBConnectionManager instances_ = null;
	private DBConnectionPool connectDBImpl = null;

	public static DBConnectionManager getInstance() {
		if (instances_ == null) {
			createLock_.lock();

			try {
				if (instances_ == null) {
					instances_ = new DBConnectionManager();
				}
			} finally {
				createLock_.unlock();
			}
		}
		return instances_;
	}

	private DBConnectionManager() {
		int hop_count = 10;
		while (hop_count > 0 && connectDBImpl == null) {
			connectDBImpl = new DBConnectionPool();
			--hop_count;
		}
		if (connectDBImpl == null) {
		}
	}

	// ---------------------

	public synchronized Connection getConnection(long timeOut) throws Exception {
		return connectDBImpl.getConnection(timeOut);
	}

	public synchronized void returnConnection(Connection conn) {
		if (conn != null) {
			connectDBImpl.returnConnection(conn);
		}
	}

	public synchronized void invalidateConnection(Connection conn) {
		if (conn != null) {
			connectDBImpl.invalidateConnection(conn);
		}
	}

	public static void main(String[] args) {
		try {
			Connection conn = DBConnectionManager.getInstance().getConnection(
					10000);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ex. " + e.getMessage());
			// LogUtil.error(e.toString());
		}

	}
}
