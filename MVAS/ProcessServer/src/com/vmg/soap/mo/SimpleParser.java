// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleParser.java

package com.vmg.soap.mo;

import com.vmg.sms.common.Util;
import com.vmg.sms.process.Logger;
import java.util.*;

public class SimpleParser
{

    public static final boolean isStatic(String value)
        throws NullPointerException
    {
        for(int i = 0; i < value.length(); i++)
            if(value.charAt(i) == '$')
            {
                int next = i + 1;
                if(value.charAt(next) != '$')
                    return false;
            }

        return true;
    }

    public SimpleParser(String source)
        throws Exception
    {
        this(source, false, true);
    }

    public SimpleParser(String source, boolean isStatic)
        throws Exception
    {
        this(source, isStatic, true);
    }

    public SimpleParser(String source, boolean isStatic, boolean autoQuote)
        throws Exception
    {
        vTokens = null;
        szMySource = null;
        pVariables = new Properties();
        bQuoted = false;
        this.autoQuote = true;
        String szToken = null;
        String szTag = null;
        int iStatus = 0;
        this.autoQuote = autoQuote;
        int posQuoted = source.indexOf("\r\nContent-Transfer-Encoding: quoted-printable");
        bQuoted = posQuoted > -1;
        StringTokenizer stText = new StringTokenizer(source, "$", true);
        if(isStatic || stText.countTokens() < 2)
        {
            bStatic = true;
            int pos1;
            if(!bQuoted || !autoQuote)
                szMySource = source;
            else
            if((pos1 = source.substring(0, posQuoted).lastIndexOf("\r\n\r\n")) == -1)
            {
                pos1 = source.indexOf("\r\n\r\n", posQuoted);
                szMySource = source.substring(0, pos1 + 4) + getQuotedPrintableText(source.substring(pos1 + 4));
            } else
            {
                String boundaryName = source.substring(pos1, source.indexOf("\r\n", pos1 + 4));
                do
                {
                    pos1 = source.indexOf("\r\n\r\n", posQuoted);
                    int pos2 = source.indexOf(boundaryName, pos1);
                    if(pos2 == -1)
                        source = source.substring(0, pos1 + 4) + getQuotedPrintableText(source.substring(pos1 + 4));
                    else
                    if(pos2 > pos1 + 4)
                        source = source.substring(0, pos1 + 4) + getQuotedPrintableText(source.substring(pos1 + 4, pos2)) + source.substring(pos2);
                } while((posQuoted = source.indexOf("\r\nContent-Transfer-Encoding: quoted-printable", posQuoted + 2)) > -1);
                szMySource = source;
            }
        } else
        {
            bStatic = false;
            vTokens = new Vector();
            while(stText.hasMoreTokens()) 
            {
                szToken = stText.nextToken();
                if(szToken.charAt(0) == '$')
                    switch(iStatus)
                    {
                    case 0: // '\0'
                        iStatus = 1;
                        break;

                    case 1: // '\001'
                        vTokens.addElement("$");
                        iStatus = 0;
                        break;

                    case 2: // '\002'
                        vTokens.addElement(null);
                        vTokens.addElement(szTag);
                        iStatus = 0;
                        break;
                    }
                else
                    switch(iStatus)
                    {
                    default:
                        break;

                    case 0: // '\0'
                        vTokens.addElement(szToken);
                        break;

                    case 1: // '\001'
                        szTag = szToken;
                        iStatus = 2;
                        if(szTag.length() > 0)
                            addVariableName(szTag);
                        break;

                    case 2: // '\002'
                        iStatus = 0;
                        break;
                    }
            }
            if(iStatus != 0)
                throw new Exception("Bad source: odd '$' tags ???");
        }
    }

    public boolean isStatic()
    {
        return bStatic;
    }

    public Properties getAllVariables()
    {
        if(!pVariables.isEmpty())
            pVariables.setProperty("jfmt", "true");
        return pVariables;
    }

    public String parse(Properties p)
    {
        Stack stackShowComment = new Stack();
        int iCommentFlag = 0;
        if(bStatic)
            return szMySource;
        StringBuffer sbText = new StringBuffer();
        for(int iCount = 0; iCount < vTokens.size(); iCount++)
            try
            {
                String szToken = (String)vTokens.elementAt(iCount);
                if(szToken == null)
                {
                    szToken = (String)vTokens.elementAt(++iCount);
                    if(szToken.charAt(0) == '(')
                    {
                        int iPointer = szToken.indexOf(')');
                        String szModifier = szToken.substring(1, iPointer);
                        if(szModifier.equalsIgnoreCase("!comment"))
                        {
                            String szValue = p.getProperty(szToken.substring(iPointer + 1));
                            if(ConditionMatched(szToken.substring(iPointer + 1), p))
                            {
                                stackShowComment.push(new Boolean(true));
                                iCommentFlag++;
                            } else
                            {
                                stackShowComment.push(new Boolean(false));
                            }
                        } else
                        if(szModifier.equalsIgnoreCase("!comment-end"))
                        {
                            if(((Boolean)stackShowComment.pop()).booleanValue())
                                iCommentFlag = iCommentFlag <= 0 ? 0 : iCommentFlag - 1;
                        } else
                        if(szModifier.equalsIgnoreCase("!show"))
                        {
                            if(!ConditionMatched(szToken.substring(iPointer + 1), p))
                            {
                                stackShowComment.push(new Boolean(true));
                                iCommentFlag++;
                            } else
                            {
                                stackShowComment.push(new Boolean(false));
                            }
                        } else
                        if(szModifier.equalsIgnoreCase("!show-end") && ((Boolean)stackShowComment.pop()).booleanValue())
                            iCommentFlag = iCommentFlag <= 0 ? 0 : iCommentFlag - 1;
                    } else
                    if(iCommentFlag == 0)
                    {
                        String szValue = p.getProperty(szToken);
                        if(szValue != null)
                            sbText.append(szValue);
                    }
                } else
                if(iCommentFlag == 0)
                    sbText.append(szToken);
            }
            catch(Exception exception) { }

        if(!bQuoted || !autoQuote)
            return sbText.toString();
        String szSource = sbText.toString();
        int posQuoted = szSource.indexOf("\r\nContent-Transfer-Encoding: quoted-printable");
        int pos1;
        if((pos1 = szSource.substring(0, posQuoted).lastIndexOf("\r\n\r\n")) == -1)
        {
            pos1 = szSource.indexOf("\r\n\r\n", posQuoted);
            return szSource.substring(0, pos1 + 4) + getQuotedPrintableText(szSource.substring(pos1 + 4));
        }
        String boundaryName = szSource.substring(pos1, szSource.indexOf("\r\n", pos1 + 4));
        do
        {
            pos1 = szSource.indexOf("\r\n\r\n", posQuoted);
            int pos2 = szSource.indexOf(boundaryName, pos1);
            if(pos2 == -1)
                szSource = szSource.substring(0, pos1 + 4) + getQuotedPrintableText(szSource.substring(pos1 + 4));
            else
            if(pos2 > pos1 + 4)
                szSource = szSource.substring(0, pos1 + 4) + getQuotedPrintableText(szSource.substring(pos1 + 4, pos2)) + szSource.substring(pos2);
        } while((posQuoted = szSource.indexOf("\r\nContent-Transfer-Encoding: quoted-printable", posQuoted + 2)) > -1);
        return szSource;
    }

    private boolean ConditionMatched(String szCondition, Properties Pr)
        throws Exception
    {
        int iLogicalOperatorPos = -1;
        int iLogicalOperatorFound = 0;
        boolean bUnaryOperator = false;
        String szVariableValue = null;
        String szValue = null;
        if(szCondition == null)
            return false;
        szCondition = szCondition.trim();
        String szVariable = szCondition;
        for(int i = 0; i < asLogicalOperators.length; i++)
        {
            switch(i)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                iLogicalOperatorPos = szCondition.indexOf(" " + asLogicalOperators[i]);
                bUnaryOperator = true;
                break;

            default:
                iLogicalOperatorPos = szCondition.indexOf(" " + asLogicalOperators[i] + " ");
                bUnaryOperator = false;
                break;
            }
            if(iLogicalOperatorPos == -1)
                continue;
            iLogicalOperatorFound = i;
            szVariable = szCondition.substring(0, iLogicalOperatorPos).trim();
            if(!bUnaryOperator)
            {
                if(szCondition.length() <= iLogicalOperatorPos + asLogicalOperators[iLogicalOperatorFound].length() + 2)
                {
                    Util.logger.info(getClass().getName() + ".MatchCondition" + "@" + "Binary Operator has not parameter");
                    throw new Exception("Error in SimpleParser.MatchCondition: Binary Operator has not parameter");
                }
                szValue = szCondition.substring(iLogicalOperatorPos + asLogicalOperators[iLogicalOperatorFound].length() + 2).trim();
                if(szValue.length() > 0 && szValue.charAt(0) == '"')
                {
                    szValue = szValue.substring(1);
                    if(szValue.length() > 0 && szValue.charAt(szValue.length() - 1) == '"')
                        szValue = szValue.substring(0, szValue.length() - 1);
                }
            }
            break;
        }

        switch(iLogicalOperatorFound)
        {
        case 0: // '\0'
            return Pr.getProperty(szVariable) != null;

        case 1: // '\001'
            return Pr.getProperty(getKeyNameInProperties(szVariable, Pr)) != null;

        case 2: // '\002'
            return Pr.getProperty(szVariable) == null;

        case 3: // '\003'
            return Pr.getProperty(getKeyNameInProperties(szVariable, Pr)) == null;
        }
        szVariableValue = Pr.getProperty(szVariable);
        if(szVariableValue == null)
            return false;
        switch(iLogicalOperatorFound)
        {
        case 4: // '\004'
            return szVariableValue.equals(szValue);

        case 5: // '\005'
            return szVariableValue.equalsIgnoreCase(szValue);

        case 6: // '\006'
            return !szVariableValue.equals(szValue);

        case 7: // '\007'
            return !szVariableValue.equalsIgnoreCase(szValue);

        case 8: // '\b'
            return Integer.parseInt(szVariableValue) > Integer.parseInt(szValue);

        case 9: // '\t'
            return szVariableValue.compareTo(szValue) > 0;

        case 10: // '\n'
            return szVariableValue.compareToIgnoreCase(szValue) > 0;

        case 11: // '\013'
            return Integer.parseInt(szVariableValue) >= Integer.parseInt(szValue);

        case 12: // '\f'
            return szVariableValue.compareTo(szValue) >= 0;

        case 13: // '\r'
            return szVariableValue.compareToIgnoreCase(szValue) >= 0;

        case 14: // '\016'
            return Integer.parseInt(szVariableValue) < Integer.parseInt(szValue);

        case 15: // '\017'
            return szVariableValue.compareTo(szValue) < 0;

        case 16: // '\020'
            return szVariableValue.compareToIgnoreCase(szValue) < 0;

        case 17: // '\021'
            return Integer.parseInt(szVariableValue) <= Integer.parseInt(szValue);

        case 18: // '\022'
            return szVariableValue.compareTo(szValue) <= 0;

        case 19: // '\023'
            return szVariableValue.compareToIgnoreCase(szValue) <= 0;

        case 20: // '\024'
            return szVariableValue.indexOf(szValue) != -1;

        case 21: // '\025'
            return szVariableValue.indexOf(szValue) == -1;

        case 22: // '\026'
            return szVariableValue.toUpperCase().indexOf(szValue.toUpperCase()) != -1;

        case 23: // '\027'
            return szVariableValue.toUpperCase().indexOf(szValue.toUpperCase()) == -1;

        case 24: // '\030'
            return szVariableValue.startsWith(szValue);

        case 25: // '\031'
            return !szVariableValue.startsWith(szValue);

        case 26: // '\032'
            return szVariableValue.toUpperCase().startsWith(szValue.toUpperCase());

        case 27: // '\033'
            return !szVariableValue.toUpperCase().startsWith(szValue.toUpperCase());

        case 28: // '\034'
            return szVariableValue.endsWith(szValue);

        case 29: // '\035'
            return !szVariableValue.endsWith(szValue);

        case 30: // '\036'
            return szVariableValue.toUpperCase().endsWith(szValue.toUpperCase());

        case 31: // '\037'
            return !szVariableValue.toUpperCase().endsWith(szValue.toUpperCase());

        case 32: // ' '
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
            szValue = ";" + szValue.substring(1, szValue.length() - 1).trim() + ";";
            switch(iLogicalOperatorFound)
            {
            case 32: // ' '
                return szValue.indexOf(";" + szVariableValue + ";") != -1;

            case 33: // '!'
                return szValue.indexOf(";" + szVariableValue + ";") == -1;

            case 34: // '"'
                return szValue.toUpperCase().indexOf(";" + szVariableValue.toUpperCase() + ";") != -1;

            case 35: // '#'
                return szValue.toUpperCase().indexOf(";" + szVariableValue.toUpperCase() + ";") == -1;
            }
            // fall through

        case 36: // '$'
            szValue = szValue.substring(1, szValue.length() - 1);
            int iCommaPos = szValue.indexOf(',');
            int iDiv = 1;
            int iMod = 0;
            if(iCommaPos != -1)
            {
                iDiv = Integer.parseInt(szValue.substring(0, iCommaPos));
                iMod = Integer.parseInt(szValue.substring(iCommaPos + 1));
            } else
            {
                iDiv = Integer.parseInt(szValue);
            }
            return Integer.parseInt(szVariableValue) % iDiv == iMod;

        default:
            return false;
        }
    }

    private String getKeyNameInProperties(String szPropertyName, Properties Pr)
    {
        if(Pr.getProperty(szPropertyName) != null)
            return szPropertyName;
        for(Enumeration eKeys = Pr.keys(); eKeys.hasMoreElements();)
        {
            String szKey = (String)eKeys.nextElement();
            if(szKey.equalsIgnoreCase(szPropertyName))
                return szKey;
        }

        return "";
    }

    private void addVariableName(String tok)
    {
        int pos1 = -1;
        if(tok.startsWith("(") && (pos1 = tok.indexOf(")")) > 1)
        {
            int pos2 = tok.indexOf(" ", pos1);
            if(pos2 > -1)
                tok = tok.substring(pos1 + 1, pos2);
            else
                tok = tok.substring(pos1 + 1);
        }
        if(tok.length() > 0)
            pVariables.setProperty(tok, "true");
    }

    private String getQuotedPrintableText(String msgtext)
    {
        int size = msgtext.length();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < size; i++)
        {
            char tmp = msgtext.charAt(i);
            int iAsciiCode = tmp;
            if(iAsciiCode > 127 || iAsciiCode == 61)
                sb.append("=" + Integer.toString(iAsciiCode, 16).toUpperCase());
            else
                sb.append(tmp);
        }

        return sb.toString();
    }

    public static String parseQuotedPrintableText(String qsource)
    {
        for(int pos = -1; (pos = qsource.indexOf("=", pos + 1)) != -1;)
            qsource = qsource.substring(0, pos) + (char)Integer.parseInt(qsource.substring(pos + 1, pos + 3), 16) + qsource.substring(pos + 3);

        return qsource;
    }

    private static String asLogicalOperators[] = {
        "ex", "EX", "!ex", "!EX", "==", "=i", "!==", "!=i", ">>", "gt", 
        "GT", ">=", "ge", "GE", "<<", "lt", "LT", "<=", "le", "LE", 
        "cc", "!cc", "CC", "!CC", "sw", "!sw", "SW", "!SW", "ew", "!ew", 
        "EW", "!EW", "in", "!in", "IN", "!IN", "mod"
    };
    private static final int EXIST = 0;
    private static final int EXIST_I = 1;
    private static final int NOT_EXIST = 2;
    private static final int NOT_EXIST_I = 3;
    private static final int EQUAL = 4;
    private static final int EQUAL_I = 5;
    private static final int DIFFERENT = 6;
    private static final int DIFFERENT_I = 7;
    private static final int GREATER_NUMBERS = 8;
    private static final int GREATER_STRINGS = 9;
    private static final int GREATER_STRINGS_I = 10;
    private static final int GREATER_EQUAL_NUMBERS = 11;
    private static final int GREATER_EQUAL_STRINGS = 12;
    private static final int GREATER_EQUAL_STRINGS_I = 13;
    private static final int LESS_NUMBERS = 14;
    private static final int LESS_STRINGS = 15;
    private static final int LESS_STRINGS_I = 16;
    private static final int LESS_EQUAL_NUMBERS = 17;
    private static final int LESS_EQUAL_STRINGS = 18;
    private static final int LESS_EQUAL_STRINGS_I = 19;
    private static final int CONTAINS = 20;
    private static final int NOT_CONTAINS = 21;
    private static final int CONTAINS_I = 22;
    private static final int NOT_CONTAINS_I = 23;
    private static final int STARTS_WITH = 24;
    private static final int NOT_STARTS_WITH = 25;
    private static final int STARTS_WITH_I = 26;
    private static final int NOT_STARTS_WITH_I = 27;
    private static final int ENDS_WITH = 28;
    private static final int NOT_ENDS_WITH = 29;
    private static final int ENDS_WITH_I = 30;
    private static final int NOT_ENDS_WITH_I = 31;
    private static final int IN = 32;
    private static final int NOT_IN = 33;
    private static final int IN_I = 34;
    private static final int NOT_IN_I = 35;
    private static final int MOD = 36;
    private static final String tofind = "\r\nContent-Transfer-Encoding: quoted-printable";
    private Vector vTokens;
    private String szMySource;
    private Properties pVariables;
    private boolean bStatic;
    private boolean bQuoted;
    private boolean autoQuote;

}
