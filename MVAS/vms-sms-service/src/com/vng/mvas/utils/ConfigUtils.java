package com.vng.mvas.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ConfigUtils {
	private static ConfigUtils config = null;

	public ConfigUtils() {
		String configpath = System.getProperty("config", ".");
		System.out.println("conf = " + configpath);
		PropertyConfigurator.configure(configpath + "/log4j.properties");
		loadConfigFile(configpath + "/vms-sms-service.ini");
	}

	public static void init() {
		if (config == null) {
			config = new ConfigUtils();
		}
	}

	public Properties loadConfigFile(String filename) {

		Properties config = new Properties();

		try {
			config.load(new FileInputStream(filename));
		} catch (IOException ex) {
			Logger.getLogger(ConfigUtils.class).warn(ex.getMessage());
		} 

		Enumeration<Object> en = config.keys();

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
