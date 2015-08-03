// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Add2EmsSendLog.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;

// Referenced classes of package com.vmg.smpp.gateway:
//            DBTools, Gateway, EMSData

public class Add2EmsSendLog extends Thread
{

    public Add2EmsSendLog(Queue InvQueue)
    {
        sms = null;
        logQueue = null;
        dbTools = null;
        Results = 0;
        logQueue = InvQueue;
        dbTools = new DBTools();
        setPriority(5);
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            try
            {
                if(logQueue.size() > 0)
                {
                    AddMT2SendLog();
                    sleep(100L);
                } else
                {
                    sleep(10000L);
                }
            }
            catch(Exception ex)
            {
                try
                {
                    Utilities _tmp = Gateway.util;
                    Utilities.log(getClass().getName(), "MoveMO2Lottery:" + ex.getMessage());
                }
                catch(Exception exception) { }
            }
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public void AddMT2SendLog()
    {
        sms = (EMSData)logQueue.dequeue();
        int logID1 = 0;
        try
        {
            logID1 = dbTools.add2EMSSendLog(sms);
            if(logID1 != 0)
            {
                Utilities _tmp = Gateway.util;
                Utilities.log(getClass().getName(), "{AddMT2Log}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{Info=" + sms.getText() + "}{Request_ID" + sms.getsRequestID() + "}{emsID=" + sms.getId() + "}");
                if(sms.getMessageType() == 1)
                {
                    if(DBTools.add2CdrQueueb(sms, "1"))
                    {
                        Utilities _tmp1 = Gateway.util;
                        Utilities.log(getClass().getName(), "{CDR write=yes}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=1}{RequestID=" + sms.getRequestId() + "}");
                    } else
                    {
                        Utilities _tmp2 = Gateway.util;
                        Utilities.logConsole(getClass().getName(), "{CDR write Err}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=1}{RequestID=" + sms.getRequestId() + "}");
                    }
                } else
                if((new StringBuffer(String.valueOf(sms.getMessageType()))).toString().startsWith("2"))
                    if(DBTools.add2CdrQueueb(sms, (new StringBuffer(String.valueOf(sms.getMessageType()))).toString()))
                    {
                        Utilities _tmp3 = Gateway.util;
                        Utilities.log(getClass().getName(), "{CDR write=yes}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=0}{RequestID=" + sms.getRequestId() + "}");
                    } else
                    {
                        Utilities _tmp4 = Gateway.util;
                        Utilities.logConsole(getClass().getName(), "{CDR write Err}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=0}{RequestID=" + sms.getRequestId() + "}");
                    }
            } else
            {
                Utilities _tmp5 = Gateway.util;
                Utilities.logErr(getClass().getName(), "{ERR: Add MT2Log and CDR}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{Info=" + sms.getText() + "}{Request_ID" + sms.getsRequestID() + "}{emsID=" + sms.getId() + "}");
            }
        }
        catch(Exception ex)
        {
            Utilities _tmp6 = Gateway.util;
            Utilities.logErr(getClass().getName(), "{ERR: Add MT2Log and CDR}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{Info=" + sms.getText() + "} ERR=" + ex.getMessage());
        }
    }

    static final int MAX_SMS_IN_QUEUE = 1000;
    private EMSData sms;
    private Queue logQueue;
    private DBTools dbTools;
    private int Results;
}
