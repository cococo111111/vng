package vng.zingme.payment.model.util;

import java.io.File;
import java.util.Collection;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import vng.zingme.util.LogUtil;

public class Config {
	public static String url;
	public static String dbName;
	public static String driver;
	public static String userName;
	public static String passWord;
	public static String billingHost;
	public static int billingPort;
	
	public static String transactionHost;
	public static int transactionPort;
	
	public static String cacheHost;
	public static int cachePort;
	
	public static String queueHost;
	public static int queuePort;
	public static String queueHost2;
	public static int queuePort2;
	
	
	public static long timeOut;
	public static int maxPoolSize;
	public static int maxQueueSize;
	public static int GTXTransactionCacheMaxItem;
	public static int T_TransactionCacheMaxItem;
	public static int T_InvoiceCacheMaxItem;
	public static int T_AccountCacheMaxItem;
	
	public static int NumOfQueueWorker;
	public static String TransactionCommitLog;
	public static int TransactionCommitFile;
	public static String T_InvoiceCommitLog;
	public static int T_InvoiceCommitFile;
	public static String T_AccountCommitLog;
	public static int T_AccountCommitFile;
	
	private static XMLConfiguration config;
	
	static {
		//LogUtil.init();
		String nameSystem = "";
		String file = System.getProperty("config");
		file += File.separator + "config.xml";
//		String file = "conf/config.xml";
		try {
			config = new XMLConfiguration(file);
			LogUtil.info("Read file configuration " + file );
		} catch (ConfigurationException e1) {
			LogUtil.error("Error to load file configuration " + file);
		}
		try {
			Object prop = config.getProperty("system.name");
			if (prop instanceof Collection<?>) {
				for (int i = 0; i < ((Collection<?>) prop).size(); i++) {
					nameSystem = config.getString("system(" + i + ").name");
					if (nameSystem.toLowerCase().equals("mysql")) {
						driver = config.getString("system(" + i + ").driver");
						passWord = config.getString("system(" + i
								+ ").password");
						url = config.getString("system(" + i + ").url");
						userName = config.getString("system(" + i
								+ ").username");
						dbName = config.getString("system(" + i + ").dbName");
						timeOut = config.getLong("system(" + i + ").timeOut");
						maxPoolSize=config.getInt("system(" + i + ").maxPoolSize");
						maxQueueSize=config.getInt("system(" + i + ").maxQueueSize");
					}
					if (nameSystem.toLowerCase().equals("billing")) {
						billingHost = config
								.getString("system(" + i + ").host");
						billingPort = config.getInt("system(" + i + ").port");
					}
					
					if (nameSystem.toLowerCase().equals("queue")) {
						queueHost = config
								.getString("system(" + i + ").host");
						queuePort = config.getInt("system(" + i + ").port");
						
						queueHost2 = config
						.getString("system(" + i + ").host2");
						queuePort2 = config.getInt("system(" + i + ").port2");
						
						NumOfQueueWorker = config.getInt("system(" + i + ").NumOfQueueWorker");
						
					}
					
					if (nameSystem.toLowerCase().equals("transaction")) {
						transactionHost = config
								.getString("system(" + i + ").host");
						transactionPort = config.getInt("system(" + i + ").port");
						GTXTransactionCacheMaxItem = config.getInt("system(" + i + ").GTXTransactionCacheMaxItem");
						TransactionCommitLog = config.getString("system(" + i + ").TransactionCommitLog");
						TransactionCommitFile = config.getInt("system(" + i + ").TransactionCommitFile");
						T_TransactionCacheMaxItem=config.getInt("system(" + i + ").T_TransactionCacheMaxItem");
						
						T_InvoiceCommitLog = config.getString("system(" + i + ").T_InvoiceCommitLog");
						T_InvoiceCommitFile = config.getInt("system(" + i + ").T_InvoiceCommitFile");
						T_InvoiceCacheMaxItem=config.getInt("system(" + i + ").T_InvoiceCacheMaxItem");
						
						T_AccountCommitLog = config.getString("system(" + i + ").T_AccountCommitLog");
						T_AccountCommitFile = config.getInt("system(" + i + ").T_AccountCommitFile");
						T_AccountCacheMaxItem=config.getInt("system(" + i + ").T_AccountCacheMaxItem");
						
					}
					
					
					if (nameSystem.toLowerCase().equals("cache")) {
						cacheHost = config
								.getString("system(" + i + ").host");
						cachePort = config.getInt("system(" + i + ").port");					
						
					}
					
					LogUtil.info("Read information of system:" + nameSystem);

				}
			}
		} catch (Exception e) {
			LogUtil.error("Error to load property of file configuration : " + file + ". And system is shutdown");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		//LogUtil.init("billing");
		//System.out.println("asdfa");
	}
}
