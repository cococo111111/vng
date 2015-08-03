// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentAbstract.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.vmg.sms.process:
//            MsgObject, Logger, DBPool, Keyword

public abstract class ContentAbstract
{

    public ContentAbstract()
    {
    }

    public void start(MsgObject msgObject, Keyword keyword)
        throws Exception
    {
        try
        {
            Collection messages = getMessages(msgObject, keyword);
            if(messages != null)
            {
                Iterator iter = messages.iterator();
                for(int i = 1; iter.hasNext(); i++)
                {
                    MsgObject msgMT = (MsgObject)iter.next();
                    sendMT(msgMT);
                }

            } else
            {
                Util.logger.info("ContentAbstract@start:" + msgObject.getUserid() + "@TO" + msgObject.getServiceid() + "@" + msgObject.getUsertext() + ": LOST MESSAGE");
            }
        }
        catch(Exception e)
        {
            Util.logger.info("ContentAbstract@start:" + msgObject.getUserid() + "@TO" + msgObject.getServiceid() + "@" + msgObject.getUsertext() + ": LOST MESSAGE" + e.toString());
        }
    }

    protected abstract Collection getMessages(MsgObject msgobject, Keyword keyword)
        throws Exception;

    private static synchronized Collection splitMsg(String arg)
    {
        String result[] = new String[3];
        Vector v = new Vector();
        int segment = 0;
        if(arg.length() <= 160)
        {
            result[0] = arg;
            v.add(result[0]);
            return v;
        }
        segment = 160;
        StringTokenizer tk = new StringTokenizer(arg, " ");
        String temp = "";
        int j = 0;
        while(tk.hasMoreElements()) 
        {
            String token = (String)tk.nextElement();
            if(temp.equals(""))
                temp = temp + token;
            else
                temp = temp + " " + token;
            if(temp.length() > segment)
            {
                temp = token;
                j++;
            } else
            {
                result[j] = temp;
            }
            if(j == 3)
                break;
        }
        for(int i = 0; i < result.length; i++)
            if(result[i] != null)
                v.add(result[i]);

        return v;
    }

    private static int sendMT(MsgObject msgObject)
    {
        Connection connection;
        PreparedStatement statement;
        DBPool dbpool;
        connection = null;
        statement = null;
        String sqlString = null;
        if("".equalsIgnoreCase(msgObject.getUsertext().trim()) || msgObject.getUsertext() == null)
        {
            Util.logger.error("ContentAbstract@sendMT@{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}@message is null - LOST MESSAGE");
            return 1;
        }
        dbpool = new DBPool();
        Util.logger.info("ContentAbstract@sendMT@{userid=" + msgObject.getUserid() + "}{contenttype=" + msgObject.getContenttype() + "}{usertext=" + msgObject.getUsertext() + "}{msgtype=" + msgObject.getMsgtype() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}");
        connection = dbpool.getConnectionGateway();
        if(connection != null) goto _L2; else goto _L1
_L1:
        Util.logger.crisis("ContentAbstract@sendMT: Error connection == null" + msgObject.getUserid() + "@TO" + msgObject.getServiceid() + "@" + msgObject.getUsertext() + "@requestid=" + msgObject.getRequestid().toString());
_L5:
        return -1;
_L2:
        String sqlString = "INSERT INTO ems_send_queue( USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID, CONTENT_TYPE,cpid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        statement = connection.prepareStatement(sqlString);
        statement.setString(1, msgObject.getUserid());
        statement.setString(2, msgObject.getServiceid());
        statement.setString(3, msgObject.getMobileoperator());
        statement.setString(4, msgObject.getKeyword());
        statement.setString(5, msgObject.getUsertext());
        statement.setTimestamp(6, null);
        statement.setTimestamp(7, null);
        statement.setInt(8, 0);
        statement.setInt(9, msgObject.getMsgtype());
        statement.setBigDecimal(10, msgObject.getRequestid());
        statement.setString(11, "1");
        statement.setInt(12, msgObject.getContenttype());
        statement.setInt(13, msgObject.getCpid());
        if(statement.executeUpdate() == 1) goto _L4; else goto _L3
_L3:
        Util.logger.crisis("ContentAbstract@sendMT: Error@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString());
          goto _L5
_L4:
        return 1;
        SQLException e;
        e;
        Util.logger.crisis("ContentAbstract@sendMT: Error:@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString() + "@" + e.toString());
          goto _L5
        e;
        Util.logger.crisis("ContentAbstract@sendMT: Error:@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString() + "@" + e.toString());
          goto _L5
        local;
        dbpool.cleanup(statement);
        dbpool.cleanup(connection);
        JVM INSTR ret 6;
    }
}
