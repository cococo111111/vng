// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuerySMResp.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, WrongLengthOfStringException, WrongDateFormatException, 
//            ByteData, PDU

public class QuerySMResp extends Response
{

    public QuerySMResp()
    {
        super(0x80000003);
        messageId = "";
        finalDate = "";
        messageState = 0;
        errorCode = 0;
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setMessageId(buffer.removeCString());
        setFinalDate(buffer.removeCString());
        setMessageState(buffer.removeByte());
        setErrorCode(buffer.removeByte());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getMessageId());
        buffer.appendCString(getFinalDate());
        buffer.appendByte(getMessageState());
        buffer.appendByte(getErrorCode());
        return buffer;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
    }

    public void setFinalDate(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        finalDate = value;
    }

    public void setMessageState(byte value)
    {
        messageState = value;
    }

    public void setErrorCode(byte value)
    {
        errorCode = value;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public String getFinalDate()
    {
        return finalDate;
    }

    public byte getMessageState()
    {
        return messageState;
    }

    public byte getErrorCode()
    {
        return errorCode;
    }

    public String debugString()
    {
        String dbgs = "(query_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getFinalDate();
        dbgs = dbgs + " ";
        dbgs = dbgs + getMessageState();
        dbgs = dbgs + " ";
        dbgs = dbgs + getErrorCode();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
    private String finalDate;
    private byte messageState;
    private byte errorCode;
}
