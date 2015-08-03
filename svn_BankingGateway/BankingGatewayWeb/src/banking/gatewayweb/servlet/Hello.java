/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.gatewayweb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Sonpvh <sonpvh@vng.com.vn>
 */
public class Hello extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

//        PrintWriter out = response.getWriter();
//        // handle informantion from post action
//        out.println("<html>");
//        out.println("<body>");
//
//        out.print("TODO: HELLO");
//        out.println("</body>");
//        out.println("</html>");
//        response.sendRedirect(null);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://testsecureacceptance.cybersource.com/pay");
        String amount = request.getParameter("amount");
        String reference_number = request.getParameter("reference_number");
        String profile_id = request.getParameter("profile_id");
        String signed_date_time = request.getParameter("signed_date_time");
        String transaction_type = request.getParameter("transaction_type");
        String locale = request.getParameter("locale");
        String transaction_uuid = request.getParameter("transaction_uuid");
        String access_key = request.getParameter("access_key");
        String unsigned_field_names = request.getParameter("unsigned_field_names");
        String currency = request.getParameter("currency");
        String signature = request.getParameter("signature");
        
        
        String signed_field_names = "access_key,profile_id,transaction_uuid,signed_field_names,unsigned_field_names,signed_date_time,locale,transaction_type,reference_number,amount,currency";
        
        //,bill_to_forename,bill_to_surname,bill_to_address_line1,bill_to_address_city,bill_to_address_country,bill_to_address_postal_code,bill_to_email
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("reference_number", reference_number));
            nameValuePairs.add(new BasicNameValuePair("amount", amount));
            nameValuePairs.add(new BasicNameValuePair("signed_field_names", signed_field_names));
            nameValuePairs.add(new BasicNameValuePair("profile_id", profile_id));
            nameValuePairs.add(new BasicNameValuePair("signed_date_time", signed_date_time));
            nameValuePairs.add(new BasicNameValuePair("transaction_type", transaction_type));
            nameValuePairs.add(new BasicNameValuePair("locale", locale));
            nameValuePairs.add(new BasicNameValuePair("transaction_uuid", transaction_uuid));
            nameValuePairs.add(new BasicNameValuePair("access_key", access_key));
            nameValuePairs.add(new BasicNameValuePair("unsigned_field_names", unsigned_field_names));
            nameValuePairs.add(new BasicNameValuePair("currency", currency));
            nameValuePairs.add(new BasicNameValuePair("signature", signature));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_forename", "aaa"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_surname", "aaaa"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_address_line1", "aaaaa"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_address_city", "aaaaaa"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_address_country", "VN"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_address_postal_code", "084"));
            
            nameValuePairs.add(new BasicNameValuePair("bill_to_email", "lentd@vng.com.vn"));
            
            
            
            
            
            
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        

            HttpHost proxy = new HttpHost("10.30.22.23", 80);
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            HttpResponse res = client.execute(post);          
         
        

Header[] cookie  = (Header[]) res.getHeaders("Set-Cookie");

            EntityUtils.consume(res.getEntity());
            
            
            // TO DO : check res
            
            HttpGet httpGet = new HttpGet("https://testsecureacceptance.cybersource.com/billing");
            httpGet.setHeader("Host", "https://testsecureacceptance.cybersource.com");
            httpGet.setHeaders(cookie);
         
            HttpResponse res1 = client.execute(httpGet);
            
            response.setHeader("Location", "https://testsecureacceptance.cybersource.com");
            response.setHeader("Host", "https://testsecureacceptance.cybersource.com");
            List<org.apache.http.cookie.Cookie> list = client.getCookieStore().getCookies();
            
            for (int i = 0; i < list.size(); i++) {
                Cookie cookieServlet = new Cookie(list.get(i).getName(), list.get(i).getValue());
                cookieServlet.setDomain("testsecureacceptance.cybersource.com");
            response.addCookie(cookieServlet);
            }
            
            
//               StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res1.getEntity().getContent(), "UTF-8"));
        String line = bufferedReader.readLine();
        while(line != null){
//            inputStringBuilder.append(line);
//            inputStringBuilder.append('\n');
            if(line.indexOf("href=\"/") >= 0) {
                line = line.replaceFirst("href=\"/", "href=\"https://testsecureacceptance.cybersource.com/");
            } else {
                if(line.indexOf("href=\"") >= 0) {
                    line = line.replaceFirst("href=\"", "href=\"https://testsecureacceptance.cybersource.com/");
                } 
            }
            
            if(line.indexOf("src=\"/") >= 0) {
                line = line.replaceFirst("src=\"/", "src=\"https://testsecureacceptance.cybersource.com/");
            } else {
                if(line.indexOf("src=\"") >= 0) {
                    line = line.replaceFirst("src=\"", "src=\"https://testsecureacceptance.cybersource.com/");
                }
            }
            if(line.indexOf("action=\"/") >= 0) {
                line = line.replaceFirst("action=\"/", "action=\"https://testsecureacceptance.cybersource.com/");
            } else {
                if(line.indexOf("action=\"") >= 0) {
                    line = line.replaceFirst("action=\"", "action=\"https://testsecureacceptance.cybersource.com/");
                }
            }
            
            response.getOutputStream().write(line.getBytes(), 0, line.length());
            
            line = bufferedReader.readLine();
        }
        
        
//            byte[] buffer = new byte[64000];
//            int bytesRead = 0;
//
//            while (true) {
//                bytesRead = res1.getEntity().getContent().read(buffer);
//                if (bytesRead == -1) {
//                    break;
//                }
//
//                response.getOutputStream().write(buffer, 0, bytesRead);
//            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
