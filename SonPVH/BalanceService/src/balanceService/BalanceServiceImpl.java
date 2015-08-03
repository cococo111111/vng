package balanceService;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import balanceProducer.BalanceProducerImpl;
import balanceProducer.IBalanceProducer;
import balanceZoo.BalanceZooImpl;
import balanceZoo.IBalanceZoo;
import cache.Cache;
import common.Common;
import dto.Balance;
import dto.BalanceDTO;
import org.apache.log4j.Logger;

public class BalanceServiceImpl implements IBalanceService {

    private IBalanceZoo zoo = null;
    private IBalanceProducer Producer = null;
    private static final Logger log = Logger.getLogger("exception");
    public static String balanceZooPrefix;
    Cache cache = null;

    public BalanceServiceImpl() {
        if (this.zoo == null) {
            this.zoo = new BalanceZooImpl();
        }
        if (this.Producer == null) {
            this.Producer = new BalanceProducerImpl();
        }
        if (cache == null) {
            cache = Cache.getInstanceCache();
        }
    }

    /**
     * sum: addMoney into Balance
     * 1: check UserIsExist()
     * 2: if existedUser -> get + add
     * 3: if !existedUser -> create + add
     * @param balanceDto
     */
    @Override
    public boolean addMoney(BalanceDTO balanceDto) {
        log.info("Balance adding Money ...");
        boolean res = true;

        //1. check User Is Existed.
        Balance balance = converDTO2Balance(balanceDto);
        String path = Common.createPath(balanceZooPrefix, balanceDto.getUserId());
        int userId = balanceDto.getUserId();

        if (checkUserIsExisted(userId, path)) {
            res = zoo.add(balance, balanceDto.getBalanceType(), path);

        } else {
            res = zoo.create(balance, path);
        }
        //2. update UserId into Cache
        if (!res) {
            return res;
        }
        cache.updateBalanceCache(zoo.get(userId, path));
        //3. Store data into ActiveMQ
        res = Producer.sendMessage(balanceDto);
        //4. Subsciber

        //5. Log4j
        log.info("Balance add Money SUCCESS");
        return res;
    }

    @Override
    public boolean deductMoney(BalanceDTO balanceDto) {
        log.info("balance deducting Money ....");
        boolean res = true;
        //1. check User Is Existed.
        Balance balance = converDTO2Balance(balanceDto);
        String path = Common.createPath(balanceZooPrefix, balanceDto.getUserId());

        int userId = balanceDto.getUserId();
        if (checkUserIsExisted(userId, path)) {
            res = zoo.deduct(balance, balanceDto.getBalanceType(), path);
        } else {
            res = zoo.create(balance, path);
        }

        if (!res) {
            return res;
        }
        //2. Set this UserId into Cache
        cache.updateBalanceCache(zoo.get(userId, path));
        //3. Store data into ActiveMQ
        res = Producer.sendMessage(balanceDto);
        //4. Subsciber

        //5. Log4j
        log.info("Balance deduct Money SUCCESS");
        return res;
    }

    /**
     * @description: get Balance by UserId
     * 1: check in Cache.
     * 2: check in BalanceZoo.
     * - If existed: update Cache.
     * @param userId
     * @return (null if not existed)
     */
    @Override
    public Balance getBalancebyId(int userId) {
        log.info("geting Balance ... by userId: " + userId);
        //1. check in Cache:
        Balance balance = cache.getBalanceinCache(userId);
        if (null != balance) {
            log.info("get Balance in Cache SUCCESS ");
            return balance;
        }

        //2. else -  check in Balance
        String path = Common.createPath(balanceZooPrefix, userId);
        balance = zoo.get(userId, path);

        if (null != balance) {
            log.info("get Balance in balanceZoo SUCCESS ");
            cache.updateBalanceCache(balance);   // update cache
            return balance;
        }

        //Balance is not exist
        log.info("Balance of userid " + userId + " is not exist");
        return null;
    }

    /**
     * check User is existed in Zoo
     * @param userId
     * @return
     */
    private boolean checkUserIsExisted(int userId, String path) {
        boolean res = (zoo.get(userId, path) == null) ? false : true;
        return res;
    }

    private static Balance converDTO2Balance(BalanceDTO balanceDto) {
        Balance balance = new Balance();
        balance.setUserId(balanceDto.getUserId());
        balance.setLastTxId(balanceDto.getLastTxId());
        switch (balanceDto.getBalanceType()) {
            case Common.BALANCE_TYPE.MAIN:
                balance.setMain(balanceDto.getAmount());
                break;
            case Common.BALANCE_TYPE.PROMOTION:
                balance.setPromotion(balanceDto.getAmount());
                break;
            case Common.BALANCE_TYPE.GAME:
                balance.setGame(balanceDto.getAmount());
                break;
            default:
                throw new AssertionError();
        }
        return balance;
    }
}
