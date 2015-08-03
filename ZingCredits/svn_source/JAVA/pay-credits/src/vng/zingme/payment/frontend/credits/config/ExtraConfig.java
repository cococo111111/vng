/**
 *Class: ExtraConfig
 * Copyright VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.log4j.Logger;
import vng.wte.common.LogUtil;
import vng.wte.corelib.cache.lru.InstrumentedCache;

/**
 *
 * @author nhutnh@vng.com.vn
 */
public class ExtraConfig {

    private static final InstrumentedCache<String, String> localconfig = new InstrumentedCache(500);
    private static CompositeConfiguration config = new CompositeConfiguration();
    private static List<String> configNames = new ArrayList<String>();
    private static final Logger logger_ = Logger.getLogger(ExtraConfig.class);

    public static String getParam(String section, String name) {
        if (section == null) {
            section = "default";
        }
        if (!configNames.contains(section)) {
            addConfig(section);
            configNames.add(section);
        }
        String key = section + "-view." + name;

        String value = (String) localconfig.get(key);

        if (value != null) {
            return value;
        }

        value = config.getString(key);
        if (value != null) {
            localconfig.put(key, value);
        } else {
            key = "default-view." + name;
            value = (String) localconfig.get(key);
            if (value != null) {
                return value;
            }
            value = config.getString(key);
            if (value != null) {
                localconfig.put(key, value);
            }
        }
        return value;
    }

    private synchronized static void addConfig(String name) {
        if (name == null) {
            name = "default";
        }
        if (configNames.contains(name)) {
            return;
        }

        String HOME_PATH = System.getProperty("apppath");
        File configFile = new File(HOME_PATH + File.separator + "conf" + File.separator + "view." + name + ".config.ini");
        try {
            config.addConfiguration(new HierarchicalINIConfiguration(configFile));
        } catch (ConfigurationException e) {
            logger_.error("ERROR" + LogUtil.stackTrace(e));
        }
    }
}
