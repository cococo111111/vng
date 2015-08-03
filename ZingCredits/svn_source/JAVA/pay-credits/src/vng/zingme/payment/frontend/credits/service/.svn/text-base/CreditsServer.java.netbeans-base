package vng.zingme.payment.frontend.credits.service;

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
        Configuration.init();
        ServletHandler handler = new ServletHandler();
        //server.setHandler(handler);
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.ErrorController", "/billing/error");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.RequestFormController", "/billing/requestform");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BillController", "/billing/bill");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BillStatusController", "/billing/billstatus");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.User4AppController", "/billing/ufora");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.DecodeController", "/billing/decode");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.MaintainController", "/maintain");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.RequestFormMobileController", "/m/requestform");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BillMobileController", "/m/bill");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.RequestFormWapController", "/wap/requestform");
        handler.addServletWithMapping("vng.zingme.payment.frontend.credits.service.BillWapController", "/wap/bill");
        //server.setHandler(handler);


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(false);
        resource_handler.setResourceBase(Configuration.SYSTEM_PUBLIC_PATH);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, handler});
        server.setHandler(handlers);

        //start cache       
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
