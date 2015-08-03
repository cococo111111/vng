// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindResponse.java

package org.smpp.pdu;

import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, WrongLengthOfStringException, ValueNotSetException, 
//            PDU, ByteData

public abstract class BindResponse extends Response
{

    public BindResponse(int commandId)
    {
        super(commandId);
        systemId = "";
        scInterfaceVersion = new TLVByte((short)528);
        registerOptional(scInterfaceVersion);
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        if(getCommandStatus() == 0)
            setSystemId(buffer.removeCString());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        if(getCommandStatus() == 0)
            buffer.appendCString(getSystemId());
        return buffer;
    }

    public void setSystemId(String sysId)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(sysId, 16);
        systemId = sysId;
    }

    public String getSystemId()
    {
        return systemId;
    }

    public boolean hasScInterfaceVersion()
    {
        return scInterfaceVersion.hasValue();
    }

    public void setScInterfaceVersion(byte value)
    {
        scInterfaceVersion.setValue(value);
    }

    public byte getScInterfaceVersion()
        throws ValueNotSetException
    {
        return scInterfaceVersion.getValue();
    }

    public String debugString()
    {
        String dbgs = "(bindresp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSystemId();
        if(hasScInterfaceVersion())
        {
            dbgs = dbgs + " ";
            try
            {
                dbgs = dbgs + getScInterfaceVersion();
            }
            catch(Exception e) { }
        }
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String systemId;
    private TLVByte scInterfaceVersion;
}
