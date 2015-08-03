package vng.zingme.stats.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.Common;
import vng.zingme.stats.service.Utils;

import com.vng.zing.stdprofile2.thrift.TValue;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private UserDao userDao;

	private static Logger log = Logger.getLogger(AppServiceImpl.class);

	public List<T_AppInfo> sortApp(List<T_AppInfo> appInfos) {
		try {
			Collections.sort(appInfos, new Comparator<T_AppInfo>() {

				public int compare(T_AppInfo app1, T_AppInfo app2) {
					return app1.getAppID().compareTo(app2.getAppID());
				}
			});
		} catch (Exception ex) {
			log.error("sort List exception: " + ex.getMessage());
		}
		return appInfos;
	}

	public List<T_AppInfo> get_List_T_AppInfo_Sorted() {

		List<T_AppInfo> allApps = new ArrayList<>();
		try {
			allApps = Common.getAppHandler().getAllAppInfo();

			Collections.sort(allApps, new Comparator<T_AppInfo>() {

				public int compare(T_AppInfo app1, T_AppInfo app2) {
					return app1.getAppID().compareTo(app2.getAppID());
				}
			});
		} catch (Exception e) {
			log.error("get_T_AppInfo fail " + e.getMessage());
		}
		return allApps;
	}

	@Override
	public String registerAppInfo(T_AppInfo appinfo) {
		String AdminSig = Config2.adminsignatureKey;
		boolean blnNewKey = true; // fixxx what is blnNewKey?
		String key = "";
		try {
			key = Common.getAppHandler().registerGameServer(appinfo, AdminSig,
					blnNewKey);
		} catch (Exception e) {
			log.error("register_T_AppInfo fail " + e.getMessage());
		}
		return key;
	}

	@Override
	public T_AppInfo get_T_AppInfo(String appId) {
		T_AppInfo appinfo = new T_AppInfo();
		try {
			appinfo = Common.getAppHandler().getAppInfo(appId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return appinfo;
	}

	@Override
	public List<User2> getListUserOfWhiteListApp(List<Integer> whList) {
		List<User2> userWhiteList = new ArrayList<>();
		try {
			if (whList != null) {
				for (Integer uid : whList) {
					TValue profile = Utils.getProfile(uid);

					User2 user = new User2();
					user.setUserId(uid.toString());
					user.setUserName(profile.userName);
					userWhiteList.add(user);
				}
			}
		} catch (Exception e) {
			log.error("ex in get getListUserOfWhiteListApp: " + e.getMessage());
		}
		return userWhiteList;
	}

	@Override
	public boolean addIdToWhitelist(String appId, int uID) {
		boolean result = true;
		try {
			Common.getAppHandler().addIdToWhitelist(appId, uID);
		} catch (Exception e) {
			log.error("add user=" + uID + " ToWhitelist appid= " + appId
					+ " error: " + e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public boolean removeIdFromWhitelist(String appId, int userId) {
		boolean rs = true;
		try {
			Common.getAppHandler().removeIdFromWhitelist(appId, userId);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs = false;
		}
		return rs;
	}

	@Override
	public List<TranByApp> getListAppOfLoggingUser() {
		// get logged in username
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String loggingUserName = auth.getName();

		List<String> appL = userDao.getAppNameOfUser(loggingUserName);
		List<TranByApp> appList = new ArrayList<TranByApp>();
		try {
			if ("1".equals(appL.get(0)) || "2".equals(appL.get(0))) {
				List<T_AppInfo> allApps = sortApp(Common
						.getAppHandler().getAllAppInfo());
				for (T_AppInfo t_AppInfo : allApps) {
					TranByApp a = new TranByApp();
					a.setAppId(t_AppInfo.getAppID());
					a.setAppName(t_AppInfo.getAppName());
					appList.add(a);
				}
			} else {
				for (String appId : appL) {
					String appName = Common.getAppHandler().getAppName(appId);
					TranByApp app = new TranByApp();
					app.setAppId(appId);
					app.setAppName(appName);
					appList.add(app);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return appList;
	}

}
