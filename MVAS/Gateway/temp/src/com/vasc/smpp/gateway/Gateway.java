// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Gateway.java

package com.vasc.smpp.gateway;

import com.vasc.common.StringTool;
import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.smpp.Receiver;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.pdu.BindReceiver;
import org.smpp.pdu.BindRequest;
import org.smpp.pdu.BindResponse;
import org.smpp.pdu.BindTransciever;
import org.smpp.pdu.BindTransmitter;
import org.smpp.pdu.UnbindResp;

// Referenced classes of package com.vasc.smpp.gateway:
//            PDUBuilder, SMSCSender, ResponseProcessor, ReportProcessor, 
//            CheckWait4ReportTimeout, RequestProcessor, SMSCReceiver, EnquireLinkThread, 
//            DBTools, PDUEventListener, Wait4Report, Preference, 
//            SMSCTools, MobileBuffer, MobileBufferInfo

public class Gateway extends Thread
{

    public Gateway()
    {
        requestQueue = null;
        responseQueue = null;
        deliveryQueue = null;
        toSMSC = null;
        pduListener = null;
        requestQueue = new Queue();
        responseQueue = new Queue();
        deliveryQueue = new Queue();
        liveThreads = new Vector();
        wait4ReportTable = new HashMap();
        toSMSC = new Queue();
        dbPool = new Queue();
        util = new Utilities();
    }

    static void addLiveThread(Thread thread)
    {
        synchronized(liveThreads)
        {
            liveThreads.add(thread);
        }
    }

    public static void addMoreConnection2Pool(int number)
    {
        int i;
        util;
        Utilities.log("Connecting to database...");
        i = 0;
          goto _L1
_L3:
        Connection conn = null;
        conn = util.getDBConnection(Preference.db_DriverClassName, Preference.db_URL, Preference.db_user, Preference.db_password);
        break MISSING_BLOCK_LABEL_86;
        SQLException ex;
        ex;
        util;
        Utilities.log("Error: " + ex.getMessage());
        util;
        Utilities.log("Khong noi dc voi database roi, xem lai di.");
        System.exit(1);
        if(conn != null)
            dbPool.enqueue(conn);
        i++;
_L1:
        if(i < number) goto _L3; else goto _L2
_L2:
    }

    public synchronized void bind()
    {
        if(bound)
        {
            Utilities _tmp = util;
            Utilities.log("Already bound, unbind first.");
            return;
        }
        bound_or_unbound_time = System.currentTimeMillis();
        if(Preference.asynchronous)
        {
            bindASync();
        } else
        {
            if(Preference.bindMode.compareToIgnoreCase("t") == 0)
                bindSyncTransmitter();
            else
            if(Preference.bindMode.compareToIgnoreCase("r") == 0)
                bindSyncReceiver();
            else
            if(Preference.bindMode.compareToIgnoreCase("tr") == 0)
            {
                bindSyncTransmitter();
                bindSyncReceiver();
            } else
            {
                Utilities _tmp1 = util;
                Utilities.log("Invalid bind mode (" + Preference.bindMode + ") expected t, r or tr. Operation canceled.");
                return;
            }
        }
        bound_or_unbound_time = System.currentTimeMillis();
    }

    public synchronized void bindASync()
    {
        BindRequest request;
        DBTools dbTool;
        request = null;
        BindResponse response = null;
        org.smpp.Connection connection = null;
        dbTool = new DBTools();
          goto _L1
_L3:
        System.out.println("Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
        org.smpp.Connection connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
        connection.setReceiveTimeout(2000L);
        session = new Session(connection);
        if(Preference.bindMode.compareToIgnoreCase("t") == 0)
            request = new BindTransmitter();
        else
        if(Preference.bindMode.compareToIgnoreCase("r") == 0)
            request = new BindReceiver();
        else
        if(Preference.bindMode.compareToIgnoreCase("tr") == 0)
        {
            request = new BindTransciever();
        } else
        {
            util;
            Utilities.log("Invalid bind mode (" + Preference.bindMode + ") expected t, r or tr. Operation canceled.");
            return;
        }
        request.setSystemId(Preference.systemId);
        request.setPassword(Preference.password);
        request.setSystemType(Preference.systemType);
        request.setInterfaceVersion((byte)52);
        request.setAddressRange(Preference.addressRange);
        if(pduListener == null)
            pduListener = new PDUEventListener(requestQueue, responseQueue, deliveryQueue, toSMSC);
        util;
        Utilities.log("Bind request " + request.debugString());
        BindResponse response = session.bind(request, pduListener);
        util;
        Utilities.log("Bind response " + response.debugString());
        if(response.getCommandStatus() == 0)
        {
            bound = true;
            util;
            Utilities.log("Succesfully Bound to SMSC in " + Preference.bindMode + (Preference.asynchronous ? "-async" : "-sync") + " mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
            break MISSING_BLOCK_LABEL_489;
        }
        SMSCTools.printCommandStatus(response.getCommandStatus());
        dbTool;
        DBTools.logMC(Preference.sourceAddressList.toString(), "Gateway", "<-" + Preference.mobileOperator + "-> ERROR: " + response.getCommandStatus(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_488;
        InterruptedException ie;
        ie;
        continue; /* Loop/switch isn't completed */
        Exception e;
        e;
        util;
        Utilities.log("Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
        dbTool;
        DBTools.logMC(Preference.sourceAddressList.toString(), "Gateway", "<-" + Preference.mobileOperator + "-> Bind operation FAILT", 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_594;
        Exception ie;
        ie;
_L1:
        if(!bound) goto _L3; else goto _L2
_L2:
    }

    public synchronized void bindSyncReceiver()
    {
        BindRequest request = null;
        BindResponse response = null;
        org.smpp.Connection connection = null;
          goto _L1
_L3:
        try
        {
            System.out.println("Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
            org.smpp.Connection connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
            connection.setReceiveTimeout(2000L);
            sessionr = new Session(connection);
            BindRequest request = new BindReceiver();
            request.setSystemId(Preference.systemId);
            request.setPassword(Preference.password);
            request.setSystemType(Preference.systemType);
            request.setInterfaceVersion((byte)52);
            request.setAddressRange(Preference.addressRange);
            util;
            Utilities.log("Bind request " + request.debugString());
            BindResponse response = sessionr.bind(request);
            util;
            Utilities.log("Bind response " + response.debugString());
            if(response.getCommandStatus() == 0)
            {
                boundr = true;
                util;
                Utilities.log("Succesfully Bound to SMSC in r-sync mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
            } else
            {
                SMSCTools.printCommandStatus(response.getCommandStatus());
            }
            continue; /* Loop/switch isn't completed */
        }
        catch(Exception e)
        {
            util;
            Utilities.log("Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
        }
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_308;
        InterruptedException ie;
        ie;
_L1:
        if(!boundr) goto _L3; else goto _L2
_L2:
    }

    public synchronized void bindSyncTransmitter()
    {
        BindRequest request = null;
        BindResponse response = null;
        TCPIPConnection connection = null;
          goto _L1
_L3:
        try
        {
            System.out.println("Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
            TCPIPConnection connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
            connection.setReceiveTimeout(2000L);
            session = new Session(connection);
            BindRequest request = new BindTransmitter();
            request.setSystemId(Preference.systemId);
            request.setPassword(Preference.password);
            request.setSystemType(Preference.systemType);
            request.setInterfaceVersion((byte)52);
            request.setAddressRange(Preference.addressRange);
            util;
            Utilities.log("Bind request " + request.debugString());
            BindResponse response = session.bind(request);
            util;
            Utilities.log("Bind response " + response.debugString());
            if(response.getCommandStatus() == 0)
            {
                bound = true;
                util;
                Utilities.log("Succesfully Bound to SMSC in t-sync mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
            } else
            {
                SMSCTools.printCommandStatus(response.getCommandStatus());
            }
            continue; /* Loop/switch isn't completed */
        }
        catch(Exception e)
        {
            util;
            Utilities.log("Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
        }
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_308;
        InterruptedException ie;
        ie;
_L1:
        if(!bound) goto _L3; else goto _L2
_L2:
    }

    public static void closeAllConnectionInPool()
    {
        Utilities _tmp = util;
        Utilities.log("Closing database connection...");
        int size = dbPool.size();
        for(int i = 0; i < size; i++)
        {
            Connection conn = (Connection)dbPool.dequeue();
            try
            {
                if(conn != null)
                    conn.close();
            }
            catch(SQLException ex) { }
        }

    }

    public static void exit()
    {
        int nLoop;
        running = false;
        if(bound || boundr)
            unbind();
        closeAllConnectionInPool();
        if(Preference.reportRequired)
            saveWait4ReportTable();
        System.out.print("Waiting all threads to die");
        nLoop = 0;
          goto _L1
_L3:
        System.out.print(".");
        Thread.sleep(500L);
        nLoop++;
        continue; /* Loop/switch isn't completed */
        InterruptedException ex;
        ex;
_L1:
        if(liveThreads.size() > 0 && nLoop <= 5) goto _L3; else goto _L2
_L2:
        System.out.println("\r\nGateway stopped.");
        System.exit(0);
        return;
    }

    public Queue getDeliveryQueue()
    {
        return deliveryQueue;
    }

    public PDUEventListener getPDUEventListener()
    {
        return pduListener;
    }

    private String getParam(String prompt, String defaultValue)
    {
        String value;
        value = "";
        String promptFull = prompt;
        promptFull = promptFull + (defaultValue != null ? " [" + defaultValue + "] " : "");
        System.out.print(promptFull);
        value = keyboard.readLine();
        break MISSING_BLOCK_LABEL_110;
        IOException e;
        e;
        System.out.println("Got exception getting a param. " + e);
        if(value.compareTo("") == 0)
            return defaultValue;
        else
            return value;
    }

    public Queue getRequestQueue()
    {
        return requestQueue;
    }

    public Queue getResponseQueue()
    {
        return responseQueue;
    }

    public Session getSession()
    {
        return session;
    }

    public Queue getToSMSCQueue()
    {
        return toSMSC;
    }

    public Map getWait4ReportTable()
    {
        return wait4ReportTable;
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static void loadWait4ReportTable()
    {
        Map map;
        map = new HashMap();
        System.out.print("Loading wait4report_Table...");
        BufferedReader fin = new BufferedReader(new FileReader("wait4report.dat"));
        PrintWriter fTimeout = new PrintWriter(new FileOutputStream("wait4report_timeout.dat", true));
        String line = null;
        int nTimeout = 0;
        while((line = fin.readLine()) != null) 
        {
            Collection coll = StringTool.parseString(line, "\t");
            String s[] = new String[coll.size()];
            coll.toArray(s);
            String msgId = s[0];
            String logId = s[1];
            long ltime = Long.parseLong(s[2]);
            String stime = s[3];
            Wait4Report wait4report = new Wait4Report(new BigDecimal(logId), new Timestamp(ltime));
            if(!wait4report.isTimeout())
            {
                map.put(msgId, wait4report);
            } else
            {
                fTimeout.print(msgId + "\t");
                fTimeout.print(logId + "\t");
                fTimeout.print(ltime + "\t");
                fTimeout.println(stime);
                nTimeout++;
            }
        }
        fin.close();
        fTimeout.flush();
        fTimeout.close();
        wait4ReportTable.putAll(map);
        System.out.println(" [" + map.size() + " entries loaded, " + nTimeout + " entries timeout]!");
        break MISSING_BLOCK_LABEL_371;
        IOException ex;
        ex;
        System.out.println("Gateway.loadWait4ReportTable: " + ex.getMessage());
        return;
    }

    public static void main(String args[])
    {
        Gateway gateway = new Gateway();
        Preference.loadProperties(propsFilePath);
        break MISSING_BLOCK_LABEL_50;
        IOException e;
        e;
        System.out.println("Gateway.main(): khong tim thay file cau hinh " + propsFilePath);
        if(Preference.reportRequired)
        {
            gateway;
            loadWait4ReportTable();
        }
        gateway;
        addMoreConnection2Pool(Preference.db_MaxConnections);
        gateway.bind();
        gateway.start();
        return;
    }

    public static void rebuildDBConnections(int number)
    {
        boolean ok;
        Connection conn;
        closeAllConnectionInPool();
        ok = false;
        conn = null;
          goto _L1
_L3:
        conn = util.getDBConnection(Preference.db_DriverClassName, Preference.db_URL, Preference.db_user, Preference.db_password);
        ok = true;
        continue; /* Loop/switch isn't completed */
        SQLException e;
        e;
        util;
        Utilities.log("Get DB Connection FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_90;
        InterruptedException ie;
        ie;
_L1:
        if(!ok) goto _L3; else goto _L2
_L2:
        if(conn != null)
            dbPool.enqueue(conn);
        addMoreConnection2Pool(number - 1);
        return;
    }

    static boolean removeThread(Thread threadToDie)
    {
        Vector vector = liveThreads;
        JVM INSTR monitorenter ;
        Iterator it = liveThreads.iterator();
          goto _L1
_L3:
        Thread currThread = (Thread)it.next();
        if(!currThread.equals(threadToDie))
            continue; /* Loop/switch isn't completed */
        liveThreads.remove(threadToDie);
        return true;
_L1:
        if(it.hasNext()) goto _L3; else goto _L2
_L2:
        false;
        vector;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void run()
    {
        if("VIETEL".equalsIgnoreCase(Preference.mobileOperator))
        {
            if(Preference.VIETTEL_MODE.equalsIgnoreCase("t") || Preference.VIETTEL_MODE.equalsIgnoreCase("tr"))
            {
                (new PDUBuilder(toSMSC)).start();
                (new SMSCSender(toSMSC, this)).start();
                (new ResponseProcessor(responseQueue, wait4ReportTable)).start();
                (new ReportProcessor(deliveryQueue, wait4ReportTable)).start();
                if(Preference.reportRequired)
                    (new CheckWait4ReportTimeout(wait4ReportTable)).start();
            }
            if(Preference.VIETTEL_MODE.equalsIgnoreCase("r") || Preference.VIETTEL_MODE.equalsIgnoreCase("tr"))
            {
                (new RequestProcessor(requestQueue, toSMSC)).start();
                if(!Preference.asynchronous)
                    (new SMSCReceiver(requestQueue, responseQueue, deliveryQueue, sessionr)).start();
            }
            if(Preference.VIETTEL_MODE.equalsIgnoreCase("r") && Preference.asynchronous)
                (new SMSCSender(toSMSC, this)).start();
        } else
        {
            if(Preference.bindMode.equalsIgnoreCase("t") || Preference.bindMode.equalsIgnoreCase("tr"))
            {
                (new SMSCSender(toSMSC, this)).start();
                (new PDUBuilder(toSMSC)).start();
                (new ResponseProcessor(responseQueue, wait4ReportTable)).start();
                (new ReportProcessor(deliveryQueue, wait4ReportTable)).start();
                if(Preference.reportRequired)
                    (new CheckWait4ReportTimeout(wait4ReportTable)).start();
            }
            if(Preference.bindMode.equalsIgnoreCase("r") || Preference.bindMode.equalsIgnoreCase("tr"))
            {
                (new RequestProcessor(requestQueue, toSMSC)).start();
                if(!Preference.asynchronous)
                    (new SMSCReceiver(requestQueue, responseQueue, deliveryQueue, sessionr)).start();
            }
            if(Preference.bindMode.equalsIgnoreCase("r") && Preference.asynchronous)
                (new SMSCSender(toSMSC, this)).start();
        }
        (new EnquireLinkThread(this)).start();
        System.out.println("Gateway started.");
          goto _L1
_L3:
        showMenu();
        continue; /* Loop/switch isn't completed */
        Exception e;
        e;
        System.out.println("Gateway::" + e.getMessage());
_L1:
        if(running) goto _L3; else goto _L2
_L2:
        System.out.println("END.");
        return;
    }

    public static void saveWait4ReportTable()
    {
        int nTimeout;
        if(wait4ReportTable == null || wait4ReportTable.size() == 0)
            return;
        nTimeout = 0;
        System.out.print("Saving wait4report_Table...");
        PrintWriter fout = new PrintWriter(new FileOutputStream("wait4report.dat", false));
        PrintWriter fTimeout = new PrintWriter(new FileOutputStream("wait4report_timeout.dat", true));
        synchronized(wait4ReportTable)
        {
            for(Enumeration e = (new Vector(wait4ReportTable.keySet())).elements(); e.hasMoreElements();)
            {
                String key = (String)e.nextElement();
                Wait4Report value = (Wait4Report)wait4ReportTable.get(key);
                if(value.isTimeout())
                {
                    fTimeout.print(key + "\t");
                    fTimeout.print(value.logId + "\t");
                    fTimeout.print(value.time.getTime() + "\t");
                    fTimeout.println(value.time);
                    nTimeout++;
                } else
                {
                    fout.print(key + "\t");
                    fout.print(value.logId + "\t");
                    fout.print(value.time.getTime() + "\t");
                    fout.println(value.time);
                }
            }

        }
        fout.flush();
        fout.close();
        fTimeout.flush();
        fTimeout.close();
        System.out.println(" [" + nTimeout + " entries timeout]!");
        break MISSING_BLOCK_LABEL_432;
        IOException ex;
        ex;
        System.out.println("Gateway.saveWait4ReportTable: " + ex.getMessage());
        return;
    }

    private void showMenu()
    {
        String option;
        System.out.println();
        System.out.println("S - submit");
        System.out.println("R - reload");
        System.out.println("Q - quit");
        System.out.println();
        option = "";
        option = keyboard.readLine();
        option = option.toUpperCase();
        if("S".equals(option))
        {
            String sFrom = getParam("Srce Address " + Preference.sourceAddressList.toString(), "");
            String sTo = getParam("Dest Address: ", "");
            String sMessage = getParam("Shor Message: ", "");
            DBTools dbTools = new DBTools();
            dbTools.add2SMSSendQueue(sTo, sFrom, "?", "CONSOLE", sMessage, 0, 0, 0, new BigDecimal(0.0D), 1, 1, 0);
            break MISSING_BLOCK_LABEL_591;
        }
        if(!"R".equals(option))
            break MISSING_BLOCK_LABEL_223;
        Preference.loadProperties(propsFilePath);
        System.out.println("----Prefixes------");
        System.out.println(Preference.prefixMap);
        break MISSING_BLOCK_LABEL_219;
        IOException e;
        e;
        System.out.println("Gateway.showMenu(): khong tim thay file cau hinh " + propsFilePath);
        break MISSING_BLOCK_LABEL_591;
        if("Q".equals(option))
        {
            this;
            exit();
        } else
        if("ST".equals(option))
            System.out.println("Startup Time: " + new Timestamp(startup_time));
        else
        if("BT".equals(option))
            System.out.println("Bound/Unbound Time: " + new Timestamp(bound_or_unbound_time));
        else
        if(option.startsWith("MB"))
        {
            String mobile = null;
            if(option.length() > 2)
                mobile = option.substring(2).trim();
            if(mobile != null && !"".equals(mobile))
            {
                System.out.println("Looking for mobile: " + mobile);
                MobileBufferInfo info = MobileBuffer.lookup(mobile);
                if(info != null)
                {
                    System.out.println("MO Time : " + new Timestamp(info.mo_Time * 1000L));
                    System.out.println("MO Count: " + info.mo_Counter);
                    System.out.println("MT Time : " + new Timestamp(info.mt_Time * 1000L));
                    System.out.println("MT Count: " + info.mt_Counter);
                } else
                {
                    System.out.println("Not found");
                }
            } else
            {
                System.out.println("Total size of mobileBuffer: " + MobileBuffer.size());
            }
        }
        break MISSING_BLOCK_LABEL_628;
        mobile;
        System.out.println("Gateway::" + mobile.getMessage());
        return;
    }

    public static synchronized void unbind()
    {
        try
        {
            if(bound || boundr)
            {
                Utilities _tmp = util;
                Utilities.log("Going to unbind...");
            }
            if(bound)
            {
                UnbindResp response = session.unbind();
                Utilities _tmp1 = util;
                Utilities.log("Unbind response " + response.debugString());
                bound = false;
            }
            if(boundr)
            {
                if(sessionr.getReceiver().isReceiver())
                {
                    Utilities _tmp2 = util;
                    Utilities.log("It can take a while to stop the receiver.");
                    Utilities _tmp3 = util;
                    Utilities.log("Doi chut xiu!!!");
                }
                UnbindResp response = sessionr.unbind();
                Utilities _tmp4 = util;
                Utilities.log("Unbind response " + response.debugString());
                boundr = false;
            }
            if(!bound && !boundr)
            {
                Utilities _tmp5 = util;
                Utilities.log("UnBound to SMSC At " + new Timestamp(System.currentTimeMillis()) + "!!!");
            }
        }
        catch(Exception e)
        {
            Utilities _tmp6 = util;
            Utilities.log("Unbind operation failed. " + e);
        }
    }

    static boolean bound = false;
    static long bound_or_unbound_time = 0L;
    static boolean boundr = false;
    static final String copyright = "Copyright (c) 1997-2004 by M-Commerce center, VASC";
    static Queue dbPool = null;
    private Queue deliveryQueue;
    static BufferedReader keyboard;
    static long last_received_time = 0L;
    static Vector liveThreads = null;
    private PDUEventListener pduListener;
    static String propsFilePath = "gateway.cfg";
    private Queue requestQueue;
    private Queue responseQueue;
    static boolean running = true;
    static Session session = null;
    static Session sessionr = null;
    static long startup_time = System.currentTimeMillis();
    private Queue toSMSC;
    static Utilities util = null;
    static final String version = "SMPP Gateway, version 1.0\n";
    static Map wait4ReportTable = null;

    static 
    {
        System.out.println();
        System.out.println("Copyright (c) 1997-2004 by M-Commerce center, VASC");
        System.out.println("SMPP Gateway, version 1.0\n");
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
