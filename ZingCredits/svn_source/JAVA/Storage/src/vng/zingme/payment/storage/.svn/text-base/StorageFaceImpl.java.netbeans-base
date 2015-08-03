package vng.zingme.payment.storage;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.thrift.TException;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.PaymentReturnCode;

import vng.zingme.payment.model.db.dao.AccountsDao;
import vng.zingme.payment.model.db.dao.TransactionsDao;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.storage.queue.QueueManager;
import vng.zingme.payment.thrift.*;
import vng.zingme.payment.util.TimedStatsDeque;
import vng.zingme.util.LogUtil;

public class StorageFaceImpl implements TStorage.Iface, StorageFaceImplMBean {

    private volatile long totalMicroTime = 0;
    private volatile long numUpdate = 0;
    private long numLogTx = 0;
    private long numStoreTx = 0;
    private long numUpdateFail = 0;
    private long numDupliate = 0;
    private TimedStatsDeque tpm = new TimedStatsDeque(60000);
    private TimedStatsDeque updatePM = new TimedStatsDeque(60000);

    public StorageFaceImpl() {
        //register MBean
        DBConnectionManager.getInstance();
        QueueManager.getInstance();

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        String mbeanName = "vng.zingme.payment.storage" + " :type=StorageService";
        try {
            mbs.registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    public int storeTx(T_Transaction tx) throws TException {
        // TODO Auto-generated method stub

        int res = TransactionsDao.saveTranx(tx);

        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            numStoreTx++;
        }

        return res;
    }

    @Override
    public int updateBalance(T_Account account) throws TException {
        // TODO Auto-generated method stub
        int res = PaymentReturnCode.DatabaseCode.DB_ERROR;

        long tmp = System.nanoTime();

        res = AccountsDao.updateBalance(account.userID, account.currentBalance, account.amount,
                account.txID, account.txType, account.agentID);

        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            lasttime = ((System.nanoTime() - tmp) / 1000);
            totalMicroTime += lasttime;
            ++numUpdate;
        }

        if (res == PaymentReturnCode.DatabaseCode.DB_ERROR_DUPLICATE_KEY) {
            ++numDupliate;
        }

        return res;
    }

    @Override
    public double getBalance(int UserID) throws TException {
        // TODO Auto-generated method stub
        double balance = 0;
        try {
            balance = AccountsDao.getAvailableBalance(UserID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.warn(LogUtil.stackTrace(e));
        }
        return balance;
    }

    @Override
    public long getTotalHit() {
        // TODO Auto-generated method stub
        return numLogTx + numStoreTx + numUpdate;
    }

    @Override
    public long getTotalLogTx() {
        // TODO Auto-generated method stub
        return numLogTx;
    }

    @Override
    public long getTotalStoreTx() {
        // TODO Auto-generated method stub
        return numStoreTx;
    }

    @Override
    public long getTotalUpdateBalance() {
        // TODO Auto-generated method stub
        return numUpdate;
    }

    @Override
    public long getTotalUpdateBalanceFail() {
        // TODO Auto-generated method stub
        return numUpdateFail;
    }

    @Override
    public long getTotalUpdateDuplicate() {
        // TODO Auto-generated method stub
        return numDupliate;
    }

    @Override
    public long getTpm() {
        // TODO Auto-generated method stub
        return tpm.size();
    }

    @Override
    public double getUpdateDupplicationPercentage() {
        // TODO Auto-generated method stub
        long totalHit = getTotalHit();
        if (totalHit == 0) {
            return 0;
        }
        return numDupliate / totalHit;
    }

    @Override
    public double getUpdateFailPercentage() {
        // TODO Auto-generated method stub
        long totalHit = getTotalHit();
        if (totalHit == 0) {
            return 0;
        }
        return numUpdateFail / totalHit;
    }

    @Override
    public long getUpdatePM() {
        // TODO Auto-generated method stub
        return updatePM.size();
    }

    @Override
    public double getUpdateRate() {
        // TODO Auto-generated method stub
        return ((numUpdate * (double) 1000000.0) / totalMicroTime);
    }
    private volatile long lasttime = 0;
    // private volatile long tmp = 0;

    public double getLastTime() {
        return lasttime / (double) 1000000.0;
    }
    private static final Logger log = Logger.getLogger("appActions");

    public int updateTransactionStatus(T_TranStat tts) throws TException {
        return TransactionsDao.updateTranxStat(tts);
    }
}
