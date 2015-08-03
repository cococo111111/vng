/**
 *Class: User4AppController * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import hapax.TemplateException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.Monitor;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Jun 21, 2011
 */
public class User4AppController extends CreditsCore {

    private final Monitor readStats = new Monitor();
    private static Logger logger_ = Logger.getLogger(User4AppController.class);
    private final String PARAM_STATS = "stats";

    public User4AppController() {
        _userAppMemClient = getUserAppMemClient();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            logger_.error(ex.getMessage());
            this.echo(Configuration.SYSTEM_MAINTAIN_TEXT, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws TException, TemplateException {
        long startTime = System.nanoTime();
        String stats = request.getParameter(PARAM_STATS);
        if (stats != null && stats.equals(PARAM_STATS)) {
            this.echo(this.readStats.dumpHtmlStats(), response);
            return;
        }
        if (!this.validUser(request, response)) {
            return;
        }
        int userID = Integer.parseInt(request.getAttribute("zme.viewerId").toString());
        if (request.getParameter("lite") != null && request.getParameter("appID") != null && request.getParameter("lite").equals("1")) {
            setUserAppMemCache(userID, request.getParameter("appID"), 1);
            this.echoAndStats(startTime, "1", response);
            return;
        }
        if (request.getParameter("lite") != null && request.getParameter("appID") != null && request.getParameter("lite").equals("0")) {
            delUserAppMemCache(userID, request.getParameter("appID"));
            this.echoAndStats(startTime, "0", response);
            return;
        }
    }

    private void echoAndStats(long startTime, String html, HttpServletResponse response) {
        this.echo(html, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);
    }
}
