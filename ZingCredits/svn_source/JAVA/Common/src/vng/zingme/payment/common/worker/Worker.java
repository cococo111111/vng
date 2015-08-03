package vng.zingme.payment.common.worker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Worker {

    Thread _thr = null;
    IWorkerRunable _workerInTask = null;

    public Worker(IWorkerRunable workerInTask) {
        _workerInTask = workerInTask;
        _thr = new Thread(_workerInTask);
        _thr.start();
    }

    public void work(Object job) {
        _workerInTask.pushJob(job);
    }

    public void endWorker() {
        _workerInTask.stop();
    }
}
