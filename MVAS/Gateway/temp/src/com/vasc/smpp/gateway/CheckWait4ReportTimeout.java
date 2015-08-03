// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CheckWait4ReportTimeout.java

package com.vasc.smpp.gateway;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            Wait4Report, Gateway

public class CheckWait4ReportTimeout extends Thread
{

    public CheckWait4ReportTimeout(Map messageIdTable)
    {
        wait4ReportTable = null;
        wait4ReportTable = messageIdTable;
    }

    public void checkIfTimeout()
    {
        PrintWriter fTimeout;
        String msgId = null;
        Wait4Report wait4report = null;
        System.out.println("------------------CheckWait4ReportTimeout: begin");
        fTimeout = null;
        fTimeout = new PrintWriter(new FileOutputStream("wait4report_timeout.dat", true));
        break MISSING_BLOCK_LABEL_71;
        FileNotFoundException ex;
        ex;
        System.out.println("CheckWait4ReportTimeout: " + ex.getMessage());
        synchronized(wait4ReportTable)
        {
            for(Iterator it = wait4ReportTable.keySet().iterator(); it.hasNext();)
            {
                String msgId = (String)it.next();
                Wait4Report wait4report = (Wait4Report)wait4ReportTable.get(msgId);
                if(wait4report.isTimeout())
                {
                    wait4ReportTable.remove(msgId);
                    System.out.println("messageId:" + msgId + "(logId:" + wait4report.logId + ") is timeout");
                    if(fTimeout != null)
                    {
                        fTimeout.print(msgId + "\t");
                        fTimeout.print(wait4report.logId + "\t");
                        fTimeout.print(wait4report.time.getTime() + "\t");
                        fTimeout.println(wait4report.time);
                    }
                }
            }

            fTimeout.flush();
            fTimeout.close();
        }
        System.out.println("------------------CheckWait4ReportTimeout: end.");
        return;
    }

    public void run()
    {
_L2:
        checkIfTimeout();
        this;
        sleep(0x927c0L);
        break MISSING_BLOCK_LABEL_23;
        InterruptedException ex;
        ex;
        if(Gateway.running) goto _L2; else goto _L1
_L1:
    }

    private Map wait4ReportTable;
}
