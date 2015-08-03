package com.services.soap.mo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import uk.org.primrose.DebugLogger;

public class LogMOInfo {
	private static PrintWriter logWriter = null;
	private static boolean logVerbose = false;
	private static boolean logInfo = false;
	private static boolean logWarn = false;
	private static boolean logError = false;
	private static boolean logCrisis = false;

	private static int logDayOfMonth = -1;
	private static String origLogName = null;
	private static String emailEvents = null;
	private static String mxServer = null;
	private static String toAddress = null;
	private static String poolName = null;
	public static void setLogWriter(String log) throws IOException {
		if (origLogName == null) {
			origLogName = log;
		}
		Calendar cal = Calendar.getInstance();
		int localLogDayOfMonth = cal.get(5);
		boolean makeNewLog = false;
		if ((logDayOfMonth == -1) && (logWriter == null))
			makeNewLog = true;
		if ((origLogName.indexOf("${") > -1)
				&& (localLogDayOfMonth != logDayOfMonth)) {
			String dateFormat = origLogName.substring(
					origLogName.indexOf("${") + 2, origLogName.indexOf("}"));
			String logPrefix = origLogName.substring(0, origLogName
					.indexOf("${"));
			String logSuffix = origLogName.substring(
					origLogName.indexOf("}") + 1, origLogName.length());
			SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat);
			Date tmp = new Date();
			String formattedDate = sdf2.format(tmp);
			log = logPrefix + formattedDate + logSuffix;
			logDayOfMonth = localLogDayOfMonth;
			makeNewLog = true;
		}
		if (makeNewLog) {
			if (DebugLogger.getEnabled())
				DebugLogger.log("[Logger@" + poolName + "] Making new log ("
						+ log + ")");
			if (logWriter != null)
				logWriter.close();
			logWriter = new PrintWriter(new FileOutputStream(log, true), true);
		}
	}

	public static void setEmailDetails(String emailEvents, String toAddress,
			String mxServer, String poolName) {
	}

	public static void setLogLevel(String level) {
		if ((level != null) && (level.length() > 0)) {
			String[] levels = level.split(",");
			for (int i = 0; i < levels.length; i++)
				if (levels[i].equalsIgnoreCase("verbose"))
					logVerbose = true;
				else if (levels[i].equalsIgnoreCase("info"))
					logInfo = true;
				else if (levels[i].equalsIgnoreCase("warn"))
					logWarn = true;
				else if (levels[i].equalsIgnoreCase("error"))
					logError = true;
				else if (levels[i].equalsIgnoreCase("crisis"))
					logCrisis = true;
				else if (levels[i].equalsIgnoreCase("debug"))
					DebugLogger.setEnabled(true);
		}
	}

	public static void printStackTrace(Throwable t) {
		if (logWriter != null)
			t.printStackTrace(logWriter);
		else {
			t.printStackTrace(System.err);
		}
		if (DebugLogger.getEnabled())
			DebugLogger.printStackTrace(t);
	}

	public static void close() {
		if (logWriter != null)
			logWriter.close();
	}

	public static void verbose(String data) {
		if (logVerbose)
			log("VERBOSE", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void info(byte[] data) {
		if (logInfo)
			log("INFO", new String(data));
	}

	public static void info(String data) {
		if (logInfo)
			log("INFO", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void info(String classname, String data) {
		data = classname + "@" + data;

		if (logInfo)
			log("INFO", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void warn(String data) {
		if (logWarn)
			log("WARN", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void error(String data) {
		if (logError)
			log("ERROR", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void error(String classname, String data) {
		data = classname + "@" + data;

		if (logError)
			log("ERROR", data);
		if (DebugLogger.getEnabled())
			DebugLogger.log(data);
	}

	public static void email(String eventType, String message) {
		if ((emailEvents == null)
				|| (emailEvents.indexOf(eventType.toUpperCase()) == -1)) {
			return;
		}

		if (DebugLogger.getEnabled()) {
			DebugLogger.log("About to email event " + eventType + " :: "
					+ message);
		}
		String host = "unknown_host";
		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			printStackTrace(e);
		}

		String fromAddress = "pools@primrose.org.uk";
		String subject = eventType + " : " + poolName + "@" + host;
		info("Sending email for eventType(" + eventType + "), toAddress("
				+ toAddress + "), fromAddress(" + fromAddress + ") message("
				+ message + ")");
	}

	public static void crisis(String message) {
		if (logCrisis) {
			log("CRISIS", message);
		}

		if (DebugLogger.getEnabled())
			DebugLogger.log(message);
		if (emailEvents == null)
			return;
		email("CRISIS", message);
	}

	public static void crisis(String classname, String message) {
		message = classname + "@" + message;
		if (logCrisis) {
			log("CRISIS", message);
		}

		if (DebugLogger.getEnabled())
			DebugLogger.log(message);
		if (emailEvents == null)
			return;
		email("CRISIS", message);
	}

	private static void log(String level, String data) {
		try {
			setLogWriter(origLogName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		Calendar now = Calendar.getInstance();
		String nowString = now.get(5) + "/" + (now.get(2) + 1) + "/"
				+ now.get(1) + " " + now.get(11) + ":" + now.get(12) + ":"
				+ now.get(13) + ":" + now.get(Calendar.MILLISECOND);

		//String message = nowString + " : " + level + ": " + data;
		String message = data;

		if (logWriter == null)
			System.out.println(message);
		else
			logWriter.println(message);
	}

	public static void linebreak() {
		if (logWriter == null)
			System.out.println("\n");
		else
			logWriter.println("\n");
	}

	public String getLogLevel() {
		return null;
	}

	public int getLogDayOfMonth() {
		return logDayOfMonth;
	}

	public void setLogDayOfMonth(int logDayOfMonth) {
		logDayOfMonth = logDayOfMonth;
	}
}
