package com.vng.mvas.utils;

import java.sql.Connection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

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
            Logger.getLogger("appActions").error("ConnectDBManager:ConnectDBManager connectDBImpl-Object is null!");
        }
    }

    // ---------------------
    public synchronized Connection getConnection() throws Exception {
        return connectDBImpl.getConnection();
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

}
