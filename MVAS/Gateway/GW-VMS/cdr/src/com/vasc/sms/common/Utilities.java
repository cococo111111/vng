// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package com.vasc.sms.common;

import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;
import javax.naming.*;

public class Utilities
{

    public Utilities()
    {
        url = "t3://192.168.75.214:80";
        user = null;
        password = null;
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
        catch(Exception e)
        {
            log("Error closing Connection: " + e.getMessage());
        }
    }

    public void closeConnection(Connection connection, Statement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
        }
        catch(SQLException e) { }
        try
        {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) { }
    }

    public static String exceptPrefix(String mobile)
    {
        if(mobile.length() <= 3)
            return mobile;
        if(mobile.substring(0, 2).equals("04") || mobile.substring(0, 2).equals("08"))
            return mobile.substring(2);
        if(mobile.substring(0, 3).equals("084") || mobile.substring(0, 3).equals("084"))
            return mobile.substring(3);
        else
            return mobile;
    }

    public Connection getDBConnection(String user, String password, String SID)
    {
        Connection conn = null;
        Properties props = new Properties();
        props.put("user", user);
        props.put("password", password);
        Driver myDriver = (Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:oci8:@" + SID;
        conn = myDriver.connect(url, props);
        break MISSING_BLOCK_LABEL_114;
        Exception e;
        e;
        log("Utilities.getDBConnection: " + e.getMessage());
        return conn;
    }

    public Context getWebLogicContext()
        throws NamingException
    {
        Properties p = new Properties();
        p.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        p.put("java.naming.provider.url", url);
        if(user != null)
        {
            p.put("java.naming.security.principal", user);
            if(password == null)
                password = "";
            p.put("java.naming.security.credentials", password);
        }
        return new InitialContext(p);
    }

    public void log(String s)
    {
        System.out.println(s);
    }

    static final String DATA_SOURCE = "SMSDataSource";
    static final boolean VERBOSE = true;
    String password;
    String url;
    String user;
}
