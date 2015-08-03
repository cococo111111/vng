/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.model.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vng.bankinggateway.common.PaymentReturnCode;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.model.util.Config;
import vng.bankinggateway.model.util.DBConnectionManager;
import vng.bankinggateway.thrift.T_Task;

/**
 *
 * @author sonhoang
 */
public class LogTasksDao {
    //Database

    private static final String TABLE_NAME = " tasks ";
    //Column
    private static final String ID = "`Id`";
    private static final String TASKID = "`TaskId`";
    private static final String TASKNAME = "`TaskName`";
    private static final String STATUS = "`Status`";
    private static final String DATE = "`Date`";
    private static final String STARTTIME = "`StartTime`";
    private static final String ENDTIME = "`EndTime`";
    private static final String ISRERUN = "`IsRerun`";
    //SQL QUERY
    private static final String GET_LIST_TASK = "SELECT * FROM" + TABLE_NAME;
    private static final String GET_TASK_BY_ID = "SELECT * FROM" + TABLE_NAME + "WHERE " + ID
            + "= ?";
    private static final String GET_TASK_BY_TASKID = "SELECT * FROM " + TABLE_NAME
            + " WHERE TaskId = ?";
    private static final String GET_TASK_BY_DATE = "SELECT * FROM " + TABLE_NAME
            + " WHERE Date = ?";
    private static final String DELETE_TASK_BY_TASKID = "DELETE  FROM " + TABLE_NAME
            + " WHERE TaskId =?";
    private static final String INSERT_TASK = "INSERT INTO " + TABLE_NAME + "(" + TASKID + ", "
            + TASKNAME + ", " + STATUS + ", " + DATE + "," + ISRERUN + " )"
            + "VALUES" + "(?,?,?,?,?)";
    private static final String UPDATE_TASK_START = "UPDATE" + TABLE_NAME + "SET " + STATUS
            + " = ?, " + STARTTIME + "=?"
            + "WHERE" + TASKID + "= ?";
    private static final String UPDATE_TASK_STOP = "UPDATE" + TABLE_NAME + "SET " + STATUS
            + " = ?, " + ENDTIME + " =?"
            + "WHERE" + TASKID + "= ?";
    private static final String UPDATE_TASK_EVERYDAY = "UPDATE" + TABLE_NAME + "SET" + STATUS
            + " = ?,"
            + "DATE" + "= ?," + STARTTIME + "= null," + ENDTIME + "=null " + "WHERE" + TASKID
            + "= ?";

    private static String getCurrentDate() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(dt);
        return currentDate;
    }

    private static String getCurrentTime() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }

    private static void insertData(PreparedStatement prepareStatement, String taskId,
            String taskName, int status,
            String date, boolean isRerun) throws SQLException {
        prepareStatement.setString(1, taskId);
        prepareStatement.setString(2, taskName);
        prepareStatement.setShort(3,(short) status);
        prepareStatement.setString(4, date);
        prepareStatement.setBoolean(5, isRerun);
        prepareStatement.addBatch();
    }

    private static void updateData(PreparedStatement prepareStatement, int status, String date,
            String taskId) throws SQLException {
        prepareStatement.setInt(1, status);
        prepareStatement.setString(2, date);
        prepareStatement.setString(3, taskId);
        prepareStatement.addBatch();
    }

    private static void getTaskFromData(ResultSet result, T_Task task) throws SQLException {
        task.id = result.getInt("Id");
        task.taskId = result.getString("TaskId");
        task.taskName = result.getString("TaskName");
        task.status = result.getShort("Status");
        task.date = result.getString("Date");
        task.startTime = result.getString("StartTime");
        task.endTime = result.getString("EndTime");
        task.isReRun = result.getBoolean("IsRerun");
    }

    private static void prepareStatementStop(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(LogTasksDao.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void resultSetStop(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(LogTasksDao.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int updateTaskEveryDay() {
        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String today = getCurrentDate();
        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (connection != null) {
                preparedStatement = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(UPDATE_TASK_EVERYDAY);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_DOISOAT_HD_BANK);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_DOISOAT_VIETIN_BANK);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_DOISOAT_ESALE);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_PROCESS_PENDING);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_RUN_RECOMFIRM);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_DAILYUPDATE);
                updateData(preparedStatement, StorageCommonDef.Task_Status.NEW.ordinal(), today,
                        StorageCommonDef.TASK_ID_NOTIFY);
                preparedStatement.executeBatch();
                result = PaymentReturnCode.DatabaseCode.DB_SUCCESS;
            }
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(preparedStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
        }
        return result;
    }

    public static int insertTask() {
        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String today = getCurrentDate();
        try {
            conn = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            if (conn != null) {
                preparedStatement = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(INSERT_TASK);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_DOISOAT_HD_BANK,
                        StorageCommonDef.NAME_DOISOAT_HD_BANK, StorageCommonDef.Task_Status.NEW.ordinal(), today, false);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_DOISOAT_VIETIN_BANK,
                        StorageCommonDef.NAME_DOISOAT_VIETIN_BANK, StorageCommonDef.Task_Status.NEW.ordinal(), today, false);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_DOISOAT_ESALE, StorageCommonDef.NAME_DOISOAT_ESALE,
                        StorageCommonDef.Task_Status.NEW.ordinal(), today, false);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_PROCESS_PENDING, StorageCommonDef.NAME_PENDING, StorageCommonDef.Task_Status.NEW.ordinal(),
                        today, false);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_RUN_RECOMFIRM, StorageCommonDef.NAME_RECOMFIRM,
                        StorageCommonDef.Task_Status.NEW.ordinal(), today, true);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_DAILYUPDATE, StorageCommonDef.NAME_DAILYUPDATE,
                        StorageCommonDef.Task_Status.NEW.ordinal(), today, true);
                insertData(preparedStatement, StorageCommonDef.TASK_ID_NOTIFY, StorageCommonDef.NAME_ID_NOTIFY,
                        StorageCommonDef.Task_Status.NEW.ordinal(), today, true);
                preparedStatement.executeBatch();
            }
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(preparedStatement);
            DBConnectionManager.getInstance().returnConnection(conn);
        }
        return result;
    }

    public static int updateTaskStart(T_Task task) {
        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;
        Connection connection = null;
        PreparedStatement prepareStatement = null;

        String startTime = getCurrentTime();
        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            prepareStatement = connection.prepareStatement(UPDATE_TASK_START);
            prepareStatement.setInt(1, StorageCommonDef.Task_Status.RUNNING.ordinal());
            prepareStatement.setString(2, startTime);
            prepareStatement.setString(3, task.taskId);
            result = prepareStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(prepareStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
        }
        return result;
    }

    public static int updateTaskStop(T_Task task) {
        int result = PaymentReturnCode.DatabaseCode.DB_ERROR;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        //         int status = (flag ==true)?STA_SUCCESS:STA_FALSE;
        String endTime = getCurrentTime();
        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            prepareStatement = connection.prepareStatement(UPDATE_TASK_STOP);
            prepareStatement.setShort(1, task.getStatus());
            prepareStatement.setString(2, endTime);
            prepareStatement.setString(3, task.taskId);

            prepareStatement.executeUpdate();
            result = PaymentReturnCode.DatabaseCode.DB_SUCCESS;
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(prepareStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
        }
        return result;
    }

    public static T_Task getTaskById(int i) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        T_Task task = new T_Task();
        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            prepareStatement = connection.prepareStatement(GET_TASK_BY_ID);
            prepareStatement.setInt(1, i);
            result = prepareStatement.executeQuery();
            if (result.next()) {
                getTaskFromData(result, task);
            }
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(prepareStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
            resultSetStop(result);
        }
        return task;
    }
    
    public static T_Task getTaskByTaskId(String taskID) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        T_Task task = new T_Task();
        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            prepareStatement = connection.prepareStatement(GET_TASK_BY_TASKID);
            prepareStatement.setString(1, taskID);
            result = prepareStatement.executeQuery();
            if (result.next()) {
                getTaskFromData(result, task);
            }
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            prepareStatementStop(prepareStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
            resultSetStop(result);
        }
        return task;
    }

    public static List<T_Task> getListTaskToday() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        List<T_Task> ListTask = new ArrayList<T_Task>();

        try {
            connection = DBConnectionManager.getInstance().getConnection(Config.timeOut);
            prepareStatement = connection.prepareStatement(GET_LIST_TASK);
            result = prepareStatement.executeQuery();

            while (result.next()) {
                T_Task task = new T_Task();
                getTaskFromData(result, task);
                ListTask.add(task);
            }
        } catch (Exception ex) {
            Logger.getLogger(LogTasksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            resultSetStop(result);
            prepareStatementStop(prepareStatement);
            DBConnectionManager.getInstance().returnConnection(connection);
        }
        return ListTask;
    }
}
