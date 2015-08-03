/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.stub.MerchantSVCStub;

/**
 *
 * @author root
 */
public class VietTinBankTopupServiceADBClient {

    private static String URL = "http://dev.bankinggw.me.zing.vn/axis2/services/MerchantSVC";

    public static void main(String[] args) {
        testVietTinBankTopupService();
    }

    private static void testVietTinBankTopupService() {
        try {
            MerchantSVCStub stub  = new MerchantSVCStub(URL);
            MerchantSVCStub.Execute excute = new MerchantSVCStub.Execute();
            excute.setCmdCode("CONFIRMTRANS");
        
//            String aa = "10007901" + "|" + "100002" + "|" + "1000" + "|" + "VND"
//                + "|" + "VNPAY" + "|" + "30130711170150" + "|" + "aaaa" + "|" + "127.0.0.1" + "|";
            
           String aa = "00|10007901|100024|1000|VND|123456|VNMART|30130711170150|aaa|";
            
            String SecretKey     = "D9256F32E6E7DDBA1E86A36426E80642";
            String input = aa + Encryption.MD5(aa + SecretKey) + "|";
            excute.setStrInput(input);
            System.out.println(" inpiut = " + input);
            MerchantSVCStub.ExecuteResponse response = stub.execute(excute);

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
