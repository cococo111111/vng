// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDRServer.java

package com.vasc.smpp.cdr;

import com.vasc.smpp.gateway.Gateway;
import com.vasc.smpp.gateway.Preference;
import java.io.*;

// Referenced classes of package com.vasc.smpp.cdr:
//            FtpData

public class CDRServer extends Thread
{

    public CDRServer()
    {
        gateway = new Gateway();
        Preference.loadProperties("gateway.cfg");
        FtpData.loadProperties("ftp2cdrserver.cfg");
        break MISSING_BLOCK_LABEL_41;
        IOException e;
        e;
        System.out.println("CDRServer: khong tim thay file cau hinh ");
        gateway;
        Gateway.addMoreConnection2Pool(1);
        return;
    }

    private static void exit()
    {
        running = false;
        Gateway _tmp = gateway;
        Gateway.closeAllConnectionInPool();
        System.out.println("Stop.");
        System.exit(0);
    }

    public void run()
    {
        while(running) 
            showMenu();
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
            System.out.println("CDRServer::" + e.getMessage());
        }
    }

    static Gateway gateway = null;
    static BufferedReader keyboard;
    static boolean running = true;

    static 
    {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
