// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUHeader.java

package org.smpp.pdu;

import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;

// Referenced classes of package org.smpp.pdu:
//            ByteData

public class PDUHeader extends ByteData
{

    public PDUHeader()
    {
        commandLength = 0;
        commandId = 0;
        commandStatus = 0;
        sequenceNumber = 1;
    }

    public ByteBuffer getData()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendInt(getCommandLength());
        buffer.appendInt(getCommandId());
        buffer.appendInt(getCommandStatus());
        buffer.appendInt(getSequenceNumber());
        return buffer;
    }

    public void setData(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException
    {
        commandLength = buffer.removeInt();
        commandId = buffer.removeInt();
        commandStatus = buffer.removeInt();
        sequenceNumber = buffer.removeInt();
    }

    public int getCommandLength()
    {
        return commandLength;
    }

    public int getCommandId()
    {
        return commandId;
    }

    public int getCommandStatus()
    {
        return commandStatus;
    }

    public int getSequenceNumber()
    {
        return sequenceNumber;
    }

    public void setCommandLength(int cmdLen)
    {
        commandLength = cmdLen;
    }

    public void setCommandId(int cmdId)
    {
        commandId = cmdId;
    }

    public void setCommandStatus(int cmdStatus)
    {
        commandStatus = cmdStatus;
    }

    public void setSequenceNumber(int seqNr)
    {
        sequenceNumber = seqNr;
    }

    private int commandLength;
    private int commandId;
    private int commandStatus;
    private int sequenceNumber;
}
