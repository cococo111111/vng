// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Address.java

package org.smpp.pdu;

import org.smpp.Data;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, WrongLengthOfStringException

public class Address extends ByteData
{

    public Address()
    {
        this(Data.getDefaultTon(), Data.getDefaultNpi(), defaultMaxAddressLength);
    }

    public Address(int maxAddressLength)
    {
        this(Data.getDefaultTon(), Data.getDefaultNpi(), maxAddressLength);
    }

    public Address(byte ton, byte npi, int maxAddressLength)
    {
        this.ton = 0;
        this.npi = 0;
        address = "";
        this.maxAddressLength = defaultMaxAddressLength;
        setTon(ton);
        setNpi(npi);
        try
        {
            setAddress("", maxAddressLength);
        }
        catch(WrongLengthOfStringException e)
        {
            throw new Error("Default address value was longer than default max address length.");
        }
    }

    public Address(String address)
        throws WrongLengthOfStringException
    {
        this(Data.getDefaultTon(), Data.getDefaultNpi(), address, defaultMaxAddressLength);
    }

    public Address(String address, int maxAddressLength)
        throws WrongLengthOfStringException
    {
        this(Data.getDefaultTon(), Data.getDefaultNpi(), address, maxAddressLength);
    }

    public Address(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        this(ton, npi, address, defaultMaxAddressLength);
    }

    public Address(byte ton, byte npi, String address, int maxAddressLength)
        throws WrongLengthOfStringException
    {
        this.ton = 0;
        this.npi = 0;
        this.address = "";
        this.maxAddressLength = defaultMaxAddressLength;
        setTon(ton);
        setNpi(npi);
        setAddress(address, maxAddressLength);
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException
    {
        byte ton = buffer.removeByte();
        byte npi = buffer.removeByte();
        String address = buffer.removeCString();
        setAddress(address);
        setTon(ton);
        setNpi(npi);
    }

    public ByteBuffer getData()
    {
        ByteBuffer addressBuf = new ByteBuffer();
        addressBuf.appendByte(getTon());
        addressBuf.appendByte(getNpi());
        addressBuf.appendCString(getAddress());
        return addressBuf;
    }

    public void setTon(byte ton)
    {
        this.ton = ton;
    }

    public void setNpi(byte npi)
    {
        this.npi = npi;
    }

    public void setAddress(String address)
        throws WrongLengthOfStringException
    {
        setAddress(address, maxAddressLength);
    }

    public void setAddress(String address, int maxAddressLength)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(address, maxAddressLength);
        this.maxAddressLength = maxAddressLength;
        this.address = address;
    }

    public byte getTon()
    {
        return ton;
    }

    public byte getNpi()
    {
        return npi;
    }

    public String getAddress()
    {
        return address;
    }

    public String debugString()
    {
        String dbgs = "(addr: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + Integer.toString(getTon());
        dbgs = dbgs + " ";
        dbgs = dbgs + Integer.toString(getNpi());
        dbgs = dbgs + " ";
        dbgs = dbgs + getAddress();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private byte ton;
    private byte npi;
    private String address;
    private static int defaultMaxAddressLength = 21;
    private int maxAddressLength;

}
