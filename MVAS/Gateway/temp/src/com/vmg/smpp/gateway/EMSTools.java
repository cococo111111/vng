// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EMSTools.java

package com.vmg.smpp.gateway;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import org.smpp.pdu.SubmitSM;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            EMSException, Cli, OperatorLogo, Preference, 
//            Logger, PictureMessage

public class EMSTools
{

    public EMSTools()
    {
    }

    private static ByteBuffer addHeaders2OTAData(ByteBuffer otaData, String mobileOperator, int contentType)
        throws EMSException
    {
        ByteBuffer buffer = null;
        switch(contentType)
        {
        case 3: // '\003'
            Cli cli = new Cli();
            cli.setOTB(otaData.getBuffer());
            cli.encode();
            buffer = cli.getEncoded();
            break;

        case 2: // '\002'
            OperatorLogo logo = new OperatorLogo();
            logo.setOTB(otaData.getBuffer());
            logo.setMobileOperator(mobileOperator);
            logo.encode();
            buffer = logo.getEncoded();
            break;

        default:
            buffer = otaData;
            break;
        }
        return buffer;
    }

    public static SubmitSM buildSubmitSMfromUTF16HexString(ByteBuffer UTF16HexString, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request = null;
        request = new SubmitSM();
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        request.setShortMessageData(UTF16HexString);
        request.setDataCoding((byte)8);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
        if(MsgType == 2 || MsgType > 19 && MsgType < 30)
            MsgType = 0;
        else
        if(MsgType == 1 || MsgType == 3)
            MsgType = 1;
        else
            MsgType = 4;
        if(srcAddr.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMN") || sCommand_code.equals("XSTD")))
            MsgType = 4;
        ByteBuffer Charge = new ByteBuffer();
        if(TotalSegments == 0)
            TotalSegments = 1;
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        throw new EMSException(e.getMessage());
    }

    public static SubmitSM buildSubmitEMS(ByteBuffer otaData, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request = new SubmitSM();
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        ByteBuffer message = new ByteBuffer();
        message.appendByte((byte)6);
        message.appendByte((byte)5);
        message.appendByte((byte)4);
        message.appendShort((short)9204);
        message.appendShort((short)0);
        message.appendBuffer(otaData);
        request.setShortMessageData(message);
        request.setEsmClass((byte)64);
        request.setDataCoding((byte)-11);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
        if(TotalSegments == 0)
            TotalSegments = 1;
        ByteBuffer Charge = new ByteBuffer();
        Charge.appendByte((byte)-96);
        Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
        request.setSourceSubaddress(Charge);
        return request;
        Exception e;
        e;
        System.out.println("buildSubmitEMS: " + e.getMessage());
        return null;
    }

    public static Collection buildSubmitEMS(ByteBuffer otaData, String srcAddr, String destAddr, int contentType, BigDecimal emsId, byte registeredDelivery)
        throws EMSException
    {
        Vector vRequests;
        SubmitSM request = null;
        vRequests = new Vector();
        String mobileOperator = Preference.mobileOperator;
        otaData = addHeaders2OTAData(otaData, mobileOperator, contentType);
        if(otaData.length() <= 133)
        {
            MAX_LENGTH = 133;
            isFragmented = false;
            totalSegments = 1;
        } else
        {
            MAX_LENGTH = 128;
            isFragmented = true;
            totalSegments = (byte)(otaData.length() / MAX_LENGTH + 1);
        }
        ByteBuffer message = null;
        for(byte i = 1; i <= totalSegments; i++)
        {
            SubmitSM request = new SubmitSM();
            request.setSourceAddr((byte)0, (byte)1, srcAddr);
            request.setDestAddr((byte)1, (byte)1, destAddr);
            message = new ByteBuffer();
            if(isFragmented)
                message.appendByte((byte)11);
            else
                message.appendByte((byte)6);
            message.appendByte((byte)5);
            message.appendByte((byte)4);
            switch(contentType)
            {
            case 3: // '\003'
                message.appendShort((short)5507);
                break;

            case 2: // '\002'
                message.appendShort((short)5506);
                break;

            case 4: // '\004'
                message.appendShort((short)5514);
                break;

            case 1: // '\001'
                message.appendShort((short)5505);
                break;

            case 6: // '\006'
            case 8: // '\b'
                message.appendShort((short)2948);
                break;

            case 7: // '\007'
                message.appendShort((short)-15537);
                break;

            case 10: // '\n'
                message.appendShort((short)9204);
                break;

            case 11: // '\013'
                message.appendShort((short)9205);
                break;

            case 5: // '\005'
            case 9: // '\t'
            default:
                throw new EMSException("Invalid content type " + contentType + ")");
            }
            if(contentType == 8 || contentType == 6)
                message.appendShort((short)9200);
            else
            if(contentType == 7)
                message.appendShort((short)-16382);
            else
                message.appendShort((short)0);
            if(isFragmented)
            {
                message.appendByte((byte)0);
                message.appendByte((byte)3);
                message.appendByte((byte)0);
                message.appendByte(totalSegments);
                message.appendByte(i);
            }
            if(otaData.length() <= MAX_LENGTH)
                message.appendBuffer(otaData);
            else
                message.appendBuffer(otaData.removeBuffer(MAX_LENGTH));
            request.setShortMessageData(message);
            request.setEsmClass((byte)64);
            request.setDataCoding((byte)-11);
            String s_emsId = emsId.toString();
            request.setSequenceNumber(Integer.parseInt(s_emsId + i));
            if(i == totalSegments)
                request.setRegisteredDelivery(registeredDelivery);
            vRequests.addElement(request);
        }

        return vRequests;
        Exception e;
        e;
        throw new EMSException(e.getMessage());
    }

    public static Collection buildSubmitEMS(ByteBuffer otaData, String srcAddr, String destAddr, int contentType, BigDecimal emsId, byte registeredDelivery, int MsgType, BigDecimal RequestID)
        throws EMSException
    {
        Vector vRequests;
        SubmitSM request = null;
        vRequests = new Vector();
        String mobileOperator = "";
        mobileOperator = Preference.mobileOperator;
        otaData = addHeaders2OTAData(otaData, mobileOperator, contentType);
        Logger.info("otadata.length:" + otaData.length());
        if(otaData.length() <= 133)
        {
            MAX_LENGTH = 133;
            isFragmented = false;
            totalSegments = 1;
        } else
        {
            MAX_LENGTH = 128;
            isFragmented = true;
            totalSegments = (byte)(otaData.length() / MAX_LENGTH + 1);
        }
        ByteBuffer message = null;
        for(byte i = 1; i <= totalSegments; i++)
        {
            SubmitSM request = new SubmitSM();
            request.setSourceAddr((byte)0, (byte)1, srcAddr);
            request.setDestAddr((byte)1, (byte)1, destAddr);
            request.setServiceType("WPUSH");
            message = new ByteBuffer();
            if(otaData.length() <= MAX_LENGTH)
                message.appendBuffer(otaData);
            else
                message.appendBuffer(otaData.removeBuffer(MAX_LENGTH));
            request.setShortMessageData(message);
            request.setEsmClass((byte)0);
            request.setProtocolId((byte)0);
            String s_emsId = emsId.toString();
            request.setSequenceNumber(Integer.parseInt(s_emsId + i));
            if(i == totalSegments)
                request.setRegisteredDelivery(registeredDelivery);
            String DestSubAddress = "0xB0";
            if(MsgType != 3)
            {
                ByteBuffer ChargeDest = new ByteBuffer();
                ChargeDest.appendByte((byte)-80);
                ChargeDest.appendString(RequestID);
                DestSubAddress = DestSubAddress + RequestID;
                if("SFONE".equalsIgnoreCase(Preference.mobileOperator))
                    request.setDestSubaddress(ChargeDest);
            }
            String chargeflag = "0x0000";
            if("SFONE".equalsIgnoreCase(Preference.mobileOperator))
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
            vRequests.addElement(request);
        }

        return vRequests;
        Exception e;
        e;
        throw new EMSException(e.getMessage());
    }

    public static SubmitSM buildSubmitDalink(String srcMsg, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request;
        ByteBuffer optinal;
        request = new SubmitSM();
        optinal = new ByteBuffer();
        optinal.appendInt(1);
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        request.setSourceSubaddress(optinal);
        String debug = "";
        ByteBuffer message = new ByteBuffer();
        if(Integer.toHexString(srcMsg.length() + 8).length() < 2)
            debug = debug + "0" + Integer.toHexString(srcMsg.length() + 8);
        else
            debug = debug + Integer.toHexString(srcMsg.length() + 8);
        message.appendByte((byte)6);
        message.appendByte((byte)5);
        message.appendByte((byte)4);
        debug = debug + "060504";
        message.appendShort((short)-15535);
        message.appendShort((short)-15535);
        message.appendCString(srcMsg);
        debug = debug + "C351C351";
        debug = debug + srcMsg;
        request.setShortMessageData(message);
        request.setEsmClass((byte)64);
        request.setDataCoding((byte)-11);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
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
        System.out.println(".: " + e.getMessage());
        return null;
    }

    public static SubmitSM buildSubmitDalinkVSSA(String srcMsg, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request = new SubmitSM();
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        String debug = "";
        ByteBuffer message = new ByteBuffer();
        if(Integer.toHexString(srcMsg.length() + 8).length() < 2)
            debug = debug + "0" + Integer.toHexString(srcMsg.length() + 8);
        else
            debug = debug + Integer.toHexString(srcMsg.length() + 8);
        message.appendByte((byte)6);
        message.appendByte((byte)5);
        message.appendByte((byte)4);
        debug = debug + "060504";
        message.appendShort((short)-15534);
        message.appendShort((short)-15534);
        message.appendCString(srcMsg);
        debug = debug + "C351C351";
        debug = debug + srcMsg;
        request.setShortMessageData(message);
        request.setEsmClass((byte)64);
        request.setDataCoding((byte)-11);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
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
        System.out.println(".: " + e.getMessage());
        return null;
    }

    public static SubmitSM buildSubmitMC8000(String srcMsg, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request = new SubmitSM();
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        String debug = "";
        ByteBuffer message = new ByteBuffer();
        if(Integer.toHexString(srcMsg.length() + 8).length() < 2)
            debug = debug + "0" + Integer.toHexString(srcMsg.length() + 8);
        else
            debug = debug + Integer.toHexString(srcMsg.length() + 8);
        message.appendByte((byte)6);
        message.appendByte((byte)5);
        message.appendByte((byte)4);
        debug = debug + "060504";
        message.appendShort((short)7505);
        message.appendShort((short)0);
        message.appendCString(srcMsg);
        debug = debug + "C351C351";
        debug = debug + srcMsg;
        request.setShortMessageData(message);
        request.setEsmClass((byte)64);
        request.setDataCoding((byte)-11);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
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
        System.out.println(".: " + e.getMessage());
        return null;
    }

    public static SubmitSM buildSubmitKARAOKE(String srcMsg, String srcAddr, String destAddr, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        SubmitSM request = new SubmitSM();
        request.setSourceAddr((byte)0, (byte)1, srcAddr);
        request.setDestAddr((byte)1, (byte)1, destAddr);
        String debug = "";
        ByteBuffer message = new ByteBuffer();
        if(Integer.toHexString(srcMsg.length() + 8).length() < 2)
            debug = debug + "0" + Integer.toHexString(srcMsg.length() + 8);
        else
            debug = debug + Integer.toHexString(srcMsg.length() + 8);
        message.appendByte((byte)6);
        message.appendByte((byte)5);
        message.appendByte((byte)4);
        debug = debug + "060504";
        message.appendShort((short)7500);
        message.appendShort((short)7500);
        message.appendCString(srcMsg);
        debug = debug + "C351C351";
        debug = debug + srcMsg;
        request.setShortMessageData(message);
        request.setEsmClass((byte)64);
        request.setDataCoding((byte)-11);
        request.setSequenceNumber(Integer.parseInt(emsId.toString() + 1));
        request.setRegisteredDelivery(registeredDelivery);
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
        System.out.println(".: " + e.getMessage());
        return null;
    }

    public static Collection buildSubmitEMS(ByteBuffer otaData, String srcAddr, String destAddr, int contentType, BigDecimal emsId, byte registeredDelivery, int MsgType, String sCommand_code, 
            int TotalSegments)
        throws EMSException
    {
        Vector vRequests;
        SubmitSM request = null;
        vRequests = new Vector();
        String mobileOperator = Preference.mobileOperator;
        otaData = addHeaders2OTAData(otaData, mobileOperator, contentType);
        if(otaData.length() <= 133)
        {
            MAX_LENGTH = 133;
            isFragmented = false;
            totalSegments = 1;
        } else
        {
            MAX_LENGTH = 128;
            isFragmented = true;
            totalSegments = (byte)(otaData.length() / MAX_LENGTH + 1);
        }
        ByteBuffer message = null;
        for(byte i = 1; i <= totalSegments; i++)
        {
            SubmitSM request = new SubmitSM();
            request.setSourceAddr((byte)0, (byte)1, srcAddr);
            request.setDestAddr((byte)1, (byte)1, destAddr);
            message = new ByteBuffer();
            if(isFragmented)
                message.appendByte((byte)11);
            else
                message.appendByte((byte)6);
            message.appendByte((byte)5);
            message.appendByte((byte)4);
            switch(contentType)
            {
            case 3: // '\003'
                message.appendShort((short)5507);
                break;

            case 2: // '\002'
                message.appendShort((short)5506);
                break;

            case 4: // '\004'
                message.appendShort((short)5514);
                break;

            case 1: // '\001'
                message.appendShort((short)5505);
                break;

            case 6: // '\006'
            case 8: // '\b'
                message.appendShort((short)2948);
                break;

            case 7: // '\007'
                message.appendShort((short)-15537);
                break;

            case 10: // '\n'
                message.appendShort((short)9204);
                break;

            case 11: // '\013'
                message.appendShort((short)9205);
                break;

            case 5: // '\005'
            case 9: // '\t'
            default:
                throw new EMSException("Invalid content type " + contentType + ")");
            }
            if(contentType == 8 || contentType == 6)
                message.appendShort((short)9200);
            else
            if(contentType == 7)
                message.appendShort((short)-16382);
            else
                message.appendShort((short)0);
            if(isFragmented)
            {
                message.appendByte((byte)0);
                message.appendByte((byte)3);
                message.appendByte((byte)0);
                message.appendByte(totalSegments);
                message.appendByte(i);
            }
            if(otaData.length() <= MAX_LENGTH)
                message.appendBuffer(otaData);
            else
                message.appendBuffer(otaData.removeBuffer(MAX_LENGTH));
            request.setShortMessageData(message);
            request.setEsmClass((byte)64);
            request.setDataCoding((byte)-11);
            String s_emsId = emsId.toString();
            request.setSequenceNumber(Integer.parseInt(s_emsId + i));
            if(TotalSegments == 0)
                TotalSegments = 1;
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendByte((byte)-96);
            Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
            request.setSourceSubaddress(Charge);
            if(i == totalSegments)
                request.setRegisteredDelivery(registeredDelivery);
            vRequests.addElement(request);
        }

        return vRequests;
        Exception e;
        e;
        throw new EMSException(e.getMessage());
    }

    public static Collection buildSubmitEMS(String filename, String srcAddress, String destAddress, int contentType, int MsgType, String sCommand_code, int TotalSegments)
        throws EMSException
    {
        Vector vRequests;
        SubmitSM request = null;
        vRequests = new Vector();
        ByteBuffer otaData = null;
        short port = 0;
        switch(contentType)
        {
        case 3: // '\003'
            port = 5507;
            Cli cli = new Cli(filename);
            cli.encode();
            otaData = cli.getEncoded();
            break;

        case 2: // '\002'
            port = 5506;
            OperatorLogo logo = new OperatorLogo(filename);
            logo.setMobileOperator(Preference.mobileOperator);
            logo.encode();
            otaData = logo.getEncoded();
            break;

        case 4: // '\004'
            port = 5514;
            PictureMessage pic = new PictureMessage(filename);
            pic.encode();
            otaData = pic.getEncoded();
            break;

        case 1: // '\001'
            port = 5505;
            otaData = loadByteBuffer(filename);
            break;

        default:
            throw new EMSException("Invalid content type " + contentType + ")");
        }
        if(otaData.length() <= 133)
        {
            MAX_LENGTH = 133;
            isFragmented = false;
            totalSegments = 1;
        } else
        {
            MAX_LENGTH = 128;
            isFragmented = true;
            totalSegments = (byte)(otaData.length() / MAX_LENGTH + 1);
        }
        ByteBuffer message = null;
        for(byte i = 1; i <= totalSegments; i++)
        {
            SubmitSM request = new SubmitSM();
            request.setSourceAddr((byte)0, (byte)0, srcAddress);
            request.setDestAddr((byte)1, (byte)1, destAddress);
            message = new ByteBuffer();
            if(isFragmented)
                message.appendByte((byte)11);
            else
                message.appendByte((byte)6);
            message.appendByte((byte)5);
            message.appendByte((byte)4);
            message.appendShort(port);
            message.appendShort((short)0);
            if(isFragmented)
            {
                message.appendByte((byte)0);
                message.appendByte((byte)3);
                message.appendByte((byte)0);
                message.appendByte(totalSegments);
                message.appendByte(i);
            }
            if(otaData.length() <= MAX_LENGTH)
                message.appendBuffer(otaData);
            else
                message.appendBuffer(otaData.removeBuffer(MAX_LENGTH));
            request.setShortMessageData(message);
            request.setEsmClass((byte)64);
            request.setDataCoding((byte)-11);
            request.setSequenceNumber(i + 1);
            if(MsgType == 2 || MsgType > 19 && MsgType < 30)
                MsgType = 0;
            else
            if((MsgType == 1 || MsgType == 3) && i == 1)
                MsgType = 1;
            else
                MsgType = 4;
            if(srcAddress.equals("996") && MsgType == 0 && (sCommand_code.equals("XSMN") || sCommand_code.equals("XSMT") || sCommand_code.equals("XSMN") || sCommand_code.equals("XSTD")))
                MsgType = 4;
            if(TotalSegments == 0)
                TotalSegments = 1;
            ByteBuffer Charge = new ByteBuffer();
            Charge.appendByte((byte)-96);
            Charge.appendString(sCommand_code + "," + MsgType + "," + TotalSegments);
            request.setSourceSubaddress(Charge);
            vRequests.addElement(request);
        }

        return vRequests;
        Exception e;
        e;
        throw new EMSException(e.getMessage());
    }

    public static ByteBuffer loadByteBuffer(String fileName)
        throws IOException
    {
        FileInputStream is = new FileInputStream(fileName);
        byte data[] = new byte[is.available()];
        is.read(data);
        is.close();
        return new ByteBuffer(data);
    }

    public static void main(String args[])
    {
        try
        {
            ByteBuffer buffer = loadByteBuffer("logo.otb");
            buildSubmitEMS(buffer, "997", "84904060008", 2, new BigDecimal(0), (byte)0, 1, "Test", 1);
        }
        catch(EMSException emsexception) { }
        catch(IOException ioexception) { }
    }

    private static int MAX_LENGTH = 133;
    private static boolean isFragmented = false;
    private static byte totalSegments = 1;

}
