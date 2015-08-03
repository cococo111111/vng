// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindTransmitter.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            BindRequest, BindTransmitterResp, Response

public class BindTransmitter extends BindRequest
{

    public BindTransmitter()
    {
        super(2);
    }

    protected Response createResponse()
    {
        return new BindTransmitterResp();
    }

    public boolean isTransmitter()
    {
        return true;
    }

    public boolean isReceiver()
    {
        return false;
    }
}
