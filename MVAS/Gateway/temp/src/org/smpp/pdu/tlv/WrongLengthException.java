// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrongLengthException.java

package org.smpp.pdu.tlv;


// Referenced classes of package org.smpp.pdu.tlv:
//            TLVException

public class WrongLengthException extends TLVException
{

    public WrongLengthException()
    {
        super("The TLV is shorter or longer than allowed.");
    }

    public WrongLengthException(int min, int max, int actual)
    {
        super("The TLV is shorter or longer than allowed:  min=" + min + " max=" + max + " actual=" + actual + ".");
    }
}
