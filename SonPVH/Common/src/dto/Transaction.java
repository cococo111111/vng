/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author sonpvh
 */
public class Transaction implements java.io.Serializable {

    public Transaction() {
    }

    public Transaction(long txID, short txType, int txTime,
            int userID, String userName,
            double currentBalance, double amount, int balanceType,
            String agentID, String refID,
            String itemIDs, String itemNames, String itemPrices, String itemQuantities,
            short responseCode, String description) {
        this.txID = txID;
        this.txType = txType;
        this.txTime = txTime;
        this.userID = userID;
        this.userName = userName;
        this.currentBalance = currentBalance;
        this.balanceType = balanceType;
        this.amount = amount;
        this.agentID = agentID;
        this.refID = refID;
        this.itemIDs = itemIDs;
        this.itemNames = itemNames;
        this.itemPrices = itemPrices;
        this.itemQuantities = itemQuantities;
        this.responseCode = responseCode;
        this.description = description;
    }
    private long txID;
    private short txType;
    private int txTime;
    private int userID;
    private String userName;
    private double currentBalance;
    private double amount;
    private int balanceType;
    private String agentID;
    private String refID;
    private String itemIDs;
    private String itemNames;
    private String itemPrices;
    private String itemQuantities;
    private short responseCode;
    private String description;

    public long getTxID() {
        return txID;
    }

    public void setTxID(long txID) {
        this.txID = txID;
    }

    public short getTxType() {
        return txType;
    }

    public void setTxType(short txType) {
        this.txType = txType;
    }

    public int getTxTime() {
        return txTime;
    }

    public void setTxTime(int txTime) {
        this.txTime = txTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getItemIDs() {
        return itemIDs;
    }

    public void setItemIDs(String itemIDs) {
        this.itemIDs = itemIDs;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public String getItemPrices() {
        return itemPrices;
    }

    public void setItemPrices(String itemPrices) {
        this.itemPrices = itemPrices;
    }

    public String getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(String itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public short getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(short responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(int balanceType) {
        this.balanceType = balanceType;
    }

    public String getScribeMessage() {
        return txID + "\t" + txType + "\t" + txTime + "\t"
                + userID + "\t" + userName
                + "\t" + currentBalance + "\t" + amount + "\t" + balanceType
                + "\t" + agentID + "\t" + refID + "\t"
                + itemIDs + "\t" + itemNames + "\t" + itemQuantities + "\t" + itemPrices
                + responseCode + "\t" + description;
    }
}
