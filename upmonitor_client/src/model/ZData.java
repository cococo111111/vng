/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import com.vng.jcore.common.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author locth2
 */
public class ZData {

    private String hostname;
    private String appname;
    private List<InetAddress> ipAddresses;
    private String desc;
    public byte[] data;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZData.class);

    public ZData() {
        JSONObject objData = new JSONObject();
        objData.put("appname", getAppname());
        objData.put("servername", getHostname());
        objData.put("serverip", getIpAddresses());
        objData.put("description", getDesc());
        objData.put("zme_contacts", getZmeContacts());
        objData.put("sms_contacts", getSmsContacts());
        objData.put("mail_contacts", getMailContacts());
        objData.put("service_dependencies", getServiceDependencies());
        objData.put("extras", getExtras());
        objData.put("properties", getProperties());
        objData.put("configuration", getConfiguration());
        objData.put("url_live", getUrlLive());
        objData.put("start_time", getStartTime());
        this.data = objData.toJSONString().getBytes();
    }

    private String getExtras() {
        return Config.getParam("upmonitor", "extra");
    }

    private String getUrlLive() {
        return Config.getParam("upmonitor", "url_live");
    }

    private String getServiceDependencies() {
        return Config.getParam("upmonitor", "service_dependencies");
    }

    private String getZmeContacts() {
        return Config.getParam("upmonitor", "zme_contacts");

    }

    private String getSmsContacts() {
        return Config.getParam("upmonitor", "sms_contacts");

    }

    private String getMailContacts() {
        return Config.getParam("upmonitor", "mail_contacts");

    }

    private String getDesc() {
        return Config.getParam("upmonitor", "description");

    }

    private String getAppname() {
        appname = System.getProperty("zname");
        return appname;
    }

    private String getConfiguration() {
        String configFile = Config.CONFIG_HOME + "/" + System.getProperty("appenv") + "." + Config.CONFIG_FILE;
        String entireFileText = null;
        try {
            entireFileText = new Scanner(new File(configFile))
                    .useDelimiter("\\A").next();
            return entireFileText;
        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private JSONArray getProperties() {
        JSONArray ret = new JSONArray();
        Properties props = System.getProperties();
        Enumeration e = props.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            JSONObject obj = new JSONObject();
            obj.put(key, value);
            ret.add(obj);
        }
        if (!ret.isEmpty() && ret != null) {
            return ret;
        }
        return null;
    }

    private JSONArray getIpAddresses() {
        JSONArray jsonArray = new JSONArray();
        Enumeration e;
        try {
            e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e.nextElement();
                if (ni.isLoopback() || !ni.isUp()) {
                    continue;
                }
                for (Enumeration e2 = ni.getInetAddresses(); e2.hasMoreElements();) {
                    InetAddress addr = (InetAddress) e2.nextElement();
                    if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
                        jsonArray.add(addr.toString());
                    }
                }
            }
            return jsonArray;
        } catch (SocketException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private long getStartTime() {
        return ManagementFactory.getRuntimeMXBean().getStartTime() / 1000;
    }
}
