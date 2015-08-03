    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transactionService;

import balanceService.BalanceServiceImpl;
import balanceService.IBalanceService;
import balanceZoo.BalanceZooImpl;
import common.Common;
import common.Common.TRANX_TYPE;
import daoService.IDao;
import dto.Balance;
import dto.BalanceDTO;
import dto.Transaction;
import dto.TranxStatus;
import java.util.logging.Logger;
import tranxDao.TransactionDaoImpl;
import tranxStatusDao.TranStatusDaoImpl;
import tranxZoo.TransactionZooImpl;
import zooService.IZoo;

/**
 *
 * @author root
 */
public class TransactionServiceImpl implements ITransactionService {

    public static String tranxPrefix = "";
    private static final Logger log = Logger.getLogger("exception");
    IZoo zk = null;
    IZoo bZk = null;
    IBalanceService balanceService = null;
    IDao dao = null;
    IDao tsDao = null;

    public TransactionServiceImpl() {
        if (zk == null) {
            zk = new TransactionZooImpl();
        }
        if (dao == null) {
            dao = TransactionDaoImpl.getInstance();
        }
        if (balanceService == null) {
            balanceService = new BalanceServiceImpl();
        }
        if (tsDao == null) {
            tsDao = TranStatusDaoImpl.getInstance();
        }
        if (bZk == null) {
            bZk = new BalanceZooImpl();
        }
    }

    /**
     * 1. log.info:
     * 2. subscriber (config host, port, getClient)
     * 3. create zoo: path tranxPrefix + / tranID (data=serialize Transaction)
     * /stats (data = Transaction Status)
     * (init configuration)
     * 4. stored DB
     * return errorCode {define errorCode }
     * @param tranx
     */
    @Override
    public int createTranx(Transaction tranx) {
        log.info("Creating ... Tranx: " + tranx.toString());
        String path = Common.createPath(tranxPrefix, tranx.getUserID());
        String bPath = Common.createPath(BalanceServiceImpl.balanceZooPrefix, tranx.getUserID());

        if (!zk.create(tranx, path)) {
            return Common.TRANX_RESPONSE_CODE.TRANX_ZNODE_FALSE;
        }
        // set current Balance
        Balance ba = (Balance) bZk.get(tranx.getUserID(), bPath);
        if (ba != null) {
            if (tranx.getBalanceType() == Common.BALANCE_TYPE.MAIN) {
                tranx.setCurrentBalance(ba.getMain());
            } else if (tranx.getBalanceType() == Common.BALANCE_TYPE.GAME) {
                tranx.setCurrentBalance(ba.getGame());
            } else if (tranx.getBalanceType() == Common.BALANCE_TYPE.PROMOTION) {
                tranx.setCurrentBalance(ba.getPromotion());
            }
        }

        // insert DB
        if (!dao.insert(tranx)) {
            return Common.TRANX_RESPONSE_CODE.TRANX_DB_FAIL;
        }
        //    ScriberTransaxtion.sendLogCreateTransaction(tranx);
        return Common.TRANX_RESPONSE_CODE.SUCCESS;
    }

    /**
     * 1: add BalanceService???
     * 2: delete Zoo
     * 3: save DB
     * 4: log4j
     * @param tranx
     */
    @Override
    public int confirmSuccessTranx(Transaction tranx) {
        boolean res = true;
        log.info("confirming SuccessTranx ....");
        BalanceDTO balanceDto = converTranx2BalanceDto(tranx);
        // 1 add BalanceService
        switch (tranx.getTxType()) {
            case TRANX_TYPE.TT_PUSH_MONEY_ATM:
            case TRANX_TYPE.TT_PUSH_MONEY_CARD:
            case TRANX_TYPE.TT_PUSH_MONEY_IBANKING:
            case TRANX_TYPE.TT_PUSH_MONEY_SMS:
                res = balanceService.addMoney(balanceDto);
                break;
            case TRANX_TYPE.TT_DEDUCT_MONEY:
                res = balanceService.deductMoney(balanceDto);
                break;
            default:
                log.info("TRANX_TYPE WRONG");
        }

        if (!res) {
            return Common.TRANX_RESPONSE_CODE.BALANCE_SERVICE_FALSE;
        }
        //2 delete Zoo
        res = zk.delete(tranx.getUserID(), Common.createPath(tranxPrefix, tranx.getUserID()));

        if (!res) {
            return Common.TRANX_RESPONSE_CODE.TRANX_ZNODE_FALSE;
        }
        //3. insert tranxStatus
        TranxStatus tranxStatus = new TranxStatus(tranx.getUserID(),
                Common.TRANX_STATUS_TYPE.SUCCESS, tranx.getResponseCode(), null, tranx.getUserID());
        res = tsDao.insert(tranxStatus);

        if (!res) {
            return Common.TRANX_RESPONSE_CODE.TRANX_DB_FAIL;
        }
        //4 insert subscriber
        // ScriberTransaxtion.sendLogConfirmSuccessTransaction(tranx);

        //5 insert log4j
        log.info("confirm success tranx SUCCESS");
        return Common.TRANX_RESPONSE_CODE.SUCCESS;
    }

    /**
     *
     * 1: delete Zoo
     * 2: save DB
     * 3: log4j
     * @param tranx
     */
    @Override
    public int removeTranx(Transaction tranx) {
        boolean res = true;
        log.info("Removing Tranx ... ");
        //1: delete Zoo
        res = zk.delete(tranx.getUserID(), Common.createPath(tranxPrefix, tranx.getUserID()));

        if (!res) {
            return Common.TRANX_RESPONSE_CODE.TRANX_ZNODE_FALSE;
        }
        //2: save DB
        TranxStatus tranxStatus = new TranxStatus(tranx.getUserID(),
                Common.TRANX_STATUS_TYPE.ERROR, tranx.getResponseCode(), null, tranx.getUserID());
        res = tsDao.insert(tranxStatus);

        if (!res) {
            return Common.TRANX_RESPONSE_CODE.TRANX_DB_FAIL;
        }
        //3: log subscriber
        //  ScriberTransaxtion.sendLogRemoveTransaction(tranx);

        //4: log4j
        log.info("Remove Tranx SUCCESS");
        return Common.TRANX_RESPONSE_CODE.SUCCESS;
    }

    private static BalanceDTO converTranx2BalanceDto(Transaction tranx) {
        BalanceDTO dto = new BalanceDTO();
        dto.setUserId(tranx.getUserID());
        dto.setAmount(tranx.getAmount());
        dto.setBalanceType(tranx.getBalanceType());
        dto.setCurrentBalance(tranx.getCurrentBalance());
        dto.setTxId(tranx.getTxID()); // delete
        dto.setTxType(tranx.getTxType());
        dto.setNote(null);//what the note?? NOTE:
        dto.setLastTxId(tranx.getTxID());
        dto.setDate(tranx.getTxTime()); // fixxxx
        return dto;
    }
}
