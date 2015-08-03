package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.stats.model.LastestTranx;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.UserService;

@Controller
@RequestMapping("/")
public class UserLatestTranxController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "app/latesttranx", method = RequestMethod.GET)
	public ModelAndView lastedTranxGet() {
		return new ModelAndView("/jsp/latesttranx.jsp", "user", new User2());
	}

	@RequestMapping(value = "app/latesttranx", method = RequestMethod.POST)
	public ModelAndView lastedTranxPost(
			@Valid @ModelAttribute("user") User2 user, BindingResult result) {

		int userId = userService.getUserId(user);
		if (userId == -1) {
			result.rejectValue("userName", "userError.error",
					"this userName is not exist");
			return new ModelAndView("/jsp/latesttranx.jsp", "user", user);
		} else if (userId == -2) {
			result.rejectValue("userName", "userError.error",
					"userId must be a number");
			return new ModelAndView("/jsp/latesttranx.jsp", "user", user);
		}
		List<LastestTranx> latestTranxsList = new ArrayList<>();
		if (userId > 0) {
			List<T_Transaction> tranxsList = userService
					.getUserLastestTranx(userId);
			// show latest Tranx
			for (T_Transaction tranx : tranxsList) {
				LastestTranx latestTranx = LastestTranx
						.convertT_Tranx2LastestTranx(tranx, userId);
				latestTranxsList.add(latestTranx);
			}
		}
		String userName = userService.getUserNameByUserId(userId);
		ModelAndView mav = new ModelAndView("/jsp/latesttranx.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("latestTranx", latestTranxsList);
		model.addAttribute("userName", userName);
		model.addAttribute("user", new User2());
		return mav;
	}

	@RequestMapping(value = "admin/deletelatesttranx", method = RequestMethod.GET)
	public ModelAndView deleteTranx(@RequestParam(value = "uId") String uId,
			@RequestParam(value = "tranxid") String txId) {
		List<LastestTranx> latestTranxsList = new ArrayList<LastestTranx>();
		List<T_Transaction> tranxsList = userService.removeUserLastestTranx(
				Integer.parseInt(uId), Long.parseLong(txId));

		for (T_Transaction tranx : tranxsList) {
			LastestTranx latestTranx = LastestTranx
					.convertT_Tranx2LastestTranx(tranx, Integer.parseInt(uId));
			latestTranxsList.add(latestTranx);
		}
		ModelAndView mav = new ModelAndView("/jsp/latesttranx.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("latestTranx", latestTranxsList);
		model.addAttribute("user", new User2());
		return mav;
	}
}
