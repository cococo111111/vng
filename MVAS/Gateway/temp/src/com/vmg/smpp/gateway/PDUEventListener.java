// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUEventListener.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.smpp.*;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger, PDUData, Preference

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
        if(pdu.isValid())
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
                System.out.println("pdu of unknown class (not request nor response) received; Discarding " + pdu.debugString());
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
                    Logger.info(getClass().getName(), "dsm.getEsmClass() == 0x04");
                } else
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
                    Date date = new Date();
                    String datetime = dateFormat.format(date);
                    PDUData pd = new PDUData();
                    pd.setPDU(pdu);
                    pd.setRequestID(Preference.prefix_requestid + datetime);
                    requestQueue.enqueue(pd);
                    String userid = dsm.getSourceAddr().getAddress();
                    dsm.setSourceAddr(Preference.formatUserIdMO(userid, 0));
                    String dsmLog = "{MO-comes}{Request_ID=" + Preference.prefix_requestid + datetime + "}{UserID=" + dsm.getSourceAddr().getAddress() + "}{ServiceID=" + dsm.getDestAddr().getAddress() + "}{Info=" + dsm.getShortMessage() + "}";
                    Logger.info(getClass().getName(), dsmLog);
                }
                break;

            case 259: 
                Logger.error(getClass().getName(), "  Data_SM --> Not processed.");
                break;

            case 6: // '\006'
                Logger.info(getClass().getName(), "  Data.UNBIND --> Not processed.");
                requestQueue.enqueue(pdu);
                break;

            default:
                Logger.error("processRequest: Unspecified SM " + pdu.debugString());
                break;
            }
        }
        catch(Exception e)
        {
            Logger.error(getClass().getName(), "Exception " + e.toString());
        }
    }

    private void saveToFile(String pduFile, PDU pdu)
    {
        Logger.info(" Saving PDU into file " + pduFile);
        try
        {
            byte b[] = pdu.getData().getBuffer();
            FileOutputStream fout = new FileOutputStream(pduFile);
            fout.write(b);
            fout.flush();
            fout.close();
        }
        catch(Exception ex)
        {
            Logger.error("saveToFile:" + ex.getMessage());
        }
    }

    private String assignMessageId()
    {
        String messageId = "Smsc";
        return messageId;
    }

    private Queue requestQueue;
    private Queue responseQueue;
    private Queue deliveryQueue;
    private Queue toSMSC;
    private PDU pdu;
    private DeliverSM dsm;
    private SubmitSMResp ssmr;
}
