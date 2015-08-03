// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnknownCommandIdException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException, PDUHeader

public class UnknownCommandIdException extends PDUException
{

    public UnknownCommandIdException()
    {
        header = null;
    }

    public UnknownCommandIdException(PDUHeader header)
    {
        this.header = null;
        this.header = header;
    }

    private void checkHeader()
    {
        if(header == null)
            header = new PDUHeader();
    }

    public int getCommandLength()
    {
        return header != null ? header.getCommandLength() : 0;
    }

    public int getCommandId()
    {
        return header != null ? header.getCommandId() : 0;
    }

    public int getCommandStatus()
    {
        return header != null ? header.getCommandStatus() : 0;
    }

    public int getSequenceNumber()
    {
        return header != null ? header.getSequenceNumber() : 0;
    }

    private transient PDUHeader header;
}
