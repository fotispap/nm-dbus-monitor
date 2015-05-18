package gr.uoa.di.scan.dbus.server.resources.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="AvailableDevices")
public class DAO_DeviceList {
	private String deviceNo;
	private List<DAO_DeviceWrap> DeviceList;
	
	
	public String getDeviceNo() {
		return deviceNo;
	}
	@XmlAttribute
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public List<DAO_DeviceWrap> getDeviceList() {
		return DeviceList;
	}
	@XmlElement(name="Device")
	public void setDeviceList(List<DAO_DeviceWrap> deviceList) {
		DeviceList = deviceList;
	}
	
}
