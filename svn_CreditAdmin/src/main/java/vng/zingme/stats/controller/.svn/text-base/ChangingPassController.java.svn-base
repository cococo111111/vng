package vng.zingme.stats.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.Md5Encryption;

@Controller
@RequestMapping("/personal/")
public class ChangingPassController {
	@Autowired
	private UserDao userDao;

	private static Logger log = Logger.getLogger(ChangingPassController.class);

	@RequestMapping(value = "changepass", method = RequestMethod.GET)
	public ModelAndView changePassGet(@ModelAttribute("user") User2 user) {
		return new ModelAndView("/jsp/changepass.jsp", "user", user);
	}

	@RequestMapping(value = "changepass", method = RequestMethod.POST)
	public String changePassPost(@Valid @ModelAttribute("user") User2 user,
			BindingResult result, HttpServletRequest request) {
		String oldPass = user.getCurrentPass();
		// get currentPass (has md5 encrypted)
		String currentUser = ChangingPassController.getCurrentUserName();

		log.info("user" + currentUser + " changing passWord ... ");
		String currentPass = userDao.getCurrentPassByName(currentUser);

		if (!Md5Encryption.MD5(oldPass).equals(currentPass)) {
			result.rejectValue("currentPass", "currentPassError.error",
					"Wrong PassWord!");
			return "/jsp/changepass.jsp";
		} else {
			String newPass = user.getPassWord();
			int updatePassResult = userDao.updatePass(
					Md5Encryption.MD5(newPass), currentUser);
			if (updatePassResult == 0) {
				result.rejectValue("currentPass", "changePass.error",
						"Cannot change Password Now!!! Please contact with Admin");
				log.error(currentUser
						+ " cannot change PassWord because DB error");
				return "/jsp/changepass.jsp";
			}
		}
		log.info("change PassWord Success!");
		return "redirect:/layout";
	}

	private static String getCurrentUserName() {
		// get logged in username
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return auth.getName();

	}

}
