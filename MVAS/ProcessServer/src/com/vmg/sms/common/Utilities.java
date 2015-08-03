// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package com.vmg.sms.common;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;

public class Utilities
{

    public Utilities()
    {
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

    public void cleanup(Statement stmt)
    {
        try
        {
            if(stmt != null)
                stmt.close();
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

    public static void main(String args[])
    {
        String a = null;
        if(a == null)
            System.out.println("AAAAAAAAAAAAAAAa");
    }

    public static String replaceWhiteLetter(String sInput)
    {
        String strTmp = sInput;
        String sReturn = "";
        boolean flag = true;
        for(int i = 0; i < sInput.length() && flag; i++)
        {
            char ch = sInput.charAt(i);
            if(ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z')
                flag = false;
            else
                strTmp = sInput.substring(i + 1);
        }

        sReturn = strTmp;
        return sReturn;
    }

    static FileOutputStream fout = null;
    static final boolean VERBOSE = true;
    static char hexChar[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };

}
