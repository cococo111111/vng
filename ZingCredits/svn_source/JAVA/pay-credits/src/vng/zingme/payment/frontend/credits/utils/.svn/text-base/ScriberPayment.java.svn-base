/**
 *Class: ScriberPayment * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

import org.apache.log4j.Logger;
import vng.wte.core.scribe.ScribeClientManager;
import vng.zingme.payment.frontend.credits.config.Configuration;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class ScriberPayment {

    private static Logger logger_ = Logger.getLogger(ScriberPayment.class);
    private static final String LOG_FORMAT = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";

    public static void sendLogStep1(int userID, String agentID, String clientIP, String domainName, String serverIP) {
        sendLog(userID, agentID, "", "", "", 1, clientIP, domainName, serverIP);
    }

    public static void sendLogStep2(int userID, String agentID, String refID, String clientIP, String domainName, String serverIP) {
        sendLog(userID, agentID, refID, "", "", 2, clientIP, domainName, serverIP);
    }

    public static void sendLogStep3(int userID, String agentID, String refID, int responseCode, String refNo, String clientIP, String domainName, String serverIP) {
        sendLog(userID, agentID, refID, responseCode+"", refNo, 3, clientIP, domainName, serverIP);

    }

    private static void sendLog(int userID, String agentID, String refID, String responseCode, String refNo, int logStep, String clientIP, String domainName, String serverIP) {
        try {

            long time = System.currentTimeMillis() / 1000;

            String s = String.format(LOG_FORMAT, serverIP, domainName, clientIP, userID, agentID, refID, responseCode, refNo, logStep, time);
            //System.out.println("s="+s);
            // logger_.error("s="+s);
            ScribeClientManager.getInstance(Configuration.SCRIBE_HOST, Configuration.SCRIBE_PORT).log(s);
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }

    }
}
