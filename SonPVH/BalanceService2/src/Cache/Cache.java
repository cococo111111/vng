/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Common.Balance;
import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;

/**
 *
 * @author root
 */
public class Cache {

    private static final int CACHE_ELEMENTS_SIZE = 2000000;
    private static final Cache cacheInstance = new Cache();
    private static ConcurrentLinkedHashMap<Integer, Balance> cache =
            ConcurrentLinkedHashMap.create(ConcurrentLinkedHashMap.EvictionPolicy.LRU, CACHE_ELEMENTS_SIZE);

    public static Cache getInstanceCache() {
        return cacheInstance;
    }

    /**
     * Get Element in Cache
     *
     * @param UserId
     *
     * @return Balance
     */
    public Balance getBalanceCache(int userId) {
        return Cache.cache.get(userId);
    }

    /**
     * update cache when get new User
     *
     * @param balance
     */
    public void updateBalanceCache(Balance balance) {
        Cache.cache.remove(balance.getUserId());
        Cache.cache.put(balance.getUserId(), balance);
    }
}
