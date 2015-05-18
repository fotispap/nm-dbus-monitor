package gr.uoa.di.scan.dbus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gr.uoa.di.scan.dbus.memory.GlobalMemory;
import gr.uoa.di.scan.dbus.server.resources.DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_AccessPoint;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Adr_list;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Device;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_DeviceList;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_DeviceWrap;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Dhcp4Config;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Domain;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_GeneralState;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_IP4Config;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_Nameserver;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_WiredDevice;
import gr.uoa.di.scan.dbus.server.resources.dao.DAO_WirelessDevice;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.core.MediaType;



import com.google.gwt.animation.client.Animation;
import com.google.gwt.dev.ModuleTabPanel.Session;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;



class GuiSettings {
	private String Address;
	
	public GuiSettings() {
		Address = "http://localhost:9998/mainpath/";
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
}
@SuppressWarnings("serial")
@Theme("runo")
public class ApneaUI extends UI{

	static final GuiSettings settings = new GuiSettings();
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApneaUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	@Override
	protected void init(VaadinRequest request) {
		//final AbsoluteLayout root = new AbsoluteLayout();
		final AbsoluteLayout layout = new AbsoluteLayout();
		//final AbsoluteLayout header = new AbsoluteLayout();
		//final HorizontalLayout footer = new HorizontalLayout();
		//header.setMargin(true);
		//layout.setMargin(true);
		//Label lb = new Label("this s label");
		setContent(layout);
		TabSheet tabsheet = new TabSheet();
		
//		tabsheet.setHeight(100,Unit.PERCENTAGE);
		Embedded e = new Embedded(null,	new ThemeResource("scan_logo.png"));	
		layout.addComponent(e, "left: 270px; top: 15px;");
		layout.addComponent(tabsheet, "left: 15px; top: 200px;");
		tabsheet.setSizeUndefined();
		tabsheet.setWidth(1500, Unit.PIXELS);
		tabsheet.setHeight(1500, Unit.PIXELS);
		final VerticalLayout tab1 = new VerticalLayout();
		final VerticalLayout tab1_area = new VerticalLayout();
		final VerticalLayout tab3 = new VerticalLayout();
		tab1.addComponent(new Embedded(null));
		tab1.addComponent(new Label("This is the device information tab"));
		tabsheet.addTab(tab3,"Server");
		tab1_area.setMargin(true);
		tab1.setMargin(true);
		tab3.setMargin(true);
		tabsheet.addTab(tab1, "Devices");
	//	settings.setAddress("http://localhost:9998/mainpath/");
		tab3.addComponent(new Embedded(null));
		tab3.addComponent(new Label("Rest Server Settings"));
		final TextField server_adr = new TextField("Rest Server Address");
		server_adr.setValue(settings.getAddress());
		Button server_adr_but = new Button("Save");
		tab3.addComponent(server_adr);
		tab3.addComponent(server_adr_but);
		tab1_area.setSizeUndefined();
		tab1_area.setSpacing(true);
		server_adr_but.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				settings.setAddress(server_adr.getValue());

			}
		});
		// This tab gets its caption from the component caption
		VerticalLayout tab2 = new VerticalLayout();
		tab2.addComponent(new Embedded(null));
		tab2.setCaption("General");
		tab2.setMargin(true);
		tabsheet.addTab(tab2);
        final HashMap<String,DeviceLayout> dev_layout_map = new HashMap<String,DeviceLayout>();
        final HashMap<String,Dhcp4ConfigLayout> dhcp_layout_map = new HashMap<String,Dhcp4ConfigLayout>();
        final HashMap<String,List<AddressesLayout>> addr_layout_map = new HashMap<String,List<AddressesLayout>>();
        final HashMap<String,List<DomainLayout>> dom_layout_map = new HashMap<String,List<DomainLayout>>();
        final HashMap<String,List<NameserverLayout>> nm_layout_map = new HashMap<String,List<NameserverLayout>>();

        final HashMap<String,GridLayout> dev_spec_layout_map = new HashMap<String,GridLayout>();
        final HashMap<String,AccessPointLayout> apl_layout_map = new HashMap<String,AccessPointLayout>();
        List<AddressesLayout> addr_layout_list = new ArrayList<AddressesLayout>();
        List<DomainLayout> dom_layout_list = new ArrayList<DomainLayout>();
        List<NameserverLayout> nm_layout_list = new ArrayList<NameserverLayout>();
        //button get rest info
		//ip 
		try {
		ClientConfig config2 = new DefaultClientConfig();
        WebResource service2 = Client.create(config2).resource(settings.getAddress());
        ClientResponse response = service2.path("Devices").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)  
                .get(ClientResponse.class);
        DAO_DeviceList li = new DAO_DeviceList();

        li = response.getEntity(DAO_DeviceList.class);
        
        ClientResponse response2 = service2.path("General").type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)  
                .get(ClientResponse.class);
	
        DAO_GeneralState g_state = new DAO_GeneralState();
        g_state = response2.getEntity(DAO_GeneralState.class);
        GeneralLayout gen_layout = new GeneralLayout();
		FieldGroup fieldGroup1 = new FieldGroup(new BeanItem<DAO_GeneralState>(g_state));
        fieldGroup1.bindMemberFields(gen_layout);
        
        final NativeSelect select = new NativeSelect("Device List");
        
        for(int i =0; i<li.getDeviceList().size();i++) {
        	
    			if(li.getDeviceList().get(i).getDevInfo().getDeviceType().equals("ETHERNET")) {

                final DeviceLayout devLayout = new DeviceLayout();

        		DAO_DeviceWrap dev = new DAO_WiredDevice();
        		dev =  li.getDeviceList().get(i);
            	final WiredLayout wir_layout = new WiredLayout();
            	 FieldGroup fieldGroup_wir = new FieldGroup(new BeanItem<DAO_WiredDevice>(((DAO_WiredDevice) dev)));
                 fieldGroup_wir.bindMemberFields(wir_layout);
            	dev_spec_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), wir_layout);
                select.addItem(((DAO_WiredDevice) dev).getDevInfo().getInterface());
                if(((DAO_WiredDevice) dev).getDevInfo().getIp4config()!=null) {
                	DAO_IP4Config dao_ip4c = new DAO_IP4Config();
                	dao_ip4c = ((DAO_WiredDevice) dev).getDevInfo().getIp4config();
                	List<DAO_Adr_list> addr_list = ((DAO_WiredDevice) dev).getDevInfo().getIp4config().getAddresses();
                	List<DAO_Domain> dom_list1 = new ArrayList<DAO_Domain>();
                	List<DAO_Nameserver> nm_list1 = new ArrayList<DAO_Nameserver>();
                	dom_list1 = ((DAO_WiredDevice) dev).getDevInfo().getIp4config().getDomains();
                	nm_list1 = ((DAO_WiredDevice) dev).getDevInfo().getIp4config().getNameservers();
                	if(addr_list!=null) {
                	for(int k=0;k<addr_list.size();k++) {
                		AddressesLayout adr_layout = new AddressesLayout();
                		 FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Adr_list>(addr_list.get(k)));
                		 fieldGroup_ip4_adr.bindMemberFields(adr_layout);
                		 addr_layout_list.add(adr_layout);
                	}
                	for(int k=0;k<dom_list1.size();k++) {
                		DomainLayout dom_layout = new DomainLayout();
                		FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Domain>(dom_list1.get(k)));
                		fieldGroup_ip4_adr.bindMemberFields(dom_layout);
                		dom_layout_list.add(dom_layout);
                	}
                	for(int k=0;k<nm_list1.size();k++) {
                		NameserverLayout nm_layout = new NameserverLayout();
                		FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Nameserver>(nm_list1.get(k)));
                		fieldGroup_ip4_adr.bindMemberFields(nm_layout);
                		nm_layout_list.add(nm_layout);
                	}
                	addr_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), addr_layout_list);
                	dom_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), dom_layout_list);
                	nm_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), nm_layout_list);

                	}
                }
                if(((DAO_WiredDevice) dev).getDevInfo().getDhcp4config()!=null) {
                	final Dhcp4ConfigLayout dhcp_layout = new Dhcp4ConfigLayout();
                    FieldGroup fieldGroup_dhcp = new FieldGroup(new BeanItem<DAO_Dhcp4Config>(((DAO_WiredDevice) dev).getDevInfo().getDhcp4config()));
                    fieldGroup_dhcp.bindMemberFields(dhcp_layout);
                    dhcp_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), dhcp_layout);
                }
                FieldGroup fieldGroup = new FieldGroup(new BeanItem<DAO_Device>(((DAO_WiredDevice) dev).getDevInfo()));
                fieldGroup.bindMemberFields(devLayout);
                dev_layout_map.put(((DAO_WiredDevice) dev).getDevInfo().getInterface(), devLayout);

        	} else {
                final DeviceLayout devLayout = new DeviceLayout();
                
        		DAO_DeviceWrap dev = new DAO_WirelessDevice();
        		dev =  li.getDeviceList().get(i);
                select.addItem(((DAO_WirelessDevice) dev).getDevInfo().getInterface());
                final WirelessLayout wirelesslayout = new WirelessLayout();
                FieldGroup fieldGroup_wir = new FieldGroup(new BeanItem<DAO_WirelessDevice>(((DAO_WirelessDevice) dev)));
                fieldGroup_wir.bindMemberFields(wirelesslayout);
                
                if(((DAO_WirelessDevice) dev).getActiveAccessPoint()!=null) {
                	AccessPointLayout apl = new AccessPointLayout();
                	FieldGroup fieldGroup_apl = new FieldGroup(new BeanItem<DAO_AccessPoint>(((DAO_WirelessDevice) dev).getActiveAccessPoint()));
                    fieldGroup_apl.bindMemberFields(apl);
                	apl_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), apl);
                	
                }
                
                dev_spec_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), wirelesslayout);
                select.addItem(((DAO_WirelessDevice) dev).getDevInfo().getInterface());
                if(((DAO_WirelessDevice) dev).getDevInfo().getDhcp4config()!=null) {

                	final Dhcp4ConfigLayout dhcp_layout = new Dhcp4ConfigLayout();
                    FieldGroup fieldGroup_dhcp = new FieldGroup(new BeanItem<DAO_Dhcp4Config>(((DAO_WirelessDevice) dev).getDevInfo().getDhcp4config()));
                    fieldGroup_dhcp.bindMemberFields(dhcp_layout);
                    dhcp_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), dhcp_layout);
                }
                if(((DAO_WirelessDevice) dev).getDevInfo().getIp4config()!=null) {
                	DAO_IP4Config dao_ip4c = new DAO_IP4Config();
                	dao_ip4c = ((DAO_WirelessDevice) dev).getDevInfo().getIp4config();
        
                	List<DAO_Adr_list> addr_list1 = new ArrayList<DAO_Adr_list>();
                	List<DAO_Domain> dom_list1 = new ArrayList<DAO_Domain>();
                	List<DAO_Nameserver> nm_list1 = new ArrayList<DAO_Nameserver>();
                	addr_list1 = ((DAO_WirelessDevice) dev).getDevInfo().getIp4config().getAddresses();
                	dom_list1 = ((DAO_WirelessDevice) dev).getDevInfo().getIp4config().getDomains();
                	nm_list1 = ((DAO_WirelessDevice) dev).getDevInfo().getIp4config().getNameservers();
                	if(addr_list1!=null) {
                	for(int k=0;k<addr_list1.size();k++) {
                		AddressesLayout adr_layout = new AddressesLayout();
                		FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Adr_list>(addr_list1.get(k)));
                		fieldGroup_ip4_adr.bindMemberFields(adr_layout);
                		addr_layout_list.add(adr_layout);
                	}
                	for(int k=0;k<dom_list1.size();k++) {
                		DomainLayout dom_layout = new DomainLayout();
                		FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Domain>(dom_list1.get(k)));
                		fieldGroup_ip4_adr.bindMemberFields(dom_layout);
                		dom_layout_list.add(dom_layout);
                	}
                	for(int k=0;k<nm_list1.size();k++) {
                		NameserverLayout nm_layout = new NameserverLayout();
                		FieldGroup fieldGroup_ip4_adr = new FieldGroup(new BeanItem<DAO_Nameserver>(nm_list1.get(k)));
                		fieldGroup_ip4_adr.bindMemberFields(nm_layout);
                		nm_layout_list.add(nm_layout);
                	}

                	addr_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), addr_layout_list);
                	dom_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), dom_layout_list);
                	nm_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), nm_layout_list);


                	
                	}
                	
                
                }
                if(((DAO_WirelessDevice) dev).getActiveAccessPoint()!=null) {
                	
                }
                FieldGroup fieldGroup = new FieldGroup(new BeanItem<DAO_Device>(((DAO_WirelessDevice) dev).getDevInfo()));
                fieldGroup.bindMemberFields(devLayout);
                dev_layout_map.put(((DAO_WirelessDevice) dev).getDevInfo().getInterface(), devLayout);
                

        	}
        }
        tab1.addComponent(select);
		tab1.addComponent(tab1_area);
		tab2.addComponent(gen_layout);
     // Create a field group and use it to bind the fields in the layout
        select.setNullSelectionAllowed(false);
        select.getValue();
        select.addValueChangeListener(
        	    new Property.ValueChangeListener() {
        	    public void valueChange(ValueChangeEvent event) {
        	        String i_face = (String) select.getValue();
        	        tab1_area.removeAllComponents();
        	        Panel pan1 = new Panel("General Device Information");
        	        
        	        pan1.setContent(dev_layout_map.get(i_face));
        	       // tab1_area.addComponent(new Label("General Device Information:"));
        	        
        	        tab1_area.addComponent(pan1);
        	        Panel pan2 = new Panel("Type Specific Device Information:");
        	        pan2.setContent(dev_spec_layout_map.get(i_face));
        	        tab1_area.addComponent(pan2);
        	        if(apl_layout_map.get(i_face)!=null) {
        	        	 tab1_area.addComponent(new Label("Active Access Point Information:"));
             	         tab1_area.addComponent(apl_layout_map.get(i_face));
        	        }
        	        if(dhcp_layout_map.get(i_face)!=null) {
        	        	Panel pan = new Panel("DHCP4Configuration");
        	        	//pan.setStyleName();
        	        	pan.setContent(dhcp_layout_map.get(i_face));
            	      //  tab1_area.addComponent(new Label("DHCP4 Configuration:"));
            	       //tab1_area.addComponent(dhcp_layout_map.get(i_face));
             	       tab1_area.addComponent(pan);
        	        }
        	        if(addr_layout_map.get(i_face)!=null) {
            	        Panel pan = new Panel("IP4Config");
            	        

        	        	for(int k=0;k<addr_layout_map.get(i_face).size();k++) {
        	        		pan.setContent(addr_layout_map.get(i_face).get(k));
        	        	}
        	        	tab1_area.addComponent(pan);
        	        }
        	        if(dom_layout_map.get(i_face)!=null) {
        	        	for(int k=0;k<dom_layout_map.get(i_face).size();k++) {
        	        		tab1_area.addComponent(dom_layout_map.get(i_face).get(k));
        	        	}
        	        }
        	        if(nm_layout_map.get(i_face)!=null) {
        	        	for(int k=0;k<nm_layout_map.get(i_face).size();k++) {
        	        		tab1_area.addComponent(nm_layout_map.get(i_face).get(k));
        	        	}
        	        }
        	        
        	        
        	    }
        	});       
        	select.setImmediate(true);
        
     // Create a selection component
        
	} catch(Exception ex) {
			tabsheet.removeComponent(tab1);
			tabsheet.removeComponent(tab2);
			tab3.addComponent(new Label("Error contacting REST server"));
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
		

       // System.out.println(li.getDeviceNo());
	}

}