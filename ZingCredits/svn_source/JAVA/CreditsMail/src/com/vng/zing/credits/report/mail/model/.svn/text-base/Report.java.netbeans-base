package com.vng.zing.credits.report.mail.model;

import com.vng.jcore.common.Config;
import hapax.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class Report {

    final static private Logger _logger = Logger.getLogger(Report.class);
    ArrayList<AppReport> appReports = new ArrayList<AppReport>();
    String title;
    String to;
    String cc;
    String bcc;

    public Report(String title, String to, String cc, String bcc) {
        if (title == null || title.trim().length() == 0) {
            this.title = "Doanh thu từ Ví Zing Me";
        } else {
            this.title = title;
        }
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
    }

    public void addReport(AppReport appReport) {
        appReports.add(appReport);
    }

    public int getAppCount() {
        return appReports.size();
    }

    public void sendReport() {
        try {
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", Config.getParam("SMTP", "host"));
            properties.setProperty("mail.smtp.port", Config.getParam("SMTP", "port"));
            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Config.getParam("SMTP", "sender"), Config.getParam("SMTP", "name"), "utf-8"));

            message.addRecipients(Message.RecipientType.TO, to);
            message.addRecipients(Message.RecipientType.CC, cc);
            message.addRecipients(Message.RecipientType.BCC, bcc);

            message.setSubject(title);
            MimeMultipart multipart = new MimeMultipart("related");
            String content = "";
            for (int i = 0; i < appReports.size(); ++i) {
                BodyPart messageBodyPart = new MimeBodyPart();
                AppReport arp = appReports.get(i);
                content += arp.getData();
                DataSource fds = new FileDataSource(arp.getChartUrl());
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<image" + arp.getAppId() + ">");
                messageBodyPart.setFileName(arp.getChart());
                multipart.addBodyPart(messageBodyPart);
            }
            BodyPart bodyPart = new MimeBodyPart();
            TemplateDataDictionary report = TemplateDictionary.create();
            report.setVariable("content", content);
            TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/credits/report/mail/view/");
            Template template = templateLoader.getTemplate("report");
            bodyPart.setContent(template.renderToString(report), "text/html;charset=utf-8");
            multipart.addBodyPart(bodyPart, 0);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }
}
