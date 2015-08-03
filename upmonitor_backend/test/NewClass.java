//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author locth2
// */
//public class NewClass {
//
//    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://targethost/homepage");
//        CloseableHttpResponse response1 = httpclient.execute(httpGet);
//// The underlying HTTP connection is still held by the response object
//// to allow the response content to be streamed directly from the network socket.
//// In order to ensure correct deallocation of system resources
//// the user MUST either fully consume the response content  or abort request
//// execution by calling CloseableHttpResponse#close().
//
//        try {
//            System.out.println(response1.getStatusLine());
//            HttpEntity entity1 = response1.getEntity();
//            // do something useful with the response body
//            // and ensure it is fully consumed
//            EntityUtils.consume(entity1);
//        } finally {
//            response1.close();
//        }
//
//        HttpPost httpPost = new HttpPost("http://targethost/login");
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("username", "vip"));
//        nvps.add(new BasicNameValuePair("password", "secret"));
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//        CloseableHttpResponse response2 = httpclient.execute(httpPost);
//
//        try {
//            System.out.println(response2.getStatusLine());
//            HttpEntity entity2 = response2.getEntity();
//            // do something useful with the response body
//            // and ensure it is fully consumed
//            EntityUtils.consume(entity2);
//        } finally {
//            response2.close();
//        }
//    }
//
//}
