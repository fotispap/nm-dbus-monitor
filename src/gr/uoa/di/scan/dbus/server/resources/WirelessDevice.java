package gr.uoa.di.scan.dbus.server.resources;

import gr.uoa.di.scan.dbus.server.resources.AccessPoint;
import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;

import java.util.Map;

import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;


public class WirelessDevice extends DeviceWrap {
	private String HwAddress;
	private String PermHwAddress;
	private int Mode;
	private int Bitrate;
	private AccessPoint ActiveAccessPoint;
	private int WirelessCapabilities;
	public String getHwAddress() {
		return HwAddress;
	}
	public void setHwAddress(String hwAddress) {
		HwAddress = hwAddress;
	}
	public String getPermHwAddress() {
		return PermHwAddress;
	}
	public void setPermHwAddress(String permHwAddress) {
		PermHwAddress = permHwAddress;
	}
	public int getMode() {
		return Mode;
	}
	public void setMode(int mode) {
		Mode = mode;
	}
	public int getBitrate() {
		return Bitrate;
	}
	public void setBitrate(int bitrate) {
		Bitrate = bitrate;
	}
	public AccessPoint getActiveAccessPoint() {
		return ActiveAccessPoint;
	}
	public void setActiveAccessPoint(AccessPoint activeAccessPoint) {
		ActiveAccessPoint = activeAccessPoint;
	}
	public int getWirelessCapabilities() {
		return WirelessCapabilities;
	}
	public void setWirelessCapabilities(int wirelessCapabilities) {
		WirelessCapabilities = wirelessCapabilities;
	}
	public void setDevTypeInfo(Map<String,Variant> props){
		Variant type = props.get("HwAddress");
		HwAddress = (String) type.getValue();
		type = props.get("PermHwAddress");
		PermHwAddress = (String) type.getValue();
		type = props.get("Bitrate");
		UInt32 temp = (UInt32) type.getValue(); 
		Bitrate = temp.intValue();
		type = props.get("Mode");
		temp = (UInt32) type.getValue(); 
		Mode = temp.intValue();
		type = props.get("WirelessCapabilities");
		temp = (UInt32) type.getValue(); 
		WirelessCapabilities = temp.intValue();
		System.out.println("SSSSSSSSSSSSSSSSSSSSS-->" + WirelessCapabilities);
		type = props.get("ActiveAccessPoint");
		Path AAP = (Path) type.getValue();
		DBusConnection conn;
		try {
			conn = DBusConnection.getConnection(DBusConnection.SYSTEM);
		if(AAP.getPath().length()>3) {
		DBus.Properties ap_props = conn.getRemoteObject("org.freedesktop.NetworkManager", AAP.getPath(), DBus.Properties.class);
		Map<String,Variant> ap_props_map = ap_props.GetAll("org.freedesktop.NetworkManager.AccessPoint");
		ActiveAccessPoint = new AccessPoint();
		ActiveAccessPoint.setProperties(ap_props_map);
		}
	//	System.out.println(ap_props_map);
		} catch (DBusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printProperties() {
		System.out.println("HwAddress: " + HwAddress);
		System.out.println("PermHwAddress: " + PermHwAddress);
		System.out.println("Bitrate: " + Bitrate);
		System.out.println("Mode: " + Mode);
		System.out.println("WirelessCapabilities: " + WirelessCapabilities);



	}
	
}
