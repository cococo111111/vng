package vng.zingme.stats.mySqlConnectionPool;

import org.apache.commons.configuration.XMLConfiguration;

public class Config2 {
	public static String url;
	public static String dbName;
	public static String driver;
	public static String userName;
	public static String passWord;
	public static long timeOut;
	public static int maxPoolSize;
	public static int maxQueueSize;

	// staging.init
	public static String stdProfile2Host;
	public static int stdProfile2Port;
	public static String stdprofile2source;
	public static String stdprofile2auth;

	public static String balancehost;
	public static int balanceport;

	public static String latestcachedhost;
	public static int latestcachedport;

	public static String reporthost;
	public static int reportport;

	public static String appinfohost;
	public static int appinfoport;

	public static String adminsignatureKey;
	public static String adminhost;
	public static int adminport;

	public static String memcachehost;
	public static int memcacheport;

	public static String tranxfilter;
	public static String autoReport;

	private static XMLConfiguration config;

}
