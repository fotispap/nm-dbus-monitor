package gr.uoa.di.scan.dbus.server.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;

public class IP4Config {
	private List<List<String>> Addresses;
	private List<String> Nameservers;
	private List<String> Domains;
	private List<List<String>> Routes;
	
	public List<List<String>> getAddresses() {
		return Addresses;
	}
	public void setAddresses(List<List<String>> addresses) {
		Addresses = addresses;
	}
	public List<String> getNameservers() {
		return Nameservers;
	}
	public void setNameservers(List<String> nameservers) {
		Nameservers = nameservers;
	}
	public List<String> getDomains() {
		return Domains;
	}
	public void setDomains(List<String> domains) {
		Domains = domains;
	}
	public List<List<String>> getRoutes() {
		return Routes;
	}
	public void setRoutes(List<List<String>> routes) {
		Routes = routes;
	}
	
	public String ipToString(int ip)
	{ 
		
		String ipStr = 
		String.format("%d"+".%d"+".%d" + ".%d",
		         (ip & 0xff),   
		         (ip >> 8 & 0xff) ,           
		         (ip >> 16 & 0xff) ,    
		         (ip >> 24 & 0xff));
		return ipStr;
	}
	public void setProperties(Map<String, Variant> props_map) {
		Variant type;
		if(props_map.containsKey("Addresses")) {
			type = props_map.get("Addresses");
			List<List<UInt32>> temp_adr = new ArrayList<List<UInt32>>();
			temp_adr = (List<List<UInt32>>) type.getValue();
		//	System.out.print("Addresses: [ ");
			Addresses = new ArrayList<List<String>>();
			for(int k = 0; k< temp_adr.size();k++) {
				Addresses.add(new ArrayList<String>());
				for(int j=0; j < temp_adr.get(k).size(); j++) {
					Addresses.get(k).add(ipToString(temp_adr.get(k).get(j).intValue()));
				//	System.out.print(Addresses.get(k).get(j) + " ");

				}
				System.out.println("]");
			}
			
		}
		if(props_map.containsKey("Routes")) {
			type = props_map.get("Routes");
			List<List<UInt32>> temp_adr = new ArrayList<List<UInt32>>();
			temp_adr = (List<List<UInt32>>) type.getValue();
			Routes = new ArrayList<List<String>>();
		//	System.out.print("Routes: ");
			for(int k = 0; k< temp_adr.size();k++) {
				Routes.add(new ArrayList<String>());
				for(int j=0; j < temp_adr.get(k).size(); j++) {
					Routes.get(k).add(ipToString(temp_adr.get(k).get(j).intValue()));
			//		System.out.print(Routes.get(k).get(j));

				}
				
			}
	//		System.out.println("");
			
		}
		if(props_map.containsKey("Nameservers")) {
			type = props_map.get("Nameservers");
			List<UInt32> temp_adr = (List<UInt32>) type.getValue();
			Nameservers = new ArrayList<String>();
		//	System.out.println("Nameservers");
			for(int k = 0; k<temp_adr.size(); k++) {
				Nameservers.add(ipToString(temp_adr.get(k).intValue()));
		//		System.out.println(ipToString(temp_adr.get(k).intValue()));
			}
		}
		if(props_map.containsKey("Domains")) {
			type = props_map.get("Domains");
			List<String> temp_adr = (List<String>) type.getValue();
			Domains = new ArrayList<String>();
		//	System.out.println("Domains:");
			for(int k = 0; k<temp_adr.size(); k++) {
				Domains.add(temp_adr.get(k));
			//	System.out.println(temp_adr.get(k));
			}
		}
	}
	public void printProperties(){
	
	}
}
