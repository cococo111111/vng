package vng.zingme.payment.bo.thrift;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TStorage;
import vng.zingme.payment.thrift.T_Account;
import vng.zingme.payment.thrift.T_Response;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class StorageHandler implements TStorage.Iface {

    private static GenericObjectPool _dbStorageClient;
    private final Logger log = Logger.getLogger("appActions");
    private static Object lockObj = new Object();
    private ReentrantLock locker = new ReentrantLock();
    private static StorageHandler _mainInstance;

    public StorageHandler(String stHost, int stPort) {

        _dbStorageClient = new GenericObjectPool(new StorageClientFactory(
                stHost, stPort), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static StorageHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String stHost = System.getProperty("storageHost");
                    int stPort = Integer.parseInt(System.getProperty("storagePort"));
                    _mainInstance = new StorageHandler(stHost, stPort);
                }
            }
        }
        return _mainInstance;
    }

    public static StorageHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new StorageHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TStorage.Client getClient() throws Exception {
        TStorage.Client client = null;
        locker.lock();
        try {
            client = (TStorage.Client) _dbStorageClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TStorage.Client> command, T defaultValue) {
        TStorage.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _dbStorageClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _dbStorageClient.invalidateObject(client);
                int invalidCount = _dbStorageClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _dbStorageClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;
    }

    public int storeTx(final T_Transaction tx) throws TException {
        Command<Integer, TStorage.Client> command = new Command<Integer, TStorage.Client>() {

            @Override
            public Integer exec(TStorage.Client client) throws Exception {
                return client.storeTx(tx);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int updateBalance(final T_Account accBalance) throws TException {
        Command<Integer, TStorage.Client> command = new Command<Integer, TStorage.Client>() {

            @Override
            public Integer exec(TStorage.Client client) throws Exception {
                return client.updateBalance(accBalance);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public double getBalance(final int userID) throws TException {
        Command<Double, TStorage.Client> command = new Command<Double, TStorage.Client>() {

            @Override
            public Double exec(TStorage.Client client) throws Exception {
                return client.getBalance(userID);
            }
        };
        return this.exec(command, -1.00).doubleValue();
    }

    public int updateTransactionStatus(final T_TranStat tranxStat) throws TException {
        Command<Integer, TStorage.Client> command = new Command<Integer, TStorage.Client>() {

            @Override
            public Integer exec(TStorage.Client client) throws Exception {
                return client.updateTransactionStatus(tranxStat);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }
}
