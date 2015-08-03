package vng.zingme.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;

@Controller
@RequestMapping("/admin")
public class DeleteAccountController {

	@RequestMapping(value = "/deleteaccount/userId={userName}", method = RequestMethod.GET)
	public @ResponseBody
	void deleteAccount(@PathVariable("userName") String userName) {

		UserDao uDao = new UserDaoImpl();
		uDao.deleteUserByName(userName);
	}
}
