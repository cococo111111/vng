package vng.zingme.payment.storage;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.log4j.Logger;

import vng.zingme.payment.thrift.TStorage;
import vng.zingme.payment.model.util.Config;
import vng.zingme.util.LogUtil;

public class StorageDaemon {

    private TServer serverEngine;
    private static final Logger log = Logger.getLogger("appActions");

    private void setup() throws IOException, TTransportException {
        try {
            int listenPort = Config.queuePort;
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                public void uncaughtException(Thread t, Throwable e) {
                    log.info("Fatal exception in thread " + t.toString());
                    log.warn(LogUtil.stackTrace(e));
                    if (e instanceof OutOfMemoryError) {
                        System.exit(100);
                    }
                }
            });
            // now we start listening for clients
            StorageFaceImpl face = new StorageFaceImpl();
            TStorage.Processor processor = new TStorage.Processor(face);

            TNonblockingServerSocket server = new TNonblockingServerSocket(
                    listenPort);
            THsHaServer.Options options = new THsHaServer.Options();
            serverEngine = new THsHaServer(processor, server, options);
            log.info("Storage service has started at port: " + listenPort);
        } catch (Exception e) {
            log.info("Storage service has not started. " + e.getMessage());
            if (e instanceof IOException) {
                throw (IOException) e;
            } else if (e instanceof TTransportException) {
                throw (TTransportException) e;
            }

        }
    }

    /** hook for JSVC */
    public void init(String[] args) throws IOException, TTransportException {
        setup();
    }

    /** hook for JSVC */
    public void start() {
        serverEngine.serve();
    }

    /** hook for JSVC */
    public void stop() {
        serverEngine.stop();
    }

    /** hook for JSVC */
    public void destroy() {
    }

    public static void main(String[] args) {
        String configpath = System.getProperty("confpath", "");

        PropertyConfigurator.configure(configpath + "conf/log4j.properties");

        StorageDaemon daemon = new StorageDaemon();
        try {
            daemon.init(args);

            daemon.start();

        } catch (Exception e) {
            log.warn(LogUtil.stackTrace(e));
            System.exit(3);
        }
    }
}
