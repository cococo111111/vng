package com.vng.zing.credits.report.mail.controller;

import com.vng.zing.credits.report.mail.model.ReportMailingList;
import com.vng.zing.credits.report.mail.model.QueryBuilder;
import com.vng.zing.credits.report.mail.util.json.JSONException;
import com.vng.zing.credits.report.mail.util.json.JSONObject;
import hapax.TemplateDataDictionary;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class AutoReport extends CoreAction {

    private static org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(AutoReport.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (validUser(req, resp)) {
                TemplateDataDictionary dic = getDictionary();
                dic.addSection("LOGGED");
                dic = dic.addSection("MAILINGLIST");
                dic.showSection("mailinglist");
                QueryBuilder qb = new QueryBuilder();
                ResultSet rs = qb.select(ReportMailingList.TABLE);
                String to;
                String cc;
                String bcc;
                TemplateDataDictionary appDic;
                boolean white = true;
                while (rs.next()) {
                    appDic = dic.addSection("REPORT");
                    appDic.setVariable("rp_id", rs.getString("id"));
                    String title = rs.getString("title");
                    String applist = rs.getString("applist");
                    if (title.length() == 0) {
                        appDic.setVariable("title", "Doanh thu từ Ví Zing Me");
                    } else {
                        appDic.setVariable("title", title);
                    }
                    appDic.setVariable("applist", applist.replace(",", "<br/>"));
                    to = rs.getString("to");
                    cc = rs.getString("cc");
                    bcc = rs.getString("bcc");
                    appDic.setVariable("to", to.replace(",", "<br/>"));
                    appDic.setVariable("cc", cc.replace(",", "<br/>"));
                    appDic.setVariable("bcc", bcc.replace(",", "<br/>"));
                    appDic.setVariable("ta_title", title);
                    appDic.setVariable("ta_applist", applist.replace(",", "\n"));
                    appDic.setVariable("ta_to", to.replace(",", "\n"));
                    appDic.setVariable("ta_cc", cc.replace(",", "\n"));
                    appDic.setVariable("ta_bcc", bcc.replace(",", "\n"));
                    if (white) {
                        appDic.setVariable("color", "white");
                    } else {
                        appDic.setVariable("color", "#f0f0f0");
                    }
                    white = !white;
                    if (rs.getBoolean("active")) {
                        appDic.setVariable("active", "checked='checked'");
                    }
                }
                qb.release();
                print(applyTemplate(dic), resp);
            } else {
                print("Access Deny!", resp);
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (validUser(req, resp)) {
                String cmd = req.getParameter("command");
                if ("info".equals(cmd)) {
                    infoAction(req, resp);
                } else if ("update".equals(cmd)) {
                    updateAction(req, resp);
                } else if ("register".equals(cmd)) {
                    registerAction(req, resp);
                }
            } else {
                JSONObject jsML = new JSONObject();
                jsML.put("error", -1);
                jsML.put("message", "Session time out!");
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }

    private void infoAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException, SQLException {
        JSONObject info = new JSONObject();
        QueryBuilder qb = new QueryBuilder();
        qb.where("id=" + qb.quote(req.getParameter("rp_id")));
        ResultSet rs = qb.select(ReportMailingList.TABLE);
        if (rs == null || !rs.next()) {
            info.put("error", -1);
        } else {
            info.put("error", 0);
            info.put("rp_id", rs.getString("id"));
            String title = rs.getString("title");
            String applist = rs.getString("applist");
            if (title.length() == 0) {
                info.put("title", "Doanh thu từ Ví Zing Me");
            } else {
                info.put("title", title);
            }
            String to = rs.getString("to");
            String cc = rs.getString("cc");
            String bcc = rs.getString("bcc");
            info.put("applist", applist.replace(",", "<br/>"));
            info.put("to", to.replace(",", "<br/>"));
            info.put("cc", cc.replace(",", "<br/>"));
            info.put("bcc", bcc.replace(",", "<br/>"));
            info.put("ta_title", title);
            info.put("ta_applist", applist.replace(",", "\n"));
            info.put("ta_to", to.replace(",", "\n"));
            info.put("ta_cc", cc.replace(",", "\n"));
            info.put("ta_bcc", bcc.replace(",", "\n"));
            info.put("active", rs.getBoolean("active"));
        }
        qb.release();
        printJSON(info, resp);
    }

    String checkParams(HttpServletRequest req, ReportMailingList ml) {
        ml.setTitle(req.getParameter("title"));
        ml.setAppList(req.getParameter("applist"));
        String appPattern = "[A-Za-z0-9]+@[^@]+$";
        if (ml.getAppList() == null || ml.getAppList().trim().length() < 1) {
            return "Invalid Application List";
        }
        String applist[] = ml.getAppList().split("\n");
        for (String app : applist) {
            if (!app.matches(appPattern)) {
                return "Invalid Application List";
            }
        }
        ml.setAppList(ml.getAppList().trim().replace("\n", ","));

        String emPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        if (req.getParameter("to") == null || req.getParameter("to").trim().length() == 0) {
            ml.setTo("");
        } else {
            ml.setTo(req.getParameter("to").trim().replace("\n", ","));
            String tos[] = ml.getTo().split(",");
            for (String to : tos) {
                if (!to.trim().matches(emPattern)) {
                    return "Invalid TO list";
                }
            }
        }
        if (req.getParameter("cc") == null || req.getParameter("cc").trim().length() == 0) {
            ml.setCc("");
        } else {
            ml.setCc(req.getParameter("cc").trim().replace("\n", ","));
            String ccs[] = ml.getCc().split(",");
            for (String cc : ccs) {
                if (!cc.trim().matches(emPattern)) {
                    return "Invalid CC list";
                }
            }

        }
        if (req.getParameter("bcc") == null || req.getParameter("bcc").trim().length() == 0) {
            ml.setBcc("");
        } else {
            ml.setBcc(req.getParameter("bcc").trim().replace("\n", ","));
            String bccs[] = ml.getBcc().split(",");
            for (String bcc : bccs) {
                if (!bcc.trim().matches(emPattern)) {
                    return "Invalid BCC list";
                }
            }
        }
        if ("1".equals(req.getParameter("active"))) {
            ml.setActive(true);
        } else {
            ml.setActive(false);
        }
        return null;
    }

    private void updateAction(HttpServletRequest req, HttpServletResponse resp) throws JSONException, IOException {
        ReportMailingList rml = new ReportMailingList();
        String errorMsg = checkParams(req, rml);
        JSONObject jsML = new JSONObject();
        if (errorMsg != null) {
            jsML.put("error", -1);
            jsML.put("message", errorMsg);
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            rml.setId(req.getParameter("rp_id"));
            data.put("`title`", rml.getTitle());
            data.put("`applist`", rml.getAppList());
            data.put("`to`", rml.getTo());
            data.put("`cc`", rml.getCc());
            data.put("`bcc`", rml.getBcc());
            if (rml.isActive()) {
                data.put("`active`", 1);
            } else {
                data.put("`active`", 0);
            }
            QueryBuilder qb = new QueryBuilder();
            qb.where("`id`=" + qb.quote(Integer.parseInt(rml.getId())));
            int eCode = qb.update(ReportMailingList.TABLE, data);
            jsML.put("error", 0);
            jsML.put("reload", 1);
            if (eCode < 0) {
                if (eCode == -1) {
                    errorMsg = "Repport Mailing List's ID does not exist!";
                } else {
                    errorMsg = "DB operation error!";
                }
                jsML.put("error", -1);
                jsML.put("message", errorMsg);
            } else {
                jsML.put("error", 0);
                jsML.put("rp_id", rml.getId());
                if (rml.getTitle().length() == 0) {
                    jsML.put("title", "Doanh thu từ Ví Zing Me");
                } else {
                    jsML.put("title", rml.getTitle());
                }
                jsML.put("applist", rml.getAppList().replace(",", "<br/>"));
                jsML.put("to", rml.getTo().replace(",", "<br/>"));
                jsML.put("cc", rml.getCc().replace(",", "<br/>"));
                jsML.put("bcc", rml.getBcc().replace(",", "<br/>"));
                jsML.put("ta_title", rml.getTitle());
                jsML.put("ta_applist", rml.getAppList().replace(",", "\n"));
                jsML.put("ta_to", rml.getTo().replace(",", "\n"));
                jsML.put("ta_cc", rml.getCc().replace(",", "\n"));
                jsML.put("ta_bcc", rml.getBcc().replace(",", "\n"));
                jsML.put("active", rml.isActive());
                jsML.put("reload", 0);
            }
            qb.release();
        }
        printJSON(jsML, resp);
    }

    private void registerAction(HttpServletRequest req, HttpServletResponse resp) throws JSONException, IOException {
        ReportMailingList rml = new ReportMailingList();
        String errorMsg = checkParams(req, rml);
        JSONObject jsML = new JSONObject();
        if (errorMsg != null) {
            jsML.put("error", -1);
            jsML.put("message", errorMsg);
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("`title`", rml.getTitle());
            data.put("`applist`", rml.getAppList());
            data.put("`to`", rml.getTo());
            data.put("`cc`", rml.getCc());
            data.put("`bcc`", rml.getBcc());
            if (rml.isActive()) {
                data.put("`active`", 1);
            } else {
                data.put("`active`", 0);
            }
            QueryBuilder qb = new QueryBuilder();
            if (qb.insert(ReportMailingList.TABLE, data) < 0) {
                jsML.put("error", -1);
                jsML.put("message", "DB operation error!");
            } else {
                jsML.put("error", 0);
                jsML.put("reload", 1);
            }
            qb.release();
        }
        printJSON(jsML, resp);
    }
}
