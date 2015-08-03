/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.gatewayweb.servlet;

import banking.gatewayweb.server.InitUtil;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;

/**
 *
 * @author root
 */
public class Payment extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
//        String refID, String time, String username,
//            String agencyCode, String region, int amount, String clientIP, String description,
//            String bankCode, String commision, String urlCallBack, String transferType, String sig) throws TException {

//        Request from Esale: M140324-0003	032414102722	agencyhcm	9014780450	ZIONBAC	50000	127.0.0.1
        String refID = request.getParameter("refID");
        String time = request.getParameter("time");
        String username = request.getParameter("username");
        String agencyCode = request.getParameter("agencyCode");
        String region = request.getParameter("region");
        String amount = request.getParameter("amount");
        String clientIP = request.getParameter("clientIP");
        String description = request.getParameter("description");
        String bankCode = request.getParameter("bankCode");
        String commision = request.getParameter("commision");
        String urlCallBack = request.getParameter("urlCallBack");
        String transferType = request.getParameter("transferType");
        String sig = request.getParameter("sig");
        String forename = request.getParameter("forename");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        
        // TODO check transaction existed
        // check sum
        // Store DB
        // output to post
        // set terminalCode

        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("<body>");

        out.println("<form name=\"myForm\" id=\"myForm\" action=\"" + InitUtil.VIETTINBANKVISA_URL  + "\" method=\"POST\">");
        HashMap params = new HashMap();
        if (region.equals(CommonDef.ZION_REGION.ZIONNAM)) {
            out.println("<input type=\"hidden\" name=\"access_key\" value=\"" + InitUtil.ACCESS_KEY_SG  + "\">");
            params.put("access_key", InitUtil.ACCESS_KEY_SG);
            out.println("<input type=\"hidden\" name=\"profile_id\" value=\"" + InitUtil.PROFILE_ID_SG  + "\">");
            params.put("profile_id", InitUtil.PROFILE_ID_SG);
        } else if (region.equals(CommonDef.ZION_REGION.ZIONBAC)) {
            out.println("<input type=\"hidden\" name=\"access_key\" value=\"" + InitUtil.ACCESS_KEY_HN  + "\">");
            params.put("access_key", InitUtil.ACCESS_KEY_HN);
            out.println("<input type=\"hidden\" name=\"profile_id\" value=\"" + InitUtil.PROFILE_ID_HN  + "\">");
            params.put("profile_id", InitUtil.PROFILE_ID_HN);
        } else {
            // TODO return wrong 
        }
        
        out.println("<input type=\"hidden\" name=\"transaction_uuid\" value=\"" + refID  + "\">");
        out.println("<input type=\"hidden\" name=\"signed_date_time\" value=\"" + InitUtil.formatTime(time, InitUtil.MMddyyHHmmss, InitUtil.yyyy_MM_ddHHmmss)  + "\">");
        out.println("<input type=\"hidden\" name=\"locale\" value=\"en\">");
        out.println("<input type=\"hidden\" name=\"bill_to_forename\" value=\"" + forename  + "\">");
        out.println("<input type=\"hidden\" name=\"bill_to_surname\" value=\"" + surname  + "\">");
        out.println("<input type=\"hidden\" name=\"bill_to_address_line1\" value=\"" + address  + "\">");
        out.println("<input type=\"hidden\" name=\"bill_to_address_city\" value=\"" + city  + "\">");
        out.println("<input type=\"hidden\" name=\"bill_to_address_country\" value=\"VN\">");
        out.println("<input type=\"hidden\" name=\"bill_to_address_postal_code\" value=\"70000\">");
        out.println("<input type=\"hidden\" name=\"bill_to_email\" value=\"" + email  + "\">");
        out.println("<input type=\"hidden\" name=\"transaction_type\" value=\"" + InitUtil.TRANSACTION_TYPE  + "\">");
        out.println("<input type=\"hidden\" name=\"reference_number\" value=\"" + refID  + "\">");
        out.println("<input type=\"hidden\" name=\"amount\" value=\"" + amount  + "\">");
        out.println("<input type=\"hidden\" name=\"currency\" value=\"VND\">");
        out.println("<input type=\"hidden\" name=\"unsigned_field_names\" value=\"\">");
        
        String signedFieldNames= "access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency,bill_to_forename,bill_to_surname,bill_to_address_line1,bill_to_address_city,bill_to_address_country,bill_to_address_postal_code,bill_to_email";
        out.println("<input type=\"hidden\" name=\"signed_field_names\" value=\"" + signedFieldNames  + "\">");
        
        params.put("transaction_uuid", refID);
        params.put("signed_date_time", InitUtil.formatTime(time, InitUtil.MMddyyHHmmss, InitUtil.yyyy_MM_ddHHmmss));
        params.put("locale", "en");
        params.put("bill_to_forename", forename);
        params.put("bill_to_surname", surname);
        params.put("bill_to_address_line1", address);
        params.put("bill_to_address_city", city);
        params.put("bill_to_address_country", "VN");
        params.put("bill_to_address_postal_code", "70000");
        params.put("bill_to_email", email);
        params.put("transaction_type", InitUtil.TRANSACTION_TYPE);
        params.put("reference_number", refID);
        params.put("amount", amount);
        params.put("currency", "VND");
        params.put("unsigned_field_names", "");
        params.put("signed_field_names", signedFieldNames);
        try {
         if (region.equals(CommonDef.ZION_REGION.ZIONNAM)) {
            out.println("<input type=\"hidden\" name=\"signature\" value=\"" + InitUtil.sign(params, InitUtil.SECRET_KEY_SG) + "\">");
        } else if (region.equals(CommonDef.ZION_REGION.ZIONBAC)) {
            out.println("<input type=\"hidden\" name=\"signature\" value=\"" + InitUtil.sign(params, InitUtil.SECRET_KEY_HN) + "\">");
        }
        } catch(Exception ex) {
            // TODO log....
        }
        
        out.println("</form>");

        out.println("<script>");
        out.println("document.onreadystatechange=function(){");
        out.println("document.getElementById(\"myForm\").submit();}");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");

    }
}
