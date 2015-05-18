package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class AccessPointLayout extends GridLayout {
	/**
	 * 
	 */
	private TextField Flags = new TextField("Flags");
	private TextField WpaFlags = new TextField("WpaFlags");
	private TextField RsnFlags = new TextField("RsnFlags");
	private TextField Ssid = new TextField("Ssid");
	private TextField Frequency = new TextField("Frequency (Mhz)");
	private TextField HwAddress = new TextField("HwAddress");
	private TextField Mode = new TextField("Mode");
	private TextField MaxBitrate = new TextField("MaxBitrate (Kb/s)");
	private TextField Strength = new TextField("Strength (percentage %)");
	private static final long serialVersionUID = 1L;
	
	public AccessPointLayout() {
		super(3,4);
		setSpacing(true);
        setMargin(true);
        addComponent( Flags );
		addComponent( WpaFlags );
		addComponent( RsnFlags );
		addComponent( Ssid );
		addComponent( Frequency );
		addComponent( HwAddress );
		addComponent( Mode );
		addComponent( MaxBitrate );
		addComponent( Strength );
	}
	
	
}
