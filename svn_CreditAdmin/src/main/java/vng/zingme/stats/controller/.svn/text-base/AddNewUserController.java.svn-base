package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.stats.dao.RoleDao;
import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.UserRoleDao;
import vng.zingme.stats.model.App;
import vng.zingme.stats.model.Role2;
import vng.zingme.stats.model.User2;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.Md5Encryption;

@Controller
@RequestMapping("/admin")
public class AddNewUserController {
	@Autowired
	private RoleDao roleDao; 
	@Autowired
	private UserDao userDao; 
	@Autowired
	private AppService	appService; 
	@Autowired
	private UserRoleDao userRoleDao; 
	
	private Logger log = Logger.getLogger(AddNewUserController.class);
	@RequestMapping(value = "/addnewuser", method = RequestMethod.GET)
	public ModelAndView getNewUser(@ModelAttribute("user") User2 user,
			ModelMap model) {
	
		// get all List App
		List<App> appList = new ArrayList<App>();
		List<T_AppInfo> t_AppList = appService.get_List_T_AppInfo_Sorted(); 

			for (T_AppInfo t_AppInfo : t_AppList) {
				App app = new App();
				app.setAppId(t_AppInfo.getAppID());
				app.setAppName(t_AppInfo.getAppName());
				appList.add(app);
			}

			// get all List Role
			List<Role2> roleList = new ArrayList<Role2>();
			List<Role2> rList = roleDao.getListRole();
			
			for (Role2 role2 : rList) {
				if (!"SUPER_ADMIN".equals(role2.getName())) {
					roleList.add(role2);
				}
			}
			// add model View
			ModelAndView mav = new ModelAndView("/jsp/addnewuser.jsp");
			model.addAttribute("appList", appList);
			model.addAttribute("roleList", roleList);
			
		return mav;
	}

	@RequestMapping(value = "/addnewuser", method = RequestMethod.POST)
	public String addUser( @ModelAttribute("user") User2 user) {
		
		System.out.println("app " + user.getAppList());
		
		// md5 PassWord
		String pass = Md5Encryption.MD5(user.getPassWord());

		// ADMIN - CS ROLE
		if ("ADMIN".equals(user.getRole()) || "ROLE_CS".equals(user.getRole())) {
			// save user DB
			User2 userDto = new User2(user.getUserName(), user.getAdminName(),
					pass, user.getAppList(), user.getRole());
			userDao.insert(userDto);
			userRoleDao.insert(user.getUserName(), user.getRole());
			// save user_role DB
			if ("1".equals(user.getSuperAdmin())) {
				userRoleDao.insert(user.getUserName(), "SUPER_ADMIN");
			}
		} else { // ROLE REPORT
			// add listapp into new user
			List<String> strAppList = new ArrayList<String>();
			if (user.getAppList().contains("credits_report")) {
				strAppList.add("credits_report");
			}
			if ("1".equals(user.getRole())) {
				strAppList.add(0,"1"); // add nhom1: xem tat ca
				user.setAppList(strAppList);
			} else if ("2".equals(user.getRole())) {
				strAppList.add(0,"2"); // add nhom2: xem tat ca - zing pay + admin
				user.setAppList(strAppList);
			}
			// add user into DB
			User2 userDto = new User2(user.getUserName(), user.getAdminName(),
					pass, user.getAppList(), "ROLE_REPORT");

			userDao.insert(userDto);
			userRoleDao.insert(user.getUserName(), "ROLE_REPORT");
		}
		return "redirect:" + "/admin/accountcontrol";
	}

}
