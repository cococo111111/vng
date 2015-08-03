package com.vng.cpnew.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.vng.cpnew.util.CPRMTConstants;

/**
 * <p>
 * Description: DBConfigLoader.
 * 
 * @author <A HREF="mailto:duyn77@yahoo.com">Nguyen Duc Duy</A>
 * @version 1.0
 */

public class DBConfigLoader {
	private static Logger logger = Logger.getLogger(DBConfigLoader.class);

	private static DBConfigLoader instance = null;
	private DBConfig dbConfig = new DBConfig();

	private DBConfigLoader() {
		Map<String, String> map = loadResource(CPRMTConstants.DEFAULT_FOLDER,
				CPRMTConstants.DB_CONFIG_FILE_NAME);
		if (map != null && map.size() > 0) {
			dbConfig.setPrivateKey(String.valueOf(
					map.get(CPRMTConstants.PRIVATE_KEY)).trim());

		} else {
			logger.debug("Resource file :["
					+ CPRMTConstants.DB_CONFIG_FILE_NAME + "] IS EMPTY!");
			throw new RuntimeException("Resource file :["
					+ CPRMTConstants.DB_CONFIG_FILE_NAME + "] IS EMPTY!");
		}
	}

	public static final DBConfigLoader getInstance() {
		if (instance == null) {
			synchronized (DBConfigLoader.class) {
				if (instance == null) {
					instance = new DBConfigLoader();
				}
			}

		}
		return instance;
	}

	private Map<String, String> loadResource(String DEFAULT_FOLDER,
			String resourceFile) {
		Map<String, String> propMap = null;
		try {
			// 1. Get resource from classpath
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(resourceFile);
			if (is != null) {
				logger.info("Load resource [" + resourceFile
						+ "] from classpath");
			} else {
				logger.info("Load resource [" + resourceFile
						+ "] from default folder " + DEFAULT_FOLDER);
			}
			if (is == null && DEFAULT_FOLDER != null) {
				// 2. Get resource from default folder
				is = this.getClass().getResourceAsStream(
						DEFAULT_FOLDER + CPRMTConstants.BACK_SLASH
								+ resourceFile);
			}
			if (is == null) {
				logger
						.error("CAN NOT LOAD FOUND FROM CLASSPATH AND DEDAULT FOLDER");
				throw new IOException(
						"CAN NOT FOUND RESOURCE FROM CLASSPATH AND DEDAULT FOLDER");
			}
			Properties prop = new Properties();
			prop.load(is);
			Iterator iterator = prop.entrySet().iterator();
			propMap = new HashMap<String, String>();
			for (Iterator iter = iterator; iter.hasNext();) {
				Entry element = (Entry) iter.next();
				propMap.put(String.valueOf(element.getKey()).trim(), String
						.valueOf(element.getValue()).trim());
			}
		} catch (IOException e) {
			logger.error("Failed to load resource from resource file :["
					+ resourceFile + "] Ex:" + e.getMessage());
			throw new RuntimeException(
					"Failed to load resource from resource file :["
							+ resourceFile + "]");
		}
		return propMap;
	}

	public DBConfig getDbConfig() {
		return dbConfig;
	}
}
