package vng.zingme.payment.backend;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.AsyncCallback.ChildrenCallback;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ZKListener implements Watcher, DataCallback, StatCallback, ChildrenCallback {

    private ZKListener() {
    }

    public ZKListener(ZooKeeper zk, String znode, DataMonitorListener listener) {
        this.znode = znode;
        this.listener = listener;
        // Get things started by checking if the node exists. We are going
        // to be completely event driven
        ZookeeperListenerManager.getZk().exists(znode, true, this, null);
        _eventmode = Integer.parseInt(System.getProperty("eventmode", "0"));
        if (_eventmode == 0) {
            ZookeeperListenerManager.getZk().getChildren(znode, true, this, null);
        }
    }
    String znode;
    private static HashMap<String, Watcher> chainedWatcher = new HashMap<String, Watcher>();
    boolean dead;
    DataMonitorListener listener;
    byte prevData[];

    /**
     * Other classes use the DataMonitor by implementing this method
     */
    public interface DataMonitorListener {

        /**
         * The existence status of the node has changed.
         */
        void exists(byte data[]);

        /**
         * The ZooKeeper session is no longer valid.
         *
         * @param rc
         *            the ZooKeeper reason code
         */
        void closing(int rc);
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            // We are are being told that the state of the
            // connection has changed
            switch (event.getState()) {
                case SyncConnected:
                    // In this particular example we don't need to do anything
                    // here - watches are automatically re-registered with
                    // server and any watches triggered while the client was
                    // disconnected will be delivered (in order of course)
                    break;
                case Expired:
                    // It's all over
                    dead = true;
                    listener.closing(KeeperException.Code.SessionExpired);
                    break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                // Something has changed on the node, let's find out
                ZookeeperListenerManager.getZk().exists(znode, true, this, null);
                if (_eventmode == 0) {
                    ZookeeperListenerManager.getZk().getChildren(znode, true, this, null); // fire children change event
                }

            }
        }
        if (chainedWatcher != null) {
            Watcher watcher = chainedWatcher.get(path);
            if (watcher != null) {
                watcher.process(event);
            }
        }
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case KeeperException.Code.Ok:
                exists = true;
                break;
            case KeeperException.Code.NoNode:
                exists = false;
                break;
            case KeeperException.Code.SessionExpired:
            case KeeperException.Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                // Retry errors
                ZookeeperListenerManager.getZk().exists(znode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            try {
                b = ZookeeperListenerManager.getZk().getData(znode, false, null);
            } catch (KeeperException e) {
                // We don't need to worry about recovering now. The watch
                // callbacks will kick off any exception handling
            } catch (InterruptedException e) {
                return;
            }
        }
        if ((b != null && !Arrays.equals(prevData, b))) {
            listener.exists(b);
            prevData = b;
        }
    }

    public void processResult(int rc, String path, Object ctx,
            List<String> children) {
        // TODO Auto-generated method stub
        for (String child : children) {
            if (!ZKBackEndMainWorker.getInstance().getFilter().containsKey(child)) {
                ZookeeperListenerManager.getMainInstance().getBackgroundWorker().work(child);
                ZKBackEndMainWorker.getInstance().getFilter().put(Long.parseLong(child), Byte.MIN_VALUE);
            }
        }
    }

    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HashMap<String, Watcher> getChainedWatcher() {
        return chainedWatcher;
    }
    private static int _eventmode = -1;
}
