// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetMt2EmsQueue.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import com.vmg.common.Utilities;

// Referenced classes of package com.vmg.smpp.gateway:
//            DBTools, Gateway, DBException, EMSException, 
//            Preference

public class GetMt2EmsQueue extends Thread
{

    public GetMt2EmsQueue(Queue EMSQueue, String iMod, String iNum)
    {
        this.EMSQueue = null;
        dbTools = null;
        this.iMod = "";
        this.iNum = "";
        this.EMSQueue = EMSQueue;
        this.iMod = iMod;
        this.iNum = iNum;
        dbTools = new DBTools();
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            if(Gateway.bound)
                try
                {
                    AddEMS2QueueEx();
                    sleep(100L);
                }
                catch(InterruptedException ex)
                {
                    Utilities _tmp = Gateway.util;
                    Utilities.log(getClass().getName(), "{InterruptedException}" + ex.getMessage());
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "{InterruptedException}" + ex.getMessage());
                    }
                    catch(DBException ex1)
                    {
                        Utilities _tmp1 = Gateway.util;
                        Utilities.log(getClass().getName(), "{GetMt2EmsQueue Alert2YM DBException}" + ex1.getMessage());
                    }
                }
                catch(DBException ex)
                {
                    Utilities _tmp2 = Gateway.util;
                    Utilities.log(getClass().getName(), "{DBException}" + ex.getMessage());
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "{DBException}" + ex.getMessage());
                    }
                    catch(DBException ex1)
                    {
                        Utilities _tmp3 = Gateway.util;
                        Utilities.log(getClass().getName(), "{GetMt2EmsQueue Alert2YM Error}" + ex1.getMessage());
                    }
                }
                catch(Exception ex)
                {
                    Utilities _tmp4 = Gateway.util;
                    Utilities.log(getClass().getName(), "GetMt2EmsQueue:: " + ex.getMessage());
                    try
                    {
                        DBTools.Alert2YM(getClass().getName() + "EMSBuilder:: " + ex.getMessage());
                    }
                    catch(DBException ex1)
                    {
                        Utilities _tmp5 = Gateway.util;
                        Utilities.log(getClass().getName(), "{GetMt2EmsQueue Alert2YM Error}" + ex1.getMessage());
                    }
                }
        Utilities _tmp6 = Gateway.util;
        Utilities.log(getClass().getName(), "{" + getClass().getName() + " stopped}");
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public int AddEMS2QueueEx()
        throws DBException, EMSException
    {
        int numOfEms = 0;
        try
        {
            if(!dbTools.getAllEMSSendQueue(Preference.SEND_MODE, iMod, iNum, EMSQueue))
            {
                Utilities _tmp = Gateway.util;
                Utilities.logErr(getClass().getName(), "Loi load MT tu DB");
            }
        }
        catch(Exception ex)
        {
            Utilities _tmp1 = Gateway.util;
            Utilities.logErr(getClass().getName(), "{Exception:}" + ex.getMessage());
        }
        return numOfEms;
    }

    private Queue EMSQueue;
    private DBTools dbTools;
    private String iMod;
    private String iNum;
}
