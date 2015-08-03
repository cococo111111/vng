/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.accountbalance;

import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.log4j.Logger;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.thrift.TBalanceCaching;

/**
 *
 * @author root
 */
public class AccBalCacheMain {

    public static void main(String[] args) {
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "conf/accbalcache.config", AccBalCacheMain.class.getName());

        AccBalCacheMain srv = new AccBalCacheMain();
        srv.serverAccBalCacheStart();
    }

    private void serverAccBalCacheStart() {
        String status = "starting.........";
        try {
            int _accbalCachePort = Integer.parseInt(System.getProperty("accbalPort", "5363"));
            TBalanceCaching.Processor blProcessor = new TBalanceCaching.Processor(new AccBalCacheServerHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_accbalCachePort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(blProcessor, serverSocket, options);

            status += "[OK]";

            log.info(status + "server balCache lister at port " + String.valueOf(_accbalCachePort));

            server.serve();

        } catch (TTransportException e) {
            log.warn("server balCache run fail " + e.getMessage());
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
}
