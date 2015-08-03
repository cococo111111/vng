/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.esaleservice.client.ConfirmOrderBankServiceHandler;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author root
 */
public class RunManualError {

    public static void main(String[] args) throws TException {
        InitUtil.initConfiguration();
        try {
            int esaleStatus = ConfirmOrderBankServiceHandler.getInstance(
                    CommonDef.ESALE_WEBSERVICE_URL).confirmOrderBankService("9187972681", "M130816-0366", "00","322080" , "VIETINBANK");
            System.out.println("esaleStatus = " + esaleStatus);
            
            esaleStatus = ConfirmOrderBankServiceHandler.getInstance(
                    CommonDef.ESALE_WEBSERVICE_URL).confirmOrderBankService("9066431347", "M130816-0368", "00", "322078","VIETINBANK");
            System.out.println("esaleStatus = " + esaleStatus);
        } catch (Exception ex) {
        }
    }
}
