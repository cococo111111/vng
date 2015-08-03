// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileTool.java

package com.vmg.common;

import java.io.*;
import java.util.Vector;

public class FileTool
{

    public FileTool()
    {
    }

    public static Vector getAllFiles(File location, String fileExt)
    {
        Vector v = new Vector();
        File list[] = location.listFiles();
        for(int i = 0; i < list.length; i++)
            if(list[i].toString().endsWith(fileExt))
                v.addElement(list[i]);

        return v;
    }

    public static byte[] readFile(String filename)
        throws IOException
    {
        byte buffer[] = (byte[])null;
        FileInputStream fin = new FileInputStream(filename);
        buffer = new byte[fin.available()];
        fin.read(buffer);
        return buffer;
    }

    public static void saveToFile(byte output[], String filename)
    {
        try
        {
            File f = new File(filename);
            FileOutputStream out = new FileOutputStream(f);
            out.write(output);
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[])
    {
        FileTool fTool = new FileTool();
        String s = "..\\TEST";
        File f = new File(s);
        Vector v = getAllFiles(f, ".txt");
        System.out.println("Size: " + v.size());
    }
}
