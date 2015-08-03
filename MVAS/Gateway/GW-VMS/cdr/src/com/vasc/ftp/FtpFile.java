// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpFile.java

package com.vasc.ftp;

import com.vasc.ftp.io.CoFile;
import com.vasc.ftp.io.CoFilenameFilter;
import com.vasc.ftp.io.CoOrder;
import com.vasc.ftp.ui.CoConsole;
import java.io.*;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package com.vasc.ftp:
//            FtpInputStream, FtpOutputStream, FtpListInputStream, Ftp, 
//            FtpContext

public final class FtpFile
    implements CoFile
{

    public FtpFile(FtpFile dir, String name, Ftp client)
    {
        this(dir.toString(), name, client);
    }

    public FtpFile(String path, String name, Ftp client)
    {
        this.name = null;
        ext = null;
        this.client = null;
        access = null;
        owner = null;
        group = null;
        size = 0L;
        date = 0L;
        this.path = null;
        access = "f?????????";
        owner = "";
        group = "";
        size = -1L;
        date = 0L;
        if(path.endsWith("/"))
            this.path = path + name;
        else
            this.path = path + "/" + name;
        this.client = client;
        sortSetup(name);
    }

    public FtpFile(String path, Ftp client)
    {
        name = null;
        ext = null;
        this.client = null;
        access = null;
        owner = null;
        group = null;
        size = 0L;
        date = 0L;
        this.path = null;
        access = "d?????????";
        owner = "";
        group = "";
        size = -1L;
        date = 0L;
        if(path.compareTo("/") != 0 && path.endsWith("/"))
            this.path = path.substring(0, path.length() - 1);
        else
            this.path = path;
        this.client = client;
        sortSetup(getName());
    }

    private FtpFile()
    {
        name = null;
        ext = null;
        client = null;
        access = null;
        owner = null;
        group = null;
        size = 0L;
        date = 0L;
        path = null;
    }

    public boolean canRead()
    {
        return true;
    }

    public boolean canWrite()
    {
        return true;
    }

    public int compareExtToIgnoreCase(CoOrder file)
    {
        if(file instanceof FtpFile)
        {
            FtpFile l2 = (FtpFile)file;
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
        if(file instanceof FtpFile)
        {
            FtpFile l2 = (FtpFile)file;
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
        if(isDirectory())
        {
            client.cd(getParent());
            if(client.rmdir(path))
                return true;
            else
                return client.rm(path);
        }
        if(client.rm(path))
        {
            return true;
        } else
        {
            client.cd(getParent());
            return client.rmdir(path);
        }
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

    private static FtpFile examineListLine(FtpFile path, String line)
        throws NoSuchElementException
    {
        if("0123456789".indexOf(line.charAt(0)) < 0)
            return examineUnixListLine(path, line);
        else
            return examineWinListLine(path, line);
    }

    private static FtpFile examineNameListLine(FtpFile path, String line, int listtype)
        throws NoSuchElementException
    {
        FtpFile ff = new FtpFile();
        ff.client = path.client;
        switch(listtype)
        {
        default:
            break;

        case 2: // '\002'
            if(line.indexOf('.') != -1)
                ff.access = "-?????????";
            else
                ff.access = "d?????????";
            break;

        case 3: // '\003'
            if(line.endsWith("/"))
            {
                ff.access = "d?????????";
                line = line.substring(0, line.length() - 1);
            } else
            {
                ff.access = "-?????????";
            }
            break;

        case 4: // '\004'
            if(line.endsWith("/"))
            {
                ff.access = "d?????????";
                line = line.substring(0, line.length() - 1);
                break;
            }
            if(line.endsWith("*"))
            {
                ff.access = "-??x??x??x";
                line = line.substring(0, line.length() - 1);
                break;
            }
            if(line.endsWith("@"))
            {
                ff.access = "l?????????";
                line = line.substring(0, line.length() - 1);
            } else
            {
                ff.access = "-?????????";
            }
            break;
        }
        ff.owner = "";
        ff.group = "";
        ff.size = -1L;
        ff.date = 0L;
        ff.path = path.getAbsolutePath();
        if(!ff.path.endsWith("/"))
            ff.path = ff.path + '/' + line;
        else
            ff.path = ff.path + line;
        ff.sortSetup(line);
        if(ff.getName().compareTo(".") == 0 || ff.getName().compareTo("..") == 0)
            throw new NoSuchElementException("skip");
        else
            return ff;
    }

    private static long examineUnixListDate(String month, String day, String year2time)
    {
        Calendar c = Calendar.getInstance();
        month = month.toUpperCase();
        try
        {
            for(int m = 0; m < 12; m++)
            {
                if(!month.equals(months[m]))
                    continue;
                if(year2time.indexOf(':') != -1)
                {
                    c.setTime(new Date(System.currentTimeMillis()));
                    StringTokenizer toker = new StringTokenizer(year2time, ":");
                    c.set(c.get(1), m, Integer.parseInt(day), Integer.parseInt(toker.nextToken()), Integer.parseInt(toker.nextToken()));
                } else
                {
                    c.set(Integer.parseInt(year2time), m, Integer.parseInt(day), 0, 0);
                }
                break;
            }

        }
        catch(NumberFormatException e)
        {
            throw new NoSuchElementException("Unix-List: Invalid Date Format");
        }
        return c.getTime().getTime();
    }

    private static FtpFile examineUnixListLine(FtpFile path, String line)
        throws NoSuchElementException
    {
        FtpFile ff;
        ff = new FtpFile();
        ff.client = path.client;
        if(line.indexOf("->") >= 0)
            line = line.substring(0, line.indexOf("->"));
        StringTokenizer toker = new StringTokenizer(line);
        ff.access = toker.nextToken();
        toker.nextToken();
        ff.owner = toker.nextToken();
        ff.group = toker.nextToken();
        String size = toker.nextToken();
        if(size.endsWith(","))
            size = size.substring(0, size.indexOf(","));
        String uu = size;
        if(ff.access.startsWith("c"))
            uu = toker.nextToken();
        if("0123456789".indexOf(uu.charAt(0)) < 0)
        {
            size = ff.group;
            ff.group = "";
        }
        ff.size = Integer.parseInt(size);
        ff.date = examineUnixListDate("0123456789".indexOf(uu.charAt(0)) >= 0 ? toker.nextToken() : uu, toker.nextToken(), toker.nextToken());
        String name = toker.nextToken("").trim();
        ff.path = path.toString();
        if(!ff.path.endsWith("/"))
            ff.path = ff.path + '/' + name;
        else
            ff.path = ff.path + name;
        ff.sortSetup(name);
        break MISSING_BLOCK_LABEL_378;
        NumberFormatException e;
        e;
        throw new NoSuchElementException("Unix-List: Invalid Number Format");
        NoSuchElementException e;
        e;
        try
        {
            StringTokenizer toker = new StringTokenizer(line);
            if(!toker.nextToken().equals("total"))
                throw e;
            Long.parseLong(toker.nextToken());
            if(!toker.hasMoreTokens())
                throw new NoSuchElementException("skip");
            else
                throw e;
        }
        catch(NumberFormatException x)
        {
            throw e;
        }
        if(ff.getName().compareTo(".") == 0 || ff.getName().compareTo("..") == 0)
            throw new NoSuchElementException("skip");
        else
            return ff;
    }

    private static long examineWinListDate(String date, String time)
    {
        Calendar c = Calendar.getInstance();
        StringTokenizer toker = new StringTokenizer(date, "-");
        int m = Integer.parseInt(toker.nextToken());
        int d = Integer.parseInt(toker.nextToken());
        int y = Integer.parseInt(toker.nextToken());
        if(y >= 70)
            y += 1900;
        else
            y += 2000;
        toker = new StringTokenizer(time, ":APM");
        c.set(y, m, d, (time.endsWith("PM") ? 12 : 0) + Integer.parseInt(toker.nextToken()), Integer.parseInt(toker.nextToken()));
        break MISSING_BLOCK_LABEL_134;
        NumberFormatException e;
        e;
        throw new NoSuchElementException("Win-List: Invalid Date Format");
        return c.getTime().getTime();
    }

    private static FtpFile examineWinListLine(FtpFile path, String line)
        throws NoSuchElementException
    {
        FtpFile ff;
        ff = new FtpFile();
        ff.client = path.client;
        StringTokenizer toker = new StringTokenizer(line);
        ff.date = examineWinListDate(toker.nextToken(), toker.nextToken());
        String size2dir = toker.nextToken();
        if(size2dir.equals("<DIR>"))
        {
            ff.access = "d?????????";
            ff.size = -1L;
        } else
        {
            ff.access = "-?????????";
            ff.size = Long.parseLong(size2dir);
        }
        String name = toker.nextToken("").trim();
        ff.owner = "";
        ff.group = "";
        ff.path = path.toString();
        if(!ff.path.endsWith("/"))
            ff.path = ff.path + '/' + name;
        else
            ff.path = ff.path + name;
        ff.sortSetup(name);
        break MISSING_BLOCK_LABEL_213;
        NumberFormatException e;
        e;
        throw new NoSuchElementException("Win-List: Invalid Number Format");
        if(ff.getName().compareTo(".") == 0 || ff.getName().compareTo("..") == 0)
            throw new NoSuchElementException("skip");
        else
            return ff;
    }

    public boolean exists()
    {
        return false;
    }

    public String getAbsolutePath()
    {
        return path;
    }

    public String getAccess()
    {
        return access;
    }

    public CoConsole getConsole()
    {
        return client.getContext().getConsole();
    }

    public char getDataType()
    {
        if(client.getContext().getFileTransferMode() == 'S')
            return !equalsExtTo(client.getContext().getTextFilter()) ? 'I' : 'A';
        else
            return client.getContext().getFileTransferMode();
    }

    public String getGroup()
    {
        return group;
    }

    public String getHost()
    {
        String system = "";
        system = client.host();
        break MISSING_BLOCK_LABEL_49;
        IOException e;
        e;
        client.getContext().printlog("< File: Can't obtain host name. >\n" + e);
        return system;
    }

    public InputStream getInputStream()
        throws IOException
    {
        return new FtpInputStream(this);
    }

    public String getName()
    {
        if(path.lastIndexOf('/') >= 0)
            return path.substring(path.lastIndexOf('/') + 1);
        else
            return path;
    }

    public OutputStream getOutputStream(boolean append)
        throws IOException
    {
        return new FtpOutputStream(this, append);
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return new FtpOutputStream(this, false);
    }

    public String getOwner()
    {
        return owner;
    }

    public String getParent()
    {
        if(path.lastIndexOf('/') > 0)
            return path.substring(0, path.lastIndexOf('/'));
        else
            return new String("/");
    }

    public String[] getPathArray()
    {
        Vector dv;
        StringTokenizer toker;
        dv = new Vector();
        dv.addElement(getHost());
        toker = new StringTokenizer(path, "/");
        do
        {
            String d = toker.nextToken();
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
        int depth = -1;
        for(int length = -1; (length = path.indexOf('/', length + 1)) >= 0;)
            depth++;

        if(!path.endsWith("/"))
            depth++;
        return depth;
    }

    public CoFile getPathFragment(int depth)
    {
        if(depth > 0)
        {
            int length = -1;
            for(int n = 0; n <= depth; n++)
                if((length = path.indexOf('/', length + 1)) < 0)
                    break;

            if(length > 0)
                return new FtpFile(path.substring(0, length), client);
            else
                return this;
        } else
        {
            return new FtpFile("/", client);
        }
    }

    public boolean isAbsolute()
    {
        return path.charAt(0) == '/';
    }

    public boolean isConnected()
    {
        return client.isConnected();
    }

    public boolean isDirectory()
    {
        return access.charAt(0) == 'd';
    }

    public boolean isExecutable()
    {
        return access.indexOf('x') != -1;
    }

    public boolean isFile()
    {
        return access.charAt(0) == '-';
    }

    public boolean isHidden()
    {
        return false;
    }

    public boolean isLink()
    {
        return access.charAt(0) == 'l';
    }

    public boolean isSpecial()
    {
        return access.charAt(0) == 'c';
    }

    public long lastModified()
    {
        return date;
    }

    public String lastModifiedString()
    {
        return DateFormat.getDateTimeInstance(3, 3).format(new Date(lastModified()));
    }

    public long length()
    {
        return size;
    }

    public CoFile[] listCoFiles(CoFilenameFilter filter)
        throws SecurityException
    {
        FtpFile fs[] = (FtpFile[])listCoFiles();
        if(fs == null)
            return null;
        if(filter != null)
        {
            Vector fv = new Vector();
            for(int i = 0; i < fs.length; i++)
                if(filter.accept(this, fs[i].getName()))
                    fv.addElement(fs[i]);

            fs = new FtpFile[fv.size()];
            fv.copyInto(fs);
        }
        return fs;
    }

    public CoFile[] listCoFiles()
        throws SecurityException
    {
        FtpFile fs[];
        BufferedReader ibuf;
        int listtype;
        fs = null;
        ibuf = null;
        listtype = client.getContext().getListCommandMode();
        boolean error = false;
        Vector fv = new Vector();
        ibuf = new BufferedReader(new InputStreamReader(new FtpListInputStream(this)));
        String line;
        while((line = ibuf.readLine()) != null) 
        {
            try
            {
                switch(listtype)
                {
                default:
                    break;

                case 1: // '\001'
                    fv.addElement(examineListLine(this, line));
                    break;

                case 2: // '\002'
                    if(line.startsWith("/") && line.endsWith(":"))
                        break;
                    if(line.indexOf("//") != -1)
                        line = line.substring(line.lastIndexOf('/') + 1);
                    // fall through

                case 3: // '\003'
                case 4: // '\004'
                    if(line.length() > 0)
                        fv.addElement(examineNameListLine(this, line, listtype));
                    break;

                case 5: // '\005'
                    fv.addElement(examineUnixListLine(this, line));
                    break;
                }
            }
            catch(NoSuchElementException e)
            {
                if(!error && (e.getMessage() == null || !e.getMessage().equals("skip")))
                {
                    client.printlog("\n   < File: Invalid List Format ! >" + (e.getMessage() == null ? "" : "\n   " + e.getMessage()) + "\n   Line: " + line + "\n   Try: 'NAME_LIST' List Command");
                    error = true;
                }
            }
        }
        fs = new FtpFile[fv.size()];
        fv.copyInto(fs);
        IOException e;
        try
        {
            if(ibuf != null)
            {
                Reader r = ibuf;
                ibuf = null;
                r.close();
            }
        }
        // Misplaced declaration of an exception variable
        catch(IOException e)
        {
            client.printerr(e);
        }
        break MISSING_BLOCK_LABEL_468;
        e;
        client.printlog("< File: Can't list directory! >\n" + e);
        try
        {
            if(ibuf != null)
            {
                Reader r = ibuf;
                ibuf = null;
                r.close();
            }
        }
        catch(IOException e)
        {
            client.printerr(e);
        }
        break MISSING_BLOCK_LABEL_468;
        Exception exception;
        exception;
        try
        {
            if(ibuf != null)
            {
                Reader r = ibuf;
                ibuf = null;
                r.close();
            }
        }
        catch(IOException e)
        {
            client.printerr(e);
        }
        throw exception;
        return fs;
    }

    public CoFile[] listCoRoots()
    {
        CoFile fs[] = new FtpFile[1];
        fs[0] = getPathFragment(0);
        return fs;
    }

    public boolean mkdir()
        throws SecurityException
    {
        return client.mkdir(path);
    }

    public boolean mkdirs()
        throws SecurityException
    {
        boolean done = true;
        int depth = getPathDepth();
        for(int i = 0; i < depth; i++)
            if(!((FtpFile)getPathFragment(i)).mkdir())
                done = false;
            else
                done = true;

        return done;
    }

    public CoFile newFileChild(String child)
    {
        return new FtpFile(this, child, client);
    }

    public CoFile newFileRename(String name)
    {
        return new FtpFile(getParent(), name, client);
    }

    public String propertyString()
    {
        String desc = getAccess() + " " + getOwner() + " " + getGroup();
        return isFile() ? "" + length() + " " + desc : desc;
    }

    public boolean renameTo(CoFile dest)
        throws SecurityException
    {
        return client.mv(path, dest.getAbsolutePath());
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

    private static final char FILE = 45;
    private static final char FOLDER = 100;
    private static final char LINK = 108;
    private static final char SPECIAL = 99;
    private String access;
    Ftp client;
    private long date;
    private String ext;
    private String group;
    private static final String months[] = {
        "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", 
        "NOV", "DEC"
    };
    private String name;
    private String owner;
    String path;
    private long size;

}
