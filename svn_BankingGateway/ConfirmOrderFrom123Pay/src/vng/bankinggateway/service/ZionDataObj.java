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
    private String responseCode;
    private String agencyCode;
    private String transactionID;
    private String time;
    private String refId123pay;
    private String sig;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRefId123pay() {
        return refId123pay;
    }

    public void setRefId123pay(String refId) {
        this.refId123pay = refId;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public ZionDataObj() {
    }
    
    public ZionDataObj(String responseCode, String agencyCode, String transactionID, 
            String refId, String time, String sig) {
        this.responseCode = responseCode;
        this.agencyCode = agencyCode;
        this.transactionID = transactionID;
        this.time = time;
        this.refId123pay = refId;
        this.sig = sig;
    }
    

    public String toQueueMessage() {
        return responseCode + "|" + agencyCode + "|" + transactionID  + "|" + refId123pay + "|" + time;
    }

    public boolean fromQueueMessage(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.responseCode = (objArr[0]);
                this.agencyCode = (objArr[1]);
                this.transactionID = (objArr[2]);
                this.refId123pay = (objArr[3]);
                this.time = (objArr[4]);
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        return responseCode + agencyCode + transactionID + refId123pay + time ;
    }
    
    public String toStringForSubscribe() {
        return responseCode + "\t" + agencyCode + "\t" + transactionID + "\t" + refId123pay + "\t" + time + "\t" + sig ;
    }
}
