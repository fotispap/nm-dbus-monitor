package gr.uoa.di.scan.dbus.gui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class DomainLayout extends GridLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField Domain = new TextField("Domain");
	
	public DomainLayout() {
		addComponent(Domain);
		setSpacing(true);
        setMargin(true);
	}
}
