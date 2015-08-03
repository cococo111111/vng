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
import vng.zingme.payment.thrift.TReport;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class ReportHandler implements TReport.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static ReportHandler _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _reportClient;
    private final Logger log = Logger.getLogger("appActions");

    private ReportHandler(String host, int port) {
        _reportClient = new GenericObjectPool(new ReportFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static ReportHandler getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String rpHost = System.getProperty("reportHost", "localhost");
                    int rpPort = Integer.parseInt(System.getProperty(
                            "reportPort", "10116"));
                    _mainInstance = new ReportHandler(rpHost, rpPort);
                }
            }
        }
        return _mainInstance;
    }

    public static ReportHandler getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new ReportHandler(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TReport.Client getClient() throws Exception {
        TReport.Client client = null;
        locker.lock();
        try {
            client = (TReport.Client) _reportClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TReport.Client> command, T defaultValue) {
        TReport.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _reportClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            try {
                _reportClient.invalidateObject(client);
                int invalidCount = _reportClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _reportClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    public List<T_ReportTransaction> getTransByApp(final String appID, final String startTime, final String endTime, final int tranxType, final int startIndex, final int numRow, final int txStatus) throws TException {
        Command<List<T_ReportTransaction>, TReport.Client> command = new Command<List<T_ReportTransaction>, TReport.Client>() {

            @Override
            public List<T_ReportTransaction> exec(TReport.Client client) throws Exception {
                return client.getTransByApp(appID, startTime, endTime, tranxType, startIndex, numRow, txStatus);
            }
        };

        return this.exec(command, null);
    }

    public List<T_ReportTransaction> getTransByUser(final int userID, final long txID, final String startTime, final String endTime, final int startIndex, final int numRow, final int txStatus) throws TException {
        Command<List<T_ReportTransaction>, TReport.Client> command = new Command<List<T_ReportTransaction>, TReport.Client>() {

            @Override
            public List<T_ReportTransaction> exec(TReport.Client client) throws Exception {
                return client.getTransByUser(userID, txID, startTime, endTime, startIndex, numRow, txStatus);
            }
        };

        return this.exec(command, null);
    }

    public List<T_ReportTransaction> getTransStatus(final long txID, final int localTime) throws TException {
        Command<List<T_ReportTransaction>, TReport.Client> command = new Command<List<T_ReportTransaction>, TReport.Client>() {

            @Override
            public List<T_ReportTransaction> exec(TReport.Client client) throws Exception {
                return client.getTransStatus(txID, localTime);
            }
        };

        return this.exec(command, null);
    }

    public T_ReportSummary summary(final String appID, final String startTime, final String endTime) throws TException {
        Command<T_ReportSummary, TReport.Client> command = new Command<T_ReportSummary, TReport.Client>() {

            @Override
            public T_ReportSummary exec(TReport.Client client) throws Exception {
                return client.summary(appID, startTime, endTime);
            }
        };

        return this.exec(command, null);
    }

    public List<T_ReportSummary> summaryDaily(String appID, final String startTime, final String endTime) throws TException {
        Command<List<T_ReportSummary>, TReport.Client> command = new Command<List<T_ReportSummary>, TReport.Client>() {

            @Override
            public List<T_ReportSummary> exec(TReport.Client client) throws Exception {
                return client.summaryDaily(null, startTime, endTime);
            }
        };

        return this.exec(command, null);
    }
}
