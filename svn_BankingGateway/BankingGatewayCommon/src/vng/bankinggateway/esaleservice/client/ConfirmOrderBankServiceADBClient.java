/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.esaleservice.client;

import java.math.BigDecimal;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.ConfirmOrder;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.ConfirmOrderResponse;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.Topup;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;

/**
 *
 * @author root
 */
public class ConfirmOrderBankServiceADBClient {

    private static String URL = "http://10.30.17.193:9001/ConfirmOrderBankService.asmx";
    private static String SHARED_KEY = "eSale@123";

    public static void main(String[] args) {
        //testConfirmOrderBankService();
        testCheckAgency();
//                testTopup();
    }

    private static void testConfirmOrderBankService() {
        try {
            ConfirmOrderBankServiceStub stub = new ConfirmOrderBankServiceStub(URL);
            ConfirmOrder confirmOrder = new ConfirmOrder();
            confirmOrder.setAgencyCode("9000684722");
            confirmOrder.setOrderNo("M130219-0011");
            confirmOrder.setResponseCode("00");
            confirmOrder.setTrnsID("123456");

            String checkSum = Encryption.SHA1(confirmOrder.getAgencyCode()
                                              + confirmOrder.getOrderNo() + confirmOrder.getResponseCode() + confirmOrder.getTrnsID() + SHARED_KEY);
            confirmOrder.setCheckSum(checkSum);

            System.out.println(confirmOrder.getAgencyCode()
                               + confirmOrder.getOrderNo() + SHARED_KEY);

            System.out.println(checkSum);

            ConfirmOrderResponse confirmOrderResponse = stub.confirmOrder(confirmOrder);

            System.out.println("Response RESULT =    :" + confirmOrderResponse.getConfirmOrderResult());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void testCheckAgency() {
        try {
            ConfirmOrderBankServiceHandler confirmOrderBankServiceHandler =
                  new ConfirmOrderBankServiceHandler(URL);
            CommonDef.SHA1_SHARED_KEY = SHARED_KEY;
            confirmOrderBankServiceHandler.checkAgency("pmqc");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void testTopup() {
        try {
            ConfirmOrderBankServiceHandler confirmOrderBankServiceHandler =
                  new ConfirmOrderBankServiceHandler(URL);
            CommonDef.SHA1_SHARED_KEY = SHARED_KEY;
            BigDecimal b = new BigDecimal("1000000");

            Topup topup = new Topup();

            String checkSum =
                  Encryption.SHA1("paymentzx" + b + "note" + "0000" + "HDBANK" + "EBANKING" + CommonDef.SHA1_SHARED_KEY);


            confirmOrderBankServiceHandler.topup("paymentzx", b, "note", "1111", "HDBANK", "EBANKING");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
