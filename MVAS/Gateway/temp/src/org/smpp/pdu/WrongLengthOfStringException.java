// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrongLengthOfStringException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException

public class WrongLengthOfStringException extends PDUException
{

    public WrongLengthOfStringException(String s)
    {
        super(s);
    }

    public WrongLengthOfStringException()
    {
        super("The string is shorter or longer than required.");
    }

    public WrongLengthOfStringException(int min, int max, int actual)
    {
        super("The string is shorter or longer than required:  min=" + min + " max=" + max + " actual=" + actual + ".");
    }
}
