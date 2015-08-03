/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.accountbalance;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.lang.management.ManagementFactory;
import java.util.concurrent.locks.ReentrantLock;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.queue.TimedStatsDeque;

/**
 *
 * @author root
 */
public class AccBalCache implements AccBalCacheMBean {

    public class PutResult {

        public PutResult() {
        }

        public PutResult(boolean state, Double balance) {
            this.state = state;
            this.balance = balance;
        }
        public boolean state;
        public Double balance;
    }
    protected volatile long totalMiss = 0;
    protected volatile long totalSetMiss = 0;
    protected volatile long totalDelete = 0;
    protected volatile long totalSet = 0;
    protected volatile long totalGet = 0;
    protected volatile long totalEviction = 0;
    protected volatile long totalBytes = 0;
    protected volatile TimedStatsDeque hitStats = new vng.zingme.queue.TimedStatsDeque(6000);// 1
    protected volatile TimedStatsDeque missStats = new TimedStatsDeque(6000);// 1
    protected volatile TimedStatsDeque setMissStats = new TimedStatsDeque(6000);// 1
    protected static ReentrantLock locker = new ReentrantLock();
    private static AccBalCache _instance = null;
    private static ConcurrentLinkedHashMap<Integer, Double> _cache = null;

    public AccBalCache(int lockCapacity) {
        int _sz = Integer.parseInt(System.getProperty("cachesize"));
        _cache = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, _sz);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String mbeanName = "vng.zingme.payment.cache.accountbalance: type=" + AccBalCache.class.getSimpleName();
        try {
            mbs.registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static AccBalCache getInstance() {
        if (_instance == null) {
            locker.lock();
            try {
                if (_instance == null) {
                    int lockCapacity = Integer.parseInt(System.getProperty("lockcapacity", "1000"));
                    _instance = new AccBalCache(lockCapacity);
                }
            } finally {
                locker.unlock();
            }
        }
        return _instance;
    }

    public Double get(int userID) {
        totalGet++;

        Double balance = null;

        locker.lock();
        try {
            balance = _cache.get(userID);
        } catch (Exception ex) {
            balance = null;
        } finally {
            locker.unlock();
        }
        if (balance == null) {
            totalMiss++;
        }
        return balance;
    }

    @Override
    public int getMaxItemCache() {
        // TODO Auto-generated method stub
        return _cache.capacity();
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
        totalBytes = getTotalItem() * 20;
        return totalBytes;
    }

    @Override
    public long getTotalDelete() {
        // TODO Auto-generated method stub
        return totalDelete;
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
        return _cache.size();
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
        _cache.setCapacity(maxItemCache);
    }

    public long getTotalEviction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //@Override
    public long getTotalSetMiss() {
        // TODO Auto-generated method stub
        return totalSetMiss;
    }

    //@Override
    public long getTotalMissSetPerMinute() {
        // TODO Auto-generated method stub
        return setMissStats.size();
    }

    public T_AccResponse sub(int userID, Double balance) {
        T_AccResponse accRes = new T_AccResponse(PaymentReturnCode.BalCacheCode.E_ERROR, 0.0);

        ++totalSubOperator;
        long tmpsub = System.nanoTime();

        locker.lock();
        try {
            Double currbalance = _cache.get(userID);
            if (currbalance == null) {
                accRes.code = PaymentReturnCode.BalCacheCode.E_ERROR_NOT_EXIST_KEY;
            } else {
                if (currbalance >= balance) {
                    currbalance -= balance;
                    _cache.put(userID, currbalance);
                    ++totalSet;
                    accRes.code = PaymentReturnCode.BalCacheCode.E_SUCCESS;
                } else {
                    accRes.code = PaymentReturnCode.BalCacheCode.E_NOT_ENOUGH_TO_SUD;
                }
                accRes.currentBalance = currbalance;
            }
        } catch (Exception ex) {
        } finally {
            locker.unlock();
        }

        lastSub = ((System.nanoTime() - tmpsub) / 1000);
        totalSubMicroSec += lastSub;

        return accRes;
    }

    public T_AccResponse add(int userID, Double balance) {
        T_AccResponse accRes = new T_AccResponse(PaymentReturnCode.BalCacheCode.E_ERROR, 0.0);

        ++totalAddOperator;
        long tmpadd = System.nanoTime();

        locker.lock();
        try {
            Double currbalance = _cache.get(userID);
            if (currbalance == null) {
                accRes.code = PaymentReturnCode.BalCacheCode.E_ERROR_NOT_EXIST_KEY;
            } else {
                currbalance += balance;
                _cache.put(userID, currbalance);
                ++totalSet;
                accRes.code = PaymentReturnCode.BalCacheCode.E_SUCCESS;
                accRes.currentBalance = currbalance;
            }
        } catch (Exception ex) {
        } finally {
            locker.unlock();
        }

        lastAdd = ((System.nanoTime() - tmpadd) / 1000);
        totalAddMicroSec += lastAdd;

        return accRes;
    }

    public PutResult warmup(int userID, Double balance) {
        PutResult res = new PutResult(false, null);

        locker.lock();
        try {
            Double currbalance = _cache.get(userID);
            if (currbalance != null) {
                res.balance = currbalance;
                res.state = true;
            } else {
                _cache.put(userID, balance);
                ++totalSet;
                res.state = true;
                res.balance = balance;
            }
        } catch (Exception ex) {
        } finally {
            locker.unlock();
        }

        return res;
    }
    private volatile long totalAddOperator = 0;
    private volatile long totalAddMicroSec = 0;
    private volatile long totalSubOperator = 0;
    private volatile long totalSubMicroSec = 0;
    private volatile long lastAdd = 0;
    private volatile long lastSub = 0;

    public double getAddRate() {
        return ((totalAddOperator * (double) 1000000.0) / totalAddMicroSec);
    }

    public double getSubRate() {
        return ((totalSubOperator * (double) 1000000.0) / totalSubMicroSec);
    }

    public double getLastAddTime() {
        return lastAdd / (double) 1000000.0;
    }

    public double getLastSubTime() {
        return lastSub / (double) 1000000.0;
    }
}
