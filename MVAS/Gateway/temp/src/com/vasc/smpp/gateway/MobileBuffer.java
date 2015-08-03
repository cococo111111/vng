// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MobileBuffer.java

package com.vasc.smpp.gateway;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.vasc.smpp.gateway:
//            MobileBufferInfo

public class MobileBuffer
    implements Serializable
{

    public MobileBuffer()
    {
        hashmap = new HashMap();
    }

    public static void add(String mobile, MobileBufferInfo info)
    {
        int h = mobile.hashCode();
        int bucket = h & 0xf;
        bufferArray[bucket].addInternal(mobile, info);
    }

    private void addInternal(String theKey, MobileBufferInfo theVal)
    {
        if(theKey == null || theVal == null)
            return;
        synchronized(hashmap)
        {
            hashmap.put(theKey, theVal);
        }
    }

    public static void clearAll()
    {
        for(int i = 0; i < bufferArray.length; i++)
        {
            System.out.println("Clearing buffer " + i);
            bufferArray[i].clearInternal();
        }

        DAY_IN_BUFFER = today();
    }

    private void clearInternal()
    {
        synchronized(hashmap)
        {
            hashmap.clear();
        }
    }

    public static MobileBufferInfo lookup(String mobile)
    {
        int h = mobile.hashCode();
        int bucket = h & 0xf;
        return bufferArray[bucket].lookupInternal(mobile);
    }

    private MobileBufferInfo lookupInternal(String theKey)
    {
        MobileBufferInfo theVal = null;
        synchronized(hashmap)
        {
            theVal = (MobileBufferInfo)hashmap.get(theKey);
        }
        return theVal;
    }

    public static void remove(String mobile)
    {
        int h = mobile.hashCode();
        int bucket = h & 0xf;
        bufferArray[bucket].removeInternal(mobile);
    }

    private void removeInternal(String theKey)
    {
        if(theKey == null)
            return;
        synchronized(hashmap)
        {
            hashmap.remove(theKey);
        }
    }

    public static int size()
    {
        int size = 0;
        for(int i = 0; i < bufferArray.length; i++)
        {
            System.out.println("size of buffer " + i + ": " + bufferArray[i].sizeInternal());
            size += bufferArray[i].sizeInternal();
        }

        return size;
    }

    private int sizeInternal()
    {
        Map map = hashmap;
        JVM INSTR monitorenter ;
        return hashmap.size();
        Exception exception;
        exception;
        throw exception;
    }

    private static int today()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar _tmp = calendar;
        return calendar.get(5);
    }

    public static void update(String mobile, MobileBufferInfo info)
    {
        int h = mobile.hashCode();
        int bucket = h & 0xf;
        bufferArray[bucket].updateInternal(mobile, info);
    }

    private void updateInternal(String theKey, MobileBufferInfo theVal)
    {
        if(theKey == null || theVal == null)
            return;
        synchronized(hashmap)
        {
            hashmap.put(theKey, theVal);
        }
    }

    public static int DAY_IN_BUFFER = today();
    static final int NUM_OF_BUFFERS = 16;
    static final MobileBuffer bufferArray[];
    private Map hashmap;

    static 
    {
        bufferArray = new MobileBuffer[16];
        System.out.print("[MobileBuffer] initializing 16 buffers...");
        for(int idx = bufferArray.length - 1; idx >= 0; idx--)
            bufferArray[idx] = new MobileBuffer();

        System.out.println("OK");
    }
}
