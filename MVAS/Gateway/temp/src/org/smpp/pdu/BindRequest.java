// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindRequest.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, AddressRange, PDUException, WrongLengthOfStringException, 
//            ByteData, PDU

public abstract class BindRequest extends Request
{

    public abstract boolean isTransmitter();

    public abstract boolean isReceiver();

    public BindRequest(int commandId)
    {
        super(commandId);
        systemId = "";
        password = "";
        systemType = "";
        addressRange = new AddressRange();
        interfaceVersion = 52;
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setSystemId(buffer.removeCString());
        setPassword(buffer.removeCString());
        setSystemType(buffer.removeCString());
        setInterfaceVersion(buffer.removeByte());
        addressRange.setData(buffer);
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getSystemId());
        buffer.appendCString(getPassword());
        buffer.appendCString(getSystemType());
        buffer.appendByte(getInterfaceVersion());
        buffer.appendBuffer(getAddressRange().getData());
        return buffer;
    }

    public void setSystemId(String sysId)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(sysId, 16);
        systemId = sysId;
    }

    public void setPassword(String pwd)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(pwd, 9);
        password = pwd;
    }

    public void setSystemType(String type)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(type, 13);
        systemType = type;
    }

    public void setInterfaceVersion(byte vers)
    {
        interfaceVersion = vers;
    }

    public void setAddressRange(AddressRange adr)
    {
        addressRange = adr;
    }

    public void setAddressRange(String rangeString)
        throws WrongLengthOfStringException
    {
        setAddressRange(new AddressRange(rangeString));
    }

    public void setAddressRange(byte ton, byte npi, String rangeString)
        throws WrongLengthOfStringException
    {
        setAddressRange(new AddressRange(ton, npi, rangeString));
    }

    public String getSystemId()
    {
        return systemId;
    }

    public String getPassword()
    {
        return password;
    }

    public String getSystemType()
    {
        return systemType;
    }

    public byte getInterfaceVersion()
    {
        return interfaceVersion;
    }

    public AddressRange getAddressRange()
    {
        return addressRange;
    }

    public String debugString()
    {
        String dbgs = "(bindreq: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSystemId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getPassword();
        dbgs = dbgs + " ";
        dbgs = dbgs + getSystemType();
        dbgs = dbgs + " ";
        dbgs = dbgs + Integer.toString(getInterfaceVersion());
        dbgs = dbgs + " ";
        dbgs = dbgs + getAddressRange().debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String systemId;
    private String password;
    private String systemType;
    private AddressRange addressRange;
    private byte interfaceVersion;
}
