package vng.zingme.stats.controller;

import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.model.AppInfo;
import vng.zingme.stats.mySqlConnectionPool.Config2;

@Controller
@RequestMapping("/admin")
public class AppUpdateController {
	@RequestMapping(value = "/change/appId={appId}", method = RequestMethod.GET)
	public String appChange(@PathVariable("appId") String appId, ModelMap model) {

		AppServiceHandler appHandler = AppServiceHandler.getMainInstance(
				Config2.appinfohost, Config2.appinfoport);
		model.addAttribute("appId", appId);
		try {
			T_AppInfo appi = appHandler.getAppInfo(appId);
			AppInfo appinfo = new AppInfo();

			String key = "0";
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
			appinfo.setStatus(key);
			appinfo.setIsNewCypher(appi.isNewCryto == true ? "1" : "0");

			model.addAttribute("app", appinfo);
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/jsp/appupdate.jsp";
	}

	@RequestMapping(value = "/change/isEnable={isEnable}&appId={appId}")
	public @ResponseBody
	void appChange(@PathVariable("isEnable") String isEnable,
			@PathVariable("appId") String appId, ModelMap model) {

		AppServiceHandler appHandler = AppServiceHandler.getMainInstance(
				Config2.appinfohost, Config2.appinfoport);
		try {
			T_AppInfo a = appHandler.getAppInfo(appId);
			a.setIsEnabled(new Byte(isEnable));
			appHandler.registerGameServer(a, Config2.adminsignatureKey, false);

		} catch (Exception e) {
			System.out.println("e in enable app " + e.getMessage());
		}

	}

}