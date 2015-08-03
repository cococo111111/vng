package vng.bankinggateway.model.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import vng.bankinggateway.model.util.Config;
import vng.bankinggateway.model.util.DBConnectionManager;
import vng.bankinggateway.model.util.SQLUtil;
import vng.bankinggateway.thrift.T_TranStat;
import vng.bankinggateway.thrift.T_Transaction;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.PaymentReturnCode;
import vng.zingme.util.LogUtil;

public class TransactionsDao {

        public static final int DB_ERROR = -1;
        public static final int NEW_TRANSACTIONID = 100000;
        public static final int TRANSACTION_NOT_EXISTED = -10;
        public static final int NO_TRANSACTION_TODAY = -11;
        private static String SQL_GET_TRANSACTION_WITH_BANKCODE ="";
        private static String SQL_GET_TRANSACTION_ID =
                "select ID from transactions where txID=? AND time=?";
        private static String SQL_GET_LIST_TRANSACTION =
                "select tx.*, txStatus.txStatus as txStatus, txStatus.responseCode as responseCode from transactions tx, transactions_status txStatus where tx.ID=txStatus.ID AND tx.time=? AND txStatus.txStatus=?";
        private static String SQL_GET_TRANSACTION_STATUS =
                "select tx.txID as txID, txStatus.* from transactions tx, transactions_status txStatus where tx.ID=txStatus.ID AND tx.refID=?";
        private static String SQL_GET_TRANSACTION =
                "select * from transactions where txID=? AND time=?";
        private static String SQL_INSERT_TRANX =
                "insert into transactions(bankCode,txID,txType,time,userName,agencyCode,providerCode,refID,amount,clientIP,description,bankAccountCode,bankAccountNumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        private static String SQL_INSERT_TRANX_STAT =
                "insert into transactions_status(ID,txStatus,responseCode,message) values(?,?,?,?)";
        private static String SQL_SELECT_CURRENT_TRANXID =
                "select lastID from generatedTransID WHERE day=?";
        private static String SQL_UPDATE_TRANX =
                "update transactions set bankAccountCode = ?, bankAccountNumber = ?,confirmStatus =? refID=? WHERE id=?";
        private static String SQL_INSERT_NEW_TRANXID =
                "insert into generatedTransID(lastID,day) values(?,?)";
        private static String SQL_UPDATE_TRANXID =
                "update generatedTransID set lastID = ? WHERE day=?";
        private static String SQL_SELECT_CURRENT_QUERYID =
                "select lastID from generatedQueryID WHERE day=?";
        private static String SQL_INSERT_NEW_QUERYID =
                "insert into generatedQueryID(lastID,day) values(?,?)";
        private static String SQL_UPDATE_QUERYID =
                "update generatedQueryID set lastID = ? WHERE day=?";
        
        private static String SQL_SELECT_TRANXID_BY_BANKCODE =
                "select lastID from generatedTransID_bankCode WHERE bankCode=?";
        private static String SQL_INSERT_NEW_TRANXID_BY_BANKCODE =
                "insert into generatedTransID_bankCode(lastID,bankCode) values(?,?)";
        private static String SQL_UPDATE_TRANXID_BY_BANKCODE =
                "update generatedTransID_bankCode set lastID = ? WHERE bankCode=?";
        private static Logger logger = Logger.getLogger("appActions");

        private static void updateSQL(String txLocalTime) throws ParseException {
                DateFormat df = new SimpleDateFormat("MMddyyHHmmss");
                DateFormat dfToGetExtraTables = new SimpleDateFormat("yyyyMM");
                DateFormat dfToGetTranxs = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(txLocalTime);

                String suffixOfTable = dfToGetExtraTables.format(date);

                dfToGetTranxs.format(date);
                String startTime = dfToGetTranxs.format(date) + " 00:00:00";
                String endTime = dfToGetTranxs.format(date) + " 23:59:59";
                SQL_GET_TRANSACTION_ID = "select ID from transactions_" + suffixOfTable
                        + " where txID=? AND time=?";
                SQL_INSERT_TRANX = "insert into transactions_" + suffixOfTable
                        + "(bankCode,txID,txType,time,userName,agencyCode,providerCode,refID,amount,clientIP,description,bankAccountCode,bankAccountNumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQL_INSERT_TRANX_STAT = "insert into transactions_status_" + suffixOfTable
                        + "(ID,txStatus,responseCode,message) values(?,?,?,?)";
                SQL_GET_LIST_TRANSACTION =
                        "select tx.*, txStatus.txStatus as txStatus, txStatus.responseCode as responseCode from transactions_"
                        + suffixOfTable + " tx, transactions_status_" + suffixOfTable
                        + " txStatus where tx.ID=txStatus.ID AND tx.lastUpdate >= '" + startTime
                        + "' AND tx.lastUpdate <= '" + endTime + "' AND txStatus.txStatus=?";
                SQL_UPDATE_TRANX = "update transactions_" + suffixOfTable
                        + " set bankAccountCode = ?, bankAccountNumber = ?, confirmStatus = ?, refID = ? WHERE id=?";
                SQL_GET_TRANSACTION = "select * from transactions_" + suffixOfTable
                        + " where txID=? AND time=?";
                SQL_GET_TRANSACTION_WITH_BANKCODE = "select * from transactions_" + suffixOfTable
                        + " where txID=? AND bankCode=?";
                SQL_GET_TRANSACTION_STATUS =
                        "select tx.txID as txID, tx.time as time, txStatus.* from transactions_"
                        + suffixOfTable + " tx, transactions_status_" + suffixOfTable
                        + " txStatus where tx.ID=txStatus.ID AND tx.refID=? ORDER BY txStatus.lastUpdate DESC";
        }

        public static int saveTranx(T_Transaction tranx) {
                int result = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement preparedStatement = null;
                try {
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                updateSQL(tranx.time);

                                preparedStatement = conn.prepareStatement(SQL_INSERT_TRANX);

                                preparedStatement.setString(1, tranx.bankCode);
                                preparedStatement.setInt(2, tranx.txID);
                                preparedStatement.setShort(3, tranx.txType);
                                preparedStatement.setString(4, tranx.time);
                                preparedStatement.setString(5, tranx.userName);
                                preparedStatement.setString(6, tranx.agencyCode);
                                preparedStatement.setString(7, tranx.providerCode);
                                preparedStatement.setString(8, tranx.refID);
                                preparedStatement.setInt(9, tranx.amount);
                                preparedStatement.setString(10, tranx.clientIP);
                                preparedStatement.setString(11, tranx.description);
                                preparedStatement.setString(12, tranx.bankAccountCode);
                                preparedStatement.setString(13, tranx.bankAccountNumber);

                                result = preparedStatement.executeUpdate();
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);

                                if (result == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                        Logger.getLogger("infoActions").debug(
                                                "[Success] log transaction (table:transactions_) for "
                                                + tranx.toString());
                                        T_TranStat ts = new T_TranStat(tranx.txID, tranx.time,
                                                (short) StorageCommonDef.TRANX_STAT.TS_INPROCESS,
                                                StorageCommonDef.TRANX_RES_CODE.TC_INPROCESS, "");
                                        int updateTranxStat = updateTranxStat(ts);
                                        if (updateTranxStat
                                                == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                                Logger.getLogger("infoActions").debug(
                                                        "[Success] log stat_transaction (table:transactions_status_) for "
                                                        + tranx.toString());
                                        } else {
                                                Logger.getLogger("infoActions").info(
                                                        "[Fail] log stat_transaction (table:transactions_status_) for "
                                                        + tranx.toString());
                                        }
                                } else {
                                        Logger.getLogger("infoActions").info(
                                                "[Fail] log transaction (table:transactions_) for "
                                                + tranx.toString());
                                }
                        }
                } catch (Exception e) {

                        if (SQLUtil.isDuplicate(e)) {
                                result = PaymentReturnCode.DatabaseCode.DB_SUCCESS;
                        }

                        if (preparedStatement != null) {
                                Logger.getLogger("infoActions").warn("(saveTranx) sql query: "
                                        + preparedStatement.toString());
                                logger.
                                        warn("(saveTranx) sql query: " + preparedStatement.
                                        toString());
                        } else {
                                Logger.getLogger("infoActions").warn(
                                        "(saveTranx) preparedStatement is null");
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

        private static long getTransactionID(int txID, String time) {
                long result = DB_ERROR;
                Connection conn = null;
                try {
                        updateSQL(time);
                        //
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_GET_TRANSACTION_ID);
                                preparedStatement.setInt(1, txID);
                                preparedStatement.setString(2, time);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result = resultSet.getLong(1);
                                } else {
                                        result = TRANSACTION_NOT_EXISTED;
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static T_Transaction getTransaction(int txID, String time) {
                T_Transaction result = new T_Transaction();
                Connection conn = null;
                try {
                        updateSQL(time);
                        //
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_GET_TRANSACTION);
                                preparedStatement.setInt(1, txID);
                                preparedStatement.setString(2, time);

                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result.setBankCode(resultSet.getString("bankCode"));
                                        result.setTxID(resultSet.getInt("txID"));
                                        result.setTxType(resultSet.getShort("txType"));
                                        result.setTime(resultSet.getString("time"));
                                        result.setUserName(resultSet.getString("userName"));
                                        result.setAgencyCode(resultSet.getString("agencyCode"));
                                        result.setProviderCode(resultSet.getString("providerCode"));
                                        result.setRefID(resultSet.getString("refID"));
                                        result.setAmount(resultSet.getInt("amount"));
                                        result.setClientIP(resultSet.getString("clientIP"));
                                        result.setDescription(resultSet.getString("description"));
                                        result.setBankAccountCode(resultSet.getString(
                                                "bankAccountCode"));
                                        result.setBankAccountNumber(resultSet.getString(
                                                "bankAccountNumber"));
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }
        
        public static T_Transaction getTransactionWithBankCode(int txID, String time, String bankCode) {
                T_Transaction result = new T_Transaction();
                Connection conn = null;
                try {
                        updateSQL(time);
                        //
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_GET_TRANSACTION_WITH_BANKCODE);
                                preparedStatement.setInt(1, txID);
                                preparedStatement.setString(2, bankCode);

                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result.setBankCode(resultSet.getString("bankCode"));
                                        result.setTxID(resultSet.getInt("txID"));
                                        result.setTxType(resultSet.getShort("txType"));
                                        result.setTime(resultSet.getString("time"));
                                        result.setUserName(resultSet.getString("userName"));
                                        result.setAgencyCode(resultSet.getString("agencyCode"));
                                        result.setProviderCode(resultSet.getString("providerCode"));
                                        result.setRefID(resultSet.getString("refID"));
                                        result.setAmount(resultSet.getInt("amount"));
                                        result.setClientIP(resultSet.getString("clientIP"));
                                        result.setDescription(resultSet.getString("description"));
                                        result.setBankAccountCode(resultSet.getString(
                                                "bankAccountCode"));
                                        result.setBankAccountNumber(resultSet.getString(
                                                "bankAccountNumber"));
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static T_TranStat getTranxStatus(String refID, String day) {
                T_TranStat result = new T_TranStat();
                Connection conn = null;
                try {
                        DateFormat df = new SimpleDateFormat("MMddyy");
                        DateFormat dfToGetExtraTables = new SimpleDateFormat("yyyyMM");
                        Date date = df.parse(day);

                        String suffixOfTable = dfToGetExtraTables.format(date);
                        SQL_GET_TRANSACTION_STATUS =
                                "select tx.txID as txID, tx.time as time, txStatus.* from transactions_"
                                + suffixOfTable + " tx, transactions_status_" + suffixOfTable
                                + " txStatus where tx.ID=txStatus.ID AND tx.refID=? ORDER BY txStatus.lastUpdate DESC";
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_GET_TRANSACTION_STATUS);
                                preparedStatement.setString(1, refID);

                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result.setTxID(resultSet.getInt("txID"));
                                        result.setTime(resultSet.getString("time"));
                                        result.setTxStatus((short) resultSet.getInt(
                                                "txStatus.txStatus"));
                                        result.setResponseCode(resultSet.getString(
                                                "txStatus.responseCode"));
                                        result.setMessage(resultSet.getString("txStatus.message"));
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static List<T_TransactionReport> getTranx(String day, int txStatus, boolean allStatus, String bankCode) {
                List<T_TransactionReport> result = new ArrayList<T_TransactionReport>();
                Connection conn = null;
                try {
                        DateFormat df = new SimpleDateFormat("MMddyy");
                        DateFormat dfToGetExtraTables = new SimpleDateFormat("yyyyMM");
                        DateFormat dfToGetTranxs = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = df.parse(day);

                        String suffixOfTable = dfToGetExtraTables.format(date);
                        String startTime = dfToGetTranxs.format(date) + " 00:00:00";
                        String endTime = dfToGetTranxs.format(date) + " 23:59:59";
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                if (txStatus == 1 && !allStatus) {
                                        SQL_GET_LIST_TRANSACTION =
                                                "SELECT * From (SELECT tx.* ,COUNT(txStatus.txStatus) as COUNT, txStatus.responseCode as responseCode from transactions_"
                                                + suffixOfTable + " tx, transactions_status_"
                                                + suffixOfTable
                                                + " txStatus where tx.ID=txStatus.ID "
                                                + ("0".equals(bankCode)?"":" AND tx.bankCode='"+bankCode+"'" )
                                                + " AND tx.lastUpdate >= '"
                                                + startTime + "' AND tx.lastUpdate <= '" + endTime
                                                + "'GROUP BY tx.ID ) as foo "
                                                + "WHERE foo.COUNT = 1";
                                        PreparedStatement preparedStatement = conn.prepareStatement(
                                                SQL_GET_LIST_TRANSACTION);

                                        ResultSet resultSet = preparedStatement.executeQuery();
                                        while (resultSet != null && resultSet.next()) {

                                                T_TransactionReport trans =
                                                        new T_TransactionReport();
                                                trans.setBankCode(resultSet.getString("bankCode"));
                                                trans.setTxID(resultSet.getInt("txID"));
                                                trans.setTxType(resultSet.getShort("txType"));
                                                trans.setTime(resultSet.getString("time"));
                                                trans.setUserName(resultSet.getString("userName"));
                                                trans.setAgencyCode(resultSet.
                                                        getString("agencyCode"));
                                                trans.setProviderCode(resultSet.getString(
                                                        "providerCode"));
                                                trans.setRefID(resultSet.getString("refID"));
                                                trans.setAmount(resultSet.getInt("amount"));
                                                trans.setClientIP(resultSet.getString("clientIP"));
                                                trans.setDescription(resultSet.getString(
                                                        "description"));
                                                trans.setBankAccountCode(resultSet.getString(
                                                        "bankAccountCode"));
                                                trans.setBankAccountNumber(resultSet.getString(
                                                        "bankAccountNumber"));
                                                trans.setConfirmStatus(resultSet.getShort(
                                                        "confirmStatus"));

                                                trans.setTxStatus((short) 1);
                                                trans.setResponseCode(resultSet.getString(
                                                        "responseCode"));


                                                result.add(trans);
                                        }
                                        preparedStatement.close();
                                } else {

                                        if (allStatus) {
                                                SQL_GET_LIST_TRANSACTION =
                                                        "SELECT tx.*, txStatus.* from transactions_"
                                                        + suffixOfTable
                                                        + " tx, transactions_status_"
                                                        + suffixOfTable
                                                        + " txStatus where txStatus.lastUpdate = ( SELECT MAX(lastUpdate) FROM transactions_status_"
                                                        + suffixOfTable
                                                        + "  WHERE ID = txStatus.ID ) AND txStatus.ID=tx.ID AND ? "
                                                        + ("0".equals(bankCode)?"":" AND tx.bankCode='"+bankCode+"'" )
                                                        + " AND tx.lastUpdate >= '"
                                                        + startTime + "' AND tx.lastUpdate <= '"
                                                        + endTime + "' Order by tx.ID";
                                        } else {
                                                SQL_GET_LIST_TRANSACTION =
                                                        "SELECT tx.*, txStatus.txStatus as txStatus, txStatus.responseCode as responseCode from transactions_"
                                                        + suffixOfTable
                                                        + " tx, transactions_status_"
                                                        + suffixOfTable
                                                        + " txStatus where tx.ID=txStatus.ID "
                                                        + ("0".equals(bankCode)?"":" AND tx.bankCode='"+bankCode+"'" )
                                                         + " AND tx.lastUpdate >= '"
                                                        + startTime + "' AND tx.lastUpdate <= '"
                                                        + endTime
                                                        + "' AND txStatus.txStatus=? Order by tx.ID";
                                        }
                                        PreparedStatement preparedStatement = conn.prepareStatement(
                                                SQL_GET_LIST_TRANSACTION);
                                        preparedStatement.setInt(1, (allStatus) ? 1 : txStatus);

                                        ResultSet resultSet = preparedStatement.executeQuery();
                                        while (resultSet != null && resultSet.next()) {

                                                T_TransactionReport trans =
                                                        new T_TransactionReport();
                                                trans.
                                                        setBankCode(resultSet.getString(
                                                        "tx.bankCode"));
                                                trans.setTxID(resultSet.getInt("tx.txID"));
                                                trans.setTxType(resultSet.getShort("txType"));
                                                trans.setTime(resultSet.getString("tx.time"));
                                                trans.
                                                        setUserName(resultSet.getString(
                                                        "tx.userName"));
                                                trans.setAgencyCode(resultSet.getString(
                                                        "tx.agencyCode"));
                                                trans.setProviderCode(resultSet.getString(
                                                        "tx.providerCode"));
                                                trans.setRefID(resultSet.getString("tx.refID"));
                                                trans.setAmount(resultSet.getInt("tx.amount"));
                                                trans.
                                                        setClientIP(resultSet.getString(
                                                        "tx.clientIP"));
                                                trans.setDescription(resultSet.getString(
                                                        "tx.description"));
                                                trans.setBankAccountCode(resultSet.getString(
                                                        "tx.bankAccountCode"));
                                                trans.setBankAccountNumber(resultSet.getString(
                                                        "tx.bankAccountNumber"));
                                                trans.setConfirmStatus(resultSet.getShort(
                                                        "tx.confirmStatus"));

                                                trans.setTxStatus(resultSet.getShort("txStatus"));
                                                trans.setResponseCode(resultSet.getString(
                                                        "responseCode"));


                                                result.add(trans);
                                        }
                                        preparedStatement.close();
                                }

                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static int updateTranxStat(T_TranStat ts) {
                int count_retry = HOPE_COUNT,
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                long ID = getTransactionID(ts.txID, ts.time);
                if (ID > 0) {
                        while (count_retry != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                try {
                                        if (conn == null) {
                                                conn = DBConnectionManager.getInstance().
                                                        getConnection(Config.timeOut);
                                        }

                                        stmt = conn.prepareStatement(SQL_INSERT_TRANX_STAT);

                                        stmt.setLong(1, ID);
                                        stmt.setShort(2, ts.txStatus);
                                        stmt.setString(3, ts.responseCode);
                                        stmt.setString(4, ts.message);

                                        res = stmt.executeUpdate();
                                        stmt.close();
                                        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                                DBConnectionManager.getInstance().returnConnection(
                                                        conn);
                                        } else {
                                                DBConnectionManager.getInstance().
                                                        invalidateConnection(conn);
                                                conn = null;
                                        }
                                } catch (Exception ex) {
                                        if (stmt != null) {
                                                Logger.getLogger("infoActions").warn(
                                                        "(updateTranxStat) sql query: " + stmt.
                                                        toString());
                                                logger.warn("(updateTranxStat) sql query: " + stmt.
                                                        toString());
                                        } else {
                                                Logger.getLogger("infoActions").warn(
                                                        "(updateTranxStat) stmt is null");
                                                logger.warn("(updateTranxStat) stmt is null");
                                        }
                                        logger.warn(LogUtil.stackTrace(ex));
                                        DBConnectionManager.getInstance().invalidateConnection(conn);
                                        conn = null;
                                }
                                --count_retry;
                        }
                } else {
                        res = (int) ID;
                }
                return res;
        }

        private static int updateTranx(long id, T_Transaction tx) {
                int count_retry = HOPE_COUNT,
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                while (count_retry != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                        try {
                                if (conn == null) {
                                    conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                                }
                                updateSQL(tx.getTime());
                                stmt = conn.prepareStatement(SQL_UPDATE_TRANX);

                                stmt.setString(1, tx.getBankAccountCode());
                                stmt.setString(2, tx.getBankAccountNumber());
                                stmt.setShort(3, tx.getConfirmStatus());
                                stmt.setString(4, tx.getRefID());
                                stmt.setLong(5, id);

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
                                        Logger.getLogger("infoActions").warn(
                                                "(updateTranx) sql query: " + stmt.toString());
                                        logger.warn("(updateTranx) sql query: " + stmt.toString());
                                } else {
                                        Logger.getLogger("infoActions").warn(
                                                "(updateTranx) stmt is null");
                                        logger.warn("(updateTranx) stmt is null");
                                }
                                logger.warn(LogUtil.stackTrace(ex));
                                DBConnectionManager.getInstance().invalidateConnection(conn);
                                conn = null;
                        }
                        --count_retry;
                }
                return res;
        }

        public static int updateTransaction(T_Transaction tx) {
                int res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                long id = getTransactionID(tx.txID, tx.time);
                if (id > 0) {
                        res = updateTranx(id, tx);
                }
                return res;
        }

        public static int updateTranxAndStatus(T_Transaction tx, short txStatus, String responseCode) {
                int count_retry = HOPE_COUNT,
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                long id = getTransactionID(tx.txID, tx.time);
                if (id > 0) {
                        res = updateTranx(id, tx);
                        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                        } else {
                                return res;
                        }

                        while (count_retry != 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                try {
                                        if (conn == null) {
                                                conn = DBConnectionManager.getInstance().
                                                        getConnection(Config.timeOut);
                                        }

                                        stmt = conn.prepareStatement(SQL_INSERT_TRANX_STAT);

                                        stmt.setLong(1, id);
                                        stmt.setShort(2, txStatus);
                                        stmt.setString(3, responseCode);
                                        stmt.setString(4, "");

                                        res = stmt.executeUpdate();
                                        stmt.close();
                                        if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                                DBConnectionManager.getInstance().returnConnection(
                                                        conn);
                                        } else {
                                                DBConnectionManager.getInstance().
                                                        invalidateConnection(conn);
                                                conn = null;
                                        }
                                } catch (Exception ex) {
                                        if (stmt != null) {
                                                Logger.getLogger("infoActions").warn(
                                                        "(updateTranxStat) sql query: " + stmt.
                                                        toString());
                                                logger.warn("(updateTranxStat) sql query: " + stmt.
                                                        toString());
                                        } else {
                                                Logger.getLogger("infoActions").warn(
                                                        "(updateTranxStat) stmt is null");
                                                logger.warn("(updateTranxStat) stmt is null");
                                        }
                                        logger.warn(LogUtil.stackTrace(ex));
                                        DBConnectionManager.getInstance().invalidateConnection(conn);
                                        conn = null;
                                }
                                --count_retry;
                        }
                }
                return res;
        }

        private static int getTodayTransID(String time) {

                int result = DB_ERROR;
                Connection conn = null;
                try {
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_SELECT_CURRENT_TRANXID);
                                preparedStatement.setString(1, time);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result = resultSet.getInt(1);
                                } else {
                                        result = NO_TRANSACTION_TODAY;
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static int generateTransID(String day) throws ParseException {
                int count_retry = HOPE_COUNT,
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                int id = getTodayTransID(day);
                if (id == PaymentReturnCode.DatabaseCode.DB_ERROR) {
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                } else {

                        String sql = "";
                        if (id == NO_TRANSACTION_TODAY) {
                                id = NEW_TRANSACTIONID;
                                sql = SQL_INSERT_NEW_TRANXID;
                        } else {
                                id = id + 1;
                                sql = SQL_UPDATE_TRANXID;
                        }

                        try {
                                if (conn == null) {
                                        conn = DBConnectionManager.getInstance().getConnection(
                                                Config.timeOut);
                                }
                                stmt = conn.prepareStatement(sql);

                                stmt.setInt(1, id);
                                stmt.setString(2, day);

                                res = stmt.executeUpdate();
                                stmt.close();
                                if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                        DBConnectionManager.getInstance().returnConnection(conn);
                                        res = id;
                                } else {
                                        DBConnectionManager.getInstance().invalidateConnection(conn);
                                        conn = null;
                                }
                        } catch (Exception ex) {
                                if (stmt != null) {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateTransID) sql query: " + stmt.toString());
                                        logger.warn("(generateTransID) sql query: " + stmt.
                                                toString());
                                } else {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateTransID) stmt is null");
                                        logger.warn("(generateTransID) stmt is null");
                                }
                                logger.warn(LogUtil.stackTrace(ex));
                                DBConnectionManager.getInstance().invalidateConnection(conn);
                                conn = null;
                        }
                }
                return res;
        }
        
        private static int getTransID_BankCode(String bankCode) {

                int result = DB_ERROR;
                Connection conn = null;
                try {
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_SELECT_TRANXID_BY_BANKCODE);
                                preparedStatement.setString(1, bankCode);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result = resultSet.getInt(1);
                                } else {
                                        result = NO_TRANSACTION_TODAY;
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }
        
        public static int generateTransIDByBankCode(String bankCode) throws ParseException {
                int  res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                int id = getTransID_BankCode(bankCode);
                if (id == PaymentReturnCode.DatabaseCode.DB_ERROR) {
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                } else {

                        String sql = "";
                        if (id == NO_TRANSACTION_TODAY) {
                                id = NEW_TRANSACTIONID;
                                sql = SQL_INSERT_NEW_TRANXID_BY_BANKCODE;
                        } else {
                                id = id + 1;
                                sql = SQL_UPDATE_TRANXID_BY_BANKCODE;
                        }

                        try {
                                if (conn == null) {
                                        conn = DBConnectionManager.getInstance().getConnection(
                                                Config.timeOut);
                                }
                                stmt = conn.prepareStatement(sql);

                                stmt.setInt(1, id);
                                stmt.setString(2, bankCode);

                                res = stmt.executeUpdate();
                                stmt.close();
                                if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                        DBConnectionManager.getInstance().returnConnection(conn);
                                        res = id;
                                } else {
                                        DBConnectionManager.getInstance().invalidateConnection(conn);
                                        conn = null;
                                }
                        } catch (Exception ex) {
                                if (stmt != null) {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateTransIDByBankCode) sql query: " + stmt.toString());
                                        logger.warn("(generateTransIDByBankCode) sql query: " + stmt.
                                                toString());
                                } else {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateTransIDByBankCode) stmt is null");
                                        logger.warn("(generateTransIDByBankCode) stmt is null");
                                }
                                logger.warn(LogUtil.stackTrace(ex));
                                DBConnectionManager.getInstance().invalidateConnection(conn);
                                conn = null;
                        }
                }
                return res;
        }

        private static int getTodayQueryID(String time) {

                int result = DB_ERROR;
                Connection conn = null;
                try {
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        SQL_SELECT_CURRENT_QUERYID);
                                preparedStatement.setString(1, time);
                                ResultSet resultSet = preparedStatement.executeQuery();
                                if (resultSet != null && resultSet.next()) {
                                        result = resultSet.getInt(1);
                                } else {
                                        result = NO_TRANSACTION_TODAY;
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }

        public static int generateQueryID(String day) throws ParseException {
                int res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                Connection conn = null;
                PreparedStatement stmt = null;
                int id = getTodayQueryID(day);
                if (id == PaymentReturnCode.DatabaseCode.DB_ERROR) {
                        res = PaymentReturnCode.DatabaseCode.DB_ERROR;
                } else {

                        String sql = "";
                        if (id == NO_TRANSACTION_TODAY) {
                                id = NEW_TRANSACTIONID;
                                sql = SQL_INSERT_NEW_QUERYID;
                        } else {
                                id = id + 1;
                                sql = SQL_UPDATE_QUERYID;
                        }
                        try {
                                if (conn == null) {
                                        conn = DBConnectionManager.getInstance().getConnection(
                                                Config.timeOut);
                                }
                                stmt = conn.prepareStatement(sql);

                                stmt.setInt(1, id);
                                stmt.setString(2, day);

                                res = stmt.executeUpdate();
                                stmt.close();
                                if (res == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
                                        DBConnectionManager.getInstance().returnConnection(conn);
                                        res = id;
                                } else {
                                        DBConnectionManager.getInstance().invalidateConnection(conn);
                                        conn = null;
                                }
                        } catch (Exception ex) {
                                if (stmt != null) {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateQueryID) sql query: " + stmt.toString());
                                        logger.warn("(generateTransID) sql query: " + stmt.
                                                toString());
                                } else {
                                        Logger.getLogger("infoActions").warn(
                                                "(generateQueryID) stmt is null");
                                        logger.warn("(generateTransID) stmt is null");
                                }
                                logger.warn(LogUtil.stackTrace(ex));
                                DBConnectionManager.getInstance().invalidateConnection(conn);
                                conn = null;
                        }
                }
                return res;
        }

        public static List<T_TransactionReport> getTranxsWithConfirmStatus(String day,
                                                                           short confirmStatus, String bankCode) {
                List<T_TransactionReport> result = new ArrayList<T_TransactionReport>();
                Connection conn = null;
                try {
                        DateFormat df = new SimpleDateFormat("MMddyy");
                        DateFormat dfToGetExtraTables = new SimpleDateFormat("yyyyMM");
                        DateFormat dfToGetTranxs = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = df.parse(day);

                        String suffixOfTable = dfToGetExtraTables.format(date);
                        String startTime = dfToGetTranxs.format(date) + " 00:00:00";
                        String endTime = dfToGetTranxs.format(date) + " 23:59:59";

                        String sql  =
                                "select tx.*, txStatus.txStatus as txStatus, txStatus.responseCode as responseCode from transactions_"
                                + suffixOfTable + " tx, transactions_status_" + suffixOfTable
                                + " txStatus where tx.ID=txStatus.ID "
                                + ("0".equals(bankCode)? "":" AND tx.bankCode='"+bankCode+"'" )
                                + " AND tx.lastUpdate >= '"
                                + startTime + "' AND tx.lastUpdate <= '" + endTime
                                + "'AND txStatus.txStatus <> "
                                + (confirmStatus <= 0 ? 0 : 1);
                        if(confirmStatus >=0) 
                                sql += " AND tx.confirmStatus="+ confirmStatus;
                                
                        conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                        if (conn != null) {
                                PreparedStatement preparedStatement = conn.prepareStatement(
                                        sql);
//                                preparedStatement.setShort(1, confirmStatus);

                                ResultSet resultSet = preparedStatement.executeQuery();
                                while (resultSet != null && resultSet.next()) {
                                        T_TransactionReport trans = new T_TransactionReport();
                                        trans.setBankCode(resultSet.getString("tx.bankCode"));
                                        trans.setTxID(resultSet.getInt("tx.txID"));
                                        trans.setTxType(resultSet.getShort("txType"));
                                        trans.setTime(resultSet.getString("tx.time"));
                                        trans.setUserName(resultSet.getString("tx.userName"));
                                        trans.setAgencyCode(resultSet.getString("tx.agencyCode"));
                                        trans.
                                                setProviderCode(resultSet.getString(
                                                "tx.providerCode"));
                                        trans.setRefID(resultSet.getString("tx.refID"));
                                        trans.setAmount(resultSet.getInt("tx.amount"));
                                        trans.setClientIP(resultSet.getString("tx.clientIP"));
                                        trans.setDescription(resultSet.getString("tx.description"));
                                        trans.setBankAccountCode(resultSet.getString(
                                                "tx.bankAccountCode"));
                                        trans.setBankAccountNumber(resultSet.getString(
                                                "tx.bankAccountNumber"));

                                        trans.setTxStatus(resultSet.getShort("txStatus"));
                                        trans.setResponseCode(resultSet.getString("responseCode"));
                                        trans.setConfirmStatus(resultSet.getShort("tx.confirmStatus"));

                                        result.add(trans);
                                }
                                preparedStatement.close();
                                DBConnectionManager.getInstance().returnConnection(conn);
                        }
                } catch (SQLException e) {
                        logger.warn(LogUtil.stackTrace(e));
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                } catch (Exception ex) {
                        DBConnectionManager.getInstance().invalidateConnection(conn);
                        logger.warn(LogUtil.stackTrace(ex));
                }
                return result;
        }
}
