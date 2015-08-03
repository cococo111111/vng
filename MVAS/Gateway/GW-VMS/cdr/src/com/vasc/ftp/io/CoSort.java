// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoSort.java

package com.vasc.ftp.io;

import java.util.Date;
import java.util.Vector;

// Referenced classes of package com.vasc.ftp.io:
//            CoFile

public class CoSort
{

    CoSort(int sorttype)
    {
        this.sorttype = sorttype;
    }

    private boolean AgreaterB(CoFile a, CoFile b)
    {
        boolean compare = false;
        switch(sorttype)
        {
        case 1: // '\001'
            compare = a.compareNameToIgnoreCase(b) > 0;
            break;

        case 2: // '\002'
            compare = a.compareExtToIgnoreCase(b) > 0;
            break;

        case 3: // '\003'
            compare = a.length() > b.length();
            break;

        case 4: // '\004'
            compare = (new Date(a.lastModified())).after(new Date(b.lastModified()));
            break;

        case 5: // '\005'
            compare = a.compareTo(b) > 0;
            break;
        }
        return compare;
    }

    private boolean AsmallerB(CoFile a, CoFile b)
    {
        boolean compare = false;
        switch(sorttype)
        {
        case 1: // '\001'
            compare = a.compareNameToIgnoreCase(b) < 0;
            break;

        case 2: // '\002'
            compare = a.compareExtToIgnoreCase(b) < 0;
            break;

        case 3: // '\003'
            compare = a.length() < b.length();
            break;

        case 4: // '\004'
            compare = (new Date(a.lastModified())).before(new Date(b.lastModified()));
            break;

        case 5: // '\005'
            compare = a.compareTo(b) < 0;
            break;
        }
        return compare;
    }

    private boolean Pause()
        throws Exception
    {
        return true;
    }

    private void QuickSort(CoFile a[], int lo0, int hi0)
        throws Exception
    {
        int lo = lo0;
        int hi = hi0;
        if(hi0 > lo0)
        {
            CoFile mid = a[(lo0 + hi0) / 2];
            while(lo <= hi) 
            {
                while(lo < hi0 && Pause() && AsmallerB(a[lo], mid)) 
                    lo++;
                for(; hi > lo0 && Pause() && AgreaterB(a[hi], mid); hi--);
                if(lo <= hi)
                {
                    Swap(a, lo, hi);
                    lo++;
                    hi--;
                }
            }
            if(lo0 < hi)
                QuickSort(a, lo0, hi);
            if(lo < hi0)
                QuickSort(a, lo, hi0);
        }
    }

    private void Sort(CoFile a[])
        throws Exception
    {
        QuickSort(a, 0, a.length - 1);
        if((sorttype & 8) > 0)
        {
            for(int n = 0; n < a.length / 2; n++)
                Swap(a, n, a.length - 1 - n);

        }
    }

    private void Swap(CoFile a[], int i, int j)
    {
        CoFile T = a[i];
        a[i] = a[j];
        a[j] = T;
    }

    public static CoFile[] listFilter(CoFile list[], String filter[])
    {
        if(filter.length != 0)
        {
            Vector ffv = new Vector();
            for(int i = 0; i < list.length; i++)
                if(list[i].isDirectory() || list[i].isLink() || list[i].equalsExtTo(filter))
                    ffv.addElement(list[i]);

            CoFile ffs[] = new CoFile[ffv.size()];
            ffv.copyInto(ffs);
            return ffs;
        } else
        {
            return list;
        }
    }

    public static CoFile[] listOrder(CoFile list[], int sorttype)
    {
        CoSort s;
        if((sorttype & -9) == 6)
            break MISSING_BLOCK_LABEL_33;
        s = new CoSort(sorttype);
        s.Sort(list);
        break MISSING_BLOCK_LABEL_31;
        Exception e;
        e;
        return list;
        return list;
    }

    public static CoFile[] listSplit(CoFile list[])
    {
        Vector ffv = new Vector();
        for(int i = 0; i < list.length; i++)
            if(list[i].isDirectory() || list[i].isLink())
                ffv.addElement(list[i]);

        for(int i = 0; i < list.length; i++)
            if(!list[i].isDirectory() && !list[i].isLink())
                ffv.addElement(list[i]);

        CoFile ffs[] = new CoFile[ffv.size()];
        ffv.copyInto(ffs);
        return ffs;
    }

    public static final int ORDER_BY_DATE = 4;
    public static final int ORDER_BY_NAME = 1;
    public static final int ORDER_BY_NONE = 6;
    public static final int ORDER_BY_PATH = 5;
    public static final int ORDER_BY_SIZE = 3;
    public static final int ORDER_BY_TYPE = 2;
    public static final int ORDER_INVERSE = 8;
    private int sorttype;
}
