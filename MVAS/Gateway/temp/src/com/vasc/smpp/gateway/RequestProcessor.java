// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestProcessor.java

package com.vasc.smpp.gateway;

import com.vasc.common.StringTool;
import com.vasc.common.Utilities;
import com.vasc.common.util.Queue;
import java.io.PrintStream;
import java.util.*;
import org.smpp.pdu.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            DBTools, ReportMsgParser, DBException, MobileBufferInfo, 
//            Gateway, Preference, MobileBuffer

public class RequestProcessor extends Thread
{

    public RequestProcessor(Queue fromSMSC, Queue toSMSC)
    {
        this.fromSMSC = null;
        this.toSMSC = null;
        pdu = null;
        dsm = null;
        userId = null;
        serviceId = null;
        operator = null;
        commandCode = null;
        info = null;
        dbTools = null;
        parser = null;
        this.fromSMSC = fromSMSC;
        this.toSMSC = toSMSC;
        dbTools = new DBTools();
        parser = new ReportMsgParser();
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

    private int checkMobileBuffer(String mobile, String msg)
    {
        long currTime = System.currentTimeMillis() / 1000L;
        int currDay = getDay(System.currentTimeMillis());
        MobileBufferInfo info = MobileBuffer.lookup(mobile);
        if(info == null)
        {
            MobileBuffer.add(mobile, new MobileBufferInfo(mobile, currTime, 0L, 1, 0, msg));
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
            info.mo_Counter = 1;
            result = 1;
        } else
        {
            info.mo_Counter++;
            if(currTime - lastTime < 30L && info.msg.equals(msg))
                result = 2;
            else
            if(info.mo_Counter == 101)
                result = info.mo_Counter;
            else
            if(info.mo_Counter > 101)
                result = info.mo_Counter;
            else
                result = 1;
        }
        if(result <= 1)
        {
            info.mo_Time = currTime;
            MobileBuffer.update(mobile, info);
        }
        return result;
    }

    private int checkMobileBuffer(String mobile)
    {
        long currTime = System.currentTimeMillis() / 1000L;
        int currDay = getDay(System.currentTimeMillis());
        MobileBufferInfo info = MobileBuffer.lookup(mobile);
        if(info == null)
        {
            MobileBuffer.add(mobile, new MobileBufferInfo(mobile, currTime, 0L, 1, 0));
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
            info.mo_Counter = 1;
            result = 1;
        } else
        {
            info.mo_Counter++;
            if(currTime - lastTime < 30L)
                result = 2;
            else
            if(info.mo_Counter == 101)
                result = info.mo_Counter;
            else
            if(info.mo_Counter > 101)
                result = info.mo_Counter;
            else
                result = 1;
        }
        info.mo_Time = currTime;
        MobileBuffer.update(mobile, info);
        return result;
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    private int getDay(long miliSecs)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(miliSecs));
        Calendar _tmp = calendar;
        return calendar.get(5);
    }

    private boolean isValidAddress(String userId, String serviceId)
    {
        if(userId == null || "".equals(userId) || serviceId == null || "".equals(serviceId))
        {
            Utilities _tmp = Gateway.util;
            Utilities.log("  Source/dest address NULL --> PDU discarded");
            return false;
        }
        if(!Preference.isValidServiceId(serviceId))
        {
            Utilities _tmp1 = Gateway.util;
            Utilities.log("  Invalid dest address:" + serviceId + " --> PDU discarded");
            return false;
        }
        if(userId.startsWith("849") || userId.startsWith("+849") || userId.startsWith("09") || userId.startsWith("84"))
        {
            return true;
        } else
        {
            Utilities _tmp2 = Gateway.util;
            Utilities.log("  Invalid source address:" + userId + " --> PDU discarded");
            return true;
        }
    }

    private boolean isValidPrefix(String serviceId, String text)
    {
        if(text == null || "".equals(text))
            return false;
        String temp = text.trim().toUpperCase();
        Collection cPrefixes = (Collection)Preference.prefixMap.get(serviceId);
        for(Iterator it = cPrefixes.iterator(); it.hasNext();)
        {
            String currLabel = (String)it.next();
            if(temp.startsWith(currLabel))
            {
                commandCode = currLabel;
                return true;
            }
        }

        commandCode = "";
        return false;
    }

    private void processRequest(PDU pdu)
        throws DBException
    {
        if(pdu.getCommandId() == 5)
        {
            dsm = (DeliverSM)pdu;
            userId = dsm.getSourceAddr().getAddress();
            serviceId = dsm.getDestAddr().getAddress();
            info = dsm.getShortMessage();
            if(info == null)
                info = "null";
            if(!isValidAddress(userId, serviceId))
                return;
            userId = removePlusSign(userId);
            serviceId = rebuildServiceId(serviceId);
            operator = buildMobileOperator(userId);
            if(dsm.getEsmClass() == 4 || info.startsWith("id:"))
                return;
            info = StringTool.removeChar(info, '\0');
            if(isValidPrefix(serviceId, info))
            {
                Utilities _tmp = Gateway.util;
                Utilities.log("    OK --> Add to receive_queue.");
                dbTools.add2SMSReceiveQueueEx(userId, serviceId, operator, commandCode, info);
            } else
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.log("    Invalid prefix --> Add to receive_invalid.");
                java.math.BigDecimal msgLogId = dbTools.add2SMSReceiveInvalidEx(userId, serviceId, operator, "INV", info);
                String invalidPrefixMessage = (String)Preference.messageMap.get(serviceId);
                dbTools.add2SMSSendQueueEx(userId, serviceId, operator, "INV", invalidPrefixMessage, 0, 0, 21, msgLogId, 1, 1, 0);
            }
        }
    }

    private String rebuildServiceId(String serviceId)
    {
        String temp = serviceId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        if(temp.startsWith("84") || temp.startsWith("04"))
            temp = temp.substring(2);
        return temp;
    }

    private String removePlusSign(String userId)
    {
        String temp = userId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        return temp;
    }

    public void run()
    {
        Gateway.addLiveThread(this);
          goto _L1
_L3:
        pdu = (PDU)fromSMSC.dequeue();
        if(pdu.isRequest())
            processRequest(pdu);
        continue; /* Loop/switch isn't completed */
        DBException ex;
        ex;
        System.out.println("RequestProcessor::" + ex.getMessage());
        dbTools;
        DBTools.logMC(Preference.sourceAddressList.toString(), "RequestProc", "<-" + Preference.mobileOperator + "-> ERROR: Ket noi Database bi loi: " + ex.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_136;
        Exception e;
        e;
        Gateway.rebuildDBConnections(Preference.db_MaxConnections);
        System.out.println("Size: " + Gateway.dbPool.size());
        continue; /* Loop/switch isn't completed */
        e;
        System.out.println("RequestProcessor: " + e.getMessage());
        dbTools;
        DBTools.logMC(Preference.sourceAddressList.toString(), "RequestProc", "<-" + Preference.mobileOperator + "-> ERROR: " + e.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_268;
        Exception ex;
        ex;
_L1:
        if(Gateway.running) goto _L3; else goto _L2
_L2:
        destroy();
        return;
    }

    private String commandCode;
    private DBTools dbTools;
    private DeliverSM dsm;
    private Queue fromSMSC;
    private String info;
    private String operator;
    private ReportMsgParser parser;
    private PDU pdu;
    private String serviceId;
    private Queue toSMSC;
    private String userId;
}
