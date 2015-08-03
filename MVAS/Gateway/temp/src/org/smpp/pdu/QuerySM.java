// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuerySM.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, QuerySMResp, PDUException, 
//            WrongLengthOfStringException, ByteData, PDU, Response

public class QuerySM extends Request
{

    public QuerySM()
    {
        super(3);
        messageId = "";
        sourceAddr = new Address();
    }

    protected Response createResponse()
    {
        return new QuerySMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setMessageId(buffer.removeCString());
        sourceAddr.setData(buffer);
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
        buffer.appendBuffer(getSourceAddr().getData());
        return buffer;
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

    public String getMessageId()
    {
        return messageId;
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public String debugString()
    {
        String dbgs = "(query: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
    private Address sourceAddr;
}
