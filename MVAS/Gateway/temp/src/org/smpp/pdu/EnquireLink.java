// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnquireLink.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, EnquireLinkResp, PDUException, PDU, 
//            Response

public class EnquireLink extends Request
{

    public EnquireLink()
    {
        super(21);
    }

    protected Response createResponse()
    {
        return new EnquireLinkResp();
    }

    public void setBody(ByteBuffer bytebuffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
    }

    public ByteBuffer getBody()
    {
        return null;
    }

    public String debugString()
    {
        String dbgs = "(enquirelink: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }
}
