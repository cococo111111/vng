// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Transmitter.java

package org.smpp;

import java.io.IOException;
import org.smpp.debug.Debug;
import org.smpp.pdu.PDU;
import org.smpp.pdu.ValueNotSetException;

// Referenced classes of package org.smpp:
//            SmppObject, Connection

public class Transmitter extends SmppObject
{

    protected Transmitter()
    {
        connection = null;
    }

    public Transmitter(Connection c)
    {
        connection = null;
        connection = c;
    }

    public void send(PDU pdu)
        throws ValueNotSetException, IOException
    {
        SmppObject.debug.enter(7, this, "send");
        pdu.assignSequenceNumber();
        try
        {
            SmppObject.debug.write(7, "going to send pdu's data over connection");
            connection.send(pdu.getData());
            SmppObject.debug.write(7, "successfully sent pdu's data over connection");
        }
        finally
        {
            SmppObject.debug.exit(7, this);
        }
    }

    private Connection connection;
}
