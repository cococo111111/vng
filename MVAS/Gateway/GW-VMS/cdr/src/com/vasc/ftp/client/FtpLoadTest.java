// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpLoadTest.java

package com.vasc.ftp.client;

import com.vasc.ftp.*;
import com.vasc.ftp.io.*;
import java.io.IOException;
import java.io.PrintStream;

public class FtpLoadTest
{

    public FtpLoadTest()
    {
    }

    public static void main(String args[])
    {
        FtpConnect conn;
        Ftp ftp;
        conn = FtpConnect.newConnect("ftp://telsoft.vasc.com.vn/");
        conn.setUserName("tho");
        conn.setPassWord("tho123");
        conn.setPathName("tho");
        ftp = new Ftp();
        ftp.connect(conn);
        CoFile file = new LocalFile("D:\\Tho\\", "b.txt");
        System.out.println("From: " + file.toString());
        CoFile to = new FtpFile("b.txt", ftp);
        System.out.println("To:   " + to.toString());
        System.out.println("Load: " + CoLoad.copy(to, file));
        break MISSING_BLOCK_LABEL_192;
        IOException e;
        e;
        System.out.println(e);
        break MISSING_BLOCK_LABEL_192;
        local;
        ftp.disconnect();
        JVM INSTR ret 6;
        return;
    }
}
