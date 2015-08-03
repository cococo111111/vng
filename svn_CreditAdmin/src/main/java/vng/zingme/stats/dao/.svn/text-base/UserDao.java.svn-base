package vng.zingme.stats.dao;

import java.util.List;

import vng.zingme.stats.model.User2;

/**
 * @author sonhoang
 * 
 */
public interface UserDao extends IDao<User2, User2> {

	/**
	 * get User by Name
	 * 
	 * @param UserName
	 * @return User
	 */
	public User2 getUserByName(String userName);

	/**
	 * @return List<User2> or null
	 */
	public List<User2> getAllUser();

	/**
	 * @param userName
	 * @param adminName
	 * @return the number of effected row or 0 (nothing change)
	 */
	public int updateAdminName(String userName, String adminName);

	/**
	 * @param userName
	 * @return the number of effected row or 0 (nothing change)
	 */
	public int deleteUserByName(String userName);

	/**
	 * @param userName
	 * @return the number of effected row or 0 (nothing change)
	 */
	public int resetUserpass(String userName);

	/**
	 * @param userName
	 * @return List<String> appName or null
	 */
	public List<String> getAppNameOfUser(String userName);

	/**
	 * @param userName
	 * @return String currentPass
	 */
	public String getCurrentPassByName(String userName);

	/**
	 * @param pass
	 * @return the number of effected row or 0 (nothing change)
	 */
	public int updatePass(String pass, String userName);
	
	/**
	 * @param userName
	 * @return boolean
	 */
	public boolean checkCreditReport(String userName);
}
