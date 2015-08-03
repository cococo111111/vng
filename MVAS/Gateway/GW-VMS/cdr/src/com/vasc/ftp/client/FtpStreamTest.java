// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpStreamTest.java

package com.vasc.ftp.client;

import com.vasc.ftp.*;
import java.io.*;

public class FtpStreamTest
{

    public FtpStreamTest()
    {
    }

    public static void main(String args[])
    {
        FtpConnect cn;
        Ftp cl;
        FtpInputStream is;
        cn = FtpConnect.newConnect("ftp://ftp.netscape.com/");
        cn.setPassWord("eternity@matrix.com");
        cl = new Ftp();
        is = null;
        cl.connect(cn);
        FtpFile file = new FtpFile("/Welcome", cl);
        System.out.println("From: " + file.toString());
        is = new FtpInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        while((s = br.readLine()) != null) 
            System.out.println(s);
        br.close();
        break MISSING_BLOCK_LABEL_181;
        IOException e;
        e;
        System.out.println(e);
        break MISSING_BLOCK_LABEL_181;
        local;
        if(is == null)
            break MISSING_BLOCK_LABEL_174;
        is.close();
        break MISSING_BLOCK_LABEL_174;
        IOException e;
        e;
        cl.disconnect();
        JVM INSTR ret 8;
        return;
    }
}
