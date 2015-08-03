/**
 *Class: HugeListIntClientManager * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.Command;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.payment.frontend.credits.config.Configuration;
import wtcommon_hugelist.TInvalidArgument;
import wtcommon_hugelist.TInvalidOperation;
import wtcommon_hugelist.TOperationPolicy;
import wthugelistint.TDataResult;
import wthugelistint.THugeListInt_DataServiceR;
import wthugelistint.TMapDataResult;

/**
 * client for Huge-List-Int Server
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 19, 2011
 */
public class HugeListReadHandler implements THugeListInt_DataServiceR.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static HugeListReadHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _adminClient;
    private final Logger log = Logger.getLogger("appActions");

    private HugeListReadHandler(String host, int port) {
        _adminClient = new GenericObjectPool(new HugListReadFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static HugeListReadHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = Configuration.HUGELIST_READ_HOST;
                    int adPort = Configuration.HUGELIST_READ_PORT;
                    _mainInstance = new HugeListReadHandler(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static HugeListReadHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new HugeListReadHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private THugeListInt_DataServiceR.Client getClient() throws Exception {
        THugeListInt_DataServiceR.Client client = null;
        locker.lock();
        try {
            client = (THugeListInt_DataServiceR.Client) _adminClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, THugeListInt_DataServiceR.Client> command, T defaultValue) {
        THugeListInt_DataServiceR.Client client = null;
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

    public void ping() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String testTransferring(String string) throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getInterfaceSig() throws TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult getData(int i, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetData(List<Integer> list, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult getFromHandler(int i, String string, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult multiGetFromHandler(List<Integer> list, String string, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult tryGetData(int i, int i1, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TMapDataResult tryMultiGetData(List<Integer> list, int i, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int notifyGetData(int i, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int notifyMultiGetData(List<Integer> list, TOperationPolicy top) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult li_GetSlice(int i, int i1, int i2) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TDataResult li_GetSliceFromVal(int i, int i1, int i2, int i3) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *check userID existed in applicationID
     * @param applicationID
     * @param userID
     * @return existed or not/default return:false
     * @throws TInvalidOperation
     * @throws TInvalidArgument
     * @throws TException
     */
    public boolean li_HasEntry(final int applicationID, final int userID) throws TInvalidOperation, TInvalidArgument, TException {
        Command<Boolean, THugeListInt_DataServiceR.Client> command = new Command<Boolean, THugeListInt_DataServiceR.Client>() {

            @Override
            public Boolean exec(THugeListInt_DataServiceR.Client client) throws Exception {
                return client.li_HasEntry(applicationID, userID);
            }
        };
        return exec(command, false).booleanValue();
    }

    public int li_GetEntriesCount(int i) throws TInvalidOperation, TInvalidArgument, TException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
