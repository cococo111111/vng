// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Logger.java

package com.vasc.smpp.cdr;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.vasc.smpp.cdr:
//            DebugLogger

public class Logger
{

    public Logger()
    {
    }

    public static void close()
    {
        if(logWriter != null)
            logWriter.close();
    }

    public static void email(String eventType, String message)
    {
        String host;
        if(emailEvents == null || emailEvents.indexOf(eventType.toUpperCase()) == -1)
            return;
        host = "unknown_host";
        host = InetAddress.getLocalHost().getHostName();
        break MISSING_BLOCK_LABEL_44;
        Exception e;
        e;
        printStackTrace(e);
        String fromAddress = "pools@primrose.org.uk";
        String subject = eventType + " : " + poolName + "@" + host;
        info("Sending email for eventType(" + eventType + "), toAddress(" + toAddress + "), fromAddress(" + fromAddress + ") message(" + message + ")");
        return;
    }

    public static void error(String data)
    {
        if(logError)
            log("ERROR", data);
        if(DebugLogger.getEnabled())
            DebugLogger.log(data);
    }

    public int getLogDayOfMonth()
    {
        return logDayOfMonth;
    }

    public String getLogLevel()
    {
        return null;
    }

    public static void info(String classname, String data)
    {
        data = classname + "@" + data;
        if(logInfo)
            log("INFO", data);
        if(DebugLogger.getEnabled())
            DebugLogger.log(data);
    }

    public static void info(String data)
    {
        if(logInfo)
            log("INFO", data);
        if(DebugLogger.getEnabled())
            DebugLogger.log(data);
    }

    public static void info(byte data[])
    {
        if(logInfo)
            log("INFO", new String(data));
    }

    public static void linebreak()
    {
        if(logWriter == null)
            System.out.println("\n");
        else
            logWriter.println("\n");
    }

    private static void log(String level, String data)
    {
        setLogWriter(origLogName);
        break MISSING_BLOCK_LABEL_19;
        IOException ioe;
        ioe;
        ioe.printStackTrace();
        Calendar now = Calendar.getInstance();
        String nowString = now.get(5) + "/" + (now.get(2) + 1) + "/" + now.get(1) + " " + now.get(11) + ":" + now.get(12) + ":" + now.get(13);
        String message = nowString + " : " + level + ": " + data;
        if(logWriter == null)
            System.out.println(message);
        else
            logWriter.println(message);
        return;
    }

    public static void printStackTrace(Throwable t)
    {
        if(logWriter != null)
            t.printStackTrace(logWriter);
        else
            t.printStackTrace(System.err);
        if(DebugLogger.getEnabled())
            DebugLogger.printStackTrace(t);
    }

    public static void setEmailDetails(String s, String s1, String s2, String s3)
    {
    }

    public void setLogDayOfMonth(int logDayOfMonth)
    {
        Logger _tmp = this;
        logDayOfMonth = logDayOfMonth;
    }

    public static void setLogLevel(String level)
    {
        if(level != null && level.length() > 0)
        {
            String levels[] = level.split(",");
            for(int i = 0; i < levels.length; i++)
            {
                if(levels[i].equalsIgnoreCase("verbose"))
                    logVerbose = true;
                else
                if(levels[i].equalsIgnoreCase("info"))
                    logInfo = true;
                else
                if(levels[i].equalsIgnoreCase("warn"))
                    logWarn = true;
                else
                if(levels[i].equalsIgnoreCase("error"))
                    logError = true;
                else
                if(levels[i].equalsIgnoreCase("crisis"))
                    logCrisis = true;
                else
                if(levels[i].equalsIgnoreCase("debug"))
                    DebugLogger.setEnabled(true);
            }

        }
    }

    public static void setLogWriter(String log)
        throws IOException
    {
        if(origLogName == null)
            origLogName = log;
        Calendar cal = Calendar.getInstance();
        int localLogDayOfMonth = cal.get(5);
        boolean makeNewLog = false;
        if(logDayOfMonth == -1 && logWriter == null)
            makeNewLog = true;
        if(origLogName.indexOf("${") > -1 && localLogDayOfMonth != logDayOfMonth)
        {
            String dateFormat = origLogName.substring(origLogName.indexOf("${") + 2, origLogName.indexOf("}"));
            String logPrefix = origLogName.substring(0, origLogName.indexOf("${"));
            String logSuffix = origLogName.substring(origLogName.indexOf("}") + 1, origLogName.length());
            SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat);
            Date tmp = new Date();
            String formattedDate = sdf2.format(tmp);
            log = logPrefix + formattedDate + logSuffix;
            logDayOfMonth = localLogDayOfMonth;
            makeNewLog = true;
        }
        if(makeNewLog)
        {
            if(DebugLogger.getEnabled())
                DebugLogger.log("[Logger@" + poolName + "] Making new log (" + log + ")");
            if(logWriter != null)
                logWriter.close();
            logWriter = new PrintWriter(new FileOutputStream(log, true), true);
        }
    }

    public static void verbose(String data)
    {
        if(logVerbose)
            log("VERBOSE", data);
        if(DebugLogger.getEnabled())
            DebugLogger.log(data);
    }

    public static void warn(String data)
    {
        if(logWarn)
            log("WARN", data);
        if(DebugLogger.getEnabled())
            DebugLogger.log(data);
    }

    private static String emailEvents = null;
    private static boolean logCrisis = false;
    private static int logDayOfMonth = -1;
    private static boolean logError = false;
    private static boolean logInfo = false;
    private static boolean logVerbose = false;
    private static boolean logWarn = false;
    private static PrintWriter logWriter = null;
    private static String mxServer = null;
    private static String origLogName = null;
    private static String poolName = null;
    private static String toAddress = null;

}
