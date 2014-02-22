/**
 * 
 */
package nl.tudelft.da.lab1.process;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.tudelft.da.lab1.commom.IProcessInterface;


/**
 * @author vincentgong
 *
 */
public class ProcessServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			   System.setSecurityManager(new RMISecurityManager());

			   IProcessInterface process = new Process();
			   Registry registry = LocateRegistry.createRegistry(3233);
	            registry.rebind("TEST", process);

			   System.out.println("Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Server failed: " + e);
				}
	}

}
