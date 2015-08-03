// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AddressRange.java

package org.smpp.pdu;

import org.smpp.Data;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, WrongLengthOfStringException

public class AddressRange extends ByteData
{

    public AddressRange()
    {
        ton = Data.getDefaultTon();
        npi = Data.getDefaultNpi();
        addressRange = "";
        setTon(Data.getDefaultTon());
        setNpi(Data.getDefaultNpi());
    }

    public AddressRange(String addressRange)
        throws WrongLengthOfStringException
    {
        ton = Data.getDefaultTon();
        npi = Data.getDefaultNpi();
        this.addressRange = "";
        setTon(Data.getDefaultTon());
        setNpi(Data.getDefaultNpi());
        setAddressRange(addressRange);
    }

    public AddressRange(byte ton, byte npi, String addressRange)
        throws WrongLengthOfStringException
    {
        this.ton = Data.getDefaultTon();
        this.npi = Data.getDefaultNpi();
        this.addressRange = "";
        setTon(ton);
        setNpi(npi);
        setAddressRange(addressRange);
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException
    {
        byte ton = buffer.removeByte();
        byte npi = buffer.removeByte();
        String addressRange = buffer.removeCString();
        setAddressRange(addressRange);
        setTon(ton);
        setNpi(npi);
    }

    public ByteBuffer getData()
    {
        ByteBuffer addressBuf = new ByteBuffer();
        addressBuf.appendByte(getTon());
        addressBuf.appendByte(getNpi());
        addressBuf.appendCString(getAddressRange());
        return addressBuf;
    }

    public void setTon(byte t)
    {
        ton = t;
    }

    public void setNpi(byte n)
    {
        npi = n;
    }

    public void setAddressRange(String a)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(a, 41);
        addressRange = a;
    }

    public byte getTon()
    {
        return ton;
    }

    public byte getNpi()
    {
        return npi;
    }

    public String getAddressRange()
    {
        return addressRange;
    }

    public String debugString()
    {
        String dbgs = "(addrrang: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + Integer.toString(getTon());
        dbgs = dbgs + " ";
        dbgs = dbgs + Integer.toString(getNpi());
        dbgs = dbgs + " ";
        dbgs = dbgs + getAddressRange();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private byte ton;
    private byte npi;
    private String addressRange;
}
