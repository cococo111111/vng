///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package blog.models;
//
//import blog.component.utils.common.Util;
//import com.google.gson.Gson;
//import com.vng.jcore.cache.lruexpire.LruExpireCache;
//import com.vng.jcore.cache.lruexpire.LruExpireCacheManager;
//import com.vng.jcore.common.Config;
//import com.vng.lib.blog.mysql.MysqlAdapter;
//import com.vng.zing.lib.util.ByteUtils;
//import com.zing.kyotocache.thrift.KyotoCacheUPool;
//import com.zing.kyotocache.thrift.ReturnType;
//import com.zing.kyotocache.thrift.ReturnValue;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import org.apache.log4j.Logger;
//import org.apache.thrift.TException;
//
///**
// *
// * @author hoannn
// */
//public class ThemeImpl {
//
//    private static final Logger _log = Logger.getLogger(ThemeImpl.class);
//    private static final Lock createLock_ = new ReentrantLock();
//    private static final String _tablename_themes = "themes";
//    private static final String _tablename_theme_cates = "theme_categories";
//    private static final String _theme_fields = "themeid,cateid,host,css_file_name,theme_name,thumb_image,theme_path,theme_type,`create`,version";
//    private static final String _theme_cate_fields = "cateid,cate_name,parent,`create`";
//    private static LruExpireCache<Integer, List<ThemeInfoStructImpl>> themes_cache = null;
//    private static long themes_expire = -1L;
//    private static LruExpireCache<String, List<Map>> themecates_cache = null;
//    private static long themecates_expire = -1L;
//    private static LruExpireCache<Integer, ThemeInfoStructImpl> themeinfo_cache = null;
//    private static long themeinfo_expire = -1L;
//    private static String mysql_host = "";
//    private static String mysql_port = "";
//    private static String mysql_user = "";
//    private static String mysql_pass = "";
//    private static String mysql_dbname = "";
//
//    private static int kyotocache_themeinfo_port = 8246;
//    private static String kkyotocache_themeinfo_host = "10.30.12.90";
//    private static KyotoCacheUPool kyotoThemeInstance = null;
//
//    private static final String KEY_THEMEINFO_CACHING = "themeinfo.%d";
//
//    static {
//
//        kyotocache_themeinfo_port = Integer.valueOf(Config.getParam("kyotocache_themeinfo", "port"));
//        kkyotocache_themeinfo_host = Config.getParam("kyotocache_themeinfo", "host");
//        String kyotoKeyService = kkyotocache_themeinfo_host + ":" + kyotocache_themeinfo_port;
//        kyotoThemeInstance = KyotoCacheUPool.getInstance(ThemeImpl.class.toString(), kyotoKeyService, kyotoKeyService);
//
//        mysql_host = Config.getParam("blogdb", "host");
//        mysql_port = Config.getParam("blogdb", "port");
//        mysql_user = Config.getParam("blogdb", "username");
//        mysql_pass = Config.getParam("blogdb", "password");
//        mysql_dbname = Config.getParam("blogdb", "dbname");
//
//        themes_cache = LruExpireCacheManager.getCache("themes_cache", Integer.valueOf(Config.getParam("localcache", "themes_item")));
//        themes_expire = Long.valueOf(Config.getParam("localcache", "themes_expire"));
//
//        themeinfo_cache = LruExpireCacheManager.getCache("themeinfo_cache", Integer.valueOf(Config.getParam("localcache", "themeinfo_item")));
//        themeinfo_expire = Long.valueOf(Config.getParam("localcache", "themeinfo_expire"));
//
//        themecates_cache = LruExpireCacheManager.getCache("themecates_cache", Integer.valueOf(Config.getParam("localcache", "themecates_item")));
//        themecates_expire = Long.valueOf(Config.getParam("localcache", "themecates_expire"));
//    }
//
//    private ThemeImpl() {
//    }
//
//    public static ThemeImpl getInstance() {
//        return ThemeImpl.ThemeImplHolder.INSTANCE;
//    }
//
//    private static class ThemeImplHolder {
//
//        private static final ThemeImpl INSTANCE = new ThemeImpl();
//    }
//
//    private MysqlAdapter getStorageMySQL() {
//        MysqlAdapter db = MysqlAdapter.getInstance(mysql_host, mysql_port, mysql_dbname, mysql_user, mysql_pass);
//        return db;
//    }
//
//    private Connection getDbConnection() throws Exception {
//        MysqlAdapter db = getStorageMySQL();
//        return db.getDbConnection();
//    }
//
//    private void releaseDbConnection(Connection conn) {
//        MysqlAdapter db = getStorageMySQL();
//        db.releaseDbConnection(conn);
//    }
//
//    private void invalidDbConnection(Connection conn) {
//        MysqlAdapter db = getStorageMySQL();
//        db.invalidDbConnection(conn);
//    }
//
//    public void warmupThemes(int cateid) {
//        themes_cache.remove(cateid);
//    }
//
//    public void warmupThemeInfo(int themeid) {
//        themeinfo_cache.remove(themeid);
//    }
//
//    private String getKeyThemeCates() {
//        return "bthemec";
//    }
//
//    public void warmupThemeCates() {
//        themecates_cache.remove(this.getKeyThemeCates());
//    }
//
//    public void loadAllThemes() {
//        try {
//            this.getAllThemeFromMysql();
//        } catch (Exception e) {
//            _log.error("loadAllThemes:" + e.getMessage(), e);
//        }
//
//    }
//
//    private void getAllThemeFromMysql() {
//        try {
//            List<ThemeInfoStructImpl> allTheme = null;
//            List<ThemeInfoStructImpl> themeCate = null;
//            Connection db = getDbConnection();
//            String query = "SELECT " + _theme_fields + " FROM " + _tablename_themes;
//            try (Statement st = db.createStatement()) {
//                ResultSet result = st.executeQuery(query);
//
//                if (result != null) {
//                    allTheme = new ArrayList<>();
//
//                    while (result.next()) {
//                        int themeid = result.getInt("themeid");
//                        ThemeInfoStructImpl themeInfo = new ThemeInfoStructImpl();
//                        themeInfo.themeid = themeid;
//                        themeInfo.cateid = result.getInt("cateid");
//                        themeInfo.host = new String(result.getBytes("host"));
//                        themeInfo.css_file_name = result.getString("css_file_name");
//                        themeInfo.theme_name = result.getString("theme_name");
//                        themeInfo.thumb_image = result.getString("thumb_image");
//                        themeInfo.theme_path = result.getString("theme_path");
//                        themeInfo.theme_type = result.getInt("theme_type");
//                        themeInfo.create = result.getInt("create");
//                        themeInfo.version = result.getString("version");
//                        //set cache info
//
//                        setThemeCache(themeInfo);
//
//                        allTheme.add(themeInfo);
//
//                        if (themes_cache.get(result.getInt("cateid")) != null) {
//                            themeCate = themes_cache.get(result.getInt("cateid"));
//                            themeCate.add(themeInfo);
//                            themes_cache.put(result.getInt("cateid"), themeCate);
//                        } else {
//                            themeCate = new ArrayList<>();
//                            themeCate.add(themeInfo);
//                            themes_cache.put(result.getInt("cateid"), themeCate);
//                        }
//                    }
//                    //set cache all
//                    themes_cache.put(0, allTheme, themes_expire);
//
//                }
//            }
//            this.releaseDbConnection(db);
//
//        } catch (Exception ex) {
//            _log.error("getAllThemeFromMysql:" + ex.getMessage(), ex);
//        }
//    }
//
//    public ThemeInfoStructImpl getThemeInfo(int themeid) {
//        ThemeInfoStructImpl themeInfo = null;
//        try {
//            themeInfo = getThemeCache(themeid);
//            if (themeInfo == null) {
//                themeInfo = this.getThemeInfoMysql(themeid);
////                if (themeInfo != null) {
////                    themeinfo_cache.put(themeid, themeInfo, themeinfo_expire);
////                } else {
////                    themeinfo_cache.put(themeid, new ThemeInfoStructImpl(), 3600000);
////                    themeInfo = null;
////                }
//            }
//
//        } catch (Exception ex) {
//            _log.error("getThemeInfo - themeid:" + themeid + "  - " + ex.getMessage(), ex);
//        }
//        return themeInfo;
//    }
//
//    private ThemeInfoStructImpl getThemeInfoMysql(int themeid) throws Exception {
//        ThemeInfoStructImpl themeInfo = null;
//        Connection db = getDbConnection();
//        try {
//            String query = "SELECT " + _theme_fields + " FROM " + _tablename_themes + " WHERE themeid = " + themeid;
//            Statement st = db.createStatement();
//            ResultSet result = st.executeQuery(query);
//
//            if (result != null) {
//                if (result.next()) {
//                    themeInfo = new ThemeInfoStructImpl();
//                    themeInfo.themeid = result.getInt("themeid");
//                    themeInfo.cateid = result.getInt("cateid");
//                    themeInfo.host = new String(result.getBytes("host"));
//                    themeInfo.css_file_name = result.getString("css_file_name");
//                    themeInfo.theme_name = result.getString("theme_name");
//                    themeInfo.thumb_image = result.getString("thumb_image");
//                    themeInfo.theme_path = result.getString("theme_path");
//                    themeInfo.theme_type = result.getInt("theme_type");
//                    themeInfo.create = result.getInt("create");
//                    themeInfo.version = result.getString("version");
//                    setThemeCache(themeInfo);
//                }
//                st.close();
//                this.releaseDbConnection(db);
//            } else {
//                this.releaseDbConnection(db);
//            }
//        } catch (SQLException ex) {
//            this.releaseDbConnection(db);
//            _log.error("getThemeInfo:" + ex.getMessage(), ex);
//        }
//        return themeInfo;
//    }
//
//    public List<Map> getListThemeCates() {
//        List<Map> result = null;
//        try {
//            String key = this.getKeyThemeCates();
//            result = themecates_cache.get(key);
//            if (result == null) {
//                result = this._getListThemeCatesConfig();
//                if (result != null) {
//                    themecates_cache.put(key, result, themecates_expire);
//                }
//            }
//        } catch (Exception ex) {
//            _log.error("getListThemeCates:" + ex.getMessage(), ex);
//        }
//        return result;
//    }
//
//    //Hoannn
//    //ToDo change to mysql status
//    //
//    private List<Map> _getListThemeCatesConfig() throws Exception {
//        List<Map> rs = null;
//        try {
//            List<Map> data = new ArrayList<>();
//            int total = Integer.valueOf(Config.getParam("themecates", "total"));
//            for (int i = total; i > 0; i--) {
//                HashMap item = new HashMap();
//                String[] strArr = Config.getParam("themecates", "" + i).split(",");
//                item.put("cateid", strArr[0]);
//                item.put("cate_name", strArr[1]);
//                data.add(item);
//            }
//            return data;
//        } catch (NumberFormatException ex) {
//            _log.error("_getListThemeCatesConfig:" + ex.getMessage(), ex);
//        }
//        return rs;
//    }
//
//    public List<ThemeInfoStructImpl> getListThemeByCate(int page, int limit, int cateid) {
//        List<ThemeInfoStructImpl> result = null;
//        try {
//            result = themes_cache.get(cateid);
//            if (result == null) {
//                result = this.getListThemeByCateMysql(cateid);
//                if (result != null) {
//                    themes_cache.put(cateid, result, themes_expire);
//                }
//            }
//            if (result != null && result.size() > 0) {
//
//                int start = (page - 1);
//                int total = result.size();
//                int from = start * limit;
//                int to = start * limit + limit;
//                if (to > total) {
//                    to = total;
//                }
//                result = result.subList(from, to);
//            }
//        } catch (Exception ex) {
//            _log.error("getListThemeByCate:" + ex.getMessage(), ex);
//        }
//        return result;
//    }
//
//    private List<ThemeInfoStructImpl> getListThemeByCateMysql(int cateid) throws Exception {
//        List<ThemeInfoStructImpl> rs = null;
//        Connection db = getDbConnection();
//        try {
//            String where = "";
//            if (cateid > 0) {
//                where = " WHERE cateid = " + cateid;
//            }
//            String query = "SELECT " + _theme_fields + " FROM " + _tablename_themes + where + " ORDER BY themeid DESC";
//            Statement st = db.createStatement();
//            ResultSet result = st.executeQuery(query);
//            if (result != null) {
//                rs = new ArrayList<>();
//                while (result.next()) {
//                    ThemeInfoStructImpl themeInfo = new ThemeInfoStructImpl();
//                    themeInfo.themeid = result.getInt("themeid");
//                    themeInfo.cateid = result.getInt("cateid");
//                    themeInfo.host = new String(result.getBytes("host"));
//                    themeInfo.css_file_name = result.getString("css_file_name");
//                    themeInfo.theme_name = result.getString("theme_name");
//                    themeInfo.thumb_image = result.getString("thumb_image");
//                    themeInfo.theme_path = result.getString("theme_path");
//                    themeInfo.theme_type = result.getInt("theme_type");
//                    themeInfo.create = result.getInt("create");
//                    themeInfo.version = result.getString("version");
//
//                    rs.add(themeInfo);
//
//                    if (getThemeCache(themeInfo.themeid) == null) {
//                        setThemeCache(themeInfo);
//                    }
//
//                }
//                st.close();
//                this.releaseDbConnection(db);
//            } else {
//                this.releaseDbConnection(db);
//                return rs;
//            }
//        } catch (SQLException ex) {
//            this.releaseDbConnection(db);
//            _log.error("_getListThemeByCateMysql:" + ex.getMessage(), ex);
//        }
//        return rs;
//    }
//
//    public int getTotalThemeByCate(int cateid) {
//        List<ThemeInfoStructImpl> result = null;
//        int total = 0;
//        try {
//            result = themes_cache.get(cateid);
//            if (result == null) {
//                result = this.getListThemeByCateMysql(cateid);
//                if (result != null) {
//                    themes_cache.put(cateid, result, themes_expire);
//                }
//            }
//            if (result != null && result.size() > 0) {
//                total = result.size();
//            }
//        } catch (Exception ex) {
//            _log.error("getListThemeByCate:" + ex.getMessage(), ex);
//        }
//        return total;
//    }
//
//    private int _insertThemeMysql(int themeid, int cateid, String host, String css_file_name, String theme_name, String thumb_image, String theme_path, String theme_type, String version) throws Exception {
//        int result = -1;
//        Connection db = getDbConnection();
//        try {
//            int create = (int) (System.currentTimeMillis() / 1000);
//
//            String query = "INSERT INTO " + _tablename_themes + "(themeid,`cateid`,host,css_file_name,theme_name,thumb_image,theme_path,theme_type,`create`,version) VALUES(?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement preparedStmt = db.prepareStatement(query);
//            preparedStmt.setInt(1, themeid);
//            preparedStmt.setInt(2, cateid);
//            preparedStmt.setString(3, host);
//            preparedStmt.setString(4, css_file_name);
//            preparedStmt.setString(5, theme_name);
//            preparedStmt.setString(6, thumb_image);
//            preparedStmt.setString(7, theme_path);
//            preparedStmt.setString(8, theme_type);
//            preparedStmt.setInt(9, create);
//            preparedStmt.setString(10, version);
//            boolean result2 = preparedStmt.execute();
//            if (result2) {
//                this.releaseDbConnection(db);
//                return 1;
//            }
//            db.close();
//            this.releaseDbConnection(db);
//        } catch (SQLException ex) {
//            this.releaseDbConnection(db);
//            _log.error("insertTheme:" + ex.getMessage());
//        }
//        return result;
//    }
//
//    private int _updateThemeMysql(int themeid, int cateid, String host, String css_file_name, String theme_name, String thumb_image, String theme_path, String theme_type, String version) throws Exception {
//        int result = -1;
//        Connection db = getDbConnection();
//        try {
//            int create = (int) (System.currentTimeMillis() / 1000);
//
//            String query = "UPDATE " + _tablename_themes + " SET `cateid`=?, host=?, css_file_name=?, theme_name=?, thumb_image=?, theme_path=?, theme_type=?, `create`=?, version=?  WHERE themeid = ?";
//            PreparedStatement preparedStmt = db.prepareStatement(query);
//            preparedStmt.setInt(1, cateid);
//            preparedStmt.setString(2, host);
//            preparedStmt.setString(3, css_file_name);
//            preparedStmt.setString(4, theme_name);
//            preparedStmt.setString(5, thumb_image);
//            preparedStmt.setString(6, theme_path);
//            preparedStmt.setString(7, theme_type);
//            preparedStmt.setInt(8, create);
//            preparedStmt.setString(9, version);
//            preparedStmt.setInt(10, themeid);
//
//            boolean result2 = preparedStmt.execute();
//            if (result2) {
//                this.releaseDbConnection(db);
//                return 1;
//            }
//            db.close();
//            this.releaseDbConnection(db);
//        } catch (SQLException ex) {
//            this.releaseDbConnection(db);
//            _log.error("updateTheme:" + ex.getMessage(), ex);
//            return result;
//        }
//        return result;
//    }
//
//    private int _deleteThemeMysql(int themeid) throws Exception {
//        int result = -1;
//        Connection db = getDbConnection();
//        try {
//
//            String query = "DELETE FROM " + _tablename_themes + " WHERE themeid = ?";
//            PreparedStatement preparedStmt = db.prepareStatement(query);
//            preparedStmt.setInt(1, themeid);
//            result = preparedStmt.executeUpdate();
//            this.releaseDbConnection(db);
//        } catch (SQLException ex) {
//            this.releaseDbConnection(db);
//            _log.error("deleteTheme:" + ex.getMessage(), ex);
//            return result;
//        }
//        return result;
//    }
//
//    private ThemeInfoStructImpl getThemeCache(int themeId) {
//        ThemeInfoStructImpl themeinfo = null;
//        try {
//            if (themeId > 0) {
//                //uu tien doc local cache truoc
//            themeinfo = themeinfo_cache.get(themeId);
//                if (themeinfo == null) {
//
//                    byte[] key = ByteUtils.getBytes(String.format(KEY_THEMEINFO_CACHING, themeId));
//                    ReturnValue returnVal = kyotoThemeInstance.getCache(ByteBuffer.wrap(key));
//                    if (returnVal.Type == ReturnType.IN_CACHE) {
//
//                        Gson gson = new Gson();
//                        byte[] data = returnVal.getData();
//                        themeinfo = gson.fromJson(new String(data, "UTF-8"), ThemeInfoStructImpl.class);
//
//                        _log.error("getThemeCache: hitcache - themeid:" + themeId);
//
//                    }
//
//                }
//            }
//        } catch (IOException | TException ex) {
//            _log.error("getThemeCache:" + ex.getMessage(), ex);
//        }
//
//        return themeinfo;
//    }
//
//    private void setThemeCache(ThemeInfoStructImpl themeinfo) {
//
//        if (themeinfo != null && themeinfo.themeid > 0) {
//            try {
//                themeinfo_cache.put(themeinfo.themeid, themeinfo, themeinfo_expire);
//                Gson gson = new Gson();
//
//                String stheme = gson.toJson(themeinfo);
//                byte[] key = ByteUtils.getBytes(String.format(KEY_THEMEINFO_CACHING, themeinfo.themeid));
//                byte[] value = stheme.getBytes("UTF-8");
//
//                kyotoThemeInstance.setCacheOw(ByteBuffer.wrap(key), ByteBuffer.wrap(value));
//
//            } catch (IOException | TException e) {
//                _log.error("setThemeCache: hitcache - themeid:" + themeinfo.themeid);
//            }
//
//        }
//    }
//
//    public String statsTheme() {
//        String content;
//
//        NumberFormat formatter = new DecimalFormat("#0.00");
//        long req = themes_cache.getRequests();
//        long hits = themes_cache.getHits();
//        long totalitems = themes_cache.getSize();
//        long size = themes_cache.getCapacity();
//        double ratioHits = 0.0D;
//        if (req != 0L) {
//            ratioHits = hits * 100.0D / req;
//        }
//        content = "total req:\t\t" + themes_cache.getRequests() + "<br>";
//        content = content + "total hits:\t\t" + themes_cache.getHits() + "<br>";
//        content = content + "% hits:\t\t" + formatter.format(ratioHits) + "%<br>";
//        content = content + "cur items:\t\t" + totalitems + "<br>";
//        content = content + "max items:\t\t" + size + "<br>";
//        return content;
//    }
//
//    public String statsThemeInfo() {
//        String content = "";
//
//        NumberFormat formatter = new DecimalFormat("#0.00");
//        long req = themeinfo_cache.getRequests();
//        long hits = themeinfo_cache.getHits();
//        long totalitems = themeinfo_cache.getSize();
//        long size = themeinfo_cache.getCapacity();
//        double ratioHits = 0.0D;
//        if (req != 0L) {
//            ratioHits = hits * 100.0D / req;
//        }
//        content = "total req:\t\t" + themeinfo_cache.getRequests() + "<br>";
//        content = content + "total hits:\t\t" + themeinfo_cache.getHits() + "<br>";
//        content = content + "% hits:\t\t" + formatter.format(ratioHits) + "%<br>";
//        content = content + "cur items:\t\t" + totalitems + "<br>";
//        content = content + "max items:\t\t" + size + "<br>";
//        return content;
//    }
//
//    public String statsThemeCate() {
//        String content = "";
//
//        NumberFormat formatter = new DecimalFormat("#0.00");
//        long req = themecates_cache.getRequests();
//        long hits = themecates_cache.getHits();
//        long totalitems = themecates_cache.getSize();
//        long size = themecates_cache.getCapacity();
//        double ratioHits = 0.0D;
//        if (req != 0L) {
//            ratioHits = hits * 100.0D / req;
//        }
//        content = "total req:\t\t" + themecates_cache.getRequests() + "<br>";
//        content = content + "total hits:\t\t" + themecates_cache.getHits() + "<br>";
//        content = content + "% hits:\t\t" + formatter.format(ratioHits) + "%<br>";
//        content = content + "cur items:\t\t" + totalitems + "<br>";
//        content = content + "max items:\t\t" + size + "<br>";
//        return content;
//    }
//}
