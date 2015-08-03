// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hexa.java

package com.vasc.common;

import java.io.PrintStream;

public class Hexa
{

    public Hexa(String Bits)
    {
        if(Bits.length() != 4)
        {
            System.out.println("Impossible de convertir en Hexa la chaine de bits");
        } else
        {
            int hex = 0;
            for(int i = 0; i < 4; i++)
            {
                char temp = Bits.charAt(i);
                int j = 0;
                if(temp == '1')
                    j = 1;
                hex = 2 * hex + j;
            }

            switch(hex)
            {
            case 0: // '\0'
                Hex = '0';
                break;

            case 1: // '\001'
                Hex = '1';
                break;

            case 2: // '\002'
                Hex = '2';
                break;

            case 3: // '\003'
                Hex = '3';
                break;

            case 4: // '\004'
                Hex = '4';
                break;

            case 5: // '\005'
                Hex = '5';
                break;

            case 6: // '\006'
                Hex = '6';
                break;

            case 7: // '\007'
                Hex = '7';
                break;

            case 8: // '\b'
                Hex = '8';
                break;

            case 9: // '\t'
                Hex = '9';
                break;

            case 10: // '\n'
                Hex = 'A';
                break;

            case 11: // '\013'
                Hex = 'B';
                break;

            case 12: // '\f'
                Hex = 'C';
                break;

            case 13: // '\r'
                Hex = 'D';
                break;

            case 14: // '\016'
                Hex = 'E';
                break;

            case 15: // '\017'
                Hex = 'F';
                break;
            }
        }
    }

    public Hexa(char c)
    {
        Hex = c;
    }

    public Hexa(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            Hex = '0';
            break;

        case 1: // '\001'
            Hex = '1';
            break;

        case 2: // '\002'
            Hex = '2';
            break;

        case 3: // '\003'
            Hex = '3';
            break;

        case 4: // '\004'
            Hex = '4';
            break;

        case 5: // '\005'
            Hex = '5';
            break;

        case 6: // '\006'
            Hex = '6';
            break;

        case 7: // '\007'
            Hex = '7';
            break;

        case 8: // '\b'
            Hex = '8';
            break;

        case 9: // '\t'
            Hex = '9';
            break;

        case 10: // '\n'
            Hex = 'A';
            break;

        case 11: // '\013'
            Hex = 'B';
            break;

        case 12: // '\f'
            Hex = 'C';
            break;

        case 13: // '\r'
            Hex = 'D';
            break;

        case 14: // '\016'
            Hex = 'E';
            break;

        case 15: // '\017'
            Hex = 'F';
            break;
        }
    }

    public String HexaToBits()
    {
        int i = getInt();
        String bits = "";
        switch(i)
        {
        case 0: // '\0'
            bits = "0000";
            break;

        case 1: // '\001'
            bits = "0001";
            break;

        case 2: // '\002'
            bits = "0010";
            break;

        case 3: // '\003'
            bits = "0011";
            break;

        case 4: // '\004'
            bits = "0100";
            break;

        case 5: // '\005'
            bits = "0101";
            break;

        case 6: // '\006'
            bits = "0110";
            break;

        case 7: // '\007'
            bits = "0111";
            break;

        case 8: // '\b'
            bits = "1000";
            break;

        case 9: // '\t'
            bits = "1001";
            break;

        case 10: // '\n'
            bits = "1010";
            break;

        case 11: // '\013'
            bits = "1011";
            break;

        case 12: // '\f'
            bits = "1100";
            break;

        case 13: // '\r'
            bits = "1101";
            break;

        case 14: // '\016'
            bits = "1110";
            break;

        case 15: // '\017'
            bits = "1111";
            break;
        }
        return bits;
    }

    public void display()
    {
        System.out.println("Hexa : " + Hex);
    }

    public char getHexa()
    {
        return Hex;
    }

    public int getInt()
    {
        int i = 0;
        switch(Hex)
        {
        case 48: // '0'
            i = 0;
            break;

        case 49: // '1'
            i = 1;
            break;

        case 50: // '2'
            i = 2;
            break;

        case 51: // '3'
            i = 3;
            break;

        case 52: // '4'
            i = 4;
            break;

        case 53: // '5'
            i = 5;
            break;

        case 54: // '6'
            i = 6;
            break;

        case 55: // '7'
            i = 7;
            break;

        case 56: // '8'
            i = 8;
            break;

        case 57: // '9'
            i = 9;
            break;

        case 65: // 'A'
            i = 10;
            break;

        case 66: // 'B'
            i = 11;
            break;

        case 67: // 'C'
            i = 12;
            break;

        case 68: // 'D'
            i = 13;
            break;

        case 69: // 'E'
            i = 14;
            break;

        case 70: // 'F'
            i = 15;
            break;
        }
        return i;
    }

    public boolean isHexa()
    {
        return getInt() >= 0 && getInt() <= 15;
    }

    public void setHexa(char c)
    {
        Hex = c;
    }

    public String toString()
    {
        return HexaToBits();
    }

    private char Hex;
}
