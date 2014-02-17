

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
 
public class MultipleServer {
	   public static void main (String[] argv) {
		   try {
			   System.setSecurityManager(new RMISecurityManager());
 
			   Multiple Multi = new Multiple();			   		   
			  // Naming.rebind("rmi://localhost/Multi", Multi);
 
			   Registry registry = LocateRegistry.createRegistry(3232);
	            registry.rebind("multi", Multi);
			   
			   System.out.println("Multiple Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Multiple Server failed: " + e);
				}
		   }
}