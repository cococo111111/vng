// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Queue.java

package com.vasc.common.util;

import java.util.Vector;

public class Queue
{

    public Queue()
    {
        queue = new Vector();
    }

    public Object dequeue()
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

    public void enqueue(Object item)
    {
        synchronized(queue)
        {
            queue.addElement(item);
            queue.notifyAll();
        }
    }

    public boolean isEmpty()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
        return queue.isEmpty();
        Exception exception;
        exception;
        throw exception;
    }

    public int size()
    {
        Vector vector = queue;
        JVM INSTR monitorenter ;
        return queue.size();
        Exception exception;
        exception;
        throw exception;
    }

    protected Vector queue;
}
