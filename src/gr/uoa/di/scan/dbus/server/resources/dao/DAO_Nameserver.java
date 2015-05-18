package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_Nameserver {
	private String Nameserver;

	public String getNameserver() {
		return Nameserver;
	}
	@XmlElement
	public void setNameserver(String Nameserver) {
		this.Nameserver = Nameserver;
	}
}
