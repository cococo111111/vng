/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.viettinbankservice.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.storage.client.BankingStorageClient;
import vng.bankinggateway.viettinbankservice.stub.MerchantSvcStub;

/**
 *
 * @author root
 */
public class VietTinBankServiceADBClient {

    private static String URL_VietTin = "https://www.vnpayment.vn/merchantsvc/merchantsvc.asmx?wsdl";

    public static void main(String[] args) {
      testBankingGatewayService();
    }

    private static void testBankService() {
        try {
        } catch (Exception ex) {
            System.out.print(ex.getMessage());

        }
    }
    
    private static void testBankingGatewayService() {
        try {
            MerchantSvcStub stub  = new MerchantSvcStub(URL_VietTin);
            MerchantSvcStub.Execute excute = new MerchantSvcStub.Execute();
            excute.setCmdCode("inittrans");
            int tranID = BankingStorageClient.getMainInstance("10.30.56.52", 9778).generateTransIdByBankCode(CommonDef.BANKCODE.VIETINBANK);
        
            String aa = "10004102" + "|" + tranID + "|" + "1000" + "|" + "VND"
                + "|" + "VIETINBANK" + "|" + "20130712120150" + "|" + "aaaa" + "|" + "127.0.0.1" + "|";
            String SecretKey     = "DF066FAA67D552FB7BD978FA2226455C";
            String input = aa + Encryption.MD5(aa + SecretKey) + "|";
            excute.setStrData(input);
            System.out.println(" inpiut = " + input);
            MerchantSvcStub.ExecuteResponse response = stub.execute(excute);

            System.out.println("Response =    :" + response.getExecuteResult());

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
