/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;

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
            Logger.getLogger("appActions").warn(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger("appActions").warn(ex.getMessage());
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
}
