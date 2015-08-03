package com.vng.cpnew.db.dao;

import java.sql.Connection;

import com.vng.cpnew.db.dao.beans.EmsSendQueue;

public interface EmsSendQueueDAO {
	public int insert(Connection connection, EmsSendQueue emsSendQueue)
			throws Exception;

	public int insert(EmsSendQueue emsSendQueue) throws Exception;

}
