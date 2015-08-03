/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.latesttranx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.thrift.TException;
import org.apache.log4j.Logger;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap.EvictionListener;
import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap.EvictionPolicy;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.queue.TimedStatsDeque;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import vng.zingme.payment.bo.thrift.XCache2Handler;

import org.apache.commons.codec.binary.Base64;
import vng.zingme.payment.bo.thrift.ObjectSerialize;
import vng.zingme.util.LogUtil;
import vng.zingme.xcache2_payment.thrift.TDataResult;
import vng.zingme.xcache2_payment.thrift.TOperationPolicy;

/**
 *
 * @author root
 */
public class LatestTXCaching implements LatestTXCachingMBean, EvictionListener {

    protected volatile long totalMiss = 0;
    protected volatile long totalDelete = 0;
    protected volatile long totalSet = 0;
    protected volatile long totalGet = 0;
    protected volatile long totalEviction = 0;
    protected volatile long totalBytes = 0;
    protected volatile TimedStatsDeque hitStats = new vng.zingme.queue.TimedStatsDeque(6000);// 1
    protected volatile TimedStatsDeque missStats = new TimedStatsDeque(6000);// 1
    protected ConcurrentLinkedHashMap<Integer, Vector<byte[]>> cache = null;
    protected int maxLatest = 30;
    protected static ReentrantLock locker = new ReentrantLock();

    public LatestTXCaching(String beanName) {
        int _sz = Integer.parseInt(System.getProperty("cachesize"));
        cache = ConcurrentLinkedHashMap.create(EvictionPolicy.LRU, _sz, this);

        int _cacheOnUser = Integer.parseInt(System.getProperty("cacheonuser", "12"));
        if (_cacheOnUser > 0) {
            maxLatest = _cacheOnUser;
        }

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String mbeanName = "cache.latest-cache: type=" + beanName;
        try {
            mbs.registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        xcache = XCache2Handler.getMainInstance();
        authcode = System.getProperty("authcode", "l@t35ttx");
    }

    public void put(T_Transaction tx) throws Exception {

        Vector<byte[]> value = cache.get(tx.userID);

        //monitor
        ++totalGet;

        if (value == null) {
            value = getDataFromXCache(tx.userID);
        }

        if (value == null) {
            ++totalMiss;

            value = new Vector<byte[]>(maxLatest);

        }

        TransactionCachingOnUser.putTransaction(tx, value);

        //Monotor
        ++totalSet;

        putInternalCache(tx.userID, value);

        putXCache(tx.userID, value);

    }

    public List<T_Transaction> getTransactions(int userID) throws TException {
        if (userID <= 0) {
            log.warn("getTransactions with user:" + userID + " sfail");
            return new ArrayList<T_Transaction>();
        }

        List<T_Transaction> txs = null;

        Vector<byte[]> value = cache.get(userID);

        ++totalGet;

        if (value != null) {
            txs = TransactionCachingOnUser.getTransaction(value);
        } else {
            value = getDataFromXCache(userID);
            if (value != null) {
                txs = TransactionCachingOnUser.getTransaction(value);

                putInternalCache(userID, value);
            }
        }

        if (txs == null) {
            return new ArrayList<T_Transaction>();
        }

        return txs;
    }

    @Override
    public int getMaxItemCache() {
        // TODO Auto-generated method stub
        return cache.capacity();
    }

    @Override
    public float getPercentMiss() {
        // TODO Auto-generated method stub
        long totalHit = totalDelete + totalGet + totalSet;
        if (totalHit == 0) {
            return 0;
        }
        return totalMiss / totalHit * 100;
    }

    @Override
    public long getTotalByte() {
        // TODO Auto-generated method stub
        return totalBytes;
    }

    @Override
    public long getTotalDelete() {
        // TODO Auto-generated method stub
        return totalDelete;
    }

    @Override
    public long getTotalEviction() {
        // TODO Auto-generated method stub
        return totalEviction;
    }

    @Override
    public long getTotalGet() {
        // TODO Auto-generated method stub
        return totalGet;
    }

    @Override
    public long getTotalHit() {
        // TODO Auto-generated method stub
        return totalGet + totalSet + totalDelete;
    }

    @Override
    public long getTotalHitPerMinute() {
        // TODO Auto-generated method stub
        return hitStats.size();
    }

    @Override
    public int getTotalItem() {
        // TODO Auto-generated method stub
        return cache.size();
    }

    @Override
    public long getTotalMiss() {
        // TODO Auto-generated method stub
        return totalMiss;
    }

    @Override
    public long getTotalMissPerMinute() {
        // TODO Auto-generated method stub
        return missStats.size();
    }

    @Override
    public long getTotalSet() {
        // TODO Auto-generated method stub
        return totalSet;
    }

    @Override
    public void setMaxItemCache(int maxItemCache) {
        // TODO Auto-generated method stub
        cache.setCapacity(maxItemCache);
    }

    @Override
    public void onEviction(Object key, Object val) {
        // TODO Auto-generated method stub
        totalEviction++;
    }
    private static LatestTXCaching _instance = null;

    public static LatestTXCaching getInstance() {
        if (_instance == null) {
            locker.lock();
            try {
                if (_instance == null) {
                    _instance = new LatestTXCaching(LatestTXCaching.class.getName());
                }
            } finally {
                locker.unlock();
            }
        }
        return _instance;
    }

    public int updateTransactionStat(T_Transaction tranx) {
        return updateTransactionStat(tranx.userID, tranx.txID, tranx.responseCode, tranx.description, tranx.currentBalance);
    }

    public int updateTransactionStat(int userID, long tranxID, int stat, String description, double currentbanlce) {

        Vector<byte[]> value = cache.get(userID);
        if (value != null) {

            if (TransactionCachingOnUser.updateTransactionStat(userID, tranxID, stat, description, currentbanlce, value)) {

                putXCache(userID, value);

                return PaymentReturnCode.SUCCESS;
            }
        } else {
            value = getDataFromXCache(userID);
            if (value != null) {

                if (TransactionCachingOnUser.updateTransactionStat(userID, tranxID, stat, description, currentbanlce, value)) {

                    putInternalCache(userID, value);
                    putXCache(userID, value);

                    return PaymentReturnCode.SUCCESS;
                }
            } else {
                log.info("Cache at userID " + userID + " is null!");
            }
        }
        return PaymentReturnCode.ERROR_OPERATOR_FAIL;
    }
    Logger log = Logger.getLogger("appActions");

    public int removeTransaction(int userID, long tranxID) {
        Vector<byte[]> value = cache.get(userID);
        if (value != null) {

            if (TransactionCachingOnUser.removeTransaction(userID, tranxID, value)) {

                putXCache(userID, value);

                return PaymentReturnCode.SUCCESS;
            }
        } else {
            value = getDataFromXCache(userID);
            if (value != null) {

                if (TransactionCachingOnUser.removeTransaction(userID, tranxID, value)) {

                    putInternalCache(userID, value);
                    putXCache(userID, value);

                    return PaymentReturnCode.SUCCESS;
                }
            } else {
                log.info("Cache at userID " + userID + " is null!");
            }
        }
        return PaymentReturnCode.ERROR_OPERATOR_FAIL;
    }

    public T_Transaction getTransaction(final int userID, final String agentID, final String billNo) {
        Vector<byte[]> value = cache.get(userID);
        if (value != null) {
            return TransactionCachingOnUser.getTransaction(agentID, billNo, value);
        } else {
            value = getDataFromXCache(userID);
            if (value != null) {

                putInternalCache(userID, value);

                return TransactionCachingOnUser.getTransaction(agentID, billNo, value);

            } else {
                log.info("Cache at userID " + userID + " is null!");
                return new T_Transaction();
            }

        }
    }

    public T_Transaction getTransaction(final int userID, final long txID) {
        Vector<byte[]> value = cache.get(userID);
        if (value != null) {
            return TransactionCachingOnUser.getTransaction(txID, value);
        } else {
            value = getDataFromXCache(userID);
            if (value != null) {

                putInternalCache(userID, value);

                return TransactionCachingOnUser.getTransaction(txID, value);

            } else {
                log.info("Cache at userID " + userID + " is null!");
                return new T_Transaction();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        LatestTXCaching obj = new LatestTXCaching();

        xcache = XCache2Handler.getMainInstance("10.30.22.135", 10091);
        authcode = System.getProperty("authcode", "l@t35ttx");

        Vector<Object> v = new Vector<Object>();
        v.add("hello-xxx");
        v.add(123);
        v.add(true);
        // v.add(v);

        System.out.println(obj.deserialize(obj.serialize(v)));

        Vector<Object> vRes = (Vector<Object>) (obj.deserialize(obj.serialize(v)));

        for (Object object : vRes) {
            System.out.println(object);
        }

        xcache.put(authcode, "1001", obj.serialize(v), TOperationPolicy.OPPOL_INS_OR_UPD);

        Thread.sleep(2000);
        TDataResult data = xcache.getData("1001", TOperationPolicy.OPPOL_NONE);

        if (data != null & data.error >= 0) {
            vRes = (Vector<Object>) (obj.deserialize(data.data.value));

            for (Object object : vRes) {
                System.out.println(object);
            }
        }
    }

    private String serialize(Object o) {
        String res = "";
        synchronized (lockObj) {
            res = objSerialize.serializeS(o);
        }

        return res;
    }

    private Object deserialize(String s)
            throws IOException, ClassNotFoundException {

        // byte[] bytes = StringUtil.HexStringToByteArray(s);
        byte[] bytes = Base64.decodeBase64(s.getBytes());

        return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }
    private static XCache2Handler xcache = null;
    private static String authcode = "";

    private Vector<byte[]> getDataFromXCache(int userID) {
        Vector<byte[]> res = null;
        try {
            TDataResult data = xcache.getData(String.valueOf(userID), TOperationPolicy.OPPOL_NONE);

            if (data.error < 0) {
                res = null;
            } else {
                res = (Vector<byte[]>) deserialize(data.data.value);
            }

        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            res = null;
        }

        return res;
    }

    private void putXCache(int userID, Vector<byte[]> value) {
        try {
            xcache.put(authcode, String.valueOf(userID), serialize(value), TOperationPolicy.OPPOL_INS_OR_UPD);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
    }

    private void putInternalCache(int userID, Vector<byte[]> value) {
        cache.put(userID, value);
    }

    public LatestTXCaching() {
    }
    private static final ObjectSerialize objSerialize = new ObjectSerialize();
    private static final Object lockObj = new Object();
}
