/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import ZGenerator.Generator;
import ZGenerator.InvalidOperation;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.TClientPoolConfig;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class GenmasterHandler implements Generator.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static Object lockObj = new Object();
    private static GenmasterHandler _mainInstance;
    private static GenericObjectPool _genmasterClient;
    private final Logger log = Logger.getLogger("appActions");

    private GenmasterHandler(String host, int port) {
        _genmasterClient = new GenericObjectPool(
                new GenmasterFactory(host, port), TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static GenmasterHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String zgenHost = System.getProperty("zgenHost");
                    int zgenPort = Integer.parseInt(System.getProperty("zgenPort"));
                    _mainInstance = new GenmasterHandler(
                            zgenHost, zgenPort);
                }
            }
        }
        return _mainInstance;
    }

    public static GenmasterHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new GenmasterHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private Generator.Client getClient() throws Exception {
        Generator.Client client = null;
        locker.lock();
        try {
            client = (Generator.Client) _genmasterClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, Generator.Client> command, T defaultValue) {
        Generator.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _genmasterClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _genmasterClient.invalidateObject(client);
                int invalidCount = _genmasterClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _genmasterClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public int createGenerator(final String genName) throws InvalidOperation, TException {
        Command<Integer, Generator.Client> command = new Command<Integer, Generator.Client>() {

            @Override
            public Integer exec(Generator.Client client) throws Exception {
                return client.createGenerator(genName);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public int removeGenerator(final String genName) throws InvalidOperation, TException {
        Command<Integer, Generator.Client> command = new Command<Integer, Generator.Client>() {

            @Override
            public Integer exec(Generator.Client client) throws Exception {
                return client.removeGenerator(genName);
            }
        };
        return this.exec(command, PaymentReturnCode.ERROR_OPERATOR_FAIL).intValue();
    }

    public long getCurrentValue(final String genName) throws InvalidOperation, TException {
        Command<Long, Generator.Client> command = new Command<Long, Generator.Client>() {

            @Override
            public Long exec(Generator.Client client) throws Exception {
                return client.getCurrentValue(genName);
            }
        };
        return (long) this.exec(command, new Long(-1));
    }

    public long getValue(final String genName) throws InvalidOperation, TException {
        Command<Long, Generator.Client> command = new Command<Long, Generator.Client>() {

            @Override
            public Long exec(Generator.Client client) throws Exception {
                return client.getValue(genName);
            }
        };
        return (long) this.exec(command, new Long(-1));
    }

    public static void main(String[] args) {
        try {
            TTransport socket = new TSocket("10.30.22.135", 9090);
            // TTransport framedtrans = new TFramedTransport(socket);
            // TProtocol protocol = new TBinaryProtocol(framedtrans);
            // framedtrans.open();
            TProtocol protocol = new TBinaryProtocol(socket);
            socket.open();
            Generator.Client client = new Generator.Client(protocol);

            client.createGenerator("zingcredits");

            for (int i = 0; i < 10; ++i) {
                long k = client.getValue("zingcredits");
                int h = 22;
            }

        } catch (Exception ex) {
        }
    }
}
