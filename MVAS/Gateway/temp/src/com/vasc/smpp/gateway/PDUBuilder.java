// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDUBuilder.java

package com.vasc.smpp.gateway;

import com.vasc.common.util.Queue;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            DBTools, DBException, SMSData, MobileBufferInfo, 
//            Gateway, Preference, SMSCTools, MobileBuffer

public class PDUBuilder extends Thread
{

    public PDUBuilder(Queue toSMSC)
    {
        sms = null;
        this.toSMSC = null;
        dbTools = null;
        this.toSMSC = toSMSC;
        dbTools = new DBTools();
        setPriority(6);
    }

    private int checkMobileBuffer(String mobile)
    {
        long currTime = System.currentTimeMillis() / 1000L;
        int currDay = getDay(System.currentTimeMillis());
        MobileBufferInfo info = MobileBuffer.lookup(mobile);
        if(info == null)
        {
            MobileBuffer.add(mobile, new MobileBufferInfo(mobile, 0L, currTime, 0, 1));
            return 1;
        }
        int result = 0;
        long lastTime = info.mt_Time;
        if(lastTime <= 0L)
        {
            info.mt_Counter = 1;
            result = 1;
        } else
        if(currDay != getDay(lastTime * 1000L))
        {
            MobileBuffer.clearAll();
            info.mt_Counter = 1;
            result = 1;
        } else
        {
            info.mt_Counter++;
            if(info.mt_Counter >= 101)
                result = info.mt_Counter;
            else
                result = 1;
        }
        info.mt_Time = currTime;
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

    public void run()
    {
        Gateway.addLiveThread(this);
          goto _L1
_L3:
        if(!Gateway.bound)
            continue; /* Loop/switch isn't completed */
        if(toSMSC.size() < 1000)
        {
            sendSMSQueueEx();
            this;
            sleep(100L);
        } else
        {
            this;
            sleep(60000L);
        }
        break MISSING_BLOCK_LABEL_254;
        DBException ex;
        ex;
        System.out.println("PDUBuilder::" + ex.getMessage());
        dbTools;
        DBTools.logMC(Preference.sourceAddressList.toString(), "PDUBuilder", "<-" + Preference.mobileOperator + "-> ERROR: Ket noi Database bi loi: " + ex.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_148;
        Exception e;
        e;
        Gateway.rebuildDBConnections(Preference.db_MaxConnections);
        break MISSING_BLOCK_LABEL_254;
        Exception ex;
        ex;
        System.out.println("PDUBuilder:::" + ex.getMessage());
        dbTools;
        DBTools.logMC(Preference.sourceAddressList.toString(), "PDUBuilder", "<-" + Preference.mobileOperator + "-> ERROR: " + ex.getMessage(), 1, "DKTRUNG 0904098489", "or PVDUNG 0985956668");
        break MISSING_BLOCK_LABEL_250;
        Exception e;
        e;
_L1:
        if(Gateway.running) goto _L3; else goto _L2
_L2:
        destroy();
        return;
    }

    public int sendSMSQueue()
        throws DBException
    {
        int numOfMsg = 0;
        byte registeredDelivery = 0;
        Collection collection = dbTools.getAllSMSesInSendQueue();
        if(collection != null && collection.size() > 0)
        {
            numOfMsg = collection.size();
            BigDecimal msgId = null;
            Iterator it = collection.iterator();
            while(it.hasNext()) 
            {
                msgId = (BigDecimal)it.next();
                sms = dbTools.getSMSinSendQueue(msgId);
                if(sms == null)
                    continue;
                if(!sms.isValidServiceId() || !sms.isValidUserId() || sms.getNumberOfSend() >= Preference.maxNumOfSend && sms.isTimeout())
                {
                    dbTools.moveSMSFromSendQueueToSendLog(msgId);
                    continue;
                }
                if(!sms.isAddressToSend())
                    continue;
                if(sms.isNotSentYet())
                {
                    boolean isMOperatorChanged = sms.rebuildMobileOperator();
                    if(isMOperatorChanged)
                        dbTools.updateMobileOperator(sms.getId(), sms.getMobileOperator());
                    if(sms.getMessageType() == 3 && sms.getMoreMsgsToSend() == 0)
                        dbTools.add2CdrQueue(sms);
                }
                if(sms.isNotSentYet() || sms.isTimeout())
                {
                    if(Preference.reportRequired && sms.getMessageType() == 1 && sms.getMoreMsgsToSend() == 0)
                        registeredDelivery = 1;
                    else
                        registeredDelivery = 0;
                    String info = sms.getInfo();
                    org.smpp.pdu.SubmitSM ssm = SMSCTools.buildSubmitSM(sms.getServiceIdEx(12), sms.getUserIdEx(0), info, sms.getId().intValue(), registeredDelivery, sms.getMessageType());
                    if(toSMSC.size() < 1000)
                    {
                        toSMSC.enqueue(ssm);
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        int n = sms.getNumberOfSend() + 1;
                        dbTools.updateLastSendTime(msgId, time, n);
                    } else
                    {
                        try
                        {
                            PDUBuilder _tmp = this;
                            sleep(60000L);
                            continue;
                        }
                        catch(InterruptedException ie) { }
                    }
                }
            }
        }
        return numOfMsg;
    }

    public int sendSMSQueueEx()
        throws DBException
    {
        int numOfMsg = 0;
        byte registeredDelivery = 0;
        Timestamp time = null;
        boolean isCharged = false;
        if(toSMSC.size() > 1000)
            return 0;
        Collection collection = dbTools.getAllSMSesInSendQueue(Preference.SEND_MODE);
        if(collection != null && collection.size() > 0)
        {
            numOfMsg = collection.size();
            BigDecimal msgId = null;
            for(Iterator it = collection.iterator(); it.hasNext();)
            {
                sms = (SMSData)it.next();
                msgId = sms.getId();
                if(!sms.isValidServiceId() || !sms.isValidUserId() || sms.getNumberOfSend() >= Preference.maxNumOfSend && sms.isTimeout())
                    dbTools.moveSMSFromSendQueueToSendLogEx(sms);
                else
                if(sms.isAddressToSend())
                {
                    if(sms.isNotSentYet())
                    {
                        boolean isMOperatorChanged = sms.rebuildMobileOperator();
                        if(isMOperatorChanged)
                            dbTools.updateMobileOperator(sms.getId(), sms.getMobileOperator());
                    }
                    if(sms.isNotSentYet() || sms.isTimeout())
                    {
                        if(Preference.reportRequired && sms.getMessageType() == 1 && sms.getMoreMsgsToSend() == 0)
                            registeredDelivery = 1;
                        else
                            registeredDelivery = 0;
                        org.smpp.pdu.SubmitSM ssm = SMSCTools.buildSubmitSM(sms.getServiceIdEx(12), sms.getUserIdEx(0), sms.getInfo(), sms.getId().intValue(), registeredDelivery, sms.getMessageType());
                        time = new Timestamp(System.currentTimeMillis());
                        int n = sms.getNumberOfSend() + 1;
                        if(dbTools.updateLastSendTime(msgId, time, n))
                        {
                            toSMSC.enqueue(ssm);
                            if(sms.getMessageType() == 3 && sms.getMoreMsgsToSend() == 0)
                                dbTools.add2CdrQueueEx(sms);
                        }
                    }
                }
            }

        }
        return numOfMsg;
    }

    static final int MAX_SMS_IN_QUEUE = 1000;
    private DBTools dbTools;
    private SMSData sms;
    private Queue toSMSC;
}
