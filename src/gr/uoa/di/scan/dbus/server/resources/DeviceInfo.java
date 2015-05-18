package gr.uoa.di.scan.dbus.server.resources;

import gr.uoa.di.scan.dbus.server.resources.DHCP4Config;
import gr.uoa.di.scan.dbus.server.resources.IP4Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.freedesktop.DBus;
import org.freedesktop.Pair;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;



public class DeviceInfo {
	private String Udi;
	private String Interface;
	private String IpInterface;
	private String Driver;
	private String Ip4Address;
	private String State;
	private IP4Config Ip4Config;
	private DHCP4Config Dhcp4Config;
	private String DeviceType;
	
	public String getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}
	public String getUdi() {
		return Udi;
	}
	public void setUdi(String udi) {
		Udi = udi;
	}
	public String getInterface() {
		return Interface;
	}
	public void setInterface(String interface1) {
		Interface = interface1;
	}
	public String getIpInterface() {
		return IpInterface;
	}
	public void setIpInterface(String ipInterface) {
		IpInterface = ipInterface;
	}
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	public String getIp4Address() {
		return Ip4Address;
	}
	public void setIp4Address(String ip4Address) {
		Ip4Address = ip4Address;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public IP4Config getIp4Config() {
		return Ip4Config;
	}
	public void setIp4Config(IP4Config ip4Config) {
		Ip4Config = ip4Config;
	}
	public DHCP4Config getDhcp4Config() {
		return Dhcp4Config;
	}
	public void setDhcp4Config(DHCP4Config dhcp4Config) {
		Dhcp4Config = dhcp4Config;
	}
	public String ipToString(int ip)
	{ 
		
		String ipStr = 
		String.format("%d.%d.%d.%d",
		         (ip & 0xff),   
		         (ip >> 8 & 0xff),             
		         (ip >> 16 & 0xff),    
		         (ip >> 24 & 0xff));
		return ipStr;
	}
	public void setDeviceProps(Map<String,Variant> props_map) { 
		
	List<Path> AvailableConnections = new ArrayList<Path>();
	Variant type = props_map.get("Udi");
	Udi = (String) type.getValue();
	//System.out.println("@@@@@@@@@" + Udi);
	type = props_map.get("Interface");
	Interface = (String) type.getValue();
	type = props_map.get("IpInterface");
	IpInterface = (String) type.getValue();
	type = props_map.get("Driver");
	Driver = (String) type.getValue();
	type = props_map.get("Ip4Address");
	UInt32 Ip4Address1 = (UInt32) type.getValue();
	Ip4Address = ipToString(Ip4Address1.intValue());
	if(props_map.containsKey("State")) {
		type = props_map.get("State");
		UInt32 State_tmp = (UInt32) type.getValue();
		State = getStateString(State_tmp.intValue());
	}
	if(props_map.containsKey("Ip4Config")) {
		try {
			type = props_map.get("Ip4Config");
			Path Ip4Config_path = (Path) type.getValue();
			if(Ip4Config_path.getPath().length()>3) {
			DBusConnection conn = DBusConnection.getConnection(DBusConnection.SYSTEM);
			DBus.Properties props = conn.getRemoteObject("org.freedesktop.NetworkManager", Ip4Config_path.getPath(), DBus.Properties.class);
			Map<String,Variant> ip4c_props_map = props.GetAll("org.freedesktop.NetworkManager.IP4Config");
			Ip4Config = new IP4Config();
			Ip4Config.setProperties(ip4c_props_map);
			}
		} catch (DBusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	if(props_map.containsKey("Dhcp4Config")) {
		try {
			type = props_map.get("Dhcp4Config");
			Path Dhcp4Config_path = (Path) type.getValue();
			if(Dhcp4Config_path.getPath().length()>3) {
			DBusConnection conn = DBusConnection.getConnection(DBusConnection.SYSTEM);
			DBus.Properties props = conn.getRemoteObject("org.freedesktop.NetworkManager", Dhcp4Config_path.getPath(), DBus.Properties.class);
			Map<String,Variant> dhcp4c_props_map = props.GetAll("org.freedesktop.NetworkManager.DHCP4Config");
			Dhcp4Config = new DHCP4Config();
			Dhcp4Config.setProperties(dhcp4c_props_map);
			}
		} catch (DBusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	if(props_map.containsKey("DeviceType")) {
		type = props_map.get("DeviceType");
		UInt32 DeviceType = (UInt32) type.getValue();
	}
	/*
	if(props_map.containsKey("StateReason")) {
		type = props_map.get("StateReason");
		Object[] str = (Object[]) type.getValue();
		StateReason = new Pair<UInt32,UInt32>((UInt32) str[0], (UInt32) str[1]);
		//System.out.println(type.getSig());
		//System.out.println("sdds" + type.toString());
		//System.out.println("sdds" + type.getValue());
		//StateReason = type.getClass();
		//StateReason = (Pair<UInt32,UInt32>) type.getValue();
	}
	if(props_map.containsKey("ActiveConnection")) {
		type = props_map.get("ActiveConnection");
		ActiveConnection = (Path) type.getValue();
	}
	
	if(props_map.containsKey("Managed")) {
		type = props_map.get("Managed");
		Managed = (Boolean) type.getValue();
	}
	if(props_map.containsKey("DeviceType")) {
		type = props_map.get("DeviceType");
		DeviceType = (UInt32) type.getValue();
	}
	if(props_map.containsKey("AvailableConnections")) {
		type = props_map.get("AvailableConnections");
		AvailableConnections = (List<Path>) type.getValue();
	}
	if(props_map.containsKey("Capabilities")) {
		type = props_map.get("Capabilities");
		Capabilities = (UInt32) type.getValue();
	}*/
}
	public void printProperties() {
		System.out.println("Udi: "+ Udi);
		System.out.println("Interface: "+ Interface);
		System.out.println("IpInterface: "+ IpInterface);
		System.out.println("Driver: "+ Driver);
		System.out.println("IP: " + Ip4Address);
		System.out.println("State: " + State);
	}
	public String getDeviceTypeString(int type) {
		String dev_type = new String();
		System.out.println("TYPE: " + type);
		switch(type) {
		case 0: dev_type = "NM_DEVICE_TYPE_UNKNOWN"; break;
		case 1: dev_type = "ETHERNET"; break;
		case 2: dev_type = "WIFI"; break;
		case 7: dev_type = "NM_DEVICE_TYPE_WIMAX"; break;
		case 8: dev_type = "NM_DEVICE_TYPE_MODEM"; break;
		default: dev_type = "OTHER";
		}
		System.out.println("AAAAAAAAAAAAA:" + dev_type);
		return dev_type;
	}
	public String getStateString(int state) {
		String dev_state = new String();
		switch(state) {
		case 0: dev_state = "UNKNOWN"; break;
		case 10: dev_state = "UNMANAGED"; break;
		case 20: dev_state = "UNAIVAILABLE"; break;
		case 30: dev_state = "DISCONNECTED"; break;
		case 40: dev_state = "PREPARING"; break;
		case 50: dev_state = "CONFIGURING"; break;
		case 60: dev_state = "NEEDS_AUTH"; break;
		case 70: dev_state = "CONFIGURING IP"; break;
		case 80: dev_state = "CHECKING IP"; break;
		case 90: dev_state = "WAITING"; break;
		case 100: dev_state = "ACTIVATED"; break;
		case 110: dev_state = "DEACTIVATING"; break;
		case 120: dev_state = "FAILED"; break;
		default: break;
		
		
		}
		return dev_state;
		
	}
	
}
