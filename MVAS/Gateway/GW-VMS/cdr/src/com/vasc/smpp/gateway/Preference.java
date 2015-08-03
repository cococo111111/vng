// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Preference.java

package com.vasc.smpp.gateway;

import java.io.*;
import java.util.*;

public class Preference
{

    public Preference()
    {
    }

    static byte getByteProperty(String propName, byte defaultValue)
    {
        return Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)).trim());
    }

    static int getIntProperty(String propName, int defaultValue)
    {
        return Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
    }

    public static boolean isValidServiceId(String serviceId)
    {
        if(serviceId == null)
            return false;
        if(serviceId.startsWith("+"))
            serviceId = serviceId.substring(1);
        for(int i = 0; i < validServiceIds.length; i++)
        {
            if(validServiceIds[i].equals(serviceId))
                return true;
        }

        return false;
    }

    public static boolean isValidServiceNumber(String serviceNum)
    {
        if(serviceNum == null)
            return false;
        if(serviceNum.startsWith("+"))
            serviceNum = serviceNum.substring(1);
        for(Iterator it = sourceAddressList.iterator(); it.hasNext();)
        {
            if(((String)it.next()).equals(serviceNum))
                return true;
        }

        return false;
    }

    public static void loadProperties(String fileName)
        throws IOException
    {
        System.out.println("Reading configuration file " + fileName + "...");
        FileInputStream propsFile = new FileInputStream(fileName);
        properties.load(propsFile);
        propsFile.close();
        System.out.println("Setting default parameters...");
        ipAddress = properties.getProperty("ip_address");
        port = getIntProperty("port", port);
        systemId = properties.getProperty("system_id");
        password = properties.getProperty("password");
        src_addr_ton = getByteProperty("source_addr_ton", src_addr_ton);
        src_addr_npi = getByteProperty("source_addr_npi", src_addr_ton);
        sourceAddressList = parseString(properties.getProperty("source_addr", ""));
        if(sourceAddressList != null && sourceAddressList.size() > 0)
            System.out.println("This gateway will process: " + sourceAddressList);
        else
            System.out.println("Warning: No source address is specified!");
        serviceType = properties.getProperty("service_type", serviceType);
        systemType = properties.getProperty("system_type", systemType);
        String strTemp = properties.getProperty("bind_mode", bindMode);
        if(!strTemp.equalsIgnoreCase("t") && !strTemp.equalsIgnoreCase("r") && !strTemp.equalsIgnoreCase("tr"))
        {
            System.out.println("Wrong value of bind_mode parameter in the configuration file " + fileName + "--> Setting the default: t");
            strTemp = "t";
        }
        bindMode = strTemp;
        String syncMode = properties.getProperty("sync_mode", asynchronous ? "async" : "sync");
        if(syncMode.equalsIgnoreCase("sync"))
            asynchronous = false;
        else
        if(syncMode.equalsIgnoreCase("async"))
            asynchronous = true;
        else
            asynchronous = false;
        int rcvTimeout = 0;
        rcvTimeout = getIntProperty("receive_timeout", rcvTimeout);
        db_DriverClassName = properties.getProperty("db_driver", db_DriverClassName);
        db_name = properties.getProperty("db_name", db_name);
        db_server = properties.getProperty("db_server", db_server);
        db_user = properties.getProperty("db_user", db_user);
        db_password = properties.getProperty("db_password", db_password);
        db_port = properties.getProperty("db_port", db_port);
        db_MaxConnections = getIntProperty("db_max_connections", db_MaxConnections);
        db_DriverClassName_alert = properties.getProperty("db_driver_alert", db_DriverClassName_alert);
        db_name_alert = properties.getProperty("db_name_alert", db_name_alert);
        db_server_alert = properties.getProperty("db_server_alert", db_server_alert);
        db_user_alert = properties.getProperty("db_user_alert", db_user_alert);
        db_password_alert = properties.getProperty("db_password_alert", db_password_alert);
        db_port_alert = properties.getProperty("db_port_alert", db_port_alert);
        db_MaxConnections_alert = getIntProperty("db_max_connections_alert", db_MaxConnections_alert);
        alert_person = properties.getProperty("alert_person", alert_person);
        alert_mobile = properties.getProperty("alert_mobile", alert_mobile);
        maxNumOfSend = getIntProperty("max_num_of_send", maxNumOfSend);
        logToFile = getIntProperty("log_to_file", logToFile);
        fileToLog = properties.getProperty("file_to_log", fileToLog);
        timeRebind = getIntProperty("time_rebind", timeRebind / 1000) * 1000;
        timeResend = getIntProperty("time_resend", timeResend / 1000) * 1000;
        timeEnquireLink = getIntProperty("time_enquire_link", timeEnquireLink / 1000) * 1000;
        maxSMPerSecond = getIntProperty("max_sm_per_sec", maxSMPerSecond);
        mobileOperator = properties.getProperty("mobile_operator", mobileOperator).toUpperCase();
        String sTemp = properties.getProperty("report_required", "0");
        if("1".equals(sTemp))
            reportRequired = true;
        sTemp = properties.getProperty("cdr_enabled", "0");
        if("1".equals(sTemp))
            cdrEnabled = true;
        cdroutFolder = properties.getProperty("cdrout_folder", cdroutFolder);
        cdrsentFolder = properties.getProperty("cdrsent_folder", cdrsentFolder);
        cdrfileExtension = properties.getProperty("cdrfile_extension", cdrfileExtension);
        receiveLogFolder = properties.getProperty("receive-log-folder", receiveLogFolder);
        sendLogFolder = properties.getProperty("send-log-folder", sendLogFolder);
        if(sourceAddressList != null && sourceAddressList.size() > 0)
        {
            for(Iterator it = sourceAddressList.iterator(); it.hasNext();)
            {
                String sNumber = (String)it.next();
                Collection cPrefixes = parseString(properties.getProperty("prefix_" + sNumber));
                if(cPrefixes != null && cPrefixes.size() > 0)
                    prefixMap.put(sNumber, cPrefixes);
                else
                    System.out.println("Warning: No prefix is specified for " + sNumber);
                String sMessage = properties.getProperty("message_" + sNumber);
                if(sMessage != null)
                    messageMap.put(sNumber, sMessage);
                else
                    System.out.println("Warning: No message is specified for " + sNumber);
            }

        }
    }

    public static void main(String args[])
    {
        Preference pre = new Preference();
        pre;
        loadProperties("gateway.cfg");
        break MISSING_BLOCK_LABEL_23;
        IOException ex;
        ex;
        return;
    }

    static Vector parseString(String text)
    {
        Vector prefixes = new Vector();
        if(text == null || "".equals(text))
            return prefixes;
        String tempStr = text.toUpperCase();
        String currentLabel = null;
        for(int index = tempStr.indexOf(","); index != -1; index = tempStr.indexOf(","))
        {
            currentLabel = tempStr.substring(0, index).trim();
            if(!"".equals(currentLabel))
                prefixes.addElement(currentLabel);
            tempStr = tempStr.substring(index + 1);
        }

        currentLabel = tempStr.trim();
        if(!"".equals(currentLabel))
            prefixes.addElement(currentLabel);
        return prefixes;
    }

    public static String alert_mobile = "84988087935";
    public static String alert_person = "Vu Quang Mai";
    public static boolean asynchronous = false;
    public static String bindMode = "tr";
    public static boolean cdrEnabled = false;
    public static String cdrfileExtension = ".cdr";
    public static String cdroutFolder = "./CDROUT";
    public static String cdrsentFolder = "./CDRSENT";
    public static byte dataCoding = 0;
    static String db_DriverClassName = "com.mysql.jdbc.Driver";
    static String db_DriverClassName_alert = "com.mysql.jdbc.Driver";
    static int db_MaxConnections = 3;
    static int db_MaxConnections_alert = 3;
    static String db_name = "votting_sfone";
    static String db_name_alert = "votting_sfone";
    static String db_password = "itrd";
    static String db_password_alert = "itrd";
    static String db_port = "3306";
    static String db_port_alert = "3306";
    static String db_server = "localhost";
    static String db_server_alert = "localhost";
    static String db_user = "root";
    static String db_user_alert = "root";
    private static byte dest_addr_npi = 0;
    private static byte dest_addr_ton = 0;
    public static byte esmClass = 0;
    public static String fileToLog = null;
    public static String ipAddress = null;
    public static int logToFile = 0;
    public static int maxNumOfSend = 0;
    public static int maxSMPerSecond = 1;
    public static String messageId = "";
    public static Map messageMap = new Hashtable();
    public static String mobileOperator = "";
    public static String password = null;
    public static int port = 0;
    public static Map prefixMap = new Hashtable();
    public static byte priorityFlag = 0;
    private static Properties properties = new Properties();
    public static byte protocolId = 0;
    public static String receiveLogFolder = "./LOG-R";
    public static long receiveTimeout = 0L;
    public static byte registeredDelivery = 0;
    public static byte replaceIfPresentFlag = 0;
    public static boolean reportRequired = false;
    public static String scheduleDeliveryTime = "";
    public static String sendLogFolder = "./LOG-S";
    public static String serviceType = "";
    public static String shortMessage = "";
    public static byte smDefaultMsgId = 0;
    public static Collection sourceAddressList = null;
    private static byte src_addr_npi = 0;
    private static byte src_addr_ton = 0;
    public static String systemId = null;
    public static String systemType = "";
    public static int timeEnquireLink = 0x11170;
    public static int timeRebind = 1000;
    public static int timeResend = 0x493e0;
    public static String validServiceIds[] = {
        "996", "997", "998", "19001255", "19001522", "19001799", "84996", "84997", "84998", "8419001255", 
        "8419001522", "8419001799", "04996", "04997", "04998", "0419001255", "0419001522", "0419001799"
    };
    public static String validityPeriod = "";

}
