package aTestTranx;

import balanceService.BalanceServiceImpl;
import balanceService.IBalanceService;
import common.Common;
import dto.Balance;
import dto.Transaction;
import org.apache.log4j.Logger;
import transactionService.ITransactionService;
import transactionService.TransactionServiceImpl;
import tranxConfig.ConfigUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class NewMain {

    private static final Logger log = Logger.getLogger("exception");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //init config
        ConfigUtil.initConfiguration();

        // check Balance  is existed?
        int userId = 72;
        int tranxId = 92;
        IBalanceService ba = new BalanceServiceImpl();
        Balance balance = ba.getBalancebyId(userId);
        if (balance == null) {
            log.info(" balance at userId = " + userId + " null ");
        }

        // create transaction
        Transaction tranx = new Transaction((long) tranxId, (short) Common.TRANX_TYPE.TT_DEDUCT_MONEY,
                (int) (System.currentTimeMillis() / 1000), userId, "son",
                (double) 1000, (double) 1000, Common.BALANCE_TYPE.GAME, "agentID",
                null, null, null, null, null, Common.TRANX_RESPONSE_CODE.TRANX_PENDING, null);

        ITransactionService tranxService = new TransactionServiceImpl();

        // confirm/ remove  transaction
        int res = tranxService.createTranx(tranx);
        if (res == Common.TRANX_RESPONSE_CODE.SUCCESS) {
            tranx.setResponseCode(Common.TRANX_RESPONSE_CODE.SUCCESS);
            tranxService.confirmSuccessTranx(tranx);

            balance = ba.getBalancebyId(userId);
            log.info("balance "
                    + ", userid " + balance.getUserId()
                    + ", main " + balance.getMain()
                    + ", game " + balance.getGame()
                    + ", promotion " + balance.getPromotion());

        } else {
            tranxService.removeTranx(tranx);
        }


    }
}
