/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentreport;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.thrift.TReport;

/**
 *
 * @author root
 */
public class PaymentReportMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "conf/report.config", PaymentReportMain.class.getName());

        //active config
        Config.timeOut = 1001;


        runReportServer();
    }

    private static void runReportServer() {
        try {
            int _reportPort = Integer.parseInt(System.getProperty("reportPort", "10119"));
            TReport.Processor frProcessor = new TReport.Processor(new PaymentReportHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_reportPort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(frProcessor, serverSocket, options);

            String msg = "server PaymentReport has listen at port " + String.valueOf(_reportPort) + " ...";
            log.info(msg);
            System.out.println(msg);
            server.serve();

        } catch (TTransportException e) {
            log.warn("server PaymentReport has run fail " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
}
