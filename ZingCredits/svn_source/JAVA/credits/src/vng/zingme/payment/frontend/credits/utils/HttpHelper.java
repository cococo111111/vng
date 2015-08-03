/**
 *Class: HttpHelper * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHelper {

    public static void redirect(HttpServletResponse resp, String urlRedirect) {
        try {
            resp.sendRedirect(urlRedirect);
        } catch (Exception ex) {
        }

    }

    public static String getCookie(HttpServletRequest req, String name) {

        Cookie[] cookies = req.getCookies();
        String value = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    value = cookies[i].getValue();
                    break;
                }
            }
        }
        return value;
    }
	
	public static boolean removeCookie(HttpServletRequest req, HttpServletResponse res, String name) {

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
					cookies[i].setMaxAge(0);
					res.addCookie(cookies[i]);
                    return true;
                }
            }
        }
        return false;
    }
}
