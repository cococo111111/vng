/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class VietTinDataObj {

    public VietTinDataObj(String refID, String time, String username, String agencyCode, 
            String region, int amount, String clientIP, String description, 
            String bankCode, String commision, String urlCallBack, String transferType, String paymentMethod) {
        this.refId = refID;
        this.agencyCode = agencyCode;
        this.time = time;
        this.username = username;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.moneyCode = "VND";
        this.region = region;
        this.clientIP = clientIP;
        this.description = description;
        this.bankCode = bankCode;
    }

    private String message;
    private String urlRedirect;
    private String region;
    private String username;
    private String refId;
    private String bankCode;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlRedirect() {
        return urlRedirect;
    }

    public void setUrlRedirect(String urlRedirect) {
        this.urlRedirect = urlRedirect;
    }
    
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

    public String toStringForRequestWithoutSignature() {
        return providerCode + "|" + transactionID + "|" + amount + "|" + moneyCode
                + "|" + paymentMethod + "|" + time + "|" + description + "|" + clientIP + "|";
    }

    public String toStringForRequest() {
        return providerCode + "|" + transactionID + "|" + amount + "|" + moneyCode
                + "|" + paymentMethod + "|" + time + "|" + description + "|" + clientIP + "|" + signature + "|";
    }

    public String getScribeMessageForRequest() {
        return providerCode + "\t" + transactionID + "\t" + amount + "\t" + moneyCode + "\t" + paymentMethod
                + "\t" + time + "\t" + description + "\t" + clientIP + "\t" + signature + "\t";
    }

    public VietTinDataObj() {
    }

    public boolean fromStringForResponse(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.responseCode = (objArr[0]);
                this.message = (objArr[1]);
                this.urlRedirect = (objArr[2]);
                this.signature = objArr[3];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public String toStringForResponseWithoutSignature() {
        return responseCode + "|" + message + "|" + urlRedirect + "|";
    }

    public String toStringForResponse() {
        return responseCode + "|" + message + "|" + urlRedirect + "|" + signature + "|";
    }

    public String getScribeMessageForResponse() {
        return responseCode + "\t" + message + "\t" + urlRedirect + signature + "\t";
    }
}
