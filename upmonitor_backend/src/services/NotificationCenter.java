/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clients.PushZMeNotify;
import clients.SendMail;
import clients.StorageMySQLCli;
import com.vng.jcore.common.Config;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author locth2
 */
public class NotificationCenter {

    int level = Integer.parseInt(Config.getParam("alert", "max"));
    int interval = Integer.parseInt(Config.getParam("alert", "interval"));
    String smsHour = Config.getParam("alert", "smshour");
    String mailHour = Config.getParam("alert", "mailhour");
    String zmeHour = Config.getParam("alert", "zmehour");
    String ucomZmeContacts = Config.getParam("zmenotify", "contacts");
    String ucomMailContacts = Config.getParam("smtpd", "contacts");
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NotificationCenter.class);
    private static StorageMySQLCli sqlCli = StorageMySQLCli.getInstance();

    public int checkTimeAlert(String min, String max) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date today;
            today = dateFormat.parse(dateFormat.format(new Date()));
            long currTime = System.currentTimeMillis();
            String[] minSplits = min.split(":");
            String[] maxSplits = max.split(":");
            long minTime = today.getTime() + Integer.parseInt(minSplits[0]) * 3600000L + Integer.parseInt(minSplits[1]) * 60000L;
            long maxTime = today.getTime() + Integer.parseInt(maxSplits[0]) * 3600000L + Integer.parseInt(maxSplits[1]) * 60000L;
            if (currTime >= minTime && currTime <= maxTime) {
                return 1;
            }
        } catch (ParseException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return 0;
    }

    public void doNotify(String message, String path) {

        //ZME contacts from config + mysql
        try {
            ArrayList<Integer> zmeContacts = new ArrayList<>();
            //Get from config
            String[] ZmeContactStrings = ucomZmeContacts.split(",");

            if (ZmeContactStrings.length > 0 && ZmeContactStrings != null) {
                for (String contact : ZmeContactStrings) {
                    zmeContacts.add(Integer.parseInt(contact));
                }
            }
            //Get from mysql & add to ZME contacts
            ArrayList<Integer> sqlZmeContacts = sqlCli.getZmeContacts(path);
            if (!sqlZmeContacts.isEmpty() && sqlZmeContacts != null) {
                for (int contact : sqlZmeContacts) {
                    zmeContacts.add(contact);
                }
            }

            if (zmeHour != null && !zmeHour.isEmpty()) {
                String[] zmeHours = zmeHour.split(",");
                if (zmeHours.length > 0) {
                    ArrayList<String> arrayListZmeHour = new ArrayList<>();
                    arrayListZmeHour.addAll(Arrays.asList(zmeHours));
                    if (arrayListZmeHour != null && !arrayListZmeHour.isEmpty()) {
                        for (String time : arrayListZmeHour) {
                            int checkTimeAlert = checkTimeAlert(time.split("-")[0], time.split("-")[1]);
                            if (checkTimeAlert == 1) {
                                PushZMeNotify pushZMeNotify = new PushZMeNotify();
                                pushZMeNotify.pushZMeNotify(message, zmeContacts);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("No contact or wrong format config [contact] for ZME notification." + e.getMessage(), e);
        }

        //Mail contacts from config + mysql
        try {
            ArrayList<String> mailContacts = new ArrayList<>();
            //Get from config
            String[] mailContactStrings = ucomMailContacts.split(",");
            if (mailContactStrings.length > 0 && mailContactStrings != null) {
                mailContacts.addAll(Arrays.asList(mailContactStrings));
            }
            //Mail from mysql & add to ZME contacts
            ArrayList<String> sqlMailContacts = sqlCli.getMailContacts(path);
            if (!sqlMailContacts.isEmpty() && sqlMailContacts != null) {
                for (String contact : sqlMailContacts) {
                    mailContacts.add(contact);
                }
            }

            if (mailHour != null && !mailHour.isEmpty()) {
                String[] mailHours = mailHour.split(",");
                if (mailHours.length > 0) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.addAll(Arrays.asList(mailHours));
                    if (arrayList != null && !arrayList.isEmpty()) {
                        for (String time : arrayList) {
                            int checkTimeAlert = checkTimeAlert(time.split("-")[0], time.split("-")[1]);
                            if (checkTimeAlert == 1) {
                                SendMail sendMail = new SendMail();
                                sendMail.sendMail(message,message);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("No contact or wrong format config [contact] for Mail notification." + e.getMessage(), e);
        }
    }
}
