/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import org.apache.thrift.TException;
import vng.bankinggateway.service.BankingGatewayHandler;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author root
 */
public class RunReconfirmStatus {

    private static BankingGatewayHandler _bankingGatewayHandler = null;

    public static void main(String[] args) throws TException {
        InitUtil.initConfiguration();
        _bankingGatewayHandler = new BankingGatewayHandler();
        String strDay = System.getProperty("day");
        if(args.length > 1) {
            strDay = args[1];
        }
        _bankingGatewayHandler.reconfirmStatusForEsale(strDay, args[0]);
    }
}
