// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBTools.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// Referenced classes of package com.vmg.smpp.gateway:
//            DBException, Logger, EMSData, Gateway, 
//            Preference, LoadConfig, Keyword

public class DBTools
{

    public DBTools()
    {
        util = null;
        util = new Utilities();
    }

    public static BigDecimal add2SMSReceiveQueueR(String userId, String serviceId, String mobileOperator, String commandCode, String info, String RequestID)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        exception = null;
        id = null;
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        String cmdcode = "INV";
        try
        {
            conn = getConnection("gateway");
            Timestamp time = new Timestamp(System.currentTimeMillis());
            int NOT_RESPONDED = 0;
            strSQL = "INSERT INTO sms_receive_queue (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, RECEIVE_DATE, RESPONDED,REQUEST_ID) VALUES ( ?, ?, ?, ?, ?, ?, ?,?)";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setString(1, userId);
            preStmt.setString(2, serviceId);
            preStmt.setString(3, mobileOperator);
            preStmt.setString(4, cmdcode);
            preStmt.setString(5, info);
            preStmt.setTimestamp(6, time);
            preStmt.setInt(7, NOT_RESPONDED);
            preStmt.setString(8, RequestID);
            if(preStmt.executeUpdate() != 1)
            {
                conn.rollback();
                Logger.error("DBTools.add2SMSReceiveQueue", "DBTools.add2SMSReceiveQueue(): Error adding row");
                EMSData ems = new EMSData();
                ems.setUserId(userId);
                ems.setServiceId(serviceId);
                ems.setMobileOperator(mobileOperator);
                ems.setCommandCode(cmdcode);
                ems.setText(info);
                ems.setsRequestID(RequestID);
                Gateway.saveSMSObject(RequestID + ".modb", ems);
            } else
            {
                Logger.info("DBTools.add2SMSReceiveQueue", "{Add-MO2DB}{Request_ID=" + RequestID + "}{User_ID=" + userId + "}{Service_ID=" + serviceId + "}{Info=" + info + "}");
                id = new BigDecimal(1);
            }
        }
        catch(SQLException e)
        {
            Logger.error("DBTools.add2SMSReceiveQueue", "Error executing " + strSQL + " >>> " + e.getMessage());
            EMSData ems = new EMSData();
            ems.setUserId(userId);
            ems.setServiceId(serviceId);
            ems.setMobileOperator(mobileOperator);
            ems.setCommandCode(cmdcode);
            ems.setText(info);
            ems.setsRequestID(RequestID);
            Gateway.saveSMSObject(RequestID + ".modb", ems);
            if(e.getMessage().startsWith("ORA-03114"))
                exception = new DBException(e.getMessage());
        }
        catch(Exception e)
        {
            Logger.error("DBTools.add2SMSReceiveQueue", "Exception: " + e.getMessage());
            EMSData ems = new EMSData();
            ems.setUserId(userId);
            ems.setServiceId(serviceId);
            ems.setMobileOperator(mobileOperator);
            ems.setCommandCode(cmdcode);
            ems.setText(info);
            ems.setsRequestID(RequestID);
            Gateway.saveSMSObject(RequestID + ".modb", ems);
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.close();
                if(preStmt != null)
                    preStmt.close();
                if(conn != null)
                    conn.close();
            }
            catch(SQLException sqlexception) { }
        }
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public EMSData getEMSinSendQueue(BigDecimal queueId)
    {
        EMSData ems;
        Connection conn;
        PreparedStatement preStmt;
        String strSQL;
        ResultSet rs;
        String sR;
        ems = null;
        conn = null;
        preStmt = null;
        strSQL = null;
        rs = null;
        sR = "";
        EMSData emsdata;
        if(conn == null)
            conn = getConnection("gateway");
        if(!"0".equalsIgnoreCase(Preference.SEND_MODE))
            sR = Preference.SEND_MODE;
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, INFO, RAW_INFO, SUBMIT_DATE, DONE_DATE, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID, TOTAL_SEGMENTS, RETRIES_NUM FROM ems_send_queue" + sR + " WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, queueId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            ems = new EMSData();
            ems.setUserId(rs.getString(1));
            ems.setServiceId(rs.getString(2));
            ems.setMobileOperator(rs.getString(3));
            ems.setCommandCode(rs.getString(4));
            ems.setContentType(rs.getInt(5));
            ems.setText(rs.getString(6));
            ems.setBytes(rs.getBytes(7));
            ems.setSubmitDate(rs.getTimestamp(8));
            ems.setDoneDate(rs.getTimestamp(9));
            ems.setProcessResult(rs.getInt(10));
            ems.setMessageType(rs.getInt(11));
            ems.setRequestId(rs.getBigDecimal(12));
            ems.setsRequestID(rs.getString(12));
            ems.setMessageId(rs.getString(13));
            ems.setTotalSegments(rs.getInt(14));
            ems.setSendNum(rs.getInt(15));
            ems.setId(queueId);
        }
        emsdata = ems;
        return emsdata;
        SQLException e;
        e;
        Logger.error(getClass().getName(), "SQLException: Error executing SQL " + strSQL + ">>>" + e.toString());
        emsdata = ems;
        return emsdata;
        e;
        Logger.error(getClass().getName(), "Exception: " + e.toString());
        emsdata = ems;
        return emsdata;
        local;
        util.cleanup(rs);
        util.cleanup(conn, preStmt);
        JVM INSTR ret 9;
    }

    public boolean removeFromEMSSendQueue(BigDecimal id)
    {
        Connection conn;
        PreparedStatement preStmt;
        String strSQL;
        boolean result;
        String sr;
        conn = null;
        preStmt = null;
        strSQL = null;
        result = false;
        sr = "";
        boolean flag;
        if(conn == null)
            conn = getConnection("gateway");
        if(!"0".equals(Preference.SEND_MODE))
            sr = Preference.SEND_MODE;
        strSQL = "DELETE FROM EMS_SEND_QUEUE" + sr + " WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        if(preStmt.executeUpdate() >= 1)
            result = true;
        flag = result;
        return flag;
        SQLException e;
        e;
        Logger.error(getClass().getName(), "removeFromEMSSendQueue: Error executing SQL " + strSQL + ">>>" + e.toString());
        flag = result;
        return flag;
        e;
        Logger.error(getClass().getName(), "removeFromEMSSendQueue: " + e.toString());
        flag = result;
        return flag;
        local;
        util.cleanup(conn, preStmt);
        JVM INSTR ret 8;
    }

    public static int Alert2YM(String StrAlert)
        throws DBException
    {
        Connection connection;
        PreparedStatement stmt;
        Utilities util;
        String newissue;
        String sSQL;
        if(Preference.Alert_YM != 1)
            break MISSING_BLOCK_LABEL_221;
        connection = null;
        stmt = null;
        util = new Utilities();
        newissue = StrAlert;
        String newalertmsg = StrAlert;
        if(StrAlert.length() > 20)
            newissue = StrAlert.substring(0, 19);
        if(newalertmsg.length() > 200)
            newalertmsg = StrAlert.substring(0, 199);
        sSQL = "insert into msg_alerter( domain, issue, level,alertmsg,contact) values('Gateway" + Preference.Channel + "',?,'none',?,'gatewayadmin')";
        if(connection == null)
            connection = getConnection("alert");
        stmt = connection.prepareStatement(sSQL);
        stmt.setString(1, newissue);
        stmt.setString(2, StrAlert);
        if(stmt.executeUpdate() != -1) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        return 1;
        Exception ex;
        ex;
        Logger.error("DBTools", "{Alert2YM Error:}" + ex.getMessage());
        if(!ex.getMessage().startsWith("ORA-03114")) goto _L1; else goto _L3
_L3:
        throw new DBException(ex.getMessage());
        local;
        util.cleanup(connection, stmt);
        JVM INSTR ret 8;
        return 0;
    }

    public static int Alert(String StrAlert)
        throws DBException
    {
        Connection connection;
        PreparedStatement stmt;
        Utilities util;
        String newissue;
        String sSQL;
        if(Preference.Alert_YM != 1)
            break MISSING_BLOCK_LABEL_221;
        connection = null;
        stmt = null;
        util = new Utilities();
        newissue = StrAlert;
        String newalertmsg = StrAlert;
        if(StrAlert.length() > 20)
            newissue = StrAlert.substring(0, 19);
        if(newalertmsg.length() > 200)
            newalertmsg = StrAlert.substring(0, 199);
        sSQL = "insert into msg_alerter( domain, issue, level,alertmsg,contact) values('Gateway" + Preference.Channel + "',?,'none',?,'gatewayadmin')";
        if(connection == null)
            connection = getConnection("alert");
        stmt = connection.prepareStatement(sSQL);
        stmt.setString(1, newissue);
        stmt.setString(2, StrAlert);
        if(stmt.executeUpdate() != -1) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        return 1;
        Exception ex;
        ex;
        Logger.error("DBTools", "{Alert2YM Error:}" + ex.getMessage());
        if(!ex.getMessage().startsWith("ORA-03114")) goto _L1; else goto _L3
_L3:
        throw new DBException(ex.getMessage());
        local;
        util.cleanup(connection, stmt);
        JVM INSTR ret 8;
        return 0;
    }

    public static int Alert(String domain, String issue, String level, String alertmsg, String contact)
        throws DBException
    {
        Connection connection;
        PreparedStatement stmt;
        Utilities util;
        String newissue;
        String newalertmsg;
        String newdomain;
        String sSQL;
        if(Preference.Alert_YM != 1)
            break MISSING_BLOCK_LABEL_269;
        connection = null;
        stmt = null;
        util = new Utilities();
        newissue = issue;
        newalertmsg = alertmsg;
        newdomain = Preference.Channel + "@" + domain;
        if(issue.length() > 20)
            newissue = issue.substring(0, 19);
        if(alertmsg.length() > 200)
            newalertmsg = alertmsg.substring(0, 199);
        sSQL = "insert into msg_alerter( domain, issue, level,alertmsg,contact) values(?,?,?,?,?)";
        if(connection == null)
            connection = getConnection("alert");
        stmt = connection.prepareStatement(sSQL);
        stmt.setString(1, newdomain);
        stmt.setString(2, newissue);
        stmt.setString(3, level);
        stmt.setString(4, newalertmsg);
        stmt.setString(5, contact);
        if(stmt.executeUpdate() != -1) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        return 1;
        Exception ex;
        ex;
        Logger.info("DBTools", "{Alert Error:}" + ex.getMessage());
        if(!ex.getMessage().startsWith("ORA-03114")) goto _L1; else goto _L3
_L3:
        throw new DBException(ex.getMessage());
        local;
        util.cleanup(connection, stmt);
        JVM INSTR ret 13;
        return 0;
    }

    public boolean getAllEMSSendQueue(String sRound, String iMod, String iNum, Queue Ems)
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        String sR;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        Keyword keyword = null;
        sR = "";
        try
        {
            if(!"0".equals(sRound))
                sR = sRound;
            conn = getConnection("gateway");
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            strSQL = "select id, user_id, service_id, mobile_operator, command_code, content_type, info,  process_result, message_type, request_id, message_id, total_segments,cpid from ems_send_queue" + sR + " where  info is not null " + "and (mod(id," + iMod + ") = " + iNum + ") and upper(mobile_operator)=upper('" + Preference.mobileOperator + "')";
            preStmt = conn.prepareStatement(strSQL, 1004, 1008);
            if(preStmt.execute())
                for(rs = preStmt.getResultSet(); rs.next();)
                {
                    EMSData ems = new EMSData();
                    ems.setId(rs.getBigDecimal(1));
                    ems.setUserId(rs.getString(2));
                    ems.setServiceId(rs.getString(3));
                    ems.setMobileOperator(rs.getString(4));
                    ems.setCommandCode(rs.getString(5));
                    ems.setContentType(rs.getInt(6));
                    ems.setText(rs.getString(7));
                    ems.setSubmitDate(ts);
                    ems.setDoneDate(ts);
                    ems.setProcessResult(rs.getInt(8));
                    ems.setMessageType(rs.getInt(9));
                    ems.setRequestId(rs.getBigDecimal(10));
                    ems.setMessageId(rs.getString(11));
                    ems.setTotalSegments(rs.getInt(12));
                    String commandcode = rs.getString(5);
                    String serviceid = rs.getString(3);
                    if(Preference.CheckServiceId(serviceid))
                    {
                        if("1".equalsIgnoreCase(Preference.MAPCP))
                        {
                            Keyword keyword = Gateway.loadconfig.getKeyword(commandcode, serviceid);
                            ems.setCpid(keyword.getCpid());
                        } else
                        {
                            ems.setCpid(rs.getInt(13));
                        }
                        try
                        {
                            Logger.info(getClass().getName(), "{getMTfromDB}{Request_ID=" + ems.getRequestId() + "}{User_ID=" + ems.getUserId() + "}");
                            rs.deleteRow();
                            Ems.enqueue(ems);
                        }
                        catch(Exception ex)
                        {
                            Logger.error("DBTools", "{getAllEMSSendQueue: Error executing SQL }{getMTfromDB}{Request_ID=" + ems.getRequestId() + "}{User_ID=" + ems.getUserId() + "}" + strSQL + ": " + ex.toString());
                        }
                    }
                }

        }
        catch(SQLException e)
        {
            Logger.error("DBTools", "{getAllEMSSendQueue: SQLException}" + e.getMessage());
            Logger.error("DBTools", "{getAllEMSSendQueue: Error executing SQL }" + strSQL + ": " + e.toString());
        }
        catch(Exception e)
        {
            Logger.error("DBTools", "{getAllEMSSendQueue: Exception}" + e.getMessage());
        }
        return true;
        local;
        util.cleanup(rs);
        util.cleanup(conn, preStmt);
        if(exception != null)
            throw exception;
        JVM INSTR ret 17;
    }

    public int add2EMSSendLog(EMSData ems)
    {
        int result;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        if(ems == null)
            return 0;
        result = 0;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        int i;
        conn = getConnection("gateway");
        String tablename = "ems_send_log" + (new SimpleDateFormat("yyyyMM")).format(new Date());
        strSQL = "insert into " + tablename + " ( USER_ID, SERVICE_ID, MOBILE_OPERATOR, " + "COMMAND_CODE, CONTENT_TYPE, INFO, SUBMIT_DATE, DONE_DATE, " + "PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID, TOTAL_SEGMENTS, RETRIES_NUM,NOTES,cpid) " + "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, ems.getUserId());
        preStmt.setString(2, ems.getServiceId());
        preStmt.setString(3, ems.getMobileOperator());
        preStmt.setString(4, ems.getCommandCode());
        preStmt.setInt(5, ems.getContentType());
        preStmt.setString(6, ems.getText());
        preStmt.setTimestamp(7, ems.getSubmitDate());
        preStmt.setTimestamp(8, ems.getDoneDate());
        preStmt.setInt(9, ems.getProcessResult());
        preStmt.setInt(10, ems.getMessageType());
        preStmt.setBigDecimal(11, ems.getRequestId());
        preStmt.setString(12, ems.getMessageId());
        preStmt.setInt(13, ems.getTotalSegments());
        preStmt.setInt(14, ems.getSendNum());
        preStmt.setString(15, ems.getNotes());
        preStmt.setInt(16, ems.getCpid());
        result = preStmt.executeUpdate();
        i = result;
        return i;
        SQLException e;
        e;
        Logger.error(getClass().getName(), "SQLException: Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.saveSMSObject(ems.getId() + ".slog", ems);
        i = result;
        return i;
        e;
        Logger.error(getClass().getName(), "Exception: " + e.toString());
        Gateway.saveSMSObject(ems.getId() + ".slog", ems);
        i = result;
        return i;
        local;
        util.cleanup(rs);
        util.cleanup(conn, preStmt);
        JVM INSTR ret 8;
    }

    public EMSData moveEMSFromSendQueueToSendLog(BigDecimal queueId)
    {
        EMSData ems = getEMSinSendQueue(queueId);
        if(ems != null)
        {
            int logId = add2EMSSendLog(ems);
            if(logId != 0)
            {
                int nloop = 0;
                while(!removeFromEMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                return ems;
            }
        }
        return null;
    }

    public EMSData moveEMSFromSendQueueToSendLog(BigDecimal queueId, int processResult)
    {
        EMSData ems = getEMSinSendQueue(queueId);
        if(ems != null)
        {
            ems.setProcessResult(processResult);
            int logId = add2EMSSendLog(ems);
            if(logId != 0)
            {
                int nloop = 0;
                while(!removeFromEMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                return ems;
            }
        }
        return null;
    }

    public static boolean add2CdrQueueb(EMSData ems, String MessageType)
        throws DBException
    {
        DBException exception;
        boolean rt;
        Connection conn;
        PreparedStatement preStmt;
        String strSQL;
        exception = null;
        rt = false;
        conn = null;
        preStmt = null;
        strSQL = null;
        boolean flag;
        if(conn == null)
            conn = getConnection("gateway");
        conn.setAutoCommit(false);
        strSQL = "insert into cdr_queue ( USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS,Message_Type,process_result,request_id,cpid) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, ems.getUserIdEx(0));
        preStmt.setString(2, ems.getServiceIdEx(10));
        preStmt.setString(3, ems.getMobileOperator());
        preStmt.setString(4, ems.getCommandCode());
        String info = ems.getTextEx(true);
        if(info == null)
            info = ems.getCommandCode();
        else
        if(info.length() > 20)
        {
            info = info.substring(0, 20);
            info = info + "...";
        }
        preStmt.setString(5, info);
        preStmt.setTimestamp(6, ems.getSubmitDate());
        preStmt.setTimestamp(7, ems.getDoneDate());
        preStmt.setInt(8, ems.getTotalSegments());
        preStmt.setInt(9, Integer.parseInt(MessageType));
        preStmt.setInt(10, 1);
        preStmt.setBigDecimal(11, ems.getRequestId());
        preStmt.setInt(12, ems.getCpid());
        if(preStmt.executeUpdate() > 0)
        {
            conn.commit();
            rt = true;
        } else
        {
            conn.rollback();
            rt = false;
            Logger.error("DBTools", "Cannot add CDRQueue");
        }
        flag = rt;
        return flag;
        SQLException e;
        e;
        Logger.error("DBTools.add2CdrQueue", "Error executing " + strSQL + " >>> " + e.toString());
        Gateway.saveSMSObject(ems.getId() + ".cdr", ems);
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        flag = rt;
        return flag;
        e;
        Logger.error("DBTools.add2CdrQueue", "Error:" + e.toString());
        Gateway.saveSMSObject(ems.getId() + ".cdr", ems);
        flag = rt;
        return flag;
        local;
        try
        {
            conn.setAutoCommit(true);
            preStmt.close();
            conn.close();
        }
        catch(SQLException ex)
        {
            Logger.error("DBTools", "Error:" + ex.toString());
        }
        if(exception != null)
            throw exception;
        JVM INSTR ret 8;
    }

    private void releaseConnection(Connection conn, PreparedStatement preStmt)
    {
        try
        {
            if(preStmt != null)
                preStmt.close();
        }
        catch(SQLException sqlexception) { }
    }

    private void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs)
    {
        releaseConnection(conn, preStmt);
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException sqlexception) { }
    }

    public static Connection getConnection(String PoolName)
    {
        Connection conn = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/" + PoolName);
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            Logger.error("DBTools", "getConnection Failed!" + e);
        }
        catch(Exception e)
        {
            Logger.error("DBTools", "getConnection Failed!" + e);
        }
        return conn;
    }

    public static void main(String args[])
    {
        System.out.println(2);
    }

    public void cleanup(Connection con)
    {
        try
        {
            if(con != null)
                con.close();
        }
        catch(SQLException e)
        {
            Logger.error("cleanup Connection" + e.toString());
        }
        catch(Exception e)
        {
            Logger.error("cleanup Connection,PreparedStatement" + e.toString());
        }
    }

    private Utilities util;
    static String whereClause = null;

}
