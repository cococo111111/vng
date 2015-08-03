/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.banking.admin.servlet;

import com.vng.jcore.common.LogUtil;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.banking.admin.service.BankingCore;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class TranxByConfirmStatusController extends BankingCore {

    private static Logger _logger = Logger.getLogger(TranxByConfirmStatusController.class);
    private static TemplateDataDictionary dic = TemplateDictionary.create();
    private static String day = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            dic.setVariable("name", "vinh ");

            day = request.getParameter("viewdate");
            if (day == null || "".equals(day)) {
                Calendar cal = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat("MMddyy");
                day = df.format(cal.getTime());
            } else {
                day = formatTime(day);
            }
            dic.setVariable("viewdate", toViewTime(day));

            dic.showSection("tranxbyConfirmStatus");
            print(applyTemplate(dic), response);

        } catch (Exception ex) {
            _logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TemplateDataDictionary catDic;
            DateFormat df = new SimpleDateFormat("MMddyy");
            dic.setVariable("name", "vinh ");
            dic.showSection("tranxbyConfirmStatus");
            day = request.getParameter("viewdate");
            short status = Short.parseShort(request.getParameter("status"));
            String bankCode = request.getParameter("bankCode");
            if (day == null || "".equals(day)) {
                Calendar cal = Calendar.getInstance();

                day = df.format(cal.getTime());
            } else {
                day = formatTime(day);
            }
            dic.setVariable("viewdate", toViewTime(day));

            String page = request.getParameter("page");
            if (page == null || "".equals(page) || Integer.parseInt(page) < 1) {
                page = "1";
            }
            dic.setVariable("page", page);
            int i = 1;
            List<T_TransactionReport> tranxs = BankingStorageClient.getMainInstance(vng.banking.admin.config.Configuration.STORAGE_HOST, vng.banking.admin.config.Configuration.STORAGE_PORT).getTranxsWithConfirmStatusAndBankCode(day, status, bankCode);
            if (tranxs != null) {
//                if (!tranxs.isEmpty()) {
//                    dic.showSection("NEWER");
//                }
//                if (!"1".equals(page)) {
//                    dic.showSection("OLDER");
//                }

                for (T_TransactionReport tranx : tranxs) {
                    catDic = dic.addSection("TRANX");
                    catDic.setVariable("class", (i % 2) > 0 ? "hligh1" : "hligh2");
                    catDic.setVariable("i", String.valueOf(i++));
                    catDic.setVariable("time", toDisplayTime(tranx.time));
                    catDic.setVariable("txID", String.valueOf(tranx.txID));
                    catDic.setVariable("txType", String.valueOf(tranx.txType));
                    catDic.setVariable("userName", tranx.userName);
                    catDic.setVariable("agencyCode", tranx.agencyCode);
                    catDic.setVariable("providerCode", tranx.providerCode);
                    catDic.setVariable("refID", tranx.refID);
                    catDic.setVariable("amount", String.valueOf(tranx.amount));
                    catDic.setVariable("clientIP", tranx.clientIP);
                    catDic.setVariable("txStatus", String.valueOf(tranx.txStatus));
                    catDic.setVariable("responseCode", tranx.responseCode);
                    catDic.setVariable("confirmStatus", String.valueOf(tranx.confirmStatus));
                }
            }

            print(applyTemplate(dic), response);
        } catch (Exception ex) {
            _logger.error(LogUtil.stackTrace(ex));
        }
    }

    private static String toViewTime(String inputTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMddyy");
        Date date = df.parse(inputTime);
        DateFormat dfTransxTime = new SimpleDateFormat("yyyy-MM-dd");
        return dfTransxTime.format(date);
    }

    private static String formatTime(String inputTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(inputTime);
        DateFormat dfTransxTime = new SimpleDateFormat("MMddyy");
        return dfTransxTime.format(date);
    }

    private static String toDisplayTime(String inputTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("MMddyyHHmmss");
        Date date = df.parse(inputTime);
        DateFormat dfTransxTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return dfTransxTime.format(date);
    }
}
