// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReceivedPDUEvent.java

package org.smpp;

import java.util.EventObject;
import org.smpp.pdu.PDU;

// Referenced classes of package org.smpp:
//            Connection, ReceiverBase

public class ReceivedPDUEvent extends EventObject
{

    public ReceivedPDUEvent(ReceiverBase source, Connection connection, PDU pdu)
    {
        super(source);
        this.connection = null;
        this.pdu = null;
        this.connection = connection;
        this.pdu = pdu;
    }

    public Connection getConnection()
    {
        return connection;
    }

    public PDU getPDU()
    {
        return pdu;
    }

    private transient Connection connection;
    private transient PDU pdu;
}
