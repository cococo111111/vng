package com.vng.mvas.utils;

import java.sql.Connection;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class DBConnectionPool implements DBConnectionPoolMBean {

    private static GenericObjectPool _connPool = null;
    Logger logger = Logger.getLogger(DBConnectionPool.class);

    public DBConnectionPool() {
        GenericObjectPool.Config conf = new GenericObjectPool.Config();
        conf.maxIdle = Integer.parseInt(System.getProperty("maxIdle","2"));;
        conf.minIdle = 1;
        conf.maxActive = Integer.parseInt(System.getProperty("maxActive","5"));;
        conf.maxWait = -1; //unlimit
        conf.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
        conf.testOnBorrow = true;
        conf.testOnReturn = false;
        conf.testWhileIdle = true;
        conf.timeBetweenEvictionRunsMillis = 10000; //10 secs
        conf.numTestsPerEvictionRun = -1; //take size of pools
        conf.minEvictableIdleTimeMillis = 600000; //10 min
        conf.softMinEvictableIdleTimeMillis = -1; //unlimit
        conf.lifo = false; //queue
        _connPool = new GenericObjectPool(new DBConnectionFactory(), conf);
    }

    public Connection getConnection() throws Exception {
        Connection conn = (Connection) _connPool.borrowObject();
        return conn;
    }

    public void returnConnection(Connection conn) {
        try {
            _connPool.returnObject(conn);
        } catch (Exception e) {
            logger.error("returnConnection error: " + e.getMessage());
        }
    }

    public void invalidateConnection(Connection conn) {
        try {
            _connPool.invalidateObject(conn);
        } catch (Exception ex) {
            Logger.getLogger("appActions").error("invalidConnection " + ex.getMessage());
        }
    }

    @Override
    public int getMaxPoolSize() {
        return _connPool.getMaxActive();
    }

    @Override
    public long getMaxQueueSize() {
        return _connPool.getMaxWait();
    }

    @Override
    public long getMaxIdle() {
        // TODO Auto-generated method stub
        return _connPool.getMaxIdle();
    }

    @Override
    public long getNumerActive() {
        // TODO Auto-generated method stub
        return _connPool.getNumActive();
    }

    @Override
    public long getNumerIdle() {
        // TODO Auto-generated method stub
        return _connPool.getNumIdle();
    }
}
