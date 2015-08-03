package com.services.soap.mo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.MsgObject;

public class ZMUtils {
	public static final String className = "com.services.soap.mo.ZMUtils";
	
	public static final String TABLE_CONTENT_PROVIDER = "content_provider";
	public static final String TABLE_PICTURE = "picture";
	public static final String TABLE_THEME = "theme";
	public static final String TABLE_CARD = "card";
	public static final String TABLE_RINGTONG = "media";
	public static final String TABLE_TEXT = "document";
	public static final String TABLE_OTHER_CAT = "other_cat";
	public static final int CATEGORY_ALBUM = 1;
	public static final int CATEGORY_PACKAGE = 3;
	
	public static final String TABLE_MO_CONTENT_LOG = "mo_content_log";
	
	public static final String CP_CODE = "CP_CODE";
	public static final String CP_ITEM_ID = "CP_ITEM_ID";
	public static final String CP_ID = "CP_ID";
	
	public static final String KEYWORD = "KEYWORD";
	public static final String PICTURE_ID = "PICTURE_ID";
	public static final String THEME_ID = "THEME_ID";
	public static final String CARD_ID = "CARD_ID";
	public static final String RINGTONG_ID = "RINGTONG_ID";
	public static final String TEXT_ID = "TEXT_ID";
	public static final String PACKAGE_ID = "PACKAGE_ID";
	public static final String ALBUM_ID = "ALBUM_ID";
	
	public static final String FRIEND_MOBILE = "FRIEND_MOBILE";
	
	public static final String ZING_MOBILE = "zingmobile";
	
	
	public static Map<String, String> getCPCodeFromZingMobile(String itemCode, String tableItem){
		Map<String, String> inforCP = new HashMap<String, String>();
		
		String query = "SELECT CP.code, P.cp_item_id FROM " + TABLE_CONTENT_PROVIDER + " CP INNER JOIN " + tableItem + " P ON CP.id = P.cp_id "
						+ " WHERE P.id = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnection(ZMUtils.ZING_MOBILE);
			ps = connection.prepareStatement(query);
			ps.setString(1, itemCode);
			ResultSet rs =  ps.executeQuery();
			if (rs != null && rs.next()){
				String cpCode =  rs.getString("code") != null ? rs.getString("code") : "";
				String cp_item_id =  rs.getString("cp_item_id") != null ? rs.getString("cp_item_id").trim() : "";
				if (!cp_item_id.equals("0")){
					inforCP.put(CP_ITEM_ID, cp_item_id); 
				}else{
					inforCP.put(CP_ITEM_ID, "");					
				} 
				inforCP.put(CP_CODE, cpCode);
			}else{
				inforCP.put(CP_CODE, ""); 
				inforCP.put(CP_ITEM_ID, ""); 
			}
			rs.close();
		} catch (SQLException e) {
			inforCP.put(CP_CODE, "-1"); 
			inforCP.put(CP_ITEM_ID, "-1"); 
			Util.logger.error(className + ".getCPNameFromZingMobile() ERROR: " + e.getMessage());
		} catch (Exception e) {
			inforCP.put(CP_CODE, "-1"); 
			inforCP.put(CP_ITEM_ID, "-1"); 
			Util.logger.error(className + ".getCPNameFromZingMobile() ERROR: " + e.getMessage());
		}
		finally {
			try{
				if (ps != null ){
					ps.close();
				}
			}catch (Exception e) {
			}
			dbpool.cleanup(connection);
		}
		return inforCP;
	}
	
	public static Map<String, String> getCPCodeFromZingMobile(int categoryId, int categoryType){
		Map<String, String> inforCP = new HashMap<String, String>();
		
		String query = "SELECT CP.code FROM " + TABLE_OTHER_CAT + " OC INNER JOIN "  + TABLE_CONTENT_PROVIDER + " CP on CP.id = OC.cp_id "
						+ " WHERE OC.id = ? AND OC.content_id = ? AND OC.active=1";
		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnection(ZMUtils.ZING_MOBILE);
			ps = connection.prepareStatement(query);
			ps.setInt(1, categoryId);
			ps.setInt(2, categoryType);
			ResultSet rs =  ps.executeQuery();
			if (rs != null && rs.next()){
				String cpCode =  SoapUtils.convertToStringIfNull(rs.getString("code"), "");
				inforCP.put(CP_CODE, cpCode);
			}else{
				inforCP.put(CP_CODE, ""); 
			}
			rs.close();
		} catch (SQLException e) {
			inforCP.put(CP_CODE, "-1"); 
			Util.logger.error(className + ".getCPCodeFromZingMobile(int categoryId, int categoryType) ERROR: " + e.getMessage());
		} catch (Exception e) {
			inforCP.put(CP_CODE, "-1"); 
			Util.logger.error(className + ".getCPCodeFromZingMobile(int categoryId, int categoryType) ERROR: " + e.getMessage());
		}
		finally {
			try{
				if (ps != null ){
					ps.close();
				}
			}catch (Exception e) {
			}
			dbpool.cleanup(connection);
		}
		return inforCP;
	}
	
	public static BigDecimal add2MoContentLog(MsgObject msgObject, String cpCode) {
		Util.logger.info(className + ".add2MoContentLog()");
		BigDecimal result =  msgObject.getRequestid();

		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(new Date());		
		String tablename = TABLE_MO_CONTENT_LOG + yyyyMM;
		
		String sSQLInsert = "INSERT INTO " + tablename
							+ "(REQUEST_ID, USER_ID, SERVICE_ID, MOBILE_OPERATOR, COMMAND_CODE, INFO, CP_CODE, RECEIVE_DATE)"
							+ " VALUES(?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			ps = connection.prepareStatement(sSQLInsert);
			ps.setBigDecimal(1, msgObject.getRequestid());
			ps.setString(2, msgObject.getUserid());
			ps.setString(3, msgObject.getServiceid());
			ps.setString(4, msgObject.getMobileoperator());
			ps.setString(5, msgObject.getKeyword());
			ps.setString(6, msgObject.getUsertext());
			ps.setString(7, cpCode);
			ps.setTimestamp(8, msgObject.getTTimes());
			
			if (ps.executeUpdate() != 1) {
				Util.logger.error(className + ".add2MoContentLog(): TABLE: ["+ tablename + "], REQUEST_ID:[" + msgObject.getRequestid() + "], "
									+ "USER_ID:[" + msgObject.getUserid()+ "], USER_TEXT:[" + msgObject.getUsertext() + "] FAILED.");
				result = new BigDecimal(-1);
			}
			ps.close();
		} catch (SQLException e) {
			Util.logger.error(className + ".add2MoContentLog(): TABLE: ["+ tablename + "], REQUEST_ID:[" + msgObject.getRequestid() + "], "
					+ "USER_ID:[" + msgObject.getUserid()+ "], USER_TEXT:[" + msgObject.getUsertext() + "] FAILED."
					+ "ERROR: " + e.getMessage());
			result = new BigDecimal(-1);
		} catch (Exception e) {
			Util.logger.error(className + ".add2MoContentLog(): TABLE: ["+ tablename + "], REQUEST_ID:[" + msgObject.getRequestid() + "], "
					+ "USER_ID:[" + msgObject.getUserid()+ "], USER_TEXT:[" + msgObject.getUsertext() + "] FAILED."
					+ "ERROR: " + e.getMessage());
			result = new BigDecimal(-1);
		}

		finally {
			dbpool.cleanup(connection);
		}
		return result;
	}
		
}
