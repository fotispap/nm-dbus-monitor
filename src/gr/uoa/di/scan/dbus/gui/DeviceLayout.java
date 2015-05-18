package gr.uoa.di.scan.dbus.gui;

import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Dhcp4Config;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_IP4Config;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;


public class DeviceLayout extends GridLayout {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField Udi = new TextField("Udi:");
    private TextField Driver = new TextField("Driver");

    private TextField Interface = new TextField("Interface");
    private TextField IpInterface = new TextField("IpInterface");
	private TextField Ip4Address = new TextField("Ip4Address");
	private TextField State = new TextField("State");
	private DAO_IP4Config ip4config;
	private DAO_Dhcp4Config dhcp4config;
	private TextField DeviceType = new TextField("DeviceType");
    // The name of the property is by default the name of the member field,
    // but it can be redefined with the @PropertyId annotation


    public DeviceLayout() {
        // Set up the GridLayout
        super(5, 3);
        setSpacing(true);
        setMargin(true);
        // Add the (currently unbound) fields
        addComponent(Udi);
        addComponent(Interface);
        addComponent(IpInterface);
        addComponent(Driver);
        addComponent(Ip4Address);
        addComponent(State);
        addComponent(DeviceType);
        //DeviceType.setWidth(200, Unit.PIXELS);
    }
}
