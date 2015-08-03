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
import vng.zingme.util.LogUtil;
import vng.zingme.xcache2_payment.thrift.TData;
import vng.zingme.xcache2_payment.thrift.TDataResult;
import vng.zingme.xcache2_payment.thrift.TInvalidArgument;
import vng.zingme.xcache2_payment.thrift.TInvalidOperation;
import vng.zingme.xcache2_payment.thrift.TMapDataResult;
import vng.zingme.xcache2_payment.thrift.TOperationPolicy;
import vng.zingme.xcache2_payment.thrift.TXCache2_DataService;

/**
 *
 * @author root
 */
public class XCache2Handler implements TXCache2_DataService.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static XCache2Handler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _xcacheClient;
    private final Logger log = Logger.getLogger("appActions");

    private XCache2Handler(String host, int port) {
        _xcacheClient = new GenericObjectPool(new XCache2Factory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static XCache2Handler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String xcHost = System.getProperty("xcacheHost", "localhost");
                    int xcPort = Integer.parseInt(System.getProperty(
                            "xcachePort", "10091"));
                    _mainInstance = new XCache2Handler(xcHost, xcPort);
                }
            }
        }
        return _mainInstance;
    }

    public static XCache2Handler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new XCache2Handler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TXCache2_DataService.Client getClient() throws Exception {
        TXCache2_DataService.Client client = null;
        locker.lock();
        try {
            client = (TXCache2_DataService.Client) _xcacheClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TXCache2_DataService.Client> command, T defaultValue) {
        TXCache2_DataService.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _xcacheClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _xcacheClient.invalidateObject(client);
                int invalidCount = _xcacheClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _xcacheClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public void put(final String string, final String string1, final String string2, final TOperationPolicy top) throws TException {
        Command<Void, TXCache2_DataService.Client> command = new Command<Void, TXCache2_DataService.Client>() {

            @Override
            public Void exec(TXCache2_DataService.Client client) throws Exception {
                client.put(string, string1, string2, top);
                return null;
            }
        };
        this.exec(command, null);
    }

    public void multiPut(String string, List<TData> list, TOperationPolicy top) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(String string, String string1) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void multiRemove(String string, List<String> list) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int syn_Put(String string, String string1, String string2, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int syn_MultiPut(String string, List<TData> list, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int syn_Remove(String string, String string1) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int syn_MultiRemove(String string, List<String> list) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ping() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String testTransferring(String string) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getInterfaceSig() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult getData(final String string, final TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        Command<TDataResult, TXCache2_DataService.Client> command = new Command<TDataResult, TXCache2_DataService.Client>() {

            @Override
            public TDataResult exec(TXCache2_DataService.Client client) throws Exception {
                return client.getData(string, top);
            }
        };
        return this.exec(command, null);
    }

    public TMapDataResult multiGetData(List<String> list, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult getFromHandler(String string, String string1, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetFromHandler(List<String> list, String string, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult tryGetData(String string, int i, boolean bln, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult tryMultiGetData(List<String> list, int i, boolean bln, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyGetData(String string, TOperationPolicy top) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyMultiGetData(List<String> list, TOperationPolicy top) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
