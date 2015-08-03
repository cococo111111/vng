package com.services.vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vmg.sms.common.Util;

public class SessionItems {
	private static final String className = "com.services.vote.SessionItems";
	
	private static final String TABLE_VOTING_ITEMS = "voting_items";
	private static final String TABLE_SESSION_ITEMS = "voting_session_item";
	public SessionItems() {
	}
	public static int findItemByCode(Connection connection, int sessionId, String itemCode){
		String query = "SELECT Item.id FROM " + TABLE_VOTING_ITEMS + " Item INNER JOIN " +  TABLE_SESSION_ITEMS 
						+ " SI ON Item.id = SI.item_id "
						+ " WHERE SI.session_id = ? AND Item.code = ? ";
		int itemId = 0;
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, sessionId);
			ps.setString(2, itemCode);
			ResultSet rs =  ps.executeQuery();
			if (rs.next()){
				itemId = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			Util.logger.error(className + ".findItemByCode() ERROR: " + e.getMessage());
		} catch (Exception e){
			Util.logger.error(className + ".findItemByCode() ERROR: " + e.getMessage());
		}
		finally {
			try{
				if (ps != null ){
					ps.close();
				}
			}catch (Exception e) {
			}
		}
		return itemId;
	}	

}
