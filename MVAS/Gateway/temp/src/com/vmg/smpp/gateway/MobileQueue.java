// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MobileQueue.java

package com.vmg.smpp.gateway;

import java.io.Serializable;
import java.util.Vector;

// Referenced classes of package com.vmg.smpp.gateway:
//            MobileQueueInfo

public class MobileQueue
    implements Serializable
{

    public MobileQueue()
    {
        queue = new Vector();
    }

    public void add(String mobile, long time)
    {
        add(new MobileQueueInfo(mobile, time));
    }

    public void add(MobileQueueInfo info)
    {
        if(info == null)
            return;
        synchronized(queue)
        {
            if(queue.size() >= 20)
                queue.removeElementAt(0);
            queue.add(info);
        }
    }

    public void update(String mobile, long time)
    {
        update(new MobileQueueInfo(mobile, time));
    }

    public void update(MobileQueueInfo info)
    {
        if(info == null)
        {
            return;
        } else
        {
            remove(info.mobile);
            add(info);
            return;
        }
    }

    public long lookup(String mobile)
    {
        return getTime(mobile);
    }

    public long getTime(String mobile)
    {
        if(mobile == null)
            return -1L;
        long time = 0L;
        synchronized(queue)
        {
            for(int i = 0; i < queue.size(); i++)
            {
                MobileQueueInfo info = (MobileQueueInfo)queue.elementAt(i);
                if(!mobile.equals(info.mobile))
                    continue;
                time = info.time;
                break;
            }

        }
        return time;
    }

    public void remove(String theKey)
    {
        if(theKey == null)
            return;
        synchronized(queue)
        {
            for(int i = 0; i < queue.size(); i++)
            {
                MobileQueueInfo info = (MobileQueueInfo)queue.elementAt(i);
                if(!theKey.equals(info.mobile))
                    continue;
                queue.removeElementAt(i);
                break;
            }

        }
    }

    public int size()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
        return queue.size();
        vector;
        JVM INSTR monitorexit ;
        throw ;
    }

    public boolean isEmpty()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
        return queue.isEmpty();
        vector;
        JVM INSTR monitorexit ;
        throw ;
    }

    public static void main(String args[])
    {
        MobileQueue queue = new MobileQueue();
        queue.add(new MobileQueueInfo("84904060008", 128L));
        queue.add(new MobileQueueInfo("84989152696", 127L));
    }

    static final int MAX_QUEUE_SIZE = 20;
    Vector queue;
}
