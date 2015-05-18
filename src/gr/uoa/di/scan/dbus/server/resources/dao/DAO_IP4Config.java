package gr.uoa.di.scan.dbus.server.resources.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_IP4Config {
	private List<DAO_Adr_list> Addresses;
	private List<DAO_Domain> Domains;
	private List<DAO_Nameserver> Nameservers;

	/*private List<String> Domains;
	private List<List<String>> Routes;*/
	//private List<String> Nameservers;
	
	public List<DAO_Domain> getDomains() {
		return Domains;
	}
	@XmlElement
	public void setDomains(List<DAO_Domain> domains) {
		Domains = domains;
	}
	public List<DAO_Nameserver> getNameservers() {
		return Nameservers;
	}
	@XmlElement
	public void setNameservers(List<DAO_Nameserver> nameservers) {
		Nameservers = nameservers;
	}
	public List<DAO_Adr_list> getAddresses() {
		return Addresses;
	}
	@XmlElement
	public void setAddresses(List<DAO_Adr_list> addresses) {
		Addresses = addresses;
	}/*
	public List<String> getDomains() {
		return Domains;
	}
	@XmlElement
	public void setDomains(List<String> domains) {
		Domains = domains;
	}
	public List<List<String>> getRoutes() {
		return Routes;
	}
	@XmlElement
	public void setRoutes(List<List<String>> routes) {
		Routes = routes;
	} 
	public List<String> getNameservers() {
		return Nameservers;
	}
	@XmlElement
	public void setNameservers(List<String> nameservers) {
		Nameservers = nameservers;
	}*/
	
}
