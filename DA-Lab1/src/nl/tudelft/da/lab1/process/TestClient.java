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
		SClock sc = new SClock();

		try {
			System.setSecurityManager(new RMISecurityManager());
			Registry registry = LocateRegistry.getRegistry("145.94.181.38", 3233);
			process0 = (IProcessInterface) registry.lookup("GX");
//			process1 = (IProcessInterface) registry.lookup("Process1");
			int i = 0;
			while(true){
				Thread.sleep(1500);
				//Msg msg0 = new Msg("message!"+i, "145.94.181.38", 3233, "GX", sc.increase().currentClock());
//				Msg msg1 = new Msg("message!"+i, "127.0.0.1", 3233, "Process1", sc.increase());
				
				//process0.post(msg0);
//				process1.post(msg1);
				System.out.println("Message has been sent. ");
			}
			

		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}

}
