// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCSender.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.IOException;
import java.io.PrintStream;
import org.smpp.*;
import org.smpp.pdu.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            Gateway, Preference, DBTools

public class SMSCSender extends Thread
{

    public SMSCSender(Queue toSMSC, Gateway gateway)
    {
        this.toSMSC = null;
        this.gateway = null;
        pdu = null;
        commandId = 0;
        this.toSMSC = toSMSC;
        this.gateway = gateway;
        setPriority(6);
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public void run()
    {
        Gateway.addLiveThread(this);
          goto _L1
_L3:
        pdu = (PDU)toSMSC.dequeue();
        if(pdu.isResponse())
        {
            gateway;
            Gateway.session.respond((Response)pdu);
            this;
            sleep(30L);
        } else
        if(pdu.isRequest())
        {
            sendRequest(pdu);
            int time2sleep = toSMSC.size() <= 20 ? 1000 / Preference.maxSMPerSecond : 30;
            this;
            sleep(time2sleep);
        }
        continue; /* Loop/switch isn't completed */
        InterruptedException ex;
        ex;
        continue; /* Loop/switch isn't completed */
        ValueNotSetException ex;
        ex;
        Gateway.util;
        Utilities.log("SMSCSender:: " + ex.getMessage());
        continue; /* Loop/switch isn't completed */
        IOException ex;
        ex;
        Gateway.util;
        Utilities.log("SMSCSender:: " + ex.getMessage());
        toSMSC.enqueue(pdu);
        gateway;
        Gateway.bound = false;
        gateway.bind();
        continue; /* Loop/switch isn't completed */
        Exception ex;
        ex;
        Gateway.util;
        Utilities.log("SMSCSender:: " + ex.getMessage());
        DBTools.logMC(Preference.sourceAddressList.toString(), "SMSSender", "<-" + Preference.mobileOperator + "-> ERROR: " + ex.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_307;
        Exception e;
        e;
_L1:
        if(Gateway.running) goto _L3; else goto _L2
_L2:
        destroy();
        return;
    }

    private void sendRequest(PDU pdu)
        throws IOException
    {
        if(pdu == null)
            return;
        if(pdu.getCommandId() == 4)
        {
            try
            {
                SubmitSM request = (SubmitSM)pdu;
                String info = request.getShortMessage();
                if(info != null && info.length() > 10)
                    info = info.substring(0, 10);
                System.out.println(toSMSC.size() + "<== " + request.getDestAddr().getAddress() + "<--" + request.getSourceAddr().getAddress() + ":" + info);
                if(Preference.asynchronous)
                {
                    Gateway _tmp = gateway;
                    Gateway.session.submit(request);
                } else
                {
                    Gateway _tmp1 = gateway;
                    SubmitSMResp response = Gateway.session.submit(request);
                    Preference.messageId = response.getMessageId();
                    gateway.getResponseQueue().enqueue(response);
                }
            }
            catch(WrongSessionStateException ex)
            {
                Utilities _tmp2 = Gateway.util;
                Utilities.log("SMSCSender.sendRequest(): " + ex.getMessage());
            }
            catch(PDUException ex)
            {
                Utilities _tmp3 = Gateway.util;
                Utilities.log("SMSCSender.sendRequest(): " + ex.getMessage());
            }
            catch(TimeoutException ex)
            {
                Utilities _tmp4 = Gateway.util;
                Utilities.log("SMSCSender.sendRequest(): " + ex.getMessage());
            }
            catch(IOException ex)
            {
                throw ex;
            }
        }
    }

    private int commandId;
    private Gateway gateway;
    private PDU pdu;
    private Queue toSMSC;
}
