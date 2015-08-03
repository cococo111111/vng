//  16/04/2014
/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upzkclient;

import com.vng.jcore.cache.lruexpire.LruExpireCache;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import model.ZData;
import org.apache.log4j.Logger;

/**
 *
 * @author locth2
 */
public final class UpClient implements Watcher, Runnable, StatCallback {

    private static final Logger logger = Logger.getLogger(UpClient.class);
    private ZooKeeper zk;
    private final String address;
    private static final int TIMEOUT = 5000;
    private CountDownLatch latch = new CountDownLatch(1);
    private static String znode;
    private ZData data;
    private static final int max_retry = 3;
    private static final LruExpireCache<Integer, Integer> cache_retry = new LruExpireCache<>(1);

    public UpClient(String znode, String address) {
        UpClient.znode = znode.toLowerCase().trim();
        this.address = address;
        connectZk();
    }

    private synchronized void connectZk() {
        logger.info("UpClient create connection to zookeeper");
        try {
            zk = new ZooKeeper(address, TIMEOUT, this);
            latch.await();
            zk.exists(znode, true, this, null);
        } catch (IOException | InterruptedException ex) {
            logger.error("UpClient.conectZk error - connect to zookeeper|node " + znode + ":" + ex.getMessage(), ex);
        }
        logger.info("UpClient.connectZk: Connected");
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException ex) {
            logger.error("UpClient.run error: " + ex.getMessage(), ex);
        }
    }

    // if reconnect try is greater than  max_retry times/1hour , client will stop reconnect. Need to restart apps
    private void reConnectZk() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
        latch = new CountDownLatch(1);
        Integer retry = cache_retry.get(0);
        if (retry == null) {
            cache_retry.put(0, 0, 3600000L);
            retry = 0;
        }
        if (retry < max_retry) {
            retry++;
            cache_retry.put(0, retry, 3600000L);
            connectZk();
            logger.info("UpClient.reconnectZk: retry " + retry + " times");
        } else {
            logger.info("UpClient.reconnectZk: retry " + retry + " times. Do not reconnect any more!");
        }
    }

    // process event 
    @Override
    public void process(WatchedEvent event) {
        logger.info("UpClient.process watched event: " + event);
        if (event.getType() == Event.EventType.None) {
            if (event.getState() == Event.KeeperState.SyncConnected) {  // Important
                latch.countDown();
            } else if (event.getState() == Event.KeeperState.Expired) {
                try {
                    logger.info("UpClient.process Session Expired. Reconnect ... ");
                    reConnectZk();
                } catch (InterruptedException ex) {
                    logger.error("UpClient.process Expired error: ", ex);
                }
            }
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            try {
                logger.info("UpClient.process Node Deleted. Reconnect ... ");
                reConnectZk();
            } catch (InterruptedException ex) {
                logger.error("UpClient.process NodeDeleted error: ", ex);
            }
        } else {
            logger.warn("UpClient.process: un-catched watchs: " + event.getType());
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        logger.info("UpClient.prcessResult:  " + path + ". " + rc + " = " + KeeperException.Code.get(rc));
        switch (rc) {
            case KeeperException.Code.NodeExists:
                logger.error("UpClient.processResult NodeExits: " + path);
                break;
            case KeeperException.Code.NoNode:
                logger.info("UpClient.processResult NoNode: " + path + ". Create data on node");
                createNode(path);
                break;
            case KeeperException.Code.Ok:
                logger.warn("UpClient.processResult NodeExits: " + path);
                break;
            default:
                zk.exists(znode, true, this, null);
        }
    }

    //Create data & node on zookeeper
    void createNode(String path) {
        data = new ZData();
        try {
            zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info("UpClient.createNode: " + path + " REGISTERED SUCCESSFULLY!");
        } catch (KeeperException ex) {
            if (ex.code().equals(KeeperException.Code.NONODE)) {
                try {
                    //Create List node need to create with persistent
                    String[] nodeList = path.split("/");
                    for (int i = 1; i < nodeList.length - 1; i++) {
                        nodeList[i] = nodeList[i - 1] + "/" + nodeList[i];
                        try {
                            zk.create(nodeList[i], "locth2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                        } catch (KeeperException ex2) {
                            if (ex2.code().equals(KeeperException.Code.NODEEXISTS)) {
                                logger.info("UpClient.createNode PERSISTENT : " + nodeList[i] + " existed! ... continue");
                            } else if (ex2.code().equals(KeeperException.Code.NOCHILDRENFOREPHEMERALS)) {
                                logger.error("UpClient.createNode PERSISTENT - NOCHILDRENFOREPHEMERALS error: " + nodeList[i] + ", stop create data.");
                            } else {
                                logger.error("UpClient.createNode PERSISTENT - NodeExits error: " + ex2.getMessage(), ex2);
                            }
                        }
                    }
                    zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    zk.exists(znode, true);
                    logger.info("UpClient.createNode EPHEMERAL: " + path + " REGISTERED SUCCESSFULLY");
                } catch (KeeperException ex1) {
                    logger.error("UpClient.createNode EPHEMERAL  - NodeExits error: " + ex1.getMessage(), ex1);
                } catch (InterruptedException ex1) {
                    logger.error("UpClient.createNode  EPHEMERAL- Interrupted error: " + ex1.getMessage(), ex1);
                }
            } else {
                logger.error("UpClient.createNode - NodeExits error: " + ex.getMessage(), ex);
            }
        } catch (InterruptedException ex) {
            logger.error("UpClient.createNode - Interrupted error: " + ex.getMessage(), ex);
        }
    }
}
