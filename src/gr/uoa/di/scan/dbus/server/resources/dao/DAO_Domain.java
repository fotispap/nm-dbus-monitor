package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_Domain {
	private String domain;

	public String getDomain() {
		return domain;
	}
	@XmlElement
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
