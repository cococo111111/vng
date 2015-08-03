/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentgateway;

/**
 *
 * @author root
 */
public interface PaymentGatewayModelMBean {

    public double getTransferRate();

    public long getTotalOperator();

    public long getTotalBillOperator();

    public double getBillingRate();

    public double getGetBalanceRate();

    public long getTotalRequestGetBalance();

    public long getGetMoney();

    public double getGetMoneyRate();

    public double getLastTransferTime();

    public double getLastGetMoneyTime();

    public double getLastBillingTime();
}
