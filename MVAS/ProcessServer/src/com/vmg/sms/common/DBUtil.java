// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBUtil.java

package com.vmg.sms.common;

import com.vmg.sms.process.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.vmg.sms.common:
//            Util

public class DBUtil
{

    public DBUtil()
    {
    }

    public static Vector getVtValue()
    {
        return vtValue;
    }

    public static void setVtValue(Vector vtValue)
    {
        vtValue = vtValue;
    }

    public static List getLsValue()
    {
        return lsValue;
    }

    public static void setLsValue(List lsValue)
    {
        lsValue = lsValue;
    }

    public static String getStringValue(Connection cnn, String fldName, String tblName, String con)
        throws Exception
    {
        String tmpVal;
        tmpVal = "";
        String strSQL = "SELECT " + fldName + " FROM " + tblName;
        if(!con.equals(""))
            strSQL = strSQL + " WHERE " + con;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(vtValue != null)
            {
                for(int i = 0; i < vtValue.size(); i++)
                    pstm.setString(i + 1, vtValue.elementAt(i).toString());

                vtValue = null;
            }
            rs = pstm.executeQuery();
            tmpVal = rs.next() ? rs.getString(1) : "";
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return tmpVal;
    }

    public static int getIntValue(Connection cnn, String fldName, String tblName, String con)
        throws Exception
    {
        int tmpVal;
        tmpVal = 0;
        String strSQL = "SELECT " + fldName + " FROM " + tblName;
        if(!con.equals(""))
            strSQL = strSQL + " WHERE " + con;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(vtValue != null)
            {
                for(int i = 0; i < vtValue.size(); i++)
                    pstm.setString(i + 1, vtValue.elementAt(i).toString());

                vtValue = null;
            }
            rs = pstm.executeQuery();
            tmpVal = rs.next() ? rs.getInt(1) : 0;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return tmpVal;
    }

    public static double getDoubleValue(Connection cnn, String fldName, String tblName, String con)
        throws Exception
    {
        double tmpVal;
        tmpVal = 0.0D;
        String strSQL = "SELECT " + fldName + " FROM " + tblName;
        if(!con.equals(""))
            strSQL = strSQL + " WHERE " + con;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(vtValue != null)
            {
                for(int i = 0; i < vtValue.size(); i++)
                    pstm.setString(i + 1, vtValue.elementAt(i).toString());

                vtValue = null;
            }
            rs = pstm.executeQuery();
            tmpVal = rs.next() ? rs.getDouble(1) : 0.0D;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return tmpVal;
    }

    public float getFloatValue(Connection cnn, String fldName, String tblName, String con)
        throws Exception
    {
        float tmpVal;
        tmpVal = 0.0F;
        String strSQL = "SELECT " + fldName + " FROM " + tblName;
        if(!con.equals(""))
            strSQL = strSQL + " WHERE " + con;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(vtValue != null)
            {
                for(int i = 0; i < vtValue.size(); i++)
                    pstm.setString(i + 1, vtValue.elementAt(i).toString());

                vtValue = null;
            }
            rs = pstm.executeQuery();
            tmpVal = rs.next() ? rs.getFloat(1) : 0.0F;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return tmpVal;
    }

    public static String getSequenceValue(Connection cnn, String sequenceName)
        throws Exception
    {
        String strReturn;
        String strSQL = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        strReturn = "";
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            if(!rs.next())
                throw new Exception("Sequence " + sequenceName + " does not exist");
            strReturn = rs.getString(1);
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return strReturn;
    }

    public static Vector getVectorTable(Connection cnn, String strSQL)
        throws Exception
    {
        Vector vt;
        vt = null;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(vtValue != null)
            {
                for(int i = 0; i < vtValue.size(); i++)
                    pstm.setString(i + 1, vtValue.elementAt(i).toString());

                vtValue = null;
            }
            rs = pstm.executeQuery();
            vt = convertToVector(rs);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return vt;
    }

    public static List getListTable(Connection cnn, String strSQL)
        throws Exception
    {
        List lst;
        lst = null;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(lsValue != null)
            {
                for(int i = 0; i < lsValue.size(); i++)
                    pstm.setString(i + 1, lsValue.get(i).toString());

                lsValue = null;
            }
            rs = pstm.executeQuery();
            lst = convertToList(rs);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return lst;
    }

    public static List getListTablePartition(Connection cnn, String strSQL)
        throws Exception
    {
        List lst;
        lst = null;
        try
        {
            pstm = cnn.prepareStatement(strSQL);
            if(lsValue != null)
            {
                for(int i = 0; i < lsValue.size(); i++)
                    pstm.setString(i + 1, lsValue.get(i).toString());

                lsValue = null;
            }
            rs = pstm.executeQuery();
            lst = convertToList(rs);
        }
        catch(SQLException ex)
        {
            lst = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        finally
        {
            closeObject(rs);
            closeObject(pstm);
        }
        return lst;
    }

    public static Vector convertToVector(ResultSet rsData)
        throws Exception
    {
        Vector vctReturn = new Vector();
        int iColumnCount = rsData.getMetaData().getColumnCount();
        Vector vctRow;
        for(; rsData.next(); vctReturn.addElement(vctRow))
        {
            vctRow = new Vector();
            for(int i = 1; i <= iColumnCount; i++)
            {
                String strValue = rsData.getString(i);
                if(strValue == null)
                    strValue = "";
                vctRow.addElement(strValue);
            }

        }

        vctReturn.trimToSize();
        return vctReturn;
    }

    public static List convertToList(ResultSet rsData)
        throws Exception
    {
        List lstReturn = new ArrayList();
        int iColumnCount = rsData.getMetaData().getColumnCount();
        List lstRow;
        for(; rsData.next(); lstReturn.add(lstRow))
        {
            lstRow = new ArrayList();
            for(int i = 1; i <= iColumnCount; i++)
            {
                String strValue = rsData.getString(i);
                if(strValue == null)
                    strValue = "";
                lstRow.add(strValue);
            }

        }

        return lstReturn;
    }

    public static String addCondition(String strField, String strValue)
    {
        String strReturn = "";
        if(!strValue.equals("") && strValue.indexOf("%") == -1)
            strReturn = " AND " + strField + " = '" + strValue + "' ";
        else
        if(!strValue.equals(""))
            strReturn = " AND " + strField + " like '" + strValue + "' ";
        return strReturn;
    }

    public static String addDateCondition(String strField, String strValue, String strCompare)
    {
        String strReturn = "";
        if(!strValue.equals(""))
            strReturn = " AND " + strField + " " + strCompare + " TO_DATE('" + strValue + "','DD/MM/YYYY')";
        return strReturn;
    }

    public static String addDateTextCondition(String strField, String strValue, String strCompare)
    {
        String strReturn = "";
        if(!strValue.equals(""))
            strReturn = " AND to_date(TO_CHAR (" + strField + ", 'dd-MM-yyyy hh24:mi:ss'),'dd-mm-yyyy hh24:mi:ss') " + strCompare + " TO_DATE('" + strValue + "','DD/MM/YYYY')";
        return strReturn;
    }

    public static String addTimeCondition(String strField, String strValue, String strCompare)
    {
        String strReturn = "";
        if(!strValue.equals(""))
            strReturn = " AND " + strField + " " + strCompare + " TO_DATE('" + strValue + "','DD/MM/YYYY hh24:mi:ss')";
        return strReturn;
    }

    public static void closeObject(PreparedStatement obj)
    {
        try
        {
            if(obj != null)
                obj.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closeObject(CallableStatement obj)
    {
        try
        {
            if(obj != null)
                obj.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closeObject(Statement obj)
    {
        try
        {
            if(obj != null)
                obj.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closeObject(ResultSet obj)
    {
        try
        {
            if(obj != null)
                obj.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closeObject(Connection obj)
    {
        try
        {
            if(obj != null && !obj.isClosed())
            {
                if(!obj.getAutoCommit())
                    obj.rollback();
                obj.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int getDayOfWeek(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(7);
    }

    public static int getDayOfWeek()
    {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(7);
    }

    public static int getHourOfDay()
    {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(11);
    }

    public static int getHourOfDay(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(11);
    }

    public static int executeSQL(Connection obj, String sql)
    {
        PreparedStatement statement = null;
        statement = obj.prepareStatement(sql);
        if(statement.executeUpdate() >= 0) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        return 1;
        SQLException e;
        e;
        continue; /* Loop/switch isn't completed */
        e;
        if(true) goto _L1; else goto _L3
_L3:
        local;
        closeObject(statement);
        JVM INSTR ret 4;
    }

    public static int sendMT(MsgObject msgObject)
    {
        Connection connection;
        PreparedStatement statement;
        DBPool dbpool;
        connection = null;
        statement = null;
        String sqlString = null;
        if("".equalsIgnoreCase(msgObject.getUsertext().trim()) || msgObject.getUsertext() == null)
        {
            Util.logger.error("DBUtil@sendMT@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@requestid=" + msgObject.getRequestid().toString() + "@message is null - LOST MESSAGE");
            return 1;
        }
        dbpool = new DBPool();
        Util.logger.info("DBUtil@sendMT@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString());
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
        Util.logger.crisis("DBUtil@sendMT: Error@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString());
          goto _L5
_L4:
        return 1;
        SQLException e;
        e;
        Util.logger.crisis("DBUtil@sendMT: Error:@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString() + "@" + e.toString());
          goto _L5
        e;
        Util.logger.crisis("DBUtil@sendMT: Error:@userid=" + msgObject.getUserid() + "@serviceid=" + msgObject.getServiceid() + "@usertext=" + msgObject.getUsertext() + "@messagetype=" + msgObject.getMsgtype() + "@requestid=" + msgObject.getRequestid().toString() + "@" + e.toString());
          goto _L5
        local;
        dbpool.cleanup(statement);
        dbpool.cleanup(connection);
        JVM INSTR ret 6;
    }

    public static int Alert(String domain, String issue, String level, String alertmsg, String contact)
    {
        Connection connection;
        PreparedStatement stmt;
        DBPool dbpool;
        String newissue;
        String newalert;
        String sSQL;
        connection = null;
        stmt = null;
        dbpool = new DBPool();
        newissue = issue;
        if(issue.length() > 20)
            newissue = issue.substring(0, 19);
        newalert = alertmsg;
        if(newalert.length() > 130)
            newalert = newalert.substring(0, 130);
        sSQL = "insert into msg_alerter( domain, issue, level,alertmsg,contact) values(?,?,?,?,?)";
        if(connection == null)
            connection = dbpool.getConnection("alert");
        stmt = connection.prepareStatement(sSQL);
        stmt.setString(1, domain);
        stmt.setString(2, newissue);
        stmt.setString(3, level);
        stmt.setString(4, newalert);
        stmt.setString(5, contact);
        if(stmt.executeUpdate() != -1) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        return 1;
        Exception ex;
        ex;
        Util.logger.info("DBTools{Alert Error:}" + ex.getMessage());
          goto _L1
        local;
        dbpool.cleanup(connection, stmt);
        JVM INSTR ret 12;
    }

    private static PreparedStatement pstm = null;
    private static ResultSet rs = null;
    private static Vector vtValue = null;
    private static List lsValue = null;

}
