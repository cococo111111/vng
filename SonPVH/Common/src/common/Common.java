/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author sonhoang
 */
public class Common {

    public static String hostChrome = "";
    public static String SCRIBE_HOST = "";
    public static String SCRIBE_PORT = "";

    public static class BALANCE_TYPE {

        public static final int MAIN = 0;
        public static final int PROMOTION = 1;
        public static final int GAME = 2;
    }

    public static class TRANX_TYPE {

        public static final int TT_DEDUCT_MONEY = 200;
        public static final int TT_PUSH_MONEY_CARD = 101;
        public static final int TT_PUSH_MONEY_ATM = 102;
        public static final int TT_PUSH_MONEY_IBANKING = 103;
        public static final int TT_PUSH_MONEY_SMS = 104;

        private TRANX_TYPE() {
        }
    }

    public static class TRANX_RESPONSE_CODE {

        public static final short BALANCE_ZNODE_FAIL = -20;
        public static final short BALANCE_SERVICE_FALSE = -21;
        public static final short TRANX_ZNODE_FALSE = -10;
        public static final short TRANX_DB_FAIL = -11;
        public static final short SUCCESS = 0;
        public static final short TRANX_PENDING = 1;

        private TRANX_RESPONSE_CODE() {
        }
    }

    public static class TRANX_STATUS_TYPE {

        public static final short ERROR = -1;
        public static final short PENDING = 0;
        public static final short SUCCESS = 1;
    }

    public static String createPath(String prefix, int id) {
        long suffix = (id % 10);
        String path = prefix + suffix + "/" + id;
        return path;
    }

    private Common() {
    }
}
