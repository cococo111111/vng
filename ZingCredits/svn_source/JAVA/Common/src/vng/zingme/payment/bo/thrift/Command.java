package vng.zingme.payment.bo.thrift;

public interface Command<T, K> {
	public T exec(K client) throws Exception;
}
