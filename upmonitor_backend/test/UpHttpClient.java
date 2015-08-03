
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author locth2
 */
public class UpHttpClient {

    public static void main(String[] args) throws MalformedURLException, IOException {
//        URL obj = new URL("http://ticket.zapps.vn");
//        URLConnection conn = obj.openConnection();
//
//        //get all headers
//        Map<String, List<String>> map = conn.getHeaderFields();
//        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//            System.out.println("Key : " + entry.getKey()
//                    + " ,Value : " + entry.getValue());
//        }
//
//        String server = conn.getHeaderField("Server");
        
        HttpClient client = new DefaultHttpClient();
	HttpGet request = new HttpGet("http://mkyong.com");
	HttpResponse response = client.execute(request);
 
	//get all headers		
	Header[] headers = response.getAllHeaders();
	for (Header header : headers) {
		System.out.println("Key : " + header.getName() 
		      + " ,Value : " + header.getValue());
	}
 
	//get header by 'key'
	String server = response.getFirstHeader("Server").getValue();
    }

}
