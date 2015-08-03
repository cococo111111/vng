// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBPool.java

package com.vasc.smpp.gateway;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import uk.org.primrose.GeneralException;
import uk.org.primrose.vendor.standalone.PrimroseLoader;

public class DBPool
{

    public DBPool()
    {
    }

    public static void ConfigDB()
    {
        java.util.List a = PrimroseLoader.load("database.config", true);
        break MISSING_BLOCK_LABEL_15;
        GeneralException ex;
        ex;
        break MISSING_BLOCK_LABEL_23;
        Exception exp;
        exp;
        return;
    }

    public static void cleanup(Connection con)
    {
        try
        {
            if(con != null)
                con.close();
        }
        catch(SQLException e) { }
        catch(Exception e) { }
    }

    public void cleanup(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e) { }
        catch(Exception ex) { }
    }

    public static void cleanup1(Connection con, PreparedStatement ps)
    {
        try
        {
            if(ps != null)
                ps.close();
            if(con != null)
                con.close();
        }
        catch(SQLException e) { }
        catch(Exception e) { }
    }

    public void cleanup1(ResultSet rs, PreparedStatement pst)
    {
        try
        {
            if(rs != null)
                rs.close();
            if(pst != null)
                pst.close();
        }
        catch(SQLException e) { }
        catch(Exception ex) { }
    }

    public void cleanup11(PreparedStatement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
        }
        catch(SQLException e) { }
        catch(Exception ex) { }
    }

    public void cleanup12(Statement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
        }
        catch(SQLException e) { }
        catch(Exception ex) { }
    }

    public Connection getConnectionAlert()
    {
        Connection conn = null;
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/alert");
        conn = ds.getConnection();
        break MISSING_BLOCK_LABEL_41;
        SQLException e;
        e;
        break MISSING_BLOCK_LABEL_41;
        Exception e;
        e;
        return conn;
    }

    public Connection getConnectionGateway()
    {
        Connection conn = null;
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/gateway");
        conn = ds.getConnection();
        break MISSING_BLOCK_LABEL_41;
        SQLException e;
        e;
        break MISSING_BLOCK_LABEL_41;
        Exception e;
        e;
        return conn;
    }

    public Connection getConnectionSequence()
    {
        Connection conn = null;
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/report");
        conn = ds.getConnection();
        break MISSING_BLOCK_LABEL_41;
        SQLException e;
        e;
        break MISSING_BLOCK_LABEL_41;
        Exception e;
        e;
        return conn;
    }

    public Connection getConnectionService()
    {
        Connection conn = null;
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/service");
        conn = ds.getConnection();
        break MISSING_BLOCK_LABEL_41;
        SQLException e;
        e;
        break MISSING_BLOCK_LABEL_41;
        Exception e;
        e;
        return conn;
    }
}
