package gr.uoa.di.scan.dbus.server.resources;

import gr.uoa.di.scan.dbus.server.resources.DeviceInfo;

import java.util.Map;

import org.freedesktop.dbus.Variant;


public abstract class DeviceWrap {
	private DeviceInfo DevInfo;

	public DeviceInfo getDevInfo() {
		return DevInfo;
	}

	public void setDevInfo(DeviceInfo devInfo) {
		DevInfo = devInfo;
	}
	public abstract void setDevTypeInfo(Map<String,Variant> props);
	public abstract void printProperties();
	public DeviceWrap() {
		DevInfo = new DeviceInfo();
	}
	
}
