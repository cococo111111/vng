// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUProcessor.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.*;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vasc.smpp.gateway:
//            DBTools, ReportMsgParser, Wait4Report, DBException, 
//            Gateway, Preference, SMSCTools, SMSData

public class PDUProcessor extends Thread
{

    public PDUProcessor(Queue fromSMSC, Queue toSMSC, Map wait4reportTable, Gateway gateway)
    {
        this.fromSMSC = null;
        this.toSMSC = null;
        wait4ReportTable = null;
        this.gateway = null;
        pdu = null;
        dsm = null;
        ssmr = null;
        userId = null;
        serviceId = null;
        operator = null;
        commandCode = null;
        info = null;
        requestId = null;
        messageId = null;
        seqNumber = null;
        commandStatus = 0;
        dbTools = null;
        parser = null;
        this.fromSMSC = fromSMSC;
        this.toSMSC = toSMSC;
        wait4ReportTable = wait4reportTable;
        this.gateway = gateway;
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
        if(temp.startsWith("8496") || temp.startsWith("+8496"))
            result = "EVN";
        else
        if(temp.startsWith("8492") || temp.startsWith("+8492"))
            result = "HTC";
        else
            result = "EVN";
        return result;
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

    private boolean isValidPrefix(String text)
    {
        if(text == null || "".equals(text))
            return false;
        String temp = text.trim().toUpperCase();
        Collection cPrefixes = (Collection)Preference.prefixMap.get(serviceId);
        for(Iterator it = cPrefixes.iterator(); it.hasNext();)
        {
            String currLabel = (String)it.next();
            if(temp.startsWith(currLabel))
            {
                commandCode = currLabel;
                return true;
            }
        }

        commandCode = "";
        return false;
    }

    private BigDecimal processReport(String info)
        throws DBException
    {
        if(!parser.parseMessage(info))
        {
            System.out.println("Parse failed!");
            return null;
        }
        if(!parser.isDelivered())
        {
            System.out.println("Delivery error !");
            wait4ReportTable.remove(parser.getId());
            return null;
        }
        System.out.println("  (" + wait4ReportTable.size() + ")--> Getout from Wait4Report_Table.");
        Wait4Report wait4report = (Wait4Report)wait4ReportTable.remove(parser.getId());
        SMSData sms = null;
        if(wait4report != null && wait4report.logId != null && (sms = dbTools.getSMSInSendLog(wait4report.logId)) != null)
        {
            if(sms.getMoreMsgsToSend() != 0)
                return sms.getId();
            if(sms.getMessageType() < 1 || sms.getMessageType() > 2 || sms.getRequestId().intValue() == 0)
                return sms.getId();
            if(sms.getMessageType() == 1)
            {
                sms.setSubmitDate(parser.getSubmitDate());
                sms.setDoneDate(parser.getDoneDate());
                dbTools.add2CdrQueue(sms);
            }
            return sms.getId();
        } else
        {
            System.out.println("     Not Found !");
            return null;
        }
    }

    private void processRequest(PDU pdu)
        throws Exception
    {
        switch(pdu.getCommandId())
        {
        case 5: // '\005'
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
            operator = buildMobileOperator(userId);
            if(dsm.getEsmClass() == 4)
            {
                processReport(info);
                return;
            }
            if(info.startsWith("id:"))
            {
                System.out.println("<?>Co ve nhu day la mot ban tin DeliverReport (not processed)!");
                return;
            }
            if(isValidPrefix(info))
            {
                Utilities _tmp = Gateway.util;
                Utilities.log("    OK --> Add to receive_queue.");
                dbTools.add2SMSReceiveQueue(userId, serviceId, operator, commandCode, info);
            } else
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.log("    Invalid prefix --> Add to receive_invalid.");
                BigDecimal msgLogId = dbTools.add2SMSReceiveInvalid(userId, serviceId, operator, "INV", info);
                String invalidPrefixMessage = (String)Preference.messageMap.get(serviceId);
                org.smpp.pdu.SubmitSM ssm = SMSCTools.buildSubmitSM(serviceId, userId, invalidPrefixMessage, 1, (byte)0, 0);
                toSMSC.enqueue(ssm);
            }
            break;

        case 259: 
            Utilities _tmp2 = Gateway.util;
            Utilities.log("  Data_SM --> Not processed.");
            break;

        case 6: // '\006'
            Gateway _tmp3 = gateway;
            Gateway.bound = false;
            gateway.bind();
            break;

        default:
            Utilities _tmp4 = Gateway.util;
            Utilities.log("PDUProcessor:: Unspecified SM " + pdu.debugString());
            break;
        }
    }

    private void processResponse(PDU pdu)
        throws Exception
    {
        String commandStatus_Hex;
        if(pdu.getCommandId() != 0x80000004)
            break MISSING_BLOCK_LABEL_680;
        ssmr = (SubmitSMResp)pdu;
        commandStatus = ssmr.getCommandStatus();
        commandStatus_Hex = "0x" + Integer.toHexString(commandStatus).toUpperCase();
        commandStatus;
        JVM INSTR lookupswitch 8: default 653
    //                   0: 136
    //                   1: 450
    //                   2: 479
    //                   8: 508
    //                   10: 537
    //                   11: 566
    //                   69: 595
    //                   88: 624;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L2:
        Gateway.util;
        Utilities.log("    OK!!!");
        seqNumber = new BigDecimal(ssmr.getSequenceNumber());
        if(seqNumber.intValue() == 1)
            return;
        int n = 0;
        int n = Integer.parseInt(ssmr.getMessageId().trim(), 16);
        messageId = Integer.toString(n);
          goto _L10
        NumberFormatException ex;
        ex;
        messageId = ssmr.getMessageId().trim();
_L10:
        dbTools.updateProcessResult(seqNumber, 1, messageId);
        SMSData sms = dbTools.moveSMSFromSendQueueToSendLog(seqNumber);
        BigDecimal logId = sms.getId();
        if(sms.getMessageType() != 1 || sms.getMoreMsgsToSend() != 0)
            break; /* Loop/switch isn't completed */
        if(("GPC".equals(sms.getMobileOperator()) || "VIETEL".equals(sms.getMobileOperator())) && Preference.reportRequired)
        {
            Gateway.util;
            Utilities.log("    (" + wait4ReportTable.size() + ")--> Add to Wait4Report_Table, logId:" + logId);
            wait4ReportTable.put(messageId, new Wait4Report(logId));
        } else
        if("VMS".equals(sms.getMobileOperator()) || "SFONE".equals(sms.getMobileOperator()) || "HTC".equals(sms.getMobileOperator()) || "EVN".equals(sms.getMobileOperator()))
            dbTools.add2CdrQueue(sms);
        break; /* Loop/switch isn't completed */
_L3:
        Gateway.util;
        Utilities.log("    MESSAGE LENGTH IS INVALID:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L4:
        Gateway.util;
        Utilities.log("    COMMAND LENGTH IS INVALID:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L5:
        Gateway.util;
        Utilities.log("    SYSTEM ERROR:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L6:
        Gateway.util;
        Utilities.log("    INVALID SOURCE ADDRESS:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L7:
        Gateway.util;
        Utilities.log("    INVALID DEST ADDRESS:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L8:
        Gateway.util;
        Utilities.log("    SUBMIT FAILED:" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L9:
        Gateway.util;
        Utilities.log("    Throttling error (ESME has exceeded allowed message limits):" + commandStatus_Hex);
        break; /* Loop/switch isn't completed */
_L1:
        Gateway.util;
        Utilities.log("    ERROR:" + commandStatus_Hex);
        if(pdu.isGNack())
        {
            Gateway.util;
            Utilities.log("  GENERIC_NAK (not processed).");
        }
        return;
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
        while(Gateway.running) 
        {
            try
            {
                pdu = (PDU)fromSMSC.dequeue();
                if(pdu.isRequest())
                    processRequest(pdu);
                else
                if(pdu.isResponse())
                    processResponse(pdu);
            }
            catch(Exception e)
            {
                System.out.println("PDUProcessor: " + e.getMessage());
            }
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

    private String commandCode;
    private int commandStatus;
    private DBTools dbTools;
    private DeliverSM dsm;
    private Queue fromSMSC;
    private Gateway gateway;
    private String info;
    private String messageId;
    private String operator;
    private ReportMsgParser parser;
    private PDU pdu;
    private BigDecimal requestId;
    private BigDecimal seqNumber;
    private String serviceId;
    private SubmitSMResp ssmr;
    private Queue toSMSC;
    private String userId;
    private Map wait4ReportTable;
}
