/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.bo.thrift;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;
import vng.zingme.util.LogUtil;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author root
 */
public class ObjectSerialize {

    public byte[] serialize(Object o) {
        byte[] res = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            res = baos.toByteArray();
            baos.reset();
            oos.close();
        } catch (Exception ex) {
            log.error(LogUtil.stackTrace(ex));
        }
        return res;
    }

    public String serializeS(Object o) {
        String res = "";
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            byte[] bytes = baos.toByteArray();
            res = new String(Base64.encodeBase64(bytes));
            baos.reset();
            oos.close();
        } catch (Exception ex) {
            log.error(LogUtil.stackTrace(ex));
        }
        return res;
    }
    private final Logger log = Logger.getLogger("appActions");
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ObjectOutputStream oos = null;
}
