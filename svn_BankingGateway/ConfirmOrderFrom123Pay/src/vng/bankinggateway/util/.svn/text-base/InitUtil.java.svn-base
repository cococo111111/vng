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
    public static String TERMINAL_CODE_1 = "";
    public static String TERMINAL_CODE_2 = "";
    public static String SECRETKEY = "";
    
    public static void initConfiguration() {
        String config = System.getProperty("config", "") + "/viettinbank";
        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/viettinbank.config");
        InitUtil.TERMINAL_CODE_1 = System.getProperty("TerminalCode1", "");
        InitUtil.TERMINAL_CODE_2 = System.getProperty("TerminalCode2", "");
        InitUtil.SECRETKEY = System.getProperty("SecretKey", "");
        
        // Call common to init
        CommonDef.initConfigurationForCommon();
    }
}
