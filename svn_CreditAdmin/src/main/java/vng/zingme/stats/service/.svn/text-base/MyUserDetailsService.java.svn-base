package vng.zingme.stats.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.dao.impl.UserDaoImpl;
import vng.zingme.stats.model.Role2;
import vng.zingme.stats.model.User2;

/**
 * 
 * @author lentd
 * 
 */
public class MyUserDetailsService implements UserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		UserDao dao = new UserDaoImpl();
		User2 user = dao.getUserByName(username);
		List<Role2> roleList = user.getRole(username);
		for (Role2 role2 : roleList) {
			authorities.add(new GrantedAuthorityImpl(role2.getName()));
			if("ROLE_REPORT".equals(role2.getName())){
				if(dao.checkCreditReport(username)){
					authorities.add(new GrantedAuthorityImpl("CREDIT_REPORT"));
				}
			}
		}

		authorities.add(new GrantedAuthorityImpl("LOGGINED"));

		return new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getPassWord(), true, true, true, true,
				authorities);

	}
}
