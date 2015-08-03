package com.services.soap.mo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.DBPool;

public class WSConfigLoader {
	private static WSConfigLoader instance = null;
	
	private Map<String, WSConfig> cachedWSConfig = new HashMap<String, WSConfig>();;
	private Map<String, KeywordWhiteList> cachedKeywordWhiteList = new HashMap<String, KeywordWhiteList>();;
	
	private WSConfigLoader() {
		retrieveConfig();
	}
	
	public synchronized void retrieveConfig(){
		DBPool dbpool = null;
		Connection connection = null;
		try{
			dbpool = new DBPool();
			connection = dbpool.getConnectionGateway(); 
			
			loadAllWSConfig(connection);
			loadAllKeywordWhiteList(connection);
		}catch(Exception e){
			Util.logger.error(this.getClass().getName() + ".WSConfigLoader() ERROR: " + e.getMessage());
		}
		finally{
			dbpool.cleanup(connection);
		}		
	}
	
	public static WSConfigLoader getInstance(){
		if (instance == null){
			instance = new WSConfigLoader();
		}
		return instance;
	}
	
	private void loadAllWSConfig(Connection connection){
		String query = "SELECT cp_id, cp_name, ws_url, user_name, password, class_name, cp_code FROM webservice_config";
		PreparedStatement ps = null;
		Map<String, WSConfig> dbWSConfig;
		try {
			
			ps = connection.prepareStatement(query);
			ResultSet rs =  ps.executeQuery();
			dbWSConfig = new HashMap<String, WSConfig>();
			WSConfig wsConfig = null; 
			while ( rs.next() ){
				wsConfig = new WSConfig();
				wsConfig.setCpID(rs.getInt("cp_id"));
				wsConfig.setCpName(SoapUtils.convertToStringIfNull(rs.getString("cp_name"), ""));
				wsConfig.setWsURL(SoapUtils.convertToStringIfNull(rs.getString("ws_url"), ""));
				wsConfig.setUserName(SoapUtils.convertToStringIfNull(rs.getString("user_name"), ""));
				wsConfig.setPassword(SoapUtils.convertToStringIfNull(rs.getString("password"),""));
				wsConfig.setClassName(SoapUtils.convertToStringIfNull(rs.getString("class_name"), ""));
				wsConfig.setCpCode(SoapUtils.convertToStringIfNull(rs.getString("cp_code"), ""));
				dbWSConfig.put(rs.getString("cp_code"), wsConfig);
			}
			rs.close();
			cachedWSConfig = dbWSConfig;
		} catch (SQLException e) {
			Util.logger.error(this.getClass().getName() + ".loadWSConfigByCPName() ERROR: " + e.getMessage());
		} catch (Exception e) {
			Util.logger.error(this.getClass().getName() + ".loadWSConfigByCPName() ERROR: " + e.getMessage());
		}
		finally {
			if (ps != null){
				try{
					ps.close();
				}catch (Exception e) {
				}
			}
		}
	}
	
	private void loadAllKeywordWhiteList(Connection connection){
		String query = "SELECT keyword, service_id, operator, whitelist, msg_return, msg_type FROM keyword_whitelist";
		PreparedStatement ps = null;
		Map<String, KeywordWhiteList> dbKeywordWhiteList;
		try {

			ps = connection.prepareStatement(query);
			ResultSet rs =  ps.executeQuery();
			dbKeywordWhiteList = new HashMap<String, KeywordWhiteList>();
			KeywordWhiteList keywordWhiteList = null; 
			while ( rs.next() ){
				keywordWhiteList = new KeywordWhiteList();
				keywordWhiteList.setKeyword(SoapUtils.convertToStringIfNull(rs.getString("keyword"), ""));
				keywordWhiteList.setServiceId(SoapUtils.convertToStringIfNull(rs.getString("service_id"), ""));
				keywordWhiteList.setMobileOperator(SoapUtils.convertToStringIfNull(rs.getString("operator"), ""));
				keywordWhiteList.setWhiteList(SoapUtils.convertToStringIfNull(rs.getString("whitelist"), ""));
				keywordWhiteList.setMsgReturn(SoapUtils.convertToStringIfNull(rs.getString("msg_return"), ""));
				keywordWhiteList.setMsgType(rs.getInt("msg_type"));
				
				dbKeywordWhiteList.put(keywordWhiteList.getKeyword() + keywordWhiteList.getServiceId() + keywordWhiteList.getMobileOperator(), keywordWhiteList);
			}
			rs.close();
			cachedKeywordWhiteList = dbKeywordWhiteList;
		} catch (SQLException e) {
			Util.logger.error(this.getClass().getName() + ".loadAllKeywordWhiteList() ERROR: " + e.getMessage());
		} catch (Exception e) {
			Util.logger.error(this.getClass().getName() + ".loadAllKeywordWhiteList() ERROR: " + e.getMessage());
		}
		finally {
			if (ps != null){
				try{
					ps.close();
				}catch (Exception e) {
				}
			}
		}
	}	
	
	public WSConfig getWSConfigByCpCode(String cpCode){
		WSConfig wsConfig = cachedWSConfig.get(cpCode);
		
		if (wsConfig == null){
			Util.logger.error(this.getClass().getName() + ".getWSConfigByCpCode() ERROR: " + "cpCode: [" + cpCode + "] did not exist. Please check table webservice_config config.");
			//throw new RuntimeException("cpCode: [" + cpCode + "] did not exist. Please check table webservice_config config.");
		}
		
		return wsConfig;
	}

	public final KeywordWhiteList getKeywordWhiteListByKeywordServiceID(String KeywordServiceID) {
		KeywordWhiteList keywordWhiteList = cachedKeywordWhiteList.get(KeywordServiceID);
		return keywordWhiteList;
	}

	public final Map<String, WSConfig> getCachedWSConfig() {
		return cachedWSConfig;
	}

	public final Map<String, KeywordWhiteList> getCachedKeywordWhiteList() {
		return cachedKeywordWhiteList;
	}
	
}
