package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class Dhcp4ConfigLayout extends GridLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField DomainName= new TextField("DomainName");
	private TextField Expiry= new TextField("Expiry");
	private TextField BroadCastAddress= new TextField("BroadCastAddress");
	private TextField DHCPMessageType= new TextField("DHCPMessageType");
	private TextField DHCPLeaseTime= new TextField("DHCPLeaseTime");
	private TextField IPAddress= new TextField("IPAddress");
	private TextField Routers= new TextField("Routers");
	private TextField SubnetMask= new TextField("SubnetMask");
	private TextField DomainNameServers= new TextField("DomainNameServers");
	private TextField NetworkNumber= new TextField("NetworkNumber");
	private TextField DHCPServerIdentifier= new TextField("DHCPServerIdentifier");
	
	public Dhcp4ConfigLayout(){
		super(5, 3);
        setSpacing(true);
        setSpacing(true);
        setMargin(true);
		addComponent( DomainName);
		addComponent( Expiry);
		addComponent( BroadCastAddress);
		addComponent( DHCPMessageType);
		addComponent( DHCPLeaseTime);
		addComponent( IPAddress);
		addComponent( Routers);
		addComponent( SubnetMask);
		addComponent( DomainNameServers);
		addComponent( NetworkNumber);
		addComponent( DHCPServerIdentifier);
	}
}
