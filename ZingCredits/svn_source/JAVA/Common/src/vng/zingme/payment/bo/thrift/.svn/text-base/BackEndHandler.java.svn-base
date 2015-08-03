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
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TZKBackEnd;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class BackEndHandler implements TZKBackEnd.Iface {

    private static Object lockObj = new Object();
    private ReentrantLock locker = new ReentrantLock();
    private static BackEndHandler _mainInstance;
    private static GenericObjectPool _backendClient;
    private final Logger log = Logger.getLogger("appActions");

    private BackEndHandler(String host, int port) {
        _backendClient = new GenericObjectPool(new BackEndClientFactory(host,
                port), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static BackEndHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String frHost = System.getProperty("zkBEHost", "localhost");
                    int frPort = Integer.parseInt(System.getProperty(
                            "zkBEPort", "4116"));
                    _mainInstance = new BackEndHandler(frHost, frPort);
                }
            }
        }
        return _mainInstance;
    }

    public static BackEndHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new BackEndHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TZKBackEnd.Client getClient() throws Exception {
        TZKBackEnd.Client client = null;
        locker.lock();
        try {
            client = (TZKBackEnd.Client) _backendClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TZKBackEnd.Client> command, T defaultValue) {
        TZKBackEnd.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _backendClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _backendClient.invalidateObject(client);
                int invalidCount = _backendClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _backendClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public void tranxComing(final long tranxID) throws TException {
        Command<Void, TZKBackEnd.Client> command = new Command<Void, TZKBackEnd.Client>() {

            @Override
            public Void exec(TZKBackEnd.Client client) throws Exception {
                client.tranxComing(tranxID);
                return null;
            }
        };
        this.exec(command, null);
    }

    public int recoveryMissData(final String adminSig) throws TException {
        Command<Integer, TZKBackEnd.Client> command = new Command<Integer, TZKBackEnd.Client>() {

            @Override
            public Integer exec(TZKBackEnd.Client client) throws Exception {
                return client.recoveryMissData(adminSig);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int rollbackTransaction(final long tranxID) throws TException {
        Command<Integer, TZKBackEnd.Client> command = new Command<Integer, TZKBackEnd.Client>() {

            @Override
            public Integer exec(TZKBackEnd.Client client) throws Exception {
                return client.rollbackTransaction(tranxID);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int retryTransaction(final long tranxID) throws TException {
        Command<Integer, TZKBackEnd.Client> command = new Command<Integer, TZKBackEnd.Client>() {

            @Override
            public Integer exec(TZKBackEnd.Client client) throws Exception {
                return client.retryTransaction(tranxID);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }
}
