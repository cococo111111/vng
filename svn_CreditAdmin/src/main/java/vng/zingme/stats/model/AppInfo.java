package vng.zingme.stats.model;

import java.util.ArrayList;
import java.util.List;

import vng.zingme.payment.thrift.T_AppInfo;

public class AppInfo {
	private String appId;
	private String appName;
	private String appRestUrl;
	private String key1;
	private String key2;
	private String appLogo;
	private String appDesc;
	private String appUrl;
	private String status = "0";
	private Byte isEnabale;
	private List<Integer> whiteList = new ArrayList<Integer>();
	private String position;
	private String isNewCypher;

	public String getIsNewCypher() {
		return isNewCypher;
	}

	public void setIsNewCypher(String isNewCypher) {
		this.isNewCypher = isNewCypher;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Integer> getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(List<Integer> whiteList) {
		this.whiteList = whiteList;
	}

	public Byte getIsEnabale() {
		return isEnabale;
	}

	public void setIsEnabale(Byte isEnabale) {
		this.isEnabale = isEnabale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AppInfo(String appId, String appName, String appRestURL,
			String key1, String key2) {
		super();
		this.appId = appId;
		this.appName = appName;
		this.appRestUrl = appRestURL;
		this.key1 = key1;
		this.key2 = key2;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public AppInfo() {
		super();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppRestUrl() {
		return appRestUrl;
	}

	public void setAppRestUrl(String appRestUrl) {
		this.appRestUrl = appRestUrl;
	}

	public String getAppLogo() {
		return appLogo;
	}

	public void setAppLogo(String appLogo) {
		this.appLogo = appLogo;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	/**
	 * @param T_AppInfo
	 * @return AppInfo
	 */
	public static AppInfo conver2AppInfo(T_AppInfo appi) {
		AppInfo appinfo = new AppInfo();

		appinfo.setAppId(appi.getAppID());
		appinfo.setAppName(appi.getAppName());
		appinfo.setAppDesc(appi.getAppDes());
		appinfo.setAppUrl(appi.getAppURL());
		appinfo.setAppLogo(appi.getIconPath());
		appinfo.setAppRestUrl(appi.restURL);
		appinfo.setKey1(appi.getKey1());
		appinfo.setKey2(appi.getKey2());
		appinfo.setIsEnabale(appi.isEnabled);
		appinfo.setWhiteList(appi.getLswhitelist());

		return appinfo;
	}

	/**
	 * @param appinfo
	 * @return T_AppInfo
	 */
	public static T_AppInfo conver2T_Appinfo(AppInfo appinfo) {
		T_AppInfo t_Appinfo = new T_AppInfo();

		t_Appinfo.setAppID(appinfo.getAppId());
		t_Appinfo.setAppDes(appinfo.getAppDesc());
		t_Appinfo.setAppName(appinfo.getAppName());
		t_Appinfo.setAppURL(appinfo.getAppUrl());
		t_Appinfo.setIconPath(appinfo.getAppLogo());
		t_Appinfo.setRestURL(appinfo.getAppRestUrl());
		t_Appinfo.setIsNewCryto("1".equals(appinfo.getIsNewCypher()));
		t_Appinfo.setIsEnabled(new Byte("1"));

		return t_Appinfo;
	}

}
