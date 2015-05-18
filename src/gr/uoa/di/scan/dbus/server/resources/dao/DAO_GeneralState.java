package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_GeneralState {
	private String NetworkingEnabled;
	private String WirelessEnabled;
	private String WirelessHardwareEnabled;
	private String WwanEnabled;
	private String WwanHardwareEnabled;
	private String WimaxEnabled;
	private String WimaxHardwareEnabled;
	public String getNetworkingEnabled() {
		return NetworkingEnabled;
	}
	@XmlElement
	public void setNetworkingEnabled(String networkingEnabled) {
		NetworkingEnabled = networkingEnabled;
	}
	public String getWirelessEnabled() {
		return WirelessEnabled;
	}
	@XmlElement
	public void setWirelessEnabled(String wirelessEnabled) {
		WirelessEnabled = wirelessEnabled;
	}
	public String getWirelessHardwareEnabled() {
		return WirelessHardwareEnabled;
	
	}
	@XmlElement
	public void setWirelessHardwareEnabled(String wirelessHardwareEnabled) {
		WirelessHardwareEnabled = wirelessHardwareEnabled;
	}
	public String getWwanEnabled() {
		return WwanEnabled;
	}
	@XmlElement
	public void setWwanEnabled(String wwanEnabled) {
		WwanEnabled = wwanEnabled;
	}
	public String getWwanHardwareEnabled() {
		return WwanHardwareEnabled;
	}
	@XmlElement
	public void setWwanHardwareEnabled(String wwanHardwareEnabled) {
		WwanHardwareEnabled = wwanHardwareEnabled;
	}
	public String getWimaxEnabled() {
		return WimaxEnabled;
	}
	@XmlElement
	public void setWimaxEnabled(String wimaxEnabled) {
		WimaxEnabled = wimaxEnabled;
	}
	public String getWimaxHardwareEnabled() {
		return WimaxHardwareEnabled;
	}
	@XmlElement
	public void setWimaxHardwareEnabled(String wimaxHardwareEnabled) {
		WimaxHardwareEnabled = wimaxHardwareEnabled;
	}
}
