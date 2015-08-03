// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BindTransciever.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            BindRequest, BindTranscieverResp, Response

public class BindTransciever extends BindRequest
{

    public BindTransciever()
    {
        super(9);
    }

    protected Response createResponse()
    {
        return new BindTranscieverResp();
    }

    public boolean isTransmitter()
    {
        return true;
    }

    public boolean isReceiver()
    {
        return true;
    }
}
