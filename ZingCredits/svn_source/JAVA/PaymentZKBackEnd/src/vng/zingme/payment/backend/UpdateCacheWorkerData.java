/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

/**
 *
 * @author root
 */
public class UpdateCacheWorkerData {

    public int userID;
    public long txID;
    public int stat; // Status code
    public int resCode = 0; // resCode is a part of status Code
    public String description;
    public double currentbalance;
    public String agentID;

    public UpdateCacheWorkerData(String agentID, int userID, long txID, int stat, String description, double curbal) {
        this.agentID = agentID;
        this.userID = userID;
        this.txID = txID;
        this.stat = stat;
        this.description = description;
        this.currentbalance=curbal;
    }
    
    public UpdateCacheWorkerData(String agentID, int userID, long txID, int stat, String description, double curbal, int resCode) {
        this.agentID = agentID;
        this.userID = userID;
        this.txID = txID;
        this.stat = stat;
        this.description = description;
        this.currentbalance=curbal;
        this.resCode = resCode;
    }
}
