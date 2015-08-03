// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnsuccessSME.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Address, WrongLengthOfStringException

public class UnsuccessSME extends Address
{

    public UnsuccessSME()
    {
        errorStatusCode = 0;
    }

    public UnsuccessSME(String address, int err)
        throws WrongLengthOfStringException
    {
        super(address);
        errorStatusCode = 0;
        setErrorStatusCode(err);
    }

    public UnsuccessSME(byte ton, byte npi, String address, int err)
        throws WrongLengthOfStringException
    {
        super(ton, npi, address);
        errorStatusCode = 0;
        setErrorStatusCode(err);
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException
    {
        super.setData(buffer);
        setErrorStatusCode(buffer.removeInt());
    }

    public ByteBuffer getData()
    {
        ByteBuffer buffer = super.getData();
        buffer.appendInt(getErrorStatusCode());
        return buffer;
    }

    public void setErrorStatusCode(int sc)
    {
        errorStatusCode = sc;
    }

    public int getErrorStatusCode()
    {
        return errorStatusCode;
    }

    public String debugString()
    {
        String dbgs = "(unsucsme: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + Integer.toString(getErrorStatusCode());
        dbgs = dbgs + ") ";
        return dbgs;
    }

    public int errorStatusCode;
}
