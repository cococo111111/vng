package vng.bankinggateway.storage.client;

public interface Command<T, K> {
	public T exec(K client) throws Exception;
}
