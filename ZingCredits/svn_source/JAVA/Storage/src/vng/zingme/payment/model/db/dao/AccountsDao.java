package vng.zingme.payment.model.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;

import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.payment.model.util.SQLUtil;
import vng.zingme.util.LogUtil;

public class AccountsDao {

    public static final int DEFAULT_TIMEOUT = 1000;
    private static final String INSERT = "insert into accounts(amount, lastTxID, userID) values(?,?,?)";
    private static final String UPDATE_PLUS = "update accounts set amount=amount+?, lastTxID=? where userID=? and lastTxID<>?";
    private static final String UPDATE_MINUS = "update accounts set amount=amount-?, lastTxID=? where userID=? and lastTxID<>?";
    public static final byte PLUS = 1;
    public static final byte MINUS = -1;
    public static final byte DB_ERROR = -1;
    public static final byte ACCOUNT_NOT_EXISTED = -10;
    private static String SQL_HISTORY_TRANX_PUSH = "insert into acc_history_push(userID, currentBalance, amount, txID, txType, notes) values(?,?,?,?,?,?)";
    private static String SQL_HISTORY_TRANX_BILL = "insert into acc_history_bill(userID, currentBalance, amount, txID, txType, notes) values(?,?,?,?,?,?)";
    private static final String SQL_SELECT_BALANCE = "select amount from accounts where userID=?";
    private static Logger log = Logger.getLogger("appActions");

    public static int updateBalance(int userID, double currentBalance, double amount,
            long txID, short txType, String notes) {

        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;

        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
        } catch (Exception ex) {
            log.warn("AccountsDao:updateBalance getConnection: " + ex.getMessage());
            DBConnectionManager.getInstance().invalidateConnection(conn);
            return result;
        }

        if (conn == null) {
            System.out.print("AccountsDao:updateBalance connection-db null!");
            return result;
        }

        // update: Accounts-table
        if (update(userID, amount, txID, txType, conn) != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
                Logger.getLogger("infoActions").info("[Fail] update (table:accounts) for tranxid " + txID + "; userID " + userID + "; amount " + amount + "; txType " + txType);
            return result;
        }
        DBConnectionManager.getInstance().returnConnection(conn);
        Logger.getLogger("infoActions").debug("[Success] update (table:accounts) for tranxid " + txID + "; userID " + userID + "; amount " + amount + "; txType " + txType);

        //update: history_???_YYYYMM table
        int writeHistory = writeHistory(userID, currentBalance, amount, txID, txType, notes, null);
        if (writeHistory == PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            Logger.getLogger("infoActions").debug("[Success] history (table:history_) for tranxid " + txID + "; userID " + userID + "; amount " + amount + "; txType " + txType);
        } else {
            Logger.getLogger("infoActions").info("[Fail] history (table:history_) for tranxid " + txID + "; userID " + userID + "; amount " + amount + "; txType " + txType);
        }

        return PaymentReturnCode.DatabaseCode.DB_SUCCESS;
    }

    public static double getAvailableBalance(int userID) {
        double result = DB_ERROR;
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_BALANCE);
                preparedStatement.setInt(1, userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet != null && resultSet.next()) {
                    result = resultSet.getDouble(1);
                } else {
                    result = ACCOUNT_NOT_EXISTED;
                }
                preparedStatement.close();
                DBConnectionManager.getInstance().returnConnection(conn);
            }
        } catch (SQLException e) {
            log.warn(LogUtil.stackTrace(e));
            DBConnectionManager.getInstance().invalidateConnection(conn);
        } catch (Exception ex) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
            log.warn(LogUtil.stackTrace(ex));
        }
        return result;
    }

    private static String getUpdateSQL(short txType) {
        String sql = "";
        switch (txType) {
            case CommonDef.TRANX_TYPE.TT_DEDUCT_MONEY:
            case CommonDef.TRANX_TYPE.TT_ZINGWALLET_DEDUCT:
                sql = UPDATE_MINUS;
                break;
            case CommonDef.TRANX_TYPE.TT_PRESENT_MONEY:
            case CommonDef.TRANX_TYPE.TT_GIVE_BACK_MONEY:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_CARD:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ATM:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_SMS:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_IBANKING:
            case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ESALE:
            case CommonDef.TRANX_TYPE.TT_ADMIN:
            case CommonDef.TRANX_TYPE.TT_ZINGWALLET_ROLLBACK:
                sql = UPDATE_PLUS;
                break;
        }
        return sql;
    }

    public static int update(int userID, double amount, long txID, short txType, Connection conn) {
        int updatedRow = PaymentReturnCode.DatabaseCode.DB_ERROR;

        String sql = getUpdateSQL(txType);

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, amount);
            stmt.setLong(2, txID);
            stmt.setInt(3, userID);
            stmt.setLong(4, txID);

            updatedRow = stmt.executeUpdate();

            stmt.close();

            if (updatedRow <= 0) {
                PreparedStatement preparedStatement2 = conn.prepareStatement(SQL_SELECT_BALANCE);
                preparedStatement2.setInt(1, userID);
                ResultSet resultSet = preparedStatement2.executeQuery();
                if (resultSet != null && resultSet.next()) {
                    updatedRow = PaymentReturnCode.DatabaseCode.DB_SUCCESS;
                }
                preparedStatement2.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger("infoActions").warn("Update phase 1 (SQLException): sql query: " + sql);
            log.warn("Update phase 1 (SQLException): sql query: " + sql);
            log.warn("Update phase 1 (SQLException):" + ex.getMessage());
            if (stmt != null) {
                Logger.getLogger("infoActions").warn("Update phase 1 (SQLException) sql query: " + stmt.toString());
                log.warn("Update phase 1 (SQLException) sql query: " + stmt.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger("infoActions").warn("Update phase 1 (Exception): sql query: " + sql);
            log.warn("Update phase 1 (Exception): sql query: " + sql);
            log.warn("Update phase 1 (Exception):" + ex.getMessage());
            if (stmt != null) {
                Logger.getLogger("infoActions").warn("Update phase 1 (Exception) sql query: " + stmt.toString());
                log.warn("Update phase 1 (Exception) sql query: " + stmt.toString());
            }
        }

        if (updatedRow == 0) {//account is not existed, insert account
            try {
                stmt = conn.prepareStatement(INSERT);

                stmt.setDouble(1, amount);
                stmt.setLong(2, txID);
                stmt.setInt(3, userID);

                updatedRow = stmt.executeUpdate();

                stmt.close();

            } catch (SQLException ex) {
                if (!SQLUtil.isDuplicate(ex)) {
                    Logger.getLogger("infoActions").warn("Update phase 2-insert (SQLException): sql query: " + sql);
                    log.warn("Update phase 2-insert (SQLException): sql query: " + sql);
                    log.warn("Update phase 2-insert (SQLException):" + ex.getMessage());
                    if (stmt != null) {
                        Logger.getLogger("infoActions").warn("Update phase 2 (SQLException) sql query: " + stmt.toString());
                        log.warn("Update phase 2 (SQLException) sql query: " + stmt.toString());
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger("infoActions").warn("Update phase 2-insert (Exception): sql query: " + sql);
                log.warn("Update phase 2-insert (Exception): sql query: " + sql);
                log.warn("Update phase 2-insert (Exception):" + ex.getMessage());
                if (stmt != null) {
                    Logger.getLogger("infoActions").warn("Update phase 2 (Exception) sql query: " + stmt.toString());
                    log.warn("Update phase 2 (Exception) sql query: " + stmt.toString());
                }
            }
        }

        return updatedRow;

    }

    public static void updateSQL(String str) {
        SQL_HISTORY_TRANX_PUSH = "insert into acc_history_push_" + str + "(userID, currentBalance, amount, txID, txType, notes) values(?,?,?,?,?,?)";
        SQL_HISTORY_TRANX_BILL = "insert into acc_history_bill_" + str + "(userID, currentBalance, amount, txID, txType, notes) values(?,?,?,?,?,?)";
    }

    private static int writeHistory(int userID, double currentBalance, double amount, long txID, short txType, String notes, Connection _conn) {
        PreparedStatement stmt = null;
        Connection conn = _conn;
        int res = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        int hope_count = 3;
        while (hope_count > 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                if (conn == null) {
                    conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                }

                if (txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY
                        || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_CARD
                        || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ATM
                        || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_SMS
                        || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_IBANKING
                        || txType == CommonDef.TRANX_TYPE.TT_PUSH_MONEY_ESALE
                        || txType == CommonDef.TRANX_TYPE.TT_PRESENT_MONEY) {
                    stmt = conn.prepareStatement(SQL_HISTORY_TRANX_PUSH);
                }
                if (txType == CommonDef.TRANX_TYPE.TT_DEDUCT_MONEY 
                        || txType == CommonDef.TRANX_TYPE.TT_GIVE_BACK_MONEY ||
                        txType == CommonDef.TRANX_TYPE.TT_ZINGWALLET_DEDUCT ||
                        txType == CommonDef.TRANX_TYPE.TT_ZINGWALLET_ROLLBACK) {
                    stmt = conn.prepareStatement(SQL_HISTORY_TRANX_BILL);
                }
                if (txType == CommonDef.TRANX_TYPE.TT_ADMIN) {
                    stmt = conn.prepareStatement(SQL_HISTORY_TRANX_PUSH);
                }

                if (stmt == null) {
                    System.out.println("AccountsDao:updateBalance stmt is null");
                }

                stmt.setInt(1, userID);
                stmt.setDouble(2, currentBalance);
                stmt.setDouble(3, amount);
                stmt.setLong(4, txID);
                stmt.setShort(5, txType);
                stmt.setString(6, notes);

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
                    Logger.getLogger("infoActions").warn("(writeHistory) sql query: " + stmt.toString());
                    log.warn("(writeHistory) sql query: " + stmt.toString());
                } else {
                    Logger.getLogger("infoActions").warn("(writeHistory) stmt is null");
                    log.warn("(writeHistory) stmt is null");
                }
                log.warn(LogUtil.stackTrace(ex));
                DBConnectionManager.getInstance().invalidateConnection(conn);
                conn = null;
            }
            --hope_count;
        }
        return res;
    }
}
