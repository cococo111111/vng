package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.UserService;

@Controller
@RequestMapping("/admin")
public class WhiteListController {
	private static final Logger log = Logger
			.getLogger(WhiteListController.class);
	@Autowired
	private AppService appService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/whitelist", method = RequestMethod.GET)
	public String whileList(@RequestParam(value = "appId") String appId,
			@RequestParam(value = "appName") String appName,
			@ModelAttribute("usert") User2 user, ModelMap model) {

		List<User2> userWhiteList = this.getWhiteList(appId);

		model.addAttribute("user", userWhiteList);
		model.addAttribute("appId", appId);
		model.addAttribute("appName", appName);

		return "/jsp/whitelist.jsp";
	}

	@RequestMapping(value = "/whitelist", method = RequestMethod.POST)
	public ModelAndView postUser(@RequestParam(value = "appId") String appId,
			@RequestParam(value = "appName") String appName,
			@Valid @ModelAttribute("usert") User2 user, BindingResult result) {
		int userId = userService.getUserId(user);
		if (userId == -1) {
			result.rejectValue("userName", "userError.error",
					"this userName is not exist");
			return this.showWhListView(new ArrayList<User2>(), appId, appName);
		} else if (userId == -2) {
			result.rejectValue("userName", "userError.error",
					"userId must be a number");
			return this.showWhListView(new ArrayList<User2>(), appId, appName);
		}
		Boolean res = appService.addIdToWhitelist(appId, userId);
		if (!res) {
			result.rejectValue("userName", "userError.error",
					"cannot add  id into white List now!!! please contact adminSystem");
			return this.showWhListView(new ArrayList<User2>(), appId, appName);
		}
		List<User2> userWhiteList = this.getWhiteList(appId);

		return this.showWhListView(userWhiteList, appId, appName);
	}

	@RequestMapping(value = "/removewhitelist", method = RequestMethod.GET)
	public ModelAndView removeWhileList(
			@RequestParam(value = "uId") String userId,
			@RequestParam("appid") String appId,
			@RequestParam(value = "appName") String appName,
			@Valid @ModelAttribute("usert") User2 user, BindingResult result) {

		log.info("remove userId: " + userId + " in WhiteList of App: " + appId);
		if (!appService.removeIdFromWhitelist(appId, Integer.parseInt(userId))) {
			result.rejectValue("userName", "userError.error",
					"cannot delete whiteList now!!! please contact adminSystem");
			return new ModelAndView("/jsp/whitelist.jsp", "user", user);
		}

		List<User2> userWhiteList = this.getWhiteList(appId);

		return this.showWhListView(userWhiteList, appId, appName);

	}

	private List<User2> getWhiteList(String appId) {
		T_AppInfo appi = appService.get_T_AppInfo(appId);
		List<Integer> whList = appi.getLswhitelist();

		return appService.getListUserOfWhiteListApp(whList);
	}

	private ModelAndView showWhListView(List<User2> userWhList, String appId,
			String appName) {
		ModelAndView mav = new ModelAndView("/jsp/whitelist.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("user", userWhList);
		model.addAttribute("appId", appId);
		model.addAttribute("appName", appName);
		return mav;
	}

}
