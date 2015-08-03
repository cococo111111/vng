package vng.zingme.payment.backend;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.TZKBackEnd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ZKBackendHandler implements TZKBackEnd.Iface {

    public ZKBackendHandler() {
        _cache = new ConcurrentHashMap(4096);
    }

    public void tranxComing(long tranxID) throws TException {
        ZKBackEndMainWorker.getInstance().execJob(tranxID);
    }

    public int recoveryMissData(String adminSig) throws TException {
        /*if (AdminModel.checkAdmin(adminSig) != PaymentReturnCode.AdminCode.A_SUCCESS) {
            return PaymentReturnCode.ERROR_OPERATOR_FAIL;
        }
        ZookeeperListenerManager.coveryMissData(true);*/
        return PaymentReturnCode.SUCCESS;
    }

    public int rollbackTransaction(long tranxID) throws TException {
        Logger.getLogger("appActions").info("rollbackTransaction has been call with txID=" + tranxID);
        if (!checkAndAdd(tranxID)) {
            return PaymentReturnCode.ERROR_EXIST_KEY;
        }
        int res = ZKBackEndMainWorker.getInstance().doJob(tranxID, true, false);
        remove(tranxID);
        return res;
    }

    public int retryTransaction(long tranxID) throws TException {
        Logger.getLogger("appActions").info("retryTransaction has been call with txID=" + tranxID);
        if (!checkAndAdd(tranxID)) {
            return PaymentReturnCode.ERROR_EXIST_KEY;
        }
        return ZKBackEndMainWorker.getInstance().doJob(tranxID, false, false);
    }
    private static ConcurrentHashMap<Long, Byte> _cache;

    public static boolean checkAndAdd(long key) {
        boolean res = false;
        try {
            _locker.lock();

            if (!_cache.containsKey(key)) {
                _cache.put(key, Byte.MIN_VALUE);
                res = true;
            }

        } finally {
            _locker.unlock();
        }
        return res;
    }

    public static void remove(long key) {
        try {
            _locker.lock();

            _cache.remove(key);

        } finally {
            _locker.unlock();
        }
    }
    private static final ReentrantLock _locker = new ReentrantLock();
}
