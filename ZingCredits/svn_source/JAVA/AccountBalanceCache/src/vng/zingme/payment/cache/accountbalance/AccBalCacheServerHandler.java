/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.accountbalance;

import org.apache.thrift.TException;

import vng.zingme.payment.thrift.TBalanceCaching;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Account;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class AccBalCacheServerHandler implements TBalanceCaching.Iface {

    private static AccBalCachingManager accBalCacheManager = null;

    public AccBalCacheServerHandler() {
        accBalCacheManager = AccBalCachingManager.getInstance();
    }

    @Override
    public T_AccResponse add(T_Account accBalance) throws TException {
        // TODO Auto-generated method stub
        T_AccResponse res = null;
        if (accBalCacheManager != null) {
            res = accBalCacheManager.pushMoney(accBalance);
        }
        return res;
    }

    public void warmupCache(int userID) throws TException {
        if (accBalCacheManager != null) {
            accBalCacheManager.warmupCache(userID);
        }
    }

    public T_AccResponse deduct(T_Transaction tx) throws TException {
        if (accBalCacheManager != null) {
            return accBalCacheManager.deduct(tx);
        }
        return null;
    }

    public T_AccResponse getBalance(int userID) throws TException {
        if (accBalCacheManager != null) {
            return accBalCacheManager.getBalance(userID);
        }
        return null;
    }

    @Override
    public T_AccResponse sub(int userID, double amount) throws TException {
        if (accBalCacheManager != null) {
            return accBalCacheManager.sub(userID, amount);
        }
        return null;
    }
}
