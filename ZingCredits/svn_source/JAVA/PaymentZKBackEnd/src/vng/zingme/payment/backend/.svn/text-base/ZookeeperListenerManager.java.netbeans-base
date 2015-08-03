package vng.zingme.payment.backend;

import vng.zingme.payment.common.worker.Worker;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.ZKBackEndType;
import vng.zingme.payment.common.zk.MultiServerZookeeper;
import vng.zingme.util.LogUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ZookeeperListenerManager implements Watcher, Runnable, ZKListener.DataMonitorListener {

    ZKListener _zkbeAdap = null;
    private static ZookeeperListenerManager _mainInstance = null;
    private static ZooKeeper _zk = null;
    private static String _parentTranxPath = null;
    private static String _nodeStat = null;

    public void process(WatchedEvent event) {
        _zkbeAdap.process(event);
    }

    public void run() {
        try {
            synchronized (this) {
                while (!_mainInstance._zkbeAdap.dead) {
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

    public void exists(byte[] data) {
        if (data != null) { //data-change comming
        }
    }

    private ZookeeperListenerManager(String znode) {
        System.out.println("I try to listen on node: " + znode);
        _zkbeAdap = new ZKListener(_zk, znode + CommonDef.FILE_SEP + _nodeStat, this);
    }

    public static ZookeeperListenerManager getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObject) {
                if (_mainInstance == null) {
                    _mainInstance = new ZookeeperListenerManager();
                }
            }
        }
        return _mainInstance;
    }

    private ZookeeperListenerManager() {
        _adapterCase = Integer.parseInt(System.getProperty("zkBEServerType"));
        if (_adapterCase == ZKBackEndType.BE_TYPE_PULL) {
            _parentTranxPath = System.getProperty("vng.zingme.payment.pull.tranx.path");
        } else {
            if (_adapterCase == ZKBackEndType.BE_TYPE_PUSH) {
                _parentTranxPath = System.getProperty("vng.zingme.payment.push.tranx.path");
            } else {
                if (_adapterCase == ZKBackEndType.BE_TYPE_BILL) {
                    _parentTranxPath = System.getProperty("vng.zingme.payment.bill.tranx.path");
                }
            }
        }
        _nodeStat = System.getProperty("zkZMNodeStat");

        try {
            _zk = MultiServerZookeeper.getInstance().newZookeeper();
            _zkbeAdap = new ZKListener(_zk, _parentTranxPath, this);
        } catch (IOException ex) {
            log.warn(ex.getMessage());
        }
    }

    public void initialize() {
        _background = ZKBackEndMainWorker.getInstance();
        _backgroundWorker = new Worker(_background);

        int num_extern = Integer.parseInt(System.getProperty("externworkers", "0"));
        if (num_extern > 0) {
            _externworker = new LinkedList<Worker>();
            for (int i = 0; i < num_extern; ++i) {
                _externworker.add(new Worker(_background));
            }
        }
    }

    public static void coveryMissData(boolean deductIgnoreFilter) {
        try {
            List<String> children = ZookeeperListenerManager.getZk().getChildren(getMainInstance().getParentTranxPath(), false);
            for (String string : children) {
                if (deductIgnoreFilter) {
                    ZKBackEndMainWorker.getInstance().doJob(Long.parseLong(string), false, false);
                } else {
                    if (ZKBackEndMainWorker.getInstance().getWorkerQueue().size()
                            > ZookeeperListenerManager.getExternworker().size()) { //it is busy
                        return;
                    }
                    ZKBackEndMainWorker.getInstance().execJob(Long.parseLong(string));
                }
            }
        } catch (KeeperException ex) {
            log.warn(ex.getMessage());
            if (ex.code() == KeeperException.Code.SESSIONEXPIRED || ex.code() == KeeperException.Code.CONNECTIONLOSS) {
                reNewZookeeper();
            }
        } catch (InterruptedException ex) {
            log.warn(ex.getMessage());
        }
    }

    public static class RecoverData implements Runnable {

        public RecoverData() {
            _sleep = Integer.parseInt(System.getProperty("covermissinterval", "60"));
            _sleep *= CommonDef.MILISECINSEC;
            isClosed = false;
        }

        public void run() {
            while (!isClosed) {
                coveryMissData(false);
                try {
                    Thread.sleep(_sleep);
                } catch (InterruptedException ex) {
                    log.warn(ex.getMessage());
                }
            }
        }
        private static int _sleep = 0;
        public boolean isClosed = false;
    }
    private static Worker _backgroundWorker = null;
    private static ZKBackEndMainWorker _background = null;
    private static List<Worker> _externworker = null;

    public static ZooKeeper getZk() {
        return _zk;
    }

    public String getParentTranxPath() {
        return _parentTranxPath;
    }

    public String getNodeStat() {
        return _nodeStat;
    }

    public ZKListener getZkbeAdap() {
        return _zkbeAdap;
    }
    private static int _adapterCase = -1;

    public Worker getBackgroundWorker() {
        return _backgroundWorker;
    }
    private static Object lockObject = new Object();
    private static final Logger log = Logger.getLogger("appActions");

    public static List<Worker> getExternworker() {
        return _externworker;
    }

    public static int getAdapterCase() {
        return _adapterCase;
    }

    public static ZooKeeper reNewZookeeper() {
        _zk = null;
        try {
            _zk = MultiServerZookeeper.getInstance().newZookeeper();
        } catch (IOException ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        return _zk;
    }
}
