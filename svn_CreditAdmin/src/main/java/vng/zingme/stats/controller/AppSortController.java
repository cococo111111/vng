package vng.zingme.stats.controller;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.model.AppInfo;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.service.AdminReportHandler;
import vng.zingme.stats.service.AppService;

@Controller
@RequestMapping("/admin")
public class AppSortController {
	private MemcachedClient _memClient = null;
	protected final Object _tokenmemClientLock = new Object();
	private Map<String, Object> _result = null;
	private static final Logger log = Logger.getLogger(AppSortController.class);

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/appsort", method = RequestMethod.GET)
	public ModelAndView appSort() {
		List<T_AppInfo> allApps = appService.get_List_T_AppInfo_Sorted();
		this.sortAppInfoCache(allApps);

		List<AppInfo> allAppInfo = new ArrayList<AppInfo>();
		for (T_AppInfo app : allApps) {
			AppInfo a = AppInfo.conver2AppInfo(app);

			String pos = "0";
			if (!_result.containsKey(app.getAppID())) {
				pos = "0";
			} else {
				if (_result.get(app.getAppID()) instanceof String) {
					pos = _result.get(app.getAppID()).toString();
				}
				if (_result.get(app.getAppID()) instanceof byte[]) {
					pos = new String((byte[]) _result.get(app.getAppID()));
				}
			}
			a.setPosition(pos);
			allAppInfo.add(a);
		}

		ModelAndView mav = new ModelAndView("/jsp/appsort.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("apps", allAppInfo);
		return mav;
	}

	@RequestMapping(value = "/appsort", method = RequestMethod.POST)
	public @ResponseBody
	String changeAppPosition(@RequestParam("appId") String appId,
			@RequestParam("sort") String sort) {
		try {
			AdminReportHandler.setPosition(appId, Integer.parseInt(sort));
			_memClient.set(appId, 0, sort);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sort;
	}

	@RequestMapping(value = "/reloadappsort", method = RequestMethod.POST)
	public @ResponseBody
	String reloadAppPosition(@RequestParam("appId") String appId,
			@RequestParam("pos") String post) {
		try {
			post = String.valueOf(AdminReportHandler.getPosition(appId));
			_memClient.set(appId, 0, post);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return post;
	}

	@RequestMapping(value = "/resynallapp", method = RequestMethod.GET)
	public ModelAndView reSynAllApp() {
		List<T_AppInfo> allApps = appService.get_List_T_AppInfo_Sorted();
		this.sortAppInfoCache(allApps);
		List<AppInfo> allAppInfo = new ArrayList<AppInfo>();
		try {
			for (T_AppInfo app : allApps) {
				AppInfo a = AppInfo.conver2AppInfo(app);

				String pos = "0";
				if (!_result.containsKey(app.getAppID())) {
					pos = "0";
				} else {
					if (_result.get(app.getAppID()) instanceof String) {
						pos = _result.get(app.getAppID()).toString();
					}
					if (_result.get(app.getAppID()) instanceof byte[]) {
						pos = new String((byte[]) _result.get(app.getAppID()));
					}
				}
				a.setPosition(pos);
				allAppInfo.add(a);
			}
			Map<String, Integer> allPos = AdminReportHandler.getAllPosition();
			for (String key : allPos.keySet()) {
				_memClient.set(key, 0, String.valueOf(allPos.get(key)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		ModelAndView mav = new ModelAndView("/jsp/appsort.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("apps", allAppInfo);
		return mav;
	}

	List<T_AppInfo> sortAppInfoCache(List<T_AppInfo> appInfos) {
		try {

			List<String> appkeys = new ArrayList<String>();
			for (T_AppInfo app : appInfos) {
				appkeys.add(app.getAppID());
			}
			_result = getTokenMemCache(appkeys);
			if (_result == null) {
				return appInfos;
			}
			Collections.sort(appInfos, new Comparator<T_AppInfo>() {

				public int compare(T_AppInfo app1, T_AppInfo app2) {
					String b1 = "0";
					String b2 = "0";
					if (!_result.containsKey(app1.getAppID())) {
						b1 = "0";
					} else {
						if (_result.get(app1.getAppID()) instanceof String) {
							b1 = _result.get(app1.getAppID()).toString();
						}
						if (_result.get(app1.getAppID()) instanceof byte[]) {
							b1 = new String((byte[]) _result.get(app1
									.getAppID()));
						}
					}
					if (!_result.containsKey(app2.getAppID())) {
						b2 = "0";
					} else {

						if (_result.get(app2.getAppID()) instanceof String) {
							b2 = _result.get(app2.getAppID()).toString();
						}
						if (_result.get(app2.getAppID()) instanceof byte[]) {
							b2 = new String((byte[]) _result.get(app2
									.getAppID()));
						}
					}
					if (new Integer(b2).compareTo(new Integer(b1)) == 0) {
						return app1.getAppID().compareTo(app2.getAppID());
					}
					return new Integer(b2).compareTo(new Integer(b1));
				}
			});
		} catch (Exception ex) {
			log.error("sortAppInfoCache ex: " + ex.getMessage());
		}
		return appInfos;
	}

	protected MemcachedClient getMemClient() {
		synchronized (_tokenmemClientLock) {
			if (_memClient == null) {
				_memClient = getMemCacheClient(Config2.memcachehost,
						Config2.memcacheport);
			}
		}
		return _memClient;
	}

	protected MemcachedClient renewMemClient() {
		synchronized (_tokenmemClientLock) {
			if (_memClient != null) {
				_memClient.shutdown();
			}
			_memClient = getMemCacheClient(Config2.memcachehost,
					Config2.memcacheport);
		}
		return _memClient;
	}

	protected MemcachedClient getMemCacheClient(String host, int port) {
		MemcachedClient client = null;
		try {
			client = new MemcachedClient(new InetSocketAddress(host, port));
		} catch (Exception ex) {
			log.error("getMemCacheClient ex: " + ex.getMessage());
		}
		return client;
	}

	protected Map<String, Object> getTokenMemCache(List<String> key) {
		int hope_count = 3;
		boolean flag = false;
		Map<String, Object> obj = null;
		while (hope_count > 0 && !flag) {
			try {
				obj = _memClient.getBulk(key);
				if (obj != null) {
					flag = true;
				}
			} catch (Exception ex) {
				log.error("getTokenMemCache ex:" + ex.getMessage());
				renewMemClient();
			}
			--hope_count;
		}
		return obj;
	}
}
