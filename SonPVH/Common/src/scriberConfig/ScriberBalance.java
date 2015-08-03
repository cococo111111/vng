/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scriberConfig;

import dto.BalanceDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author sonpvh
 */
public class ScriberBalance {

    private static Logger log = Logger.getLogger("exception");
    private static final String SCRIBE_CATEGORY = "TRANSACTION_SERVICE";

    public static void sendLogAddMoneyBalance(BalanceDTO balanceDTO) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(balanceDTO.getScribeMessage() + currentTime);
    }

    public static void sendLogDeductMoneyBalance(BalanceDTO balanceDTO) {
        long currentTime = System.currentTimeMillis() / 1000;
        sendLog(balanceDTO.getScribeMessage() + currentTime);
    }

    public static void sendLog(String message) {
        try {
            ScribeServiceClient.getInstance().writeLog(SCRIBE_CATEGORY, message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
