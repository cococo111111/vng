package com.vng.mvas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Properties;

public class Utils {

	static public Properties readProperties(String filename) throws IOException{
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        props.load(stream);
        System.out.println("URL:"+props.getProperty("jdbcUrl"));
        System.out.println(props.toString());
        return props;
    }
	
	public static String MD5(String text) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] md5hash = new byte[32];
            md.update(text.getBytes("UTF-8"), 0, text.length());
            md5hash = md.digest();
            return convertToHex(md5hash);
        } catch (Exception ex) {
        }
        return "";
    }
    private static String convertToHex(byte[] data) {
        //convert the byte to hex format
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    public static String getServiceID(String serviceId) {
    	String res = "";
    	res = System.getProperty("serviceId_"+serviceId);
    	return res;
    	
    }
    

}
