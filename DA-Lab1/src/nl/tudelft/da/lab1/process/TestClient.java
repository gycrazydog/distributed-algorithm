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
		IProcessInterface process0;
		IProcessInterface process1;

		try {
			System.setSecurityManager(new RMISecurityManager());
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 3233);
			process0 = (IProcessInterface) registry.lookup("Pro0");
			process1 = (IProcessInterface) registry.lookup("Pro1");


			Msg msg0 = new Msg("test0 message!", "127.0.0.1", 3233, "Pro0", new SClock());
			Msg msg1 = new Msg("test1 message!", "127.0.0.1", 3233, "Pro1", new SClock());

			process0.post(msg0);
			process1.post(msg1);
			
			System.out.println("Message has been sent. ");

		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}

}
