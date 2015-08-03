package vng.zingme.stats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.model.AppInfo;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.service.AppService;

@Controller
@RequestMapping("/admin")
public class AppManageController {

	//private static Logger log = Logger.getLogger(AppManageController.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/appmanage", method = RequestMethod.GET)
	public ModelAndView appManage(ModelMap model) {
		
		List<T_AppInfo> allApps = appService.get_List_T_AppInfo_Sorted();
		
		String key = "";
		model.addAttribute("apps", allApps);
		model.addAttribute("genKey", key);

		return new ModelAndView("/jsp/appmanage.jsp", model);
	}

	@RequestMapping(value = "/appmanage/appId={appId}", method = RequestMethod.POST)
	public ModelAndView appManagePost(@ModelAttribute("app") AppInfo app,
			@PathVariable("appId") String appId, ModelMap model) {

		String adminSign = Config2.adminsignatureKey;

		model.addAttribute("appId", app.getAppId());
		model.addAttribute("appName", app.getAppName());
		model.addAttribute("appDes", app.getAppDesc());
		model.addAttribute("appLogo", app.getAppLogo());
		model.addAttribute("appUrl", app.getAppUrl());
		model.addAttribute("appRestUrl", app.getAppRestUrl());
		model.addAttribute("status", app.getStatus());
		model.addAttribute("isNewCypher", app.getIsNewCypher());

		AppServiceHandler appHandler = AppServiceHandler.getMainInstance(
				Config2.appinfohost, Config2.appinfoport);
		ModelAndView mav = new ModelAndView("/jsp/appmanage.jsp");

		try {
			// appinfo
			T_AppInfo appinfo = new T_AppInfo();
			appinfo.setAppID(app.getAppId());
			appinfo.setAppName(app.getAppName());
			appinfo.setAppDes(app.getAppDesc());
			appinfo.setIconPath(app.getAppLogo());
			appinfo.setAppURL(app.getAppUrl());
			appinfo.setRestURL(app.getAppRestUrl());
			appinfo.setIsEnabled(new Byte("1"));
			appinfo.setIsNewCryto("1".equals(app.getIsNewCypher()));
			boolean blnNewKey = false;
			if ("1".equals(app.getStatus())) {
				blnNewKey = true;
			}
			String genKey = appHandler.registerGameServer(appinfo, adminSign,
					blnNewKey);
			System.out.println("appinfo isnew " + appinfo.isNewCryto);

			ModelMap model2 = mav.getModelMap();
			List<T_AppInfo> allApps = appHandler.getAllAppInfo();

			model2.addAttribute("apps", allApps);
			model2.addAttribute("genKey", genKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("POST appinfo error : " + e.getMessage());
		}
		return mav;
	}

}
