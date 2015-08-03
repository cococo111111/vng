// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReplaceSM.java

package org.smpp.pdu;

import java.io.UnsupportedEncodingException;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, ShortMessage, ReplaceSMResp, 
//            PDUException, WrongLengthOfStringException, WrongDateFormatException, ByteData, 
//            PDU, Response

public class ReplaceSM extends Request
{

    public ReplaceSM()
    {
        super(7);
        messageId = "";
        sourceAddr = new Address();
        scheduleDeliveryTime = "";
        validityPeriod = "";
        registeredDelivery = 0;
        smDefaultMsgId = 0;
        smLength = 0;
        shortMessage = new ShortMessage(300);
    }

    protected Response createResponse()
    {
        return new ReplaceSMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setMessageId(buffer.removeCString());
        sourceAddr.setData(buffer);
        setScheduleDeliveryTime(buffer.removeCString());
        setValidityPeriod(buffer.removeCString());
        setRegisteredDelivery(buffer.removeByte());
        setSmDefaultMsgId(buffer.removeByte());
        setSmLength(ByteData.decodeUnsigned(buffer.removeByte()));
        shortMessage.setData(buffer.removeBuffer(getSmLength()));
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
        buffer.appendBuffer(getSourceAddr().getData());
        buffer.appendCString(getScheduleDeliveryTime());
        buffer.appendCString(getValidityPeriod());
        buffer.appendByte(getRegisteredDelivery());
        buffer.appendByte(getSmDefaultMsgId());
        buffer.appendByte(ByteData.encodeUnsigned(getSmLength()));
        buffer.appendBuffer(shortMessage.getData());
        return buffer;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
    }

    public void setScheduleDeliveryTime(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        scheduleDeliveryTime = value;
    }

    public void setValidityPeriod(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        validityPeriod = value;
    }

    public void setShortMessage(String value)
        throws WrongLengthOfStringException
    {
        shortMessage.setMessage(value);
        setSmLength((short)shortMessage.getLength());
    }

    public void setShortMessage(String value, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        shortMessage.setMessage(value, encoding);
        setSmLength((short)shortMessage.getLength());
    }

    public void setSourceAddr(Address value)
    {
        sourceAddr = value;
    }

    public void setSourceAddr(String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(address));
    }

    public void setSourceAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(ton, npi, address));
    }

    public void setRegisteredDelivery(byte value)
    {
        registeredDelivery = value;
    }

    public void setSmDefaultMsgId(byte value)
    {
        smDefaultMsgId = value;
    }

    private void setSmLength(short value)
    {
        smLength = value;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public String getScheduleDeliveryTime()
    {
        return scheduleDeliveryTime;
    }

    public String getValidityPeriod()
    {
        return validityPeriod;
    }

    public String getShortMessage()
    {
        return shortMessage.getMessage();
    }

    public String getShortMessage(String encoding)
        throws UnsupportedEncodingException
    {
        return shortMessage.getMessage(encoding);
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public byte getRegisteredDelivery()
    {
        return registeredDelivery;
    }

    public byte getSmDefaultMsgId()
    {
        return smDefaultMsgId;
    }

    public short getSmLength()
    {
        return smLength;
    }

    public String debugString()
    {
        String dbgs = "(replace: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + shortMessage.debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
    private Address sourceAddr;
    private String scheduleDeliveryTime;
    private String validityPeriod;
    private byte registeredDelivery;
    private byte smDefaultMsgId;
    private short smLength;
    private ShortMessage shortMessage;
}
