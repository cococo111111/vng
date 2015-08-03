/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuartzSchedulerJob;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Task;

/**
 *
 * @author root
 */
public class DailyUpdateTaskJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            SimpleDateFormat dfTask = new SimpleDateFormat("HHmmss");
            T_Task task = new T_Task();
            task.setTaskId(StorageCommonDef.TASK_ID_DAILYUPDATE);
            task.setStatus((short) StorageCommonDef.Task_Status.RUNNING.ordinal());
            task.setStartTime(dfTask.format(new Date()));
            BankingStorageClient.getMainInstance(vng.banking.admin.config.Configuration.STORAGE_HOST, 
                    vng.banking.admin.config.Configuration.STORAGE_PORT).
                    updateTaskEveryDay();

            BankingStorageClient.getMainInstance(vng.banking.admin.config.Configuration.STORAGE_HOST,
                    vng.banking.admin.config.Configuration.STORAGE_PORT).updateTaskStart(task);

            T_Task endTask = new T_Task();
            endTask.setTaskId(StorageCommonDef.TASK_ID_DAILYUPDATE);
            endTask.setStatus((short) StorageCommonDef.Task_Status.SUCCESS.ordinal());
            endTask.setEndTime(dfTask.format(new Date()));
            BankingStorageClient.getMainInstance(vng.banking.admin.config.Configuration.STORAGE_HOST,
                    vng.banking.admin.config.Configuration.STORAGE_PORT).updateTaskStop(endTask);
        } catch (TException ex) {
            Logger.getLogger(DailyUpdateTaskJob.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
