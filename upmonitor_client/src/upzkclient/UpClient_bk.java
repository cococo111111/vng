// /* To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package upzkclient;
//
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.zookeeper.AsyncCallback.StatCallback;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooDefs.Ids;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.Stat;
//import model.ZData;
//
///**
// *
// * @author locth2
// */
//public final class UpClient implements Watcher, Runnable, StatCallback {
//
//    static ZooKeeper zk = null;
//    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UpClient.class);
//    String znode;
//    String address;
//    ZData data;
//
//    public UpClient(String znode, String address) {
//        this.znode = znode.toLowerCase();
//        this.address = address;
//        connectZk();
//    }
//
//    private void connectZk() {
//        if (zk == null) {
//            logger.info("Begin to connect Upmonitor.");
//            try {
//                zk = new ZooKeeper(address, 50000, this);
//                int count = 0;
//                while (zk != null && count < 5 && !"CONNECTED".toString().equals(zk.getState().toString())) {
//                    try {
//                        zk.getData("/", false, null);
//                    } catch (KeeperException ex) {
//                        if (ex.code().CONNECTIONLOSS != KeeperException.Code.CONNECTIONLOSS && count != 0) {
//                            logger.error(ex.getMessage(), ex);
//                        }
//                    } catch (InterruptedException ex) {
//                        logger.error(ex.getMessage(), ex);
//                    }
//                    try {
//                        Thread.sleep(1000);
//                        count++;
//                    } catch (InterruptedException ex) {
//                        logger.error(ex.getMessage(), ex);
//                    }
//                }
//                if (zk != null && "CONNECTED".toString().equals(zk.getState().toString())) {
//                    zk.exists(znode, false, this, null);
//                } else {
//                    logger.error("Cannot contact zookeeper server or Node" + znode.split("/")[1] + ".Please contact UCOM!");
//                }
//            } catch (IOException ex) {
//                logger.error(ex.getMessage(), ex);
//                zk = null;
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        try {
//            synchronized (this) {
//                while (true) {
//                   wait();
//                }
//            }
//        } catch (InterruptedException ex) {
//            logger.error(ex.getMessage(), ex);
//        }
//    }
//
//    private void closing() {
//        try {
//            zk.close();
//            zk = null;
//            Thread.sleep(3000L);
//        } catch (InterruptedException ex) {
//            logger.error("Cannot closing ZK connection.");
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        logger.info("Process: " + event.getPath() + ". type=" + event.getType() + ", state= " + event.getState());
//        if (event.getType() == Event.EventType.None) {
//            switch (event.getState()) {
//                case SyncConnected:
//                    logger.info("Connected to ZkServer!");
//                    break;
//                case Expired:
//                    logger.info("Session expired or disconnected, close connection and reconnect new one.");
//                    closing();
//                    connectZk();
//                    break;
//            }
//        }
//        if (event.getType() == Event.EventType.NodeDeleted) {
//            closing();
//            connectZk();
//        }
//    }
//
//    @Override
//    public void processResult(int rc, String path, Object ctx, Stat stat) {
//        logger.info("Process result:  " + path + ". " + rc + " = " + KeeperException.Code.get(rc)
//        );
//        switch (rc) {
//            case KeeperException.Code.NodeExists:
//                break;
//            case KeeperException.Code.Ok:
//                if (!path.equals("/")) {
//                    try {
//                        zk.exists(znode, true);
//                    } catch (KeeperException ex) {
//                        logger.error("Cannot set watcher on " + path + " " + ex.getMessage(), ex);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(UpClient.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                break;
//
//            case KeeperException.Code.NoNode:
//                createNode(path);
//                break;
//            default:
//                zk.exists(znode, false, this, null);
//        }
//    }
//
//    void createNode(String path
//    ) {
//        data = new ZData();
//        try {
//            zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            logger.info(path + " REGISTERED SUCCESSFULLY!");
//        } catch (KeeperException ex) {
//            if (ex.code().equals(KeeperException.Code.NONODE)) {
//                try {
//                    //Create List node need to create with persistent
//                    String[] nodeList = path.split("/");
//                    for (int i = 1; i < nodeList.length - 1; i++) {
//                        nodeList[i] = nodeList[i - 1] + "/" + nodeList[i];
//                        try {
//                            zk.create(nodeList[i], "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//                        } catch (KeeperException ex2) {
//                            if (!ex.code().equals(KeeperException.Code.NODEEXISTS)) {
//                                logger.info(nodeList[i] + " existed! ... continue");
//                                continue;
//                            } else {
//                                logger.error(ex2.getMessage(), ex2);
//                            }
//                        }
//                    }
//                    zk.create(znode, data.data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//                    logger.info(path + " REGISTERED SUCCESSFULLY!");
//                } catch (KeeperException ex1) {
//                    if (!ex1.code().equals(KeeperException.Code.NODEEXISTS)) {
//                        logger.error(ex1.getMessage(), ex1);
//                    }
//                } catch (InterruptedException ex1) {
//                    logger.error(ex1.getMessage(), ex1);
//                }
//            } else {
//                logger.error(ex.getMessage(), ex);
//            }
//        } catch (InterruptedException ex) {
//            logger.error(ex.getMessage(), ex);
//        }
//    }
//}
