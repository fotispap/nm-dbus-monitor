package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class GeneralLayout extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3778282040493452169L;
	private TextField NetworkingEnabled = new TextField("Networking Enabled: ");
	private TextField WirelessEnabled  = new TextField("Wireless Enabled ");
	private TextField WirelessHardwareEnabled  = new TextField("Wireless Hardware Enabled ");
	private TextField WwanEnabled = new TextField("Wwan Enabled ");
	private TextField WwanHardwareEnabled = new TextField("Wwan Hardware Enabled ");
	private TextField WimaxEnabled  = new TextField("WiMax Enabled");
	private TextField WimaxHardwareEnabled = new TextField("Wimax Hardware Enabled: ");
	
	public GeneralLayout() {
		setSpacing(true);
        setMargin(true);
	    	  addComponent(NetworkingEnabled);
	        addComponent(WirelessEnabled);
	        addComponent(WirelessHardwareEnabled);
	        addComponent(WwanEnabled);
	        addComponent(WwanHardwareEnabled);
	        addComponent(WimaxEnabled);
	        addComponent(WimaxHardwareEnabled);
	}
}
