// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ftp2CdrServer.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import com.vasc.common.FileTool;
import com.vasc.ftp.*;
import com.vasc.ftp.io.*;
import com.vasc.smpp.gateway.DBTools;
import com.vasc.smpp.gateway.Preference;
import java.io.*;
import java.util.*;

// Referenced classes of package com.vasc.smpp.cdr:
//            CDRServer, FtpData, Logger, CdrFilename4vms

public class Ftp2CdrServer extends Thread
{

    public Ftp2CdrServer()
    {
        vFiles = new Vector();
        ftp = null;
        fileTool = null;
        foldername = "";
        fileTool = new FileTool();
    }

    public static void main(String arguments[])
        throws Exception
    {
        Ftp2CdrServer sender = new Ftp2CdrServer();
        FtpData.loadProperties("ftp2cdrserver.cfg");
        sender.start();
    }

    public void makeFtp()
        throws IOException
    {
        FtpConnect conn = FtpConnect.newConnect(FtpData.FTP_SERVER);
        conn.setUserName(FtpData.FTP_USER);
        conn.setPassWord(FtpData.FTP_PASSWORD);
        conn.setPathName(FtpData.FTP_PATH);
        Ftp ftp = new Ftp();
        ftp.connect(conn);
        this.ftp = ftp;
    }

    public void run()
    {
        sleepEx(1);
          goto _L1
_L3:
        System.out.println();
        vFiles = fileTool.getAllFiles(new File(FtpData.LOCAL_FOLDER), FtpData.FILE_EXTENSION);
        if(vFiles.size() > 0)
        {
            makeFtp();
            send2FtpServer();
            ftp.disconnect();
            sleepByScheduleTime();
            break MISSING_BLOCK_LABEL_114;
        }
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP ", "No CDR file found, sleep in 1 min");
        DBTools.log_alert("Billing system", "-> ERROR: No CDR file found ! ", 1, 0, "serious", Preference.alert_person);
        break MISSING_BLOCK_LABEL_108;
        Exception e;
        e;
        sleepEx(1);
        continue; /* Loop/switch isn't completed */
        e;
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP ", "Ftp2CdrServer error: " + e.getMessage());
        continue; /* Loop/switch isn't completed */
        InterruptedException e;
        e;
_L1:
        if(CDRServer.running) goto _L3; else goto _L2
_L2:
    }

    public void runftp()
    {
        if(!CDRServer.running)
            break MISSING_BLOCK_LABEL_256;
        vFiles = fileTool.getAllFiles(new File(FtpData.LOCAL_FOLDER), FtpData.FILE_EXTENSION);
        if(vFiles.size() > 0)
        {
            makeFtp();
            String sDateVMS = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "");
            CoFile dir = new FtpFile(ftp.pwd(), ftp);
            CoFile fls[] = dir.listCoFiles();
            if(fls.length > 0)
            {
                for(int n = 0; n < fls.length; n++)
                {
                    if(fls[n].getName() == sDateVMS)
                    {
                        foldername = sDateVMS;
                        break;
                    }
                    ftp.mkdir(sDateVMS);
                    foldername = sDateVMS;
                }

            } else
            {
                ftp.mkdir(sDateVMS);
                foldername = sDateVMS;
            }
            send2FtpServer();
            ftp.disconnect();
            break MISSING_BLOCK_LABEL_214;
        }
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP ", "No CDR file found, sleep in 1 min");
        DBTools.log_alert("Billing system", "-> ERROR: No CDR file found ! ", 1, 0, "serious", Preference.alert_person);
        break MISSING_BLOCK_LABEL_208;
        Exception e;
        e;
        sleepEx(1);
        break MISSING_BLOCK_LABEL_255;
        e;
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP ", "Ftp2CdrServer error: " + e.getMessage());
        return;
    }

    public int send2FtpServer()
    {
        CoFile file;
        String filename;
        int i;
        file = null;
        CoFile to = null;
        filename = "";
        String dest_filename = "";
        String sum_line = "";
        boolean loadResult = false;
        boolean renameFileResult = false;
        i = 0;
          goto _L1
_L3:
        String fileLength;
        filename = ((File)vFiles.elementAt(i)).getName();
        file = new LocalFile(FtpData.LOCAL_FOLDER, filename);
        File f = new File(FtpData.LOCAL_FOLDER + "/" + filename);
        fileLength = String.valueOf(f.length());
        CoFile to = new FtpFile(foldername + "/tmp_" + filename, ftp);
        boolean loadResult = CoLoad.copy(to, file);
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP: " + loadResult);
        boolean renameFileResult = ftp.mv(foldername + "/tmp_" + filename, foldername + "/" + filename);
        if(!loadResult || !renameFileResult)
            break MISSING_BLOCK_LABEL_551;
        String sDateFolder = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "-");
        File dest_dir = new File(FtpData.SENT_FOLDER_VMS + "/" + sDateFolder);
        boolean exists = dest_dir.exists();
        boolean success;
        if(!exists)
            success = dest_dir.mkdirs();
        fileTool;
        FileTool.copy(FtpData.LOCAL_FOLDER + "/" + file.getName(), FtpData.SENT_FOLDER_VMS + "/" + sDateFolder + "/" + file.getName());
        Logger;
        com.vasc.smpp.cdr.Logger.info("backup file: from: " + FtpData.LOCAL_FOLDER + "/" + file.getName() + " to: " + FtpData.SENT_FOLDER_VMS + "/" + sDateFolder + "/" + file.getName());
        file.delete();
        CdrFilename4vms.setNewFilenameVMS(filename);
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP : file name: " + filename + "# filesize: " + fileLength + " bytes");
        break MISSING_BLOCK_LABEL_547;
        IOException ex;
        ex;
        ex.printStackTrace();
        Logger;
        com.vasc.smpp.cdr.Logger.info("FTP", ex.getMessage());
        break MISSING_BLOCK_LABEL_643;
        try
        {
            boolean loadResult;
            boolean renameFileResult;
            if((loadResult = false) && (renameFileResult = true))
                DBTools.log_alert("Billing system", "-> ERROR: Khong ftp duoc file cuoc sang telcos! ", 1, 0, "serious", Preference.alert_person);
            else
            if((loadResult = true) && (renameFileResult = false))
                DBTools.log_alert("Billing system", "-> ERROR: Khong rename duoc file cuoc sau khi ftp ! ", 1, 0, "serious", Preference.alert_person);
            else
                DBTools.log_alert("Billing system", "-> ERROR: Loi ftp file cuoc ! ", 1, 0, "serious", Preference.alert_person);
        }
        catch(Exception e) { }
        i++;
_L1:
        if(i < vFiles.size()) goto _L3; else goto _L2
_L2:
        return vFiles.size();
    }

    private void sleepByScheduleTime()
        throws InterruptedException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar _tmp = calendar;
        int currHour = calendar.get(11);
        if((24 - currHour) * 60 < 2 * FtpData.SCHEDULE_TIME)
            Thread.sleep((FtpData.SCHEDULE_TIME + 1) * 60000);
        else
            Thread.sleep(FtpData.SCHEDULE_TIME * 60000);
    }

    private void sleepEx(int minute)
    {
        Thread.sleep(minute * 60000);
        break MISSING_BLOCK_LABEL_16;
        Exception ex;
        ex;
        return;
    }

    private Logger Logger;
    private FileTool fileTool;
    private String foldername;
    private Ftp ftp;
    private Vector vFiles;
}
