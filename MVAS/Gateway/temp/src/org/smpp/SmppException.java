// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SmppException.java

package org.smpp;


public class SmppException extends Exception
{

    public SmppException()
    {
    }

    public SmppException(Exception e)
    {
        super(e.getMessage());
    }

    public SmppException(String s)
    {
        super(s);
    }

    public SmppException(String s, Exception e)
    {
        super(s + e.getMessage());
    }
}
