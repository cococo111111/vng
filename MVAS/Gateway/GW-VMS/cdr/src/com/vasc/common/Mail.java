// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Mail.java

package com.vasc.common;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail
{

    public Mail()
    {
    }

    public static boolean Send(String from, String name, String to, String subject, String text)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setHeader("X-Priority", "1 (Highest)");
        msg.setHeader("X-MSMail-Priority", "High");
        msg.setHeader("Sensitivity", "Company-Confidential");
        msg.setHeader("Importance", "High");
        msg.setFrom(new InternetAddress(from, name));
        msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        MimeMultipart mp = new MimeMultipart();
        BodyPart tp = new MimeBodyPart();
        tp.setText(text);
        mp.addBodyPart(tp);
        tp = new MimeBodyPart();
        tp.setContent(text, "text/html");
        mp.addBodyPart(tp);
        mp.setSubType("alternative");
        msg.setContent(mp);
        Transport.send(msg);
        return true;
        MessagingException ex;
        ex;
        ex.printStackTrace();
        return false;
        UnsupportedEncodingException ex;
        ex;
        ex.printStackTrace();
        return false;
    }

    public boolean SendMailwithAttachment(String from, String name, String to, String subject, String text, String filename)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        Session session = Session.getInstance(props, null);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from, name));
        message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(text, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        javax.activation.DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        Transport.send(message);
        return true;
        MessagingException ex;
        ex;
        ex.printStackTrace();
        return false;
        UnsupportedEncodingException ex;
        ex;
        ex.printStackTrace();
        return false;
    }

    public static void main(String args[])
    {
        Mail mail = new Mail();
        mail;
        sendMsg2Mail(smtpHost, username, password, "tuanhn@vasc.com.vn", "trongtho@yahoo.com", "Hello", "Hi !!!");
        break MISSING_BLOCK_LABEL_43;
        Exception ex;
        ex;
        ex.printStackTrace();
        return;
    }

    public static void sendMsg2Mail(String MAIL_SERVER, String MAIL_USER, String MAIL_PWD, String FROM_ADDRESS, String TO_ADDRESS, String strBody, String strSubject)
        throws Exception
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_SERVER);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "120000");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("X-Priority", "1 (Highest)");
        message.setHeader("X-MSMail-Priority", "High");
        message.setHeader("Sensitivity", "Company-Confidential");
        message.setHeader("Importance", "High");
        message.setFrom(new InternetAddress(FROM_ADDRESS, "AloFun WebMaster"));
        message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(TO_ADDRESS));
        message.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress("hdquy@vasc.com.vn"));
        message.setSubject(strSubject);
        MimeMultipart mp = new MimeMultipart();
        BodyPart tp = new MimeBodyPart();
        tp.setContent(strBody, "text/html");
        mp.addBodyPart(tp);
        mp.setSubType("alternative");
        message.setContent(mp);
        Transport transport = session.getTransport("smtp");
        transport.connect(MAIL_SERVER, MAIL_USER, MAIL_PWD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    static String password = "lyduc";
    static String smtpHost = "smtp.vasc.com.vn";
    static String username = "trongtho";

}
