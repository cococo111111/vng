// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Connection.java

package org.smpp;

import java.io.IOException;
import org.smpp.util.ByteBuffer;

// Referenced classes of package org.smpp:
//            SmppObject

public abstract class Connection extends SmppObject
{

    protected Connection()
    {
        commsTimeout = 60000L;
        receiveTimeout = 10000L;
    }

    public abstract void open()
        throws IOException;

    public abstract void close()
        throws IOException;

    public abstract void send(ByteBuffer bytebuffer)
        throws IOException;

    public abstract ByteBuffer receive()
        throws IOException;

    public abstract Connection accept()
        throws IOException;

    public synchronized void setCommsTimeout(long commsTimeout)
    {
        this.commsTimeout = commsTimeout;
    }

    public synchronized void setReceiveTimeout(long receiveTimeout)
    {
        this.receiveTimeout = receiveTimeout;
    }

    public synchronized long getCommsTimeout()
    {
        return commsTimeout;
    }

    public synchronized long getReceiveTimeout()
    {
        return receiveTimeout;
    }

    private long commsTimeout;
    private long receiveTimeout;
}
