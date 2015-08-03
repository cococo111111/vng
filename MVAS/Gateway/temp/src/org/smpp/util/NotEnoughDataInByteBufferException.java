// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NotEnoughDataInByteBufferException.java

package org.smpp.util;

import org.smpp.SmppException;

public class NotEnoughDataInByteBufferException extends SmppException
{

    public NotEnoughDataInByteBufferException(int p_available, int p_expected)
    {
        super("Not enough data in byte buffer. Expected " + p_expected + ", available: " + p_available + ".");
        available = p_available;
        expected = p_expected;
    }

    public NotEnoughDataInByteBufferException(String s)
    {
        super(s);
        available = 0;
        expected = 0;
    }

    public int getAvailable()
    {
        return available;
    }

    public int getExpected()
    {
        return expected;
    }

    private int available;
    private int expected;
}
