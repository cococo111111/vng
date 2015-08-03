// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadConfig.java

package com.vmg.sms.process;

import com.vmg.sms.common.Util;
import com.vmg.sms.common.Utilities;
import java.util.*;

// Referenced classes of package com.vmg.sms.process:
//            Keyword, Constants, Logger, ConsoleSRV

public class LoadConfig extends Thread
{

    public LoadConfig()
    {
    }

    public Keyword getKeyword(String keyword, String serviceid)
    {
        Keyword retobj = new Keyword();
        retobj.setClassname(Constants.INV_CLASS);
        retobj.setKeyword(Constants.INV_KEYWORD);
        retobj.setServiceid(serviceid);
        String keytosearch = serviceid + "@" + keyword;
        String strkey = Constants.INV_KEYWORD;
        for(Iterator it = vtKeyword.iterator(); it.hasNext();)
        {
            String currLabel = (String)it.next();
            if(keytosearch.startsWith(currLabel))
            {
                strkey = currLabel;
                retobj = (Keyword)keywords.get(strkey);
                Util.logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "} {keyword=" + retobj.getKeyword() + "}");
                return retobj;
            }
        }

        Util.logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "} {keyword=" + retobj.getKeyword() + "}");
        return retobj;
    }

    public Keyword getKeywordInvalid(String keyword, String serviceid)
    {
        Keyword retobj = new Keyword();
        String newkeyword = Utilities.replaceWhiteLetter(keyword);
        retobj.setClassname(Constants.INV_CLASS);
        retobj.setKeyword(Constants.INV_KEYWORD);
        retobj.setServiceid(serviceid);
        String keytosearch = serviceid + "@" + newkeyword;
        String strkey = Constants.INV_KEYWORD;
        for(Iterator it = vtKeyword.iterator(); it.hasNext();)
        {
            String currLabel = (String)it.next();
            if(keytosearch.startsWith(currLabel))
            {
                strkey = currLabel;
                retobj = (Keyword)keywords.get(strkey);
                Util.logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "}{msg_old=" + keyword + "} {keyword=" + retobj.getKeyword() + "}");
                return retobj;
            }
        }

        Util.logger.info("{LoadConfig.getKeyword}{Keytosearh=" + keytosearch + "}{msgold=" + keyword + "} {keyword=" + retobj.getKeyword() + "}");
        return retobj;
    }

    public void run()
    {
        Util.logger.info("LoadConfig - Start");
        while(ConsoleSRV.processData) 
            try
            {
                keywords = Keyword.retrieveKeyword();
                try
                {
                    sleep(60000L);
                }
                catch(InterruptedException interruptedexception) { }
            }
            catch(Exception ex3)
            {
                Util.logger.crisis("Loi khi doc cau hinh:" + ex3.toString());
            }
    }

    private Hashtable keywords;
    public Vector vtKeyword;
}
