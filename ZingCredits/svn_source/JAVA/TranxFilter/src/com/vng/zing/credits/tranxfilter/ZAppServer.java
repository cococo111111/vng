package com.vng.zing.credits.tranxfilter;

import com.vng.jcore.common.Config;
import java.lang.management.ManagementFactory;

import javax.management.ObjectName;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class ZAppServer implements ZAppServerMBean {

    public void start() throws Exception {

        Server server = new Server();
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        String mbeanName = "vng.zingme.profilewidget" + ":type=profile";

        try {
            mbContainer.getMBeanServer().registerMBean(this, new ObjectName(mbeanName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int listenPort = Integer.parseInt(System.getProperty("port", "9999"));
        int maxThreads = Integer.parseInt(System.getProperty("maxthread", "100"));

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
        
        ServletHandler servlet_handler = new ServletHandler();
        servlet_handler.addServletWithMapping("com.vng.zing.credits.tranxfilter.controller.Index", "/index");
        
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(false);
        resource_handler.setResourceBase(Config.getParam("APP", "resource"));
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, servlet_handler});
        
        server.setHandler(handlers);
        
        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);
        server.start();
        server.join();
    }

    @Override
    public String getStartTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}
