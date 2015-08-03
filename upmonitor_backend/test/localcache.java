
import com.vng.jcore.cache.lruexpire.LruExpireCache;
import com.vng.jcore.cache.lruexpire.LruExpireCacheManager;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author locth2
 */
public class localcache {
     private static LruExpireCache<String,String> lruCached = null;
    public static void main(String[] args) {
       lruCached = LruExpireCacheManager.getCache("loc", 100);
       lruCached.put("hoan", "a",-1l);
         String get = lruCached.get("hoan");
        System.out.println(get);
       
               
    }
}
