package gr.uoa.di.scan.dbus.server.resources;

import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;

import java.util.Map;

import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;


public class WiredDevice extends DeviceWrap{
	private String HwAddress;
	private String PermHwAddress;
	private int Speed;
	private boolean Carrier;
	
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
	public int getSpeed() {
		return Speed;
	}
	public void setSpeed(int speed) {
		Speed = speed;
	}
	public boolean isCarrier() {
		return Carrier;
	}
	public void setCarrier(boolean carrier) {
		Carrier = carrier;
	}
	public void setDevTypeInfo(Map<String,Variant> props){
		Variant type = props.get("HwAddress");
		HwAddress = (String) type.getValue();
		type = props.get("PermHwAddress");
		PermHwAddress = (String) type.getValue();
		type = props.get("Speed");
		UInt32 Speed_temp = (UInt32) type.getValue();
		Speed = Speed_temp.intValue();
		type = props.get("Carrier");
		Carrier = (Boolean) type.getValue();
		
	}
	public void printProperties() {
		System.out.println("HwAddress: " + HwAddress);
		System.out.println("PermHwAddress: " + PermHwAddress);
		System.out.println("Speed: " + Speed);
		System.out.println("Carrier: " + Carrier);
		}
}
