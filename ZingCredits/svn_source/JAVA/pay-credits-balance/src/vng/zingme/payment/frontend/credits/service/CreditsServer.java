package vng.zingme.payment.frontend.credits.service;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.servlet.ServletHandler;

import vng.wte.common.Config;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;

public class CreditsServer implements CreditsServerMBean {

    public void start() throws Exception {

        Server server = new Server();

        // Setup JMX
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        String mbeanName = "vng.zingme.credits" + ":type=pay-credits";

        try {
            mbContainer.getMBeanServer().registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int listenPort = Integer.parseInt(Config.getParam("system", "port"));
        int maxThreads = Integer.parseInt(Config.getParam("system", "maxthread"));	//1000

        server.getContainer().addEventListener(mbContainer);
        server.addBean(mbContainer);
        mbContainer.addBean(Log.getLog());

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(maxThreads);
        server.setThreadPool(threadPool);


        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(listenPort);
        connector.setMaxIdleTime(30000);
        connector.setConfidentialPort(8443);
        connector.setStatsOn(false);

        server.setConnectors(new Connector[]{connector});

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BalanceController", "/balance");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BalanceController", "/balance/*");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BalanceController", "/billing/balance");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BalanceController", "/billing/balance/*");

        //start cache
        Configuration.init();
        AppInfoCache.getInstance();
        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);

        server.start();
        server.join();


    }

    @Override
    public String getStartTime() {
        // TODO Auto-generated method stub
        return String.valueOf(System.currentTimeMillis());
    }
}
