/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.util.Map;
import wtcommon.TInvalidArgument;
import wtcommon.TInvalidOperation;
import wtname2id.TName2IDService;
import wtname2id.TNameID;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class NameToIDHandler implements TName2IDService.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static Object lockObj = new Object();
    private static NameToIDHandler _mainInstance;
    private static GenericObjectPool _nametoidClient;
    private final Logger log = Logger.getLogger("appActions");

    private NameToIDHandler(String host, int port) {
        _nametoidClient = new GenericObjectPool(
                new NameToIDFactory(host, port), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static NameToIDHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String n2idHost = System.getProperty("n2idHost", "10.30.12.13");
                    int n2idPort = Integer.parseInt(System.getProperty("n2idPort", "9293"));
                    _mainInstance = new NameToIDHandler(
                            n2idHost, n2idPort);
                }
            }
        }
        return _mainInstance;
    }

    public static NameToIDHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new NameToIDHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TName2IDService.Client getClient() throws Exception {
        TName2IDService.Client client = null;
        locker.lock();
        try {
            client = (TName2IDService.Client) _nametoidClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TName2IDService.Client> command, T defaultValue) {
        TName2IDService.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _nametoidClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _nametoidClient.invalidateObject(client);
                int invalidCount = _nametoidClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _nametoidClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public String dump() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getID(final String string) throws TInvalidOperation, TInvalidArgument, TException {
        Command<Integer, TName2IDService.Client> command = new Command<Integer, TName2IDService.Client>() {

            @Override
            public Integer exec(TName2IDService.Client client) throws Exception {
                return client.getID(string);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public Map<String, Integer> multiGetID(List<String> list) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int multiPut(List<TNameID> list) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int multiRemove(List<String> list) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int ping() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int put(TNameID tnid) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int remove(String string) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
