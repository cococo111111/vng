// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirTool.java

package com.vasc.common;

import java.io.File;

public class DirTool
{

    public DirTool()
    {
    }

    public static boolean createAllDirectory(String dirName)
    {
        boolean success = (new File(dirName)).mkdirs();
        return success;
    }

    public static boolean createOneDirectory(String dirName)
    {
        boolean success = (new File(dirName)).mkdir();
        return success;
    }

    public static boolean deleteDirectory(File dir)
    {
        if(dir.isDirectory())
        {
            String children[] = dir.list();
            for(int i = 0; i < children.length; i++)
            {
                boolean success = deleteDirectory(new File(dir, children[i]));
                if(!success)
                    return false;
            }

        }
        return dir.delete();
    }

    public static boolean deleteEmptyDirectory(String dirName)
    {
        boolean success = (new File(dirName)).delete();
        return success;
    }

    public static String getWorkingDirectory()
    {
        String curDir = System.getProperty("user.dir");
        return curDir;
    }

    public static boolean isExist(String dirName)
    {
        boolean exists = (new File(dirName)).exists();
        return exists;
    }
}
