// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBPool.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import uk.org.primrose.GeneralException;
import uk.org.primrose.vendor.standalone.PrimroseLoader;

// Referenced classes of package com.vmg.sms.process:
//            Logger

public class DBPool
{

    public DBPool()
    {
    }

    public void cleanup(Statement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
        }
        catch(SQLException e)
        {
            Util.logger.error(" Close statement:" + e.toString());
        }
        catch(Exception ex)
        {
            Util.logger.error(" Close statement:" + ex.toString());
        }
    }

    public void cleanup(PreparedStatement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
        }
        catch(SQLException e)
        {
            Util.logger.error(" Close PreparedStatement:" + e.toString());
        }
        catch(Exception ex)
        {
            Util.logger.error(" Close PreparedStatement:" + ex.toString());
        }
    }

    public void cleanup(ResultSet rs, PreparedStatement pst)
    {
        try
        {
            if(rs != null)
                rs.close();
            if(pst != null)
                pst.close();
        }
        catch(SQLException e)
        {
            Util.logger.error("cleanup ResultSet,PreparedStatement" + e.toString());
        }
        catch(Exception e)
        {
            Util.logger.error("cleanup ResultSet,PreparedStatement" + e.toString());
        }
    }

    public void cleanup(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e)
        {
            Util.logger.error("cleanup ResultSet" + e.toString());
        }
        catch(Exception e)
        {
            Util.logger.error("cleanup ResultSet" + e.toString());
        }
    }

    public void cleanup(Connection con, PreparedStatement ps)
    {
        try
        {
            if(ps != null)
                ps.close();
            if(con != null)
                con.close();
        }
        catch(SQLException e)
        {
            Util.logger.error("cleanup Connection,PreparedStatement" + e.toString());
        }
        catch(Exception e)
        {
            Util.logger.error("cleanup Connection,PreparedStatement" + e.toString());
        }
    }

    public void cleanup(Connection con)
    {
        try
        {
            if(con != null)
                con.close();
        }
        catch(SQLException e)
        {
            Util.logger.error("cleanup Connection" + e.toString());
        }
        catch(Exception e)
        {
            Util.logger.error("cleanup Connection,PreparedStatement" + e.toString());
        }
    }

    public Connection getConnectionGateway()
    {
        Connection conn = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/gateway");
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            Util.logger.crisis("getConnectionGateway Failed!" + e);
        }
        catch(Exception e)
        {
            Util.logger.crisis("getConnectionGateway Failed!" + e);
        }
        return conn;
    }

    public Connection getConnectionAlert()
    {
        Connection conn = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/alert");
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            Util.logger.crisis("getConnectionAlert Failed!" + e);
        }
        catch(Exception e)
        {
            Util.logger.crisis("getConnectionAlert Failed!" + e);
        }
        return conn;
    }

    public Connection getConnection(String dbname)
    {
        Connection conn = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/" + dbname);
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            Util.logger.crisis("getConnection Failed!" + dbname + ":" + e);
        }
        catch(Exception e)
        {
            Util.logger.crisis("getConnection Failed!" + dbname + ":" + e);
        }
        return conn;
    }

    public Connection getConnectionCMS()
    {
        Connection conn = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/cmsdb");
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            Util.logger.crisis("getConnectionCMS Failed!" + e);
        }
        catch(Exception e)
        {
            Util.logger.crisis("getConnectionCMS Failed!" + e);
        }
        return conn;
    }

    public static void ConfigDB()
    {
        try
        {
            try
            {
                java.util.List a = PrimroseLoader.load("config.cfg", true);
                Util.logger.info("ConfigPrirose - Pool:" + a);
            }
            catch(GeneralException ex)
            {
                Util.logger.crisis("ConfigPrirose Failed!" + ex.toString());
            }
        }
        catch(Exception exp)
        {
            Util.logger.crisis("{Utilities}{Config Database Proxool}{Error}" + exp.getMessage());
        }
    }
}
