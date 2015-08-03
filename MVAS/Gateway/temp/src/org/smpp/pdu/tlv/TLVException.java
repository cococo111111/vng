// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TLVException.java

package org.smpp.pdu.tlv;

import org.smpp.pdu.PDUException;

public class TLVException extends PDUException
{

    public TLVException()
    {
    }

    public TLVException(String s)
    {
        super(s);
    }
}
