// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Outbind.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, PDU, PDUException, WrongLengthOfStringException, 
//            ByteData, Response

public class Outbind extends Request
{

    public Outbind()
    {
        super(11);
        systemId = "";
        password = "";
    }

    protected Response createResponse()
    {
        return null;
    }

    public boolean canResponse()
    {
        return false;
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setSystemId(buffer.removeCString());
        setPassword(buffer.removeCString());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getSystemId());
        buffer.appendCString(getPassword());
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

    public String getSystemId()
    {
        return systemId;
    }

    public String getPassword()
    {
        return password;
    }

    public String debugString()
    {
        String dbgs = "(outbind: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSystemId();
        dbgs = dbgs + " ";
        dbgs = dbgs + getPassword();
        dbgs = dbgs + ")";
        return dbgs;
    }

    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        } else
        {
            PDU pdu = (PDU)object;
            return pdu.getCommandId() == getCommandId();
        }
    }

    private String systemId;
    private String password;
}
