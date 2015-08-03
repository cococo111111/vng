// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MsgQueue.java

package com.vasc.smpp.gateway;

import java.util.Vector;

public class MsgQueue
{

    public MsgQueue()
    {
        queue = new Vector();
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

    public Vector getVector()
    {
        return queue;
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public Object remove()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
          goto _L1
_L3:
        queue.wait();
        continue; /* Loop/switch isn't completed */
        InterruptedException ex;
        ex;
_L1:
        if(queue.isEmpty()) goto _L3; else goto _L2
_L2:
        Object item;
        item = queue.firstElement();
        queue.removeElement(item);
        return item;
        Exception exception;
        exception;
        throw exception;
    }

    public void setVector(Vector v)
    {
        queue = v;
    }

    protected Vector queue;
}
