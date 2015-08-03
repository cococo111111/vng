/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.dailyreport;

import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.util.DateUtil;

/**
 *
 * @author root
 */
public class DailyReportMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long monitor_start = System.currentTimeMillis();

        String config = System.getProperty("config", "");

        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/dailyreport.config", "appActions");

        boolean isDeleteOld = false;
        if (args == null || args.length < 3) {
            return;
        }

        String appID = args[0];
        String path = args[1];
        if (path.compareToIgnoreCase("default") == 0) {
            path = "";
        }
        int mode = Integer.parseInt(args[2]);

        DailyReportModel model = new DailyReportModel(path, mode);

        String startTime = "", endTime = "";

        if (args.length == 3) { // Day is NOT input, get currentDay
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            startTime = DateUtil.formatDate(cal.getTime(), DateUtil.DEFAULT_PATTERN);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            endTime = DateUtil.formatDate(cal.getTime(), DateUtil.DEFAULT_PATTERN);
        } else { // day is input
            if (args.length == 4) {
                startTime = args[3] + START_OF_DATE;
                endTime = args[3] + END_OF_DATE;
            } else {
                startTime = args[3] + START_OF_DATE;
                endTime = args[4] + END_OF_DATE;
            }
            if (!DailyReportModel.checkValidTime(startTime) || !DailyReportModel.checkValidTime(endTime)) {
                return;
            }
            
            isDeleteOld = true; // just deleted for old day
        }

        if (appID.compareToIgnoreCase("all") == 0) {
            String msg = "start export: all-app from " + startTime + " to " + endTime;
            log.info(msg);
            System.out.println(msg);
            model.exportDataAll(startTime, endTime, isDeleteOld);
            long monitor_end = System.currentTimeMillis();
            msg = "export: all-app from " + startTime + " to " + endTime + " TAKE " + String.valueOf(monitor_end - monitor_start) + " ms";
            log.info(msg);
            System.out.println(msg);
        } else {
            if (DailyReportModel.checkAppExists(appID)) {
                String msg = "start export: " + appID + "from " + startTime + " to " + endTime;
                log.info(msg);
                System.out.println(msg);
                model.exportData(appID, startTime, endTime, false, isDeleteOld);
                long monitor_end = System.currentTimeMillis();
                msg = "export: " + appID + "from " + startTime + " to " + endTime + " TAKE " + String.valueOf(monitor_end - monitor_start) + " ms";
                log.info(msg);
                System.out.println(msg);
            }
        }

    }
    private static final String START_OF_DATE = " 00:00:00";
    private static final String END_OF_DATE = " 23:59:59";
    private static final Logger log = Logger.getLogger("appActions");
}
