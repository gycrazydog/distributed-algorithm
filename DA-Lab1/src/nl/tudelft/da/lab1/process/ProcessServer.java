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

			Registry registry = LocateRegistry.createRegistry(3233);
			IProcessInterface process0 = new Process("process0");
			IProcessInterface process1 = new Process("process1");
			registry.rebind("Pro0", process0);
			registry.rebind("Pro1", process1);

			System.out.println("Server is ready.");
		} catch (Exception e) {
			System.out.println("Server failed: " + e);
		}
	}

}
