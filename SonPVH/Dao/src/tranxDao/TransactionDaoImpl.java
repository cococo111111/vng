/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tranxDao;

import daoService.DaoImpl;
import daoService.IDao;
import dto.Transaction;
import dto.TranxStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mySqlConnectionPool.Config;
import mySqlConnectionPool.DBConnectionManager;
import tranxStatusDao.TranStatusDaoImpl;

public class TransactionDaoImpl extends DaoImpl<Transaction> implements ITransactionDao {

    private static volatile TransactionDaoImpl instance = null;

    private TransactionDaoImpl() {
    }

    public static TransactionDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TransactionDaoImpl.class) {
                instance = new TransactionDaoImpl();
            }
        }
        return instance;
    }

    @Override
    public boolean insert(Transaction tranx) {
        log.info("inserting Tranx ... ");
        int result = -1;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {

                String str = getTableId(tranx.getTxTime());
                String SQL_INSERT_TRANX = "insert into " + TABLE_TRANSACTION + str + "`"
                        + "(`txID`, `txType`, `txTime`, `userId`, `userName`, "
                        + "`currentBalance`, `agentID`, `refID`, "
                        + "`itemID`, `itemName`, `itemsPrice`, `itemsQuantity`,"
                        + " `amount`, `responseCode`, "
                        + "`description`, `balanceType`)"
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";;

                preparedStatement = conn.prepareStatement(SQL_INSERT_TRANX);
                preparedStatement.setLong(1, tranx.getTxID());
                preparedStatement.setShort(2, tranx.getTxType());
                preparedStatement.setInt(3, tranx.getTxTime());
                preparedStatement.setInt(4, tranx.getUserID());
                preparedStatement.setString(5, tranx.getUserName());
                preparedStatement.setDouble(6, tranx.getCurrentBalance());
                preparedStatement.setString(7, tranx.getAgentID());
                preparedStatement.setString(8, tranx.getRefID());
                preparedStatement.setString(9, tranx.getItemIDs());
                preparedStatement.setString(10, tranx.getItemNames());
                preparedStatement.setString(11, tranx.getItemPrices());
                preparedStatement.setString(12, tranx.getItemQuantities());
                preparedStatement.setDouble(13, tranx.getAmount());
                preparedStatement.setShort(14, tranx.getResponseCode());
                preparedStatement.setString(15, tranx.getDescription());
                preparedStatement.setInt(16, tranx.getBalanceType());

                result = preparedStatement.executeUpdate();

                if (result == 1) {
                    TranxStatus ts = new TranxStatus(tranx.getTxID(),
                            (short) TS_INPROCESS, TC_INPROCESS,
                            "", tranx.getUserID());
                    IDao<TranxStatus> dao = TranStatusDaoImpl.getInstance();
                    dao.insert(ts);
                }
            }
        } catch (SQLException ex) {
            log.error("INSERT TRANSACTION FAIL, ex: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("CONNECT TRANSACTION FAIL ex: " + ex.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    log.error("CLOSE TRANX FAIL ex: " + ex.getMessage());
                }
            }
            if (conn != null) {
                DBConnectionManager.getInstance().returnConnection(conn);
            }

        }
        log.info("insert Tranx SUCCESS at userID: " + tranx.getUserID());
        return true;
    }
    private static final String TABLE_TRANSACTION = "`demo`.`transactions_";
    public static final int TS_INPROCESS = 1;
    public static final short TC_INPROCESS = 32767;
}
