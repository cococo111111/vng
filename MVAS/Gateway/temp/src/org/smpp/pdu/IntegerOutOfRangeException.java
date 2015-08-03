// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerOutOfRangeException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException

public class IntegerOutOfRangeException extends PDUException
{

    public IntegerOutOfRangeException()
    {
        super("The integer is lower or greater than required.");
    }

    public IntegerOutOfRangeException(int min, int max, int val)
    {
        super("The integer is lower or greater than required:  min=" + min + " max=" + max + " actual=" + val + ".");
    }
}
