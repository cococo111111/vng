/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author sonpvh
 */
public class BalanceDTO implements java.io.Serializable {

    private int userId;
    private double amount = 0;
    private int balanceType;
    private long lastTxId;
    private int txTime;
    private double currentBalance;
    private long txId; // delete
    private int txType;
    private String note;

    public BalanceDTO() {
    }

    public BalanceDTO(int userId, double amount, int balanceType, long lastTxId,
            int txTime, double currentBalance, long txId, int txType, String note) {
        this.userId = userId;
        this.amount = amount;
        this.balanceType = balanceType;
        this.lastTxId = lastTxId;
        this.txTime = txTime;
        this.currentBalance = currentBalance;
        this.txId = txId;
        this.txType = txType;
        this.note = note;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getLastTxId() {
        return lastTxId;
    }

    public void setLastTxId(long lastTxId) {
        this.lastTxId = lastTxId;
    }

    public int txTime() {
        return txTime;
    }

    public void setDate(int txTime) {
        this.txTime = txTime;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
    }

    public int getTxType() {
        return txType;
    }

    public void setTxType(int txType) {
        this.txType = txType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(int balanceType) {
        this.balanceType = balanceType;
    }

    public int getTxTime() {
        return txTime;
    }

    public void setTxTime(int txTime) {
        this.txTime = txTime;
    }

    public String getScribeMessage() {
        return userId + "\t" + amount + "\t" + balanceType + "\t" + lastTxId + "\t"
                + txTime + "\t" + currentBalance + "\t" + txId + "\t" + txType
                + "\t" + note;
    }
}
