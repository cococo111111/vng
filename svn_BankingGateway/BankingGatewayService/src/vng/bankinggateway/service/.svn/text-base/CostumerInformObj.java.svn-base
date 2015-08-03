/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author root
 */
public class CostumerInformObj {

    private String messageCode;
    private String actionCode;
    private String transactionId;
    private String transactionChannel;
    private String time;
    private String accountName;
    private String mAC;
    private String responseCode;
    private String agencyCode;
    private String fullName;
    private String idNumber;
    private String address;
    private String region;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionChannel() {
        return transactionChannel;
    }

    public void setTransactionChannel(String transactionChannel) {
        this.transactionChannel = transactionChannel;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
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

    public String getmAC() {
        return mAC;
    }

    public void setmAC(String mAC) {
        this.mAC = mAC;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String MessageCode) {
        this.messageCode = MessageCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public CostumerInformObj() {
    }

    public boolean fromString(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.messageCode = (objArr[0]);
                this.actionCode = (objArr[1]);
                this.transactionId = (objArr[2]);
                this.transactionChannel = (objArr[3]);
                this.time = objArr[4];
                this.accountName = objArr[5];
                this.mAC = objArr[6];


                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * get String to check Mac
     *
     * @return
     */
    public String toStringFromRequest() {
        return messageCode + "|" + actionCode + "|" + transactionId + "|" + transactionChannel
               + "|" + time + "|" + accountName + "|";
    }

    /**
     * is used to log for request
     *
     * @return
     */
    public String toStringFromRequestWithMac() {
        return messageCode + "\t" + actionCode + "\t" + transactionId + "\t" + transactionChannel
               + "\t" + time + "\t" + accountName + "\t" + mAC + "\t";
    }

    /**
     * get string to Enscript before response
     *
     * @return
     */
    public String toStringFromReponse() {
        return messageCode + "|" + actionCode + "|" + transactionId + "|" + transactionChannel
               + "|" + time + "|" + accountName + "|" + responseCode
               + "|" + agencyCode + "|" + fullName + "|" + idNumber + "|" + address
               + "|" + region + "|";
    }

    /**
     * is used to log for response
     *
     * @return
     */
    public String toStringFromReponseWithMac() {
        return messageCode + "\t" + actionCode + "\t" + transactionId + "\t" + transactionChannel
               + "\t" + time + "\t" + accountName + "\t" + responseCode
               + "\t" + agencyCode + "\t" + fullName + "\t" + idNumber + "\t" + address
               + "\t" + region + "\t"+ mAC + "\t";
    }
}
