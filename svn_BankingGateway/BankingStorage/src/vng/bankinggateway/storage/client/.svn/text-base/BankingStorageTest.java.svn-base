/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.storage.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.thrift.TException;
import vng.bankinggateway.model.util.Config;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingStorageTest {

        public static void main(String[] args) throws TException, ParseException, IOException {
                //                T_Transaction tx = new T_Transaction("HDBANK",1,"022113171717","vinhbkiter","agentID","ZIONSRV","M130212-0001",1000000,"127.0.0.1","vinh test","anhdao","112113453",(short)0 );
                //                int res = BankingStorageClient.getMainInstance("10.30.22.138",9778).storeTranx(tx);
                //          T_TranStat txStatus = new T_TranStat(1,"022113171717",(short)1,"07","test");
                //          int res = BankingStorageClient.getMainInstance("10.30.22.138",9778).updateTranxStatus(txStatus);
                //        T_TranStat txStatus = new T_TranStat(1, "022113171717", (short) 1, "07", "test");
                //          int res = BankingStorageClient.getMainInstance("10.30.22.138",9778).updateTranxAndStatus(tx,(short)2,"00");
                //        int res = BankingStorageClient.getMainInstance("10.30.22.138",9778).generateTransID("022713");
                //        System.out.println(res);
                //        T_TranStat ret = BankingStorageClient.getMainInstance("10.30.22.138",9778).getTranxStatus("REF-485465","022713");
                //        System.out.println(ret.toString());
//        int res = BankingStorageClient.getMainInstance("10.30.22.138",9778).generateQueryID("030413");
//        String time = Config.dayReport;
//        System.out.println(formatTime(time));
//        List<T_TransactionReport> res = BankingStorageClient.getMainInstance("10.30.22.138", 9778).getTranxs(formatTime(time), (short)100, true);
//
////        
//                List<T_TransactionReport> ret = BankingStorageClient.getMainInstance("10.30.22.138", 9778).getTranxsWithConfirmStatus("042513", (short) 0);
//                System.out.println(ret.size());
//                for (T_TransactionReport tx : ret) {
//                        System.out.println(tx.txID);
////            T_TranStat rs = BankingStorageClient.getMainInstance("10.30.22.138", 9778).getTranxStatus(tx.refID, "031113");
//                        System.out.println(tx.txStatus);
//
////            System.out.println(rs.txStatus);
//                }
//        exportData2Esale();
            BankingStorageClient.getMainInstance("10.30.22.138", 9778).generateTransIdByBankCode("HDBANK");

        }


        private static String formatTime(String inputTime) throws ParseException {
                DateFormat df = new SimpleDateFormat("yyMMdd");
                Date date = df.parse(inputTime);
                DateFormat dfTransxTime = new SimpleDateFormat("MMddyy");
                return dfTransxTime.format(date);
        }
}
