// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MsgQueue.java

package com.vmg.sms.process;

import java.util.Vector;

public class MsgQueue
{

    public MsgQueue()
    {
        queue = new Vector();
    }

    public Object remove()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
        Object item;
        while(queue.isEmpty()) 
            try
            {
                queue.wait();
            }
            catch(InterruptedException interruptedexception) { }
        item = queue.firstElement();
        queue.removeElement(item);
        return item;
        vector;
        JVM INSTR monitorexit ;
        throw ;
    }

    public void add(Object item)
    {
        synchronized(queue)
        {
            queue.addElement(item);
            queue.notify();
        }
    }

    public long getSize()
    {
        return (long)queue.size();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public void setVector(Vector v)
    {
        queue = v;
    }

    public Vector getVector()
    {
        return queue;
    }

    protected Vector queue;
}
