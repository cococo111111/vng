// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadConfig.java

package com.vmg.smpp.gateway;

import java.util.*;

// Referenced classes of package com.vmg.smpp.gateway:
//            Keyword, Logger, Gateway

public class LoadConfig extends Thread
{

    public LoadConfig()
    {
        isLoaded = false;
    }

    public Keyword getKeyword(String keyword, String serviceid)
    {
        Keyword retobj = new Keyword();
        retobj.setClassname("INV");
        retobj.setKeyword("INV");
        retobj.setServiceid(serviceid);
        String keytosearch = serviceid + "@" + keyword;
        String strkey = "INV";
        for(Iterator it = vtKeyword.iterator(); it.hasNext();)
        {
            String currLabel = (String)it.next();
            if(keytosearch.startsWith(currLabel))
            {
                strkey = currLabel;
                retobj = (Keyword)keywords.get(strkey);
                Logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "} {keyword=" + retobj.getKeyword() + "}");
                return retobj;
            }
        }

        Logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "} {keyword=" + retobj.getKeyword() + "}");
        return retobj;
    }

    public void run()
    {
        Logger.info("LoadConfig - Start");
        isLoaded = false;
        while(Gateway.running) 
            try
            {
                keywords = Keyword.retrieveKeyword();
                isLoaded = true;
                try
                {
                    sleep(0x1d4c0L);
                }
                catch(InterruptedException interruptedexception) { }
            }
            catch(Exception ex3)
            {
                Logger.crisis("Loi khi doc cau hinh:" + ex3.toString());
            }
    }

    private Hashtable keywords;
    public Vector vtKeyword;
    public boolean isLoaded;
}
