// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubmitMultiSMResp.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, UnsuccessSME, PDUException, ValueNotSetException, 
//            WrongLengthOfStringException, TooManyValuesException, ByteDataList, ByteData, 
//            PDU

public class SubmitMultiSMResp extends Response
{
    private class UnsuccessSMEsList extends ByteDataList
    {

        public ByteData createValue()
        {
            return new UnsuccessSME();
        }

        public String debugString()
        {
            return "(unsuccess_addr_list: " + super.debugString() + ")";
        }

        public UnsuccessSMEsList()
        {
            super(254, 1);
        }
    }


    public SubmitMultiSMResp()
    {
        super(0x80000021);
        messageId = "";
        unsuccessSMEs = new UnsuccessSMEsList();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setMessageId(buffer.removeCString());
        unsuccessSMEs.setData(buffer);
    }

    public ByteBuffer getBody()
        throws ValueNotSetException
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
        buffer.appendBuffer(unsuccessSMEs.getData());
        return buffer;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
    }

    public void addUnsuccessSME(UnsuccessSME unsuccessSME)
        throws TooManyValuesException
    {
        unsuccessSMEs.addValue(unsuccessSME);
    }

    public String getMessageId()
    {
        return messageId;
    }

    public short getNoUnsuccess()
    {
        return (short)unsuccessSMEs.getCount();
    }

    public UnsuccessSME getUnsuccessSME(int i)
    {
        return (UnsuccessSME)unsuccessSMEs.getValue(i);
    }

    public String debugString()
    {
        String dbgs = "(submitmulti_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + unsuccessSMEs.debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
    private UnsuccessSMEsList unsuccessSMEs;
}
