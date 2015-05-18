package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class WiredLayout extends GridLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField HwAddress = new TextField("HwAddress");
	private TextField PermHwAddress = new TextField("PermHwAddress");
	private TextField Speed = new TextField("Speed (Mb/s)");
	
	public WiredLayout() {
		super(3,3);
		setSpacing(true);
        setMargin(true);
		addComponent( HwAddress );
		addComponent( PermHwAddress );
		addComponent( Speed );
		
	}
}
