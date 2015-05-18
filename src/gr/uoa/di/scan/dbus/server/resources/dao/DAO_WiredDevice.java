package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_WiredDevice extends DAO_DeviceWrap {
	private String HwAddress;
	private String PermHwAddress;
	private String Speed;
	private String Carrier;
	private DAO_Device DevInfo;
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
	public String getSpeed() {
		return Speed;
	}
	@XmlElement
	public void setSpeed(String speed) {
		Speed = speed;
	}
	public String getCarrier() {
		return Carrier;
	}
	@XmlElement
	public void setCarrier(String carrier) {
		Carrier = carrier;
	}
	public DAO_Device getDevInfo() {
		return DevInfo;
	}
	@XmlElement
	public void setDevInfo(DAO_Device devInfo) {
		DevInfo = devInfo;
	}
}
