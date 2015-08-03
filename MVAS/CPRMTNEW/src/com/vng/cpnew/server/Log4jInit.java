package com.vng.cpnew.server;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.xml.DOMConfigurator;

import com.vng.cpnew.util.Utils;

public class Log4jInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6375616325463485722L;

	@Override
	public void init() throws ServletException {

		String prefix = Utils.searchReplace(getServletContext()
				.getRealPath("/"), "\\", "/");
		String file = getInitParameter("ReceiverCP_config_file");
		String configFile = prefix + file;

		if (configFile != null && configFile.length() > 0
				&& (new File(configFile)).exists()
				&& (new File(configFile)).isFile()) {
			DOMConfigurator.configure(configFile);
		} else {
			throw new RuntimeException("ERROR Not found ReceiverADS log: ["
					+ file + "]");
		}

	}

}
