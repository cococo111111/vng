package vng.zingme.payment.model.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.rmi.CORBA.Util;

import org.apache.log4j.Logger;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;

import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.model.util.SQLUtil;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

public class TransactionsDao {

    private static String SQL_INSERT_TRANX = "insert into transactions(txID,txType,txTime,txLocalTime,userID,userName,currentBalance,agentID,refID,items,itemNames,prices,quantities,amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String SQL_INSERT_TRANX_STAT = "insert into transactions_status(txID,txStatus,resultCode,message) values(?,?,?,?)";
    private static Logger logger = Logger.getLogger("appActions");

    public static int saveTranx(T_Transaction tranx) {

        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {

                updateSQL(tranx.txLocalTime);

                preparedStatement = conn.prepareStatement(SQL_INSERT_TRANX);

                preparedStatement.setLong(1, tranx.txID);
                preparedStatement.setShort(2, tranx.txType);
                preparedStatement.setInt(3, tranx.txTime);
                preparedStatement.setInt(4, tranx.txLocalTime);
                preparedStatement.setInt(5, tranx.userID);
                preparedStatement.setString(6, tranx.userName);
                preparedStatement.setDouble(7, tranx.currentBalance);
                preparedStatement.setString(8, tranx.agentID);
                preparedStatement.setString(9, tranx.refID);
                preparedStatement.setString(10, tranx.itemIDs);
                preparedStatement.setString(11, tranx.itemNames);
                preparedStatement.setString(12, tranx.itemPrices);
                preparedStatement.setString(13, tranx.itemQuantities);
                preparedStatement.setDouble(14, tranx.amount);

                result = preparedStatement.executeUpdate();
                preparedStatement.close();
                DBConnectionManager.getInstance().returnConnection(conn);

                if (result == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                    Logger.getLogger("infoActions").debug("[Success] log transaction (table:transactions_) for " + tranx.toString());
                    T_TranStat ts = new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_INPROCESS, CommonDef.TRANX_RES_CODE.TC_INPROCESS, "");
                    int updateTranxStat = updateTranxStat(ts);
                    if (updateTranxStat == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                        Logger.getLogger("infoActions").debug("[Success] log stat_transaction (table:transactions_status_) for " + tranx.toString());
                    } else {
                        Logger.getLogger("infoActions").info("[Fail] log stat_transaction (table:transactions_status_) for " + tranx.toString());
                    }
                } else {
                    Logger.getLogger("infoActions").info("[Fail] log transaction (table:transactions_) for " + tranx.toString());
                }
            }
        } catch (Exception e) {

            if (SQLUtil.isDuplicate(e)) {
                result = PaymentReturnCode.DatabaseCode.DB_SUCCESS;
            }

            if (preparedStatement != null) {
                Logger.getLogger("infoActions").warn("(saveTranx) sql query: " + preparedStatement.toString());
                logger.warn("(saveTranx) sql query: " + preparedStatement.toString());
            } else {
                Logger.getLogger("infoActions").warn("(saveTranx) preparedStatement is null");
                logger.warn("(saveTranx) preparedStatement is null");
            }
            logger.warn(LogUtil.stackTrace(e));

            if (result != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                DBConnectionManager.getInstance().invalidateConnection(conn);
            } else {
                DBConnectionManager.getInstance().returnConnection(conn);
            }

        }

        return result;
    }
    private static int HOPE_COUNT = 3;

    public static int updateTranxStat(T_TranStat ts) {
        int count_retry = HOPE_COUNT, res = PaymentReturnCode.DatabaseCode.DB_ERROR;
        Connection conn = null;
        PreparedStatement stmt = null;
        while (count_retry != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                if (conn == null) {
                    conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                }

                stmt = conn.prepareStatement(SQL_INSERT_TRANX_STAT);

                stmt.setLong(1, ts.txID);
                stmt.setShort(2, ts.txStatus);
                stmt.setShort(3, ts.resultCode);
                stmt.setString(4, ts.message);

                res = stmt.executeUpdate();
                stmt.close();
                if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                    DBConnectionManager.getInstance().returnConnection(conn);
                } else {
                    DBConnectionManager.getInstance().invalidateConnection(conn);
                    conn = null;
                }
            } catch (Exception ex) {
                if (stmt != null) {
                    Logger.getLogger("infoActions").warn("(updateTranxStat) sql query: " + stmt.toString());
                    logger.warn("(updateTranxStat) sql query: " + stmt.toString());
                } else {
                    Logger.getLogger("infoActions").warn("(updateTranxStat) stmt is null");
                    logger.warn("(updateTranxStat) stmt is null");
                }
                logger.warn(LogUtil.stackTrace(ex));
                DBConnectionManager.getInstance().invalidateConnection(conn);
                conn = null;
            }
            --count_retry;
        }
        return res;
    }

    private static void updateSQL(int txLocalTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(txLocalTime * (long) (CommonDef.MILISECINSEC));
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        if (month == mth && year == yr) {
            return;
        }
        mth = month;
        yr = year;
        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }
        String str = String.valueOf(year) + smonth;
        SQL_INSERT_TRANX = "insert into transactions_" + str + "(txID,txType,txTime,txLocalTime,userID,userName,currentBalance,agentID,refID,items,itemNames,prices,quantities,amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQL_INSERT_TRANX_STAT = "insert into transactions_status_" + str + "(txID,txStatus,resultCode,message) values(?,?,?,?)";

        AccountsDao.updateSQL(str);
    }
    private static int mth = 0, yr = 0;
}
