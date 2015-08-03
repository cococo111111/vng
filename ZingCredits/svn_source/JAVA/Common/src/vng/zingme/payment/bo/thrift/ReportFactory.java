/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import vng.zingme.payment.thrift.TReport;

/**
 *
 * @author root
 */
public class ReportFactory implements PoolableObjectFactory {

    private String host;
    private int port;

    public ReportFactory(String host, int port) {
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
        TReport.Client client = (TReport.Client) obj;
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
        TReport.Client client = new TReport.Client(protocol);
        return client;
    }

    @Override
    public void passivateObject(Object arg0) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean validateObject(Object obj) {
        // TODO Auto-generated method stub
        return ((TReport.Client) obj).getOutputProtocol().getTransport().isOpen();
    }
}
