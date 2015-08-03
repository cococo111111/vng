/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import ZGenerator.Generator;
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
public class GenmasterFactory implements PoolableObjectFactory {

    private String host;
    private int port;

    public GenmasterFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object makeObject() throws Exception {
        TTransport socket = new TSocket(host, port);
        // TTransport framedtrans = new TFramedTransport(socket);
        // TProtocol protocol = new TBinaryProtocol(framedtrans);
        // framedtrans.open();
        TProtocol protocol = new TBinaryProtocol(socket);
        socket.open();
        Generator.Client client = new Generator.Client(protocol);
        return client;
    }

    public void destroyObject(Object o) throws Exception {
        Generator.Client client = (Generator.Client) o;
        ((TSocket) client.getInputProtocol().getTransport()).close();
        ((TSocket) client.getOutputProtocol().getTransport()).close();
    }

    public boolean validateObject(Object o) {
        return ((TSocket) ((Generator.Client) o).getOutputProtocol().getTransport()).isOpen();
    }

    public void activateObject(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void passivateObject(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
