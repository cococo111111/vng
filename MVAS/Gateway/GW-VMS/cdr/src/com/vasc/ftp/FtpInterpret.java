// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpInterpret.java

package com.vasc.ftp;

import java.util.Hashtable;

final class FtpInterpret
{

    protected FtpInterpret()
    {
    }

    static boolean allowExecution(String commandline)
    {
        return !startsWith(commandline, notAllow);
    }

    static boolean allowManualExecution(String commandline)
    {
        return !startsWith(commandline, notManual);
    }

    static String getCommand(String commandline)
    {
        return commandline.substring(0, commandline.indexOf(" ") < 0 || commandline.indexOf(" ") > 4 ? commandline.length() : commandline.indexOf(" "));
    }

    static String[] getReplies(String commandline)
    {
        String command = getCommand(commandline);
        String replies[] = (String[])library.get(command);
        if(replies == null)
            replies = new String[0];
        return replies;
    }

    static void putReplies(String command, String replies[])
    {
        library.put(command, replies);
    }

    static boolean startsWith(String string, String prefixes[])
    {
        boolean done = false;
        for(int i = 0; i < prefixes.length; i++)
        {
            if(string.indexOf(prefixes[i]) != 0)
                continue;
            done = true;
            break;
        }

        return done;
    }

    private static final Hashtable library = new Hashtable();
    private static final String notAllow[];
    private static final String notManual[];

    static 
    {
        String loginok[] = {
            "220"
        };
        putReplies("login-done", loginok);
        String userok[] = {
            "230", "331"
        };
        putReplies("USER", userok);
        String skipok[] = {
            "230"
        };
        putReplies("USER-done", skipok);
        String passok[] = {
            "230", "202"
        };
        putReplies("PASS", passok);
        String listok[] = {
            "125", "150"
        };
        putReplies("RETR", listok);
        putReplies("STOR", listok);
        putReplies("STOU", listok);
        putReplies("APPE", listok);
        putReplies("LIST", listok);
        putReplies("NLST", listok);
        String doneok[] = {
            "226", "250"
        };
        putReplies("data-done", doneok);
        String aborok[] = {
            "225"
        };
        putReplies("ABOR", aborok);
        String typeok[] = {
            "200"
        };
        putReplies("TYPE", typeok);
        putReplies("PORT", typeok);
        String cwdok[] = {
            "250"
        };
        putReplies("CWD", cwdok);
        putReplies("CDUP", cwdok);
        putReplies("RMD", cwdok);
        putReplies("DELE", cwdok);
        String rnfrok[] = {
            "350"
        };
        putReplies("RNFR", rnfrok);
        putReplies("RNTO", cwdok);
        String mkdok[] = {
            "257"
        };
        putReplies("MKD", mkdok);
        putReplies("PWD", mkdok);
        String systok[] = {
            "215"
        };
        putReplies("SYST", systok);
        String quitok[] = {
            "221"
        };
        putReplies("QUIT", quitok);
        String pasvok[] = {
            "227"
        };
        putReplies("PASV", pasvok);
        String nA[] = {
            "REST", "MODE", "STRU"
        };
        notAllow = nA;
        String nM[] = {
            "PORT", "TYPE", "RETR", "STOR", "STOU", "APPE", "LIST", "NLST", "USER", "PASS", 
            "QUIT", "PASV"
        };
        notManual = nM;
    }
}
