// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportProcessor.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Map;
import org.smpp.pdu.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            DBTools, ReportMsgParser, Wait4Report, DBException, 
//            Gateway, SMSData

public class ReportProcessor extends Thread
{

    public ReportProcessor(Queue fromSMSC, Map messageIdTable)
    {
        this.fromSMSC = null;
        wait4ReportTable = null;
        pdu = null;
        dsm = null;
        userId = null;
        serviceId = null;
        info = null;
        dbTools = null;
        parser = null;
        this.fromSMSC = fromSMSC;
        wait4ReportTable = messageIdTable;
        dbTools = new DBTools();
        parser = new ReportMsgParser();
    }

    private String buildMobileOperator(String userId)
    {
        String temp = userId;
        String result = null;
        if(temp == null || "".equals(temp))
            result = "null";
        else
        if(temp.startsWith("8490") || temp.startsWith("+8490") || temp.startsWith("8493") || temp.startsWith("+8493"))
            result = "VMS";
        else
        if(temp.startsWith("8491") || temp.startsWith("+8491") || temp.startsWith("8494") || temp.startsWith("+8494"))
            result = "GPC";
        else
        if(temp.startsWith("8498") || temp.startsWith("+8498") || temp.startsWith("8497") || temp.startsWith("+8497"))
            result = "VIETEL";
        else
        if(temp.startsWith("8495") || temp.startsWith("+8495"))
            result = "SFONE";
        else
        if(temp.startsWith("8492") || temp.startsWith("+8492"))
            result = "HTC";
        else
        if(temp.startsWith("8496") || temp.startsWith("+8496"))
            result = "EVN";
        else
            result = "EVN";
        return result;
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private boolean isValidAddress(String userId, String serviceId)
    {
        if(userId == null || "".equals(userId) || serviceId == null || "".equals(serviceId))
        {
            Utilities _tmp = Gateway.util;
            Utilities.log("  Source/dest address NULL --> PDU discarded");
            return false;
        }
        if(userId.startsWith("84") || userId.startsWith("+84"))
        {
            return true;
        } else
        {
            Utilities _tmp1 = Gateway.util;
            Utilities.log("  Invalid source address --> PDU discarded");
            return false;
        }
    }

    private void processReport(PDU pdu)
        throws DBException
    {
        dsm = (DeliverSM)pdu;
        userId = dsm.getSourceAddr().getAddress();
        serviceId = dsm.getDestAddr().getAddress();
        info = dsm.getShortMessage();
        if(info == null)
            info = "null";
        if(!isValidAddress(userId, serviceId))
            return;
        userId = removePlusSign(userId);
        serviceId = rebuildServiceId(serviceId);
        if(dsm.getEsmClass() != 4)
        {
            System.out.println("Not a Delivery report, esm_class = " + dsm.getEsmClass() + " != 0x4");
            return;
        }
        if(!parser.parseMessage(info))
        {
            System.out.println("Parse failed!");
            return;
        }
        Wait4Report wait4report;
        if(!parser.isDelivered())
        {
            System.out.println("Delivery error !");
            wait4report = null;
            synchronized(wait4ReportTable)
            {
                wait4report = (Wait4Report)wait4ReportTable.remove(parser.getId());
            }
            if(wait4report != null && wait4report.logId != null)
                dbTools.updateProcessResult(wait4report.logId, 3);
            return;
        }
        wait4report = null;
        synchronized(wait4ReportTable)
        {
            System.out.println("  (" + wait4ReportTable.size() + ")--> Getout from Wait4Report_Table.");
            wait4report = (Wait4Report)wait4ReportTable.remove(parser.getId());
        }
        SMSData sms = null;
        if(wait4report != null && wait4report.logId != null && (sms = dbTools.getSMSInSendLog(wait4report.logId)) != null)
        {
            if(sms.getMoreMsgsToSend() != 0)
                return;
            if(sms.getMessageType() < 1 || sms.getMessageType() > 2 || sms.getRequestId().intValue() == 0)
                return;
            if(sms.getMessageType() == 1)
            {
                dbTools.add2CdrQueueEx(sms);
                dbTools.updateProcessResult(wait4report.logId, 2);
            }
            return;
        } else
        {
            System.out.println("     Not Found !");
            return;
        }
    }

    private String rebuildServiceId(String serviceId)
    {
        String temp = serviceId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        if(temp.startsWith("84") || temp.startsWith("04"))
            temp = temp.substring(2);
        return temp;
    }

    private String removePlusSign(String userId)
    {
        String temp = userId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        return temp;
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
        {
            try
            {
                pdu = (PDU)fromSMSC.dequeue();
                if(pdu.getCommandId() == 5)
                    processReport(pdu);
                else
                    System.out.println("ReportProcessor::dequeue() pdu is not a DeliverSM");
            }
            catch(Exception e)
            {
                System.out.println("ReportProcessor: " + e.getMessage());
            }
        }
        destroy();
    }

    private DBTools dbTools;
    private DeliverSM dsm;
    private Queue fromSMSC;
    private String info;
    private ReportMsgParser parser;
    private PDU pdu;
    private String serviceId;
    private String userId;
    private Map wait4ReportTable;
}
