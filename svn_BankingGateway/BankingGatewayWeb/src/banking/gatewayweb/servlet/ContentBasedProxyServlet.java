/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banking.gatewayweb.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.http.HttpURI;

//import org.eclipse.jetty.servlets.ProxyServlet;


/**
 * When a request cannot be satisfied on the local machine, it asynchronously
 * proxied to the destination box. Define the rule 
 */
public class ContentBasedProxyServlet  {
private int remotePort = 8080;

public void setPort(int port) {
    this.remotePort = port;
}

public void init(ServletConfig config) throws ServletException {
//    super.init(config);
}

public void service(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {
//    super.service(request, response);
}


/**
*   Applicable to Jetty 9+ only. 
*/
//@Override
//protected HttpURI proxyHttpURI(String scheme, String serverName, int serverPort, String uri) throws MalformedURLException {
//    String proxyTo = getProxyTo(request);
//    if (proxyTo == null)
//        return null;
//    String path = request.getRequestURI();
//    String query = request.getQueryString();
//    if (query != null)
//        path += "?" + query;
//    return URI.create(proxyTo + "/" + path).normalize();
//}

//private String getProxyTo(HttpServletRequest request) {
// /*
// *  Implement this method: All the magic happens here. Use this method to figure out your destination machine address. You can maintain
// *  a static list of addresses, and depending on the URI or request content you can route your request transparently.
// */
//}
}