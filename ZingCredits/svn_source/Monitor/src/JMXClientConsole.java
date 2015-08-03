import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import vng.wte.monitor.jmx.Config;
import vng.wte.monitor.jmx.JMXClient;

public class JMXClientConsole {
	static JMXClient[] beanClient;
	static List<Hashtable<String, String>> lstHashBean;
	static Hashtable<Integer, List<String>> htbAttrib;
	static List<String> lstFormatOutput;
	static List<Boolean> lstEnable;
	static int pos = 0;
	static int NEW_LINE = 6;

	public static String echo(String msg) {
		StringBuilder bd = new StringBuilder();
		bd.append(msg + "\n");
		return bd.toString();
	}

	public static String echo(String msg, int n) {
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < n; i++)
			bd.append(msg);
		bd.append("\n");
		return bd.toString();
	}

	public static String OutputBean(String host, int port, int pos) {
		StringBuilder bd = new StringBuilder();
		// List<String> obj = new ArrayList<String>();
		Hashtable<String, String> htbBean = new Hashtable<String, String>();
		String nameCache = "";
		String key = "";
		htbBean = beanClient[pos].getBean(Config.bean.get(pos).getName());
		nameCache = Config.bean.get(pos).getName().substring(
				Config.bean.get(pos).getName().lastIndexOf("=") + 1);
		nameCache = nameCache + " (Host:" + host + ",Port:" + port + ")";
		List<String> att = htbAttrib.get(pos);
		//System.out.println(nameCache);
		bd.append(nameCache + "\n");
		for (int j = 0; j < att.size(); j++) {
			key = att.get(j);
			//System.out.println(key + " = " + htbBean.get(key));
			bd.append(key + " = " + htbBean.get(key) + "\n");
		}
		bd.append("\n");
		//System.out.println("");

		return bd.toString();
	}

	public static void main(String[] args) {
		final long delay = Config.DELAY;
		StringBuilder bd = new StringBuilder();

		beanClient = new JMXClient[Config.bean.size()];
		lstHashBean = new ArrayList<Hashtable<String, String>>();
		htbAttrib = new Hashtable<Integer, List<String>>();
		lstFormatOutput = new ArrayList<String>();
		lstEnable = new ArrayList<Boolean>();
		for (int i = 0; i < Config.bean.size(); i++) {
			// danh sach bean client
			beanClient[i] = new JMXClient(Config.bean.get(i).getHost(),
					Config.bean.get(i).getPort());
			// danh sach thuoc tinh va value
			lstHashBean
					.add(beanClient[i].getBean(Config.bean.get(i).getName()));

			// danh sach thuoc tinh
			htbAttrib.put(i, beanClient[i].getAttrib(Config.bean.get(i)
					.getName()));

			lstEnable.add(Config.bean.get(i).isEnable());
		}

		try {
			while (true) {
				for (int j = 0; j < Config.bean.size(); j++) {
					if (lstEnable.get(j) == true) {
						bd.append(OutputBean(Config.bean.get(j).getHost(),
								Config.bean.get(j).getPort(), j));
					}
				}

				System.out.print(bd.toString());
				//bd.delete(0, bd.length());
				bd = new StringBuilder();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();	
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
