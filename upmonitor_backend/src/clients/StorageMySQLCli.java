/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import com.vng.jcore.common.Config;
import services.StorageMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import model.ZNode;
import org.apache.log4j.Logger;
import org.json.simple.JSONValue;

/**
 *
 * @author locth2
 */
public class StorageMySQLCli {

    private static final Logger _logger = Logger.getLogger(StorageMySQLCli.class);
    private static final Lock createLock_ = new ReentrantLock();
    private static StorageMySQLCli _instance = null;
    private static String _dbhost = "";
    private static String _dbport = "";
    private static String _dbuser = "";
    private static String _dbpass = "";
    private static String _dbname = "";

    static {
        try {
            _dbhost = Config.getParam("mysqld", "host");
            _dbport = Config.getParam("mysqld", "port");
            _dbuser = Config.getParam("mysqld", "user");
            _dbpass = Config.getParam("mysqld", "password");
            _dbname = Config.getParam("mysqld", "db");
        } catch (Exception ex) {
            _logger.error("PresentMysql.getconfig:" + ex.getMessage(), ex);
        }
    }

    public static StorageMySQLCli getInstance() {
        if (_instance == null) {
            createLock_.lock();
            try {
                if (_instance == null) {
                    _instance = new StorageMySQLCli();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instance;
    }

    private StorageMySQL getStorageMySQL() {
        StorageMySQL db = StorageMySQL.getInstance(_dbhost, _dbport, _dbname, _dbuser, _dbpass);
        return db;
    }

    private Connection getDbConnection() throws Exception {
        StorageMySQL db = this.getStorageMySQL();
        return db.getDbConnection();
    }

    private void releaseDbConnection(Connection conn) {
        StorageMySQL db = this.getStorageMySQL();
        db.releaseDbConnection(conn);
    }

    private void invalidDbConnection(Connection conn) {
        StorageMySQL db = this.getStorageMySQL();
        db.invalidDbConnection(conn);
    }

    public int getState(String path) {
        String query = "SELECT online, deletetime FROM znode WHERE path='" + path + "'";
        Connection conn = null;
        int state = 0;
        int detetetime = 0;
        try {
            conn = this.getDbConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                state = rs.getInt("online");
                detetetime = rs.getInt("deletetime");
            }
            stmt.close();
            this.releaseDbConnection(conn);
            if (state == 0) {
                return detetetime;
            }
        } catch (Exception e) {
            invalidDbConnection(conn);
            _logger.error(e.getMessage(), e);
        }
        return -1;
    }

//    public int insertNode(String name, String path, String parent, String data,
//            String child, int znodeonline, int level, int createtime, int deletetime) {
//        int rs = 0;
//        try {
//            String query = "INSERT INTO znode (znodename, znodepath, createtime, znodechild,"
//                    + " znodeonline, deletetime, znodeparent, znodelevel, znodedata)"
//                    + " value ('%s','%s','%d','%s','%d','%d','%s','%d','%s') ON DUPLICATE KEY"
//                    + " UPDATE znodename = '%s', znodepath = '%s', createtime = '%s', znodechild ='%s',"
//                    + " znodeonline = '%d', deletetime = '%d', znodeparent = '%s',"
//                    + " znodelevel = '%d', znodedata = '%s'";
//            query = String.format(query, name, path, createtime, child, znodeonline,
//                    deletetime, parent, level, data, name, path, createtime, child, znodeonline,
//                    deletetime, parent, level, data);
//
//            Connection conn = null;
//            PreparedStatement stmt = null;
//            try {
//                conn = this.getDbConnection();
//                stmt = conn.prepareStatement(query);
//                stmt.setString(9, data);
//                rs = stmt.executeUpdate();
//                stmt.close();
//            } catch (Exception ex) {
//                invalidDbConnection(conn);
//                _logger.error(ex.getMessage(), ex);
//            }
//            this.releaseDbConnection(conn);
//        } catch (Exception ex) {
//            _logger.error(ex.getMessage(), ex);
//            return -1;
//        }
//        return rs;
//    }
    public boolean updateNode(String path) {
        try {
            long deletetime = System.currentTimeMillis() / 1000L;
            String query = "update znode set online = 0, deletetime =" + (int) deletetime + " where path = '" + path + "'";
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = this.getDbConnection();
                stmt = conn.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception ex) {
                invalidDbConnection(conn);
                _logger.error(ex.getMessage(), ex);
            }
            stmt.close();
            this.releaseDbConnection(conn);
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    public boolean updateChildNode(List<String> childNodeCur, String path) {
        try {
            String childString = JSONValue.toJSONString(childNodeCur);
            String query = "update znode set child = '" + childString + "', ispersistent = 1 where path = '" + path + "'";
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = this.getDbConnection();
                stmt = conn.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception ex) {
                invalidDbConnection(conn);
                _logger.error(ex.getMessage(), ex);
            }
            stmt.close();
            this.releaseDbConnection(conn);
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }

//    public int createNode(String name, String path, String parent, String data,
//            String child, int znodeonline, int level, int createtime, int deletetime) {
    public int createNode(ZNode node) {
        String name = node.getName();
        String path = node.getPath();
        String parent = node.getParent();
        String child = node.getChild();
        int createtime = node.getCreatetime();
        int online = node.getOnline();
        int deletetime = node.getDeletetime();
        int level = node.getLevel();
        int isDel = node.getIsDel();
        String description = node.getDescription();
        String zmeContacts = node.getZmeContacts();
        String smsContacts = node.getSmsContacts();
        String mailContacts = node.getMailContacts();
        String properties = node.getProperties();
        String configuration = node.getConfiguration();
        String extras = node.getExtras();
        String serviceDependencies = node.getServiceDependencies();
        String serverIp = node.getServerIp();
        String serverName = node.getServerName();
        String urlLive = node.getUrlLive();
        String jmxString = node.getJmxString();

        int rs = 0;
        try {
//            String query = "INSERT INTO znode (name, path, online, createtime, deletetime, parent, level, child, "
//                    + "description, zmecontacts, smscontacts, mailcontacts, properties, configuration, extras, "
//                    + "servicedependencies, serverip, servername, urllive, jmx)"
//                    + "value('%s','%s','%d','%d','%d','%s','%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"
//                    + "ON DUPLICATE KEY UPDATE name='%s', path='%s', online='%d', createtime='%d', deletetime='%d', "
//                    + "parent='%s', level='%d', child='%s', description='%s', zmecontacts='%s', smscontacts='%s', "
//                    + "mailcontacts='%s', properties='%s', configuration='%s', extras='%s', servicedependencies='%s', "
//                    + "serverip='%s', servername='%s', urllive='%s', jmx='%s'";
//
////            String query = "INSERT INTO znode (name, path, createtime, child,"
////                    + " online, deletetime, parent, level, )"
////                    + " value ('%(s','%s','%d','%s','%d','%d','%s','%d','%s') ON DUPLICATE KEY"
////                    + " UPDATE znodename = '%s', znodepath = '%s', createtime = '%s', znodechild ='%s',"
////                    + " znodeonline = '%d', deletetime = '%d', znodeparent = '%s',"
////                    + " znodelevel = '%d', znodedata = '%s'";
//            query = String.format(query, name, path, online, createtime, deletetime, parent, level, child,
//                    description, zmeContacts, smsContacts, mailContacts, properties, configuration, extras,
//                    serviceDependencies, serverIp, serverName, urlLive, jmxString, name, path, online, createtime, deletetime, parent, level, child,
//                    description, zmeContacts, smsContacts, mailContacts, properties, configuration, extras,
//                    serviceDependencies, serverIp, serverName, urlLive, jmxString);

            String query = "INSERT INTO znode (name, path, online, createtime, deletetime, parent, level, child, "
                    + "description, zmecontacts, smscontacts, mailcontacts, properties, configuration, extras, "
                    + "servicedependencies, serverip, servername, urllive, jmx,isdel)"
                    + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    + "ON DUPLICATE KEY UPDATE name=?, path=?, online=?, createtime=?, deletetime=?, "
                    + "parent=?, level=?, child=?, description=?, zmecontacts=?, smscontacts=?, "
                    + "mailcontacts=?, properties=?, configuration=?, extras=?, servicedependencies=?, "
                    + "serverip=?, servername=?, urllive=?, jmx=?,isdel=?";
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = this.getDbConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, path);
                stmt.setInt(3, online);
                stmt.setInt(4, createtime);
                stmt.setInt(5, deletetime);
                stmt.setString(6, parent);
                stmt.setInt(7, level);
                stmt.setString(8, child);
                stmt.setString(9, description);
                stmt.setString(10, zmeContacts);
                stmt.setString(11, smsContacts);
                stmt.setString(12, mailContacts);
                stmt.setString(13, properties);
                stmt.setString(14, configuration);
                stmt.setString(15, extras);
                stmt.setString(16, serviceDependencies);
                stmt.setString(17, serverIp);
                stmt.setString(18, serverName);
                stmt.setString(19, urlLive);
                stmt.setString(20, jmxString);
                stmt.setInt(21, isDel);
                stmt.setString(22, name);
                stmt.setString(23, path);
                stmt.setInt(24, online);
                stmt.setInt(25, createtime);
                stmt.setInt(26, deletetime);
                stmt.setString(27, parent);
                stmt.setInt(28, level);
                stmt.setString(29, child);
                stmt.setString(30, description);
                stmt.setString(31, zmeContacts);
                stmt.setString(32, smsContacts);
                stmt.setString(33, mailContacts);
                stmt.setString(34, properties);
                stmt.setString(35, configuration);
                stmt.setString(36, extras);
                stmt.setString(37, serviceDependencies);
                stmt.setString(38, serverIp);
                stmt.setString(39, serverName);
                stmt.setString(40, urlLive);
                stmt.setString(41, jmxString);
                stmt.setInt(42, isDel);
                rs = stmt.executeUpdate();
                stmt.close();
            } catch (Exception ex) {
                invalidDbConnection(conn);
                _logger.error(ex.getMessage(), ex);
            }
            this.releaseDbConnection(conn);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            return -1;
        }
        return rs;
    }

    public ArrayList<Integer> getZmeContacts(String path) {
        ArrayList<Integer> ret = new ArrayList<>();
        String query = "SELECT zmecontacts FROM znode WHERE path='" + path + "'";
        Connection conn = null;
        try {
            conn = this.getDbConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String zmeContacts = rs.getString("zmecontacts");
                if (zmeContacts != null && !zmeContacts.isEmpty()) {
                    String[] split = zmeContacts.split(",");
                    for (String string : split) {
                        ret.add(Integer.parseInt(string.trim()));
                    }
                }
            }
            stmt.close();
            this.releaseDbConnection(conn);
        } catch (Exception e) {
            invalidDbConnection(conn);
            _logger.error(e.getMessage(), e);
        }
        return ret;
    }

    public ArrayList<String> getMailContacts(String path) {
        ArrayList<String> ret = new ArrayList<>();
        String query = "SELECT mailcontacts FROM znode WHERE path='" + path + "'";
        Connection conn = null;
        int state = 0;
        int detetetime = 0;
        try {
            conn = this.getDbConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String mailContacts = rs.getString("mailcontacts");
                if (mailContacts != null && !mailContacts.isEmpty()) {
                    String[] split = mailContacts.split(",");
                    for (String string : split) {
                        ret.add(string.trim());
                    }
                }
            }
            stmt.close();
            this.releaseDbConnection(conn);
        } catch (Exception e) {
            invalidDbConnection(conn);
            _logger.error(e.getMessage(), e);
        }
        return ret;
    }

    public ArrayList<String> getSmsContacts(String path) {
        ArrayList<String> ret = new ArrayList<>();
        String query = "SELECT smscontacts FROM znode WHERE path='" + path + "'";
        Connection conn = null;
        int state = 0;
        int detetetime = 0;
        try {
            conn = this.getDbConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String smsContacts = rs.getString("smscontacts");
                if (!smsContacts.isEmpty() && smsContacts != null) {
                    String[] split = smsContacts.split(",");
                    for (String string : split) {
                        ret.add(string.trim());
                    }
                }
            }
            stmt.close();
            this.releaseDbConnection(conn);
        } catch (Exception e) {
            invalidDbConnection(conn);
            _logger.error(e.getMessage(), e);
        }
        return ret;
    }
}
