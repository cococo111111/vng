package vng.zingme.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;

@Controller
@RequestMapping("/admin")
public class ResetUserPassController {

	/**
	 * 
	 * @note: get data send by ajax by @RequestParam
	 * @param userName
	 */
	@RequestMapping(value = "/resetpass", method = RequestMethod.POST)
	public @ResponseBody
	void resetPass(@RequestParam(value = "userName") String userName) {
		UserDao dao = new UserDaoImpl();
		dao.resetUserpass(userName);
	}

}
