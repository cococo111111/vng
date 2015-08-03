import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import vng.wte.monitor.jmx.Config;
import vng.wte.monitor.jmx.JMXClient;

public class JMXClientTableConsole {
	static JMXClient[] beanClient;
	static List<Hashtable<String, String>> lstHashBean;
	static Hashtable<Integer, List<String>> htbAttrib;
	static Hashtable<Integer, Boolean> htbEnable;
	static final int TITLE_LEN = 5;
	static final int MAX_LENGHT_SCREEN = 100;
	static int pos = 0;
	static Hashtable<Integer, Integer> htbMaxLenghtAtr;
	static Hashtable<Integer, String> nameCache;
	static StringBuilder bd;
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

	public static String printToConsole(List<String> lstTitle,
			List<String> lstValue) {

		StringBuilder bdTitle = new StringBuilder();
		StringBuilder bdValue = new StringBuilder();
		bdTitle.append("|");
		bdValue.append("|");
		for (int i = 0; i < lstTitle.size(); i++) {
			bdTitle.append(String.format("%" + htbMaxLenghtAtr.get(pos) + "s|",
					lstTitle.get(i)));
			bdValue.append(String.format("%" + htbMaxLenghtAtr.get(pos) + "s|",
					lstValue.get(i)));
		}
		bdTitle.append("\n");
		bdTitle.append(bdValue.toString());
		bdTitle.append("\n");
		return bdTitle.toString();
	}

	private static String printByFullConsole(int max, List<String> titles,
			List<String> values) {
		int total = 0;
		StringBuffer result = new StringBuffer();
		List<String> subTitles = new ArrayList<String>();
		List<String> subValues = new ArrayList<String>();
		int index = 0;
		for (String title : titles) {
			if (htbMaxLenghtAtr.get(pos) + total > max) {
				result.append(printToConsole(subTitles, subValues));
				subTitles = new ArrayList<String>();
				subValues = new ArrayList<String>();
				total = 0;
			}
			subTitles.add(title);
			subValues.add(values.get(index));
			index++;
			total += htbMaxLenghtAtr.get(pos);
		}
		if (subTitles.size() > 0) {
			result.append(printToConsole(subTitles, subValues));
		}
		return result.toString();
	}

	public static String OutputBean(String host, int port, int pos,int decoratorsize) {

		Hashtable<String, String> htbBeanKeyValue = new Hashtable<String, String>();
		htbBeanKeyValue = beanClient[pos].getBean(Config.bean.get(pos)
				.getName());
		List<String> lstTitle, lstValue;
		lstTitle = new ArrayList<String>();
		lstValue = new ArrayList<String>();
		lstTitle = htbAttrib.get(pos);
		for (int i = 0; i < lstTitle.size(); i++)
			lstValue.add(htbBeanKeyValue.get(lstTitle.get(i)));
		//make print extend properties
		StringBuffer buffer = new StringBuffer();
		buffer.append(JMXClient.printExtendProperties(htbBeanKeyValue,decoratorsize));
		
		buffer.append(printByFullConsole(MAX_LENGHT_SCREEN, lstTitle, lstValue));
		return buffer.toString();
	}

	public static String getFormat(List<String> lstStr) {
		StringBuilder bd = new StringBuilder();
		String tmp = "";
		for (int j = 0; j < lstStr.size(); j++) {
			tmp = String.format("%" + lstStr.get(j).length() + "s", lstStr
					.get(j)
					+ "|");
			bd.append(tmp);
		}
		return bd.toString();
	}

	public static String formatString(String strFormat, List<String> lstStr) {

		StringBuilder bd = new StringBuilder();
		String[] arr = strFormat.split("s");
		String tmp = "";
		for (int i = 0; i < arr.length; i++) {
			tmp = String.format(arr[i] + "s", lstStr.get(i));
			bd.append(tmp + "|");
		}
		return bd.toString();
	}

	public static String formatTitle(String str, int len) {
		return String.format("%" + len + "s|", str);
	}

	public static int finMaxLenght(List<String> lstStr) {
		int maxLenght_ = lstStr.get(0).length();
		for (int i = 1; i < lstStr.size(); i++)
			if (lstStr.get(i).length() > maxLenght_)
				maxLenght_ = lstStr.get(i).length();
		return maxLenght_;
	}

	public static void gotoxy(int x, int y) {
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df", escCode, y, x));
	}

	public static void main(String[] args) {
		final long delay = Config.DELAY;
		bd = new StringBuilder();
		//System.setProperty("config", "conf");
		beanClient = new JMXClient[Config.bean.size()];
		lstHashBean = new ArrayList<Hashtable<String, String>>();
		htbAttrib = new Hashtable<Integer, List<String>>();
		htbEnable = new Hashtable<Integer, Boolean>();
		htbMaxLenghtAtr = new Hashtable<Integer, Integer>();
		nameCache = new Hashtable<Integer, String>();
		for (int i = 0; i < Config.bean.size(); i++) {
			beanClient[i] = new JMXClient(Config.bean.get(i).getHost(),
					Config.bean.get(i).getPort());
			htbAttrib.put(i, beanClient[i].getAttrib(Config.bean.get(i)
					.getName()));
			htbEnable.put(i, (Config.bean.get(i).isEnable()));
			htbMaxLenghtAtr.put(i, (finMaxLenght(htbAttrib.get(i))));
			nameCache.put(i, Config.bean.get(i).getName().substring(
					Config.bean.get(i).getName().lastIndexOf("=") + 1)
					+ "(Host:"
					+ Config.bean.get(i).getHost()
					+ ", Port: "
					+ Config.bean.get(i).getPort() 
					);
		}
		try {
			Thread thread = new Thread() {
				@Override
				public void run() {
					while (true) {
						for (int j = 0; j < Config.bean.size(); j++) {
							if (htbEnable.get(j) == true) {
								pos = j;
								bd.append("\n*" + nameCache.get(j));
								bd.append(OutputBean(Config.bean.get(j)
										.getHost(), Config.bean.get(j)
										.getPort(), j,nameCache.get(j).length()));
							}
						}
						System.out.print(bd.toString());
						bd= new StringBuilder();						
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