package com.vng.zing.credits.report.mail;

import java.io.File;

import com.vng.jcore.common.LogUtil;

public class ZAppDaemon {
    static {
        System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
    }
    public static void main(String[] args) throws Exception {
        LogUtil.init();
        ZAppServer webserver = new ZAppServer();
        String pidFile = System.getProperty("pidfile");
        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }
            webserver.start();
        } catch (Throwable e) {
            System.out.println(e);
            System.exit(3);
        }
    }
}
