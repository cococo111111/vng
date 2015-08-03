/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.thrift.TException;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Task;
import vng.bankinggateway.thrift.T_TransactionReport;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class ExportDataForReconcile {
    public static void main(String[] args) throws TException, ParseException, IOException {
        InitUtil.initConfiguration();
        if (args.length == 2) {
            if(args[0].trim().equals(CommonDef.BANKCODE.HDBANK)
                    || args[0].trim().equals(CommonDef.BANKCODE.VIETINBANK)) {
                exportData2Bank(args[1], args[0].trim());
            } else {
                exportData2Esale(args[1], CommonDef.BANKCODE.HDBANK);
                exportData2Esale(args[1], CommonDef.BANKCODE.VIETINBANK);
            }
        } else if (args.length == 1) {
            String time = System.getProperty("day");
            if(args[0].trim().equals(CommonDef.BANKCODE.HDBANK)
                    || args[0].trim().equals(CommonDef.BANKCODE.VIETINBANK)) {
                exportData2Bank(time, args[0].trim());
            } else {
                exportData2Esale(time, CommonDef.BANKCODE.HDBANK);
                exportData2Esale(time, CommonDef.BANKCODE.VIETINBANK);
            }
        }
    }

    private static void exportData2Esale(String time, String bankCode) throws ParseException, TException, IOException {
        SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");
        T_Task task = new T_Task();
        task.setTaskId(StorageCommonDef.TASK_ID_DOISOAT_ESALE);
        task.setStatus((short)StorageCommonDef.Task_Status.RUNNING.ordinal());
        task.setStartTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).updateTaskStart(task);
        
        System.out.println(CommonDef.SHA1_SHARED_KEY);
        System.out.println(InitUtil.REPORTER);

        List<T_TransactionReport> list_tx = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,  CommonDef.STORAGE_PORT).getTranxsWithBankCode(time, (short)100, false, bankCode);
        BufferedWriter bw = new BufferedWriter(new FileWriter(InitUtil.FOLDER_RECONCILE + "esale/" + formatTime(time) + "_" +bankCode +"_TRANS_Esale.txt"));
        bw.append("OrderNo,AgencyCode,Amount,Status,CreatedDate,CheckSum\n");
        DateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        DateFormat df_RecordTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (list_tx != null) {
            for (T_TransactionReport tx : list_tx) {
                Date date = df.parse(tx.time);

                String detail_record =  tx.refID + ","
                                        + tx.agencyCode + ","
                                        + tx.amount + ","
                                        + tx.responseCode + ","
                                        + df_RecordTime.format(date) + ",";

                String input = detail_record.replace(",", "");
                String checksum = Encryption.SHA1(input + CommonDef.SHA1_SHARED_KEY);

                bw.append(detail_record + checksum + "\n");
            }
            Date today = new Date();
            String trailer_record =list_tx.size() + ","+ InitUtil.REPORTER + "," + df_RecordTime.format(today);
            String input2 = df_RecordTime.format(today) + list_tx.size();
            String checksum2 = Encryption.SHA1(input2 + CommonDef.SHA1_SHARED_KEY);
            bw.append(trailer_record + "," + checksum2);

        }
        System.out.println(list_tx.size());
        bw.close();
        
        T_Task endTask = new T_Task();
        endTask.setTaskId(StorageCommonDef.TASK_ID_DOISOAT_ESALE);
        endTask.setStatus((short)StorageCommonDef.Task_Status.SUCCESS.ordinal());
        endTask.setEndTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).updateTaskStop(endTask);
    }

    private static void exportData2Bank(String time, String bankCode) throws ParseException, TException, IOException {
        SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");
        T_Task task = new T_Task();
        String taskId = StorageCommonDef.TASK_ID_DOISOAT_HD_BANK;
        if (bankCode.equals(CommonDef.BANKCODE.VIETINBANK)) {
            taskId = StorageCommonDef.TASK_ID_DOISOAT_VIETIN_BANK;
        }
        task.setTaskId(taskId);
        task.setStatus((short)StorageCommonDef.Task_Status.RUNNING.ordinal());
        task.setStartTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).updateTaskStart(task);
        
        System.out.println(InitUtil.PRIVATE_KEY_RECONCILE);
        System.out.println(InitUtil.REPORTER);

        List<T_TransactionReport> list_tx = BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST,  CommonDef.STORAGE_PORT).getTranxsWithBankCode(time, (short) 100, false, bankCode);
        BufferedWriter bw = new BufferedWriter(new FileWriter(InitUtil.FOLDER_RECONCILE + "bank/" + bankCode + "/"+ formatTime(time) + "_TRANS_Zion.txt"));
        bw.append("LoaiBanGhi,KetQua,TenDangNhap,MaDinhDanh,SoTien,ThongTinChiTiet,TransID,ThoiDiem,KenhThanhToan,SoTaiKhoan,LoaiGiaoDich,MaTraLoi,CheckSum\n");
        DateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        DateFormat df_RecordTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (list_tx != null) {
            for (T_TransactionReport tx : list_tx) {
                Date date = df.parse(tx.time);

                String detail_record = "0002,00,"
                        + tx.userName + ","
                        + tx.agencyCode + ","
                        + tx.amount + ","
                        + tx.description + ","
                        + tx.txID + ","
                        + df_RecordTime.format(date) + ","
                        + "1,"
                        + tx.bankAccountNumber + ","
                        + "TRA,"
                        + tx.responseCode + ",";

                String input = detail_record.replace(",", "");
                String checksum = Encryption.MD5(input + InitUtil.PRIVATE_KEY_RECONCILE);

                bw.append(detail_record + checksum + "\n");
            }
            Date today = new Date();
            String trailer_record = "0009," + list_tx.size() + ","+ InitUtil.REPORTER + "," + df_RecordTime.format(today);
            String input2 = df_RecordTime.format(today) + list_tx.size();
            String checksum2 = Encryption.MD5(input2 + InitUtil.PRIVATE_KEY_RECONCILE);
            bw.append(trailer_record + "," + checksum2);

        }
        System.out.println(list_tx.size());
        bw.close();
        
        T_Task endTask = new T_Task();
        endTask.setTaskId(taskId);
        endTask.setStatus((short)StorageCommonDef.Task_Status.SUCCESS.ordinal());
        endTask.setEndTime(dfTask.format(new Date()));
        BankingStorageClient.getMainInstance(CommonDef.STORAGE_HOST, CommonDef.STORAGE_PORT).updateTaskStop(endTask);
    }
    
    private static String formatTime(String inputTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMddyy");
        Date date = df.parse(inputTime);
        DateFormat dfTransxTime = new SimpleDateFormat("yyMMdd");
        return dfTransxTime.format(date);
    }
}
