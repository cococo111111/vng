// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CancelSM.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, CancelSMResp, PDUException, 
//            WrongLengthOfStringException, ByteData, PDU, Response

public class CancelSM extends Request
{

    public CancelSM()
    {
        super(8);
        serviceType = "";
        messageId = "";
        sourceAddr = new Address();
        destAddr = new Address();
    }

    protected Response createResponse()
    {
        return new CancelSMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setServiceType(buffer.removeCString());
        setMessageId(buffer.removeCString());
        sourceAddr.setData(buffer);
        destAddr.setData(buffer);
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getServiceType());
        buffer.appendCString(messageId);
        buffer.appendBuffer(getSourceAddr().getData());
        buffer.appendBuffer(getDestAddr().getData());
        return buffer;
    }

    public void setServiceType(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(value, 6);
        serviceType = value;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
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

    public void setDestAddr(Address value)
    {
        destAddr = value;
    }

    public void setDestAddr(String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(address));
    }

    public void setDestAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(ton, npi, address));
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public Address getDestAddr()
    {
        return destAddr;
    }

    public String debugString()
    {
        String dbgs = "(cancel: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getServiceType();
        dbgs = dbgs + " ";
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + getDestAddr().debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String serviceType;
    private String messageId;
    private Address sourceAddr;
    private Address destAddr;
}
