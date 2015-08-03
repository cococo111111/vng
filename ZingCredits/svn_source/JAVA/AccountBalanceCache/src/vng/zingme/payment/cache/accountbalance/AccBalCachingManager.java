package vng.zingme.payment.cache.accountbalance;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.thrift.TException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import vng.zingme.payment.bo.thrift.BackEndHandler;
import vng.zingme.payment.bo.thrift.MEFramedTransport;
import vng.zingme.payment.bo.thrift.StorageHandler;
import vng.zingme.payment.common.CommonDef;

import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.ResponseCode;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Account;
import vng.zingme.payment.thrift.T_Transaction;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import vng.zingme.payment.bo.thrift.SerializeDeserializeHandler;
import vng.zingme.payment.common.worker.Worker;
import vng.zingme.payment.common.zk.MultiServerZookeeper;
import vng.zingme.payment.common.zk.ZKUtil;
import vng.zingme.util.LogUtil;

public class AccBalCachingManager {

    private AccBalCache cacher = null;
    private String _host = null;
    private int _port = 0;
    private String _path = "";
    private int _eventmode = 0;
    private ZooKeeper zk = null;

    public AccBalCachingManager(int lockCapacity) {
        cacher = AccBalCache.getInstance();

        _host = System.getProperty("storageHost", "localhost");
        _port = Integer.parseInt(System.getProperty("storagePort", "9702"));

        _path = System.getProperty("vng.zingme.payment.bill.tranx.path");
        _eventmode = Integer.parseInt(System.getProperty("eventmode", "0"));

        int hop_count = 5;
        while (zk == null && hop_count > 0) {
            try {
                zk = MultiServerZookeeper.getInstance().newZookeeper();
            } catch (IOException ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hop_count;
        }

        beHandler = BackEndHandler.getMainInstance();
        stHandler = StorageHandler.getMainInstance(_host, _port);

        ZKUtil.createZKPath(zk, _path, "vng-sig");

        _zkwriter = new ZKWriterWorker();
        _zkwriterworker = new Worker(_zkwriter);
    }
    private final Logger log = Logger.getLogger("appActions");
    private static BackEndHandler beHandler = null;
    private static StorageHandler stHandler = null;

    public T_AccResponse deduct(T_Transaction tx) {

        T_AccResponse res = new T_AccResponse(PaymentReturnCode.BalCacheCode.E_ERROR, 0.0);

        if (cacher != null) {
            res = cacher.sub(tx.userID, tx.amount);
        }

        if (res.code == PaymentReturnCode.BalCacheCode.E_SUCCESS) {

            if (zk != null) {

                tx.currentBalance = res.currentBalance + tx.amount;

                _zkwriterworker.work(tx);

            } else {
                res.code = PaymentReturnCode.ERROR_OPERATOR_FAIL;
                addUtilSuccess(tx.userID, tx.amount);
            }
        }
        return res;
    }

    private T_AccResponse addUtilSuccess(int userID, Double balance) {
        T_AccResponse res = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, -1.0);
        while (res.code != PaymentReturnCode.BalCacheCode.E_SUCCESS) {
            res = cacher.add(userID, balance);
        }
        return res;
    }

    public T_AccResponse pushMoney(T_Account account) {

        T_AccResponse res = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, -1.0);

        if (cacher == null) {
            return res;
        }

        if (!warmupCache(account.userID)) {
            return res;
        }

        int ret = ResponseCode.DB_ERROR;
        try {
            ret = stHandler.updateBalance(account);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            ret = ResponseCode.DB_ERROR;
        }

        if (ret == ResponseCode.UPDATE_SUCCESS) {
            res = addUtilSuccess(account.userID, account.amount);
        }
        res.code = ret;

        return res;
    }

    public boolean warmupCache(int userID) {

        Double cbalance = cacher.get(userID);

        if (cbalance != null) {
            return true;
        }

        double bbalance = -1.0;

        int hope_count = 5;
        while (bbalance == -1.0 && hope_count > 0) {
            try {
                bbalance = stHandler.getBalance(userID);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
                bbalance = -1.0;
            }
            --hope_count;
        }

        if (bbalance == ResponseCode.KEY_NOTEXIST) {
            bbalance = 0.0;
        }

        if (bbalance >= 0) {
            AccBalCache.PutResult pRes = cacher.warmup(userID, bbalance);
            return pRes.state;
        }
        return false;
    }

    public T_AccResponse getBalance(int userID) {

        T_AccResponse res = new T_AccResponse(PaymentReturnCode.ERROR_OPERATOR_FAIL, -1.0);

        if (cacher == null) {
            return res;
        }

        Double cbalance = cacher.get(userID);

        if (cbalance != null) {
            return new T_AccResponse(PaymentReturnCode.SUCCESS, cbalance.doubleValue());
        }
        if (warmupCache(userID)) {
            return new T_AccResponse(PaymentReturnCode.SUCCESS, cacher.get(userID));
        }
        log.warn("Fail: storage fail or internal-cache fail! userId = " + userID);
        return res;
    }

    public static AccBalCachingManager getInstance() {
        if (_instance == null) {
            locker.lock();
            try {
                if (_instance == null) {
                    int lockCapacity = Integer.parseInt(System.getProperty("lockcapacity", "1000"));
                    _instance = new AccBalCachingManager(lockCapacity);
                }
            } finally {
                locker.unlock();
            }
        }
        return _instance;
    }
    protected static ReentrantLock locker = new ReentrantLock();
    private static AccBalCachingManager _instance = null;

    public boolean writeToZookeeper(T_Transaction tx) {
        byte[] _bdata = null;
        _bdata = serhandler.serialize(tx).clone();

        if (_bdata == null) {
            return false;
        }
        boolean res = false;

        try {
            zk.create(_path + CommonDef.FILE_SEP + tx.txID, _bdata, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            if (_eventmode != 0) {
                try {
                    beHandler.tranxComing(tx.txID);
                } catch (TException ex) {
                    log.warn("beHandler.transfer(" + tx.txID + ") " + ex.getMessage());
                }
            }
            res = true;
        } catch (Exception ex) {
            log.warn("zk.create fail " + ex.getMessage());

            try {
                zk.close();
            } catch (Exception except) {
                log.warn(LogUtil.stackTrace(except));
            }
            zk = null;
            try {
                zk = MultiServerZookeeper.getInstance().newZookeeper();
            } catch (IOException ex1) {
                log.warn(ex1);
            }
        }
        return res;
    }
    
    public T_AccResponse sub(int userID, double amount) {

        T_AccResponse res = new T_AccResponse(PaymentReturnCode.BalCacheCode.E_ERROR, 0.0);

        if (cacher != null) {
            res = cacher.sub(userID, amount);
        }
        return res;
    }
    
    private static Worker _zkwriterworker = null;
    private static ZKWriterWorker _zkwriter = null;
    private static final SerializeDeserializeHandler serhandler = SerializeDeserializeHandler.getMainInstance();
}
