// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnexpectedOptionalParameterException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException

public class UnexpectedOptionalParameterException extends PDUException
{

    public UnexpectedOptionalParameterException()
    {
        super("The optional parameter wasn't expected for the PDU.");
        tag = 0;
    }

    public UnexpectedOptionalParameterException(short tag)
    {
        super("The optional parameter wasn't expected for the PDU: tag=" + tag + ".");
        this.tag = 0;
    }

    private int tag;
}
