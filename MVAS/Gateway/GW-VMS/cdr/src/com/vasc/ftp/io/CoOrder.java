// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoOrder.java

package com.vasc.ftp.io;


public interface CoOrder
    extends Comparable
{

    public abstract int compareExtToIgnoreCase(CoOrder coorder);

    public abstract int compareNameToIgnoreCase(CoOrder coorder);

    public abstract int compareTo(Object obj);

    public abstract boolean equalsExtTo(String as[]);

    public abstract boolean equalsExtTo(String s);

    public abstract boolean isConnected();

    public abstract boolean startsWithIgnoreCase(char c);
}
