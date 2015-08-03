// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyboardReader.java

package com.vmg.smpp.gateway;

import com.vmg.common.Queue;
import java.io.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import org.smpp.pdu.SubmitSM;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway, EMSTools, Preference, MobileBuffer, 
//            MobileBufferInfo

public class KeyboardReader extends Thread
{

    public KeyboardReader()
    {
    }

    public void run()
    {
        showMenu();
        String command = "";
        while(Gateway.running) 
            try
            {
                command = keyboard.readLine();
                processCommand(command);
            }
            catch(Exception e)
            {
                System.out.println(">>>Keyboard");
                System.out.println("run: " + e.getMessage());
            }
    }

    private void showMenu()
    {
        System.out.println("");
        System.out.println("S - submit");
        System.out.println("R - reload");
        System.out.println("Q - quit");
        System.out.println("");
    }

    private void processCommand(String command)
    {
        if(command == null)
            return;
        command = command.trim().toUpperCase();
        if("?".equals(command))
            showMenu();
        else
        if("S".equals(command))
        {
            String sFrom = getParam("Srce Address: ", "996");
            String sTo = getParam("Dest Address: ", "");
            String sType = getParam("Type (0-TEXT, 1-RINGTONE, 2-OPER_LOGO, 3-CLI_ICON, 4-PIC_MSG):", "0");
            String sMessage;
            if("0".equals(sType))
            {
                sMessage = getParam("Short Message: ", "");
            } else
            {
                String sFile = getParam("File Path: ", "");
                try
                {
                    Collection vSubmits = EMSTools.buildSubmitEMS(sFile, sFrom, sTo, Integer.parseInt(sType), 0, "Test", 1);
                    if(vSubmits != null && vSubmits.size() > 0)
                    {
                        SubmitSM ssm;
                        for(Iterator it = vSubmits.iterator(); it.hasNext(); Gateway.toSMSC.enqueue(ssm))
                            ssm = (SubmitSM)it.next();

                    } else
                    {
                        System.out.println("processCommand: No SubmitSM");
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("processCommand: " + ex.getMessage());
                }
            }
        } else
        if("R".equals(command))
            try
            {
                Preference.loadProperties(Gateway.propsFilePath);
            }
            catch(IOException e)
            {
                System.out.println("processCommand: khong tim thay file cau hinh " + Gateway.propsFilePath);
            }
        else
        if("Q".equals(command))
            Gateway.exit();
        else
        if(command.startsWith("MB"))
        {
            String mobile = null;
            if(command.length() > 2)
                mobile = command.substring(2).trim();
            if(mobile != null && !"".equals(mobile))
            {
                System.out.println("Looking for mobile: " + mobile);
                MobileBufferInfo info = MobileBuffer.lookup(mobile);
                if(info != null)
                {
                    System.out.println("MO Time  : " + new Timestamp(info.mo_Time * 1000L));
                    System.out.println("MO Count : " + info.mo_Counter);
                    System.out.println("MT Time  : " + new Timestamp(info.mt_Time * 1000L));
                    System.out.println("MT Count : " + info.mt_Counter);
                    System.out.println("CDR Count: " + info.cdr_Counter);
                } else
                {
                    System.out.println("Not found");
                }
            } else
            {
                System.out.println("Total size of mobileBuffer: " + MobileBuffer.size());
            }
        } else
        if(command.startsWith("EB"))
        {
            String cmd = command.substring(2).trim();
            if(cmd.startsWith("LEARN"))
            {
                Gateway.learning = !Gateway.learning;
                System.out.println("EMSBuffer learning is " + (Gateway.learning ? "ON" : "OFF"));
            }
        }
    }

    private String getParam(String prompt, String defaultValue)
    {
        String value = "";
        String promptFull = prompt;
        promptFull = promptFull + (defaultValue != null ? " [" + defaultValue + "] " : "");
        System.out.print(promptFull);
        try
        {
            value = keyboard.readLine();
        }
        catch(IOException ioexception) { }
        if(value.compareTo("") == 0)
            return defaultValue;
        else
            return value;
    }

    static BufferedReader keyboard;

    static 
    {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
}
