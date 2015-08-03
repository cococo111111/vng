// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Gateway.java

package com.vmg.smpp.gateway;

import com.vmg.common.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.smpp.*;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger, Preference, DBTools, ShutdownInterceptor, 
//            DBException, LoadConfig, SMSCSender, GetMt2EmsQueue, 
//            EMSBuilderfromQueue, ResponseProcessor, Add2EmsSendLog, ResendErrMt2Telcos, 
//            CheckTimeOutWait4Response, RequestProcessor, SMSCReceiver, WriteLogQueue, 
//            EnquireLinkThread, KeyboardReader, SMSCTools, PDUEventListener, 
//            Wait4Report, EMSData, PDUData

public class Gateway extends Thread
{

    static Vector getLiveThreads()
    {
        return liveThreads;
    }

    static void addLiveThread(Thread thread)
    {
        synchronized(liveThreads)
        {
            liveThreads.add(thread);
        }
    }

    static boolean removeThread(Thread diedThread)
    {
        Vector vector = liveThreads;
        JVM INSTR monitorenter ;
        Iterator it = liveThreads.iterator();
          goto _L1
_L4:
        Thread currThread = (Thread)it.next();
        if(!currThread.equals(diedThread)) goto _L1; else goto _L2
_L2:
        liveThreads.remove(diedThread);
        return true;
_L1:
        if(it.hasNext()) goto _L4; else goto _L3
_L3:
        vector;
        JVM INSTR monitorexit ;
        return false;
        vector;
        JVM INSTR monitorexit ;
        throw ;
    }

    static Vector getLiveTelnetSessions()
    {
        if(liveTelnetSessions == null)
            liveTelnetSessions = new Vector();
        return liveTelnetSessions;
    }

    public Queue getRequestQueue()
    {
        return requestQueue;
    }

    public Queue getResendQueue()
    {
        return ResendQueue;
    }

    public Queue getResponseQueue()
    {
        return responseQueue;
    }

    public Queue getDeliveryQueue()
    {
        return deliveryQueue;
    }

    public Queue getToSMSCQueue()
    {
        return toSMSC;
    }

    public Queue getEMSQueue()
    {
        return EMSQueue;
    }

    public Queue getSendLogQueue()
    {
        return SendLogQueue;
    }

    public Session getSession()
    {
        return session;
    }

    public Map getWait4ReportTable()
    {
        return wait4ReportTable;
    }

    public Map getWait4ResponseTable()
    {
        return wait4ResponseTable;
    }

    public PDUEventListener getPDUEventListener()
    {
        return pduListener;
    }

    public Gateway()
    {
        deliveryQueue = null;
        pduListener = null;
        requestQueue = new Queue();
        responseQueue = new Queue();
        deliveryQueue = new Queue();
        EMSQueue = new Queue();
        SendLogQueue = new Queue();
        ResendQueue = new Queue();
        try
        {
            Logger.setLogWriter("../log/gateway${yyyy-MM-dd}.log");
        }
        catch(IOException ioexception) { }
        Logger.setLogLevel("info,warn,error,crisis");
    }

    public static void main(String args[])
    {
        Gateway gateway = new Gateway();
        Utilities.log("Gateway", "Copyright (c) 2006-2008 by VietNamNet Media Group");
        Utilities.log("Gateway", "SMPP Client, version date:11-09-2008");
        Utilities.ConfigPrirose();
        try
        {
            Preference.loadProperties(propsFilePath);
        }
        catch(IOException e)
        {
            Logger.info("Gateway", "main: khong tim thay file cau hinh" + propsFilePath);
        }
        try
        {
            DBTools.Alert2YM("Gateway Start!!!");
        }
        catch(DBException dbexception) { }
        ShutdownInterceptor shutdownInterceptor = new ShutdownInterceptor(gateway);
        Runtime.getRuntime().addShutdownHook(shutdownInterceptor);
        gateway.bind();
        gateway.start();
    }

    public void run()
    {
        if("1".equalsIgnoreCase(Preference.MAPCP))
        {
            loadconfig = new LoadConfig();
            loadconfig.setPriority(10);
            loadconfig.start();
            while(!loadconfig.isLoaded) 
                try
                {
                    sleep(100L);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
        }
        try
        {
            PullMO2Queue();
        }
        catch(IOException ex)
        {
            Logger.info(getClass().getName(), "PullMO2Queue IOException:" + ex.getMessage());
        }
        if(Preference.bindMode.equalsIgnoreCase("t") || Preference.bindMode.equalsIgnoreCase("tr"))
        {
            for(int i = 0; i < Preference.nTheardMT; i++)
                (new SMSCSender(toSMSC, this)).start();

            for(int k = 0; k < Preference.nGetMTfromDB; k++)
                (new GetMt2EmsQueue(EMSQueue, (new StringBuffer(String.valueOf(Preference.nGetMTfromDB))).toString(), (new StringBuffer(String.valueOf(k))).toString())).start();

            for(int n = 0; n < Preference.nBuildEMS; n++)
                (new EMSBuilderfromQueue(toSMSC, EMSQueue, wait4ResponseTable)).start();

            for(int j = 0; j < Preference.nTheardResp; j++)
                (new ResponseProcessor(responseQueue, wait4ResponseTable, SendLogQueue, ResendQueue)).start();

            for(int l = 0; l < Preference.nSendLog; l++)
                (new Add2EmsSendLog(SendLogQueue)).start();

            (new ResendErrMt2Telcos(ResendQueue, EMSQueue)).start();
            (new CheckTimeOutWait4Response(wait4ResponseTable, SendLogQueue)).start();
        }
        if(Preference.bindMode.equalsIgnoreCase("r") || Preference.bindMode.equalsIgnoreCase("tr"))
        {
            for(int k = 0; k < Preference.nTheardMO; k++)
                (new RequestProcessor(requestQueue, toSMSC)).start();

            if(!Preference.asynchronous)
                (new SMSCReceiver(sessionr, requestQueue)).start();
        }
        if(Preference.bindMode.equalsIgnoreCase("r") && Preference.asynchronous)
        {
            for(int i = 0; i < Preference.nTheardMT; i++)
                (new SMSCSender(toSMSC, this)).start();

        }
        if(Preference.nLogQueue == 1)
            (new WriteLogQueue(toSMSC, requestQueue, responseQueue, EMSQueue, SendLogQueue, ResendQueue, wait4ResponseTable)).start();
        (new EnquireLinkThread(this)).start();
        if(Preference.logToConsole == 1)
            (new KeyboardReader()).start();
        Logger.info("Gateway", "Gateway started.");
    }

    public synchronized void bind()
    {
        if(bound)
        {
            Logger.info("Gateway.bind", "Already bound, unbind first");
            return;
        }
        bound_or_unbound_time = System.currentTimeMillis();
        if(Preference.asynchronous)
            bindASync();
        else
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
            Logger.error("Gateway.bind", "Invalid bind mode (" + Preference.bindMode + ") expected t, r or tr. Operation canceled.");
            return;
        }
        bound_or_unbound_time = System.currentTimeMillis();
    }

    public synchronized void bindSyncTransmitter()
    {
        BindRequest request = null;
        BindResponse response = null;
        TCPIPConnection connection = null;
        while(!bound) 
            try
            {
                Logger.info("Gateway.bindSyncTransmitter", "Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
                connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
                connection.setReceiveTimeout(2000L);
                session = new Session(connection);
                request = new BindTransmitter();
                request.setSystemId(Preference.systemId);
                request.setPassword(Preference.password);
                request.setSystemType(Preference.systemType);
                request.setInterfaceVersion((byte)52);
                request.setAddressRange(Preference.addressRange);
                Logger.info("Gateway.bindSyncTransmitter", "Bind request " + request.debugString());
                response = session.bind(request);
                Logger.info("Gateway.bindSyncTransmitter", "Bind response " + response.debugString());
                if(response.getCommandStatus() == 0)
                {
                    bound = true;
                    Logger.info("Gateway.bindSyncTransmitter", "Succesfully Bound to SMSC in t-sync mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
                } else
                {
                    SMSCTools.printCommandStatus(response.getCommandStatus());
                }
            }
            catch(Exception e)
            {
                Logger.error("Gateway.bindSyncTransmitter", "Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
                try
                {
                    sleep(Preference.timeRebind);
                }
                catch(InterruptedException interruptedexception) { }
            }
    }

    public synchronized void bindSyncReceiver()
    {
        BindRequest request = null;
        BindResponse response = null;
        Connection connection = null;
        while(!boundr) 
            try
            {
                Logger.info("Gateway", "Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
                connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
                connection.setReceiveTimeout(2000L);
                sessionr = new Session(connection);
                request = new BindReceiver();
                request.setSystemId(Preference.systemId);
                request.setPassword(Preference.password);
                request.setSystemType(Preference.systemType);
                request.setInterfaceVersion((byte)52);
                request.setAddressRange(Preference.addressRange);
                Logger.info(getClass().getName(), "Bind request " + request.debugString());
                response = sessionr.bind(request);
                Logger.info(getClass().getName(), "Bind response " + response.debugString());
                if(response.getCommandStatus() == 0)
                {
                    boundr = true;
                    Logger.info(getClass().getName(), "Succesfully Bound to SMSC in r-sync mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
                } else
                {
                    SMSCTools.printCommandStatus(response.getCommandStatus());
                }
            }
            catch(Exception e)
            {
                Logger.info(getClass().getName(), e.toString());
                Logger.info(getClass().getName(), "Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
                try
                {
                    sleep(Preference.timeRebind);
                }
                catch(InterruptedException interruptedexception) { }
            }
    }

    public synchronized void bindASync()
    {
        BindRequest request;
        request = null;
        BindResponse response = null;
        Connection connection = null;
          goto _L1
_L3:
        Logger.info(getClass().getName(), "Connecting to SMSC " + Preference.ipAddress + ":" + Preference.port);
        Connection connection = new TCPIPConnection(Preference.ipAddress, Preference.port);
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
            Logger.error(getClass().getName(), "Invalid bind mode (" + Preference.bindMode + ") expected t, r or tr. Operation canceled.");
            return;
        }
        try
        {
            request.setSystemId(Preference.systemId);
            request.setPassword(Preference.password);
            request.setSystemType(Preference.systemType);
            request.setInterfaceVersion((byte)52);
            request.setAddressRange(Preference.addressRange);
            if(pduListener == null)
                pduListener = new PDUEventListener(requestQueue, responseQueue, deliveryQueue, toSMSC);
            Logger.info(getClass().getName(), "Bind request " + request.debugString());
            BindResponse response = session.bind(request, pduListener);
            Logger.info(getClass().getName(), "Bind response " + response.debugString());
            if(response.getCommandStatus() == 0)
            {
                bound = true;
                Logger.info(getClass().getName(), "Succesfully Bound to SMSC in " + Preference.bindMode + (Preference.asynchronous ? "-async" : "-sync") + " mode At " + new Timestamp(System.currentTimeMillis()) + "!!!");
            } else
            {
                SMSCTools.printCommandStatus(response.getCommandStatus());
                DBTools.Alert2YM(Preference.Channel + "Gateway:" + Preference.mobileOperator + "-> ERROR: " + response.getCommandStatus());
                try
                {
                    sleep(Preference.timeRebind);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
        catch(Exception e)
        {
            Logger.error(getClass().getName(), "Bind operation FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
            try
            {
                Logger.crisis(getClass().getName(), "-Gateway" + Preference.mobileOperator + "-> Bind operation FAILT");
                sleep(Preference.timeRebind);
            }
            catch(Exception exception) { }
        }
_L1:
        if(!bound) goto _L3; else goto _L2
_L2:
    }

    public static synchronized void unbind()
    {
        try
        {
            if(bound || boundr)
                Logger.info("Gateway", "Going to unbind...");
            if(bound)
            {
                UnbindResp response = session.unbind();
                Logger.info("Gateway", "Unbind response " + response.debugString());
                bound = false;
            }
            if(boundr)
            {
                if(sessionr.getReceiver().isReceiver())
                {
                    Logger.info("Gateway", "It can take a while to stop the receiver.");
                    Logger.info("Gateway", "Doi chut xiu!!!");
                }
                UnbindResp response = sessionr.unbind();
                Logger.info("Gateway", "Unbind response " + response.debugString());
                boundr = false;
            }
            if(!bound && !boundr)
                Logger.info("Gateway", "UnBound to SMSC At " + new Timestamp(System.currentTimeMillis()) + "!!!");
        }
        catch(Exception e)
        {
            Logger.error("Gateway", "Unbind operation failed. " + e);
        }
    }

    public static void saveWait4ReportTable()
    {
        if(wait4ReportTable == null || wait4ReportTable.size() == 0)
            return;
        int nTimeout = 0;
        Logger.info("Gateway", "Saving wait4report_Table...");
        try
        {
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
            Logger.info("Gateway", " [" + nTimeout + " entries timeout]!");
        }
        catch(IOException ex)
        {
            Logger.error("Gateway", "Gateway.saveWait4ReportTable: " + ex.getMessage());
        }
    }

    public static void saveWait4ResponseTable()
    {
label0:
        {
            int nTimeout;
            Enumeration e;
            Exception ex;
            String key;
            EMSData value;
            synchronized(wait4ResponseTable)
            {
                if(wait4ResponseTable != null && wait4ResponseTable.size() != 0)
                    break label0;
            }
            return;
        }
        nTimeout = 0;
        Logger.info("Gateway", "Saving wait4ResponseTable...");
        try
        {
            if(wait4ResponseTable.size() > 0)
                for(e = (new Vector(wait4ResponseTable.keySet())).elements(); e.hasMoreElements();)
                {
                    key = (String)e.nextElement();
                    value = (EMSData)wait4ResponseTable.get(key);
                    if(value != null)
                    {
                        saveSMSObject(nTimeout + ".w4rt", value);
                        nTimeout++;
                    }
                }

        }
        // Misplaced declaration of an exception variable
        catch(Exception ex)
        {
            Logger.error("Gateway", "IOException:Loi luu file tu wait4ResponseTable: " + ex.getMessage());
        }
        map;
        JVM INSTR monitorexit ;
    }

    public static void saveMO()
    {
        PDUData pdud = null;
        PDU pdu = null;
        int nTimeout = 1;
        Logger.info("Gateway", "Saving MO-MT-Response to file...");
        try
        {
            Logger.info("Gateway.SaveMO", "Save MO queue size");
            while(requestQueue != null && requestQueue.size() != 0) 
            {
                Logger.info("Gateway", "MO Queue size: " + requestQueue.size());
                pdud = (PDUData)requestQueue.dequeue();
                pdu = pdud.getPDU();
                if(pdu.isRequest())
                {
                    saveToFile(nTimeout + ".mo", pdu);
                    nTimeout++;
                }
            }
            Logger.info("Gateway.SaveMO", "Save Response queue size");
            while(responseQueue != null && responseQueue.size() != 0) 
            {
                Logger.info("Gateway", "Response Queue size: " + responseQueue.size());
                pdu = (PDU)responseQueue.dequeue();
                if(pdu.isResponse())
                {
                    saveToFile(nTimeout + ".respond", pdu);
                    nTimeout++;
                }
            }
            Logger.info("Gateway.SaveMO", "Save  MT get from DB Queue queue size");
            while(EMSQueue != null && EMSQueue.size() != 0) 
            {
                Logger.info("Gateway", "MT get from DB Queue size: " + EMSQueue.size());
                EMSData ems = (EMSData)EMSQueue.dequeue();
                if(ems != null)
                {
                    saveSMSObject(nTimeout + ".mt", ems);
                    nTimeout++;
                }
            }
            Logger.info("Gateway.SaveMO", "Save SendLogQueue");
            while(SendLogQueue != null && SendLogQueue.size() != 0) 
            {
                Logger.info("Gateway", "SendLogQueue size: " + SendLogQueue.size());
                EMSData ems = (EMSData)SendLogQueue.dequeue();
                if(ems != null)
                {
                    saveSMSObject(nTimeout + ".slog", ems);
                    nTimeout++;
                    System.out.print(".");
                }
            }
            Logger.info("Gateway.SaveMO", "Save ResendQueue queue size");
            while(ResendQueue != null && ResendQueue.size() != 0) 
            {
                Logger.info("Gateway", "ResendQueue size: " + ResendQueue.size());
                EMSData ems = (EMSData)ResendQueue.dequeue();
                if(ems != null)
                {
                    saveSMSObject(nTimeout + ".rsend", ems);
                    nTimeout++;
                    System.out.print(".");
                }
            }
            Logger.info("Gateway.SaveMO", "Save toSMSC queue");
            for(; toSMSC != null && toSMSC.size() != 0; System.out.print("."))
            {
                Logger.info("Gateway", "toSMSC size: " + toSMSC.size());
                pdu = (PDU)toSMSC.dequeue();
                saveToFile(nTimeout + ".tosmsc", pdu);
                nTimeout++;
            }

        }
        catch(Exception ex)
        {
            Logger.error("Gateway.SaveMO", ex.getMessage());
        }
    }

    private static void saveToFile(String pduFile, PDU pdu)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String datetime = dateFormat.format(date);
        try
        {
            byte b[] = pdu.getData().getBuffer();
            FileOutputStream fout = new FileOutputStream("./MOQueue/" + datetime + pduFile);
            fout.write(b);
            fout.flush();
            fout.close();
        }
        catch(Exception ex)
        {
            Logger.error("Gateway", "saveToFile:" + ex.getMessage());
        }
    }

    public static void saveSMSObject(String sfile, EMSData object)
    {
        Logger.info("Gateway", " Saving EMSData into file " + sfile);
        FileOutputStream fout = null;
        ObjectOutputStream objOut = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String datetime = dateFormat.format(date);
        try
        {
            fout = new FileOutputStream("./MOQueue/" + datetime + sfile);
            objOut = new ObjectOutputStream(fout);
            objOut.writeObject(object);
            objOut.flush();
        }
        catch(IOException ex)
        {
            Logger.error("Gateway", "Save data error: " + ex.getMessage());
        }
        finally
        {
            try
            {
                objOut.close();
                fout.close();
            }
            catch(IOException ioexception) { }
        }
        return;
    }

    public static void saveSMSObject1(String sfile, EMSData object)
    {
        Logger.info("Gateway", " Saving EMSData into file " + sfile);
        FileOutputStream fout = null;
        ObjectOutputStream objOut = null;
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String datetime = dateFormat.format(date);
            fout = new FileOutputStream("./MOQueue/" + datetime + sfile);
            objOut = new ObjectOutputStream(fout);
            objOut.writeObject(object);
            objOut.flush();
        }
        catch(IOException ex)
        {
            Logger.error("{Gateway}", "Save data error: " + sfile + " -> " + ex.getMessage());
        }
        finally
        {
            try
            {
                objOut.close();
                fout.close();
            }
            catch(IOException ioexception) { }
        }
        return;
    }

    public static EMSData readSMSObject(File file)
    {
        FileInputStream fin;
        ObjectInputStream objIn;
        EMSData object;
        fin = null;
        objIn = null;
        object = null;
        EMSData emsdata;
        fin = new FileInputStream(file);
        objIn = new ObjectInputStream(fin);
        object = (EMSData)objIn.readObject();
        emsdata = object;
        return emsdata;
        ClassNotFoundException ex1;
        ex1;
_L1:
        return null;
        IOException ex;
        ex;
        Logger.error("{Gateway}", "ReadData: " + ex.getMessage());
          goto _L1
        local;
        objIn.close();
        fin.close();
        boolean success = file.renameTo(new File("./MOQueue/bak/" + file.getName() + ".bak"));
        if(!success)
        {
            Logger.error("Gateway", "Rename failed:" + file.getName());
            if(!file.delete())
                Logger.error("Gateway", "Delete: deletion failed:" + file.getName());
        }
        return object;
        IOException ex;
        ex;
        Logger.error("{Gateway}", "Save data error can not close file: -> " + ex.getMessage());
        JVM INSTR ret 5;
    }

    public static void saveMOData(String fileName)
    {
        Logger.info("Gateway", "Saving " + fileName + " . . .");
        FileOutputStream fout = null;
        ObjectOutputStream objOut = null;
        PDUData pdu = null;
        int nTimeout = 0;
        if(requestQueue == null || requestQueue.size() == 0)
            Logger.info("Gateway", "MO queue size = 0");
        try
        {
            fout = new FileOutputStream(fileName, false);
            objOut = new ObjectOutputStream(fout);
            while(requestQueue != null && requestQueue.size() != 0) 
            {
                pdu = (PDUData)requestQueue.dequeue();
                objOut.writeObject(pdu);
                objOut.flush();
                nTimeout++;
            }
            while(requestQueue != null && requestQueue.size() != 0) 
            {
                pdu = (PDUData)requestQueue.dequeue();
                objOut.writeObject(pdu);
                objOut.flush();
                nTimeout++;
            }
            Logger.info("Gateway.saveMOData", "num of Data: " + nTimeout + " MOs Saved");
        }
        catch(IOException ex)
        {
            Logger.error("Gateway.saveMOData", "Save data error: " + ex.getMessage());
        }
        finally
        {
            try
            {
                objOut.close();
                fout.close();
            }
            catch(IOException ex)
            {
                Logger.error("Gateway.saveMOData", "Save data error: " + ex.getMessage());
            }
        }
        return;
    }

    public static void loadSMSMOData(String fileName)
    {
        Logger.info("Gateway.loadSMSMOData", "Loading data from file...");
        boolean flag = true;
        FileInputStream fin = null;
        ObjectInputStream objIn = null;
        try
        {
            fin = new FileInputStream(fileName);
            objIn = new ObjectInputStream(fin);
            while(flag) 
                try
                {
                    PDU object = (PDU)objIn.readObject();
                    requestQueue.enqueue(object);
                }
                catch(Exception ex)
                {
                    flag = false;
                }
            Logger.info("Gateway.loadSMSMOData", " Load data successful!");
        }
        catch(IOException ex)
        {
            Logger.error("Gateway.loadSMSMOData", "Load data error: " + ex.getMessage());
        }
        finally
        {
            try
            {
                fin.close();
            }
            catch(Exception ex)
            {
                Logger.error("Gateway.loadSMSMOData", "Load data error: " + ex.getMessage());
            }
        }
        return;
    }

    public static void loadWait4ReportTable()
    {
        Map map = new HashMap();
        Logger.info("Gateway", "Loading wait4report_Table...");
        try
        {
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
            Logger.info("Gateway", " [" + map.size() + " entries loaded, " + nTimeout + " entries timeout]!");
        }
        catch(IOException ex)
        {
            Logger.error("Gateway", "Gateway.loadWait4ReportTable: " + ex.getMessage());
        }
    }

    public static void loadWait4ResponseTable()
    {
        Map map = new HashMap();
        Logger.info("Gateway", "Loading wait4ResponseTable...");
        try
        {
            BufferedReader fin = new BufferedReader(new FileReader("wait4Response.dat"));
            PrintWriter fTimeout = new PrintWriter(new FileOutputStream("wait4Response_timeout.dat", true));
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
            Logger.info("Gateway", " [" + map.size() + " entries loaded, " + nTimeout + " entries timeout]!");
        }
        catch(IOException ex)
        {
            Logger.info("Gateway", "Gateway.loadWait4ReportTable: " + ex.getMessage());
        }
    }

    private String getParam(String prompt, String defaultValue)
    {
        String value = "";
        String promptFull = prompt;
        promptFull = promptFull + (defaultValue != null ? " [" + defaultValue + "] " : "");
        Logger.info("Gateway", promptFull);
        try
        {
            value = keyboard.readLine();
        }
        catch(IOException e)
        {
            Logger.error("Gateway", "Got exception getting a param. " + e);
        }
        if(value.compareTo("") == 0)
            return defaultValue;
        else
            return value;
    }

    private void showMenu()
    {
        System.out.println();
        System.out.println("R - Reload");
        System.out.println("Q - Quit");
        System.out.println("M - Load MO");
        System.out.println("T - Add new Thread");
        System.out.println();
        String option = "";
        try
        {
            option = keyboard.readLine();
            option = option.toUpperCase();
            if("R".equals(option))
                try
                {
                    Preference.loadProperties(propsFilePath);
                }
                catch(IOException e)
                {
                    Logger.error("Gateway", "Gateway.showMenu(): khong tim thay file cau hinh " + propsFilePath);
                }
            else
            if("TH".equals(option))
                synchronized(wait4ResponseTable)
                {
                    Logger.info(Preference.mobileOperator + "-toSMSQueueTX: " + toSMSC.size() + "\n" + Preference.mobileOperator + "-fromSMSQueueRX: " + requestQueue.size() + "\n" + Preference.mobileOperator + "-RespondSMSQueueRX: " + responseQueue.size() + "\nWait4ResponseTable: " + wait4ResponseTable.size() + "\nSendLogQueue: " + SendLogQueue.size() + "\nGetMT2Queue: " + EMSQueue.size() + "\nMOSimQueue: " + ResendQueue.size());
                }
            else
            if("M".equals(option))
                PullMO2Queue();
            else
            if(!"Q".equals(option) && option.equalsIgnoreCase("T"))
            {
                if(Preference.nAddNewTheardMO > 0)
                {
                    for(int i = 0; i < Preference.nAddNewTheardMO; i++)
                    {
                        Logger.info("Gateway", "New Thread MO added");
                        (new RequestProcessor(requestQueue, toSMSC)).start();
                    }

                }
                if(Preference.nAddNewTheardMT > 0)
                {
                    for(int i = 0; i < Preference.nAddNewTheardMT; i++)
                    {
                        Logger.info("Gateway", "New Thread MT added");
                        (new SMSCSender(toSMSC, this)).start();
                    }

                }
                if(Preference.nAddNewTheardResp > 0)
                {
                    for(int i = 0; i < Preference.nAddNewTheardResp; i++)
                    {
                        Logger.info("Gateway", "New Thread Respond added");
                        (new ResponseProcessor(responseQueue, wait4ResponseTable, SendLogQueue, ResendQueue)).start();
                    }

                }
                if(Preference.nAddNewBuildEMS > 0)
                {
                    for(int i = 0; i < Preference.nAddNewBuildEMS; i++)
                    {
                        Logger.info("Gateway", "New Thread EMSBuilder added");
                        (new EMSBuilderfromQueue(toSMSC, EMSQueue, wait4ResponseTable)).start();
                    }

                }
                if(Preference.nAddNewSendLog > 0)
                {
                    for(int i = 0; i < Preference.nAddNewSendLog; i++)
                    {
                        Logger.info("Gateway", "New Thread Send Log added");
                        (new Add2EmsSendLog(SendLogQueue)).start();
                    }

                }
            }
        }
        catch(Exception e)
        {
            Logger.error("Gateway", "Gateway::" + e.getMessage());
        }
    }

    public void PullMO2Queue()
        throws IOException
    {
        byte buffer[] = (byte[])null;
        PDU pdu = null;
        EMSData ems = null;
        Queue resp = new Queue();
        try
        {
            File f = new File("./MOQueue/");
            File listFile[] = f.listFiles();
            int numOfFile = 0;
            for(int i = 0; i < listFile.length; i++)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
                Date date = new Date();
                String datetime = dateFormat.format(date);
                if(listFile[i].getName().toUpperCase().endsWith(".MO"))
                {
                    FileInputStream fin = new FileInputStream(listFile[i]);
                    buffer = new byte[fin.available()];
                    fin.read(buffer);
                    ByteBuffer bf = new ByteBuffer();
                    bf.appendBytes(buffer);
                    pdu = PDU.createPDU(bf);
                    PDUData pdud = new PDUData();
                    pdud.setPDU(pdu);
                    pdud.setRequestID(datetime);
                    requestQueue.enqueue(pdud);
                    fin.close();
                    boolean success = listFile[i].renameTo(new File("./MOQueue/bak/" + listFile[i].getName() + ".bak"));
                    if(!success)
                    {
                        Logger.info(getClass().getName(), "Rename failed:" + listFile[i].getName());
                        if(!listFile[i].delete())
                            Logger.info(getClass().getName(), "Delete: deletion failed:" + listFile[i].getName());
                    }
                }
                if(listFile[i].getName().toLowerCase().endsWith(".tosmsc"))
                {
                    FileInputStream fin = new FileInputStream(listFile[i]);
                    buffer = new byte[fin.available()];
                    fin.read(buffer);
                    ByteBuffer bf = new ByteBuffer();
                    bf.appendBytes(buffer);
                    pdu = PDU.createPDU(bf);
                    toSMSC.enqueue(pdu);
                    fin.close();
                    boolean success = listFile[i].renameTo(new File("./MOQueue/bak/" + listFile[i].getName() + ".bak"));
                    if(!success)
                    {
                        Logger.info(getClass().getName(), "Rename failed:" + listFile[i].getName());
                        if(!listFile[i].delete())
                            Logger.info(getClass().getName(), "Delete: deletion failed:" + listFile[i].getName());
                    }
                }
                if(listFile[i].getName().toUpperCase().endsWith(".RESPOND"))
                {
                    FileInputStream fin = new FileInputStream(listFile[i]);
                    buffer = new byte[fin.available()];
                    fin.read(buffer);
                    ByteBuffer bf = new ByteBuffer();
                    bf.appendBytes(buffer);
                    pdu = PDU.createPDU(bf);
                    resp.enqueue(pdu);
                    fin.close();
                    boolean success = listFile[i].renameTo(new File("./MOQueue/bak/" + listFile[i].getName() + ".bak"));
                    if(!success)
                    {
                        Logger.info(getClass().getName(), "Rename failed:" + listFile[i].getName());
                        if(!listFile[i].delete())
                            Logger.info(getClass().getName(), "Delete: deletion failed:" + listFile[i].getName());
                    }
                }
                if(listFile[i].getName().toLowerCase().endsWith(".rsend"))
                {
                    ems = readSMSObject(listFile[i]);
                    if(ems != null)
                        ResendQueue.enqueue(ems);
                }
                if(listFile[i].getName().toLowerCase().endsWith(".slog"))
                {
                    ems = readSMSObject(listFile[i]);
                    if(ems != null)
                        SendLogQueue.enqueue(ems);
                }
                if(listFile[i].getName().toLowerCase().endsWith(".w4rt"))
                {
                    ems = readSMSObject(listFile[i]);
                    if(ems != null)
                        synchronized(wait4ResponseTable)
                        {
                            wait4ResponseTable.put((new StringBuffer(String.valueOf(ems.getId().intValue()))).toString(), ems);
                        }
                }
                if(listFile[i].getName().toLowerCase().endsWith(".mt"))
                {
                    ems = readSMSObject(listFile[i]);
                    if(ems != null)
                        EMSQueue.enqueue(ems);
                }
                if(listFile[i].getName().toLowerCase().endsWith(".modb"))
                {
                    ems = readSMSObject(listFile[i]);
                    DBTools.add2SMSReceiveQueueR(ems.getUserId(), ems.getServiceId(), ems.getMobileOperator(), ems.getCommandCode(), ems.getText(), ems.getsRequestID());
                }
                if(listFile[i].getName().toLowerCase().endsWith(".cdr"))
                {
                    EMSData sms = readSMSObject(listFile[i]);
                    if(sms.getMessageType() == 1 || sms.getMessageType() == 3)
                    {
                        if(DBTools.add2CdrQueueb(sms, "1"))
                            Logger.info(getClass().getName(), "{CDR write=yes}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=1}{RequestID=" + sms.getRequestId() + "}");
                        else
                            Logger.info(getClass().getName(), "{CDR write Err}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=1}{RequestID=" + sms.getRequestId() + "}");
                    } else
                    if((new StringBuffer(String.valueOf(sms.getMessageType()))).toString().startsWith("2"))
                        if(DBTools.add2CdrQueueb(sms, "0"))
                            Logger.info(getClass().getName(), "{CDR write=yes}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=0}{RequestID=" + sms.getRequestId() + "}");
                        else
                            Logger.info(getClass().getName(), "{CDR write Err}{User_ID=" + sms.getUserId() + "}{Service_ID=" + sms.getServiceId() + "}{MessageType=0}{RequestID=" + sms.getRequestId() + "}");
                }
            }

            PDU rsp;
            for(; resp.size() > 0; responseQueue.enqueue(rsp))
                rsp = (PDU)resp.dequeue();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Logger.error("Gateway", "Load queue files err: " + ex.getMessage());
        }
    }

    public static void exit()
    {
        running = false;
        if(bound || boundr)
            unbind();
        saveWait4ResponseTable();
        saveMO();
        Logger.info("Gateway", "Waiting all threads to die");
        for(int nLoop = 0; liveThreads.size() > 0 && nLoop <= 5;)
        {
            System.out.print(".");
            try
            {
                sleep(500L);
                nLoop++;
            }
            catch(InterruptedException interruptedexception) { }
        }

        Logger.info("Gateway.exit", "Gateway stopped.");
    }

    static Utilities util = new Utilities();
    public static LoadConfig loadconfig = null;
    static final String copyright = "Copyright (c) 2006-2008 by VietNamNet Media Group";
    static final String version = "SMPP Client, version date:11-09-2008";
    static String propsFilePath = "gateway.cfg";
    static BufferedReader keyboard;
    static Session session = null;
    static Session sessionr = null;
    static boolean bound = false;
    static boolean boundr = false;
    static boolean running = true;
    static boolean learning = false;
    static Queue responseQueue = null;
    private Queue deliveryQueue;
    static Queue requestQueue = null;
    static Queue EMSQueue = null;
    static Queue SendLogQueue = null;
    static Queue ResendQueue = null;
    static Map wait4ReportTable = new HashMap();
    static Map wait4ResponseTable = new HashMap();
    static Queue toSMSC = new Queue();
    static long startup_time = System.currentTimeMillis();
    static long bound_or_unbound_time = 0L;
    private PDUEventListener pduListener;
    private static Vector liveThreads = new Vector();
    private static Vector liveTelnetSessions = null;

    static 
    {
        Utilities.log("Gateway", "Copyright (c) 2006-2008 by VietNamNet Media Group");
        Utilities.log("Gateway", "SMPP Client, version date:11-09-2008");
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
