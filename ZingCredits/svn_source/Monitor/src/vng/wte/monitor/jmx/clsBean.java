package vng.wte.monitor.jmx;

import java.util.ArrayList;
import java.util.List;

public class clsBean {
	private String name;
	private String host;
	private int port;
	private boolean enable;
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
		
	public clsBean(){
		this.name="";
		this.host="";
		this.port=0;		
	}
}
