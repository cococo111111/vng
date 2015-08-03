package vng.zingme.payment.frontend.credits.service;

import java.io.File;
import org.apache.log4j.Logger;
import vng.wte.common.LogUtil;

public class CreditsDaemon {
    static {
        try {
            System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
            System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        } catch (Exception e) {
            Logger.getLogger("appActions").warn(e);
        }
    }

    public static void main(String[] args) throws Exception {
        LogUtil.init();
        CreditsServer webserver = new CreditsServer();
        String pidFile = System.getProperty("pidfile");
        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }
            if (System.getProperty("foreground") == null) {
                System.out.close();
                System.err.close();
            }
            webserver.start();

        } catch (Throwable e) {
            System.exit(3);
        }
    }
}
