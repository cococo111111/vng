package vng.zingme.payment.bo.thrift;

import org.apache.thrift.TException;

import vng.zingme.payment.thrift.T_Transaction;

public class ThriftUtil {
	
	public static byte[] serializer(T_Transaction tx) throws TException{
		MEFramedTransport transport = new MEFramedTransport(null);
		return transport.serialize(tx);
	}
	
	public static T_Transaction deserializer(byte[] b) throws TException{
		MEFramedTransport transport = new MEFramedTransport(null);
		return transport.deserialize(b);
	}
}
