package vng.zingme.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;
import vng.zingme.stats.model.User2;

@Controller
@RequestMapping("/admin")
public class AccountUpdateController {
	@RequestMapping(value = "/accountupdate/userId={userName}", method = RequestMethod.GET)
	public String getUpdateAccount(@PathVariable("userName") String userName,
			ModelMap model) {
			
		// get user from DB
		UserDao uDao = new UserDaoImpl();
		User2 user = uDao.getUserByName(userName);
		model.addAttribute("user", user);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("adminName", user.getAdminName());
		return "/jsp/accountupdate.jsp";
	}

	@RequestMapping(value = "/accountupdate/userId={userName}", method = RequestMethod.POST)
	public String postUpdateAccount(@PathVariable("userName") String userName,
			@ModelAttribute("user") User2 user) {
		// update DB
		UserDao uDao = new UserDaoImpl();
		uDao.updateAdminName(userName, user.getAdminName().trim());
		return "redirect:" + "/admin/accountcontrol";
	}

}
