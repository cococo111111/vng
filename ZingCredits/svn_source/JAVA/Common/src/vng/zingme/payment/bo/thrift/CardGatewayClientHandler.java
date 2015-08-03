/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TCardGateway;
import vng.zingme.payment.thrift.TCardGateway.Client;
import vng.zingme.payment.thrift.T_QueryOrderResponse;
import vng.zingme.payment.thrift.T_VerifyCardRequest;
import vng.zingme.payment.thrift.T_VerifyCardResponse;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class CardGatewayClientHandler implements TCardGateway.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static CardGatewayClientHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _client;
    private final Logger log = Logger.getLogger("appActions");

    private CardGatewayClientHandler(String host, int port) {
        _client = new GenericObjectPool(new CardGatewayFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static CardGatewayClientHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = System.getProperty("adminHost", "localhost");
                    int adPort = Integer.parseInt(System.getProperty(
                            "adminPort", "10114"));
                    _mainInstance = new CardGatewayClientHandler(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static CardGatewayClientHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new CardGatewayClientHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TCardGateway.Client getClient() throws Exception {
        TCardGateway.Client client = null;
        locker.lock();
        try {
            client = (TCardGateway.Client) _client.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TCardGateway.Client> command, T defaultValue) {
        TCardGateway.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _client.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _client.invalidateObject(client);
                int invalidCount = _client.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _client.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    @Override
    public T_VerifyCardResponse verifyCard(final T_VerifyCardRequest cardRequest) throws TException {
        Command<T_VerifyCardResponse, TCardGateway.Client> command = new Command<T_VerifyCardResponse, TCardGateway.Client>() {

            @Override
            public T_VerifyCardResponse exec(Client client) throws Exception {
                return client.verifyCard(cardRequest);
            }
        };

        return this.exec(command, null);
    }

    @Override
    public T_QueryOrderResponse queryOrder(final long transactionID) throws TException {
        Command<T_QueryOrderResponse, TCardGateway.Client> command = new Command<T_QueryOrderResponse, TCardGateway.Client>() {

            @Override
            public T_QueryOrderResponse exec(Client client) throws Exception {
                return client.queryOrder(transactionID);
            }
        };

        return this.exec(command, null);
    }
    
}
