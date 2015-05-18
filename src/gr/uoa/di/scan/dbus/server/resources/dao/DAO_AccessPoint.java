package gr.uoa.di.scan.dbus.server.resources.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DAO_AccessPoint {
	private String Flags;
	private String WpaFlags;
	private String RsnFlags;
	private String Ssid;
	private String Frequency;
	private String HwAddress;
	private String Mode;
	private String MaxBitrate;
	private String Strength;
	public String getFlags() {
		return Flags;
	}
	@XmlElement
	public void setFlags(String flags) {
		Flags = flags;
	}
	public String getWpaFlags() {
		return WpaFlags;
	}
	@XmlElement
	public void setWpaFlags(String wpaFlags) {
		WpaFlags = wpaFlags;
	}
	public String getRsnFlags() {
		return RsnFlags;
	}
	public void setRsnFlags(String rsnFlags) {
		RsnFlags = rsnFlags;
	}
	public String getSsid() {
		return Ssid;
	}
	@XmlElement
	public void setSsid(String ssid) {
		Ssid = ssid;
	}
	public String getFrequency() {
		return Frequency;
	}
	@XmlElement
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getHwAddress() {
		return HwAddress;
	}
	@XmlElement
	public void setHwAddress(String hwAddress) {
		HwAddress = hwAddress;
	}
	public String getMode() {
		return Mode;
	}
	@XmlElement
	public void setMode(String mode) {
		Mode = mode;
	}
	public String getMaxBitrate() {
		return MaxBitrate;
	}
	@XmlElement
	public void setMaxBitrate(String maxBitrate) {
		MaxBitrate = maxBitrate;
	}
	public String getStrength() {
		return Strength;
	}
	@XmlElement
	public void setStrength(String strength) {
		Strength = strength;
	}
}
