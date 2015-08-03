// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExecuteQueue.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import com.vmg.sms.common.Utilities;
import java.math.BigDecimal;

// Referenced classes of package com.vmg.sms.process:
//            MsgQueue, MsgObject, ConsoleSRV, LoadConfig, 
//            Constants, Keyword, Logger, ContentAbstract

public class ExecuteQueue extends Thread
{

    public ExecuteQueue(MsgQueue queue, MsgQueue queueLog, int threadID)
    {
        this.threadID = 0;
        this.queue = null;
        this.queueLog = null;
        AM = new BigDecimal(-1D);
        this.queue = queue;
        this.queueLog = queueLog;
        this.threadID = threadID;
    }

    public void run()
    {
        MsgObject msgObject = null;
        String serviceId = "";
        String info = "";
        Keyword keyword = null;
        String process_result = "";
        try
        {
            sleep(50L);
        }
        catch(InterruptedException interruptedexception) { }
        while(ConsoleSRV.processData) 
        {
            process_result = "";
            try
            {
                msgObject = (MsgObject)queue.remove();
                serviceId = msgObject.getServiceid();
                info = msgObject.getUsertext();
                keyword = ConsoleSRV.loadconfig.getKeyword(info, serviceId);
                if(Constants.INV_KEYWORD.equalsIgnoreCase(keyword.getKeyword()))
                {
                    keyword = ConsoleSRV.loadconfig.getKeywordInvalid(info, serviceId);
                    if(!Constants.INV_KEYWORD.equalsIgnoreCase(keyword.getKeyword()))
                    {
                        String newinfo = Utilities.replaceWhiteLetter(info);
                        msgObject.setUsertext(newinfo);
                        Util.logger.info("{userid=" + msgObject.getUserid() + "}{info_old=" + info + "}{info_new=" + newinfo + "}");
                    }
                }
                msgObject.setKeyword(keyword.getKeyword());
                msgObject.setCpid(keyword.getCpid());
                process_result = processQueueMsg(msgObject, keyword);
                msgObject.setMsgNotes(process_result);
                queueLog.add(new MsgObject(msgObject.getMo_id(), serviceId, msgObject.getUserid(), keyword.getKeyword(), info, msgObject.getRequestid(), msgObject.getTTimes(), msgObject.getMobileoperator(), 0, 0, msgObject.getCpid(), msgObject.getMsgnotes()));
            }
            catch(Exception ex)
            {
                Util.logger.error("Execute queue. Ex:" + ex.toString());
                queue.add(msgObject);
            }
        }
    }

    private String processQueueMsg(MsgObject msgObject, Keyword keyword)
    {
        ContentAbstract delegate = null;
        try
        {
            String classname = "";
            if(keyword.getClassname().startsWith("~"))
            {
                classname = "com.vmg.sms.process.ChatSMS";
                String sInfo = keyword.getClassname();
                String arrInfo[] = sInfo.split("~");
                String mtreply = "";
                int msgtype = 2;
                if(arrInfo.length > 2)
                {
                    mtreply = arrInfo[2];
                    if(Constants.MT_CHARGING.equals(arrInfo[1]))
                        msgtype = 1;
                    else
                    if(Constants.MT_PUSH.equals(arrInfo[1]))
                        msgtype = 3;
                    else
                    if(Constants.MT_REFUND_SYNTAX.equals(arrInfo[1]))
                        msgtype = 21;
                    else
                    if(Constants.MT_REFUND_CONTENT.equals(arrInfo[1]))
                        msgtype = 22;
                    else
                        msgtype = 2;
                } else
                {
                    mtreply = arrInfo[1];
                    msgtype = 2;
                }
                msgObject.setUsertext(mtreply);
                msgObject.setMsgtype(msgtype);
            } else
            {
                classname = keyword.getClassname();
            }
            Util.logger.info("processQueueMsg:{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}@" + classname);
            Class delegateClass = Class.forName(classname);
            Object delegateObject = delegateClass.newInstance();
            delegate = (ContentAbstract)delegateObject;
            delegate.start(msgObject, keyword);
        }
        catch(Exception e)
        {
            Util.logger.crisis("processQueueMsg:{userid=" + msgObject.getUserid() + "}{usertext=" + msgObject.getUsertext() + "}{requestid=" + msgObject.getRequestid().toString() + "}{moid=" + msgObject.getMo_id() + "}@" + e.toString());
            return msgObject.getUserid() + ":" + e.toString();
        }
        return "OK";
    }

    int threadID;
    MsgQueue queue;
    MsgQueue queueLog;
    BigDecimal AM;
}
