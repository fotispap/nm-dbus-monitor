package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({gr.uoa.di.scan.dbus.server.resources.dao.DAO_WirelessDevice.class, gr.uoa.di.scan.dbus.server.resources.dao.DAO_WiredDevice.class})
public abstract class DAO_DeviceWrap {
	private DAO_Device DevInfo;

	public DAO_Device getDevInfo() {
		return DevInfo;
	}
	@XmlElement
	public void setDevInfo(DAO_Device devInfo) {
		DevInfo = devInfo;
	} 
}
