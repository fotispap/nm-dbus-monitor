package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class WirelessLayout extends GridLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField HwAddress = new TextField("HwAddress");
	private TextField PermHwAddress = new TextField("PermHwAddress");
	private TextField Mode = new TextField("Mode");
	private TextField Bitrate = new TextField("Bitrate (Kbit/s)");
	private TextField WirelessCapabilities = new TextField("WirelessCapabalities");
	private AccessPointLayout apl;
	
	public AccessPointLayout getApl() {
		return apl;
	}

	public void setApl(AccessPointLayout apl) {
		this.apl = apl;
	}

	public WirelessLayout() {
		super(4,3);

		setSpacing(true);
        setMargin(true);
		addComponent( HwAddress);
		addComponent( PermHwAddress);
		addComponent( Mode);
		addComponent( Bitrate);
		addComponent( WirelessCapabilities);
	}
}
