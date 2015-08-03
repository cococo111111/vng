// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVUByte.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, TLVException

public class TLVUByte extends TLV
{

    public TLVUByte()
    {
        super(1, 1);
        value = 0;
    }

    public TLVUByte(short p_tag)
    {
        super(p_tag, 1, 1);
        value = 0;
    }

    protected void setValueData(ByteBuffer buffer)
        throws TLVException
    {
        checkLength(buffer);
        try
        {
            value = ByteData.decodeUnsigned(buffer.removeByte());
        }
        catch(NotEnoughDataInByteBufferException e) { }
        markValueSet();
    }

    protected ByteBuffer getValueData()
        throws ValueNotSetException
    {
        ByteBuffer valueBuf = new ByteBuffer();
        valueBuf.appendByte(ByteData.encodeUnsigned(getValue()));
        return valueBuf;
    }

    public void setValue(short value)
        throws IntegerOutOfRangeException
    {
        ByteData.checkRange(0, value, 255);
        this.value = value;
        markValueSet();
    }

    public short getValue()
        throws ValueNotSetException
    {
        if(hasValue())
            return value;
        else
            throw new ValueNotSetException();
    }

    public String debugString()
    {
        String dbgs = "(byte: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + value;
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private short value;
}
