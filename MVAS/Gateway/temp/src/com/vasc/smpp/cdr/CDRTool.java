// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDRTool.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import com.vasc.common.FileTool;
import com.vasc.smpp.gateway.Preference;
import com.vasc.smpp.gateway.SMSData;
import java.io.*;
import java.util.Vector;

// Referenced classes of package com.vasc.smpp.cdr:
//            CDR, CdrFilename4vms

public class CDRTool
{

    public CDRTool()
    {
    }

    public static synchronized void add2CDRFile(CDR cdr)
    {
        String fileCDR;
        fileCDR = null;
        if(!"GPC".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_342;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".vasc");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = DateProc.getYYYYMMDDHHMMString(ts) + ".vasc";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        fout.writeBytes("M\t");
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        fout.writeBytes(sId + "\t");
        fout.writeBytes(cdr.getUserId() + "\t");
        fout.writeBytes("20" + cdr.getSubmitDate() + "\t");
        fout.writeBytes("D\t");
        fout.writeBytes("20" + cdr.getDoneDate() + "\t");
        fout.writeBytes("4\t");
        fout.writeBytes("MAPMO.1:1\t");
        fout.writeBytes("0\t");
        fout.writeBytes("MAP.1:1\t");
        fout.writeBytes("1/160\t");
        fout.writeBytes("1\n");
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_338;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        break MISSING_BLOCK_LABEL_1166;
        if(!"VMS".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_803;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bin");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilename();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        fout.writeBytes(cdr.getUserId() + ":");
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        fout.writeBytes(sId + ":");
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        fout.writeBytes(commandCode.substring(0, 2) + ":");
        fout.writeBytes(commandCode + ":");
        fout.writeBytes(cdr.getUserId() + ":");
        fout.writeBytes("20" + cdr.getSubmitDate() + ":");
        fout.writeBytes("20" + cdr.getDoneDate() + ":");
        fout.writeBytes(cdr.getTotalSegments() + ":");
        fout.writeBytes("0:");
        fout.writeBytes("1:");
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        fout.writeBytes(info);
        fout.writeBytes("\n");
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_799;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_1166;
        if(!"VIETEL".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_1166;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = "VASC" + DateProc.getYYYYMMDDHHMMString(ts) + ".bil";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        fout.writeBytes(cdr.getUserId() + ":");
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        fout.writeBytes(sId + ":");
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        fout.writeBytes(commandCode + ":");
        fout.writeBytes("20" + cdr.getSubmitDate() + ":");
        fout.writeBytes("20" + cdr.getDoneDate() + ":");
        fout.writeBytes("1:");
        fout.writeBytes(cdr.getTotalSegments() + "\n");
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1165;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        return;
    }

    public static synchronized void add2CDRFileEx(CDR cdr)
    {
        String fileCDR;
        fileCDR = null;
        if(!"GPC".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_296;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".vasc");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = DateProc.getYYYYMMDDHHMMString(ts) + ".vasc";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String sData = "M\t" + sId + "\t" + cdr.getUserId() + "\t" + "20" + cdr.getSubmitDate() + "\t" + "D\t" + "20" + cdr.getDoneDate() + "\t" + "4\t" + "MAPMO.1:1\t" + "0\t" + "MAP.1:1\t" + "1/160\t" + "1\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_292;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        break MISSING_BLOCK_LABEL_955;
        if(!"VMS".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_659;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bin");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilename();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String sData = cdr.getUserId() + ":" + sId + ":" + commandCode.substring(0, 2) + ":" + commandCode + ":" + cdr.getUserId() + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + cdr.getTotalSegments() + ":" + "0:" + "1:" + info + "\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_655;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_955;
        if(!"VIETEL".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_955;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = "VASC" + DateProc.getYYYYMMDDHHMMString(ts) + ".bil";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String sData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + cdr.getTotalSegments() + "\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_954;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        return;
    }

    private static String buildCommandCode(String service_id, String originalCode)
    {
        String cc = "";
        if("04996".equals(service_id) || "84996".endsWith(service_id) || "996".equals(service_id))
            cc = "DA";
        else
        if("04997".equals(service_id) || "84997".endsWith(service_id) || "997".equals(service_id))
            cc = "XS";
        else
        if("04998".equals(service_id) || "84998".endsWith(service_id) || "998".equals(service_id))
            cc = "IM";
        else
            cc = originalCode;
        return cc;
    }
}
