// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Md5.java

package com.vmg.common.util;


public class Md5
{
    private abstract class Fcore
    {

        abstract int f(int i, int j, int k);

        private Fcore()
        {
        }

        Fcore(Fcore fcore)
        {
            this();
        }
    }


    public Md5()
    {
        F1 = new Fcore() {

            int f(int x, int y, int z)
            {
                return z ^ x & (y ^ z);
            }

        }
;
        F2 = new Fcore() {

            int f(int x, int y, int z)
            {
                return y ^ z & (x ^ y);
            }

        }
;
        F3 = new Fcore() {

            int f(int x, int y, int z)
            {
                return x ^ y ^ z;
            }

        }
;
        F4 = new Fcore() {

            int f(int x, int y, int z)
            {
                return y ^ (x | ~z);
            }

        }
;
        buf = new int[4];
        buf[0] = 0x67452301;
        buf[1] = 0xefcdab89;
        buf[2] = 0x98badcfe;
        buf[3] = 0x10325476;
        bits = 0L;
        in = new byte[64];
        inint = new int[16];
    }

    public void update(byte newbuf[])
    {
        update(newbuf, 0, newbuf.length);
    }

    public void update(byte newbuf[], int length)
    {
        update(newbuf, 0, length);
    }

    public void update(byte newbuf[], int bufstart, int buflen)
    {
        int len = buflen;
        int t = (int)bits;
        bits += len << 3;
        t = t >>> 3 & 0x3f;
        if(t != 0)
        {
            int p = t;
            t = 64 - t;
            if(len < t)
            {
                System.arraycopy(newbuf, bufstart, in, p, len);
                return;
            }
            System.arraycopy(newbuf, bufstart, in, p, t);
            transform();
            bufstart += t;
            len -= t;
        }
        for(; len >= 64; len -= 64)
        {
            System.arraycopy(newbuf, bufstart, in, 0, 64);
            transform();
            bufstart += 64;
        }

        System.arraycopy(newbuf, bufstart, in, 0, len);
    }

    public void md5final(byte digest[])
    {
        int count = (int)(bits >>> 3 & 63L);
        int p = count;
        in[p++] = -128;
        count = 63 - count;
        if(count < 8)
        {
            zeroByteArray(in, p, count);
            transform();
            zeroByteArray(in, 0, 56);
        } else
        {
            zeroByteArray(in, p, count - 8);
        }
        int lowbits = (int)bits;
        int highbits = (int)(bits >>> 32);
        PUT_32BIT_LSB_FIRST(in, 56, lowbits);
        PUT_32BIT_LSB_FIRST(in, 60, highbits);
        transform();
        PUT_32BIT_LSB_FIRST(digest, 0, buf[0]);
        PUT_32BIT_LSB_FIRST(digest, 4, buf[1]);
        PUT_32BIT_LSB_FIRST(digest, 8, buf[2]);
        PUT_32BIT_LSB_FIRST(digest, 12, buf[3]);
        zeroByteArray(in);
        zeroIntArray(buf);
        bits = 0L;
        zeroIntArray(inint);
    }

    public static String Hash(String in)
    {
        if(in == null)
            return null;
        Md5 md = new Md5();
        byte out[] = new byte[16];
        byte buf[] = in.getBytes();
        for(int i = 0; i < in.length(); i++)
            md.update(buf);

        md.md5final(out);
        return dumpBytes(out);
    }

    private void zeroByteArray(byte a[])
    {
        zeroByteArray(a, 0, a.length);
    }

    private void zeroByteArray(byte a[], int start, int length)
    {
        setByteArray(a, (byte)0, start, length);
    }

    private void setByteArray(byte a[], byte val, int start, int length)
    {
        int end = start + length;
        for(int i = start; i < end; i++)
            a[i] = val;

    }

    private void zeroIntArray(int a[])
    {
        zeroIntArray(a, 0, a.length);
    }

    private void zeroIntArray(int a[], int start, int length)
    {
        setIntArray(a, 0, start, length);
    }

    private void setIntArray(int a[], int val, int start, int length)
    {
        int end = start + length;
        for(int i = start; i < end; i++)
            a[i] = val;

    }

    private int MD5STEP(Fcore f, int w, int x, int y, int z, int data, int s)
    {
        w += f.f(x, y, z) + data;
        w = w << s | w >>> 32 - s;
        w += x;
        return w;
    }

    private void transform()
    {
        int inint[] = new int[16];
        for(int i = 0; i < 16; i++)
            inint[i] = GET_32BIT_LSB_FIRST(in, 4 * i);

        int a = buf[0];
        int b = buf[1];
        int c = buf[2];
        int d = buf[3];
        a = MD5STEP(F1, a, b, c, d, inint[0] + 0xd76aa478, 7);
        d = MD5STEP(F1, d, a, b, c, inint[1] + 0xe8c7b756, 12);
        c = MD5STEP(F1, c, d, a, b, inint[2] + 0x242070db, 17);
        b = MD5STEP(F1, b, c, d, a, inint[3] + 0xc1bdceee, 22);
        a = MD5STEP(F1, a, b, c, d, inint[4] + 0xf57c0faf, 7);
        d = MD5STEP(F1, d, a, b, c, inint[5] + 0x4787c62a, 12);
        c = MD5STEP(F1, c, d, a, b, inint[6] + 0xa8304613, 17);
        b = MD5STEP(F1, b, c, d, a, inint[7] + 0xfd469501, 22);
        a = MD5STEP(F1, a, b, c, d, inint[8] + 0x698098d8, 7);
        d = MD5STEP(F1, d, a, b, c, inint[9] + 0x8b44f7af, 12);
        c = MD5STEP(F1, c, d, a, b, inint[10] + -42063, 17);
        b = MD5STEP(F1, b, c, d, a, inint[11] + 0x895cd7be, 22);
        a = MD5STEP(F1, a, b, c, d, inint[12] + 0x6b901122, 7);
        d = MD5STEP(F1, d, a, b, c, inint[13] + 0xfd987193, 12);
        c = MD5STEP(F1, c, d, a, b, inint[14] + 0xa679438e, 17);
        b = MD5STEP(F1, b, c, d, a, inint[15] + 0x49b40821, 22);
        a = MD5STEP(F2, a, b, c, d, inint[1] + 0xf61e2562, 5);
        d = MD5STEP(F2, d, a, b, c, inint[6] + 0xc040b340, 9);
        c = MD5STEP(F2, c, d, a, b, inint[11] + 0x265e5a51, 14);
        b = MD5STEP(F2, b, c, d, a, inint[0] + 0xe9b6c7aa, 20);
        a = MD5STEP(F2, a, b, c, d, inint[5] + 0xd62f105d, 5);
        d = MD5STEP(F2, d, a, b, c, inint[10] + 0x2441453, 9);
        c = MD5STEP(F2, c, d, a, b, inint[15] + 0xd8a1e681, 14);
        b = MD5STEP(F2, b, c, d, a, inint[4] + 0xe7d3fbc8, 20);
        a = MD5STEP(F2, a, b, c, d, inint[9] + 0x21e1cde6, 5);
        d = MD5STEP(F2, d, a, b, c, inint[14] + 0xc33707d6, 9);
        c = MD5STEP(F2, c, d, a, b, inint[3] + 0xf4d50d87, 14);
        b = MD5STEP(F2, b, c, d, a, inint[8] + 0x455a14ed, 20);
        a = MD5STEP(F2, a, b, c, d, inint[13] + 0xa9e3e905, 5);
        d = MD5STEP(F2, d, a, b, c, inint[2] + 0xfcefa3f8, 9);
        c = MD5STEP(F2, c, d, a, b, inint[7] + 0x676f02d9, 14);
        b = MD5STEP(F2, b, c, d, a, inint[12] + 0x8d2a4c8a, 20);
        a = MD5STEP(F3, a, b, c, d, inint[5] + 0xfffa3942, 4);
        d = MD5STEP(F3, d, a, b, c, inint[8] + 0x8771f681, 11);
        c = MD5STEP(F3, c, d, a, b, inint[11] + 0x6d9d6122, 16);
        b = MD5STEP(F3, b, c, d, a, inint[14] + 0xfde5380c, 23);
        a = MD5STEP(F3, a, b, c, d, inint[1] + 0xa4beea44, 4);
        d = MD5STEP(F3, d, a, b, c, inint[4] + 0x4bdecfa9, 11);
        c = MD5STEP(F3, c, d, a, b, inint[7] + 0xf6bb4b60, 16);
        b = MD5STEP(F3, b, c, d, a, inint[10] + 0xbebfbc70, 23);
        a = MD5STEP(F3, a, b, c, d, inint[13] + 0x289b7ec6, 4);
        d = MD5STEP(F3, d, a, b, c, inint[0] + 0xeaa127fa, 11);
        c = MD5STEP(F3, c, d, a, b, inint[3] + 0xd4ef3085, 16);
        b = MD5STEP(F3, b, c, d, a, inint[6] + 0x4881d05, 23);
        a = MD5STEP(F3, a, b, c, d, inint[9] + 0xd9d4d039, 4);
        d = MD5STEP(F3, d, a, b, c, inint[12] + 0xe6db99e5, 11);
        c = MD5STEP(F3, c, d, a, b, inint[15] + 0x1fa27cf8, 16);
        b = MD5STEP(F3, b, c, d, a, inint[2] + 0xc4ac5665, 23);
        a = MD5STEP(F4, a, b, c, d, inint[0] + 0xf4292244, 6);
        d = MD5STEP(F4, d, a, b, c, inint[7] + 0x432aff97, 10);
        c = MD5STEP(F4, c, d, a, b, inint[14] + 0xab9423a7, 15);
        b = MD5STEP(F4, b, c, d, a, inint[5] + 0xfc93a039, 21);
        a = MD5STEP(F4, a, b, c, d, inint[12] + 0x655b59c3, 6);
        d = MD5STEP(F4, d, a, b, c, inint[3] + 0x8f0ccc92, 10);
        c = MD5STEP(F4, c, d, a, b, inint[10] + 0xffeff47d, 15);
        b = MD5STEP(F4, b, c, d, a, inint[1] + 0x85845dd1, 21);
        a = MD5STEP(F4, a, b, c, d, inint[8] + 0x6fa87e4f, 6);
        d = MD5STEP(F4, d, a, b, c, inint[15] + 0xfe2ce6e0, 10);
        c = MD5STEP(F4, c, d, a, b, inint[6] + 0xa3014314, 15);
        b = MD5STEP(F4, b, c, d, a, inint[13] + 0x4e0811a1, 21);
        a = MD5STEP(F4, a, b, c, d, inint[4] + 0xf7537e82, 6);
        d = MD5STEP(F4, d, a, b, c, inint[11] + 0xbd3af235, 10);
        c = MD5STEP(F4, c, d, a, b, inint[2] + 0x2ad7d2bb, 15);
        b = MD5STEP(F4, b, c, d, a, inint[9] + 0xeb86d391, 21);
        buf[0] += a;
        buf[1] += b;
        buf[2] += c;
        buf[3] += d;
    }

    private int GET_32BIT_LSB_FIRST(byte b[], int off)
    {
        return b[off + 0] & 0xff | (b[off + 1] & 0xff) << 8 | (b[off + 2] & 0xff) << 16 | (b[off + 3] & 0xff) << 24;
    }

    private void PUT_32BIT_LSB_FIRST(byte b[], int off, int value)
    {
        b[off + 0] = (byte)(value & 0xff);
        b[off + 1] = (byte)(value >> 8 & 0xff);
        b[off + 2] = (byte)(value >> 16 & 0xff);
        b[off + 3] = (byte)(value >> 24 & 0xff);
    }

    private static String dumpBytes(byte bytes[])
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++)
        {
            if(i % 32 == 0 && i != 0)
                sb.append("\n");
            String s = Integer.toHexString(bytes[i]);
            if(s.length() < 2)
                s = "0" + s;
            if(s.length() > 2)
                s = s.substring(s.length() - 2);
            sb.append(s);
        }

        return sb.toString();
    }

    int buf[];
    long bits;
    byte in[];
    int inint[];
    private Fcore F1;
    private Fcore F2;
    private Fcore F3;
    private Fcore F4;
}
