/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentgateway;

import java.util.List;
import org.apache.thrift.TException;
import vng.zingme.payment.thrift.TPayment;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_Response;
import vng.zingme.payment.thrift.T_Token;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class PaymentGatewayHandler implements TPayment.Iface {

    private static PaymentGatewayModel _model = null;

    public PaymentGatewayHandler() {
        _model = new PaymentGatewayModel(null);
    }

    public T_AccResponse getBalance(int userID) throws TException {
        if (_model == null) {
            return null;
        }

        return _model.getBalance(userID);
    }

    public void warmupCache(int userID) throws TException {
        if (_model == null) {
            return;
        }
        _model.warmupCache(userID);
    }

    public T_Response bill(String string, String string1, T_Token tt, String clientIP) throws TException {
        if (_model == null) {
            return null;
        }
        return _model.processBill(string, string1, tt, clientIP);
    }

//    public T_Transaction parseTransaction(String string, String string1) throws TException {
//        if (_model == null) {
//            return null;
//        }
//        return _model.parseTransaction(string, string1);
//    }
    public List<String> zingUnSignature(String string, String string1, final int kindofkey) throws TException {
        if (_model == null) {
            return null;
        }
        return _model.zingUnSignature(string, string1, kindofkey);
    }

    public T_Response billing(final T_Transaction tx, final T_Token tk) throws TException {
        if (_model == null) {
            return null;
        }
        return _model.bill(tx, tk, "");
    }
}
