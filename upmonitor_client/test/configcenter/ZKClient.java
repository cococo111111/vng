package configcenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 *
 * @author taitt
 */
public class ZKClient implements Watcher, Runnable {

    private static final Logger _logger = Logger.getLogger(ZKClient.class);
    private ZooKeeper zk;
    private final String connectString;
    private static final int TIMEOUT = 5000;
    private Map<String, Monitor> monitorMap = null;
    private Map<String, Register> registerMap = null;
    private CountDownLatch latch = new CountDownLatch(1);
    private boolean stopping = false;

    public ZKClient(String zkConnect) throws IOException, InterruptedException,
            KeeperException {
        this.connectString = zkConnect;
        this.monitorMap = new HashMap<String, Monitor>();
        this.registerMap = new HashMap<String, Register>();
        connectZK();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!stopping) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            _logger.error("ZKClient.run error- ", e);
        }
    }

    public void stop() {
        _logger.info("ZKClient shutting down...");
        for (Monitor monitor : this.monitorMap.values()) {
            monitor.onClose();
        }
        stopping = true;
        latch.countDown();
        synchronized (this) {
            notifyAll();
        }
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException ex) {
                _logger.error("ZKClient.stop error- ", ex);
            }
        }
        _logger.info("ZKClient stop watching.");
    }

    public void addMonitor(Monitor monitor) throws KeeperException, InterruptedException {
        List<String> nodes = monitor.getNodes();
        for (String node : nodes) {
            if (node != null && !node.trim().isEmpty()) {
                _logger.info("ZKClient add monitor: " + node);
                this.monitorMap.put(node, monitor);
                processMonitor(node, true);
            }
        }
    }

    public void removeMonitor(String node) {
        if (node != null && !node.trim().isEmpty()) {
            _logger.info("ZKClient remove monitor: " + node);
            monitorMap.remove(node);
        }
    }

    public void addRegister(Register register) throws KeeperException, InterruptedException {
        if (register != null) {
            this.registerMap.put(register.node, register);
            processRegister(register);
        }
    }

    public void removeRegister(String node) {
        if (node != null && !node.trim().isEmpty()) {
            _logger.info("ZKClient remove register at node: " + node);
            monitorMap.remove(node);
        }
    }

    private synchronized void connectZK() throws IOException, InterruptedException, KeeperException {
        _logger.info("ZKClient create connection to zookeeper.");
        zk = new ZooKeeper(connectString, TIMEOUT, this);

        latch.await();
        if (stopping) {
            return;
        }

        for (String node : monitorMap.keySet()) {
            if (zk.exists(node, this) != null) {
                processMonitor(node, true);
            }
        }

        for (Register register : registerMap.values()) {
            processRegister(register);
        }

        _logger.info("ZKClient connected. Waiting for config changes.");
    }

    private synchronized void processMonitor(String node, boolean isCreate) throws KeeperException, InterruptedException {
        Data zkdata = new Data();
        zkdata.node = node;
        if (zk.exists(node, true) == null) {
            return;
        }
        zkdata.nodeData = zk.getData(node, false, null);

        List<String> listChildren = zk.getChildren(node, this);
        zkdata.childrenData = new HashMap<String, byte[]>();

        for (String child : listChildren) {
            String childnode = node + "/" + child;
            byte[] childData = zk.getData(childnode, false, null);
            zkdata.childrenData.put(childnode, childData);
        }

        Monitor monitor = this.monitorMap.get(node);
        if (monitor != null) {
            if (isCreate) {
                monitor.onCreate(zkdata);
            } else {
                monitor.onChange(zkdata);
            }
        }
    }

    private synchronized void processRegister(Register register) throws KeeperException, InterruptedException {
        CreateMode cm = CreateMode.EPHEMERAL;
        if (register.isSequential) {
            cm = CreateMode.EPHEMERAL_SEQUENTIAL;
        }
        zk.create(register.node, register.data, ZooDefs.Ids.OPEN_ACL_UNSAFE, cm);
        _logger.info("ZKClient registed service at node: " + register.node);
    }

    @Override
    public void process(WatchedEvent event) {
        _logger.info("ZKClient.process whatched event: " + event);
        if (event.getType() == Event.EventType.None) {
            if (event.getState() == Event.KeeperState.SyncConnected) {  // Important
                latch.countDown();
            } else if (event.getState() == Event.KeeperState.Expired) {
                try {
                    if (zk != null) {
                        zk.close();
                    }
                    latch = new CountDownLatch(1);
                    connectZK();
                } catch (Exception ex) {
                    _logger.error("ZKClient.process Expired error - ", ex);
                }
            }
        } else if (event.getType() == Event.EventType.NodeCreated) {
            String node = event.getPath();
            if (monitorMap.containsKey(node)) {
                try {
                    if (zk.exists(node, this) != null) {
                        processMonitor(node, true);
                    }
                } catch (Exception ex) {
                    _logger.error("ZKClient.process NodeCreated error - ", ex);
                }
            }
        } else if (event.getType() == Event.EventType.NodeDataChanged) {    // Important
            String node = event.getPath();
            if (monitorMap.containsKey(node)) {
                try {
                    processMonitor(node, false);
                } catch (Exception ex) {
                    _logger.error("ZKClient.process NodeDataChanged error - ", ex);
                }
            }
        } else {
            // event.getType() == Event.EventType.NodeChildrenChanged
            // event.getType() == Event.EventType.NodeDeleted
            _logger.error("ZKClient.process: un-catched watchs: " + event.getType());
        }
    }

    public static class Data {

        public String node;
        public byte[] nodeData;
        public Map<String, byte[]> childrenData;
    }

    public static class Register {

        public String node;
        public byte[] data;
        public boolean isSequential;

        public Register(String node, byte[] data, boolean isSequential) {
            this.node = node;
            this.data = data;
            this.isSequential = isSequential;
        }
    }

    public interface Monitor {

        public List<String> getNodes();

        public void onCreate(Data zkdata);

        public void onChange(Data zkdata);

        public void onDelete();

        public void onClose();
    }
}
