package com.vng.zing.credits.service;

import com.vng.jcore.common.Config;
import com.vng.jcore.dbconn.ClientManager;
import com.vng.jcore.dbconn.ManagerIF;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class MysqlService {

    private static Logger logger_ = Logger.getLogger(MysqlService.class);
    private static final Lock createLock_ = new ReentrantLock();
    private final String _driver = Config.getParam("MYSQL", "driver");
    private static final Map<String, MysqlService> _instances = new LinkedHashMap();
    private ManagerIF dbmanager = null;

    public static MysqlService getInstance() {
        String key = String.format("%s:%s:%s", new Object[]{Config.getParam("MYSQL", "host"), Config.getParam("MYSQL", "port"), Config.getParam("MYSQL", "dbname")});
        if (!_instances.containsKey(key)) {
            createLock_.lock();
            try {
                if (!_instances.containsKey(key)) {
                    _instances.put(key, new MysqlService(Config.getParam("MYSQL", "host"), Config.getParam("MYSQL", "port"), Config.getParam("MYSQL", "dbname"), Config.getParam("MYSQL", "username"), Config.getParam("MYSQL", "password")));
                }
            } finally {
                createLock_.unlock();
            }
        }
        return (MysqlService) _instances.get(key);
    }

    public MysqlService(String host, String port, String dbname, String user, String password) {
        getClass();
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8&";
        logger_.info("init connection manager with url = " + url);
        this.dbmanager = ClientManager.getInstance(_driver, url, user, password);
    }

    public Connection getDbConnection() {
        boolean retry;
        Connection conn = null;
        do {
            try {
                conn = (Connection) this.dbmanager.borrowClient();
                retry = false;
            } catch (Exception ex) {
                if (conn != null) {
                    invalidDbConnection(conn);
                }
                retry = true;
                logger_.info("Retry to get dbConnection...");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        } while (retry);
        return conn;
    }

    public void releaseDbConnection(java.sql.Connection conn) {
        this.dbmanager.returnClient(conn);
    }

    public void invalidDbConnection(java.sql.Connection conn) {
        this.dbmanager.invalidClient(conn);
    }
}
