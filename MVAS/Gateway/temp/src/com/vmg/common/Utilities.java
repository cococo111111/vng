// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package com.vmg.common;

import com.vmg.smpp.gateway.Logger;
import com.vmg.smpp.gateway.Preference;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import uk.org.primrose.GeneralException;
import uk.org.primrose.vendor.standalone.PrimroseLoader;

// Referenced classes of package com.vmg.common:
//            DateProc

public class Utilities
{

    public Utilities()
    {
        url = "t3://localhost:80";
        user = null;
        password = null;
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

    public Connection getDBConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.75.11:1521:ORA", "smpp", "smpp2003");
        }
        catch(Exception ex)
        {
            System.out.println("Utilities.getDBConnection:: " + ex.toString());
        }
        return conn;
    }

    public Connection getDBConnection(String driver, String url, String user, String password)
        throws SQLException
    {
        Connection conn = null;
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(ClassNotFoundException ex)
        {
            throw new SQLException(ex.getMessage());
        }
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

    public void cleanup(Connection con, PreparedStatement ps)
    {
        try
        {
            if(ps != null)
                ps.close();
            if(con != null)
                con.close();
        }
        catch(Exception exception) { }
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
        catch(Exception exception) { }
    }

    public void cleanup(ResultSet rs)
    {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(Exception exception) { }
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
        catch(SQLException sqlexception) { }
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

    public static byte[] readFile(String filename)
    {
        byte buffer[] = (byte[])null;
        try
        {
            FileInputStream fin = new FileInputStream(filename);
            buffer = new byte[fin.available()];
            fin.read(buffer);
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file :" + filename);
            System.exit(200);
        }
        return buffer;
    }

    public static void saveToFile(byte output[], String filename)
    {
        try
        {
            File f = new File(filename);
            FileOutputStream out = new FileOutputStream(f);
            out.write(output);
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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

    private static void openLogFile()
    {
        try
        {
            fout = new FileOutputStream("MO-Data.log", true);
        }
        catch(Exception ex)
        {
            System.out.println("Utilities.openLogFile: " + ex.getMessage());
        }
    }

    private static void openLogQueue()
    {
        try
        {
            fout = new FileOutputStream("SMPPQueueRX.log", false);
        }
        catch(Exception ex)
        {
            System.out.println("Utilities.openLogFile: " + ex.getMessage());
        }
    }

    public static void logMO(String s)
    {
        System.out.println(s);
        try
        {
            openLogFile();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            fout.write(("[" + DateProc.getDateTime24hString(time) + "]," + s + "\n").getBytes());
            fout.flush();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
        }
    }

    public static void logQueue(String s)
    {
        if(Preference.ViewConsole == 1)
            System.out.println(s);
        try
        {
            openLogQueue();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            fout.write(("[" + DateProc.getDateTime24hString(time) + "]\n" + s + "\n").getBytes());
            System.out.println("[" + DateProc.getDateTime24hString(time) + "]\n" + s + "\n");
            fout.flush();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
        }
    }

    public static void logQueueBak(String s)
    {
        System.out.println(s);
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String datetime = dateFormat.format(date);
            openLogFile("LogQueue-bak.log");
            fout.write((datetime + ":\n " + s + "\n").getBytes());
            fout.flush();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
        }
    }

    public static void logMOBak(String s)
    {
        System.out.println(s);
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String datetime = dateFormat.format(date);
            openLogFile("MObak" + datetime + ".log");
            fout.write((s + "\n").getBytes());
            fout.flush();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
        }
    }

    public static void logMOInvBak(String s)
    {
        System.out.println(s);
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String datetime = dateFormat.format(date);
            openLogFile("InvalidMO" + datetime + ".dat");
            fout.write((s + "\n").getBytes());
            fout.flush();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
        }
    }

    public static void log(String ClassName, String s)
    {
        Logger.info(ClassName, s);
    }

    public static void logConsole(String ClassName, String s)
    {
        System.out.println(s);
        Logger.info(ClassName, s);
    }

    public static void logErr(String ClassName, String s)
    {
        Logger.error(ClassName, s);
    }

    private static void openLogFile(String s)
    {
        try
        {
            System.currentTimeMillis();
            fout = new FileOutputStream(s, true);
        }
        catch(Exception ex)
        {
            System.out.println("Utilities.openLogFile: " + ex.getMessage());
        }
    }

    public Collection getLogMO()
    {
        Vector keys = new Vector();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("MO-Data.log"));
            String str;
            while((str = in.readLine()) != null) 
            {
                System.out.println(str);
                keys.addElement(str);
                logMOBak(str);
            }
            in.close();
        }
        catch(Exception e)
        {
            System.out.println("Utilities.log: " + e.getMessage());
            return null;
        }
        DeleteFile("MO-Data.log");
        return keys;
    }

    public static void DeleteFile(String fileName)
    {
        File f = new File(fileName);
        if(!f.exists())
            throw new IllegalArgumentException("Delete: no such file or directory: " + fileName);
        if(!f.canWrite())
            throw new IllegalArgumentException("Delete: write protected: " + fileName);
        if(f.isDirectory())
        {
            String files[] = f.list();
            if(files.length > 0)
                throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
        }
        boolean success = f.delete();
        if(!success)
            throw new IllegalArgumentException("Delete: deletion failed");
        else
            return;
    }

    public static void main(String args[])
    {
        Utilities util = new Utilities();
        System.out.println(util.getDBConnection());
    }

    public static void ConfigPrirose()
    {
        Logger.info("ConfigPrirose", "ConfigPrirose.start");
        java.util.List a;
        try
        {
            try
            {
                a = PrimroseLoader.load("database.cfg", true);
            }
            catch(GeneralException ex)
            {
                log("Utilities", "ConfigPrimrose:" + ex.toString());
            }
        }
        catch(Exception exp)
        {
            log("Utilities", "{Utilities}{Config Database Primrose}{Error}" + exp.getMessage());
        }
    }

    String url;
    String user;
    String password;
    static FileOutputStream fout = null;
    static FileInputStream fin = null;

}
