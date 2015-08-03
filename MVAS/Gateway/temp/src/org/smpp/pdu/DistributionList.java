// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributionList.java

package org.smpp.pdu;

import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, WrongLengthOfStringException

public class DistributionList extends ByteData
{

    public DistributionList()
    {
        dlName = "";
    }

    public DistributionList(String dlName)
        throws WrongLengthOfStringException
    {
        this.dlName = "";
        setDlName(dlName);
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, WrongLengthOfStringException
    {
        setDlName(buffer.removeCString());
    }

    public ByteBuffer getData()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getDlName());
        return buffer;
    }

    public void setDlName(String dln)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(dln, 21);
        dlName = dln;
    }

    public String getDlName()
    {
        return dlName;
    }

    public String debugString()
    {
        String dbgs = "(dl: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getDlName();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String dlName;
}
