package vng.zingme.payment.paymentgateway;

import vng.zingme.payment.paymentgateway.push.ws.GetMoney;
import vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse;
import vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse;
import vng.zingme.payment.paymentgateway.push.ws.AddMoneyServiceSkeletonInterface;
import vng.zingme.payment.paymentgateway.push.ws.AddMoney;
import vng.zingme.payment.paymentgateway.push.ws.GetSum;
import vng.zingme.payment.paymentgateway.push.ws.GetSumResponse;
import vng.zingme.payment.paymentgateway.push.ws.GetUser;
import vng.zingme.payment.paymentgateway.push.ws.GetUserResponse;
import vng.zingme.payment.paymentgateway.push.ws.PushMoney;
import vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class PaymentGatewayServerHandler implements AddMoneyServiceSkeletonInterface {

    public GetMoneyResponse GetMoney(GetMoney getMoney) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            GetMoneyResponse result = pgModel.getMoney(getMoney);
            ServiceMonitor.getInstance().incTotalGetMoney();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }

    public AddMoneyResponse AddMoney(AddMoney addMoney, String clientIP) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            AddMoneyResponse result = pgModel.addMoney(addMoney, clientIP);
            ServiceMonitor.getInstance().incTotalAddMoney();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }
    private static final PaymentGatewayModel pgModel = PaymentGatewayModel.getMainInstance();

    public PushMoneyResponse PushMoney(PushMoney pushMoney, String clientIP) {
        return pgModel.pushMoney(pushMoney, clientIP);
    }

    public GetSumResponse GetSum(GetSum getSum) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            GetSumResponse result = pgModel.GetSum(getSum);
            ServiceMonitor.getInstance().incTotalGetSum();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }

    public GetUserResponse GetUser(GetUser getUser) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            GetUserResponse result = pgModel.GetUser(getUser);
            ServiceMonitor.getInstance().incTotalGetUser();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }
}
