package vng.zingme.stats.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vng.zingme.stats.dao.RoleDao;
import vng.zingme.stats.model.Role2;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

/**
 * @author sonhoang
 * 
 */
@Repository
public class RoleDaoImpl implements RoleDao {

	private static Logger log = Logger.getLogger(RoleDaoImpl.class);
	private static final String INSERT_ROLE = "INSERT INTO `role` "
			+ "(`name`) VALUES (?);";

	private static final String GET_LIST_ROLE = "SELECT name FROM `role`";

	private Connection dbConnection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet res = null;

	public int insert(Role2 t) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(INSERT_ROLE);
			preparedStatement.setString(1, t.getName());

			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("INSERT ROLE FAIL" + ex.getMessage());
		} catch (Exception ex) {
			System.out
					.println("CONNECT DB INSERT ROLE FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm INSERT ROLE FAIL");
				}
			}
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
		}
		return rs;
	}

	@Override
	public List<Role2> getListRole() {
		List<Role2> listRole = null;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);

			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_LIST_ROLE);
			res = preparedStatement.executeQuery();

			if (!res.wasNull()) {
				listRole = new ArrayList<>();
				while (res.next()) {
					Role2 role = new Role2(res.getString("name"));
					listRole.add(role);
				}
			}
		} catch (Exception e) {
			log.error("SEARCH ROLE FAIL" + e.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_LIST_ROLE close fail " + ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm GET_LIST_ROLE close fail "
							+ ex.getMessage());
				}
			}
		}
		return listRole;
	}

}
