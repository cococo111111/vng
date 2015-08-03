

//vng.wte.point:type=test
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import vng.wte.monitor.jmx.Config;
import vng.wte.monitor.jmx.JMXClient;

public class JMXClientConsole__ {
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
		//List<String> obj = new ArrayList<String>();
		Hashtable<String, String> htbBean = new Hashtable<String, String>();
		String nameCache = "";
		String key = "";
		htbBean = beanClient[pos].getBean(Config.bean.get(pos).getName());
		nameCache = Config.bean.get(pos).getName().substring(
				Config.bean.get(pos).getName().lastIndexOf("=") + 1);
		nameCache = nameCache + " (Host:" + host + ",Port:" + port + ")";
		List<String> att = htbAttrib.get(pos);
		System.out.println(nameCache);
		for (int j = 0; j < att.size(); j++) {
			key = att.get(j);			
			System.out.println(key + " = " + htbBean.get(key));
		}
		System.out.println("");
		return bd.toString();
	}

	public static void main(String[] args) {
		final boolean c2Enable, queueEnable, calcEnable, cacheEnable;
		final long delay = Config.DELAY;
		final StringBuilder bd = new StringBuilder();

		System.setProperty("config", "conf");

		beanClient = new JMXClient[Config.bean.size()];
		lstHashBean = new ArrayList<Hashtable<String, String>>();
		htbAttrib = new Hashtable<Integer, List<String>>();
		lstFormatOutput = new ArrayList<String>();
		lstEnable = new ArrayList<Boolean>();
		for (int i = 0; i < Config.bean.size(); i++) {
			// danh sach bean client
			//beanClient[i] = JMXClient.newinstance(Config.bean.get(i).getHost(),
					Config.bean.get(i).getPort();
			// danh sach thuoc tinh va value
			lstHashBean
					.add(beanClient[i].getBean(Config.bean.get(i).getName()));

			// danh sach thuoc tinh
			htbAttrib.put(i, beanClient[i].getAttrib(Config.bean.get(i)
					.getName()));

			// // lay format cua cac thuoc tinh
			// String t="";
			//	
			// lstFormatOutput
			// .add(beanClient[i].getFormatOutput
			// (beanClient[i].getAttrib(Config.bean.get(i).getName())));
			lstEnable.add(Config.bean.get(i).isEnable());
		}

		// for(int i=0;i<lstHashBean.size();i++)
		// //System.out.println(htbAttrib.get(i));
		// System.out.println(lstHashBean.get(i));

		// String key;
		// for(int i=0;i<lstHashBean.size();i++)
		// {
		// for(int j =0;j<htbAttrib.size();i++)
		// {
		// List<String> l= htbAttrib.get(i);
		// for(int m=0;m<l.size();m++)
		// {
		// key = l.get(m);
		// //lstHashBean.get(i).get(key);
		// System.out.println(l.get(m) + ": "+ lstHashBean.get(i).get(key));
		// }
		// }
		// //System.out.println("asdf\n\n");
		//			
		// }

		//		
		try {
			Thread thread = new Thread() {
				@Override
				public void run() {
					while (true) {

						for (int j = 0; j < Config.bean.size(); j++) {
							if (lstEnable.get(j) == true) {
								bd.append(OutputBean(Config.bean.get(j)
										.getHost(), Config.bean.get(j)
										.getPort(), j));
							}
						}

						System.out.print(bd.toString());
						bd.delete(0, bd.length());
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {

						}

					}

				}
			};
			thread.start();
		} catch (Exception e) {
		}
	}
}
