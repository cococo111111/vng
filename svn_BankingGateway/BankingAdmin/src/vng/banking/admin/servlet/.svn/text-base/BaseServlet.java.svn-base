/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.banking.admin.servlet;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tunm
 */
public class BaseServlet extends HttpServlet {

        private static final Logger _baseLogger = Logger.getLogger(BaseServlet.class);

        protected void outAndClose(String content, HttpServletResponse resp) {
                PrintWriter out = null;
                try {
                        out = resp.getWriter();
                        out.println(content);
                } catch (Exception e) {
                } finally {
                        if (out != null) {
                                out.close();
                        }
                }
        }

        protected void prepareHeader(HttpServletResponse resp) {
                resp.setCharacterEncoding("utf-8");

                resp.setContentType("text/html;charset=UTF-8");
                resp.addHeader("Server", "ZGC-1.0");
        }
}
