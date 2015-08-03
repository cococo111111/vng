/**
 *Class: LatencyTracker
 * Copyright VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.service;

import java.util.concurrent.atomic.AtomicLong;

public class LatencyTracker {

    private long latencyPeakTime = 0L;
    private long latencyPeak = 0L;
    private long rpsPeakTime = 0L;
    private long rpsPeak = 0L;
    private final AtomicLong curSec = new AtomicLong(-1L);
    private final AtomicLong opCount = new AtomicLong(0L);
    private final AtomicLong totalLatency = new AtomicLong(0L);
    private long lastLatency = 0L;
    private long lastOpCount = 0L;
    private long lastSec = 0L;
    private long opsPerSec = 0L;

    public LatencyTracker() {
        lastSec = System.currentTimeMillis();
    }

    public void clear() {
        latencyPeakTime = 0L;
        latencyPeak = 0L;
        rpsPeak = 0L;
        rpsPeakTime = 0L;
        lastLatency = 0L;
        lastOpCount = 0L;
        lastSec = 0L;
        opsPerSec = 0L;
        curSec.set(-1L);
        opCount.set(0L);
        totalLatency.set(0L);
    }

    public void addNano(long nanos) {
        addMicro(nanos / 1000L);
    }

    public void addMicro(long micros) {
        opCount.incrementAndGet();
        totalLatency.addAndGet(micros);

        if (micros > latencyPeak) {
            latencyPeak = micros;
            latencyPeakTime = System.currentTimeMillis();
        }
    }

    public long getOpCount() {
        return opCount.get();
    }

    public long getTotalLatencyMicros() {
        return totalLatency.get();
    }

    public double getAvgLatency() {
        long ops = opCount.get();
        long n = totalLatency.get();
        if (ops == 0L) {
            return 0.0D;
        }

        return n / ops;
    }

    public double getRecentLatencyMicros() {
        long ops = opCount.get();
        long n = totalLatency.get();
        long sec = System.currentTimeMillis() / 1000L;

        double ret = 0.0D;
        try {
            opsPerSec = ((ops - lastOpCount) / (sec - lastSec));

            if (opsPerSec > rpsPeak) {
                rpsPeak = opsPerSec;
                rpsPeakTime = System.currentTimeMillis();
            }
            if (ops - lastOpCount != 0L) {
                ret = (n - lastLatency) / (ops - lastOpCount);
            }
            return ret;
        } finally {
            lastLatency = n;
            lastOpCount = ops;
            lastSec = sec;
        }
    }

    public long getLatencyPeak() {
        return latencyPeak;
    }

    public long getLatencyPeakTime() {
        return latencyPeakTime;
    }

    public long getRPSPeak() {
        return rpsPeak;
    }

    public long getRPSPeakTime() {
        return rpsPeakTime;
    }

    private void autoCalculate() {
        long sec = System.currentTimeMillis() / 1000L;
        long cur = curSec.get();
        if (cur == -1L) {
            curSec.set(sec);
        } else if (cur != sec) {
            getRecentLatencyMicros();
            curSec.getAndSet(sec);
        }
    }

    public long getTotalOpsPerSec() {
        return opsPerSec;
    }
}
