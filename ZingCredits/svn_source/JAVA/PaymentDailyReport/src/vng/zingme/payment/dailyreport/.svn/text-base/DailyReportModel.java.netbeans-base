/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.dailyreport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.paymentreport.PaymentReportModel;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.util.DateUtil;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class DailyReportModel {

    public DailyReportModel(String dataPath, int mode) {
        if (dataPath != null && !dataPath.equals("")) {
            path = dataPath;
        }
        try {
            (new File(path)).mkdirs();
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        if (mode == EXPORT_DETAIL || mode == EXPORT_SUMMARY) {
            this.mode = mode;
        }
    }

    public void exportDataAll(String startTime, String endTime, boolean isDeleteOld) {
        try {
            List<T_AppInfo> allAppInfo = appServiceHandler.getAllAppInfo();
            T_ReportSummary summaryAll = new T_ReportSummary("all", startTime, endTime, 0, 0, 0, 0, 0, 0, 0);
            for (T_AppInfo t_AppInfo : allAppInfo) {
                T_ReportSummary summApp = exportData(t_AppInfo.appID, startTime, endTime, true, isDeleteOld);
                summaryAll = summary(summApp, summaryAll);
            }

            if (mode == EXPORT_DETAIL_SUMMARY || mode == EXPORT_SUMMARY) {
                summaryAll.totalDiffUser = setUsers.keySet().size();
                writeSummary(makeFileName("all", endTime, SUMMARY), summaryAll, isDeleteOld);
            }

        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
    }

    public T_ReportSummary exportData(String appID, String startTime, String endTime, boolean callByAll, boolean isDeleteOld) {
        T_ReportSummary res = new T_ReportSummary(appID, startTime, endTime, 0, 0, 0, 0, 0, 0, 0);

        List<T_ReportTransaction> transByApp = new LinkedList<T_ReportTransaction>();
        LinkedHashMap<Integer, Byte> setUsersOnApp = new LinkedHashMap<Integer, Byte>();

        long monitor_start = System.currentTimeMillis();
        int hope_count = 5;
        while (hope_count > 0 && transByApp.size() <= 0) {
            try {
                boolean isPushMoneyApp = false;
                if (appID.equals("admin") || appID.equals(CommonDef.ZING_PAY_ID)) {
                    isPushMoneyApp = true;
                }
                transByApp = _reportModel.getListTransaction(appID, startTime, endTime, res, setUsersOnApp, isPushMoneyApp);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }

        if (transByApp != null && transByApp.size() > 0) {

            //monitor-here
            long monitor_end = System.currentTimeMillis();
            String msg = appID + ": " + transByApp.size() + " row(s); query in " + String.valueOf(monitor_end - monitor_start) + " ms";
            log.info(msg);
            System.out.println(msg);

            if ((mode == EXPORT_DETAIL_SUMMARY || mode == EXPORT_DETAIL)) {

                writeTransaction(makeFileName(appID, endTime, ""), transByApp, isDeleteOld);

            }

            if (mode == EXPORT_DETAIL_SUMMARY || mode == EXPORT_SUMMARY) {
                res.totalDiffUser = setUsersOnApp.keySet().size();
                writeSummary(makeFileName(appID, endTime, SUMMARY), res, isDeleteOld);

                if (callByAll) {
                    for (Integer key : setUsersOnApp.keySet()) {
                        setUsers.put(key, Byte.MIN_VALUE);
                    }
                }

            }

        }

        return res;
    }

    private String makeSuffix(String dateTime) {
        return dateTime.substring(0, 10);
    }

    private String transactionToString(T_ReportTransaction tranx) {
        StringBuilder buffer = new StringBuilder();

        buffer.append(tranx.agentID).append(SEPARATE);

        if (tranx.agentID.equals(CommonDef.ZING_PAY_ID)) {
            buffer.append(tranx.userName).append(SEPARATE).append(tranx.refID).append(SEPARATE).append(tranx.amount);
        } else {
            Date date = new Date(tranx.txTime * (long) CommonDef.MILISECINSEC);
            String sTime = DateUtil.formatDate(date, DateUtil.DEFAULT_PATTERN);

            buffer.append(tranx.userID).append(SEPARATE).append(tranx.refID).append(SEPARATE).append(tranx.amount).append(SEPARATE).append(tranx.txID).append(SEPARATE).append(sTime).append(SEPARATE).append(tranx.itemIDs).append(SEPARATE).append("\"" + tranx.itemNames + "\"").append(SEPARATE).append(tranx.itemPrices).append(SEPARATE).append(tranx.itemQuantities).append(SEPARATE).append(tranx.resultCode);
            /*if (tranx.message != null && !tranx.message.equals("")) {
            buffer.append(SEPARATE).append(tranx.message);
            }*/
        }

        return buffer.toString();
    }

    private String summaryToString(T_ReportSummary summary) {
        StringBuilder buffer = new StringBuilder();

        // buffer.append(summary.agentID).append(SEPARATE).append(summary.startTime).append(SEPARATE).append(summary.endTime).append(SEPARATE).append(summary.totalTransaction).append(SEPARATE).append(summary.totalTransactionSuccess).append(SEPARATE).append(summary.totalTransactionFail).append(SEPARATE).append(summary.totalTransactionTimeOut).append(SEPARATE).append(summary.totalTransactionNetError).append(SEPARATE).append(summary.totalMoney).append(SEPARATE).append(summary.totalDiffUser);
        String day = summary.endTime.substring(0, 10);
        buffer.append(summary.agentID).append(SEPARATE).append(day).append(SEPARATE).append(summary.totalTransaction).append(SEPARATE).append(summary.totalTransactionSuccess).append(SEPARATE).append(summary.totalTransactionFail).append(SEPARATE).append(summary.totalTransactionTimeOut).append(SEPARATE).append(summary.totalTransactionNetError).append(SEPARATE).append(summary.totalMoney).append(SEPARATE).append(summary.totalDiffUser);

        return buffer.toString();
    }

    private T_ReportSummary summary(T_ReportSummary summApp, T_ReportSummary summ) {
        summ.totalTransaction += summApp.totalTransaction;
        summ.totalTransactionSuccess += summApp.totalTransactionSuccess;
        summ.totalTransactionFail += summApp.totalTransactionFail;
        summ.totalMoney += summApp.totalMoney;
        summ.totalTransactionTimeOut += summApp.totalTransactionTimeOut;
        summ.totalTransactionNetError += summApp.totalTransactionNetError;
        // summ.totalDiffUser
        if (summApp.agentID.equals("ZingCreditsPlus")) {
            summ.totalTransactionSuccess = summ.totalTransaction;
        }
        return summ;
    }

    private void writeTransaction(String fileName, List<T_ReportTransaction> lsTrans, boolean isDeletedOld) {
        File tmpFile = new File(fileName);
        if (tmpFile.exists() && !isDeletedOld) {
            log.warn(fileName + " is exists!. writeTransaction stopped.");
            return;
        } else if (isDeletedOld) {
            tmpFile.delete();
        }
        
        Writer output = null;
        try {
            // output = new BufferedWriter(new FileWriter(fileName, true));
            OutputStream out = new FileOutputStream(fileName, true);
            output = new OutputStreamWriter(out, "UTF-8");
            for (T_ReportTransaction t_ReportTransaction : lsTrans) {
                //write line to file
                output.write(transactionToString(t_ReportTransaction));
                output.write("\n");
            }
            output.close();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }

    private void writeSummary(String fileName, T_ReportSummary summary, boolean isDelete) {
        File tmpFile = new File(fileName);
        
        if (tmpFile.exists() && !isDelete) {
            log.warn(fileName + " is exists!. writeSummary stopped.");
            return;
        } else if (isDelete) {
            tmpFile.delete();
        }
        Writer output_summ = null;
        File file_summ = new File(fileName);
        try {
            output_summ = new BufferedWriter(new FileWriter(file_summ, true));
            output_summ.write(summaryToString(summary));
            output_summ.write("\n");
            output_summ.close();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }
    private String path = "/data/zingcredits/exports";
    private static final Logger log = Logger.getLogger("appActions");
    private static AppServiceHandler appServiceHandler = AppServiceHandler.getMainInstance();
    private static LinkedHashMap<Integer, Byte> setUsers = new LinkedHashMap<Integer, Byte>();
    private int mode = EXPORT_DETAIL_SUMMARY;
    private PaymentReportModel _reportModel = new PaymentReportModel();
    private static final int EXPORT_DETAIL_SUMMARY = 0;
    private static final int EXPORT_DETAIL = 1;
    private static final int EXPORT_SUMMARY = 2;
    private static final String SEPARATE = "\t";

    private void makeDir(String dir) {
        try {
            (new File(dir)).mkdirs();
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
    }

    private String makeFileName(String appID, String time, String subname) {
        String subpath = path + File.separator + appID;
        makeDir(subpath);
        String fileName = subpath + File.separator + appID + SEPA + makeSuffix(time);
        if (subname != null && !"".equals(subname)) {
            fileName += SEPA + subname;
        }
        return fileName;
    }
    private static final String SEPA = "_";
    private static final String SUMMARY = "summary";
    private static final int TIME_LENT = 10;
    private static final String TIME_SEPA = "-";
    private static final int MAX_YEAR = 2200;
    private static final int MAX_MONTH = 12;
    private static final int MAX_DAY = 31;
    private static final int MIN_YEAR = 2011;
    private static final int MIN_MONTH = 1;
    private static final int MIN_DAY = 1;

    public static boolean checkValidTime(String time) {
        if (time.length() < TIME_LENT) {
            return false;
        }
        String tmp = time.substring(0, TIME_LENT);
        boolean res = true;
        try {
            String[] split = tmp.split(TIME_SEPA);
            if (split.length != 3) {
                return false;
            }
            if (split[0].length() != 4) {
                return false;
            }
            int year = Integer.parseInt(split[0]);
            if (year < MIN_YEAR || year > MAX_YEAR) {
                return false;
            }
            if (split[1].length() != 2) {
                return false;
            }
            int month = Integer.parseInt(split[1]);
            if (month < MIN_MONTH || month > MAX_MONTH) {
                return false;
            }
            if (split[2].length() != 2) {
                return false;
            }
            int day = Integer.parseInt(split[2]);
            if (day < MIN_DAY || day > MAX_DAY) {
                return false;
            }
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
            res = false;
        }
        return res;
    }

    public static boolean checkAppExists(String appID) {
        try {
            List<T_AppInfo> allAppInfo = appServiceHandler.getAllAppInfo();

            for (T_AppInfo t_AppInfo : allAppInfo) {
                if (t_AppInfo.appID.equals(appID)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
        return false;
    }
}
