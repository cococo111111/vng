package com.vng.mvas.worker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.vng.mvas.models.Sending;
import com.vng.mvas.utils.DBUtils;
import com.vng.mvas.vmsgateway.SendSMSClient;

public class SendMessageWorker extends Thread {
	private static Logger logger = Logger.getLogger(SendMessageWorker.class);
	private boolean stoped = false;
	private LogMessageWorker _logMessage = null;
	private ArrayBlockingQueue<Sending> workerQueue = null;
	private String telco = "";
	private static int sleep_inteval = Integer.parseInt(System
			.getProperty("sleep_inteval"));

	public SendMessageWorker(String telco) {
		this.telco = telco;
	}

	public int init() {
		int queuesize = Integer.parseInt(System.getProperty("queuesize",
				"102400"));
		workerQueue = new ArrayBlockingQueue<Sending>(queuesize, true);
		loadSMSDataTable("data.dat", workerQueue);
		_logMessage = new LogMessageWorker(workerQueue);
		workerQueue.clear();

		try {
			Thread thread = new Thread(_logMessage);
			thread.start();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 1;
	}

	public void process(boolean out) {
		try {
			List<Sending> dataList = DBUtils.getData(telco);

			if (out) {
				System.out.println("dataList=" + dataList.size());
			}

			if (out || dataList.size() > 0) {
				logger.info("dataSize=" + dataList.size());
			}

			for (Iterator<Sending> iterator = dataList.iterator(); iterator
					.hasNext();) {

				Sending sending = (Sending) iterator.next();
				String rs = SendSMSClient.sendSMS(sending);
				// logger.info("rs=" + rs);
				if (out)
					System.out.println("rs=" + rs);

				if (rs != null) {
					sending.setNOTES(rs);
				}
				_logMessage.pushJob(sending);
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void run() {
		while (!stoped) {
			long start = System.currentTimeMillis();
			process(false);
			long duration = System.currentTimeMillis() - start;
			if (duration < sleep_inteval)
				try {
					Thread.sleep(sleep_inteval - duration);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
		}

	}

	public static void loadSMSDataTable(String fileName,
			ArrayBlockingQueue<Sending> workerqueue) {
		boolean flag = true;
		FileInputStream fin = null;
		ObjectInputStream objIn = null;
		FileOutputStream fout = null;
		ObjectOutputStream objOut = null;
		logger.info("loadSMSDataTable:" + fileName);
		long nummo = 0L;
		try {
			fin = new FileInputStream(fileName);
			objIn = new ObjectInputStream(fin);
			while (flag)
				try {
					Sending object = (Sending) objIn.readObject();
					workerqueue.put(object);
					nummo++;
				} catch (Exception ex) {
					flag = false;
					logger.error("readObject error: " + ex.getMessage());
				}
			if (nummo == 0L)
				logger.info(fileName + " is empty");
			else
				logger.info("Load data successful: " + nummo + " MT");
		} catch (IOException ex) {
			logger.error("Load data error: " + ex.getMessage());
		} finally {
			try {
				fin.close();
				fout = new FileOutputStream(fileName, false);
				fout.close();
				logger.info("Deleting.....: " + fileName);
			} catch (Exception exception1) {
			}
		}
		return;
	}

	public static void saveSMSDataTable(String fileName,
			ArrayBlockingQueue<Sending> queue) {
		logger.info("Saving " + fileName + " . . .");
		FileOutputStream fout = null;
		ObjectOutputStream objOut = null;
		long numqueue = 0L;
		try {
			fout = new FileOutputStream(fileName, false);
			objOut = new ObjectOutputStream(fout);
			for (Sending object : queue) {
				objOut.writeObject(object);
				objOut.flush();
				numqueue++;
			}

			logger.info("complete:" + numqueue);
		} catch (IOException ex) {
			logger.error("Save data error: " + ex.getMessage());
		} finally {
			try {
				objOut.close();
				fout.close();
			} catch (IOException ioexception) {
			}
		}
		return;
	}

	public void windowClosing() {
		int nCount = 0;
		stoped = true;
		_logMessage.stop();
		System.out.print("\nWaiting .....");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());
		}
		while (_logMessage.getQueue().size() > 0 && nCount < 5) {
			nCount++;
			try {
				System.out.println("...Queue(" + _logMessage.getQueue().size()
						+ ")");
				Thread.sleep(1000L);
			} catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
		}
		logger.info("Queue Size=" + _logMessage.getQueue().size());
		saveSMSDataTable("data.dat", _logMessage.getQueue());
		logger.info("Shutdown");
		System.out.println("\nExit");
	}

}
