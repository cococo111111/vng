// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WriteLogQueue.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import java.util.Map;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway, Preference, Logger

public class WriteLogQueue extends Thread
{

    public WriteLogQueue(Queue toSMSC, Queue fromSMSC, Queue respondSMSC, Queue EMSQueue, Queue SendLogQueue, Queue ResendQueue, Map wait4ResponseTable)
    {
        this.toSMSC = null;
        this.fromSMSC = null;
        this.respondSMSC = null;
        this.EMSQueue = null;
        this.SendLogQueue = null;
        this.ResendQueue = null;
        this.wait4ResponseTable = null;
        this.toSMSC = toSMSC;
        this.fromSMSC = fromSMSC;
        this.respondSMSC = respondSMSC;
        this.EMSQueue = EMSQueue;
        this.SendLogQueue = SendLogQueue;
        this.ResendQueue = ResendQueue;
        this.wait4ResponseTable = wait4ResponseTable;
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
        {
            synchronized(wait4ResponseTable)
            {
                Logger.info(Preference.mobileOperator + "-toSMSQueueTX: " + toSMSC.size() + "\n" + Preference.mobileOperator + "-fromSMSQueueRX: " + fromSMSC.size() + "\n" + Preference.mobileOperator + "-RespondSMSQueueRX: " + respondSMSC.size() + "\nWait4ResponseTable: " + wait4ResponseTable.size() + "\nSendLogQueue: " + SendLogQueue.size() + "\nGetMT2Queue: " + EMSQueue.size() + "\nResendQueue: " + ResendQueue.size());
            }
            if(toSMSC.size() > 2 || fromSMSC.size() > 2 || respondSMSC.size() > 2 || SendLogQueue.size() > 2 || EMSQueue.size() > 2 || ResendQueue.size() > 2 || wait4ResponseTable.size() > 2)
                synchronized(wait4ResponseTable)
                {
                    Logger.info(Preference.mobileOperator + "-toSMSQueueTX: " + toSMSC.size() + "\n" + Preference.mobileOperator + "-fromSMSQueueRX: " + fromSMSC.size() + "\n" + Preference.mobileOperator + "-RespondSMSQueueRX: " + respondSMSC.size() + "\nWait4ResponseTable: " + wait4ResponseTable.size() + "\nSendLogQueue: " + SendLogQueue.size() + "\nGetMT2Queue: " + EMSQueue.size() + "\nResendQueue: " + ResendQueue.size());
                }
            Logger.info("-----Auto Update Log for " + Preference.Channel);
            try
            {
                sleep(60000L);
            }
            catch(InterruptedException interruptedexception) { }
        }
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private Queue toSMSC;
    private Queue fromSMSC;
    private Queue respondSMSC;
    private Queue EMSQueue;
    private Queue SendLogQueue;
    private Queue ResendQueue;
    private Map wait4ResponseTable;
}
