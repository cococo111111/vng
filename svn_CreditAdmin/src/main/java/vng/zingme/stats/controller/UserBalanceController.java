package vng.zingme.stats.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.UserService;

@Controller
@RequestMapping("/")
public class UserBalanceController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "app/userbalance", method = RequestMethod.GET)
	public ModelAndView userBalance() {
		return new ModelAndView("/jsp/userbalance.jsp", "user", new User2());
	}

	@RequestMapping(value = "app/userbalance", method = RequestMethod.POST)
	public ModelAndView addUserBalance(
			@Valid @ModelAttribute("user") User2 user, BindingResult result) {

		int userId = userService.getUserId(user);
		if (userId == -1) {
			result.rejectValue("userName", "userError.error",
					"this userName is not exist");
			return new ModelAndView("/jsp/userbalance.jsp", "user", user);
		} else if (userId == -2) {
			result.rejectValue("userName", "userError.error",
					"userId must be a number");
			return new ModelAndView("/jsp/userbalance.jsp", "user", user);
		}
		User2 userB = new User2();
		if (userId > 0) {
			Double curBal = userService.getUserBalanceById(userId);
			userB.setUserBalance(String.valueOf(curBal));
			userB.setUserName(userService.getUserNameByUserId(userId));
			userB.setUserId(String.valueOf(userId));
		}
		return new ModelAndView("/jsp/userbalance.jsp", "userB", userB);
	}
}
