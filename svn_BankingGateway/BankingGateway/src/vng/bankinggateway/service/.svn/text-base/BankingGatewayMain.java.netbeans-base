/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.service;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import vng.bankinggateway.thrift.TBankingService;
import vng.bankinggateway.util.InitUtil;
import vng.bankinggateway.worker.BankingGatewayWorker;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class BankingGatewayMain {
    
    private static ConcurrentLinkedHashMap<String, Byte> _filter;
    private static final ReentrantLock _locker = new ReentrantLock();
    private static Logger log = Logger.getLogger("systemActions");
    private static BankingGatewayWorker _bankingGatewayWorker = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InitUtil.initConfiguration();
        _filter = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, 10000);
        
        log.info("Starting bankingGatewayWorker ...");
        _bankingGatewayWorker = new BankingGatewayWorker();
        Thread thread = new Thread(_bankingGatewayWorker);
        thread.start();            
        log.info("BankingGatewayWorker started!");
        runServer();
    }

    private static void runServer() {
        try {
            int thrift_port = Integer.parseInt(System.getProperty("bankinggatewayPort", "20118"));
            TBankingService.Processor processor = new TBankingService.Processor(new BankingGatewayHandler());
            int thrift_thread = Integer.parseInt(System.getProperty("workerThreads", "30"));
            TNonblockingServerSocket socket = new TNonblockingServerSocket(thrift_port);
            THsHaServer.Args options = new THsHaServer.Args(socket);
            options.workerThreads(thrift_thread);
            options.processor(processor);
            TServer server = new THsHaServer(options);
            String msg = "server BankingGateway has listen at port " + String.valueOf(thrift_port) + " ...";
            
            log.info(msg);
            log.info("Number of worker threads = " + thrift_thread);
            System.out.println(msg);
            System.out.println("Number of worker threads = " + thrift_thread);
            
            server.serve();
        } catch (TTransportException e) {
            log.error("server BankingGateway has run fail " + e.getMessage());
        }
    }
    
    public static boolean checkTransaction(String refID) {
        boolean res = false;
        try {
            _locker.lock();
            if (!_filter.containsKey(refID)) {
                _filter.put(refID, Byte.MIN_VALUE);
                res = true;
            }
        } finally {
            _locker.unlock();
        }
        return res;
    }
}
