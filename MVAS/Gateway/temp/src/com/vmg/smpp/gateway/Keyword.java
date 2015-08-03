// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Keyword.java

package com.vmg.smpp.gateway;

import com.vmg.common.Utilities;
import java.sql.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.vmg.smpp.gateway:
//            Logger, DBTools, Gateway, LoadConfig

public class Keyword
{

    public Keyword()
    {
        cpid = 0;
        util = new Utilities();
    }

    public String getKeyword()
    {
        return keyword;
    }

    public String getServiceid()
    {
        return serviceid;
    }

    public String getClassname()
    {
        return classname;
    }

    public String getOptions()
    {
        return options;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public void setServiceid(String serviceid)
    {
        this.serviceid = serviceid;
    }

    public void setClassname(String classname)
    {
        this.classname = classname;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public static Hashtable retrieveKeyword()
        throws Exception
    {
        Hashtable keywords;
        Vector vtkeywords;
        Logger.info("retrieveKeyword");
        String query = "select service_id,keyword,class_name,options, cpid from sfone_keyword_config  where status = 1 and current_timestamp >= activedate and ((current_timestamp < inactivedate ) or inactivedate is null  or inactivedate='0000-00-00 00:00:00' ) order by length(keyword) desc, keyword asc ";
        Logger.info("retrieveKeyword@" + query);
        DBTools dbpool = new DBTools();
        keywords = new Hashtable();
        vtkeywords = new Vector();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try
        {
            connection = DBTools.getConnection("gateway");
            stmt = connection.prepareStatement(query);
            if(stmt.execute())
            {
                Keyword keywordtemp;
                for(rs = stmt.getResultSet(); rs.next(); vtkeywords.addElement(keywordtemp.serviceid + "@" + keywordtemp.keyword))
                {
                    keywordtemp = new Keyword();
                    keywordtemp.serviceid = rs.getString(1);
                    keywordtemp.keyword = rs.getString(2);
                    keywordtemp.classname = rs.getString(3);
                    keywordtemp.options = rs.getString(4);
                    keywordtemp.cpid = rs.getInt(5);
                    keywords.put(keywordtemp.serviceid + "@" + keywordtemp.keyword, keywordtemp);
                }

            }
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            dbpool.cleanup(connection);
        }
        catch(SQLException ex2)
        {
            Logger.error("Load Keyword. Ex:" + ex2.toString());
        }
        catch(Exception ex3)
        {
            Logger.error("Load Keyword. Ex3:" + ex3.toString());
        }
        finally
        {
            dbpool.cleanup(connection);
        }
        Gateway.loadconfig.vtKeyword = vtkeywords;
        return keywords;
    }

    public int getCpid()
    {
        return cpid;
    }

    public void setCpid(int cpid)
    {
        this.cpid = cpid;
    }

    private static final String tablekeyword = "sfone_keyword_config";
    private String serviceid;
    private String keyword;
    private String classname;
    private int cpid;
    private String options;
    private Utilities util;
}
