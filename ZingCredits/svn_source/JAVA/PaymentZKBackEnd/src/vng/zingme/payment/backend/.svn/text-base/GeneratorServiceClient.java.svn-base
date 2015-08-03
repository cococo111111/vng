/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

import com.vng.zing.common.thriftutil.TClientPool;
import com.vng.zing.common.thriftutil.TClientPoolConfig;
import com.vng.zing.common.thriftutil.TClientPoolMan;
import com.vng.zingme.ZGenerator.Generator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.cliffc.high_scale_lib.NonBlockingHashMap;

/**
 *
 * @author root
 */
public class GeneratorServiceClient {

    private static Logger logger_ = Logger.getLogger(GeneratorServiceClient.class);
    private static final Lock createLock_ = new ReentrantLock();
    private TClientPoolConfig.ConnConfig CONFIG_CONN_CFG_R;
    private TClientPool<Generator.Client> Pool;
    private static int max_retry = 2;
    private static Map<String, GeneratorServiceClient> _instances = new NonBlockingHashMap();

    public static GeneratorServiceClient getInstance(String host, int port) {
        String key = host + port;
        if (!_instances.containsKey(key)) {
            createLock_.lock();
            try {
                if (_instances.get(key) == null) {
                    _instances.put(key, new GeneratorServiceClient(host, port));
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instances.get(key);
    }

    public GeneratorServiceClient(String host, int port) {
        CONFIG_CONN_CFG_R = new TClientPoolConfig.ConnConfig(
                host, port, false, false, 0);
        
        // set config instead of TClientPoolConfig.DefaultPooTlConfig
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxIdle = 10;
        config.minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
        config.maxActive = -1; //unlimit
        config.maxWait = -1; //unlimit
        config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
        config.testOnBorrow = false;
        config.testOnReturn = false;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 10000; //10 secs
        config.numTestsPerEvictionRun = -1; //take size of pools
        config.minEvictableIdleTimeMillis = 3600000; //1hour
        config.softMinEvictableIdleTimeMillis = -1; //unlimit
        config.lifo = false; //queue

        TClientPoolMan.Instance.addPool(
                new TClientPool<Generator.Client>(
                new Generator.Client.Factory(), CONFIG_CONN_CFG_R, config));

        Pool = (TClientPool<Generator.Client>) TClientPoolMan.Instance.getFirstPool(Generator.Client.class);
    }

    public int createGenerator(String name) {
        int ret = 0;
        int retry_max = max_retry;
        int retry = 0;

        while (retry < retry_max) {

            try {
                Generator.Client cli = Pool.borrowClient();
                try {
                    ret = cli.createGenerator(name);
                    Pool.returnObject(cli);
                    return ret;
                } catch (Exception ex) {
                    Pool.invalidObjExless(cli);
                    throw ex;
                }
            } catch (Exception e) {
                if (retry < retry_max) {
                    logger_.error("Exception createGenerator for name=" + name + " exceed need retry #" + retry + " : " + e.getMessage());
                    retry++;
                } else {
                    logger_.error("Exception createGenerator for name=" + name + " exceed max retry " + retry_max + " : " + e.getMessage());
                    return 0;
                }
            }
        }
        return ret;
    }

    public long getCurrentValue(String name) {
        long ret = 0;
        int retry_max = max_retry;
        int retry = 0;

        while (retry < retry_max) {

            try {
                Generator.Client cli = Pool.borrowClient();
                try {
                    ret = cli.getCurrentValue(name);
                    Pool.returnObject(cli);
                    return ret;
                } catch (Exception ex) {
                    Pool.invalidObjExless(cli);
                    throw ex;
                }
            } catch (Exception e) {
                if (retry < retry_max) {
                    logger_.error("Exception getUserLogged exceed need retry #" + retry + " : " + e.getMessage());
                    retry++;
                } else {
                    logger_.error("Exception getUserLogged exceed max retry " + retry_max + " : " + e.getMessage());
                    return 0;
                }
            }
        }
        return ret;
    }

    public long getValue(String name) {
        long ret = 0;
        int retry_max = max_retry;
        int retry = 0;

        while (retry < retry_max) {
            try {
                Generator.Client cli = Pool.borrowClient();
                try {
                    ret = cli.getValue(name);
                    Pool.returnObject(cli);
                    return ret;
                } catch (Exception ex) {
                    Pool.invalidObjExless(cli);
                    throw ex;
                }
            } catch (Exception e) {
                if (retry < retry_max) {
                    logger_.error("Exception getUserLogged exceed need retry #" + retry + " : " + e.getMessage());
                    retry++;
                } else {
                    logger_.error("Exception getUserLogged exceed max retry " + retry_max + " : " + e.getMessage());
                    return 0;
                }
            }
        }
        return ret;
    }
}