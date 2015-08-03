/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zooService;

import common.Common;
import exchangeData.Serializator;
import zooService.IZoo;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public abstract class ZooImpl<T> implements IZoo<T> {

    protected static final int SECTION_TIMEOUT = 1000000;
    protected static final int RECONNECTION_TIMES = 10;
    protected final Logger log = Logger.getLogger("exception");
    protected ZooKeeper zk = null;

    @Override
    public boolean delete(int id, String path) {
        log.info("zoo deleting ... ");
        /*
         * 1. Delete Znode at userId
         */
        try {
            this.zk.delete(path, -1); // Delete childnode

            log.info("ZOO DELETE SUCCESS");
            return true;
        } catch (Exception ex) {
            log.error("DELETE ZNODE FAIL at UserId:" + id
                    + ". Ex: " + ex.getMessage());
            /*
             * 2. RECONNECT and Delete again
             */
            reConnect();
            delete(id, path);
            return false;
        }
    }

    @Override
    public T get(int id, String path) {
        byte[] data = getZnode(id, path);
        T t = null;
        if (data != null) {
            Serializator<T> exchangeType = Serializator.getInstance();
            t = exchangeType.deserialize(data);
        }
        return t;
    }

    /**
     * description: reconnect to hostChrome if disconnect.
     */
    protected void reConnect() {
        boolean bflag = true;
        int retryTimes = 0;
        while (bflag && retryTimes < RECONNECTION_TIMES) {
            try {
                retryTimes++;
                this.zk = new ZooKeeper(Common.hostChrome, SECTION_TIMEOUT, null);
                //this.zk.getData("/test0", null, null); //NOTE: HardCode

                bflag = false; // RECONNECT SUCCESS
            } catch (IOException ex) {
                log.error("this.zk cannot RECONNECT to hostpost:" + ex.getMessage());
            }
//            catch (KeeperException ex) {
//                log.error("this.zk RECONNECTED but cannot GETDATA: " + ex.getMessage());
//            } catch (InterruptedException ex) {
//                log.error("this.zk RECONNECTED but lose connection when GETDATA " + ex.getMessage());
//            }
        }
    }

    private byte[] getZnode(int userId, String path) {

        byte[] data = null;
        try {
            data = zk.getData(path, null, null);
        } catch (InterruptedException ex) {
            log.error("GET ZNODE FAIL at userId: " + userId
                    + ", at path: " + path
                    + ", ex: " + ex.getMessage());
            reConnect(); // reconnect to server
            getZnode(userId, path);
        } catch (KeeperException ex) {
            //     System.out.println("Node is not existed " + ex.getMessage());
            return data;
        }
        return data;
    }

    protected void setZk(ZooKeeper zk) {
        if (this.zk == null) {
            this.zk = zk;
        }
    }
}
