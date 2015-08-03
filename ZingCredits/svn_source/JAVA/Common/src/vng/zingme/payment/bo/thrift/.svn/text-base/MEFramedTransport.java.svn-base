package vng.zingme.payment.bo.thrift;

import java.io.ByteArrayInputStream;

import org.apache.thrift.TBase;
import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import vng.zingme.payment.thrift.T_Transaction;

// import vng.zingme.mep_bo.thrift.T_Transaction;
public class MEFramedTransport extends TFramedTransport {

    private final String packageName = "vng.zingme.payment.thrift";
    private TByteArrayOutputStream arrayWritter = new TByteArrayOutputStream();
    private TTransport transport = null;

    public MEFramedTransport(TTransport transport) {
        super(transport);
        // TODO Auto-generated constructor stub
        this.transport = transport;
    }

    public void write(byte[] buf, int off, int len) throws TTransportException {
        if (transport == null) {
            arrayWritter.write(buf, off, len);
        } else {
            this.write(buf, off, len);
        }
    }

    public byte[] serialize(T_Transaction tx) throws TException {
        TProtocol prot = new TBinaryProtocol(this);
        tx.write(prot);
        return arrayWritter.get();
    }
    private ByteArrayInputStream readBuffer;

    public int read(byte[] buf, int off, int len) throws TTransportException {
        if (transport != null) {
            return this.read(buf, off, len);
        }

        if (readBuffer != null) {
            int got = readBuffer.read(buf, off, len);
            if (got > 0) {
                return got;
            }
        }
        readBuffer = new ByteArrayInputStream(buf);
        return readBuffer.read(buf, off, len);
    }

    public void setBuf(ByteArrayInputStream buf) {
        readBuffer = buf;
    }

    public T_Transaction deserialize(byte[] b) throws TException {
        T_Transaction tx = new T_Transaction();
        readBuffer = new ByteArrayInputStream(b);
        TBinaryProtocol prot = new TBinaryProtocol(this);
        tx.read(prot);
        return tx;
    }

    public TBase deserialize(byte[] b, String className) throws Exception {
        className = packageName + "." + className;
        TBase tx = (TBase) Class.forName(className).newInstance();
        readBuffer = new ByteArrayInputStream(b);
        TBinaryProtocol prot = new TBinaryProtocol(this);
        tx.read(prot);
        return tx;
    }

    public byte[] serialize(TBase tx) throws Exception {
        TProtocol prot = new TBinaryProtocol(this);
        tx.write(prot);
        return arrayWritter.get();
    }

    public static void main(String[] args) {
        MEFramedTransport trans = new MEFramedTransport(null);
        T_Transaction tx = new T_Transaction();
        tx.userID = 12345;
        tx.userName = "Toan";
        try {
            byte[] b = trans.serialize(tx);
            T_Transaction t = (T_Transaction) trans.deserialize(b, "T_Transaction");
            System.out.println(t.toString());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void resetWrite() {
        arrayWritter.reset();
    }
}
