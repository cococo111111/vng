package vng.banking.admin.service;

import com.vng.jcore.common.LogUtil;
import java.io.File;



public class BankingDaemon {
	
		
	public static void main(String[] args) throws Exception {
		LogUtil.init();
		BankingServer webserver = new BankingServer();		
		String pidFile = System.getProperty("pidfile");
                System.out.println("pidFile "+ pidFile);
		try {
			if (pidFile != null) {
				new File(pidFile).deleteOnExit();
			}
//			if (System.getProperty("foreground") == null) {
//				System.out.close();
//				System.err.close();
//			}
            
            
			webserver.start();
            
		
		} catch (Throwable e) {
//			String msg = "Exception encountered during startup.";
//			logger_.error(msg, e);
//	
//			// try to warn user on stdout too, if we haven't already detached
//			System.out.println(msg);
//			logger_.error("Uncaught exception: " + e.getMessage());
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        
			System.exit(3);
		}
		
		
	}
	
}
