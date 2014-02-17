

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
 
public class AdditionServer {
	   public static void main (String[] argv) {
		   try {
			   System.setSecurityManager(new RMISecurityManager());
 
			   Addition addition = new Addition();			   		   
//			   Naming.rebind("rmi://localhost/Addition", addition);
			   
			   Registry registry = LocateRegistry.createRegistry(3233);
	           registry.rebind("Addition", addition);
			   System.out.println("Addition Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Addition Server failed: " + e);
			   }
		   }
}