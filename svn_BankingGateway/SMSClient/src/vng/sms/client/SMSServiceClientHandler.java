/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.sms.client;

/**
 *
 * @author root
 */
public class SMSServiceClientHandler {

    private String URL = "http://10.30.22.138:8080/axis2/services/BankingGatewayService";
    private static SMSServiceClientHandler instance = null;

    public SMSServiceClientHandler(String url) {
        this.URL = url;
    }

    public static SMSServiceClientHandler getInstance(String url) {
        if (instance == null) {
            instance = new SMSServiceClientHandler(url);
        }

        return instance;
    }

    public String callMTReceiver(String requestID, String userID,
            String serviceID, String commandCode, String message) {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
