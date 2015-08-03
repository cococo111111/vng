// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCSender.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;
import java.io.IOException;
import org.smpp.*;
import org.smpp.pdu.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway, Preference, DBTools

public class SMSCSender extends Thread
{

    public SMSCSender(Queue toSMSC, Gateway gateway)
    {
        this.toSMSC = null;
        this.gateway = null;
        pdu = null;
        this.toSMSC = toSMSC;
        this.gateway = gateway;
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            if(Gateway.bound)
                try
                {
                    pdu = (PDU)toSMSC.dequeue();
                    if(pdu.isResponse())
                    {
                        Gateway.session.respond((Response)pdu);
                        sleep(20L);
                    } else
                    if(pdu.isRequest())
                    {
                        sendRequest(pdu);
                        int time2sleep = toSMSC.size() <= 20 ? 1000 / Preference.maxSMPerSecond : 30;
                        sleep(time2sleep);
                    }
                }
                catch(InterruptedException ex1)
                {
                    Utilities _tmp = Gateway.util;
                    Utilities.log(getClass().getName(), "InterruptedException:" + ex1.getMessage());
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "InterruptedException:" + ex1.getMessage());
                    }
                    catch(Exception exception) { }
                }
                catch(ValueNotSetException ex)
                {
                    Utilities _tmp1 = Gateway.util;
                    Utilities.log(getClass().getName(), "ValueNotSetException:" + ex.getMessage());
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "ValueNotSetException:" + ex.getMessage());
                    }
                    catch(Exception exception1) { }
                }
                catch(IOException ex)
                {
                    Utilities _tmp2 = Gateway.util;
                    Utilities.log(getClass().getName(), "IOException:" + ex.getMessage());
                    toSMSC.enqueue(pdu);
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "IOException:" + ex.getMessage());
                    }
                    catch(Exception exception2) { }
                }
                catch(Exception ex)
                {
                    Utilities _tmp3 = Gateway.util;
                    Utilities.log(getClass().getName(), "Exception:" + ex.getMessage());
                    toSMSC.enqueue(pdu);
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "Exception:" + ex.getMessage());
                    }
                    catch(Exception exception3) { }
                }
        Utilities _tmp4 = Gateway.util;
        Utilities.log(getClass().getName(), "{" + getClass().getName() + " stopped}");
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private void sendRequest(PDU pdu)
        throws IOException
    {
        if(pdu == null)
            return;
        if(pdu.getCommandId() == 4)
            try
            {
                SubmitSM request = (SubmitSM)pdu;
                String info = request.getShortMessage();
                if(request.getDataCoding() == 0)
                    info = request.getShortMessage();
                Utilities _tmp = Gateway.util;
                Utilities.log(getClass().getName(), "{toSMSC size= " + toSMSC.size() + "}{User_ID= " + request.getDestAddr().getAddress() + "}{Service_ID=" + request.getSourceAddr().getAddress() + "}{Info = " + info + "}");
                if(Preference.asynchronous)
                {
                    Gateway.session.submit(request);
                } else
                {
                    SubmitSMResp response = Gateway.session.submit(request);
                    Preference.messageId = response.getMessageId();
                    gateway.getResponseQueue().enqueue(response);
                }
            }
            catch(WrongSessionStateException ex)
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.log(getClass().getName(), "WrongSessionStateException: " + ex.getMessage());
            }
            catch(PDUException ex)
            {
                Utilities _tmp2 = Gateway.util;
                Utilities.log(getClass().getName(), "PDUException: " + ex.getMessage());
            }
            catch(TimeoutException ex)
            {
                Utilities _tmp3 = Gateway.util;
                Utilities.log(getClass().getName(), "TimeoutException: " + ex.getMessage());
            }
            catch(IOException ex)
            {
                throw ex;
            }
            catch(Exception ex)
            {
                Utilities _tmp4 = Gateway.util;
                Utilities.log(getClass().getName(), "Exception1: " + ex.getMessage());
                try
                {
                    DBTools.Alert2YM(getClass().getName() + "Exception1:" + ex.getMessage());
                }
                catch(Exception exception) { }
            }
    }

    private Queue toSMSC;
    private Gateway gateway;
    private PDU pdu;
}
