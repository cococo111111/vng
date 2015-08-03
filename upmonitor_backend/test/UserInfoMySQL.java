///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package upzkmonitor;
//
//import business.Util;
//import business.db.StorageMySQL;
//
//import com.vng.jcore.common.Config;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author nghiadc
// */
//public class UserInfoMySQL {
//
//    private static final Logger log = Logger.getLogger(UserInfoMySQL.class);
//    private static final Lock createLock_ = new ReentrantLock();
//    private static UserInfoMySQL _instance;
//    private static String table = "user_info";
//
//    public static UserInfoMySQL getInstance() {
//        if (_instance == null) {
//            createLock_.lock();
//            try {
//                if (_instance == null) {
//                    _instance = new UserInfoMySQL();
//                }
//            } finally {
//                createLock_.unlock();
//            }
//        }
//        return _instance;
//    }
//
//    private StorageMySQL getStorageMySQL(int uid) {
//        String part = Util.getProfileDbPart(uid);
//        String host = Config.getParam("profiledb", "host");
//        String port = Config.getParam("profiledb", "port");
//        String user = Config.getParam("profiledb", "user");
//        String pass = Config.getParam("profiledb", "pass");
//        String dbname = Config.getParam("profiledb", "dbname");
//        dbname = dbname + part;
//        StorageMySQL db = StorageMySQL.getInstance(host, port, dbname, user, pass);
//        return db;
//    }
//
//    private Connection getDbConnection(int uid) throws Exception {
//        StorageMySQL db = this.getStorageMySQL(uid);
//        return db.getDbConnection();
//    }
//
//    private void releaseDbConnection(Connection conn, int uid) {
//        StorageMySQL db = this.getStorageMySQL(uid);
//        db.releaseDbConnection(conn);
//    }
//
//    private void invalidDbConnection(Connection conn, int uid) {
//        StorageMySQL db = this.getStorageMySQL(uid);
//        db.invalidDbConnection(conn);
//    }
//
//    public boolean insertUserInfo(int userid) {
//        try {
//            String iquery = "Insert into %s(userid,lasttimelogin) "
//                    + "value (?,?)";
//            String query = String.format(iquery, table);
//            Connection conn = null;
//            int __try = 2;
//
//            PreparedStatement stmt = null;
//            while (__try > 0) {
//                try {
//
//                    conn = this.getDbConnection(userid);
//                    stmt = conn.prepareStatement(query);
//                    stmt.setInt(1, userid);
//                    stmt.setString(2, "-1");
//                    int numerr = stmt.executeUpdate();
//                    stmt.close();
//                    break;
//                } catch (Exception ex) {
//                    invalidDbConnection(conn, userid);
//                    __try--;
//                    log.error(ex.getMessage(), ex);
//                }
//            }
//            stmt.close();
//            this.releaseDbConnection(conn, userid);
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//            return false;
//        }
//
//        return true;
//    }
//
//    public boolean updateUserInfo(int userid, String data) {
//        try {
//            String uquery = "UPDATE %s SET lasttimelogin = ? "
//                    + "where userid = ?";
//            String query = String.format(uquery, table);
//            Connection conn = null;
//            int __try = 2;
//
//            PreparedStatement stmt = null;
//            while (__try > 0) {
//                try {
//
//                    conn = this.getDbConnection(userid);
//                    stmt = conn.prepareStatement(query);
//                    stmt.setString(1, data);
//                    stmt.setInt(2, userid);
//                    int numerr = stmt.executeUpdate();
//                    stmt.close();
//                    break;
//                } catch (Exception ex) {
//                    invalidDbConnection(conn, userid);
//                    __try--;
//                    log.error(ex.getMessage(), ex);
//                }
//            }
//            stmt.close();
//            this.releaseDbConnection(conn, userid);
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//            return false;
//        }
//
//        return true;
//    }
//}
