package com.vng.zing.credits.report.mail.model;

import com.vng.jcore.common.Config;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class ReportsMonitor {

    private final static Logger _logger = Logger.getLogger(ReportsMonitor.class);
    private final HashMap<String, AppReport> appReports = new HashMap<String, AppReport>();
    private final ArrayList<Report> reports = new ArrayList<Report>();
    private long startTime;
    private long duration;

    public static class Instance extends Thread {

        @Override
        public void run() {
            ReportsMonitor rps = new ReportsMonitor();
            rps.work();
        }
    }

    private ReportsMonitor() {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(System.currentTimeMillis());
        if ("1".equals(Config.getParam("REPORT", "bod"))) {
            cl.set(Calendar.HOUR_OF_DAY, 0);
            cl.set(Calendar.MINUTE, 0);
            cl.set(Calendar.SECOND, 0);
            cl.set(Calendar.MILLISECOND, 30);
        }
        duration = Integer.parseInt(Config.getParam("REPORT", "duration")) * 1000;
        startTime = cl.getTimeInMillis() + duration;
    }

    private void createReports() {
        try {
            QueryBuilder qb = QueryBuilder.getInstance();
            qb.where("active=1");
            ResultSet rs = qb.select(ReportMailingList.TABLE);
            while (rs.next()) {
                Report r = new Report(rs.getString("title"), rs.getString("to"), rs.getString("cc"), rs.getString("bcc"));
                String[] applist = rs.getString("applist").split(",");
                for (String id : applist) {
                    try {
                        String[] ap = id.split("@");
                        ap[0] = ap[0].trim();
                        ap[1] = ap[1].trim();
                        AppReport ar = appReports.get(ap[0]);
                        if (ar == null) {
                            ar = new AppReport(ap[0], ap[1]);
                            appReports.put(ap[0], ar);
                        }
                        r.addReport(ar);
                    } catch (Exception ex) {
                        _logger.error(ex.getMessage(), ex);
                    }
                }
                if (r.getAppCount() > 0) {
                    reports.add(r);
                }
            }
            qb.release();
        } catch (SQLException ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }

    private void sendReports() {
        for (int i = 0; i < reports.size(); ++i) {
            reports.get(i).sendReport();
        }
    }

    private void cleanUp() {
        reports.clear();
        appReports.clear();        
    }

    private void work() {
        do {
            do {
                if (startTime > System.currentTimeMillis()) {
                    try {
                        Thread.sleep(startTime - System.currentTimeMillis());
                    } catch (Exception ex) {
                        _logger.warn(ex.getMessage(), ex);
                    }
                }
            } while (System.currentTimeMillis() < startTime);
            createReports();
            sendReports();
            cleanUp();
            startTime += duration;
        } while (true);
    }
}
