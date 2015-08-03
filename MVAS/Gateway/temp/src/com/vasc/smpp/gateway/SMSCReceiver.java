// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCReceiver.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.PrintStream;
import org.smpp.Session;
import org.smpp.pdu.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            Preference, Gateway

public class SMSCReceiver extends Thread
{

    public SMSCReceiver(Queue requestQueue, Queue responseQueue, Queue deliveryQueue, Session session)
    {
        this.requestQueue = null;
        this.responseQueue = null;
        this.deliveryQueue = null;
        pdu = null;
        dsm = null;
        ssmr = null;
        this.session = null;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.deliveryQueue = deliveryQueue;
        this.session = session;
    }

    private void processRequest(PDU pdu)
    {
        try
        {
            switch(pdu.getCommandId())
            {
            case 5: // '\005'
                dsm = (DeliverSM)pdu;
                if(dsm.getEsmClass() == 4)
                {
                    deliveryQueue.enqueue(pdu);
                } else
                {
                    requestQueue.enqueue(pdu);
                    System.out.println("==> " + dsm.getSourceAddr().getAddress() + "-->" + dsm.getDestAddr().getAddress() + ":" + dsm.getShortMessage());
                }
                break;

            case 259: 
                Utilities _tmp = Gateway.util;
                Utilities.log("  Data_SM --> Not processed.");
                break;

            case 6: // '\006'
                requestQueue.enqueue(pdu);
                break;

            default:
                Utilities _tmp1 = Gateway.util;
                Utilities.log("SMSCReceiver:: Unspecified SM " + pdu.debugString());
                break;
            }
        }
        catch(Exception e)
        {
            System.out.println("SMSCReceiver2: " + e.getMessage());
        }
    }

    public void run()
    {
_L2:
        pdu = session.receive(Preference.receiveTimeout);
        if(pdu != null)
        {
            try
            {
                if(pdu.isValid())
                {
                    if(pdu.isRequest())
                    {
                        org.smpp.pdu.Response response = ((Request)pdu).getResponse();
                        session.respond(response);
                        if(pdu.getCommandId() != 21)
                            processRequest(pdu);
                        else
                            System.out.println("==> " + pdu.debugString());
                    } else
                    if(pdu.isResponse())
                    {
                        if(pdu.getCommandId() != 0x80000015)
                            responseQueue.enqueue(pdu);
                    } else
                    {
                        Gateway.util;
                        Utilities.log("pdu of unknown class (not request nor response) received; Discarding " + pdu.debugString());
                    }
                } else
                {
                    System.out.println("Received an invalid pdu: " + pdu);
                }
            }
            catch(Exception e)
            {
                Gateway.util;
                Utilities.log("Receiving failed. " + e);
            }
        }
        if(Gateway.running) goto _L2; else goto _L1
_L1:
    }

    private Queue deliveryQueue;
    private DeliverSM dsm;
    private PDU pdu;
    private Queue requestQueue;
    private Queue responseQueue;
    private Session session;
    private SubmitSMResp ssmr;
}
