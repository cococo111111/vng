/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TBalanceCaching;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Account;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class BalanceCacheHandler implements TBalanceCaching.Iface {

    private static Object lockObj = new Object();
    private ReentrantLock locker = new ReentrantLock();
    private static BalanceCacheHandler _mainInstance;
    private static Logger logger;
    private static String realm;
    private static GenericObjectPool _balanceCahceClient;
    private final Logger log = Logger.getLogger("appActions");

    private BalanceCacheHandler(String host, int port) {
        _balanceCahceClient = new GenericObjectPool(
                new BalanceCacheClientFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    private TBalanceCaching.Client getClient() throws Exception {
        TBalanceCaching.Client client = null;
        locker.lock();
        try {
            client = (TBalanceCaching.Client) _balanceCahceClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TBalanceCaching.Client> command,
            T defaultValue) {
        TBalanceCaching.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _balanceCahceClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn("ret = client fail " + LogUtil.stackTrace(e));
            try {
                _balanceCahceClient.invalidateObject(client);
                int invalidCount = _balanceCahceClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _balanceCahceClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public static BalanceCacheHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String balanceCacheHost = System.getProperty("accbalHost");
                    int balanceCachePort = Integer.parseInt(System.getProperty("accbalPort"));
                    _mainInstance = new BalanceCacheHandler(balanceCacheHost,
                            balanceCachePort);
                }
            }
        }
        return _mainInstance;
    }

    public static BalanceCacheHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new BalanceCacheHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getRealm() {
        return realm;
    }

    public void setLogger(Logger logger, String string) {
        BalanceCacheHandler.logger = logger;
        BalanceCacheHandler.realm = string;
    }

    public T_AccResponse add(final T_Account accBalance) throws TException {
        Command<T_AccResponse, TBalanceCaching.Client> command = new Command<T_AccResponse, TBalanceCaching.Client>() {

            @Override
            public T_AccResponse exec(TBalanceCaching.Client client)
                    throws Exception {
                return client.add(accBalance);
            }
        };
        return this.exec(command, null);
    }

    public T_AccResponse deduct(final T_Transaction tx) throws TException {
        Command<T_AccResponse, TBalanceCaching.Client> command = new Command<T_AccResponse, TBalanceCaching.Client>() {

            @Override
            public T_AccResponse exec(TBalanceCaching.Client client)
                    throws Exception {
                return client.deduct(tx);
            }
        };
        return this.exec(command, null);
    }

    public void warmupCache(final int userID) throws TException {
        Command<Void, TBalanceCaching.Client> command = new Command<Void, TBalanceCaching.Client>() {

            @Override
            public Void exec(TBalanceCaching.Client client) throws Exception {
                client.warmupCache(userID);
                return null;
            }
        };
        this.exec(command, null);
    }

    public T_AccResponse getBalance(final int userID) throws TException {
        Command<T_AccResponse, TBalanceCaching.Client> command = new Command<T_AccResponse, TBalanceCaching.Client>() {

            @Override
            public T_AccResponse exec(TBalanceCaching.Client client)
                    throws Exception {
                return client.getBalance(userID);
            }
        };
        return this.exec(command, null);
    }

    @Override
    public T_AccResponse sub(final int userID, final double amount) throws TException {
        Command<T_AccResponse, TBalanceCaching.Client> command = new Command<T_AccResponse, TBalanceCaching.Client>() {

            @Override
            public T_AccResponse exec(TBalanceCaching.Client client)
                    throws Exception {
                return client.sub(userID, amount);
            }
        };
        return this.exec(command, null);
    }
}
