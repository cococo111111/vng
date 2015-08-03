/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.service;

import vng.zingme.payment.model.Money;
import vng.zingme.payment.model.MoneyResponse;
import vng.zingme.payment.model.User;
import vng.zingme.payment.model.UserResponse;

/**
 *
 * @author root
 */
public class PaymentService {
    
    private static final PaymentServiceGateway pgModel = PaymentServiceGateway.getMainInstance();
    /**
     * get money by userId
     * @param user
     * @return 
     */
    public MoneyResponse getMoney(User user) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            MoneyResponse result = pgModel.getMoney(user);
            ServiceMonitor.getInstance().incTotalGetMoney();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }
    
    /**
     * get user info by userId
     * @param user
     * @return 
     */
    public UserResponse getUser(User user) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            UserResponse result = pgModel.getUser(user);
            ServiceMonitor.getInstance().incTotalGetUser();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }
    
    /**
     * get money by userId
     * @param user
     * @return 
     */
    public MoneyResponse addMoney(Money money, String clientIP) {
        long startTime = System.nanoTime();
        if (pgModel != null) {
            MoneyResponse result = pgModel.addMoney(money, clientIP);
            ServiceMonitor.getInstance().incTotalAddMoney();
            ServiceMonitor.getInstance().addNano(System.nanoTime() - startTime);
            return result;
        }
        return null;
    }
}
