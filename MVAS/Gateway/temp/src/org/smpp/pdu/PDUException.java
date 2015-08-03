// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUException.java

package org.smpp.pdu;

import org.smpp.SmppException;

// Referenced classes of package org.smpp.pdu:
//            PDU

public class PDUException extends SmppException
{

    public PDUException()
    {
        pdu = null;
    }

    public PDUException(PDU pdu)
    {
        this.pdu = null;
        setPDU(pdu);
    }

    public PDUException(String s)
    {
        super(s);
        pdu = null;
    }

    public PDUException(PDU pdu, String s)
    {
        super(s);
        this.pdu = null;
        setPDU(pdu);
    }

    public PDUException(PDU pdu, Exception e)
    {
        super(e);
        this.pdu = null;
        setPDU(pdu);
    }

    public PDUException(PDU pdu, String s, Exception e)
    {
        super(s, e);
        this.pdu = null;
        setPDU(pdu);
    }

    public String toString()
    {
        String s = super.toString();
        if(pdu != null)
            s = s + "\nPDU debug string: " + pdu.debugString();
        return s;
    }

    public void setPDU(PDU pdu)
    {
        this.pdu = pdu;
    }

    public PDU getPDU()
    {
        return pdu;
    }

    public boolean hasPDU()
    {
        return pdu != null;
    }

    private transient PDU pdu;
}
