// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoFile.java

package com.vasc.ftp.io;


// Referenced classes of package com.vasc.ftp.io:
//            CoOrder, CoOpen, CoFilenameFilter

public interface CoFile
    extends CoOrder, CoOpen
{

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract boolean delete()
        throws SecurityException;

    public abstract boolean exists();

    public abstract String getAbsolutePath();

    public abstract String getAccess();

    public abstract String getHost();

    public abstract String getName();

    public abstract String getParent();

    public abstract String[] getPathArray();

    public abstract int getPathDepth();

    public abstract CoFile getPathFragment(int i);

    public abstract boolean isAbsolute();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isHidden();

    public abstract boolean isLink();

    public abstract boolean isSpecial();

    public abstract long lastModified();

    public abstract String lastModifiedString();

    public abstract long length();

    public abstract CoFile[] listCoFiles(CoFilenameFilter cofilenamefilter)
        throws SecurityException;

    public abstract CoFile[] listCoFiles()
        throws SecurityException;

    public abstract CoFile[] listCoRoots();

    public abstract boolean mkdir()
        throws SecurityException;

    public abstract boolean mkdirs()
        throws SecurityException;

    public abstract String propertyString();

    public abstract boolean renameTo(CoFile cofile)
        throws SecurityException;

    public abstract String toString();
}
