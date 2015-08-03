package aTest;

import org.apache.log4j.Logger;
import balanceZoo.BalanceZooImpl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Test {

    private static final Logger log = Logger.getLogger("exception");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        ConfigUtil.initConfiguration();
//        System.out.println("common.prefix " + CommonTranx.prefix);
//        IZoo a = null;
//        a = new TransactionZooImpl();
//        log.info("zoo");

        BalanceZooImpl ba = new BalanceZooImpl();

    }
}
