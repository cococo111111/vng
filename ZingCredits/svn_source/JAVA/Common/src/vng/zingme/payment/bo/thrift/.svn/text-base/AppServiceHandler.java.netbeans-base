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

import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TAppServer;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class AppServiceHandler implements TAppServer.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static AppServiceHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _adminClient;
    private final Logger log = Logger.getLogger("appActions");

    private AppServiceHandler(String host, int port) {
        _adminClient = new GenericObjectPool(new AppServiceFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static AppServiceHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = System.getProperty("appHost", "localhost");
                    int adPort = Integer.parseInt(System.getProperty(
                            "appPort", "10114"));
                    _mainInstance = new AppServiceHandler(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static AppServiceHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new AppServiceHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TAppServer.Client getClient() throws Exception {
        TAppServer.Client client = null;
        locker.lock();
        try {
            client = (TAppServer.Client) _adminClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TAppServer.Client> command, T defaultValue) {
        TAppServer.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _adminClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _adminClient.invalidateObject(client);
                int invalidCount = _adminClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _adminClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public String getAppName(final String appID) throws TException {
        Command<String, TAppServer.Client> command = new Command<String, TAppServer.Client>() {

            @Override
            public String exec(TAppServer.Client client) throws Exception {
                return client.getAppName(appID);
            }
        };
        return this.exec(command, "");
    }

    public T_AppInfo getAppInfo(final String appID) throws TException {
        Command<T_AppInfo, TAppServer.Client> command = new Command<T_AppInfo, TAppServer.Client>() {

            @Override
            public T_AppInfo exec(TAppServer.Client client) throws Exception {
                return client.getAppInfo(appID);
            }
        };
        return this.exec(command, new T_AppInfo());
    }

    public String registerGameServer(final T_AppInfo appInfo, final String adminSig, final boolean isNewKey) throws TException {
        Command<String, TAppServer.Client> command = new Command<String, TAppServer.Client>() {

            @Override
            public String exec(TAppServer.Client client) throws Exception {
                return client.registerGameServer(appInfo, adminSig, isNewKey);
            }
        };
        return this.exec(command, "");
    }

    public int registerPayLetterServer(final T_AppInfo appInfo, final String adminSig) throws TException {
        Command<Integer, TAppServer.Client> command = new Command<Integer, TAppServer.Client>() {

            @Override
            public Integer exec(TAppServer.Client client) throws Exception {
                return client.registerPayLetterServer(appInfo, adminSig);
            }
        };

        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public List<T_AppInfo> getAllAppInfo() throws TException {
        Command<List<T_AppInfo>, TAppServer.Client> command = new Command<List<T_AppInfo>, TAppServer.Client>() {

            @Override
            public List<T_AppInfo> exec(TAppServer.Client client) throws Exception {
                return client.getAllAppInfo();
            }
        };

        return this.exec(command, null);
    }

    public void addIdToWhitelist(final String appID, final int userID) throws TException {
        Command<Void, TAppServer.Client> command = new Command<Void, TAppServer.Client>() {

            @Override
            public Void exec(TAppServer.Client client) throws Exception {
                client.addIdToWhitelist(appID, userID);
                return null;
            }
        };
        this.exec(command, null);
    }

    public void addIdsToWhitelist(final String appID, final List<Integer> lsUserID) throws TException {
        Command<Void, TAppServer.Client> command = new Command<Void, TAppServer.Client>() {

            @Override
            public Void exec(TAppServer.Client client) throws Exception {
                client.addIdsToWhitelist(appID, lsUserID);
                return null;
            }
        };
        this.exec(command, null);
    }

    public void removeIdFromWhitelist(final String appID, final int userID) throws TException {
        Command<Void, TAppServer.Client> command = new Command<Void, TAppServer.Client>() {

            @Override
            public Void exec(TAppServer.Client client) throws Exception {
                client.removeIdFromWhitelist(appID, userID);
                return null;
            }
        };
        this.exec(command, null);
    }
}
