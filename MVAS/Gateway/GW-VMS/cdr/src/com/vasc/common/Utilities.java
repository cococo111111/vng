// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package com.vasc.common;

import com.vasc.smpp.gateway.Preference;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;
import javax.naming.*;
import javax.sql.DataSource;

// Referenced classes of package com.vasc.common:
//            DateProc

public class Utilities
{

    public Utilities()
    {
        url = "t3://localhost:80";
        user = null;
        password = null;
    }

    private static int charToNibble(char c)
    {
        if('0' <= c && c <= '9')
            return c - 48;
        if('a' <= c && c <= 'f')
            return (c - 97) + 10;
        if('A' <= c && c <= 'F')
            return (c - 65) + 10;
        else
            throw new IllegalArgumentException("Invalid hex character: " + c);
    }

    public void cleanup(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(Exception e) { }
    }

    public void cleanup(PreparedStatement ps, Statement stmt)
    {
        try
        {
            if(ps != null)
                ps.close();
            if(stmt != null)
                stmt.close();
        }
        catch(Exception e) { }
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
        catch(Exception e) { }
    }

    public void closeConnection(Connection connection, Statement statement)
    {
        try
        {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) { }
    }

    public static byte[] fromHexString(String s)
    {
        int stringLength = s.length();
        if((stringLength & 1) != 0)
            throw new IllegalArgumentException("fromHexString requires an even number of hex characters");
        byte b[] = new byte[stringLength / 2];
        int i = 0;
        for(int j = 0; i < stringLength; j++)
        {
            int high = charToNibble(s.charAt(i));
            int low = charToNibble(s.charAt(i + 1));
            b[j] = (byte)(high << 4 | low);
            i += 2;
        }

        return b;
    }

    public static byte[] getBytes(Object obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        bos.close();
        byte data[] = bos.toByteArray();
        return data;
    }

    public Connection getDBConnection(String driver, String url, String user, String password)
        throws SQLException
    {
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        break MISSING_BLOCK_LABEL_36;
        ClassNotFoundException ex;
        ex;
        throw new SQLException(ex.getMessage());
        return conn;
    }

    public Connection getDBConnection()
    {
        Connection conn = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.4.5.100:1521:ORA", "smpp", "smpp2004");
        break MISSING_BLOCK_LABEL_55;
        Exception ex;
        ex;
        System.out.println("Utilities.getDBConnection:: " + ex.toString());
        return conn;
    }

    public Connection getDBConnectionAlert(String driver, String server, String database, String user, String password, String port)
        throws SQLException
    {
        Connection conn;
        String url;
        conn = null;
        url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url, user, password);
        break MISSING_BLOCK_LABEL_83;
        Exception e;
        e;
        System.out.print(e);
        conn = null;
        return conn;
    }

    public Connection getDBConnectionMySQL(String driver, String server, String database, String user, String password, String port)
        throws SQLException
    {
        Connection conn;
        String url;
        conn = null;
        url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url, user, password);
        break MISSING_BLOCK_LABEL_83;
        Exception e;
        e;
        System.out.print(e);
        conn = null;
        return conn;
    }

    public DataSource getDataSource(String strDataSourceName)
        throws NamingException
    {
        DataSource datasource = null;
        Context ic = getWebLogicContext();
        datasource = (DataSource)ic.lookup(strDataSourceName);
        return datasource;
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

    public static void log(String s)
    {
        System.out.println(s);
        if(Preference.logToFile != 1)
            break MISSING_BLOCK_LABEL_117;
        openLogFile();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        fout.write(("[" + DateProc.getDateTime24hString(time) + "] " + s + "\n").getBytes());
        fout.flush();
        break MISSING_BLOCK_LABEL_116;
        Exception e;
        e;
        System.out.println("Utilities.log: " + e.getMessage());
        return;
    }

    public static void main(String args[])
    {
        Utilities util = new Utilities();
        System.out.println(util.getDBConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@10.4.5.100:1521:ORA", "smpp", "smpp2004"));
        break MISSING_BLOCK_LABEL_34;
        SQLException ex;
        ex;
        return;
    }

    private static void openLogFile()
    {
        try
        {
            if(fout == null)
                fout = new FileOutputStream(Preference.fileToLog, true);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Utilities.openLogFile: " + ex.getMessage());
        }
    }

    public static double round(double value, int decimalPlace)
    {
        double power_of_ten;
        for(power_of_ten = 1.0D; decimalPlace-- > 0; power_of_ten *= 10D);
        return (double)Math.round(value * power_of_ten) / power_of_ten;
    }

    public static double roundEx(double value, int decimalPlace)
    {
        BigDecimal bigValue = new BigDecimal(value);
        bigValue = bigValue.setScale(decimalPlace, 4);
        return bigValue.doubleValue();
    }

    public static String toHexString(byte b[])
    {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for(int i = 0; i < b.length; i++)
        {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0xf]);
        }

        return sb.toString();
    }

    static final boolean VERBOSE = true;
    static FileOutputStream fout = null;
    static char hexChar[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    String password;
    String url;
    String user;

}
