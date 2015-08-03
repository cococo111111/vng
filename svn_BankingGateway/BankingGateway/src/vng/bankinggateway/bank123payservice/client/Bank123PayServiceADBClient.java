/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.bank123payservice.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.bankinggateway.bank123payservice.stub.CreateOPOrderStub;
import vng.bankinggateway.bank123payservice.stub.Query1OrderStub;
import vng.bankinggateway.common.util.Encryption;

/**
 *
 * @author root
 */
public class Bank123PayServiceADBClient {
    
    private static String URL_123pay = "http://10.30.8.133:8889/ESALE/createOPOrder?WSDL";
    private static String URL_123pay_query = "http://10.30.8.133:8889/ESALE/query1Order?WSDL";
    
    public static void main(String[] args) {
//        test123PayService();
        test123PayQueryService();
    }
    
    private static void testBankService() {
        try {
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            
        }
    }
    
    private static void test123PayService() {
        try {
            //ESALE	9225611447	123PBIDV	100005	vietinbankesale2	
            // 9225611447			118.102.7.146							
            // 1000000	ZIONNAM		ESALE@Passcode	ESALE@SecretKey	D05D5EFDCF5C6CFF80747C4339A1BA0A0FA3046B
            
            
          /*  2013-07-31 19:57:48,519 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@72] - merchantCode = ESALE
2013-07-31 19:57:48,520 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@74] - agencyCode = 9225611447
2013-07-31 19:57:48,520 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@76] - bankCode = 123PBIDV
2013-07-31 19:57:48,520 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@78] - merchantTranxId = 100015
2013-07-31 19:57:48,521 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@80] - accountName = vietinbankesale2
2013-07-31 19:57:48,521 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@82] - accountID = 9225611447
2013-07-31 19:57:48,522 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@84] - emailLogin = 
2013-07-31 19:57:48,522 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@86] - phoneLogin = 
2013-07-31 19:57:48,522 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@88] - clientIP = 118.102.7.146
2013-07-31 19:57:48,523 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@90] - custName = 
2013-07-31 19:57:48,523 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@92] - custAddress = 
2013-07-31 19:57:48,523 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@94] - custGender = 
2013-07-31 19:57:48,524 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@96] - custDOB = 
2013-07-31 19:57:48,524 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@98] - custPhone = 
2013-07-31 19:57:48,524 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@100] - custMail = 
2013-07-31 19:57:48,525 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@102] - description = Nap tien truc tuyen - Ngan hang HDBank. Ma Dai Ly: 9225611447. Phi dich vu nap tien: 3.300 VND/giao dich. Nha cung cap: CT TNHH Thuong mai dich vu truyen thong Thanh Son (Mien Nam)
2013-07-31 19:57:48,525 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@104] - totalAmount = 1000000
2013-07-31 19:57:48,525 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@106] - accountZion = ZIONNAM
2013-07-31 19:57:48,526 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@108] - redirectURL = https://esale2.zing.vn/thank.php?zb_SecureHash=67C5F735C939505641E5B10B88D78571C1394AA8&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=0
2013-07-31 19:57:48,526 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@110] - cancelURL = https://esale2.zing.vn/thank.php?zb_SecureHash=67C5F735C939505641E5B10B88D78571C1394AA8&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=0
2013-07-31 19:57:48,526 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@112] - errorURL = https://esale2.zing.vn/thank.php?zb_SecureHash=77B6DC27FAA6B005CBE62DC1707CE0D5CCF0DF82&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=6
2013-07-31 19:57:48,527 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@114] - passcode = ESALE@Passcode
2013-07-31 19:57:48,527 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@116] - addInfo = [Ljava.lang.String;@5043153c
2013-07-31 19:57:48,528 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@118] - checksum = 5B55D24999F7B1A4E23827F1B14BBA5C677745B0
2013-07-31 19:57:48,834 - INFO  [pool-1-thread-1:Bank123PayServiceHandler@128] - Receive From Pay123BANK: 6000	Xac thuc merchant that bai	
2013-07-31 19:57:48,836 - INFO  [pool-1-thread-1:Pay123Banker@160] - loi goi ve tu 123pay 6000*/

            String merchantCode = "ESALE";
            String agencyCode = "9225611447";
            String bankCode = "123PBIDV";
            String merchantTranxId = "100015";
            String accountName = "vietinbankesale2";
            String accountID = "9225611447";
            String emailLogin = "";
            String phoneLogin = "";
            String clientIP = "118.102.7.146";
            String custName = "";
            String custAddress = "";
            String custGender = "";
            String custDOB = "";
            String custPhone = "";
            String custMail = "";
            String description = "Nap tien truc tuyen - Ngan hang HDBank. Ma Dai Ly: 9225611447. Phi dich vu nap tien: 3.300 VND/giao dich. Nha cung cap: CT TNHH Thuong mai dich vu truyen thong Thanh Son (Mien Nam)";
            String totalAmount = "1000000";
            String accountZion = "ZIONNAM";
            
            String cancelURL = "https://esale2.zing.vn/thank.php?zb_SecureHash=67C5F735C939505641E5B10B88D78571C1394AA8&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=0";
            String redirectURL = "https://esale2.zing.vn/thank.php?zb_SecureHash=67C5F735C939505641E5B10B88D78571C1394AA8&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=0";
            String errorURL = "https://esale2.zing.vn/thank.php?zb_SecureHash=77B6DC27FAA6B005CBE62DC1707CE0D5CCF0DF82&zb_MerchTxnRef=M130731-0019&zb_Amount=1000000&zb_ResponseCode=6";
            
            String passcode = "ESALE@Passcode";
            String secretKey = "ESALE@SecretKey";
            
            String checksum = Encryption.SHA1(merchantCode + agencyCode + bankCode + merchantTranxId + accountName + accountID + emailLogin + phoneLogin + clientIP + custName + custAddress + custGender + custDOB + custPhone + custMail + totalAmount + accountZion + redirectURL + passcode + secretKey);
            String[] addInfo = {};
            
            CreateOPOrderStub stub = new CreateOPOrderStub(URL_123pay);
            CreateOPOrderStub.CreateOPOrderE orderE = new CreateOPOrderStub.CreateOPOrderE();
            CreateOPOrderStub.CreateOPOrder order = new CreateOPOrderStub.CreateOPOrder();
            order.setMerchantCode(merchantCode);
            order.setAgencyCode(agencyCode);
            order.setBankCode(bankCode);
            order.setMerchantTranxId(merchantTranxId);
            order.setAccountName(accountName);
            order.setAccountID(accountID);
            order.setEmailLogin(emailLogin);
            order.setPhoneLogin(phoneLogin);
            order.setClientIP(clientIP);
            order.setCustName(custName);
            order.setCustAddress(custAddress);
            order.setCustGender(custGender);
            order.setCustDOB(custDOB);
            order.setCustPhone(custPhone);
            order.setCustMail(custMail);
            order.setDescription(description);
            order.setTotalAmount(totalAmount);
            order.setAccountZion(accountZion);
            order.setRedirectURL(redirectURL);
            order.setCancelURL(cancelURL);
            order.setErrorURL(errorURL);
            order.setPasscode(passcode);
            order.setAddInfo(addInfo);
            order.setChecksum(checksum);
            
            orderE.setCreateOPOrder(order);
            CreateOPOrderStub.CreateOPOrderResponseE response = stub.createOPOrder(orderE);
            String[] arrayString = response.getCreateOPOrderResponse().get_return();
            for (int i = 0; i < arrayString.length; i++) {
                System.out.println(" " + arrayString[i]);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void test123PayQueryService() {
        try {
            String merchantCode = "ESALE";
            String merchantTranxId = "100010";
            String clientIP = "127.0.0.1";
            String passcode = "ESALE@Passcode";
            String secretKey = "ESALE@SecretKey";
            
            String checksum = Encryption.SHA1(merchantTranxId + merchantCode + clientIP + passcode + secretKey);
            
            Query1OrderStub stub = new Query1OrderStub(URL_123pay_query);
            Query1OrderStub.Query1OrderE orderE = new Query1OrderStub.Query1OrderE();
            Query1OrderStub.Query1Order query = new Query1OrderStub.Query1Order();
            query.setMerchantCode(merchantCode);
            query.setMTransactionID(merchantTranxId);
            query.setClientIP(clientIP);
            query.setPasscode(passcode);
            query.setChecksum(checksum.toLowerCase());
            
            orderE.setQuery1Order(query);
            Query1OrderStub.Query1OrderResponseE response = stub.query1Order(orderE);
            String[] arrayString = response.getQuery1OrderResponse().get_return();
            for (int i = 0; i < arrayString.length; i++) {
                System.out.println(" " + arrayString[i]);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static MessageDigest getMessageDigest() {
        MessageDigest digest = null;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        } catch (Exception ex) {
        }
        
        return digest;
    }
}
