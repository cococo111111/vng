/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.latesttranx;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.TLatestCache;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class LatestTranxCacheServerHandler implements TLatestCache.Iface {

    public List<T_Transaction> get(int userID) throws TException {
        return LatestTXCaching.getInstance().getTransactions(userID);
    }

    public int put(T_Transaction tx) throws TException {
        try {
            LatestTXCaching.getInstance().put(tx);
            return PaymentReturnCode.SUCCESS;
        } catch (Exception ex) {
            Logger.getLogger("appActions").warn(ex);
        }
        return PaymentReturnCode.ERROR_OPERATOR_FAIL;
    }

    public int updateStat(T_Transaction tx) throws TException {
        return LatestTXCaching.getInstance().updateTransactionStat(tx);
    }

    public int updateTransactionStat(int userID, long tranxID, int stat, String description, double currentbanlce) throws TException {
        return LatestTXCaching.getInstance().updateTransactionStat(userID, tranxID, stat, description, currentbanlce);
    }

    public int removeTransaction(int userID, long tranxID) throws TException {
        return LatestTXCaching.getInstance().removeTransaction(userID, tranxID);
    }

    public T_Transaction getTransaction(final int userID, final String agentID, final String billNo) throws TException {

        return LatestTXCaching.getInstance().getTransaction(userID, agentID, billNo);
    }

    public T_Transaction getTransactionByTxID(final int userID, final long txID) throws TException {

        return LatestTXCaching.getInstance().getTransaction(userID, txID);
    }

    public int getTransactionStatus(final int userID, final long txID) throws TException {

        return getTransactionByTxID(userID, txID).responseCode;
    }
}
