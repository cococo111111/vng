// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Timer.java

package com.vmg.common.util;

import java.io.PrintStream;

final class Timer
{

    public Timer()
    {
        reset();
    }

    public void start()
    {
        System.gc();
        start = System.currentTimeMillis();
    }

    public void end()
    {
        System.gc();
        end = System.currentTimeMillis();
    }

    public long duration()
    {
        return end - start;
    }

    public void reset()
    {
        start = 0L;
        end = 0L;
    }

    public static void main(String s[])
    {
        Timer t = new Timer();
        t.start();
        for(int i = 0; i < 80; i++)
            System.out.print(".");

        t.end();
        System.out.println("\n" + t.duration());
    }

    private long start;
    private long end;
}
