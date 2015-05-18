package gr.uoa.di.scan.dbus.server.resources;

import java.util.Map;

import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;

public class AccessPoint {
	private String Flags;
	private String WpaFlags;
	private int RsnFlags;
	private byte[] Ssid;
	private int Frequency;
	private String HwAddress;
	private int Mode;
	private int MaxBitrate;
	private byte Strength;
	public String getFlags() {
		return Flags;
	}
	public void setFlags(String flags) {
		Flags = flags;
	}
	public String getWpaFlags() {
		return WpaFlags;
	}
	public void setWpaFlags(String wpaFlags) {
		WpaFlags = wpaFlags;
	}
	public int getRsnFlags() {
		return RsnFlags;
	}
	public void setRsnFlags(int rsnFlags) {
		RsnFlags = rsnFlags;
	}
	public byte[] getSsid() {
		return Ssid;
	}
	public void setSsid(byte[] ssid) {
		Ssid = ssid;
	}
	public int getFrequency() {
		return Frequency;
	}
	public void setFrequency(int frequency) {
		Frequency = frequency;
	}
	public String getHwAddress() {
		return HwAddress;
	}
	public void setHwAddress(String hwAddress) {
		HwAddress = hwAddress;
	}
	public int getMode() {
		return Mode;
	}
	public void setMode(int mode) {
		Mode = mode;
	}
	public int getMaxBitrate() {
		return MaxBitrate;
	}
	public void setMaxBitrate(int maxBitrate) {
		MaxBitrate = maxBitrate;
	}
	public byte getStrength() {
		return Strength;
	}
	public void setStrength(byte strength) {
		Strength = strength;
	}
	public void setProperties(Map<String,Variant> props) {
		Variant type = props.get("HwAddress");
		HwAddress = (String) type.getValue();
		if(props.containsKey("Flags")) {
			type = props.get("Flags");
			UInt32 temp = (UInt32) type.getValue();
			
			Flags = getFlagsString(temp.intValue());
		}
		if(props.containsKey("WpaFlags")) {
			type = props.get("WpaFlags");
			UInt32 temp = (UInt32) type.getValue();
			WpaFlags = getFlagsString(temp.intValue());
		}
		if(props.containsKey("MaxBitrate")) {
			type = props.get("MaxBitrate");
			UInt32 temp = (UInt32) type.getValue();
			MaxBitrate = temp.intValue();
		}
		if(props.containsKey("Strength")) {
			type = props.get("Strength");
			byte temp = (Byte) type.getValue();
			Strength = temp;
		}
		if(props.containsKey("Ssid")) {
			type = props.get("Ssid");
			byte[] temp = (byte[]) type.getValue();
			Ssid = temp;
		}
		if(props.containsKey("RsnFlags")) {
			type = props.get("RsnFlags");
			UInt32 temp = (UInt32) type.getValue();
			RsnFlags = temp.intValue();
		}
		if(props.containsKey("Frequency")) {
			type = props.get("Frequency");
			UInt32 temp = (UInt32) type.getValue();
			Frequency = temp.intValue();
		}
		if(props.containsKey("Mode")) {
			type = props.get("Mode");
			UInt32 temp = (UInt32) type.getValue();
			Mode = temp.intValue();
		}
	
	}
	public String getFlagsString(int flags) {
		String flags_string = new String();
		switch(flags) {
		case 0: flags_string = "NM_802_11_AP_FLAGS_NONE";
		case 1: flags_string = "NM_802_11_AP_FLAGS_PRIVACY";
		}
		return flags_string;
	}
	public String getWpaFlagsString(int flags) {
		String flags_string = new String();
		switch(flags) {
		case 0: flags_string = "NM_802_11_AP_SEC_NONE"; 
		case 1: flags_string = "NM_802_11_AP_SEC_PAIR_WEP40"; 
		case 2: flags_string = "NM_802_11_AP_SEC_PAIR_WEP104";
		case 4: flags_string  = "NM_802_11_AP_SEC_PAIR_TKIP"; 
		case 8: flags_string = "NM_802_11_AP_SEC_PAIR_CCMP"; 
		case 10: flags_string = "NM_802_11_AP_SEC_GROUP_WEP40";
		default: flags_string = "NONE"; /*	NM_802_11_AP_SEC_GROUP_WEP104 = 0x20
			NM_802_11_AP_SEC_GROUP_TKIP = 0x40
			NM_802_11_AP_SEC_GROUP_CCMP = 0x80
			NM_802_11_AP_SEC_KEY_MGMT_PSK = 0x100
			NM_802_11_AP_SEC_KEY_MGMT_802_1X = 0x200 */
		}
		return flags_string;
	}
	
}
