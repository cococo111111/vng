package vng.zingme.stats.service;

import java.util.List;

import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.stats.model.User2;

/**
 * @author sonhoang
 * 
 */
public interface UserService {

	/**
	 * @param User2
	 * @return int userId
	 */
	public int getUserId(User2 user);
	
	/**
	 * @return String userName is loging
	 */
	public String getLogingUser();
		
	/**
	 * @param userId
	 * @return double
	 */
	public Double getUserBalanceById(int userId);
	
	/**
	 * @param userId
	 * @return List<T_Transaction>
	 */
	public List<T_Transaction> getUserLastestTranx(int userId);
	
	/**
	 * @param uId
	 * @param txId
	 * @return List<T_Transaction>
	 */
	public  List<T_Transaction> removeUserLastestTranx(int uId, Long txId);

	/**
	 * @param userId
	 * @return String
	 */
	public String getUserNameByUserId (int userId);
	
}
