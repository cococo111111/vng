// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCReceiver.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import java.io.PrintStream;
import org.smpp.Session;
import org.smpp.pdu.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Preference, Gateway

public class SMSCReceiver extends Thread
{

    public SMSCReceiver(Session session, Queue requestQueue)
    {
        pdu = null;
        dsm = null;
        this.session = null;
        this.requestQueue = null;
        this.session = session;
        this.requestQueue = requestQueue;
    }

    public void run()
    {
        while(Gateway.running) 
            try
            {
                pdu = session.receive(Preference.receiveTimeout);
                if(pdu != null && pdu.isValid())
                {
                    if(pdu.isRequest())
                    {
                        org.smpp.pdu.Response response = ((Request)pdu).getResponse();
                        session.respond(response);
                        if(pdu.getCommandId() != 21)
                            if(pdu.getCommandId() == 5)
                            {
                                dsm = (DeliverSM)pdu;
                                requestQueue.enqueue(pdu);
                            } else
                            {
                                System.out.println("Not a Deliver_SM: " + pdu.debugString());
                            }
                    } else
                    if(pdu.isResponse())
                        System.out.println("received a response(?), while expect requests only.");
                    else
                        System.out.println("pdu of unknown class (not request nor response) received; Discarding " + pdu.debugString());
                } else
                {
                    System.out.println("Received an invalid pdu!");
                }
            }
            catch(Exception ex)
            {
                System.out.println(">>>Receiver");
                System.out.println("run: " + ex.getMessage());
            }
    }

    private PDU pdu;
    private DeliverSM dsm;
    private Session session;
    private Queue requestQueue;
}
