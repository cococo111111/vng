package vng.zingme.payment.tokenserver;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.thrift.TToken;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class TokenServerMain {

    public static void main(String[] args) {
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "conf/token.config", TokenServerMain.class.getName());

        runTokenServer();
    }

    private static void runTokenServer() {
        try {
            int _tokenPort = Integer.parseInt(System.getProperty("TokenPort", "7117"));
            TToken.Processor frProcessor = new TToken.Processor(new TokenHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_tokenPort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(frProcessor, serverSocket, options);

            String msg = "server Token has listen at port " + String.valueOf(_tokenPort) + " ...";
            Logger.getLogger("appActions").info(msg);
            System.out.println(msg);
            server.serve();

        } catch (TTransportException e) {
            Logger.getLogger("appActions").warn("server Token has run fail " + e.getMessage());
            e.printStackTrace();
        }
    }
}
