// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVEmpty.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.ByteBuffer;

// Referenced classes of package org.smpp.pdu.tlv:
//            TLV, WrongLengthException

public class TLVEmpty extends TLV
{

    public TLVEmpty()
    {
        super(0, 0);
        present = false;
    }

    public TLVEmpty(short p_tag)
    {
        super(p_tag, 0, 0);
        present = false;
    }

    public TLVEmpty(short p_tag, boolean p_present)
    {
        super(p_tag, 0, 0);
        present = false;
        present = p_present;
        markValueSet();
    }

    public ByteBuffer getValueData()
    {
        return null;
    }

    public void setValueData(ByteBuffer buffer)
        throws WrongLengthException
    {
        checkLength(buffer);
        setValue(true);
    }

    public void setValue(boolean p_present)
    {
        present = p_present;
        markValueSet();
    }

    public boolean getValue()
        throws ValueNotSetException
    {
        if(hasValue())
            return present;
        else
            throw new ValueNotSetException();
    }

    public String debugString()
    {
        String dbgs = "(empty: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private boolean present;
}
