/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consumer;

import aMQCommon.MQCommon;
import balanceDao.BalanceDaoImpl;
import daoService.IDao;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class ActiveMQConsumer extends Thread implements MessageListener {

    private static final Logger log = Logger.getLogger("exception");
    private ConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;
    public IDao dao = null;

    public ActiveMQConsumer() {
        if (dao == null) {
            dao = BalanceDaoImpl.getInstance();
        }
        this.connectionFactory = new ActiveMQConnectionFactory(MQCommon.url);
        try {
            this.connection = connectionFactory.createConnection();
            this.connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination = session.createQueue(MQCommon.subject);
        } catch (JMSException ex) {
            log.error("CONSUMER FAIL");
        }
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
//            log.info("received a Object Message");
//            TextMessage msg = (TextMessage) message;
//            try {
//                Gson gson = new Gson();
//                BalanceDTO balanceDto = gson.fromJson(msg.getText(), BalanceDTO.class);
//                log.info("BalanceDTO:"
//                        + "\t userID: " + balanceDto.getUserId()
//                        + ",\t amout: " + balanceDto.getAmount()
//                        + ",\t TxType: " + balanceDto.getTxType()
//                        + ",\t TxTime: " + balanceDto.getTxTime());
//                dao.insert(balanceDto);
//            } catch (JMSException ex) {
//                ex.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        int numberConsumer = 3;
        for (int i = 0; i < numberConsumer; i++) {
            Runnable task = new Runnable() {
                public int startIndex;
                ActiveMQConsumer activeMQ;

                public Runnable setStart(int startIndex) {
                    activeMQ = new ActiveMQConsumer();
                    this.startIndex = startIndex;
                    return this;
                }

                @Override
                public void run() {
                    try {
                        activeMQ.consumer = activeMQ.session.createConsumer(activeMQ.destination);
                        activeMQ.consumer.setMessageListener(activeMQ);

                    } catch (JMSException ex) {
                        log.error("active consumer fail");
                    }
                }
            }.setStart(i);
            new Thread(task).start();
        }
    }
}
