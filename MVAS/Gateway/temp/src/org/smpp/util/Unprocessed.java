// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Unprocessed.java

package org.smpp.util;

import org.smpp.Data;

// Referenced classes of package org.smpp.util:
//            ByteBuffer

public class Unprocessed
{

    public Unprocessed()
    {
        unprocessed = new ByteBuffer();
        expected = 0;
        hasUnprocessed = false;
        lastTimeReceived = 0L;
    }

    public void reset()
    {
        hasUnprocessed = false;
        unprocessed.setBuffer(null);
        expected = 0;
    }

    public void check()
    {
        hasUnprocessed = unprocessed.length() > 0;
    }

    public void setHasUnprocessed(boolean value)
    {
        hasUnprocessed = value;
    }

    public void setExpected(int value)
    {
        expected = value;
    }

    public void setLastTimeReceived(long value)
    {
        lastTimeReceived = value;
    }

    public void setLastTimeReceived()
    {
        lastTimeReceived = Data.getCurrentTime();
    }

    public ByteBuffer getUnprocessed()
    {
        return unprocessed;
    }

    public boolean getHasUnprocessed()
    {
        return hasUnprocessed;
    }

    public int getExpected()
    {
        return expected;
    }

    public long getLastTimeReceived()
    {
        return lastTimeReceived;
    }

    private ByteBuffer unprocessed;
    private int expected;
    private boolean hasUnprocessed;
    private long lastTimeReceived;
}
