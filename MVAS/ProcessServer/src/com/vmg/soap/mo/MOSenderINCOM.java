// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MOSenderINCOM.java

package com.vmg.soap.mo;


// Referenced classes of package com.vmg.soap.mo:
//            MOSender

public class MOSenderINCOM extends MOSender
{

    public MOSenderINCOM()
    {
    }

    public void setTemplate(String strXml)
    {
        super.template = strXml;
    }

    public void setTemplate()
    {
        super.template = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ReceivedMO xmlns=\"http://tempuri.org/smsmoservice81/MOReceiver\"><UserID>$User_ID$</UserID><ServiceID>$Service_ID$</ServiceID><CommandCode>$Command_Code$</CommandCode><RequestID>$Request_ID$</RequestID><SmsContent>$Message$</SmsContent></ReceivedMO></soap:Body></soap:Envelope>";
    }
}
