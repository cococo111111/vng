package com.services.vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.services.soap.mo.SoapUtils;
import com.vmg.sms.common.Util;

public class Session {
	private static final String className = "com.services.vote.Session";
	private static final String TABLE_VOTING_SESSION = "voting_session";
	private static final String TABLE_VOTING_LIST = "voting_list";
	
	private static final String MYSQL_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	private int id;
	private String name;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String  keyword;
	private String serviceID;
	private String voteType;
	private int voteNumberAgree;
	private String titleMessage;
	private String templateMessage;
	
	public final int getId() {
		return id;
	}
	
	public final void setId(int id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final Timestamp getBeginTime() {
		return beginTime;
	}
	public final void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public final Timestamp getEndTime() {
		return endTime;
	}
	public final void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public final String getKeyword() {
		return keyword;
	}
	public final void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public final String getServiceID() {
		return serviceID;
	}
	public final void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public final String getVoteType() {
		return voteType;
	}
	public final void setVoteType(String voteType) {
		this.voteType = voteType;
	}
	public final int getVoteNumberAgree() {
		return voteNumberAgree;
	}
	public final void setVoteNumberAgree(int voteNumberAgree) {
		this.voteNumberAgree = voteNumberAgree;
	}
	
	public static Session getVotingInfo(Connection connection, String keyword, String type, Date voteTime){
        String sVoteTime = new SimpleDateFormat(MYSQL_DATETIME).format(voteTime);
		
		String query = "SELECT S.id, S.name, begin_time, end_time, keyword, service_id, vote_type, vote_number_agree, title_message, template_message FROM " 
						+ TABLE_VOTING_SESSION + " S INNER JOIN " + TABLE_VOTING_LIST + " L ON S.vote_id=L.id "
						+ " WHERE keyword = ? AND vote_type = ? "
						+ " AND begin_time <= ? "
						+ " AND end_time >= ?";
		PreparedStatement ps = null;
		Session session = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, keyword);
			ps.setString(2, type);
			ps.setString(3, sVoteTime);
			ps.setString(4, sVoteTime);
			ResultSet rs =  ps.executeQuery();
			if (rs != null && rs.next()){
				session = new Session();
				session.setId(rs.getInt("id"));
				session.setName(SoapUtils.convertToStringIfNull(rs.getString("name"), ""));
				session.setBeginTime(rs.getTimestamp("begin_time"));
				session.setEndTime(rs.getTimestamp("end_time"));
				session.setKeyword(SoapUtils.convertToStringIfNull(rs.getString("keyword"), ""));
				session.setServiceID(SoapUtils.convertToStringIfNull(rs.getString("service_id"), ""));
				session.setVoteType(SoapUtils.convertToStringIfNull(rs.getString("vote_type"), ""));
				session.setVoteNumberAgree(rs.getInt("vote_number_agree"));
				session.setTitleMessage(SoapUtils.convertToStringIfNull(rs.getString("title_message"), ""));
				session.setTemplateMessage(SoapUtils.convertToStringIfNull(rs.getString("template_message"), ""));
			}
			rs.close();
		} catch (SQLException e) {
			session = new Session();
			session.setId(-1);
			Util.logger.error(className + ".getVotingInfo() ERROR: " + e.getMessage());
		} catch (Exception e) {
			session = new Session();
			session.setId(-1);
			Util.logger.error(className + ".getVotingInfo() ERROR: " + e.getMessage());
		}
		finally {
			try{
				if (ps != null ){
					ps.close();
				}
			}catch (Exception e) {
			}
		}
		return session;
	}

	public final String getTitleMessage() {
		return titleMessage;
	}

	public final void setTitleMessage(String titleMessage) {
		this.titleMessage = titleMessage;
	}

	public final String getTemplateMessage() {
		return templateMessage;
	}

	public final void setTemplateMessage(String templateMessage) {
		this.templateMessage = templateMessage;
	}
	
}
