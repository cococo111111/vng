/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vng.zingme.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
	private static final String ERROR = "error";
	private static final String INFO = "info";
	private static final String SERVICE_NAME = "ServiceName";
//	private static String service = "pg";
	private static Logger logInfo = null;
	private static Logger logError = null;
	protected LogUtil() {
	}
	public static void info(String msg) {
		logInfo.info(msg);
	}

	public static void error(String msg) {
		logError.error(msg);
	}

	static {
		String file = System.getProperty("config");
		file += File.separator + "log4j.properties";
		PropertyConfigurator.configure(file);
		logInfo = Logger.getLogger(("appActions"));
		logError = Logger.getLogger(("appActions"));
		logInfo.info("Loaded informatin of log4j from file " + file);
	}


	public static Logger getLogger(String name) {
		return Logger.getLogger(name);
	}

	public static String stackTrace(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public static String getTimestamp() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return df.format(date);
	}

	public static String throwableToString(Throwable e) {
		StringBuilder sbuf = new StringBuilder("");
		String trace = stackTrace(e);
		sbuf.append((new StringBuilder()).append(
				"Exception was generated at : ").append(getTimestamp()).append(
				" on thread ").append(Thread.currentThread().getName())
				.toString());
		sbuf.append(System.getProperty("line.separator"));
		String message = e.getMessage();
		if (message != null)
			sbuf.append(message);
		sbuf.append(System.getProperty("line.separator")).append(trace);
		return sbuf.toString();
	}

	public static String gmsgetLogMessage(String message) {
		StringBuilder sbuf = new StringBuilder((new StringBuilder()).append(
				"Log started at : ").append(getTimestamp()).toString());
		sbuf.append(File.separator).append(message);
		return sbuf.toString();
	}

	public static void setLogLevel(String logger, String level) {
		Logger loggerObj = LogManager.getLogger(logger);
		if (null == loggerObj)
			return;
		level = level.toUpperCase();
		if (level.equals("DEBUG"))
			loggerObj.setLevel(Level.DEBUG);
		else if (level.equals("ERROR"))
			loggerObj.setLevel(Level.ERROR);
		else if (level.equals("FATAL"))
			loggerObj.setLevel(Level.FATAL);
		else if (level.equals("INFO"))
			loggerObj.setLevel(Level.INFO);
		else if (level.equals("OFF"))
			loggerObj.setLevel(Level.OFF);
		else if (level.equals("WARN"))
			loggerObj.setLevel(Level.WARN);
		else
			loggerObj.setLevel(Level.ALL);
	}
	public static void main(String[] args){
		LogUtil.error("Testing1");
		LogUtil.error("Testing2");
		LogUtil.error("Testing3");
	}

}
