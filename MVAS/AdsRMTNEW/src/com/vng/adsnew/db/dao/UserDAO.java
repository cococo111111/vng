package com.vng.adsnew.db.dao;

import java.sql.Connection;

import com.vng.adsnew.db.dao.beans.User;

public interface UserDAO {
	public int changePassword(Connection connection, int userId, String password)
			throws Exception;

	public int changePassword(int userId, String password) throws Exception;

	public User getUser(Connection connection, String loginName, String password)
			throws Exception;

	public User getUser(String loginName, String password) throws Exception;

	public User getUserInfoByUserID(Connection connection, int userID)
			throws Exception;

	public User getUserInfoByUserID(int userID) throws Exception;

	public int insert(Connection connection, User user) throws Exception;

	public int insert(User user) throws Exception;

	public int update(Connection connection, User user) throws Exception;

	public int update(User user) throws Exception;

	public int deleteByUserID(Connection connection, int userID)
			throws Exception;

	public int deleteByUserID(int userID) throws Exception;

	public boolean checkUserName(Connection connection, String usr, String pwd,
			String ip) throws Exception;

	public String getOperator(Connection connection, String phoneNumber,
			String tableName, String YYYYDD) throws Exception;

	public String getOperator(String phoneNumber, String tableName,
			String YYYYDD) throws Exception;
}
