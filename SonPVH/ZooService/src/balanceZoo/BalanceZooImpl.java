/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceZoo;

import common.Common;
import dto.Balance;
import exchangeData.BalanceSerializator;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import zooService.ZooImpl;

public class BalanceZooImpl extends ZooImpl<Balance> implements IBalanceZoo {

    private final Logger log = Logger.getLogger("exception");
    private static final int ADD = 1;
    private static final int DEDUCT = -1;
    private ZooKeeper zk = null;

    public BalanceZooImpl() {
        try {
            this.zk = new ZooKeeper(Common.hostChrome, SECTION_TIMEOUT, null);
            this.setZk(zk);
        } catch (IOException ex) {
            log.error("CONNECT FAIL to host, ex: " + ex.getMessage());
            reConnect();
        }
    }

    @Override
    public boolean create(Balance balance, String path) {
        log.info("Balance Zoo Creating at userID " + balance.getUserId() + " .... ");
        int userId = balance.getUserId();

        BalanceSerializator exchange = BalanceSerializator.getInstance();
        byte[] data = exchange.serialize(balance);

        try {
            zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            log.info("Balance Zoo Created SUCCESS at userID " + balance.getUserId());
            return true;
        } catch (Exception ex) {
            try {
                log.error("CREATE BALANCE ZNODE FAIL at userid: " + userId
                        + ". Ex: " + ex.getMessage());

                reConnect();  //RECONNECTION
                zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            } catch (Exception ex1) {
                log.error("Cannot RECREATE ZNODE at userid: " + userId
                        + ". Ex: " + ex1.getMessage());
            }
        }
        return false;

    }

    @Override
    public boolean add(Balance balanceAdd, int balanceType, String path) {
        int userId = balanceAdd.getUserId();
        log.info("Balance Zoo Adding at userID " + userId + ".... ");
        // 1. get Znode at userId
        Balance balance = get(userId, path);
        //TODO : # khac prefix moi dc.

        //2. update balance
        balance = updateBalance(balance, balanceAdd, balanceType, ADD);
        BalanceSerializator exchange = BalanceSerializator.getInstance();
        byte[] data = exchange.serialize(balance);

        // 3. update Znode with new Balance:
        boolean res = updateZnode(userId, data, path);
        log.info("add balance SUCCESS at Userid" + userId);

        return res;
    }

    @Override
    public boolean deduct(Balance balanceAdd, int balanceType, String path) {
        int userId = balanceAdd.getUserId();
        log.info("Balance Zoo deducting .... at userid" + userId);

        //1. get Znode at userId
        Balance balance = get(userId, path);

        // 2. update balance
        balance = updateBalance(balance, balanceAdd, balanceType, DEDUCT);
        BalanceSerializator exchange = BalanceSerializator.getInstance();
        byte[] data = exchange.serialize(balance);

        // 3. update Znode with new Balance:
        boolean res = updateZnode(userId, data, path);
        log.info("deduct balance SUCCESS at Userid" + userId);

        return res;
    }

    private boolean updateZnode(int userId, byte[] data, String path) {
        try {
            this.zk.setData(path, data, -1);
            log.info("Zoo update (Add/Deduct) SUCCESS");
            return true;
        } catch (Exception ex) {
            log.error("UPDATE ZNODE FAIL at userId: " + userId
                    + ". Ex: " + ex.getMessage());

            reConnect(); //reconnect
            updateZnode(userId, data, path); //recursive
            return false;
        }

    }

    private Balance updateBalance(Balance balance, Balance balanceAdd, int balanceType, int flag) {
        double updatedMoney = 0;
        switch (balanceType) {
            case Common.BALANCE_TYPE.MAIN:
                updatedMoney = balance.getMain() + (flag) * balanceAdd.getMain();
                balance.setMain(updatedMoney);
                break;
            case Common.BALANCE_TYPE.PROMOTION:
                updatedMoney = balance.getPromotion() + (flag) * balanceAdd.getPromotion();
                balance.setPromotion(updatedMoney);
                break;
            case Common.BALANCE_TYPE.GAME:
                updatedMoney = balance.getGame() + (flag) * balanceAdd.getGame();
                balance.setGame(updatedMoney);
                break;
            default:
                log.info("haven't this type of balance");
                break;
        }
        return balance;
    }
}
