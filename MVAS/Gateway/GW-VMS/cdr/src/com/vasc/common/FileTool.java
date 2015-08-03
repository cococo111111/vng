// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileTool.java

package com.vasc.common;

import java.io.*;
import java.util.Vector;

// Referenced classes of package com.vasc.common:
//            FileCopyException

public class FileTool
{

    public FileTool()
    {
    }

    public static void copy(File source_file, File dest_file)
        throws IOException
    {
        FileInputStream source = null;
        FileOutputStream destination = null;
        if(!source_file.exists() || !source_file.isFile())
            throw new FileCopyException("FileCopy: no such source file: " + source_file.getPath());
        if(!source_file.canRead())
            throw new FileCopyException("FileCopy: source file is unreadable: " + source_file.getPath());
        source = new FileInputStream(source_file);
        destination = new FileOutputStream(dest_file);
        byte buffer[] = new byte[1024];
        int i;
        while((i = source.read(buffer)) != -1) 
            destination.write(buffer, 0, i);
        try
        {
            if(source != null)
                source.close();
            if(destination != null)
                destination.close();
        }
        catch(IOException e) { }
    }

    public static void copy(String source_name, String dest_name)
        throws IOException
    {
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);
        copy(source_file, dest_file);
    }

    public static Vector getAllFiles(File location, String fileExt)
    {
        Vector v = new Vector();
        File list[] = location.listFiles();
        if(list != null && list.length > 0)
        {
            for(int i = 0; i < list.length; i++)
            {
                if(list[i].toString().endsWith(fileExt))
                    v.addElement(list[i]);
            }

        }
        return v;
    }

    public static void main(String args[])
    {
        FileTool fTool = new FileTool();
        String s = "..\\TEST";
        File f = new File(s);
        FileTool _tmp = fTool;
        Vector v = getAllFiles(f, ".txt");
        System.out.println("Size: " + v.size());
    }

    public static void move(File source_file, File dest_file)
        throws IOException
    {
        copy(source_file, dest_file);
    }

    public static void move(String source_name, String dest_name)
        throws IOException
    {
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);
        copy(source_file, dest_file);
        source_file.delete();
    }

    public static void moveVMS(File source_file, File dest_file)
        throws IOException
    {
        copy(source_file, dest_file);
    }

    public static byte[] objectToByte(Object object)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.close();
        byte buf[] = bos.toByteArray();
        return buf;
    }

    public static byte[] readFile(String filename)
        throws IOException
    {
        byte buffer[] = null;
        FileInputStream fin = new FileInputStream(filename);
        buffer = new byte[fin.available()];
        fin.read(buffer);
        return buffer;
    }

    public static Object readObjectFromFile(String filename)
        throws IOException, ClassNotFoundException
    {
        File file = new File(filename);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Object object = in.readObject();
        in.close();
        return object;
    }

    public static void saveObjectToFile(Object object, String filename)
        throws IOException
    {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(object);
        out.close();
    }

    public static void saveToFile(byte output[], String filename)
        throws IOException
    {
        File f = new File(filename);
        FileOutputStream out = new FileOutputStream(f);
        out.write(output);
        out.close();
    }
}
