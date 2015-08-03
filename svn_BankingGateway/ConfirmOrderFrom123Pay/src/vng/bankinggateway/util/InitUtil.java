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
    public static String PASSCODE = "";
    
    public static void initConfiguration() {
        String config = System.getProperty("config", "") + "/123pay";
        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/123pay.config");
        InitUtil.PASSCODE = System.getProperty("PassCode", "");
        
        // Call common to init
        CommonDef.initConfigurationForCommon();
    }
}
