// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CdrFilename4vms.java

package com.vasc.smpp.cdr;


class CdrFilenameException extends Exception
{

    public CdrFilenameException(String message)
    {
        this.message = "";
        this.message = message;
    }

    public CdrFilenameException()
    {
        message = "";
        message = "";
    }

    public String getMessage()
    {
        return message;
    }

    String message;
}
