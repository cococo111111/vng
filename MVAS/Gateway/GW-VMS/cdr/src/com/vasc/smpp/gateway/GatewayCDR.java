// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GatewayCDR.java

package com.vasc.smpp.gateway;

import com.vasc.common.Utilities;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

// Referenced classes of package com.vasc.smpp.gateway:
//            MsgQueue, Preference

public class GatewayCDR
{

    public GatewayCDR()
    {
        dbPool = new MsgQueue();
        util = new Utilities();
    }

    public static void addMoreConnection2Pool(int number)
    {
        int i;
        util;
        Utilities.log("Connecting to database.....");
        i = 0;
          goto _L1
_L3:
        Connection conn = null;
        conn = util.getDBConnectionMySQL(Preference.db_DriverClassName, Preference.db_server, Preference.db_name, Preference.db_user, Preference.db_password, Preference.db_port);
        break MISSING_BLOCK_LABEL_92;
        SQLException ex;
        ex;
        util;
        Utilities.log("Error: " + ex.getMessage());
        util;
        Utilities.log("Khong noi dc voi database roi, xem lai di.!!!!!!!!!!!");
        System.exit(1);
        if(conn != null)
        {
            dbPool.add(conn);
            System.out.print("connect to database successful !\n");
        }
        i++;
_L1:
        if(i < number) goto _L3; else goto _L2
_L2:
    }

    public static void closeAllConnectionInPool()
    {
        Utilities _tmp = util;
        Utilities.log("Closing database connection...");
        int size = (int)dbPool.getSize();
        for(int i = 0; i < size; i++)
        {
            Connection conn = (Connection)dbPool.remove();
            try
            {
                if(conn != null)
                    conn.close();
            }
            catch(SQLException ex) { }
        }

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

    public static boolean isRunning()
    {
        return running;
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
        conn = util.getDBConnectionMySQL(Preference.db_DriverClassName, Preference.db_server, Preference.db_name, Preference.db_user, Preference.db_password, Preference.db_port);
        ok = true;
        continue; /* Loop/switch isn't completed */
        SQLException e;
        e;
        util;
        Utilities.log("Get DB Connection FAILT. Try later in " + Preference.timeRebind / 1000 + " seconds");
        Thread.sleep(Preference.timeRebind);
        break MISSING_BLOCK_LABEL_95;
        InterruptedException ie;
        ie;
_L1:
        if(!ok) goto _L3; else goto _L2
_L2:
        if(conn != null)
            dbPool.add(conn);
        addMoreConnection2Pool(number - 1);
        return;
    }

    static boolean bound = false;
    static long bound_or_unbound_time = 0L;
    static boolean boundr = false;
    static final String copyright = "Copyright \251 2008 by IT R&D -  VMG ";
    static MsgQueue dbPool = null;
    static BufferedReader keyboard;
    static long last_received_time = 0L;
    static String propsFilePath = "gateway.cfg";
    public static boolean running = true;
    static long startup_time = System.currentTimeMillis();
    public static Utilities util = null;
    static final String version = "Billing System, version 1.0\n";

    static 
    {
        System.out.println();
        System.out.println("Copyright \251 2008 by IT R&D -  VMG ");
        System.out.println("Billing System, version 1.0\n");
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
