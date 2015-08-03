/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upzkclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import model.ZData;

/**
 *
 * @author locth2
 */
public final class ZooKeeperZClient implements Watcher, Runnable, StatCallback {

    static ZooKeeper zk = null;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZooKeeperZClient.class);
    String znode;
    String address;
    ZData data;

    public ZooKeeperZClient(String znode, String address) {
        this.znode = znode.toLowerCase();
        this.address = address;
        connectZk();
    }

    private void connectZk() {
        if (zk == null) {
            try {
                zk = new ZooKeeper(address, 50000, this);
                int count = 0;
                while (count < 5 && !"CONNECTED".toString().equals(zk.getState().toString())) {
                    try {
                        zk.getData("/", false, null);
                    } catch (KeeperException ex) {
                        if (ex.code().CONNECTIONLOSS != KeeperException.Code.CONNECTIONLOSS && count != 0) {
                            Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                            logger.error(ex.getMessage(), ex);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                        logger.error(ex.getMessage(), ex);
                    }
                    try {
                        Thread.sleep(1000);
                        count++;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                        logger.error(ex.getMessage(), ex);
                    }
                }
                if ("CONNECTED".toString().equals(zk.getState().toString())) {
                    zk.exists(znode, false, this, null);
                } else {
                    logger.error("Cannot contact zookeeper server or Node" + znode.split("/")[1] + ".Please contact UCOM!");
                    System.exit(-1);
                }
            } catch (IOException ex) {
                Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                logger.error(ex.getMessage(), ex);
                zk = null;
            }
        }
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
            logger.error(ex.getMessage(), ex);
        }
    }

    private void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    System.out.println("Connected to ZKserver");
                    break;
                case Expired:
                    closing(KeeperException.Code.SessionExpired);
                    connectZk();
                    break;
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case KeeperException.Code.Ok:
                exists = true;
                System.out.println("Node is not available!");
                break;
            case KeeperException.Code.NoNode:
                exists = false;
                data = new ZData();
                try {
                    zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (KeeperException ex) {
                    if (ex.code().equals(KeeperException.Code.NONODE)) {
                        try {
                            KeeperException.Code tmp = null;
                            tmp = KeeperException.Code.NONODE;

                            //Create List node need to create with persistent
                            String[] nodeList = path.split("/");
                            for (int i = 1; i < nodeList.length - 1; i++) {
                                nodeList[i] = nodeList[i - 1] + "/" + nodeList[i];
                                String tmp1 = nodeList[i];
                                try {
                                    zk.create(nodeList[i], "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                                } catch (KeeperException ex2) {
                                    if (!ex.code().equals(KeeperException.Code.NODEEXISTS)) {
                                        Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex2);
                                        logger.error(ex2.getMessage(), ex2);
                                        continue;
                                    }
                                }
                            }
                            zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                        } catch (KeeperException ex1) {
                            if (!ex1.code().equals(KeeperException.Code.NODEEXISTS)) {
                                Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex1);
                                logger.error(ex1.getMessage(), ex1);
                            }
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex1);
                            logger.error(ex1.getMessage(), ex1);
                        }
                    } else {
                        Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                        logger.error(ex.getMessage(), ex);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ZooKeeperZClient.class.getName()).log(Level.SEVERE, null, ex);
                    logger.error(ex.getMessage(), ex);
                }
                break;
            case KeeperException.Code.SessionExpired:
                closing(rc);
                connectZk();

            default:
                zk.exists(znode, false, this, null);
        }
    }
}
