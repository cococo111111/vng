/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tranxStatusDao;

import daoService.DaoImpl;
import dto.TranxStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mySqlConnectionPool.Config;
import mySqlConnectionPool.DBConnectionManager;

public class TranStatusDaoImpl extends DaoImpl<TranxStatus> implements ITranStatusDao {

    private static volatile TranStatusDaoImpl instance = null;

    private TranStatusDaoImpl() {
    }

    public static TranStatusDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TranStatusDaoImpl.class) {
                instance = new TranStatusDaoImpl();
            }
        }
        return instance;
    }

    @Override
    public boolean insert(TranxStatus ts) {
        log.info("inserting tranxStatus ... ");

        int count_retry = 3;
        int res = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        while (count_retry != 0 && res != 1) {
            try {
                if (conn == null) {
                    conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                }
                stmt = conn.prepareStatement(update_sql(DaoImpl.tableId));

                stmt.setShort(1, ts.getTxStatus());
                stmt.setShort(2, ts.getResponseCode());
                stmt.setString(3, ts.getMessage());
                stmt.setInt(4, ts.getUserId());

                res = stmt.executeUpdate();
                log.info("insert tranxStatus SUCEESS at userID: " + ts.getUserId());
                return true;
            } catch (SQLException ex) {
                log.error("INSERT TRANXSTATUS FAIL, ex" + ex.getMessage());
            } catch (Exception ex) {
                log.error("CONNECT TRANXSTATUS FAIL, ex" + ex.getMessage());
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        log.error("CLOSE PREPARE STATUS FAIL, ex" + ex.getMessage());
                    }
                }
                if (conn != null) {
                    DBConnectionManager.getInstance().returnConnection(conn);
                }
            }
            --count_retry;
        }
        return false;
    }

    private static String update_sql(String str) {
        return "insert into" + TABLE_TRANSACTION_STATUS
                + str + "` (`txStatus`,`responseCode`,`message`,`userId`)"
                + " values(?,?,?,?)";
    }
    private static final String TABLE_TRANSACTION_STATUS = "`demo`.`transactions_status_";
}
