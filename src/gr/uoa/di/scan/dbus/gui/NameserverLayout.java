package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class NameserverLayout extends GridLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField Nameserver = new TextField("Nameserver");
	
	public NameserverLayout() {
		addComponent(Nameserver);
		setSpacing(true);
        setMargin(true);
	}
}
