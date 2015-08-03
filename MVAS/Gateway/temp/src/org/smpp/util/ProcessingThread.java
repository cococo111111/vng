// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProcessingThread.java

package org.smpp.util;

import org.smpp.SmppObject;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;

public abstract class ProcessingThread extends SmppObject
    implements Runnable
{

    public ProcessingThread()
    {
        keepProcessing = true;
        processingStatus = 0;
        processingStatusLock = new Object();
        termException = null;
        processingThread = null;
    }

    public abstract void process();

    public void start()
    {
        SmppObject.debug.enter(9, this, "start()");
        if(!isProcessing())
        {
            setProcessingStatus((byte)0);
            termException = null;
            keepProcessing = true;
            processingThread = new Thread(this);
            processingThread.setName(generateIndexedThreadName());
            processingThread.start();
            for(; isInitialising(); Thread.yield());
        }
        SmppObject.debug.exit(9, this);
    }

    public void stop()
    {
        SmppObject.debug.enter(9, this, "stop()");
        if(isProcessing())
        {
            stopProcessing(null);
            for(; !isFinished(); Thread.yield());
        }
        SmppObject.debug.exit(9, this);
    }

    protected void stopProcessing(Exception e)
    {
        setTermException(e);
        keepProcessing = false;
    }

    public void run()
    {
        SmppObject.debug.enter(9, this, "run()");
        try
        {
            setProcessingStatus((byte)1);
            while(keepProcessing) 
            {
                process();
                Thread.yield();
            }
        }
        catch(Exception e)
        {
            setTermException(e);
            SmppObject.debug.write("ProcessingThread.run() caught unhadled exception " + e);
            SmppObject.event.write(e, "ProcessingThread.run() unhadled exception");
        }
        finally
        {
            setProcessingStatus((byte)2);
            SmppObject.debug.exit(9, this);
        }
    }

    public String getThreadName()
    {
        return "ProcThread";
    }

    public int getThreadIndex()
    {
        return ++threadIndex;
    }

    public String generateIndexedThreadName()
    {
        return getThreadName() + "-" + getThreadIndex();
    }

    protected void setTermException(Exception e)
    {
        termException = e;
    }

    public Exception getTermException()
    {
        return termException;
    }

    private void setProcessingStatus(byte value)
    {
        synchronized(processingStatusLock)
        {
            processingStatus = value;
        }
    }

    private boolean isInitialising()
    {
        Object obj = processingStatusLock;
        JVM INSTR monitorenter ;
        return processingStatus == 0;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isProcessing()
    {
        Object obj = processingStatusLock;
        JVM INSTR monitorenter ;
        return processingStatus == 1;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isFinished()
    {
        Object obj = processingStatusLock;
        JVM INSTR monitorenter ;
        return processingStatus == 2;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String PROCESSING_THREAD_NAME = "ProcThread";
    private static int threadIndex = 0;
    private boolean keepProcessing;
    private byte processingStatus;
    private static final byte PROC_INITIALISING = 0;
    private static final byte PROC_RECEIVING = 1;
    private static final byte PROC_FINISHED = 2;
    private Object processingStatusLock;
    private Exception termException;
    private Thread processingThread;

}
