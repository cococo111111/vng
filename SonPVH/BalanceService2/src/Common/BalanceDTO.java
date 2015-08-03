/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.sql.Date;

/**
 *
 * @author root
 */
public class BalanceDTO implements java.io.Serializable {

    private int userId;
    private double amount = 0;
    private int balanceType;
    private long lastTxId;
    private Date date;
    private double currentBalance;
    private long txId;
    private int txType;
    private String note;
    private Date lastUpdate;

    public BalanceDTO() {
    }

    public BalanceDTO(int userId, double amount, int balanceType, long lastTxId, Date date, double currentBalance, long txId, int txType, String note, Date lastUpdate) {
        this.userId = userId;
        this.amount = amount;
        this.balanceType = balanceType;
        this.lastTxId = lastTxId;
        this.date = date;
        this.currentBalance = currentBalance;
        this.txId = txId;
        this.txType = txType;
        this.note = note;
        this.lastUpdate = lastUpdate;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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
}
