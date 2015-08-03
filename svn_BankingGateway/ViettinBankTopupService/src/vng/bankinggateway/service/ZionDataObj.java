/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class ZionDataObj {
    
    private String agencyCode;

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    private String messageCode;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    private String transactionID;

    /**
     * Get the value of transactionID
     *
     * @return the value of transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Set the value of transactionID
     *
     * @param transactionID new value of transactionID
     */
    public void setTransactionID(String TransactionID) {
        this.transactionID = TransactionID;
    }
    private String time;

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the value of time
     *
     * @param time new value of time
     */
    public void setTime(String Time) {
        this.time = Time;
    }
    private String paymentMethod;

    /**
     * Get the value of paymentMethod
     *
     * @return the value of paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the value of paymentMethod
     *
     * @param paymentMethod new value of paymentMethod
     */
    public void setPaymentMethod(String UserName) {
        this.paymentMethod = UserName;
    }
    private String providerCode;

    /**
     * Get the value of providerCode
     *
     * @return the value of providerCode
     */
    public String getProviderCode() {
        return providerCode;
    }
    private String responseCode;

    /**
     * Get the value of responseCode
     *
     * @return the value of responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Set the value of responseCode
     *
     * @param responseCode new value of responseCode
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Set the value of providerCode
     *
     * @param providerCode new value of providerCode
     */
    public void setProviderCode(String ProviderCode) {
        this.providerCode = ProviderCode;
    }
    private int amount;

    /**
     * Get the value of amount
     *
     * @return the value of amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the value of amount
     *
     * @param amount new value of amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    private String moneyCode;

    /**
     * Get the value of moneyCode
     *
     * @return the value of moneyCode
     */
    public String getMoneyCode() {
        return moneyCode;
    }

    /**
     * Set the value of moneyCode
     *
     * @param moneyCode new value of moneyCode
     */
    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode;
    }
    private String bankAccountCode;

    /**
     * Get the value of bankAccountCode
     *
     * @return the value of bankAccountCode
     */
    public String getBankAccountCode() {
        return bankAccountCode;
    }

    /**
     * Set the value of bankAccountCode
     *
     * @param bankAccountCode new value of bankAccountCode
     */
    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }
    private String signature;

    /**
     * Get the value of signature
     *
     * @return the value of signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Set the value of signature
     *
     * @param signature new value of signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
    private String clientIP;

    /**
     * Get the value of clientIP
     *
     * @return the value of clientIP
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * Set the value of clientIP
     *
     * @param clientIP new value of clientIP
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
    private String description;

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    private boolean updatedStatus = false;

    /**
     * Get the value of updatedStatus
     *
     * @return the value of updatedStatus
     */
    public boolean isUpdatedStatus() {
        return updatedStatus;
    }
    private boolean calledEsale = false;

    /**
     * Get the value of isCalledEsale
     *
     * @return the value of isCalledEsale
     */
    public boolean isCalledEsale() {
        return calledEsale;
    }

    /**
     * Set the value of isCalledEsale
     *
     * @param isCalledEsale new value of isCalledEsale
     */
    public void setCalledEsale(boolean calledEsale) {
        this.calledEsale = calledEsale;
    }

    /**
     * Set the value of updatedStatus
     *
     * @param updatedStatus new value of isUpdatedStatus
     */
    public void setUpdatedStatus(boolean updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    public String toStringForResponseWithoutSignature() {
        return responseCode + "|" + description + "|" + providerCode + "|" + transactionID
                + "|" + time + "|";
    }
    
    public String toStringForResponse() {
        return responseCode + "|" + description + "|" + providerCode + "|" + transactionID
                + "|" + time + "|" + signature + "|";
    }
    
    public String getScribeMessageForResponse() {
         return responseCode + "\t" + description + "\t" + providerCode + "\t" + transactionID
                + "\t" + time + "\t" + signature + "\t";
    }

    public String getScribeMessageForRequest() {
        return responseCode + "\t" + providerCode + "\t" + transactionID + "\t" + amount
                + "\t" + moneyCode + "\t" + bankAccountCode + "\t" + paymentMethod + "\t" + time
                + "\t" + description + "\t" + signature + "\t";
    }
    
    public String toStringForRequestWithoutSignature() {
        return responseCode + "|" + providerCode + "|" + transactionID + "|" + amount
                + "|" + moneyCode + "|" + bankAccountCode + "|" + paymentMethod + "|" + time
                + "|" + description + "|";
    }

    public ZionDataObj() {
    }

    public boolean fromString(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.responseCode = (objArr[0]);
                this.providerCode = (objArr[1]);
                this.transactionID = (objArr[2]);
                this.amount = Integer.parseInt(objArr[3]);
                this.moneyCode = (objArr[4]);
                this.bankAccountCode = (objArr[5]);
                this.paymentMethod = objArr[6];
                this.time = objArr[7];
                this.description = objArr[8];
                this.signature = objArr[9];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public String toQueueMessage() {
        return responseCode + "|" + providerCode + "|" + transactionID + "|" + amount
                + "|" + moneyCode + "|" + bankAccountCode + "|" + paymentMethod + "|" + time
                + "|" + description + "|" + signature + "|";
    }

    public boolean fromQueueMessage(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.responseCode = (objArr[0]);
                this.providerCode = (objArr[1]);
                this.transactionID = (objArr[2]);
                this.amount = Integer.parseInt(objArr[3]);
                this.moneyCode = (objArr[4]);
                this.bankAccountCode = (objArr[5]);
                this.paymentMethod = objArr[6];
                this.time = objArr[7];
                this.description = objArr[8];
                this.signature = objArr[9];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }
}
