// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsoleSRV.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package com.vmg.sms.process:
//            MsgQueue, LoadMO, ExecuteQueue, ExecuteInsertReceiveLog, 
//            Constants, Logger, DBPool, LoadConfig, 
//            MsgObject, ShutdownInterceptor

public class ConsoleSRV extends Thread
{

    public ConsoleSRV()
    {
        queue = new MsgQueue();
        queueLog = new MsgQueue();
        loadMO = new LoadMO[2];
        executequeue = new ExecuteQueue[20];
        insert_receive_log = new ExecuteInsertReceiveLog[1];
        Constants.loadProperties("config.cfg");
        executequeue = new ExecuteQueue[Constants.NUM_THREAD];
        loadMO = new LoadMO[Constants.NUM_THREAD_LOAD_MO];
        insert_receive_log = new ExecuteInsertReceiveLog[Constants.NUM_THREAD_INSERTLOG];
        try
        {
            logger = new Logger();
            try
            {
                logger.setLogWriter(Constants.LOGPATH + Constants.LOGFILE);
            }
            catch(IOException ioexception) { }
            logger.setLogLevel(Constants.LOGLEVEL);
            logger.info("Start :" + VERSION);
            util = new Util(Constants.LOGPATH + Constants.LOGFILE, Constants.LOGLEVEL);
            Init();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Init()
        throws Exception
    {
        dbpool = new DBPool();
        DBPool.ConfigDB();
        loadSMSDataTable("data.dat", queue);
        loadSMSDataTable("receivelog.dat", queueLog);
        loadconfig = new LoadConfig();
        loadconfig.setPriority(10);
        loadconfig.start();
        for(int i = 0; i < loadMO.length; i++)
        {
            loadMO[i] = new LoadMO(queue, loadMO.length, i);
            loadMO[i].setPriority(10);
            loadMO[i].start();
        }

        for(int i = 0; i < executequeue.length; i++)
        {
            executequeue[i] = new ExecuteQueue(queue, queueLog, i);
            executequeue[i].setPriority(10);
            executequeue[i].start();
        }

        for(int i = 0; i < insert_receive_log.length; i++)
        {
            insert_receive_log[i] = new ExecuteInsertReceiveLog(queueLog);
            insert_receive_log[i].setPriority(5);
            insert_receive_log[i].start();
        }

    }

    public void windowClosing()
    {
        int nCount = 0;
        getData = false;
        processData = false;
        System.out.print("\nWaiting .....");
        try
        {
            Thread.sleep(1000L);
        }
        catch(InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        while(queue.getSize() > 0L && nCount < 5) 
        {
            nCount++;
            try
            {
                System.out.println("...Queue(" + queue.getSize() + ")");
                Thread.sleep(1000L);
            }
            catch(InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
        }
        saveSMSDataTable("data.dat", queue);
        saveSMSDataTable("receivelog.dat", queueLog);
        Util.logger.info("Shutdown");
        System.out.print("\nExit");
    }

    public static void loadSMSDataTable(String fileName, MsgQueue queue)
    {
        boolean flag = true;
        FileInputStream fin = null;
        ObjectInputStream objIn = null;
        FileOutputStream fout = null;
        ObjectOutputStream objOut = null;
        Util.logger.info("loadSMSDataTable:" + fileName);
        long nummo = 0L;
        try
        {
            fin = new FileInputStream(fileName);
            objIn = new ObjectInputStream(fin);
            while(flag) 
                try
                {
                    MsgObject object = (MsgObject)objIn.readObject();
                    queue.add(object);
                    nummo++;
                }
                catch(Exception ex)
                {
                    flag = false;
                }
            if(nummo == 0L)
                Util.logger.info(fileName + " is empty");
            else
                Util.logger.info("Load data successful: " + nummo + " MO");
        }
        catch(IOException ex)
        {
            Util.logger.error("Load data error: " + ex.getMessage());
        }
        finally
        {
            try
            {
                fin.close();
                fout = new FileOutputStream(fileName, false);
                fout.close();
                Util.logger.info("Deleting.....: " + fileName);
            }
            catch(Exception exception1) { }
        }
        return;
    }

    public static void saveSMSDataTable(String fileName, MsgQueue queue)
    {
        Util.logger.info("Saving " + fileName + " . . .");
        FileOutputStream fout = null;
        ObjectOutputStream objOut = null;
        long numqueue = 0L;
        try
        {
            fout = new FileOutputStream(fileName, false);
            objOut = new ObjectOutputStream(fout);
            for(Enumeration e = queue.getVector().elements(); e.hasMoreElements();)
            {
                MsgObject object = (MsgObject)e.nextElement();
                objOut.writeObject(object);
                objOut.flush();
                numqueue++;
            }

            Util.logger.info("complete:" + numqueue);
        }
        catch(IOException ex)
        {
            Util.logger.error("Save data error: " + ex.getMessage());
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

    public static void main(String args[])
    {
        System.out.println("Starting ProcessServer - version " + VERSION);
        System.out.println("Copyright 2006-2008 VinaGame - All Rights Reserved.");
        ConsoleSRV smsConsole = new ConsoleSRV();
        ShutdownInterceptor shutdownInterceptor = new ShutdownInterceptor(smsConsole);
        Runtime.getRuntime().addShutdownHook(shutdownInterceptor);
        smsConsole.start();
    }

    public void run()
    {
        Util.logger.info("Version " + VERSION);
        if(Constants.RUNCLASS != null)
        {
            for(int i = 0; i < Constants.RUNCLASS.length; i++)
                runthread(Constants.RUNCLASS[i]);

        }
        System.out.println("Version " + VERSION);
    }

    private void runthread(String classname)
    {
        try
        {
            Class delegateClass = Class.forName(classname);
            Util.logger.info("{runthread}{Start:" + classname + "}");
            Object delegateObject = null;
            try
            {
                delegateObject = delegateClass.newInstance();
            }
            catch(InstantiationException e)
            {
                Util.logger.error(e.toString());
                e.printStackTrace();
            }
            catch(IllegalAccessException e)
            {
                Util.logger.error(e.toString());
                e.printStackTrace();
            }
            Thread delegate = (Thread)delegateObject;
            delegate.start();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            Util.logger.error(e.toString());
        }
    }

    public static boolean getData = true;
    public static boolean processData = true;
    public static DBPool dbpool = null;
    public static LoadConfig loadconfig = null;
    public static Util util = new Util();
    MsgQueue queue;
    MsgQueue queueLog;
    LoadMO loadMO[];
    public static Logger logger = null;
    ExecuteQueue executequeue[];
    ExecuteInsertReceiveLog insert_receive_log[];
    static String VERSION = "2008.08.05.10.18";

}
