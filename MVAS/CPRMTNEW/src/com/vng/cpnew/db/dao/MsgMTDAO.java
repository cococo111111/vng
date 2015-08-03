package com.vng.cpnew.db.dao;

import java.sql.Connection;

import com.vng.cpnew.db.dao.beans.MsgMT;

public interface MsgMTDAO {
	public int insert(Connection connection, MsgMT msgMT) throws Exception;

	public int insert(MsgMT msgMT) throws Exception;

}
