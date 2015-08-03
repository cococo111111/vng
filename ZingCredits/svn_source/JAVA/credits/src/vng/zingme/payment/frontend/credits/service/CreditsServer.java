package vng.zingme.payment.frontend.credits.service;

import java.lang.management.ManagementFactory;
import org.apache.log4j.Logger;

import javax.management.ObjectName;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import vng.wte.common.Config;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;

public class CreditsServer implements CreditsServerMBean {

    private static Logger logger_ = Logger.getLogger(CreditsServer.class);

    public void start() throws Exception {

        Server server = new Server();

        // Setup JMX
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        String mbeanName = "vng.zingme.credits" + ":type=credits";

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
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.IndexController", "/index");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.FixAppUrlsController", "/fix-app-urls");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.IndexMobileController", "/m");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.MaintainController", "/maintain");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.IndexWapController", "/wap");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.DepositZingXuController", "/v2/deposit");
       
        //start cache
        Configuration.init();
        Configuration.loadAppUrls();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(false);
        resource_handler.setResourceBase(Configuration.SYSTEM_PUBLIC_PATH);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{handler, resource_handler});

        server.setHandler(handlers);


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
