/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.sms.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vng.sms.stub.AdsRMTNEWServiceStub;

/**
 *
 * @author root
 */
public class SMSADBClient {

    private static String URL = "http://10.199.38.101/axis/services/AdsRMTNEW?WSDL";

    public static void main(String[] args) {
        testMTReceiver();
    }

    private static void testMTReceiver() {
        try {
            AdsRMTNEWServiceStub stub  = new AdsRMTNEWServiceStub(URL);
            AdsRMTNEWServiceStub.MtReceiver mtReceiver = new AdsRMTNEWServiceStub.MtReceiver();
            // set parameter for response
            org.apache.axis2.databinding.types.soapencoding.String ss = new org.apache.axis2.databinding.types.soapencoding.String();
            ss.setString("123456");
            
            mtReceiver.setRequestID(ss);
            mtReceiver.setUserID(ss);
            mtReceiver.setServiceID(ss);
            mtReceiver.setCommandCode(ss);
            mtReceiver.setMessage(ss);
            mtReceiver.setSig(ss);
           
            AdsRMTNEWServiceStub.MtReceiverResponse response = stub.mtReceiver(mtReceiver);

            System.out.println("Response =    :" + response.getMtReceiverReturn());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
