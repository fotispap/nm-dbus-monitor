package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Addresses")
public class DAO_Adr_list {
	private String Address;
	private String Prefix;
	private String Gateway;
	
	public String getAddress() {
		return Address;
	}
	@XmlElement
	public void setAddress(String address) {
		Address = address;
	}
	public String getPrefix() {
		return Prefix;
	}
	@XmlElement
	public void setPrefix(String prefix) {
		Prefix = prefix;
	}
	public String getGateway() {
		return Gateway;
	}
	@XmlElement
	public void setGateway(String gateway) {
		Gateway = gateway;
	}
}
