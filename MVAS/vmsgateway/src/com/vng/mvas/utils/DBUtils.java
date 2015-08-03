package com.vng.mvas.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vng.mvas.models.Sending;

public class DBUtils {

	public static int CPId = Integer.parseInt(System.getProperty("CPId"));
	public static int LIMIT = Integer.parseInt(System.getProperty("LIMIT"));
	private static Logger logger = Logger.getLogger(DBUtils.class);

	public static List<Sending> getData(String telco) throws SQLException {
		String SQL_GETMT = "SELECT * FROM ems_send_queue WHERE MOBILE_OPERATOR='"
				+ telco + "' ORDER BY PRIORITY DESC LIMIT " + LIMIT;

		List<Sending> rs = new ArrayList<Sending>();
		Connection connection = null;

		try {

			connection = DBConnectionManager.getInstance().getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(SQL_GETMT);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {

				while (resultSet.next()) {
					Sending data = new Sending();
					data.setID(resultSet.getLong("ID"));
					data.setUSER_ID(resultSet.getString("USER_ID"));
					data.setServiceId(resultSet.getString("SERVICE_ID"));
					data.setMOBILE_OPERATOR(resultSet
							.getString("MOBILE_OPERATOR"));
					data.setCOMMAND_CODE(resultSet.getString("COMMAND_CODE"));
					data.setCONTENT_TYPE(resultSet.getInt("CONTENT_TYPE"));
					data.setINFO(resultSet.getString("INFO"));
					try{
					data.setSUBMIT_DATE(resultSet.getTimestamp("SUBMIT_DATE"));
					data.setDONE_DATE(resultSet.getTimestamp("DONE_DATE"));
					
					}catch (Exception e) {
						logger.error(e.getMessage() +"\nCOMMAND_CODE:" +data.getCOMMAND_CODE()+" USER_ID:"+ data.getUSER_ID());
						data.setSUBMIT_DATE(new Timestamp(new Date().getTime()));
						data.setDONE_DATE(new Timestamp(new Date().getTime()));
					}
					data.setMESSAGE_TYPE(resultSet.getInt("MESSAGE_TYPE"));
					data.setREQUEST_ID(resultSet.getString("REQUEST_ID"));
					data.setMESSAGE_ID(resultSet.getString("MESSAGE_ID"));
					data.setTOTAL_SEGMENTS(resultSet.getInt("TOTAL_SEGMENTS"));
					data.setRETRIES_NUM(resultSet.getInt("RETRIES_NUM"));
					data.setCPId(resultSet.getInt("CPId"));

					rs.add(data);
				}
			}
			preparedStatement.close();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			DBConnectionManager.getInstance().returnConnection(connection);
		}

		return rs;
	}

	public static int logSending(Sending msg) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		String tableSuffix = df.format(msg.getDONE_DATE());
		String sql = "INSERT INTO ems_send_log"
				+ tableSuffix
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, CONTENT_TYPE, "
				+ " INFO, MESSAGE_TYPE, REQUEST_ID, MESSAGE_ID,  TOTAL_SEGMENTS,RETRIES_NUM, CPId, SUBMIT_DATE, DONE_DATE,NOTES,PROCESS_RESULT)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		Connection connection = null;
		int errorCode = 0;
		try {
			connection = DBConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			
			//Luu CDR if messageType # 0
			if(msg.getMESSAGE_TYPE()!=0)
			{
				insertCDR(connection, msg);
			}
			
			// if node=null. medssageTYpe=2,. process=4, ghi log
			if(msg.getNOTES()==null)
			{
				msg.setMESSAGE_TYPE(2);
				msg.setPROCESS_RESULT(4);
			}else{
				msg.setPROCESS_RESULT(1);
			}
				
			ps = connection.prepareStatement(sql);
			ps.setString(1, msg.getUSER_ID());
			ps.setString(2, msg.getServiceId());
			ps.setString(3, msg.getMOBILE_OPERATOR());
			ps.setString(4, msg.getCOMMAND_CODE());
			ps.setInt(5, msg.getCONTENT_TYPE());
			ps.setString(6, msg.getINFO());
			ps.setInt(7, msg.getMESSAGE_TYPE());
			ps.setString(8, msg.getREQUEST_ID());
			ps.setString(9, msg.getMESSAGE_ID());
			ps.setInt(10, msg.getTOTAL_SEGMENTS());
			ps.setInt(11, msg.getRETRIES_NUM());
			ps.setInt(12, CPId);
			ps.setString(13, msg.getSUBMIT_DATE().toString());
			ps.setString(14, msg.getDONE_DATE().toString());
			ps.setString(15, msg.getNOTES());
			ps.setInt(16, msg.getPROCESS_RESULT());
			ps.execute();
			errorCode = ps.getUpdateCount();
			if (errorCode > 0) {
				errorCode = deleteSendQueue(connection, msg);
			}
		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
			DBConnectionManager.getInstance().returnConnection(connection);
		}

		return errorCode;
	}

	public static int deleteSendQueue(Connection connection, Sending msg)
			throws SQLException {
		String sql = "DELETE FROM ems_send_queue " + "WHERE ID='" + msg.getID()
				+ "';";

		PreparedStatement ps = null;
		int errorCode = 0;
		try {
			ps = connection.prepareStatement(sql);
			errorCode = ps.executeUpdate();
			System.out.println("code DELETE =" + errorCode);
		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger.error(e.getMessage());
		} finally {
			ps.close();
		}
		if (errorCode > 0)
			connection.commit();
		return errorCode;
	}
	
	public static int insertCDR(Connection connection, Sending msg) throws SQLException {
		String sql = "INSERT INTO cdr_queue"
				+ " (USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, "
				+ " INFO,SUBMIT_DATE,DONE_DATE, TOTAL_SEGMENTS,PROCESS_RESULT,MESSAGE_TYPE, REQUEST_ID,CPId)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		int errorCode = 0;
		try {
				
			ps = connection.prepareStatement(sql);
			ps.setString(1, msg.getUSER_ID());
			ps.setString(2, msg.getServiceId());
			ps.setString(3, msg.getMOBILE_OPERATOR());
			ps.setString(4, msg.getCOMMAND_CODE());
			ps.setString(5, msg.getINFO());
			ps.setString(6, msg.getSUBMIT_DATE().toString());
			ps.setString(7, msg.getDONE_DATE().toString());
			ps.setInt(8, msg.getTOTAL_SEGMENTS());
			ps.setInt(9, 1);
			ps.setInt(10, msg.getMESSAGE_TYPE());
			ps.setString(11, msg.getREQUEST_ID());
			ps.setInt(12, CPId);
			ps.execute();
			errorCode = ps.getUpdateCount();
		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
		}

		return errorCode;
	}

}