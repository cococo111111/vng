package com.services.soap.mo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.MsgObject;


public class ServicePrice {
	private static ServicePrice instance = null;
	
	private Map<String, String> servicePrices =  new HashMap<String, String>();
	private Map<String, String> operatorMaxMoneyAllow =  new HashMap<String, String>();
	
	private ServicePrice(){
				
		servicePrices.put("6069", "500");
		servicePrices.put("6169", "1000");
		servicePrices.put("6269", "2000");
		servicePrices.put("6369", "3000");
		servicePrices.put("6469", "4000");
		servicePrices.put("6569", "5000");
		servicePrices.put("6669", "10000");
		servicePrices.put("6769", "15000");
		
		operatorMaxMoneyAllow.put("GPC", "150000");
		operatorMaxMoneyAllow.put("VMS", "150000");
		operatorMaxMoneyAllow.put("VIETEL", "150000");
		operatorMaxMoneyAllow.put("SFONE", "150000");
		
	}
	
	public static ServicePrice getInstance(){
		if (instance == null ){
			instance = new ServicePrice();			
		}
		return instance;		
	}
	
	public int getPriceByServiceNumber(String serviceNumber){		
		return new Integer(servicePrices.get(serviceNumber)).intValue();
	}
	
	public int getMaxMoneyAllowByOperator(String operator){		
		return new Integer(operatorMaxMoneyAllow.get(operator)).intValue();
	}
	
	public boolean overMaxMoneyAllow(MsgObject msgObject) {
		// get dd value of MO receive_date
		String dd = String.valueOf(msgObject.getTTimes().getDate());
		if (dd.length()<2) dd = "0".concat(dd);
		// get MO user_id
		String user_id = msgObject.getUserid();
		
		// get money used by user_id by date dd
		int money=0;
		
		Util.logger.info(this.getClass().getName() + ".overMaxMoneyAllow():" + msgObject.getUserid() + "@" + msgObject.getUsertext());
		
		String tablename = "quota_" + dd;
		String sSQL = "SELECT money FROM " + tablename + " WHERE user_id = ? ";

		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			ps = connection.prepareStatement(sSQL);
			ps.setString(1, user_id);
			
			ResultSet result = ps.executeQuery();
			if (result.next()) money = result.getInt("money");
			
			result.close();
			ps.close();
		} catch (SQLException e) {
			Util.logger.error(this.getClass().getName()  + ".overMaxMoneyAllow():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error getting money from " + tablename + ":" + e.toString());
		} catch (Exception e) {
			Util.logger.error(this.getClass().getName()  + ".overMaxMoneyAllow():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error getting money from " + tablename + ":" + e.toString());
		}

		finally {
			dbpool.cleanup(connection);
		}
		
		// check if money >= MAX_MONEY_ALLOW
		int price = this.getPriceByServiceNumber(msgObject.getServiceid());
		if (price + money >= this.getMaxMoneyAllowByOperator(msgObject.getMobileoperator())) {
			return true;
		}
		else
			return false;
	}
	
}