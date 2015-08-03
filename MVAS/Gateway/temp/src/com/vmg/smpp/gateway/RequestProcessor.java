// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestProcessor.java

package com.vmg.smpp.gateway;

import com.vmg.common.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import org.smpp.pdu.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            MobileQueue, DBTools, ReportMsgParser, Gateway, 
//            PDUData, DBException, Logger, Convert, 
//            Preference, MobileBuffer, MobileBufferInfo

public class RequestProcessor extends Thread
{

    public RequestProcessor(Queue fromSMSC, Queue toSMSC)
    {
        this.fromSMSC = null;
        this.toSMSC = null;
        MOSimQueue = null;
        pdu = null;
        pdud = null;
        dsm = null;
        userId = null;
        serviceId = null;
        operator = null;
        commandCode = null;
        info = null;
        RequestID = null;
        dbTools = null;
        parser = null;
        mQueue = new MobileQueue();
        this.fromSMSC = fromSMSC;
        this.toSMSC = toSMSC;
        dbTools = new DBTools();
        parser = new ReportMsgParser();
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            try
            {
                pdud = (PDUData)fromSMSC.dequeue();
                pdu = pdud.getPDU();
                if(pdu.isRequest())
                {
                    RequestID = pdud.getRequestID();
                    processRequest(pdu);
                }
            }
            catch(DBException ex)
            {
                Logger.error(getClass().getName(), "DBException: " + ex.getMessage());
                try
                {
                    DBTools.Alert2YM(getClass().getName() + " DBException: " + ex.getMessage());
                }
                catch(Exception exception) { }
                Logger.error(getClass().getName(), "Alert2YM DBException: " + ex.getMessage());
            }
            catch(Exception e)
            {
                Logger.error(getClass().getName(), "Exception: " + e.getMessage());
            }
        Logger.info(getClass().getName(), "{" + getClass().getName() + " stopped}");
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private void processRequest(PDU pdu)
        throws DBException, IOException
    {
        if(pdu.getCommandId() == 5)
        {
            dsm = (DeliverSM)pdu;
            userId = dsm.getSourceAddr().getAddress();
            serviceId = dsm.getDestAddr().getAddress();
            info = dsm.getShortMessage();
            ByteBuffer da = null;
            try
            {
                da = dsm.getDestSubaddress();
                String te = Convert.hexToString(da.getHexDump().substring(2));
                RequestID = te;
                Utilities _tmp = Gateway.util;
                Utilities.log(getClass().getName(), "getDestSubaddress:" + te);
            }
            catch(ValueNotSetException valuenotsetexception) { }
            if(info == null)
                info = "null";
            userId = removePlusSign(userId);
            serviceId = rebuildServiceId(serviceId);
            operator = Preference.mobileOperator;
            if(dsm.getEsmClass() == 4 || info.startsWith("id:"))
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.log(getClass().getName(), "It can be DeliverReport (not processed)!");
                return;
            }
            info = StringTool.removeChar(info, '\0');
            DBTools.add2SMSReceiveQueueR(userId, serviceId, operator, commandCode, info, RequestID);
        }
    }

    private boolean isValidAddress(String userId, String serviceId)
    {
        if(userId == null || "".equals(userId) || serviceId == null || "".equals(serviceId))
        {
            System.out.println("Source/dest address NULL --> PDU discarded");
            return false;
        }
        if(!Preference.isValidServiceId(serviceId))
        {
            System.out.println("Invalid dest address:" + serviceId + " --> PDU discarded");
            return false;
        }
        if(userId.startsWith("849") || userId.startsWith("+849") || userId.startsWith("09"))
            return true;
        return !"UNKNOWN".equalsIgnoreCase(Preference.mobileOperator) ? true : true;
    }

    private int checkMobileBuffer(String mobile)
    {
        long currTime = System.currentTimeMillis() / 1000L;
        int currDay = getDay(System.currentTimeMillis());
        MobileBufferInfo info = MobileBuffer.lookup(mobile);
        if(info == null)
        {
            MobileBuffer.add(mobile, new MobileBufferInfo(mobile, currTime, 0L, 1, 0, 0));
            return 1;
        }
        int result = 0;
        long lastTime = info.mo_Time;
        if(lastTime <= 0L)
        {
            info.mo_Counter = 1;
            result = 1;
        } else
        if(currDay != getDay(lastTime * 1000L))
        {
            MobileBuffer.clearAll();
            result = 1;
        } else
        {
            info.mo_Counter++;
            if(info.mo_Counter >= 101)
                result = info.mo_Counter;
            else
                result = 1;
        }
        info.mo_Time = currTime;
        MobileBuffer.update(mobile, info);
        return result;
    }

    private int checkMobileBuffer(String mobile, String msg)
    {
        long currTime = System.currentTimeMillis() / 1000L;
        int currDay = getDay(System.currentTimeMillis());
        MobileBufferInfo info = MobileBuffer.lookup(mobile);
        if(info == null)
        {
            MobileBuffer.add(mobile, new MobileBufferInfo(mobile, currTime, 0L, 1, 0, 0, msg));
            return 1;
        }
        int result = 0;
        long lastTime = info.mo_Time;
        if(lastTime <= 0L)
        {
            info.mo_Counter = 1;
            result = 1;
        } else
        if(currDay != getDay(lastTime * 1000L))
        {
            MobileBuffer.clearAll();
            result = 1;
        } else
        {
            info.mo_Counter++;
            if(currTime - lastTime < 30L && msg.equals(info.msg))
                result = 2;
            else
            if(info.mo_Counter >= 101)
                result = info.mo_Counter;
            else
                result = 1;
        }
        if(result <= 1)
        {
            info.msg = msg;
            info.mo_Time = currTime;
            MobileBuffer.update(mobile, info);
        }
        return result;
    }

    private int getDay(long miliSecs)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(miliSecs));
        return calendar.get(5);
    }

    private String buildMobileOperator(String userId)
    {
        String temp = userId;
        String result = null;
        if(temp == null || "".equals(temp))
            result = "null";
        else
        if(temp.startsWith("8490") || temp.startsWith("+8490") || temp.startsWith("8493") || temp.startsWith("+8493"))
            result = "VMS";
        else
        if(temp.startsWith("8491") || temp.startsWith("+8491") || temp.startsWith("8494") || temp.startsWith("+8494"))
            result = "GPC";
        else
        if(temp.startsWith("8498") || temp.startsWith("+8498") || temp.startsWith("8497") || temp.startsWith("+8497"))
            result = "VIETEL";
        else
        if(temp.startsWith("8495") || temp.startsWith("+8495"))
            result = "SFONE";
        else
        if(temp.startsWith("8492") || temp.startsWith("+8492"))
            result = "HTC";
        else
        if(temp.startsWith("8496") || temp.startsWith("+8496"))
            result = "EVN";
        else
            result = "EVN";
        return result;
    }

    private String removePlusSign(String userId)
    {
        String temp = userId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        return temp;
    }

    private String rebuildServiceId(String serviceId)
    {
        String temp = serviceId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        if(temp.startsWith("84") && temp.length() > 4 || temp.startsWith("04"))
            temp = temp.substring(2);
        if(temp.startsWith("095") && temp.length() > 5)
            temp = temp.substring(3);
        return temp;
    }

    private Queue fromSMSC;
    private Queue toSMSC;
    private Queue MOSimQueue;
    private PDU pdu;
    private PDUData pdud;
    private DeliverSM dsm;
    private String userId;
    private String serviceId;
    private String operator;
    private String commandCode;
    private String info;
    private String RequestID;
    private DBTools dbTools;
    private ReportMsgParser parser;
    private MobileQueue mQueue;
}
