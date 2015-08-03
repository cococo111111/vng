/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import com.vng.jcore.cache.lruexpire.LruExpireCache;
import com.vng.jcore.common.Config;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author nghidv
 */
public class SendMail {

    private static final Logger log = Logger.getLogger(SendMail.class);
    private static final int expired = 300; //300 seconds
    private static final int connectTimeout = 20000; //milisecs
    private static final LruExpireCache<String, String> lruCache = new LruExpireCache(100);
    private static final String sender = Config.getParam("smtpd", "sender");
    private static final String senderName = Config.getParam("smtpd", "senderName");
    private static final String replyto = Config.getParam("smtpd", "replyto");
    private final String contactList = Config.getParam("smtpd", "contacts");

    //dump step to send email
    private static final boolean isSMTPDebug = false;

    public static boolean send(String receiver, String receiverName, String subject, String body) {

        int port = 0;
        String host = null;
        String username = null;
        String password = null;
        host = Config.getParam("smtpd", "host");
        port = Integer.parseInt(Config.getParam("smtpd", "port"));
        username = Config.getParam("smptd", "username");
        password = Config.getParam("smptd", "password");

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        //set connect to mail server timeout
        props.put("mail.smtp.connectiontimeout", connectTimeout);
        //set send email timeout
        props.put("mail.smtp.timeout", connectTimeout);
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.username", username);
            props.put("mail.smtp.password", password);
        } else {
            props.put("mail.smtp.auth", "false");
        }
        if (port > 25) {
            props.put("mail.smtp.starttls.enable", "true");
        } else {
            props.put("mail.smtp.starttls.enable", "false");
        }
//        log.info(host+"-"+port+"-"+username+"-"+password);
        try {
            Session session = Session.getInstance(props, null);

            //debug request
            session.setDebug(isSMTPDebug);

            // — Create a new message –
            Message msg = new MimeMessage(session);
            // — Set the FROM and TO fields –
            if (receiverName == null) {
                receiverName = "";
            }
            msg.setFrom(new InternetAddress(sender, senderName));
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver, receiverName));
            msg.setReplyTo(new javax.mail.Address[]{new javax.mail.internet.InternetAddress(replyto)});
            // — Set the subject and body text –
            msg.setSubject(subject);
            msg.setContent(body, "text/html; charset=utf-8");
            // Now set the actual message
//            msg.setText(body);
            // — Set some other header information –
//            msg.setHeader("X-Mailer", "ZingMeMail");
            msg.setHeader("List-Unsubscribe", "<mailto:" + replyto + ">");
            //1: hight, 3:medium, 5:low
            msg.setHeader("X-Priority", "5");
            msg.setSentDate(new Date());
            msg.saveChanges();
            if (port == 25 && (username == null || password == null)) {
                // — Send the message –                
                Transport.send(msg);
            } else {
                //ssl 
                Transport transport = session.getTransport("smtp");
                transport.connect(host, username, password);

                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
            }
            return true;
        } catch (UnsupportedEncodingException | MessagingException e) {
            java.util.logging.Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, e);
            log.error("Error at sendZMail. " + e.getMessage(), e);
//            System.out.println(e.getMessage());
        }
        return false;

    }

    public void sendMail(String subject, String body) {
        String[] contacts = contactList.split(",");
        if (contacts.length > 0) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(Arrays.asList(contacts));
            if (arrayList != null && !arrayList.isEmpty()) {
                for (String uid : arrayList) {
                    subject = Config.getParam("smtpd", "subject") + " " + subject;
                    body = "Hi " + uid + " ,</br></br>Message: " + body + "</br></br>Thanks & best regards.";
                    send(uid, uid, subject, body);
                    log.info("Pushed notify to " + uid + ": " + body);
                }
            }
        }

    }
}
