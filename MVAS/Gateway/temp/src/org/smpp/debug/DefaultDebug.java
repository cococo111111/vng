// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultDebug.java

package org.smpp.debug;

import java.io.PrintStream;

// Referenced classes of package org.smpp.debug:
//            Debug

public class DefaultDebug
    implements Debug
{

    public DefaultDebug()
    {
        indent = 0;
        active = false;
    }

    public void enter(int group, Object from, String name)
    {
        enter(from, name);
    }

    public void enter(Object from, String name)
    {
        if(active)
        {
            System.out.println(getDelimiter(true, from, name));
            indent++;
        }
    }

    public void write(int group, String msg)
    {
        write(msg);
    }

    public void write(String msg)
    {
        if(active)
            System.out.println(getIndent() + " " + msg);
    }

    public void exit(int group, Object from)
    {
        exit(from);
    }

    public void exit(Object from)
    {
        if(active)
        {
            indent--;
            if(indent < 0)
                indent = 0;
            System.out.println(getDelimiter(false, from, ""));
        }
    }

    public void activate()
    {
        active = true;
    }

    public void activate(int i)
    {
    }

    public void deactivate()
    {
        active = false;
    }

    public void deactivate(int i)
    {
    }

    public boolean active(int group)
    {
        return true;
    }

    private String getDelimiter(boolean start, Object from, String name)
    {
        String indentStr = getIndent();
        if(start)
            indentStr = indentStr + "-> ";
        else
            indentStr = indentStr + "<- ";
        return indentStr + from.toString() + (name != "" ? " " + name : "");
    }

    private String getIndent()
    {
        String result = new String("");
        for(int i = 0; i < indent; i++)
            result = result + "  ";

        return result;
    }

    private int indent;
    private boolean active;
}
