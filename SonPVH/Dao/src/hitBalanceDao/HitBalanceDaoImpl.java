/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hitBalanceDao;

import hitBalanceDao.IHitBalanceDao;
import daoService.DaoImpl;
import dto.BalanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mySqlConnectionPool.Config;
import mySqlConnectionPool.DBConnectionManager;

public class HitBalanceDaoImpl extends DaoImpl<BalanceDTO> implements IHitBalanceDao {

    private static HitBalanceDaoImpl instance = null;

    private HitBalanceDaoImpl() {
    }

    public static HitBalanceDaoImpl getInstance() {
        if (instance == null) {
            instance = new HitBalanceDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(BalanceDTO balanceDto) {
        int retry = 3;
        int res = -999;
        while (retry > 0 && res != 1) {
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;
            try {
                dbConnection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
                if (dbConnection != null) {
                    preparedStatement = dbConnection.prepareStatement(
                            update_sql(DaoImpl.getTableId(balanceDto.getTxTime())));

                    preparedStatement.setInt(1, balanceDto.getUserId());
                    preparedStatement.setDouble(2, balanceDto.getCurrentBalance());
                    preparedStatement.setLong(3, balanceDto.getTxId());
                    preparedStatement.setInt(4, balanceDto.getTxType());
                    preparedStatement.setInt(5, balanceDto.getTxTime());
                    preparedStatement.setString(6, balanceDto.getNote());
                    preparedStatement.setInt(7, balanceDto.getBalanceType());

                    res = preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                log.error("INSERT RETRY HITBALANCE FAIL " + ex.getMessage());
                return false;
            } catch (Exception ex) {
                log.error("CONNCECTION at INSERT HITBALANCE FAIL " + ex.getMessage());
                return false;
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        log.error("CLOSE HITBALANCE FAIL");
                    }
                }
                if (dbConnection != null) {
                    DBConnectionManager.getInstance().returnConnection(dbConnection);
                }
            }
            retry--;
        }
        return true;
    }
    private static final String TABLE_HIT_BALANCE = "`demo`.`HitBalance_";

    private String update_sql(String tableId) {
        return "INSERT INTO" + TABLE_HIT_BALANCE + tableId + "`"
                + "(`UserId`, `CurrentBalance`,`TxId`, `txType`, `TxTime`, `Note`,`BalanceType`)"
                + "VALUES (?, ?, ?, ? ,? ,? ,?)";
    }
}
