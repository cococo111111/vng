/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.amq;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.jms.Connection;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.backend.ZKBackEndMainWorker;
import vng.zingme.payment.bo.thrift.MEFramedTransport;
import vng.zingme.payment.bo.thrift.SerializeDeserializeHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.ScriberUtil;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;
import vng.zingme.util.StringUtil;

/**
 *
 * @author root
 */
public class AMQueueProducer {

    public AMQueueProducer() {
        try {
            // Create the connection.
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();

            initialize(connection);
        } catch (JMSException ex) {
            log.error("Start Producer fail " + ex.getMessage());
        }
    }

    private String createMessage(T_Transaction tranx) {
        String res = "";

        String key = ZKBackEndMainWorker.getKey(tranx.agentID, 2);

        if (key == null) {
            return res;
        }

        String mac = "";

        std__vectorT_std__string_t params = new std__vectorT_std__string_t();
        // params.add(tranx.agentID);
        // params.add(tranx.userName);
        params.add(String.valueOf(tranx.userID));
        params.add(tranx.refID);
        params.add(tranx.itemIDs);
        params.add(tranx.itemNames);
        params.add(tranx.itemQuantities);
        params.add(tranx.itemPrices);
        params.add(String.valueOf(tranx.amount));
        params.add(String.valueOf(tranx.txTime));
        params.add(String.valueOf(tranx.txID));
        zcommon_StringHolder k = new zcommon_StringHolder();
        k.setValue(key);
        zcommon_StringHolder data = new zcommon_StringHolder();
        //encode
        int e = ZCypher.zma_encode(data, params, k, 0, 0);

        mac = data.getValue();

        tranx.mac = mac;

        byte[] tranxEnc = null;
        tranxEnc = SerializeDeserializeHandler.getMainInstance().serialize(tranx).clone();

        if (tranxEnc == null) {
            return res;
        }

        return StringUtil.getHexString(tranxEnc);
    }

    public int enAMQueue(String strMsg) {
        if (connection == null) {
            return PaymentReturnCode.ERROR_OPERATOR_FAIL;
        }
        int res = PaymentReturnCode.ERROR_OPERATOR_FAIL;
        try {
            if (session == null || producer == null) {
                initialize(connection);
            }
            // Start sending messages
            TextMessage message = session.createTextMessage(strMsg);

            producer.send(message);

            String msg;

            res = PaymentReturnCode.SUCCESS;

        } catch (Exception e) {
            String msg = "[" + this.getClass().getName() + "] Caught: " + e;
            // System.out.println(msg);
            log.error(msg);
        }
        return res;
    }

    public int enAMQueue(T_Transaction tranx) {
        //log stat to db
        ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_REST_REQUESTING, CommonDef.TRANX_RES_CODE.TC_INPROCESS, ""));

        tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_REST_REQUESTING;
        String s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_INPROCESS);

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

        return enAMQueue(createMessage(tranx));
    }
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = "RETURNGAME.QUEUE";
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;

    private void initialize(Connection connection) {
        try {
            // Create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            // Create the producer.
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.setTimeToLive(0);
        } catch (JMSException ex) {
            log.warn("initialize fail: " + ex.getMessage());
        }
    }

    public void stopProducer() {
        try {
            connection.close();
        } catch (JMSException ex) {
            log.info(ex.getMessage());
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
    private static final Logger datalog = Logger.getLogger("dataActions");
}
