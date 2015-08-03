// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerPDUEvent.java

package org.smpp;

import java.util.EventObject;
import org.smpp.pdu.PDU;

// Referenced classes of package org.smpp:
//            ReceivedPDUEvent, Receiver, Connection

public class ServerPDUEvent extends ReceivedPDUEvent
{

    public ServerPDUEvent(Receiver source, Connection connection, PDU pdu)
    {
        super(source, connection, pdu);
    }

    public Receiver getReceiver()
    {
        return (Receiver)getSource();
    }
}
