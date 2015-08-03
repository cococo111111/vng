/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class VietTinQueryObj {

    private String providerCode;
    private String transactionID;
    private String bankAccountCode; // VNPAY Transaction ID
    private String time; // date request yyyyMMddHHmmss
    private String signature;
    private String responseCode;
    private String message;
    private String paymentMethod;
    

    public VietTinQueryObj(String providerCode, String transactionID, String bankAccountCode, String time, String signature) {
        this.providerCode = providerCode;
        this.transactionID = transactionID;
        this.bankAccountCode = bankAccountCode;
        this.time = time;
        this.signature = signature;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getBankAccountCode() {
        return bankAccountCode;
    }

    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    

    public String toStringForRequestWithoutSignature() {
        return providerCode + "|" + transactionID + "|" + bankAccountCode + "|" + time + "|";
    }

    public String toStringForRequest() {
        return providerCode + "|" + transactionID + "|" + bankAccountCode + "|" + time + "|" + signature + "|";
    }

    public String getScribeMessageForRequest() {
        return providerCode + "\t" + transactionID + "\t" + bankAccountCode + "\t" + time + "\t" + signature + "\t";
    }

    public VietTinQueryObj() {
    }

    public boolean fromStringForResponse(String fromString) {
        boolean flag = false;
        try {
            if (fromString != null && !"".equals(fromString)) {
                String[] objArr = fromString.split("\\|");
                this.responseCode = (objArr[0]);
                this.message = (objArr[1]);
                this.paymentMethod = (objArr[2]);
//                this.time = (objArr[3]);
                this.signature = objArr[3];
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public String toStringForResponseWithoutSignature() {
//        return responseCode + "|" + message + "|" + paymentMethod + "|" + time + "|";
        return responseCode + "|" + message + "|" + paymentMethod + "|";
    }

    public String toStringForResponse() {
//        return responseCode + "|" + message + "|" + paymentMethod + "|" + time + "|" + signature + "|";
        return responseCode + "|" + message + "|" + paymentMethod + "|" + signature + "|";
    }

    public String getScribeMessageForResponse() {
//        return responseCode + "\t" + message + "\t" + paymentMethod + "\t" + time + "\t" + signature + "\t";
        return responseCode + "\t" + message + "\t" + paymentMethod + "\t" + signature + "\t";
    }
}
