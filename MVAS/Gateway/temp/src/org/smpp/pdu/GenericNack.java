// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericNack.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, PDU

public class GenericNack extends Response
{

    public GenericNack()
    {
        super(0x80000000);
    }

    public GenericNack(int commandStatus, int sequenceNumber)
    {
        super(0x80000000);
        setCommandStatus(commandStatus);
        setSequenceNumber(sequenceNumber);
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
        String dbgs = "(genericnack: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + ")";
        return dbgs;
    }
}
