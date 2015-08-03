package aMQCommon;

import org.apache.activemq.ActiveMQConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class MQCommon {

    private MQCommon() {
    }
    //    public static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // Name of the queue we will be sending messages to
    public static String subject = "TESTQUEUE12";
}
