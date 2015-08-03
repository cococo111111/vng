/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;

public class Serializator<T> {

    private static Serializator instance = null;
    private static final Logger log = Logger.getLogger("exception");

    public static Serializator getInstance() {
        if (instance == null) {
            instance = new Serializator();
        }
        return instance;
    }

    public byte[] serialize(T t) {
        byte[] data = null;
        ObjectOutputStream ObjectStream = null;
        ByteArrayOutputStream outputByte = null;
        try {
            outputByte = new ByteArrayOutputStream();
            ObjectStream = new ObjectOutputStream(outputByte);
            ObjectStream.writeObject(t);
            data = outputByte.toByteArray();
        } catch (IOException ex) {
            log.error("SERIALIZE FAIL (in SERIALIZE) "
                    + "ex: " + ex.getMessage());
        } finally {
            try {
                if (null != outputByte) {
                    outputByte.close();
                }
                if (null != ObjectStream) {
                    ObjectStream.close();
                }
            } catch (IOException ex) {
                log.error("CLOSE FAIL (in SERIALIZE) "
                        + " ex: " + ex.getMessage());
            }
        }
        return data;
    }

    public T deserialize(byte[] data) {
        ByteArrayInputStream inputStreamBytes = null;
        ObjectInputStream outputStreamBalance = null;
        T t = null;
        try {
            inputStreamBytes = new ByteArrayInputStream(data);
            outputStreamBalance = new ObjectInputStream(inputStreamBytes);
            t = (T) outputStreamBalance.readObject();
        } catch (IOException ex) {
            log.error("DESERIALIZE FAIL data "
                    + "ex: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log.error("Ex when cast obj into BalanceType (in DESERIALIZE"
                    + "ex: " + ex.getMessage());
        } finally {
            try {
                if (null != inputStreamBytes) {
                    inputStreamBytes.close();
                }
                if (null != outputStreamBalance) {
                    outputStreamBalance.close();
                }
            } catch (IOException ex) {
                log.error("CLOSE FAIL (in DESERIALIZE) "
                        + ". Ex: " + ex.getMessage());
            }
        }
        return t;
    }
}
