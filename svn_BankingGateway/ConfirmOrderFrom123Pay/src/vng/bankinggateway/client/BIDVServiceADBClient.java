/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.stub.ConfirmOrderFrom123PayServiceStub;

/**
 *
 * @author root
 */
public class BIDVServiceADBClient {

    private static String URL = "http://dev.bankinggw.me.zing.vn/axis2/services/ConfirmOrderFrom123PayService?wsdl";

    public static void main(String[] args) {
        testBidvBankTopupService();
    }

    private static void testBidvBankTopupService() {
        try {
            ConfirmOrderFrom123PayServiceStub stub  = new ConfirmOrderFrom123PayServiceStub(URL);
            ConfirmOrderFrom123PayServiceStub.ConfirmOrderBy123Pay confirmOrderBy123Pay 
                    = new ConfirmOrderFrom123PayServiceStub.ConfirmOrderBy123Pay();
            confirmOrderBy123Pay.setResponseCode("1");
            confirmOrderBy123Pay.setAgencyCode("12345");
            confirmOrderBy123Pay.setTransactionId("1234569");
            confirmOrderBy123Pay.setRefTransId123Pay("98765");
            confirmOrderBy123Pay.setTime("072913161616");
            
        
           String sig = confirmOrderBy123Pay.getResponseCode() + confirmOrderBy123Pay.getAgencyCode() + 
                   confirmOrderBy123Pay.getTransactionId() +
                   confirmOrderBy123Pay.getRefTransId123Pay() +
            confirmOrderBy123Pay.getTime();
            
            String SecretKey     = "ESALE@SecretKey";
            confirmOrderBy123Pay.setSig(Encryption.SHA1(sig + SecretKey));
            ConfirmOrderFrom123PayServiceStub.ConfirmOrderBy123PayResponse response = stub.confirmOrderBy123Pay(confirmOrderBy123Pay);

            System.out.println("Response =    :" + response.get_return());

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
