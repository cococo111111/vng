// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseProcessor.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;
import java.sql.Timestamp;
import java.util.Map;
import org.smpp.pdu.PDU;
import org.smpp.pdu.SubmitSMResp;

// Referenced classes of package com.vmg.smpp.gateway:
//            ReportMsgParser, Gateway, EMSData, SMSCTools, 
//            Preference

public class ResponseProcessor extends Thread
{

    public ResponseProcessor(Queue fromSMSC, Map wait4ResponseTable, Queue SendLogQueue, Queue ResendQueue)
    {
        this.fromSMSC = null;
        this.SendLogQueue = null;
        this.ResendQueue = null;
        this.wait4ResponseTable = null;
        pdu = null;
        ssmr = null;
        seqNumber = 0;
        commandStatus = 0;
        parser = null;
        this.fromSMSC = fromSMSC;
        this.wait4ResponseTable = wait4ResponseTable;
        this.SendLogQueue = SendLogQueue;
        this.ResendQueue = ResendQueue;
        parser = new ReportMsgParser();
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            try
            {
                pdu = (PDU)fromSMSC.dequeue();
                if(pdu.isResponse())
                    processResponse(pdu);
            }
            catch(Exception e)
            {
                Utilities _tmp = Gateway.util;
                Utilities.logErr(getClass().getName(), "ResponseProcessor.run: " + e.getMessage());
            }
        Utilities _tmp1 = Gateway.util;
        Utilities.log(getClass().getName(), "{ResponseProcessor stoped}");
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private void processResponse(PDU pdu)
        throws Exception
    {
        int resend = 1;
        String sErr = "";
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if(pdu.getCommandId() == 0x80000004)
        {
            ssmr = (SubmitSMResp)pdu;
            commandStatus = ssmr.getCommandStatus();
            if(commandStatus == 0)
            {
label0:
                {
                    Gateway.util;
                    Utilities.log(getClass().getName(), "{Respond for MT}{MessageId=" + ssmr.getSequenceNumber() + "}");
                    EMSData ems;
                    synchronized(wait4ResponseTable)
                    {
                        ems = (EMSData)wait4ResponseTable.remove((new StringBuffer(String.valueOf(ssmr.getSequenceNumber()))).toString());
                        if(ems != null)
                            break label0;
                        Gateway.util;
                        Utilities.logErr(getClass().getName(), "{wait4ResponseTable: ems==null}{emsId=" + ssmr.getSequenceNumber() + "}{MessageID=" + ssmr.getMessageId() + "}");
                    }
                    return;
                }
            } else
            {
label1:
                {
                    resend = SMSCTools.CheckResend(commandStatus, ssmr.getMessageId());
                    sErr = SMSCTools.GetErrName(commandStatus, ssmr.getMessageId());
                    seqNumber = ssmr.getSequenceNumber();
                    if(seqNumber < 10)
                    {
                        Gateway.util;
                        Utilities.log(getClass().getName(), "ResponseProcessor: seqNumber < 10, =" + seqNumber);
                        return;
                    }
                    EMSData ems;
                    synchronized(wait4ResponseTable)
                    {
                        ems = (EMSData)wait4ResponseTable.remove((new StringBuffer(String.valueOf(ssmr.getSequenceNumber()))).toString());
                        if(ems != null)
                            break label1;
                        Gateway.util;
                        Utilities.log(getClass().getName(), "{processResponse: ems==null}{emsId=" + seqNumber + "}{MessageID=" + ssmr.getMessageId() + "}");
                    }
                    return;
                }
            }
        }
          goto _L1
        ems.setDoneDate(time);
        ems.setMessageId(ssmr.getMessageId());
        ems.setProcessResult(1);
        SendLogQueue.enqueue(ems);
        map;
        JVM INSTR monitorexit ;
          goto _L1
        ems.setNotes(ems.getNotes() + ":" + sErr);
        Gateway.util;
        Utilities.log(getClass().getName(), "{Response for MT}{Request_ID=" + ems.getRequestId() + "}{User_ID=" + ems.getUserId() + "}{Message_ID=" + ems.getMessageId() + "}");
        if(resend == 1 && ems.getSendNum() < Preference.NumOfRetries)
        {
            if(time.compareTo(ems.getSubmitDate()) > 0)
                ems.setSendNum(ems.getSendNum() + 1);
            Gateway.util;
            Utilities.log(getClass().getName(), "{Add MT to Resend Queue}{Request_ID=" + ems.getRequestId() + "}{User_ID=" + ems.getUserId() + "}{Message_ID=" + ems.getMessageId() + "}{Resend num=" + ems.getSendNum() + "}");
            ResendQueue.enqueue(ems);
        } else
        {
            Gateway.util;
            Utilities.log(getClass().getName(), "{MT not Resend}{Request_ID=" + ems.getRequestId() + "}{User_ID=" + ems.getUserId() + "}{Message_ID=" + ems.getMessageId() + "}{Resend num=" + ems.getSendNum() + "}");
            ems.setMessageType(2);
            SendLogQueue.enqueue(ems);
        }
        map1;
        JVM INSTR monitorexit ;
_L1:
        if(pdu.isGNack())
        {
            Gateway.util;
            Utilities.logErr(getClass().getName(), "GENERIC_NAK (not processed).");
        }
        return;
    }

    private Queue fromSMSC;
    private Queue SendLogQueue;
    private Queue ResendQueue;
    private Map wait4ResponseTable;
    private PDU pdu;
    private SubmitSMResp ssmr;
    private int seqNumber;
    private int commandStatus;
    private ReportMsgParser parser;
}
