// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeliverSMResp.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, WrongLengthOfStringException, ByteData, PDU

public class DeliverSMResp extends Response
{

    public DeliverSMResp()
    {
        super(0x80000005);
        messageId = "";
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException
    {
        setMessageId(buffer.removeCString());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
        return buffer;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public String debugString()
    {
        String dbgs = "(deliver_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
}
