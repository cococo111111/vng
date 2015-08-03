// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUData.java

package com.vmg.smpp.gateway;

import java.io.Serializable;
import org.smpp.pdu.PDU;

public class PDUData
    implements Serializable
{

    public PDUData()
    {
        pdu = null;
        requestID = "0";
    }

    public void setPDU(PDU value)
    {
        pdu = value;
    }

    public PDU getPDU()
    {
        return pdu;
    }

    public void setRequestID(String value)
    {
        requestID = value;
    }

    public String getRequestID()
    {
        return requestID;
    }

    private PDU pdu;
    private String requestID;
}
