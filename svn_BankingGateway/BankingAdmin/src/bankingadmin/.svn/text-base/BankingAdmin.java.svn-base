/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingadmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import vng.banking.admin.config.Configuration;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Task;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SimpleDateFormat dfTask = new SimpleDateFormat("MMddyy");
            List<T_TransactionReport> tranxs  = BankingStorageClient.getMainInstance("10.30.22.138",
                    9778).getTranxsWithConfirmStatus(dfTask.format(new Date()), 
                        CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR);
           
           if (tranxs!= null) {
               System.out.println("True");
                System.out.println(" " + tranxs.size());
           } else {
           }
        } catch (TException ex) {
            Logger.getLogger(BankingAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
