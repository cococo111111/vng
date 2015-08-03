// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadMO.java

package com.vmg.sms.process;

import com.vmg.sms.common.DBUtil;
import com.vmg.sms.common.Util;
import java.math.BigDecimal;
import java.sql.*;

// Referenced classes of package com.vmg.sms.process:
//            DBPool, Logger, MsgObject, MsgQueue, 
//            ConsoleSRV

public class LoadMO extends Thread
{

    public LoadMO(MsgQueue queue, int processnum, int processindex)
    {
        this.queue = null;
        this.processnum = 1;
        this.processindex = 1;
        dbpool = new DBPool();
        this.queue = queue;
        this.processnum = processnum;
        this.processindex = processindex;
    }

    public void run()
    {
        String SQL_LOAD;
        MsgObject msgObject = null;
        String serviceId = "";
        String userId = "";
        String info = "";
        String operator = "";
        BigDecimal requestId = new BigDecimal(-1D);
        int moid = -1;
        SQL_LOAD = "Select  ID, SERVICE_ID,USER_ID, INFO, RECEIVE_DATE,MOBILE_OPERATOR,  REQUEST_ID from sms_receive_queue where (mod(id," + processnum + ")=" + processindex + ")";
        Util.logger.info("LoadMO - Start");
        Util.logger.info("LoadMO - SQL:" + SQL_LOAD);
          goto _L1
_L3:
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try
        {
            if(connection == null)
                connection = dbpool.getConnectionGateway();
            stmt = connection.prepareStatement(SQL_LOAD, 1004, 1008);
            if(stmt.execute())
            {
                for(rs = stmt.getResultSet(); rs.next();)
                {
                    String serviceId = rs.getString("SERVICE_ID");
                    String userId = rs.getString("USER_ID");
                    String info = rs.getString("INFO").toUpperCase();
                    java.sql.Timestamp tTime = rs.getTimestamp("RECEIVE_DATE");
                    String operator = rs.getString("MOBILE_OPERATOR");
                    BigDecimal requestId = rs.getBigDecimal("REQUEST_ID");
                    int moid = rs.getInt("ID");
                    MsgObject msgObject = new MsgObject(moid, serviceId, userId, "INV", info, requestId, tTime, operator, 0, 0);
                    Util.logger.info("{LoadMO}-add2queue:Q" + serviceId + "[" + queue.getSize() + "]{userid=" + userId + "}{info=" + info + "}{requestid=" + requestId.toString() + "}{moid=" + moid + "}");
                    queue.add(msgObject);
                    try
                    {
                        rs.deleteRow();
                    }
                    catch(SQLException ex)
                    {
                        Util.logger.error("{Load MO}{Ex:" + ex.toString());
                        Util.logger.info("{LoadMO}{add2queue:Q" + serviceId + "[" + queue.getSize() + "]{userid=" + userId + "}{info=" + info + "}{requestid=" + requestId.toString() + "}{moid=" + moid + "}@SQLException:" + ex.toString() + "}");
                        queue.remove();
                    }
                    catch(Exception ex1)
                    {
                        Util.logger.error("{Load MO}{Ex1:" + ex1.toString());
                        Util.logger.info("{LoadMO}{add2queue:Q" + serviceId + "[" + queue.getSize() + "]{userid=" + userId + "}{info=" + info + "}{requestid=" + requestId.toString() + "}{moid=" + moid + "}@SQLException:" + ex1.toString() + "}");
                        queue.remove();
                    }
                }

                sleep(TIME_DELAY_LOAD_MO);
            }
        }
        catch(SQLException ex3)
        {
            Util.logger.error("Load MO. SQLException:" + ex3.toString());
            DBUtil.Alert("Process.LoadMO", "LoadMO.SQLException", "major", "LoadMO.SQLException:" + ex3.toString(), "processAdmin");
        }
        catch(Exception ex2)
        {
            Util.logger.error("Load MO. Exception:" + ex2.toString());
            DBUtil.Alert("Process.LoadMO", "LoadMO.Exception", "major", "LoadMO.Exception:" + ex2.toString(), "processAdmin");
        }
        finally
        {
            dbpool.cleanup(rs, stmt);
            dbpool.cleanup(connection);
        }
_L1:
        if(ConsoleSRV.getData) goto _L3; else goto _L2
_L2:
    }

    MsgQueue queue;
    int processnum;
    int processindex;
    static int TIME_DELAY_LOAD_MO = 100;
    DBPool dbpool;
    public static String mobileOperators[] = {
        "GPC", "VMS", "VIETEL", "EVN", "SFONE", "HTC", "CPHONE"
    };

}
