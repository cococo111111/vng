// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpListTest.java

package com.vasc.ftp.client;

import com.vasc.ftp.*;
import com.vasc.ftp.io.CoFile;
import java.io.IOException;
import java.io.PrintStream;

public class FtpListTest
{

    public FtpListTest()
    {
    }

    public static void main(String args[])
    {
        FtpConnect conn = FtpConnect.newConnect("ftp://telsoft.vasc.com.vn/");
        conn.setUserName("tho");
        conn.setPassWord("tho123");
        conn.setPathName("tho");
        Ftp ftp = new Ftp();
        try
        {
            ftp.connect(conn);
            CoFile dir = new FtpFile(ftp.pwd(), ftp);
            CoFile fls[] = dir.listCoFiles();
            if(fls != null)
            {
                for(int n = 0; n < fls.length; n++)
                    System.out.println(fls[n].getName() + (fls[n].isDirectory() ? "/" : ""));

            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            ftp.disconnect();
        }
    }
}
