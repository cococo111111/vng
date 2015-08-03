// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CheckTimeOutWait4Response.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway, Preference, EMSData

public class CheckTimeOutWait4Response extends Thread
{

    public CheckTimeOutWait4Response(Map Wait4ResponseTable, Queue SendLogQueue)
    {
        sms = null;
        this.SendLogQueue = null;
        this.Wait4ResponseTable = null;
        this.Wait4ResponseTable = Wait4ResponseTable;
        this.SendLogQueue = SendLogQueue;
        setPriority(1);
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        try
        {
            sleep(Preference.timeOut * 60 * 1000);
        }
        catch(InterruptedException ex1)
        {
            Utilities _tmp = Gateway.util;
            Utilities.logErr(getClass().getName(), "CheckTimeOutWait4Response:" + ex1.getMessage());
        }
        while(Gateway.running) 
            try
            {
                if(Wait4ResponseTable.size() > 0)
                {
                    sleep(Preference.timeOut * 60 * 1000);
                    synchronized(Wait4ResponseTable)
                    {
                        for(Enumeration e = (new Vector(Wait4ResponseTable.keySet())).elements(); e.hasMoreElements();)
                        {
                            String key = (String)e.nextElement();
                            EMSData ems = (EMSData)Wait4ResponseTable.get(key);
                            if(ems != null && isTimeOut(ems.getSubmitDate()))
                            {
                                ems.setProcessResult(4);
                                ems.setNotes("No ACK return for this message!!!");
                                ems.setMessageType(2);
                                SendLogQueue.enqueue(ems);
                                Wait4ResponseTable.remove(key);
                            }
                        }

                    }
                } else
                {
                    sleep(Preference.timeOut * 60 * 1000);
                }
            }
            catch(Exception ex)
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.logErr(getClass().getName(), "CheckTimeOutWait4Response:" + ex.getMessage());
            }
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public boolean isTimeOut(Timestamp time)
    {
        long currTime = System.currentTimeMillis();
        return currTime - time.getTime() > (long)(Preference.timeOut * 60 * 1000);
    }

    private EMSData sms;
    private Queue SendLogQueue;
    private Map Wait4ResponseTable;
}
