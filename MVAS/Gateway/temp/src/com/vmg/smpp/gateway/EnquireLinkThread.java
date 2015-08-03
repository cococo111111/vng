// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnquireLinkThread.java

package com.vmg.smpp.gateway;

import org.smpp.Session;
import org.smpp.pdu.EnquireLink;
import org.smpp.pdu.EnquireLinkResp;

// Referenced classes of package com.vmg.smpp.gateway:
//            Preference, Gateway, Logger

public class EnquireLinkThread extends Thread
{

    public EnquireLinkThread(Gateway gateway)
    {
        this.gateway = null;
        this.gateway = gateway;
    }

    public void run()
    {
        while(Gateway.running) 
        {
            try
            {
                sleep(Preference.timeEnquireLink);
            }
            catch(InterruptedException interruptedexception) { }
            if(Gateway.bound)
                enquireLink();
        }
    }

    private void enquireLink()
    {
        try
        {
            EnquireLink request = new EnquireLink();
            Logger.info(getClass().getName(), "<== " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.enquireLink(request);
            } else
            {
                EnquireLinkResp response = Gateway.session.enquireLink(request);
                Logger.info(getClass().getName(), "==> " + response.debugString());
            }
        }
        catch(Exception ex)
        {
            Logger.crisis(getClass().getName(), "enquireLink: " + ex.getMessage());
            if(Gateway.running)
            {
                Gateway.bound = false;
                Gateway.boundr = false;
                Logger.info(getClass().getName(), "Start rebind....");
                gateway.bind();
            }
        }
    }

    private Gateway gateway;
}
