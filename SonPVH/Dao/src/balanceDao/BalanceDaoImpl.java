/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceDao;

import common.Common.TRANX_TYPE;
import daoService.DaoImpl;
import daoService.IDao;
import dto.Balance;
import dto.BalanceDTO;
import hitBalanceDao.HitBalanceDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mySqlConnectionPool.Config;
import mySqlConnectionPool.DBConnectionManager;

public class BalanceDaoImpl extends DaoImpl<BalanceDTO> implements IBalanceDao {

    private static BalanceDaoImpl instance = null;

    private BalanceDaoImpl() {
    }

    public static BalanceDaoImpl getInstance() {
        if (instance == null) {
            instance = new BalanceDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(BalanceDTO balanceDto) {
        boolean res = true;
        log.info("inserting Balance ... userID: " + balanceDto.getUserId());
        try {
            Balance currentBalance = checkBalanceIsExisted(balanceDto);
            if (currentBalance != null) {
                res = updateBalance(currentBalance, balanceDto);
            } else {
                res = insertNewBalance(balanceDto);
            }
        } catch (SQLException ex) {
            log.error("CLOSE BALANCE FAIL");
            res = false;
        } catch (Exception ex) {
            log.error("INSERT BALANCE FAIL + ex " + ex.getMessage());
            res = false;
        }
        log.info("insert Balance SUCCESS userID: " + balanceDto.getUserId());
        return res;
    }

    private Balance checkBalanceIsExisted(BalanceDTO balanceDto) throws SQLException, Exception {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Balance resBalance = null;
        try {
            tableID = (balanceDto.getUserId() % 10);
            dbConnection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            preparedStatement = dbConnection.prepareStatement(setQuery(GET_BALANCE, tableID));

            preparedStatement.setInt(1, balanceDto.getUserId());
            rs = preparedStatement.executeQuery();
            // rs.next == false => this balance is not existed
            while (rs.next()) {
                resBalance = new Balance();
                resBalance.setUserId(rs.getInt("UserId"));
                resBalance.setMain(rs.getDouble("Main"));
                resBalance.setPromotion(rs.getDouble("Promotion"));
                resBalance.setGame(rs.getDouble("Game"));
                resBalance.setLastTxId(rs.getLong("LastTxid"));
            }
        } catch (SQLException ex) {
            log.error("GET BALANCE FAIL " + ex.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (dbConnection != null) {
                DBConnectionManager.getInstance().returnConnection(dbConnection);
            }
        }
        return resBalance;

    }

    private boolean insertNewBalance(BalanceDTO balanceDto) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            tableID = (balanceDto.getUserId() % 10);

            dbConnection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            preparedStatement = dbConnection.prepareStatement(setQuery(INSERT_BALANCE, tableID));

            preparedStatement.setInt(1, balanceDto.getUserId());
            preparedStatement.setLong(5, balanceDto.getLastTxId());
            switch (balanceDto.getBalanceType()) {
                case MAIN:
                    preparedStatement.setDouble(2, balanceDto.getAmount());
                    preparedStatement.setDouble(3, 0);
                    preparedStatement.setDouble(4, 0);
                    break;
                case PROMOTION:
                    preparedStatement.setDouble(2, 0);
                    preparedStatement.setDouble(3, balanceDto.getAmount());
                    preparedStatement.setDouble(4, 0);
                    break;
                case GAME:
                    preparedStatement.setDouble(2, 0);
                    preparedStatement.setDouble(3, 0);
                    preparedStatement.setDouble(4, balanceDto.getAmount());
                    break;
                default:
                    log.error("WRONG BALANCE TYPE");
                    break;
            }
            /*
             * insert BalanceHit Table if insert BalanceTable SUCCESS
             */
            int res = preparedStatement.executeUpdate();
            if (res == 1) {
                IDao dao = HitBalanceDaoImpl.getInstance();
                dao.insert(balanceDto);
            }
        } catch (SQLException ex) {
            log.error("INSERT NEW BALANCE FAIL " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("CONNCECTION at INSERT BALANCE FAIL " + ex.getMessage());
            return false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                DBConnectionManager.getInstance().returnConnection(dbConnection);
            }
        }
        return true;
    }

    private boolean updateBalance(Balance currentBalance, BalanceDTO balanceAdd) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            tableID = (balanceAdd.getUserId() % 10);
            preparedStatement = dbConnection.prepareStatement(
                    setQuery(balanceAdd.getBalanceType(), tableID));

            //reset amout:
            double amout = resetAmount(currentBalance, balanceAdd);

            preparedStatement.setDouble(1, amout);
            preparedStatement.setInt(2, balanceAdd.getUserId());

            /*
             * Insert Hit Balance when insert Balance Table SUCCESS
             */
            int response = preparedStatement.executeUpdate();
            if (response == 1) {
                IDao dao = HitBalanceDaoImpl.getInstance();
                dao.insert(balanceAdd);
            }
        } catch (SQLException ex) {
            log.error("UPDATE BALANCE FAIL " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("CONNCECTION at UPDATE BALANCE FAIL " + ex.getMessage());
            return false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                DBConnectionManager.getInstance().returnConnection(dbConnection);
            }
        }
        return true;
    }

    private String setQuery(int queryType, int tableId) {
        String Query = "";
        switch (queryType) {
            case INSERT_BALANCE:
                Query = "INSERT INTO" + TABLE_BALANCE + tableId + "`"
                        + "(`UserId`,`Main`, `Promotion`, `Game`, `LastTxid`) "
                        + "VALUES (?, ?, ?, ?, ?)";
                break;
            case UPDATE_MAIN_BALANCE:
                Query = "UPDATE" + TABLE_BALANCE + tableId + "`"
                        + "SET" + "`Main` = ? WHERE `UserId` =?";
                break;
            case UPDATE_PROMOTION:
                Query = "UPDATE" + TABLE_BALANCE + tableId + "`"
                        + "SET" + "`Promotion` = ? WHERE `UserId` =?";
                break;
            case UPDATE_GAME:
                Query = "UPDATE" + TABLE_BALANCE + tableId + "`"
                        + "SET" + "`Game` = ? WHERE `UserId` =?";
                break;
            case GET_BALANCE:
                Query = "SELECT * FROM " + TABLE_BALANCE + tableId + "`"
                        + "WHERE `UserId` =?";
                break;
            default:
                log.error("wrong query type");
        }
        return Query;
    }

    private double resetAmount(Balance currentBalance, BalanceDTO balanceDto) {
        boolean updateAdd = false;

        switch (balanceDto.getTxType()) {
            case TRANX_TYPE.TT_PUSH_MONEY_ATM:
            case TRANX_TYPE.TT_PUSH_MONEY_CARD:
            case TRANX_TYPE.TT_PUSH_MONEY_IBANKING:
            case TRANX_TYPE.TT_PUSH_MONEY_SMS:
                updateAdd = true;
                break;
            case TRANX_TYPE.TT_DEDUCT_MONEY:
                break;
            default:
                System.out.println("TRANX_TYPE WRONG");
        }
        double resetAmout = AMOUNT_ERROR;
        switch (balanceDto.getBalanceType()) {
            case MAIN:
                resetAmout = (updateAdd)
                        ? (currentBalance.getMain() + balanceDto.getAmount())
                        : (currentBalance.getMain() - balanceDto.getAmount());
                break;
            case PROMOTION:
                resetAmout = (updateAdd)
                        ? (currentBalance.getPromotion() + balanceDto.getAmount())
                        : (currentBalance.getPromotion() - balanceDto.getAmount());
                break;
            case GAME:
                resetAmout = (updateAdd)
                        ? (currentBalance.getGame() + balanceDto.getAmount())
                        : (currentBalance.getGame() - balanceDto.getAmount());
                break;
            default:
                throw new AssertionError();
        }
        return resetAmout;
    }
    //
    private static int tableID = -999;
    private static final String TABLE_BALANCE = "`demo`.`Balance";
    private static final int UPDATE_MAIN_BALANCE = 0;
    private static final int UPDATE_PROMOTION = 1;
    private static final int UPDATE_GAME = 2;
    private static final int INSERT_BALANCE = 3;
    private static final int GET_BALANCE = 4;
    private static final int MAIN = 0;
    private static final int PROMOTION = 1;
    private static final int GAME = 2;
    public static final double AMOUNT_ERROR = -999999;

    /*
     * caution: MAIN = UPDATE_MAIN_BALANCE
     * PROMOTION = UPDATE_PROMOTION
     * GAME = UPDATE_GAME
     */
}
