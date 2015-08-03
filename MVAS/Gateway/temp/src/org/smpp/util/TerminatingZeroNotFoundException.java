// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TerminatingZeroNotFoundException.java

package org.smpp.util;

import org.smpp.SmppException;

public class TerminatingZeroNotFoundException extends SmppException
{

    public TerminatingZeroNotFoundException()
    {
        super("Terminating zero not found in buffer.");
    }

    public TerminatingZeroNotFoundException(String s)
    {
        super(s);
    }
}
