/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.admin;

import org.apache.thrift.TException;
import vng.zingme.payment.thrift.TAdminServer;

/**
 *
 * @author root
 */
public class AdminHandler implements TAdminServer.Iface {

    public int adjustUser(final int userID, final double adjustMoney, final String adminSig, final String reason, final String clientIP, final int time) throws TException {
        return AdminModel.adjustUser(userID, adjustMoney, adminSig, reason, clientIP, time);
    }

    public int compensate(final int userID, final double adjustMoney, final String adminSig, final String reason, final String clientIP, final int time, final String appID, final int txType) throws TException {
        return AdminModel.compensate(userID, adjustMoney, adminSig, reason, clientIP, time, appID, txType);
    }
}
