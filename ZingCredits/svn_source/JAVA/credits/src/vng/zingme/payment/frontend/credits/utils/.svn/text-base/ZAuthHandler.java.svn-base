package vng.zingme.payment.frontend.credits.utils;

import FwSession.SessionResult;
import FwSession.SessionServiceRead;
import java.util.concurrent.locks.*;
import org.apache.commons.pool.impl.GenericObjectPool;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;


import vng.zingme.payment.bo.thrift.Command;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.frontend.credits.config.Configuration;

public class ZAuthHandler implements SessionServiceRead.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static ZAuthHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _adminClient;
    private final Logger log = Logger.getLogger("appActions");

    private ZAuthHandler(String host, int port) {
        _adminClient = new GenericObjectPool(new ZAuthFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static ZAuthHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = Configuration.SSO_HOST;
                    int adPort = Configuration.SSO_PORT;
                    _mainInstance = new ZAuthHandler(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static ZAuthHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new ZAuthHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private SessionServiceRead.Client getClient() throws Exception {
        SessionServiceRead.Client client = null;
        locker.lock();
        try {
            client = (SessionServiceRead.Client) _adminClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, SessionServiceRead.Client> command, T defaultValue) {
        SessionServiceRead.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _adminClient.returnObject(client);
            return o;
        } catch (Exception e) {
            //log.warn(LogUtil.stackTrace(e));
            try {
                _adminClient.invalidateObject(client);
                int invalidCount = _adminClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _adminClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                //log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public SessionResult GetSession(final String string) throws TException {
        Command<SessionResult, SessionServiceRead.Client> command = new Command<SessionResult, SessionServiceRead.Client>() {

            @Override
            public SessionResult exec(SessionServiceRead.Client client) throws Exception {
                return client.GetSession(string);
            }
        };
        return exec(command, new SessionResult());
    }

    public SessionResult GetSessionWithCheckIP(String string, String string1) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SessionResult GetSessionWithCheckIPBrowser(String string, String string1, String string2) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SessionResult GetSessionWithCheckBrowser(String string, String string1) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
