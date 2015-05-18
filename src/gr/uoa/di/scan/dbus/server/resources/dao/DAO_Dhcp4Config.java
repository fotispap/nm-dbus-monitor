package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_Dhcp4Config {
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
	@XmlElement
	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	public String getExpiry() {
		return Expiry;
	}
	@XmlElement
	public void setExpiry(String expiry) {
		this.Expiry = expiry;
	}
	public String getBroadCastAddress() {
		return BroadCastAddress;
	}
	@XmlElement
	public void setBroadCastAddress(String broadCastAddress) {
		BroadCastAddress = broadCastAddress;
	}
	public String getDHCPMessageType() {
		return DHCPMessageType;
	}
	@XmlElement
	public void setDHCPMessageType(String dHCPMessageType) {
		DHCPMessageType = dHCPMessageType;
	}
	public String getDHCPLeaseTime() {
		return DHCPLeaseTime;
	}
	@XmlElement
	public void setDHCPLeaseTime(String dHCPLeaseTime) {
		DHCPLeaseTime = dHCPLeaseTime;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	@XmlElement
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getRouters() {
		return Routers;
	}
	@XmlElement
	public void setRouters(String routers) {
		this.Routers = routers;
	}
	public String getSubnetMask() {
		return SubnetMask;
	}
	@XmlElement
	public void setSubnetMask(String subnetMask) {
		SubnetMask = subnetMask;
	}
	public String getDomainNameServers() {
		return DomainNameServers;
	}
	@XmlElement
	public void setDomainNameServers(String domainNameServers) {
		DomainNameServers = domainNameServers;
	}
	public String getNetworkNumber() {
		return NetworkNumber;
	}
	@XmlElement
	public void setNetworkNumber(String networkNumber) {
		NetworkNumber = networkNumber;
	}
	public String getDHCPServerIdentifier() {
		return DHCPServerIdentifier;
	}
	@XmlElement
	public void setDHCPServerIdentifier(String dHCPServerIdentifier) {
		DHCPServerIdentifier = dHCPServerIdentifier;
	}

}
