package vng.zingme.stats.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.model.AppInfo;
import vng.zingme.stats.service.AppService;

@Controller
@RequestMapping("/admin")
public class AppRegisterController {

	@Autowired
	private AppService appService;

	private static Logger log = Logger.getLogger(AppRegisterController.class);

	@RequestMapping(value = "/registerapp", method = RequestMethod.GET)
	public ModelAndView userBalance(ModelMap model) {

		model.addAttribute("app", new AppInfo());
		return new ModelAndView("/jsp/registerapp.jsp", model);
	}

	@RequestMapping(value = "/registerapp", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("app") AppInfo app,
			BindingResult result) {

		T_AppInfo t_appinfo = AppInfo.conver2T_Appinfo(app);
		
		if ("".equals(appService.registerAppInfo(t_appinfo))) {
			result.rejectValue("appId", "appId.error",
					"Cannot Register App Now! Please wait or contact System Admin");

			log.info("register app fail, check AppServiceHandler.registerGameServer (or check AppServiceImpl");
			return "/jsp/registerapp.jsp";
		}
		return "redirect:" + "/admin/appmanage";
	}
}
