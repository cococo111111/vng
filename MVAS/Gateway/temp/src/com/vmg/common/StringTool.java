// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringTool.java

package com.vmg.common;

import java.io.PrintStream;
import java.util.*;

public class StringTool
{

    public StringTool()
    {
    }

    public static String replaceCharAt(String s, int pos, char c)
    {
        StringBuffer buf = new StringBuffer(s);
        buf.setCharAt(pos, c);
        return buf.toString();
    }

    public static String removeChar(String s, char c)
    {
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < s.length(); i++)
        {
            char cur = s.charAt(i);
            if(cur != c)
                newString.append(cur);
        }

        return newString.toString();
    }

    public static String removeCharAt(String s, int pos)
    {
        StringBuffer buf = new StringBuffer(s.length() - 1);
        buf.append(s.substring(0, pos)).append(s.substring(pos + 1));
        return buf.toString();
    }

    public static Collection parseString(String text, String seperator)
    {
        Vector vResult = new Vector();
        if(text == null || "".equals(text))
            return vResult;
        String tempStr = text.trim();
        String currentLabel = null;
        for(int index = tempStr.indexOf(seperator); index != -1; index = tempStr.indexOf(seperator))
        {
            currentLabel = tempStr.substring(0, index).trim();
            if(!"".equals(currentLabel))
                vResult.addElement(currentLabel);
            tempStr = tempStr.substring(index + 1);
        }

        currentLabel = tempStr.trim();
        if(!"".equals(currentLabel))
            vResult.addElement(currentLabel);
        return vResult;
    }

    public static Collection parseString(String text)
    {
        Vector vResult = new Vector();
        if(text == null || "".equals(text))
            return vResult;
        String tempStr = text.trim();
        String currentLabel = null;
        for(int index = getNextIndex(tempStr); index != -1; index = getNextIndex(tempStr))
        {
            currentLabel = tempStr.substring(0, index).trim();
            if(!"".equals(currentLabel))
                vResult.addElement(currentLabel);
            tempStr = tempStr.substring(index + 1);
        }

        currentLabel = tempStr.trim();
        if(!"".equals(currentLabel))
            vResult.addElement(currentLabel);
        return vResult;
    }

    private static int getNextIndex(String text)
    {
        int index = 0;
        int newIdx = 0;
        boolean hasOne = false;
        for(int i = 0; i < seperators.length; i++)
        {
            newIdx = text.indexOf(seperators[i]);
            if(!hasOne)
            {
                if(newIdx != -1)
                {
                    index = newIdx;
                    hasOne = true;
                }
            } else
            if(newIdx != -1 && newIdx < index)
                index = newIdx;
        }

        if(!hasOne)
            index = -1;
        return index;
    }

    public static Collection parseStringEx(String text)
    {
        Vector vResult = new Vector();
        if(text == null || "".equals(text))
            return vResult;
        String tempStr = text.trim();
        char NINE = '9';
        char ZERO = '0';
        char CH_a = 'a';
        char CH_z = 'z';
        char CH_A = 'A';
        char CH_Z = 'Z';
        String currLabel = "";
        char currChar = '\0';
        for(int i = 0; i < tempStr.length(); i++)
        {
            currChar = tempStr.charAt(i);
            if(currChar >= ZERO && currChar <= NINE || currChar >= CH_a && currChar <= CH_z || currChar >= CH_A && currChar <= CH_Z)
                currLabel = currLabel + currChar;
            else
            if(currLabel.length() > 0)
            {
                vResult.add(currLabel);
                currLabel = new String("");
            }
        }

        if(currLabel.length() > 0)
            vResult.add(currLabel);
        return vResult;
    }

    public static boolean isNumberic(String sNumber)
    {
        if(sNumber == null || "".equals(sNumber))
            return false;
        char ch_max = '9';
        char ch_min = '0';
        for(int i = 0; i < sNumber.length(); i++)
        {
            char ch = sNumber.charAt(i);
            if(ch < ch_min || ch > ch_max)
                return false;
        }

        return true;
    }

    public String generateRandomString(int length)
    {
        char ch[] = new char[length];
        for(int i = 0; i < length; i++)
            ch[i] = charArray[random.nextInt(charArray.length)];

        return new String(ch);
    }

    public static void main(String args[])
    {
        Collection coll = parseStringEx("DA.2130444 4455 55595");
        System.out.println("Size: " + coll.size());
        for(Iterator it = coll.iterator(); it.hasNext(); System.out.println((String)it.next()));
    }

    static final String seperators[] = {
        " ", ".", ",", "-", "_", "="
    };
    private static char charArray[];
    private static Random random = null;

    static 
    {
        charArray = null;
        int numOfChars = 26;
        int numOfDigits = 10;
        random = new Random();
        charArray = new char[numOfChars + numOfDigits];
        for(int i = 0; i < numOfChars; i++)
            charArray[i] = (char)(65 + i);

        for(int i = 0; i < numOfDigits; i++)
            charArray[numOfChars + i] = (char)(48 + i);

    }
}
