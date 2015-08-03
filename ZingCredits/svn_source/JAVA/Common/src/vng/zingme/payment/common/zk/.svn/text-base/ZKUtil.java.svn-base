package vng.zingme.payment.common.zk;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ZKUtil {

    public static boolean createZKNode(ZooKeeper zk, String nodePathName, byte[] initialVal) {
        long tmp = System.nanoTime();
        ++totalCreate;
        try {
            if (zk.create(nodePathName, initialVal, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT) != null) {
                return true;
            }
        } catch (KeeperException ex) {
            log.warn(ex.getMessage());
        } catch (InterruptedException ex) {
            log.warn(ex.getMessage());
        } finally {
            totalMicroSecCreate += ((System.nanoTime() - tmp) / 1000);
        }
        return false;
    }

    public static void deleteZKNode(ZooKeeper zk, String nodePathName) throws IOException,
            InterruptedException, KeeperException {

        Stat stat = zk.exists(nodePathName, false);
        if (stat == null) {
            return;
        }

        List<String> children1 = zk.getChildren(nodePathName, false);
        List<String> c2 = zk.getChildren(nodePathName, false, stat);

        if (!children1.equals(c2)) {
            // fail("children lists from getChildren()/getChildren2() do not match");
            System.out.println("children lists from getChildren()/getChildren2() do not match");
        }

        if (!stat.equals(stat)) {
            // fail("stats from exists()/getChildren2() do not match");
            System.out.println("stats from exists()/getChildren2() do not match");
        }

        if (children1.isEmpty()) {
            zk.delete(nodePathName, -1);
            return;
        }

        for (String n : children1) {
            deleteZKNode(zk, nodePathName + CommonDef.FILE_SEP + n);
        }

        zk.delete(nodePathName, -1);
    }

    public static byte[] readZKNode(ZooKeeper zk, String nodePathName) {
        long tmp = System.nanoTime();
        ++totalRead;
        byte[] data = null;
        try {
            data = zk.getData(nodePathName, false, null);
        } catch (KeeperException ex) {
            if (ex.code() == KeeperException.Code.CONNECTIONLOSS) {
            }
            if (ex.code() == KeeperException.Code.NONODE) {
            }
            log.info(ex.getMessage());
        } catch (InterruptedException ex) {
            log.info(ex.getMessage());
        } finally {
            totalMicroSecRead += ((System.nanoTime() - tmp) / 1000);
        }
        return data;
    }

    public static boolean writeZKnode(ZooKeeper zk, String nodePathName, byte[] data) {
        long tmp = System.nanoTime();
        ++totalWrite;
        try {
            if (zk.setData(nodePathName, data, -1) != null) {
                return true;
            }
        } catch (KeeperException ex) {
            log.warn(ex.getMessage());
        } catch (InterruptedException ex) {
            log.warn(ex.getMessage());
        } finally {
            totalMicroSecWrite += ((System.nanoTime() - tmp) / 1000);
        }
        return false;
    }

    public static int createZKPath(ZooKeeper zk, String path, String auth) {
        String[] listString = path.split(CommonDef.FILE_SEP);
        String string = "";
        for (int i = 1; i < listString.length; ++i) {
            string += CommonDef.FILE_SEP;
            string += listString[i];
            Stat stat = null;
            try {
                stat = zk.exists(string, false);
            } catch (KeeperException ex) {
                log.warn(ex.getMessage());
            } catch (InterruptedException ex) {
                log.warn(ex.getMessage());
            }
            if (stat == null) {
                createZKNode(zk, string, CommonDef.B_SIG);
            }
        }
        return PaymentReturnCode.SUCCESS;
    }

    public static boolean existPath(ZooKeeper zk, String path, String auth) {
        Stat stat = null;
        long tmp = System.nanoTime();
        ++totalCheckExist;
        try {
            stat = zk.exists(path, null);
        } catch (KeeperException ex) {
            log.info(ex.getMessage());
        } catch (InterruptedException ex) {
            log.info(ex.getMessage());
        } finally {
            totalMicroSecCheckExist += ((System.nanoTime() - tmp) / 1000);
        }
        return stat != null;
    }
    public static volatile long totalCreate = 0;
    public static volatile long totalCheckExist = 0;
    public static volatile long totalWrite = 0;
    public static volatile long totalRead = 0;
    public static volatile long totalMicroSecCreate = 0;
    public static volatile long totalMicroSecCheckExist = 0;
    public static volatile long totalMicroSecWrite = 0;
    public static volatile long totalMicroSecRead = 0;
    private static final Logger log = Logger.getLogger("appActions");
}
