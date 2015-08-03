// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnquireLinkResp.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, PDU

public class EnquireLinkResp extends Response
{

    public EnquireLinkResp()
    {
        super(0x80000015);
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
        String dbgs = "(enquirelink_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }
}
