package vng.zingme.stats.service;

import java.util.List;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.model.User2;

public interface AppService {

	/**
	 * sort Appinfo
	 * 
	 * @param appInfos
	 * @return List<T_AppInfo> after Sort or TException
	 */
	public List<T_AppInfo> sortApp(List<T_AppInfo> appInfos);

	/**
	 * @return List<T_AppInfo> after Sort or TException
	 */
	public List<T_AppInfo> get_List_T_AppInfo_Sorted();

	/**
	 * @param app
	 * @return "" (if exception) or key of registerT_AppInfo
	 */
	public String registerAppInfo(T_AppInfo app);

	/**
	 * @param appId
	 * @return T_AppInfo
	 */
	public T_AppInfo get_T_AppInfo(String appId);

	/**
	 * @param whList
	 * @return List<User2>
	 */
	public List<User2> getListUserOfWhiteListApp(List<Integer> whList);

	/**
	 * @param appId
	 * @param userID
	 * @return boolean
	 */
	public boolean addIdToWhitelist(String appId, int uID);

	/**
	 * @param appId
	 * @param userId
	 * @return boolean
	 */
	public boolean removeIdFromWhitelist(String appId, int userId);
	
	
	/**
	 * @return List<TranByApp>
	 */
	public List<TranByApp> getListAppOfLoggingUser();
	
	
}
