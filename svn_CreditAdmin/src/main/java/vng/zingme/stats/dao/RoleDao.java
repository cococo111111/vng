package vng.zingme.stats.dao;

import java.util.List;

import vng.zingme.stats.model.Role2;

/**
 * @author sonhoang
 * 
 */
public interface RoleDao extends IDao<Role2, Role2> {

	/**
	 * get all role
	 * 
	 * @return List<Role2> or null
	 */
	public List<Role2> getListRole();
}
