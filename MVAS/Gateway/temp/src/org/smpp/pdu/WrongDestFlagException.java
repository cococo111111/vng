// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrongDestFlagException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException, PDU

public class WrongDestFlagException extends PDUException
{

    public WrongDestFlagException()
    {
    }

    public WrongDestFlagException(PDU pdu)
    {
        super(pdu);
    }

    public WrongDestFlagException(String s)
    {
        super(s);
    }

    public WrongDestFlagException(PDU pdu, String s)
    {
        super(pdu, s);
    }
}
