/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.latesttranx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import vng.zingme.payment.bo.thrift.SerializeDeserializeHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class TransactionCachingOnUser {

    public TransactionCachingOnUser() {
        _timeWarmup = Integer.parseInt(System.getProperty("timewarmup", "1200"));
    }
    private static int _maxSize = Integer.parseInt(System.getProperty("cacheonuser", "12"));

    public static synchronized boolean putTransaction(T_Transaction tranx, Vector<byte[]> _lastCache) {
        int _count = _lastCache.size();
        if (_count >= _maxSize) {
            int endIndex = _count;
            int stat = CommonDef.TRANX_STAT.TS_INPROCESS;
            while (endIndex > 0 && stat == CommonDef.TRANX_STAT.TS_INPROCESS) {
                --endIndex;
                byte[] data = _lastCache.elementAt(endIndex);
                stat = serderhandler.deserialize(data).responseCode;
            }
            if (stat != CommonDef.TRANX_STAT.TS_INPROCESS) {
                _lastCache.removeElementAt(endIndex);
            } else {
                _lastCache.removeElementAt(_count - 1);
            }
        }

        _lastCache.insertElementAt(serderhandler.serialize(tranx).clone(), 0);
        return true;
    }

    public static List<T_Transaction> getTransaction(Vector<byte[]> _lastCache) {
        List<T_Transaction> res = new ArrayList<T_Transaction>();
        int nw = (int) (System.currentTimeMillis() / (long) CommonDef.MILISECINSEC);
        nw -= _timeWarmup;

        Vector<byte[]> copyCache = (Vector<byte[]>) _lastCache.clone();
        for (Iterator<byte[]> it = copyCache.iterator(); it.hasNext();) {
            byte[] bs = it.next();
            T_Transaction tranx = serderhandler.deserialize(bs);
            if (tranx.responseCode == CommonDef.TRANX_STAT.TS_INPROCESS && tranx.txLocalTime < nw) {
                T_ReportTransaction tx = getCompletedStatus(tranx.txID, tranx.txLocalTime);
                if (tx != null) {

                    updateTransactionStat(tranx.userID, tranx.txID, tx.txStatus, tx.message, tranx.currentBalance, _lastCache);
                }
            }
            res.add(tranx);
        }
        return res;
    }

    public static synchronized boolean updateTransactionStat(int userID, long tranxID, int stat, String description, double currentbalance, Vector<byte[]> _lastCache) {
        //log.debug("updateTransactionStat with userId=" + String.valueOf(userID) + ";tranxID=" + String.valueOf(tranxID) + ";stat=" + String.valueOf(stat) + "; des=" + description);
        for (Iterator<byte[]> it = _lastCache.iterator(); it.hasNext();) {
            T_Transaction t_Transaction = null;
            byte[] element = it.next();
            t_Transaction = serderhandler.deserialize(element);
            if (t_Transaction != null && t_Transaction.txID == tranxID) {
                _lastCache.removeElement(element);
                if (stat != CommonDef.TRANX_STAT.TS_DELETE) {
                    t_Transaction.responseCode = (short) stat;
                    t_Transaction.currentBalance = currentbalance;
                    if (!description.equals("")) {
                        t_Transaction.description = description;
                    }
                    _lastCache.insertElementAt(serderhandler.serialize(t_Transaction).clone(), 0);
                }
                return true;
            }
        }
        return false;
    }
    private static final Logger log = Logger.getLogger("appActions");

    public static synchronized boolean removeTransaction(int userID, long tranxID, Vector<byte[]> _lastCache) {
        return updateTransactionStat(userID, tranxID, CommonDef.TRANX_STAT.TS_DELETE, "", 0, _lastCache);
    }

    public static T_Transaction getTransaction(final String agentID, final String billNo, Vector<byte[]> _lastCache) {
        for (Iterator<byte[]> it = _lastCache.iterator(); it.hasNext();) {
            T_Transaction t_Transaction = null;
            byte[] element = it.next();
            t_Transaction = serderhandler.deserialize(element);
            if (t_Transaction != null && billNo.equals(t_Transaction.refID) && agentID.equals(t_Transaction.agentID)) {
                return t_Transaction;
            }
        }
        return new T_Transaction();
    }

    public static T_Transaction getTransaction(final long txID, Vector<byte[]> _lastCache) {
        for (Iterator<byte[]> it = _lastCache.iterator(); it.hasNext();) {
            T_Transaction t_Transaction = null;
            byte[] element = it.next();
            t_Transaction = serderhandler.deserialize(element);
            if (t_Transaction != null && txID == t_Transaction.txID) {
                return t_Transaction;
            }
        }
        return new T_Transaction();
    }
    private static int _timeWarmup = 3600;

    private static T_ReportTransaction getCompletedStatus(long txID, int time) {
        Connection conn = null;

        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);

        } catch (Exception ex) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
            log.warn("PaymentReportModel:getAdminAction getTransaction: " + ex.getMessage());

        }

        if (conn == null) {
            String msg = "PaymentReportModel:getTransaction connection-db null!";
            log.warn(msg);

            return null;

        }

        T_ReportTransaction tranx = null;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * (long) (CommonDef.MILISECINSEC));
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }
        String str = String.valueOf(year) + smonth;
        String sql =
                "      SELECT *"
                + "      FROM zingcredits.transactions_status_" + str + " transactions_status"
                + "      where"
                + "    	transactions_status.txID=? and (transactions_status.txStatus>=" + CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS
                + " or transactions_status.txStatus<=" + CommonDef.TRANX_STAT.TS_CLOSE_FAIL
                + " or transactions_status.txStatus=" + CommonDef.TRANX_STAT.TS_UPDATED_BALANCE + ")";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, txID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                while (resultSet.next()) {

                    tranx = new T_ReportTransaction();

                    tranx.txStatus = resultSet.getShort("txStatus");
                    tranx.message = resultSet.getString("message");
                    tranx.resultCode = resultSet.getShort("resultCode");
                }
            }
            preparedStatement.close();

            DBConnectionManager.getInstance().returnConnection(conn);

        } catch (Exception ex) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
            log.warn(LogUtil.stackTrace(ex));
            tranx = null;

        }
        return tranx;
    }
    private static final SerializeDeserializeHandler serderhandler = SerializeDeserializeHandler.getMainInstance();
}
