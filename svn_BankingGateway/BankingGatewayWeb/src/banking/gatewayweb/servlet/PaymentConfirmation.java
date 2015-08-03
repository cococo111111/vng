/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.gatewayweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sonpvh <sonpvh@vng.com.vn>
 */
public class PaymentConfirmation extends HttpServlet {

    public void doPost (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HashMap params = new HashMap();
        Enumeration paramsEnum = request.getParameterNames();

        while (paramsEnum.hasMoreElements()) {
            String paramName = (String) paramsEnum.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }

        PrintWriter out = response.getWriter();
        // handle informantion from post action
        out.println("<html>");
        out.println("<body>");
        Iterator paramsIterator = params.entrySet().iterator();
        while (paramsIterator.hasNext()) {
            Map.Entry param = (Map.Entry) paramsIterator.next();
            out.println(param.getKey() + " : " + param.getValue());
            out.println("<br>");
        }

        out.println("TODO: SIGNATURE");
        out.println("</body>");
        out.println("</html>");
    }
}
