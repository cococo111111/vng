/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.service;

public interface ServiceMonitorMBean {

    public long getTotalGetUser();

    public long getTotalGetSum();

    public long getTotalAddMoney();

    public long getTotalGetMoney();

    public long getTotalReadLatencyMicros();

    public long getReadOperationPerSecond();

    public long getReadOperations();

    public String getTotalReadLatencyAvg();

    public String getRecentLatencyMicros();
}
