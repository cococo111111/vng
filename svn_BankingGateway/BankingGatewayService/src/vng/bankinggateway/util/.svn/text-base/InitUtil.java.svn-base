/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.util;

import org.apache.log4j.PropertyConfigurator;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.ConfigUtil;

/**
 *
 * @author root
 */
public class InitUtil {
    public static byte[] sharedkey = {};

    public static byte[] sharedvector = {};
    
    public static void initEncryptionInfo(String key, String vector) throws Exception {
        String[] temp = key.split(",");
        if (temp.length < 24) {
            throw new Exception("[ERROR]Invalid key format, must be 24 hex chars");
        }
        byte[] keyTemp = new byte[24];
        for (int i = 0; i < 24; i++) {
            int intTemp;
            try {
                intTemp = Integer.parseInt(temp[i]);
            } catch (Exception e) {
                throw new Exception("[ERROR]Invalid key format, key member must be a number");
            }
            keyTemp[i] = (byte) intTemp;
        }
        InitUtil.sharedkey = keyTemp.clone();
        String[] temp2 = vector.split(",");
        if (temp2.length < 8) {
            throw new Exception("[ERROR]Invalid vector format, must be 8 hex chars");
        }
        byte[] vectorTemp = new byte[8];
        for (int i = 0; i < 8; i++) {
            int intTemp2;
            try {
                intTemp2 = Integer.parseInt(temp2[i]);
            } catch (Exception e) {
                throw new Exception("[ERROR]Invalid vector format, vector member must be a number");
            }
            vectorTemp[i] = (byte) intTemp2;
        }
        InitUtil.sharedvector = vectorTemp.clone();
    }
    
    public static void initConfiguration() {
        String config = System.getProperty("config", "") + "/BankingGatewayService";
        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/bankinggatewayservice.config");
        // Call common to init
        CommonDef.initConfigurationForCommon();
        try {
            initEncryptionInfo(System.getProperty("sharedkey"), System.getProperty("sharedvector"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
