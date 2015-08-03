// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Queue.java

package org.smpp.util;

import java.util.*;
import org.smpp.SmppObject;

public class Queue extends SmppObject
{

    public Queue()
    {
        maxQueueSize = 0;
        queueData = new LinkedList();
        mutex = this;
    }

    public Queue(int maxSize)
    {
        maxQueueSize = 0;
        queueData = new LinkedList();
        maxQueueSize = maxSize;
        mutex = this;
    }

    public int size()
    {
        Object obj = mutex;
        JVM INSTR monitorenter ;
        return queueData.size();
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isEmpty()
    {
        Object obj = mutex;
        JVM INSTR monitorenter ;
        return queueData.isEmpty();
        Exception exception;
        exception;
        throw exception;
    }

    public Object dequeue()
    {
        Object obj = mutex;
        JVM INSTR monitorenter ;
        Object first;
        first = null;
        if(size() > 0)
            first = queueData.removeFirst();
        return first;
        Exception exception;
        exception;
        throw exception;
    }

    public Object dequeue(Object obj)
    {
        Object found = null;
        synchronized(mutex)
        {
            found = find(obj);
            if(found != null)
                queueData.remove(found);
        }
        return found;
    }

    public void enqueue(Object obj)
        throws IndexOutOfBoundsException
    {
        synchronized(mutex)
        {
            if(maxQueueSize > 0 && size() >= maxQueueSize)
                throw new IndexOutOfBoundsException("Queue is full. Element not added.");
            queueData.add(obj);
        }
    }

    public Object find(Object obj)
    {
        Object obj1 = mutex;
        JVM INSTR monitorenter ;
        ListIterator iter = queueData.listIterator(0);
          goto _L1
_L3:
        Object current = iter.next();
        if(current.equals(obj))
            return current;
_L1:
        if(iter.hasNext()) goto _L3; else goto _L2
_L2:
        obj1;
        JVM INSTR monitorexit ;
          goto _L4
        Exception exception;
        exception;
        throw exception;
_L4:
        return null;
    }

    private int maxQueueSize;
    private LinkedList queueData;
    private Object mutex;
}
