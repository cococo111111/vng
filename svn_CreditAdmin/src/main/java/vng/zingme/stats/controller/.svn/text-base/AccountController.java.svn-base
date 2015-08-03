package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.UserRoleDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;
import vng.zingme.stats.model.Role2;
import vng.zingme.stats.model.User2;

@Controller
@RequestMapping("/admin")
public class AccountController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao; 

	@RequestMapping(value = "/accountcontrol", method = RequestMethod.GET)
	public ModelAndView addUser() {

		// get userList
		List<User2> userList = userDao.getAllUser();

		//get List role of User
		for (User2 user2 : userList) {
			List<Role2> roleList =  userRoleDao.getRoleByUserName(user2.getUserName());
			user2.setRole(roleList.get(0).getName());
		}

		ModelAndView mav = new ModelAndView("/jsp/accountcontrol.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("userList", userList);

		return mav;
	}

	/*@RequestMapping(value = "/accountupdate", method = RequestMethod.GET)
	public String getUpdateAccount(@RequestParam(value = "userName") String userName, ModelMap model) {
		
		// get user from DB
		User2 user = userModel.getUserByName(userName);
		model.addAttribute("user", user);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("adminName", user.getAdminName());

		return "/jsp/accountupdate.jsp";
	}

	@RequestMapping(value = "/accountupdate/userId={userName}", method = RequestMethod.POST)
	public String postUpdateAccount(@PathVariable("userName") String userName,
			@ModelAttribute("user") User2 user) {
		// update DB
		UserModel uDao = new UserModelImpl();
		uDao.updateAdminName(userName, user.getAdminName());
		return "redirect:" + "/admin/accountcontrol";
	}
*/
	
	
	@RequestMapping(value = "/accountcontrol", method = RequestMethod.POST)
	public ModelAndView updateUser(ModelMap model) {
		User2 user = new User2();
		model.addAttribute("user", user);

		UserDao dao = new UserDaoImpl();
		dao.updateAdminName(user.getUserName(), user.getAdminName());

		ModelAndView mav = new ModelAndView("/jsp/accountcontrol.jsp");
		ModelMap model2 = mav.getModelMap();
		List<User2> userList = new ArrayList<User2>();
		userList = dao.getAllUser();
		model2.addAttribute("userList", userList);
		return mav;
	}
}
