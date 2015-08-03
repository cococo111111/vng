// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EMSBuilderfromQueue.java

package com.vmg.smpp.gateway;

import com.vmg.common.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import org.smpp.pdu.SubmitSM;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            DBTools, Gateway, EMSData, DBException, 
//            EMSException, SMSCTools, Si, EMSTools, 
//            SIgsm

public class EMSBuilderfromQueue extends Thread
{

    public EMSBuilderfromQueue(Queue toSMSC, Queue EMSQueue, Map Wait4ResponseTable)
    {
        this.toSMSC = null;
        this.EMSQueue = null;
        this.Wait4ResponseTable = null;
        dbTools = null;
        this.toSMSC = toSMSC;
        this.EMSQueue = EMSQueue;
        this.Wait4ResponseTable = Wait4ResponseTable;
        dbTools = new DBTools();
    }

    public void run()
    {
        Gateway.addLiveThread(this);
        while(Gateway.running) 
            try
            {
                EMSData ems = (EMSData)EMSQueue.dequeue();
                sendEMSQueueEx(ems);
                sleep(20L);
            }
            catch(InterruptedException ex)
            {
                Utilities _tmp = Gateway.util;
                Utilities.logErr(getClass().getName(), "{InterruptedException}" + ex.getMessage());
            }
            catch(DBException ex)
            {
                Utilities _tmp1 = Gateway.util;
                Utilities.logErr(getClass().getName(), "{DBException}" + ex.getMessage());
            }
            catch(Exception ex)
            {
                Utilities _tmp2 = Gateway.util;
                Utilities.logErr(getClass().getName(), "EMSBuilderfromQueue:: " + ex.getMessage());
            }
        Utilities _tmp3 = Gateway.util;
        Utilities.log(getClass().getName(), "{" + getClass().getName() + " stopped}");
        destroy();
    }

    public void destroy()
    {
        Gateway.removeThread(this);
    }

    public int sendEMSQueueEx(EMSData ems)
        throws DBException, EMSException
    {
        int numOfEms = 0;
        byte registeredDelivery = 0;
        Timestamp time = null;
        boolean isCharged = false;
        try
        {
            numOfEms = EMSQueue.size();
            if(ems == null)
            {
                Utilities _tmp = Gateway.util;
                Utilities.log(getClass().getName(), "{EMSBuilderfromQueue: inValid ContentType}{ems is null}");
            }
            registeredDelivery = 0;
            if(ems.getContentType() == 0)
            {
                SubmitSM ssm = SMSCTools.buildSubmitSM(ems.getServiceIdEx(12), ems.getUserIdEx(0), ems.getText(), ems.getId().intValue(), registeredDelivery, ems.getMessageType(), ems.getCommandCode(), ems.getRequestId());
                synchronized(Wait4ResponseTable)
                {
                    Wait4ResponseTable.put((new StringBuffer(String.valueOf(ems.getId().intValue()))).toString(), ems);
                }
                Utilities _tmp1 = Gateway.util;
                Utilities.log(getClass().getName(), "{Add2Wait4ResponseTable}{ems=" + ems.getId() + "}" + "{RequestID=" + ems.getRequestId() + "}");
                toSMSC.enqueue(ssm);
                time = new Timestamp(System.currentTimeMillis());
                ems.setSubmitDate(time);
            } else
            if(ems.getContentType() == 8)
            {
                if("SFONE".equalsIgnoreCase(ems.getMobileOperator()))
                {
                    ByteBuffer otaData = null;
                    String sSi = ems.getText();
                    int dot_idx = sSi.indexOf(":");
                    if(dot_idx > 0)
                    {
                        String sMsg = sSi.substring(0, dot_idx);
                        String sUrl = sSi.substring(dot_idx + 1);
                        Si si = new Si();
                        si.setURL(sUrl);
                        si.setMessage(sMsg);
                        si.encodeSI();
                        otaData = si.getEncodedSI();
                    } else
                    {
                        Utilities _tmp2 = Gateway.util;
                        Utilities.logErr(getClass().getName(), "{User_ID=" + ems.getUserId() + "}{Invalid SI:" + ems.getText() + "}");
                    }
                    Collection vSubmits = EMSTools.buildSubmitEMS(otaData, ems.getServiceIdEx(12), ems.getUserIdEx(0), ems.getContentType(), ems.getId(), registeredDelivery, ems.getMessageType(), ems.getRequestId());
                    int i = 1;
                    ems.setTotalSegments(vSubmits.size());
                    for(Iterator it2 = vSubmits.iterator(); it2.hasNext();)
                    {
                        SubmitSM ssm = (SubmitSM)it2.next();
                        if(i == 1)
                        {
                            synchronized(Wait4ResponseTable)
                            {
                                Wait4ResponseTable.put((new StringBuffer(String.valueOf(ssm.getSequenceNumber()))).toString(), ems);
                            }
                            Utilities _tmp3 = Gateway.util;
                            Utilities.log(getClass().getName(), "{Add2Wait4ResponseTable}{ems=" + ssm.getSequenceNumber() + "}" + "{RequestID=" + ems.getRequestId() + "}");
                        }
                        toSMSC.enqueue(ssm);
                        i++;
                    }

                } else
                {
                    ByteBuffer otaData = null;
                    boolean infoError = false;
                    String sSi = ems.getText();
                    int dot_idx = sSi.indexOf(":");
                    if(dot_idx > 0)
                    {
                        String sMsg = sSi.substring(0, dot_idx);
                        Utilities _tmp4 = Gateway.util;
                        Utilities.log(getClass().getName(), "msg=" + sMsg);
                        String sUrl = sSi.substring(dot_idx + 1);
                        Utilities _tmp5 = Gateway.util;
                        Utilities.log(getClass().getName(), "url=" + sUrl);
                        SIgsm si = new SIgsm();
                        si.setURL(sUrl);
                        si.setMessage(sMsg);
                        si.encodeSI();
                        otaData = si.getEncodedSI();
                    } else
                    {
                        System.out.println("invalid SI: " + ems.getText());
                        Utilities _tmp6 = Gateway.util;
                        Utilities.log(getClass().getName(), "invalid SI: " + ems.getText());
                        infoError = true;
                    }
                    Collection vSubmits = EMSTools.buildSubmitEMS(otaData, ems.getServiceIdEx(12), ems.getUserIdEx(0), ems.getContentType(), ems.getId(), registeredDelivery);
                    int i = 1;
                    ems.setTotalSegments(vSubmits.size());
                    for(Iterator it2 = vSubmits.iterator(); it2.hasNext();)
                    {
                        SubmitSM ssm = (SubmitSM)it2.next();
                        if(i == 1)
                        {
                            synchronized(Wait4ResponseTable)
                            {
                                Wait4ResponseTable.put((new StringBuffer(String.valueOf(ssm.getSequenceNumber()))).toString(), ems);
                            }
                            Utilities _tmp7 = Gateway.util;
                            Utilities.log(getClass().getName(), "{Add2Wait4ResponseTable}{ems=" + ssm.getSequenceNumber() + "}" + "{RequestID=" + ems.getRequestId() + "}");
                        }
                        toSMSC.enqueue(ssm);
                        i++;
                    }

                }
            } else
            {
                ByteBuffer otaData = null;
                boolean infoError = false;
                if(ems.getBytes() != null && ems.getBytes().length > 0)
                    otaData = new ByteBuffer(ems.getBytes());
                else
                if(ems.getText() != null && ems.getText().length() > 0)
                {
                    try
                    {
                        byte b[] = HexaTool.fromHexString(ems.getText());
                        otaData = new ByteBuffer(b);
                    }
                    catch(IllegalArgumentException ex)
                    {
                        System.out.println("fromHexString: " + ex.getMessage());
                        infoError = true;
                    }
                } else
                {
                    Utilities _tmp8 = Gateway.util;
                    Utilities.log(getClass().getName(), "Both Info and Raw_Info are null");
                    infoError = true;
                }
                if(infoError)
                {
                    Utilities _tmp9 = Gateway.util;
                    Utilities.log(getClass().getName(), "infoError: " + ems.getUserId() + ":" + ems.getCommandCode() + ":" + ems.getRequestId() + ":" + ems.getText());
                    System.out.println("    --> Move to send log.");
                } else
                {
                    Collection vSubmits = EMSTools.buildSubmitEMS(otaData, ems.getServiceIdEx(12), ems.getUserIdEx(0), ems.getContentType(), ems.getId(), registeredDelivery, ems.getMessageType(), ems.getCommandCode(), ems.getTotalSegments());
                    int i = 1;
                    ems.setTotalSegments(vSubmits.size());
                    synchronized(Wait4ResponseTable)
                    {
                        Wait4ResponseTable.put((new StringBuffer(String.valueOf(ems.getId().intValue()))).toString(), ems);
                    }
                    Utilities _tmp10 = Gateway.util;
                    Utilities.log(getClass().getName(), "{Add2Wait4ResponseTable}{ems=" + ems.getId() + "}" + "{RequestID=" + ems.getRequestId() + "}");
                    for(Iterator it2 = vSubmits.iterator(); it2.hasNext();)
                    {
                        SubmitSM ssm = (SubmitSM)it2.next();
                        toSMSC.enqueue(ssm);
                        i++;
                    }

                }
            }
        }
        catch(Exception ex)
        {
            Utilities _tmp11 = Gateway.util;
            Utilities.logErr(getClass().getName(), "Err:" + ex.getMessage());
        }
        return numOfEms;
    }

    private Queue toSMSC;
    private Queue EMSQueue;
    private Map Wait4ResponseTable;
    private DBTools dbTools;
}
