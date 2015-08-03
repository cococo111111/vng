package vng.zingme.payment.backend;

import vng.zingme.payment.common.worker.IWorkerRunable;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import vng.zingme.payment.common.zk.ZKUtil;
import vng.zingme.util.LogUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class RecycleZKWorker implements IWorkerRunable {

	private static ArrayBlockingQueue<String> workerQueue = null;

	public RecycleZKWorker() {
		int _recyclezkQueueSize = Integer.parseInt(System.getProperty("recyclezkqueuesize", "512"));
		workerQueue = new ArrayBlockingQueue<String>(_recyclezkQueueSize, true);
	}

	public void run() {
		ZooKeeper zk = null;
		zk = ZookeeperListenerManager.getZk();
		while (!stoped) {
			try {
				String path = workerQueue.take();
				if (path != null) {
					boolean isRemoved = false;

					try {
						ZKUtil.deleteZKNode(zk, path);
						isRemoved = true;
					} catch (IOException ex) {
						log.info(ex.getMessage());
					} catch (InterruptedException ex) {
						log.info(ex.getMessage());
					} catch (KeeperException ex) {
						if (ex.code() == KeeperException.Code.NONODE) {
							isRemoved = true;
						}
						log.info(ex.getMessage());
						if (ex.code() == KeeperException.Code.SESSIONEXPIRED) {
							try {
								zk.close();
							} catch (Exception except) {
								log.warn(LogUtil.stackTrace(except));
							}
							zk = ZookeeperListenerManager.reNewZookeeper();
						}
					}

					if (isRemoved) {
					} else {
						log.info("Can't delete path: " + path + "from ZK!");
						workerQueue.put(path);
					}
				}
			} catch (InterruptedException ex) {
				log.info(ex.getMessage());
			}

		}
	}

	public void stop() {
		stoped = true;
	}
	static boolean stoped = false;

	public void pushJob(Object obj) {
		try {
			workerQueue.put((String) obj);
		} catch (InterruptedException ex) {
			log.warn(ex.getMessage());
		}
	}
	private static final Logger log = Logger.getLogger("appActions");
}
