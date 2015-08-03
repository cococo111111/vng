package vng.zingme.payment.apps;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.thrift.TAppServer;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class AppsServiceMain {

    public static void main(String[] args) {
        // TODO code application logic here
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "conf/appservice.config", "appActions");

        //active config
        Config.timeOut = 1001;

        System.out.println(AppsServiceModel.genAdminCode());
        runAppServer();
    }

    private static void runAppServer() {
        try {
            int _adminPort = Integer.parseInt(System.getProperty("appservicePort", "10118"));
            TAppServer.Processor frProcessor = new TAppServer.Processor(new AppsServiceHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_adminPort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(frProcessor, serverSocket, options);

            String msg = "server PaymentAppsService has listen at port " + String.valueOf(_adminPort) + " ...";
            log.info(msg);
            System.out.println(msg);
            server.serve();

        } catch (TTransportException e) {
            log.warn("server PaymentAppsService has run fail " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
}
