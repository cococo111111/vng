/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.banking.admin.servlet;

import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.banking.admin.config.Configuration;
import vng.banking.admin.service.BankingCore;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.thrift.T_TransactionReport;

/**
 *
 * @author tunm
 */
public class StatsServlet extends BankingCore {

        private static Logger _logger = Logger.getLogger(StatsServlet.class);
        private static TemplateDataDictionary dic = TemplateDictionary.create();
        static final String authorize_key = "gc^@uth";
        private static String day = "";
        private static int totalpending = 0;
        private static int totalsuccess = 0;
        private static int totalfailover = 0;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
              ServletException, IOException {
                try {
                        dic.setVariable("name", "vinh ");

                        day = req.getParameter("viewdate");
                        if (day == null || "".equals(day)) {
                                Calendar cal = Calendar.getInstance();
                                DateFormat df = new SimpleDateFormat("MMddyy");
                                day = df.format(cal.getTime());
                        } else {
                                day = formatTime(day);
                        }
                        List<T_TransactionReport> list_tx = new ArrayList<T_TransactionReport>();
                        list_tx = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                              Configuration.STORAGE_PORT).getTranxsWithConfirmStatus(day, (short) 0);
                        totalpending = (list_tx == null) ? 0 : list_tx.size();

                        list_tx = BankingStorageClient.getMainInstance(Configuration.STORAGE_HOST,
                              Configuration.STORAGE_PORT).getTranxsWithConfirmStatus(day, (short) 4);
                        totalsuccess = (list_tx == null) ? 0 : list_tx.size();
                        dic.setVariable("pending", String.valueOf(totalpending));
                        dic.setVariable("success", String.valueOf(totalsuccess));
                        dic.setVariable("failover", String.valueOf(0));


                        dic.setVariable("viewdate", toViewTime(day));
                        print(applyTemplate(dic), resp);

                } catch (Exception ex) {
                        _logger.error(ex.getMessage());
                }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
              ServletException, IOException {
                doGet(req, resp);
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

        private void outAndClose(String content, HttpServletRequest req, HttpServletResponse resp) {
                PrintWriter out = null;
                try {
                        out = resp.getWriter();
                        out.print(content);
                } catch (Exception ex) {
                        _logger.error(ex.getMessage() + " URI=" + req.getRequestURI() + "?" + req.
                              getQueryString(), ex);
                } finally {
                        if (out != null) {
                                out.close();
                        }
                }
        }
}
