package vng.zingme.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Configuration {
	public static Map<String, String> getConfiguration(String fileName)throws IOException, JDOMException{
		Map<String, String> cfg = new HashMap<String, String>();
		File f = new File(fileName);
	    SAXBuilder builder = new SAXBuilder();
	    Document doc = builder.build(f);
	    Element elements = doc.getRootElement();
	    Iterator<Element> iter = elements.getChildren("property").iterator(); 
	    while(iter.hasNext()){
	    	Element e =iter.next();
	    	String name = e.getAttributeValue("name");
	    	String value = e.getAttributeValue("value");
	    	cfg.put(name, value);
	    }
		return cfg;
	}
}
