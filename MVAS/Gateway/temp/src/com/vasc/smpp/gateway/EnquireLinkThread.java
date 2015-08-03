// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnquireLinkThread.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import java.io.PrintStream;
import org.smpp.Session;
import org.smpp.pdu.EnquireLink;
import org.smpp.pdu.EnquireLinkResp;

// Referenced classes of package com.vasc.smpp.gateway:
//            Preference, Gateway

public class EnquireLinkThread extends Thread
{

    public EnquireLinkThread(Gateway gateway)
    {
        this.gateway = null;
        this.gateway = gateway;
    }

    private void enquireLink()
    {
        EnquireLink request;
        request = new EnquireLink();
        if(!Preference.asynchronous)
            break MISSING_BLOCK_LABEL_107;
        System.out.println("<== " + request.debugString());
        Gateway.session.enquireLink(request);
        break MISSING_BLOCK_LABEL_103;
        Exception ex;
        ex;
        Gateway.util;
        Utilities.log("EnquireLinkThread.enquireLink(): " + ex);
        if(Gateway.running)
        {
            Gateway.bound = false;
            gateway.bindASync();
        }
        break MISSING_BLOCK_LABEL_308;
        System.out.println("<== " + request.debugString());
        EnquireLinkResp response = Gateway.session.enquireLink(request);
        System.out.println("==> " + response.debugString());
        break MISSING_BLOCK_LABEL_207;
        ex;
        Gateway.util;
        Utilities.log("EnquireLinkThread.enquireLink(): sessionT is closed");
        if(Gateway.running)
        {
            Gateway.bound = false;
            gateway.bindSyncTransmitter();
        }
        System.out.println("<== " + request.debugString());
        EnquireLinkResp response = Gateway.sessionr.enquireLink(request);
        System.out.println("==> " + response.debugString());
        break MISSING_BLOCK_LABEL_307;
        ex;
        Gateway.util;
        Utilities.log("EnquireLinkThread.enquireLink(): sessionR is closed");
        if(Gateway.running)
        {
            Gateway.boundr = false;
            gateway.bindSyncReceiver();
        }
        break MISSING_BLOCK_LABEL_365;
        Exception ex;
        ex;
        Gateway.util;
        Utilities.log("EnquireLinkThread.enquireLink(): " + ex);
        if(Gateway.running)
        {
            Gateway.bound = false;
            Gateway.boundr = false;
            gateway.bind();
        }
        return;
    }

    public void run()
    {
        while(Gateway.running) 
        {
            try
            {
                EnquireLinkThread _tmp = this;
                sleep(Preference.timeEnquireLink);
                if(Gateway.bound)
                    enquireLink();
            }
            catch(InterruptedException ex) { }
        }
    }

    Gateway gateway;
}
