// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Response.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDU, Request

public abstract class Response extends PDU
{

    public Response()
    {
        originalRequest = null;
    }

    public Response(int commandId)
    {
        super(commandId);
        originalRequest = null;
    }

    public boolean canResponse()
    {
        return false;
    }

    public boolean isRequest()
    {
        return false;
    }

    public boolean isResponse()
    {
        return true;
    }

    public void setOriginalRequest(Request originalRequest)
    {
        this.originalRequest = originalRequest;
    }

    public Request getOriginalRequest()
    {
        return originalRequest;
    }

    private Request originalRequest;
}
