/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producerService;

import aMQCommon.MQCommon;
import com.google.gson.Gson;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public abstract class ProducerImpl<T> implements IProducer<T> {

    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
    private ConnectionFactory connectionFactory = null;
    private static final Logger log = Logger.getLogger("exception");

    public ProducerImpl() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(MQCommon.url);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(MQCommon.subject);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException ex) {
            log.error("PRODUCER fails");
        }
    }

    @Override
    public boolean sendMessage(T t) {
        try {
            if (t == null) {
                log.error("WRONG BALANCE");
                return false;
            }
            //Send Message
            TextMessage message = createMessage(t);
            log.info("producer send message");
            this.producer.send(message);
            return true;
        } catch (JMSException ex) {
            log.error("PRODUCER SEND MESSAGE FAIL : at connection :"
                    + this.connection + " session: " + this.session
                    + " destination: " + this.destination);

            return false;
        }

    }

    private TextMessage createMessage(T t) {
        TextMessage message = null;
        try {
            // MessageProducer is used for sending messages (as opposed
            // to MessageConsumer which is used for receiving them)

            // We will send a small text message
            Gson gson = new Gson();
            String Json = gson.toJson(t);
            message = session.createTextMessage(Json);
        } catch (JMSException ex) {
            log.error("session: " + session.toString()
                    + "MESSAGE CREATION(1) FAIL: " + ex.getMessage());
        }
        return message;
    }
}
