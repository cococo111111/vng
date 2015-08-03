// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Preference.java

package com.vmg.smpp.gateway;

import java.io.*;
import java.util.*;
import org.smpp.pdu.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger, ConfigGW

public class Preference
{

    public Preference()
    {
    }

    public static boolean isValidServiceId(String serviceId)
    {
        if(serviceId == null)
            return false;
        if(serviceId.startsWith("+"))
            serviceId = serviceId.substring(1);
        if(serviceId.length() > 2)
            return true;
        return serviceId.startsWith("998") && serviceId.length() == 10;
    }

    public static boolean CheckServiceId(String serviceId)
    {
        if(serviceId == null || "".equalsIgnoreCase(serviceId))
            return false;
        int i = 0;
          goto _L1
_L3:
        if(VALID_SERVICEID[i].endsWith(serviceId))
            return true;
        i++;
_L1:
        if(i < VALID_SERVICEID.length) goto _L3; else goto _L2
_L2:
        return false;
        Exception exception;
        exception;
        return false;
    }

    public static void loadProperties(String fileName)
        throws IOException
    {
        Logger.info("Reading configuration file " + fileName + "...");
        FileInputStream propsFile = new FileInputStream(fileName);
        properties.load(propsFile);
        propsFile.close();
        try
        {
            String gateway_name = properties.getProperty("gateway_name", "");
            if("".equalsIgnoreCase(gateway_name))
            {
                Logger.info("gateway name is null --> reading configuration file");
            } else
            {
                Logger.info("retrieveConfigGW from DB :" + gateway_name);
                preference = ConfigGW.retrieveConfigGW(gateway_name);
            }
        }
        catch(Exception e1)
        {
            Logger.info("retrieveConfigGW " + e1.toString() + "...");
            e1.printStackTrace();
        }
        Alert_YM = getIntProperty("alert_ym", 1);
        Logger.info("Setting parameters...");
        nTheardMO = getIntProperty("num_thread_mo", 1);
        nTheardMT = getIntProperty("num_thread_mt", 1);
        nTheardResp = getIntProperty("num_thread_respond", 1);
        nLogQueue = getIntProperty("log_queue", 1);
        WriteLog = getIntProperty("write_log", 1);
        ViewConsole = getIntProperty("view_console", 1);
        nAddNewTheardMO = getIntProperty("add_thread_mo", 0);
        nAddNewTheardMT = getIntProperty("add_thread_mt", 0);
        nAddNewTheardResp = getIntProperty("add_thread_respond", 0);
        nAddNewBuildEMS = getIntProperty("add_build_ems", 0);
        nAddNewSendLog = getIntProperty("add_thread_send_log", 0);
        nAddNewMOSim = getIntProperty("add_thread_mo_sim", 0);
        nTheardMOSim = getIntProperty("num_thread_mo_sim", 1);
        nBuildEMS = getIntProperty("num_build_ems", 1);
        nGetMTfromDB = getIntProperty("num_get_mt_from_db", 1);
        nSendLog = getIntProperty("num_thread_send_log", 1);
        ipAddress = getStringProperty("ip_address");
        port = getIntProperty("port", port);
        systemId = getStringProperty("system_id");
        password = getStringProperty("password");
        ketqua = getStringProperty("ketqua");
        Channel = getStringProperty("channel");
        byte ton = getByteProperty("addr_ton", addressRange.getTon());
        byte npi = getByteProperty("addr_npi", addressRange.getNpi());
        String addr = getStringProperty("addr_range", addressRange.getAddressRange());
        addressRange.setTon(ton);
        addressRange.setNpi(npi);
        try
        {
            addressRange.setAddressRange(addr);
        }
        catch(WrongLengthOfStringException e)
        {
            System.out.println("The length of address_range parameter is wrong.");
        }
        src_addr_ton = getByteProperty("source_addr_ton", src_addr_ton);
        src_addr_npi = getByteProperty("source_addr_npi", src_addr_npi);
        sourceAddressList = parseString(properties.getProperty("source_addr", ""));
        if(sourceAddressList != null && sourceAddressList.size() > 0)
            Logger.info("This gateway will process: " + sourceAddressList);
        else
            Logger.info("Warning: No source address is specified!");
        dest_addr_ton = getByteProperty("dest_addr_ton", dest_addr_ton);
        dest_addr_npi = getByteProperty("dest_addr_npi", dest_addr_npi);
        addr = getStringProperty("destination_addr", "");
        serviceType = getStringProperty("service_type", serviceType);
        systemType = getStringProperty("system_type", systemType);
        String strTemp = getStringProperty("bind_mode", bindMode);
        if(!strTemp.equalsIgnoreCase("t") && !strTemp.equalsIgnoreCase("r") && !strTemp.equalsIgnoreCase("tr"))
        {
            Logger.info("Wrong value of bind_mode parameter in the configuration file " + fileName + "--> Setting the default: t");
            strTemp = "t";
        }
        bindMode = strTemp;
        ketqua = null;
        bLottery = false;
        bLog = false;
        String syncMode = getStringProperty("sync_mode", asynchronous ? "async" : "sync");
        if(syncMode.equalsIgnoreCase("sync"))
            asynchronous = false;
        else
        if(syncMode.equalsIgnoreCase("async"))
            asynchronous = true;
        else
            asynchronous = false;
        int rcvTimeout = 0;
        rcvTimeout = getIntProperty("receive_timeout", rcvTimeout);
        if(rcvTimeout == -1)
            receiveTimeout = -1L;
        else
            receiveTimeout = rcvTimeout * 1000;
        PayLoadUDH = Byte.toString((byte)6) + Byte.toString((byte)5) + Byte.toString((byte)4) + Byte.toString((byte)19) + Byte.toString((byte)-120) + Byte.toString((byte)0) + Byte.toString((byte)0);
        NumOfRetries = getIntProperty("num_retries", NumOfRetries);
        RetriesTime = getIntProperty("retries_time", RetriesTime);
        maxNumOfSend = getIntProperty("max_num_of_send", maxNumOfSend);
        logToFile = getIntProperty("log_to_file", logToFile);
        logToConsole = getIntProperty("log_to_console", logToConsole);
        logToFolder = getStringProperty("log_to_folder", logToFolder);
        MODatafile = getStringProperty("file_mo_data", MODatafile);
        logFullMessage = getIntProperty("log_full_message", logFullMessage);
        telnetServerPort = getIntProperty("telnet_server_port", telnetServerPort);
        telnetAllowedIp = parseIPaddresses(properties.getProperty("telnet_allowed_ip", ""));
        timeRebind = getIntProperty("time_rebind", timeRebind / 1000) * 1000;
        timeResend = getIntProperty("time_resend", timeResend);
        timeOut = getIntProperty("time_out", timeResend);
        timeEnquireLink = getIntProperty("time_enquire_link", timeEnquireLink / 1000) * 1000;
        maxSMPerSecond = getIntProperty("max_sm_per_sec", maxSMPerSecond);
        mobileOperator = getStringProperty("mobile_operator", mobileOperator).toUpperCase();
        String sTemp = getStringProperty("report_required", "0");
        if("1".equals(sTemp))
            reportRequired = true;
        sTemp = getStringProperty("cdr_enabled", "0");
        if("1".equals(sTemp))
            cdrEnabled = true;
        cdroutFolder = getStringProperty("cdrout_folder", cdroutFolder);
        cdrsentFolder = getStringProperty("cdrsent_folder", cdrsentFolder);
        LogFolder = getStringProperty("log_path", LogFolder);
        cdrfileExtension = getStringProperty("cdrfile_extension", cdrfileExtension);
        receiveLogFolder = getStringProperty("receive-log-folder", receiveLogFolder);
        sendLogFolder = getStringProperty("send-log-folder", sendLogFolder);
        VIETTEL_MODE = getStringProperty("vt-mode", VIETTEL_MODE);
        SEND_MODE = getStringProperty("send_mode", SEND_MODE);
        prefix_requestid = getStringProperty("prefix_requestid", prefix_requestid);
    }

    public static Address buildSrcAddress(String srcAddr)
    {
        Address address;
        address = new Address();
        address.setTon(src_addr_ton);
        address.setNpi(src_addr_npi);
        address.setAddress(srcAddr);
        return address;
        WrongLengthOfStringException e;
        e;
        Logger.info("The length of dest_addr parameter is wrong: " + srcAddr);
        return null;
    }

    public static Address buildDestAddress(String destAddr)
    {
        Address address;
        address = new Address();
        address.setTon(dest_addr_ton);
        address.setNpi(dest_addr_npi);
        address.setAddress(destAddr);
        return address;
        WrongLengthOfStringException e;
        e;
        Logger.info("The length of dest_addr parameter is wrong: " + destAddr);
        return null;
    }

    static byte getByteProperty(String propName, byte defaultValue)
    {
        String temp = getConfigValue(propName);
        byte result = defaultValue;
        if(temp != null)
        {
            result = Byte.parseByte(temp);
            Logger.info("{DB}-" + propName + ":" + result);
        } else
        {
            result = Byte.parseByte(properties.getProperty(propName, Byte.toString(defaultValue)).trim());
            Logger.info("{FILE}-" + propName + ":" + result);
        }
        return result;
    }

    static int getIntProperty(String propName, int defaultValue)
    {
        String temp = getConfigValue(propName);
        int result = defaultValue;
        if(temp != null)
        {
            result = Integer.parseInt(temp);
            Logger.info("{DB}-" + propName + ":" + result);
        } else
        {
            result = Integer.parseInt(properties.getProperty(propName, Integer.toString(defaultValue)).trim());
            Logger.info("{FILE}-" + propName + ":" + result);
        }
        return result;
    }

    static String getStringProperty(String propName)
    {
        String temp = getConfigValue(propName);
        String result = "";
        if(temp != null)
        {
            result = temp;
            Logger.info("{DB}-" + propName + ":" + result);
        } else
        {
            result = properties.getProperty(propName);
            Logger.info("{FILE}-" + propName + ":" + result);
        }
        return result;
    }

    static String getStringProperty(String propName, String defaultval)
    {
        String temp = getConfigValue(propName);
        String result = "";
        if(temp != null)
        {
            result = temp;
            Logger.info("{DB}-" + propName + ":" + result);
        } else
        {
            result = properties.getProperty(propName, defaultval);
            Logger.info("{FILE}-" + propName + ":" + result);
        }
        return result;
    }

    static Collection parseString(String text)
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

    static Collection parseIPaddresses(String text)
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

    public static void main(String args[])
    {
        String sInfo = "~1~aaaa";
        String arrInfo[] = sInfo.split("~");
        System.out.println("sinfoleng:" + arrInfo.length);
        System.out.println("sinfoleng:" + arrInfo[1]);
        System.out.println("sinfoleng:" + arrInfo[2]);
        try
        {
            Logger.setLogWriter("testlogger");
            Logger.setLogLevel("info,warn,error,crisis");
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        Logger.info("aaaaa");
    }

    public static String getConfigValue(String configname)
    {
        ConfigGW retobj = new ConfigGW();
        if(preference == null)
            return null;
        Enumeration keys = preference.keys();
        String keytosearch = configname;
        while(keys.hasMoreElements()) 
        {
            String key = (String)keys.nextElement();
            if(keytosearch.equalsIgnoreCase(key))
            {
                retobj = (ConfigGW)preference.get(key);
                return retobj.getConfigvalue();
            }
        }
        return null;
    }

    public static String formatUserId(String userId, int formatType)
    {
        if(userId == null || "".equals(userId))
            return null;
        String temp = userId;
        switch(formatType)
        {
        case 0: // '\0'
            if(temp.startsWith("9"))
            {
                temp = "84" + temp;
                break;
            }
            if(temp.startsWith("09"))
            {
                temp = "84" + temp.substring(1);
                break;
            }
            if(temp.startsWith("01"))
            {
                temp = "84" + temp.substring(1);
                break;
            }
            if(temp.startsWith("1"))
                temp = "84" + temp;
            break;

        case 2: // '\002'
            if(temp.startsWith("84"))
            {
                temp = temp.substring(2);
                break;
            }
            if(temp.startsWith("0"))
                temp = temp.substring(1);
            break;

        case 1: // '\001'
            if(temp.startsWith("84"))
            {
                temp = "0" + temp.substring(2);
                break;
            }
            if(temp.startsWith("9") || temp.startsWith("1"))
                temp = "0" + temp;
            break;

        default:
            System.out.println("formatUserId: Invalid userId format_type " + formatType);
            return temp;
        }
        return temp;
    }

    public static String formatUserIdMO(String userId, int formatType)
    {
        if(userId == null || "".equals(userId))
            return null;
        String temp = userId;
        if("0".equalsIgnoreCase(REBUILD_USERID))
            return userId;
        switch(formatType)
        {
        case 0: // '\0'
            if(temp.startsWith("9"))
            {
                temp = "84" + temp;
                break;
            }
            if(temp.startsWith("09"))
            {
                temp = "84" + temp.substring(1);
                break;
            }
            if(temp.startsWith("01"))
            {
                temp = "84" + temp.substring(1);
                break;
            }
            if(temp.startsWith("1"))
                temp = "84" + temp;
            break;

        case 2: // '\002'
            if(temp.startsWith("84"))
            {
                temp = temp.substring(2);
                break;
            }
            if(temp.startsWith("0"))
                temp = temp.substring(1);
            break;

        case 1: // '\001'
            if(temp.startsWith("84"))
            {
                temp = "0" + temp.substring(2);
                break;
            }
            if(temp.startsWith("9") || temp.startsWith("1"))
                temp = "0" + temp;
            break;

        default:
            System.out.println("formatUserId: Invalid userId format_type " + formatType);
            return temp;
        }
        return temp;
    }

    private static Hashtable preference = null;
    public static boolean bLottery = false;
    public static String PreRequestID = "0";
    public static int NumOfRetries = 1;
    public static int RetriesTime = 3;
    public static boolean bLog = false;
    public static int Alert_YM = 1;
    public static int nTheardMO = 1;
    public static int nTheardMT = 1;
    public static int nTheardResp = 1;
    public static int nTheardMOSim = 1;
    public static int nAddNewTheardMO = 0;
    public static int nAddNewTheardMT = 0;
    public static int nAddNewTheardResp = 0;
    public static int nAddNewBuildEMS = 0;
    public static int nAddNewSendLog = 0;
    public static int nAddNewMOSim = 0;
    public static int nBuildEMS = 1;
    public static int nGetMTfromDB = 1;
    public static int nSendLog = 1;
    public static int n = 1;
    public static int nLogQueue = 1;
    public static int WriteLog = 1;
    public static int ViewConsole = 1;
    public static String ipAddress = null;
    public static int port = 0;
    public static String systemId = null;
    public static String password = null;
    public static String ketqua = null;
    public static String Channel = null;
    public static String mobileOperator = "";
    public static String bindMode = "tr";
    public static boolean asynchronous = false;
    public static Collection sourceAddressList = null;
    public static String validServiceIds[] = {
        "6069", "6169", "6269", "6369", "6469", "6769", "6869", "6969"
    };
    public static String VALID_SERVICEID[] = {
        "6069", "6169", "6269", "6369", "6469", "6569", "6669", "6769", "6869", "6969"
    };
    public static String SERVICEID_6x69[] = {
        "6069", "6169", "6269", "6369", "6469", "6569", "6669", "6769", "6869", "6969"
    };
    public static String SERVICEID_8x42[] = {
        "8042", "8142", "8242", "8342", "8442", "8542", "8642", "8742"
    };
    public static String SERVICEID_9x99[] = {
        "9099", "9199", "9299", "9399", "9499", "9599", "9699", "9799"
    };
    public static String systemType = "";
    public static String serviceType = "";
    public static String scheduleDeliveryTime = "";
    public static String validityPeriod = "";
    public static String shortMessage = "";
    public static String messageId = "";
    public static byte esmClass = 0;
    public static byte protocolId = 0;
    public static byte priorityFlag = 0;
    public static byte registeredDelivery = 0;
    public static byte replaceIfPresentFlag = 0;
    public static byte dataCoding = 0;
    public static byte smDefaultMsgId = 0;
    public static AddressRange addressRange = new AddressRange();
    public static long receiveTimeout = 0L;
    private static byte src_addr_ton = 0;
    private static byte src_addr_npi = 0;
    private static byte dest_addr_ton = 0;
    private static byte dest_addr_npi = 0;
    public static String PayLoadUDH = null;
    static String db_DriverClassName = "oracle.jdbc.driver.OracleDriver";
    static String db_URL = "jdbc:oracle:oci8:@ORA";
    static String db_user = "smpp";
    static String db_password = "smpp2003";
    static int db_MaxConnections = 3;
    static String db_DriverClassName_Service = "oracle.jdbc.driver.OracleDriver";
    static String db_URL_Service = "jdbc:oracle:oci8:@ORA";
    static String db_user_Service = "smpp";
    static String db_password_Service = "smpp2003";
    static int db_MaxConnections_Service = 1;
    static String db_DriverClassName_Inv = "oracle.jdbc.driver.OracleDriver";
    static String db_URL_Inv = "jdbc:oracle:oci8:@ORA";
    static String db_user_Inv = "smpp";
    static String db_password_Inv = "smpp2003";
    static int db_MaxConnections_Inv = 1;
    public static int maxNumOfSend = 0;
    public static int logToFile = 0;
    public static int logToConsole = 0;
    public static String logToFolder = ".\\LOG-G";
    public static int logFullMessage = 0;
    public static String MODatafile = "MOData.dat";
    public static int commandFromLocal = 0;
    public static int commandFromRemote = 0;
    public static int telnetServerPort = 7002;
    public static Collection telnetAllowedIp = null;
    public static int timeRebind = 1000;
    public static int timeResend = 3;
    public static int timeOut = 2;
    public static int timeEnquireLink = 0x11170;
    public static int maxSMPerSecond = 1;
    public static boolean reportRequired = false;
    public static boolean cdrEnabled = false;
    public static String cdroutFolder = ".\\CDROUT";
    public static String cdrsentFolder = ".\\CDRSENT";
    public static String cdrfileExtension = ".bil";
    public static String LogFolder = "./";
    public static String receiveLogFolder = ".\\LOG-R";
    public static String sendLogFolder = ".\\LOG-S";
    public static String VIETTEL_MODE = "tr";
    public static String SEND_MODE = "";
    public static String prefix_requestid = "20";
    public static String MAPCP = "1";
    public static String REBUILD_USERID = "1";
    public static String REFUND_ACTIVE = "0";
    private static Properties properties = new Properties();

}
