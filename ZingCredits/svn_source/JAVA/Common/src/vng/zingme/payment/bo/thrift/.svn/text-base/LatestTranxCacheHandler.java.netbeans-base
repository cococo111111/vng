/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TLatestCache;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class LatestTranxCacheHandler implements TLatestCache.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static Object lockObj = new Object();
    private static LatestTranxCacheHandler _mainInstance;
    private static GenericObjectPool _latestCacheClient;
    private final Logger log = Logger.getLogger("appActions");

    private LatestTranxCacheHandler(String host, int port) {
        _latestCacheClient = new GenericObjectPool(
                new LatestTranxClientFactory(host, port), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static LatestTranxCacheHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String latestCacheHost = System.getProperty("latestHost");
                    int latestCachePort = Integer.parseInt(System.getProperty("latestPort"));
                    _mainInstance = new LatestTranxCacheHandler(
                            latestCacheHost, latestCachePort);
                }
            }
        }
        return _mainInstance;
    }

    public static LatestTranxCacheHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new LatestTranxCacheHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TLatestCache.Client getClient() throws Exception {
        TLatestCache.Client client = null;
        locker.lock();
        try {
            client = (TLatestCache.Client) _latestCacheClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TLatestCache.Client> command, T defaultValue) {
        TLatestCache.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _latestCacheClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _latestCacheClient.invalidateObject(client);
                int invalidCount = _latestCacheClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _latestCacheClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public List<T_Transaction> get(final int userID) throws TException {
        Command<List<T_Transaction>, TLatestCache.Client> command = new Command<List<T_Transaction>, TLatestCache.Client>() {

            @Override
            public List<T_Transaction> exec(TLatestCache.Client client)
                    throws Exception {
                return client.get(userID);
            }
        };
        return this.exec(command, null);
    }

    public int put(final T_Transaction tx) throws TException {
        Command<Integer, TLatestCache.Client> command = new Command<Integer, TLatestCache.Client>() {

            @Override
            public Integer exec(TLatestCache.Client client) throws Exception {
                return client.put(tx);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int updateStat(final T_Transaction tx) throws TException {
        Command<Integer, TLatestCache.Client> command = new Command<Integer, TLatestCache.Client>() {

            @Override
            public Integer exec(TLatestCache.Client client) throws Exception {
                return client.updateStat(tx);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int updateTransactionStat(final int userID, final long tranxID, final int stat, final String description, final double currentbalance) throws TException {
        Command<Integer, TLatestCache.Client> command = new Command<Integer, TLatestCache.Client>() {

            @Override
            public Integer exec(TLatestCache.Client client) throws Exception {
                return client.updateTransactionStat(userID, tranxID, stat, description, currentbalance);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int removeTransaction(final int userID, final long tranxID) throws TException {
        Command<Integer, TLatestCache.Client> command = new Command<Integer, TLatestCache.Client>() {

            @Override
            public Integer exec(TLatestCache.Client client) throws Exception {
                return client.removeTransaction(userID, tranxID);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public T_Transaction getTransaction(final int userID, final String agentID, final String billNo) throws TException {
        Command<T_Transaction, TLatestCache.Client> command = new Command<T_Transaction, TLatestCache.Client>() {

            @Override
            public T_Transaction exec(TLatestCache.Client client) throws Exception {
                return client.getTransaction(userID, agentID, billNo);
            }
        };
        return this.exec(command, null);
    }

    public T_Transaction getTransactionByTxID(final int userID, final long txID) throws TException {
        Command<T_Transaction, TLatestCache.Client> command = new Command<T_Transaction, TLatestCache.Client>() {

            @Override
            public T_Transaction exec(TLatestCache.Client client) throws Exception {
                return client.getTransactionByTxID(userID, txID);
            }
        };
        return this.exec(command, null);
    }

    public int getTransactionStatus(final int userID, final long txID) throws TException {
        Command<Integer, TLatestCache.Client> command = new Command<Integer, TLatestCache.Client>() {

            @Override
            public Integer exec(TLatestCache.Client client) throws Exception {
                return client.getTransactionStatus(userID, txID);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }
}
