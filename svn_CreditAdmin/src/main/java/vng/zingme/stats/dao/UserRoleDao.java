package vng.zingme.stats.dao;

import java.util.List;

import vng.zingme.stats.model.Role2;

/**
 * @author sonhoang
 * 
 */
public interface UserRoleDao {

	/**
	 * @param username
	 * @param rolename
	 * @return 0 (nothing change) or the row count
	 */
	public int insert(String username, String rolename);

	/**
	 * @param username
	 * @return
	 */
	public List<Role2> getRoleByUserName(String username);

	/**
	 * @param userName
	 * @return 0 (nothing change) or the row count
	 */
	public int deleteUserByUserName(String userName);
}
