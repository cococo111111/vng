package vng.zingme.payment.service;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import vng.zingme.payment.bo.thrift.BackEndHandler;
import vng.zingme.payment.bo.thrift.SerializeDeserializeHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.zk.MultiServerZookeeper;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ZKFrontEndService {

	private ZKFrontEndService() {
		try {
			_zk = MultiServerZookeeper.getInstance().newZookeeper();
		} catch (IOException ex) {
			log.warn(ex.getMessage());
		}
		_pushtranxpath = System.getProperty("vng.zingme.payment.push.tranx.path");

		_eventmode = Integer.parseInt(System.getProperty("eventmode", "0"));
		beHandler = BackEndHandler.getMainInstance();
	}

	public static ZKFrontEndService getInstance() {
		if (_instance == null) {
			synchronized (lockObj) {
				if (_instance == null) {
					_instance = new ZKFrontEndService();
				}
			}
		}
		return _instance;
	}
	private static ZooKeeper _zk = null;

	public static ZooKeeper getZk() {
		return _zk;
	}
	private static ZKFrontEndService _instance = null;
	private static String _pushtranxpath = null;
	private static byte[] _bpushdata = null;

	public int storepushTransaction(T_Transaction transaction, String auth) {
		String tranxPath = _pushtranxpath + CommonDef.FILE_SEP + transaction.txID;

		_bpushdata = serhandler.serialize(transaction).clone();
		if (_bpushdata == null) {
			return PaymentReturnCode.ERROR_OPERATOR_FAIL;
		}

		try {
			ZKFrontEndService._zk.create(tranxPath, _bpushdata, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			if (_eventmode != 0) {
				try {
					beHandler.tranxComing(transaction.txID);
				} catch (TException ex) {
					log.warn(ex.getMessage());
				}
			}

			return PaymentReturnCode.SUCCESS;
		} catch (KeeperException ex) {
			log.warn(ex.getMessage());
			if (ex.code() == KeeperException.Code.NODEEXISTS) {
				log.info("Have exist transaction: " + transaction.toString() + "in zookeeper. Transfered successed!");
				return PaymentReturnCode.SUCCESS;
			}
			if (ex.code() == KeeperException.Code.SESSIONEXPIRED || ex.code() == KeeperException.Code.SESSIONMOVED
					|| ex.code() == KeeperException.Code.CONNECTIONLOSS) {
				try {
					_zk.close();
				} catch (Exception except) {
					log.warn(LogUtil.stackTrace(except));
				}
				_zk = null;
				try {
					_zk = MultiServerZookeeper.getInstance().newZookeeper();
				} catch (IOException ex1) {
					log.warn(ex1);
				}
			}
		} catch (InterruptedException ex) {
			log.warn(LogUtil.stackTrace(ex));
		}

		return PaymentReturnCode.ERROR_OPERATOR_FAIL;
	}
	private static int _eventmode = -1;
	private static Object lockObj = new Object();
	private static final Logger log = Logger.getLogger("appActions");
	private static BackEndHandler beHandler = null;
	private static final SerializeDeserializeHandler serhandler = SerializeDeserializeHandler.getMainInstance();
}
