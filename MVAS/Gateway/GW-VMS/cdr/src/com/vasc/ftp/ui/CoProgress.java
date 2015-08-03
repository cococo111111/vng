// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoProgress.java

package com.vasc.ftp.ui;

import com.vasc.ftp.io.CoFile;

public interface CoProgress
{

    public abstract boolean isAborted();

    public abstract void setDelay(long l);

    public abstract void setFile(CoFile cofile, CoFile cofile1);

    public abstract void setFile(CoFile cofile);

    public abstract void setProgress(int i);
}
