/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.frontend.credits.utils;

import com.vng.zing.stdprofile2.thrift.StdProfile2Service_Rd;
import com.vng.zing.stdprofile2.thrift.TMapDataResult;
import com.vng.zing.stdprofile2.thrift.TMapErrorResult;
import com.vng.zing.stdprofile2.thrift.TMapNameDataResult;
import com.vng.zing.stdprofile2.thrift.TMapNameIDResult;
import com.vng.zing.stdprofile2.thrift.TUIDResult;
import com.vng.zing.stdprofile2.thrift.TValueResult;
import com.vng.zing.zcommon.thrift.OpHandle;
import com.vng.zing.zcommon.thrift.TAppInfo;
import com.vng.zing.zcommon.thrift.TServiceState;
import com.vng.zing.zcommon.thrift.ZException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import vng.wte.common.thrift.Command;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.thrift.TAdminServer;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class STDProfile2Handler implements StdProfile2Service_Rd.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static STDProfile2Handler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _adminClient;
    private final Logger log = Logger.getLogger("appActions");

    private STDProfile2Handler(String host, int port) {
        _adminClient = new GenericObjectPool(new STDProfile2Factory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static STDProfile2Handler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = System.getProperty("adminHost", "localhost");
                    int adPort = Integer.parseInt(System.getProperty(
                            "adminPort", "10114"));
                    _mainInstance = new STDProfile2Handler(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static STDProfile2Handler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new STDProfile2Handler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private StdProfile2Service_Rd.Client getClient() throws Exception {
        StdProfile2Service_Rd.Client client = null;
        locker.lock();
        try {
            client = (StdProfile2Service_Rd.Client) _adminClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, StdProfile2Service_Rd.Client> command, T defaultValue) {
        StdProfile2Service_Rd.Client client = null;
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

    public TValueResult get(final OpHandle oh, final int i) throws ZException, TException {
        Command<TValueResult, StdProfile2Service_Rd.Client> command = new Command<TValueResult, StdProfile2Service_Rd.Client>() {

            @Override
            public TValueResult exec(StdProfile2Service_Rd.Client client) throws Exception {
                return client.get(oh, i);
            }
        };

        return this.exec(command, new TValueResult());

    }

    public int exist(OpHandle oh, int i) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGet(OpHandle oh, List<Integer> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapErrorResult multiExist(OpHandle oh, List<Integer> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TValueResult getFrom(OpHandle oh, int i, String string) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetFrom(OpHandle oh, List<Integer> list, String string) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TUIDResult getUID(final OpHandle oh, final String string) throws ZException, TException {
        Command<TUIDResult, StdProfile2Service_Rd.Client> command = new Command<TUIDResult, StdProfile2Service_Rd.Client>() {

            @Override
            public TUIDResult exec(StdProfile2Service_Rd.Client client) throws Exception {
                return client.getUID(oh, string);
            }
        };

        return this.exec(command, new TUIDResult());

    }

    public TMapNameIDResult multiGetUID(OpHandle oh, List<String> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TValueResult getByName(OpHandle oh, String string) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapNameDataResult multiGetByName(OpHandle oh, List<String> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TValueResult getNameAvatar(OpHandle oh, int i) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetNameAvatar(OpHandle oh, List<Integer> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ping() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getIfSig() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TAppInfo getAppInfo() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TServiceState getServiceState() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TValueResult getCertNameAvatar(OpHandle oh, int i) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetCertNameAvatar(OpHandle oh, List<Integer> list) throws ZException, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
