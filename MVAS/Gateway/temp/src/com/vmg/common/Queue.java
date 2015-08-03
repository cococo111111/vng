// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Queue.java

package com.vmg.common;

import java.util.LinkedList;

public class Queue
{

    public Queue()
    {
        queue = new LinkedList();
    }

    public Object dequeue()
    {
        LinkedList linkedlist = queue;
        JVM INSTR monitorenter ;
        Object item;
        while(queue.isEmpty()) 
            try
            {
                queue.wait();
            }
            catch(InterruptedException interruptedexception) { }
        item = queue.removeFirst();
        return item;
        linkedlist;
        JVM INSTR monitorexit ;
        throw ;
    }

    public void enqueue(Object item)
    {
        synchronized(queue)
        {
            queue.addLast(item);
            queue.notify();
        }
    }

    public int size()
    {
        LinkedList linkedlist = queue;
        JVM INSTR monitorenter ;
        return queue.size();
        linkedlist;
        JVM INSTR monitorexit ;
        throw ;
    }

    public boolean isEmpty()
    {
        LinkedList linkedlist = queue;
        JVM INSTR monitorenter ;
        return queue.isEmpty();
        linkedlist;
        JVM INSTR monitorexit ;
        throw ;
    }

    protected LinkedList queue;
}
