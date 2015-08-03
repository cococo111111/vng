package vng.zingme.stats.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.bo.thrift.AdminHandler;
import vng.zingme.payment.bo.thrift.ReportHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.model.UserAdjust;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.service.ReportService;
import vng.zingme.stats.service.UserService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/superadmin")
public class UserAdjustController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;
	private static final Logger log = Logger
			.getLogger(UserAdjustController.class);

	@RequestMapping(value = "/useradjust", method = RequestMethod.GET)
	public ModelAndView userAdjustGet(@ModelAttribute("user") UserAdjust user) {

		return new ModelAndView("/jsp/useradjust.jsp", "user", user);
	}

	@RequestMapping(value = "/useradjust", method = RequestMethod.POST)
	public ModelAndView userAdjustPost(
			@Valid @ModelAttribute("user") UserAdjust user,
			BindingResult result, HttpServletRequest req) {

		String reason = ("".equals(user.getReason1())) ? user.getReason2() : user
				.getReason1();
		String clientIp = req.getRemoteAddr();

		User2 u = new User2();
		u.setUserId(user.getUserId());
		u.setUserName(user.getUserName());
		int userId = userService.getUserId(u);
		if (userId == -1) {
			result.rejectValue("userName", "userError.error",
					"this userName is not exist");
			return new ModelAndView("/jsp/userbalance.jsp", "user", user);
		} else if (userId == -2) {
			result.rejectValue("userName", "userError.error",
					"userId must be a number");
			return new ModelAndView("/jsp/userbalance.jsp", "user", user);
		}
		if (userId > 0) {
			AdminHandler adminHandler = AdminHandler.getMainInstance(
					Config2.adminhost, Config2.adminport);

			int time = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);

			int rs;
			try {
				rs = adminHandler.adjustUser(userId,
						Double.parseDouble(user.getZingXu()),
						Config2.adminsignatureKey, reason, clientIp, time);
				if (rs == 0) {
					// success
					user.setStatus("You just add " + user.getZingXu()
							+ " ZingXu to " + user.getUserName()
							+ "'s account ! (" + reason + ")");
				} else {
					user.setStatus("cannot adjust ZingXu");
				}
			} catch (NumberFormatException | TException e) {
				log.error("data: uID =" + userId + " ZingXu= "
						+ user.getZingXu() + " reason= " + reason
						+ " clientIP= " + clientIp + " exception: " +e.getMessage());
			}

		}

		return new ModelAndView("/jsp/useradjust.jsp", "user", user);
	}

	@RequestMapping(value = "/tranxinfo", method = RequestMethod.POST)
	public @ResponseBody
	String getTranxinfo(@RequestParam String txId, @RequestParam String txTime)
			throws NumberFormatException, TException {

		Gson gson = new Gson();
		UserAdjust userAdjust = new UserAdjust();
		if (txId != null) {
			String fromdate = txTime + " 00:00:00";
			String todate = txTime + " 23:59:59";
			ReportHandler reportHandler = ReportHandler.getMainInstance(
					Config2.reporthost, Config2.reportport);
			List<T_ReportTransaction> list = reportHandler.getTransByUser(0,
					Long.parseLong(txId.trim()), fromdate, todate, 0, 10, 0);
			if (!list.isEmpty()) {
				T_ReportTransaction tranx = list.get(0);
				String itemname = tranx.itemNames == null ? ""
						: tranx.itemNames;

				gson = new Gson();

				userAdjust.setUserName(tranx.userName);
				userAdjust.setReason1("Hoàn lại "
						+ new Double(tranx.amount).intValue()
						+ " Zing Xu cho giao dịch mua " + itemname
						+ " game Ví Zing Me (mã: " + tranx.txID + ")'");
				userAdjust.setZingXu(tranx.amount + "");
				return gson.toJson(userAdjust, UserAdjust.class);
			}
		}
		return "";
	}
}
