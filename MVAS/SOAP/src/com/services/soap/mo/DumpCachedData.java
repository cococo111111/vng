package com.services.soap.mo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.DBPool;

public class DumpCachedData extends Thread {
	public DumpCachedData(){
		
	}
	
	public void run() {
		DBPool dbpool = null;
		Connection connection = null;
		while (true) {
			try {
				Util.logger.info(this.getClass().getName() + ".run() Dump data:");
				dbpool = new DBPool();
				connection = dbpool.getConnectionGateway();				
				
				Util.logger.info("---------------Dump data of KeywordWhiteList----------------------");
				Map<String, KeywordWhiteList> mapWhiteList =  WSConfigLoader.getInstance().getCachedKeywordWhiteList();
				Iterator iterator = mapWhiteList.entrySet().iterator();
				while( iterator. hasNext() ){
					Entry<String, KeywordWhiteList> entry = (Entry<String, KeywordWhiteList>)iterator.next();
					KeywordWhiteList keywordWhiteList = entry.getValue();
					Util.logger.info("Keyword:[" + keywordWhiteList.getKeyword() + "], ServiceID:["+ keywordWhiteList.getServiceId() +"], WhiteList:[" + keywordWhiteList.getWhiteList() + "], Operator:[" + keywordWhiteList.getMobileOperator() + "]");
				}		
				Util.logger.info("-------------------------------------------------------------------");
				
				Util.logger.info("---------------Dump data of WSConfig-------------------------------");
				Map<String, WSConfig> mapWSConfig =  WSConfigLoader.getInstance().getCachedWSConfig();
				Iterator iterator2 = mapWSConfig.entrySet().iterator();
				while( iterator2. hasNext() ){
					Entry<String, WSConfig> entry = (Entry<String, WSConfig>)iterator2.next();
					WSConfig wSConfig = entry.getValue();
					Util.logger.info("CPcode :[" + wSConfig.getCpCode() + "], WsURL:[" + wSConfig.getWsURL() + "], " 
							+ "UserName:[" + wSConfig.getUserName() + "], Password:[" + wSConfig.getPassword() + "], "
							+ "ClassName:[" + wSConfig.getClassName() + "]");
				}		
				Util.logger.info("-------------------------------------------------------------------");
				
				sleep(1000 * 60 * 1);
			}catch (InterruptedException e) {
			}catch (NumberFormatException ex) {
				Util.logger.error(this.getClass().getName() + "run() ERROR:" + ex.getMessage());
			}catch (Exception ex1) {
				Util.logger.error(this.getClass().getName() + "run() ERROR:" + ex1.getMessage());
			}finally{
				dbpool.cleanup(connection);
			}
		}
	}
	
	private List<String> loadAllKeywordServiceID(Connection connection){
		String query = "SELECT keyword, service_id, operator FROM keyword_whitelist";
		PreparedStatement ps = null;
		List<String> list = new ArrayList<String>();
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs =  ps.executeQuery();
			while ( rs.next() ){
				String keyword = SoapUtils.convertToStringIfNull(rs.getString("keyword"), "").trim();
				String serviceId = SoapUtils.convertToStringIfNull(rs.getString("service_id"), "").trim();
				String operator = SoapUtils.convertToStringIfNull(rs.getString("operator"), "").trim();
				list.add(keyword + serviceId + operator);
			}
			rs.close();
		} catch (SQLException e) {
			Util.logger.error(this.getClass().getName() + ".loadAllKeywordServiceID() ERROR: " + e.getMessage());
		} catch (Exception e) {
			Util.logger.error(this.getClass().getName() + ".loadAllKeywordServiceID() ERROR: " + e.getMessage());
		}
		finally {
			if (ps != null){
				try{
					ps.close();
				}catch (Exception e) {
				}
			}
		}
		return list;
	}		
	private List<String> loadAllCPCode(Connection connection){
		String query = "SELECT cp_code FROM webservice_config";
		PreparedStatement ps = null;
		List<String> cpCodes = new ArrayList<String>();
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs =  ps.executeQuery();
			WSConfig wsConfig = null; 
			while ( rs.next() ){
				cpCodes.add(SoapUtils.convertToStringIfNull(rs.getString("cp_code"), "").trim());
			}
			rs.close();
		} catch (SQLException e) {
			Util.logger.error(this.getClass().getName() + ".loadAllCPCode() ERROR: " + e.getMessage());
		} catch (Exception e) {
			Util.logger.error(this.getClass().getName() + ".loadAllCPCode() ERROR: " + e.getMessage());
		}
		finally {
			if (ps != null){
				try{
					ps.close();
				}catch (Exception e) {
				}
			}
		}
		return cpCodes;
	}	
}
