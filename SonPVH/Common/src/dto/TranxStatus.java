/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author root
 */
public class TranxStatus {

    private long txID;
    private short txStatus;
    private short responseCode;
    private String message;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TranxStatus(long txID, short txStatus, short responseCode,
            String message, int userId) {
        this.txID = txID;
        this.txStatus = txStatus;
        this.responseCode = responseCode;
        this.message = message;
        this.userId = userId;
    }

    public long getTxID() {
        return txID;
    }

    public void setTxID(long txID) {
        this.txID = txID;
    }

    public short getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(short txStatus) {
        this.txStatus = txStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public short getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(short responseCode) {
        this.responseCode = responseCode;
    }
}
