/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentreport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.util.DateUtil;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class PaymentReportModel {

    private static final Short NO_FIELD = 0;
    private static final String START_TIME = "2011-04-26 00:00:00";
    private static final Logger log = Logger.getLogger("appActions");

    /**
     * convert second time to calendar
     * @param secondTime
     * @return calendar
     */
    private Calendar getDateFromSecondTimeStamp(int secondTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(secondTime * (long) (CommonDef.MILISECINSEC));
        return cal;
    }

    public List<T_ReportTransaction> getTransByApp(String appID, String startTime, String endTime, int tranxType, int startIndex, int numRow, final int txStatus) {
        List<T_ReportTransaction> res = new LinkedList<T_ReportTransaction>();

        String now = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_PATTERN);

        String validStartTime = validStartTime(startTime, endTime, now);
        String validEndTime = validEndTime(startTime, endTime, now);

        if (validStartTime == null || validEndTime == null) {
            return res;
        }

        List<String> lsSubName = listTableSubName(validStartTime, validEndTime);
        if (lsSubName == null || lsSubName.size() <= 0) {
            return res;
        }
        if (lsSubName.size() > 1) {
            return res;
        }

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

        boolean flagCompletedTask = true;
        for (String subtbname : lsSubName) {

            String sql = "";
            if (txStatus == 0) {
                sql =
                        "SELECT * FROM zingcredits.transactions_" + subtbname + " transactions where";
                if (!appID.equals("0")) {
                    sql += " transactions.agentID = '" + appID + "' and";
                }
                sql += " lastUpdate>='" + validStartTime + "' and lastUpdate<='" + validEndTime + "'";

                if (tranxType != 0) {
                    sql += " and transactions.txType='" + tranxType + "'";
                }
                sql += " order by agentID,lastUpdate limit " + startIndex + "," + numRow;
            } else {
                sql =
                        "SELECT * FROM zingcredits.transactions_" + subtbname + " transactions, zingcredits.transactions_status_" + subtbname + " txs  where";
                if (!appID.equals("0")) {
                    sql += " transactions.agentID = '" + appID + "' and";
                }
                sql += " transactions.lastUpdate>='" + validStartTime + "' and transactions.lastUpdate<='" + validEndTime + "'";
                sql += " and transactions.txid=txs.txid";
                sql += " and txs.txStatus = " + txStatus;

                if (tranxType != 0) {
                    sql += " and transactions.txType='" + tranxType + "'";
                }
                sql += " order by agentID, transactions.lastUpdate limit " + startIndex + "," + numRow;
            }

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {

                    while (resultSet.next()) {

                        T_ReportTransaction tranx = getData(resultSet);

                        if (txStatus != 0) {
                            tranx = getDataStatus(resultSet, tranx);
                        }

                        res.add(tranx);

                    }
                }
                preparedStatement.close();

            } catch (Exception ex) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
                log.warn(LogUtil.stackTrace(ex));
                flagCompletedTask = false;
            }

            if (!flagCompletedTask) {
                break;
            }
        }

        if (flagCompletedTask) {
            DBConnectionManager.getInstance().returnConnection(conn);
        }

        return res;

    }

    public List<T_ReportTransaction> getTransByUser(int userID, long txID, String startTime, String endTime, int startIndex, int numRow, final int txStatus) {
        List<T_ReportTransaction> res = new LinkedList<T_ReportTransaction>();

        String now = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_PATTERN);

        String validStartTime = validStartTime(startTime, endTime, now);
        String validEndTime = validEndTime(startTime, endTime, now);

        if (validStartTime == null || validEndTime == null) {
            return res;
        }

        List<String> lsSubName = listTableSubName(validStartTime, validEndTime);
        if (lsSubName == null || lsSubName.size() <= 0) {
            return res;
        }
        if (lsSubName.size() > 1) {
            return res;
        }

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

        boolean flagCompletedTask = true;
        for (String subtbname : lsSubName) {

            String sql = "";
            if (txStatus == 0) {
                sql =
                        "      SELECT *"
                        + "      FROM zingcredits.transactions_" + subtbname + " transactions"
                        + "      where";
                if (txID != 0) {
                    sql += "    transactions.txID=? ";
                } else {
                    sql += "    	transactions.userID=? ";
                }
                sql += "         and lastUpdate>=? and lastUpdate<=?"
                        + "       order by lastUpdate limit ?,?";
            } else {
                sql =
                        "      SELECT *"
                        + "      FROM zingcredits.transactions_" + subtbname + " transactions, "
                        + "      zingcredits.transactions_status_" + subtbname + " txs where";
                if (txID != 0) {
                    sql += "    transactions.txID=? ";
                } else {
                    sql += "    	transactions.userID=? ";
                }
                sql += "         and transactions.lastUpdate>=? and transactions.lastUpdate<=?";
                sql += " and transactions.txid=txs.txid and txs.txStatus=" + txStatus
                        + "       order by transactions.lastUpdate limit ?,?";
            }

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                if (txID != 0) {
                    preparedStatement.setLong(1, txID);
                } else {
                    preparedStatement.setInt(1, userID);
                }
                preparedStatement.setString(2, validStartTime);
                preparedStatement.setString(3, validEndTime);
                preparedStatement.setInt(4, startIndex);
                preparedStatement.setInt(5, numRow);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {

                    while (resultSet.next()) {

                        T_ReportTransaction tranx = getData(resultSet);

                        if (txStatus != 0) {
                            tranx = getDataStatus(resultSet, tranx);
                        }

                        res.add(tranx);

                    }
                    if (txID != 0) {
                        break;
                    }
                }
                preparedStatement.close();

            } catch (Exception ex) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
                log.warn(LogUtil.stackTrace(ex));
                flagCompletedTask = false;
            }

            if (!flagCompletedTask) {
                break;
            }
        }

        if (flagCompletedTask) {
            DBConnectionManager.getInstance().returnConnection(conn);
        }

        return res;
    }

    public List<T_ReportTransaction> getTransStatus(long txID, int localTime) {
        List<T_ReportTransaction> lstRes = new LinkedList<T_ReportTransaction>();


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

        Calendar cal1 = getDateFromSecondTimeStamp(localTime);
        int month = cal1.get(Calendar.MONTH) + 1;
        int year = cal1.get(Calendar.YEAR);
        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }
        String str = String.valueOf(year) + smonth;
        String sql =
                "      SELECT *"
                + "      FROM zingcredits.transactions_status_" + str + " transactions_status"
                + "      where"
                + "    	transactions_status.txID=?"
                + "       order by lastUpdate";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, txID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                while (resultSet.next()) {

                    T_ReportTransaction tranx = new T_ReportTransaction();

                    tranx = getDataStatus(resultSet, tranx);

                    lstRes.add(tranx);

                }
            }
            preparedStatement.close();

            DBConnectionManager.getInstance().returnConnection(conn);

        } catch (Exception ex) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
            log.warn(LogUtil.stackTrace(ex));
            lstRes = null;

        }

        return lstRes;

    }

    public T_ReportSummary summary(final String appID, final String startTime, final String endTime) {
        T_ReportSummary summary = new T_ReportSummary(appID, startTime, endTime, 0, 0, 0, 0, 0, 0, 0);

        /*LinkedHashMap<Integer, Byte> setUsers = new LinkedHashMap<Integer, Byte>();
        getListTransaction(appID, startTime, endTime, summary, setUsers);
        summary.totalDiffUser = setUsers.keySet().size();*/

        return summary;
    }

    public List<T_ReportSummary> summaryDaily(String appID, final String startTime, final String endTime) {
        List<T_ReportSummary> res = new LinkedList<T_ReportSummary>();

        String now = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_PATTERN);

        String validStartTime = validStartTime(startTime, endTime, now);
        String validEndTime = validEndTime(startTime, endTime, now);

        if (validStartTime == null || validEndTime == null) {
            return res;
        }

        Calendar cal1 = getCal(DateUtil.parse(validStartTime, DateUtil.DEFAULT_PATTERN));
        Calendar cal2 = getCal(DateUtil.parse(validStartTime, DateUtil.DEFAULT_PATTERN));

        while (cal1.compareTo(cal2) < 0) {

            Calendar caltmp = (Calendar) cal1.clone();
            String stTime = DateUtil.formatDate(caltmp.getTime(), DateUtil.DEFAULT_PATTERN);
            caltmp.add(Calendar.DATE, 1);
            caltmp.add(Calendar.SECOND, -1);
            String edTime = DateUtil.formatDate(caltmp.getTime(), DateUtil.DEFAULT_PATTERN);
            T_ReportSummary summ = summary(appID, stTime, edTime);

            res.add(summ);

            cal1.add(Calendar.DATE, 1);
        }

        return res;
    }

    public List<T_ReportTransaction> getListTransaction(String appID, String startTime, String endTime, T_ReportSummary summ, LinkedHashMap<Integer, Byte> setUsers, boolean isPushMoneyApp) {
        List<T_ReportTransaction> res = new LinkedList<T_ReportTransaction>();

        String now = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_PATTERN);

        String validStartTime = validStartTime(startTime, endTime, now);
        String validEndTime = validEndTime(startTime, endTime, now);

        if (validStartTime == null || validEndTime == null) {
            return res;
        }

        List<String> lsSubName = listTableSubName(validStartTime, validEndTime);
        if (lsSubName == null || lsSubName.size() <= 0) {
            return res;
        }

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

        boolean flagCompletedTask = true;
        for (String subtbname : lsSubName) {

            String sql = "select tx.*, txs.txStatus, txs.resultCode, txs.message from zingcredits.transactions_" + subtbname + " tx "
                    + "LEFT JOIN zingcredits.transactions_status_" + subtbname + " txs ON txs.txID=tx.txID and ";
            if (isPushMoneyApp) {
                sql += "txs.txStatus=" + CommonDef.TRANX_STAT.TS_UPDATED_BALANCE;
            } else {
                sql += "(txs.txStatus>=" + CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS + " or txs.txStatus<=" + CommonDef.TRANX_STAT.TS_CLOSE_FAIL + ")";
            }
            sql += " where tx.agentID='" + appID + "' and tx.lastUpdate>='" + validStartTime + "' and tx.lastUpdate<='" + validEndTime + "' "
                    + "order by lastUpdate, txID";

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                /*preparedStatement.setShort(1, (short) CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS);
                preparedStatement.setShort(2, (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL);
                preparedStatement.setString(3, appID);
                preparedStatement.setString(4, validStartTime);
                preparedStatement.setString(5, validEndTime);*/

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet != null) {

                    while (resultSet.next()) {

                        T_ReportTransaction tranx = getData(resultSet);
                        tranx = getDataStatus(resultSet, tranx);

                        if (summ != null) {
                            summary(tranx, summ);
                            setUsers.put(tranx.userID, Byte.MIN_VALUE);
                        }

                        res.add(tranx);

                    }
                }
                preparedStatement.close();

            } catch (Exception ex) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
                log.warn(LogUtil.stackTrace(ex));
                flagCompletedTask = false;
            }

            if (!flagCompletedTask) {
                break;
            }
        }

        if (flagCompletedTask) {
            DBConnectionManager.getInstance().returnConnection(conn);
        }

        return res;

    }

    private static String makeTableSubfix(Calendar cal) {
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }
        String str = String.valueOf(year) + smonth;

        return str;
    }

    private static List<String> listTableSubName(final String startDate, final String endDate) {
        List<String> lsSubName = new LinkedList<String>();

        Calendar cal1 = getCal(DateUtil.parse(startDate, DateUtil.DEFAULT_PATTERN));
        Calendar cal2 = getCal(DateUtil.parse(endDate, DateUtil.DEFAULT_PATTERN));

        while (cal1.compareTo(cal2) < 0) {
            String subName = makeTableSubfix(cal1);
            lsSubName.add(subName);
            cal1.add(Calendar.MONTH, 1);
        }
        if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
            String subName = makeTableSubfix(cal1);
            lsSubName.add(subName);
        }

        return lsSubName;
    }

    private static Calendar getCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    private static String validStartTime(String startDate, String endDate, String now) {
        if (startDate == null || endDate == null
                || startDate.compareToIgnoreCase(endDate) > 0
                || startDate.compareToIgnoreCase(now) > 0
                || endDate.compareToIgnoreCase(START_TIME) <= 0) {
            return null;
        }

        if (startDate.compareToIgnoreCase(START_TIME) < 0) {
            startDate = START_TIME;
        }
        return startDate;
    }

    private static String validEndTime(String startDate, String endDate, String now) {
        if (endDate.compareToIgnoreCase(now) > 0) {
            endDate = now;
        }
        return endDate;
    }

    private static T_ReportTransaction getData(ResultSet resultSet) throws Exception {
        T_ReportTransaction tranx = new T_ReportTransaction();

        tranx.txID = resultSet.getLong("txID");
        tranx.txType = resultSet.getShort("txType");
        tranx.txTime = resultSet.getInt("txTime");
        tranx.txLocalTime = resultSet.getInt("txLocalTime");
        tranx.currentBalance = resultSet.getDouble("currentBalance");
        tranx.amount = resultSet.getDouble("amount");
        tranx.agentID = resultSet.getString("agentID");
        tranx.refID = resultSet.getString("refID");
        tranx.itemIDs = resultSet.getString("items");
        tranx.itemNames = resultSet.getString("itemNames");
        tranx.itemPrices = resultSet.getString("prices");
        tranx.itemQuantities = resultSet.getString("quantities");

        tranx.userID = resultSet.getInt("userID");
        tranx.userName = resultSet.getString("userName");

        tranx.lastUpdate = resultSet.getString("lastUpdate");

        return tranx;
    }

    private static T_ReportTransaction getDataStatus(ResultSet resultSet, T_ReportTransaction tranx) throws Exception {
        tranx.txID = resultSet.getLong("txID");

        Short sval = resultSet.getShort("txStatus");
        if (sval == null) {
            tranx.txStatus = NO_FIELD;
        } else {
            tranx.txStatus = sval.shortValue();
        }

        sval = resultSet.getShort("resultCode");
        if (sval == null) {
            tranx.resultCode = NO_FIELD;
        } else {
            tranx.resultCode = sval.shortValue();
        }

        tranx.message = resultSet.getString("message");

        tranx.lastUpdate = resultSet.getString("lastUpdate");

        return tranx;
    }

    private static void summary(T_ReportTransaction tranx, T_ReportSummary summ) {
        ++summ.totalTransaction;
        if (tranx.txStatus == NO_FIELD) {
            if (tranx.txType == CommonDef.TRANX_TYPE.TT_ADMIN) {
                ++summ.totalTransactionFail;
            }
            if (tranx.txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY) {
                ++summ.totalTransactionSuccess;
                summ.totalMoney += tranx.amount;
            }
            log.info("Transaction is inprocess, manual double check! " + tranx.toString());
            return;
        }
        switch (tranx.txStatus) {
            case CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS: {
                ++summ.totalTransactionSuccess;
                summ.totalMoney += tranx.amount;
            }
            break;
            case CommonDef.TRANX_STAT.TS_UPDATED_BALANCE: {
                ++summ.totalTransactionSuccess;
                summ.totalMoney += tranx.amount;
            }
            break;
            case CommonDef.TRANX_STAT.TS_CLOSE_FAIL: {
                ++summ.totalTransactionFail;
            }
            break;
            case CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT: {
                summ.totalMoney += tranx.amount;
                ++summ.totalTransactionTimeOut;
            }
            break;
            case CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK: {
                ++summ.totalTransactionNetError;
            }
            break;
            case CommonDef.TRANX_STAT.TS_INPROCESS:
            case CommonDef.TRANX_STAT.TS_REST_REQUESTING:
            default:
                break;
        }
    }
}
