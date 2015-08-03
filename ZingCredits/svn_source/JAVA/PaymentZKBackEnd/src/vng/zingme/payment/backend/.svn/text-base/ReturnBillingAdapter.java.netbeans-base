/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.console.command.store.amq.CommandLineSupport;
import org.apache.log4j.Logger;
import vng.zingme.payment.amq.LogAMQueueProducer;
import vng.zingme.payment.bo.thrift.SerializeDeserializeHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.ScriberUtil;
import vng.zingme.payment.gamereturn.GameServiceClient;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;
import vng.zingme.util.StringUtil;

/**
 *
 * @author root
 */
public class ReturnBillingAdapter extends Thread implements MessageListener, ExceptionListener {

    private boolean running;
    private Session session;
    private Destination destination;
    private MessageProducer replyProducer;
    private boolean pauseBeforeShutdown = false;
    private boolean verbose = true;
    private int maxiumMessages;
    private static int parallelThreads = 1;
    private String subject = "RETURNGAME.QUEUE";
    private boolean topic;
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private boolean transacted;
    private boolean durable;
    private String clientId;
    private int ackMode = Session.AUTO_ACKNOWLEDGE;
    private long sleepTime;
    private long receiveTimeOut;

    public static void main(String[] args) {
        ArrayList<ReturnBillingAdapter> threads = new ArrayList();
        ReturnBillingAdapter consumerTool = new ReturnBillingAdapter();

        parallelThreads = Integer.parseInt(System.getProperty("numberconsumer", "20"));

        String[] unknown = CommandLineSupport.setOptions(consumerTool, args);
        if (unknown.length > 0) {
            System.out.println("Unknown options: " + Arrays.toString(unknown));
            System.exit(-1);
        }

        // consumerTool.showParameters();
        for (int threadCount = 1; threadCount <= parallelThreads; threadCount++) {
            consumerTool = new ReturnBillingAdapter();
            CommandLineSupport.setOptions(consumerTool, args);
            consumerTool.start();
            threads.add(consumerTool);
        }

        while (true) {
            Iterator<ReturnBillingAdapter> itr = threads.iterator();
            int running = 0;
            while (itr.hasNext()) {
                ReturnBillingAdapter thread = itr.next();
                if (thread.isAlive()) {
                    running++;
                }
            }

            if (running <= 0) {
                System.out.println("All threads completed their work");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        Iterator<ReturnBillingAdapter> itr = threads.iterator();
        while (itr.hasNext()) {
            ReturnBillingAdapter thread = itr.next();
        }
    }

    public void showParameters() {
        System.out.println("Connecting to URL: " + url);
        System.out.println("Consuming " + (topic ? "topic" : "queue") + ": " + subject);
        System.out.println("Using a " + (durable ? "durable" : "non-durable") + " subscription");
        System.out.println("Running " + parallelThreads + " parallel threads");
    }

    public void run() {
        try {
            running = true;

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            Connection connection = connectionFactory.createConnection();
            if (durable && clientId != null && clientId.length() > 0 && !"null".equals(clientId)) {
                connection.setClientID(clientId);
            }
            connection.setExceptionListener(this);
            connection.start();

            subject = "RETURNGAME.QUEUE";

            session = connection.createSession(transacted, ackMode);
            if (topic) {
                destination = session.createTopic(subject);
            } else {
                destination = session.createQueue(subject);
            }

            replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer consumer = null;
            consumer = session.createConsumer(destination);

            maxiumMessages = 0;
            receiveTimeOut = 0;

            if (maxiumMessages > 0) {
                consumeMessagesAndClose(connection, session, consumer);
            } else {
                if (receiveTimeOut == 0) {
                    consumer.setMessageListener(this);
                } else {
                    consumeMessagesAndClose(connection, session, consumer, receiveTimeOut);
                }
            }

        } catch (Exception e) {
            String msg = "[" + this.getName() + "] Caught: " + e;
            // System.out.println(msg);
            log.error(msg);
        }
    }
    int count_ = 0;

    public void onMessage(Message message) {
        try {
            // System.out.println(message);
            if (message instanceof TextMessage) {

                TextMessage txtMsg = (TextMessage) message;

                GameResponse gRes = new GameResponse(PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT, user);

                byte[] tranxEnc = StringUtil.HexStringToByteArray(txtMsg.getText());

                T_Transaction tranx = null;
                tranx = SerializeDeserializeHandler.getMainInstance().deserialize(tranxEnc);

                if (tranx == null) {
                    log.warn("Transaction deserialized is null.");
                    return;
                }

                for (int i = 0; i < RETRY_COUNT && (gRes.code == PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT); ++i) {

                    gRes = _gameService.sendReturnGame(tranx);
                }

                if (gRes.code >= PaymentReturnCode.GameCode.G_SUCCESS) {

                    ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS, (short) gRes.code, ""));

                    ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS, gRes.des, tranx.currentBalance));

                    tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS;
                    String s_data_log = ScriberUtil.logme(tranx, gRes.code);

                    try {

                        ScriberUtil.getScribe().log(s_data_log);
                        // log payment
                        logProducer.enAMQueue(tranx);
                    } catch (Exception ex) {
                        log.warn(LogUtil.stackTrace(ex));
                    }

                    try {
                        datalog.info(s_data_log);
                    } catch (Exception ex) {
                        log.warn(LogUtil.stackTrace(ex));
                    }

                } else {
                    if (gRes.code == PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT) {

                        ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT, (short) gRes.code, ""));

                        ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT, gRes.des, tranx.currentBalance, gRes.code));

                        tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT;
                        String s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_NET_ERROR);

                        try {

                            ScriberUtil.getScribe().log(s_data_log);
                            // log payment
                            logProducer.enAMQueue(tranx);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        try {
                            datalog.info(s_data_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        String s_fail_log = s_data_log + "\t" + gRes.des;
                        try {
                            updatefaillog.info(s_fail_log);
                            responseTimeoutlog.info(s_fail_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                    } else {

                        if (ZKBackEndMainWorker.getInstance().doJob(tranx.txID, true, true) != PaymentReturnCode.ERROR_OPERATOR_FAIL) {
                            if (gRes.code == PaymentReturnCode.GameCode.G_ERROR_NETWORK_FAIL
                                    || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_COMPATIBLE
                                    || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_FOUND_SERVICE
                                    || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_SERVER
                                    || gRes.code == PaymentReturnCode.GameCode.G_ERROR_SERVICE_INVALID
                                    || gRes.code == PaymentReturnCode.GameCode.G_ERROR_UNKNOWN) {
                                ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK, (short) gRes.code, ""));

                                ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK, gRes.des, tranx.currentBalance, gRes.code));

                                tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK;
                                String s_data_log = ScriberUtil.logme(tranx, gRes.code);

                                try {

                                    ScriberUtil.getScribe().log(s_data_log);
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }

                                try {
                                    datalog.info(s_data_log);
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }

                                String s_fail_log = s_data_log + "\t" + gRes.des;
                                try {
                                    updatefaillog.info(s_fail_log);
                                    if (gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_COMPATIBLE) {
                                        errorNotCompatiblelog.info(s_fail_log);
                                    }
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }

                            } else {
                                ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL, (short) gRes.code, gRes.des));

                                ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_FAIL, gRes.des, tranx.currentBalance));

                                tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL;
                                String s_data_log = ScriberUtil.logme(tranx, gRes.code);

                                try {

                                    ScriberUtil.getScribe().log(s_data_log);
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }

                                try {
                                    datalog.info(s_data_log);
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }

                                String s_fail_log = s_data_log + "\t" + gRes.des;
                                try {
                                    updatefaillog.info(s_fail_log);
                                } catch (Exception ex) {
                                    log.warn(LogUtil.stackTrace(ex));
                                }
                            }
                        }
                    }
                }

                //recycle path in zookeeper
                ZKBackEndMainWorker.getInstance().getRecyclezkworker().work(ZookeeperListenerManager.getMainInstance().getParentTranxPath() + CommonDef.FILE_SEP + tranx.txID);

                // ZKBackendHandler.remove(astr[1]);
            } else {

                if (verbose) {
                    String msg = "[" + this.getName() + "] Received: '" + message + "'";
                    // System.out.println(msg);
                    log.info(msg);
                }

            }

            if (message.getJMSReplyTo() != null) {
                replyProducer.send(message.getJMSReplyTo(), session.createTextMessage("Reply: " + message.getJMSMessageID()));
            }

            if (transacted) {
                session.commit();
            } else if (ackMode == Session.CLIENT_ACKNOWLEDGE) {
                message.acknowledge();
            }

        } catch (JMSException e) {
            String msg = "[" + this.getName() + "] Caught: " + e;
            // System.out.println(msg);
            log.error(msg);
        }
    }

    public synchronized void onException(JMSException ex) {
        String msg = "[" + this.getName() + "] JMS Exception occured.  Shutting down client.";
        // System.out.println(msg);
        log.error(msg);
        running = false;
    }

    synchronized boolean isRunning() {
        return running;
    }

    protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer) throws JMSException,
            IOException {
        System.out.println("[" + this.getName() + "] We are about to wait until we consume: " + maxiumMessages
                + " message(s) then we will shutdown");

        for (int i = 0; i < maxiumMessages && isRunning();) {
            Message message = consumer.receive(1000);
            if (message != null) {
                i++;
                onMessage(message);
            }
        }
        System.out.println("[" + this.getName() + "] Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            System.out.println("[" + this.getName() + "] Press return to shut down");
            System.in.read();
        }
    }

    protected void consumeMessagesAndClose(Connection connection, Session session, MessageConsumer consumer, long timeout)
            throws JMSException, IOException {
        System.out.println("[" + this.getName() + "] We will consume messages while they continue to be delivered within: " + timeout
                + " ms, and then we will shutdown");

        Message message;
        while ((message = consumer.receive(timeout)) != null) {
            onMessage(message);
        }

        System.out.println("[" + this.getName() + "] Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (pauseBeforeShutdown) {
            System.out.println("[" + this.getName() + "] Press return to shut down");
            System.in.read();
        }
    }

    public void setAckMode(String ackMode) {
        if ("CLIENT_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.CLIENT_ACKNOWLEDGE;
        }
        if ("AUTO_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.AUTO_ACKNOWLEDGE;
        }
        if ("DUPS_OK_ACKNOWLEDGE".equals(ackMode)) {
            this.ackMode = Session.DUPS_OK_ACKNOWLEDGE;
        }
        if ("SESSION_TRANSACTED".equals(ackMode)) {
            this.ackMode = Session.SESSION_TRANSACTED;
        }
    }

    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public void setMaxiumMessages(int maxiumMessages) {
        this.maxiumMessages = maxiumMessages;
    }

    public void setPauseBeforeShutdown(boolean pauseBeforeShutdown) {
        this.pauseBeforeShutdown = pauseBeforeShutdown;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public void setReceiveTimeOut(long receiveTimeOut) {
        this.receiveTimeOut = receiveTimeOut;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setParallelThreads(int parallelThreads) {
        if (parallelThreads < 1) {
            parallelThreads = 1;
        }
        this.parallelThreads = parallelThreads;
    }

    public void setTopic(boolean topic) {
        this.topic = topic;
    }

    public void setQueue(boolean queue) {
        this.topic = !queue;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    private static GameServiceClient _gameService = new GameServiceClient();
    private static LogAMQueueProducer logProducer = new LogAMQueueProducer();
    private static final int RETRY_COUNT = 3;
    private static final Logger log = Logger.getLogger("appActions");
    private static final Logger datalog = Logger.getLogger("dataActions");
    private static final Logger updatefaillog = Logger.getLogger("updatefailActions");
    private static final Logger responseTimeoutlog = Logger.getLogger("responseTimeout");
    private static final Logger errorNotCompatiblelog = Logger.getLogger("errorNotCompatible");

}
