/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.common;

/**
 *
 * @author root
 */
public class CommonDef {

    public static final int POOL_CLIENT = 10000;
    public static final String FILE_SEP = "/";
    public static final String SIG = "vng-zingme-signature";
    public static final byte[] B_SIG = SIG.getBytes();
    public static final int MILISECINSEC = 1000;
    public static final long MILISECONDINDAY = 86400000;
    public static final String ZING_PAY_ID = "zing";

    public static class TRANX_FAIL_DESCRIPTION {

        public static final String SERVICE_NOT_FOUNT = "Không tìm thấy REST-URL của ứng dụng";
        public static final String SERVICE_NOT_VALID = "REST-URL của ứng dụng không hợp lệ";
        public static final String SERVICE_NOT_COMPATIBLE = "REST-URL của ứng dụng không tương thích";
        public static final String NETWORK_FAIL = "Lỗi network";
        public static final String SERVICE_TIMEOUT = "Lỗi quá thời gian chờ";
        public static final String UNKNOWN_DESCRIPTION = "Lỗi không xác định";
    }

    public static class TRANX_STAT {

        public static final int TS_INPROCESS = 1;
        public static final String TSM_INPROCESS = "Khởi động giao dịch";
        public static final int TS_UPDATED_BALANCE = 2;
        public static final String TSM_UPDATED_BALANCE = "Đã cập nhật số tiền mới vào tài khoản";
        public static final int TS_REST_REQUESTING = 3;
        public static final String TSM_REST_REQUESTING = "Chuẩn bị gửi REST request yêu cầu ứng dụng chuyển vật phẩm cho người dùng";
        public static final int TS_ROLLBACK_BALANCE = 4;
        public static final String TSM_ROLLBACK_BALANCE = "Đã rollback tiền trong tài khoản";
        public static final int TS_CLOSE_SUCCESS = 101;
        public static final String TSM_CLOSE_SUCCESS = "Ứng dụng trả lời mã thành công (đã chuyển vật phẩm cho người dùng)";
        public static final int TS_CLOSE_EX_TIME_OUT = 102;
        public static final String TSM_CLOSE_EX_TIME_OUT = "Đã gửi request REST cho ứng dụng, nhưng không nhận được trả lời (xem như đã chuyển vật phẩm cho người dùng)";
        public static final int TS_CLOSE_EX_NETWORK = -104;
        public static final String TSM_CLOSE_EX_NETWORK = "Lỗi mạng khi gửi request REST cho ứng dụng (chưa chuyển vật phẩm cho người dùng)";
        public static final int TS_CLOSE_FAIL = -103;
        public static final String TSM_CLOSE_FAIL = "Ứng dụng trả lời mã thất bại (không chuyển vật phẩm cho người dùng)";
        public static final int TS_DELETE = -101;
    }

    public static class TRANX_TYPE {

        public static final int TT_TEST = 1;
        public static final int TT_ADMIN = 2;
        public static final int TT_PUSH_MONEY = 101;
        public static final int TT_TRANSFER_MONEY = 102;
        public static final int TT_PRESENT_MONEY = 103;
        public static final int TT_DEDUCT_MONEY = 201;
        public static final int TT_GIVE_BACK_MONEY = 202;
        
        public static final int TT_PUSH_MONEY_CARD = 1011;
        public static final int TT_PUSH_MONEY_ATM = 1012;
        public static final int TT_PUSH_MONEY_IBANKING = 1013;
        public static final int TT_PUSH_MONEY_SMS = 1014;
        public static final int TT_PUSH_MONEY_ESALE = 1015;
        public static final int TT_PUSH_MONEY_TELCO = 1016;
        public static final int TT_PUSH_MONEY_TELCO_RECONCILE = 1017;
        
        public static final int TT_COMPENSATE_CREDIT = 301;
        public static final int TT_COMPENSATE_DEBIT = 302;
        
        public static final int TT_ZINGWALLET_DEDUCT = 401; // Deduct account from walletgateway
        public static final int TT_ZINGWALLET_ROLLBACK = 402; // Rollback account from walletgateway
    }

    public static class TRANX_RES_CODE {

        public static final short TC_SUCCESS = 0;
        public static final short TC_INPROCESS = Short.MAX_VALUE;
        public static final short TC_NET_ERROR = Short.MIN_VALUE;
    }
}
