// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVByte.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, TLVException

public class TLVByte extends TLV
{

    public TLVByte()
    {
        super(1, 1);
        value = 0;
    }

    public TLVByte(short p_tag)
    {
        super(p_tag, 1, 1);
        value = 0;
    }

    public TLVByte(short p_tag, byte p_value)
    {
        super(p_tag, 1, 1);
        value = 0;
        value = p_value;
        markValueSet();
    }

    protected void setValueData(ByteBuffer buffer)
        throws TLVException
    {
        checkLength(buffer);
        try
        {
            value = buffer.removeByte();
        }
        catch(NotEnoughDataInByteBufferException e) { }
        markValueSet();
    }

    protected ByteBuffer getValueData()
        throws ValueNotSetException
    {
        ByteBuffer valueBuf = new ByteBuffer();
        valueBuf.appendByte(getValue());
        return valueBuf;
    }

    public void setValue(byte p_value)
    {
        value = p_value;
        markValueSet();
    }

    public byte getValue()
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

    private byte value;
}
