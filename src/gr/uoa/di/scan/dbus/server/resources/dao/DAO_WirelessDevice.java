package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_WirelessDevice extends DAO_DeviceWrap {
	private String HwAddress;
	private String PermHwAddress;
	private String Mode;
	private String Bitrate;
	private DAO_Device DevInfo;
	private DAO_AccessPoint ActiveAccessPoint;
	private String WirelessCapabilities;
	
	public String getHwAddress() {
		return HwAddress;
	}
	@XmlElement
	public void setHwAddress(String hwAddress) {
		HwAddress = hwAddress;
	}
	public String getPermHwAddress() {
		return PermHwAddress;
	}
	@XmlElement
	public void setPermHwAddress(String permHwAddress) {
		PermHwAddress = permHwAddress;
	}
	public String getMode() {
		return Mode;
	}
	@XmlElement
	public void setMode(String mode) {
		Mode = mode;
	}
	public String getBitrate() {
		return Bitrate;
	}
	
	@XmlElement
	public void setBitrate(String bitrate) {
		Bitrate = bitrate;
	}
	public DAO_AccessPoint getActiveAccessPoint() {
		return ActiveAccessPoint;
	}
	@XmlElement
	public void setActiveAccessPoint(DAO_AccessPoint activeAccessPoint) {
		ActiveAccessPoint = activeAccessPoint;
	}
	public String getWirelessCapabilities() {
		return WirelessCapabilities;
	}
	@XmlElement
	public void setWirelessCapabilities(String wirelessCapabilities) {
		WirelessCapabilities = wirelessCapabilities;
	}
	public DAO_Device getDevInfo() {
		return DevInfo;
	}
	@XmlElement		
	public void setDevInfo(DAO_Device devInfo) {
		DevInfo = devInfo;
	}
}
