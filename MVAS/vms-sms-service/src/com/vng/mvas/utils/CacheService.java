package com.vng.mvas.utils;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;

public class CacheService {
	
	private static ConcurrentLinkedHashMap<String, String> _cache = null;
	private static CacheService cacheService = null;

	public CacheService() {
		int cacheSize = Integer.parseInt(System.getProperty("cacheSize"));
		 _cache = ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, cacheSize);

	}
	 public static CacheService getInstance() {
	        if (cacheService == null) {
	        	cacheService = new CacheService();
	              return cacheService;
	            } else {
	              return cacheService;
	            }
	    }
	 public ConcurrentLinkedHashMap<String, String> getCardcache() {
	        return _cache;
	    }
}
