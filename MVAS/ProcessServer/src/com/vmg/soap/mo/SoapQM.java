// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SoapQM.java

package com.vmg.soap.mo;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

// Referenced classes of package com.vmg.soap.mo:
//            MOSender

public class SoapQM extends ContentAbstract
{

    public SoapQM()
    {
    }

    public String sendMessageMO(MsgObject msgObject, String textMessage, String partner, String commandcode)
        throws Exception
    {
        String url = Constants._prop.getProperty("mo.soap." + partner + ".url");
        String username = Constants._prop.getProperty("mo.soap." + partner + ".username");
        String password = Constants._prop.getProperty("mo.soap." + partner + ".password");
        String action = Constants._prop.getProperty("mo.soap." + partner + ".action");
        String module = Constants._prop.getProperty("mo.soap." + partner + ".module");
        String xml = Constants._prop.getProperty("mo.soap." + partner + ".xml");
        String userws = Constants._prop.getProperty("mo.soap." + partner + ".userws");
        String passws = Constants._prop.getProperty("mo.soap." + partner + ".passws");
        if(url == null)
            throw new Exception("In the profile is missing mo.soap." + partner + ".url");
        if(module == null)
        {
            throw new Exception("In the profile is missing mo.soap." + partner + ".module");
        } else
        {
            MOSender sender = (MOSender)Class.forName(module).newInstance();
            String result = sender.sendMO(xml, url, username, password, action, msgObject, textMessage, commandcode, userws, passws);
            return result;
        }
    }

    protected Collection getMessages(MsgObject msgObject, Keyword keyword)
        throws Exception
    {
        int iRetries;
        int iTimeout;
        iRetries = Constants.MAX_RETRIES;
        iTimeout = Constants.RETRIES_TIME;
          goto _L1
_L3:
        String result;
        result = sendMessageMO(msgObject, msgObject.getUsertext(), keyword.getOptions(), msgObject.getKeyword());
        if(!"202".equals(result))
            break MISSING_BLOCK_LABEL_148;
        Util.logger.info(getClass().getName() + "@" + "send ok ,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
        return null;
        if(!"400".equals(result))
            break MISSING_BLOCK_LABEL_276;
        Util.logger.info(getClass().getName() + "@" + "result=400 - Bad request ,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
        msgObject.setMsgNotes("400-Badrequest");
        add2SMSSendFailed(msgObject);
        return null;
        if(!"401".equals(result))
            break MISSING_BLOCK_LABEL_404;
        Util.logger.info(getClass().getName() + "@" + "result=401 - duplicate MO ,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
        msgObject.setMsgNotes("401-duplicateMO");
        add2SMSSendFailed(msgObject);
        return null;
        if(!"404".equals(result))
            break MISSING_BLOCK_LABEL_532;
        Util.logger.info(getClass().getName() + "@" + "result=404 - forbidden ,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
        msgObject.setMsgNotes("404-forbidden");
        add2SMSSendFailed(msgObject);
        return null;
        try
        {
            Util.logger.info(getClass().getName() + "@" + "Got result=" + result + ", Going For Retry, Sleeping,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
            iRetries--;
            Thread.sleep(iTimeout * 1000);
        }
        catch(Exception e)
        {
            Util.logger.error(getClass().getName() + "@" + "Some Exception..!! Got -1, Going For Retry, Sleeping,Details: " + "Msisdn: " + msgObject.getUserid() + " Shortcode: " + msgObject.getServiceid() + " Keyword: " + msgObject.getKeyword() + " RequestID: " + msgObject.getRequestid() + "CommandCode: " + msgObject.getKeyword() + " Online Retry countdown: " + iRetries);
            Util.logger.info(getClass().getName() + "@" + "Exception: " + e.toString());
            iRetries--;
            Thread.sleep(iTimeout * 1000);
        }
_L1:
        if(iRetries > 0) goto _L3; else goto _L2
_L2:
        add2SMSSendFailed(msgObject);
        return null;
    }

    private static BigDecimal add2SMSSendFailed(MsgObject msgObject)
    {
        PreparedStatement statement;
        String sSQLInsert;
        Connection connection;
        DBPool dbpool;
        Util.logger.info("add2SMSSendFailed:" + msgObject.getUserid() + "@" + msgObject.getUsertext());
        statement = null;
        sSQLInsert = null;
        connection = null;
        dbpool = new DBPool();
        String tablename = "sms_receive_error";
        sSQLInsert = "insert into " + tablename + "(REQUEST_ID,USER_ID,SERVICE_ID,MOBILE_OPERATOR,COMMAND_CODE,INFO,RECEIVE_DATE,RESPONDED,CPID)" + " values(?,?,?,?,?,?,?,?,?)";
        BigDecimal bigdecimal;
        connection = dbpool.getConnectionGateway();
        statement = connection.prepareStatement(sSQLInsert);
        statement.setBigDecimal(1, msgObject.getRequestid());
        statement.setString(2, msgObject.getUserid());
        statement.setString(3, msgObject.getServiceid());
        statement.setString(4, msgObject.getMobileoperator());
        statement.setString(5, msgObject.getKeyword());
        statement.setString(6, msgObject.getUsertext());
        statement.setTimestamp(7, msgObject.getTTimes());
        statement.setInt(8, 0);
        statement.setInt(9, msgObject.getCpid());
        if(statement.executeUpdate() == 1)
            break MISSING_BLOCK_LABEL_270;
        Util.logger.error("add2SMSSendFailed:" + msgObject.getUserid() + ":" + msgObject.getUsertext() + ":statement.executeUpdate failed");
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        statement.close();
        bigdecimal = msgObject.getRequestid();
        return bigdecimal;
        SQLException e;
        e;
        Util.logger.error("add2SMSSendFailed:" + msgObject.getUserid() + ":" + msgObject.getUsertext() + ":Error add row from sms receive error:" + e.toString());
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        e;
        Util.logger.error("add2SMSSendFailed:" + msgObject.getUserid() + ":" + msgObject.getUsertext() + ":Error add row from sms receive error:" + e.toString());
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        local;
        dbpool.cleanup(connection);
        JVM INSTR ret 7;
    }
}
