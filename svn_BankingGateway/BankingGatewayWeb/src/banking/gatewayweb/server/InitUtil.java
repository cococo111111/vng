/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.gatewayweb.server;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.PropertyConfigurator;
import sun.misc.BASE64Encoder;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.ConfigUtil;

/**
 *
 * @author root
 */
public class InitUtil {
    public static String PROXY_HOST = "";
    public static int PROXY_PORT = 0;
    public static String HDBANK_URL = "";
    public static int DURATION_TIME_PENDING = 0;
    public static int TIME_SLEEP = 0;
    public static final String MMddyyHHmmss = "MMddyyHHmmss";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM_ddHHmmss = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
    // VIETTIN VISA - MASTER
    public static String PROFILE_ID_SG = "";
    public static String ACCESS_KEY_SG = "";
    public static String SECRET_KEY_SG = "";
    public static String PROFILE_ID_HN = "";
    public static String ACCESS_KEY_HN = "";
    public static String SECRET_KEY_HN = "";
    
    public static String VIETTINBANKVISA_URL = "";
    public static String TRANSACTION_TYPE = "";
    
    public static void initConfiguration() {
        try {
            // log 4j and config
            String config = System.getProperty("config", "");
            String appEnv = System.getProperty("appenv", "staging");
            PropertyConfigurator.configure(config + "/log4j.properties");
            ConfigUtil.loadConfigFile(config + "/bankinggatewayweb_" + appEnv + ".config");
            
            // for esale
            CommonDef.SHA1_SHARED_KEY = System.getProperty("sha1_shared_key", "");
            CommonDef.ESALE_WEBSERVICE_URL = System.getProperty("ESALE_URL", "");
            CommonDef.ESALE_REDIRECT_URL = System.getProperty("esale_redirect_url", "");
            
            CommonDef.STORAGE_HOST = System.getProperty("storage_host", "");
            CommonDef.STORAGE_PORT = Integer.valueOf(System.getProperty("storage_port", "0"));
            CommonDef.SCRIBE_HOST = System.getProperty("scribe_host", "");
            CommonDef.SCRIBE_PORT = Integer.valueOf(System.getProperty("scribe_port", ""));
            
            InitUtil.PROXY_HOST = System.getProperty("proxy_host", "");
            InitUtil.PROXY_PORT = Integer.valueOf(System.getProperty("proxy_port", "80"));
            InitUtil.DURATION_TIME_PENDING = Integer.valueOf(System.getProperty("duration_time_pending", "900000"));
            InitUtil.TIME_SLEEP = Integer.valueOf(System.getProperty("time_sleep_worker", "300000"));
            
            
            InitUtil.PROFILE_ID_SG = System.getProperty("profile_id_sg", "");
            InitUtil.ACCESS_KEY_SG = System.getProperty("access_key_sg", "");
            InitUtil.SECRET_KEY_SG = System.getProperty("secret_key_sg", "");
            InitUtil.PROFILE_ID_HN = System.getProperty("profile_id_hn", "");
            InitUtil.ACCESS_KEY_HN = System.getProperty("access_key_hn", "");
            InitUtil.SECRET_KEY_HN = System.getProperty("secret_key_hn", "");
          
            InitUtil.VIETTINBANKVISA_URL = System.getProperty("VietTinBankVisa_url");
            InitUtil.TRANSACTION_TYPE = System.getProperty("transaction_type", "sale");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static String sign(HashMap params, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        return sign(buildDataToSign(params), secretKey);
    }

    public static String sign(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(rawHmac).replace("\n", "");
    }

    private static String buildDataToSign(HashMap params) {
        String[] signedFieldNames = String.valueOf(params.get("signed_field_names")).split(",");
        ArrayList<String> dataToSign = new ArrayList<String>();
        for (String signedFieldName : signedFieldNames) {
            dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
        }
        return commaSeparate(dataToSign);
    }

    private static String commaSeparate(ArrayList<String> dataToSign) {
        StringBuilder csv = new StringBuilder();
        for (Iterator<String> it = dataToSign.iterator(); it.hasNext(); ) {
            csv.append(it.next());
            if (it.hasNext()) {
                csv.append(",");
            }
        }
        return csv.toString();
    }
    
    public static String getUTCDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }
    
    public static String formatTime(String inputTime, String fromDateFormat, String toDateFormat) {
        try {
            DateFormat df = new SimpleDateFormat(fromDateFormat);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(inputTime);
            DateFormat dfTransxTime = new SimpleDateFormat(toDateFormat);
            dfTransxTime.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dfTransxTime.format(date);
        } catch (ParseException ex) {
        }
        return "";
    }

    public static String parseTime(Date date, String dateFormat) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            return df.format(date);
        } catch (Exception ex) {
        }
        return "";
    }
}
