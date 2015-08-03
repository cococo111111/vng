/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author root
 */
public class TopupIdentifyCheckObj {

    private String messageCode;
    private String actionCode;
    private String transactionCode;
    private String transactionChannel;
    private String time;
    private String accountName;
    private String agencyCode;
    private String paymentAccount;
    private String amount;
    private String moneyCode;
    private String description;
    private String mAC;
    private String responseCode;

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionChannel() {
        return transactionChannel;
    }

    public void setTransactionChannel(String transactionChannel) {
        this.transactionChannel = transactionChannel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode;
    }

    public String getmAC() {
        return mAC;
    }

    public void setmAC(String mAC) {
        this.mAC = mAC;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public TopupIdentifyCheckObj() {
    }

    public boolean fromString(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.messageCode = (objArr[0]);
                this.actionCode = (objArr[1]);
                this.transactionCode = (objArr[2]);
                this.transactionChannel = (objArr[3]);
                this.time = objArr[4];
                this.accountName = objArr[5];
                this.agencyCode = objArr[6];
                this.paymentAccount = objArr[7];
                this.amount = objArr[8];
                this.moneyCode = objArr[9];
                this.description = objArr[10];
                this.mAC = objArr[11];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false; // do not use try-catch for flow-code. fixxxxxx
        }
        return flag;
    }

    /**
     * get String for comparing with Mac
     *
     * @return
     */
    public String toStringFromRequest() {
        return messageCode + "|" + actionCode + "|" + transactionCode + "|" + transactionChannel
               + "|" + time + "|" + accountName + "|" + agencyCode + "|" + paymentAccount
               + "|" + amount + "|" + moneyCode + "|" + description + "|";
    }

    /**
     * get String from request for logging
     *
     * @return
     */
    public String toStringFromRequestWithMac() {
        return messageCode + "\t" + actionCode + "\t" + transactionCode + "\t" + transactionChannel
               + "\t" + time + "\t" + accountName + "\t" + agencyCode + "\t" + paymentAccount
               + "\t" + amount + "\t" + moneyCode + "\t" + description + "\t" + mAC + "\t";
    }

    /**
     * get String for response
     *
     * @return
     */
    public String toStringForResponse() {
        return messageCode + "|" + actionCode + "|" + transactionCode + "|" + transactionChannel
               + "|" + time + "|" + accountName + "|" + agencyCode + "|" + paymentAccount
               + "|" + amount + "|" + moneyCode + "|" + responseCode + "|";
    }

    /**
     * get String for response with MAC
     *
     * @return
     */
    public String toStringForResponseWithMac() {
        return messageCode + "\t" + actionCode + "\t" + transactionCode + "\t" + transactionChannel
               + "\t" + time + "\t" + accountName + "\t" + agencyCode + "\t" + paymentAccount
               + "\t" + amount + "\t" + moneyCode + "\t" + responseCode + "\t" + mAC + "\t";
    }
}
