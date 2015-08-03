/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuartzSchedulerJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.thrift.TException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import vng.banking.admin.config.Configuration;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Task;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author root
 */
public class SendEmailNotifyTaskJob implements Job {
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");
            SimpleDateFormat dfSearch = new SimpleDateFormat("MMddyy");
            T_Task task = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                    Configuration.STORAGE_PORT).getTaskByTaskId(StorageCommonDef.TASK_ID_NOTIFY);

            if (task.getStatus() != StorageCommonDef.Task_Status.FAIL.ordinal()) {
                // start time
                task.setStatus((short) StorageCommonDef.Task_Status.NEW.ordinal());
                task.setStartTime(dfTask.format(new Date()));
                BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).updateTaskStart(task);

                List<T_TransactionReport> tranxs = BankingStorageClient.getMainInstance(
                        Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).getTranxsWithBankCode(dfSearch.format(new Date()),
                        CommonDef.TRANSACTION_STATUS.TXS_ERROR, false, CommonDef.BANKCODE.HDBANK);
                if (tranxs != null && tranxs.size() > 0 && validateDurationTime(tranxs)) {
                    task.setEndTime(dfTask.format(new Date()));
                    task.setStatus((short) StorageCommonDef.Task_Status.FAIL.ordinal());
                    BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                            Configuration.STORAGE_PORT).updateTaskStop(task);
                    this.sendNotify(CommonDef.BANKCODE.HDBANK, "");
                }

                tranxs = BankingStorageClient.getMainInstance(
                        Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).getTranxsWithBankCode(dfSearch.format(new Date()),
                        CommonDef.TRANSACTION_STATUS.TXS_ERROR, false, CommonDef.BANKCODE.VIETINBANK);
                if (tranxs != null && tranxs.size() > 0 && validateDurationTime(tranxs)) {
                    task.setStatus((short) StorageCommonDef.Task_Status.FAIL.ordinal());
                    task.setEndTime(dfTask.format(new Date()));
                    BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                            Configuration.STORAGE_PORT).updateTaskStop(task);
                    this.sendNotify(CommonDef.BANKCODE.VIETINBANK, "");
                }

                tranxs = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).getTranxsWithConfirmStatus(dfSearch.format(new Date()), 
                        CommonDef.CONFIRM_TRANSACTION_STATUS.FAILED_TRANSANPORT);
                if (tranxs != null && tranxs.size() > 0 && validateDurationTime(tranxs)) {
                    task.setStatus((short) StorageCommonDef.Task_Status.FAIL.ordinal());
                    task.setEndTime(dfTask.format(new Date()));
                    BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                            Configuration.STORAGE_PORT).updateTaskStop(task);
                    this.sendNotify("ESALE", "");
                    return;
                }
                
                tranxs = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).getTranxsWithConfirmStatus(dfSearch.format(new Date()), 
                        CommonDef.CONFIRM_TRANSACTION_STATUS.CONNECTION_TIMEOUT);
                if (tranxs != null && tranxs.size() > 0 && validateDurationTime(tranxs)) {
                    task.setStatus((short) StorageCommonDef.Task_Status.FAIL.ordinal());
                    task.setEndTime(dfTask.format(new Date()));
                    BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                            Configuration.STORAGE_PORT).updateTaskStop(task);
                    this.sendNotify("ESALE", "");
                    return;
                }
                
                tranxs = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                        Configuration.STORAGE_PORT).getTranxsWithConfirmStatus(dfSearch.format(new Date()), 
                        CommonDef.CONFIRM_TRANSACTION_STATUS.WEBSERVICE_RESPONSE_ERROR);
                if (tranxs != null && tranxs.size() > 0 && validateDurationTime(tranxs)) {
                    task.setStatus((short) StorageCommonDef.Task_Status.FAIL.ordinal());
                    task.setEndTime(dfTask.format(new Date()));
                    BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                            Configuration.STORAGE_PORT).updateTaskStop(task);
                    this.sendNotify("ESALE", "");
                }
            }
        } catch (TException ex) {
            Logger.getLogger(SendEmailNotifyTaskJob.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    // send email if true
    private boolean validateDurationTime (List<T_TransactionReport> tranxs) {
        SimpleDateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        for (T_TransactionReport tranx : tranxs) {
            long lTime = 0;
            try {
                lTime = df.parse(tranx.getTime()).getTime();
            } catch (ParseException ex) {
                ex.getMessage();
            }
            // CHECK DURATION TIME
            if (System.currentTimeMillis() - lTime < Configuration.EMAIL_DURATION) {
                return true;
            }
        }
        
        return false;
    }
        
    public void sendNotify(String agent, String rootCause) {
        try {
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", Configuration.EMAIL_HOST);
            properties.setProperty("mail.smtp.port", String.valueOf(Configuration.EMAIL_PORT));
            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("notifycation@zing.vn", "Notification From BankGateway", "utf-8"));

            message.addRecipients(Message.RecipientType.TO, Configuration.EMAIL_LIST);
            message.addRecipients(Message.RecipientType.CC, "lentd@vng.com.vn, vinhcq@vng.com.vn");

            message.setSubject("Can not conect to webservice of " + agent);
            message.setText("Can not conect to webservice of " + agent + " because of Network Error. \n Please correct it and use Admintool to reset the notification.");
            Transport.send(message);
        } catch (Exception ex) {
        }
    }
}
