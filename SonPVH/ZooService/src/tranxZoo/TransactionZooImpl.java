/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tranxZoo;

import zooService.ZooImpl;
import common.Common;
import dto.Transaction;
import exchangeData.TranxSerializator;
import tranxZoo.ITransactionZoo;
import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class TransactionZooImpl extends ZooImpl<Transaction> implements ITransactionZoo {

    public TransactionZooImpl() {
        try {
            this.zk = new ZooKeeper(Common.hostChrome, SECTION_TIMEOUT, null);
            setZk(zk);
        } catch (IOException ex) {
            log.error("CONNECTED FAIL to host, ex: " + ex.getMessage());
            reConnect();
        }
    }

    @Override
    public boolean create(Transaction transaction, String path) {
        log.info("Creating TranxZoo for userID ...  " + transaction.getUserID());
        int userId = transaction.getUserID();

        TranxSerializator exchange = TranxSerializator.getInstance();
        byte[] data = exchange.serialize(transaction);

        try {
            zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);

            log.info("Created TranxZoo for userID SUCCESS  " + transaction.getUserID());
            return true;
        } catch (Exception ex) {
            try {
                log.error("TRANXZNODE CREATED FAIL at userid: " + userId
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
}
