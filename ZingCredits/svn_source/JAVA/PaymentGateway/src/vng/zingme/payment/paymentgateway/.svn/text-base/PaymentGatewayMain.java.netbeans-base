package vng.zingme.payment.paymentgateway;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.common.zk.ZKUtil;
import vng.zingme.payment.frontend.ZKFrontEndModel;
import vng.zingme.payment.thrift.TPayment;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class PaymentGatewayMain {

    public static void main(String[] args) {

        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "conf/paymentgateway.config",
                PaymentGatewayMain.class.getName());

        PaymentGatewayMain srv = new PaymentGatewayMain();
        srv.paymentGatewayStart();
    }

    private void paymentGatewayStart() {
        try {
            int _pgPort = Integer.parseInt(System.getProperty("pgPort", "6162"));
            TPayment.Processor pgProcessor = new TPayment.Processor(new PaymentGatewayHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_pgPort);
            THsHaServer.Options options = new THsHaServer.Options();
            options.workerThreads = Integer.parseInt(System.getProperty("workerThreads", "20"));
            System.out.println("workerThreads = " + Integer.parseInt(System.getProperty("workerThreads", "20")));
            TServer server = new THsHaServer(pgProcessor, serverSocket, options);

            System.out.println("server PaymentGateway has listen at port " + String.valueOf(_pgPort) + " ...");
            Logger.getLogger("appActions").info("server PaymentGateway has listen at port " + String.valueOf(_pgPort) + " ...");
            server.serve();

        } catch (TTransportException e) {
            Logger.getLogger("appActions").warn("server PaymentGateway has run fail!");
        }
    }

    public static void createZKWorkingPath() {
        String pulltranxpath = System.getProperty("vng.zingme.payment.pull.tranx.path");
        String pushtranxpath = System.getProperty("vng.zingme.payment.push.tranx.path");

        String auth = System.getProperty("paymentauthencode");

        ZKFrontEndModel adater = ZKFrontEndModel.getInstance();
        if (adater != null) {
            ZKUtil.createZKPath(ZKFrontEndModel.getZk(), pulltranxpath, auth);
            ZKUtil.createZKPath(ZKFrontEndModel.getZk(), pushtranxpath, auth);
        }
    }
}
