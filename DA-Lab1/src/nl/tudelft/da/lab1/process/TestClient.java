/**
 * 
 */
package nl.tudelft.da.lab1.process;


import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.tudelft.da.lab1.commom.IProcessInterface;
import nl.tudelft.da.lab1.message.Msg;

/**
 * @author vincentgong
 *
 */
public class TestClient{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IProcessInterface process;
		try {
			System.setSecurityManager(new RMISecurityManager());
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 3233);
			process = (IProcessInterface) registry.lookup("TEST");

			Msg msg = new Msg("test2 message!", "127.0.0.1", 3222, "YEBE");
			process.post(msg);
			
			System.out.println("Message has been sent. ");

		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}

}
