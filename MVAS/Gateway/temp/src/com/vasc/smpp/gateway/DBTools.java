// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBTools.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import com.vasc.smpp.cdr.CDR;
import com.vasc.smpp.cdr.MobileProfile;
import com.vasc.smpp.pdu.DeliveryReceipt;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            SMSData, DBException, Gateway, Preference

public class DBTools
{

    public DBTools()
    {
        util = null;
        util = new Utilities();
    }

    public BigDecimal add2CdrLog(CDR cdr)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        DBException exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO CDR_LOG (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS) VALUES (S_CDR_LOG.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, cdr.getUserId());
        preStmt.setString(2, cdr.getServiceId());
        preStmt.setString(3, cdr.getMobileOperator());
        preStmt.setString(4, cdr.getCommandCode());
        preStmt.setString(5, cdr.getInfo());
        preStmt.setString(6, cdr.getSubmitDate());
        preStmt.setString(7, cdr.getDoneDate());
        preStmt.setInt(8, cdr.getTotalSegments());
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2CdrLog(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_CDR_LOG.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2CdrLog(): Error in S_CDR_LOG.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.add2CdrLog: " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
    }

    public BigDecimal add2CdrQueue(SMSData sms)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        for(int i = 0; i < chargeFreeList.length; i++)
        {
            if(sms.getUserId().endsWith(chargeFreeList[i]))
                return new BigDecimal(0.0D);
        }

        System.out.println("     --> Add to CDR queue ");
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO CDR_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS) VALUES (S_CDR_QUEUE.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, sms.getUserIdEx(0));
        preStmt.setString(2, sms.getServiceIdEx(10));
        preStmt.setString(3, sms.getMobileOperator());
        preStmt.setString(4, sms.getCommandCode());
        String info = sms.getInfoEx(true);
        if(info != null && info.length() > 20)
            info = info.substring(0, 19) + "...";
        preStmt.setString(5, info);
        preStmt.setString(6, sms.getSubmitDate());
        preStmt.setString(7, sms.getDoneDate());
        preStmt.setInt(8, sms.getTotalSegments());
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2CdrQueue(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_CDR_QUEUE.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2CdrQueue(): Error in S_CDR_QUEUE.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2CdrQueue: " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2CdrQueue(String userId, String serviceId, String mobileOperator, String commandCode, String info, String submitDate, String doneDate, 
            int totalSegments)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        System.out.println("     --> Add to CDR queue ");
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO CDR_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS) VALUES (S_CDR_QUEUE.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setString(5, info);
        preStmt.setString(6, submitDate);
        preStmt.setString(7, doneDate);
        preStmt.setInt(8, totalSegments);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2CdrQueue(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_CDR_QUEUE.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2CdrQueue(): Error in S_CDR_QUEUE.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2CdrQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2CdrQueueEx(SMSData sms)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        PreparedStatement stmt;
        ResultSet rs;
        String strSQL;
        for(int i = 0; i < chargeFreeList.length; i++)
        {
            if(sms.getUserId().endsWith(chargeFreeList[i]))
                return new BigDecimal(0.0D);
        }

        System.out.println("     --> Add to CDR queue ");
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        stmt = conn.prepareStatement("Select S_CDR_QUEUE.nextval from dual");
        if(stmt.executeUpdate() >= 1)
            break MISSING_BLOCK_LABEL_187;
        conn.rollback();
        System.out.println("DBTools.add2CdrQueue(): Error in S_CDR_QUEUE.currval !!!");
        Object obj = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_150;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        rs = stmt.getResultSet();
        if(rs.next())
        {
            id = rs.getBigDecimal(1);
            break MISSING_BLOCK_LABEL_293;
        }
        conn.rollback();
        System.out.println("DBTools.add2CdrQueue(): Error in S_CDR_QUEUE.currval !!!");
        Object obj1 = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_256;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO CDR_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        preStmt.setString(2, sms.getUserIdEx(0));
        preStmt.setString(3, sms.getServiceIdEx(10));
        preStmt.setString(4, sms.getMobileOperator());
        preStmt.setString(5, sms.getCommandCode());
        String info = sms.getInfoEx(true);
        if(info != null && info.length() > 20)
            info = info.substring(0, 19) + "...";
        preStmt.setString(6, info);
        preStmt.setString(7, sms.getSubmitDate());
        preStmt.setString(8, sms.getDoneDate());
        preStmt.setInt(9, sms.getTotalSegments());
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2CdrQueue(): Error adding row");
        } else
        {
            conn.commit();
        }
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_541;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_664;
        info;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2CdrQueue: " + e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_751;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_807;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2EMSSendQueue(String userId, String serviceId, String mobileOperator, String commandCode, int contentType, String text, byte bytes[], 
            int messageType, BigDecimal requestId)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO EMS_SEND_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, INFO, RAW_INFO, MESSAGE_TYPE, REQUEST_ID) VALUES (S_EMS_SEND_QUEUE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setInt(5, contentType);
        preStmt.setString(6, text);
        preStmt.setBytes(7, bytes);
        preStmt.setInt(8, messageType);
        preStmt.setBigDecimal(9, requestId);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2EMSSendQueue(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_EMS_SEND_QUEUE.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2EMSSendQueue(): Error in S_EMS_SEND_QUEUE.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        Gateway.dbPool.enqueue(conn);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.toString());
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        Gateway.dbPool.enqueue(conn);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.add2EMSSendQueue:" + e.toString());
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        Gateway.dbPool.enqueue(conn);
        return id;
        Exception exception;
        exception;
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        Gateway.dbPool.enqueue(conn);
        return id;
    }

    public boolean add2MobileProfile(String soTB84, Timestamp lastChargedTime, String shortCode)
        throws DBException
    {
        DBException exception;
        boolean result;
        Connection conn;
        PreparedStatement preStmt;
        String strSQL;
        exception = null;
        result = false;
        conn = null;
        preStmt = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "INSERT INTO MOBILE_PROFILE  (SOTB84, LAST_CHARGED_TIME, LAST_CHARGED_SHORTCODE) VALUES (?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, soTB84);
        preStmt.setTimestamp(2, lastChargedTime);
        preStmt.setString(3, shortCode);
        if(preStmt.executeUpdate() != 1)
            System.out.println("DBTools.add2MobileProfile(): Error adding row");
        else
            result = true;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return result;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return result;
        Exception e;
        e;
        System.out.println("DBTools.add2MobileProfile:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return result;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return result;
    }

    public BigDecimal add2SMSDeliveryReceipt(String userId, String serviceId, String mobileOperator, String info)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_DELIVERY_RECEIPT (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, INFO) VALUES (S_SMS_DELIVERY_RECEIPT.nextval, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, info);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.addToSMSDeliveryReceipt(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_DELIVERY_RECEIPT.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2SMSReceiveQueue(): Error in S_SMS_DELIVERY_RECEIPT.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSDeliveryReceipt:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
    }

    public BigDecimal add2SMSReceiveInvalid(String userId, String serviceId, String mobileOperator, String commandCode, String info)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_RECEIVE_INVALID (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, \"TIMESTAMP\", RESPONDED) VALUES (S_SMS_RECEIVE_INVALID.nextval, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setString(5, info);
        preStmt.setTimestamp(6, time);
        preStmt.setInt(7, NOT_RESPONDED);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2SMSReceiveInvalid(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_RECEIVE_INVALID.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2SMSReceiveInvalid(): Error in S_SMS_RECEIVE_INVALID.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSReceiveInvalid:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2SMSReceiveInvalidEx(String userId, String serviceId, String mobileOperator, String commandCode, String info)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        PreparedStatement stmt;
        ResultSet rs;
        String strSQL;
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        stmt = conn.prepareStatement("Select S_SMS_RECEIVE_INVALID.nextval from dual");
        if(stmt.executeUpdate() >= 1)
            break MISSING_BLOCK_LABEL_136;
        System.out.println("DBTools.add2SMSReceiveInvalid(): Error in S_SMS_RECEIVE_INVALID.currval !!!");
        conn.rollback();
        Object obj = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_97;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        rs = stmt.getResultSet();
        if(rs.next())
        {
            id = rs.getBigDecimal(1);
            break MISSING_BLOCK_LABEL_243;
        }
        conn.rollback();
        System.out.println("DBTools.add2SMSReceiveInvalid(): Error in S_SMS_RECEIVE_INVALID.currval !!!");
        Object obj1 = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_204;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_RECEIVE_INVALID (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, \"TIMESTAMP\", RESPONDED) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        preStmt.setString(2, userId);
        preStmt.setString(3, serviceId);
        preStmt.setString(4, mobileOperator);
        preStmt.setString(5, commandCode);
        preStmt.setString(6, info);
        preStmt.setTimestamp(7, time);
        preStmt.setInt(8, NOT_RESPONDED);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2SMSReceiveInvalid(): Error adding row");
        } else
        {
            conn.commit();
        }
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_409;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_535;
        NOT_RESPONDED;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSReceiveInvalid:" + e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_623;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_681;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2SMSReceiveLog(String userId, String serviceId, String mobileOperator, String commandCode, String info)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_RECEIVE_LOG (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, \"TIMESTAMP\", RESPONDED) VALUES (S_SMS_RECEIVE_LOG.nextval, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setString(5, info);
        preStmt.setTimestamp(6, time);
        preStmt.setInt(7, NOT_RESPONDED);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2SMSReceiveLog(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_RECEIVE_LOG.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2SMSReceiveLog(): Error in S_SMS_RECEIVE_LOG.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSReceiveLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
    }

    public BigDecimal add2SMSReceiveQueue(String userId, String serviceId, String mobileOperator, String commandCode, String info)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_RECEIVE_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, \"TIMESTAMP\", RESPONDED) VALUES (S_SMS_RECEIVE_QUEUE.nextval, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setString(5, info);
        preStmt.setTimestamp(6, time);
        preStmt.setInt(7, NOT_RESPONDED);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2SMSReceiveQueue(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_RECEIVE_QUEUE.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.add2SMSReceiveQueue(): Error in S_SMS_RECEIVE_QUEUE.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSReceiveQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2SMSReceiveQueueEx(String userId, String serviceId, String mobileOperator, String commandCode, String info)
        throws DBException
    {
        DBException exception;
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        PreparedStatement stmt;
        ResultSet rs;
        String strSQL;
        exception = null;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        stmt = conn.prepareStatement("Select S_SMS_RECEIVE_QUEUE.nextval from dual");
        if(stmt.executeUpdate() >= 1)
            break MISSING_BLOCK_LABEL_128;
        conn.rollback();
        System.out.println("DBTools.add2SMSReceiveQueue(): Error adding row");
        Object obj = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_89;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        rs = stmt.getResultSet();
        if(rs.next())
        {
            id = rs.getBigDecimal(1);
            break MISSING_BLOCK_LABEL_235;
        }
        conn.rollback();
        System.out.println("DBTools.add2SMSReceiveQueue(): Error in S_SMS_RECEIVE_QUEUE.currval !!!");
        Object obj1 = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_196;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int NOT_RESPONDED = 0;
        strSQL = "INSERT INTO SMS_RECEIVE_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, \"TIMESTAMP\", RESPONDED) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        preStmt.setString(2, userId);
        preStmt.setString(3, serviceId);
        preStmt.setString(4, mobileOperator);
        preStmt.setString(5, commandCode);
        preStmt.setString(6, info);
        preStmt.setTimestamp(7, time);
        preStmt.setInt(8, NOT_RESPONDED);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.add2SMSReceiveQueue(): Error adding row");
        } else
        {
            conn.commit();
        }
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_401;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        SQLException e;
        e;
        System.out.println("Error executing " + strSQL + " >>> " + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_527;
        NOT_RESPONDED;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSReceiveQueue:" + e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_615;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
        Exception exception1;
        exception1;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_673;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        if(exception != null)
            throw exception;
        else
            return id;
    }

    public BigDecimal add2SMSSendLog(SMSData sms)
    {
        BigDecimal result;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        if(sms == null)
        {
            System.out.println("DBTools.add2SMSSendLog: sms null.");
            return null;
        }
        result = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        strSQL = "INSERT INTO SMS_SEND_LOG (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE,INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID) VALUES (S_SMS_SEND_LOG.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, sms.getUserId());
        preStmt.setString(2, sms.getServiceId());
        preStmt.setString(3, sms.getMobileOperator());
        preStmt.setString(4, sms.getCommandCode());
        preStmt.setString(5, sms.getInfo());
        preStmt.setTimestamp(6, sms.getFirstSendTime());
        preStmt.setTimestamp(7, sms.getLastSendTime());
        preStmt.setInt(8, sms.getNumberOfSend());
        preStmt.setInt(9, sms.getProcessResult());
        preStmt.setInt(10, sms.getMessageType());
        preStmt.setBigDecimal(11, sms.getRequestId());
        preStmt.setInt(12, sms.getTotalSegments());
        preStmt.setInt(13, sms.getSegmentSeqnum());
        preStmt.setInt(14, sms.getMoreMsgsToSend());
        preStmt.setString(15, sms.getMessageId());
        if(preStmt.executeUpdate() != 1)
        {
            System.out.println("DBTools.addToSMSSendLog(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_SEND_LOG.currval from dual");
            if(rs.next())
            {
                result = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.addToSMSSendLog(): Can't get currval from sequence !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSinSendLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
    }

    public BigDecimal add2SMSSendLogEx(SMSData sms)
    {
        BigDecimal result;
        Connection conn;
        PreparedStatement preStmt;
        PreparedStatement stmt;
        ResultSet rs;
        String strSQL;
        if(sms == null)
        {
            System.out.println("DBTools.add2SMSSendLog: sms null.");
            return null;
        }
        result = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        stmt = conn.prepareStatement("Select S_SMS_SEND_LOG.nextval from dual");
        if(stmt.executeUpdate() >= 1)
            break MISSING_BLOCK_LABEL_101;
        System.out.println("DBTools.addToSMSSendLog(): Error adding row");
        Object obj = null;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        rs = stmt.getResultSet();
        if(rs.next())
        {
            result = rs.getBigDecimal(1);
            break MISSING_BLOCK_LABEL_173;
        }
        System.out.println("DBTools.addToSMSSendLog(): Can't get nextval from sequence !!!");
        Object obj1 = null;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        strSQL = "INSERT INTO SMS_SEND_LOG (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE,INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, result);
        preStmt.setString(2, sms.getUserId());
        preStmt.setString(3, sms.getServiceId());
        preStmt.setString(4, sms.getMobileOperator());
        preStmt.setString(5, sms.getCommandCode());
        preStmt.setString(6, sms.getInfo());
        preStmt.setTimestamp(7, sms.getFirstSendTime());
        preStmt.setTimestamp(8, sms.getLastSendTime());
        preStmt.setInt(9, sms.getNumberOfSend());
        preStmt.setInt(10, sms.getProcessResult());
        preStmt.setInt(11, sms.getMessageType());
        preStmt.setBigDecimal(12, sms.getRequestId());
        preStmt.setInt(13, sms.getTotalSegments());
        preStmt.setInt(14, sms.getSegmentSeqnum());
        preStmt.setInt(15, sms.getMoreMsgsToSend());
        preStmt.setString(16, sms.getMessageId());
        if(preStmt.executeUpdate() != 1)
            System.out.println("DBTools.addToSMSSendLog(): Error adding row");
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        Exception e;
        e;
        System.out.println("DBTools.add2SMSinSendLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return result;
    }

    public BigDecimal add2SMSSendQueue(String userId, String serviceId, String mobileOperator, String commandCode, String info, int numberOfSend, int processResult, 
            int messageType, BigDecimal requestId, int totalSegments, int segmentSeqnum, int moreMsgsToSend)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        Statement stmt;
        ResultSet rs;
        String strSQL;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO SMS_SEND_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE,INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID) VALUES (S_SMS_SEND_QUEUE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, userId);
        preStmt.setString(2, serviceId);
        preStmt.setString(3, mobileOperator);
        preStmt.setString(4, commandCode);
        preStmt.setString(5, info);
        preStmt.setTimestamp(6, time);
        preStmt.setTimestamp(7, time);
        preStmt.setInt(8, numberOfSend);
        preStmt.setInt(9, processResult);
        preStmt.setInt(10, messageType);
        preStmt.setBigDecimal(11, requestId);
        preStmt.setInt(12, totalSegments);
        preStmt.setInt(13, segmentSeqnum);
        preStmt.setInt(14, moreMsgsToSend);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.addToSMSSendQueue(): Error adding row");
        } else
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select S_SMS_SEND_QUEUE.currval from dual");
            if(rs.next())
            {
                id = rs.getBigDecimal(1);
                conn.commit();
            } else
            {
                conn.rollback();
                System.out.println("DBTools.addToSMSSendQueue(): Error in S_SMS_SEND_QUEUE.currval !!!");
            }
        }
        conn.setAutoCommit(true);
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.addToSMSSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
    }

    public BigDecimal add2SMSSendQueueEx(String userId, String serviceId, String mobileOperator, String commandCode, String info, int numberOfSend, int processResult, 
            int messageType, BigDecimal requestId, int totalSegments, int segmentSeqnum, int moreMsgsToSend)
    {
        BigDecimal id;
        Connection conn;
        PreparedStatement preStmt;
        PreparedStatement stmt;
        ResultSet rs;
        String strSQL;
        id = null;
        conn = null;
        preStmt = null;
        stmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        conn.setAutoCommit(false);
        stmt = conn.prepareStatement("Select S_SMS_SEND_QUEUE.nextval from dual");
        if(stmt.executeUpdate() >= 1)
            break MISSING_BLOCK_LABEL_125;
        conn.rollback();
        System.out.println("DBTools.addToSMSSendQueue(): Error adding row");
        Object obj = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_94;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        rs = stmt.getResultSet();
        if(rs.next())
        {
            id = rs.getBigDecimal(1);
            break MISSING_BLOCK_LABEL_224;
        }
        conn.rollback();
        System.out.println("DBTools.addToSMSSendQueue(): Error in S_SMS_SEND_QUEUE.currval !!!");
        Object obj1 = null;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_193;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        strSQL = "INSERT INTO SMS_SEND_QUEUE (ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE,INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        preStmt.setString(2, userId);
        preStmt.setString(3, serviceId);
        preStmt.setString(4, mobileOperator);
        preStmt.setString(5, commandCode);
        preStmt.setString(6, info);
        preStmt.setTimestamp(7, time);
        preStmt.setTimestamp(8, time);
        preStmt.setInt(9, numberOfSend);
        preStmt.setInt(10, processResult);
        preStmt.setInt(11, messageType);
        preStmt.setBigDecimal(12, requestId);
        preStmt.setInt(13, totalSegments);
        preStmt.setInt(14, segmentSeqnum);
        preStmt.setInt(15, moreMsgsToSend);
        if(preStmt.executeUpdate() != 1)
        {
            conn.rollback();
            System.out.println("DBTools.addToSMSSendQueue(): Error adding row");
        } else
        {
            conn.commit();
        }
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_464;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_554;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception e;
        e;
        System.out.println("DBTools.addToSMSSendQueue:" + e.getMessage());
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_634;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
        Exception exception;
        exception;
        conn.setAutoCommit(true);
        break MISSING_BLOCK_LABEL_684;
        SQLException ex;
        ex;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, stmt);
        return id;
    }

    public Map getAllCDRsInLog(String mobileOperator, String serviceId, String yymmdd)
    {
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Map counters;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        counters = new HashMap();
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "select count(*), COMMAND_CODE from CDR_LOG WHERE UPPER(MOBILE_OPERATOR) = '" + mobileOperator.toUpperCase() + "' and SERVICE_ID like '%" + serviceId + "' and SUBMIT_DATE like '" + yymmdd + "%' GROUP BY COMMAND_CODE";
        System.out.println("SQL: " + strSQL);
        preStmt = conn.prepareStatement(strSQL);
        String count;
        String code;
        for(rs = preStmt.executeQuery(); rs.next(); counters.put(code, count))
        {
            count = rs.getString(1);
            code = rs.getString(2);
        }

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return counters;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return counters;
        Exception e;
        e;
        System.out.println("DBTools.getAllCDRsInLog(" + mobileOperator + "): " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return counters;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return counters;
    }

    public Collection getAllCDRsInQueue(String mobileOperator)
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID FROM CDR_QUEUE WHERE UPPER(MOBILE_OPERATOR) = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, mobileOperator);
        BigDecimal id;
        for(rs = preStmt.executeQuery(); rs.next(); keys.addElement(id))
            id = rs.getBigDecimal(1);

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllCDRsInQueue(" + mobileOperator + "): " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public Collection getAllCDRsInQueueEx(String mobileOperator)
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS FROM CDR_QUEUE WHERE UPPER(MOBILE_OPERATOR) = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, mobileOperator);
        CDR cdr;
        for(rs = preStmt.executeQuery(); rs.next(); keys.addElement(cdr))
        {
            cdr = new CDR();
            cdr.setId(rs.getBigDecimal(1));
            cdr.setUserId(rs.getString(2));
            cdr.setServiceId(rs.getString(3));
            cdr.setMobileOperator(rs.getString(4));
            cdr.setCommandCode(rs.getString(5));
            cdr.setInfo(rs.getString(6));
            cdr.setSubmitDate(rs.getString(7));
            cdr.setDoneDate(rs.getString(8));
            cdr.setTotalSegments(rs.getInt(9));
        }

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllCDRsInQueue(" + mobileOperator + "): " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public Collection getAllSMSesInDeliveryReceipt()
    {
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID FROM SMS_DELIVERY_RECEIPT ";
        preStmt = conn.prepareStatement(strSQL);
        BigDecimal id;
        for(rs = preStmt.executeQuery(); rs.next(); keys.addElement(id))
            id = rs.getBigDecimal(1);

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllSMSesInDeliveryReceipt:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return keys;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return keys;
    }

    public Collection getAllSMSesInSendLog(String sDate)
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID FROM SMS_SEND_LOG WHERE TO_CHAR(LAST_SEND_TIME, 'dd-mm-yyyy') = '" + sDate + "'";
        preStmt = conn.prepareStatement(strSQL);
        BigDecimal id;
        for(rs = preStmt.executeQuery(); rs.next(); keys.addElement(id))
            id = rs.getBigDecimal(1);

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getSMSInSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public Collection getAllSMSesInSendQueue(String sendMode)
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        SMSData sms = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        if(whereClause == null)
        {
            whereClause = "";
            for(Iterator it = Preference.sourceAddressList.iterator(); it.hasNext();)
            {
                String sAddress = (String)it.next();
                whereClause = whereClause + "SERVICE_ID LIKE '%" + sAddress + "%'";
                if(it.hasNext())
                    whereClause = whereClause + " OR ";
            }

        }
        if("1".equals(sendMode))
            strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_QUEUE WHERE (" + whereClause + ") AND MOD(ID,2) = 1";
        else
        if("2".equals(sendMode))
            strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_QUEUE WHERE (" + whereClause + ") AND MOD(ID,2) = 0";
        else
            strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_QUEUE WHERE " + whereClause;
        preStmt = conn.prepareStatement(strSQL);
        if(preStmt.executeUpdate() > 0)
        {
            SMSData sms;
            for(rs = preStmt.getResultSet(); rs.next(); keys.addElement(sms))
            {
                sms = new SMSData();
                sms.setId(rs.getBigDecimal(1));
                sms.setUserId(rs.getString(2));
                sms.setServiceId(rs.getString(3));
                sms.setMobileOperator(rs.getString(4));
                sms.setCommandCode(rs.getString(5));
                sms.setInfo(rs.getString(6));
                sms.setFirstSendTime(rs.getTimestamp(7));
                sms.setLastSendTime(rs.getTimestamp(8));
                sms.setNumberOfSend(rs.getInt(9));
                sms.setProcessResult(rs.getInt(10));
                sms.setMessageType(rs.getInt(11));
                sms.setRequestId(rs.getBigDecimal(12));
                sms.setTotalSegments(rs.getInt(13));
                sms.setSegmentSeqnum(rs.getInt(14));
                sms.setMoreMsgsToSend(rs.getInt(15));
                sms.setMessageId(rs.getString(16));
            }

        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllSMSesInSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public Collection getAllSMSesInSendQueue()
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        SMSData sms = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        if(whereClause == null)
        {
            whereClause = "";
            for(Iterator it = Preference.sourceAddressList.iterator(); it.hasNext();)
            {
                String sAddress = (String)it.next();
                whereClause = whereClause + "SERVICE_ID LIKE '%" + sAddress + "%'";
                if(it.hasNext())
                    whereClause = whereClause + " OR ";
            }

        }
        strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_QUEUE WHERE " + whereClause;
        preStmt = conn.prepareStatement(strSQL);
        if(preStmt.executeUpdate() > 0)
        {
            SMSData sms;
            for(rs = preStmt.getResultSet(); rs.next(); keys.addElement(sms))
            {
                sms = new SMSData();
                sms.setId(rs.getBigDecimal(1));
                sms.setUserId(rs.getString(2));
                sms.setServiceId(rs.getString(3));
                sms.setMobileOperator(rs.getString(4));
                sms.setCommandCode(rs.getString(5));
                sms.setInfo(rs.getString(6));
                sms.setFirstSendTime(rs.getTimestamp(7));
                sms.setLastSendTime(rs.getTimestamp(8));
                sms.setNumberOfSend(rs.getInt(9));
                sms.setProcessResult(rs.getInt(10));
                sms.setMessageType(rs.getInt(11));
                sms.setRequestId(rs.getBigDecimal(12));
                sms.setTotalSegments(rs.getInt(13));
                sms.setSegmentSeqnum(rs.getInt(14));
                sms.setMoreMsgsToSend(rs.getInt(15));
                sms.setMessageId(rs.getString(16));
            }

        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllSMSesInSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public Collection getAllSMSesInSendQueueOld()
        throws DBException
    {
        DBException exception;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        Vector keys;
        exception = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        keys = new Vector();
        conn = (Connection)Gateway.dbPool.dequeue();
        if(whereClause == null)
        {
            whereClause = "";
            for(Iterator it = Preference.sourceAddressList.iterator(); it.hasNext();)
            {
                String sAddress = (String)it.next();
                whereClause = whereClause + "SERVICE_ID LIKE '%" + sAddress + "%'";
                if(it.hasNext())
                    whereClause = whereClause + " OR ";
            }

        }
        strSQL = "SELECT ID FROM SMS_SEND_QUEUE WHERE " + whereClause;
        preStmt = conn.prepareStatement(strSQL);
        BigDecimal id;
        for(rs = preStmt.executeQuery(); rs.next(); keys.addElement(id))
            id = rs.getBigDecimal(1);

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        if(e.getMessage().startsWith("ORA-03114"))
            exception = new DBException(e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception e;
        e;
        System.out.println("DBTools.getAllSMSesInSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
        Exception exception1;
        exception1;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        if(exception != null)
            throw exception;
        else
            return keys;
    }

    public CDR getCDRinQueue(BigDecimal queueId)
    {
        CDR cdr;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        cdr = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, SUBMIT_DATE, DONE_DATE, TOTAL_SEGMENTS FROM CDR_QUEUE WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, queueId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            cdr = new CDR();
            cdr.setId(queueId);
            cdr.setUserId(rs.getString(1));
            cdr.setServiceId(rs.getString(2));
            cdr.setMobileOperator(rs.getString(3));
            cdr.setCommandCode(rs.getString(4));
            cdr.setInfo(rs.getString(5));
            cdr.setSubmitDate(rs.getString(6));
            cdr.setDoneDate(rs.getString(7));
            cdr.setTotalSegments(rs.getInt(8));
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return cdr;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return cdr;
        Exception e;
        e;
        System.out.println("DBTools.getCDRinQueue(id=" + queueId + "): " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return cdr;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return cdr;
    }

    public DeliveryReceipt getMessageInDeliveryReceipt(BigDecimal id)
    {
        DeliveryReceipt receipt;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        receipt = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, INFO FROM SMS_DELIVERY_RECEIPT WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            receipt = new DeliveryReceipt();
            receipt.setId(id);
            receipt.setUserId(rs.getString(1));
            receipt.setServiceId(rs.getString(2));
            receipt.setMobileOperator(rs.getString(3));
            receipt.setInfo(rs.getString(4));
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return receipt;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return receipt;
        Exception e;
        e;
        System.out.println("DBTools.getMessageInDeliveryReceipt:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return receipt;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return receipt;
    }

    public MobileProfile getMobileProfile(String soTB84)
    {
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        MobileProfile profile;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        profile = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "select LAST_CHARGED_TIME, LAST_CHARGED_SHORTCODE from MOBILE_PROFILE where SOTB84 = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, soTB84);
        for(rs = preStmt.executeQuery(); rs.next(); profile.setSoTB84(soTB84))
        {
            profile = new MobileProfile();
            profile.setLastChargedTime(rs.getTimestamp(1));
            profile.setLastChargedShortCode(rs.getString(2));
        }

        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return profile;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return profile;
        Exception e;
        e;
        System.out.println("DBTools.getMobileProfile(" + soTB84 + ") " + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return profile;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return profile;
    }

    public SMSData getSMSInSendLog(String messageId)
    {
        SMSData sms;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        sms = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND FROM SMS_SEND_LOG WHERE ID=(select max(ID) from SMS_SEND_LOG where MESSAGE_ID=?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, messageId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            sms = new SMSData();
            sms.setId(rs.getBigDecimal(1));
            sms.setUserId(rs.getString(2));
            sms.setServiceId(rs.getString(3));
            sms.setMobileOperator(rs.getString(4));
            sms.setCommandCode(rs.getString(5));
            sms.setInfo(rs.getString(6));
            sms.setFirstSendTime(rs.getTimestamp(7));
            sms.setLastSendTime(rs.getTimestamp(8));
            sms.setNumberOfSend(rs.getInt(9));
            sms.setProcessResult(rs.getInt(10));
            sms.setMessageType(rs.getInt(11));
            sms.setRequestId(rs.getBigDecimal(12));
            sms.setTotalSegments(rs.getInt(13));
            sms.setSegmentSeqnum(rs.getInt(14));
            sms.setMoreMsgsToSend(rs.getInt(15));
            sms.setMessageId(messageId);
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception e;
        e;
        System.out.println("DBTools.getSMSinSendLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
    }

    public SMSData getSMSInSendLog(BigDecimal logId)
    {
        SMSData sms;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        sms = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_LOG WHERE ID=?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, logId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            sms = new SMSData();
            sms.setId(logId);
            sms.setUserId(rs.getString(1));
            sms.setServiceId(rs.getString(2));
            sms.setMobileOperator(rs.getString(3));
            sms.setCommandCode(rs.getString(4));
            sms.setInfo(rs.getString(5));
            sms.setFirstSendTime(rs.getTimestamp(6));
            sms.setLastSendTime(rs.getTimestamp(7));
            sms.setNumberOfSend(rs.getInt(8));
            sms.setProcessResult(rs.getInt(9));
            sms.setMessageType(rs.getInt(10));
            sms.setRequestId(rs.getBigDecimal(11));
            sms.setTotalSegments(rs.getInt(12));
            sms.setSegmentSeqnum(rs.getInt(13));
            sms.setMoreMsgsToSend(rs.getInt(14));
            sms.setMessageId(rs.getString(15));
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception e;
        e;
        System.out.println("DBTools.getSMSinSendLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
    }

    public SMSData getSMSInSendQueue(String messageId)
    {
        SMSData sms;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        sms = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND FROM SMS_SEND_LOG WHERE ID=(select max(ID) from SMS_SEND_QUEUE where MESSAGE_ID=?)";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setString(1, messageId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            sms = new SMSData();
            sms.setId(rs.getBigDecimal(1));
            sms.setUserId(rs.getString(2));
            sms.setServiceId(rs.getString(3));
            sms.setMobileOperator(rs.getString(4));
            sms.setCommandCode(rs.getString(5));
            sms.setInfo(rs.getString(6));
            sms.setFirstSendTime(rs.getTimestamp(7));
            sms.setLastSendTime(rs.getTimestamp(8));
            sms.setNumberOfSend(rs.getInt(9));
            sms.setProcessResult(rs.getInt(10));
            sms.setMessageType(rs.getInt(11));
            sms.setRequestId(rs.getBigDecimal(12));
            sms.setTotalSegments(rs.getInt(13));
            sms.setSegmentSeqnum(rs.getInt(14));
            sms.setMoreMsgsToSend(rs.getInt(15));
            sms.setMessageId(messageId);
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception e;
        e;
        System.out.println("DBTools.getSMSInSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
    }

    public SMSData getSMSinSendLog(BigDecimal logId)
    {
        SMSData sms;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        sms = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_LOG WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, logId);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            sms = new SMSData();
            sms.setId(logId);
            sms.setUserId(rs.getString(1));
            sms.setServiceId(rs.getString(2));
            sms.setMobileOperator(rs.getString(3));
            sms.setCommandCode(rs.getString(4));
            sms.setInfo(rs.getString(5));
            sms.setFirstSendTime(rs.getTimestamp(6));
            sms.setLastSendTime(rs.getTimestamp(7));
            sms.setNumberOfSend(rs.getInt(8));
            sms.setProcessResult(rs.getInt(9));
            sms.setMessageType(rs.getInt(10));
            sms.setRequestId(rs.getBigDecimal(11));
            sms.setTotalSegments(rs.getInt(12));
            sms.setSegmentSeqnum(rs.getInt(13));
            sms.setMoreMsgsToSend(rs.getInt(14));
            sms.setMessageId(rs.getString(15));
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception e;
        e;
        System.out.println("DBTools.getSMSinSendLog:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
    }

    public SMSData getSMSinSendQueue(BigDecimal id)
    {
        SMSData sms;
        Connection conn;
        PreparedStatement preStmt;
        ResultSet rs;
        String strSQL;
        sms = null;
        conn = null;
        preStmt = null;
        rs = null;
        strSQL = null;
        conn = (Connection)Gateway.dbPool.dequeue();
        strSQL = "SELECT USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, FIRST_SEND_TIME, LAST_SEND_TIME, NUMBER_OF_SEND, PROCESS_RESULT, MESSAGE_TYPE, REQUEST_ID, TOTAL_SEGMENTS, SEGMENT_SEQNUM, MORE_MSGS_TO_SEND, MESSAGE_ID FROM SMS_SEND_QUEUE WHERE ID = ?";
        preStmt = conn.prepareStatement(strSQL);
        preStmt.setBigDecimal(1, id);
        rs = preStmt.executeQuery();
        if(rs.next())
        {
            sms = new SMSData();
            sms.setId(id);
            sms.setUserId(rs.getString(1));
            sms.setServiceId(rs.getString(2));
            sms.setMobileOperator(rs.getString(3));
            sms.setCommandCode(rs.getString(4));
            sms.setInfo(rs.getString(5));
            sms.setFirstSendTime(rs.getTimestamp(6));
            sms.setLastSendTime(rs.getTimestamp(7));
            sms.setNumberOfSend(rs.getInt(8));
            sms.setProcessResult(rs.getInt(9));
            sms.setMessageType(rs.getInt(10));
            sms.setRequestId(rs.getBigDecimal(11));
            sms.setTotalSegments(rs.getInt(12));
            sms.setSegmentSeqnum(rs.getInt(13));
            sms.setMoreMsgsToSend(rs.getInt(14));
            sms.setMessageId(rs.getString(15));
        }
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        SQLException e;
        e;
        System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception e;
        e;
        System.out.println("DBTools.getSMSinSendQueue:" + e.getMessage());
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
        Exception exception;
        exception;
        Gateway.dbPool.enqueue(conn);
        util.cleanup(rs);
        util.cleanup(preStmt, null);
        return sms;
    }

    public static int logMC(String serviceID, String commandCode, String errMsg, int errType, String contactName, String contactPhone)
        throws DBException
    {
        Connection connection;
        PreparedStatement stmt;
        Utilities util;
        String sSQL;
        connection = null;
        stmt = null;
        util = new Utilities();
        sSQL = "insert into MC_LOG(LOG_ID,SERVICE_NUMBER,COMMAND_CODE,ERROR_MSG,STATUS,ERROR_TYPE,CONTACT_PERSON,CONTACT_PHONE) values(S_MC_LOG.nextval,?,?,?,?,?,?,?)";
        byte byte0;
        connection = util.getDBConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:oci8:@mcdb", "season", "seasonstart2004");
        stmt = connection.prepareStatement(sSQL);
        stmt.setString(1, serviceID);
        stmt.setString(2, commandCode);
        stmt.setString(3, errMsg);
        stmt.setInt(4, 0);
        stmt.setInt(5, errType);
        stmt.setString(6, contactName);
        stmt.setString(7, contactPhone);
        if(stmt.executeUpdate() != -1)
            break MISSING_BLOCK_LABEL_137;
        byte0 = -1;
        return byte0;
        int i = 1;
        return i;
        Exception ex;
        ex;
        System.out.println("(" + commandCode + ") Log error:" + ex.toString());
        System.out.println(">>" + sSQL);
        if(ex.getMessage().startsWith("ORA-03114"))
            throw new DBException(ex.getMessage());
        i = -1;
        return i;
        local;
        util.cleanup(connection, stmt);
        JVM INSTR ret 13;
    }

    public static void main(String args[])
    {
        DBTools dbTools;
        Gateway gateway;
        dbTools = new DBTools();
        gateway = new Gateway();
        Preference.loadProperties("gateway.cfg");
        break MISSING_BLOCK_LABEL_40;
        IOException e;
        e;
        System.out.println("Gateway.main(): khong tim thay file cau hinh ");
        gateway;
        Gateway.addMoreConnection2Pool(1);
        BigDecimal id = dbTools.add2CdrQueue("0904060008", "84996", "VMS", "DA", "DA 123", "0402011040", "0402011040", 1);
        break MISSING_BLOCK_LABEL_86;
        DBException ex;
        ex;
        ex.printStackTrace();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        gateway;
        Gateway.closeAllConnectionInPool();
        return;
    }

    public CDR moveCDRFromQueueToLog(BigDecimal queueId)
    {
        CDR cdr = getCDRinQueue(queueId);
        if(cdr != null)
        {
            BigDecimal logId = add2CdrLog(cdr);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromCdrQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                cdr.setId(logId);
                return cdr;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLog(BigDecimal queueId, int processResult, String messageId)
    {
        SMSData sms = getSMSinSendQueue(queueId);
        if(sms != null)
        {
            sms.setProcessResult(processResult);
            sms.setMessageId(messageId);
            BigDecimal logId = add2SMSSendLog(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLog(BigDecimal queueId, int processResult)
    {
        SMSData sms = getSMSinSendQueue(queueId);
        if(sms != null)
        {
            sms.setProcessResult(processResult);
            BigDecimal logId = add2SMSSendLog(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLog(BigDecimal queueId)
    {
        SMSData sms = getSMSinSendQueue(queueId);
        if(sms != null)
        {
            BigDecimal logId = add2SMSSendLog(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLogEx(SMSData sms, int processResult, String messageId)
    {
        BigDecimal queueId = sms.getId();
        if(sms != null)
        {
            sms.setProcessResult(processResult);
            sms.setMessageId(messageId);
            BigDecimal logId = add2SMSSendLogEx(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLogEx(SMSData sms, int processResult)
    {
        BigDecimal queueId = sms.getId();
        if(sms != null)
        {
            sms.setProcessResult(processResult);
            BigDecimal logId = add2SMSSendLogEx(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public SMSData moveSMSFromSendQueueToSendLogEx(SMSData sms)
    {
        BigDecimal queueId = sms.getId();
        if(sms != null)
        {
            BigDecimal logId = add2SMSSendLogEx(sms);
            if(logId != null)
            {
                int nloop = 0;
                while(!removeFromSMSSendQueue(queueId)) 
                    if(++nloop >= 3)
                        break;
                sms.setId(logId);
                return sms;
            }
        }
        return null;
    }

    public boolean removeFromCdrQueue(BigDecimal queueId)
    {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        boolean result = false;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "DELETE FROM CDR_QUEUE WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setBigDecimal(1, queueId);
            if(preStmt.executeUpdate() < 1)
                System.out.println("Error deleting row");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.removeFromCdrQueue(id=" + queueId + "): " + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public void removeFromDeliveryReceipt(BigDecimal id)
    {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "DELETE FROM SMS_DELIVERY_RECEIPT WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setBigDecimal(1, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("Error deleting row");
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.removeFromDeliveryReceipt: " + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
        }
    }

    public boolean removeFromSMSSendQueue(BigDecimal id)
    {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        boolean result = false;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "DELETE FROM SMS_SEND_QUEUE WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setBigDecimal(1, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("Error deleting row");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.removeFromSMSSendQueue:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateLastSendTime(BigDecimal id, Timestamp currTime, int numOfSend)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE SMS_SEND_QUEUE SET LAST_SEND_TIME = ?, NUMBER_OF_SEND = ? WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setTimestamp(1, currTime);
            preStmt.setInt(2, numOfSend);
            preStmt.setBigDecimal(3, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateLastSendTime:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateMobileOperator(BigDecimal id, String mobileOperator)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE SMS_SEND_QUEUE SET MOBILE_OPERATOR = ? WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setString(1, mobileOperator);
            preStmt.setBigDecimal(2, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("DBTools.updateMobileOperator(id=" + id + ")::Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateMobileOperator:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateMobileProfile(MobileProfile profile)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE MOBILE_PROFILE SET LAST_CHARGED_TIME = ?, LAST_CHARGED_SHORTCODE = ? WHERE SOTB84 = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setTimestamp(1, profile.getLastChargedTime());
            preStmt.setString(2, profile.getLastChargedShortCode());
            preStmt.setString(3, profile.getSoTB84());
            if(preStmt.executeUpdate() < 1)
                System.out.println("DBTools.updateMobileProfile(" + profile.getSoTB84() + ") Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateMobileProfile:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateProcessResult(BigDecimal id, int processResult, String messageId)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE SMS_SEND_QUEUE SET PROCESS_RESULT = ?, MESSAGE_ID = ? WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, processResult);
            preStmt.setString(2, messageId);
            preStmt.setBigDecimal(3, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("DBTools.updateProcessResult(queueId=" + id + ")::Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateProcessResult:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateProcessResult(BigDecimal id, int processResult)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE SMS_SEND_LOG SET PROCESS_RESULT = ? WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, processResult);
            preStmt.setBigDecimal(2, id);
            if(preStmt.executeUpdate() < 1)
                System.out.println("DBTools.updateProcessResult(logId=" + id + ")::Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateProcessResult:" + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    public boolean updateResponded(BigDecimal requestId, int responded, String receiveTable)
    {
        boolean result = false;
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        try
        {
            conn = (Connection)Gateway.dbPool.dequeue();
            strSQL = "UPDATE " + receiveTable + " SET RESPONDED = ? WHERE ID = ?";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setInt(1, responded);
            preStmt.setBigDecimal(2, requestId);
            if(preStmt.executeUpdate() < 1)
                System.out.println("Row does not exist");
            else
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error executing SQL " + strSQL + ">>>" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("DBTools.updateResponded: " + e.getMessage());
        }
        finally
        {
            Gateway.dbPool.enqueue(conn);
            util.cleanup(preStmt, null);
            return result;
        }
        do
            ;
        while(true);
    }

    private String chargeFreeList[] = {
        "985956668", "912099902", "912816396", "989068604", "989152696"
    };
    private Utilities util;
    static String whereClause = null;

}
