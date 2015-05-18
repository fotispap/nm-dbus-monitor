package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Device")
public class DAO_Device {
	private String Udi;
	private String Interface;
	private String IpInterface;
	private String Driver;
	private String Ip4Address;
	private String State;
	private DAO_IP4Config ip4config;
	private DAO_Dhcp4Config dhcp4config;
	private String Name;
	private String DeviceType;
	
	
	public String getDeviceType() {
		return DeviceType;
	}
	@XmlAttribute
	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}
	public DAO_Dhcp4Config getDhcp4config() {
		return dhcp4config;
	}
	@XmlElement
	public void setDhcp4config(DAO_Dhcp4Config dhcp4config) {
		this.dhcp4config = dhcp4config;
	}
	
	@XmlElement
	public DAO_IP4Config getIp4config() {
		return ip4config;
	}
	public void setIp4config(DAO_IP4Config ip4config) {
		this.ip4config = ip4config;
	}
	public String getUdi() {
		return Udi;
	}
	@XmlElement
	public void setUdi(String udi) {
		Udi = udi;
	}
	public String getInterface() {
		return Interface;
	}
	@XmlElement
	public void setInterface(String interface1) {
		Interface = interface1;
	}
	public String getIpInterface() {
		return IpInterface;
	}
	@XmlElement
	public void setIpInterface(String ipInterface) {
		IpInterface = ipInterface;
	}
	public String getDriver() {
		return Driver;
	}
	@XmlElement
	public void setDriver(String driver) {
		Driver = driver;
	}
	public String getIp4Address() {
		return Ip4Address;
	}
	@XmlElement
	public void setIp4Address(String ip4Address) {
		Ip4Address = ip4Address;
	}
	public String getState() {
		return State;
	}
	@XmlElement
	public void setState(String state) {
		State = state;
	}
	public String getName() {
		return Name;
	}
	@XmlAttribute
	public void setName(String name) {
		Name = name;
	}
}
