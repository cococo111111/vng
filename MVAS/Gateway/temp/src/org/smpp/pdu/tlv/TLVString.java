// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVString.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, TLVException, WrongLengthException

public class TLVString extends TLV
{

    public TLVString()
    {
    }

    public TLVString(short tag)
    {
        super(tag);
    }

    public TLVString(short tag, int min, int max)
    {
        super(tag, min, max);
    }

    public TLVString(short tag, String value)
        throws TLVException
    {
        super(tag);
        setValue(value);
    }

    public TLVString(short tag, int min, int max, String value)
        throws TLVException
    {
        super(tag, min, max);
        setValue(value);
    }

    public void setValueData(ByteBuffer buffer)
        throws TLVException
    {
        checkLength(buffer);
        if(buffer != null)
            try
            {
                value = buffer.removeCString();
            }
            catch(NotEnoughDataInByteBufferException e)
            {
                throw new TLVException("Not enough data for string in the buffer.");
            }
            catch(TerminatingZeroNotFoundException e)
            {
                throw new TLVException("String terminating zero not found in the buffer.");
            }
        else
            value = new String("");
        markValueSet();
    }

    public ByteBuffer getValueData()
        throws ValueNotSetException
    {
        ByteBuffer valueBuf = new ByteBuffer();
        valueBuf.appendCString(getValue());
        return valueBuf;
    }

    public void setValue(String value)
        throws WrongLengthException
    {
        checkLength(value.length() + 1);
        this.value = value;
        markValueSet();
    }

    public String getValue()
        throws ValueNotSetException
    {
        if(hasValue())
            return value;
        else
            throw new ValueNotSetException();
    }

    public String debugString()
    {
        String dbgs = "(str: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + value;
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String value;
}
