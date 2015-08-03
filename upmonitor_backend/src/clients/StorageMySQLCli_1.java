///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package clients;
//
//import com.vng.jcore.common.Config;
//import services.StorageMySQL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author locth2
// */
//public class StorageMySQLCli {
//
//    String mysqldHost = Config.getParam("mysqld", "host");
//    String mysqldPort = Config.getParam("mysqld", "port");
//    String mysqldDb = Config.getParam("mysqld", "db");
//    String mysqldUser = Config.getParam("mysqld", "user");
//    String mysqldPass = Config.getParam("mysqld", "password");
//    StorageMySQL db;
//    Connection conn;
//    private static final Logger logger = Logger.getLogger(StorageMySQLCli.class);
//    private static StorageMySQLCli _instances = null;
//    private static final Lock createLock_ = new ReentrantLock();
//    
//    public StorageMySQLCli() {
//        this.db = StorageMySQL.getInstance(mysqldHost, mysqldPort, mysqldDb, mysqldUser, mysqldPass);
//    }
//    
//    public static StorageMySQLCli getInstance() {
//        
//        if (_instances == null) {
//            createLock_.lock();
//            try {
//                    _instances = new StorageMySQLCli();
//            } finally {
//                createLock_.unlock();
//            }
//        }
//        return _instances;
//    }
//
//    public int getState(String path) {
//        conn = db.getDbConnection();
//        int state = 0;
//        int detetetime = 0;
//        ResultSet rs = null;
//        try {
//            try (Statement st = conn.createStatement()) {
//                rs = st.executeQuery("Select znodeonline, deletetime from znode where znodepath='" + path + "';");
//                while (rs.next()) {
//                    state = rs.getInt("znodeonline");
//                    detetetime = rs.getInt("deletetime");
//                }
//                st.close();
//                }
//                db.releaseDbConnection(conn);
//                if (state == 0)
//                    return detetetime;
//            } catch (SQLException ex) {
//                java.util.logging.Logger.getLogger(StorageMySQLCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                logger.error(ex.getMessage(), ex);
//            }
//        return -1;
//        }
//    
//    public int executeUpdate(String querry) {
//        conn = db.getDbConnection();
//        int result = 0;
//        try {
//            try (Statement st = conn.createStatement()) {
//                result = st.executeUpdate(querry);
//            }
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(StorageMySQLCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            logger.error(ex.getMessage(), ex);
//            result = 0;
//        }
//        db.releaseDbConnection(conn);
//        return result;
//    }
//
//    public int setNodetoDb(String name, String path, String parent, String data,
//            String child, int znodeonline, int level, int createtime, int deletetime) {
//        conn = db.getDbConnection();
//        int result = 0;
//        String querry = "INSERT INTO znode (znodename, znodepath, createtime, znodechild,"
//                + " znodeonline, deletetime, znodeparent, znodelevel, znodedata)"
//                + " value ('%s','%s','%d','%s','%d','%d','%s','%d','%s') ON DUPLICATE KEY"
//                + " UPDATE znodename = '%s', znodepath = '%s', createtime = '%s', znodechild ='%s',"
//                + " znodeonline = '%d', deletetime = '%d', znodeparent = '%s',"
//                + " znodelevel = '%d', znodedata = '%s'";
//        querry = String.format(querry, name, path, createtime, child, znodeonline,
//                deletetime, parent, level, data, name, path, createtime, child, znodeonline,
//                deletetime, parent, level, data);
//
//        try {
//            try (Statement st = conn.createStatement()) {
//                result = st.executeUpdate(querry);
//            }
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(StorageMySQLCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            logger.error(ex.getMessage(), ex);
//            result = 0;
//        }
//        db.releaseDbConnection(conn);
//        return result;
//
//    }
//    
//    public static void main(String[] args) {
//        StorageMySQLCli client = StorageMySQLCli.getInstance();
//        client.executeUpdate(null);
//    }
//}
