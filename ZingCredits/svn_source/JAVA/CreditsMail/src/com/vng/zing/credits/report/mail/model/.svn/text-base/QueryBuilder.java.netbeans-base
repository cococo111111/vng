package com.vng.zing.credits.report.mail.model;

import com.vng.zing.credits.report.mail.service.MysqlService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class QueryBuilder {

    private static final Logger _logger = Logger.getLogger(QueryBuilder.class);
    String COLS = "*";
    String WHERE = "";
    String LIM = "";
    String HAVING = "";
    String GROUP = "";
    String ORDER = "";
    Connection dbConnection;

    public QueryBuilder() {
        dbConnection = MysqlService.getInstance().getDbConnection();
    }

    public  void release() {
        MysqlService.getInstance().releaseDbConnection(dbConnection);        
    }    

    public static QueryBuilder getInstance() {
        return new QueryBuilder();
    }

    public void reset() {
        COLS = "*";
        WHERE = "";
        LIM = "";
        HAVING = "";
        GROUP = "";
        ORDER = "";
    }
    
    public void where(String condition) {
        WHERE = " WHERE " + condition;
    }

    public void having(String condition) {
        HAVING = " HAVING " + condition;
    }

    public void groupBy(String cols) {
        GROUP = " GROUP BY " + cols;
    }

    public void order(String order) {
        ORDER = " " + order;
    }

    public void limit(String limit) {
        LIM = " LIMIT " + limit;
    }

    public ResultSet select(String tables) {
        ResultSet rs = null;
        try {
            String sql = "SELECT " + COLS + " FROM " + tables + WHERE + HAVING + GROUP + ORDER + LIM;
            rs = dbConnection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return rs;
    }

    public int update(String table, Map<String, Object> data) {
        try {
            int size = data.size();
            String[] keys = new String[size];
            data.keySet().toArray(keys);       
            String sql = "UPDATE " + table + " SET ";
            for (int i = 0; i < size; ++i) {
                if (i > 0) {
                    sql += ",";
                }
                sql += keys[i] + "=" + quote(data.get(keys[i]));
            }
            sql += WHERE;
            if (dbConnection.createStatement().executeUpdate(sql) == 1) {
                return 1;
            }
            return -2;
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
            return -1;
        }        
    }

    public String quote(Object o) {
        if ("java.lang.String".equals(o.getClass().getName())) {
            return "'" + ((String) o).replace("'", "''") + "'";
        }
        return o.toString();
    }

    public int insert(String table, Map<String, Object> data) {
        try {
            int size = data.size();
            String[] keys = new String[size];
            data.keySet().toArray(keys);            
            String sql = "INSERT INTO " + table + "(";
            String values = ") VALUES (";
            for (int i = 0; i < size; ++i) {
                 if (i > 0) {
                    sql += ",";
                    values += ",";
                }
                sql +=  keys[i];
                values += quote(data.get(keys[i]));
            }
            sql +=  values + ")";
            dbConnection.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
            return -1;
        }
        return 1;
    }

    public void delete(String table) {
        try {
            String sql = "DELETE FROM " + table + WHERE;
            dbConnection.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }
}
