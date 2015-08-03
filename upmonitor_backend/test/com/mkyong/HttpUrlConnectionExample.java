package com.mkyong;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

//import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpUrlConnectionExample {

    private List<String> cookies;
    private HttpURLConnection conn;

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        String url = "http://login.me.zing.vn";
        String gmail = "http://me.zing.vn/jfr/pr/";
        String postParams = "";
        String page = "";
        HttpUrlConnectionExample http = new HttpUrlConnectionExample();

        // make sure cookies is turn on
//        CookieHandler.setDefault(new CookieManager());
//        http.sendPost(url, gmail);
//        System.exit(0);

        // 1. Send a "GET" request, so that you can extract the form's data.
        page = http.GetPageContent(url);
        postParams = http.getFormParams(page, "hiepsigiacmo", "conbo2006");
//        System.out.println(postParams);
//        System.exit(0);

	// 2. Construct above post's content and then send a POST request for
        // authentication
        http.sendPost(url, postParams);
//        System.exit(0);
        // 3. success then go to gmail.
        String result = http.GetPageContent(gmail);
        System.out.println(result);
    }

    private void sendPost(String url, String postParams) throws Exception {
//        url = "https://sso3.zing.vn/alogin?pid=25&u1=http://login.me.zing.vn/"
//                + "login/success&fp=http://login.me.zing.vn/login/fail&"
//                + "apikey=6c78e66f436d279ea62255a6dd96f1a1&u=hiepsigiacmo&"
//                + "p=conbo2006&bntlogin=";
        url ="https://sso3.zing.vn/alogin";
        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Host", "sso3.zing.vn");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            System.out.println( cookie.split(";", 1)[0]);
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "http://login.me.zing.vn");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
        
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        System.out.println("haha:");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            if (entry.getKey() != null && entry.getKey().equals("Set-Cookie")) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }
        }
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

//        BufferedReader in
//                = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        System.out.println(response.toString());

    }

    private String GetPageContent(String url) throws Exception {

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in
                = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();

    }

    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

//	System.out.println("Extracting form's data...");
        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("frmLogin");
        Elements inputElements = loginform.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("u")) {
                value = username;
            } else if (key.equals("p")) {
                value = password;
            }
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            System.out.println("Key:" + key + "====== Value: " + value);
        }

        // build parameters list
        StringBuilder result = new StringBuilder();

        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

}
