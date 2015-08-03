package vng.bankinggateway.common.util;

import org.apache.log4j.Logger;

/**
 * 
 * @author root
 */
public class ScriberBankingGateway {

    private static Logger log = Logger.getLogger("systemActions");
    private static final String LOG_FORMAT_CREATED_TRANSACTION = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";
    private static final String SCRIBE_CATEGORY = "BANKING_GATEWAY";

    public static void sendLogCreateTransactionFromEsale(String refID, String time, String username, String agencyCode, String region, 
            int amount, String clientIP, String description, String sig) {
        long currentTime = System.currentTimeMillis() / 1000;

        String s = String.format(LOG_FORMAT_CREATED_TRANSACTION, refID, time, username, agencyCode, region,
                String.valueOf(amount), clientIP, description, sig, currentTime);
        sendLog(s);
    }
    
    public static void sendLogWithCurrentTime(String message) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(message + currentTime);
    }
    
    public static void sendLog(String message) {
        try {
            ScribeServiceClient.getInstance().writeLog(SCRIBE_CATEGORY, message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
