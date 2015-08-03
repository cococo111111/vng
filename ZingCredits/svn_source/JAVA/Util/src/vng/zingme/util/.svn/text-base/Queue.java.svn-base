package vng.zingme.util;

import java.util.LinkedList;

public class Queue{
	private LinkedList<Runnable> queue = null;
	public Queue(){
		queue = new LinkedList<Runnable>();
	}
	
	public void enqueue(Runnable r){
		queue.addLast(r);
	}
	public Runnable dequeue(){
		return (queue.size()>0)? queue.removeFirst():null;
	}
}
