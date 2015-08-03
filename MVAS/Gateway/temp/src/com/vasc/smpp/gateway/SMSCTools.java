// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCTools.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import java.io.IOException;
import java.io.PrintStream;
import org.smpp.Session;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vasc.smpp.gateway:
//            Gateway, Preference

public class SMSCTools
{

    public SMSCTools()
    {
    }

    public static DataSM buildDataSM(String srcAddr, String destAddress, int MsgType)
    {
        DataSM request;
        request = new DataSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddress));
        request.setEsmClass(Preference.esmClass);
        request.setRegisteredDelivery(Preference.registeredDelivery);
        request.setDataCoding(Preference.dataCoding);
        if(MsgType == 1 || MsgType == 3)
        {
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendInt(1);
            request.setSourceSubaddress(Charge);
        }
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("buildDataSM operation failed. " + e);
        return null;
    }

    public static SubmitMultiSM buildSubmitMultiSM(String srcAddr, String ListOfDestAddr[], String shortMsg)
    {
        SubmitMultiSM request = null;
        request = new SubmitMultiSM();
        for(int i = 0; i < ListOfDestAddr.length; i++)
            request.addDestAddress(new DestinationAddress(Preference.buildDestAddress(ListOfDestAddr[i])));

        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setServiceType(Preference.serviceType);
        request.setReplaceIfPresentFlag(Preference.replaceIfPresentFlag);
        request.setShortMessage(shortMsg);
        request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
        request.setValidityPeriod(Preference.validityPeriod);
        request.setEsmClass(Preference.esmClass);
        request.setProtocolId(Preference.protocolId);
        request.setPriorityFlag(Preference.priorityFlag);
        request.setRegisteredDelivery(Preference.registeredDelivery);
        request.setDataCoding(Preference.dataCoding);
        request.setSmDefaultMsgId(Preference.smDefaultMsgId);
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("buildSubmitMultiSM operation failed. " + e);
        return null;
    }

    public static SubmitSM buildSubmitSM(String srcAddr, String destAddr, String shortMsg, int seqNumber, byte registeredDelivery, int MsgType)
    {
        SubmitSM request = null;
        request = new SubmitSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddr));
        request.setShortMessage(shortMsg);
        request.setRegisteredDelivery(registeredDelivery);
        request.setReplaceIfPresentFlag(Preference.replaceIfPresentFlag);
        request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
        request.setValidityPeriod(Preference.validityPeriod);
        request.setEsmClass(Preference.esmClass);
        request.setProtocolId(Preference.protocolId);
        request.setPriorityFlag(Preference.priorityFlag);
        request.setDataCoding(Preference.dataCoding);
        request.setSmDefaultMsgId(Preference.smDefaultMsgId);
        request.setSequenceNumber(seqNumber);
        if(MsgType == 1 || MsgType == 3)
        {
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendInt(1);
            request.setSourceSubaddress(Charge);
        }
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("buildSubmitSM operation failed. " + e);
        return null;
    }

    public static SubmitSM buildSubmitSM(String srcAddr, String destAddr, String shortMsg, int seqNumber, int MsgType)
    {
        SubmitSM request = null;
        request = new SubmitSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddr));
        request.setShortMessage(shortMsg);
        request.setReplaceIfPresentFlag(Preference.replaceIfPresentFlag);
        request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
        request.setValidityPeriod(Preference.validityPeriod);
        request.setEsmClass(Preference.esmClass);
        request.setProtocolId(Preference.protocolId);
        request.setPriorityFlag(Preference.priorityFlag);
        request.setRegisteredDelivery((byte)0);
        request.setDataCoding(Preference.dataCoding);
        request.setSmDefaultMsgId(Preference.smDefaultMsgId);
        request.setSequenceNumber(seqNumber);
        if(MsgType == 1 || MsgType == 3)
        {
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendInt(1);
            request.setSourceSubaddress(Charge);
        }
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("buildSubmitSM operation failed. " + e);
        return null;
    }

    public static SubmitSM buildSubmitSM(String srcAddr, String destAddr, String shortMsg, int MsgType)
    {
        SubmitSM request = null;
        request = new SubmitSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddr));
        request.setShortMessage(shortMsg);
        request.setReplaceIfPresentFlag(Preference.replaceIfPresentFlag);
        request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
        request.setValidityPeriod(Preference.validityPeriod);
        request.setEsmClass(Preference.esmClass);
        request.setProtocolId(Preference.protocolId);
        request.setPriorityFlag(Preference.priorityFlag);
        request.setRegisteredDelivery((byte)0);
        request.setDataCoding(Preference.dataCoding);
        request.setSmDefaultMsgId(Preference.smDefaultMsgId);
        request.setSequenceNumber(1);
        if(MsgType == 1 || MsgType == 3)
        {
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendInt(1);
            request.setSourceSubaddress(Charge);
        }
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("buildSubmitSM operation failed. " + e);
        return null;
    }

    public void cancel(String msgId, String srcAddr, String destAddr)
    {
        try
        {
            CancelSM request = new CancelSM();
            request.setServiceType(Preference.serviceType);
            request.setMessageId(msgId);
            request.setSourceAddr(srcAddr);
            request.setDestAddr(destAddr);
            Utilities _tmp = Gateway.util;
            Utilities.log("Cancel request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.cancel(request);
            } else
            {
                CancelSMResp response = Gateway.session.cancel(request);
                Utilities _tmp1 = Gateway.util;
                Utilities.log("Cancel response " + response.debugString());
            }
        }
        catch(Exception e)
        {
            Utilities _tmp2 = Gateway.util;
            Utilities.log("Cancel operation failed. " + e);
        }
    }

    public static void enquireLink()
    {
        try
        {
            EnquireLink request = new EnquireLink();
            Utilities _tmp = Gateway.util;
            Utilities.log("Enquire Link request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.enquireLink(request);
            } else
            {
                EnquireLinkResp response = Gateway.session.enquireLink(request);
                Utilities _tmp1 = Gateway.util;
                Utilities.log("Enquire Link response " + response.debugString());
            }
        }
        catch(IOException ex)
        {
            Utilities _tmp2 = Gateway.util;
            Utilities.log("SMSCTools.enquireLink(): " + ex);
        }
        catch(Exception ex)
        {
            Utilities _tmp3 = Gateway.util;
            Utilities.log("Enquire Link operation failed. " + ex);
        }
    }

    public static void printCommandStatus(int commandStatus)
    {
        String commandStatus_Hex = "0x" + Integer.toHexString(commandStatus).toUpperCase();
        String strTemp = null;
        switch(commandStatus)
        {
        case 1: // '\001'
            strTemp = "MESSAGE LENGTH IS INVALID";
            break;

        case 2: // '\002'
            strTemp = "COMMAND LENGTH IS INVALID";
            break;

        case 8: // '\b'
            strTemp = "SYSTEM ERROR";
            break;

        case 10: // '\n'
            strTemp = "INVALID SOURCE ADDRESS";
            break;

        case 11: // '\013'
            strTemp = "INVALID DEST ADDRESS";
            break;

        case 69: // 'E'
            strTemp = "SUBMIT FAILED";
            break;

        case 88: // 'X'
            strTemp = "Throttling error (ESME has exceeded allowed message limits)";
            break;

        case 5: // '\005'
            strTemp = "ESME ALREADY IN BOUND STATE";
            break;

        case 13: // '\r'
            strTemp = "BIND FAILED";
            break;

        case 14: // '\016'
            strTemp = "INVALID PASSWORD";
            break;

        case 15: // '\017'
            strTemp = "INVALID SYSTEM_ID";
            break;

        case 21: // '\025'
            strTemp = "INVALID SERVICE TYPE";
            break;

        case 72: // 'H'
            strTemp = "INVALID SOURCE_ADDR_TON";
            break;

        case 73: // 'I'
            strTemp = "INVALID SOURCE_ADDR_NPI";
            break;

        case 80: // 'P'
            strTemp = "INVALID DEST_ADDR_TON";
            break;

        case 81: // 'Q'
            strTemp = "INVALID DEST_ADDR_NPI";
            break;

        case 20: // '\024'
            strTemp = "MESSAGE QUEUE FULL";
            break;

        case 83: // 'S'
            strTemp = "INVALID SYSTEM_TYPE";
            break;

        default:
            strTemp = "ERROR";
            break;
        }
        System.out.println("    " + strTemp + ":" + commandStatus_Hex);
    }

    public void query(String msgId, String sourceAddress)
    {
        try
        {
            QuerySM request = new QuerySM();
            request.setMessageId(msgId);
            request.setSourceAddr(Preference.buildSrcAddress(sourceAddress));
            Utilities _tmp = Gateway.util;
            Utilities.log("Query request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.query(request);
            } else
            {
                QuerySMResp response = Gateway.session.query(request);
                Utilities _tmp1 = Gateway.util;
                Utilities.log("Query response " + response.debugString());
                Preference.messageId = response.getMessageId();
            }
        }
        catch(Exception e)
        {
            Utilities _tmp2 = Gateway.util;
            Utilities.log("Query operation failed. " + e);
        }
    }

    public void replace(String msgId, String srcAddr, String shortMsg)
    {
        try
        {
            ReplaceSM request = new ReplaceSM();
            request.setMessageId(msgId);
            request.setSourceAddr(srcAddr);
            request.setShortMessage(shortMsg);
            request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
            request.setValidityPeriod(Preference.validityPeriod);
            request.setRegisteredDelivery(Preference.registeredDelivery);
            request.setSmDefaultMsgId(Preference.smDefaultMsgId);
            Utilities _tmp = Gateway.util;
            Utilities.log("Replace request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.replace(request);
            } else
            {
                ReplaceSMResp response = Gateway.session.replace(request);
                Utilities _tmp1 = Gateway.util;
                Utilities.log("Replace response " + response.debugString());
            }
        }
        catch(Exception e)
        {
            Utilities _tmp2 = Gateway.util;
            Utilities.log("Replace operation failed. " + e);
        }
    }
}
