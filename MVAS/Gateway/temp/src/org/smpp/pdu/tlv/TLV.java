// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLV.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.ByteData;
import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu.tlv:
//            WrongLengthException, TLVException

public abstract class TLV extends ByteData
{

    public TLV()
    {
        tag = 0;
        valueIsSet = false;
        minLength = DONT_CHECK_LIMIT;
        maxLength = DONT_CHECK_LIMIT;
    }

    public TLV(short tag)
    {
        this.tag = 0;
        valueIsSet = false;
        minLength = DONT_CHECK_LIMIT;
        maxLength = DONT_CHECK_LIMIT;
        this.tag = tag;
    }

    public TLV(int min, int max)
    {
        tag = 0;
        valueIsSet = false;
        minLength = DONT_CHECK_LIMIT;
        maxLength = DONT_CHECK_LIMIT;
        minLength = min;
        maxLength = max;
    }

    public TLV(short tag, int min, int max)
    {
        this.tag = 0;
        valueIsSet = false;
        minLength = DONT_CHECK_LIMIT;
        maxLength = DONT_CHECK_LIMIT;
        this.tag = tag;
        minLength = min;
        maxLength = max;
    }

    protected abstract void setValueData(ByteBuffer bytebuffer)
        throws TLVException;

    protected abstract ByteBuffer getValueData()
        throws ValueNotSetException;

    public void setTag(short tag)
    {
        this.tag = tag;
    }

    public short getTag()
    {
        return tag;
    }

    public int getLength()
        throws ValueNotSetException
    {
        if(hasValue())
        {
            ByteBuffer valueBuf = getValueData();
            if(valueBuf != null)
                return valueBuf.length();
            else
                return 0;
        } else
        {
            return 0;
        }
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TLVException
    {
        short newTag = buffer.removeShort();
        int length = buffer.removeShort();
        ByteBuffer valueBuf = buffer.removeBuffer(length);
        setValueData(valueBuf);
        setTag(newTag);
    }

    public ByteBuffer getData()
        throws ValueNotSetException
    {
        if(hasValue())
        {
            ByteBuffer tlvBuf = new ByteBuffer();
            tlvBuf.appendShort(getTag());
            tlvBuf.appendShort(ByteData.encodeUnsigned(getLength()));
            tlvBuf.appendBuffer(getValueData());
            return tlvBuf;
        } else
        {
            return null;
        }
    }

    protected void markValueSet()
    {
        valueIsSet = true;
    }

    public boolean hasValue()
    {
        return valueIsSet;
    }

    public boolean equals(Object obj)
    {
        if(obj != null && (obj instanceof TLV))
            return getTag() == ((TLV)obj).getTag();
        else
            return false;
    }

    protected static void checkLength(int min, int max, int length)
        throws WrongLengthException
    {
        if(length < min || length > max)
            throw new WrongLengthException(min, max, length);
        else
            return;
    }

    protected void checkLength(int length)
        throws WrongLengthException
    {
        int min = 0;
        int max = 0;
        if(minLength != DONT_CHECK_LIMIT)
            min = minLength;
        else
            min = 0;
        if(maxLength != DONT_CHECK_LIMIT)
            max = maxLength;
        else
            max = 0x7fffffff;
        checkLength(min, max, length);
    }

    protected static void checkLength(int min, int max, ByteBuffer buffer)
        throws WrongLengthException
    {
        int length;
        if(buffer != null)
            length = buffer.length();
        else
            length = 0;
        checkLength(min, max, length);
    }

    protected void checkLength(ByteBuffer buffer)
        throws WrongLengthException
    {
        int length;
        if(buffer != null)
            length = buffer.length();
        else
            length = 0;
        checkLength(length);
    }

    public String debugString()
    {
        String dbgs = "(tlv: ";
        dbgs = dbgs + tag;
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private short tag;
    private boolean valueIsSet;
    private static int DONT_CHECK_LIMIT = -1;
    private int minLength;
    private int maxLength;

}
