package com.vng.adsnew.db.dao;

import java.sql.Connection;

import com.vng.adsnew.db.dao.beans.Group;

public interface GroupDAO {
	public Group getUserInfoByGroupID(Connection connection, int groupID)
			throws Exception;

	public Group getUserInfoByGroupID(int groupID) throws Exception;

	public int insert(Connection connection, Group group) throws Exception;

	public int insert(Group group) throws Exception;

}
