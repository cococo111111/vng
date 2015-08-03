/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class SerializeDeserializeHandler {

    private ReentrantLock locker = new ReentrantLock();
    private static SerializeDeserializeHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _mftObjectPool;
    private final Logger log = Logger.getLogger("appActions");

    private SerializeDeserializeHandler() {
        _mftObjectPool = new GenericObjectPool(new SerializeDeserializeFactory(),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static SerializeDeserializeHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new SerializeDeserializeHandler();
                }
            }
        }
        return _mainInstance;
    }

    private MEFramedTransport getObject() throws Exception {
        MEFramedTransport mftObject = null;
        locker.lock();
        try {
            mftObject = (MEFramedTransport) _mftObjectPool.borrowObject();
        } finally {
            locker.unlock();
        }
        return mftObject;
    }

    public byte[] serialize(final T_Transaction tx) {
        byte[] result = null;
        MEFramedTransport mftObject = null;
        try {
            mftObject = this.getObject();
            mftObject.resetWrite();
            result = mftObject.serialize(tx);
            _mftObjectPool.returnObject(mftObject);
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _mftObjectPool.invalidateObject(mftObject);
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return result;
    }

    public T_Transaction deserialize(final byte[] bytes) {
        T_Transaction result = null;
        MEFramedTransport mftObject = null;
        try {
            mftObject = this.getObject();
            result = mftObject.deserialize(bytes);
            _mftObjectPool.returnObject(mftObject);
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _mftObjectPool.invalidateObject(mftObject);

            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return result;
    }
}
