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
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TPayment;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Response;
import vng.zingme.payment.thrift.T_Token;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class PaymentGatewayHandler implements TPayment.Iface {

    private static GenericObjectPool _paymentgatewayClient;
    private final Logger log = Logger.getLogger("appActions");
    private static Object lockObj = new Object();
    private static ReentrantLock locker = new ReentrantLock();
    private static PaymentGatewayHandler _mainInstance;

    public PaymentGatewayHandler(String host, int port) {
        _paymentgatewayClient = new GenericObjectPool(new PaymentClientFactory(
                host, port), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static PaymentGatewayHandler getMainInstance() {
        locker.lock();
        try {
            if (_mainInstance == null) {
                synchronized (lockObj) {
                    String pgHost = System.getProperty("pgHost");
                    int pgPort = Integer.parseInt(System.getProperty("pgPort"));
                    _mainInstance = new PaymentGatewayHandler(pgHost, pgPort);
                }
            }
        } finally {
            locker.unlock();
        }
        return _mainInstance;
    }

    public static PaymentGatewayHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new PaymentGatewayHandler(host, port);
                }

            }
        }
        return _mainInstance;
    }

    private TPayment.Client getClient() throws Exception {
        TPayment.Client client = null;
        locker.lock();
        try {
            client = (TPayment.Client) _paymentgatewayClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TPayment.Client> command, T defaultValue) {
        TPayment.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _paymentgatewayClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _paymentgatewayClient.invalidateObject(client);
                int invalidCount = _paymentgatewayClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _paymentgatewayClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;
    }

    public T_AccResponse getBalance(final int userID) throws TException {

        Command<T_AccResponse, TPayment.Client> command = new Command<T_AccResponse, TPayment.Client>() {

            @Override
            public T_AccResponse exec(TPayment.Client client) throws Exception {
                return client.getBalance(userID);
            }
        };
        return this.exec(command, null);
    }

    public void warmupCache(final int userID) throws TException {
        Command<Void, TPayment.Client> command = new Command<Void, TPayment.Client>() {

            @Override
            public Void exec(TPayment.Client client) throws Exception {
                client.warmupCache(userID);
                return null;
            }
        };
        this.exec(command, null);
    }

    /*public T_Response bill(final T_Transaction tx, final T_Token tk,
    final String sercuCode) throws TException {
    Command<T_Response, TPayment.Client> command = new Command<T_Response, TPayment.Client>() {

    @Override
    public T_Response exec(TPayment.Client client) throws Exception {
    return client.bill(tx, tk, sercuCode);
    }
    };
    return this.exec(command, null);
    }*/
    public T_Response bill(final String agentID, final String encodedData, final T_Token tk, final String clientIP) throws TException {
        Command<T_Response, TPayment.Client> command = new Command<T_Response, TPayment.Client>() {

            @Override
            public T_Response exec(TPayment.Client client) throws Exception {
                return client.bill(agentID, encodedData, tk, clientIP);
            }
        };
        return this.exec(command, null);
    }

    public List<String> zingUnSignature(final String agentID, final String encodedData, final int kindofkey) throws TException {
        Command<List<String>, TPayment.Client> command = new Command<List<String>, TPayment.Client>() {

            @Override
            public List<String> exec(TPayment.Client client) throws Exception {
                return client.zingUnSignature(agentID, encodedData, kindofkey);
            }
        };
        return this.exec(command, null);
    }

    public T_Response billing(final T_Transaction tx, final T_Token tk) throws TException {
        Command<T_Response, TPayment.Client> command = new Command<T_Response, TPayment.Client>() {

            @Override
            public T_Response exec(TPayment.Client client) throws Exception {
                return client.billing(tx, tk);
            }
        };
        return this.exec(command, null);
    }
}
