package vng.zingme.stats.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.UserRoleDao;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

@Repository
public class UserDaoImpl implements UserDao {

	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	private static final String INSERT_USER = "INSERT INTO `user` "
			+ "(`username`, `pass`, `appName`,`adminName` ) VALUES (?,?,?,?);";
	private static final String GET_USER_BY_NAME = "SELECT * FROM `user`WHERE `username` =?";
	private static final String DELETE_USER_BY_NAME = " DELETE FROM `user`WHERE `username` =?";
	private static final String GET_ALL_USER = "SELECT * FROM `user`";
	private static final String UPDATE_ADMIN_NAME = "UPDATE `user` SET `adminname` = ? WHERE  `user`.`username` =?;";
	private static final String RESET_USER_PASS = "UPDATE  `user` SET  `pass` =  '81dc9bdb52d04dc20036dbd8313ed055' WHERE  `user`.`username` =?;";
	private static final String GET_APP_NAME = "SELECT `appName` FROM `user` WHERE `username` = ?;";
	private static final String GET_CURRENT_PASS_BY_NAME = "SELECT `pass` FROM  `user` WHERE  `username` = ?";
	private static final String UPDATE_PASS = "UPDATE  `user` SET  `pass` = ? WHERE  `user`.`username` =?;";

	private Connection dbConnection = null;
	private ResultSet res = null;
	private PreparedStatement preparedStatement = null;

	public int insert(User2 user2) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(INSERT_USER);

			StringBuilder appList = new StringBuilder();

			if (user2.getAppList() != null && user2.getAppList().size() != 0) {
				appList.append(user2.getAppList().get(0));
				for (int i = 1; i < user2.getAppList().size(); i++) {
					appList.append(",");
					appList.append(user2.getAppList().get(i));
				}
			}
			preparedStatement.setString(1, user2.getUserName());
			preparedStatement.setString(2, user2.getPassWord());
			preparedStatement.setString(3, appList.toString());
			preparedStatement.setString(4, user2.getAdminName());

			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("INSERT USER FAIL " + ex.getMessage());
		} catch (Exception ex) {
			System.out
					.println("CONNECT DB INSERT USER FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm INSERT USER FAIL");
				}
			}
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
		}
		return rs;
	}

	public User2 getUserByName(String name) {
		User2 user2 = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_USER_BY_NAME);
			preparedStatement.setString(1, name);

			res = preparedStatement.executeQuery();
			if (!res.wasNull()) {
				user2 = new User2();
				while (res.next()) {
					user2.setUserName(name);
					user2.setPassWord(res.getString("pass"));
					if (res.getString("appName") != null) {
						String[] appL = res.getString("appName").split(",");
						List<String> appList = new ArrayList<String>();
						for (String app : appL) {
							appList.add(app);
						}
						user2.setAppList(appList);
					}

					user2.setAdminName(res.getString("adminName"));
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_USER_BY_NAME fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_USER_BY_NAME close fail "
							+ ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm GET_USER_BY_NAME close fail "
							+ ex.getMessage());
				}
			}
		}
		return user2;
	}

	public List<User2> getAllUser() {
		List<User2> userList = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_ALL_USER);
			res = preparedStatement.executeQuery();
			if (!res.wasNull()) {
				userList = new ArrayList<>();
				while (res.next()) {
					User2 user2 = new User2();
					user2.setUserName(res.getString("userName"));
					user2.setPassWord(res.getString("pass"));
					user2.setAdminName(res.getString("adminname"));

					// get applist
					List<String> appsList = new ArrayList<>();
					String apps = res.getString("appName");
					if (!"".equals(apps) || null != apps) {
						String[] appsArray = apps.split(",");
						for (int i = 0; i < appsArray.length; i++) {
							appsList.add(appsArray[i]);
						}
						user2.setAppList(appsList);
					}
					userList.add(user2);
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_ALL_USER fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_ALL_USER close fail " + ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm GET_ALL_USER close fail " + ex.getMessage());
				}
			}
		}
		return userList;
	}

	public int updateAdminName(String userName, String adminName) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(UPDATE_ADMIN_NAME);

			preparedStatement.setString(1, adminName);
			preparedStatement.setString(2, userName);
			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("UPDATE_ADMIN_NAME FAIL " + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB UPDATE_ADMIN_NAME  FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm UPDATE_ADMIN_NAME FAIL");
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
	public int deleteUserByName(String userName) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(DELETE_USER_BY_NAME);
			preparedStatement.setString(1, userName);

			rs = preparedStatement.executeUpdate();

			// delete userRole in table USER_ROLE
			UserRoleDao urDao = new UserRoleDaoImpl();
			urDao.deleteUserByUserName(userName);

		} catch (SQLException ex) {
			log.error("DELETE_USER_BY_NAME FAIL " + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB DELETE_USER_BY_NAME FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm DELETE_USER_BY_NAME FAIL");
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
	public int resetUserpass(String userName) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(RESET_USER_PASS);
			preparedStatement.setString(1, userName);
			rs = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			log.error("RESET USER PASS FAIL " + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB RESET USER PASS   FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm RESET USER PASS  FAIL");
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
	public List<String> getAppNameOfUser(String userName) {
		List<String> appList = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_APP_NAME);
			preparedStatement.setString(1, userName);

			res = preparedStatement.executeQuery();
			if (!res.wasNull()) {
				appList = new ArrayList<>();
				while (res.next()) {
					String appName = res.getString("appName");
					String[] appArray = appName.split(",");
					appList = Arrays.asList(appArray);
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_APP_NAME fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_APP_NAME close fail " + ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm GET_APP_NAME close fail " + ex.getMessage());
				}
			}
		}
		return appList;
	}
	
	

	@Override
	public String getCurrentPassByName(String userName) {
		String currentPass = "";
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_CURRENT_PASS_BY_NAME);
			preparedStatement.setString(1, userName);

			res = preparedStatement.executeQuery();
			while (res.next()) {
				currentPass = res.getString("pass");
			}
		} catch (Exception ex) {
			log.error("Connection GET_CURRENT_PASS_BY_NAME fail "
					+ ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_CURRENT_PASS_BY_NAME close fail "
							+ ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm GET_CURRENT_PASS_BY_NAME close fail "
							+ ex.getMessage());
				}
			}
		}
		return currentPass;
	}

	@Override
	public int updatePass(String pass, String userName) {
		int rs = 0;
		try {
			dbConnection = DBConnectionManager.getInstance().getConnection(
					Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(UPDATE_PASS);

			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, userName);
			rs = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			log.error("UPDATE_PASS FAIL " + ex.getMessage());
		} catch (Exception ex) {
			log.error("CONNECT DB UPDATE_PASS  FAIL " + ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("CLOSE pstm UPDATE_PASS FAIL");
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
	public boolean checkCreditReport(String userName) {
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			preparedStatement = (PreparedStatement) dbConnection
					.prepareStatement(GET_APP_NAME);
			preparedStatement.setString(1, userName);

			res = preparedStatement.executeQuery();
			if (!res.wasNull()) {
				while (res.next()) {
					if (res.getString("appName") != null && res.getString("appName").indexOf("credits_report") >=0) {
						return true;
					}
				}
			}
		} catch (Exception ex) {
			log.error("Connection checkCreditReport fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res checkCreditReport close fail " + ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					log.error("pstm checkCreditReport close fail " + ex.getMessage());
				}
			}
		}
		return false;
	}

}
