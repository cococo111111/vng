package com.vng.adsnew.db.dao;

import java.sql.Connection;

import com.vng.adsnew.db.dao.beans.SmsReceiveQueue;

public interface SmsReceiveQueueDAO {
	public int insert(Connection connection, SmsReceiveQueue smsReceiveQueue)
			throws Exception;

	public int insert(SmsReceiveQueue smsReceiveQueue) throws Exception;

}
