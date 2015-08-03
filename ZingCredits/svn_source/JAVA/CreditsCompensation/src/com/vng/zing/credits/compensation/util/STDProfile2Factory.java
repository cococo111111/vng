/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.credits.compensation.util;

import com.vng.zing.stdprofile2.thrift.StdProfile2Service_Rd;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author root
 */
public class STDProfile2Factory implements PoolableObjectFactory {

    private String host;
    private int port;

    public STDProfile2Factory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void activateObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroyObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
        StdProfile2Service_Rd.Client client = (StdProfile2Service_Rd.Client) obj;
        client.getInputProtocol().getTransport().close();
        client.getOutputProtocol().getTransport().close();
    }

    @Override
    public Object makeObject() throws Exception {
        // TODO Auto-generated method stub
        TTransport socket = new TSocket(host, port);
        TTransport framedtrans = new TFramedTransport(socket);
        TProtocol protocol = new TBinaryProtocol(framedtrans);
        framedtrans.open();
        StdProfile2Service_Rd.Client client = new StdProfile2Service_Rd.Client(protocol);
        return client;
    }

    @Override
    public void passivateObject(Object arg0) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean validateObject(Object obj) {
        // TODO Auto-generated method stub
        return ((StdProfile2Service_Rd.Client) obj).getOutputProtocol().getTransport().isOpen();
    }
}
