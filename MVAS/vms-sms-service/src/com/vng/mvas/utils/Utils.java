package com.vng.mvas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import com.vng.mvas.models.Incoming;

/**
* Set of common utilities.
* @author faheem
*
*/
public class Utils {
	
	private static final long limitation = Long.parseLong(System.getProperty("limitation"));

	/**
    * Read a properties file from the classpath and return a Properties object
    * @param filename
    * @return
    * @throws IOException
    */
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
    
    
    
    public static boolean isOverLimitation(Incoming msg,ConcurrentLinkedHashMap<String, String> _cardCache) {
    	
    	String userID = DataUtils.getUSER_ID(msg.getSenderAddress());
    	String serviceID = DataUtils.getSERVICE_ID(msg.getSmsServiceActivationNumber());
    	System.out.println("userID="+ userID);
    	String checkData = _cardCache.get(userID);
    	long totalAmount = 0;
    	String strdate = "";
    	String msgDate = "";
    	if (checkData != null && !"null".equals(checkData)) {
    		strdate = checkData.split("#")[0];
    		totalAmount = Integer.parseInt(checkData.split("#")[1]);
    	}
    	Date date = msg.getDateTime();
    	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		msgDate=df.format(date);
		boolean rs = false;
		System.out.println("msgDate:"+msgDate);
		System.out.println("serviceID:"+serviceID);
		
		if(strdate.endsWith(msgDate))
    	totalAmount += getfee(serviceID);
		else totalAmount = getfee(serviceID);
		
		System.out.println("totalAmount:"+totalAmount);
    	
    	if(totalAmount>limitation)
    	{
    		rs =  true;
    		totalAmount -=getfee(serviceID);
    	}
    	_cardCache.put(userID, msgDate+"#"+String.valueOf(totalAmount));
    	return rs;    	
    }
    
    public static int getfee(String serviceID) {
        int fee = 0;
        int code = Integer.valueOf(serviceID.substring(1, 2));
        switch (code) {
            case 0:
                fee = 500;
                break;
            case 1:
                fee = 1000;
                break;
            case 2:
                fee = 2000;
                break;
            case 3:
                fee = 3000;
                break;
            case 4:
                fee = 4000;
                break;
            case 5:
                fee = 5000;
                break;
            case 6:
                fee = 10000;
                break;
            case 7:
                fee = 15000;
                break;
            default:
                break;
        }
        return fee;
    }
}