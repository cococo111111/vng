/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.tokenserver;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.thrift.TToken;
import vng.zingme.payment.thrift.TTokenResCode;
import vng.zingme.payment.thrift.T_Token;

/**
 *
 * @author root
 */
public class TokenHandler implements TToken.Iface {

    private static TokenManager _tkManager = null;

    public TokenHandler() {
        int hop_count = 10;
        synchronized (lockObj) {
            while (_tkManager == null && hop_count > 0) {
                _tkManager = new TokenManager();
                --hop_count;
            }
        }
        if (_tkManager == null) {
            Logger.getLogger("appActions").warn("_tkManager is null!");
        }
    }

    public int checkToken(T_Token tk) throws TException {
        if (_tkManager == null) {
            return TTokenResCode.ZC_TK_RESCODE_UNKNOWN.getValue();
        }
        return _tkManager.checkToken(tk);
    }

    public T_Token getToken(int userID) throws TException {
        if (_tkManager == null) {
            return null;
        }
        return _tkManager.getToken(userID);
    }
    private static Object lockObj = new Object();
}
