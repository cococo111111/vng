// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpSetting.java

package com.vasc.ftp;


public class FtpSetting
{

    public FtpSetting()
    {
        serversystemmode = 1;
        listcommandmode = 1;
        filetransfermode = 'S';
        activesocketmode = false;
        securesocketmode = false;
    }

    public boolean getActiveSocketMode()
    {
        return activesocketmode;
    }

    public char getFileTransferMode()
    {
        return filetransfermode;
    }

    public int getListCommandMode()
    {
        return listcommandmode;
    }

    boolean getSecureSocketMode()
    {
        return securesocketmode;
    }

    int getServerSystemMode()
    {
        return serversystemmode;
    }

    public void setActiveSocketMode(boolean activesocketmode)
    {
        this.activesocketmode = activesocketmode;
    }

    public void setFileTransferMode(char filetransfermode)
    {
        this.filetransfermode = filetransfermode;
    }

    public void setListCommandMode(int listcommandmode)
    {
        this.listcommandmode = listcommandmode;
    }

    void setSecureSocketMode(boolean securesocketmode)
    {
        this.securesocketmode = securesocketmode;
    }

    void setServerSystemMode(int serversystemmode)
    {
        this.serversystemmode = serversystemmode;
    }

    public static final int LIST = 1;
    public static final int NAME_LIST = 2;
    public static final int NAME_LIST_LS_F = 4;
    public static final int NAME_LIST_LS_LA = 5;
    public static final int NAME_LIST_LS_P = 3;
    static final int UNIX = 1;
    static final int WIN = 2;
    private boolean activesocketmode;
    private char filetransfermode;
    private int listcommandmode;
    private boolean securesocketmode;
    private int serversystemmode;
}
