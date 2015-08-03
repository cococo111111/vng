// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteDataList.java

package org.smpp.pdu;

import java.util.Vector;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, TooManyValuesException, PDUException, ValueNotSetException

public abstract class ByteDataList extends ByteData
{

    public abstract ByteData createValue();

    public ByteDataList()
    {
        values = new Vector();
        maxSize = 0;
        lengthOfSize = 4;
    }

    public ByteDataList(int max, int lengthOfSize)
    {
        values = new Vector();
        maxSize = 0;
        this.lengthOfSize = 4;
        maxSize = max;
        if(lengthOfSize != 1 && lengthOfSize != 2 && lengthOfSize != 4)
        {
            throw new Error("Length of the size field is invalid. Expected 1, 2 or 4 and got " + lengthOfSize);
        } else
        {
            this.lengthOfSize = lengthOfSize;
            return;
        }
    }

    private void resetValues()
    {
        values.removeAllElements();
    }

    public void setData(ByteBuffer buffer)
        throws PDUException, NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, TooManyValuesException
    {
        resetValues();
        int nrValues = 0;
        switch(lengthOfSize)
        {
        case 1: // '\001'
            nrValues = ByteData.decodeUnsigned(buffer.removeByte());
            break;

        case 2: // '\002'
            nrValues = ByteData.decodeUnsigned(buffer.removeShort());
            break;

        case 4: // '\004'
            nrValues = buffer.removeInt();
            break;
        }
        for(int i = 0; i < nrValues; i++)
        {
            ByteData value = createValue();
            value.setData(buffer);
            addValue(value);
        }

    }

    public ByteBuffer getData()
        throws ValueNotSetException
    {
        ByteBuffer buffer = new ByteBuffer();
        int nrValues = getCount();
        switch(lengthOfSize)
        {
        case 1: // '\001'
            buffer.appendByte(ByteData.encodeUnsigned((short)nrValues));
            break;

        case 2: // '\002'
            buffer.appendShort(ByteData.encodeUnsigned(nrValues));
            break;

        case 4: // '\004'
            buffer.appendInt(nrValues);
            break;
        }
        for(int i = 0; i < nrValues; i++)
        {
            ByteData value = getValue(i);
            buffer.appendBuffer(value.getData());
        }

        return buffer;
    }

    public int getCount()
    {
        return values.size();
    }

    public void addValue(ByteData value)
        throws TooManyValuesException
    {
        if(getCount() >= maxSize)
        {
            throw new TooManyValuesException();
        } else
        {
            values.add(value);
            return;
        }
    }

    public ByteData getValue(int i)
    {
        if(i < getCount())
            return (ByteData)values.get(i);
        else
            return null;
    }

    public String debugString()
    {
        String dbgs = "(bdlist: ";
        dbgs = dbgs + super.debugString();
        int count = getCount();
        dbgs = dbgs + "(count: " + count + ") ";
        for(int i = 0; i < count; i++)
        {
            ByteData value = getValue(i);
            dbgs = dbgs + (i + 1) + ": " + value.debugString();
            dbgs = dbgs + " ";
        }

        dbgs = dbgs + ") ";
        return dbgs;
    }

    public static final byte BYTE_SIZE = 1;
    public static final byte SHORT_SIZE = 2;
    public static final byte INT_SIZE = 4;
    private Vector values;
    private int maxSize;
    private int lengthOfSize;
}
