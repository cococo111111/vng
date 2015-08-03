// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Debug.java

package org.smpp.debug;


public interface Debug
{

    public abstract void enter(int i, Object obj, String s);

    public abstract void enter(Object obj, String s);

    public abstract void write(int i, String s);

    public abstract void write(String s);

    public abstract void exit(int i, Object obj);

    public abstract void exit(Object obj);

    public abstract void activate();

    public abstract void activate(int i);

    public abstract void deactivate();

    public abstract void deactivate(int i);

    public abstract boolean active(int i);
}
