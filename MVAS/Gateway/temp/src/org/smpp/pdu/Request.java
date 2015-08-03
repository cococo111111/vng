// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Request.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDU, Response

public abstract class Request extends PDU
{

    protected abstract Response createResponse();

    public Request()
    {
    }

    public Request(int commandId)
    {
        super(commandId);
    }

    public Response getResponse()
    {
        Response response = createResponse();
        response.setSequenceNumber(getSequenceNumber());
        response.setOriginalRequest(this);
        return response;
    }

    public int getResponseCommandId()
    {
        Response response = createResponse();
        return response.getCommandId();
    }

    public boolean canResponse()
    {
        return true;
    }

    public boolean isRequest()
    {
        return true;
    }

    public boolean isResponse()
    {
        return false;
    }
}
