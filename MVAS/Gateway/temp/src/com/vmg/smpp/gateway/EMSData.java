// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EMSData.java

package com.vmg.smpp.gateway;

import com.vmg.common.StringTool;
import java.io.PrintStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.vmg.smpp.gateway:
//            Preference

public class EMSData
    implements Serializable
{

    public EMSData()
    {
        id = null;
        user_id = null;
        service_id = null;
        mobile_operator = null;
        command_code = null;
        content_type = 0;
        text = "";
        binary = null;
        submit_date = null;
        done_date = null;
        process_result = 0;
        message_type = 0;
        request_id = null;
        message_id = null;
        total_Segments = 0;
        send_num = 0;
        sRequestid = "";
        sNotes = "";
        cpid = 0;
    }

    public int getCpid()
    {
        return cpid;
    }

    public void setCpid(int cpid)
    {
        this.cpid = cpid;
    }

    public BigDecimal getId()
    {
        return id;
    }

    public String getUserId()
    {
        return user_id;
    }

    public String getsRequestID()
    {
        return sRequestid;
    }

    public void setsRequestID(String values)
    {
        sRequestid = values;
    }

    public String getServiceId()
    {
        return service_id;
    }

    public String getMobileOperator()
    {
        return mobile_operator;
    }

    public String getCommandCode()
    {
        return command_code;
    }

    public int getContentType()
    {
        return content_type;
    }

    public byte[] getBytes()
    {
        return binary;
    }

    public String getText()
    {
        if(content_type == 0 && text != null)
        {
            text = StringTool.removeChar(text, '\r');
            text = text.length() <= 160 ? text : text.substring(0, 161);
        }
        return text;
    }

    public Timestamp getSubmitDate()
    {
        if(submit_date == null)
            return new Timestamp(System.currentTimeMillis());
        else
            return submit_date;
    }

    public Timestamp getDoneDate()
    {
        if(done_date == null)
            return new Timestamp(System.currentTimeMillis());
        else
            return done_date;
    }

    public int getProcessResult()
    {
        return process_result;
    }

    public int getMessageType()
    {
        return message_type;
    }

    public BigDecimal getRequestId()
    {
        return request_id;
    }

    public String getMessageId()
    {
        return message_id;
    }

    public int getTotalSegments()
    {
        return total_Segments;
    }

    public String getUserIdEx(int formatType)
    {
        return formatUserId(user_id, formatType);
    }

    public String getServiceIdEx(int formatType)
    {
        return formatServiceId(service_id, formatType);
    }

    public String getTextEx(boolean noCRLF)
    {
        if(!noCRLF || text == null)
        {
            return text;
        } else
        {
            String temp = text.replace('\n', ' ');
            temp = temp.replace('\r', ' ');
            return temp;
        }
    }

    public String getNotes()
    {
        return sNotes;
    }

    public int getSendNum()
    {
        return send_num;
    }

    public void setId(BigDecimal id)
    {
        this.id = id;
    }

    public void setUserId(String value)
    {
        if(value != null && value.length() > 0)
        {
            value.toLowerCase();
            value.replace('o', '0');
        }
        user_id = value;
    }

    public void setServiceId(String value)
    {
        if(value != null && value.length() > 0)
        {
            value.toLowerCase();
            value.replace('o', '0');
        }
        service_id = value;
    }

    public void setMobileOperator(String value)
    {
        mobile_operator = value;
    }

    public void setCommandCode(String value)
    {
        command_code = value;
    }

    public void setContentType(int value)
    {
        content_type = value;
    }

    public void setText(String value)
    {
        text = value;
    }

    public void setBytes(byte value[])
    {
        binary = value;
    }

    public void setSubmitDate(Timestamp value)
    {
        submit_date = value;
    }

    public void setDoneDate(Timestamp value)
    {
        done_date = value;
    }

    public void setProcessResult(int value)
    {
        process_result = value;
    }

    public void setMessageType(int value)
    {
        message_type = value;
    }

    public void setRequestId(BigDecimal value)
    {
        request_id = value;
    }

    public void setMessageId(String value)
    {
        message_id = value;
    }

    public void setTotalSegments(int value)
    {
        total_Segments = value;
    }

    public void setSendNum(int value)
    {
        send_num = value;
    }

    public void setNotes(String value)
    {
        sNotes = value;
    }

    public boolean isWaiting4Response()
    {
        return submit_date != null;
    }

    public boolean isTimeout()
    {
        boolean result = false;
        if(submit_date != null)
        {
            long currTime = System.currentTimeMillis();
            if(currTime - submit_date.getTime() > (long)Preference.timeResend)
                result = true;
        }
        return result;
    }

    public boolean isValidServiceId()
    {
        return Preference.isValidServiceId(service_id);
    }

    public boolean isValidUserId()
    {
        String userId = user_id;
        if(userId == null || "".equals(userId))
            return false;
        if((userId.startsWith("90") || userId.startsWith("91") || userId.startsWith("98") || userId.startsWith("95") || userId.startsWith("93") || userId.startsWith("94") || userId.startsWith("92")) && userId.length() == 9 || (userId.startsWith("090") || userId.startsWith("091") || userId.startsWith("098") || userId.startsWith("097") || userId.startsWith("095") || userId.startsWith("093") || userId.startsWith("094") || userId.startsWith("092")) && userId.length() == 10 || userId.startsWith("8490") || userId.startsWith("8491") || userId.startsWith("8498") || userId.startsWith("8497") || userId.startsWith("8495") || userId.startsWith("8493") || userId.startsWith("8494") || userId.startsWith("8492") || userId.startsWith("8496") || userId.startsWith("096") && userId.length() == 11)
            return true;
        return !"UNKNOWN".equalsIgnoreCase(Preference.mobileOperator) ? true : true;
    }

    public boolean isValidContentType()
    {
        return content_type >= 0 && content_type <= 20;
    }

    public boolean rebuildMobileOperator1()
    {
        String userId = user_id;
        if(userId == null || "".equals(userId))
            return false;
        String new_mobile_operator = null;
        if(userId.startsWith("90") || userId.startsWith("090") || userId.startsWith("8490") || userId.startsWith("93") || userId.startsWith("093") || userId.startsWith("8493"))
        {
            if(!"VMS".equals(mobile_operator))
                new_mobile_operator = "VMS";
        } else
        if(userId.startsWith("91") || userId.startsWith("091") || userId.startsWith("8491") || userId.startsWith("94") || userId.startsWith("094") || userId.startsWith("8494"))
        {
            if(!"GPC".equals(mobile_operator))
                new_mobile_operator = "GPC";
        } else
        if(userId.startsWith("98") || userId.startsWith("098") || userId.startsWith("8498") || userId.startsWith("97") || userId.startsWith("097") || userId.startsWith("8497"))
        {
            if(!"VIETEL".equals(mobile_operator))
                new_mobile_operator = "VIETEL";
        } else
        if(userId.startsWith("95") || userId.startsWith("095") || userId.startsWith("8495"))
        {
            if(!"SFONE".equals(mobile_operator))
                new_mobile_operator = "SFONE";
        } else
        if(userId.startsWith("92") || userId.startsWith("092") || userId.startsWith("8492"))
        {
            if(!"HTC".equals(mobile_operator))
                new_mobile_operator = "HTC";
        } else
        if(userId.startsWith("96") || userId.startsWith("096") || userId.startsWith("8496"))
        {
            if(!"EVN".equals(mobile_operator))
                new_mobile_operator = "EVN";
        } else
        if(new_mobile_operator != null)
        {
            mobile_operator = new_mobile_operator;
            return true;
        }
        return false;
    }

    public boolean isAddressToSend1()
    {
        boolean result = false;
        for(Iterator it = Preference.sourceAddressList.iterator(); it.hasNext();)
        {
            String srcAddr = (String)it.next();
            if(service_id.startsWith(srcAddr))
            {
                result = true;
                break;
            }
        }

        if(!result)
            return false;
        if("VMS".equals(Preference.mobileOperator))
        {
            if(user_id.startsWith("90") || user_id.startsWith("090") || user_id.startsWith("8490") || user_id.startsWith("93") || user_id.startsWith("093") || user_id.startsWith("8493"))
                result = true;
        } else
        if("GPC".equals(Preference.mobileOperator))
        {
            if(user_id.startsWith("91") || user_id.startsWith("091") || user_id.startsWith("8491") || user_id.startsWith("94") || user_id.startsWith("094") || user_id.startsWith("8494"))
                result = true;
        } else
        if("VIETEL".equals(Preference.mobileOperator))
        {
            if(user_id.startsWith("98") || user_id.startsWith("098") || user_id.startsWith("8498") || user_id.startsWith("97") || user_id.startsWith("097") || user_id.startsWith("8497"))
                result = true;
        } else
        if("SFONE".equals(Preference.mobileOperator))
        {
            if(user_id.startsWith("95") || user_id.startsWith("095") || user_id.startsWith("8495"))
                result = true;
        } else
        if("HTC".equals(Preference.mobileOperator))
        {
            if(user_id.startsWith("92") || user_id.startsWith("092") || user_id.startsWith("8492"))
                result = true;
        } else
        if("EVN".equals(Preference.mobileOperator))
            result = true;
        else
            System.out.println("isAddressToSend: Invalid mobile_operator in the configuration file: " + Preference.mobileOperator);
        return result;
    }

    public String formatUserId(String userId, int formatType)
    {
        if(userId == null || "".equals(userId))
            return null;
        String temp = userId;
        switch(formatType)
        {
        case 0: // '\0'
            if(temp.startsWith("9"))
            {
                temp = "84" + temp;
                break;
            }
            if(temp.startsWith("09"))
                temp = "84" + temp.substring(1);
            break;

        case 2: // '\002'
            if(temp.startsWith("84"))
            {
                temp = temp.substring(2);
                break;
            }
            if(temp.startsWith("09"))
                temp = temp.substring(1);
            break;

        case 1: // '\001'
            if(temp.startsWith("84"))
            {
                temp = "0" + temp.substring(2);
                break;
            }
            if(temp.startsWith("9"))
                temp = "0" + temp;
            break;

        default:
            System.out.println("formatUserId: Invalid userId format_type " + formatType);
            return temp;
        }
        return temp;
    }

    public String formatServiceId(String serviceId, int formatType)
    {
        if(serviceId == null || "".equals(serviceId))
        {
            return null;
        } else
        {
            String temp = serviceId;
            return temp;
        }
    }

    private BigDecimal id;
    private String user_id;
    private String service_id;
    private String mobile_operator;
    private String command_code;
    private int content_type;
    private String text;
    private byte binary[];
    private Timestamp submit_date;
    private Timestamp done_date;
    private int process_result;
    private int message_type;
    private BigDecimal request_id;
    private String message_id;
    private int total_Segments;
    private int send_num;
    private String sRequestid;
    private String sNotes;
    private int cpid;
}
