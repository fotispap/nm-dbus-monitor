package gr.uoa.di.scan.dbus.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.sql.SQLException;

import javax.servlet.Servlet;

import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.spi.container.servlet.ServletContainer;


public class serverinit {
   
   
    private static GrizzlyWebServer grizzly = null;


    /**
     * Starts Yapser with the provided server address and proxy (both may be null)
     * @param serverAddress - the remote internet address that is proxied (null means that all internet addresses are proxied)
     * @param proxy - the proxy server Yapser itself is sitting behind
     * @throws IOException
     */

   
    public static void main(String [] args) throws IOException{
    	   startGrizzly();
    }

   


    private static void startGrizzly() throws IOException {
    grizzly = new GrizzlyWebServer(8888);
    addJerseyServlet(grizzly);
    try {
                    addDashboardServlet(grizzly);
            } catch (Exception e) {
                   // logger.error("Cannot initialize ZK", e);
            }
            grizzly.start();

           // logger.info("Grizzly listening on port: " + conf.getGrizzlyPortNumber());
    }
   
    private void stopGrizzly() {
            if (grizzly != null) {
                    grizzly.stop();
            }
    }
   
    private static void addJerseyServlet(GrizzlyWebServer grizzly) {
    // Jersey (serving REST resources)
           // logger.info("initializing Jersey Servlet");
    ServletContainer jersey = new ServletContainer();
    ServletAdapter sa = new ServletAdapter();
    sa.setServletInstance(jersey);
    sa.addInitParameter("com.sun.jersey.config.property.packages", "restresources");
    sa.setProperty(ServletAdapter.LOAD_ON_STARTUP, "1");
    sa.setContextPath("/rest");
    grizzly.addGrizzlyAdapter(sa, new String[]{"/rest"});
    }
   
    private static void addDashboardServlet(GrizzlyWebServer grizzly) throws Exception {
    // Dashboard Application (based on Vaadin)
           // logger.info("initializing Dashboard Servlet");
    ServletAdapter sa = initServlet("com.vaadin.terminal.gwt.server.ApplicationServlet");
    sa.setRootFolder("WebContent");
    sa.addContextParameter("productionMode", "false");
    sa.setContextPath("/apnea");
    sa.setProperty(ServletAdapter.LOAD_ON_STARTUP, "1");
    sa.addInitParameter("application", "com.example.apnea");
    grizzly.addGrizzlyAdapter(sa, new String[]{"/apnea","/VAADIN"});
    }
   
    private static ServletAdapter initServlet(String className) throws Exception {
            //logger.debug("initServlet(" + className + ")");
            ServletAdapter sa = null;
            try {
                    Servlet s = (Servlet)Class.forName(className).newInstance();
            sa = new ServletAdapter();
            sa.setServletInstance(s);
            } catch (InstantiationException e) {
                    throw new Exception(e);
            } catch (IllegalAccessException e) {
                    throw new Exception(e);
            } catch (ClassNotFoundException e) {
                    throw new Exception(e);
            }
            return sa;
    }
}
