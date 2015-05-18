package gr.uoa.di.scan.dbus.service.networkmanager;

import gr.uoa.di.scan.dbus.memory.GlobalMemory;
import gr.uoa.di.scan.dbus.server.GrizzlyThread;
import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.WiredDevice;
import gr.uoa.di.scan.dbus.server.resources.WirelessDevice;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_GeneralState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;

import networkmanager.NetworkManagerThread;

import org.freedesktop.DBus;
import org.freedesktop.NetworkManager;
import org.freedesktop.NetworkManager.Device;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;


import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;




class NetworkManagerThread implements Runnable {
	
	private boolean NetworkingEnabled;
	private boolean WirelessEnabled;
	private boolean WirelessHardwareEnabled;
	private boolean WwanEnabled;
	private boolean WwanHardwareEnabled;
	private boolean WimaxEnabled;
	private boolean WimaxHardwareEnabled;
	private List<Path> ActiveConnections;
	private String Version;
	private int State;
	
	public boolean isNetworkingEnabled() {
		return NetworkingEnabled;
	}
	public void setNetworkingEnabled(boolean networkingEnabled) {
		NetworkingEnabled = networkingEnabled;
	}
	public boolean isWirelessEnabled() {
		return WirelessEnabled;
	}
	public void setWirelessEnabled(boolean wirelessEnabled) {
		WirelessEnabled = wirelessEnabled;
	}
	public boolean isWirelessHardwareEnabled() {
		return WirelessHardwareEnabled;
	}
	public void setWirelessHardwareEnabled(boolean wirelessHardwareEnabled) {
		WirelessHardwareEnabled = wirelessHardwareEnabled;
	}
	public boolean isWwanEnabled() {
		return WwanEnabled;
	}
	public void setWwanEnabled(boolean wwanEnabled) {
		WwanEnabled = wwanEnabled;
	}
	public boolean isWwanHardwareEnabled() {
		return WwanHardwareEnabled;
	}
	public void setWwanHardwareEnabled(boolean wwanHardwareEnabled) {
		WwanHardwareEnabled = wwanHardwareEnabled;
	}
	public boolean isWimaxEnabled() {
		return WimaxEnabled;
	}
	public void setWimaxEnabled(boolean wimaxEnabled) {
		WimaxEnabled = wimaxEnabled;
	}
	public boolean isWimaxHardwareEnabled() {
		return WimaxHardwareEnabled;
	}
	public void setWimaxHardwareEnabled(boolean wimaxHardwareEnabled) {
		WimaxHardwareEnabled = wimaxHardwareEnabled;
	}
	public List<Path> getActiveConnections() {
		return ActiveConnections;
	}
	public void setActiveConnections(List<Path> activeConnections) {
		ActiveConnections = activeConnections;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public DBusConnection getConn() {
		return conn;
	}
	public void setConn(DBusConnection conn) {
		this.conn = conn;
	}
	public static boolean isFinished() {
		return finished;
	}

	private DBusConnection conn;
	
	// To do: Synchronize it
	
	private static volatile boolean finished;
	public static void setFinished(boolean finish) {
		finished = finish;
	}
	@Override
	public void run() {
		GlobalMemory gm = GlobalMemory.getInstance();
		HashMap<String,DeviceWrap> hm = gm.getHm();
	
		while(!finished) {
			
			try {
				// Connect to the bus
				conn = DBusConnection.getConnection(DBusConnection.SYSTEM);
				// Get the main network manager object
				NetworkManager nm = (NetworkManager) conn.getRemoteObject("org.freedesktop.NetworkManager", "/org/freedesktop/NetworkManager");
				DBus.Introspectable nm_intro =  conn.getRemoteObject("org.freedesktop.NetworkManager", "/org/freedesktop/NetworkManager", DBus.Introspectable.class);
				DBus.Properties nm_props_ro = conn.getRemoteObject("org.freedesktop.NetworkManager", "/org/freedesktop/NetworkManager", DBus.Properties.class);
				Map<String,Variant> nm_props = nm_props_ro.GetAll("org.freedesktop.NetworkManager");
				// Get the general Network Manager Properties
				update_nm_properties(nm_props);
				// Get list of devices and their properties
				List<DBusInterface> dev_list = nm.GetDevices();
				Device dev = (Device) dev_list.get(0);				
				for(int i=0;i<dev_list.size();i++) {
					DBus.Introspectable dev_intro = (DBus.Introspectable) dev_list.get(i);
					DBus.Properties dev_props = (DBus.Properties) dev_list.get(i);
					Map<String,Variant> dev_props_map = dev_props.GetAll("org.freedesktop.NetworkManager.Device");
					Variant tmp = dev_props_map.get("DeviceType");
					UInt32 tmp_int = (UInt32) tmp.getValue();
					int type = tmp_int.intValue();
					if(type==1) {
						DeviceWrap dev1 = new WiredDevice();
						dev1.getDevInfo().setDeviceProps(dev_props_map);
						Map<String,Variant> devtype_props_map = dev_props.GetAll("org.freedesktop.NetworkManager.Device.Wired");
						dev1.setDevTypeInfo(devtype_props_map);
						dev1.getDevInfo().setDeviceType(dev1.getDevInfo().getDeviceTypeString(type));
						//System.out.println(":"+dev1.getDevInfo().getInterface());
						hm.put(dev1.getDevInfo().getInterface(), dev1);
						
					}
					else if(type==2) {
						DeviceWrap dev1 = new WirelessDevice();
						dev1.getDevInfo().setDeviceProps(dev_props_map);
						Map<String,Variant> devtype_props_map = dev_props.GetAll("org.freedesktop.NetworkManager.Device.Wireless");
						dev1.setDevTypeInfo(devtype_props_map);
						dev1.getDevInfo().setDeviceType(dev1.getDevInfo().getDeviceTypeString(type));
						//	System.out.println(":"+dev1.getDevInfo().getInterface());
						//dev1.printProperties();
	
						hm.put(dev1.getDevInfo().getInterface(), dev1);
	
					}
				}
				//GlobalMemory.getInstance().printHm();
				DAO_GeneralState gst = new DAO_GeneralState();
				gst.setNetworkingEnabled(String.valueOf(NetworkingEnabled));
				gst.setWimaxEnabled(String.valueOf(WimaxEnabled));
				gst.setWimaxHardwareEnabled(String.valueOf(WimaxHardwareEnabled));
				gst.setWwanEnabled(String.valueOf(WwanEnabled));
				gst.setWwanHardwareEnabled(String.valueOf(WwanHardwareEnabled));
				gst.setWirelessEnabled(String.valueOf(WirelessEnabled));
				gst.setWirelessHardwareEnabled(String.valueOf(WirelessHardwareEnabled));

				GlobalMemory.getInstance().setGeneral(gst);
				
			} catch (DBusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void update_nm_properties(Map<String,Variant> props_map) {
		Variant type = props_map.get("NetworkingEnabled");
		NetworkingEnabled = (Boolean) type.getValue();
		type = props_map.get("WirelessEnabled");
		WirelessEnabled = (Boolean) type.getValue();
		type = props_map.get("WirelessHardwareEnabled");
		WirelessHardwareEnabled = (Boolean) type.getValue();
		type = props_map.get("WwanEnabled");
		WwanEnabled = (Boolean) type.getValue();
		type = props_map.get("WwanHardwareEnabled");
		WwanHardwareEnabled = (Boolean) type.getValue();
		type = props_map.get("WimaxHardwareEnabled");
		WimaxHardwareEnabled = (Boolean) type.getValue();
		type = props_map.get("WimaxEnabled");
		WimaxEnabled = (Boolean) type.getValue();
		type = props_map.get("Version");
		Version = (String) type.getValue();
		type = props_map.get("ActiveConnections");
		//List<Path> value = (List<Path>) type.getValue();
		ActiveConnections = (List<Path>) type.getValue();
		System.out.println("-------->");
		//ActiveConnections = new Vector<String>();
		//ActiveConnections = Vector<String> type.getValue();
		type = props_map.get("State");
		UInt32 tmp = (UInt32) type.getValue();
		State = tmp.intValue();
		
	}
	
}



public class Mainthread {
	/**
	 * @param args
	 */
	volatile static boolean finished;
	public static void main(String[] args) {
		finished = false;
		System.out.println(System.getProperty("user.dir"));
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("properties.txt"));
			String baseUri;
			baseUri = br.readLine();
			br.close();
			final Thread t = new Thread(new NetworkManagerThread());
			t.start();
			System.out.println("NetworkManager thread started");
			final Thread t1 = new Thread(new GrizzlyThread(baseUri));
			t1.start();
			// Uncomment for Ctr+c interception
		/*	final Thread mainThread = Thread.currentThread();
			Runtime.getRuntime().addShutdownHook(new Thread() {
			    public void run() {
			        NetworkManagerThread.setFinished( true);
			        try {
			        	System.out.println("Shuting down...");
						t1.join();
						t.join();
			        	mainThread.join();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}); */
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
