/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import vng.zingme.payment.paymentgateway.push.ws.*;
import vng.zingme.util.StringUtil;

/**
 *
 * @author root
 */
public class AddMoneyServiceADBClient {

    private static String URL = "http://10.30.22.135:8080/axis2/services/AddMoneyService";

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            testAddMoney(i);
//        }
        
        
        testAddMoney(0);
        testGetMoney();
        
        
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AddMoneyServiceADBClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        testGetMoney();
        
    }

    private static void testGetUser() {
        try {
            AddMoneyServiceStub stub  = new AddMoneyServiceStub(URL);
            
            GetUser user = new GetUser();

            user.setUserID("vinhbkiter");

            MessageDigest digest = getMessageDigest();
            String mac = "vinhbkiter" + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            user.setSig(_sig);

            GetUserResponse getUserResponseEntry = stub.getUser(user);


            System.out.println("User ID   :" + getUserResponseEntry.getUserID());
            System.out.println("Code   :" + getUserResponseEntry.getCode());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    
    }
    
    private static void testGetMoney() {
        try {
            AddMoneyServiceStub stub  = new AddMoneyServiceStub(URL);
            
            GetMoney money = new GetMoney();

            money.setUserID("vinhbkiter");

            MessageDigest digest = getMessageDigest();
            String mac = "vinhbkiter" + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            money.setSig(_sig);

            GetMoneyResponse getMoneyResponseEntry = stub.getMoney(money);
            NumberFormat formatter = new DecimalFormat("###.##");

            System.out.println("Code   :" + getMoneyResponseEntry.getCode());
            System.out.println("Money    :" + formatter.format(Double.valueOf(getMoneyResponseEntry.getMoney())));

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    
    }
    
    private static void testAddMoney(int count) {
        try {
            AddMoneyServiceStub stub  = new AddMoneyServiceStub(URL);

            AddMoney addMoney = new AddMoney();

            addMoney.setUserID("vinhbkiter");
            Random generator2 = new Random();
                    int billno = generator2.nextInt(180000) + 1;
            String transactionId = ""+ billno + count;
            addMoney.setTransactionid(transactionId);
            String moneyAmount = "100";
            addMoney.setMoney(moneyAmount);
            
            MessageDigest digest = getMessageDigest();
            String mac = moneyAmount + "vinhbkiter" +transactionId + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            addMoney.setSig(_sig);
//            addMoney.setTransferType("1014");

            AddMoneyResponse addMoneyResponseEntry = stub.addMoney(addMoney);
            NumberFormat formatter = new DecimalFormat("###.##");

            System.out.println("User ID   :" + addMoneyResponseEntry.getUserID());
            System.out.println("Code   :" + addMoneyResponseEntry.getCode());
            System.out.println("Money    :" + formatter.format(Double.valueOf(addMoneyResponseEntry.getMoney())));

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
