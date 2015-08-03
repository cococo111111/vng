package vng.wte.monitor.jmx;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class Config {
	static CompositeConfiguration config;
	public static int DELAY;
	public static List<clsBean> bean = new ArrayList<clsBean>();	
	static {

		String file = System.getProperty("config");		
		file += File.separator + "config.xml";
		config = new CompositeConfiguration();
		try {
			XMLConfiguration config = new XMLConfiguration(file);
			DELAY = config.getInt("delay");			
			Object prop = config.getProperty("bean.name");
			if (prop instanceof Collection) {
				for (int i = 0; i < ((Collection) prop).size(); i++) {
					clsBean obj = new clsBean();
					obj.setName(config.getString("bean(" + i + ").name"));
					obj.setHost(config.getString("bean(" + i + ").host"));
					obj.setPort(config.getInt("bean(" + i + ").port"));
					obj.setEnable(config.getBoolean("bean(" + i + ").enable"));
					bean.add(obj);
				}
			}
			if (prop instanceof String) {
				clsBean obj = new clsBean();
				obj.setName(config.getString("bean.name"));
				obj.setHost(config.getString("bean.host"));
				obj.setPort(config.getInt("bean.port"));
				obj.setEnable(config.getBoolean("bean.enable"));
				bean.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static clsBean getFromName(String name){
		for(int i=0;i<bean.size();i++)
			if(bean.get(i).getName().toLowerCase().compareTo(name.toLowerCase())==0)
				return bean.get(i);	
		return null;
	}
}
