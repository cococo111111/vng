/**
 * 
 */
package vng.zingme.stats.encoder;

import org.springframework.security.authentication.encoding.PasswordEncoder;

import vng.zingme.stats.service.Md5Encryption;

/**
 * @author sonhoang
 *
 */
public class MyPasswordEncoder implements PasswordEncoder {
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#encodePassword(java.lang.String, java.lang.Object)
	 */
	@Override
	public String encodePassword(String rawPass, Object salt) {
	//	System.out.println("rawPass = " + rawPass);
	//	System.out.println("salt = " + salt);
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#isPasswordValid(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
//		WebApplicationContextUtils.getWebApplicationContext(servletContext).getServletContext().getS
			    
		//System.out.println(((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId());
		//System.out.println("encPass = " + encPass);
		//System.out.println("rawPass = " + Md5Encryption.MD5(rawPass));
		//System.out.println("salt = " + salt);
		return (encPass.equalsIgnoreCase(Md5Encryption.MD5(rawPass))) ? true: false;
	}

}
