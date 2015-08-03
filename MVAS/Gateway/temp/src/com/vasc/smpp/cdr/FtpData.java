// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpData.java

package com.vasc.smpp.cdr;

import java.io.*;
import java.util.Properties;

public class FtpData
{

    public FtpData()
    {
    }

    static byte getByteProperty(String propName, byte defaultValue)
    {
        return Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)).trim());
    }

    static int getIntProperty(String propName, int defaultValue)
    {
        return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
    }

    public static void loadProperties(String fileName)
        throws IOException
    {
        System.out.println("Reading configuration file " + fileName + "...");
        FileInputStream propsFile = new FileInputStream(fileName);
        properties.load(propsFile);
        propsFile.close();
        System.out.println("Setting default parameters...");
        FTP_SERVER = properties.getProperty("FTP_SERVER");
        FTP_USER = properties.getProperty("FTP_USER");
        FTP_PASSWORD = properties.getProperty("FTP_PASSWORD");
        FTP_PATH = properties.getProperty("FTP_PATH");
        LOCAL_FOLDER = properties.getProperty("LOCAL_FOLDER");
        SENT_FOLDER = properties.getProperty("SENT_FOLDER");
        FILE_EXTENSION = properties.getProperty("FILE_EXTENSION");
        int time = getIntProperty("SCHEDULE_TIME", SCHEDULE_TIME);
        if(time < 1)
            time = 1;
        if(time > 20)
            time = 20;
        SCHEDULE_TIME = time;
    }

    public static void main(String args[])
    {
        loadProperties("ftp2cdrserver.cfg");
        System.out.println(LOCAL_FOLDER);
        System.out.println(FTP_SERVER);
        System.out.println(SCHEDULE_TIME);
        break MISSING_BLOCK_LABEL_51;
        IOException ex;
        ex;
        System.out.println(ex.getMessage());
        return;
    }

    static String FILE_EXTENSION = ".txt";
    static String FTP_PASSWORD = "tho123";
    static String FTP_PATH = "/tho";
    static String FTP_SERVER = "";
    static String FTP_USER = "tho";
    static String LOCAL_FOLDER = ".\\CDROUT";
    static int SCHEDULE_TIME = 10;
    static String SENT_FOLDER = ".\\CDRSENT";
    private static Properties properties = new Properties();

}
