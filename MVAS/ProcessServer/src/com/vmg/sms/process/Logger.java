// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Logger.java

package com.vmg.sms.process;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger
{

    public Logger()
    {
        logWriter = null;
        logVerbose = false;
        logInfo = false;
        logWarn = false;
        logError = false;
        logCrisis = false;
        logDayOfMonth = -1;
        origLogName = null;
        emailEvents = null;
        mxServer = null;
        toAddress = null;
        poolName = null;
    }

    public void setLogWriter(String log)
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
            if(logWriter != null)
                logWriter.close();
            logWriter = new PrintWriter(new FileOutputStream(log, true), true);
        }
    }

    public void setEmailDetails(String emailEvents, String toAddress, String mxServer, String poolName)
    {
        if(emailEvents != null && emailEvents.length() > 0 && !emailEvents.equals("null"))
        {
            this.emailEvents = emailEvents.toUpperCase();
            this.emailEvents += ",CRISIS";
            this.toAddress = toAddress;
            this.mxServer = mxServer;
            this.poolName = poolName;
        }
    }

    public void setLogLevel(String level)
    {
        if(level != null && level.length() > 0)
        {
            String levels[] = level.split(",");
            for(int i = 0; i < levels.length; i++)
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

        }
    }

    public void printStackTrace(Throwable t)
    {
        if(logWriter != null)
            t.printStackTrace(logWriter);
        else
            t.printStackTrace(System.err);
    }

    public void close()
    {
        if(logWriter != null)
            logWriter.close();
    }

    public void verbose(String data)
    {
        if(logVerbose)
            log("VERBOSE", data);
    }

    public void info(byte data[])
    {
        if(logInfo)
            log("INFO", new String(data));
    }

    public void info(String data)
    {
        if(logInfo)
            log("INFO", data);
    }

    public void warn(String data)
    {
        if(logWarn)
            log("WARN", data);
    }

    public void error(String data)
    {
        if(logError)
        {
            System.out.println(data);
            log("ERROR", data);
        }
    }

    public void print(String data)
    {
        System.out.println(data);
    }

    public void email(String s, String s1)
    {
    }

    public void crisis(String message)
    {
        if(logCrisis)
        {
            System.out.println(message);
            log("CRISIS", message);
        }
        if(emailEvents == null)
        {
            return;
        } else
        {
            email("CRISIS", message);
            return;
        }
    }

    private void log(String level, String data)
    {
        try
        {
            setLogWriter(origLogName);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        Calendar now = Calendar.getInstance();
        String nowString = now.get(5) + "/" + (now.get(2) + 1) + "/" + now.get(1) + " " + now.get(11) + ":" + now.get(12) + ":" + now.get(13);
        String message = nowString + " : " + level + ": " + data;
        if(logWriter == null)
            System.out.println(message);
        else
            logWriter.println(message);
    }

    public void linebreak()
    {
        if(logWriter == null)
            System.out.println("\n");
        else
            logWriter.println("\n");
    }

    public String getLogLevel()
    {
        return null;
    }

    public int getLogDayOfMonth()
    {
        return logDayOfMonth;
    }

    public void setLogDayOfMonth(int logDayOfMonth)
    {
        this.logDayOfMonth = logDayOfMonth;
    }

    private PrintWriter logWriter;
    private boolean logVerbose;
    private boolean logInfo;
    private boolean logWarn;
    private boolean logError;
    private boolean logCrisis;
    private int logDayOfMonth;
    private String origLogName;
    private String emailEvents;
    private String mxServer;
    private String toAddress;
    private String poolName;
}
