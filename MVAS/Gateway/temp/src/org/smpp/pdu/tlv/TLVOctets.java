// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVOctets.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, TLVException

public class TLVOctets extends TLV
{

    public TLVOctets()
    {
        value = null;
    }

    public TLVOctets(short p_tag)
    {
        super(p_tag);
        value = null;
    }

    public TLVOctets(short p_tag, int min, int max)
    {
        super(p_tag, min, max);
        value = null;
    }

    public TLVOctets(short p_tag, ByteBuffer p_value)
        throws TLVException
    {
        super(p_tag);
        value = null;
        setValueData(p_value);
    }

    public TLVOctets(short p_tag, int min, int max, ByteBuffer p_value)
        throws TLVException
    {
        super(p_tag, min, max);
        value = null;
        setValueData(p_value);
    }

    protected void setValueData(ByteBuffer buffer)
        throws TLVException
    {
        checkLength(buffer);
        if(buffer != null)
            try
            {
                value = buffer.removeBuffer(buffer.length());
            }
            catch(NotEnoughDataInByteBufferException e)
            {
                throw new Error("Removing buf.length() data from ByteBuffer buf reported too little data in buf, which shouldn't happen.");
            }
        else
            value = null;
        markValueSet();
    }

    protected ByteBuffer getValueData()
        throws ValueNotSetException
    {
        ByteBuffer valueBuf = new ByteBuffer();
        valueBuf.appendBuffer(getValue());
        return valueBuf;
    }

    public void setValue(ByteBuffer p_value)
    {
        if(p_value != null)
            try
            {
                value = p_value.removeBuffer(p_value.length());
            }
            catch(NotEnoughDataInByteBufferException e)
            {
                throw new Error("Removing buf.length() data from ByteBuffer buf reported too little data in buf, which shouldn't happen.");
            }
        else
            value = null;
        markValueSet();
    }

    public ByteBuffer getValue()
        throws ValueNotSetException
    {
        if(hasValue())
            return value;
        else
            throw new ValueNotSetException();
    }

    public String debugString()
    {
        String dbgs = "(oct: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + (value != null ? value.getHexDump() : "");
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private ByteBuffer value;
}
