package gr.uoa.di.scan.dbus.server.resources.methods;

import gr.uoa.di.scan.dbus.memory.GlobalMemory;
import gr.uoa.di.scan.dbus.server.resources.AccessPoint;
import gr.uoa.di.scan.dbus.server.resources.DHCP4Config;
import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.WiredDevice;
import gr.uoa.di.scan.dbus.server.resources.WirelessDevice;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_AccessPoint;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Adr_list;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Device;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_DeviceList;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Dhcp4Config;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Domain;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_GeneralState;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_IP4Config;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Nameserver;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_WiredDevice;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_WirelessDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




@Path("mainpath")
public class RestMethods {

	@GET
	@Path("Devices/")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public DAO_DeviceList get_string(){
		
		List<DAO_DeviceWrap> DevList = new ArrayList<DAO_DeviceWrap>();

		DAO_DeviceList dao_devlist = new DAO_DeviceList();
		
		
	
		GlobalMemory gm = GlobalMemory.getInstance();
		HashMap<String,DeviceWrap> hm = gm.getHm();
	
		Set<String> key_set = hm.keySet();
		Iterator<String> itr = key_set.iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			DAO_DeviceWrap dao_t_dev;
			DeviceWrap dev;
		//	dev = new WiredDevice();

			DAO_Device dao_dev = new DAO_Device();		
			dao_dev.setInterface(hm.get(key).getDevInfo().getInterface());
			dao_dev.setName(hm.get(key).getDevInfo().getInterface());
			dao_dev.setIpInterface(hm.get(key).getDevInfo().getIpInterface());
			dao_dev.setIp4Address(hm.get(key).getDevInfo().getIp4Address());
			dao_dev.setUdi(hm.get(key).getDevInfo().getUdi());
			dao_dev.setState(String.valueOf(hm.get(key).getDevInfo().getState()));
			dao_dev.setDriver(hm.get(key).getDevInfo().getDriver());
			dao_dev.setDeviceType(String.valueOf(hm.get(key).getDevInfo().getDeviceType()));
			System.out.println("DEVICE TYPE:" + dao_dev.getDeviceType());
			DAO_IP4Config dao_ipconf = new DAO_IP4Config();
			if(hm.get(key).getDevInfo().getIp4Config()!=null) {
				
				DAO_Adr_list dao_addr_list = new DAO_Adr_list(); 
				List<DAO_Adr_list> dao_addr_list_list = new ArrayList<DAO_Adr_list>();
				//List<List<String>> adr_list_list = new ArrayList<List<String>>();
				List<List<String>> adr_list_list = new ArrayList<List<String>>();
				adr_list_list = hm.get(key).getDevInfo().getIp4Config().getAddresses();
				for(int j = 0; j < adr_list_list.size(); j++) {
					dao_addr_list.setAddress(adr_list_list.get(j).get(0));
					dao_addr_list.setPrefix(adr_list_list.get(j).get(1));
					dao_addr_list.setGateway(adr_list_list.get(j).get(2));
					dao_addr_list_list.add(dao_addr_list);
				}
				
				dao_ipconf.setAddresses(dao_addr_list_list);
				
				List<String> domain_list = hm.get(key).getDevInfo().getIp4Config().getDomains();
				List<DAO_Domain> dao_dom_list = new ArrayList<DAO_Domain>();
				for(int j=0;j<domain_list.size();j++) {
					DAO_Domain dao_dom = new DAO_Domain();
					dao_dom.setDomain(domain_list.get(j));
					dao_dom_list.add(dao_dom);
				}
				dao_ipconf.setDomains(dao_dom_list);
				List<String> Nameserver_list = hm.get(key).getDevInfo().getIp4Config().getNameservers();
				List<DAO_Nameserver> dao_ns_list = new ArrayList<DAO_Nameserver>();
				for(int j=0;j<Nameserver_list.size();j++) {
					DAO_Nameserver dao_ns = new DAO_Nameserver();
					dao_ns.setNameserver(Nameserver_list.get(j));
					System.out.println(Nameserver_list.get(j));
					dao_ns_list.add(dao_ns);
				}
				dao_ipconf.setNameservers(dao_ns_list);
				//dao_ipconf.setNameservers(dao_ns_list);
			}
		//	dao_ipconf.setAddresses(hm.get(key).getDevInfo().getIp4Config().getAddresses());
		//	dao_ipconf.setDomains(hm.get(key).getDevInfo().getIp4Config().getDomains());
		//	dao_ipconf.setNameservers(hm.get(key).getDevInfo().getIp4Config().getNameservers());
		//	dao_ipconf.setRoutes(hm.get(key).getDevInfo().getIp4Config().getRoutes());

			dao_dev.setIp4config(dao_ipconf);
			
			DHCP4Config dhcp4 = new DHCP4Config();
			dhcp4 = hm.get(key).getDevInfo().getDhcp4Config();
			DAO_Dhcp4Config dao_dhcp4config = new DAO_Dhcp4Config();
			
			//dao_dhcp4config.setBroadCastAddress(dhcp4.getBroadCastAddress());
			if(hm.get(key).getDevInfo().getDhcp4Config()!=null) {
				dao_dhcp4config.setSubnetMask(dhcp4.getSubnetMask());
				dao_dhcp4config.setDHCPLeaseTime(dhcp4.getDHCPLeaseTime());
				dao_dhcp4config.setBroadCastAddress(dhcp4.getBroadCastAddress());

				dao_dhcp4config.setDHCPMessageType(dhcp4.getDHCPMessageType());
				dao_dhcp4config.setDHCPServerIdentifier(dhcp4.getDHCPServerIdentifier());
				dao_dhcp4config.setDomainName(dhcp4.getDomainName());
				dao_dhcp4config.setDomainNameServers(dhcp4.getDomainNameServers());
				dao_dhcp4config.setExpiry(dhcp4.getExpiry());
				dao_dhcp4config.setIPAddress(dhcp4.getIPAddress());
				dao_dhcp4config.setNetworkNumber(dhcp4.getNetworkNumber());
				dao_dhcp4config.setRouters(dhcp4.getRouters());
				dao_dhcp4config.setSubnetMask(dhcp4.getSubnetMask());
				dao_dev.setDhcp4config(dao_dhcp4config);
			}
			
			if(hm.get(key).getDevInfo().getDeviceType().equals("ETHERNET")) {
				dao_t_dev = new DAO_WiredDevice();
				dev = new WiredDevice();
				dao_t_dev = new DAO_WiredDevice();
				dev = (WiredDevice) hm.get(key);
				((DAO_WiredDevice) dao_t_dev).setHwAddress(((WiredDevice) dev).getHwAddress());
				((DAO_WiredDevice) dao_t_dev).setPermHwAddress(((WiredDevice) dev).getPermHwAddress());
				((DAO_WiredDevice) dao_t_dev).setSpeed(String.valueOf(((WiredDevice) dev).getSpeed()));			
				((DAO_WiredDevice) dao_t_dev).setDevInfo(dao_dev);
				DevList.add(dao_t_dev);
			} else {
				dao_t_dev = new DAO_WirelessDevice();
				dev = new WirelessDevice();
				dev = (WirelessDevice) hm.get(key);
				((DAO_WirelessDevice) dao_t_dev).setHwAddress(((WirelessDevice) dev).getHwAddress());
				((DAO_WirelessDevice) dao_t_dev).setPermHwAddress(((WirelessDevice) dev).getPermHwAddress());
				((DAO_WirelessDevice) dao_t_dev).setBitrate((String.valueOf(((WirelessDevice) dev).getBitrate())));	
				((DAO_WirelessDevice) dao_t_dev).setMode((String.valueOf(((WirelessDevice) dev).getMode())));	

				((DAO_WirelessDevice) dao_t_dev).setWirelessCapabilities((String.valueOf(((WirelessDevice) dev).getWirelessCapabilities())));
				((DAO_WirelessDevice) dao_t_dev).setDevInfo(dao_dev);
				if(((WirelessDevice) dev).getActiveAccessPoint()!=null) {
					AccessPoint ap = new AccessPoint();
					ap = ((WirelessDevice) dev).getActiveAccessPoint();
					DAO_AccessPoint dao_ap = new DAO_AccessPoint();
					dao_ap.setFlags(String.valueOf(ap.getFlags()));
					dao_ap.setFrequency(String.valueOf(ap.getFrequency()));
					dao_ap.setMaxBitrate(String.valueOf(ap.getMaxBitrate()));
					String correct = new String(ap.getSsid());

					dao_ap.setSsid(correct);
					dao_ap.setStrength(String.valueOf(ap.getStrength()));
					dao_ap.setHwAddress(ap.getHwAddress());
					dao_ap.setWpaFlags(String.valueOf(ap.getWpaFlags()));
					dao_ap.setRsnFlags(String.valueOf(ap.getRsnFlags()));
					dao_ap.setMode(String.valueOf(ap.getMode()));
					System.out.println("------------------------------");
					((DAO_WirelessDevice) dao_t_dev).setActiveAccessPoint(dao_ap);
				}
				DevList.add(dao_t_dev);
				
			}
			
		

		}
		dao_devlist.setDeviceList(DevList);
		dao_devlist.setDeviceNo(String.valueOf(key_set.size()));
		/*Set<String> key_set = hm.keySet();
		
		}*/
		for(int i=0;i<1000;i++) System.out.println("HAHAHAHOUXHA");

		return dao_devlist;
	
	}
	@GET
	@Path("General/")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public DAO_GeneralState get_state(){
		for(int i=0;i<1000;i++) System.out.println("HAHAHAHOUXHA");
		return GlobalMemory.getInstance().getGeneral();
	}

}
