// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Event.java

package org.smpp.debug;


public interface Event
{

    public abstract void write(String s);

    public abstract void write(Exception exception, String s);

    public abstract void activate();

    public abstract void deactivate();
}
