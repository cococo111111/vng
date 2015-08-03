package vng.zingme.payment.admin;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.thrift.TAdminServer;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class AdminMain {

    public static void main(String[] args) {
        // TODO code application logic here
        String config = System.getProperty("config", "");

        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/admin.config", "appActions");


        System.out.println(AdminModel.genAdminCode());
        runAdminServer();
    }

    private static void runAdminServer() {
        try {
            int _adminPort = Integer.parseInt(System.getProperty("adminPort", "10117"));
            TAdminServer.Processor frProcessor = new TAdminServer.Processor(new AdminHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_adminPort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(frProcessor, serverSocket, options);

            String msg = "server PaymentAdmin has listen at port " + String.valueOf(_adminPort) + " ...";
            log.info(msg);
            System.out.println(msg);
            server.serve();

        } catch (TTransportException e) {
            log.warn("server PaymentAdmin has run fail " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
}
