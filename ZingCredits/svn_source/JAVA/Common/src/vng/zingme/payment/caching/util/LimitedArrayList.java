package vng.zingme.payment.caching.util;

import java.util.ArrayList;

public class LimitedArrayList<K> extends ArrayList<K> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int max;
	private long totalBytes = 0;
	
	public LimitedArrayList(int max){
		super(max);
		this.max = max;
	}
	public boolean add(Object o){
		if(this.size() == max){
			Object rem = this.remove(max-1);
			if(rem instanceof byte[]){
				totalBytes -= ((byte[])rem).length;
			}
		}
		if(o instanceof byte[]){
			totalBytes += ((byte[])o).length;
		}
		this.add(0, (K) o);
		return true;
	}
	public long getTotalBytes() {
		return totalBytes;
	}
}
