/**
 *Class: RequestFormController *
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
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.Monitor;

/**
 * get request form
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class BillStatusController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(BillStatusController.class);
    private final Monitor readStats = new Monitor();

    public BillStatusController() {
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
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws TException, TemplateException {
        long startTime = System.nanoTime();
        if (!this.validUser(request, response)) {
            return;
        }
        //validate request param
        if (!this.validateParams(request)) {
            this.redirectError(request, response);
            return;
        }
        int userID = Integer.parseInt(request.getAttribute("zme.viewerId").toString());
        LatestTranxCacheHandler cacheHandler = LatestTranxCacheHandler.getMainInstance(Configuration.LASTEDCACHE_HOST, Configuration.LASTEDCACHE_PORT);
        int status = cacheHandler.getTransactionStatus(userID, Long.parseLong(request.getParameter("refID")));
        String output = "";
        switch (status) {
            case 101:
            case 102:
                output = "1";
                break;
            case -103:
            case -104:
                output = "0";
                break;
        }
        echoAndStats(startTime, output, response);
    }

    private void echoAndStats(long startTime, String html, HttpServletResponse response) {
        this.echo(html, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);
    }

    boolean validateParams(HttpServletRequest request) {
        if (request.getParameter("refID") == null || request.getParameter("refID").equals("")) {
            return false;
        }
        return true;
    }
}
