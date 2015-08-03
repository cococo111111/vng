/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.accountbalance;

import vng.zingme.payment.caching.monitor.CachingMBean;

/**
 *
 * @author root
 */
public interface AccBalCacheMBean extends CachingMBean {

    public double getAddRate();

    public double getSubRate();

    public double getLastAddTime();

    public double getLastSubTime();
}
