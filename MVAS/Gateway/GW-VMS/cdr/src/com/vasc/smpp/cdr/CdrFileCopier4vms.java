// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CdrFileCopier4vms.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import com.vasc.common.FileTool;
import com.vasc.smpp.gateway.Preference;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package com.vasc.smpp.cdr:
//            FtpData, CdrFilename4vms, CDRServer

public class CdrFileCopier4vms extends Thread
{

    public CdrFileCopier4vms()
    {
        vFiles = new Vector();
        fileTool = null;
        fileTool = new FileTool();
    }

    private boolean move2Sendout()
    {
        int i = 0;
          goto _L1
_L3:
        File source_file;
        File dest_file;
        File vms_file;
        source_file = (File)vFiles.elementAt(i);
        String sDateFolder = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "-");
        String sDateVMS = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "");
        File dest_dir = new File(Preference.cdrsentFolder + "\\" + sDateFolder);
        File vms_dir = new File(VMS_FOLDER + "\\" + sDateVMS);
        boolean exists = dest_dir.exists();
        if(!exists)
            dest_dir.mkdirs();
        exists = vms_dir.exists();
        if(!exists)
            vms_dir.mkdirs();
        dest_file = new File(Preference.cdrsentFolder + "\\" + sDateFolder + "\\" + source_file.getName());
        vms_file = null;
        if("Y".equals(FtpData.IS_8X))
        {
            if("SFONE".equals(Preference.mobileOperator))
            {
                vms_file = new File(VMS_FOLDER + "\\" + source_file.getName());
                System.out.println("Test moving>>SFONE");
            } else
            {
                vms_file = new File(VMS_FOLDER + "\\" + sDateVMS + "\\" + source_file.getName());
            }
        } else
        {
            vms_file = new File(VMS_FOLDER + "\\" + source_file.getName());
        }
        long time = System.currentTimeMillis();
        System.out.print(DateProc.getDateTime24hString(new Timestamp(time)));
        System.out.print("  moving file " + source_file + "...");
        fileTool;
        FileTool.move(source_file, dest_file);
        fileTool;
        FileTool.copy(dest_file, vms_file);
        System.out.println(" OK!");
        break MISSING_BLOCK_LABEL_474;
        IOException e;
        e;
        System.out.println("CdrFileCopy4vms.move2Sendout error: " + e.getMessage());
        return false;
        i++;
_L1:
        if(i < vFiles.size()) goto _L3; else goto _L2
_L2:
        return true;
    }

    private boolean move2SendoutVMS()
    {
        int i = 0;
          goto _L1
_L3:
        File source_file;
        File dest_file;
        File vms_file;
        source_file = (File)vFiles.elementAt(i);
        String sDateFolder = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "-");
        String sDateVMS = DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "");
        File dest_dir = new File(Preference.cdrsentFolder + "\\" + sDateFolder);
        File vms_dir = new File(VMS_FOLDER + "\\" + sDateVMS);
        boolean exists = dest_dir.exists();
        if(!exists)
            dest_dir.mkdirs();
        exists = vms_dir.exists();
        if(!exists)
            vms_dir.mkdirs();
        dest_file = new File(Preference.cdrsentFolder + "\\" + sDateFolder + "\\" + source_file.getName());
        vms_file = null;
        if("Y".equals(FtpData.IS_8X))
        {
            if("SFONE".equals(Preference.mobileOperator))
            {
                vms_file = new File(VMS_FOLDER + "\\" + source_file.getName());
                System.out.println("Test moving>>SFONE");
            } else
            {
                vms_file = new File(VMS_FOLDER + "\\" + sDateVMS + "\\" + source_file.getName());
            }
        } else
        {
            vms_file = new File(VMS_FOLDER + "\\" + source_file.getName());
        }
        long time = System.currentTimeMillis();
        System.out.print(DateProc.getDateTime24hString(new Timestamp(time)));
        System.out.print("  moving file " + source_file + "...");
        fileTool;
        FileTool.moveVMS(source_file, dest_file);
        fileTool;
        FileTool.copy(dest_file, vms_file);
        System.out.println(" OK!");
        break MISSING_BLOCK_LABEL_474;
        IOException e;
        e;
        System.out.println("CdrFileCopy4vms.move2Sendout error: " + e.getMessage());
        return false;
        i++;
_L1:
        if(i < vFiles.size()) goto _L3; else goto _L2
_L2:
        return true;
    }

    public void run()
    {
        System.out.println("CDRFileCopier started");
        sleepEx(1);
          goto _L1
_L3:
        System.out.println();
        if("Y".equals(FtpData.IS_8X))
        {
            if("SFONE".equals(Preference.mobileOperator))
            {
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_8X);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99SFONE());
                    move2Sendout();
                    sleep10Minutes();
                } else
                {
                    Thread.sleep(10000L);
                }
            } else
            {
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_8X);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99());
                    move2Sendout();
                    sleep10Minutes();
                } else
                {
                    Thread.sleep(10000L);
                }
            }
        } else
        if("SFONE".equals(Preference.mobileOperator))
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_SFONE);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilenameSFONE());
                move2Sendout();
                sleep10Minutes();
            } else
            {
                Thread.sleep(10000L);
            }
        } else
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename());
                move2Sendout();
                sleep10Minutes();
            } else
            {
                Thread.sleep(10000L);
            }
        }
        continue; /* Loop/switch isn't completed */
        InterruptedException e;
        e;
        continue; /* Loop/switch isn't completed */
        IOException ex;
        ex;
        System.out.println("CdrFileCopier4vms.run(): " + ex.getMessage());
        Thread.sleep(10000L);
        break MISSING_BLOCK_LABEL_364;
        InterruptedException e;
        e;
        continue; /* Loop/switch isn't completed */
        e;
        System.out.println("CdrFileCopy4vms error: " + e.getMessage());
_L1:
        if(CDRServer.running) goto _L3; else goto _L2
_L2:
    }

    public void runcopy()
    {
        System.out.println("CDRFileCopier started");
        if(!CDRServer.running)
            break MISSING_BLOCK_LABEL_356;
        System.out.println();
        if("Y".equals(FtpData.IS_8X))
        {
            if("SFONE".equals(Preference.mobileOperator))
            {
                System.out.println("SFONE");
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_SFONE);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99SFONE());
                    move2Sendout();
                    System.out.println(">>SFONE");
                }
            } else
            {
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_8X);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99());
                    move2Sendout();
                }
            }
        } else
        if("SFONE".equals(Preference.mobileOperator))
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_SFONE);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilenameSFONE());
                move2Sendout();
            }
        } else
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename());
                move2Sendout();
            }
        }
        break MISSING_BLOCK_LABEL_355;
        IOException ex;
        ex;
        System.out.println("CdrFileCopier4vms.run(): " + ex.getMessage());
        Thread.sleep(10000L);
        break MISSING_BLOCK_LABEL_318;
        InterruptedException e;
        e;
        break MISSING_BLOCK_LABEL_355;
        e;
        System.out.println("CdrFileCopy4vms error: " + e.getMessage());
        return;
    }

    public void runcopyVMS()
    {
        System.out.println("CDRFileCopier started");
        if(!CDRServer.running)
            break MISSING_BLOCK_LABEL_356;
        System.out.println();
        if("Y".equals(FtpData.IS_8X))
        {
            if("SFONE".equals(Preference.mobileOperator))
            {
                System.out.println("SFONE");
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_SFONE);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99SFONE());
                    move2Sendout();
                    System.out.println(">>SFONE");
                }
            } else
            {
                vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_8X);
                if(vFiles.size() > 0)
                {
                    CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename8x99());
                    move2Sendout();
                }
            }
        } else
        if("SFONE".equals(Preference.mobileOperator))
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION_SFONE);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilenameSFONE());
                move2Sendout();
            }
        } else
        {
            vFiles = fileTool.getAllFiles(new File(Preference.cdroutFolder), FILE_EXTENSION);
            if(vFiles.size() > 0)
            {
                CdrFilename4vms.setNewFilename(CdrFilename4vms.getNewFilename());
                move2SendoutVMS();
            }
        }
        break MISSING_BLOCK_LABEL_355;
        IOException ex;
        ex;
        System.out.println("CdrFileCopier4vms.run(): " + ex.getMessage());
        Thread.sleep(10000L);
        break MISSING_BLOCK_LABEL_318;
        InterruptedException e;
        e;
        break MISSING_BLOCK_LABEL_355;
        e;
        System.out.println("CdrFileCopy4vms error: " + e.getMessage());
        return;
    }

    private void sleep10Minutes()
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

    static String FILE_EXTENSION = ".cdr";
    static String FILE_EXTENSION_8X = ".cdr";
    static String FILE_EXTENSION_SFONE = ".bil";
    static String VMS_FOLDER = "..\\CDRVMS";
    private FileTool fileTool;
    private Vector vFiles;

}
