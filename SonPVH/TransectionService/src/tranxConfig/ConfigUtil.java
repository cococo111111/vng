/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tranxConfig;

import balanceService.BalanceServiceImpl;
import common.Common;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import transactionService.TransactionServiceImpl;

/**
 *
 * @author root
 */
public class ConfigUtil {

    public static Properties loadConfigFile(String filename) {
        Properties config = new Properties();

        try {
            config.load(new FileInputStream(filename));
        } catch (IOException ex) {
            Logger.getLogger("exception").warn(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger("exception").warn(ex.getMessage());
        }

        Enumeration en = config.keys();

        // System.out.println("********** System configuration **********");
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = (String) config.get(key);
            // System.out.println(key + " => " + value);
            System.setProperty(key, value);
        }
        return config;
    }

    public static void initConfiguration() {

        String config = System.getProperty("config", "");
        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/transaction.config");
        TransactionServiceImpl.tranxPrefix = System.getProperty("prefixTranxZoo");
        BalanceServiceImpl.balanceZooPrefix = System.getProperty("prefixBalanceZoo");
        Common.SCRIBE_HOST = System.getProperty("scribe_host");
        Common.SCRIBE_PORT = System.getProperty("scribe_port");

        Common.hostChrome = System.getProperty("host_chrome");
    }
}
