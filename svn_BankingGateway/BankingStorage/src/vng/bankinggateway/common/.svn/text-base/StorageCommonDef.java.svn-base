/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.common;

/**
 *
 * @author root
 */
public class StorageCommonDef {

    public static final int POOL_CLIENT = 10000;
    public static final String FILE_SEP = "/";
    public static final String SIG = "vng-zingme-signature";
    public static final byte[] B_SIG = SIG.getBytes();
    public static final int MILISECINSEC = 1000;
    public static final long MILISECONDINDAY = 86400000;
    public static final String ZING_PAY_ID = "zing";
    
    //TaskID
    public static final String TASK_ID_DOISOAT_HD_BANK = "DOISOAT_HD_BANK";
    public static final String TASK_ID_DOISOAT_VIETIN_BANK = "DOISOAT_VIETIN_BANK";
    public static final String TASK_ID_DOISOAT_ESALE = "DOISOAT_ESALE";
    public static final String TASK_ID_RUN_RECOMFIRM = "RUN_RECONFIRM";
    public static final String TASK_ID_PROCESS_PENDING = "PROCESS_PENDING";
    public static final String TASK_ID_DAILYUPDATE = "DAILY_UPDATE";
    public static final String TASK_ID_NOTIFY = "NOTIFY";
    
    //TaskName int
    public static final String NAME_DOISOAT_HD_BANK = "Doi soat HD Bank";
    public static final String NAME_DOISOAT_VIETIN_BANK = "Doi soat Vietin Bank";
    public static final String NAME_DOISOAT_ESALE = "Doi soat esale";
    public static final String NAME_RECOMFIRM = "Run reconfirm manually";
    public static final String NAME_PENDING = "Process pending by worker";
    public static final String NAME_DAILYUPDATE = "Daily update automatically";
    public static final String NAME_ID_NOTIFY = "Notify";
    
    public enum Task_Status {
        NEW,
        RUNNING,
        FAIL,
        SUCCESS
    }

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
        public static final short ESALE_EBANKING = 1;
        public static final short INTERNET_BANKING = 2;
        public static final short MOBILE_BANKING = 3;
        public static final short COUNTER_BANKING = 4;
    }

    public static class TRANX_RES_CODE {

        public static final String TC_SUCCESS = "00";
        public static final String TC_INPROCESS = "";
        public static final String TC_NET_ERROR = "06";
    }
    
}
