package vng.zingme.stats.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vng.zingme.stats.dao.UserRoleDao;
import vng.zingme.stats.model.Role2;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

/**
 * @author sonhoang
 * 
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	private static Logger log = Logger.getLogger(UserRoleDaoImpl.class);

	private static final String INSERT_USER_ROLE = "INSERT INTO `user_role` "
			+ "(`username` ,`rolename`)" + "VALUES (?,?);";

	private static final String GET_ROLE_BY_USERNAME = "SELECT DISTINCT "
			+ " role.name FROM user, user_role, role WHERE "
			+ " user.username = user_role.username "
			+ " AND user_role.rolename = role.name" + " AND user.username = ?";
	private static final String DELE_USER_ROLE_BY_USERNAME = "DELETE FROM "
			+ "`user_role` WHERE `user_role`.`username` = ?";

	private Connection dbConnection = null;
	private PreparedStatement preparedStatement = null;

	public int insert(String username, String rolename) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(INSERT_USER_ROLE);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, rolename);

			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("INSERT USER_ROLE FAIL" + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB INSERT USER_ROLE FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm INSERT USER_ROLE FAIL");
				}
			}
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
		}
		return rs;
	}

	public List<Role2> getRoleByUserName(String username) {

		List<Role2> listRole = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_ROLE_BY_USERNAME);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			// get list Role of user
			if (!rs.wasNull()) {
				listRole = new ArrayList<>();
				while (rs.next()) {
					String roleName = rs.getString("name");
					if (roleName == null) {
						return listRole;
					}
					Role2 role2 = new Role2(roleName);

					listRole.add(role2);
				}
			}
		} catch (SQLException ex) {
			log.error("GET GET_ROLE_BY_USERNAME FAIL" + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB GET_ROLE_BY_USERNAME FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm GET_ROLE_BY_USERNAME FAIL");
				}
			}
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
		}
		return listRole;
	}

	@Override
	public int deleteUserByUserName(String userName) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(DELE_USER_ROLE_BY_USERNAME);
			preparedStatement.setString(1, userName);

			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("DELE_USER_ROLE_BY_USERNAME  FAIL" + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB DELE_USER_ROLE_BY_USERNAME FAIL "
					+ ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm DELE_USER_ROLE_BY_USERNAME FAIL");
				}
			}
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
		}
		return rs;
	}

}
