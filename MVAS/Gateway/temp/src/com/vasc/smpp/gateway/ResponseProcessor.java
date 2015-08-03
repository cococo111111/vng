// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseProcessor.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Map;
import org.smpp.pdu.PDU;
import org.smpp.pdu.SubmitSMResp;

// Referenced classes of package com.vasc.smpp.gateway:
//            DBTools, ReportMsgParser, Wait4Report, Gateway, 
//            Preference, SMSCTools, SMSData

public class ResponseProcessor extends Thread
{

    public ResponseProcessor(Queue fromSMSC, Map wait4reportTable)
    {
        this.fromSMSC = null;
        wait4ReportTable = null;
        pdu = null;
        ssmr = null;
        messageId = null;
        seqNumber = 0;
        commandStatus = 0;
        dbTools = null;
        parser = null;
        this.fromSMSC = fromSMSC;
        wait4ReportTable = wait4reportTable;
        dbTools = new DBTools();
        parser = new ReportMsgParser();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private void processResponse(PDU pdu)
        throws Exception
    {
        if(pdu.getCommandId() != 0x80000004)
            break MISSING_BLOCK_LABEL_367;
        ssmr = (SubmitSMResp)pdu;
        commandStatus = ssmr.getCommandStatus();
        if(commandStatus == 0)
            System.out.println("    OK!!!");
        else
            SMSCTools.printCommandStatus(commandStatus);
        seqNumber = ssmr.getSequenceNumber();
        if(seqNumber == 1)
            return;
        int n = Integer.parseInt(ssmr.getMessageId().trim(), 16);
        messageId = Integer.toString(n);
        break MISSING_BLOCK_LABEL_122;
        NumberFormatException ex;
        ex;
        messageId = ssmr.getMessageId().trim();
        BigDecimal queueId = new BigDecimal(seqNumber);
        SMSData queueData = dbTools.getSMSinSendQueue(queueId);
        if(queueData == null)
        {
            System.out.println("ResponseProcessor: queueData==null");
            return;
        }
        if(commandStatus == 0)
        {
            SMSData logData = dbTools.moveSMSFromSendQueueToSendLogEx(queueData, 1, messageId);
            if(logData == null)
            {
                System.out.println("ResponseProcessor.moveSMSFromSendQueueToSendLog(" + queueId + ") return null.");
                return;
            }
            BigDecimal logId = logData.getId();
            if(logData.getMessageType() == 1 && logData.getMoreMsgsToSend() == 0)
            {
                if(Preference.reportRequired)
                {
                    synchronized(wait4ReportTable)
                    {
                        Gateway.util;
                        Utilities.log("    (" + wait4ReportTable.size() + ")--> Add to Wait4Report_Table, logId:" + logId);
                        wait4ReportTable.put(messageId, new Wait4Report(logId));
                    }
                } else
                {
                    dbTools.add2CdrQueueEx(logData);
                }
            }
        } else
        {
            dbTools.moveSMSFromSendQueueToSendLogEx(queueData, 4);
        }
        if(pdu.isGNack())
        {
            Gateway.util;
            Utilities.log("  GENERIC_NAK (not processed).");
        }
        return;
    }

    public void run()
    {
        Gateway.addLiveThread(this);
          goto _L1
_L3:
        pdu = (PDU)fromSMSC.dequeue();
        if(pdu.isResponse())
            processResponse(pdu);
        continue; /* Loop/switch isn't completed */
        Exception e;
        e;
        dbTools;
        DBTools.logMC(Preference.sourceAddressList.toString(), "ResponseProc", "<-" + Preference.mobileOperator + "-> ERROR: " + e.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_107;
        Exception ex;
        ex;
        System.out.println("ResponseProcessor: " + e.getMessage());
_L1:
        if(Gateway.running) goto _L3; else goto _L2
_L2:
        destroy();
        return;
    }

    private int commandStatus;
    private DBTools dbTools;
    private Queue fromSMSC;
    private String messageId;
    private ReportMsgParser parser;
    private PDU pdu;
    private int seqNumber;
    private SubmitSMResp ssmr;
    private Map wait4ReportTable;
}
