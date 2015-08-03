// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Untitled1.java

package com.vasc.smpp.cdr;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class Untitled1
{

    public Untitled1()
    {
    }

    public static Connection getConnection()
    {
        Connection conn;
        String url;
        String dbName;
        String driver;
        String userName;
        String password;
        conn = null;
        url = "jdbc:mysql://localhost:3306/";
        dbName = "cc_invalid?useUnicode=true&characterEncoding=UTF-8";
        driver = "com.mysql.jdbc.Driver";
        userName = "root";
        password = "itrd";
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url + dbName, userName, password);
        System.out.print("OK");
        break MISSING_BLOCK_LABEL_86;
        Exception e;
        e;
        System.out.print("?????");
        e.printStackTrace();
        conn = null;
        return conn;
    }

    public static void main(String args[])
    {
        Connection conn = getConnection();
    }
}
