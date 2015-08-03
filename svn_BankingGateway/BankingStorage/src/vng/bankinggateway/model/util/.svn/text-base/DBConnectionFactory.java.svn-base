package vng.bankinggateway.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

public class DBConnectionFactory implements PoolableObjectFactory {

    private Logger logger = Logger.getLogger("appActions");

    @Override
    public void activateObject(Object arg0) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroyObject(Object arg0) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = (Connection) arg0;
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger("appActions").error(e.getMessage());
        }
    }

    @Override
    public Object makeObject() throws Exception {
        // TODO Auto-generated method stub
        Connection conn = null;
        try {
            Class.forName(Config.driver).newInstance();
            conn = DriverManager.getConnection(Config.url + "/" + Config.dbName + "?useUnicode=true&characterEncoding=UTF-8",
                    Config.userName, Config.passWord);
        } catch (Exception e) {
            logger.error("Make connection error: " + e.getMessage());
            throw e;
        }
        return conn;
    }

    @Override
    public void passivateObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean validateObject(Object obj) {
        // TODO Auto-generated method stub
        Connection conn = (Connection) obj;
        boolean valid = true;
        try {
            if (conn.isClosed() || !conn.isValid(10)) {
                valid = false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            valid = false;
            Logger.getLogger("appActions").error(e.getMessage());
        }
        return valid;
    }
}
