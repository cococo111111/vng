/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clients.StorageMySQLCli;
import com.vng.jcore.cache.lruexpire.LruExpireCache;
import com.vng.jcore.common.Config;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ZData;
import model.ZNode;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author locth2
 */
public final class ZooKeeperZMonitor implements Watcher, Runnable, StatCallback {

    static ZooKeeper zk;
    Watcher chainedWatcher;
    String address = null;
    String root = "/";
//    private static LruExpireCache<String, List<String>> lruCachedString_ChildNode = null;
//    private static LruExpireCache<String, Integer> lruCachedInt_LevelNode = null;
    private static final HashMap<String, Integer> levelNode = new HashMap<>();
    private static final HashMap<String, List<String>> childNode = new HashMap<>();
    public static LruExpireCache<String, Integer> lruCachedInt_NotifyNode = null;
    private static StorageMySQLCli sqlCli = StorageMySQLCli.getInstance();
    private static final SimpleDateFormat dateFormatFull = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    private static final NotificationCenter notify = new NotificationCenter();
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZooKeeperZMonitor.class);
    private static final JmxtransFileGeneration jmxtransFG = new JmxtransFileGeneration();
    private String[] unMonitorNode = Config.getParam("zookeeper", "unmonitornode").split(",");

    public ZooKeeperZMonitor(String address) {
//        lruCachedString_ChildNode = new LruExpireCache<>(1000);
//        lruCachedInt_LevelNode = new LruExpireCache<>(1000);
        lruCachedInt_NotifyNode = new LruExpireCache<>(1000);

        sqlCli = new StorageMySQLCli();
        this.address = address;
        connectZk();
        getListsetWatcher(root, 1);
        zk.exists(System.getProperty("zname"), false, this, null);
    }

    private ZooKeeper connectZk() {
        try {
            zk = new ZooKeeper(address, 50000, this);
            int count = 0;
            while (count < 6 && !"CONNECTED".toString().equals(zk.getState().toString())) {
                try {
                    zk.getData(root, true, null);
                } catch (KeeperException ex) {
                    if (KeeperException.Code.CONNECTIONLOSS != KeeperException.Code.CONNECTIONLOSS && count != 0) {
                        Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
                        logger.error(ex.getMessage(), ex);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
                    logger.error(ex.getMessage(), ex);
                }
                try {
                    Thread.sleep(1000);
                    count++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
                    logger.error(ex.getMessage(), ex);
                }
            }
            if (!"CONNECTED".toString().equals(zk.getState().toString())) {
                zk = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
            logger.error(ex.getMessage(), ex);
            zk = null;
        }
        return zk;
    }

    private void createData(String root, String childString, int level) {
        byte[] data = null;
        File fullPath = new File(root);
        String znodeName = (fullPath.getName() != null) ? fullPath.getName() : "/";
        String znodeParent = fullPath.getParent();
        try {
            data = zk.getData(root, false, null);
            if (data != null) {
                String dataString = new String(data);
                ZNode node = new ZNode();
                node.setChild(childString);
                node.setName(znodeName);
                node.setPath(root);
                node.setParent(znodeParent);
                node.setLevel(level);
                JSONObject jsonObject = null;
                JSONParser parser = new JSONParser();
                if (dataString != null && !dataString.isEmpty()) {
                    try {
                        jsonObject = (JSONObject) parser.parse(dataString);
                        node.setDescription(jsonObject.get("description") != null ? (String) jsonObject.get("description") : "");
                        node.setZmeContacts(jsonObject.get("zme_contacts") != null ? (String) jsonObject.get("zme_contacts") : "");
                        node.setSmsContacts(jsonObject.get("sms_contacts") != null ? (String) jsonObject.get("sms_contacts") : "");
                        node.setMailContacts(jsonObject.get("mail_contacts") != null ? (String) jsonObject.get("mail_contacts") : "");
                        node.setServiceDependencies(jsonObject.get("service_dependencies") != null ? (String) jsonObject.get("service_dependencies") : "");
                        node.setExtras(jsonObject.get("extras") != null ? (String) jsonObject.get("extras") : "");
                        if (jsonObject.get("start_time") != null) {
                            Long startTime = (long) jsonObject.get("start_time");
                            node.setCreatetime(startTime.intValue());
                        }
                        node.setConfiguration((String) jsonObject.get("configuration"));
                        if (jsonObject.get("url_live") != null) {
                            node.setUrlLive(jsonObject.get("url_live").toString());
                        }
                        if (jsonObject.get("properties") != null) {
                            node.setProperties(jsonObject.get("properties").toString());
                            //Get JMX String
                            String jmxHost = "";
                            String jmxPort = "";
                            JSONArray jsonArray = new JSONArray();
                            jsonArray = (JSONArray) parser.parse(jsonObject.get("properties").toString());
                            JSONObject jsonObject1 = new JSONObject();
                            for (Object object : jsonArray) {
                                jsonObject1 = (JSONObject) parser.parse(object.toString());
                                //                            if (jsonObject1.keySet() == )
                                if (jsonObject1.containsKey("com.sun.management.jmxremote.port")) {
                                    jmxPort = (String) jsonObject1.get("com.sun.management.jmxremote.port");
                                }
                                if (jsonObject1.containsKey("java.rmi.server.hostname")) {
                                    jmxHost = (String) jsonObject1.get("java.rmi.server.hostname");
                                }
                            }
                            if (!"".equals(jmxHost) && !"".equals(jmxPort)) {
                                node.setJmxString(jmxHost + ":" + jmxPort);

                                // create json configuration file for jmxtrans
                                jmxtransFG.createFileJmxJson(jmxHost, jmxPort, root);
                            }
                        }

                        if (jsonObject.get("serverip") != null) {
                            node.setServerIp(jsonObject.get("serverip").toString());
                        }
                        if (jsonObject.get("servername") != null) {
                            node.setServerName(jsonObject.get("servername").toString());
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
                sqlCli.createNode(node);
            }
        } catch (KeeperException | InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void getListsetWatcher(String root, int level) {
        //Blacklist node, not set watcher
        for (String string : unMonitorNode) {
            if ((root + "/").startsWith(string)) {
                return;
            }
        }
        try {
            logger.info("Put watcher on node: " + root);
            List<String> children = zk.getChildren(root, true);
            String childString = JSONValue.toJSONString(children);
            createData(root, childString, level);
            childNode.put(root, children);
            levelNode.put(root, level);
            if (children != null && children.isEmpty()) {
                zk.exists(root, true);
            } else {
                for (String node : children) {
                    String pathNode = root + "/" + node;
                    if (root.equals("/")) {
                        pathNode = root + node;
                    }
                    zk.exists(root, true);
                    getListsetWatcher(pathNode, level + 1);
                }
            }
        } catch (KeeperException | InterruptedException ex) {
            Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
            logger.error(ex.getMessage(), ex);
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
        } catch (InterruptedException e) {
        }
    }

    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        String childNodeName = null;
        String path = event.getPath();
        try {
            if (path != null) {
                zk.exists(path, true);
            }
        } catch (KeeperException ex) {
            Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (event.getType()) {
            case NodeChildrenChanged:
                try {
                    List<String> childNodeCur = zk.getChildren(path, true);
                    List<String> childNodeLast = childNode.get(path);
                    if (childNodeCur.size() > childNodeLast.size()) {
                        List<String> result = new ArrayList(childNodeCur);
                        result.removeAll(childNodeLast);
                        for (String node : result) {
                            if (path.equals("/")) {
                                childNodeName = path + node;
                            } else {
                                childNodeName = path + "/" + node;
                            }
                            getListsetWatcher(childNodeName, levelNode.get(path) + 1);
                            int check_num_child_node = 0; // 	determine node new register or return!
                            if (childNode.get(childNodeName) == null) {
                                check_num_child_node = 1; // Register new node;
                            }
                            long createtime = System.currentTimeMillis() / 1000L;
                            if (check_num_child_node == 1) {
                                logger.info(path + " NodeChildrenChanged new Register: " + childNodeName);
                                notify.doNotify("Service " + childNodeName + " has sucessfully REGISTERED. " + dateFormatFull.format(createtime * 1000L) + "!", childNodeName);
                            } else {//return 
                                if (lruCachedInt_NotifyNode.get(childNodeName) != null) {
                                    notify.doNotify("Service " + childNodeName + "'s UP. " + dateFormatFull.format(createtime * 1000L) + "!", childNodeName);
                                    lruCachedInt_NotifyNode.remove(childNodeName);
                                    logger.info(path + " NodeChildrenChanged node Up: " + childNodeName);
                                } else {
                                    logger.info(path + " NodeChildrenChanged node Reborn: " + childNodeName);
                                }
                            }
                        }
                    }
                    childNode.put(path, childNodeCur);
                    sqlCli.updateChildNode(childNodeCur, path);

                } catch (KeeperException | InterruptedException ex) {
                    Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
                    logger.error(ex.getMessage(), ex);
                }
                break;
            case NodeDataChanged:
                logger.info(path + " NodeDataChanged");
//                System.out.println(path + ": NodeDataChanged");
                break;
            case NodeDeleted:
                sqlCli.updateNode(path);
                new TimerStateDown(path);
                logger.info(path + " NodeDeleted node Down!");
                break;
            case None:
                switch (event.getState()) {
                    case SyncConnected:
                        logger.info(path + " SyncConnected!");
                        break;
                    case Expired:
                        logger.info(path + " Expired!");
                        try {
                            zk.close();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ZooKeeperZMonitor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        zk = null;
                        closing(KeeperException.Code.SessionExpired);
                        connectZk();
                        break;
                }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        switch (rc) {
            case KeeperException.Code.Ok:
                logger.info("processResult " + path + " Ok!");
                break;
            case KeeperException.Code.NoNode:
                logger.info("processResult " + path + " NoNode!");
                if (path == System.getProperty("zname")) {
                    ZData data_self = new ZData();
                    try {
                        zk.create(path, data_self.data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
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
                                        zk.create(nodeList[i], "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                                    } catch (KeeperException ex2) {
                                        if (!ex.code().equals(KeeperException.Code.NODEEXISTS)) {
                                            logger.info(nodeList[i] + " existed! ... continue");
                                            continue;
                                        } else {
                                            logger.error(ex2.getMessage(), ex2);
                                        }
                                    }
                                }
                                zk.create(path, data_self.data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                            } catch (KeeperException ex1) {
                                if (!ex1.code().equals(KeeperException.Code.NODEEXISTS)) {
                                    logger.error(ex1.getMessage(), ex1);
                                }
                            } catch (InterruptedException ex1) {
                                logger.error(ex1.getMessage(), ex1);
                            }
                        } else {
                            logger.error(ex.getMessage(), ex);
                        }
                    } catch (InterruptedException ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
                break;
            case KeeperException.Code.SessionExpired:
//                logger.info("processResult " + path + " SessionExpired!");
//                closing(KeeperException.Code.SessionExpired);
//                connectZk();
                break;
            case KeeperException.Code.NoAuth:
                break;
            default:
                zk.exists(root, true, this, null);
                logger.info("processResult " + root + " set watcher default!");
        }
    }
}
