/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.common;

/**
 *
 * @author root
 */
public class PaymentReturnCode {

    public static final int SUCCESS = 0;
    public static final int ERROR_EXIST_KEY = -1;
    public static final int ERROR_CONNECT_FAIL = -2;
    public static final int ERROR_OPERATOR_FAIL = -3;

    public class DatabaseCode {

        public static final int DB_SUCCESS = 1;
        public static final int DB_ERROR_EXIST_KEY = 0;
        public static final int DB_ERROR = -1;
        public static final int DB_ERROR_DUPLICATE_KEY = -11;
    }

    public class BalCacheCode {

        public static final int E_SUCCESS = 0;
        public static final int E_ERROR_NOT_EXIST_KEY = -1;
        public static final int E_ERROR = -2;
        public static final int E_NOT_ENOUGH_TO_SUD = -3;
    }

    public class GameCode {

        public static final int G_SUCCESS = 0;
        public static final int G_ERROR_NOT_SERVER = -101;
        public static final int G_ERROR_NOT_COMPATIBLE = -102;
        public static final int G_ERROR_UNKNOWN = -103;
        public static final int G_ERROR_NULL_RETURN = -104;
        public static final int G_ERROR_NOT_FOUND_SERVICE = -105;
        public static final int G_ERROR_SERVICE_INVALID = -106;
        public static final int G_ERROR_NETWORK_FAIL = -107;
        public static final int G_ERROR_RESPONSE_TIMEOUT = -108;
    }

    public class AdminCode {

        public static final int A_SUCCESS = -1011;
        public static final int A_ERROR = -1111;
    }

    public class PayletterReturnCode {

        public static final int P_SUCCESS = 0;
        public static final int P_ERROR_ACC_NOT_EXIST = -1;
        public static final int P_ERROR_SIG_INVALID = -2;
        public static final int P_ERROR_MONEY_ILLEGAL = -3;
        public static final int P_ERROR_PARAMETER_INVALID = -4;
        public static final int P_ERROR_MONEY_NEGATIVE = -5;
        public static final int P_ERROR_DUPLICATE_TRANSACTION = -6;
        public static final int P_ERROR_DB = -7;
    }

    public class BillingCode {

        public static final int B_SUCCESS = 0;
        public static final int B_ERROR_FAIL = -1;
        public static final int B_ERROR_SIG_INVALID = -2;
        public static final int B_ERROR_NOT_ENOUGHT_MONEY = -3;
        public static final int B_ERROR_TOKEN_INVALID = -4;
        public static final int B_ERROR_GAME_APP_NOT_ALLOW = -5;
        public static final int B_ERROR_MONEY_ILLEGAL = -6;
    }
}
