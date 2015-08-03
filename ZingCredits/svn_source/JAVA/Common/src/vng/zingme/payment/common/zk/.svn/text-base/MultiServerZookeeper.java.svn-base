/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.common.zk;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.zookeeper.ZooKeeper;

/**
 *
 * @author root
 */
public class MultiServerZookeeper {

    public ZooKeeper newZookeeper() throws IOException {
        ++selected;
        if (selected >= _allsrv.size()) {
            selected = 0;
        }
        System.out.println(_allsrv.elementAt(selected));
        return new ZooKeeper(_allsrv.elementAt(selected), 3000, null);
    }

    private MultiServerZookeeper() {
        _allsrv = new Vector<String>(4, 2);
        Enumeration<Object> en = System.getProperties().keys();
        while (en.hasMoreElements()) {
            String k = (String) en.nextElement();
            if (k.startsWith("zkHostPort.")) {
                String hostport = System.getProperty(k);
                _allsrv.add(hostport);
            }
        }
    }
    private static MultiServerZookeeper _instance = null;

    public static MultiServerZookeeper getInstance() {
        if (_instance == null) {
            synchronized (_lock) {
                if (_instance == null) {
                    _instance = new MultiServerZookeeper();
                }
            }
        }
        return _instance;
    }
    private Vector<String> _allsrv = null;
    private static int selected = -1;
    private static Object _lock = new Object();
}
