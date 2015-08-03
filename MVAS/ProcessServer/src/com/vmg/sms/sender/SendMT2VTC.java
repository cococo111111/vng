// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendMT2VTC.java

package com.vmg.sms.sender;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import oracle.soap.transport.http.OracleSOAPHTTPConnection;
import org.apache.soap.*;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.rpc.*;
import sun.misc.BASE64Encoder;

public class SendMT2VTC
{

    public SendMT2VTC()
    {
        m_httpConnection = null;
        m_soapMappingRegistry = null;
        m_soapMappingRegistries = new HashMap();
        m_headers = null;
        System.setProperty("oracle.soap.transport.noHTTPClient", "true");
        m_httpConnection = new OracleSOAPHTTPConnection();
        m_soapMappingRegistry = new SOAPMappingRegistry();
        Properties props = new Properties();
        props.put("http.authType", SOAPHTTP_SMS_TYPE);
        props.put("http.username", SOAPHTTP_SMS_USER);
        props.put("http.password", SOAPHTTP_SMS_PASS);
        m_httpConnection.setProperties(props);
    }

    public void sendMessages(MsgObject msgObject, Keyword keyword)
    {
        try
        {
            String Respond = sendMTEx(msgObject.getUserid(), msgObject.getUsertext(), msgObject.getServiceid(), keyword.getKeyword(), msgObject.getMsgtype(), msgObject.getRequestid().toString(), "1", "1", "0", msgObject.getContenttype(), msgObject.getMobileoperator());
            Util.logger.info("{Respond=" + Respond + "}{userid=" + msgObject.getUserid() + "}{requestid=" + msgObject.getRequestid() + "}{message=" + msgObject.getUsertext() + "}");
        }
        catch(Exception ex)
        {
            Util.logger.error("{userid=" + msgObject.getUserid() + "}{requestid=" + msgObject.getRequestid() + "}{message=" + msgObject.getUsertext() + "}{Error=" + ex.toString() + "}");
        }
    }

    private String sendMTEx(String sMobileNumber, String sMessage, String sServiceId, String sCommandCode, String sMessageType, String sRequestId, String sTotalMessage, 
            String sMessageIndex, String sIsMore, String sContentType, String sOperator)
    {
        String soapActionURI = "";
        String encodingStyleURI = "http://schemas.xmlsoap.org/soap/encoding/";
        Vector params = new Vector();
        String strTextEncoded = "";
        BASE64Encoder encoder = new BASE64Encoder();
        strTextEncoded = encoder.encode(sMessage.getBytes());
        params.add(new Parameter("MO_ID", java.lang.Integer.class, sRequestId, null));
        params.add(new Parameter("MessageID", java.lang.Integer.class, sMessageIndex, null));
        params.add(new Parameter("UserID", java.lang.String.class, sMobileNumber, null));
        params.add(new Parameter("Message", java.lang.String.class, strTextEncoded, null));
        params.add(new Parameter("ServiceID", java.lang.String.class, sServiceId, null));
        params.add(new Parameter("CommandCode", java.lang.String.class, sCommandCode, null));
        params.add(new Parameter("Operator", java.lang.String.class, sOperator, null));
        params.add(new Parameter("MsgType", java.lang.Integer.class, sMessageType, null));
        params.add(new Parameter("ContentType", java.lang.Integer.class, sContentType, null));
        params.add(new Parameter("SumMT", java.lang.Integer.class, sTotalMessage, null));
        params.add(new Parameter("PartnerUsername", java.lang.String.class, SOAPHTTP_SMS_PARTNERUSER, null));
        params.add(new Parameter("PartnerPassword", java.lang.String.class, SOAPHTTP_SMS_PARTNERPASS, null));
        Response response = null;
        try
        {
            response = makeSOAPCallRPC(SOAPHTTP_SMS_METHOD, params, encodingStyleURI, soapActionURI);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        Parameter returnValue = response.getReturnValue();
        String resp = ((String) (returnValue.getValue()));
        return resp;
    }

    private Response makeSOAPCallRPC(String methodName, Vector params, String encodingStyleURI, String soapActionURI)
        throws Exception
    {
        Call call = new Call();
        call.setSOAPTransport(m_httpConnection);
        SOAPMappingRegistry registry;
        if((registry = (SOAPMappingRegistry)m_soapMappingRegistries.get(methodName)) != null)
            call.setSOAPMappingRegistry(registry);
        else
        if(m_soapMappingRegistry != null)
            call.setSOAPMappingRegistry(m_soapMappingRegistry);
        call.setTargetObjectURI(SOAPHTTP_SMS_SERVICE_NAME);
        call.setMethodName(methodName);
        call.setEncodingStyleURI(encodingStyleURI);
        call.setParams(params);
        if(m_headers != null)
            call.setHeader(m_headers);
        Response response = call.invoke(new URL(SOAPHTTP_SMS_URL), soapActionURI);
        System.out.println("URL=" + SOAPHTTP_SMS_URL);
        if(response.generatedFault())
        {
            Fault fault = response.getFault();
            throw new SOAPException(fault.getFaultCode(), fault.getFaultString());
        } else
        {
            return response;
        }
    }

    private OracleSOAPHTTPConnection m_httpConnection;
    private SOAPMappingRegistry m_soapMappingRegistry;
    private HashMap m_soapMappingRegistries;
    private Header m_headers;
    static String SOAPHTTP_SMS_URL;
    static String SOAPHTTP_SMS_METHOD_URI;
    static String SOAPHTTP_SMS_SERVICE_NAME;
    static String SOAPHTTP_SMS_METHOD;
    static String SOAPHTTP_SMS_USER;
    static String SOAPHTTP_SMS_PASS;
    static String SOAPHTTP_SMS_PARTNERUSER;
    static String SOAPHTTP_SMS_PARTNERPASS;
    static String SOAPHTTP_SMS_TYPE = "basic";

    static 
    {
        SOAPHTTP_SMS_URL = Constants._prop.getProperty("SOAP_VTC_URL", "http://118.102.3.231/WebServiceModule/services");
        SOAPHTTP_SMS_METHOD_URI = Constants._prop.getProperty("SOAP_VTC_URI", "Receiver?wsdl");
        SOAPHTTP_SMS_SERVICE_NAME = Constants._prop.getProperty("SOAP_VTC_SERVICENAME", "Receiver");
        SOAPHTTP_SMS_METHOD = Constants._prop.getProperty("SOAP_VTC_METHOD", "mtReceiver");
        SOAPHTTP_SMS_USER = Constants._prop.getProperty("SOAP_VTC_USER", "webservice");
        SOAPHTTP_SMS_PASS = Constants._prop.getProperty("SOAP_VTC_PASSWORD", "adminvngame223344");
        SOAPHTTP_SMS_PARTNERUSER = Constants._prop.getProperty("SOAP_VTC_PARTNERUSER", "partneruser");
        SOAPHTTP_SMS_PARTNERPASS = Constants._prop.getProperty("SOAP_VTC_PARTNERPASS", "partnerpass");
    }
}
