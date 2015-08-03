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
import vng.zingme.payment.stub.PaymentServiceStub;
import vng.zingme.util.StringUtil;

/**
 *
 * @author root
 */
public class PaymentServiceADBClient {

    private static String URL = "http://10.30.22.135:8080/axis2/services/PaymentService";

    public static void main(String[] args) {
        testGetMoney();
        
        testAddMoney(1);
//        for (int i = 0; i < 1; i++) {
//            testAddMoney(i);
//        }
//        testGetMoney();
        
//        testGetUser();
        
        testGetMoney();
        
    }

    private static void testGetUser() {
        try {
            PaymentServiceStub stub  = new PaymentServiceStub(URL);

            PaymentServiceStub.GetUser getUserEntry = new PaymentServiceStub.GetUser();


            PaymentServiceStub.User user = new PaymentServiceStub.User();

            user.setUserId("tranduclen");

            MessageDigest digest = getMessageDigest();
            String mac = "tranduclen" + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            user.setSig(_sig);

            getUserEntry.setUser(user);

            PaymentServiceStub.GetUserResponse getUserResponseEntry = stub.getUser(getUserEntry);


            System.out.println("User ID   :" + getUserResponseEntry.get_return().getUserId());
            System.out.println("Code   :" + getUserResponseEntry.get_return().getCode());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    
    }
    
    private static void testGetMoney() {
        try {
            PaymentServiceStub stub  = new PaymentServiceStub(URL);

            PaymentServiceStub.GetMoney getUserEntry = new PaymentServiceStub.GetMoney();


            PaymentServiceStub.User user = new PaymentServiceStub.User();

            user.setUserId("tranduclen");

            MessageDigest digest = getMessageDigest();
            String mac = "tranduclen" + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            user.setSig(_sig);

            getUserEntry.setUser(user);

            PaymentServiceStub.GetMoneyResponse getMoneyResponseEntry = stub.getMoney(getUserEntry);
            NumberFormat formatter = new DecimalFormat("###.##");

            System.out.println("User ID   :" + getMoneyResponseEntry.get_return().getUserId());
            System.out.println("Code   :" + getMoneyResponseEntry.get_return().getCode());
            System.out.println("Money    :" + formatter.format(Double.valueOf(getMoneyResponseEntry.get_return().getMoney())));

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    
    }
    
    private static void testAddMoney(int count) {
        try {
            PaymentServiceStub stub  = new PaymentServiceStub(URL);

            PaymentServiceStub.AddMoney addMoney = new PaymentServiceStub.AddMoney();


            PaymentServiceStub.Money money = new PaymentServiceStub.Money();

            money.setUserId("tranduclen");
            Random generator2 = new Random();
                    int billno = generator2.nextInt(180000) + 1;
            String transactionId = ""+ billno + count;
            money.setTransactionId(transactionId);
            String moneyAmount = "100";
            money.setMoney(moneyAmount);
            
            MessageDigest digest = getMessageDigest();
            String mac = moneyAmount + "tranduclen" +transactionId + "Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD";
            digest.update(mac.getBytes());
            String _sig = StringUtil.getHexString(digest.digest());

            money.setSig(_sig);
            money.setTransferType("1014");

            addMoney.setMoney(money);

            PaymentServiceStub.AddMoneyResponse addMoneyResponseEntry = stub.addMoney(addMoney);
            NumberFormat formatter = new DecimalFormat("###.##");

            System.out.println("User ID for addd   :" + addMoneyResponseEntry.get_return().getUserId());
            System.out.println("Code   :" + addMoneyResponseEntry.get_return().getCode());
            System.out.println("Money    :" + formatter.format(Double.valueOf(addMoneyResponseEntry.get_return().getMoney())));

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
