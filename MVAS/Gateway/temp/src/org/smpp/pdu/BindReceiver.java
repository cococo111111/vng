// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindReceiver.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            BindRequest, BindReceiverResp, Response

public class BindReceiver extends BindRequest
{

    public BindReceiver()
    {
        super(1);
    }

    protected Response createResponse()
    {
        return new BindReceiverResp();
    }

    public boolean isTransmitter()
    {
        return false;
    }

    public boolean isReceiver()
    {
        return true;
    }
}
