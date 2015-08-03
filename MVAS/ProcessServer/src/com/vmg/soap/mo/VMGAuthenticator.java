// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VMGAuthenticator.java

package com.vmg.soap.mo;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class VMGAuthenticator extends Authenticator
{

    public VMGAuthenticator(String user, String password)
    {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, password.toCharArray());
    }

    String user;
    String password;
}
