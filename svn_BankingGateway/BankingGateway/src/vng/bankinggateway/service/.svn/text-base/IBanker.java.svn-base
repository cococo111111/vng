/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public interface IBanker {

    public vng.bankinggateway.thrift.T_Response requestTransfer(String refID, 
            String time, String username, String agencyCode, String region, 
            int amount, String clientIP, String description, String bankCode, 
            String commision, String urlCallBack, String transferType, String sig) 
            throws org.apache.thrift.TException;
    public void reconfirmStatusForEsale(String day, String bankCode) throws TException;
    public void processPendingTransaction(String day, String bankCode) throws TException;
}
