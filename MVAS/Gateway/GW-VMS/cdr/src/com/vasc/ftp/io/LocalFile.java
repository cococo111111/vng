// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalFile.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoConsole;
import java.io.*;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package com.vasc.ftp.io:
//            CoFile, CoFilenameFilter, CoOrder

public final class LocalFile extends File
    implements CoFile
{

    public LocalFile(LocalFile dir, String name)
    {
        super(dir, name);
        this.name = null;
        ext = null;
        sortSetup(name);
    }

    public LocalFile(String path, String name)
    {
        super(path, name);
        this.name = null;
        ext = null;
        sortSetup(name);
    }

    public LocalFile(String path)
    {
        super(path);
        name = null;
        ext = null;
        sortSetup(getName());
    }

    public boolean canRead()
    {
        return super.canRead();
    }

    public boolean canWrite()
    {
        return super.canWrite();
    }

    public int compareExtToIgnoreCase(CoOrder file)
    {
        if(file instanceof LocalFile)
        {
            LocalFile l2 = (LocalFile)file;
            int result = ext.compareTo(l2.ext);
            if(result == 0)
                result = name.compareTo(l2.name);
            return result;
        } else
        {
            throw new ClassCastException();
        }
    }

    public int compareNameToIgnoreCase(CoOrder file)
    {
        if(file instanceof LocalFile)
        {
            LocalFile l2 = (LocalFile)file;
            return name.compareTo(l2.name);
        } else
        {
            throw new ClassCastException();
        }
    }

    public int compareTo(Object o)
    {
        String s1 = getHost() + getAbsolutePath();
        String s2;
        if(o instanceof CoFile)
        {
            CoFile f2 = (CoFile)o;
            s2 = f2.getHost() + f2.getAbsolutePath();
        } else
        if(o instanceof String)
            s2 = (String)o;
        else
            throw new ClassCastException();
        return s1.compareTo(s2);
    }

    public boolean delete()
        throws SecurityException
    {
        return super.delete();
    }

    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        else
            return compareTo(o) == 0;
    }

    public boolean equalsExtTo(String filter[])
    {
        boolean done = false;
        for(int j = 0; j < filter.length; j++)
        {
            if(ext.compareTo(filter[j]) != 0)
                continue;
            done = true;
            break;
        }

        return done;
    }

    public boolean equalsExtTo(String filter)
    {
        return ext.compareTo(filter) == 0;
    }

    public boolean exists()
    {
        return super.exists();
    }

    public String getAccess()
    {
        String access = null;
        if(isDirectory())
            access = "d";
        else
            access = "-";
        if(canRead())
            access = access + "r";
        else
            access = access + "-";
        if(canWrite())
            access = access + "w?";
        else
            access = access + "-?";
        return access;
    }

    public CoConsole getConsole()
    {
        return null;
    }

    public char getDataType()
    {
        return 'I';
    }

    public String getHost()
    {
        return "";
    }

    public InputStream getInputStream()
        throws IOException
    {
        return new FileInputStream(this);
    }

    public String getName()
    {
        return super.getName();
    }

    public OutputStream getOutputStream(boolean append)
        throws IOException
    {
        return new FileOutputStream(toString(), append);
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return new FileOutputStream(this);
    }

    public String getParent()
    {
        return super.getParent();
    }

    public String[] getPathArray()
    {
        Vector dv;
        StringTokenizer tokenizer;
        dv = new Vector();
        String path = getAbsolutePath();
        if(path == null)
            break MISSING_BLOCK_LABEL_57;
        tokenizer = new StringTokenizer(path, File.separator);
        do
        {
            String d = tokenizer.nextToken();
            dv.addElement(d);
        } while(true);
        NoSuchElementException e;
        e;
        String ds[] = new String[dv.size()];
        dv.copyInto(ds);
        return ds;
    }

    public int getPathDepth()
    {
        String path = getAbsolutePath();
        int depth = -1;
        for(int length = -1; (length = path.indexOf(File.separatorChar, length + 1)) >= 0;)
            depth++;

        if(!path.endsWith(File.separator))
            depth++;
        return depth;
    }

    public CoFile getPathFragment(int depth)
    {
        String path = getAbsolutePath();
        if(depth > 0)
        {
            int length = -1;
            for(int n = 0; n <= depth; n++)
                if((length = path.indexOf(File.separatorChar, length + 1)) < 0)
                    break;

            if(length > 0)
                return new LocalFile(path.substring(0, length));
            else
                return this;
        } else
        {
            return new LocalFile(path.substring(0, path.indexOf(File.separatorChar) + 1));
        }
    }

    public boolean isAbsolute()
    {
        return super.isAbsolute();
    }

    public boolean isConnected()
    {
        return true;
    }

    public boolean isDirectory()
    {
        return super.isDirectory();
    }

    public boolean isFile()
    {
        return super.isFile();
    }

    public boolean isLink()
    {
        return false;
    }

    public boolean isSpecial()
    {
        return false;
    }

    public long lastModified()
    {
        return super.lastModified();
    }

    public String lastModifiedString()
    {
        return DateFormat.getDateTimeInstance(3, 3).format(new Date(lastModified()));
    }

    public long length()
    {
        return super.length();
    }

    public CoFile[] listCoFiles(CoFilenameFilter filter)
        throws SecurityException
    {
        LocalFile fs[] = (LocalFile[])listCoFiles();
        if(fs == null)
            return null;
        if(filter != null)
        {
            Vector fv = new Vector();
            for(int i = 0; i < fs.length; i++)
                if(filter.accept(this, fs[i].getName()))
                    fv.addElement(fs[i]);

            fs = new LocalFile[fv.size()];
            fv.copyInto(fs);
        }
        return fs;
    }

    public CoFile[] listCoFiles()
        throws SecurityException
    {
        String ss[] = list();
        if(ss == null)
            return null;
        CoFile fs[] = new LocalFile[ss.length];
        for(int i = 0; i < ss.length; i++)
            fs[i] = new LocalFile(getAbsolutePath(), ss[i]);

        return fs;
    }

    public CoFile[] listCoRoots()
    {
        File ls[] = File.listRoots();
        if(ls == null)
            return null;
        CoFile fs[] = new LocalFile[ls.length];
        for(int i = 0; i < ls.length; i++)
            fs[i] = new LocalFile(ls[i].getAbsolutePath());

        return fs;
    }

    public boolean mkdir()
        throws SecurityException
    {
        return super.mkdir();
    }

    public boolean mkdirs()
        throws SecurityException
    {
        return super.mkdirs();
    }

    public CoFile newFileChild(String child)
    {
        return new LocalFile(this, child);
    }

    public CoFile newFileRename(String name)
    {
        return new LocalFile(getParent(), name);
    }

    public String propertyString()
    {
        return (isFile() ? "" + length() + " " : "") + getAccess();
    }

    public boolean renameTo(CoFile dest)
        throws SecurityException
    {
        return super.renameTo((File)dest);
    }

    private void sortSetup(String name)
    {
        this.name = name.toUpperCase();
        int index = this.name.lastIndexOf(".");
        if(index != -1 && index < this.name.length())
            ext = this.name.substring(index);
        else
            ext = " " + this.name;
    }

    public boolean startsWithIgnoreCase(char ch)
    {
        return name.charAt(0) == Character.toUpperCase(ch);
    }

    public String toString()
    {
        return getAbsolutePath();
    }

    private String ext;
    private String name;
}
