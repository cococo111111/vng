/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.PropertyConfigurator;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;
import vng.bankinggateway.stub.BankingGatewayServiceStub;
import vng.bankinggateway.stub.BankingGatewayServiceStub.ReqExecute;
import vng.bankinggateway.stub.BankingGatewayServiceStub.ReqExecuteResponse;
import vng.bankinggateway.util.InitUtil;
import static vng.bankinggateway.util.InitUtil.initEncryptionInfo;

/**
 *
 * @author root
 */
public class BankingGatewayServiceADBClient {

    private static String URL = "http://10.30.22.138:8080/axis2/services/BankingGatewayService";

    public static void main(String[] args) {
//        testCheckAgency();
        testTopup();
    }

    private static void testCheckAgency() {
        try {
            initConfiguration();
            //String userName = "0500|100000|100002|EBANKING|052813091717|hdbankesale|";
            String userName = "0500|100000|100002|EBANKING|052813091717|paymentzx|";
            
            userName = userName + Encryption.MD5(userName);
            System.out.println(" request = " + userName);
            userName = Encryption.encrypt(userName, InitUtil.sharedkey, InitUtil.sharedvector);

            BankingGatewayServiceStub stub = new BankingGatewayServiceStub(URL);

            ReqExecute reqExecute = new ReqExecute();
            reqExecute.setReq(userName);
            ReqExecuteResponse reqExecuteResponse = stub.reqExecute(reqExecute);

            System.out.println("Response =    :" + reqExecuteResponse.get_return());
            
            String decrypInput = "";
            decrypInput = Encryption.decrypt(reqExecuteResponse.get_return(), InitUtil.sharedkey,
                    InitUtil.sharedvector);
            System.out.println("costumerInformObj =  " + decrypInput);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void testTopup() {
        try {
            initConfiguration();
            String userName = "7000|200000|100006|EBANKING|052813161717|aa|1234567|001C200200|1000000|704|ABCD|";
            userName = userName + Encryption.MD5(userName);
            System.out.println(" request = " + userName);
            userName = Encryption.encrypt(userName, InitUtil.sharedkey, InitUtil.sharedvector);

            BankingGatewayServiceStub stub = new BankingGatewayServiceStub(URL);

            ReqExecute reqExecute = new ReqExecute();
            reqExecute.setReq(userName);
            ReqExecuteResponse reqExecuteResponse = stub.reqExecute(reqExecute);

            System.out.println("Response =    :" + reqExecuteResponse.get_return());
            
            String decrypInput = "";
            decrypInput = Encryption.decrypt(reqExecuteResponse.get_return(), InitUtil.sharedkey,
                    InitUtil.sharedvector);
            System.out.println("topup response =  " + decrypInput);            

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

    private static void initConfiguration() {
        String config = "conf";
        PropertyConfigurator.configure(config + "/log4j.properties");

        vng.bankinggateway.common.util.ConfigUtil.loadConfigFile(config + "/bankinggatewayservice.config");
        CommonDef.SHA1_SHARED_KEY = System.getProperty("sha1_shared_key", "");
        CommonDef.ESALE_WEBSERVICE_URL = System.getProperty("ESALE_URL", "");
        CommonDef.STORAGE_HOST = System.getProperty("storage_host", "");
        CommonDef.STORAGE_PORT = Integer.valueOf(System.getProperty("storage_port", "0"));
        CommonDef.ESALE_REDIRECT_URL = System.getProperty("esale_redirect_url", "");
        
        CommonDef.SCRIBE_HOST = System.getProperty("scribe_host", "");
        CommonDef.SCRIBE_PORT = Integer.valueOf(System.getProperty("scribe_port", ""));
        try {
            initEncryptionInfo(System.getProperty("sharedkey"), System.getProperty("sharedvector"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
