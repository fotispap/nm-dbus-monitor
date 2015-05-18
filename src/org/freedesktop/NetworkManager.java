package org.freedesktop;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.*;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusSignal;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

public interface NetworkManager extends DBusInterface
{
   public static class DeviceRemoved extends DBusSignal
   {
      public final DBusInterface a;
      public DeviceRemoved(String path, DBusInterface a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }
   public static class DeviceAdded extends DBusSignal
   {
      public final DBusInterface a;
      public DeviceAdded(String path, DBusInterface a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }
   public static class PropertiesChanged extends DBusSignal
   {
      public final Map<String,Variant> a;
      public PropertiesChanged(String path, Map<String,Variant> a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }
   public static class StateChanged extends DBusSignal
   {
      public final UInt32 a;
      public StateChanged(String path, UInt32 a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }
   public static class CheckPermissions extends DBusSignal
   {
      public CheckPermissions(String path) throws DBusException
      {
         super(path);
      }
   }

  public UInt32 state();
  public void SetLogging(String level, String domains);
  public Map<String,String> GetPermissions();
  public void Enable(boolean enable);
  public void Sleep(boolean sleep);
  public void DeactivateConnection(DBusInterface active_connection);
  public Pair<DBusInterface, DBusInterface> AddAndActivateConnection(Map<String,Map<String,Variant>> connection, DBusInterface device, DBusInterface specific_object);
  public DBusInterface ActivateConnection(DBusInterface connection, DBusInterface device, DBusInterface specific_object);
  public DBusInterface GetDeviceByIpIface(String iface);
  public List<DBusInterface> GetDevices();

  
  
  @DBusInterfaceName("org.freedesktop.NetworkManager.IP4Config")
  public interface IP4Config extends DBusInterface {
	  
  }


 @DBusInterfaceName("org.freedesktop.NetworkManager.AccessPoint")
 public interface AccessPoint extends DBusInterface {

    public static class PropertiesChanged extends DBusSignal {

        public Map<String, Variant> properties;

        public PropertiesChanged(String path, Map<String, Variant> properties) throws DBusException {
            super(path, properties);
            this.properties = properties;
        }
    }
}

@DBusInterfaceName("org.freedesktop.NetworkManager.Device")
public interface Device extends DBusInterface {

    public static class StateChanged extends DBusSignal {

        public UInt32 newState;
        public UInt32 oldState;
        public UInt32 reason;

        public StateChanged(String path, UInt32 newState, UInt32 oldState, UInt32 reason) throws DBusException {
            super(path, newState, oldState, reason);
            this.newState = newState;
            this.oldState = oldState;
            this.reason = reason;
        }
    }

    @DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wired")
    public interface Wired extends DBusInterface {

        public static class PropertiesChanged extends DBusSignal {

            public Map<String, Variant> properties;

            public PropertiesChanged(String path, Map<String, Variant> properties) throws DBusException {
                super(path, properties);
                this.properties = properties;
            }
        }
    }

    @DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wireless")
    public interface Wireless extends DBusInterface {

        public List<DBusInterface> GetAccessPoints();

        public static class PropertiesChanged extends DBusSignal {

            public Map<String, Variant> properties;

            public PropertiesChanged(String path, Map<String, Variant> properties) throws DBusException {
                super(path, properties);
                this.properties = properties;
            }
        }

        public static class AccessPointAdded extends DBusSignal {

            public DBusInterface ap;

            public AccessPointAdded(String path, DBusInterface ap) throws DBusException {
                super(path, ap);
                this.ap = ap;
            }
        }

        public static class AccessPointRemoved extends DBusSignal {

            public DBusInterface ap;

            public AccessPointRemoved(String path, DBusInterface ap) throws DBusException {
                super(path, ap);
                this.ap = ap;
            }
        }
    }

    @DBusInterfaceName("org.freedesktop.NetworkManager.Device.Cdma")
    public interface CDMA extends DBusInterface {

        public static class PropertiesChanged extends DBusSignal {

            public Map<String, Variant> properties;

            public PropertiesChanged(String path, Map<String, Variant> properties) throws DBusException {
                super(path, properties);
                this.properties = properties;
            }
        }
    }

    @DBusInterfaceName("org.freedesktop.NetworkManager.Device.Gsm")
    public interface GSM extends DBusInterface {

        public static class PropertiesChanged extends DBusSignal {

            public Map<String, Variant> properties;

            public PropertiesChanged(String path, Map<String, Variant> properties) throws DBusException {
                super(path, properties);
                this.properties = properties;
            }
        }
    }

    @DBusInterfaceName("org.freedesktop.NetworkManager.Device.Serial")
    public interface Serial extends DBusInterface {

        public static class PppStats extends DBusSignal {

            public UInt32 rxBytes;
            public UInt32 txBytes;

            public PppStats(String path, UInt32 rxBytes, UInt32 txBytes) throws DBusException {
                super(path, rxBytes, txBytes);
                this.rxBytes = rxBytes;
                this.txBytes = txBytes;
            }
        }
    }
}
/*
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusSignal;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;*/
public interface Settings extends DBusInterface 
{
   public static class NewConnection extends DBusSignal
   {
      public final DBusInterface a;
      public NewConnection(String path, DBusInterface a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }
   public static class PropertiesChanged extends DBusSignal
   {
      public final Map<String,Variant> a;
      public PropertiesChanged(String path, Map<String,Variant> a) throws DBusException
      {
         super(path, a);
         this.a = a;
      }
   }

  public void SaveHostname(String hostname);
  public DBusInterface AddConnection(Map<String,Map<String,Variant>> connection);
  public DBusInterface GetConnectionByUuid(String uuid);
  public List<DBusInterface> ListConnections();
  public interface Connection extends DBusInterface
  {
     public static class Removed extends DBusSignal
     {
        public Removed(String path) throws DBusException
        {
           super(path);
        }
     }
     public static class Updated extends DBusSignal
     {
        public Updated(String path) throws DBusException
        {
           super(path);
        }
     }

    public Map<String,Map<String,Variant>> GetSecrets(String setting_name);
    public Map<String,Map<String,Variant>> GetSettings();
    public void Delete();
    public void Update(Map<String,Map<String,Variant>> properties);

  }
}

}


