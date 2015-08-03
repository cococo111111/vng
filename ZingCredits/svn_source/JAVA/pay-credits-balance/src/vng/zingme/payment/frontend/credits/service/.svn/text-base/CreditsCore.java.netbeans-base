/**
 *Class: CoreServlet  
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.zingme.payment.frontend.credits.config.Configuration;

/**
 * Core Servlet for Credits application
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public abstract class CreditsCore extends HttpServlet {

    private static Logger logger_ = Logger.getLogger(CreditsCore.class);

    /**
     * check maintain flag.If true we echo to client and return true.<br>
     * Therefore, creditsControler will stop process
     * @param request
     * @param response
     * @return is maintain or not
     */
    protected boolean checkMainTain(HttpServletRequest request, HttpServletResponse response) {
        if (Configuration.IS_MAINTAIN_FLG) {
            this.echo("Hệ Thống ZingCredits đang được bảo trì. Mời bạn quay lại sau.", response);
            return true;
        }
        return false;
    }

    /**
     * check login user whether in white-list(only white list flag is enabled)
     * @param userID
     * @param request
     * @param response
     * @return true :(if white-list is enable and user in white-list) <br>
     * or(if white-list is disabled)
     */
    protected boolean checkWhiteList(int userID, HttpServletRequest request, HttpServletResponse response) {
        if (Configuration.IS_CHECK_WHITELIST_FLG) {
            this.echo("<p style='text-align:center'>Bạn không được phép truy cập hệ thống Zing Credits</p>", response);
            return false;
        }
        return true;
    }

    protected void setView(String key, Object value, HttpServletRequest request) {
        request.setAttribute(key, value);
    }

    protected void echo(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println(text);
            out.close();
        } catch (IOException ex) {
            logger_.error(ex.getMessage());
        } finally {
            out.close();
        }
    }

    protected void setViewScript(String viewName, HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletContext().getRequestDispatcher("/" + viewName + ".jsp").forward(request, response);
        } catch (ServletException ex) {
            logger_.error(ex.getMessage());
        } catch (IOException ex) {
            logger_.error(ex.getMessage());
        }
    }
}
