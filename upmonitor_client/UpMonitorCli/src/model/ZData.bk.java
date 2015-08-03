///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import com.vng.jcore.common.Config;
//import java.util.Iterator;
//import java.util.Properties;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
///**
// *
// * @author locth2
// */
//public class ZData {
//
//    private String hostname;
//    private String appname;
//    private List<InetAddress> ipAddresses;
//    private String desc;
//    public byte[] data;
//
//    public ZData() {
//        JSONObject objData = new JSONObject();
//        objData.put("appname", getAppname());
//        objData.put("hostname", getHostname());
//        JSONArray arr = new JSONArray();
//        List<InetAddress> ipAddresses1 = getIpAddresses();
//        for (int i = 0; i < ipAddresses1.size(); i++) {
//            InetAddress inetAddress = ipAddresses1.get(i);
//            arr.add(inetAddress.toString());
//        }
//        objData.put("ipaddr", arr);
//        objData.put("property", getAllProperties());
//        objData.put("desc", getDesc());
//        System.out.println("=====================");
//        System.out.println(objData.toJSONString());
//        System.out.println("=====================");
//        this.data = objData.toJSONString().getBytes();
//    }
//
//    private String getDesc() {
//        return Config.getParam("zookeeper", "desc");
//
//    }
//
//    private String getAppname() {
//        appname = System.getProperty("zname");
//        return appname;
//    }
//
//    private String getHostname() {
//        try {
//            return InetAddress.getLocalHost().getHostName();
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(ZData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    private Properties getProperty() {
//        Properties props = System.getProperties();
//        return props;
//    }
//
//    private JSONArray getAllProperties() {
//        JSONArray ret = new JSONArray();
//        Properties props = System.getProperties();
//        Enumeration e = props.propertyNames();
//        while (e.hasMoreElements()) {
//            String key = (String) e.nextElement();
//            String value = props.getProperty(key);
//            JSONObject obj = new JSONObject();
//            obj.put(key, value);
//            ret.add(obj);
//        }
//        return ret;
//    }
//
//    private List<InetAddress> getIpAddresses() {
//        List<InetAddress> ipAddr = new ArrayList<>();
//        Enumeration e;
//        try {
//            e = NetworkInterface.getNetworkInterfaces();
//            while (e.hasMoreElements()) {
//                NetworkInterface ni = (NetworkInterface) e.nextElement();
//                if (ni.isLoopback() || !ni.isUp()) {
//                    continue;
//                }
//                for (Enumeration e2 = ni.getInetAddresses(); e2.hasMoreElements();) {
//                    InetAddress addr = (InetAddress) e2.nextElement();
//                    if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
//                        ipAddr.add(addr);
//                    }
//                }
//            }
//            return ipAddr;
//        } catch (SocketException ex) {
//            Logger.getLogger(ZData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
////    private JSONArray getIpAddresses() {
////        JSONArray ipAddr = new JSONArray();
////        Enumeration e;
////        try {
////            e = NetworkInterface.getNetworkInterfaces();
////            while (e.hasMoreElements()) {
////                NetworkInterface ni = (NetworkInterface) e.nextElement();
////                if (ni.isLoopback() || !ni.isUp()) {
////                    continue;
////                }
////                for (Enumeration e2 = ni.getInetAddresses(); e2.hasMoreElements();) {
////                    InetAddress addr = (InetAddress) e2.nextElement();
////                    if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
////                        ipAddr.add(addr.toString());
////                    }
////                }
////            }
////            return ipAddr;
////        } catch (SocketException ex) {
////            Logger.getLogger(ZData.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        return null;
////    }
//
////    private long getCreateTime() {
////        return System.currentTimeMillis() / 1000L;
////    }
////
////    private String getConfigContent() {
////        StringBuilder fileData = new StringBuilder();
////        BufferedReader reader;
////        try {
////            reader = new BufferedReader(
////            new FileReader(Config.CONFIG_HOME+"/"+ System.getProperty("appenv") + "." +Config.CONFIG_FILE));
////            char[] buf = new char[1024];
////            int numRead=0;
////            try {
////                while((numRead=reader.read(buf)) != -1){
////                    String readData = String.valueOf(buf, 0, numRead);
////                    fileData.append(readData);
////                }
////                reader.close();
////                return fileData.toString();
////            } catch (IOException ex) {
////                Logger.getLogger(ZData.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        } catch (FileNotFoundException ex ) {
////            Logger.getLogger(ZData.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        return null;
////    }
//}
