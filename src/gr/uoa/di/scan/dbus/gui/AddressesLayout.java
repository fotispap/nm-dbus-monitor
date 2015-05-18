package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class AddressesLayout extends HorizontalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField Address = new TextField("Address");
	private TextField Prefix = new TextField("Prefix");
	private TextField Gateway = new TextField("Gateway");
	
	public AddressesLayout() {
		setSpacing(true);
        setMargin(true);
        addComponent(Address);
		addComponent(Prefix);
		addComponent(Gateway);
	}
}
