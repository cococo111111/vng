/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.worker;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.service.BankingGatewayHandler;
import vng.bankinggateway.util.InitUtil;

/**
 *
 * @author root
 */
public class BankingGatewayWorker implements Runnable {

    private static final Logger log = Logger.getLogger("systemActions");
    private SimpleDateFormat df = null;

    private BankingGatewayHandler _bankingGatewayHandler = null;
    
    public BankingGatewayWorker() {
        _bankingGatewayHandler = new BankingGatewayHandler();
        df = new SimpleDateFormat("MMddyy");
        log.info("new BankingGatewayWorker() ");
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("BankingGatewayWorker is running ");
                _bankingGatewayHandler.processPendingTransaction(df.format(new Date()), CommonDef.BANKCODE.HDBANK);
                _bankingGatewayHandler.processPendingTransaction(df.format(new Date()), CommonDef.BANKCODE.VIETINBANK);
                _bankingGatewayHandler.processPendingTransaction(df.format(new Date()), CommonDef.BANKCODE.BIDVBANK);
            } catch (TException ex) {
                log.error(ex.getMessage());
            }
            try {
                Thread.sleep(InitUtil.TIME_SLEEP);
            } catch (InterruptedException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
