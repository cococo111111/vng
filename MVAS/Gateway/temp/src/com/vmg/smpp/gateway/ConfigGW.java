// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigGW.java

package com.vmg.smpp.gateway;

import com.vmg.common.Utilities;
import java.sql.*;
import java.util.Hashtable;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger, DBTools

public class ConfigGW
{

    public ConfigGW()
    {
    }

    public static Hashtable retrieveConfigGW(String gateway_name)
        throws Exception
    {
        Hashtable keywords;
        String query = "select c.name, a.val from gateway_config a, smsc_dict b, paramgw_dict c where a.smscid = b.id and a.paramid = c.id and b.name='" + gateway_name + "'";
        keywords = new Hashtable();
        Logger.info("ConfigGW.retrieve", "query:" + query);
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try
        {
            connection = DBTools.getConnection("gateway");
            stmt = connection.prepareStatement(query);
            if(stmt.execute())
            {
                ConfigGW keywordtemp;
                for(rs = stmt.getResultSet(); rs.next(); keywords.put(keywordtemp.configname, keywordtemp))
                {
                    keywordtemp = new ConfigGW();
                    keywordtemp.configname = rs.getString(1);
                    keywordtemp.configvalue = rs.getString(2);
                }

            }
        }
        catch(SQLException ex2)
        {
            Logger.error("ConfigGW.retrieveConfiggw", "Load config. Ex:" + ex2.toString());
        }
        catch(Exception ex2)
        {
            Logger.error("ConfigGW.retrieveConfiggw", "Load config. Ex:" + ex2.toString());
        }
        finally
        {
            util.cleanup(rs);
            util.cleanup(connection, stmt);
        }
        return keywords;
    }

    public String getConfigname()
    {
        return configname;
    }

    public void setConfigname(String configname)
    {
        this.configname = configname;
    }

    public String getConfigvalue()
    {
        return configvalue;
    }

    public void setConfigvalue(String configvalue)
    {
        this.configvalue = configvalue;
    }

    private static final String tablekeyword = "sfone_keyword_config";
    private String configname;
    private String configvalue;
    private static Utilities util = new Utilities();

}
