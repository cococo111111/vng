// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CancelSMResp.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, PDU

public class CancelSMResp extends Response
{

    public CancelSMResp()
    {
        super(0x80000008);
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
        String dbgs = "(cancel_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + ") ";
        return dbgs;
    }
}
