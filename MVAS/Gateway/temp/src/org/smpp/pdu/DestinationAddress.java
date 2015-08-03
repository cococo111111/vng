// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DestinationAddress.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, Address, DistributionList, WrongDestFlagException, 
//            ValueNotSetException, WrongLengthOfStringException

public class DestinationAddress extends ByteData
{

    public DestinationAddress()
    {
        destFlag = 0;
        theAddress = null;
    }

    public DestinationAddress(Address address)
    {
        destFlag = 0;
        theAddress = null;
        setAddress(address);
    }

    public DestinationAddress(String address)
        throws WrongLengthOfStringException
    {
        destFlag = 0;
        theAddress = null;
        setAddress(new Address(address));
    }

    public DestinationAddress(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        destFlag = 0;
        theAddress = null;
        setAddress(new Address(ton, npi, address));
    }

    public DestinationAddress(DistributionList dl)
    {
        destFlag = 0;
        theAddress = null;
        setDistributionList(dl);
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException, WrongDestFlagException
    {
        destFlag = buffer.removeByte();
        switch(destFlag)
        {
        case 1: // '\001'
            Address address = new Address();
            address.setData(buffer);
            setAddress(address);
            break;

        case 2: // '\002'
            DistributionList dl = new DistributionList();
            dl.setData(buffer);
            setDistributionList(dl);
            break;

        default:
            throw new WrongDestFlagException("" + destFlag);
        }
    }

    public ByteBuffer getData()
        throws ValueNotSetException
    {
        if(hasValue())
        {
            ByteBuffer buffer = new ByteBuffer();
            buffer.appendByte(getDestFlag());
            if(isAddress())
            {
                Address address = getAddress();
                buffer.appendBuffer(address.getData());
            } else
            if(isDistributionList())
            {
                DistributionList dl = getDistributionList();
                buffer.appendBuffer(dl.getData());
            }
            return buffer;
        } else
        {
            throw new ValueNotSetException();
        }
    }

    public void setAddress(Address address)
    {
        destFlag = 1;
        theAddress = address;
    }

    public void setDistributionList(DistributionList dl)
    {
        destFlag = 2;
        theAddress = dl;
    }

    public byte getDestFlag()
    {
        return destFlag;
    }

    public ByteData getTheAddress()
    {
        return theAddress;
    }

    public Address getAddress()
    {
        if(isAddress())
            return (Address)theAddress;
        else
            return null;
    }

    public DistributionList getDistributionList()
    {
        if(isDistributionList())
            return (DistributionList)theAddress;
        else
            return null;
    }

    public boolean hasValue()
    {
        return destFlag != 0;
    }

    public boolean isAddress()
    {
        return destFlag == 1;
    }

    public boolean isDistributionList()
    {
        return destFlag == 2;
    }

    public String debugString()
    {
        String dbgs = "(destaddr: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + Integer.toString(getDestFlag());
        dbgs = dbgs + " ";
        dbgs = dbgs + getTheAddress().debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private byte destFlag;
    private ByteData theAddress;
}
