// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeoutException.java

package org.smpp;


// Referenced classes of package org.smpp:
//            SmppException

public class TimeoutException extends SmppException
{

    private TimeoutException()
    {
        timeout = 0L;
        expected = 0;
        received = 0;
    }

    public TimeoutException(long timeout, int expected, int received)
    {
        super("The rest of pdu not received for " + timeout / 1000L + " seconds. " + "Expected " + expected + " bytes, received " + received + " bytes.");
        this.timeout = 0L;
        this.expected = 0;
        this.received = 0;
        this.timeout = timeout;
        this.expected = expected;
        this.received = received;
    }

    public long timeout;
    public int expected;
    public int received;
}
