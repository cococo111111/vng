// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSCTools.java

package com.vmg.smpp.gateway;

import com.vmg.common.Utilities;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import org.smpp.Session;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            Preference, Gateway, DBTools, DBException, 
//            Logger

public class SMSCTools
{

    public SMSCTools()
    {
    }

    public static void enquireLink()
    {
        try
        {
            EnquireLink request = new EnquireLink();
            System.out.println("Enquire Link request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.enquireLink(request);
            } else
            {
                EnquireLinkResp response = Gateway.session.enquireLink(request);
                System.out.println("Enquire Link response " + response.debugString());
            }
        }
        catch(IOException ex)
        {
            System.out.println("enquireLink: " + ex);
        }
        catch(Exception ex)
        {
            System.out.println("enquireLink: " + ex);
        }
    }

    public static SubmitSM buildSubmitSM1(String srcAddr, String destAddr, String shortMsg, int MsgType, String sCommand_code, int TotalSegments)
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
        request.assignSequenceNumber(true);
        if(MsgType == 2 || MsgType > 19 && MsgType < 30)
            MsgType = 0;
        else
        if(MsgType == 1 || MsgType == 3)
            MsgType = 1;
        else
            MsgType = 4;
        if(srcAddr.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMN") || sCommand_code.equals("XSTD")))
            MsgType = 4;
        if(TotalSegments == 0)
            TotalSegments = 1;
        ByteBuffer Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        System.out.println("buildSubmitSM: " + e.getMessage());
        return null;
    }

    public static SubmitSM buildSubmitSMold(String srcAddr, String destAddr, String shortMsg, int seqNumber, int MsgType, String sCommand_code, int TotalSegments)
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
        if(MsgType == 2 || MsgType > 19 && MsgType < 30)
            MsgType = 0;
        else
        if(MsgType == 1 || MsgType == 3)
            MsgType = 1;
        else
            MsgType = 4;
        if(srcAddr.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMN") || sCommand_code.equals("XSTD")))
            MsgType = 4;
        if(TotalSegments == 0)
            TotalSegments = 1;
        ByteBuffer Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        System.out.println("buildSubmitSM: " + e);
        return null;
    }

    public static SubmitSM buildSubmitSMold(String srcAddr, String destAddr, String shortMsg, int seqNumber, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
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
        return request;
        Exception e;
        e;
        Gateway.util;
        Utilities.log("SMSCTools", "{buildSubmitSM:}" + e);
        try
        {
            DBTools.Alert2YM("{buildSubmitSM:}" + e);
        }
        catch(DBException ex)
        {
            Gateway.util;
            Utilities.log("SMSCTools", "{buildSubmitSM:}" + ex.getMessage());
        }
        return null;
    }

    public static SubmitSM buildSubmitUTF8SM(String srcAddr, String destAddr, String shortMsg, int seqNumber, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
    {
        SubmitSM request = null;
        request = new SubmitSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddr));
        request.setShortMessage(shortMsg, "UnicodeBigUnmarked");
        request.setRegisteredDelivery(registeredDelivery);
        request.setReplaceIfPresentFlag(Preference.replaceIfPresentFlag);
        request.setScheduleDeliveryTime(Preference.scheduleDeliveryTime);
        request.setValidityPeriod(Preference.validityPeriod);
        request.setEsmClass(Preference.esmClass);
        request.setProtocolId(Preference.protocolId);
        request.setPriorityFlag(Preference.priorityFlag);
        request.setDataCoding((byte)8);
        request.setSmDefaultMsgId(Preference.smDefaultMsgId);
        request.setSequenceNumber(seqNumber);
        if(MsgType == 2 || MsgType > 19 && MsgType < 30)
            MsgType = 0;
        else
        if(MsgType == 1 || MsgType == 3)
            MsgType = 1;
        else
            MsgType = 4;
        if(srcAddr.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMN") || sCommand_code.equals("XSTD")))
            MsgType = 4;
        if(TotalSegments == 0)
            TotalSegments = 1;
        ByteBuffer Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        System.out.println("buildSubmitSMUTF8: " + e);
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
        System.out.println("buildSubmitMultiSM: " + e);
        return null;
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
            System.out.println("Replace request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.replace(request);
            } else
            {
                ReplaceSMResp response = Gateway.session.replace(request);
                System.out.println("Replace response " + response.debugString());
            }
        }
        catch(Exception e)
        {
            System.out.println("replace: " + e);
        }
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
            System.out.println("Cancel request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.cancel(request);
            } else
            {
                CancelSMResp response = Gateway.session.cancel(request);
                System.out.println("Cancel response " + response.debugString());
            }
        }
        catch(Exception e)
        {
            System.out.println("cancel: " + e);
        }
    }

    public static DataSM buildDataSM(String srcAddr, String destAddress, int MsgType, String sCommand_code, int TotalSegments)
    {
        DataSM request;
        request = new DataSM();
        request.setServiceType(Preference.serviceType);
        request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
        request.setDestAddr(Preference.buildDestAddress(destAddress));
        request.setEsmClass(Preference.esmClass);
        request.setRegisteredDelivery(Preference.registeredDelivery);
        request.setDataCoding(Preference.dataCoding);
        if(MsgType == 2 || MsgType > 19 && MsgType < 30)
            MsgType = 0;
        else
        if(MsgType == 1 || MsgType == 3)
            MsgType = 1;
        else
            MsgType = 4;
        if(srcAddr.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMB") || sCommand_code.equals("XSTD")))
            MsgType = 4;
        if(TotalSegments == 0)
            TotalSegments = 1;
        ByteBuffer Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        System.out.println("buildDataSM: " + e);
        return null;
    }

    public void query(String msgId, String srcAddr)
    {
        try
        {
            QuerySM request = new QuerySM();
            request.setMessageId(msgId);
            request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
            System.out.println("Query request " + request.debugString());
            if(Preference.asynchronous)
            {
                Gateway.session.query(request);
            } else
            {
                QuerySMResp response = Gateway.session.query(request);
                System.out.println("Query response " + response.debugString());
                Preference.messageId = response.getMessageId();
            }
        }
        catch(Exception e)
        {
            System.out.println("query: " + e);
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

    public static int CheckResend(int commandStatus, String MessageID)
    {
        String commandStatus_Hex = "0x" + Integer.toHexString(commandStatus).toUpperCase();
        String strTemp = null;
        int resend = 1;
        switch(commandStatus)
        {
        case 1: // '\001'
            strTemp = "MESSAGE LENGTH IS INVALID";
            resend = 0;
            break;

        case 2: // '\002'
            strTemp = "COMMAND LENGTH IS INVALID";
            resend = 0;
            break;

        case 8: // '\b'
            strTemp = "SYSTEM ERROR";
            resend = 1;
            break;

        case 10: // '\n'
            strTemp = "INVALID SOURCE ADDRESS";
            resend = 0;
            break;

        case 11: // '\013'
            strTemp = "INVALID DEST ADDRESS";
            resend = 0;
            break;

        case 69: // 'E'
            strTemp = "SUBMIT FAILED";
            resend = 1;
            break;

        case 88: // 'X'
            strTemp = "Throttling error (ESME has exceeded allowed message limits)";
            resend = 1;
            break;

        case 5: // '\005'
            strTemp = "ESME ALREADY IN BOUND STATE";
            resend = 0;
            break;

        case 13: // '\r'
            strTemp = "BIND FAILED";
            resend = 1;
            break;

        case 14: // '\016'
            strTemp = "INVALID PASSWORD";
            resend = 1;
            break;

        case 15: // '\017'
            strTemp = "INVALID SYSTEM_ID";
            resend = 1;
            break;

        case 21: // '\025'
            strTemp = "INVALID SERVICE TYPE";
            resend = 1;
            break;

        case 72: // 'H'
            strTemp = "INVALID SOURCE_ADDR_TON";
            resend = 1;
            break;

        case 73: // 'I'
            strTemp = "INVALID SOURCE_ADDR_NPI";
            resend = 1;
            break;

        case 80: // 'P'
            strTemp = "INVALID DEST_ADDR_TON";
            resend = 1;
            break;

        case 81: // 'Q'
            strTemp = "INVALID DEST_ADDR_NPI";
            resend = 1;
            break;

        case 20: // '\024'
            strTemp = "MESSAGE QUEUE FULL";
            resend = 1;
            break;

        case 83: // 'S'
            strTemp = "INVALID SYSTEM_TYPE";
            resend = 1;
            break;

        default:
            strTemp = "ERROR";
            resend = 1;
            break;
        }
        if(resend == 1)
        {
            Utilities _tmp = Gateway.util;
            Utilities.log("SMSCTools", "{MessageID=" + MessageID + "}{ERR=" + strTemp + ":" + commandStatus_Hex + "{Resend=Yes}");
        } else
        {
            Utilities _tmp1 = Gateway.util;
            Utilities.log("SMSCTools", "{MessageID=" + MessageID + "}{ERR=" + strTemp + ":" + commandStatus_Hex + "{Resend=No}");
        }
        return resend;
    }

    public static String GetErrName(int commandStatus, String MessageID)
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
            strTemp = "UNKNOW ERROR";
            break;
        }
        Utilities _tmp = Gateway.util;
        Utilities.log("SMSCTools", "{MessageID=" + MessageID + "}{ERR=" + strTemp + ":" + commandStatus_Hex);
        return strTemp;
    }

    public static SubmitSM buildSubmitSM(String srcAddr, String destAddr, String shortMsg, int seqNumber, byte registeredDelivery, int MsgType, String sCommand_code, BigDecimal RequestID)
    {
label0:
        {
            SubmitSM request = null;
            ByteBuffer Charge;
            String DestSubAddress;
            ByteBuffer ChargeDest;
            String chargeflag;
            try
            {
                request = new SubmitSM();
                request.setServiceType(Preference.serviceType);
                request.setSourceAddr(Preference.buildSrcAddress(srcAddr));
                request.setDestAddr(Preference.buildDestAddress(destAddr));
                if(Preference.CheckServiceId(srcAddr))
                {
                    request.setShortMessage(shortMsg);
                    break label0;
                }
            }
            catch(Exception e)
            {
                Gateway.util;
                Utilities.log("SMSCTools", "{buildSubmitSM:}" + e);
                try
                {
                    DBTools.Alert2YM("{buildSubmitSM:}" + e);
                }
                catch(DBException ex)
                {
                    Gateway.util;
                    Utilities.log("SMSCTools", "{buildSubmitSM:}" + ex.getMessage());
                }
                return null;
            }
            return null;
        }
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
        Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(destAddr);
        if("SFONE".equalsIgnoreCase(Preference.mobileOperator) && MsgType == 1)
            request.setSourceSubaddress(Charge);
        DestSubAddress = "";
        if(MsgType != 3)
        {
            ChargeDest = new ByteBuffer();
            ChargeDest.appendByte((byte)-80);
            DestSubAddress = "0xB0";
            ChargeDest.appendString(RequestID);
            DestSubAddress = DestSubAddress + RequestID;
            if("SFONE".equalsIgnoreCase(Preference.mobileOperator) && "1".equalsIgnoreCase(Preference.REFUND_ACTIVE))
                request.setDestSubaddress(ChargeDest);
        }
        chargeflag = "0x0000";
        if("SFONE".equalsIgnoreCase(Preference.mobileOperator) && "1".equalsIgnoreCase(Preference.REFUND_ACTIVE))
            if(MsgType == 1)
            {
                request.setSfoneChargeFlag((short)256);
                chargeflag = "0x0100";
            } else
            if((new StringBuffer(String.valueOf(MsgType))).toString().startsWith("21"))
            {
                request.setSfoneChargeFlag((short)513);
                chargeflag = "0x0201";
            } else
            if((new StringBuffer(String.valueOf(MsgType))).toString().startsWith("22"))
            {
                request.setSfoneChargeFlag((short)514);
                chargeflag = "0x0202";
            } else
            if((new StringBuffer(String.valueOf(MsgType))).toString().equals("2"))
            {
                request.setSfoneChargeFlag((short)513);
                chargeflag = "0x0201";
            } else
            {
                request.setSfoneChargeFlag((short)0);
            }
        Logger.info("SMSCTools.buildSubmitSM", "{RequestID=" + RequestID + "}{srcAddr=" + srcAddr + "}{destAddr=" + destAddr + "}{setDestSubaddress=" + DestSubAddress + "} {chargeflag=" + chargeflag + "} ");
        return request;
    }
}
