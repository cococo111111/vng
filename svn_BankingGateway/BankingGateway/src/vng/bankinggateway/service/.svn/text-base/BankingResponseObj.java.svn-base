/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingResponseObj {

    private String messageCode;

    /**
     * Get the value of messageCode
     *
     * @return the value of messageCode
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * Set the value of messageCode
     *
     * @param messageCode new value of messageCode
     */
    public void setMessageCode(String MessageCode) {
        this.messageCode = MessageCode;
    }
    
    private String actionCode;

    /**
     * Get the value of actionCode
     *
     * @return the value of actionCode
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Set the value of actionCode
     *
     * @param actionCode new value of actionCode
     */
    public void setActionCode(String ActionCode) {
        this.actionCode = ActionCode;
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
    
    private String userName;

    /**
     * Get the value of UserName
     *
     * @return the value of UserName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of UserName
     *
     * @param UserName new value of UserName
     */
    public void setUserName(String UserName) {
        this.userName = UserName;
    }
    
    private String agencyCode;

    /**
     * Get the value of AgencyCode
     *
     * @return the value of AgencyCode
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * Set the value of AgencyCode
     *
     * @param AgencyCode new value of AgencyCode
     */
    public void setAgencyCode(String AgencyCode) {
        this.agencyCode = AgencyCode;
    }
    
    private String providerCode;

    /**
     * Get the value of ProviderCode
     *
     * @return the value of ProviderCode
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     * Set the value of ProviderCode
     *
     * @param ProviderCode new value of ProviderCode
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
    
    private String redirectUrl;

    /**
     * Get the value of redirectUrl
     *
     * @return the value of redirectUrl
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * Set the value of redirectUrl
     *
     * @param redirectUrl new value of redirectUrl
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    
    private String MAC;

    /**
     * Get the value of MAC
     *
     * @return the value of MAC
     */
    public String getMAC() {
        return MAC;
    }

    /**
     * Set the value of MAC
     *
     * @param MAC new value of MAC
     */
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    @Override
    public String toString() {
        return messageCode + "|" + actionCode + "|" + transactionID + "|" + time + "|" 
                + userName + "|" + agencyCode + "|" + providerCode + "|" + amount + "|" 
                + moneyCode + "|" + clientIP + "|" + responseCode + "|" + redirectUrl + "|";
    }
    
    public String getScribeMessage() {
        return messageCode + "\t" + actionCode + "\t" + transactionID + "\t" + time + "\t" 
                + userName + "\t" + agencyCode + "\t" + providerCode + "\t" + amount + "\t" 
                + moneyCode + "\t" + clientIP + "\t" + responseCode + "\t" + redirectUrl + "\t" + MAC + "\t";
    }

    public boolean fromString(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.messageCode = objArr[0];
                this.actionCode = objArr[1];
                this.transactionID = objArr[2];
                this.time = objArr[3];
                this.userName = objArr[4];
                this.agencyCode = objArr[5];
                this.providerCode = objArr[6];
                this.amount = Integer.parseInt(objArr[7]);
                this.moneyCode = objArr[8];
                this.clientIP = objArr[9];
                this.responseCode = objArr[10];
                this.redirectUrl = objArr[11];
                this.MAC = objArr[12];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }
}
