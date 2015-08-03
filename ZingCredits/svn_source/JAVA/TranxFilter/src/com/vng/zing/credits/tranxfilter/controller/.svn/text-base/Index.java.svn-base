package com.vng.zing.credits.tranxfilter.controller;

import com.vng.zing.credits.tranxfilter.model.Filter;
import hapax.TemplateDataDictionary;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class Index extends CoreAction {
    
    static final Logger _logger = Logger.getLogger(CoreAction.class);
    static final DateFormat df =  new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (validUser(req, resp)) {
                TemplateDataDictionary dic = getDictionary();
                String time = req.getParameter("time");
                try {
                    Filter.getTranx(dic, df.parse(time).getTime());                    
                } catch (Exception ex) {
                    Date d = new Date();
                    d.setTime(System.currentTimeMillis());
                    time = df.format(d);                    
                }                       
                dic.setVariable("time", time);
                print(applyTemplate(dic, "index"), resp);
            } else {
                print("Access Deny!", resp);
            }
        } catch (Exception ex) {
            _logger.warn(ex.getMessage(), ex);
        }
    }
}
