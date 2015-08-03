/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.vng.jcore.dbconn.*;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class StorageMySQL {

    private static final Logger logger_ = Logger.getLogger(StorageMySQL.class);
    private static final Lock createLock_ = new ReentrantLock();
    private final String _driver = "com.mysql.jdbc.Driver";
    private static final Map<String, StorageMySQL> _instances = new LinkedHashMap<>();
    //private JDCConnectionManager dbmanager = null;
    private ManagerIF dbmanager = null;

    public static StorageMySQL getInstance(String host, String port, String dbname, String user, String password) {
        String key = String.format("%s:%s:%s", host, port, dbname);
        if (!_instances.containsKey(key)) {
            createLock_.lock();
            try {
                if (!_instances.containsKey(key)) {
                    _instances.put(key, new StorageMySQL(host, port, dbname, user, password));
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instances.get(key);
    }

    public StorageMySQL(String host, String port, String dbname, String user, String password) {

        String driver = this._driver;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8&";

        logger_.info("init connection manager with url = " + url);
        System.out.println("init connection manager with url = " + url);
        
        this.dbmanager = ClientManager.getInstance(driver, url, user, password);

    }

    public Connection getDbConnection() {
        int retry = 3;
        boolean ret = false;
        Connection conn = null;
        while (ret == false) {
            try {
                conn = this.dbmanager.borrowClient();
                com.mysql.jdbc.Connection mysqlcon = (com.mysql.jdbc.Connection) conn;
                mysqlcon.ping();
                ret = true;
                //logger_.info("getDbConnection - try=" + retry);
            } catch (Exception ex) {
                if (conn != null) {
                    this.invalidDbConnection(conn);
                }
                //logger_.error("exception in getDbConnection - retry=" + retry, ex);
            } finally {
                retry--;
                if (retry <= 0) {
                    break;
                }
            }
        }
        return conn;
    }

    public void releaseDbConnection(Connection conn) {
        this.dbmanager.returnClient(conn);
    }

    public void invalidDbConnection(Connection conn) {
        this.dbmanager.invalidClient(conn);
    }
//	public ResultSet query(String query){
//		Connection conn = this.getDbConnection();
//        try {
//			Statement stmt = conn.createStatement();
//
//			ResultSet rs = stmt.executeQuery(query);
//
//			logger_.debug("getAllMainInfo access db with query=" + query);
//		}
//			rs.close();
//                                              stmt.close();
//		this.releaseDbConnection(conn);
//		return rs;
//	}
//
//	catch(Exception ex
//
//
//		) {
//                                                this.invalidDbConnection(conn);
//		logger_.error("Exception in getAllMainInfo", ex);
//		return -1;
//	}
//	}
}
