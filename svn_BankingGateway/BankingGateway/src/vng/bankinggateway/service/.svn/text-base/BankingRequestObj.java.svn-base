/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingRequestObj {

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
    
    private int transactionID;

    /**
     * Get the value of transactionID
     *
     * @return the value of transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * Set the value of transactionID
     *
     * @param transactionID new value of transactionID
     */
    public void setTransactionID(int TransactionID) {
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
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String UserName) {
        this.userName = UserName;
    }
    
    private String agencyCode;

    /**
     * Get the value of agencyCode
     *
     * @return the value of agencyCode
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * Set the value of agencyCode
     *
     * @param agencyCode new value of agencyCode
     */
    public void setAgencyCode(String AgencyCode) {
        this.agencyCode = AgencyCode;
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
        return messageCode + "|" + actionCode + "|" + transactionID + "|" + time + "|" + userName + "|" + agencyCode + "|" + providerCode + "|" + amount + "|" + moneyCode + "|" + clientIP + "|" + description+"|" ;
    }

    public BankingRequestObj(String MessageCode, String ActionCode, int TransactionID, String Time, String UserName, String AgencyCode, String ProviderCode, int amount, String moneyCode, String clientIP, String description, String MAC) {
        this.messageCode = MessageCode;
        this.actionCode = ActionCode;
        this.transactionID = TransactionID;
        this.time = Time;
        this.userName = UserName;
        this.agencyCode = AgencyCode;
        this.providerCode = ProviderCode;
        this.amount = amount;
        this.moneyCode = moneyCode;
        this.clientIP = clientIP;
        this.description = description;
        this.MAC = MAC;
    }
    
    public String getScribeMessage() {
        return messageCode + "\t" + actionCode + "\t" + transactionID + "\t" + time 
                + "\t" + userName + "\t" + agencyCode + "\t" + providerCode + "\t" 
                + amount + "\t" + moneyCode + "\t" + clientIP + "\t" + description+"\t" + MAC + "\t";
    }
}
