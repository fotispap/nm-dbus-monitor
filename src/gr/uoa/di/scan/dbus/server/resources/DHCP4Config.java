package gr.uoa.di.scan.dbus.server.resources;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.freedesktop.dbus.*;

public class DHCP4Config {
	private String DomainName;
	private String Expiry;
	private String BroadCastAddress;
	private String DHCPMessageType;
	private String DHCPLeaseTime;
	private String IPAddress;
	private String Routers;
	private String SubnetMask;
	private String DomainNameServers;
	private String NetworkNumber;
	private String DHCPServerIdentifier;
	
	public String getDomainName() {
		return DomainName;
	}
	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	public String getExpiry() {
		return Expiry;
	}
	public void setExpiry(String expiry) {
		this.Expiry = expiry;
	}
	public String getBroadCastAddress() {
		return BroadCastAddress;
	}
	public void setBroadCastAddress(String broadCastAddress) {
		BroadCastAddress = broadCastAddress;
	}
	public String getDHCPMessageType() {
		return DHCPMessageType;
	}
	public void setDHCPMessageType(String dHCPMessageType) {
		DHCPMessageType = dHCPMessageType;
	}
	public String getDHCPLeaseTime() {
		return DHCPLeaseTime;
	}
	public void setDHCPLeaseTime(String dHCPLeaseTime) {
		DHCPLeaseTime = dHCPLeaseTime;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getRouters() {
		return Routers;
	}
	public void setRouters(String routers) {
		this.Routers = routers;
	}
	public String getSubnetMask() {
		return SubnetMask;
	}
	public void setSubnetMask(String subnetMask) {
		SubnetMask = subnetMask;
	}
	public String getDomainNameServers() {
		return DomainNameServers;
	}
	public void setDomainNameServers(String domainNameServers) {
		DomainNameServers = domainNameServers;
	}
	public String getNetworkNumber() {
		return NetworkNumber;
	}
	public void setNetworkNumber(String networkNumber) {
		NetworkNumber = networkNumber;
	}
	public String getDHCPServerIdentifier() {
		return DHCPServerIdentifier;
	}
	public void setDHCPServerIdentifier(String dHCPServerIdentifier) {
		DHCPServerIdentifier = dHCPServerIdentifier;
	}

	public void setProperties( Map<String,Variant> props) {
		Variant type = props.get("Options");
		Map<String,Variant> props2 = (Map<String,Variant>) type.getValue();
		Set<String> keySet = props2.keySet();
		Iterator<String> itr = keySet.iterator();
		while(itr.hasNext()) {
			String next = itr.next();
		//	System.out.println(next + ": " + props2.get(next));
			if(next.equals("dhcp_lease_time")) {
				type = props2.get(next);
				DHCPLeaseTime = (String) type.getValue();
			}
			if(next.equals("dhcp_message_type")) {
				type = props2.get(next);
				DHCPMessageType = (String) type.getValue();
			}
			if(next.equals("dhcp_server_identifier")) {
				type = props2.get(next);
				DHCPServerIdentifier = (String) type.getValue();
			}
			if(next.equals("domain_name")) {
				type = props2.get(next);
				DomainName = (String) type.getValue();
			}
			if(next.equals("expiry")) {
				type = props2.get(next);
				Expiry = (String) type.getValue();
			}
			if(next.equals("ip_address")) {
				type = props2.get(next);
				IPAddress = (String) type.getValue();
			}
			if(next.equals("network_number")) {
				type = props2.get(next);
				NetworkNumber = (String) type.getValue();
			}
			if(next.equals("routers")) {
				type = props2.get(next);
				Routers = (String) type.getValue();
			}
			if(next.equals("subnet_mask")) {
				type = props2.get(next);
				SubnetMask = (String) type.getValue();
			}
			if(next.equals("broadcast_address")) {
				type = props2.get(next);
				BroadCastAddress = (String) type.getValue();
			}
			if(next.equals("domain_name_servers")) {
				type = props2.get(next);
				DomainNameServers = (String) type.getValue();
			}
			
			
		}
		
	}
	public void printProperties() {
		System.out.println("");
	}
}
