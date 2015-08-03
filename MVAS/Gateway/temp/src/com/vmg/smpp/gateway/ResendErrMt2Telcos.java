// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResendErrMt2Telcos.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;
import java.sql.Timestamp;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway, EMSData, Preference

public class ResendErrMt2Telcos extends Thread
{

    public ResendErrMt2Telcos(Queue ResendQueue, Queue EMSQueue)
    {
        sms = null;
        this.ResendQueue = null;
        this.EMSQueue = null;
        this.ResendQueue = ResendQueue;
        this.EMSQueue = EMSQueue;
        setPriority(1);
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            try
            {
                if(ResendQueue.size() > 0)
                {
                    for(int i = 0; i < ResendQueue.size(); i++)
                    {
                        sms = (EMSData)ResendQueue.dequeue();
                        if(sms != null)
                        {
                            ResendMT(sms);
                        } else
                        {
                            Utilities _tmp = Gateway.util;
                            Utilities.log(getClass().getName(), "{Can't resend sms get from ResendQueue is null}");
                        }
                        sleep(100L);
                    }

                    sleep(Preference.timeResend * 60 * 1000);
                } else
                {
                    sleep(Preference.timeResend * 60 * 1000);
                }
            }
            catch(Exception ex)
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.logErr(getClass().getName(), "ResendErrMt2Telcos:" + ex.getMessage());
            }
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public void ResendMT(EMSData sms)
    {
        try
        {
            if(isTimeResend(sms.getSubmitDate()))
            {
                Utilities _tmp = Gateway.util;
                Utilities.log(getClass().getName(), "{ResendMT:add2 SendQueue}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{Info=" + sms.getText() + "}{Request_ID" + sms.getsRequestID() + "}");
                Timestamp time = new Timestamp(System.currentTimeMillis());
                sms.setDoneDate(time);
                EMSQueue.enqueue(sms);
            } else
            {
                ResendQueue.enqueue(sms);
            }
        }
        catch(Exception ex)
        {
            Utilities _tmp1 = Gateway.util;
            Utilities.logErr(getClass().getName(), "{ERR: ResendMT}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{Info=" + sms.getText() + "} ERR=" + ex.getMessage());
        }
    }

    public boolean isTimeResend(Timestamp time)
    {
        long currTime = System.currentTimeMillis();
        return currTime - time.getTime() > (long)(Preference.timeResend * 60 * 1000);
    }

    static final int MAX_SMS_IN_QUEUE = 1000;
    private EMSData sms;
    private Queue ResendQueue;
    private Queue EMSQueue;
}
