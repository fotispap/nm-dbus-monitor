package gr.uoa.di.scan.dbus.memory;

import globalmemory.GlobalMemory;
import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_GeneralState;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;





public class GlobalMemory {
	private String test;
	private HashMap<String,DeviceWrap> hm;
	private DAO_GeneralState General;
	
	public DAO_GeneralState getGeneral() {
		return General;
	}
	public void setGeneral(DAO_GeneralState general) {
		General = general;
	}
	public HashMap<String, DeviceWrap> getHm() {
		return hm;
	}
	public void setHm(HashMap<String, DeviceWrap> hm) {
		this.hm = hm;
	}
	public String getTest() {
		return test;
	}
	public void setTest() {
		
	}
	private static GlobalMemory instance = null;
	protected GlobalMemory() {
		hm = new HashMap<String,DeviceWrap>();
		
	      // Exists only to defeat instantiation.
	   }
	public static synchronized GlobalMemory getInstance() {
	     if(instance == null) {
	        instance = new GlobalMemory();
	     }
	     return instance;
	  }
	public void printHm() {
		Set<String> key_set = hm.keySet();
		Iterator<String> itr = key_set.iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			System.out.println("Device: "+ key);
			hm.get(key).getDevInfo().printProperties();
			hm.get(key).printProperties();
		}
	}
}