// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendMail.java

package com.vmg.smpp.gateway;

import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger

public class SendMail
{
    class SendThread extends Thread
    {

        public void run()
        {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", SendMail.mxServer);
            Session session = Session.getDefaultInstance(props, null);
            try
            {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(fromAddress));
                msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
                msg.setSubject(subject);
                msg.setHeader("X-Mailer", "JavaMail");
                if(mbp != null)
                {
                    MimeBodyPart mbp2 = new MimeBodyPart();
                    mbp2.setText(text);
                    mp.addBodyPart(mbp2);
                    msg.setContent(mp);
                } else
                {
                    msg.setText(text);
                }
                Transport.send(msg);
            }
            catch(AddressException ae)
            {
                Logger.printStackTrace(ae);
            }
            catch(MessagingException me)
            {
                Logger.printStackTrace(me);
            }
        }

        SendThread()
        {
        }
    }


    public SendMail(Logger logger, String mxServer, String toAddress, String fromAddress, String subject, String text)
    {
        mbp = null;
        mp = new MimeMultipart();
        this.logger = null;
        mxServer = mxServer;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.text = text;
        this.logger = logger;
    }

    public void attach(File file)
    {
        try
        {
            mbp = new MimeBodyPart();
            mbp.setFileName(file.getName());
            mbp.setDataHandler(new DataHandler(new FileDataSource(file)));
            mp.addBodyPart(mbp);
        }
        catch(MessagingException me)
        {
            Logger.printStackTrace(me);
        }
    }

    public void send()
    {
        (new SendThread()).start();
    }

    public String nslookup(String domain)
    {
        String mailserver = null;
        try
        {
            Process p = Runtime.getRuntime().exec("nslookup -type=mx " + domain);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            boolean gotMxLine = false;
            String line = null;
            String token = null;
            while((line = br.readLine()) != null) 
            {
                gotMxLine = false;
                for(StringTokenizer st = new StringTokenizer(line); st.hasMoreTokens();)
                {
                    token = st.nextToken();
                    if(token.equals("exchanger"))
                        gotMxLine = true;
                    if(gotMxLine)
                        mailserver = token;
                }

            }
        }
        catch(IOException ioe)
        {
            Logger.printStackTrace(ioe);
            return null;
        }
        System.out.println("Mail Server to use is :: " + mailserver);
        return mailserver;
    }

    public static void main()
    {
        System.out.println("AAA:" + "0959099".substring(4));
    }

    static String mxServer;
    String toAddress;
    String fromAddress;
    String subject;
    String text;
    MimeBodyPart mbp;
    Multipart mp;
    Logger logger;
}
