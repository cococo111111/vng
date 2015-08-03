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
//            CDR, CdrFilename4vms, Logger

public class CDRTool
{

    public CDRTool()
    {
    }

    public static synchronized void add2CDRFile(CDR cdr)
    {
        String fileCDR;
        String fileCDRftp;
        fileCDR = null;
        fileCDRftp = null;
        String prefixfile = null;
        String writeData = "";
        if(!"GPC".equals(cdr.getMobileOperator()) || cdr.getServiceId().length() >= 6)
            break MISSING_BLOCK_LABEL_412;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".vasc");
        if(v.size() > 0)
        {
            for(int i = 0; i < v.size(); i++)
            {
                prefixfile = ((File)v.elementAt(i)).getName().substring(0, 4);
                if(!"1900".equals(prefixfile.trim()))
                {
                    fileCDR = ((File)v.elementAt(i)).getName();
                    break;
                }
                java.sql.Timestamp ts = DateProc.createTimestamp();
                fileCDR = DateProc.getYYYYMMDDHHMMSSString(ts) + ".vasc";
            }

        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = DateProc.getYYYYMMDDHHMMSSString(ts) + ".vasc";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String writeData = "M\t" + sId + "\t" + cdr.getUserId() + "\t" + "20" + cdr.getSubmitDate() + "\t" + "D\t" + "20" + cdr.getDoneDate() + "\t" + "0\t" + "MAPMO.1:1\t" + "0\t" + "MAP.1:1\t" + "1/160\t" + "1\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_408;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        break MISSING_BLOCK_LABEL_2591;
        if(!"GPC".equals(cdr.getMobileOperator()) || cdr.getServiceId().length() <= 6)
            break MISSING_BLOCK_LABEL_824;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".vasc");
        if(v.size() > 0)
        {
            for(int i = 0; i < v.size(); i++)
            {
                String prefixfile = ((File)v.elementAt(i)).getName().substring(0, 4);
                if("1900".equals(prefixfile.trim()))
                {
                    fileCDR = ((File)v.elementAt(i)).getName();
                    break;
                }
                java.sql.Timestamp ts = DateProc.createTimestamp();
                fileCDR = "1900vasc" + DateProc.getYYYYMMDDHHMMSSString(ts) + ".vasc";
            }

        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = "1900vasc" + DateProc.getYYYYMMDDHHMMSSString(ts) + ".vasc";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String writeData = "M\t" + sId + "\t" + cdr.getUserId() + "\t" + "20" + cdr.getSubmitDate() + "\t" + "D\t" + "20" + cdr.getDoneDate() + "\t" + "0\t" + "MAPMO.1:1\t" + "0\t" + "MAP.1:1\t" + "1/160\t" + "1\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_820;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2591;
        if(!"VMS".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_1674;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bin");
        if(v.size() > 0)
        {
            for(int i = 0; i < v.size(); i++)
            {
                String prefixfile = ((File)v.elementAt(i)).getName().substring(18, 3);
                if("bin".equals(prefixfile.trim()))
                {
                    fileCDR = ((File)v.elementAt(i)).getName();
                    break;
                }
                fileCDR = CdrFilename4vms.getNewFilename();
            }

        } else
        {
            fileCDR = CdrFilename4vms.getNewFilename();
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode.substring(0, 2) + ":" + commandCode + ":" + cdr.getUserId() + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + cdr.getTotalSegments() + ":" + "0:" + "1:" + info + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1253;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        Vector vftp = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".cdr");
        if(vftp.size() > 0)
        {
            for(int i = 0; i < vftp.size(); i++)
            {
                String prefixfile = ((File)vftp.elementAt(i)).getName().substring(19, 3);
                if("cdr".equals(prefixfile.trim()))
                {
                    fileCDRftp = ((File)vftp.elementAt(i)).getName();
                    break;
                }
                fileCDRftp = CdrFilename4vms.getNewFilenameFTPforVMS();
            }

        } else
        {
            fileCDRftp = CdrFilename4vms.getNewFilenameFTPforVMS();
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDRftp, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode.substring(0, 2) + ":" + commandCode + ":" + cdr.getUserId() + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + cdr.getTotalSegments() + ":" + "0:" + "1:" + info + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1670;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        break MISSING_BLOCK_LABEL_2591;
        if(!"VIETEL".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_1984;
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
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + cdr.getTotalSegments() + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1980;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2591;
        if(!"SFONE".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_2294;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = "VASC_" + DateProc.getYYYYMMDDHHMMString(ts) + ".bil";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + cdr.getTotalSegments() + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_2290;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2591;
        if(!"HTC".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_2591;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".txt");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilenameELCOM();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String ServiceType = "M";
        String sender_number = cdr.getUserId();
        String UserId = SMSData.formatUserId(sender_number, 0);
        String Service_Id = SMSData.formatServiceId(cdr.getServiceId(), 12);
        String TimeSend = cdr.getDoneDateFULL();
        String TimeReceive = cdr.getSubmitDateFULL();
        int ProcResult = 1;
        String ServiceState = "D";
        if(ProcResult == 1)
            ServiceState = "D";
        else
            ServiceState = "U";
        String sData = ServiceType + "\t" + Service_Id + "\t" + UserId + "\t" + TimeReceive + "\t" + ServiceState + "\t" + TimeSend + "\r\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_2590;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        return;
    }

    public static synchronized void add2CDRFile8x99(CDR cdr)
    {
        String fileCDR;
        fileCDR = null;
        String writeData = "";
        if(!"GPC".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_305;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".vasc");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = DateProc.getYYYYMMDDHHMMSSString(ts) + "_8x99.vasc";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String writeData = "M\t" + sId + "\t" + cdr.getUserId() + "\t" + "20" + cdr.getSubmitDate() + "\t" + "D\t" + "20" + cdr.getDoneDate() + "\t" + "0\t" + "MAPMO.1:1\t" + "0\t" + "MAP.1:1\t" + "1/160\t" + "9\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_301;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        break MISSING_BLOCK_LABEL_2052;
        if(!"VMS".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_643;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".cdr");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilename8x99();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = cdr.getCommandCode();
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + commandCode + ":" + cdr.getUserId() + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + info + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_639;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2052;
        if(!"VIETEL".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_948;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            java.sql.Timestamp ts = DateProc.createTimestamp();
            fileCDR = "VASC" + DateProc.getYYYYMMDDHHMMString(ts) + "_8x99.bil";
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 10);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + cdr.getTotalSegments() + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_944;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2052;
        if(!"EVN".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_1299;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
            break MISSING_BLOCK_LABEL_1017;
        }
        fileCDR = CdrFilename4vms.getNewFilenameEVN();
        CdrFilename4vms.setNewFilename(fileCDR);
        break MISSING_BLOCK_LABEL_1016;
        IOException ex1;
        ex1;
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 12);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String sData = cdr.getUserId() + "," + sId + "," + "20" + cdr.getSubmitDate() + "," + "20" + cdr.getDoneDate() + "," + commandCode + "," + "1" + "," + "1:1" + getChargeValue(sId) + "\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1295;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2052;
        if(!"SFONE".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_1710;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".bil");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilename8x99SFONE();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 12);
        String commandCode = "VASC";
        String info = cdr.getInfo();
        int MessageLength = info.length();
        String MessageLength_a;
        for(MessageLength_a = Integer.toString(MessageLength); MessageLength_a.length() < 3; MessageLength_a = MessageLength_a.concat(" "));
        String UserId;
        for(UserId = cdr.getUserId(); UserId.length() < 20; UserId = UserId.concat(" "));
        String Service_Id = cdr.getServiceId();
        for(Service_Id = Service_Id.substring(2, Service_Id.length()); Service_Id.length() < 20; Service_Id = Service_Id.concat(" "));
        String TimeSend = cdr.getDoneDateFULL();
        String TimeReceive = cdr.getSubmitDateFULL();
        for(; commandCode.length() < 10; commandCode = commandCode.concat(" "));
        int ProcResult = 1;
        int SegmentID = cdr.getTotalSegments();
        int segmentNum = 1;
        int SubscriberType = 0;
        String MO = "1";
        String MT = "0";
        String sData = UserId + Service_Id + TimeSend + TimeReceive + commandCode + ProcResult + SegmentID + segmentNum + MessageLength_a + SubscriberType + MT;
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_1706;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        break MISSING_BLOCK_LABEL_2052;
        if(!"HTC".equals(cdr.getMobileOperator()))
            break MISSING_BLOCK_LABEL_2052;
        Vector v = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".txt");
        if(v.size() > 0)
            fileCDR = ((File)v.elementAt(0)).getName();
        else
            fileCDR = CdrFilename4vms.getNewFilenameELCOM();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String ServiceType = "M";
        String UserId = SMSData.formatUserId(cdr.getUserId(), 0);
        String Service_Id = SMSData.formatServiceId(cdr.getServiceId(), 12);
        String TimeSend = cdr.getDoneDateFULL();
        String TimeReceive = cdr.getSubmitDateFULL();
        String ContCode = "103";
        String ContType = "2";
        String Desc = "VASC-infor:" + cdr.getInfo();
        int ProcResult = 1;
        String ServiceState = "D";
        if(ProcResult == 1)
            ServiceState = "D";
        else
            ServiceState = "U";
        String sData = Service_Id + "\t" + UserId + "\t" + TimeReceive + "\t" + ServiceState + "\t" + TimeSend + "\t" + ContCode + "\t" + ContType + "\t" + Desc + "\r\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_2051;
        fout;
        System.out.println("CDRTool::" + fout.getMessage());
        return;
    }

    public static synchronized void add2CDRFile8x99ForPushVMS(CDR cdr)
    {
        String fileCDR;
        fileCDR = null;
        String writeData = "";
        Vector v = FileTool.getAllFiles(new File(".\\"), ".cdr");
        if(v.size() > 0)
        {
            fileCDR = ((File)v.elementAt(0)).getName();
        } else
        {
            System.out.println(">>>Khong tim thay file ...");
            return;
        }
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "\\" + fileCDR, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = buildCommandCode(cdr.getServiceId(), cdr.getCommandCode());
        String info = cdr.getInfo();
        if(info != null && info.length() > 15)
            info = info.substring(0, 9) + "...";
        String writeData = cdr.getUserId() + ":" + sId + ":" + commandCode.substring(0, 2) + ":" + commandCode + ":" + cdr.getUserId() + ":" + "20" + cdr.getSubmitDate() + ":" + "20" + cdr.getDoneDate() + ":" + "1:" + info + "\n";
        fout.writeBytes(writeData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_342;
        IOException ex;
        ex;
        System.out.println("CDRTool::" + ex.getMessage());
        return;
    }

    public static synchronized void add2CDRFileEx(CDR cdr)
    {
        String fileCDRftp;
        String fileCDR = null;
        fileCDRftp = null;
        String prefixfile = null;
        String sufixfile = null;
        if(!"VMS".equals(cdr.getMobileOperator().toUpperCase()))
            break MISSING_BLOCK_LABEL_388;
        Vector vftp = FileTool.getAllFiles(new File(Preference.cdroutFolder), ".cdr");
        if(vftp.size() > 0)
            fileCDRftp = ((File)vftp.elementAt(0)).getName();
        else
            fileCDRftp = CdrFilename4vms.getNewFilenameFTPforVMS();
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(Preference.cdroutFolder + "/" + fileCDRftp, true));
        String sId = SMSData.formatServiceId(cdr.getServiceId(), 11);
        String commandCode = cdr.getCommandCode();
        String info = cdr.getInfo().replaceAll(":", "");
        if(info != null && info.length() > 15)
        {
            info = info.substring(0, 9);
            info = info.replaceAll(" ", "");
        }
        String messageType = cdr.getMessageType().trim();
        String ProcessResult = cdr.getProcessResult().trim();
        if("0".equals(ProcessResult))
        {
            messageType = "0";
        } else
        {
            if(messageType.startsWith("2"))
                messageType = "0";
            else
                messageType = "1";
        }
        String sData = cdr.getUserId() + ":" + sId + ":" + commandCode + ":" + commandCode + ":" + cdr.getUserId() + ":" + cdr.getSubmitDate() + ":" + cdr.getDoneDate() + ":" + messageType + ":" + info + "\n";
        fout.writeBytes(sData);
        fout.flush();
        fout.close();
        break MISSING_BLOCK_LABEL_387;
        IOException ex;
        ex;
        Logger.info("CDRTool:", ex.getMessage());
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

    public static String getChargeValue(String serviceID)
    {
        String sShortCode = SMSData.formatServiceId(serviceID, 12);
        if(sShortCode == null || "".equals(sShortCode))
            return "0";
        if(sShortCode.startsWith("996") || sShortCode.startsWith("82"))
            return "2000";
        if(sShortCode.startsWith("998") || sShortCode.startsWith("83"))
            return "3000";
        if(sShortCode.startsWith("8499"))
            return "4000";
        if(sShortCode.startsWith("85"))
            return "5000";
        if(sShortCode.startsWith("87"))
            return "15000";
        if(sShortCode.startsWith("190017") || sShortCode.startsWith("81") || sShortCode.startsWith("997"))
            return "1000";
        else
            return "500";
    }
}
