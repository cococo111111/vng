// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUEventListener.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.smpp.*;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vasc.smpp.gateway:
//            Gateway

public class PDUEventListener extends SmppObject
    implements ServerPDUEventListener
{

    public PDUEventListener(Queue requestQueue, Queue responseQueue, Queue deliveryQueue, Queue toSMSC)
    {
        this.requestQueue = null;
        this.responseQueue = null;
        this.deliveryQueue = null;
        this.toSMSC = null;
        pdu = null;
        dsm = null;
        ssmr = null;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
        this.deliveryQueue = deliveryQueue;
        this.toSMSC = toSMSC;
    }

    public void handleEvent(ServerPDUEvent event)
    {
        PDU pdu = event.getPDU();
        if(pdu != null && pdu.isValid())
        {
            if(pdu.isRequest())
            {
                org.smpp.pdu.Response response = ((Request)pdu).getResponse();
                toSMSC.enqueue(response);
                if(pdu.getCommandId() != 21)
                    processRequest(pdu);
            } else
            if(pdu.isResponse())
            {
                if(pdu.getCommandId() != 0x80000015)
                    responseQueue.enqueue(pdu);
            } else
            {
                Utilities _tmp = Gateway.util;
                Utilities.log("pdu of unknown class (not request nor response) received; Discarding " + pdu.debugString());
            }
        } else
        {
            System.out.println("Received an invalid pdu!");
        }
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
                Utilities.log("PDUEventListener:: Unspecified SM " + pdu.debugString());
                break;
            }
        }
        catch(Exception e)
        {
            System.out.println("PDUEventListener2: " + e.getMessage());
        }
    }

    private void saveToFile(String pduFile, PDU pdu)
    {
        Gateway.util;
        Utilities.log(" Saving PDU into file " + pduFile);
        byte b[] = pdu.getData().getBuffer();
        FileOutputStream fout = new FileOutputStream(pduFile);
        fout.write(b);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_98;
        Exception ex;
        ex;
        Gateway.util;
        Utilities.log("PDUProcessor.saveToFile:" + ex.getMessage());
        return;
    }

    private Queue deliveryQueue;
    private DeliverSM dsm;
    private PDU pdu;
    private Queue requestQueue;
    private Queue responseQueue;
    private SubmitSMResp ssmr;
    private Queue toSMSC;
}
