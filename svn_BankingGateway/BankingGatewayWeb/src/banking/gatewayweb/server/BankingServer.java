/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.gatewayweb.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author Sonpvh <sonpvh@vng.com.vn>
 */
public class BankingServer implements BankingServerBean {

    public BankingServer() {
    }

    @Override
    public String getStartTime() {
        // TODO Auto-generated method stub
        return String.valueOf(System.currentTimeMillis());
    }

    public void start() throws Exception {
        InitUtil.initConfiguration();
        
        int listenPort = 8116; // TODO
        Server server = new Server();

        // set connection
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(listenPort);
//        connector.setHost(localhost);
        connector.setMaxIdleTime(30000);
        connector.setStatsOn(false);

        server.setConnectors(new Connector[]{connector});
        

        ServletHandler handler = new ServletHandler();
        
        handler.addServletWithMapping(banking.gatewayweb.servlet.PaymentConfirmation.class, "/BankingGatewayWeb/paymentConfirmation");
        handler.addServletWithMapping(banking.gatewayweb.servlet.Hello.class, "/BankingGatewayWeb/hello");
        handler.addServletWithMapping(banking.gatewayweb.servlet.Payment.class, "/BankingGatewayWeb/payment");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{handler});
        server.setHandler(handlers);

        server.start();
        server.join();
    }

}
