// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MOSender.java

package com.vmg.soap.mo;

import com.vmg.sms.common.DateProc;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.Logger;
import com.vmg.sms.process.MsgObject;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.util.Properties;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

// Referenced classes of package com.vmg.soap.mo:
//            VMGAuthenticator, SimpleParser

public abstract class MOSender
{

    public MOSender()
    {
    }

    public abstract void setTemplate();

    public abstract void setTemplate(String s);

    public String parseResult(String xmlResponse)
    {
        if(xmlResponse.indexOf(">202<") != -1)
            return "202";
        if(xmlResponse.indexOf(">204<") != -1)
            return "204";
        if(xmlResponse.indexOf(">400<") != -1)
            return "400";
        if(xmlResponse.indexOf(">401<") != -1)
            return "401";
        if(xmlResponse.indexOf(">400<") != -1)
            return "404";
        else
            return xmlResponse;
    }

    public String sendMO(String xml, String url, String username, String password, String action, MsgObject msgObject, String text, 
            String commandcode, String userws, String passws)
        throws Exception
    {
        String xmlToSend = parseTemplate(xml, action, msgObject, text, commandcode, userws, passws);
        String resultHttpPost[] = httpPost(url, username, password, action, xmlToSend);
        String responseCode = resultHttpPost[0];
        String xmlResponse = resultHttpPost[1];
        if(!responseCode.equals("200"))
        {
            throw new Exception("THE RESPONSE CODE IS " + responseCode + "  THE XML IN ANSWERING IS " + xmlResponse);
        } else
        {
            String result = parseResult(xmlResponse);
            return result;
        }
    }

    private String[] httpPost(String url, String username, String password, String action, String xml)
        throws Exception
    {
        Authenticator cosmoteAuthenticator = new VMGAuthenticator(username, password);
        Authenticator.setDefault(cosmoteAuthenticator);
        HttpClient client = new HttpClient();
        HttpState state = client.getState();
        state.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(username, password));
        client.setState(state);
        PostMethod method = new PostMethod(url);
        method.setDoAuthentication(true);
        method.getHostAuthState().setAuthAttempted(true);
        method.getHostAuthState().setAuthRequested(true);
        method.getHostAuthState().setPreemptive();
        method.addRequestHeader("Content-Type", "text/xml");
        method.addRequestHeader("SOAPAction", action);
        try
        {
            method.setRequestBody(xml);
        }
        catch(Exception e)
        {
            try
            {
                ByteArrayRequestEntity entity = new ByteArrayRequestEntity(xml.getBytes());
                method.setRequestEntity(entity);
            }
            catch(Exception e1)
            {
                throw new Exception("Impossible to set the xml in the post");
            }
        }
        int iRes = client.executeMethod(method);
        Header headers[] = method.getRequestHeaders();
        Util.logger.info(getClass().getName() + "@" + "HEADER OF THE REQUEST");
        for(int i = 0; i < headers.length; i++)
        {
            Header header = headers[i];
            Util.logger.info(getClass().getName() + "@" + header.getName() + "=" + header.getValue());
        }

        Util.logger.info(getClass().getName() + "@" + "RESULT FO THE CALLING HTML POST REQUEST" + iRes);
        byte response[] = method.getResponseBody();
        String textResponse = new String(response);
        Util.logger.info(getClass().getName() + "@" + "MESSAGE" + textResponse);
        String toReturn[] = {
            iRes, textResponse
        };
        return toReturn;
    }

    private String parseTemplate(String xml, String action, MsgObject msgObject, String text, String commandcode, String usersws, String passws)
        throws Exception
    {
        if("".equals(xml))
            setTemplate();
        else
            setTemplate(xml);
        String msisdn = msgObject.getUserid();
        if(msisdn.startsWith("+"))
            msisdn = msisdn.substring(1);
        Properties props = new Properties();
        props.setProperty("ServiceId", msgObject.getServiceid());
        props.setProperty("UserId", msisdn);
        props.setProperty("CommandCode", commandcode);
        props.setProperty("Message", XMLUtils.xmlEncodeString(text));
        props.setProperty("MO_Id", msgObject.getMo_id());
        props.setProperty("RequestId", msgObject.getRequestid().toString());
        props.setProperty("UserName", usersws);
        props.setProperty("Password", passws);
        props.setProperty("RequestTime", DateProc.getDateTimeString(msgObject.getTTimes()));
        props.setProperty("action", action);
        SimpleParser sp = new SimpleParser(template);
        String xmlToSend = sp.parse(props);
        Util.logger.info(getClass().getName() + "@" + "XML TO SEND " + xmlToSend);
        return xmlToSend;
    }

    protected String template;
}
