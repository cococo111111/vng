/**
 *Class: MaintainController * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.wte.common.LogUtil;
import vng.zingme.payment.frontend.credits.config.Configuration;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Jul 18, 2011
 */
public class MaintainController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(MaintainController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String flag = request.getParameter("flag");
            String secretKey = request.getParameter("secretkey");
            if (flag == null || secretKey == null) {
                this.echo("ERROR ", response);
                logger_.error("maintain fail with flag=" + flag + " and secret key=" + secretKey);
                return;
            }
            if (!secretKey.equals(Configuration.MAINTAIN_SECRET_KEY)) {
                this.echo("ERROR ", response);
                logger_.error("wrong secret key=" + secretKey);
                return;
            }
            if (flag.equals("0")) {
                //non-maintain mode
                Configuration.IS_MAINTAIN_FLG = false;
                this.echo("SET NON - MAINTAIN MODE - OK ", response);
                logger_.info("SET NON - MAINTAIN MODE - OK BY CLIENT IP = " + getClientIP(request));
                return;
            }
            if (flag.equals("1")) {
                //maintain mode
                Configuration.IS_MAINTAIN_FLG = true;
                this.echo("SET  MAINTAIN MODE - OK ", response);
                logger_.info("SET MAINTAIN MODE - OK BY CLIENT IP = " + getClientIP(request));
                return;
            }
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }
    }
}
