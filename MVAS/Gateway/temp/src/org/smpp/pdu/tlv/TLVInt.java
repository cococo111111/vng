// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVInt.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, TLVException

public class TLVInt extends TLV
{

    public TLVInt()
    {
        super(4, 4);
        value = 0;
    }

    public TLVInt(short p_tag)
    {
        super(p_tag, 4, 4);
        value = 0;
    }

    public TLVInt(short p_tag, int p_value)
    {
        super(p_tag, 4, 4);
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
            value = buffer.removeInt();
        }
        catch(NotEnoughDataInByteBufferException e) { }
        markValueSet();
    }

    protected ByteBuffer getValueData()
        throws ValueNotSetException
    {
        ByteBuffer valueBuf = new ByteBuffer();
        valueBuf.appendInt(getValue());
        return valueBuf;
    }

    public void setValue(int p_value)
    {
        value = p_value;
        markValueSet();
    }

    public int getValue()
        throws ValueNotSetException
    {
        if(hasValue())
            return value;
        else
            throw new ValueNotSetException();
    }

    public String debugString()
    {
        String dbgs = "(int: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + value;
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private int value;
}
