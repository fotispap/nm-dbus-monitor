package gr.uoa.di.scan.dbus.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class GrizzlyThread implements Runnable {
	private String baseUri;
	
	public GrizzlyThread(String baseuri) {
		baseUri = baseuri;
		
	}
	@Override
	public void run() {
		
	        final Map<String, String> initParams = new HashMap<String, String>();

	        initParams.put("com.sun.jersey.config.property.packages", 
	                "restresources"); //rest classes

	        
	        System.out.println("Starting grizzly at " + baseUri + "...");
	        try {
				SelectorThread threadSelector = GrizzlyWebContainerFactory.create(
				        baseUri, initParams);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	

}
