// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Queue.java

package com.vmg.sms.common;

import java.io.PrintStream;
import java.util.Vector;

public class Queue
{

    public Queue()
    {
        queue = new Vector();
    }

    public Object dequeue()
    {
        int i = 0;
        Vector vector = queue;
        JVM INSTR monitorenter ;
        Object item;
        while(queue.isEmpty()) 
            try
            {
                queue.wait(1000L);
                i++;
                System.out.println("wait until other thread call notify(): " + i);
            }
            catch(InterruptedException interruptedexception) { }
        item = queue.firstElement();
        queue.removeElement(item);
        return item;
        vector;
        JVM INSTR monitorexit ;
        throw ;
    }

    public void enqueue(Object item)
    {
        synchronized(queue)
        {
            queue.addElement(item);
            queue.notifyAll();
        }
    }

    public int size()
    {
        return queue.size();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    protected Vector queue;
}
