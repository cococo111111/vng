// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDRServer.java

package com.vasc.smpp.cdr;

import com.vasc.smpp.gateway.*;
import java.io.*;

// Referenced classes of package com.vasc.smpp.cdr:
//            DBScanner, FtpData, Logger

public class CDRServer extends Thread
{

    public CDRServer()
    {
        dbpool = new DBPool();
        gateway = new GatewayCDR();
        Preference.loadProperties("gateway.cfg");
        FtpData.loadProperties("ftp2cdrserver.cfg");
        break MISSING_BLOCK_LABEL_51;
        IOException e;
        e;
        Logger.info("CDRServer:", "khong tim thay file cau hinh");
        dbpool;
        DBPool.ConfigDB();
        Logger.setLogWriter("log/cdr${yyyy-MM-dd}.log");
        break MISSING_BLOCK_LABEL_72;
        IOException ex;
        ex;
        Logger.setLogLevel("info,warn,error,crisis");
        return;
    }

    private static void exit()
    {
        running = false;
        GatewayCDR _tmp = gateway;
        GatewayCDR.closeAllConnectionInPool();
        System.out.println("Stop.");
        System.exit(0);
    }

    public static void main(String args[])
    {
        (new CDRServer()).start();
        (new DBScanner()).start();
    }

    public void run()
    {
    }

    private void showMenu()
    {
        String option = "";
        try
        {
            option = keyboard.readLine();
            if("Q".equals(option.toUpperCase()))
            {
                CDRServer _tmp = this;
                exit();
            }
        }
        catch(Exception e)
        {
            Logger.info("CDRServer:", e.getMessage());
        }
    }

    DBPool dbpool;
    static GatewayCDR gateway = null;
    static BufferedReader keyboard;
    static boolean running = true;
    static boolean writing = false;

    static 
    {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
