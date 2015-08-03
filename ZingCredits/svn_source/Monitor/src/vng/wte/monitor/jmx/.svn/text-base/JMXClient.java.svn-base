package vng.wte.monitor.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.management.OperatingSystemMXBean;

public class JMXClient {
	MBeanServerConnection mbsc;

	public JMXClient(String host, int port) {
		try{
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			mbsc = jmxc.getMBeanServerConnection();
		}
		catch(Exception ex){
			
		}
	}

	public MBeanServerConnection getMbsc() {
		return mbsc;
	}

	public static String getFormatOutput(List<String> lst) {
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < lst.size(); i++)
			bd.append("%" + lst.get(i).length() + "s");
		return bd.toString();
	}

	public List<String> getAttrib(String name) {
		ObjectName objBeanName;
		List<String> lstAttrib = new ArrayList<String>();
		try {
			objBeanName = new ObjectName(Config.getFromName(name).getName());
			ObjectInstance objInstance = mbsc.getObjectInstance(objBeanName);
			MBeanInfo info = mbsc.getMBeanInfo(objBeanName);

			for (int i = 0; i < info.getAttributes().length; i++) {
				lstAttrib.add(info.getAttributes()[i].getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstAttrib;
	}

	public Hashtable<String, String> getBean(String name) {
		ObjectName objBeanName;
		Hashtable<String, String> htbBean = new Hashtable<String, String>();
		try {
			objBeanName = new ObjectName(Config.getFromName(name).getName());
			ObjectInstance objInstance = mbsc.getObjectInstance(objBeanName);
			MBeanInfo info = mbsc.getMBeanInfo(objBeanName);

			for (int i = 0; i < info.getAttributes().length; i++) {
				htbBean.put(info.getAttributes()[i].getName(), mbsc.getAttribute(objBeanName,
						info.getAttributes()[i].getName()).toString());
			}

			this.getExtendProperties(htbBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return htbBean;
	}

	public void getExtendProperties(Hashtable<String, String> htbBean) throws IOException {
		if (htbBean == null) {
			htbBean = new Hashtable<String, String>();
		}
		// memory
		MemoryMXBean memoryMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.MEMORY_MXBEAN_NAME,
				MemoryMXBean.class);
		htbBean.put("MU", convertUnittoMB(memoryMBean.getHeapMemoryUsage().getUsed(), ""));// memory
		// used
		htbBean.put("MM", convertUnittoMB(memoryMBean.getHeapMemoryUsage().getMax(), "MB"));// memory
		// max

		// thread
		ThreadMXBean threadMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.THREAD_MXBEAN_NAME,
				ThreadMXBean.class);
		htbBean.put("TL", threadMBean.getThreadCount() + "");// Thread Live
		htbBean.put("TT", threadMBean.getTotalStartedThreadCount() + "");// Thread
		// Total

		// cpu
		OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc,
				"java.lang:type=OperatingSystem", OperatingSystemMXBean.class);
		
		RuntimeMXBean runTimeMBean =  ManagementFactory.newPlatformMXBeanProxy(mbsc,
				ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
		
		
		if (cpuBefore == 0) {
			cpuBefore = osMBean.getProcessCpuTime();
			nanoBefore = runTimeMBean.getUptime();
		}
		int nCPUs = osMBean.getAvailableProcessors();
		
		long nanoAfter = runTimeMBean.getUptime();
		long cpuAfter = osMBean.getProcessCpuTime();
		
	    
	    double javacpu=0.00;
	    if (nanoBefore > 0L && nanoAfter > nanoBefore) {
	    	long elapsedCpu = cpuAfter - cpuBefore;
		    long elapsedTime = nanoAfter - nanoBefore;
		    
	    	javacpu= Math.min(99F, elapsedCpu / (elapsedTime * 10000F * nCPUs));
	    }
	    else{
	    	javacpu = 0.001;
	    }
	    DecimalFormat df = new DecimalFormat("0.0");
		htbBean.put("CPU", df.format(javacpu) + "%");// CPU Used percent
		
		cpuBefore = osMBean.getProcessCpuTime();
		nanoBefore = runTimeMBean.getUptime();
	}

	public static String printExtendProperties(Hashtable<String, String> htbBean, int decoratorsize) {
		String extendOutPut = ", Memory Used: " + htbBean.get("MU") + "/" + htbBean.get("MM") + ", Thread Live: "
				+ htbBean.get("TL") + "/" + htbBean.get("TT") + " ,CPU: " + htbBean.get("CPU") + " )\n";
		return extendOutPut + echo("=", decoratorsize + extendOutPut.length());
	}

	public static String echo(String msg, int n) {
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < n; i++)
			bd.append(msg);
		bd.append("\n");
		return bd.toString();
	}

	private long cpuBefore = 0l;
	private long nanoBefore = 0l;

	private static String convertUnittoMB(long value, String prefix) {
		DecimalFormat df = new DecimalFormat("#.0" + prefix);
		return value == 0 ? df.format(0.00) : df.format(value / 1000.0 / 1000.0);
	}

	public static void main(String[] args) {
		double d = 0.1;
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(d));
	}
}
