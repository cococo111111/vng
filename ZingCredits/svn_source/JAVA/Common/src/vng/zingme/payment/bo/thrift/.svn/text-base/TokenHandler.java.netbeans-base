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
import vng.zingme.payment.thrift.TToken;
import vng.zingme.payment.thrift.TTokenResCode;
import vng.zingme.payment.thrift.T_Token;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class TokenHandler implements TToken.Iface {

    private static Object lockObj = new Object();
    private ReentrantLock locker = new ReentrantLock();
    private static TokenHandler _mainInstance;
    private static GenericObjectPool _tokenClient;
    private final Logger log = Logger.getLogger("appActions");

    private TokenHandler(String host, int port) {
        _tokenClient = new GenericObjectPool(new TokenFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static TokenHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String tkHost = System.getProperty("TokenHost", "localhost");
                    int tkPort = Integer.parseInt(System.getProperty(
                            "TokenPort", "7114"));
                    _mainInstance = new TokenHandler(tkHost, tkPort);
                }
            }
        }
        return _mainInstance;
    }

    public static TokenHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new TokenHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TToken.Client getClient() throws Exception {
        TToken.Client client = null;
        locker.lock();
        try {
            client = (TToken.Client) _tokenClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TToken.Client> command, T defaultValue) {
        TToken.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _tokenClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _tokenClient.invalidateObject(client);
                int invalidCount = _tokenClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _tokenClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;
    }

    public int checkToken(final T_Token tk) throws TException {
        Command<Integer, TToken.Client> command = new Command<Integer, TToken.Client>() {

            @Override
            public Integer exec(TToken.Client client) throws Exception {
                return client.checkToken(tk);
            }
        };
        return this.exec(command, TTokenResCode.ZC_TK_RESCODE_UNKNOWN.getValue()).intValue();
    }

    public T_Token getToken(final int userID) throws TException {
        Command<T_Token, TToken.Client> command = new Command<T_Token, TToken.Client>() {

            @Override
            public T_Token exec(TToken.Client client) throws Exception {
                return client.getToken(userID);
            }
        };
        return this.exec(command, null);
    }
}
