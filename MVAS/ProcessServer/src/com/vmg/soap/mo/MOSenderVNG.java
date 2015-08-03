// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MOSenderVNG.java

package com.vmg.soap.mo;


// Referenced classes of package com.vmg.soap.mo:
//            MOSender

public class MOSenderVNG extends MOSender
{

    public MOSenderVNG()
    {
    }

    public void setTemplate(String strXml)
    {
        super.template = strXml;
    }

    public void setTemplate()
    {
        super.template = "<?xml version='1.0' encoding='UTF-8'?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><ns1:moReceiver xmlns:ns1=\"Receiver\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><User_ID xsi:type=\"xsd:string\">84972251306</User_ID><Service_ID xsi:type=\"xsd:string\">8242</Service_ID><Command_Code xsi:type=\"xsd:string\">SM</Command_Code><Message xsi:type=\"xsd:string\">U00gMDI=</Message><Request_ID xsi:type=\"xsd:string\">21080702201703801</Request_ID><Operator xsi:type=\"xsd:string\">VIETEL</Operator></ns1:moReceiver></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    }
}
