// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidPDUException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException, PDU

public class InvalidPDUException extends PDUException
{

    public InvalidPDUException(PDU pdu, Exception e)
    {
        super(pdu, e);
        underlyingException = null;
        underlyingException = e;
    }

    public InvalidPDUException(PDU pdu, String s)
    {
        super(pdu, s);
        underlyingException = null;
    }

    public String toString()
    {
        String s = super.toString();
        if(underlyingException != null)
            s = s + "\nUnderlying exception: " + underlyingException.toString();
        return s;
    }

    public Exception getException()
    {
        return underlyingException;
    }

    private Exception underlyingException;
}
