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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.bo.thrift.ReportHandler;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.model.TranxDetail;
import vng.zingme.stats.model.TranxUser;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;
import vng.zingme.stats.service.UserService;

@Controller
@RequestMapping("/")
public class UserTranxController {
	ReportHandler reportHandler = null;

	@Autowired
	private CommonService commonService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "app/tranxbyuser", method = RequestMethod.GET)
	public ModelAndView userTranxGet() {
		return new ModelAndView("/jsp/tranxbyuser.jsp", "user", new User2());
	}

	@RequestMapping(value = "app/tranxbyuser", method = RequestMethod.POST)
	public ModelAndView userTranxPost(
			@Valid @ModelAttribute("user") User2 user, BindingResult result) {

		String fromDateView = commonService.getFirstDateOfMonth(user
				.getStartTime());
		String toDateView = commonService.getCurrentTime(user.getEndTime());
		String sFromdate = fromDateView + ":00";
		String sTodate = toDateView + ":59";

		String tranxid = user.getTxId().trim();
		List<T_ReportTransaction> tranxs = new ArrayList<>();
		String userName = "";

		ModelAndView mav = new ModelAndView("/jsp/tranxbyuser.jsp");
		if (tranxid != null && !"".equals(tranxid)) { // get UserTranx by
														// tranxid
			tranxs = reportService.getUserTranxByTranxId(tranxid, sFromdate,
					sTodate);

			if (!tranxs.isEmpty()) {
				userName = tranxs.get(0).getUserName();
			} else {
				return mav;
			}

		} else { // get usertranx by userId
			int userId = userService.getUserId(user);
			if (userId == -1) {
				result.rejectValue("userName", "userError.error",
						"this userName is not exist");
				return new ModelAndView("/jsp/tranxbyuser.jsp", "user", user);
			} else if (userId == -2) {
				result.rejectValue("userName", "userError.error",
						"userId must be a number");
				return new ModelAndView("/jsp/tranxbyuser.jsp", "user", user);
			}
			if (userId > 0) {
				tranxs = reportService.getUserTranxByUserId(userId, sFromdate,
						sTodate, Integer.parseInt(user.getTxStatus()));
			}
			userName = ("1".equals(user.getUserId())) ? userService
					.getUserNameByUserId(userId) : user.getUserName();
		}
		// showView
		List<TranxUser> tranxUserList = new ArrayList<>();
		for (T_ReportTransaction t : tranxs) {
			TranxUser txu = TranxUser.convertT_reportTranx2TranxUser(t);
			tranxUserList.add(txu);
		}

		ModelMap model = mav.getModelMap();
		model.addAttribute("tranxUserList", tranxUserList);
		model.addAttribute("userName", userName);
		model.addAttribute("fromDate", fromDateView);
		model.addAttribute("toDate", toDateView);
		return mav;
	}

	@RequestMapping(value = "ajax/tranxbyuserdetail", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView detailUserTranx(@RequestParam(value = "txId") String txId,
			@RequestParam(value = "txTime") String txTime) {

		long time = commonService.convertDate2Long("HH:mm, dd/MM/yyyy", txTime);

		List<TranxDetail> tranxDetailList = new ArrayList<>();
		List<T_ReportTransaction> tranxs = reportService.getTranxStatus(txId,
				(int) time);

		for (T_ReportTransaction tranx : tranxs) {
			TranxDetail txu = TranxDetail
					.convertT_reportTranx2TranxUserDetail(tranx);
			tranxDetailList.add(txu);
		}
		return new ModelAndView("/jsp/tranxdetail.jsp", "tranxDetailList",
				tranxDetailList);
	}

}
