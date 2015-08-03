/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.cache.latesttranx;

import vng.zingme.payment.thrift.TLatestCache;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.config.ConfigUtil;

/**
 *
 * @author root
 */
public class LatestTranxMain {

    public static void main(String[] args) {
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");
        // testSerDiserialize();

        ConfigUtil.loadConfigFile(configpath + "conf/latesttranxcache.config", LatestTranxMain.class.getName());

        LatestTranxMain srv = new LatestTranxMain();
        srv.serverlatestCacheStart();
    }

    private void serverlatestCacheStart() {
        try {
            int _latestcachePort = Integer.parseInt(System.getProperty("latestPort", "5353"));
            TLatestCache.Processor ltProcessor = new TLatestCache.Processor(new LatestTranxCacheServerHandler());

            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_latestcachePort);
            THsHaServer.Options options = new THsHaServer.Options();
            TServer server = new THsHaServer(ltProcessor, serverSocket, options);

            System.out.println("server LatestCache has listen at port " + String.valueOf(_latestcachePort) + " ...");
            Logger.getLogger("appActions").info("server LatestCache has listen at port " + String.valueOf(_latestcachePort) + " ...");
            server.serve();

        } catch (TTransportException e) {
            Logger.getLogger("appActions").warn("server LatestCache has run fail!");
            e.printStackTrace();
        }
    }
}
