/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.banking.admin.servlet;

import QuartzSchedulerJob.DailyUpdateTaskJob;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.JobExecutionException;
import vng.banking.admin.service.BankingCore;
import vng.bankinggateway.common.StorageCommonDef;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_Task;

/**
 *
 * @author sonhoang
 */
public class LogTasksServlet extends BankingCore {

    private static org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(
            LogTasksServlet.class);
    static final String authorize_key = "gc^@uth";
    private static String day = "";
    private static int totalpending = 0;
    private static int totalsuccess = 0;
    private static int totalfailover = 0;
    private static String STA_NEW = "new";
    private static String STA_RUNNING = "running";
    private static String STA_FALSE = " failed";
    private static String STA_SUCCESS = "success";
    private static String STA_CHECKED = "data is updated today";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException, IOException {
        String taskID = req.getParameter("taskId");
        if (StorageCommonDef.TASK_ID_DAILYUPDATE.equals(taskID)) {
            try {
                DailyUpdateTaskJob job = new DailyUpdateTaskJob();
                job.execute(null);
            } catch (JobExecutionException ex) {
                Logger.getLogger(LogTasksServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (StorageCommonDef.TASK_ID_RUN_RECOMFIRM.equals(taskID)) {
            Runtime.getRuntime().exec("/bin/sh /zserver/japps/zingcredits/BankingGateway/runReconfirm HDBANK");
            Runtime.getRuntime().exec("/bin/sh /zserver/japps/zingcredits/BankingGateway/runReconfirm VIETINBANK");
        }
        PrintWriter out = resp.getWriter();
        out.write(taskID);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws
            ServletException,
            IOException {
        httpServletResponse.addHeader("Content-Type", "text/html");

        PrintWriter out = httpServletResponse.getWriter();
        String content;


        try {
            content = this.renderListTaskToday();
            out.println(content);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

    }

    private void prepareData(TemplateDataDictionary listsection, String taskId, String taskName,
            String status, String date, String startTime, String endTime,
            boolean isRerun) {
        listsection.setVariable("TaskId", taskId);
        listsection.setVariable("TaskName", taskName);
        listsection.setVariable("Status", status);
        listsection.setVariable("Date", date);
        listsection.setVariable("StartTime", startTime);
        listsection.setVariable("EndTime", endTime);
        listsection.setVariable("Check", String.valueOf(isRerun ? 1 : 0));
        if (isRerun) {
            listsection.showSection("ReRun");
        }
    }

    private String prepareStatus(int pstatus) {
        String status;
        switch (pstatus) {
            case 0:
                status = STA_NEW;
                break;
            case 1:
                status = STA_RUNNING;
                break;
            case 2:
                status = STA_FALSE;
                break;
            case 3:
                status = STA_SUCCESS;
                break;
            default:
                status = STA_CHECKED;
                break;
        }
        return status;
    }

    private String renderListTaskToday() throws Exception {
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.showSection("logTask");

        List<T_Task> listTask = new ArrayList<T_Task>();

        //  LogTaskConnectionClientImpl.getMainInstance("localhost", 9702).insertTask();//hardcode fixxxx

        listTask = BankingStorageClient.getMainInstance(vng.banking.admin.config.Configuration.STORAGE_HOST, vng.banking.admin.config.Configuration.STORAGE_PORT).getListTaskToday();//hardcode fixxxx
        for (T_Task task : listTask) {
            String strStatus = prepareStatus(task.status);
            TemplateDataDictionary listsection = dic.addSection("list_section");
            prepareData(listsection, task.taskId, task.taskName, strStatus, task.date,
                    task.startTime, task.endTime, task.isReRun);
        }
        String content = applyTemplate(dic);
        return content;
    }
}
