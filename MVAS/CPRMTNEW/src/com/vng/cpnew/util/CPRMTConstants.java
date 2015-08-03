package com.vng.cpnew.util;

public class CPRMTConstants {
	public final static String BACK_SLASH = "/";
	public final static String DEFAULT_FOLDER = "resources";
	public final static String DB_CONFIG_FILE_NAME = "CPNEW_config.properties";

	public final static String COMMAND_CODE_ADS = "COMMAND_CODE_ADS";
	public final static String SERVICE_ID = "SERVICE_ID";
	public final static String END_MESSAGE = "END_MESSAGE";

	public final static String ALLOW_OPERATORS = "ALLOW_OPERATORS";

	public static String tblUser = "msg_receive_user";
	public static String tblMO = "msg_receive_mo";
	public static String tblMT = "msg_ads_mt";

	public final static String PRIVATE_KEY = "PRIVATE_KEY";

	public final static String tableNameMapping = "sms_moid_mapping";

	// public static String tblReceiveQueue = "sms_receive_queue";
	// Start Duy changed. Review later
	public static String tblReceiveQueue = "sms_receive_queue_mo";

	public static String tblSendQueue = "ems_send_queue";

	public static String tblGroup = "msg_receive_group";
	public static String tblModule = "msg_receive_module";
	public static String tblPolicy = "msg_receive_policy";

	public static String UNKNOWN = "Unknown";
	public static String UNKNOWN_OPERATOR = "Unknown";

	public static String SMS_RECEIVER_LOG = "sms_receive_log";

	public static String ADS_SERVICEID = "6069";
	public static String ADS_COMMANDCODE = "ADSCSM";

	public static int ERRORCODE_TRANSACTION_OK = 1;
	public static int ERRORCODE_INVALID_USERID = -1;
	public static int ERRORCODE_INVALID_MESSAGE = -2;
	public static int ERRORCODE_INVALID_OPERATOR = -3;
	public static int ERRORCODE_INVALID_SERVICEID_KEYWORD = -4;
	public static int ERRORCODE_INVALID_DUPLICATE = -5;
	public static int ERRORCODE_SYSTEM_BUSY = -6;
	public static int ERRORCODE_INVALID_PARTNER = -7;
	public static int ERRORCODE_INVALID_REQUESTID = -8;
	public static int ERRORCODE_CANCELED_ADS = -9;
}
