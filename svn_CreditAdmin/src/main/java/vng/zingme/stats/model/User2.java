package vng.zingme.stats.model;

import java.util.List;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.UserRoleDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;
import vng.zingme.stats.dao.impl.UserRoleDaoImpl;

/**
 * @author sonhoang
 * 
 */
public class User2 {

	/*
	 * Role: = ADMIN, ROLE_CS, ROLE_REPORT = "1" if role = ROLE_REPORT + nhom1:
	 * xem tat ca = "2" if role = ROLE_REPORT + nhom2: xem tat ca - [zingpay +
	 * admin]
	 */

	private String userId = "0";
	private String userName;
	private String adminName;
	private String currentPass;
	private String passWord;
	private String passWordConfirm;
	private List<String> appList;
	private String role;
	private String superAdmin;
	private String userBalance;
	private String txId;
	private String startTime;
	private String endTime;
	private String txStatus;

	public String getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(String userBalance) {
		this.userBalance = userBalance;
	}

	public String getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrentPass() {
		return currentPass;
	}

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public String getPassWordConfirm() {
		return passWordConfirm;
	}

	public void setPassWordConfirm(String passWordConfirm) {
		this.passWordConfirm = passWordConfirm;
	}

	public User2() {
		super();
	}

	public User2(String userName, String adminName, String passWord,
			List<String> appList, String role) {
		super();
		this.userName = userName;
		this.adminName = adminName;
		this.passWord = passWord;
		this.appList = appList;
		this.role = role;
	}

	public List<String> getAppList() {
		return appList;
	}

	public void setAppList(List<String> appName) {
		this.appList = appName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<Role2> getRole(String username) {
		UserRoleDao dao = new UserRoleDaoImpl();
		List<Role2> listRole = dao.getRoleByUserName(username);

		return listRole;
	}

	public List<String> getAppName(String username) {
		UserDao dao = new UserDaoImpl();
		List<String> appList = dao.getAppNameOfUser(username);
		return appList;
	}
}
