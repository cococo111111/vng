// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Data.java

package org.smpp;


public class Data
{

    public Data()
    {
    }

    public static final synchronized void setDefaultTon(byte dfltTon)
    {
        defaultTon = dfltTon;
    }

    public static final synchronized void setDefaultNpi(byte dfltNpi)
    {
        defaultNpi = dfltNpi;
    }

    public static final synchronized byte getDefaultTon()
    {
        return defaultTon;
    }

    public static final synchronized byte getDefaultNpi()
    {
        return defaultNpi;
    }

    public static final long getCurrentTime()
    {
        return System.currentTimeMillis();
    }

    public static final int SM_CONNID_LEN = 16;
    public static final int SM_MSG_LEN = 300;
    public static final int SM_SYSID_LEN = 16;
    public static final int SM_MSGID_LEN = 64;
    public static final int SM_PASS_LEN = 9;
    public static final int SM_DATE_LEN = 17;
    public static final int SM_SRVTYPE_LEN = 6;
    public static final int SM_SYSTYPE_LEN = 13;
    public static final int SM_ADDR_LEN = 21;
    public static final int SM_DATA_ADDR_LEN = 65;
    public static final int SM_ADDR_RANGE_LEN = 41;
    public static final int SM_TYPE_LEN = 13;
    public static final int SM_DL_NAME_LEN = 21;
    public static final int SM_PARAM_NAME_LEN = 10;
    public static final int SM_PARAM_VALUE_LEN = 10;
    public static final int SM_MAX_CNT_DEST_ADDR = 254;
    public static final int CONNECTION_CLOSED = 0;
    public static final int CONNECTION_OPENED = 1;
    public static final int SM_ACK = 1;
    public static final int SM_NO_ACK = 0;
    public static final int SM_RESPONSE_ACK = 0;
    public static final int SM_RESPONSE_TNACK = 1;
    public static final int SM_RESPONSE_PNACK = 2;
    public static final int GENERIC_NACK = 0x80000000;
    public static final int BIND_RECEIVER = 1;
    public static final int BIND_RECEIVER_RESP = 0x80000001;
    public static final int BIND_TRANSMITTER = 2;
    public static final int BIND_TRANSMITTER_RESP = 0x80000002;
    public static final int QUERY_SM = 3;
    public static final int QUERY_SM_RESP = 0x80000003;
    public static final int SUBMIT_SM = 4;
    public static final int SUBMIT_SM_RESP = 0x80000004;
    public static final int DELIVER_SM = 5;
    public static final int DELIVER_SM_RESP = 0x80000005;
    public static final int UNBIND = 6;
    public static final int UNBIND_RESP = 0x80000006;
    public static final int REPLACE_SM = 7;
    public static final int REPLACE_SM_RESP = 0x80000007;
    public static final int CANCEL_SM = 8;
    public static final int CANCEL_SM_RESP = 0x80000008;
    public static final int BIND_TRANSCEIVER = 9;
    public static final int BIND_TRANSCEIVER_RESP = 0x80000009;
    public static final int OUTBIND = 11;
    public static final int ENQUIRE_LINK = 21;
    public static final int ENQUIRE_LINK_RESP = 0x80000015;
    public static final int SUBMIT_MULTI = 33;
    public static final int SUBMIT_MULTI_RESP = 0x80000021;
    public static final int ALERT_NOTIFICATION = 258;
    public static final int DATA_SM = 259;
    public static final int DATA_SM_RESP = 0x80000103;
    public static final int ESME_ROK = 0;
    public static final int ESME_RINVMSGLEN = 1;
    public static final int ESME_RINVCMDLEN = 2;
    public static final int ESME_RINVCMDID = 3;
    public static final int ESME_RINVBNDSTS = 4;
    public static final int ESME_RALYBND = 5;
    public static final int ESME_RINVPRTFLG = 6;
    public static final int ESME_RINVREGDLVFLG = 7;
    public static final int ESME_RSYSERR = 8;
    public static final int ESME_RINVSRCADR = 10;
    public static final int ESME_RINVDSTADR = 11;
    public static final int ESME_RINVMSGID = 12;
    public static final int ESME_RBINDFAIL = 13;
    public static final int ESME_RINVPASWD = 14;
    public static final int ESME_RINVSYSID = 15;
    public static final int ESME_RCANCELFAIL = 17;
    public static final int ESME_RREPLACEFAIL = 19;
    public static final int ESME_RMSGQFUL = 20;
    public static final int ESME_RINVSERTYP = 21;
    public static final int ESME_RADDCUSTFAIL = 25;
    public static final int ESME_RDELCUSTFAIL = 26;
    public static final int ESME_RMODCUSTFAIL = 27;
    public static final int ESME_RENQCUSTFAIL = 28;
    public static final int ESME_RINVCUSTID = 29;
    public static final int ESME_RINVCUSTNAME = 31;
    public static final int ESME_RINVCUSTADR = 33;
    public static final int ESME_RINVADR = 34;
    public static final int ESME_RCUSTEXIST = 35;
    public static final int ESME_RCUSTNOTEXIST = 36;
    public static final int ESME_RADDDLFAIL = 38;
    public static final int ESME_RMODDLFAIL = 39;
    public static final int ESME_RDELDLFAIL = 40;
    public static final int ESME_RVIEWDLFAIL = 41;
    public static final int ESME_RLISTDLSFAIL = 48;
    public static final int ESME_RPARAMRETFAIL = 49;
    public static final int ESME_RINVPARAM = 50;
    public static final int ESME_RINVNUMDESTS = 51;
    public static final int ESME_RINVDLNAME = 52;
    public static final int ESME_RINVDLMEMBDESC = 53;
    public static final int ESME_RINVDLMEMBTYP = 56;
    public static final int ESME_RINVDLMODOPT = 57;
    public static final int ESME_RINVDESTFLAG = 64;
    public static final int ESME_RINVSUBREP = 66;
    public static final int ESME_RINVESMCLASS = 67;
    public static final int ESME_RCNTSUBDL = 68;
    public static final int ESME_RSUBMITFAIL = 69;
    public static final int ESME_RINVSRCTON = 72;
    public static final int ESME_RINVSRCNPI = 73;
    public static final int ESME_RINVDSTTON = 80;
    public static final int ESME_RINVDSTNPI = 81;
    public static final int ESME_RINVSYSTYP = 83;
    public static final int ESME_RINVREPFLAG = 84;
    public static final int ESME_RINVNUMMSGS = 85;
    public static final int ESME_RTHROTTLED = 88;
    public static final int ESME_RPROVNOTALLWD = 89;
    public static final int ESME_RINVSCHED = 97;
    public static final int ESME_RINVEXPIRY = 98;
    public static final int ESME_RINVDFTMSGID = 99;
    public static final int ESME_RX_T_APPN = 100;
    public static final int ESME_RX_P_APPN = 101;
    public static final int ESME_RX_R_APPN = 102;
    public static final int ESME_RQUERYFAIL = 103;
    public static final int ESME_RINVPGCUSTID = 128;
    public static final int ESME_RINVPGCUSTIDLEN = 129;
    public static final int ESME_RINVCITYLEN = 130;
    public static final int ESME_RINVSTATELEN = 131;
    public static final int ESME_RINVZIPPREFIXLEN = 132;
    public static final int ESME_RINVZIPPOSTFIXLEN = 133;
    public static final int ESME_RINVMINLEN = 134;
    public static final int ESME_RINVMIN = 135;
    public static final int ESME_RINVPINLEN = 136;
    public static final int ESME_RINVTERMCODELEN = 137;
    public static final int ESME_RINVCHANNELLEN = 138;
    public static final int ESME_RINVCOVREGIONLEN = 139;
    public static final int ESME_RINVCAPCODELEN = 140;
    public static final int ESME_RINVMDTLEN = 141;
    public static final int ESME_RINVPRIORMSGLEN = 142;
    public static final int ESME_RINVPERMSGLEN = 143;
    public static final int ESME_RINVPGALERTLEN = 144;
    public static final int ESME_RINVSMUSERLEN = 145;
    public static final int ESME_RINVRTDBLEN = 146;
    public static final int ESME_RINVREGDELLEN = 147;
    public static final int ESME_RINVMSGDISTLEN = 148;
    public static final int ESME_RINVPRIORMSG = 149;
    public static final int ESME_RINVMDT = 150;
    public static final int ESME_RINVPERMSG = 151;
    public static final int ESME_RINVMSGDIST = 152;
    public static final int ESME_RINVPGALERT = 153;
    public static final int ESME_RINVSMUSER = 154;
    public static final int ESME_RINVRTDB = 155;
    public static final int ESME_RINVREGDEL = 156;
    public static final int ESME_RINVOPTPARSTREAM = 157;
    public static final int ESME_ROPTPARNOTALLWD = 158;
    public static final int ESME_RINVOPTPARLEN = 159;
    public static final int ESME_RMISSINGOPTPARAM = 195;
    public static final int ESME_RINVOPTPARAMVAL = 196;
    public static final int ESME_RDELIVERYFAILURE = 254;
    public static final int ESME_RUNKNOWNERR = 255;
    public static final int ESME_LAST_ERROR = 300;
    public static final byte SMPP_V33 = 51;
    public static final byte SMPP_V34 = 52;
    public static final byte GSM_TON_UNKNOWN = 0;
    public static final byte GSM_TON_INTERNATIONAL = 1;
    public static final byte GSM_TON_NATIONAL = 2;
    public static final byte GSM_TON_NETWORK = 3;
    public static final byte GSM_TON_SUBSCRIBER = 4;
    public static final byte GSM_TON_ALPHANUMERIC = 5;
    public static final byte GSM_TON_ABBREVIATED = 6;
    public static final byte GSM_TON_RESERVED_EXTN = 7;
    public static final byte GSM_NPI_UNKNOWN = 0;
    public static final byte GSM_NPI_E164 = 1;
    public static final byte GSM_NPI_ISDN = 1;
    public static final byte GSM_NPI_X121 = 3;
    public static final byte GSM_NPI_TELEX = 4;
    public static final byte GSM_NPI_LAND_MOBILE = 6;
    public static final byte GSM_NPI_NATIONAL = 8;
    public static final byte GSM_NPI_PRIVATE = 9;
    public static final byte GSM_NPI_ERMES = 10;
    public static final byte GSM_NPI_INTERNET = 14;
    public static final byte GSM_NPI_WAP_CLIENT_ID = 18;
    public static final byte GSM_NPI_RESERVED_EXTN = 15;
    public static final String SERVICE_NULL = "";
    public static final String SERVICE_CMT = "CMT";
    public static final String SERVICE_CPT = "CPT";
    public static final String SERVICE_VMN = "VMN";
    public static final String SERVICE_VMA = "VMA";
    public static final String SERVICE_WAP = "WAP";
    public static final String SERVICE_USSD = "USSD";
    public static final byte SMPP_PROTOCOL = 1;
    public static final byte SMPPP_PROTOCOL = 2;
    public static final byte SM_SERVICE_MOBILE_TERMINATED = 0;
    public static final byte SM_SERVICE_MOBILE_ORIGINATED = 1;
    public static final byte SM_SERVICE_MOBILE_TRANSCEIVER = 2;
    public static final int SM_STATE_EN_ROUTE = 1;
    public static final int SM_STATE_DELIVERED = 2;
    public static final int SM_STATE_EXPIRED = 3;
    public static final int SM_STATE_DELETED = 4;
    public static final int SM_STATE_UNDELIVERABLE = 5;
    public static final int SM_STATE_ACCEPTED = 6;
    public static final int SM_STATE_INVALID = 7;
    public static final int SM_STATE_REJECTED = 8;
    public static final int SM_ESM_DEFAULT = 0;
    public static final int SM_DATAGRAM_MODE = 1;
    public static final int SM_FORWARD_MODE = 2;
    public static final int SM_STORE_FORWARD_MODE = 3;
    public static final int SM_SMSC_DLV_RCPT_TYPE = 4;
    public static final int SM_ESME_DLV_ACK_TYPE = 8;
    public static final int SM_ESME_MAN_USER_ACK_TYPE = 16;
    public static final int SM_CONV_ABORT_TYPE = 24;
    public static final int SM_INTMD_DLV_NOTIFY_TYPE = 32;
    public static final int SM_NONE_GSM = 0;
    public static final int SM_UDH_GSM = 64;
    public static final int SM_REPLY_PATH_GSM = 128;
    public static final int SM_UDH_REPLY_PATH_GSM = 192;
    public static final short OPT_PAR_MSG_WAIT = 2;
    public static final short OPT_PAR_PRIV_IND = 513;
    public static final short OPT_PAR_SRC_SUBADDR = 514;
    public static final int OPT_PAR_SRC_SUBADDR_MIN = 2;
    public static final int OPT_PAR_SRC_SUBADDR_MAX = 23;
    public static final short OPT_PAR_DEST_SUBADDR = 515;
    public static final int OPT_PAR_DEST_SUBADDR_MIN = 2;
    public static final int OPT_PAR_DEST_SUBADDR_MAX = 23;
    public static final short OPT_PAR_USER_MSG_REF = 516;
    public static final short OPT_PAR_USER_RESP_CODE = 517;
    public static final short OPT_PAR_LANG_IND = 525;
    public static final short OPT_PAR_SRC_PORT = 522;
    public static final short OPT_PAR_DST_PORT = 523;
    public static final short OPT_PAR_SAR_MSG_REF_NUM = 524;
    public static final short OPT_PAR_SAR_TOT_SEG = 526;
    public static final short OPT_PAR_SAR_SEG_SNUM = 527;
    public static final short OPT_PAR_SC_IF_VER = 528;
    public static final short OPT_PAR_DISPLAY_TIME = 4609;
    public static final short OPT_PAR_MS_VALIDITY = 4612;
    public static final short OPT_PAR_DPF_RES = 1056;
    public static final short OPT_PAR_SET_DPF = 1057;
    public static final short OPT_PAR_MS_AVAIL_STAT = 1058;
    public static final short OPT_PAR_NW_ERR_CODE = 1059;
    public static final int OPT_PAR_NW_ERR_CODE_MIN = 3;
    public static final int OPT_PAR_NW_ERR_CODE_MAX = 3;
    public static final short OPT_PAR_DEL_FAIL_RSN = 1061;
    public static final short OPT_PAR_MORE_MSGS = 1062;
    public static final short OPT_PAR_MSG_STATE = 1063;
    public static final short OPT_PAR_CALLBACK_NUM = 897;
    public static final int OPT_PAR_CALLBACK_NUM_MIN = 4;
    public static final int OPT_PAR_CALLBACK_NUM_MAX = 19;
    public static final short OPT_PAR_CALLBACK_NUM_PRES_IND = 770;
    public static final short OPT_PAR_CALLBACK_NUM_ATAG = 771;
    public static final int OPT_PAR_CALLBACK_NUM_ATAG_MIN = 1;
    public static final int OPT_PAR_CALLBACK_NUM_ATAG_MAX = 65;
    public static final short OPT_PAR_NUM_MSGS = 772;
    public static final short OPT_PAR_SMS_SIGNAL = 4611;
    public static final short OPT_PAR_ALERT_ON_MSG_DELIVERY = 4876;
    public static final short OPT_PAR_ITS_REPLY_TYPE = 4992;
    public static final short OPT_PAR_ITS_SESSION_INFO = 4995;
    public static final short OPT_PAR_USSD_SER_OP = 1281;
    public static final int SM_NOPRIORITY = 0;
    public static final int SM_PRIORITY = 1;
    public static final byte SM_SMSC_RECEIPT_MASK = 3;
    public static final byte SM_SMSC_RECEIPT_NOT_REQUESTED = 0;
    public static final byte SM_SMSC_RECEIPT_REQUESTED = 1;
    public static final byte SM_SMSC_RECEIPT_ON_FAILURE = 2;
    public static final byte SM_SME_ACK_MASK = 12;
    public static final byte SM_SME_ACK_NOT_REQUESTED = 0;
    public static final byte SM_SME_ACK_DELIVERY_REQUESTED = 4;
    public static final byte SM_SME_ACK_MANUAL_REQUESTED = 8;
    public static final byte SM_SME_ACK_BOTH_REQUESTED = 12;
    public static final byte SM_NOTIF_MASK = 16;
    public static final byte SM_NOTIF_NOT_REQUESTED = 0;
    public static final byte SM_NOTIF_REQUESTED = 16;
    public static final int SM_NOREPLACE = 0;
    public static final int SM_REPLACE = 1;
    public static final int SM_DEST_SME_ADDRESS = 1;
    public static final int SM_DEST_DL_NAME = 2;
    public static final int SM_LAYER_WDP = 0;
    public static final int SM_LAYER_WCMP = 1;
    public static final int SM_OPCLASS_DATAGRAM = 0;
    public static final int SM_OPCLASS_TRANSACTION = 3;
    public static final short OPT_PAR_ORIG_MSC_ADDR = -32639;
    public static final int OPT_PAR_ORIG_MSC_ADDR_MIN = 1;
    public static final int OPT_PAR_ORIG_MSC_ADDR_MAX = 24;
    public static final short OPT_PAR_DEST_MSC_ADDR = -32638;
    public static final int OPT_PAR_DEST_MSC_ADDR_MIN = 1;
    public static final int OPT_PAR_DEST_MSC_ADDR_MAX = 24;
    public static final int OPT_PAR_UNUSED = 65535;
    public static final short OPT_PAR_DST_ADDR_SUBUNIT = 5;
    public static final short OPT_PAR_DST_NW_TYPE = 6;
    public static final short OPT_PAR_DST_BEAR_TYPE = 7;
    public static final short OPT_PAR_DST_TELE_ID = 8;
    public static final short OPT_PAR_SRC_ADDR_SUBUNIT = 13;
    public static final short OPT_PAR_SRC_NW_TYPE = 14;
    public static final short OPT_PAR_SRC_BEAR_TYPE = 15;
    public static final short OPT_PAR_SRC_TELE_ID = 16;
    public static final short OPT_PAR_QOS_TIME_TO_LIVE = 23;
    public static final int OPT_PAR_QOS_TIME_TO_LIVE_MIN = 1;
    public static final int OPT_PAR_QOS_TIME_TO_LIVE_MAX = 4;
    public static final short OPT_PAR_PAYLOAD_TYPE = 25;
    public static final short OPT_PAR_ADD_STAT_INFO = 29;
    public static final int OPT_PAR_ADD_STAT_INFO_MIN = 1;
    public static final int OPT_PAR_ADD_STAT_INFO_MAX = 256;
    public static final short OPT_PAR_RECP_MSG_ID = 30;
    public static final int OPT_PAR_RECP_MSG_ID_MIN = 1;
    public static final int OPT_PAR_RECP_MSG_ID_MAX = 65;
    public static final short OPT_PAR_MSG_PAYLOAD = 1060;
    public static final int OPT_PAR_MSG_PAYLOAD_MIN = 1;
    public static final int OPT_PAR_MSG_PAYLOAD_MAX = 1500;
    public static final String ENC_ASCII = "ASCII";
    public static final String ENC_CP1252 = "Cp1252";
    public static final String ENC_ISO8859_1 = "ISO8859_1";
    public static final String ENC_UTF16_BEM = "UnicodeBig";
    public static final String ENC_UTF16_BE = "UnicodeBigUnmarked";
    public static final String ENC_UTF16_LEM = "UnicodeLittle";
    public static final String ENC_UTF16_LE = "UnicodeLittleUnmarked";
    public static final String ENC_UTF8 = "UTF8";
    public static final String ENC_UTF16 = "UTF-16";
    /**
     * @deprecated Field CHAR_ENC is deprecated
     */
    public static final String CHAR_ENC = "ASCII";
    public static final String DFLT_MSGID = "";
    public static final String DFLT_MSG = "";
    public static final String DFLT_SRVTYPE = "";
    public static final String DFLT_SYSID = "";
    public static final String DFLT_PASS = "";
    public static final String DFLT_SYSTYPE = "";
    public static final String DFLT_ADDR_RANGE = "";
    public static final String DFLT_DATE = "";
    public static final String DFLT_ADDR = "";
    public static final byte DFLT_MSG_STATE = 0;
    public static final byte DFLT_ERR = 0;
    public static final String DFLT_SCHEDULE = "";
    public static final String DFLT_VALIDITY = "";
    public static final byte DFLT_REG_DELIVERY = 0;
    public static final byte DFLT_DFLTMSGID = 0;
    public static final byte DFLT_MSG_LEN = 0;
    public static final byte DFLT_ESM_CLASS = 0;
    public static final byte DFLT_DATA_CODING = 0;
    public static final byte DFLT_PROTOCOLID = 0;
    public static final byte DFLT_PRIORITY_FLAG = 0;
    public static final byte DFTL_REPLACE_IFP = 0;
    public static final String DFLT_DL_NAME = "";
    public static final byte DFLT_GSM_TON = 0;
    public static final byte DFLT_GSM_NPI = 0;
    public static final byte DFLT_DEST_FLAG = 0;
    public static final int MAX_PDU_LEN = 5000;
    public static final int PDU_HEADER_SIZE = 16;
    public static final int TLV_HEADER_SIZE = 4;
    public static final long RECEIVER_TIMEOUT = 60000L;
    public static final long CONNECTION_RECEIVE_TIMEOUT = 10000L;
    public static final long COMMS_TIMEOUT = 60000L;
    public static final long QUEUE_TIMEOUT = 10000L;
    public static final long ACCEPT_TIMEOUT = 60000L;
    public static final long RECEIVE_BLOCKING = -1L;
    public static final int MAX_VALUE_PORT = 65535;
    public static final int MIN_VALUE_PORT = 100;
    public static final int MIN_LENGTH_ADDRESS = 7;
    private static byte defaultTon = 0;
    private static byte defaultNpi = 0;

}
