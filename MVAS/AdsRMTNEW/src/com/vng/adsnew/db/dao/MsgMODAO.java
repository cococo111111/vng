package com.vng.adsnew.db.dao;

import java.sql.Connection;

import com.vng.adsnew.db.dao.beans.MsgMO;

public interface MsgMODAO {
	public int insert(Connection connection, MsgMO msgMO) throws Exception;

	public int insert(MsgMO msgMO) throws Exception;
}
