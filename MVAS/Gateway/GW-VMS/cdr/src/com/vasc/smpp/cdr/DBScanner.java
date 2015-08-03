// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBScanner.java

package com.vasc.smpp.cdr;

import com.vasc.smpp.gateway.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package com.vasc.smpp.cdr:
//            CdrFileCopier4vms, Ftp2CdrServer, CDR, Logger, 
//            CDRServer, FtpData, CDRTool

public class DBScanner extends Thread
{

    public DBScanner()
    {
        maxcols = 2;
        col = 0;
        dbTools = null;
        dbTools = new DBTools();
    }

    private static void exit()
    {
        CDRServer.running = false;
        GatewayCDR.closeAllConnectionInPool();
        System.out.println("Stop.");
        System.exit(0);
    }

    public static void main(String args[])
    {
        GatewayCDR gateway = new GatewayCDR();
        Preference.loadProperties("gateway.cfg");
        FtpData.loadProperties("ftp2cdrserver.cfg");
        break MISSING_BLOCK_LABEL_35;
        Exception e;
        e;
        System.out.println("CDRServer: khong tim thay file cau hinh ");
        gateway;
        GatewayCDR.addMoreConnection2Pool(1);
        if(args != null && args.length > 1)
        {
            System.out.println(">>>" + args[0] + "  >>>  " + args[1]);
            pushCDRinQueueEx8x99(args[0].trim(), args[1].trim());
        } else
        {
            System.out.println(">>>Thang 04 !!!");
            pushCDRinQueueEx8x99("060401000000", "060425093500");
        }
        break MISSING_BLOCK_LABEL_163;
        DBException ex;
        ex;
        System.out.println(">>>>Loi: " + ex.toString());
        return;
    }

    public static int pushCDRinQueueEx8x99(String fromNum, String toNum)
        throws DBException
    {
        int numOfCdrs = 0;
        int maxcols = 2;
        int col = 0;
        DBTools dbTools = new DBTools();
        Collection collection = dbTools.getAllCDRsInLogEx8x99(fromNum, toNum);
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            CDR cdr = null;
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                cdr = (CDR)it.next();
                cdrId = cdr.getId();
                if(cdr == null)
                {
                    com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFile8x99ForPushVMS(cdr);
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
                        com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
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
                    Logger _tmp = Logger;
                    com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
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
                        Logger _tmp1 = Logger;
                        com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
    }

    public int readCDRinQueueALL()
        throws DBException
    {
        int numOfCdrs = 0;
        Collection collection = dbTools.getAllCDRsInQueueALL();
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            CDR cdr = null;
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                cdr = (CDR)it.next();
                cdrId = cdr.getId();
                if(cdr == null)
                {
                    Logger _tmp = Logger;
                    com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    cdr = dbTools.moveCDRFromQueueToLogEx(cdr);
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFileEx(cdr);
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
                        Logger _tmp1 = Logger;
                        com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
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
        Collection collection = dbTools.getAllCDRsInQueueEx();
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            CDR cdr = null;
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                cdr = (CDR)it.next();
                cdrId = cdr.getId();
                if(cdr == null)
                {
                    Logger _tmp = Logger;
                    com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    cdr = dbTools.moveCDRFromQueueToLogEx(cdr);
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFileEx(cdr);
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
                        Logger _tmp1 = Logger;
                        com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
    }

    public int readCDRinQueueEx8x99()
        throws DBException
    {
        int numOfCdrs = 0;
        Collection collection = dbTools.getAllCDRsInQueueEx8x99();
        if(collection != null && collection.size() > 0)
        {
            numOfCdrs = collection.size();
            CDR cdr = null;
            BigDecimal cdrId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                cdr = (CDR)it.next();
                cdrId = cdr.getId();
                if(cdr == null)
                {
                    Logger _tmp = Logger;
                    com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                } else
                {
                    cdr = dbTools.moveCDRFromQueueToLogEx(cdr);
                    if(cdr != null)
                    {
                        CDRTool.add2CDRFile8x99(cdr);
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
                        Logger _tmp1 = Logger;
                        com.vasc.smpp.cdr.Logger.info("CDR:", "CDR is null (queueId=" + cdrId + ")");
                    }
                }
            }

        }
        return numOfCdrs;
    }

    public void run()
    {
        int numOfCdrs = 0;
        Logger;
        com.vasc.smpp.cdr.Logger.info("CDRServer:", "Getting data from queue table");
          goto _L1
_L3:
        readCDRinQueueEx();
        if("VMS".equals(Preference.mobileOperator.toUpperCase()))
        {
            CdrFileCopier4vms cdrcopy = new CdrFileCopier4vms();
            Logger;
            com.vasc.smpp.cdr.Logger.info("FTP:", " starting FTP cdr file");
            Ftp2CdrServer ftp = new Ftp2CdrServer();
            ftp.runftp();
        } else
        {
            Logger;
            com.vasc.smpp.cdr.Logger.info("Invalid mobile operator:", Preference.mobileOperator);
            exit();
        }
        sleep10Minutes();
        continue; /* Loop/switch isn't completed */
        InterruptedException ex;
        ex;
        continue; /* Loop/switch isn't completed */
        DBException ex;
        ex;
        Logger;
        com.vasc.smpp.cdr.Logger.info("DBScanner:", ex.getMessage());
        dbTools;
        DBTools.log_alert("Billing system", "-> ERROR: Ket noi Database bi loi: " + ex.getMessage(), 1, 0, "serious", Preference.alert_person);
        break MISSING_BLOCK_LABEL_165;
        Exception e;
        e;
        GatewayCDR.rebuildDBConnections(1);
        continue; /* Loop/switch isn't completed */
        Exception ex;
        ex;
        Logger;
        com.vasc.smpp.cdr.Logger.info("DBScanner:", ex.getMessage());
        dbTools;
        DBTools.log_alert("Billing system", "-> ERROR: Loi dinh dang MOBILE OPERATOR: " + ex.getMessage(), 1, 0, "serious", Preference.alert_person);
        break MISSING_BLOCK_LABEL_240;
        Exception e;
        e;
_L1:
        if(CDRServer.running) goto _L3; else goto _L2
_L2:
    }

    private void sleep10Minutes()
        throws InterruptedException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar _tmp = calendar;
        int currHour = calendar.get(11);
        if((24 - currHour) * 60 < 2 * FtpData.SCHEDULE_TIME)
            Thread.sleep((FtpData.SCHEDULE_TIME + 1) * 60000);
        else
            Thread.sleep(FtpData.SCHEDULE_TIME * 60000);
    }

    private Logger Logger;
    private int col;
    private DBTools dbTools;
    private int maxcols;
}
