package main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vng.mvas.utils.ConfigUtils;
import com.vng.mvas.worker.SendMessageWorker;
import com.vng.mvas.worker.ShutdownInterceptor;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		String configpath = System.getProperty("config", ".");

		PropertyConfigurator.configure(configpath + "/log4j.properties");
		ConfigUtils.loadConfigFile(configpath + "/vmsgateway.ini");
		System.out.println("conf = " + configpath);

		String telco = System.getProperty("MOBILE_OPERATOR");
		String run = System.getProperty("run");

		if (telco == null) {
			telco = "VMS";
		}

		SendMessageWorker smsworker = new SendMessageWorker(telco);

		smsworker.init();
		if ("test".equals(run)) {
			logger.info("RUN TEST for telco '" + telco + "' start");
			smsworker.process(true);
		} else {
			ShutdownInterceptor shutdownInterceptor = new ShutdownInterceptor(
					smsworker);
			Runtime.getRuntime().addShutdownHook(shutdownInterceptor);
			logger.info("RUN WORKER for telco '" + telco + "' start");
			smsworker.start();
		}

	}

}
