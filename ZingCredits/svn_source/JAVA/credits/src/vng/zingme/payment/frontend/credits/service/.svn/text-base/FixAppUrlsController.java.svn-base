/**
 * Class: IndexController * Copyright (c) 2010-2011 VNG corp. All Rights
 * Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.zingme.payment.frontend.credits.config.Configuration;

/**
 * get user's Ví Zing Me version: 1.0
 *
 * @author: nhutnh@vng.com.vn created: May 19, 2011
 */
public class FixAppUrlsController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(FixAppUrlsController.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Configuration.loadAppUrls();
            echo("Config info: " + Configuration.APP_URLS.toString(), response);
        } catch (Exception ex) {
            logger_.error(ex.getMessage(), ex);
        }        
    }
}
