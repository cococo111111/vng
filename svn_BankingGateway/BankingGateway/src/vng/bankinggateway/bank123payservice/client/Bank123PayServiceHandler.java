/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.bank123payservice.client;

import org.apache.log4j.Logger;
import vng.bankinggateway.bank123payservice.stub.CreateOPOrderStub;
import vng.bankinggateway.bank123payservice.stub.Query1OrderStub;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.common.util.ScriberBankingGateway;
import vng.bankinggateway.thrift.T_Response;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author root
 */
public class Bank123PayServiceHandler {

    private String url = "";
    private String urlQuery = "";
    private static Bank123PayServiceHandler instance = null;
    private static final Logger log = Logger.getLogger("systemActions");
    private static final Logger log_tranx = Logger.getLogger("gatewayActions");

    public Bank123PayServiceHandler(String url, String urlQuery) {
        this.url = url;
        this.urlQuery = urlQuery;
    }

    public static Bank123PayServiceHandler getInstance(String url, String urlQuery) {
        if (instance == null) {
            instance = new Bank123PayServiceHandler(url, urlQuery);
        }

        return instance;
    }

    public String[] call123PayService(String refId, String merchantCode, String agencyCode, String bankCode, String merchantTranxId,
            String accountName, String accountID, String emailLogin, String phoneLogin,
            String clientIP, String custName, String custAddress, String custGender,
            String custDOB, String custPhone, String custMail, String description,
            String totalAmount, String accountZion, String cancelURL, String redirectURL,
            String errorURL) {
        T_Response response = new T_Response();
        response.code = 0;
        try {
            bankCode = CommonDef.BANKCODE.BIDV123PBANK;
            String checksum = Encryption.SHA1(merchantCode + agencyCode + bankCode 
                    + merchantTranxId + accountName + accountID + emailLogin 
                    + phoneLogin + clientIP + custName + custAddress + custGender 
                    + custDOB + custPhone + custMail + totalAmount + accountZion 
                    + redirectURL + InitUtil.PASSCODE_123PAY + InitUtil.SECRETKEY_123PAY);
            String[] addInfo = {};
            
            String logInfo = merchantCode + "\t" + agencyCode + "\t" + bankCode 
                    + "\t" + merchantTranxId + "\t" + accountName + "\t" + accountID + "\t" + emailLogin 
                    + "\t" + phoneLogin + "\t" + clientIP + "\t" + custName + "\t" + custAddress + "\t" + custGender 
                    + "\t" + custDOB + "\t" + custPhone + "\t" + custMail + "\t" + totalAmount + "\t" + accountZion 
                    + "\t" + redirectURL + "\t" + InitUtil.PASSCODE_123PAY + "\t" + InitUtil.SECRETKEY_123PAY + "\t" + checksum;
            
            log.info("SEND TO Pay123BANK " + logInfo);
            log_tranx.info("SEND TO Pay123BANK " + logInfo);
            ScriberBankingGateway.sendLogWithCurrentTime(logInfo);

            CreateOPOrderStub stub = new CreateOPOrderStub(url);
            CreateOPOrderStub.CreateOPOrderE orderE = new CreateOPOrderStub.CreateOPOrderE();
            CreateOPOrderStub.CreateOPOrder order = new CreateOPOrderStub.CreateOPOrder();
            order.setMerchantCode(merchantCode);
            log.info("merchantCode = "+ merchantCode);
            order.setAgencyCode(agencyCode);
            log.info("agencyCode = "+ agencyCode);
            order.setBankCode(bankCode);
            log.info("bankCode = "+ bankCode);
            order.setMerchantTranxId(merchantTranxId);
            log.info("merchantTranxId = "+ merchantTranxId);
            order.setAccountName(accountName);
            log.info("accountName = "+ accountName);
            order.setAccountID(accountID);
            log.info("accountID = "+ accountID);
            order.setEmailLogin(emailLogin);
            log.info("emailLogin = "+ emailLogin);
            order.setPhoneLogin(phoneLogin);
            log.info("phoneLogin = "+ phoneLogin);
            order.setClientIP(clientIP);
            log.info("clientIP = "+ clientIP);
            order.setCustName(custName);
            log.info("custName = "+ custName);
            order.setCustAddress(custAddress);
            log.info("custAddress = "+ custAddress);
            order.setCustGender(custGender);
            log.info("custGender = "+ custGender);
            order.setCustDOB(custDOB);
            log.info("custDOB = "+ custDOB);
            order.setCustPhone(custPhone);
            log.info("custPhone = "+ custPhone);
            order.setCustMail(custMail);
            log.info("custMail = "+ custMail);
            order.setDescription(description);
            log.info("description = "+ description);
            order.setTotalAmount(totalAmount);
            log.info("totalAmount = "+ totalAmount);
            order.setAccountZion(accountZion);
            log.info("accountZion = "+ accountZion);
            order.setRedirectURL(redirectURL);
            log.info("redirectURL = "+ redirectURL);
            order.setCancelURL(cancelURL);
            log.info("cancelURL = "+ cancelURL);
            order.setErrorURL(errorURL);
            log.info("errorURL = "+ errorURL);
            order.setPasscode(InitUtil.PASSCODE_123PAY);
            log.info("passcode = "+ InitUtil.PASSCODE_123PAY);
            order.setAddInfo(addInfo);
            log.info("addInfo = "+ addInfo);
            order.setChecksum(checksum);
            log.info("checksum = "+ checksum);

            orderE.setCreateOPOrder(order);
            CreateOPOrderStub.CreateOPOrderResponseE createOPOrderResponse = stub.createOPOrder(orderE);
            String[] arrayString = createOPOrderResponse.getCreateOPOrderResponse().get_return();
            String ret = "";
            for (String element : arrayString) {
                ret += element + "\t";
            }
            
            log.info("Receive From Pay123BANK: " + ret);
            log_tranx.info("Receive From Pay123BANK: " + ret);
            ScriberBankingGateway.sendLogWithCurrentTime("Receive From Pay123BANK: " + ret);
        
            return arrayString;
        } catch (Exception ex) {
           log.error(ex.getMessage());
            String [] res = {CommonDef.INVALID_CONNECTION};
            return res;
        }
    }
    
    
    public String[] call123PayQueryService(String merchantCode, String merchantTranxId, String clientIP) {
        try {
            String checksum = Encryption.SHA1(merchantTranxId + merchantCode + clientIP + InitUtil.PASSCODE_123PAY + InitUtil.SECRETKEY_123PAY);
            
            String infoLog = merchantTranxId + "\t" + merchantCode + "\t" + clientIP + "\t" + InitUtil.PASSCODE_123PAY + "\t" + InitUtil.SECRETKEY_123PAY;
            // Send to bank
            log.info("SEND TO PAY123 BANK For QUERY " + infoLog + "\t" + checksum);
            log_tranx.info("SEND TO PAY123 BANK For QUERY " + infoLog + "\t" + checksum);
            ScriberBankingGateway.sendLogWithCurrentTime("SEND TO PAY123 BANK For QUERY " + infoLog + "\t" + checksum);
        
            Query1OrderStub stub = new Query1OrderStub(urlQuery);
            Query1OrderStub.Query1OrderE orderE = new Query1OrderStub.Query1OrderE();
            Query1OrderStub.Query1Order query = new Query1OrderStub.Query1Order();
            query.setMerchantCode(merchantCode);
            query.setMTransactionID(merchantTranxId);
            query.setClientIP(clientIP);
            query.setPasscode(InitUtil.PASSCODE_123PAY);
            query.setChecksum(checksum.toLowerCase());
            
            orderE.setQuery1Order(query);
            Query1OrderStub.Query1OrderResponseE response = stub.query1Order(orderE);
            String[] arrayString = response.getQuery1OrderResponse().get_return();
            String ret = "";
            for (String element : arrayString) {
                ret += element + "\t";
            }
            log.info("Response from PAY123BANK For QUERY : " + ret);
            log_tranx.info("Response from PAY123BANK For QUERY " + ret);
            ScriberBankingGateway.sendLogWithCurrentTime("Response from PAY123BANK For QUERY " + ret);
            
            return arrayString;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            String [] response = {CommonDef.INVALID_CONNECTION};
            return response;
        }
    }
}
