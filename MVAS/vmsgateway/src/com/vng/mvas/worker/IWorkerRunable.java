package com.vng.mvas.worker;

import com.vng.mvas.models.Sending;

public interface IWorkerRunable extends Runnable {
    public void stop();
    public void pushJob(Sending obj);
}
