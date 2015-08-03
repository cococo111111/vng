// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExecuteInsertReceiveLog.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.vmg.sms.process:
//            MsgQueue, MsgObject, Logger, ConsoleSRV, 
//            DBPool

public class ExecuteInsertReceiveLog extends Thread
{

    public ExecuteInsertReceiveLog(MsgQueue queueLog)
    {
        AM = new BigDecimal(-1D);
        queueLog = queueLog;
    }

    public static void add2queueReceiveLog(MsgObject msgObject)
    {
        queueLog.add(msgObject);
    }

    public void run()
    {
        MsgObject msgObject = null;
        BigDecimal returnId = AM;
        try
        {
            sleep(1000L);
        }
        catch(InterruptedException interruptedexception) { }
        try
        {
            while(ConsoleSRV.processData) 
            {
                returnId = AM;
                try
                {
                    msgObject = (MsgObject)queueLog.remove();
                    returnId = processQueueMsg(msgObject);
                    if(returnId.equals(AM))
                        queueLog.add(msgObject);
                }
                catch(Exception ex)
                {
                    Util.logger.error(ex.toString());
                    queueLog.add(msgObject);
                }
                sleep(50L);
            }
        }
        catch(Exception ex)
        {
            Util.logger.crisis("Error: ExecuteAddReceivelog.run :" + ex.toString());
        }
    }

    private BigDecimal processQueueMsg(MsgObject msgObject)
    {
        BigDecimal returnid = add2SMSReceiveLog(msgObject);
        return returnid;
    }

    private static BigDecimal add2SMSReceiveLog(MsgObject msgObject)
    {
        PreparedStatement statement;
        String sSQLInsert;
        Connection connection;
        DBPool dbpool;
        Util.logger.info("add2SMSReceiveLog:{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}");
        statement = null;
        sSQLInsert = null;
        connection = null;
        dbpool = new DBPool();
        String tablename = "sms_receive_log" + (new SimpleDateFormat("yyyyMM")).format(new Date());
        sSQLInsert = "insert into " + tablename + "(REQUEST_ID,USER_ID,SERVICE_ID,MOBILE_OPERATOR,COMMAND_CODE,INFO,RECEIVE_DATE,RESPONDED,CPID,ID)" + " values(?,?,?,?,?,?,?,?,?,?)";
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
        statement.setInt(10, msgObject.getMo_id());
        if(statement.executeUpdate() == 1)
            break MISSING_BLOCK_LABEL_376;
        Util.logger.error("add2ReceiveLog: {userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}" + ":statement.executeUpdate failed");
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        statement.close();
        bigdecimal = msgObject.getRequestid();
        return bigdecimal;
        SQLException e;
        e;
        Util.logger.error("add2ReceiveLog:{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}" + ":Error add row from sms receive log:" + e.toString());
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        e;
        Util.logger.error("add2ReceiveLog:{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}:Error add row from sms receive log:" + e.toString());
        bigdecimal = new BigDecimal(-1D);
        return bigdecimal;
        local;
        dbpool.cleanup(connection);
        JVM INSTR ret 7;
    }

    static MsgQueue queueLog = null;
    BigDecimal AM;

}
