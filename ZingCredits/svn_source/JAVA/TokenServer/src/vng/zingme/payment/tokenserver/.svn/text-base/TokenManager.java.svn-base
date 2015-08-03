/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.tokenserver;

import org.apache.log4j.Logger;
import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.TTokenResCode;
import vng.zingme.payment.thrift.T_Token;
import vng.zingme.util.StringUtil;

/**
 *
 * @author root
 */
public class TokenManager {

    private static long _validate = 0;
    private static Random _rd = null;
    private static String _privateKey = null;
    private static MessageDigest _digest = null;
    private static ConcurrentLinkedHashMap<String, Integer> _tokenManage = null;

    public TokenManager() {
        int sz = Integer.parseInt(System.getProperty("token-manage-size", "1000000"));
        _tokenManage = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, sz);

        _validate = Long.parseLong(System.getProperty("validate", "300"));

        _rd = new Random(System.currentTimeMillis());
        _privateKey = System.getProperty("private-key", "vng-zingpm-key");

        int hop_count = 10;
        while (_digest == null && hop_count > 0) {
            try {
                _digest = java.security.MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                log.warn(ex.getMessage());
            }
            --hop_count;
        }

        if (_digest == null) {
            log.warn("_digest is null!");
        }
        _var = System.currentTimeMillis();
    }

    public int checkToken(T_Token tk) {
        if (tk == null) {
            log.warn("Token is null.");
            return PaymentReturnCode.ERROR_OPERATOR_FAIL;
        }
        log.debug("Request check token " + tk.toString());

        Integer timeVal = null;

        try {
            _locker.lock();
            timeVal = _tokenManage.get(tk.pToken);
            _tokenManage.remove(tk.pToken);
        } finally {
            _locker.unlock();
        }

        if (timeVal == null) {
            return TTokenResCode.ZC_TK_RESCODE_NOT_EXIST.getValue();
        }

        int nw = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);
        if ((nw - timeVal) > _validate) {
            return TTokenResCode.ZC_TK_RESCODE_EXPIRE.getValue();
        }

        return TTokenResCode.ZC_TK_RESCODE_SUCCESS.getValue();
    }

    public T_Token getToken(int userID) {
        if (_digest == null) {
            return null;
        }

        int time = (int) (System.currentTimeMillis() / CommonDef.MILISECINSEC);
        String key = null;
        String msg = null;
        try {
            _locker.lock();
            msg = userID + _privateKey + String.valueOf(_rd.nextInt()) + String.valueOf(++_var);


            _digest.update(msg.getBytes());

            key = StringUtil.getHexString(_digest.digest());

            _tokenManage.put(key, time);
        } finally {
            _locker.unlock();
        }

        T_Token res = new T_Token(key, time);

        log.debug("Request get token " + res.toString());

        return res;
    }
    private final ReentrantLock _locker = new ReentrantLock();
    private static final Logger log = Logger.getLogger("appActions");
    private static long _var = 0;
}
