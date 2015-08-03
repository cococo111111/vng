package vng.zingme.queue;


public interface Queue {
	public Command take();
	public boolean put(Command i);
	
}
