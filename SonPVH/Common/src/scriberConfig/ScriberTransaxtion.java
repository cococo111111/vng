package scriberConfig;

import dto.Transaction;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class ScriberTransaxtion {

    private static Logger log = Logger.getLogger("exception");
    private static final String SCRIBE_CATEGORY = "BALANCE_SERVICE";

    public static void sendLogCreateTransaction(Transaction transaction) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(transaction.getScribeMessage() + currentTime);
    }

    public static void sendLogConfirmSuccessTransaction(Transaction transaction) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(transaction.getScribeMessage() + currentTime);
    }

    public static void sendLogRemoveTransaction(Transaction transaction) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(transaction.getScribeMessage() + currentTime);
    }

    public static void sendLog(String message) {
        try {
            ScribeServiceClient.getInstance().writeLog(SCRIBE_CATEGORY, message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }
}
