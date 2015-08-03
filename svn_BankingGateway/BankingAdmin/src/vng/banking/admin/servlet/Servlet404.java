package vng.banking.admin.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class Servlet404 extends BaseServlet {

	private static final Logger logger = Logger.getLogger(Servlet404.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {		
		String content = "File not found";
		resp.setStatus(404);
		this.outAndClose(content,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		this.doGet(req, resp);
	}

	

}
