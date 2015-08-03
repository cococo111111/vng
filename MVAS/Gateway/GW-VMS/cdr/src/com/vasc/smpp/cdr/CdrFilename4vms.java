// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CdrFilename4vms.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import com.vasc.common.FileTool;
import java.io.*;

public class CdrFilename4vms
{

    public CdrFilename4vms()
    {
        fileTool = new FileTool();
    }

    public static synchronized String getCurrentFilename()
    {
        String filename;
        fileTool;
        byte buffer[] = FileTool.readFile(FILE_STORE_LAST_CDR_FILENAME);
        filename = new String(buffer);
        return filename;
        IOException e;
        e;
        System.out.println("CdrFilename4vms: " + e.getMessage());
        return null;
    }

    public static synchronized String getCurrentFilenameSFONE()
    {
        String filename;
        fileTool;
        byte buffer[] = FileTool.readFile(FILE_STORE_LAST_CDR_FILENAME);
        filename = new String(buffer);
        return filename;
        IOException e;
        e;
        System.out.println("CdrFilename4SFONE: " + e.getMessage());
        return null;
    }

    public static synchronized String getCurrentFilenameVMS()
    {
        String filename;
        fileTool;
        byte buffer[] = FileTool.readFile(FILE_STORE_LAST_CDR_FILENAME_VMS);
        filename = new String(buffer);
        return filename;
        IOException e;
        e;
        System.out.println("CdrFilename4vms: " + e.getMessage());
        return null;
    }

    public static synchronized String getNewFilename()
    {
        String curr_filename = getCurrentFilename();
        String new_filename = null;
        String new_nnnn = null;
        if(curr_filename == null || curr_filename.length() != 21 || !curr_filename.startsWith("VASC") || !curr_filename.endsWith(".bin"))
        {
            new_nnnn = "0001";
        } else
        {
            String curr_date = curr_filename.substring(4, 12);
            int curr_nnnn = Integer.parseInt(curr_filename.substring(13, 17));
            int next_nnnn = 0;
            if(curr_nnnn == 9999)
                next_nnnn = 1;
            else
                next_nnnn = curr_nnnn + 1;
            new_nnnn = String.valueOf(next_nnnn);
            int leftZero = 4 - new_nnnn.length();
            for(int i = 0; i < leftZero; i++)
                new_nnnn = "0" + new_nnnn;

        }
        java.sql.Timestamp ts = DateProc.createTimestamp();
        new_filename = "VASC" + DateProc.Timestamp2YYYYMMDD(ts) + "_" + new_nnnn + ".bin";
        return new_filename;
    }

    public static synchronized String getNewFilename8x99()
    {
        String curr_filename = getCurrentFilename();
        String new_filename = null;
        String new_nnnn = null;
        if(curr_filename == null || curr_filename.length() != 22 || !curr_filename.startsWith("VASC_") || !curr_filename.endsWith(".cdr"))
        {
            new_nnnn = "0001";
        } else
        {
            String curr_date = curr_filename.substring(5, 13);
            int curr_nnnn = Integer.parseInt(curr_filename.substring(14, 18));
            int next_nnnn = 0;
            if(curr_nnnn == 9999)
                next_nnnn = 1;
            else
                next_nnnn = curr_nnnn + 1;
            new_nnnn = String.valueOf(next_nnnn);
            int leftZero = 4 - new_nnnn.length();
            for(int i = 0; i < leftZero; i++)
                new_nnnn = "0" + new_nnnn;

        }
        java.sql.Timestamp ts = DateProc.createTimestamp();
        new_filename = "VASC_" + DateProc.Timestamp2YYYYMMDD(ts) + "_" + new_nnnn + ".cdr";
        return new_filename;
    }

    public static synchronized String getNewFilename8x99SFONE()
    {
        String curr_filename = getCurrentFilenameSFONE();
        String new_filename = null;
        String new_nnnn = null;
        java.sql.Timestamp ts = DateProc.createTimestamp();
        new_filename = "VASC_" + DateProc.getYYYYMMDDHHMMString(ts) + ".bil";
        return new_filename;
    }

    public static synchronized String getNewFilenameELCOM()
    {
        String curr_filename = getCurrentFilename();
        String new_filename = null;
        String new_nnnn = null;
        java.sql.Timestamp ts = DateProc.createTimestamp();
        String today = DateProc.Timestamp2YYYYMMDD(ts);
        String nowHours = DateProc.Timestamp2HHMM(ts).substring(0, 2);
        if(curr_filename == null || curr_filename.length() != 30 || !curr_filename.startsWith("1010_VASC_") || !curr_filename.endsWith(".txt"))
        {
            new_nnnn = "0001";
        } else
        {
            String curr_date = curr_filename.substring(10, 18);
            String curr_time = curr_filename.substring(19, 21);
            int curr_nnnn = Integer.parseInt(curr_filename.substring(22, 26));
            int next_nnnn = 0;
            if(curr_time.equals(nowHours))
            {
                if(curr_nnnn == 9999)
                    next_nnnn = 1;
                else
                    next_nnnn = curr_nnnn + 1;
            } else
            {
                next_nnnn = 1;
            }
            new_nnnn = String.valueOf(next_nnnn);
            int leftZero = 4 - new_nnnn.length();
            for(int i = 0; i < leftZero; i++)
                new_nnnn = "0" + new_nnnn;

        }
        new_filename = "1001_VASC_" + today + "_" + nowHours + "_" + new_nnnn + ".txt";
        return new_filename;
    }

    public static synchronized String getNewFilenameEVN()
    {
        String curr_filename = getCurrentFilename();
        String new_filename = null;
        String new_nnnn = null;
        java.sql.Timestamp ts = DateProc.createTimestamp();
        String today = DateProc.getYYYYMMDDHHMMString(ts).substring(0, 10);
        if(curr_filename == null || curr_filename.length() != 25 || !curr_filename.startsWith("VASC") || !curr_filename.endsWith(".bil"))
        {
            new_nnnn = "0001";
        } else
        {
            String curr_date = curr_filename.substring(4, 14);
            if(!curr_date.endsWith(today))
            {
                new_nnnn = "0001";
            } else
            {
                int curr_nnnn = Integer.parseInt(curr_filename.substring(17, 21));
                int next_nnnn = 0;
                if(curr_nnnn == 9999)
                    next_nnnn = 1;
                else
                    next_nnnn = curr_nnnn + 1;
                new_nnnn = String.valueOf(next_nnnn);
                int leftZero = 4 - new_nnnn.length();
                for(int i = 0; i < leftZero; i++)
                    new_nnnn = "0" + new_nnnn;

            }
        }
        new_filename = "VASC" + DateProc.getYYYYMMDDHHMMString(ts) + "_" + new_nnnn + ".bil";
        return new_filename;
    }

    public static synchronized String getNewFilenameFTPforVMS()
    {
        String curr_filename = getCurrentFilenameVMS();
        String new_filename = null;
        String new_nnnn = null;
        if(curr_filename == null || curr_filename.length() != 26 || !curr_filename.startsWith("vinagame") || !curr_filename.endsWith(".cdr"))
        {
            new_nnnn = "0000";
        } else
        {
            int curr_nnnn = Integer.parseInt(curr_filename.substring(18, 22));
            int next_nnnn = 0;
            if(curr_nnnn == 9999)
                next_nnnn = 0;
            else
                next_nnnn = curr_nnnn + 1;
            new_nnnn = String.valueOf(next_nnnn);
            int leftZero = 4 - new_nnnn.length();
            for(int i = 0; i < leftZero; i++)
                new_nnnn = "0" + new_nnnn;

        }
        java.sql.Timestamp ts = DateProc.createTimestamp();
        new_filename = "vinagame_" + DateProc.Timestamp2YYYYMMDD(ts) + "_" + new_nnnn + ".cdr";
        return new_filename;
    }

    public static synchronized String getNewFilenameSFONE()
    {
        String new_filename = null;
        java.sql.Timestamp ts = DateProc.createTimestamp();
        new_filename = "VASC_" + DateProc.getYYYYMMDDHHMMString(ts) + ".bil";
        return new_filename;
    }

    public static void main(String args[])
    {
        CdrFilename4vms cdrFname;
        cdrFname = new CdrFilename4vms();
        System.out.println("Curr: " + cdrFname.getCurrentFilename());
        System.out.println("New: " + cdrFname.getNewFilename());
        cdrFname;
        cdrFname;
        setNewFilename(getNewFilename());
        break MISSING_BLOCK_LABEL_84;
        IOException ex;
        ex;
        return;
    }

    public static synchronized void setNewFilename(String name)
        throws IOException
    {
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(FILE_STORE_LAST_CDR_FILENAME, false));
        fout.writeBytes(name);
        fout.flush();
        fout.close();
    }

    public static synchronized void setNewFilenameSFONE(String name)
        throws IOException
    {
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(FILE_STORE_LAST_CDR_FILENAME, false));
        fout.writeBytes(name);
        fout.flush();
        fout.close();
    }

    public static synchronized void setNewFilenameVMS(String name)
        throws IOException
    {
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(FILE_STORE_LAST_CDR_FILENAME_VMS, false));
        fout.writeBytes(name);
        fout.flush();
        fout.close();
    }

    static String FILE_STORE_LAST_CDR_FILENAME = "./lastcdrfileCDR.dat";
    static String FILE_STORE_LAST_CDR_FILENAME_VMS = "./lastcdrfileCDR.dat";
    static FileTool fileTool = null;

}
