package vng.zingme.stats.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.service.Common;
import vng.zingme.stats.service.UserService;
import vng.zingme.stats.service.Utils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public int getUserId(User2 user) {
		int uId = -1;
		try {
			if ("1".equals(user.getUserId())) {
				try {
					uId = Integer.parseInt(user.getUserName().trim());// 67919172
				} catch (NumberFormatException ex) {
					log.error(ex.getMessage());
					uId = -2;
				}
				try{
					 String userName = Utils.getProfile(uId).userName;
					 uId = Utils.getUIDFromUName(userName);
				}
				catch(Exception e){
					log.error(e.getMessage());
					uId=-1;
				}
			} else {
				uId = Utils.getUIDFromUName(user.getUserName().trim());
			}

		} catch (Exception e) {
			log.error("getUserId fail " + e.getMessage());
		}

		return uId;
	}

	@Override
	public String getLogingUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return auth.getName();
	}

	@Override
	public Double getUserBalanceById(int userId) {
		Double curBal = (double) -1;
		try {
			BalanceCacheHandler balanceHandler = BalanceCacheHandler
					.getMainInstance(Config2.balancehost, Config2.balanceport);
			curBal = balanceHandler.getBalance(userId).currentBalance;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return curBal;
	}

	@Override
	public List<T_Transaction> getUserLastestTranx(int userId) {
		List<T_Transaction> tranxsList = new ArrayList<>();
		try {
			tranxsList = Common.getTranxHandler().get(userId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return tranxsList;
	}

	@Override
	public List<T_Transaction> removeUserLastestTranx(int uId, Long txId) {
		List<T_Transaction> tranxsList = null;
		try {
			Common.getTranxHandler().removeTransaction(uId, txId);
			tranxsList = new ArrayList<>();
			tranxsList = Common.getTranxHandler().get(uId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return tranxsList;
	}

	@Override
	public String getUserNameByUserId(int userId) {
		String userName=""; 
		try{
			 userName = Utils.getProfile(userId).userName;
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return userName; 
	}

}
