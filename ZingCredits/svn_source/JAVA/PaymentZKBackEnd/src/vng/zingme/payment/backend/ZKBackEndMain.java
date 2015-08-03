/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.common.ZKBackEndType;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.thrift.TZKBackEnd;

/**
 *
 * @author root
 */
public class ZKBackEndMain {

    static {
        try {
            System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
            System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        } catch (Exception e) {
            Logger.getLogger("appActions").warn(e);
            // System.exit(1);
        }
    }

    public static void main(String[] args) {
        String configpath = System.getProperty("config", "");

        PropertyConfigurator.configure(configpath + "/log4j.properties");

        ConfigUtil.loadConfigFile(configpath + "/zkbackend.config",
                ZKBackEndMain.class.getName());

        // Create main Instance
        ZookeeperListenerManager mainInstance = ZookeeperListenerManager.getMainInstance();
        mainInstance.initialize();

        // deleteZKWorkingPath();


        int eventmode = Integer.parseInt(System.getProperty("eventmode", "0"));
        if (eventmode != 0) {
            //create - thread: recovery data
            // ...
            Thread recoverThread = new Thread(new ZookeeperListenerManager.RecoverData());
            recoverThread.start();
        }

        // thread: backend-handle server
        Thread svrthr = new Thread(new BackEndServer());
        svrthr.start();

        // GameReturnConsumer.main(args);
        if (ZookeeperListenerManager.getAdapterCase() == ZKBackEndType.BE_TYPE_BILL) {
            ReturnBillingAdapter.main(args);
        }

        //main-thread: listen zookeeper
        mainInstance.run();
    }

    private static class BackEndServer implements Runnable {

        public void run() {
            try {
                int _backendPort = Integer.parseInt(System.getProperty("zkBEPort", "4117"));
                TZKBackEnd.Processor frProcessor = new TZKBackEnd.Processor(new ZKBackendHandler());

                TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(_backendPort);
                THsHaServer.Options options = new THsHaServer.Options();
                options.workerThreads = 100;
                TServer server = new THsHaServer(frProcessor, serverSocket, options);

                String msg = "server ZKBackEnd has listen at port " + String.valueOf(_backendPort) + " ...";
                Logger.getLogger("appActions").info(msg);
                System.out.println(msg);
                server.serve();

            } catch (TTransportException e) {
                Logger.getLogger("appActions").warn("server ZKBackEnd has run fail " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
