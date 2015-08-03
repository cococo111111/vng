/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 *
 * @author root
 */
public class SerializeDeserializeFactory implements PoolableObjectFactory {

    public Object makeObject() throws Exception {
        return new MEFramedTransport(null);
    }

    public void destroyObject(Object o) throws Exception {
        MEFramedTransport mft = (MEFramedTransport) o;
        mft.close();
    }

    public boolean validateObject(Object o) {
        MEFramedTransport mft = (MEFramedTransport) o;
        return mft.isOpen();
    }

    public void activateObject(Object o) throws Exception {
    }

    public void passivateObject(Object o) throws Exception {
    }
}
