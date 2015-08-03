/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.amq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import vng.zingme.payment.common.VerifyCardCode;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author vinhcq@vng.com.vn
 */
public class LogAMQueueProducer {

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = System.getProperty("logQueueUrl");
    private String subject = System.getProperty("logQueueName");
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;
    private static final Logger log = Logger.getLogger("appActions");

    public LogAMQueueProducer() {
        try {
            // Create the connection.
            // Create the session
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            System.out.println(" add of connection " + connection);
            connection.start();

            initialize(connection);
        } catch (JMSException ex) {
            log.error("Start Producer fail " + ex.getMessage());
        }
    }

    private void initialize(Connection connection) {
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            // Create the producer.
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setTimeToLive(0);
        } catch (JMSException ex) {
            log.warn("initialize fail: " + ex.getMessage());
        }
    }

    public int enAMQueue(T_Transaction tx) {
        if (connection == null) {
            return VerifyCardCode.ERROR;
        }
        int res = VerifyCardCode.ERROR;
        try {
            if (session == null || producer == null) {
                initialize(connection);
            }
            // Start sending messages
            TextMessage message = session.createTextMessage(toScribeStr(tx));
            producer.send(message);
            res = VerifyCardCode.SUCCESS;
        } catch (Exception e) {
            String msg = "[" + this.getClass().getName() + "] Caught: " + e;
            // System.out.println(msg);
            log.error(msg);
        }
        return res;
    }

    private String toScribeStr(T_Transaction tx) {
        String data = tx.txLocalTime + "\t"
                + tx.agentID + "\t"
                + "_" + "\t"
                + "zingcredits" + "\t"
                + "zingcredits" + "\t"
                + tx.userName + "\t"
                + tx.userID + "\t"
                + "_" + "\t"
                + tx.amount + "\t"
                + tx.amount * 100 + "\t"
                + tx.txID;
        return data;
    }
}
