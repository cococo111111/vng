package vng.banking.admin.service;

import QuartzSchedulerJob.QuartzScheduler;
import com.vng.jcore.common.Config;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
import org.apache.log4j.Logger;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import vng.banking.admin.config.Configuration;

public class BankingServer implements BankingServerMBean {

        private static final Logger logger_ = Logger.getLogger(BankingServer.class);

        public void start() throws Exception {

                Server server = new Server();

                // Setup JMX
                MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
                String mbeanName = "vng.zingme.banking" + ":type=admin-banking";
                System.out.println("1");
                try {
                        mbContainer.getMBeanServer().registerMBean(this, new ObjectName(mbeanName));
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                int listenPort = Integer.parseInt(Config.getParam("system", "port"));
                int maxThreads = Integer.parseInt(Config.getParam("system", "maxthread"));	//1000
                System.out.println("2 listenPort " + listenPort);
                server.getContainer().addEventListener(mbContainer);
                server.addBean(mbContainer);
                mbContainer.addBean(Log.getLog());

                QueuedThreadPool threadPool = new QueuedThreadPool();
                threadPool.setMaxThreads(maxThreads);
                server.setThreadPool(threadPool);

                System.out.println("3 maxThreads " + maxThreads);
                SelectChannelConnector connector = new SelectChannelConnector();
                connector.setPort(listenPort);
                connector.setMaxIdleTime(30000);
                connector.setConfidentialPort(8443);
                connector.setStatsOn(false);

                server.setConnectors(new Connector[]{connector});
                
                ServletHandler handler = new ServletHandler();

                handler.addServletWithMapping(vng.banking.admin.servlet.TranxByStatusController.class, "/monitor/tranxbyStatus");
                handler.addServletWithMapping(vng.banking.admin.servlet.TranxByConfirmStatusController.class, "/monitor/tranxbyConfirmStatus");
                handler.addServletWithMapping(vng.banking.admin.servlet.StatsServlet.class, "/_stats");
                handler.addServletWithMapping(vng.banking.admin.servlet.StatsServlet.class, "/process");
                handler.addServletWithMapping(vng.banking.admin.servlet.LogTasksServlet.class, "/logTasks");
//                handler.addServletWithMapping(vng.banking.admin.servlet.Servlet404.class, "/");

                Configuration.init();
                
                ResourceHandler resource_handler = new ResourceHandler();
                resource_handler.setDirectoriesListed(true);
                resource_handler.setResourceBase(Configuration.SYSTEM_PUBLIC_PATH);
                HandlerList handlers = new HandlerList();
//                System.out.println("path : " + Configuration.SYSTEM_PUBLIC_PATH + " saasas = "+ resource_handler.getResourceBase());
                handlers.setHandlers(new Handler[]{handler, resource_handler});

                server.setHandler(handlers);
                server.setStopAtShutdown(true);
                server.setSendServerVersion(true);

                QuartzScheduler quartz = new QuartzScheduler();
                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(quartz.dailyUpdateJob(), quartz.getMidNightTrigger());
                
                Scheduler schedulerForNotifyEmail = new StdSchedulerFactory().getScheduler();
                schedulerForNotifyEmail.start();
                schedulerForNotifyEmail.scheduleJob(quartz.getNotifyJob(), quartz.getNotifyTrigger());

                server.start();
                server.join();
        }

        @Override
        public String getStartTime() {
                // TODO Auto-generated method stub
                return String.valueOf(System.currentTimeMillis());
        }
}
