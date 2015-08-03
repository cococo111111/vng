package com.services.soap.mo;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;

public class AutoLoadConfig extends Thread {
	public AutoLoadConfig() {
	}
	
	public void run() {
		while (true) {
			try {
				WSConfigLoader.getInstance().retrieveConfig();
				Util.logger.info(this.getClass().getName() + ".run() retrieve to loading Config and WhiteList.");
				sleep(Integer.parseInt(Constants._prop.getProperty("WAIT_TIME_AUTOLOAD_CONFIG")));
			}catch (InterruptedException e) {
			}catch (NumberFormatException ex) {
				Util.logger.error(this.getClass().getName() + "run() ERROR:" + ex.getMessage());
			}
		}
	}
}
