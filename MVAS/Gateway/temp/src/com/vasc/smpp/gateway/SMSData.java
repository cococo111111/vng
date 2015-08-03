// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMSData.java

package com.vasc.smpp.gateway;

import com.vasc.common.DateProc;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

// Referenced classes of package com.vasc.smpp.gateway:
//            Preference

public class SMSData
{

    public SMSData()
    {
        id = null;
        user_id = null;
        service_id = null;
        mobile_operator = null;
        command_code = null;
        info = null;
        first_send_time = null;
        last_send_time = null;
        submit_date = null;
        done_date = null;
        number_of_send = 0;
        process_result = 0;
        message_type = 0;
        request_id = null;
        total_segments = 0;
        segment_seqnum = 0;
        more_msgs_to_send = 0;
        message_id = null;
    }

    public static String formatServiceId(String serviceId, int formatType)
    {
        if(serviceId == null || "".equals(serviceId))
            return null;
        String temp = serviceId;
        switch(formatType)
        {
        case 10: // '\n'
            if(temp.startsWith("04"))
            {
                temp = "84" + temp.substring(2);
                break;
            }
            if(!temp.startsWith("84"))
                temp = "84" + temp;
            break;

        case 11: // '\013'
            if(temp.startsWith("84"))
            {
                temp = "04" + temp.substring(2);
                break;
            }
            if(!temp.startsWith("04"))
                temp = "04" + temp;
            break;

        case 12: // '\f'
            if(temp.startsWith("84") || temp.startsWith("04"))
                temp = temp.substring(2);
            break;

        default:
            System.out.println("Invalid serviceId format type " + formatType);
            return null;
        }
        return temp;
    }

    public static String formatUserId(String userId, int formatType)
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
            System.out.println("Invalid userId format type " + formatType);
            return null;
        }
        return temp;
    }

    public String getCommandCode()
    {
        return command_code;
    }

    private String getCurrentTime()
    {
        String sdate = DateProc.getYYYYMMDDHHMMSSString(new Timestamp(System.currentTimeMillis()));
        return sdate.substring(2);
    }

    public String getDoneDate()
    {
        if(done_date != null)
            return done_date;
        else
            return getCurrentTime();
    }

    public Timestamp getFirstSendTime()
    {
        return first_send_time;
    }

    public BigDecimal getId()
    {
        return id;
    }

    public String getInfo()
    {
        return info;
    }

    public String getInfoEx(boolean noCRLF)
    {
        if(!noCRLF || info == null)
        {
            return info;
        } else
        {
            String temp = info.replace('\n', ' ');
            temp = temp.replace('\r', ' ');
            return temp;
        }
    }

    public Timestamp getLastSendTime()
    {
        return last_send_time;
    }

    public String getMessageId()
    {
        return message_id;
    }

    public int getMessageType()
    {
        return message_type;
    }

    public String getMobileOperator()
    {
        return mobile_operator;
    }

    public int getMoreMsgsToSend()
    {
        return more_msgs_to_send;
    }

    public int getNumberOfSend()
    {
        return number_of_send;
    }

    public int getProcessResult()
    {
        return process_result;
    }

    public BigDecimal getRequestId()
    {
        return request_id;
    }

    public int getSegmentSeqnum()
    {
        return segment_seqnum;
    }

    public String getServiceId()
    {
        return service_id;
    }

    public String getServiceIdEx(int formatType)
    {
        return formatServiceId(service_id, formatType);
    }

    public String getSubmitDate()
    {
        if(submit_date != null)
            return submit_date;
        else
            return getCurrentTime();
    }

    public int getTotalSegments()
    {
        return total_segments;
    }

    public String getUserId()
    {
        return user_id;
    }

    public String getUserIdEx(int formatType)
    {
        return formatUserId(user_id, formatType);
    }

    public boolean isAddressToSend()
    {
        boolean result = false;
        if(!Preference.sourceAddressList.contains(service_id))
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
        {
            if(user_id.startsWith("96") || user_id.startsWith("096") || user_id.startsWith("8496"))
                result = true;
            result = true;
        } else
        {
            System.out.println("invalid value of mobile_operator in the configuration file.");
            result = false;
        }
        return result;
    }

    public boolean isNotSentYet()
    {
        return number_of_send == 0;
    }

    public boolean isTimeout()
    {
        boolean result = false;
        if(last_send_time != null)
        {
            long currTime = System.currentTimeMillis();
            if(currTime - last_send_time.getTime() > (long)Preference.timeResend)
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
        return (!userId.startsWith("90") && !userId.startsWith("93") && !userId.startsWith("91") && !userId.startsWith("94") && !userId.startsWith("98") && !userId.startsWith("95") && !userId.startsWith("92") && !userId.startsWith("96") || userId.length() != 9) && (!userId.startsWith("090") && !userId.startsWith("093") && !userId.startsWith("091") && !userId.startsWith("094") && !userId.startsWith("098") && !userId.startsWith("097") && !userId.startsWith("095") && !userId.startsWith("092") && !userId.startsWith("096") || userId.length() != 10) && (!userId.startsWith("8490") && !userId.startsWith("8493") && !userId.startsWith("8491") && !userId.startsWith("8494") && !userId.startsWith("8498") && !userId.startsWith("8497") && !userId.startsWith("8495") && !userId.startsWith("8492") && !userId.startsWith("8496") || userId.length() != 11) ? true : true;
    }

    public static void main(String args[])
    {
        SMSData sms = new SMSData();
        System.out.println(sms.formatUserId("0904060008", 0));
        System.out.println(sms.formatServiceId("04997", 11));
    }

    public boolean rebuildMobileOperator()
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
        return true;
    }

    public void setCommandCode(String value)
    {
        command_code = value;
    }

    public void setDoneDate(String value)
    {
        done_date = value;
    }

    public void setFirstSendTime(Timestamp value)
    {
        first_send_time = value;
    }

    public void setId(BigDecimal value)
    {
        id = value;
    }

    public void setInfo(String value)
    {
        if(value == null)
            value = " ";
        else
        if(value.length() > 160)
            value = value.substring(0, 160);
        info = value;
    }

    public void setLastSendTime(Timestamp value)
    {
        last_send_time = value;
    }

    public void setMessageId(String messageId)
    {
        message_id = messageId;
    }

    public void setMessageType(int messageType)
    {
        message_type = messageType;
    }

    public void setMobileOperator(String value)
    {
        mobile_operator = value;
    }

    public void setMoreMsgsToSend(int moreMsgsToSend)
    {
        more_msgs_to_send = moreMsgsToSend;
    }

    public void setNumberOfSend(int numOfSend)
    {
        number_of_send = numOfSend;
    }

    public void setProcessResult(int processResult)
    {
        process_result = processResult;
    }

    public void setRequestId(BigDecimal requestId)
    {
        request_id = requestId;
    }

    public void setSegmentSeqnum(int segmentSeqnum)
    {
        segment_seqnum = segmentSeqnum;
    }

    public void setServiceId(String value)
    {
        if(value == null)
            return;
        if(value.length() > 0)
        {
            value.toLowerCase();
            value.replace('o', '0');
        }
        if(value.startsWith("+"))
            value = value.substring(1);
        if(value.startsWith("04") || value.startsWith("84"))
            value = value.substring(2);
        service_id = value;
    }

    public void setSubmitDate(String value)
    {
        submit_date = value;
    }

    public void setTotalSegments(int totalSegments)
    {
        total_segments = totalSegments;
    }

    public void setUserId(String value)
    {
        if(value != null && value.length() > 0)
        {
            value.toLowerCase();
            value.replace('o', '0');
        }
        if(value.startsWith("+"))
            value = value.substring(1);
        user_id = value;
    }

    private String command_code;
    private String done_date;
    private Timestamp first_send_time;
    private BigDecimal id;
    private String info;
    private Timestamp last_send_time;
    private String message_id;
    private int message_type;
    private String mobile_operator;
    private int more_msgs_to_send;
    private int number_of_send;
    private int process_result;
    private BigDecimal request_id;
    private int segment_seqnum;
    private String service_id;
    private String submit_date;
    private int total_segments;
    private String user_id;
}
