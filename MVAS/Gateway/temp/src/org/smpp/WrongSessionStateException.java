// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrongSessionStateException.java

package org.smpp;


// Referenced classes of package org.smpp:
//            SmppException

public class WrongSessionStateException extends SmppException
{

    public WrongSessionStateException()
    {
        super("The operation required is not possible in the current session state.");
        hasSessionDetails = false;
    }

    public WrongSessionStateException(int sessionType, int expectedState, int currentState)
    {
        this();
        hasSessionDetails = true;
        this.sessionType = sessionType;
        this.expectedState = expectedState;
        this.currentState = currentState;
    }

    public String getMessage()
    {
        if(hasSessionDetails)
        {
            String typeDescription = "";
            switch(sessionType)
            {
            case 1: // '\001'
                typeDescription = "ESME";
                break;

            case 2: // '\002'
                typeDescription = "MC";
                break;

            default:
                typeDescription = "UNKNOWN";
                break;
            }
            String msg;
            if(expectedState != 0)
                msg = "The operation is not allowed in the current " + typeDescription + " session state. " + "Current state is " + getStateDescription(currentState) + " required state(s) is " + getStateDescription(expectedState) + ".";
            else
                msg = "The operation is not allowed in " + typeDescription + " session. ";
            return msg;
        } else
        {
            return super.getMessage();
        }
    }

    public static String getStateDescription(int state)
    {
        String descr = "";
        descr = descr + getStateDescription(state, 1, descr, "closed");
        descr = descr + getStateDescription(state, 2, descr, "opened");
        descr = descr + getStateDescription(state, 4, descr, "transmitter");
        descr = descr + getStateDescription(state, 8, descr, "receiver");
        descr = descr + getStateDescription(state, 16, descr, "transceiver");
        descr = descr + getStateDescription(state, 30, descr, "any");
        if(descr.equals(""))
            descr = "unknown";
        return descr;
    }

    public static String getStateDescription(int state, int testState, String currentDescr, String descr)
    {
        if((state & testState) == testState)
        {
            if(currentDescr.length() > 0)
                return ", " + descr;
            else
                return descr;
        } else
        {
            return "";
        }
    }

    boolean hasSessionDetails;
    int sessionType;
    int expectedState;
    int currentState;
}
