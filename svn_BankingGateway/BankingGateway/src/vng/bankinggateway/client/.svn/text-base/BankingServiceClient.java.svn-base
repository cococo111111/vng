/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import com.vng.zing.common.thriftutil.TClientPoolConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.thrift.TBankingService;
import vng.bankinggateway.thrift.T_Response;
import vng.bankinggateway.thrift.T_TranStat;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingServiceClient implements TBankingService.Iface {

    private ReentrantLock locker = new ReentrantLock();
    private static BankingServiceClient _mainInstance;
    private static Object lockObj = new Object();
    private static GenericObjectPool _bankingClient;
    private final Logger log = Logger.getLogger("appActions");

    private BankingServiceClient(String host, int port) {
        _bankingClient = new GenericObjectPool(new BankingServiceFactory(host, port),
                TClientPoolConfig.ClonePoolConfig(TClientPoolConfig.DefaultPoolConfig));
    }

    public static BankingServiceClient getMainInstance() {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    String adHost = System.getProperty("adminHost", "localhost");
                    int adPort = Integer.parseInt(System.getProperty(
                            "adminPort", "10114"));
                    _mainInstance = new BankingServiceClient(adHost, adPort);
                }
            }
        }
        return _mainInstance;
    }

    public static BankingServiceClient getMainInstance(String host, int port) {
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new BankingServiceClient(host, port);
                }
            }
        }
        return _mainInstance;
    }

    private TBankingService.Client getClient() throws Exception {
        TBankingService.Client client = null;
        locker.lock();
        try {
            client = (TBankingService.Client) _bankingClient.borrowObject();
        } finally {
            locker.unlock();
        }
        return client;
    }

    public <T, K> T exec(Command<T, TBankingService.Client> command, T defaultValue) {
        TBankingService.Client client = null;
        try {
            client = this.getClient();
            T o = command.exec(client);
            _bankingClient.returnObject(client);
            return o;
        } catch (Exception e) {
            log.warn(e.getMessage());
            e.printStackTrace();
            try {
                _bankingClient.invalidateObject(client);
                int invalidCount = _bankingClient.getNumActive() - 1;
                for (int i = 0; i < invalidCount; ++i) {
                    client = this.getClient();
                    _bankingClient.invalidateObject(client);
                }
            } catch (Exception ex1) {
                log.info(ex1.getMessage());
            }
        }
        return defaultValue;

    }

    @Override
    public T_Response requestTransfer(final String refID, final String time, final String username, final String agencyCode, final String region, final int amount, final String clientIP, final String description, final String bankCode, final String commision, final String urlCallBack, final String transferType, final String sig) throws TException {
        Command<T_Response, TBankingService.Client> command = new Command<T_Response, TBankingService.Client>() {

            @Override
            public T_Response exec(TBankingService.Client client) throws Exception {
                return client.requestTransfer(refID, time, username, agencyCode, region, amount, clientIP, description, bankCode, commision, urlCallBack, transferType, sig);
            }
        };
        return this.exec(command, new T_Response(-1, "redirectURL", "refID", "transID"));
    }

    @Override
    public T_TranStat getTranxStatus(final String refID, final String day) throws TException {
        Command<T_TranStat, TBankingService.Client> command = new Command<T_TranStat, TBankingService.Client>() {

            @Override
            public T_TranStat exec(TBankingService.Client client) throws Exception {
                return client.getTranxStatus(refID, day);
            }
        };
        return this.exec(command, new T_TranStat());
    }

    @Override
    public List<T_TransactionReport> getTranxs(final String day, final short txStatus, final boolean allStatus) throws TException {
        Command<List<T_TransactionReport>, TBankingService.Client> command = 
                new Command<List<T_TransactionReport>, TBankingService.Client>() {

            @Override
            public List<T_TransactionReport> exec(TBankingService.Client client) throws Exception {
                return client.getTranxs(day, txStatus, allStatus);
            }
        };
        return this.exec(command, new ArrayList<T_TransactionReport>());
    }
}
