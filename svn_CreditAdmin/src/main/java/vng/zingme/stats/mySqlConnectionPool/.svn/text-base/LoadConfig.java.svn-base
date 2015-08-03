package vng.zingme.stats.mySqlConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class LoadConfig extends HttpServlet {
	private static final long serialVersionUID = 4653428282575323420L;

	public static Properties loadConfigFile(String filename) {
		Properties config = new Properties();

		try {
			config.load(new FileInputStream(filename));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
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
		loadConfigFile(config + "/creditAdmin.config");
		PropertyConfigurator.configure(config + "/log4j.properties");

		Config2.url = System.getProperty("url");
		Config2.dbName = System.getProperty("dbName");
		Config2.driver = System.getProperty("driver");
		Config2.userName = System.getProperty("userName");
		Config2.passWord = System.getProperty("passWord");
		Config2.timeOut = Integer.parseInt(System.getProperty("timeOut"));
		Config2.maxPoolSize = Integer.parseInt(System
				.getProperty("maxPoolSize"));
		Config2.maxQueueSize = Integer.parseInt(System
				.getProperty("maxQueueSize"));
		Config2.stdProfile2Host = System.getProperty("stdprofile2host");
		Config2.stdProfile2Port = Integer.parseInt(System
				.getProperty("stdprofile2port"));

		Config2.stdprofile2source = System.getProperty("stdprofile2source");
		Config2.stdprofile2auth = System.getProperty("stdprofile2auth");

		Config2.balancehost = System.getProperty("balancehost");
		Config2.balanceport = Integer.parseInt(System
				.getProperty("balanceport"));

		Config2.latestcachedhost = System.getProperty("latestcachedhost");
		Config2.latestcachedport = Integer.parseInt(System
				.getProperty("latestcachedport"));

		Config2.reporthost = System.getProperty("reporthost");
		Config2.reportport = Integer.parseInt(System.getProperty("reportport"));

		Config2.appinfohost = System.getProperty("appinfohost");
		Config2.appinfoport = Integer.parseInt(System
				.getProperty("appinfoport"));

		Config2.adminsignatureKey = System.getProperty("adminsignatureKey")
				.trim();

		Config2.memcachehost = System.getProperty("memcachehost");
		Config2.memcacheport = Integer.parseInt(System
				.getProperty("memcacheport"));

		Config2.adminhost = System.getProperty("adminhost");
		Config2.adminport = Integer.parseInt(System.getProperty("adminport"));

		Config2.autoReport = System.getProperty("autoreport");
		Config2.tranxfilter = System.getProperty("tranxfilter");
	}

	@Override
	public void init() throws ServletException {
		initConfiguration();
		// TODO Auto-generated method stub
		super.init();

	}

}
