// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBScanner.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import com.vasc.smpp.gateway.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.vasc.smpp.cdr:
//            CDR, CDRServer, CDRTool

public class DBScanner extends Thread
{

    public DBScanner()
    {
        maxcols = 2;
        col = 0;
        dbTools = null;
        dbTools = new DBTools();
    }

    public int readCDRinQueue()
        throws DBException
    {
        int numOfCdrs = 0;
        Collection collection = dbTools.getAllCDRsInQueue(Preference.mobileOperator);
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                cdrId = (BigDecimal)it.next();
                CDR cdr = dbTools.getCDRinQueue(cdrId);
                if(cdr == null)
                {
                    System.out.println("CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    cdr = dbTools.moveCDRFromQueueToLog(cdrId);
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFile(cdr);
                        System.out.print(cdr.getUserId() + "-->" + cdr.getServiceId() + ":" + cdr.getCommandCode() + "\t\t");
                        if(col >= maxcols - 1)
                        {
                            System.out.println();
                            col = 0;
                        } else
                        {
                            col++;
                        }
                    } else
                    {
                        System.out.println("CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
    }

    public int readCDRinQueueEx()
        throws DBException
    {
        int numOfCdrs = 0;
        Collection collection = dbTools.getAllCDRsInQueueEx(Preference.mobileOperator);
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                CDR cdr = (CDR)it.next();
                cdrId = cdr.getId();
                if(cdr == null)
                {
                    System.out.println("CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    cdr = dbTools.moveCDRFromQueueToLog(cdrId);
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFileEx(cdr);
                        int hour = DateProc.getHour(new Timestamp(System.currentTimeMillis()));
                        if("1".equals(Preference.sendSPAM) && (hour < 14 || hour > 19))
                        {
                            String sServiceId = SMSData.formatServiceId(cdr.getServiceId(), 12);
                            if(cdr.getServiceId().endsWith("997") || cdr.getServiceId().endsWith("19001255") || cdr.getServiceId().endsWith("19001522") || cdr.getServiceId().endsWith("19001799"))
                            {
                                dbTools.add2SMSSendQueue(cdr.getUserId(), sServiceId, cdr.getMobileOperator(), "DVKH", Preference.contentSPAM, 0, 0, 0, new BigDecimal(0.0D), 1, 0, 0);
                                System.out.print("\n[SMS] Send SPAM to Customer !!!");
                            } else
                            {
                                dbTools.add2EMSSendQueue(cdr.getUserId(), sServiceId, cdr.getMobileOperator(), "DVKH", 0, Preference.contentSPAM, null, 0, new BigDecimal(0.0D));
                                System.out.print("\n[EMS] Send SPAM to Customer !!!");
                            }
                        }
                        System.out.print(cdr.getUserId() + "-->" + cdr.getServiceId() + ":" + cdr.getCommandCode() + "\t\t");
                        if(col >= maxcols - 1)
                        {
                            System.out.println();
                            col = 0;
                        } else
                        {
                            col++;
                        }
                    } else
                    {
                        System.out.println("CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
    }

    public void run()
    {
        System.out.println("DBScanner started");
          goto _L1
_L3:
        readCDRinQueueEx();
        this;
        sleep(300L);
        continue; /* Loop/switch isn't completed */
        InterruptedException ex;
        ex;
        continue; /* Loop/switch isn't completed */
        DBException ex;
        ex;
        System.out.println("DBScanner::" + ex.getMessage());
        Gateway.rebuildDBConnections(1);
        continue; /* Loop/switch isn't completed */
        Exception ex;
        ex;
        System.out.println("DBScanner::" + ex.getMessage());
_L1:
        if(CDRServer.running) goto _L3; else goto _L2
_L2:
    }

    private int col;
    private DBTools dbTools;
    private int maxcols;
}
